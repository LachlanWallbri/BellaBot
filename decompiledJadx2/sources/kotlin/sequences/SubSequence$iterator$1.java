package kotlin.sequences;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: Sequences.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000#\n\u0000\n\u0002\u0010(\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\b\u0010\u000b\u001a\u00020\fH\u0002J\t\u0010\r\u001a\u00020\u000eH\u0096\u0002J\u000e\u0010\u000f\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0010R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0011"}, m3961d2 = {"kotlin/sequences/SubSequence$iterator$1", "", "iterator", "getIterator", "()Ljava/util/Iterator;", RequestParameters.POSITION, "", "getPosition", "()I", "setPosition", "(I)V", "drop", "", "hasNext", "", ES6Iterator.NEXT_METHOD, "()Ljava/lang/Object;", "kotlin-stdlib"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class SubSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {
    private final Iterator<T> iterator;
    private int position;
    final /* synthetic */ SubSequence this$0;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubSequence$iterator$1(SubSequence subSequence) {
        Sequence sequence;
        this.this$0 = subSequence;
        sequence = subSequence.sequence;
        this.iterator = sequence.iterator();
    }

    public final Iterator<T> getIterator() {
        return this.iterator;
    }

    public final int getPosition() {
        return this.position;
    }

    public final void setPosition(int i) {
        this.position = i;
    }

    /* JADX WARN: Incorrect condition in loop: B:2:0x0008 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void drop() {
        int i;
        while (r0 < i && this.iterator.hasNext()) {
            this.iterator.next();
            this.position++;
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        int i;
        drop();
        int i2 = this.position;
        i = this.this$0.endIndex;
        return i2 < i && this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        int i;
        drop();
        int i2 = this.position;
        i = this.this$0.endIndex;
        if (i2 >= i) {
            throw new NoSuchElementException();
        }
        this.position++;
        return this.iterator.next();
    }
}
