package com.tencent.bugly.proguard;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.ar */
/* loaded from: classes7.dex */
public final class C5914ar extends AbstractC5927k implements Cloneable {

    /* renamed from: a */
    public String f8106a = "";

    /* renamed from: b */
    private String f8107b = "";

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3651a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3650a(C5926j c5926j) {
        c5926j.m3706a(this.f8106a, 0);
        c5926j.m3706a(this.f8107b, 1);
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3649a(C5925i c5925i) {
        this.f8106a = c5925i.m3695b(0, true);
        this.f8107b = c5925i.m3695b(1, true);
    }
}
