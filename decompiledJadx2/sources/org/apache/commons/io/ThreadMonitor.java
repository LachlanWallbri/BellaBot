package org.apache.commons.io;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
class ThreadMonitor implements Runnable {
    private final Thread thread;
    private final long timeout;

    public static Thread start(long j) {
        return start(Thread.currentThread(), j);
    }

    public static Thread start(Thread thread, long j) {
        if (j <= 0) {
            return null;
        }
        Thread thread2 = new Thread(new ThreadMonitor(thread, j), ThreadMonitor.class.getSimpleName());
        thread2.setDaemon(true);
        thread2.start();
        return thread2;
    }

    public static void stop(Thread thread) {
        if (thread != null) {
            thread.interrupt();
        }
    }

    private ThreadMonitor(Thread thread, long j) {
        this.thread = thread;
        this.timeout = j;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            sleep(this.timeout);
            this.thread.interrupt();
        } catch (InterruptedException unused) {
        }
    }

    private static void sleep(long j) throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis() + j;
        do {
            Thread.sleep(j);
            j = currentTimeMillis - System.currentTimeMillis();
        } while (j > 0);
    }
}
