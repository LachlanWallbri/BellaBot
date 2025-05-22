package org.apache.commons.compress.utils;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class Lists {
    public static <E> ArrayList<E> newArrayList() {
        return new ArrayList<>();
    }

    public static <E> ArrayList<E> newArrayList(Iterator<? extends E> it) {
        ArrayList<E> newArrayList = newArrayList();
        Iterators.addAll(newArrayList, it);
        return newArrayList;
    }

    private Lists() {
    }
}
