package butterknife;

import butterknife.internal.ListenerClass;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
@ListenerClass(callbacks = Callback.class, setter = "setOnItemSelectedListener", targetType = "android.widget.AdapterView<?>", type = "android.widget.AdapterView.OnItemSelectedListener")
/* loaded from: classes.dex */
public @interface OnItemSelected {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum Callback {
        ITEM_SELECTED,
        NOTHING_SELECTED
    }

    Callback callback() default Callback.ITEM_SELECTED;

    int[] value() default {-1};
}
