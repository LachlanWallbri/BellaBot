package org.checkerframework.common.value.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.SubtypeOf;

@Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@SubtypeOf({UnknownVal.class})
/* loaded from: classes9.dex */
public @interface ArrayLenRange {
    int from() default 0;

    /* renamed from: to */
    int m4133to() default Integer.MAX_VALUE;
}
