package com.loc;

import java.net.Proxy;
import java.util.Map;

/* compiled from: Request.java */
/* renamed from: com.loc.bn */
/* loaded from: classes4.dex */
public abstract class AbstractC3839bn {

    /* renamed from: c */
    int f3696c = 20000;

    /* renamed from: d */
    int f3697d = 20000;

    /* renamed from: e */
    Proxy f3698e = null;

    /* renamed from: a */
    public abstract Map<String, String> mo2487a();

    /* renamed from: a */
    public final void m2626a(int i) {
        this.f3696c = i;
    }

    /* renamed from: a */
    public final void m2627a(Proxy proxy) {
        this.f3698e = proxy;
    }

    /* renamed from: b */
    public abstract String mo2488b();

    /* renamed from: b */
    public final void m2628b(int i) {
        this.f3697d = i;
    }

    /* renamed from: c */
    public abstract Map<String, String> mo2489c();

    /* renamed from: d */
    public byte[] mo2611d() {
        return null;
    }
}
