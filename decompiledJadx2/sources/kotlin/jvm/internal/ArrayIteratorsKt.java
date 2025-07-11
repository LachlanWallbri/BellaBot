package kotlin.jvm.internal;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import kotlin.Metadata;
import kotlin.collections.BooleanIterator;
import kotlin.collections.ByteIterator;
import kotlin.collections.CharIterator;
import kotlin.collections.DoubleIterator;
import kotlin.collections.FloatIterator;
import kotlin.collections.IntIterator;
import kotlin.collections.LongIterator;
import kotlin.collections.ShortIterator;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: ArrayIterators.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\u0018\u0002\n\u0002\u0010\u0013\n\u0002\u0018\u0002\n\u0002\u0010\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0017\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0000\u001a\u00020\u00042\u0006\u0010\u0002\u001a\u00020\u0005\u001a\u000e\u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0007\u001a\u000e\u0010\u0000\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\t\u001a\u000e\u0010\u0000\u001a\u00020\n2\u0006\u0010\u0002\u001a\u00020\u000b\u001a\u000e\u0010\u0000\u001a\u00020\f2\u0006\u0010\u0002\u001a\u00020\r\u001a\u000e\u0010\u0000\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u000f\u001a\u000e\u0010\u0000\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u0011¨\u0006\u0012"}, m3961d2 = {"iterator", "Lkotlin/collections/BooleanIterator;", TmpConstant.TYPE_VALUE_ARRAY, "", "Lkotlin/collections/ByteIterator;", "", "Lkotlin/collections/CharIterator;", "", "Lkotlin/collections/DoubleIterator;", "", "Lkotlin/collections/FloatIterator;", "", "Lkotlin/collections/IntIterator;", "", "Lkotlin/collections/LongIterator;", "", "Lkotlin/collections/ShortIterator;", "", "kotlin-stdlib"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ArrayIteratorsKt {
    public static final ByteIterator iterator(byte[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayByteIterator(array);
    }

    public static final CharIterator iterator(char[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayCharIterator(array);
    }

    public static final ShortIterator iterator(short[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayShortIterator(array);
    }

    public static final IntIterator iterator(int[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayIntIterator(array);
    }

    public static final LongIterator iterator(long[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayLongIterator(array);
    }

    public static final FloatIterator iterator(float[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayFloatIterator(array);
    }

    public static final DoubleIterator iterator(double[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayDoubleIterator(array);
    }

    public static final BooleanIterator iterator(boolean[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayBooleanIterator(array);
    }
}
