package androidx.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes.dex */
public @interface RequiresPermission {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    /* loaded from: classes.dex */
    public @interface Read {
        RequiresPermission value() default @RequiresPermission;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    /* loaded from: classes.dex */
    public @interface Write {
        RequiresPermission value() default @RequiresPermission;
    }

    String[] allOf() default {};

    String[] anyOf() default {};

    boolean conditional() default false;

    String value() default "";
}
