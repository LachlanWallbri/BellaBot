package com.pudutech.importmusic;

import android.os.Build;
import android.view.View;
import android.view.Window;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes5.dex */
public class NavigationBarUtil {
    public static void hideNavigationBar(final Window window) {
        window.getDecorView().setSystemUiVisibility(2);
        window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.pudutech.importmusic.NavigationBarUtil.1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public void onSystemUiVisibilityChange(int i) {
                if (Build.VERSION.SDK_INT >= 19) {
                    window.getDecorView().setSystemUiVisibility(3846);
                }
            }
        });
    }

    public static void hideNavigationBarForDialog(Window window) {
        window.getDecorView().setSystemUiVisibility(5895);
    }

    public static void focusNotAle(Window window) {
        window.setFlags(8, 8);
    }

    public static void clearFocusNotAle(Window window) {
        window.clearFlags(8);
    }
}
