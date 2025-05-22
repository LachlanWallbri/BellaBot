package org.jboss.netty.util;

import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

/* loaded from: classes7.dex */
public class ThreadRenamingRunnable implements Runnable {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ThreadRenamingRunnable.class);
    private static volatile ThreadNameDeterminer threadNameDeterminer = ThreadNameDeterminer.PROPOSED;
    private final String proposedThreadName;
    private final Runnable runnable;

    public static ThreadNameDeterminer getThreadNameDeterminer() {
        return threadNameDeterminer;
    }

    public static void setThreadNameDeterminer(ThreadNameDeterminer threadNameDeterminer2) {
        if (threadNameDeterminer2 == null) {
            throw new NullPointerException("threadNameDeterminer");
        }
        threadNameDeterminer = threadNameDeterminer2;
    }

    public ThreadRenamingRunnable(Runnable runnable, String str) {
        if (runnable == null) {
            throw new NullPointerException("runnable");
        }
        if (str == null) {
            throw new NullPointerException("proposedThreadName");
        }
        this.runnable = runnable;
        this.proposedThreadName = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0027 A[DONT_GENERATE] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void run() {
        boolean z;
        Thread currentThread = Thread.currentThread();
        String name = currentThread.getName();
        String newThreadName = getNewThreadName(name);
        try {
            if (!name.equals(newThreadName)) {
                try {
                    currentThread.setName(newThreadName);
                    z = true;
                } catch (SecurityException e) {
                    logger.debug("Failed to rename a thread due to security restriction.", e);
                }
                this.runnable.run();
                if (z) {
                    return;
                } else {
                    return;
                }
            }
            this.runnable.run();
        } finally {
            if (z) {
                currentThread.setName(name);
            }
        }
        z = false;
    }

    private String getNewThreadName(String str) {
        String str2;
        try {
            str2 = getThreadNameDeterminer().determineThreadName(str, this.proposedThreadName);
        } catch (Throwable th) {
            logger.warn("Failed to determine the thread name", th);
            str2 = null;
        }
        return str2 == null ? str : str2;
    }
}
