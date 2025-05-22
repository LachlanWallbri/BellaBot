package com.iflytek.aiui.jni;

import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class AIUI {

    /* renamed from: a */
    private static boolean f2219a = false;

    /* renamed from: b */
    private static String f2220b = "aiui";

    /* renamed from: c */
    private static String f2221c = "";

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    public static class DataItem {
        public static final int TYPE_BIN = 4;
        public static final int TYPE_INT = 1;
        public static final int TYPE_LONG = 2;
        public static final int TYPE_STR = 3;
        public byte[] data;
        public int type;
    }

    /* renamed from: a */
    public static void m867a(String str) {
        f2220b = str;
    }

    /* renamed from: a */
    public static boolean m868a() {
        return f2219a;
    }

    /* renamed from: b */
    public static void m869b() {
        try {
            if (TextUtils.isEmpty(f2221c)) {
                System.loadLibrary(f2220b);
            } else {
                System.load(f2221c);
            }
        } catch (Exception e) {
            e.printStackTrace();
            f2219a = false;
        }
        f2219a = true;
    }

    /* renamed from: b */
    public static void m870b(String str) {
        f2221c = str;
    }

    public static native long createAgent(Object obj, String str, Object obj2, String str2);

    public static native void destroyAgent();

    public static native String getVersion();

    public static native void sendMessage(int i, int i2, int i3, String str, byte[] bArr);

    public static native void setAIUIDir(String str);

    public static native void setDataLogDir(String str);

    public static native void setGpsPos(float f, float f2);

    public static native void setLogLevel(int i);

    public static native void setMscCfg(String str);

    public static native void setNetLogLevel(int i);

    public static native void setSaveDataLog(boolean z);

    public static native void setSystemInfo(String str, String str2, Object obj);

    public static native void setVersionType(int i, Object obj);
}
