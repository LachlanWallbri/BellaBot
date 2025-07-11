package org.checkerframework.checker.units.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.SubtypeOf;

@Target({})
@Documented
@Retention(RetentionPolicy.RUNTIME)
@SubtypeOf({UnknownUnits.class})
/* loaded from: classes9.dex */
public @interface MixedUnits {
}
