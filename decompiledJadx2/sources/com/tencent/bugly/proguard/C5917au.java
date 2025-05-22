package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.au */
/* loaded from: classes7.dex */
public final class C5917au extends AbstractC5927k implements Cloneable {

    /* renamed from: f */
    private static ArrayList<C5916at> f8132f;

    /* renamed from: g */
    private static Map<String, String> f8133g;

    /* renamed from: a */
    public byte f8134a = 0;

    /* renamed from: b */
    public String f8135b = "";

    /* renamed from: c */
    public String f8136c = "";

    /* renamed from: d */
    public ArrayList<C5916at> f8137d = null;

    /* renamed from: e */
    public Map<String, String> f8138e = null;

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3651a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3650a(C5926j c5926j) {
        c5926j.m3701a(this.f8134a, 0);
        String str = this.f8135b;
        if (str != null) {
            c5926j.m3706a(str, 1);
        }
        String str2 = this.f8136c;
        if (str2 != null) {
            c5926j.m3706a(str2, 2);
        }
        ArrayList<C5916at> arrayList = this.f8137d;
        if (arrayList != null) {
            c5926j.m3707a((Collection) arrayList, 3);
        }
        Map<String, String> map = this.f8138e;
        if (map != null) {
            c5926j.m3708a((Map) map, 4);
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3649a(C5925i c5925i) {
        this.f8134a = c5925i.m3685a(this.f8134a, 0, true);
        this.f8135b = c5925i.m3695b(1, false);
        this.f8136c = c5925i.m3695b(2, false);
        if (f8132f == null) {
            f8132f = new ArrayList<>();
            f8132f.add(new C5916at());
        }
        this.f8137d = (ArrayList) c5925i.m3690a((C5925i) f8132f, 3, false);
        if (f8133g == null) {
            f8133g = new HashMap();
            f8133g.put("", "");
        }
        this.f8138e = (Map) c5925i.m3690a((C5925i) f8133g, 4, false);
    }
}
