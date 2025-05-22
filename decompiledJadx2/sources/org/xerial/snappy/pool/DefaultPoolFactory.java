package org.xerial.snappy.pool;

/* loaded from: classes9.dex */
public final class DefaultPoolFactory {
    public static final String DISABLE_CACHING_PROPERTY = "org.xerial.snappy.pool.disable";
    private static volatile BufferPool defaultPool;

    static {
        BufferPool cachingBufferPool;
        if ("true".equalsIgnoreCase(System.getProperty(DISABLE_CACHING_PROPERTY))) {
            cachingBufferPool = QuiescentBufferPool.getInstance();
        } else {
            cachingBufferPool = CachingBufferPool.getInstance();
        }
        defaultPool = cachingBufferPool;
    }

    public static BufferPool getDefaultPool() {
        return defaultPool;
    }

    public static void setDefaultPool(BufferPool bufferPool) {
        if (bufferPool == null) {
            throw new IllegalArgumentException("pool is null");
        }
        defaultPool = bufferPool;
    }
}
