package butterknife;

import butterknife.internal.ListenerClass;
import butterknife.internal.ListenerMethod;
import com.amitshekhar.utils.DataType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ListenerClass(method = {@ListenerMethod(name = "onItemClick", parameters = {"android.widget.AdapterView<?>", "android.view.View", "int", DataType.LONG})}, setter = "setOnItemClickListener", targetType = "android.widget.AdapterView<?>", type = "android.widget.AdapterView.OnItemClickListener")
/* loaded from: classes.dex */
public @interface OnItemClick {
    int[] value() default {-1};
}
