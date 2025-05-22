package okio;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* compiled from: Timeout.kt */
@Metadata(m3959bv = {1, 0, 2}, m3960d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, m3961d2 = {"okio/Timeout$Companion$NONE$1", "Lokio/Timeout;", "deadlineNanoTime", "", "throwIfReached", "", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "jvm"}, m3962k = 1, m3963mv = {1, 1, 11})
/* loaded from: classes2.dex */
public final class Timeout$Companion$NONE$1 extends Timeout {
    @Override // okio.Timeout
    public void throwIfReached() {
    }

    Timeout$Companion$NONE$1() {
    }

    @Override // okio.Timeout
    public Timeout timeout(long timeout, TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        return this;
    }

    @Override // okio.Timeout
    public Timeout deadlineNanoTime(long deadlineNanoTime) {
        return this;
    }
}
