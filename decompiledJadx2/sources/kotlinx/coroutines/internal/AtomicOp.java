package kotlinx.coroutines.internal;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlinx.coroutines.DebugKt;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Atomic.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\b&\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u001aB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00028\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&¢\u0006\u0004\b\b\u0010\tJ\u0019\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\r\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\r\u0010\fJ\u0019\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00028\u0000H&¢\u0006\u0004\b\u000e\u0010\fR\u001a\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00008V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0013\u001a\u00020\u00128F@\u0006¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0018\u001a\u00020\u00158V@\u0016X\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, m3961d2 = {"Lkotlinx/coroutines/internal/AtomicOp;", ExifInterface.GPS_DIRECTION_TRUE, "<init>", "()V", "affected", "", "failure", "", "complete", "(Ljava/lang/Object;Ljava/lang/Object;)V", "decision", "decide", "(Ljava/lang/Object;)Ljava/lang/Object;", "perform", "prepare", "getAtomicOp", "()Lkotlinx/coroutines/internal/AtomicOp;", "atomicOp", "", "isDecided", "()Z", "", "getOpSequence", "()J", "opSequence", "kotlinx-coroutines-core", "Lkotlinx/coroutines/internal/OpDescriptor;"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public abstract class AtomicOp<T> extends OpDescriptor {
    private static final AtomicReferenceFieldUpdater _consensus$FU = AtomicReferenceFieldUpdater.newUpdater(AtomicOp.class, Object.class, "_consensus");
    private volatile Object _consensus;

    public abstract void complete(T affected, Object failure);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.internal.OpDescriptor
    public AtomicOp<?> getAtomicOp() {
        return this;
    }

    public long getOpSequence() {
        return 0L;
    }

    public abstract Object prepare(T affected);

    public AtomicOp() {
        Object obj;
        obj = AtomicKt.NO_DECISION;
        this._consensus = obj;
    }

    public final boolean isDecided() {
        Object obj;
        Object obj2 = this._consensus;
        obj = AtomicKt.NO_DECISION;
        return obj2 != obj;
    }

    public final Object decide(Object decision) {
        Object obj;
        Object obj2;
        Object obj3;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            obj3 = AtomicKt.NO_DECISION;
            if (!(decision != obj3)) {
                throw new AssertionError();
            }
        }
        Object obj4 = this._consensus;
        obj = AtomicKt.NO_DECISION;
        if (obj4 != obj) {
            return obj4;
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _consensus$FU;
        obj2 = AtomicKt.NO_DECISION;
        return atomicReferenceFieldUpdater.compareAndSet(this, obj2, decision) ? decision : this._consensus;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.internal.OpDescriptor
    public final Object perform(Object affected) {
        Object obj;
        Object obj2 = this._consensus;
        obj = AtomicKt.NO_DECISION;
        if (obj2 == obj) {
            obj2 = decide(prepare(affected));
        }
        complete(affected, obj2);
        return obj2;
    }
}
