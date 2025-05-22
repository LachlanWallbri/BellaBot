package com.fasterxml.jackson.core.util;

import java.lang.ref.SoftReference;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class BufferRecyclers {
    public static final String SYSTEM_PROPERTY_TRACK_REUSABLE_BUFFERS = "com.fasterxml.jackson.core.util.BufferRecyclers.trackReusableBuffers";
    private static final ThreadLocalBufferManager _bufferRecyclerTracker;
    protected static final ThreadLocal<SoftReference<BufferRecycler>> _recyclerRef;

    static {
        boolean z;
        try {
            z = "true".equals(System.getProperty(SYSTEM_PROPERTY_TRACK_REUSABLE_BUFFERS));
        } catch (SecurityException unused) {
            z = false;
        }
        _bufferRecyclerTracker = z ? ThreadLocalBufferManager.instance() : null;
        _recyclerRef = new ThreadLocal<>();
    }

    public static BufferRecycler getBufferRecycler() {
        SoftReference<BufferRecycler> softReference;
        SoftReference<BufferRecycler> softReference2 = _recyclerRef.get();
        BufferRecycler bufferRecycler = softReference2 == null ? null : softReference2.get();
        if (bufferRecycler == null) {
            bufferRecycler = new BufferRecycler();
            ThreadLocalBufferManager threadLocalBufferManager = _bufferRecyclerTracker;
            if (threadLocalBufferManager != null) {
                softReference = threadLocalBufferManager.wrapAndTrack(bufferRecycler);
            } else {
                softReference = new SoftReference<>(bufferRecycler);
            }
            _recyclerRef.set(softReference);
        }
        return bufferRecycler;
    }

    public static int releaseBuffers() {
        ThreadLocalBufferManager threadLocalBufferManager = _bufferRecyclerTracker;
        if (threadLocalBufferManager != null) {
            return threadLocalBufferManager.releaseBuffers();
        }
        return -1;
    }
}
