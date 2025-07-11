package org.jetbrains.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes9.dex
 */
@Target({ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes2.dex */
public @interface PropertyKey {
    String resourceBundle();
}
