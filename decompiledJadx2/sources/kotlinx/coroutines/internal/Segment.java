package kotlinx.coroutines.internal;

import androidx.exifinterface.media.ExifInterface;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.Segment;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: SegmentQueue.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u0000\b \u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\u00020\u001cB\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\n\u001a\u00020\t2\b\u0010\u0007\u001a\u0004\u0018\u00018\u00002\b\u0010\b\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0004\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u0010\u0010\u000fJ\r\u0010\u0011\u001a\u00020\r¢\u0006\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\f\u001a\u0004\u0018\u00018\u00008F@\u0006¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u001a\u001a\u00020\t8&@&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, m3961d2 = {"Lkotlinx/coroutines/internal/Segment;", ExifInterface.LATITUDE_SOUTH, "", "id", "prev", "<init>", "(JLkotlinx/coroutines/internal/Segment;)V", "expected", ES6Iterator.VALUE_PROPERTY, "", "casNext", "(Lkotlinx/coroutines/internal/Segment;Lkotlinx/coroutines/internal/Segment;)Z", ES6Iterator.NEXT_METHOD, "", "moveNextToRight", "(Lkotlinx/coroutines/internal/Segment;)V", "movePrevToLeft", "remove", "()V", "J", "getId", "()J", "getNext", "()Lkotlinx/coroutines/internal/Segment;", "getRemoved", "()Z", "removed", "kotlinx-coroutines-core", ""}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public abstract class Segment<S extends Segment<S>> {
    private static final AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(Segment.class, Object.class, "_next");
    static final AtomicReferenceFieldUpdater prev$FU = AtomicReferenceFieldUpdater.newUpdater(Segment.class, Object.class, "prev");
    private volatile Object _next = null;
    private final long id;
    volatile Object prev;

    public abstract boolean getRemoved();

    public Segment(long j, S s) {
        this.id = j;
        this.prev = null;
        this.prev = s;
    }

    public final long getId() {
        return this.id;
    }

    public final S getNext() {
        return (S) this._next;
    }

    public final boolean casNext(S expected, S value) {
        return _next$FU.compareAndSet(this, expected, value);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void remove() {
        Segment segment;
        Segment next;
        Segment segment2;
        if (DebugKt.getASSERTIONS_ENABLED() && !getRemoved()) {
            throw new AssertionError();
        }
        Segment segment3 = (Segment) this._next;
        if (segment3 == null || (segment = (Segment) this.prev) == 0) {
            return;
        }
        segment.moveNextToRight(segment3);
        S s = segment;
        while (s.getRemoved() && (segment2 = (Segment) s.prev) != 0) {
            segment2.moveNextToRight(segment3);
            s = segment2;
        }
        segment3.movePrevToLeft(s);
        Segment segment4 = segment3;
        while (segment4.getRemoved() && (next = segment4.getNext()) != null) {
            next.movePrevToLeft(s);
            segment4 = next;
        }
    }

    private final void moveNextToRight(S next) {
        Segment segment;
        do {
            Object obj = this._next;
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type S");
            }
            segment = (Segment) obj;
            if (next.id <= segment.id) {
                return;
            }
        } while (!_next$FU.compareAndSet(this, segment, next));
    }

    private final void movePrevToLeft(S prev) {
        Segment segment;
        do {
            segment = (Segment) this.prev;
            if (segment == null || segment.id <= prev.id) {
                return;
            }
        } while (!prev$FU.compareAndSet(this, segment, prev));
    }
}
