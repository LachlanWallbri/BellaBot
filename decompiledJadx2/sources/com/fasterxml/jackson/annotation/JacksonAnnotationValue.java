package com.fasterxml.jackson.annotation;

import java.lang.annotation.Annotation;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface JacksonAnnotationValue<A extends Annotation> {
    Class<A> valueFor();
}
