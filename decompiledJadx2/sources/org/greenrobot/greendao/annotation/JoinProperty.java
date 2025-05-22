package org.greenrobot.greendao.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
@Target({})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes9.dex */
public @interface JoinProperty {
    String name();

    String referencedName();
}
