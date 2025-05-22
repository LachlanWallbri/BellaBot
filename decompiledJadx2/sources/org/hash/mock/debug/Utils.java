package org.hash.mock.debug;

import android.app.Application;
import android.util.TypedValue;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class Utils {
    private static Application application = BubbleContext.getAppContext();

    public static int getStatusBarHeight() {
        int identifier = application.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return BubbleContext.getAppContext().getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int getScreenWidth() {
        try {
            return application.getResources().getDisplayMetrics().widthPixels;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int getScreenHeight() {
        try {
            return application.getResources().getDisplayMetrics().heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static float dp2px(float f) {
        return TypedValue.applyDimension(1, f, application.getResources().getDisplayMetrics());
    }
}
