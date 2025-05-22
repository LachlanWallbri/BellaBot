package org.checkerframework.checker.index.qual;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.ConditionalPostconditionAnnotation;
import org.checkerframework.framework.qual.InheritedAnnotation;
import org.checkerframework.framework.qual.JavaExpression;
import org.checkerframework.framework.qual.QualifierArgument;
import org.mozilla.javascript.ES6Iterator;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@InheritedAnnotation
@ConditionalPostconditionAnnotation(qualifier = LTLengthOf.class)
@Documented
@Repeatable(List.class)
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes9.dex */
public @interface EnsuresLTLengthOfIf {

    @Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
    @InheritedAnnotation
    @ConditionalPostconditionAnnotation(qualifier = LTLengthOf.class)
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes9.dex */
    public @interface List {
        EnsuresLTLengthOfIf[] value();
    }

    String[] expression();

    @QualifierArgument(TypedValues.Cycle.S_WAVE_OFFSET)
    @JavaExpression
    String[] offset() default {};

    boolean result();

    @QualifierArgument(ES6Iterator.VALUE_PROPERTY)
    @JavaExpression
    String[] targetValue();
}
