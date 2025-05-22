package androidx.room;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes.dex */
public @interface Update {
    Class<?> entity() default Object.class;

    int onConflict() default 3;
}
