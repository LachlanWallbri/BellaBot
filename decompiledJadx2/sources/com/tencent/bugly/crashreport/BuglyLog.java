package com.tencent.bugly.crashreport;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.tencent.bugly.C5865b;
import com.tencent.bugly.proguard.C5941y;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* loaded from: classes7.dex */
public class BuglyLog {
    /* renamed from: v */
    public static void m3340v(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C5865b.f7644c) {
            Log.v(str, str2);
        }
        C5941y.m3829a(ExifInterface.GPS_MEASUREMENT_INTERRUPTED, str, str2);
    }

    /* renamed from: d */
    public static void m3336d(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C5865b.f7644c) {
            Log.d(str, str2);
        }
        C5941y.m3829a("D", str, str2);
    }

    /* renamed from: i */
    public static void m3339i(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C5865b.f7644c) {
            Log.i(str, str2);
        }
        C5941y.m3829a("I", str, str2);
    }

    /* renamed from: w */
    public static void m3341w(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C5865b.f7644c) {
            Log.w(str, str2);
        }
        C5941y.m3829a(ExifInterface.LONGITUDE_WEST, str, str2);
    }

    /* renamed from: e */
    public static void m3337e(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C5865b.f7644c) {
            Log.e(str, str2);
        }
        C5941y.m3829a(ExifInterface.LONGITUDE_EAST, str, str2);
    }

    /* renamed from: e */
    public static void m3338e(String str, String str2, Throwable th) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "null";
        }
        if (C5865b.f7644c) {
            Log.e(str, str2, th);
        }
        C5941y.m3830a(ExifInterface.LONGITUDE_EAST, str, th);
    }

    public static void setCache(int i) {
        C5941y.m3827a(i);
    }
}
