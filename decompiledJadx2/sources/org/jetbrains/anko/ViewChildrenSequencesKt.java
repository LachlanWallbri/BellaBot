package org.jetbrains.anko;

import android.view.View;
import android.view.ViewGroup;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* compiled from: viewChildrenSequences.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0002\u001a\u0010\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0002\u001a!\u0010\u0004\u001a\u00020\u0002*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0007H\u0086\b\u001a#\u0010\t\u001a\u0004\u0018\u00010\u0002*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0007H\u0086\b\u001a!\u0010\n\u001a\u00020\u000b*\u00020\u00052\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\u0007H\u0086\b\u001a'\u0010\r\u001a\u00020\u000b*\u00020\u00052\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\u000eH\u0086\b¨\u0006\u0010"}, m3961d2 = {"childrenRecursiveSequence", "Lkotlin/sequences/Sequence;", "Landroid/view/View;", "childrenSequence", "firstChild", "Landroid/view/ViewGroup;", "predicate", "Lkotlin/Function1;", "", "firstChildOrNull", "forEachChild", "", "action", "forEachChildWithIndex", "Lkotlin/Function2;", "", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class ViewChildrenSequencesKt {
    public static final void forEachChild(ViewGroup receiver, Function1<? super View, Unit> action) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, "action");
        int childCount = receiver.getChildCount() - 1;
        if (childCount < 0) {
            return;
        }
        int i = 0;
        while (true) {
            View childAt = receiver.getChildAt(i);
            Intrinsics.checkExpressionValueIsNotNull(childAt, "getChildAt(i)");
            action.invoke(childAt);
            if (i == childCount) {
                return;
            } else {
                i++;
            }
        }
    }

    public static final void forEachChildWithIndex(ViewGroup receiver, Function2<? super Integer, ? super View, Unit> action) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, "action");
        int childCount = receiver.getChildCount() - 1;
        if (childCount < 0) {
            return;
        }
        int i = 0;
        while (true) {
            Integer valueOf = Integer.valueOf(i);
            View childAt = receiver.getChildAt(i);
            Intrinsics.checkExpressionValueIsNotNull(childAt, "getChildAt(i)");
            action.invoke(valueOf, childAt);
            if (i == childCount) {
                return;
            } else {
                i++;
            }
        }
    }

    public static final View firstChild(ViewGroup receiver, Function1<? super View, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int childCount = receiver.getChildCount() - 1;
        if (childCount >= 0) {
            int i = 0;
            while (true) {
                View child = receiver.getChildAt(i);
                Intrinsics.checkExpressionValueIsNotNull(child, "child");
                if (!predicate.invoke(child).booleanValue()) {
                    if (i == childCount) {
                        break;
                    }
                    i++;
                } else {
                    Intrinsics.checkExpressionValueIsNotNull(child, "child");
                    return child;
                }
            }
        }
        throw new NoSuchElementException("No element matching predicate was found.");
    }

    public static final View firstChildOrNull(ViewGroup receiver, Function1<? super View, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int childCount = receiver.getChildCount() - 1;
        if (childCount < 0) {
            return null;
        }
        int i = 0;
        while (true) {
            View child = receiver.getChildAt(i);
            Intrinsics.checkExpressionValueIsNotNull(child, "child");
            if (predicate.invoke(child).booleanValue()) {
                return child;
            }
            if (i == childCount) {
                return null;
            }
            i++;
        }
    }

    public static final Sequence<View> childrenSequence(View receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new ViewChildrenSequence(receiver);
    }

    public static final Sequence<View> childrenRecursiveSequence(View receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new ViewChildrenRecursiveSequence(receiver);
    }
}
