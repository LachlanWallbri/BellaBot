package com.loc;

import android.text.TextUtils;
import com.autonavi.aps.amapapi.model.AMapLocationServer;

/* compiled from: LocFilter.java */
/* renamed from: com.loc.bx */
/* loaded from: classes4.dex */
public final class C3849bx {

    /* renamed from: c */
    private static C3849bx f3835c;

    /* renamed from: d */
    private AMapLocationServer f3838d = null;

    /* renamed from: e */
    private long f3839e = 0;

    /* renamed from: f */
    private long f3840f = 0;

    /* renamed from: g */
    private boolean f3841g = true;

    /* renamed from: a */
    int f3836a = 0;

    /* renamed from: b */
    long f3837b = 0;

    private C3849bx() {
    }

    /* renamed from: a */
    public static synchronized C3849bx m2726a() {
        C3849bx c3849bx;
        synchronized (C3849bx.class) {
            if (f3835c == null) {
                f3835c = new C3849bx();
            }
            c3849bx = f3835c;
        }
        return c3849bx;
    }

    /* renamed from: b */
    public static AMapLocationServer m2727b(AMapLocationServer aMapLocationServer) {
        return aMapLocationServer;
    }

    /* renamed from: c */
    private AMapLocationServer m2728c(AMapLocationServer aMapLocationServer) {
        int i;
        if (C3876cx.m2977a(aMapLocationServer)) {
            if (!this.f3841g || !C3869cq.m2877b(aMapLocationServer.getTime())) {
                i = this.f3836a;
            } else if (aMapLocationServer.getLocationType() == 5 || aMapLocationServer.getLocationType() == 6) {
                i = 2;
            }
            aMapLocationServer.setLocationType(i);
        }
        return aMapLocationServer;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00cb, code lost:
    
        if ((r9 - r2) > 30000) goto L42;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0119, code lost:
    
        if (r11 < 30000) goto L43;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final AMapLocationServer m2729a(AMapLocationServer aMapLocationServer) {
        AMapLocationServer aMapLocationServer2 = aMapLocationServer;
        if (C3876cx.m2985b() - this.f3837b > 30000) {
            this.f3838d = aMapLocationServer2;
            this.f3837b = C3876cx.m2985b();
        } else {
            this.f3837b = C3876cx.m2985b();
            if (C3876cx.m2977a(this.f3838d) && C3876cx.m2977a(aMapLocationServer)) {
                if (aMapLocationServer.getTime() == this.f3838d.getTime() && aMapLocationServer.getAccuracy() < 300.0f) {
                    return aMapLocationServer2;
                }
                if (!aMapLocationServer.getProvider().equals("gps") && aMapLocationServer.m546b() == this.f3838d.m546b() && (aMapLocationServer.getBuildingId().equals(this.f3838d.getBuildingId()) || TextUtils.isEmpty(aMapLocationServer.getBuildingId()))) {
                    this.f3836a = aMapLocationServer.getLocationType();
                    float m2962a = C3876cx.m2962a(aMapLocationServer2, this.f3838d);
                    float accuracy = this.f3838d.getAccuracy();
                    float accuracy2 = aMapLocationServer.getAccuracy();
                    float f = accuracy2 - accuracy;
                    long m2985b = C3876cx.m2985b();
                    long j = m2985b - this.f3839e;
                    if ((accuracy >= 101.0f || accuracy2 <= 299.0f) && (accuracy <= 299.0f || accuracy2 <= 299.0f)) {
                        if (accuracy2 >= 100.0f || accuracy <= 299.0f) {
                            if (accuracy2 <= 299.0f) {
                                this.f3840f = 0L;
                            }
                            if (m2962a >= 10.0f || m2962a <= 0.1d || accuracy2 <= 5.0f) {
                                if (f >= 300.0f) {
                                }
                            } else if (f < -300.0f && accuracy / accuracy2 >= 2.0f) {
                                this.f3839e = m2985b;
                                this.f3838d = aMapLocationServer2;
                            }
                            aMapLocationServer2 = m2728c(this.f3838d);
                            this.f3838d = aMapLocationServer2;
                        }
                        this.f3839e = m2985b;
                        this.f3838d = aMapLocationServer2;
                        this.f3840f = 0L;
                    } else {
                        long j2 = this.f3840f;
                        if (j2 == 0) {
                            this.f3840f = m2985b;
                        }
                        aMapLocationServer2 = m2728c(this.f3838d);
                        this.f3838d = aMapLocationServer2;
                    }
                }
            }
            this.f3839e = C3876cx.m2985b();
            this.f3838d = aMapLocationServer2;
        }
        return this.f3838d;
    }

    /* renamed from: a */
    public final void m2730a(boolean z) {
        this.f3841g = z;
    }

    /* renamed from: b */
    public final synchronized void m2731b() {
        this.f3838d = null;
        this.f3839e = 0L;
        this.f3840f = 0L;
    }
}
