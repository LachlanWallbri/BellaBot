package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechEvent;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.record.C3682b;
import com.iflytek.cloud.record.C3683c;
import java.io.IOException;
import java.util.ArrayList;

/* renamed from: com.iflytek.cloud.thirdparty.ab */
/* loaded from: classes3.dex */
public class C3690ab extends AbstractC3744w {

    /* renamed from: e */
    public boolean f2951e;

    /* renamed from: f */
    public boolean f2952f;

    /* renamed from: g */
    public String f2953g;

    /* renamed from: h */
    public SpeechError f2954h;

    /* renamed from: i */
    final Handler f2955i;

    /* renamed from: j */
    private C3683c f2956j;

    /* renamed from: k */
    private C3682b f2957k;

    /* renamed from: l */
    private SynthesizerListener f2958l;

    /* renamed from: m */
    private SynthesizerListener f2959m;

    /* renamed from: n */
    private a f2960n;

    /* renamed from: o */
    private int f2961o;

    /* renamed from: p */
    private boolean f2962p;

    /* renamed from: q */
    private InterfaceC3689aa f2963q;

    /* renamed from: r */
    private C3683c.a f2964r;

    /* renamed from: s */
    private Handler f2965s;

    /* renamed from: t */
    private boolean f2966t;

    /* renamed from: com.iflytek.cloud.thirdparty.ab$a */
    /* loaded from: classes3.dex */
    public interface a {
        /* renamed from: a */
        void mo1803a();
    }

    public C3690ab(Context context) {
        super(context);
        this.f2956j = null;
        this.f2957k = null;
        this.f2958l = null;
        this.f2959m = null;
        this.f2960n = null;
        this.f2961o = 0;
        this.f2962p = false;
        this.f2951e = false;
        this.f2952f = false;
        this.f2953g = null;
        this.f2954h = null;
        this.f2963q = new InterfaceC3689aa() { // from class: com.iflytek.cloud.thirdparty.ab.1
            @Override // com.iflytek.cloud.thirdparty.InterfaceC3689aa
            /* renamed from: a */
            public void mo1770a(ArrayList<byte[]> arrayList, int i, int i2, int i3, String str) {
                Bundle bundle = new Bundle();
                bundle.putInt("percent", i);
                bundle.putInt("begpos", i2);
                bundle.putInt("endpos", i3);
                bundle.putString("spellinfo", str);
                if (C3690ab.this.f2958l != null) {
                    Message.obtain(C3690ab.this.f2965s, 2, bundle).sendToTarget();
                }
                try {
                    C3690ab.this.f2957k.m1689a(arrayList, i, i2, i3);
                    C3690ab.this.m1781j();
                } catch (IOException e) {
                    DebugLog.LogE(e);
                    C3690ab.this.f2954h = new SpeechError(20010);
                    Message.obtain(C3690ab.this.f2965s, 6, C3690ab.this.f2954h).sendToTarget();
                    C3690ab.this.cancel(false);
                }
            }

            @Override // com.iflytek.cloud.thirdparty.InterfaceC3689aa
            /* renamed from: a */
            public void mo1769a(SpeechError speechError) {
                C3690ab c3690ab = C3690ab.this;
                c3690ab.f2954h = speechError;
                if (speechError == null) {
                    c3690ab.f2952f = true;
                    C3690ab.this.f2957k.m1691a(c3690ab.f3436c != null ? C3690ab.this.f3436c.getParam().m1827b(SpeechConstant.AUDIO_FORMAT, (String) null) : null);
                    if (C3690ab.this.f2960n != null) {
                        C3690ab.this.f2960n.mo1803a();
                        DebugLog.LogD("onCompleted NextSession pause");
                    }
                }
                Bundle bundle = new Bundle();
                bundle.putString(SpeechEvent.KEY_EVENT_SESSION_ID, C3690ab.this.f3436c.getSessionID());
                Message.obtain(C3690ab.this.f2965s, 7, bundle).sendToTarget();
                if (C3690ab.this.f2958l == null || speechError == null) {
                    return;
                }
                Message.obtain(C3690ab.this.f2965s, 6, speechError).sendToTarget();
                if (C3690ab.this.f2956j != null) {
                    C3690ab.this.f2956j.m1739e();
                }
            }
        };
        this.f2964r = new C3683c.a() { // from class: com.iflytek.cloud.thirdparty.ab.2
            @Override // com.iflytek.cloud.record.C3683c.a
            /* renamed from: a */
            public void mo1744a() {
                if (C3690ab.this.f2958l != null) {
                    Message.obtain(C3690ab.this.f2965s, 3).sendToTarget();
                }
            }

            @Override // com.iflytek.cloud.record.C3683c.a
            /* renamed from: b */
            public void mo1747b() {
                if (C3690ab.this.f2958l != null) {
                    Message.obtain(C3690ab.this.f2965s, 4).sendToTarget();
                }
            }

            @Override // com.iflytek.cloud.record.C3683c.a
            /* renamed from: a */
            public void mo1745a(int i, int i2, int i3) {
                Message.obtain(C3690ab.this.f2965s, 5, i, i2, Integer.valueOf(i3)).sendToTarget();
            }

            @Override // com.iflytek.cloud.record.C3683c.a
            /* renamed from: a */
            public void mo1746a(SpeechError speechError) {
                Message.obtain(C3690ab.this.f2965s, 6, speechError).sendToTarget();
                if (C3690ab.this.f2956j != null) {
                    C3690ab.this.f2956j.m1739e();
                }
                C3690ab.this.cancel(false);
            }

            @Override // com.iflytek.cloud.record.C3683c.a
            /* renamed from: c */
            public void mo1748c() {
                Message.obtain(C3690ab.this.f2965s, 6, null).sendToTarget();
            }
        };
        this.f2965s = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.thirdparty.ab.3

            /* renamed from: b */
            private int f2970b = 0;

            @Override // android.os.Handler
            public void handleMessage(Message message) {
                try {
                    if (C3690ab.this.f2958l == null) {
                        return;
                    }
                    switch (message.what) {
                        case 1:
                            DebugLog.LogD("tts-onSpeakBegin");
                            C3690ab.this.f2958l.onSpeakBegin();
                            return;
                        case 2:
                            Bundle bundle = (Bundle) message.obj;
                            int i = bundle.getInt("percent");
                            int i2 = bundle.getInt("begpos");
                            int i3 = bundle.getInt("endpos");
                            String string = bundle.getString("spellinfo");
                            if (C3690ab.this.f2958l != null) {
                                DebugLog.LogI("tts-onBufferProgress percent: " + i + ", beg: " + i2 + ", endpos: " + i3 + ", spell: " + string);
                                C3690ab.this.f2958l.onBufferProgress(i, i2, i3, string);
                                return;
                            }
                            return;
                        case 3:
                            DebugLog.LogD("tts-onSpeakPaused");
                            C3690ab.this.f2958l.onSpeakPaused();
                            return;
                        case 4:
                            DebugLog.LogD("tts-onSpeakResumed");
                            C3690ab.this.f2958l.onSpeakResumed();
                            return;
                        case 5:
                            int intValue = ((Integer) message.obj).intValue();
                            if (C3690ab.this.f2958l != null) {
                                if (this.f2970b != intValue) {
                                    DebugLog.LogD("tts-onSpeakProgress percent: " + message.arg1 + ", begpos: " + message.arg2 + ", endpos: " + intValue);
                                    this.f2970b = intValue;
                                }
                                C3690ab.this.f2958l.onSpeakProgress(message.arg1, message.arg2, intValue);
                                return;
                            }
                            return;
                        case 6:
                            DebugLog.LogD("tts-onCompleted");
                            C3690ab.this.f2958l.onCompleted((SpeechError) message.obj);
                            return;
                        case 7:
                            C3690ab.this.f2958l.onEvent(20001, 0, 0, (Bundle) message.obj);
                            return;
                        default:
                            return;
                    }
                } catch (Exception e) {
                    DebugLog.LogE("SpeakSession mUiHandler error:" + e);
                }
            }
        };
        this.f2966t = true;
        this.f2955i = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.thirdparty.ab.5
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                Message message2;
                try {
                    if (C3690ab.this.f2959m == null) {
                        return;
                    }
                    int i = message.what;
                    if (i == 2) {
                        Bundle bundle = (Bundle) message.obj;
                        C3690ab.this.f2959m.onBufferProgress(bundle.getInt("percent"), bundle.getInt("begpos"), bundle.getInt("endpos"), bundle.getString("spellinfo"));
                    } else if (i == 6) {
                        C3690ab.this.f2959m.onCompleted((SpeechError) message.obj);
                    } else if (i == 7 && (message2 = (Message) message.obj) != null) {
                        C3690ab.this.f2959m.onEvent(message2.what, message2.arg1, message2.arg2, (Bundle) message2.obj);
                    }
                } catch (Exception e) {
                    DebugLog.LogE("SpeakSession mUiHandler error:" + e);
                }
            }
        };
    }

    /* renamed from: a */
    public void m1796a(String str, C3692ad c3692ad) {
        setParameter(c3692ad);
        this.f2953g = str;
    }

    /* renamed from: e */
    public void m1798e() {
        if (this.f2952f) {
            return;
        }
        m1792a(this.f2953g, this.mSessionParams, null, false, this.mSessionParams.m1833e("tts_next_audio_path"));
    }

    /* renamed from: a */
    public void m1795a(a aVar) {
        this.f2960n = aVar;
    }

    /* renamed from: a */
    public synchronized int m1792a(String str, C3692ad c3692ad, SynthesizerListener synthesizerListener, boolean z, String str2) {
        int i;
        i = 0;
        try {
            DebugLog.LogD("tts start:" + System.currentTimeMillis());
            this.f2958l = synthesizerListener;
            this.f2953g = str;
            setParameter(c3692ad);
            int m1816a = c3692ad.m1816a(SpeechConstant.STREAM_TYPE, 3);
            boolean m1825a = c3692ad.m1825a(SpeechConstant.KEY_REQUEST_FOCUS, true);
            if (z) {
                this.f2956j = new C3683c(this.f3434a, m1816a, m1825a, C3692ad.m1814b(this.mSessionParams.m1831d(SpeechConstant.TTS_FADING), false), C3692ad.m1814b(this.mSessionParams.m1831d("tts_buf_fading"), false));
            }
            this.f3436c = new HandlerC3747z(this.f3434a, c3692ad, m2259a("tts"));
            this.f2957k = new C3682b(this.f3434a, this.f3436c.getSampleRate(), Math.max(1, c3692ad.m1816a("tts_min_audio_len", 0) / 1000) + (str != null ? str.length() : 0), str2, c3692ad.m1816a("tts_proc_scale", 100));
            this.f2957k.m1690a(C3692ad.m1814b(this.mSessionParams.m1831d("end_with_null"), true));
            this.f2961o = c3692ad.m1816a(SpeechConstant.TTS_BUFFER_TIME, 0);
            DebugLog.LogD("minPlaySec:" + this.f2961o);
            this.f2962p = false;
            ((HandlerC3747z) this.f3436c).m2276a(str, this.f2963q);
            this.f2951e = true;
        } catch (SpeechError e) {
            i = e.getErrorCode();
            DebugLog.LogE(e);
            return i;
        } catch (Throwable th) {
            i = 20999;
            DebugLog.LogE(th);
            return i;
        }
        return i;
    }

    /* renamed from: a */
    public void m1794a(SynthesizerListener synthesizerListener) {
        this.f2958l = synthesizerListener;
    }

    /* renamed from: f */
    public int m1799f() {
        C3683c c3683c;
        if (this.f2957k == null || (c3683c = this.f2956j) == null) {
            return 4;
        }
        return c3683c.m1734a();
    }

    /* renamed from: g */
    public void m1800g() {
        C3683c c3683c;
        if (this.f2957k == null || (c3683c = this.f2956j) == null) {
            return;
        }
        c3683c.m1737c();
    }

    /* renamed from: h */
    public boolean m1801h() {
        if (mo1797d()) {
            return true;
        }
        return (m1799f() == 4 || m1799f() == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m1781j() {
        if (this.f2962p || this.f2956j == null || !this.f2957k.m1694b(this.f2961o)) {
            return;
        }
        this.f2962p = true;
        this.f2956j.m1735a(this.f2957k, this.f2964r);
        if (this.f2958l != null) {
            Message.obtain(this.f2965s, 1).sendToTarget();
        }
    }

    /* renamed from: i */
    public void m1802i() {
        C3683c c3683c;
        if (this.f2957k != null && (c3683c = this.f2956j) != null) {
            c3683c.m1738d();
        } else {
            this.f2956j = new C3683c(this.f3434a);
            m1781j();
        }
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3744w
    public void cancel(boolean z) {
        DebugLog.LogD("SpeakSession cancel notifyError:" + z);
        if (m1801h()) {
            SynthesizerListener synthesizerListener = this.f2958l;
            if (synthesizerListener != null) {
                synthesizerListener.onEvent(21002, 0, 0, null);
            }
            SynthesizerListener synthesizerListener2 = this.f2959m;
            if (synthesizerListener2 != null) {
                synthesizerListener2.onEvent(21002, 0, 0, null);
            }
            if (z) {
                SpeechError speechError = new SpeechError(20017);
                if (this.f2958l != null) {
                    DebugLog.LogD("tts-onCompleted-cancel");
                    Message.obtain(this.f2965s, 6, speechError).sendToTarget();
                }
                SynthesizerListener synthesizerListener3 = this.f2959m;
                if (synthesizerListener3 != null) {
                    if (this.f2966t) {
                        Message.obtain(this.f2955i, 6, speechError).sendToTarget();
                    } else {
                        synthesizerListener3.onCompleted(speechError);
                    }
                }
            }
        }
        this.f2958l = null;
        this.f2959m = null;
        super.cancel(false);
        C3683c c3683c = this.f2956j;
        if (c3683c != null) {
            c3683c.m1739e();
        }
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3744w, com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean destroy() {
        synchronized (this.f3435b) {
            cancel(false);
        }
        return true;
    }

    /* renamed from: a */
    public int m1793a(String str, final String str2, C3692ad c3692ad, SynthesizerListener synthesizerListener) {
        try {
            this.f2966t = c3692ad.m1825a("message_main_thread", true);
            this.f2959m = synthesizerListener;
            this.f3436c = new HandlerC3747z(this.f3434a, c3692ad, m2259a("tts"));
            this.f2957k = new C3682b(this.f3434a, this.f3436c.getSampleRate(), Math.max(1, c3692ad.m1816a("tts_min_audio_len", 0) / 1000) + (str != null ? str.length() : 0), str2, c3692ad.m1816a("tts_proc_scale", 100));
            ((HandlerC3747z) this.f3436c).m2276a(str, new InterfaceC3689aa() { // from class: com.iflytek.cloud.thirdparty.ab.4
                @Override // com.iflytek.cloud.thirdparty.InterfaceC3689aa
                /* renamed from: a */
                public void mo1769a(SpeechError speechError) {
                    if (C3690ab.this.f2959m == null || speechError == null) {
                        return;
                    }
                    if (!C3690ab.this.f2966t) {
                        C3690ab.this.f2959m.onCompleted(speechError);
                    } else {
                        Message.obtain(C3690ab.this.f2955i, 6, speechError).sendToTarget();
                    }
                }

                @Override // com.iflytek.cloud.thirdparty.InterfaceC3689aa
                /* renamed from: a */
                public void mo1770a(ArrayList<byte[]> arrayList, int i, int i2, int i3, String str3) throws SpeechError {
                    if (C3690ab.this.f3436c != null && C3690ab.this.f3436c.getParam().m1825a(SpeechConstant.TTS_DATA_NOTIFY, false) && C3690ab.this.f2959m != null && arrayList != null) {
                        for (int i4 = 0; i4 < arrayList.size(); i4++) {
                            byte[] bArr = arrayList.get(i4);
                            Bundle bundle = new Bundle();
                            bundle.putByteArray(SpeechEvent.KEY_EVENT_TTS_BUFFER, bArr);
                            if (!C3690ab.this.f2966t) {
                                C3690ab.this.f2959m.onEvent(21001, 0, 0, bundle);
                            } else {
                                Message obtain = Message.obtain();
                                obtain.what = 21001;
                                obtain.arg1 = 0;
                                obtain.arg2 = 0;
                                obtain.obj = bundle;
                                Message.obtain(C3690ab.this.f2955i, 7, 0, 0, obtain).sendToTarget();
                            }
                        }
                    }
                    try {
                        C3690ab.this.f2957k.m1689a(arrayList, i, i2, i3);
                        if (C3690ab.this.f2959m != null) {
                            Bundle bundle2 = new Bundle();
                            bundle2.putInt("percent", i);
                            bundle2.putInt("begpos", i2);
                            bundle2.putInt("endpos", i3);
                            bundle2.putString("spellinfo", str3);
                            if (!C3690ab.this.f2966t) {
                                C3690ab.this.f2959m.onBufferProgress(i, i2, i3, str3);
                            } else {
                                Message.obtain(C3690ab.this.f2955i, 2, bundle2).sendToTarget();
                            }
                        }
                        if (i >= 100) {
                            if (C3690ab.this.f3436c == null || !C3690ab.this.f3436c.getParam().m1825a(SpeechConstant.TTS_DATA_NOTIFY, false)) {
                                String m1827b = C3690ab.this.f3436c != null ? C3690ab.this.f3436c.getParam().m1827b(SpeechConstant.AUDIO_FORMAT, (String) null) : null;
                                if (C3690ab.this.f2957k.m1695c() != 0) {
                                    if (!C3690ab.this.f2957k.m1691a(m1827b)) {
                                        throw new IOException();
                                    }
                                } else {
                                    throw new SpeechError(10118);
                                }
                            } else {
                                String m1827b2 = C3690ab.this.f3436c.getParam().m1827b(SpeechConstant.AUDIO_FORMAT, (String) null);
                                if (!TextUtils.isEmpty(str2) && !C3690ab.this.f2957k.m1691a(m1827b2)) {
                                    throw new IOException();
                                }
                            }
                            if (C3690ab.this.f2959m != null) {
                                if (!C3690ab.this.f2966t) {
                                    C3690ab.this.f2959m.onCompleted(null);
                                } else {
                                    Message.obtain(C3690ab.this.f2955i, 6, null).sendToTarget();
                                }
                            }
                        }
                    } catch (IOException e) {
                        DebugLog.LogE(e);
                        if (C3690ab.this.f2959m != null) {
                            if (!C3690ab.this.f2966t) {
                                try {
                                    C3690ab.this.f2959m.onCompleted(new SpeechError(20010));
                                } catch (Exception unused) {
                                }
                            } else {
                                Message.obtain(C3690ab.this.f2955i, 6, new SpeechError(20010)).sendToTarget();
                            }
                        }
                        if (C3690ab.this.f3436c != null) {
                            C3690ab.this.f3436c.cancel(false);
                        }
                    }
                }
            });
            return 0;
        } catch (SpeechError e) {
            int errorCode = e.getErrorCode();
            DebugLog.LogE(e);
            return errorCode;
        } catch (Throwable th) {
            DebugLog.LogE(th);
            return 20999;
        }
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3744w
    /* renamed from: d */
    public boolean mo1797d() {
        return super.mo1797d();
    }
}
