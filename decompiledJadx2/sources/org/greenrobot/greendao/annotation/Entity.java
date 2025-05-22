package org.greenrobot.greendao.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes9.dex */
public @interface Entity {
    boolean active() default false;

    boolean createInDb() default true;

    boolean generateConstructors() default true;

    boolean generateGettersSetters() default true;

    Index[] indexes() default {};

    String nameInDb() default "";

    Class protobuf() default void.class;

    String schema() default "default";
}
