package com.pudutech.base;

/* loaded from: classes.dex */
public class LogPool {
    static final int FLAG_IN_USE = 1;
    private static final int MAX_POOL_SIZE = 50;
    private static LogPool sPool;
    int flags;
    StringBuilder logBuilder = new StringBuilder();
    LogPool next;
    public static final Object sPoolSync = new Object();
    private static int sPoolSize = 0;

    public static LogPool obtain() {
        synchronized (sPoolSync) {
            if (sPool != null) {
                LogPool logPool = sPool;
                sPool = logPool.next;
                logPool.next = null;
                logPool.flags = 0;
                sPoolSize--;
                return logPool;
            }
            return new LogPool();
        }
    }

    public void recycle() {
        if (isInUse()) {
            return;
        }
        recycleUnchecked();
    }

    boolean isInUse() {
        return (this.flags & 1) == 1;
    }

    void recycleUnchecked() {
        this.flags = 1;
        StringBuilder sb = this.logBuilder;
        sb.delete(0, sb.length());
        this.logBuilder.setLength(0);
        synchronized (sPoolSync) {
            if (sPoolSize < 50) {
                this.next = sPool;
                sPool = this;
                sPoolSize++;
            }
        }
    }
}
