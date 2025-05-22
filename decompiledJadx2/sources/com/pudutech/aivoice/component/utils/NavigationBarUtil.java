package com.pudutech.aivoice.component.utils;

import android.os.Build;
import android.view.View;
import android.view.Window;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: NavigationBarUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/aivoice/component/utils/NavigationBarUtil;", "", "()V", "clearFocusNotAle", "", "window", "Landroid/view/Window;", "focusNotAle", "hideNavigationBar", "component_voiceinteraction_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class NavigationBarUtil {
    public static final NavigationBarUtil INSTANCE = new NavigationBarUtil();

    private NavigationBarUtil() {
    }

    public final void hideNavigationBar(final Window window) {
        Intrinsics.checkParameterIsNotNull(window, "window");
        View decorView = window.getDecorView();
        Intrinsics.checkExpressionValueIsNotNull(decorView, "window.decorView");
        decorView.setSystemUiVisibility(2);
        window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.pudutech.aivoice.component.utils.NavigationBarUtil$hideNavigationBar$1
            @Override // android.view.View.OnSystemUiVisibilityChangeListener
            public final void onSystemUiVisibilityChange(int i) {
                if (Build.VERSION.SDK_INT >= 19) {
                    View decorView2 = window.getDecorView();
                    Intrinsics.checkExpressionValueIsNotNull(decorView2, "window.decorView");
                    decorView2.setSystemUiVisibility(3846);
                }
            }
        });
    }

    public final void focusNotAle(Window window) {
        Intrinsics.checkParameterIsNotNull(window, "window");
        window.setFlags(8, 8);
    }

    public final void clearFocusNotAle(Window window) {
        Intrinsics.checkParameterIsNotNull(window, "window");
        window.clearFlags(8);
    }
}
