package com.aliyun.alink.dm.p018l;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: TokenWrapper.java */
/* renamed from: com.aliyun.alink.dm.l.c */
/* loaded from: classes.dex */
public class C0862c {

    /* renamed from: a */
    private String f429a = null;

    /* renamed from: b */
    private long f430b = -1;

    /* renamed from: c */
    private boolean f431c = false;

    /* renamed from: a */
    public String m149a() {
        return this.f429a;
    }

    /* renamed from: a */
    public void m151a(String str) {
        this.f429a = str;
    }

    /* renamed from: b */
    public long m153b() {
        return this.f430b;
    }

    /* renamed from: a */
    public void m150a(long j) {
        this.f430b = j;
    }

    /* renamed from: c */
    public boolean m154c() {
        return this.f431c;
    }

    /* renamed from: a */
    public void m152a(boolean z) {
        this.f431c = z;
    }

    public String toString() {
        return "[ token=" + this.f429a + ", generateTime=" + this.f430b + ", isTokenValid=" + this.f431c + "]";
    }
}
