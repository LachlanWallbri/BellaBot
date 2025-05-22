package org.bouncycastle.util;

/* loaded from: classes9.dex */
public interface Selector<T> extends Cloneable {
    Object clone();

    boolean match(T t);
}
