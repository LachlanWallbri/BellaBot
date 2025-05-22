package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.util.UUID;

/* renamed from: com.iflytek.cloud.thirdparty.bg */
/* loaded from: classes3.dex */
public class C3722bg {
    /* renamed from: a */
    public static String m2042a() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /* renamed from: a */
    public static boolean m2043a(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        return m2044a(context, "android.permission.INTERNET") && (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable();
    }

    /* renamed from: a */
    private static boolean m2044a(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager != null && packageManager.checkPermission(str, context.getPackageName()) == 0;
    }

    /* renamed from: b */
    public static SharedPreferences m2045b(Context context) {
        return context.getSharedPreferences("iflytek_collect_state", 0);
    }
}
