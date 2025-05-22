package com.aliyun.alink.linksdk.alcs.lpbs.p037b;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: TextHelper.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.b.c */
/* loaded from: classes.dex */
public class C0942c {
    /* renamed from: a */
    public static String m366a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr != null) {
            for (byte b : bArr) {
                sb.append(String.format("%02X", Integer.valueOf(b & 255)));
            }
        }
        return sb.toString();
    }
}
