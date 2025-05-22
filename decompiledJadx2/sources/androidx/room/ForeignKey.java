package androidx.room;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes.dex */
public @interface ForeignKey {
    public static final int CASCADE = 5;
    public static final int NO_ACTION = 1;
    public static final int RESTRICT = 2;
    public static final int SET_DEFAULT = 4;
    public static final int SET_NULL = 3;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes.dex */
    public @interface Action {
    }

    String[] childColumns();

    boolean deferred() default false;

    Class<?> entity();

    int onDelete() default 1;

    int onUpdate() default 1;

    String[] parentColumns();
}
