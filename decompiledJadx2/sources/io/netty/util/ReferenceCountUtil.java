package io.netty.util;

import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class ReferenceCountUtil {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ReferenceCountUtil.class);

    static {
        ResourceLeakDetector.addExclusions(ReferenceCountUtil.class, "touch");
    }

    public static <T> T retain(T t) {
        return t instanceof ReferenceCounted ? (T) ((ReferenceCounted) t).retain() : t;
    }

    public static <T> T retain(T t, int i) {
        return t instanceof ReferenceCounted ? (T) ((ReferenceCounted) t).retain(i) : t;
    }

    public static <T> T touch(T t) {
        return t instanceof ReferenceCounted ? (T) ((ReferenceCounted) t).touch() : t;
    }

    public static <T> T touch(T t, Object obj) {
        return t instanceof ReferenceCounted ? (T) ((ReferenceCounted) t).touch(obj) : t;
    }

    public static boolean release(Object obj) {
        if (obj instanceof ReferenceCounted) {
            return ((ReferenceCounted) obj).release();
        }
        return false;
    }

    public static boolean release(Object obj, int i) {
        if (obj instanceof ReferenceCounted) {
            return ((ReferenceCounted) obj).release(i);
        }
        return false;
    }

    public static void safeRelease(Object obj) {
        try {
            release(obj);
        } catch (Throwable th) {
            logger.warn("Failed to release a message: {}", obj, th);
        }
    }

    public static void safeRelease(Object obj, int i) {
        try {
            release(obj, i);
        } catch (Throwable th) {
            if (logger.isWarnEnabled()) {
                logger.warn("Failed to release a message: {} (decrement: {})", obj, Integer.valueOf(i), th);
            }
        }
    }

    @Deprecated
    public static <T> T releaseLater(T t) {
        return (T) releaseLater(t, 1);
    }

    @Deprecated
    public static <T> T releaseLater(T t, int i) {
        if (t instanceof ReferenceCounted) {
            ThreadDeathWatcher.watch(Thread.currentThread(), new ReleasingTask((ReferenceCounted) t, i));
        }
        return t;
    }

    public static int refCnt(Object obj) {
        if (obj instanceof ReferenceCounted) {
            return ((ReferenceCounted) obj).refCnt();
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class ReleasingTask implements Runnable {
        private final int decrement;
        private final ReferenceCounted obj;

        ReleasingTask(ReferenceCounted referenceCounted, int i) {
            this.obj = referenceCounted;
            this.decrement = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (!this.obj.release(this.decrement)) {
                    ReferenceCountUtil.logger.warn("Non-zero refCnt: {}", this);
                } else {
                    ReferenceCountUtil.logger.debug("Released: {}", this);
                }
            } catch (Exception e) {
                ReferenceCountUtil.logger.warn("Failed to release an object: {}", this.obj, e);
            }
        }

        public String toString() {
            return StringUtil.simpleClassName(this.obj) + ".release(" + this.decrement + ") refCnt: " + this.obj.refCnt();
        }
    }

    private ReferenceCountUtil() {
    }
}
