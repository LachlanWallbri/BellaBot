package com.google.common.base;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
final class Java8Usage {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes3.dex */
    private @interface SomeTypeAnnotation {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$performCheck$0() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String performCheck() {
        new Runnable() { // from class: com.google.common.base.-$$Lambda$Java8Usage$_bAPwwc4Nm33kMN79iqPlKkAoBQ
            @Override // java.lang.Runnable
            public final void run() {
                Java8Usage.lambda$performCheck$0();
            }
        }.run();
        return "";
    }

    private Java8Usage() {
    }
}
