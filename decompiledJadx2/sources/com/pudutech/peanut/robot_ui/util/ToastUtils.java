package com.pudutech.peanut.robot_ui.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.pudutech.peanut.robot_ui.C5508R;

/* loaded from: classes5.dex */
public class ToastUtils {
    private static Toast toastInstance;

    private ToastUtils() {
        throw new AssertionError();
    }

    public static void show(Context context, int i) {
        show(context, context.getResources().getText(i), 0);
    }

    public static void show(Context context, int i, int i2) {
        show(context, context.getResources().getText(i), i2);
    }

    public static void show(Context context, CharSequence charSequence) {
        show(context, charSequence, 0);
    }

    public static void show(Context context, CharSequence charSequence, int i) {
        showMsg(context, charSequence, i);
    }

    public static void show(Context context, int i, Object... objArr) {
        show(context, String.format(context.getResources().getString(i), objArr), 0);
    }

    public static void show(Context context, String str, Object... objArr) {
        show(context, String.format(str, objArr), 0);
    }

    public static void show(Context context, int i, int i2, Object... objArr) {
        show(context, String.format(context.getResources().getString(i), objArr), i2);
    }

    public static void show(Context context, String str, int i, Object... objArr) {
        show(context, String.format(str, objArr), i);
    }

    public static void showMsg(Context context, CharSequence charSequence, int i) {
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 28) {
            Toast toast = toastInstance;
            if (toast != null) {
                toast.cancel();
            }
            toastInstance = new Toast(context);
        } else if (toastInstance == null) {
            toastInstance = new Toast(context);
        }
        View inflate = LayoutInflater.from(context).inflate(C5508R.layout.layout_custom_notification, (ViewGroup) null);
        ((TextView) inflate.findViewById(C5508R.id.id_custom_notification_textview)).setText(charSequence);
        toastInstance.setGravity(17, 0, 0);
        toastInstance.setView(inflate);
        toastInstance.setDuration(i);
        toastInstance.show();
    }
}
