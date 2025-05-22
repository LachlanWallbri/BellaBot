package com.loc;

import android.content.Context;

/* compiled from: RollBackDynamic.java */
/* renamed from: com.loc.cv */
/* loaded from: classes4.dex */
public final class C3874cv {

    /* renamed from: a */
    static boolean f4063a = false;

    /* renamed from: b */
    static boolean f4064b = false;

    /* renamed from: c */
    static boolean f4065c = false;

    /* renamed from: d */
    static boolean f4066d = false;

    /* renamed from: e */
    static int f4067e = 0;

    /* renamed from: f */
    static int f4068f = 0;

    /* renamed from: g */
    static boolean f4069g = true;

    /* renamed from: h */
    static boolean f4070h = false;

    /* renamed from: a */
    public static void m2944a(Context context) {
        try {
            if (f4065c && !f4063a) {
                C3875cw.m2953a(context, "loc", "startMark", C3875cw.m2957b(context, "loc", "startMark", 0) + 1);
                f4063a = true;
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "RollBackDynamic", "AddStartMark");
        }
    }

    /* renamed from: a */
    private static void m2945a(Context context, int i) {
        try {
            if (f4065c) {
                C3875cw.m2953a(context, "loc", "endMark", i);
                C3875cw.m2953a(context, "loc", "startMark", i);
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "RollBackDynamic", "resetMark");
        }
    }

    /* renamed from: a */
    public static void m2946a(Context context, C3893s c3893s) {
        if (f4066d) {
            return;
        }
        f4065c = C3819au.m2481a(context, c3893s);
        f4066d = true;
        if (f4065c || !C3880f.m3103c()) {
            return;
        }
        C3819au.m2480a(context, "loc");
        C3873cu.m2935a("dexrollbackstatistics", "RollBack because of version error");
    }

    /* renamed from: a */
    public static void m2947a(Context context, String str, String str2) {
        try {
            C3819au.m2480a(context, str);
            C3873cu.m2935a("dexrollbackstatistics", "RollBack because of " + str2);
        } catch (Throwable th) {
            C3880f.m3097a(th, "RollBackDynamic", "rollBackDynamicFile");
        }
    }

    /* renamed from: b */
    public static void m2948b(Context context) {
        try {
            if (!f4066d) {
                m2946a(context, C3880f.m3091a("loc"));
                f4066d = true;
            }
            if (f4065c && !f4064b) {
                C3875cw.m2953a(context, "loc", "endMark", C3875cw.m2957b(context, "loc", "endMark", 0) + 1);
                f4064b = true;
            }
        } catch (Throwable th) {
            C3880f.m3097a(th, "RollBackDynamic", "AddEndMark");
        }
    }

    /* renamed from: c */
    public static boolean m2949c(Context context) {
        try {
        } catch (Throwable th) {
            C3880f.m3097a(th, "RollBackDynamic", "checkMark");
        }
        if (!f4065c) {
            return false;
        }
        if (f4070h) {
            return f4069g;
        }
        if (f4067e == 0) {
            f4067e = C3875cw.m2957b(context, "loc", "startMark", 0);
        }
        if (f4068f == 0) {
            f4068f = C3875cw.m2957b(context, "loc", "endMark", 0);
        }
        if (!f4063a && !f4064b) {
            if (f4067e < f4068f) {
                m2945a(context, 0);
                f4069g = true;
            }
            if (f4067e - f4068f > 0 && f4067e > 99) {
                m2945a(context, 0);
                f4069g = true;
            }
            if (f4067e - f4068f > 0 && f4067e < 99) {
                m2945a(context, -2);
                f4069g = false;
            }
            if (f4067e - f4068f > 0 && f4068f < 0) {
                m2947a(context, "loc", "checkMark");
                f4069g = false;
            }
        }
        C3875cw.m2955a(context, "loc", "isload", f4069g);
        f4070h = true;
        return f4069g;
    }

    /* renamed from: d */
    public static boolean m2950d(Context context) {
        try {
            if (f4065c) {
                return C3875cw.m2958b(context, "loc", "isload", true);
            }
            return false;
        } catch (Throwable th) {
            C3880f.m3097a(th, "RollBackDynamic", "isLoad");
            return true;
        }
    }
}
