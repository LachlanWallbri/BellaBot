package butterknife;

import butterknife.internal.ListenerClass;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ListenerClass(callbacks = Callback.class, remover = "removeOnPageChangeListener", setter = "addOnPageChangeListener", targetType = "androidx.viewpager.widget.ViewPager", type = "androidx.viewpager.widget.ViewPager.OnPageChangeListener")
/* loaded from: classes.dex */
public @interface OnPageChange {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum Callback {
        PAGE_SELECTED,
        PAGE_SCROLLED,
        PAGE_SCROLL_STATE_CHANGED
    }

    Callback callback() default Callback.PAGE_SELECTED;

    int[] value() default {-1};
}
