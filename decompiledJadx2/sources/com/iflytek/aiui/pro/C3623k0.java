package com.iflytek.aiui.pro;

/* renamed from: com.iflytek.aiui.pro.k0 */
/* loaded from: classes4.dex */
public class C3623k0 {

    /* renamed from: a */
    private final int f2571a;

    /* renamed from: b */
    private int f2572b;

    public C3623k0() {
        this(65535);
    }

    public C3623k0(int i) {
        this.f2571a = i;
        this.f2572b = 0;
    }

    /* renamed from: a */
    public int m1391a() {
        int i = this.f2572b + 1;
        this.f2572b = i;
        if (i > this.f2571a) {
            this.f2572b = 1;
        }
        return this.f2572b;
    }
}
