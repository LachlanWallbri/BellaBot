package defpackage;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.pudu.loracall.library.C3965R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ToastUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, m3961d2 = {"LToastUtils;", "", "()V", "toastInstance", "Landroid/widget/Toast;", "showMsg", "", "context", "Landroid/content/Context;", "text", "", TypedValues.Transition.S_DURATION, "", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ToastUtils {
    public static final ToastUtils INSTANCE = new ToastUtils();
    private static Toast toastInstance;

    private ToastUtils() {
    }

    public final void showMsg(Context context, CharSequence text, int duration) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(text, "text");
        if (context instanceof Activity) {
            context = context.getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context.getApplicationContext()");
        }
        if (Build.VERSION.SDK_INT >= 28) {
            Toast toast = toastInstance;
            if (toast != null) {
                if (toast == null) {
                    Intrinsics.throwNpe();
                }
                toast.cancel();
            }
            toastInstance = new Toast(context);
        } else if (toastInstance == null) {
            toastInstance = new Toast(context);
        }
        View inflate = LayoutInflater.from(context).inflate(C3965R.layout.lora_notification, (ViewGroup) null);
        TextView tv = (TextView) inflate.findViewById(C3965R.id.id_custom_notification_textview);
        Intrinsics.checkExpressionValueIsNotNull(tv, "tv");
        tv.setText(text);
        Toast toast2 = toastInstance;
        if (toast2 == null) {
            Intrinsics.throwNpe();
        }
        toast2.setGravity(17, 0, 0);
        Toast toast3 = toastInstance;
        if (toast3 == null) {
            Intrinsics.throwNpe();
        }
        toast3.setView(inflate);
        Toast toast4 = toastInstance;
        if (toast4 == null) {
            Intrinsics.throwNpe();
        }
        toast4.setDuration(duration);
        Toast toast5 = toastInstance;
        if (toast5 == null) {
            Intrinsics.throwNpe();
        }
        toast5.show();
    }
}
