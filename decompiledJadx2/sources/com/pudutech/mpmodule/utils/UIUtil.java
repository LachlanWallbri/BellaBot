package com.pudutech.mpmodule.utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.Window;
import android.view.WindowManager;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class UIUtil {
    public static int sDefaultListNumber = 6;

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static void setWindowAlpha(Window window, float f) {
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = f;
        window.setAttributes(attributes);
    }
}
