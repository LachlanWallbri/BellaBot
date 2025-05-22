package com.tencent.bugly.crashreport.crash;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.crash.a */
/* loaded from: classes7.dex */
public final class C5879a implements Comparable<C5879a> {

    /* renamed from: a */
    public long f7874a = -1;

    /* renamed from: b */
    public long f7875b = -1;

    /* renamed from: c */
    public String f7876c = null;

    /* renamed from: d */
    public boolean f7877d = false;

    /* renamed from: e */
    public boolean f7878e = false;

    /* renamed from: f */
    public int f7879f = 0;

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(C5879a c5879a) {
        C5879a c5879a2 = c5879a;
        if (c5879a2 == null) {
            return 1;
        }
        long j = this.f7875b - c5879a2.f7875b;
        if (j <= 0) {
            return j < 0 ? -1 : 0;
        }
        return 1;
    }
}
