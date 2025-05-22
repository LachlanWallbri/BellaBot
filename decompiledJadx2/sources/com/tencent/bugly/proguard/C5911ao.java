package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.ao */
/* loaded from: classes7.dex */
public final class C5911ao extends AbstractC5927k implements Cloneable {

    /* renamed from: b */
    private static ArrayList<C5910an> f8068b;

    /* renamed from: a */
    public ArrayList<C5910an> f8069a = null;

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3651a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3650a(C5926j c5926j) {
        c5926j.m3707a((Collection) this.f8069a, 0);
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3649a(C5925i c5925i) {
        if (f8068b == null) {
            f8068b = new ArrayList<>();
            f8068b.add(new C5910an());
        }
        this.f8069a = (ArrayList) c5925i.m3690a((C5925i) f8068b, 0, true);
    }
}
