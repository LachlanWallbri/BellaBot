package com.google.errorprone.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.lang.model.element.Modifier;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Target({ElementType.ANNOTATION_TYPE})
@Documented
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes3.dex */
public @interface IncompatibleModifiers {
    Modifier[] value();
}
