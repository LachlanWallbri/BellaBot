package com.google.common.collect;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
final class UsingToStringOrdering extends Ordering<Object> implements Serializable {
    static final UsingToStringOrdering INSTANCE = new UsingToStringOrdering();
    private static final long serialVersionUID = 0;

    public String toString() {
        return "Ordering.usingToString()";
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return obj.toString().compareTo(obj2.toString());
    }

    private Object readResolve() {
        return INSTANCE;
    }

    private UsingToStringOrdering() {
    }
}
