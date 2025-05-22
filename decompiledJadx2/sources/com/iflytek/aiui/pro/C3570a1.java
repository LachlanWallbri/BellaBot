package com.iflytek.aiui.pro;

import android.os.Bundle;

/* renamed from: com.iflytek.aiui.pro.a1 */
/* loaded from: classes4.dex */
public class C3570a1 {

    /* renamed from: a */
    byte[] f2242a;

    /* renamed from: b */
    int f2243b;

    /* renamed from: c */
    int f2244c;

    /* renamed from: d */
    int f2245d;

    /* renamed from: e */
    long f2246e;

    /* renamed from: f */
    int f2247f;

    /* renamed from: g */
    boolean f2248g;

    /* renamed from: h */
    Bundle f2249h;

    /* renamed from: com.iflytek.aiui.pro.a1$a */
    /* loaded from: classes4.dex */
    public enum a {
        RGB_888(1),
        NV21(2);

        a(int i) {
        }
    }

    public C3570a1(int i) {
        if (i > 0) {
            this.f2242a = new byte[i];
        }
    }

    /* renamed from: s */
    public static void m898s(C3570a1 c3570a1, C3570a1 c3570a12) {
        if (c3570a1 == null || c3570a12 == null) {
            return;
        }
        long m904f = c3570a1.m904f();
        boolean m907i = c3570a1.m907i();
        int m905g = c3570a1.m905g();
        byte[] m900b = c3570a1.m900b();
        int m906h = c3570a1.m906h();
        int m903e = c3570a1.m903e();
        int i = c3570a1.f2243b;
        Bundle m901c = c3570a1.m901c();
        c3570a1.m913o(c3570a12.m904f());
        c3570a1.m914p(c3570a12.m907i());
        c3570a1.m915q(c3570a12.m905g());
        c3570a1.m908j(c3570a12.m900b());
        c3570a1.m909k(c3570a12.m901c());
        c3570a1.m916r(c3570a12.m906h());
        c3570a1.m912n(c3570a12.m903e());
        c3570a1.m910l(c3570a12.m902d());
        c3570a12.m913o(m904f);
        c3570a12.m914p(m907i);
        c3570a12.m915q(m905g);
        c3570a12.m908j(m900b);
        c3570a12.m909k(m901c);
        c3570a12.f2244c = m906h;
        c3570a12.f2245d = m903e;
        c3570a12.f2243b = i;
    }

    /* renamed from: a */
    public void m899a(byte[] bArr, int i, boolean z) {
        int min = Math.min(bArr.length, this.f2242a.length);
        this.f2243b = min;
        System.arraycopy(bArr, 0, this.f2242a, 0, min);
        this.f2246e = System.currentTimeMillis();
        this.f2247f = i;
        this.f2248g = z;
    }

    /* renamed from: b */
    public byte[] m900b() {
        return this.f2242a;
    }

    /* renamed from: c */
    public Bundle m901c() {
        return this.f2249h;
    }

    /* renamed from: d */
    public int m902d() {
        return this.f2243b;
    }

    /* renamed from: e */
    public int m903e() {
        return this.f2245d;
    }

    /* renamed from: f */
    public long m904f() {
        return this.f2246e;
    }

    /* renamed from: g */
    public int m905g() {
        return this.f2247f;
    }

    /* renamed from: h */
    public int m906h() {
        return this.f2244c;
    }

    /* renamed from: i */
    public boolean m907i() {
        return this.f2248g;
    }

    /* renamed from: j */
    public void m908j(byte[] bArr) {
        this.f2242a = bArr;
    }

    /* renamed from: k */
    public void m909k(Bundle bundle) {
        this.f2249h = bundle;
    }

    /* renamed from: l */
    public void m910l(int i) {
        this.f2243b = i;
    }

    /* renamed from: m */
    public void m911m(a aVar) {
    }

    /* renamed from: n */
    public void m912n(int i) {
        this.f2245d = i;
    }

    /* renamed from: o */
    public void m913o(long j) {
        this.f2246e = j;
    }

    /* renamed from: p */
    public void m914p(boolean z) {
        this.f2248g = z;
    }

    /* renamed from: q */
    public void m915q(int i) {
        this.f2247f = i;
    }

    /* renamed from: r */
    public void m916r(int i) {
        this.f2244c = i;
    }
}
