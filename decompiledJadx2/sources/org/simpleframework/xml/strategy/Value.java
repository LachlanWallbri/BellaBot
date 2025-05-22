package org.simpleframework.xml.strategy;

/* loaded from: classes9.dex */
public interface Value {
    int getLength();

    Class getType();

    Object getValue();

    boolean isReference();

    void setValue(Object obj);
}
