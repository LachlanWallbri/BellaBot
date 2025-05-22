package org.checkerframework.checker.calledmethods.qual;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.PostconditionAnnotation;
import org.checkerframework.framework.qual.QualifierArgument;
import org.mozilla.javascript.ES6Iterator;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@PostconditionAnnotation(qualifier = CalledMethods.class)
/* loaded from: classes9.dex */
public @interface EnsuresCalledMethods {
    @QualifierArgument(ES6Iterator.VALUE_PROPERTY)
    String[] methods();

    String[] value();
}
