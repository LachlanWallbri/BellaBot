package com.danikula.videocache;

import android.content.Context;
import android.net.Uri;
import com.danikula.videocache.file.DiskUsage;
import com.danikula.videocache.file.FileNameGenerator;
import com.danikula.videocache.file.Md5FileNameGenerator;
import com.danikula.videocache.file.TotalCountLruDiskUsage;
import com.danikula.videocache.file.TotalSizeLruDiskUsage;
import com.danikula.videocache.headers.EmptyHeadersInjector;
import com.danikula.videocache.headers.HeaderInjector;
import com.danikula.videocache.sourcestorage.SourceInfoStorage;
import com.danikula.videocache.sourcestorage.SourceInfoStorageFactory;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: classes.dex */
public class HttpProxyCacheServer {
    private static final Logger LOG = LoggerFactory.getLogger("HttpProxyCacheServer");
    private static final String PROXY_HOST = "127.0.0.1";
    private final Object clientsLock;
    private final Map<String, HttpProxyCacheServerClients> clientsMap;
    private final Config config;
    private final Pinger pinger;
    private final int port;
    private final ServerSocket serverSocket;
    private final ExecutorService socketProcessor;
    private final Thread waitConnectionThread;

    public HttpProxyCacheServer(Context context) {
        this(new Builder(context).buildConfig());
    }

    private HttpProxyCacheServer(Config config) {
        this.clientsLock = new Object();
        this.socketProcessor = Executors.newFixedThreadPool(8);
        this.clientsMap = new ConcurrentHashMap();
        this.config = (Config) Preconditions.checkNotNull(config);
        try {
            this.serverSocket = new ServerSocket(0, 8, InetAddress.getByName(PROXY_HOST));
            this.port = this.serverSocket.getLocalPort();
            IgnoreHostProxySelector.install(PROXY_HOST, this.port);
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.waitConnectionThread = new Thread(new WaitRequestsRunnable(countDownLatch));
            this.waitConnectionThread.start();
            countDownLatch.await();
            this.pinger = new Pinger(PROXY_HOST, this.port);
            LOG.info("Proxy cache server started. Is it alive? " + isAlive());
        } catch (IOException | InterruptedException e) {
            this.socketProcessor.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e);
        }
    }

    public String getProxyUrl(String str) {
        return getProxyUrl(str, true);
    }

    public String getProxyUrl(String str, boolean z) {
        if (!z || !isCached(str)) {
            return isAlive() ? appendToProxyUrl(str) : str;
        }
        File cacheFile = getCacheFile(str);
        touchFileSafely(cacheFile);
        return Uri.fromFile(cacheFile).toString();
    }

    public void registerCacheListener(CacheListener cacheListener, String str) {
        Preconditions.checkAllNotNull(cacheListener, str);
        synchronized (this.clientsLock) {
            try {
                getClients(str).registerCacheListener(cacheListener);
            } catch (ProxyCacheException e) {
                LOG.warn("Error registering cache listener", (Throwable) e);
            }
        }
    }

    public void unregisterCacheListener(CacheListener cacheListener, String str) {
        Preconditions.checkAllNotNull(cacheListener, str);
        synchronized (this.clientsLock) {
            try {
                getClients(str).unregisterCacheListener(cacheListener);
            } catch (ProxyCacheException e) {
                LOG.warn("Error registering cache listener", (Throwable) e);
            }
        }
    }

    public void unregisterCacheListener(CacheListener cacheListener) {
        Preconditions.checkNotNull(cacheListener);
        synchronized (this.clientsLock) {
            Iterator<HttpProxyCacheServerClients> it = this.clientsMap.values().iterator();
            while (it.hasNext()) {
                it.next().unregisterCacheListener(cacheListener);
            }
        }
    }

    public boolean isCached(String str) {
        Preconditions.checkNotNull(str, "Url can't be null!");
        return getCacheFile(str).exists();
    }

    public void shutdown() {
        LOG.info("Shutdown proxy server");
        shutdownClients();
        this.config.sourceInfoStorage.release();
        this.waitConnectionThread.interrupt();
        try {
            if (this.serverSocket.isClosed()) {
                return;
            }
            this.serverSocket.close();
        } catch (IOException e) {
            onError(new ProxyCacheException("Error shutting down proxy server", e));
        }
    }

    private boolean isAlive() {
        return this.pinger.ping(3, 70);
    }

    private String appendToProxyUrl(String str) {
        return String.format(Locale.US, "http://%s:%d/%s", PROXY_HOST, Integer.valueOf(this.port), ProxyCacheUtils.encode(str));
    }

    private File getCacheFile(String str) {
        return new File(this.config.cacheRoot, this.config.fileNameGenerator.generate(str));
    }

    private void touchFileSafely(File file) {
        try {
            this.config.diskUsage.touch(file);
        } catch (IOException e) {
            LOG.error("Error touching file " + file, (Throwable) e);
        }
    }

    private void shutdownClients() {
        synchronized (this.clientsLock) {
            Iterator<HttpProxyCacheServerClients> it = this.clientsMap.values().iterator();
            while (it.hasNext()) {
                it.next().shutdown();
            }
            this.clientsMap.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void waitForRequest() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket accept = this.serverSocket.accept();
                LOG.debug("Accept new socket " + accept);
                this.socketProcessor.submit(new SocketProcessorRunnable(accept));
            } catch (IOException e) {
                onError(new ProxyCacheException("Error during waiting connection", e));
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processSocket(Socket socket) {
        Logger logger;
        StringBuilder sb;
        try {
            try {
                GetRequest read = GetRequest.read(socket.getInputStream());
                LOG.debug("Request to cache proxy:" + read);
                String decode = ProxyCacheUtils.decode(read.uri);
                if (this.pinger.isPingRequest(decode)) {
                    this.pinger.responseToPing(socket);
                } else {
                    getClients(decode).processRequest(read, socket);
                }
                releaseSocket(socket);
                logger = LOG;
                sb = new StringBuilder();
            } catch (ProxyCacheException e) {
                e = e;
                onError(new ProxyCacheException("Error processing request", e));
                releaseSocket(socket);
                logger = LOG;
                sb = new StringBuilder();
            } catch (SocketException unused) {
                LOG.debug("Closing socket… Socket is closed by client.");
                releaseSocket(socket);
                logger = LOG;
                sb = new StringBuilder();
            } catch (IOException e2) {
                e = e2;
                onError(new ProxyCacheException("Error processing request", e));
                releaseSocket(socket);
                logger = LOG;
                sb = new StringBuilder();
            }
            sb.append("Opened connections: ");
            sb.append(getClientsCount());
            logger.debug(sb.toString());
        } catch (Throwable th) {
            releaseSocket(socket);
            LOG.debug("Opened connections: " + getClientsCount());
            throw th;
        }
    }

    private HttpProxyCacheServerClients getClients(String str) throws ProxyCacheException {
        HttpProxyCacheServerClients httpProxyCacheServerClients;
        synchronized (this.clientsLock) {
            httpProxyCacheServerClients = this.clientsMap.get(str);
            if (httpProxyCacheServerClients == null) {
                httpProxyCacheServerClients = new HttpProxyCacheServerClients(str, this.config);
                this.clientsMap.put(str, httpProxyCacheServerClients);
            }
        }
        return httpProxyCacheServerClients;
    }

    private int getClientsCount() {
        int i;
        synchronized (this.clientsLock) {
            i = 0;
            Iterator<HttpProxyCacheServerClients> it = this.clientsMap.values().iterator();
            while (it.hasNext()) {
                i += it.next().getClientsCount();
            }
        }
        return i;
    }

    private void releaseSocket(Socket socket) {
        closeSocketInput(socket);
        closeSocketOutput(socket);
        closeSocket(socket);
    }

    private void closeSocketInput(Socket socket) {
        try {
            if (socket.isInputShutdown()) {
                return;
            }
            socket.shutdownInput();
        } catch (SocketException unused) {
            LOG.debug("Releasing input stream… Socket is closed by client.");
        } catch (IOException e) {
            onError(new ProxyCacheException("Error closing socket input stream", e));
        }
    }

    private void closeSocketOutput(Socket socket) {
        try {
            if (socket.isOutputShutdown()) {
                return;
            }
            socket.shutdownOutput();
        } catch (IOException e) {
            LOG.warn("Failed to close socket on proxy side: {}. It seems client have already closed connection.", e.getMessage());
        }
    }

    private void closeSocket(Socket socket) {
        try {
            if (socket.isClosed()) {
                return;
            }
            socket.close();
        } catch (IOException e) {
            onError(new ProxyCacheException("Error closing socket", e));
        }
    }

    private void onError(Throwable th) {
        LOG.error("HttpProxyCacheServer error", th);
    }

    /* loaded from: classes.dex */
    private final class WaitRequestsRunnable implements Runnable {
        private final CountDownLatch startSignal;

        public WaitRequestsRunnable(CountDownLatch countDownLatch) {
            this.startSignal = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.startSignal.countDown();
            HttpProxyCacheServer.this.waitForRequest();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class SocketProcessorRunnable implements Runnable {
        private final Socket socket;

        public SocketProcessorRunnable(Socket socket) {
            this.socket = socket;
        }

        @Override // java.lang.Runnable
        public void run() {
            HttpProxyCacheServer.this.processSocket(this.socket);
        }
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private static final long DEFAULT_MAX_SIZE = 536870912;
        private File cacheRoot;
        private DiskUsage diskUsage = new TotalSizeLruDiskUsage(DEFAULT_MAX_SIZE);
        private FileNameGenerator fileNameGenerator = new Md5FileNameGenerator();
        private HeaderInjector headerInjector = new EmptyHeadersInjector();
        private SourceInfoStorage sourceInfoStorage;

        public Builder(Context context) {
            this.sourceInfoStorage = SourceInfoStorageFactory.newSourceInfoStorage(context);
            this.cacheRoot = StorageUtils.getIndividualCacheDirectory(context);
        }

        public Builder cacheDirectory(File file) {
            this.cacheRoot = (File) Preconditions.checkNotNull(file);
            return this;
        }

        public Builder fileNameGenerator(FileNameGenerator fileNameGenerator) {
            this.fileNameGenerator = (FileNameGenerator) Preconditions.checkNotNull(fileNameGenerator);
            return this;
        }

        public Builder maxCacheSize(long j) {
            this.diskUsage = new TotalSizeLruDiskUsage(j);
            return this;
        }

        public Builder maxCacheFilesCount(int i) {
            this.diskUsage = new TotalCountLruDiskUsage(i);
            return this;
        }

        public Builder diskUsage(DiskUsage diskUsage) {
            this.diskUsage = (DiskUsage) Preconditions.checkNotNull(diskUsage);
            return this;
        }

        public Builder headerInjector(HeaderInjector headerInjector) {
            this.headerInjector = (HeaderInjector) Preconditions.checkNotNull(headerInjector);
            return this;
        }

        public HttpProxyCacheServer build() {
            return new HttpProxyCacheServer(buildConfig());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Config buildConfig() {
            return new Config(this.cacheRoot, this.fileNameGenerator, this.diskUsage, this.sourceInfoStorage, this.headerInjector);
        }
    }
}
