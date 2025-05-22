package com.pudutech.peanut.robot_ui.util;

import android.os.Build;
import android.view.View;
import android.view.Window;

/* loaded from: classes5.dex */
public class NavigationBarUtil {
    public static void hideNavigationBar(Window window) {
        window.getDecorView().setSystemUiVisibility(5895);
    }

    public static void focusNotAle(Window window) {
        window.setFlags(8, 8);
    }

    public static void clearFocusNotAle(Window window) {
        window.clearFlags(8);
    }

    public static void hideNavigationBars(final Window window) {
        window.getDecorView().setSystemUiVisibility(2);
        window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.pudutech.peanut.robot_ui.util.NavigationBarUtil.1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public void onSystemUiVisibilityChange(int i) {
                window.getDecorView().setSystemUiVisibility(Build.VERSION.SDK_INT >= 19 ? 5894 : 1799);
            }
        });
    }
}
