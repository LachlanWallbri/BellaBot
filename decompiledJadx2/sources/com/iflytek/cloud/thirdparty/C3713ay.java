package com.iflytek.cloud.thirdparty;

import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* renamed from: com.iflytek.cloud.thirdparty.ay */
/* loaded from: classes3.dex */
public class C3713ay {

    /* renamed from: a */
    protected Context f3118a;

    /* renamed from: b */
    private AbstractC3721bf f3119b;

    /* renamed from: c */
    private String[] f3120c = null;

    /* renamed from: d */
    private String[] f3121d = null;

    /* renamed from: e */
    private HashMap<String, String> f3122e = new HashMap<>();

    /* renamed from: f */
    private HashMap<String, String> f3123f = new HashMap<>();

    /* renamed from: g */
    private List<C3718bc> f3124g = new ArrayList();

    /* renamed from: h */
    private HashMap<String, String> f3125h = new HashMap<>();

    public C3713ay(Context context, AbstractC3721bf abstractC3721bf) {
        this.f3119b = abstractC3721bf;
        this.f3118a = context;
    }

    /* renamed from: a */
    public String[] m1995a() {
        m1994b();
        ArrayList arrayList = new ArrayList();
        HashMap<String, String> m2040d = this.f3119b.m2040d();
        List<C3718bc> m2041e = this.f3119b.m2041e();
        for (String str : m2040d.keySet()) {
            String str2 = m2040d.get(str);
            this.f3122e.put(str + "p", str2);
            arrayList.add(str2);
            if (str2.contains("\u0000")) {
                this.f3125h.put(str2.replace("\u0000", " "), str2);
            }
        }
        for (C3718bc c3718bc : m2041e) {
            String m2031a = c3718bc.m2031a();
            String m2033c = c3718bc.m2033c();
            String m2032b = c3718bc.m2032b();
            this.f3123f.put(m2031a + "s", m2033c);
            this.f3122e.put(m2031a + "s", m2032b);
            arrayList.add(m2032b);
            if (m2032b.contains("\u0000")) {
                this.f3125h.put(m2032b.replace("\u0000", " "), m2032b);
            }
            this.f3124g.add(c3718bc);
        }
        HashSet hashSet = new HashSet(arrayList);
        this.f3120c = (String[]) hashSet.toArray(new String[hashSet.size()]);
        return this.f3120c;
    }

    /* renamed from: b */
    private void m1994b() {
        if (this.f3123f.size() > 0) {
            this.f3123f = null;
            this.f3123f = new HashMap<>();
        }
        if (this.f3125h.size() > 0) {
            this.f3125h = null;
            this.f3125h = new HashMap<>();
        }
        if (this.f3122e.size() > 0) {
            this.f3122e = null;
            this.f3122e = new HashMap<>();
        }
        String[] strArr = this.f3120c;
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        this.f3120c = null;
    }
}
