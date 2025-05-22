package com.tencent.bugly.proguard;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.ak */
/* loaded from: classes7.dex */
public final class C5907ak extends AbstractC5927k implements Cloneable {

    /* renamed from: a */
    public String f8027a = "";

    /* renamed from: b */
    public String f8028b = "";

    /* renamed from: c */
    public String f8029c = "";

    /* renamed from: e */
    private String f8031e = "";

    /* renamed from: d */
    public String f8030d = "";

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3651a(StringBuilder sb, int i) {
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3650a(C5926j c5926j) {
        c5926j.m3706a(this.f8027a, 0);
        String str = this.f8028b;
        if (str != null) {
            c5926j.m3706a(str, 1);
        }
        String str2 = this.f8029c;
        if (str2 != null) {
            c5926j.m3706a(str2, 2);
        }
        String str3 = this.f8031e;
        if (str3 != null) {
            c5926j.m3706a(str3, 3);
        }
        String str4 = this.f8030d;
        if (str4 != null) {
            c5926j.m3706a(str4, 4);
        }
    }

    @Override // com.tencent.bugly.proguard.AbstractC5927k
    /* renamed from: a */
    public final void mo3649a(C5925i c5925i) {
        this.f8027a = c5925i.m3695b(0, true);
        this.f8028b = c5925i.m3695b(1, false);
        this.f8029c = c5925i.m3695b(2, false);
        this.f8031e = c5925i.m3695b(3, false);
        this.f8030d = c5925i.m3695b(4, false);
    }
}
