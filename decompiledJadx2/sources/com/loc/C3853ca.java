package com.loc;

import java.util.ArrayList;
import java.util.HashMap;

/* compiled from: CellAgeEstimator.java */
/* renamed from: com.loc.ca */
/* loaded from: classes4.dex */
public final class C3853ca {

    /* renamed from: a */
    private HashMap<Long, C3854cb> f3863a = new HashMap<>();

    /* renamed from: b */
    private long f3864b = 0;

    /* renamed from: a */
    private static long m2746a(int i, int i2) {
        return (i2 & 65535) | ((i & 65535) << 32);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004a  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final long m2747a(C3854cb c3854cb) {
        int m2750a;
        int m2752b;
        long m2746a;
        C3854cb c3854cb2;
        if (c3854cb == null || !c3854cb.f3880o) {
            return 0L;
        }
        HashMap<Long, C3854cb> hashMap = this.f3863a;
        int i = c3854cb.f3876k;
        if (i != 1) {
            if (i == 2) {
                m2750a = c3854cb.m2753c();
                m2752b = c3854cb.m2754d();
                m2746a = m2746a(m2750a, m2752b);
                c3854cb2 = hashMap.get(Long.valueOf(m2746a));
                if (c3854cb2 == null) {
                }
            } else if (i != 3 && i != 4) {
                m2746a = 0;
                c3854cb2 = hashMap.get(Long.valueOf(m2746a));
                if (c3854cb2 == null) {
                    c3854cb.f3878m = C3876cx.m2985b();
                    hashMap.put(Long.valueOf(m2746a), c3854cb);
                    return 0L;
                }
                if (c3854cb2.m2755e() != c3854cb.m2755e()) {
                    c3854cb.f3878m = C3876cx.m2985b();
                    hashMap.put(Long.valueOf(m2746a), c3854cb);
                    return 0L;
                }
                c3854cb.f3878m = c3854cb2.f3878m;
                hashMap.put(Long.valueOf(m2746a), c3854cb);
                return (C3876cx.m2985b() - c3854cb2.f3878m) / 1000;
            }
        }
        m2750a = c3854cb.m2750a();
        m2752b = c3854cb.m2752b();
        m2746a = m2746a(m2750a, m2752b);
        c3854cb2 = hashMap.get(Long.valueOf(m2746a));
        if (c3854cb2 == null) {
        }
    }

    /* renamed from: a */
    public final void m2748a() {
        this.f3863a.clear();
        this.f3864b = 0L;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0039, code lost:
    
        if (r13 != 4) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0084, code lost:
    
        if (r12 != 4) goto L42;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0066 A[SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void m2749a(ArrayList<? extends C3854cb> arrayList) {
        int m2750a;
        int m2752b;
        int i;
        int i2;
        C3854cb c3854cb;
        if (arrayList != null) {
            long m2985b = C3876cx.m2985b();
            long j = this.f3864b;
            if (j <= 0 || m2985b - j >= 60000) {
                HashMap<Long, C3854cb> hashMap = this.f3863a;
                int size = arrayList.size();
                long j2 = 0;
                for (int i3 = 0; i3 < size; i3++) {
                    C3854cb c3854cb2 = arrayList.get(i3);
                    if (c3854cb2.f3880o) {
                        int i4 = c3854cb2.f3876k;
                        if (i4 != 1) {
                            if (i4 == 2) {
                                i = c3854cb2.f3873h;
                                i2 = c3854cb2.f3874i;
                                j2 = m2746a(i, i2);
                                c3854cb = hashMap.get(Long.valueOf(j2));
                                if (c3854cb != null) {
                                    if (c3854cb.m2755e() == c3854cb2.m2755e()) {
                                        c3854cb2.f3878m = c3854cb.f3878m;
                                    } else {
                                        c3854cb2.f3878m = m2985b;
                                    }
                                }
                            } else if (i4 != 3) {
                            }
                        }
                        i = c3854cb2.f3868c;
                        i2 = c3854cb2.f3869d;
                        j2 = m2746a(i, i2);
                        c3854cb = hashMap.get(Long.valueOf(j2));
                        if (c3854cb != null) {
                        }
                    }
                }
                hashMap.clear();
                int size2 = arrayList.size();
                for (int i5 = 0; i5 < size2; i5++) {
                    C3854cb c3854cb3 = arrayList.get(i5);
                    if (c3854cb3.f3880o) {
                        int i6 = c3854cb3.f3876k;
                        if (i6 != 1) {
                            if (i6 == 2) {
                                m2750a = c3854cb3.m2753c();
                                m2752b = c3854cb3.m2754d();
                                j2 = m2746a(m2750a, m2752b);
                                hashMap.put(Long.valueOf(j2), c3854cb3);
                            } else if (i6 != 3) {
                            }
                        }
                        m2750a = c3854cb3.m2750a();
                        m2752b = c3854cb3.m2752b();
                        j2 = m2746a(m2750a, m2752b);
                        hashMap.put(Long.valueOf(j2), c3854cb3);
                    }
                }
                this.f3864b = m2985b;
            }
        }
    }
}
