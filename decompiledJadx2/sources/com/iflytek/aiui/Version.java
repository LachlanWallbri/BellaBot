package com.iflytek.aiui;

import com.iflytek.aiui.jni.AIUI;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class Version {

    /* renamed from: a */
    private static VersionType f2141a = VersionType.MOBILE_PHONE;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
      classes4.dex
     */
    /* loaded from: classes.dex */
    public enum VersionType {
        INTELLIGENT_HDW,
        MOBILE_PHONE
    }

    public static String getVersion() {
        if (!AIUI.m868a()) {
            AIUI.m869b();
        }
        return AIUI.getVersion();
    }

    public static VersionType getVersionType() {
        return f2141a;
    }

    public static boolean isMobileVersion() {
        return f2141a == VersionType.MOBILE_PHONE;
    }
}
