package io.reactivex.internal.util;

import io.reactivex.functions.BiFunction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class MergerBiFunction<T> implements BiFunction<List<T>, List<T>, List<T>> {
    final Comparator<? super T> comparator;

    public MergerBiFunction(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x0037, code lost:
    
        r3 = null;
     */
    @Override // io.reactivex.functions.BiFunction
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public List<T> apply(List<T> list, List<T> list2) throws Exception {
        T t;
        int size = list.size() + list2.size();
        if (size == 0) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(size);
        Iterator<T> it = list.iterator();
        Iterator<T> it2 = list2.iterator();
        T next = it.hasNext() ? it.next() : null;
        if (it2.hasNext()) {
            t = it2.next();
            while (next != null && t != null) {
                if (this.comparator.compare(next, t) < 0) {
                    arrayList.add(next);
                    next = it.hasNext() ? it.next() : null;
                } else {
                    arrayList.add(t);
                    if (it2.hasNext()) {
                        t = it2.next();
                    }
                }
            }
            if (next != null) {
                arrayList.add(next);
                while (it.hasNext()) {
                    arrayList.add(it.next());
                }
            } else {
                arrayList.add(t);
                while (it2.hasNext()) {
                    arrayList.add(it2.next());
                }
            }
            return arrayList;
        }
        t = null;
    }
}
