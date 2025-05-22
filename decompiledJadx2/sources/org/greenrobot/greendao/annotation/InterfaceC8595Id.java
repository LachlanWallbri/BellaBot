package org.greenrobot.greendao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
/* renamed from: org.greenrobot.greendao.annotation.Id */
/* loaded from: classes9.dex */
public @interface InterfaceC8595Id {
    boolean autoincrement() default false;
}
