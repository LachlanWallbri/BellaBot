package com.liulishuo.okdownload.core.file;

import com.liulishuo.okdownload.core.Util;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/* loaded from: classes4.dex */
public class FileLock {
    private static final String TAG = "FileLock";
    private static final long WAIT_RELEASE_LOCK_NANO = TimeUnit.MILLISECONDS.toNanos(100);
    private final Map<String, AtomicInteger> fileLockCountMap;
    private final Map<String, Thread> waitThreadForFileLockMap;

    FileLock(Map<String, AtomicInteger> map, Map<String, Thread> map2) {
        this.fileLockCountMap = map;
        this.waitThreadForFileLockMap = map2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileLock() {
        this(new HashMap(), new HashMap());
    }

    public void increaseLock(String str) {
        AtomicInteger atomicInteger;
        synchronized (this.fileLockCountMap) {
            atomicInteger = this.fileLockCountMap.get(str);
        }
        if (atomicInteger == null) {
            atomicInteger = new AtomicInteger(0);
            synchronized (this.fileLockCountMap) {
                this.fileLockCountMap.put(str, atomicInteger);
            }
        }
        Util.m2347d(TAG, "increaseLock increase lock-count to " + atomicInteger.incrementAndGet() + str);
    }

    public void decreaseLock(String str) {
        AtomicInteger atomicInteger;
        Thread thread;
        synchronized (this.fileLockCountMap) {
            atomicInteger = this.fileLockCountMap.get(str);
        }
        if (atomicInteger == null || atomicInteger.decrementAndGet() != 0) {
            return;
        }
        Util.m2347d(TAG, "decreaseLock decrease lock-count to 0 " + str);
        synchronized (this.waitThreadForFileLockMap) {
            thread = this.waitThreadForFileLockMap.get(str);
            if (thread != null) {
                this.waitThreadForFileLockMap.remove(str);
            }
        }
        if (thread != null) {
            Util.m2347d(TAG, "decreaseLock " + str + " unpark locked thread " + atomicInteger);
            unpark(thread);
        }
        synchronized (this.fileLockCountMap) {
            this.fileLockCountMap.remove(str);
        }
    }

    public void waitForRelease(String str) {
        AtomicInteger atomicInteger;
        synchronized (this.fileLockCountMap) {
            atomicInteger = this.fileLockCountMap.get(str);
        }
        if (atomicInteger == null || atomicInteger.get() <= 0) {
            return;
        }
        synchronized (this.waitThreadForFileLockMap) {
            this.waitThreadForFileLockMap.put(str, Thread.currentThread());
        }
        Util.m2347d(TAG, "waitForRelease start " + str);
        while (!isNotLocked(atomicInteger)) {
            park();
        }
        Util.m2347d(TAG, "waitForRelease finish " + str);
    }

    boolean isNotLocked(AtomicInteger atomicInteger) {
        return atomicInteger.get() <= 0;
    }

    void park() {
        LockSupport.park(Long.valueOf(WAIT_RELEASE_LOCK_NANO));
    }

    void unpark(Thread thread) {
        LockSupport.unpark(thread);
    }
}
