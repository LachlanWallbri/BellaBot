package com.aliyun.alink.linksdk.alcs.lpbs.p037b;

import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: TopicUtils.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.b.d */
/* loaded from: classes.dex */
public class C0943d {

    /* renamed from: a */
    public static final String f793a = "/dev";

    /* renamed from: b */
    private static final String f794b = "/";

    /* renamed from: c */
    private static final String f795c = ".";

    /* renamed from: a */
    public static String m368a(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && str.startsWith(f793a)) {
            return str;
        }
        return "/sys/" + str2 + "/" + str3 + "/thing/model/down_raw";
    }

    /* renamed from: a */
    public static String m367a(String str, String str2) {
        return "/sys/" + str + "/" + str2 + "/thing/model/up_raw";
    }

    /* renamed from: b */
    public static String m371b(String str, String str2, String str3) {
        return str + str2 + str3;
    }

    /* renamed from: b */
    public static String m370b(String str, String str2) {
        return str + str2;
    }

    /* renamed from: a */
    public static String m369a(String str, String str2, String str3, String str4) {
        return str3 + "/" + str + "/" + str2 + "/" + str4.replace(".", "/");
    }
}
