package com.pudutech.disinfect.baselib.ext.view;

import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: NavigationExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t\u001a\n\u0010\u0006\u001a\u00020\u0007*\u00020\n\u001a(\u0010\u000b\u001a\u00020\f*\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0001\"\u001a\u0010\u0000\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005¨\u0006\u0012"}, m3961d2 = {"lastNavTime", "", "getLastNavTime", "()J", "setLastNavTime", "(J)V", "nav", "Landroidx/navigation/NavController;", "view", "Landroid/view/View;", "Landroidx/fragment/app/Fragment;", "navigateAction", "", "resId", "", "bundle", "Landroid/os/Bundle;", "interval", "module_baselib_robot_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class NavigationExtKt {
    private static long lastNavTime;

    public static final NavController nav(Fragment nav) {
        Intrinsics.checkParameterIsNotNull(nav, "$this$nav");
        NavController findNavController = NavHostFragment.findNavController(nav);
        Intrinsics.checkExpressionValueIsNotNull(findNavController, "NavHostFragment.findNavController(this)");
        return findNavController;
    }

    public static final NavController nav(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        NavController findNavController = Navigation.findNavController(view);
        Intrinsics.checkExpressionValueIsNotNull(findNavController, "Navigation.findNavController(view)");
        return findNavController;
    }

    public static final long getLastNavTime() {
        return lastNavTime;
    }

    public static final void setLastNavTime(long j) {
        lastNavTime = j;
    }

    public static /* synthetic */ void navigateAction$default(NavController navController, int i, Bundle bundle, long j, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            bundle = (Bundle) null;
        }
        if ((i2 & 4) != 0) {
            j = 500;
        }
        navigateAction(navController, i, bundle, j);
    }

    public static final void navigateAction(NavController navigateAction, int i, Bundle bundle, long j) {
        Intrinsics.checkParameterIsNotNull(navigateAction, "$this$navigateAction");
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis >= lastNavTime + j) {
            lastNavTime = currentTimeMillis;
            navigateAction.navigate(i, bundle);
        }
    }
}
