package org.jboss.netty.util.internal;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* loaded from: classes7.dex */
public final class ExecutorUtil {
    public static boolean isShutdown(Executor executor) {
        return (executor instanceof ExecutorService) && ((ExecutorService) executor).isShutdown();
    }

    public static void terminate(Executor... executorArr) {
        terminate(DeadLockProofWorker.PARENT, executorArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x006d A[EDGE_INSN: B:38:0x0068->B:39:0x006d BREAK  A[LOOP:3: B:29:0x0059->B:42:0x0059], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0059 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void terminate(ThreadLocal<Executor> threadLocal, Executor... executorArr) {
        if (executorArr == null) {
            throw new NullPointerException("executors");
        }
        Executor[] executorArr2 = new Executor[executorArr.length];
        for (int i = 0; i < executorArr.length; i++) {
            if (executorArr[i] == null) {
                throw new NullPointerException("executors[" + i + "]");
            }
            executorArr2[i] = executorArr[i];
        }
        Executor executor = threadLocal.get();
        if (executor != null) {
            for (Executor executor2 : executorArr2) {
                if (executor2 == executor) {
                    throw new IllegalStateException("An Executor cannot be shut down from the thread acquired from itself.  Please make sure you are not calling releaseExternalResources() from an I/O worker thread.");
                }
            }
        }
        boolean z = false;
        for (Executor executor3 : executorArr2) {
            if (executor3 instanceof ExecutorService) {
                ExecutorService executorService = (ExecutorService) executor3;
                while (true) {
                    try {
                        try {
                            try {
                                executorService.shutdownNow();
                            } catch (SecurityException unused) {
                                executorService.shutdown();
                                try {
                                    if (!executorService.awaitTermination(100L, TimeUnit.MILLISECONDS)) {
                                    }
                                } catch (InterruptedException unused2) {
                                    z = true;
                                }
                            }
                        } catch (SecurityException unused3) {
                        }
                    } catch (NullPointerException unused4) {
                        if (!executorService.awaitTermination(100L, TimeUnit.MILLISECONDS)) {
                        }
                    }
                }
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    private ExecutorUtil() {
    }
}
