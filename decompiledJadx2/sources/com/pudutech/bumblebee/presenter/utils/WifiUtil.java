package com.pudutech.bumblebee.presenter.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: WifiUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/utils/WifiUtil;", "", "()V", "mac", "", "getMac", "()Ljava/lang/String;", "isNetworkAvailable", "", "context", "Landroid/content/Context;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class WifiUtil {
    public static final WifiUtil INSTANCE = new WifiUtil();

    private WifiUtil() {
    }

    public final String getMac() {
        return com.pudutech.disinfect.baselib.util.WifiUtil.INSTANCE.getMac();
    }

    public final boolean isNetworkAvailable(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("connectivity");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        NetworkInfo activeNetInfo = ((ConnectivityManager) systemService).getActiveNetworkInfo();
        try {
            Intrinsics.checkExpressionValueIsNotNull(activeNetInfo, "activeNetInfo");
            return activeNetInfo.isConnected();
        } catch (Exception unused) {
            return false;
        }
    }
}
