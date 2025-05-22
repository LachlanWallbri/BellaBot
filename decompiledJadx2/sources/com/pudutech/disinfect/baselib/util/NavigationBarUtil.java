package com.pudutech.disinfect.baselib.util;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes5.dex */
public class NavigationBarUtil {
    public static void hideNavigationBar(Window window) {
        window.getDecorView().setSystemUiVisibility(5895);
    }

    public static void fullScreen(Window window) {
        if (window == null) {
            return;
        }
        View decorView = window.getDecorView();
        window.addFlags(Integer.MIN_VALUE);
        decorView.setSystemUiVisibility(4614);
        hideBottomNav(window);
    }

    public static void hideBottomNav(Window window) {
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.systemUiVisibility = window.getDecorView().getSystemUiVisibility() | 2 | 2048;
        window.setAttributes(attributes);
    }

    public static void focusNotAle(Window window) {
        window.setFlags(8, 8);
    }

    public static void clearFocusNotAle(Window window) {
        window.clearFlags(8);
    }
}
