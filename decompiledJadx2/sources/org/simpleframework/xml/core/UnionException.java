package org.simpleframework.xml.core;

/* loaded from: classes9.dex */
public class UnionException extends PersistenceException {
    public UnionException(String str, Object... objArr) {
        super(String.format(str, objArr), new Object[0]);
    }
}
