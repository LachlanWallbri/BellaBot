package com.pudutech.disinfect.baselib.ext.util;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.view.WindowManager;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: SystemServiceExt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001c\u0010\u0011\u001a\u0004\u0018\u0001H\u0012\"\u0006\b\u0000\u0010\u0012\u0018\u0001*\u00020\u0002H\u0086\b¢\u0006\u0002\u0010\u0013\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\u0004\u0018\u00010\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0017\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0017\u0010\r\u001a\u0004\u0018\u00010\u000e*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, m3961d2 = {"activityManager", "Landroid/app/ActivityManager;", "Landroid/content/Context;", "getActivityManager", "(Landroid/content/Context;)Landroid/app/ActivityManager;", "connectiveManager", "Landroid/net/ConnectivityManager;", "getConnectiveManager", "(Landroid/content/Context;)Landroid/net/ConnectivityManager;", "wifiManger", "Landroid/net/wifi/WifiManager;", "getWifiManger", "(Landroid/content/Context;)Landroid/net/wifi/WifiManager;", "windowManager", "Landroid/view/WindowManager;", "getWindowManager", "(Landroid/content/Context;)Landroid/view/WindowManager;", "getSystemService", ExifInterface.GPS_DIRECTION_TRUE, "(Landroid/content/Context;)Ljava/lang/Object;", "module_baselib_robot_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SystemServiceExtKt {
    public static final /* synthetic */ <T> T getSystemService(Context getSystemService) {
        Intrinsics.checkParameterIsNotNull(getSystemService, "$this$getSystemService");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return (T) ContextCompat.getSystemService(getSystemService, Object.class);
    }

    public static final WindowManager getWindowManager(Context windowManager) {
        Intrinsics.checkParameterIsNotNull(windowManager, "$this$windowManager");
        return (WindowManager) ContextCompat.getSystemService(windowManager, WindowManager.class);
    }

    public static final ActivityManager getActivityManager(Context activityManager) {
        Intrinsics.checkParameterIsNotNull(activityManager, "$this$activityManager");
        return (ActivityManager) ContextCompat.getSystemService(activityManager, ActivityManager.class);
    }

    public static final ConnectivityManager getConnectiveManager(Context connectiveManager) {
        Intrinsics.checkParameterIsNotNull(connectiveManager, "$this$connectiveManager");
        return (ConnectivityManager) ContextCompat.getSystemService(connectiveManager, ConnectivityManager.class);
    }

    public static final WifiManager getWifiManger(Context wifiManger) {
        Intrinsics.checkParameterIsNotNull(wifiManger, "$this$wifiManger");
        return (WifiManager) ContextCompat.getSystemService(wifiManger, WifiManager.class);
    }
}
