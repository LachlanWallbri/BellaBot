package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;

/* compiled from: SPUtil.java */
/* renamed from: com.loc.cw */
/* loaded from: classes4.dex */
public final class C3875cw {
    /* renamed from: a */
    public static long m2951a(Context context, String str, String str2) {
        try {
            return context.getSharedPreferences(str, 0).getLong(str2, 0L);
        } catch (Throwable th) {
            C3880f.m3097a(th, "SPUtil", "getPrefsLong");
            return 0L;
        }
    }

    /* renamed from: a */
    public static String m2952a(Context context, String str, String str2, String str3) {
        try {
            return context.getSharedPreferences(str, 0).getString(str2, str3);
        } catch (Throwable th) {
            C3880f.m3097a(th, "SPUtil", "getPrefsInt");
            return str3;
        }
    }

    /* renamed from: a */
    public static void m2953a(Context context, String str, String str2, int i) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putInt(str2, i);
            m2956a(edit);
        } catch (Throwable th) {
            C3880f.m3097a(th, "SPUtil", "setPrefsInt");
        }
    }

    /* renamed from: a */
    public static void m2954a(Context context, String str, String str2, long j) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putLong(str2, j);
            m2956a(edit);
        } catch (Throwable th) {
            C3880f.m3097a(th, "SPUtil", "setPrefsLong");
        }
    }

    /* renamed from: a */
    public static void m2955a(Context context, String str, String str2, boolean z) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
            edit.putBoolean(str2, z);
            m2956a(edit);
        } catch (Throwable th) {
            C3880f.m3097a(th, "SPUtil", "updatePrefsBoolean");
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [com.loc.cw$1] */
    /* renamed from: a */
    public static void m2956a(final SharedPreferences.Editor editor) {
        if (editor == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 9) {
            editor.apply();
            return;
        }
        try {
            new AsyncTask<Void, Void, Void>() { // from class: com.loc.cw.1
                /* renamed from: a */
                private Void m2959a() {
                    try {
                        if (editor == null) {
                            return null;
                        }
                        editor.commit();
                        return null;
                    } catch (Throwable th) {
                        C3880f.m3097a(th, "SPUtil", "commit");
                        return null;
                    }
                }

                @Override // android.os.AsyncTask
                protected final /* synthetic */ Void doInBackground(Void[] voidArr) {
                    return m2959a();
                }
            }.execute(null, null, null);
        } catch (Throwable th) {
            C3880f.m3097a(th, "SPUtil", "commit1");
        }
    }

    /* renamed from: b */
    public static int m2957b(Context context, String str, String str2, int i) {
        try {
            return context.getSharedPreferences(str, 0).getInt(str2, i);
        } catch (Throwable th) {
            C3880f.m3097a(th, "SPUtil", "getPrefsInt");
            return i;
        }
    }

    /* renamed from: b */
    public static boolean m2958b(Context context, String str, String str2, boolean z) {
        try {
            return context.getSharedPreferences(str, 0).getBoolean(str2, z);
        } catch (Throwable th) {
            C3880f.m3097a(th, "SPUtil", "getPrefsBoolean");
            return z;
        }
    }
}
