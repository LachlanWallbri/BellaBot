package com.loc;

import androidx.core.os.EnvironmentCompat;
import java.util.Locale;

/* compiled from: Cgi.java */
/* renamed from: com.loc.cb */
/* loaded from: classes4.dex */
public final class C3854cb {

    /* renamed from: p */
    private static C3854cb f3865p;

    /* renamed from: k */
    public int f3876k;

    /* renamed from: n */
    public boolean f3879n;

    /* renamed from: a */
    public int f3866a = 0;

    /* renamed from: b */
    public int f3867b = 0;

    /* renamed from: c */
    public int f3868c = 0;

    /* renamed from: d */
    public int f3869d = 0;

    /* renamed from: e */
    public int f3870e = 0;

    /* renamed from: f */
    public int f3871f = 0;

    /* renamed from: g */
    public int f3872g = 0;

    /* renamed from: h */
    public int f3873h = 0;

    /* renamed from: i */
    public int f3874i = 0;

    /* renamed from: j */
    public int f3875j = -113;

    /* renamed from: l */
    public short f3877l = 0;

    /* renamed from: m */
    public long f3878m = 0;

    /* renamed from: o */
    public boolean f3880o = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public C3854cb(int i, boolean z) {
        this.f3876k = 0;
        this.f3879n = false;
        this.f3876k = i;
        this.f3879n = z;
    }

    /* renamed from: a */
    public final int m2750a() {
        return this.f3868c;
    }

    /* renamed from: a */
    public final boolean m2751a(C3854cb c3854cb) {
        if (c3854cb == null) {
            return false;
        }
        int i = c3854cb.f3876k;
        return i != 1 ? i != 2 ? i != 3 ? i == 4 && this.f3876k == 4 && c3854cb.f3868c == this.f3868c && c3854cb.f3869d == this.f3869d && c3854cb.f3867b == this.f3867b : this.f3876k == 3 && c3854cb.f3868c == this.f3868c && c3854cb.f3869d == this.f3869d && c3854cb.f3867b == this.f3867b : this.f3876k == 2 && c3854cb.f3874i == this.f3874i && c3854cb.f3873h == this.f3873h && c3854cb.f3872g == this.f3872g : this.f3876k == 1 && c3854cb.f3868c == this.f3868c && c3854cb.f3869d == this.f3869d && c3854cb.f3867b == this.f3867b;
    }

    /* renamed from: b */
    public final int m2752b() {
        return this.f3869d;
    }

    /* renamed from: c */
    public final int m2753c() {
        return this.f3873h;
    }

    /* renamed from: d */
    public final int m2754d() {
        return this.f3874i;
    }

    /* renamed from: e */
    public final int m2755e() {
        return this.f3875j;
    }

    public final String toString() {
        int i = this.f3876k;
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? EnvironmentCompat.MEDIA_UNKNOWN : String.format(Locale.CHINA, "WCDMA lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b", Integer.valueOf(this.f3868c), Integer.valueOf(this.f3869d), Integer.valueOf(this.f3867b), Boolean.valueOf(this.f3880o), Integer.valueOf(this.f3875j), Short.valueOf(this.f3877l), Boolean.valueOf(this.f3879n)) : String.format(Locale.CHINA, "LTE lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b", Integer.valueOf(this.f3868c), Integer.valueOf(this.f3869d), Integer.valueOf(this.f3867b), Boolean.valueOf(this.f3880o), Integer.valueOf(this.f3875j), Short.valueOf(this.f3877l), Boolean.valueOf(this.f3879n)) : String.format(Locale.CHINA, "CDMA bid=%d, nid=%d, sid=%d, valid=%b, sig=%d, age=%d, reg=%b", Integer.valueOf(this.f3874i), Integer.valueOf(this.f3873h), Integer.valueOf(this.f3872g), Boolean.valueOf(this.f3880o), Integer.valueOf(this.f3875j), Short.valueOf(this.f3877l), Boolean.valueOf(this.f3879n)) : String.format(Locale.CHINA, "GSM lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b", Integer.valueOf(this.f3868c), Integer.valueOf(this.f3869d), Integer.valueOf(this.f3867b), Boolean.valueOf(this.f3880o), Integer.valueOf(this.f3875j), Short.valueOf(this.f3877l), Boolean.valueOf(this.f3879n));
    }
}
