package com.pudu.library.loracall.utils;

import android.view.View;
import android.view.Window;

/* loaded from: classes4.dex */
public class LoraNavigationBarUtil {
    public static void hideNavigationBar(Window window) {
        window.getDecorView().setSystemUiVisibility(5895);
    }

    public static void focusNotAle(Window window) {
        window.setFlags(8, 8);
    }

    public static void clearFocusNotAle(Window window) {
        window.clearFlags(8);
    }

    public static void hideNavigationBar(View view) {
        view.setSystemUiVisibility(5895);
    }
}
