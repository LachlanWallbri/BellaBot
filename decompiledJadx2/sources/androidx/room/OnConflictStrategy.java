package androidx.room;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes.dex */
public @interface OnConflictStrategy {
    public static final int ABORT = 3;

    @Deprecated
    public static final int FAIL = 4;
    public static final int IGNORE = 5;
    public static final int REPLACE = 1;

    @Deprecated
    public static final int ROLLBACK = 2;
}
