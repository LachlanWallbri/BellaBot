package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.C3690ab;

/* renamed from: com.iflytek.cloud.thirdparty.am */
/* loaded from: classes3.dex */
public class C3701am extends AbstractC3744w implements C3690ab.a {

    /* renamed from: e */
    private Context f3027e;

    /* renamed from: f */
    private C3690ab f3028f;

    /* renamed from: g */
    private C3690ab f3029g;

    public C3701am(Context context) {
        super(context);
        this.f3027e = null;
        this.f3028f = null;
        this.f3029g = null;
        this.f3027e = context.getApplicationContext();
    }

    /* renamed from: a */
    public int m1916a(String str, SynthesizerListener synthesizerListener) {
        int i;
        DebugLog.LogD("startSpeaking enter");
        synchronized (this) {
            String m1831d = this.mSessionParams.m1831d(SpeechConstant.NEXT_TEXT);
            i = 0;
            if (this.f3028f != null && this.f3028f.m1801h()) {
                this.f3028f.cancel(this.mSessionParams.m1825a(SpeechConstant.TTS_INTERRUPT_ERROR, false));
            }
            if (this.f3029g == null) {
                i = m1915a(str, synthesizerListener, m1831d);
            } else if (!str.equals(this.f3029g.f2953g)) {
                this.f3029g.cancel(false);
                this.f3029g = null;
                i = m1915a(str, synthesizerListener, m1831d);
            } else {
                if (this.f3029g.f2954h == null && this.f3029g.f2951e) {
                    C3690ab c3690ab = this.f3029g;
                    this.f3029g = null;
                    if (!TextUtils.isEmpty(m1831d)) {
                        this.f3029g = new C3690ab(this.f3027e);
                        this.f3029g.m1795a(this);
                        this.f3029g.m1796a(m1831d, this.mSessionParams);
                    }
                    this.f3028f = c3690ab;
                    this.f3028f.m1794a(synthesizerListener);
                    this.f3028f.m1802i();
                    if (this.f3028f.f2952f) {
                        mo1803a();
                        DebugLog.LogD("startSpeaking NextSession pause");
                    }
                }
                this.f3029g.cancel(false);
                this.f3029g = null;
                i = m1915a(str, synthesizerListener, m1831d);
            }
        }
        DebugLog.LogD("startSpeaking leave");
        return i;
    }

    /* renamed from: a */
    private int m1915a(String str, SynthesizerListener synthesizerListener, String str2) {
        DebugLog.LogD("new Session Start");
        this.f3028f = new C3690ab(this.f3027e);
        this.f3028f.m1795a(this);
        int m1792a = this.f3028f.m1792a(str, this.mSessionParams, synthesizerListener, true, this.mSessionParams.m1833e(SpeechConstant.TTS_AUDIO_PATH));
        if (!TextUtils.isEmpty(str2)) {
            this.f3029g = new C3690ab(this.f3027e);
            this.f3029g.m1795a(this);
            this.f3029g.m1796a(str2, this.mSessionParams);
        }
        return m1792a;
    }

    @Override // com.iflytek.cloud.thirdparty.C3690ab.a
    /* renamed from: a */
    public void mo1803a() {
        synchronized (this) {
            if (this.f3029g != null) {
                this.f3029g.m1798e();
            }
        }
    }

    /* renamed from: a */
    public int m1917a(String str, String str2, SynthesizerListener synthesizerListener) {
        int m1793a;
        DebugLog.LogD("synthesizeToUri enter");
        synchronized (this) {
            if (this.f3028f != null && this.f3028f.m1801h()) {
                this.f3028f.cancel(this.mSessionParams.m1825a(SpeechConstant.TTS_INTERRUPT_ERROR, false));
            }
            this.f3028f = new C3690ab(this.f3027e);
            m1793a = this.f3028f.m1793a(str, str2, this.mSessionParams, synthesizerListener);
        }
        DebugLog.LogD("synthesizeToUri leave");
        return m1793a;
    }

    /* renamed from: e */
    public void m1919e() {
        DebugLog.LogD("pauseSpeaking enter");
        synchronized (this) {
            if (this.f3028f != null) {
                this.f3028f.m1800g();
            }
        }
        DebugLog.LogD("pauseSpeaking leave");
    }

    /* renamed from: f */
    public void m1920f() {
        DebugLog.LogD("resumeSpeaking enter");
        synchronized (this) {
            if (this.f3028f != null) {
                this.f3028f.m1802i();
            }
        }
        DebugLog.LogD("resumeSpeaking leave");
    }

    /* renamed from: g */
    public boolean m1921g() {
        boolean m1801h;
        DebugLog.LogD("isSpeaking enter");
        synchronized (this) {
            m1801h = this.f3028f != null ? this.f3028f.m1801h() : false;
        }
        DebugLog.LogD("isSpeaking leave");
        return m1801h;
    }

    /* renamed from: h */
    public int m1922h() {
        int m1799f;
        DebugLog.LogD("getState enter");
        synchronized (this) {
            m1799f = this.f3028f != null ? this.f3028f.m1799f() : 4;
        }
        DebugLog.LogD("getState leave");
        return m1799f;
    }

    /* renamed from: a */
    public void m1918a(boolean z) {
        DebugLog.LogD("stopSpeaking enter:" + z);
        synchronized (this) {
            if (this.f3028f != null) {
                DebugLog.LogD("-->stopSpeaking cur");
                this.f3028f.cancel(z);
                this.f3028f = null;
            }
            if (this.f3029g != null) {
                DebugLog.LogD("-->stopSpeaking cur next");
                this.f3029g.cancel(false);
                this.f3029g = null;
            }
        }
        DebugLog.LogD("stopSpeaking leave");
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3744w, com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean destroy() {
        m1918a(false);
        super.destroy();
        return true;
    }
}
