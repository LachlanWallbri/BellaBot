package org.checkerframework.checker.calledmethods.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.ConditionalPostconditionAnnotation;
import org.checkerframework.framework.qual.InheritedAnnotation;
import org.checkerframework.framework.qual.QualifierArgument;
import org.mozilla.javascript.ES6Iterator;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@InheritedAnnotation
@ConditionalPostconditionAnnotation(qualifier = CalledMethods.class)
@Documented
@Repeatable(List.class)
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes9.dex */
public @interface EnsuresCalledMethodsIf {

    @Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
    @InheritedAnnotation
    @ConditionalPostconditionAnnotation(qualifier = CalledMethods.class)
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes9.dex */
    public @interface List {
        EnsuresCalledMethodsIf[] value();
    }

    String[] expression();

    @QualifierArgument(ES6Iterator.VALUE_PROPERTY)
    String[] methods();

    boolean result();
}
