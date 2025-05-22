package com.tencent.bugly.proguard;

import java.util.ArrayList;
import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.al */
/* loaded from: classes7.dex */
public final class C5908al extends AbstractC5927k implements Cloneable {

    /* renamed from: c */
    private static ArrayList<String> f8032c;

    /* renamed from: a */
    private String f8033a = "";

    /* renamed from: b */
    private ArrayList<String> f8034b = null;

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3651a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3650a(C5926j c5926j) {
        c5926j.m3706a(this.f8033a, 0);
        ArrayList<String> arrayList = this.f8034b;
        if (arrayList != null) {
            c5926j.m3707a((Collection) arrayList, 1);
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3649a(C5925i c5925i) {
        this.f8033a = c5925i.m3695b(0, true);
        if (f8032c == null) {
            f8032c = new ArrayList<>();
            f8032c.add("");
        }
        this.f8034b = (ArrayList) c5925i.m3690a((C5925i) f8032c, 1, false);
    }
}
