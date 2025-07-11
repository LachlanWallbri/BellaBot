package com.fasterxml.jackson.core.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
class ThreadLocalBufferManager {
    private final Object RELEASE_LOCK = new Object();
    private final Map<SoftReference<BufferRecycler>, Boolean> _trackedRecyclers = new ConcurrentHashMap();
    private final ReferenceQueue<BufferRecycler> _refQueue = new ReferenceQueue<>();

    ThreadLocalBufferManager() {
    }

    public static ThreadLocalBufferManager instance() {
        return ThreadLocalBufferManagerHolder.manager;
    }

    public int releaseBuffers() {
        int i;
        synchronized (this.RELEASE_LOCK) {
            i = 0;
            removeSoftRefsClearedByGc();
            Iterator<SoftReference<BufferRecycler>> it = this._trackedRecyclers.keySet().iterator();
            while (it.hasNext()) {
                it.next().clear();
                i++;
            }
            this._trackedRecyclers.clear();
        }
        return i;
    }

    public SoftReference<BufferRecycler> wrapAndTrack(BufferRecycler bufferRecycler) {
        SoftReference<BufferRecycler> softReference = new SoftReference<>(bufferRecycler, this._refQueue);
        this._trackedRecyclers.put(softReference, true);
        removeSoftRefsClearedByGc();
        return softReference;
    }

    private void removeSoftRefsClearedByGc() {
        while (true) {
            SoftReference softReference = (SoftReference) this._refQueue.poll();
            if (softReference == null) {
                return;
            } else {
                this._trackedRecyclers.remove(softReference);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
     */
    /* loaded from: classes2.dex */
    private static final class ThreadLocalBufferManagerHolder {
        static final ThreadLocalBufferManager manager = new ThreadLocalBufferManager();

        private ThreadLocalBufferManagerHolder() {
        }
    }
}
