package org.simpleframework.xml.transform;

/* loaded from: classes9.dex */
public interface Transform<T> {
    T read(String str) throws Exception;

    String write(T t) throws Exception;
}
