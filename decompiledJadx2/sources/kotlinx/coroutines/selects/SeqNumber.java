package kotlinx.coroutines.selects;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Select.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0000\b\u0000\u0018\u00002\u00020\u0007B\u0007¢\u0006\u0004\b\u0001\u0010\u0002J\r\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, m3961d2 = {"Lkotlinx/coroutines/selects/SeqNumber;", "<init>", "()V", "", ES6Iterator.NEXT_METHOD, "()J", "kotlinx-coroutines-core", ""}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class SeqNumber {
    private static final AtomicLongFieldUpdater number$FU = AtomicLongFieldUpdater.newUpdater(SeqNumber.class, "number");
    private volatile long number = 1;

    public final long next() {
        return number$FU.incrementAndGet(this);
    }
}
