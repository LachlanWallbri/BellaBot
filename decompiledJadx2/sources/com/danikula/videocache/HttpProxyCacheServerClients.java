package com.danikula.videocache;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.danikula.videocache.file.FileCache;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class HttpProxyCacheServerClients {
    private final Config config;
    private volatile HttpProxyCache proxyCache;
    private final CacheListener uiCacheListener;
    private final String url;
    private final AtomicInteger clientsCount = new AtomicInteger(0);
    private final List<CacheListener> listeners = new CopyOnWriteArrayList();

    public HttpProxyCacheServerClients(String str, Config config) {
        this.url = (String) Preconditions.checkNotNull(str);
        this.config = (Config) Preconditions.checkNotNull(config);
        this.uiCacheListener = new UiListenerHandler(str, this.listeners);
    }

    public void processRequest(GetRequest getRequest, Socket socket) throws ProxyCacheException, IOException {
        startProcessRequest();
        try {
            this.clientsCount.incrementAndGet();
            this.proxyCache.processRequest(getRequest, socket);
        } finally {
            finishProcessRequest();
        }
    }

    private synchronized void startProcessRequest() throws ProxyCacheException {
        this.proxyCache = this.proxyCache == null ? newHttpProxyCache() : this.proxyCache;
    }

    private synchronized void finishProcessRequest() {
        if (this.clientsCount.decrementAndGet() <= 0) {
            this.proxyCache.shutdown();
            this.proxyCache = null;
        }
    }

    public void registerCacheListener(CacheListener cacheListener) {
        this.listeners.add(cacheListener);
    }

    public void unregisterCacheListener(CacheListener cacheListener) {
        this.listeners.remove(cacheListener);
    }

    public void shutdown() {
        this.listeners.clear();
        if (this.proxyCache != null) {
            this.proxyCache.registerCacheListener(null);
            this.proxyCache.shutdown();
            this.proxyCache = null;
        }
        this.clientsCount.set(0);
    }

    public int getClientsCount() {
        return this.clientsCount.get();
    }

    private HttpProxyCache newHttpProxyCache() throws ProxyCacheException {
        HttpProxyCache httpProxyCache = new HttpProxyCache(new HttpUrlSource(this.url, this.config.sourceInfoStorage, this.config.headerInjector), new FileCache(this.config.generateCacheFile(this.url), this.config.diskUsage));
        httpProxyCache.registerCacheListener(this.uiCacheListener);
        return httpProxyCache;
    }

    /* loaded from: classes.dex */
    private static final class UiListenerHandler extends Handler implements CacheListener {
        private final List<CacheListener> listeners;
        private final String url;

        public UiListenerHandler(String str, List<CacheListener> list) {
            super(Looper.getMainLooper());
            this.url = str;
            this.listeners = list;
        }

        @Override // com.danikula.videocache.CacheListener
        public void onCacheAvailable(File file, String str, int i) {
            Message obtainMessage = obtainMessage();
            obtainMessage.arg1 = i;
            obtainMessage.obj = file;
            sendMessage(obtainMessage);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Iterator<CacheListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onCacheAvailable((File) message.obj, this.url, message.arg1);
            }
        }
    }
}
