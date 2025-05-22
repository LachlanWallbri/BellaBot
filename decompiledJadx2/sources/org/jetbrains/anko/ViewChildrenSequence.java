package org.jetbrains.anko;

import android.view.View;
import android.view.ViewGroup;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.Sequence;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: viewChildrenSequences.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010(\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u000f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006H\u0096\u0002R\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, m3961d2 = {"Lorg/jetbrains/anko/ViewChildrenSequence;", "Lkotlin/sequences/Sequence;", "Landroid/view/View;", "view", "(Landroid/view/View;)V", "iterator", "", "ViewIterator", "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
final class ViewChildrenSequence implements Sequence<View> {
    private final View view;

    public ViewChildrenSequence(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        this.view = view;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<View> iterator() {
        View view = this.view;
        return !(view instanceof ViewGroup) ? CollectionsKt.emptyList().iterator() : new ViewIterator((ViewGroup) view);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes9.dex
     */
    /* compiled from: viewChildrenSequences.kt */
    @Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0002J\t\u0010\u000b\u001a\u00020\fH\u0096\u0002J\t\u0010\r\u001a\u00020\u0002H\u0096\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lorg/jetbrains/anko/ViewChildrenSequence$ViewIterator;", "", "Landroid/view/View;", "view", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "count", "", "index", "checkCount", "", "hasNext", "", ES6Iterator.NEXT_METHOD, "commons_release"}, m3962k = 1, m3963mv = {1, 1, 5})
    /* loaded from: classes2.dex */
    private static final class ViewIterator implements Iterator<View>, KMappedMarker {
        private final int count;
        private int index;
        private final ViewGroup view;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public ViewIterator(ViewGroup view) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            this.view = view;
            this.count = view.getChildCount();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public View next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            ViewGroup viewGroup = this.view;
            int i = this.index;
            this.index = i + 1;
            View childAt = viewGroup.getChildAt(i);
            Intrinsics.checkExpressionValueIsNotNull(childAt, "view.getChildAt(index++)");
            return childAt;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            checkCount();
            return this.index < this.count;
        }

        private final void checkCount() {
            if (this.count != this.view.getChildCount()) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
