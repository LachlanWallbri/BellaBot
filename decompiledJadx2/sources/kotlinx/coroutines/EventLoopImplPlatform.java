package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.Metadata;
import kotlinx.coroutines.EventLoopImplBase;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: EventLoop.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0004J\b\u0010\r\u001a\u00020\bH\u0004R\u0012\u0010\u0003\u001a\u00020\u0004X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, m3961d2 = {"Lkotlinx/coroutines/EventLoopImplPlatform;", "Lkotlinx/coroutines/EventLoop;", "()V", "thread", "Ljava/lang/Thread;", "getThread", "()Ljava/lang/Thread;", "reschedule", "", "now", "", "delayedTask", "Lkotlinx/coroutines/EventLoopImplBase$DelayedTask;", "unpark", "kotlinx-coroutines-core"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public abstract class EventLoopImplPlatform extends EventLoop {
    protected abstract Thread getThread();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void unpark() {
        Thread thread = getThread();
        if (Thread.currentThread() != thread) {
            TimeSource timeSource = TimeSourceKt.getTimeSource();
            if (timeSource != null) {
                timeSource.unpark(thread);
            } else {
                LockSupport.unpark(thread);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void reschedule(long now, EventLoopImplBase.DelayedTask delayedTask) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(this != DefaultExecutor.INSTANCE)) {
                throw new AssertionError();
            }
        }
        DefaultExecutor.INSTANCE.schedule(now, delayedTask);
    }
}
