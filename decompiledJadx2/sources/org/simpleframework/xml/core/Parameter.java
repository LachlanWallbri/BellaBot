package org.simpleframework.xml.core;

import java.lang.annotation.Annotation;

/* loaded from: classes9.dex */
interface Parameter {
    Annotation getAnnotation();

    Expression getExpression();

    int getIndex();

    Object getKey();

    String getName();

    String getPath();

    Class getType();

    boolean isAttribute();

    boolean isPrimitive();

    boolean isRequired();

    boolean isText();

    String toString();
}
