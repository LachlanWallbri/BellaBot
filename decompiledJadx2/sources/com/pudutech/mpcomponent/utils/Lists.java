package com.pudutech.mpcomponent.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class Lists {
    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> ArrayList<T> newArrayList(Collection<T> collection) {
        return new ArrayList<>(collection);
    }

    public static <T> ArrayList<T> newArrayList(T... tArr) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (T t : tArr) {
            arrayList.add(t);
        }
        return arrayList;
    }

    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }
}
