package com.loc;

import java.util.HashMap;
import java.util.Map;

/* compiled from: LogInfo.java */
/* renamed from: com.loc.ao */
/* loaded from: classes4.dex */
public abstract class AbstractC3813ao {

    /* renamed from: b */
    @InterfaceC3806ah(m2437a = "b1", m2438b = 6)
    protected String f3570b;

    /* renamed from: d */
    @InterfaceC3806ah(m2437a = "a1", m2438b = 6)
    private String f3572d;

    /* renamed from: a */
    @InterfaceC3806ah(m2437a = "b2", m2438b = 2)
    protected int f3569a = -1;

    /* renamed from: c */
    @InterfaceC3806ah(m2437a = "b3", m2438b = 2)
    protected int f3571c = 1;

    /* renamed from: c */
    public static String m2445c(int i) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("b2=");
            sb.append(i);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return sb.toString();
    }

    /* renamed from: c */
    public static String m2446c(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("b1", str);
        return C3804af.m2424a((Map<String, String>) hashMap);
    }

    /* renamed from: a */
    public final int m2447a() {
        return this.f3569a;
    }

    /* renamed from: a */
    public final void m2448a(int i) {
        this.f3569a = i;
    }

    /* renamed from: a */
    public final void m2449a(String str) {
        this.f3570b = str;
    }

    /* renamed from: b */
    public final String m2450b() {
        return this.f3570b;
    }

    /* renamed from: b */
    public final void m2451b(int i) {
        this.f3571c = i;
    }

    /* renamed from: b */
    public final void m2452b(String str) {
        this.f3572d = C3894t.m3233b(str);
    }

    /* renamed from: c */
    public final int m2453c() {
        return this.f3571c;
    }
}
