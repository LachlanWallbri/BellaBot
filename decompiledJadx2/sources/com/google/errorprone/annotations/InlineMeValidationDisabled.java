package com.google.errorprone.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
/* loaded from: classes3.dex */
public @interface InlineMeValidationDisabled {
    String value();
}
