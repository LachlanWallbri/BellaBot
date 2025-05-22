package com.iflytek.aiui.pro;

/* renamed from: com.iflytek.aiui.pro.i0 */
/* loaded from: classes4.dex */
public abstract class AbstractC3619i0 {

    /* renamed from: a */
    protected Object f2552a = new Object();

    /* renamed from: b */
    protected a f2553b;

    /* renamed from: com.iflytek.aiui.pro.i0$a */
    /* loaded from: classes4.dex */
    public interface a {
        /* renamed from: a */
        void mo1373a(int i, String str);

        /* renamed from: b */
        void mo1374b(boolean z);

        /* renamed from: c */
        void mo1375c(int i, int i2, int i3);

        /* renamed from: d */
        void mo1376d();

        /* renamed from: e */
        void mo1377e();

        /* renamed from: f */
        void mo1378f();
    }

    /* renamed from: com.iflytek.aiui.pro.i0$b */
    /* loaded from: classes4.dex */
    public enum b {
        STATE_INITED,
        STATE_STARTED,
        STATE_STOPPED,
        STATE_PLAYING,
        STATE_PAUSED,
        STATE_RELEASED
    }

    public AbstractC3619i0(a aVar) {
        this.f2553b = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m1372a(b bVar) {
        synchronized (this.f2552a) {
        }
    }
}
