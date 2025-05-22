package com.iflytek.aiui.pro;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.ah */
/* loaded from: classes.dex */
public abstract class AbstractC3578ah {

    /* renamed from: a */
    protected b f2298a;

    /* renamed from: b */
    protected Object f2299b = new Object();

    /* renamed from: c */
    protected Context f2300c;

    /* renamed from: d */
    protected a f2301d;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* renamed from: com.iflytek.aiui.pro.ah$a */
    /* loaded from: classes.dex */
    public interface a {
        /* renamed from: a */
        void mo985a();

        /* renamed from: a */
        void mo986a(int i, int i2, int i3);

        /* renamed from: a */
        void mo987a(int i, String str);

        /* renamed from: a */
        void mo988a(boolean z);

        /* renamed from: b */
        void mo989b();

        /* renamed from: c */
        void mo990c();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    /* renamed from: com.iflytek.aiui.pro.ah$b */
    /* loaded from: classes.dex */
    public enum b {
        STATE_INITED,
        STATE_STARTED,
        STATE_STOPPED,
        STATE_PLAYING,
        STATE_PAUSED,
        STATE_RELEASED
    }

    public AbstractC3578ah(a aVar) {
        this.f2301d = aVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m984a(b bVar) {
        synchronized (this.f2299b) {
            this.f2298a = bVar;
        }
    }
}
