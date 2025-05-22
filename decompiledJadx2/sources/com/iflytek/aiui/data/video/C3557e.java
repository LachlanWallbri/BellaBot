package com.iflytek.aiui.data.video;

import java.io.Serializable;

/* renamed from: com.iflytek.aiui.data.video.e */
/* loaded from: classes4.dex */
class C3557e implements Comparable<C3557e>, Serializable {

    /* renamed from: a */
    private final int f2192a;

    /* renamed from: b */
    private final int f2193b;

    public C3557e(int i, int i2) {
        this.f2192a = i;
        this.f2193b = i2;
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C3557e c3557e) {
        return (this.f2192a * this.f2193b) - (c3557e.f2192a * c3557e.f2193b);
    }

    /* renamed from: b */
    public int m830b() {
        return this.f2193b;
    }

    /* renamed from: c */
    public int m831c() {
        return this.f2192a;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3557e)) {
            return false;
        }
        C3557e c3557e = (C3557e) obj;
        return this.f2192a == c3557e.f2192a && this.f2193b == c3557e.f2193b;
    }

    public int hashCode() {
        int i = this.f2193b;
        int i2 = this.f2192a;
        return i ^ ((i2 << 16) | (i2 >>> 16));
    }

    public String toString() {
        return this.f2192a + "x" + this.f2193b;
    }
}
