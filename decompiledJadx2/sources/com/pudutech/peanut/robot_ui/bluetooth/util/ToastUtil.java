package com.pudutech.peanut.robot_ui.bluetooth.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/* loaded from: classes5.dex */
public class ToastUtil {
    public static void showToast(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        Toast.makeText(context, str, 0).show();
    }

    public static void showToast(Context context, int i) {
        if (context == null || i == 0) {
            return;
        }
        Toast.makeText(context, i, 0).show();
    }

    public static void showToastCenter(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        Toast makeText = Toast.makeText(context, str, 0);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }
}
