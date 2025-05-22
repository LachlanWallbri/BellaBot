package com.danikula.videocache;

import java.lang.Thread;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ProxyCache {
    private static final Logger LOG = LoggerFactory.getLogger("ProxyCache");
    private static final int MAX_READ_SOURCE_ATTEMPTS = 1;
    private final Cache cache;
    private final Source source;
    private volatile Thread sourceReaderThread;
    private volatile boolean stopped;

    /* renamed from: wc */
    private final Object f1248wc = new Object();
    private final Object stopLock = new Object();
    private volatile int percentsAvailable = -1;
    private final AtomicInteger readSourceErrorsCount = new AtomicInteger();

    protected void onCachePercentsAvailableChanged(int i) {
    }

    public ProxyCache(Source source, Cache cache) {
        this.source = (Source) Preconditions.checkNotNull(source);
        this.cache = (Cache) Preconditions.checkNotNull(cache);
    }

    public int read(byte[] bArr, long j, int i) throws ProxyCacheException {
        ProxyCacheUtils.assertBuffer(bArr, j, i);
        while (!this.cache.isCompleted() && this.cache.available() < i + j && !this.stopped) {
            readSourceAsync();
            waitForSourceData();
            checkReadSourceErrorsCount();
        }
        int read = this.cache.read(bArr, j, i);
        if (this.cache.isCompleted() && this.percentsAvailable != 100) {
            this.percentsAvailable = 100;
            onCachePercentsAvailableChanged(100);
        }
        return read;
    }

    private void checkReadSourceErrorsCount() throws ProxyCacheException {
        int i = this.readSourceErrorsCount.get();
        if (i < 1) {
            return;
        }
        this.readSourceErrorsCount.set(0);
        throw new ProxyCacheException("Error reading source " + i + " times");
    }

    public void shutdown() {
        synchronized (this.stopLock) {
            LOG.debug("Shutdown proxy for " + this.source);
            try {
                this.stopped = true;
                if (this.sourceReaderThread != null) {
                    this.sourceReaderThread.interrupt();
                }
                this.cache.close();
            } catch (ProxyCacheException e) {
                onError(e);
            }
        }
    }

    private synchronized void readSourceAsync() throws ProxyCacheException {
        boolean z = (this.sourceReaderThread == null || this.sourceReaderThread.getState() == Thread.State.TERMINATED) ? false : true;
        if (!this.stopped && !this.cache.isCompleted() && !z) {
            this.sourceReaderThread = new Thread(new SourceReaderRunnable(), "Source reader for " + this.source);
            this.sourceReaderThread.start();
        }
    }

    private void waitForSourceData() throws ProxyCacheException {
        synchronized (this.f1248wc) {
            try {
                try {
                    this.f1248wc.wait(1000L);
                } catch (InterruptedException e) {
                    throw new ProxyCacheException("Waiting source data is interrupted!", e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void notifyNewCacheDataAvailable(long j, long j2) {
        onCacheAvailable(j, j2);
        synchronized (this.f1248wc) {
            this.f1248wc.notifyAll();
        }
    }

    protected void onCacheAvailable(long j, long j2) {
        int i = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1)) == 0 ? 100 : (int) ((((float) j) / ((float) j2)) * 100.0f);
        boolean z = i != this.percentsAvailable;
        if ((j2 >= 0) && z) {
            onCachePercentsAvailableChanged(i);
        }
        this.percentsAvailable = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readSource() {
        long j = -1;
        long j2 = 0;
        try {
            j2 = this.cache.available();
            this.source.open(j2);
            j = this.source.length();
            byte[] bArr = new byte[8192];
            while (true) {
                int read = this.source.read(bArr);
                if (read != -1) {
                    synchronized (this.stopLock) {
                        if (isStopped()) {
                            return;
                        } else {
                            this.cache.append(bArr, read);
                        }
                    }
                    j2 += read;
                    notifyNewCacheDataAvailable(j2, j);
                } else {
                    tryComplete();
                    onSourceRead();
                    break;
                }
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    private void onSourceRead() {
        this.percentsAvailable = 100;
        onCachePercentsAvailableChanged(this.percentsAvailable);
    }

    private void tryComplete() throws ProxyCacheException {
        synchronized (this.stopLock) {
            if (!isStopped() && this.cache.available() == this.source.length()) {
                this.cache.complete();
            }
        }
    }

    private boolean isStopped() {
        return Thread.currentThread().isInterrupted() || this.stopped;
    }

    private void closeSource() {
        try {
            this.source.close();
        } catch (ProxyCacheException e) {
            onError(new ProxyCacheException("Error closing source " + this.source, e));
        }
    }

    protected final void onError(Throwable th) {
        if (th instanceof InterruptedProxyCacheException) {
            LOG.debug("ProxyCache is interrupted");
        } else {
            LOG.error("ProxyCache error", th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SourceReaderRunnable implements Runnable {
        private SourceReaderRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ProxyCache.this.readSource();
        }
    }
}
