package com.annimon.stream.internal;

import com.annimon.stream.function.IntFunction;
import com.annimon.stream.internal.SpinedBuffer;
import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class Operators {
    private Operators() {
    }

    public static <T> List<T> toList(Iterator<? extends T> it) {
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T, R> R[] toArray(Iterator<? extends T> it, IntFunction<R[]> intFunction) {
        List list = toList(it);
        int size = list.size();
        Compat.checkMaxArraySize(size);
        Object[] array = list.toArray(Compat.newArray(size, new Object[0]));
        R[] apply = intFunction.apply(size);
        System.arraycopy(array, 0, apply, 0, size);
        return apply;
    }

    public static int[] toIntArray(PrimitiveIterator.OfInt ofInt) {
        SpinedBuffer.OfInt ofInt2 = new SpinedBuffer.OfInt();
        while (ofInt.hasNext()) {
            ofInt2.accept(ofInt.nextInt());
        }
        return ofInt2.asPrimitiveArray();
    }

    public static long[] toLongArray(PrimitiveIterator.OfLong ofLong) {
        SpinedBuffer.OfLong ofLong2 = new SpinedBuffer.OfLong();
        while (ofLong.hasNext()) {
            ofLong2.accept(ofLong.nextLong());
        }
        return ofLong2.asPrimitiveArray();
    }

    public static double[] toDoubleArray(PrimitiveIterator.OfDouble ofDouble) {
        SpinedBuffer.OfDouble ofDouble2 = new SpinedBuffer.OfDouble();
        while (ofDouble.hasNext()) {
            ofDouble2.accept(ofDouble.nextDouble());
        }
        return ofDouble2.asPrimitiveArray();
    }
}
