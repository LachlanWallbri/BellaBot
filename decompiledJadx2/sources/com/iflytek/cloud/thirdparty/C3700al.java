package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.aiui.constant.InternalConstant;
import com.iflytek.cloud.GrammarListener;
import com.iflytek.cloud.LexiconListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.FileUtil;
import com.iflytek.cloud.msc.util.FuncAdapter;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.util.AudioDetector;

/* renamed from: com.iflytek.cloud.thirdparty.al */
/* loaded from: classes3.dex */
public class C3700al extends AbstractC3744w {

    /* renamed from: e */
    private boolean f3013e;

    public C3700al(Context context) {
        super(context);
        this.f3013e = false;
    }

    /* renamed from: b */
    public void m1905b(String str) {
        synchronized (this.f3435b) {
            if (this.f3436c != null) {
                ((HandlerC3725d) this.f3436c).m2076i().m2256a(str);
            }
        }
    }

    /* renamed from: a */
    public int m1901a(RecognizerListener recognizerListener) {
        int i;
        synchronized (this.f3435b) {
            i = 0;
            try {
                this.f3013e = this.mSessionParams.m1825a(SpeechConstant.KEY_REQUEST_FOCUS, true);
                if (this.f3436c != null && this.f3436c.isRunning()) {
                    this.f3436c.cancel(this.mSessionParams.m1825a(SpeechConstant.ASR_INTERRUPT_ERROR, false));
                }
                if (m1909h()) {
                    this.f3436c = new HandlerC3726e(this.f3434a, this.mSessionParams, m2259a(InternalConstant.SUB_IAT));
                } else {
                    this.f3436c = new HandlerC3725d(this.f3434a, this.mSessionParams, m2259a(InternalConstant.SUB_IAT));
                }
                FuncAdapter.Lock(this.f3434a, Boolean.valueOf(this.f3013e), null);
                ((HandlerC3725d) this.f3436c).m2064a(new c(recognizerListener));
            } catch (SpeechError e) {
                i = e.getErrorCode();
                DebugLog.LogE(e);
            } catch (Throwable th) {
                i = 20999;
                DebugLog.LogE(th);
            }
        }
        return i;
    }

    /* renamed from: a */
    public int m1904a(byte[] bArr, int i, int i2) {
        synchronized (this.f3435b) {
            if (this.f3436c == null) {
                DebugLog.LogD("writeAudio error, no active session.");
                return 21004;
            }
            if (bArr != null && bArr.length > 0) {
                if (bArr.length < i2 + i) {
                    DebugLog.LogD("writeAudio error,buffer length < length.");
                    return 10109;
                }
                if (((HandlerC3725d) this.f3436c).m2061a() != -1) {
                    return 10106;
                }
                return ((HandlerC3725d) this.f3436c).m2062a(bArr, i, i2);
            }
            DebugLog.LogD("writeAudio error,buffer is null.");
            return 10109;
        }
    }

    /* renamed from: e */
    public void m1906e() {
        synchronized (this.f3435b) {
            if (this.f3436c != null) {
                ((HandlerC3725d) this.f3436c).m2067a(true);
            }
        }
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3744w
    public void cancel(boolean z) {
        synchronized (this.f3435b) {
            m1907f();
            super.cancel(z);
        }
    }

    /* renamed from: f */
    protected void m1907f() {
        if (this.f3436c != null) {
            String m1833e = this.f3436c.getParam().m1833e(SpeechConstant.ASR_AUDIO_PATH);
            if (!TextUtils.isEmpty(m1833e) && FileUtil.saveFile(((HandlerC3725d) this.f3436c).m2068b(), m1833e)) {
                FileUtil.formatPcm(this.f3436c.getParam().m1827b(SpeechConstant.AUDIO_FORMAT, (String) null), m1833e, this.f3436c.getParam().m1816a("sample_rate", this.f3436c.mSampleRate));
            }
        }
        FuncAdapter.UnLock(this.f3434a, Boolean.valueOf(this.f3013e), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.cloud.thirdparty.al$b */
    /* loaded from: classes3.dex */
    public final class b implements LexiconListener {

        /* renamed from: b */
        private LexiconListener f3019b;

        /* renamed from: c */
        private Handler f3020c = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.thirdparty.al.b.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (b.this.f3019b == null) {
                    return;
                }
                int i = message.what;
                if (i == 0) {
                    b.this.f3019b.onLexiconUpdated(null, (SpeechError) message.obj);
                } else if (i == 1) {
                    b.this.f3019b.onLexiconUpdated((String) message.obj, null);
                }
                super.handleMessage(message);
            }
        };

        public b(LexiconListener lexiconListener) {
            this.f3019b = null;
            this.f3019b = lexiconListener;
        }

        @Override // com.iflytek.cloud.LexiconListener
        public void onLexiconUpdated(String str, SpeechError speechError) {
            if (speechError != null) {
                this.f3020c.sendMessage(this.f3020c.obtainMessage(0, speechError));
            } else {
                this.f3020c.sendMessage(this.f3020c.obtainMessage(1, str));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.cloud.thirdparty.al$a */
    /* loaded from: classes3.dex */
    public final class a implements GrammarListener {

        /* renamed from: b */
        private GrammarListener f3015b;

        /* renamed from: c */
        private Handler f3016c = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.thirdparty.al.a.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (a.this.f3015b == null) {
                    return;
                }
                int i = message.what;
                if (i == 0) {
                    a.this.f3015b.onBuildFinish(null, (SpeechError) message.obj);
                } else if (i == 1) {
                    a.this.f3015b.onBuildFinish((String) message.obj, null);
                }
                super.handleMessage(message);
            }
        };

        public a(GrammarListener grammarListener) {
            this.f3015b = null;
            this.f3015b = grammarListener;
        }

        @Override // com.iflytek.cloud.GrammarListener
        public void onBuildFinish(String str, SpeechError speechError) {
            if (speechError != null) {
                this.f3016c.sendMessage(this.f3016c.obtainMessage(0, speechError));
            } else {
                this.f3016c.sendMessage(this.f3016c.obtainMessage(1, str));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.cloud.thirdparty.al$c */
    /* loaded from: classes3.dex */
    public final class c implements RecognizerListener {

        /* renamed from: b */
        private RecognizerListener f3023b;

        /* renamed from: c */
        private boolean f3024c = false;

        /* renamed from: d */
        private Handler f3025d = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.thirdparty.al.c.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (c.this.f3023b == null) {
                    return;
                }
                int i = message.what;
                if (i == 0) {
                    c.this.f3023b.onError((SpeechError) message.obj);
                } else if (i == 1) {
                    c.this.f3023b.onVolumeChanged(message.arg1, (byte[]) message.obj);
                } else if (i == 2) {
                    c.this.f3023b.onBeginOfSpeech();
                } else if (i == 3) {
                    c.this.f3023b.onEndOfSpeech();
                } else if (i == 4) {
                    c.this.f3023b.onResult((RecognizerResult) message.obj, message.arg1 == 1);
                    if (!c.this.f3024c) {
                        C3700al.this.m1905b("ui_frs");
                        c.this.f3024c = true;
                    }
                    if (1 == message.arg1) {
                        C3700al.this.m1905b("ui_lrs");
                    }
                } else if (i == 6) {
                    Message message2 = (Message) message.obj;
                    c.this.f3023b.onEvent(message2.what, message2.arg1, message2.arg2, (Bundle) message2.obj);
                }
                super.handleMessage(message);
            }
        };

        public c(RecognizerListener recognizerListener) {
            this.f3023b = null;
            this.f3023b = recognizerListener;
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onVolumeChanged(int i, byte[] bArr) {
            this.f3025d.sendMessage(this.f3025d.obtainMessage(1, i, 0, bArr));
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onEndOfSpeech() {
            this.f3025d.sendMessage(this.f3025d.obtainMessage(3, 0, 0, null));
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onResult(RecognizerResult recognizerResult, boolean z) {
            if (z) {
                C3700al.this.m1907f();
            }
            this.f3025d.sendMessage(this.f3025d.obtainMessage(4, !z ? 0 : 1, 0, recognizerResult));
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onBeginOfSpeech() {
            DebugLog.LogD("onBeginOfSpeech");
            this.f3025d.sendMessage(this.f3025d.obtainMessage(2, 0, 0, null));
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onError(SpeechError speechError) {
            C3700al.this.m1907f();
            this.f3025d.sendMessage(this.f3025d.obtainMessage(0, speechError));
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            Message message = new Message();
            message.what = i;
            message.arg1 = i2;
            message.arg2 = i3;
            message.obj = bundle;
            this.f3025d.sendMessage(this.f3025d.obtainMessage(6, 0, 0, message));
        }
    }

    /* renamed from: g */
    public boolean m1908g() {
        return mo1797d();
    }

    /* renamed from: a */
    public int m1902a(String str, String str2, GrammarListener grammarListener) {
        if (TextUtils.isEmpty(str2)) {
            return 20009;
        }
        if (TextUtils.isEmpty(str) || grammarListener == null) {
            return 20012;
        }
        new C3724c().m2051a(str, str2, new a(grammarListener), this.mSessionParams);
        return 0;
    }

    /* renamed from: a */
    public int m1903a(String str, String str2, LexiconListener lexiconListener) {
        if (TextUtils.isEmpty(str2)) {
            return 20009;
        }
        if (TextUtils.isEmpty(str) || lexiconListener == null) {
            return 20012;
        }
        C3724c c3724c = new C3724c();
        this.mSessionParams.m1823a(SpeechConstant.SUBJECT, "uup", false);
        String parameter = getParameter(SpeechConstant.LEXICON_TYPE);
        if (TextUtils.isEmpty(parameter)) {
            parameter = str;
        }
        this.mSessionParams.m1823a("data_type", parameter, false);
        c3724c.m2052a(str, str2, new b(lexiconListener), this.mSessionParams);
        return 0;
    }

    /* renamed from: h */
    protected boolean m1909h() {
        if (TextUtils.isEmpty(this.mSessionParams.m1833e("bos_dispose"))) {
            return AudioDetector.TYPE_META.equalsIgnoreCase(this.mSessionParams.m1833e(AudioDetector.VAD_ENGINE));
        }
        return this.mSessionParams.m1825a("bos_dispose", false);
    }
}
