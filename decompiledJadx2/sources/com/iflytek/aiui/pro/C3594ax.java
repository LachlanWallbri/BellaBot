package com.iflytek.aiui.pro;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.iflytek.cloud.SpeechConstant;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.ax */
/* loaded from: classes.dex */
public class C3594ax {

    /* renamed from: a */
    public static String[][] f2400a = {new String[]{"vendor", Build.MANUFACTURER}, new String[]{"model", Build.MODEL}};

    /* renamed from: b */
    private static C3581ak f2401b = new C3581ak();

    /* renamed from: c */
    private static boolean f2402c = false;

    /* renamed from: com.iflytek.aiui.pro.ax$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    static final class AnonymousClass1 implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f2403a;

        /* renamed from: b */
        final /* synthetic */ String f2404b;

        AnonymousClass1(Context context, String str) {
            this.f2403a = context;
            this.f2404b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            C3594ax.a(this.f2403a, "iflytek.deviceid.key", this.f2404b);
        }
    }

    /* renamed from: com.iflytek.aiui.pro.ax$2, reason: invalid class name */
    /* loaded from: classes3.dex */
    static final class AnonymousClass2 implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f2405a;

        /* renamed from: b */
        final /* synthetic */ String f2406b;

        AnonymousClass2(Context context, String str) {
            this.f2405a = context;
            this.f2406b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            C3594ax.a(this.f2405a, "iflytek.deviceid.key", this.f2406b);
            C3594ax.a(this.f2405a, C3594ax.a(), ".2F6E2C5B63F0F83B", this.f2406b);
        }
    }

    /* renamed from: com.iflytek.aiui.pro.ax$3, reason: invalid class name */
    /* loaded from: classes3.dex */
    static final class AnonymousClass3 implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f2407a;

        /* renamed from: b */
        final /* synthetic */ String f2408b;

        AnonymousClass3(Context context, String str) {
            this.f2407a = context;
            this.f2408b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            C3594ax.a(this.f2407a, "iflytek.unique.deviceid.key", this.f2408b);
        }
    }

    /* renamed from: com.iflytek.aiui.pro.ax$4, reason: invalid class name */
    /* loaded from: classes3.dex */
    static final class AnonymousClass4 implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f2409a;

        /* renamed from: b */
        final /* synthetic */ String f2410b;

        AnonymousClass4(Context context, String str) {
            this.f2409a = context;
            this.f2410b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            C3594ax.a(this.f2409a, "iflytek.unique.deviceid.key", this.f2410b);
            C3594ax.a(this.f2409a, C3594ax.b(), ".1B5A88FBE00E5E45", this.f2410b);
        }
    }

    /* renamed from: com.iflytek.aiui.pro.ax$5, reason: invalid class name */
    /* loaded from: classes3.dex */
    static final class AnonymousClass5 implements Runnable {

        /* renamed from: a */
        final /* synthetic */ Context f2411a;

        /* renamed from: b */
        final /* synthetic */ String f2412b;

        AnonymousClass5(Context context, String str) {
            this.f2411a = context;
            this.f2412b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            C3594ax.a(this.f2411a, "iflytek.unique.deviceid.key", this.f2412b);
            C3594ax.a(this.f2411a, C3594ax.b(), ".1B5A88FBE00E5E45", this.f2412b);
            C3594ax.b(this.f2411a, "com.iflytek.unique.id", "pref.deviceid.key", this.f2412b);
        }
    }

    /* renamed from: a */
    public static C3581ak m1106a(Context context) {
        C3581ak c3581ak;
        synchronized (C3594ax.class) {
            if (f2402c) {
                m1107a(context, f2401b, true);
            } else {
                m1109b(context);
            }
            c3581ak = f2401b;
        }
        return c3581ak;
    }

    /* renamed from: a */
    public static void m1107a(Context context, C3581ak c3581ak, Boolean bool) {
        if (context == null) {
            c3581ak.m1018a(SpeechConstant.NET_TYPE, "none", bool.booleanValue());
            return;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            c3581ak.m1018a(SpeechConstant.NET_TYPE, "none", bool.booleanValue());
        } else {
            c3581ak.m1018a(SpeechConstant.NET_TYPE, C3590at.m1069a(activeNetworkInfo), bool.booleanValue());
            c3581ak.m1018a("net_subtype", C3590at.m1070b(activeNetworkInfo), bool.booleanValue());
        }
    }

    /* renamed from: a */
    public static void m1108a(C3581ak c3581ak, Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            c3581ak.m1017a("pkg_name", context.getPackageName());
            c3581ak.m1017a("app_ver", packageInfo.versionName);
            c3581ak.m1017a("app_name", applicationInfo.loadLabel(context.getPackageManager()).toString());
        } catch (Exception unused) {
        }
    }

    /* renamed from: b */
    private static void m1109b(Context context) {
        try {
            f2401b.m1016a();
            f2401b.m1017a("platform", "Android");
            m1108a(f2401b, context);
            f2401b.m1017a("imei", C3601bb.m1154g(context));
            f2401b.m1017a("imsi", C3601bb.m1156h(context));
            f2401b.m1017a("os_release", Build.VERSION.RELEASE);
            for (int i = 0; i < f2400a.length; i++) {
                f2401b.m1017a(f2400a[i][0], f2400a[i][1]);
            }
            m1107a(context, f2401b, false);
            f2401b.m1017a("mac", C3601bb.m1129a(context));
            f2401b.m1017a("resolution", C3601bb.m1142c(context));
            f2402c = true;
        } catch (Exception unused) {
            f2402c = false;
        }
    }
}
