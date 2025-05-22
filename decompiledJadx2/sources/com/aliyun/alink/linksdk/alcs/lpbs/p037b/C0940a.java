package com.aliyun.alink.linksdk.alcs.lpbs.p037b;

import java.security.SecureRandom;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: RandomUtils.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.b.a */
/* loaded from: classes.dex */
public class C0940a {

    /* renamed from: a */
    protected static SecureRandom f791a = new SecureRandom();

    /* renamed from: b */
    private static final String f792b = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /* renamed from: a */
    public static String m364a(int i) {
        StringBuilder sb = new StringBuilder(i);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(f792b.charAt(f791a.nextInt(62)));
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static int m363a() {
        return f791a.nextInt();
    }
}
