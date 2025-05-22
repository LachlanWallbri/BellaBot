package org.jetbrains.anko.collections;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import androidx.exifinterface.media.ExifInterface;
import com.loc.C3898x;
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
/* compiled from: Arrays.kt */
@Metadata(m3959bv = {1, 0, 1}, m3960d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086\b\u001a\u0019\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0005H\u0086\b\u001a\u0019\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0007H\u0086\b\u001a2\u0010\b\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\t0\fH\u0086\b¢\u0006\u0002\u0010\r\u001a2\u0010\u000e\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\t0\fH\u0086\b¢\u0006\u0002\u0010\r\u001a8\u0010\u000f\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\t0\u0010H\u0086\b¢\u0006\u0002\u0010\u0011\u001a8\u0010\u0012\u001a\u00020\t\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\t0\u0010H\u0086\b¢\u0006\u0002\u0010\u0011¨\u0006\u0013"}, m3961d2 = {"asSequence", "Lkotlin/sequences/Sequence;", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/util/SparseArray;", "", "Landroid/util/SparseBooleanArray;", "", "Landroid/util/SparseIntArray;", "forEachByIndex", "", "", C3898x.f4339h, "Lkotlin/Function1;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "forEachReversedByIndex", "forEachReversedWithIndex", "Lkotlin/Function2;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "forEachWithIndex", "commons_release"}, m3962k = 2, m3963mv = {1, 1, 5})
/* loaded from: classes2.dex */
public final class ArraysKt {
    public static final <T> void forEachByIndex(T[] receiver, Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        int length = receiver.length - 1;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            f.invoke(receiver[i]);
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    public static final <T> void forEachWithIndex(T[] receiver, Function2<? super Integer, ? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        int length = receiver.length - 1;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            f.invoke(Integer.valueOf(i), receiver[i]);
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    public static final <T> void forEachReversedByIndex(T[] receiver, Function1<? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        for (int length = receiver.length - 1; length >= 0; length--) {
            f.invoke(receiver[length]);
        }
    }

    public static final <T> void forEachReversedWithIndex(T[] receiver, Function2<? super Integer, ? super T, Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        for (int length = receiver.length - 1; length >= 0; length--) {
            f.invoke(Integer.valueOf(length), receiver[length]);
        }
    }

    public static final <T> Sequence<T> asSequence(SparseArray<T> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new SparseArraySequence(receiver);
    }

    public static final <T> Sequence<Boolean> asSequence(SparseBooleanArray receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new SparseBooleanArraySequence(receiver);
    }

    public static final <T> Sequence<Integer> asSequence(SparseIntArray receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new SparseIntArraySequence(receiver);
    }
}
