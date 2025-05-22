package org.nanohttpd.protocols.http;

import android.util.Log;
import com.pudutech.base.Pdlog;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.URLDecoder;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import org.nanohttpd.protocols.http.response.Response;
import org.nanohttpd.protocols.http.response.Status;
import org.nanohttpd.protocols.http.sockets.DefaultServerSocketFactory;
import org.nanohttpd.protocols.http.sockets.SecureServerSocketFactory;
import org.nanohttpd.protocols.http.tempfiles.DefaultTempFileManagerFactory;
import org.nanohttpd.protocols.http.tempfiles.ITempFileManager;
import org.nanohttpd.protocols.http.threading.DefaultAsyncRunner;
import org.nanohttpd.protocols.http.threading.IAsyncRunner;
import org.nanohttpd.protocols.util.IFactory;
import org.nanohttpd.protocols.util.IFactoryThrowing;
import org.nanohttpd.protocols.util.IHandler;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public abstract class NanoHTTPD {
    public static final String MIME_HTML = "text/html";
    public static final String MIME_PLAINTEXT = "text/plain";
    protected static Map<String, String> MIME_TYPES = null;
    private static final String QUERY_STRING_PARAMETER = "NanoHttpd.QUERY_STRING";
    public static final int SOCKET_READ_TIMEOUT = 5000;
    protected IAsyncRunner asyncRunner;
    public final String hostname;
    private IHandler<IHTTPSession, Response> httpHandler;
    protected List<IHandler<IHTTPSession, Response>> interceptors;
    public final int myPort;
    private volatile ServerSocket myServerSocket;
    private Thread myThread;
    private IFactoryThrowing<ServerSocket, IOException> serverSocketFactory;
    private IFactory<ITempFileManager> tempFileManagerFactory;
    private static final String TAG = NanoHTTPD.class.getCanonicalName();
    public static final String CONTENT_DISPOSITION_REGEX = "([ |\t]*Content-Disposition[ |\t]*:)(.*)";
    public static final Pattern CONTENT_DISPOSITION_PATTERN = Pattern.compile(CONTENT_DISPOSITION_REGEX, 2);
    public static final String CONTENT_TYPE_REGEX = "([ |\t]*content-type[ |\t]*:)(.*)";
    public static final Pattern CONTENT_TYPE_PATTERN = Pattern.compile(CONTENT_TYPE_REGEX, 2);
    public static final String CONTENT_DISPOSITION_ATTRIBUTE_REGEX = "[ |\t]*([a-zA-Z]*)[ |\t]*=[ |\t]*['|\"]([^\"^']*)['|\"]";
    public static final Pattern CONTENT_DISPOSITION_ATTRIBUTE_PATTERN = Pattern.compile(CONTENT_DISPOSITION_ATTRIBUTE_REGEX);
    public static final Logger LOG = Logger.getLogger(NanoHTTPD.class.getName());

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes9.dex */
    public static final class ResponseException extends Exception {
        private static final long serialVersionUID = 6569838532917408380L;
        private final Status status;

        public ResponseException(Status status, String str) {
            super(str);
            this.status = status;
        }

        public ResponseException(Status status, String str, Exception exc) {
            super(str, exc);
            this.status = status;
        }

        public Status getStatus() {
            return this.status;
        }
    }

    public static Map<String, String> mimeTypes() {
        if (MIME_TYPES == null) {
            MIME_TYPES = new HashMap();
            loadMimeTypes(MIME_TYPES, "META-INF/nanohttpd/default-mimetypes.properties");
            loadMimeTypes(MIME_TYPES, "META-INF/nanohttpd/mimetypes.properties");
            if (MIME_TYPES.isEmpty()) {
                LOG.log(Level.WARNING, "no mime types found in the classpath! please provide mimetypes.properties");
            }
        }
        return MIME_TYPES;
    }

    private static void loadMimeTypes(Map<String, String> map, String str) {
        try {
            Enumeration<URL> resources = NanoHTTPD.class.getClassLoader().getResources(str);
            while (resources.hasMoreElements()) {
                URL nextElement = resources.nextElement();
                Properties properties = new Properties();
                InputStream inputStream = null;
                try {
                    try {
                        inputStream = nextElement.openStream();
                        properties.load(inputStream);
                    } catch (IOException e) {
                        LOG.log(Level.SEVERE, "could not load mimetypes from " + nextElement, (Throwable) e);
                    }
                    safeClose(inputStream);
                    map.putAll(properties);
                } catch (Throwable th) {
                    safeClose(inputStream);
                    throw th;
                }
            }
        } catch (IOException unused) {
            LOG.log(Level.INFO, "no mime types available at " + str);
        }
    }

    public static SSLServerSocketFactory makeSSLSocketFactory(KeyStore keyStore, KeyManager[] keyManagerArr) throws IOException {
        try {
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(keyManagerArr, trustManagerFactory.getTrustManagers(), null);
            return sSLContext.getServerSocketFactory();
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public static SSLServerSocketFactory makeSSLSocketFactory(KeyStore keyStore, KeyManagerFactory keyManagerFactory) throws IOException {
        try {
            return makeSSLSocketFactory(keyStore, keyManagerFactory.getKeyManagers());
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public static SSLServerSocketFactory makeSSLSocketFactory(String str, char[] cArr) throws IOException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream resourceAsStream = NanoHTTPD.class.getResourceAsStream(str);
            if (resourceAsStream == null) {
                throw new IOException("Unable to load keystore from classpath: " + str);
            }
            keyStore.load(resourceAsStream, cArr);
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, cArr);
            return makeSSLSocketFactory(keyStore, keyManagerFactory);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public static String getMimeTypeForFile(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        String str2 = lastIndexOf >= 0 ? mimeTypes().get(str.substring(lastIndexOf + 1).toLowerCase()) : null;
        return str2 == null ? "application/octet-stream" : str2;
    }

    public static final void safeClose(Object obj) {
        if (obj != null) {
            try {
                if (obj instanceof Closeable) {
                    ((Closeable) obj).close();
                } else if (obj instanceof Socket) {
                    ((Socket) obj).close();
                } else {
                    if (obj instanceof ServerSocket) {
                        ((ServerSocket) obj).close();
                        return;
                    }
                    throw new IllegalArgumentException("Unknown object to close");
                }
            } catch (IOException e) {
                Pdlog.m3273d(TAG, "safeClose occur error " + Log.getStackTraceString(e));
                LOG.log(Level.SEVERE, "Could not close", (Throwable) e);
            }
        }
    }

    public ServerSocket getMyServerSocket() {
        return this.myServerSocket;
    }

    public NanoHTTPD(int i) {
        this(null, i);
    }

    public NanoHTTPD(String str, int i) {
        this.serverSocketFactory = new DefaultServerSocketFactory();
        this.interceptors = new ArrayList(4);
        this.hostname = str;
        this.myPort = i;
        setTempFileManagerFactory(new DefaultTempFileManagerFactory());
        setAsyncRunner(new DefaultAsyncRunner());
        this.httpHandler = new IHandler<IHTTPSession, Response>() { // from class: org.nanohttpd.protocols.http.NanoHTTPD.1
            @Override // org.nanohttpd.protocols.util.IHandler
            public Response handle(IHTTPSession iHTTPSession) {
                return NanoHTTPD.this.serve(iHTTPSession);
            }
        };
    }

    public void setHTTPHandler(IHandler<IHTTPSession, Response> iHandler) {
        this.httpHandler = iHandler;
    }

    public void addHTTPInterceptor(IHandler<IHTTPSession, Response> iHandler) {
        this.interceptors.add(iHandler);
    }

    public synchronized void closeAllConnections() {
        stop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ClientHandler createClientHandler(Socket socket, InputStream inputStream) {
        return new ClientHandler(this, inputStream, socket);
    }

    protected ServerRunnable createServerRunnable(int i) {
        return new ServerRunnable(this, i);
    }

    protected static Map<String, List<String>> decodeParameters(Map<String, String> map) {
        return decodeParameters(map.get(QUERY_STRING_PARAMETER));
    }

    protected static Map<String, List<String>> decodeParameters(String str) {
        HashMap hashMap = new HashMap();
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, "&");
            while (stringTokenizer.hasMoreTokens()) {
                String nextToken = stringTokenizer.nextToken();
                int indexOf = nextToken.indexOf(61);
                String trim = (indexOf >= 0 ? decodePercent(nextToken.substring(0, indexOf)) : decodePercent(nextToken)).trim();
                if (!hashMap.containsKey(trim)) {
                    hashMap.put(trim, new ArrayList());
                }
                String decodePercent = indexOf >= 0 ? decodePercent(nextToken.substring(indexOf + 1)) : null;
                if (decodePercent != null) {
                    ((List) hashMap.get(trim)).add(decodePercent);
                }
            }
        }
        return hashMap;
    }

    public static String decodePercent(String str) {
        try {
            return URLDecoder.decode(str, "UTF8");
        } catch (UnsupportedEncodingException e) {
            LOG.log(Level.WARNING, "Encoding not supported, ignored", (Throwable) e);
            return null;
        }
    }

    public final int getListeningPort() {
        if (this.myServerSocket == null) {
            return -1;
        }
        return this.myServerSocket.getLocalPort();
    }

    public final boolean isAlive() {
        return wasStarted() && !this.myServerSocket.isClosed() && this.myThread.isAlive();
    }

    public IFactoryThrowing<ServerSocket, IOException> getServerSocketFactory() {
        return this.serverSocketFactory;
    }

    public void setServerSocketFactory(IFactoryThrowing<ServerSocket, IOException> iFactoryThrowing) {
        this.serverSocketFactory = iFactoryThrowing;
    }

    public String getHostname() {
        return this.hostname;
    }

    public IFactory<ITempFileManager> getTempFileManagerFactory() {
        return this.tempFileManagerFactory;
    }

    public void makeSecure(SSLServerSocketFactory sSLServerSocketFactory, String[] strArr) {
        this.serverSocketFactory = new SecureServerSocketFactory(sSLServerSocketFactory, strArr);
    }

    public Response handle(IHTTPSession iHTTPSession) {
        Iterator<IHandler<IHTTPSession, Response>> it = this.interceptors.iterator();
        while (it.hasNext()) {
            Response handle = it.next().handle(iHTTPSession);
            if (handle != null) {
                return handle;
            }
        }
        return this.httpHandler.handle(iHTTPSession);
    }

    protected Response serve(IHTTPSession iHTTPSession) {
        return Response.newFixedLengthResponse(Status.NOT_FOUND, "text/plain", "Not Found");
    }

    public void setAsyncRunner(IAsyncRunner iAsyncRunner) {
        this.asyncRunner = iAsyncRunner;
    }

    public void setTempFileManagerFactory(IFactory<ITempFileManager> iFactory) {
        this.tempFileManagerFactory = iFactory;
    }

    public void start() throws IOException {
        start(5000);
    }

    public void start(int i) throws IOException {
        start(i, true);
    }

    public void start(int i, boolean z) throws IOException {
        this.myServerSocket = getServerSocketFactory().create();
        this.myServerSocket.setReuseAddress(true);
        ServerRunnable createServerRunnable = createServerRunnable(i);
        this.myThread = new Thread(createServerRunnable);
        this.myThread.setDaemon(z);
        this.myThread.setName("NanoHttpd Main Listener");
        this.myThread.start();
        Pdlog.m3273d(TAG, "Local Http Server is StartUp");
        while (!createServerRunnable.hasBinded() && createServerRunnable.getBindException() == null) {
            try {
                Thread.sleep(10L);
            } catch (Throwable unused) {
            }
        }
        if (createServerRunnable.getBindException() != null) {
            throw createServerRunnable.getBindException();
        }
    }

    public void stop() {
        try {
            Pdlog.m3273d(TAG, "Local Http Server is Stop");
            safeClose(this.myServerSocket);
            this.asyncRunner.closeAll();
            if (this.myThread != null) {
                this.myThread.join();
            }
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Could not stop all connections", (Throwable) e);
            Pdlog.m3273d(TAG, "stop the NanoHttpD occur error " + Log.getStackTraceString(e));
        }
    }

    public final boolean wasStarted() {
        return (this.myServerSocket == null || this.myThread == null) ? false : true;
    }
}
