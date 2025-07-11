package androidx.room;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes.dex */
public @interface ColumnInfo {
    public static final int BINARY = 2;
    public static final int BLOB = 5;
    public static final String INHERIT_FIELD_NAME = "[field-name]";
    public static final int INTEGER = 3;
    public static final int LOCALIZED = 5;
    public static final int NOCASE = 3;
    public static final int REAL = 4;
    public static final int RTRIM = 4;
    public static final int TEXT = 2;
    public static final int UNDEFINED = 1;
    public static final int UNICODE = 6;
    public static final int UNSPECIFIED = 1;
    public static final String VALUE_UNSPECIFIED = "[value-unspecified]";

    /* JADX WARN: Classes with same name are omitted:
      
     */
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes.dex */
    public @interface Collate {
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes.dex */
    public @interface SQLiteTypeAffinity {
    }

    int collate() default 1;

    String defaultValue() default "[value-unspecified]";

    boolean index() default false;

    String name() default "[field-name]";

    int typeAffinity() default 1;
}
