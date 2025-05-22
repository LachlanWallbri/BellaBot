package kotlinx.coroutines.sync;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlin.Metadata;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Semaphore.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u001bB\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nJ,\u0010\u000e\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000bH\u0086\b¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0086\b¢\u0006\u0004\b\u0010\u0010\u0011J$\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000bH\u0086\b¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0015\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0019\u001a\u00020\b8V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, m3961d2 = {"Lkotlinx/coroutines/sync/SemaphoreSegment;", "", "id", "prev", "<init>", "(JLkotlinx/coroutines/sync/SemaphoreSegment;)V", "", "index", "", "cancel", "(I)Z", "", "expected", ES6Iterator.VALUE_PROPERTY, "cas", "(ILjava/lang/Object;Ljava/lang/Object;)Z", TmpConstant.PROPERTY_IDENTIFIER_GET, "(I)Ljava/lang/Object;", "getAndSet", "(ILjava/lang/Object;)Ljava/lang/Object;", "", "toString", "()Ljava/lang/String;", "getRemoved", "()Z", "removed", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/Segment;"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class SemaphoreSegment extends Segment<SemaphoreSegment> {
    private static final AtomicIntegerFieldUpdater cancelledSlots$FU = AtomicIntegerFieldUpdater.newUpdater(SemaphoreSegment.class, "cancelledSlots");
    AtomicReferenceArray acquirers;
    private volatile int cancelledSlots;

    public SemaphoreSegment(long j, SemaphoreSegment semaphoreSegment) {
        super(j, semaphoreSegment);
        int i;
        i = SemaphoreKt.SEGMENT_SIZE;
        this.acquirers = new AtomicReferenceArray(i);
        this.cancelledSlots = 0;
    }

    @Override // kotlinx.coroutines.internal.Segment
    public boolean getRemoved() {
        int i;
        int i2 = this.cancelledSlots;
        i = SemaphoreKt.SEGMENT_SIZE;
        return i2 == i;
    }

    public final Object get(int index) {
        return this.acquirers.get(index);
    }

    public final boolean cas(int index, Object expected, Object value) {
        return this.acquirers.compareAndSet(index, expected, value);
    }

    public final Object getAndSet(int index, Object value) {
        return this.acquirers.getAndSet(index, value);
    }

    public final boolean cancel(int index) {
        Symbol symbol;
        Symbol symbol2;
        int i;
        symbol = SemaphoreKt.CANCELLED;
        Object andSet = this.acquirers.getAndSet(index, symbol);
        symbol2 = SemaphoreKt.RESUMED;
        boolean z = andSet != symbol2;
        int incrementAndGet = cancelledSlots$FU.incrementAndGet(this);
        i = SemaphoreKt.SEGMENT_SIZE;
        if (incrementAndGet == i) {
            remove();
        }
        return z;
    }

    public String toString() {
        return "SemaphoreSegment[id=" + getId() + ", hashCode=" + hashCode() + ']';
    }
}
