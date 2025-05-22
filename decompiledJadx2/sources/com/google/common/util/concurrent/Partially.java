package com.google.common.util.concurrent;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
final class Partially {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD})
    @Documented
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes3.dex */
    @interface GwtIncompatible {
        String value();
    }

    private Partially() {
    }
}
