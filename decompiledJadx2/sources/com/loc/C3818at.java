package com.loc;

/* compiled from: DexDownloadItem.java */
/* renamed from: com.loc.at */
/* loaded from: classes4.dex */
public final class C3818at {

    /* renamed from: a */
    String f3586a;

    /* renamed from: b */
    String f3587b;

    /* renamed from: c */
    String f3588c;

    /* renamed from: d */
    String f3589d;

    /* renamed from: e */
    String f3590e;

    /* renamed from: f */
    int f3591f;

    /* renamed from: g */
    int f3592g;

    /* renamed from: h */
    private String f3593h;

    /* renamed from: i */
    private String f3594i;

    public C3818at(String str, String str2) {
        this.f3593h = str;
        this.f3594i = str2;
        try {
            String[] split = str.split("/");
            int length = split.length;
            if (length <= 1) {
                return;
            }
            this.f3586a = split[length - 1];
            String[] split2 = this.f3586a.split("_");
            this.f3587b = split2[0];
            this.f3588c = split2[2];
            this.f3589d = split2[1];
            this.f3591f = Integer.parseInt(split2[3]);
            this.f3592g = Integer.parseInt(split2[4].split("\\.")[0]);
        } catch (Throwable th) {
            C3897w.m3249a(th, "DexDownloadItem", "DexDownloadItem");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final String m2474a() {
        return this.f3593h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final String m2475b() {
        return this.f3594i;
    }
}
