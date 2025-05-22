package com.tencent.bugly.proguard;

import android.os.Handler;
import android.os.SystemClock;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.aa */
/* loaded from: classes7.dex */
public final class RunnableC5897aa implements Runnable {

    /* renamed from: a */
    private final Handler f8015a;

    /* renamed from: b */
    private final String f8016b;

    /* renamed from: c */
    private long f8017c;

    /* renamed from: d */
    private final long f8018d;

    /* renamed from: e */
    private boolean f8019e = true;

    /* renamed from: f */
    private long f8020f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC5897aa(Handler handler, String str, long j) {
        this.f8015a = handler;
        this.f8016b = str;
        this.f8017c = j;
        this.f8018d = j;
    }

    /* renamed from: a */
    public final void m3627a() {
        if (!this.f8019e) {
            C5940x.m3824d("scheduleCheckBlock fail as %s thread is blocked.", this.f8016b);
            return;
        }
        this.f8019e = false;
        this.f8020f = SystemClock.uptimeMillis();
        this.f8015a.postAtFrontOfQueue(this);
    }

    /* renamed from: b */
    public final boolean m3629b() {
        C5940x.m3823c("%s thread waitTime:%d", this.f8016b, Long.valueOf(this.f8017c));
        return !this.f8019e && SystemClock.uptimeMillis() > this.f8020f + this.f8017c;
    }

    /* renamed from: c */
    public final int m3630c() {
        if (this.f8019e) {
            return 0;
        }
        return SystemClock.uptimeMillis() - this.f8020f < this.f8017c ? 1 : 3;
    }

    /* renamed from: d */
    public final Thread m3631d() {
        return this.f8015a.getLooper().getThread();
    }

    /* renamed from: e */
    public final String m3632e() {
        return this.f8016b;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f8019e = true;
        this.f8017c = this.f8018d;
    }

    /* renamed from: a */
    public final void m3628a(long j) {
        this.f8017c = Long.MAX_VALUE;
    }

    /* renamed from: f */
    public final void m3633f() {
        this.f8017c = this.f8018d;
    }
}
