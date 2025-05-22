package com.iflytek.cloud;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.AbstractC3743v;
import com.iflytek.cloud.thirdparty.C3700al;
import com.iflytek.cloud.util.ResourceUtil;
import com.iflytek.speech.SpeechRecognizerAidl;

/* loaded from: classes3.dex */
public final class SpeechRecognizer extends AbstractC3743v {

    /* renamed from: a */
    private static SpeechRecognizer f2787a;

    /* renamed from: b */
    private C3700al f2788b;

    /* renamed from: c */
    private SpeechRecognizerAidl f2789c;

    /* renamed from: e */
    private InitListener f2791e;

    /* renamed from: d */
    private C3664a f2790d = null;

    /* renamed from: f */
    private Handler f2792f = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.SpeechRecognizer.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (SpeechRecognizer.this.f2791e == null) {
                return;
            }
            SpeechRecognizer.this.f2791e.onInit(0);
        }
    };

    /* renamed from: com.iflytek.cloud.SpeechRecognizer$a */
    /* loaded from: classes3.dex */
    private final class C3664a implements RecognizerListener {

        /* renamed from: a */
        private com.iflytek.speech.RecognizerListener f2794a;

        /* renamed from: b */
        private Handler f2795b;

        @Override // com.iflytek.cloud.RecognizerListener
        public void onVolumeChanged(int i, byte[] bArr) {
            this.f2795b.sendMessage(this.f2795b.obtainMessage(1, i, 0, bArr));
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onEndOfSpeech() {
            this.f2795b.sendMessage(this.f2795b.obtainMessage(3, 0, 0, null));
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onResult(RecognizerResult recognizerResult, boolean z) {
            this.f2795b.sendMessage(this.f2795b.obtainMessage(4, !z ? 0 : 1, 0, recognizerResult));
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onBeginOfSpeech() {
            this.f2795b.sendMessage(this.f2795b.obtainMessage(2, 0, 0, null));
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onError(SpeechError speechError) {
            this.f2795b.sendMessage(this.f2795b.obtainMessage(0, speechError));
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            Message message = new Message();
            message.what = i;
            message.arg1 = i2;
            message.arg2 = i3;
            message.obj = bundle;
            this.f2795b.sendMessage(this.f2795b.obtainMessage(6, 0, 0, message));
        }
    }

    public static synchronized SpeechRecognizer createRecognizer(Context context, InitListener initListener) {
        SpeechRecognizer speechRecognizer;
        synchronized (SpeechRecognizer.class) {
            synchronized (sSync) {
                if (f2787a == null && SpeechUtility.getUtility() != null) {
                    f2787a = new SpeechRecognizer(context, initListener);
                }
            }
            speechRecognizer = f2787a;
        }
        return speechRecognizer;
    }

    public static SpeechRecognizer getRecognizer() {
        return f2787a;
    }

    protected SpeechRecognizer(Context context, InitListener initListener) {
        this.f2788b = null;
        this.f2789c = null;
        this.f2791e = null;
        this.f2791e = initListener;
        this.f2788b = new C3700al(context);
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null && utility.m1667a() && utility.getEngineMode() != AbstractC3743v.a.MSC) {
            this.f2789c = new SpeechRecognizerAidl(context.getApplicationContext(), initListener);
        } else if (initListener != null) {
            Message.obtain(this.f2792f, 0, 0, 0, null).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m1653a(Context context) {
        SpeechRecognizerAidl speechRecognizerAidl;
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null && utility.m1667a() && utility.getEngineMode() != AbstractC3743v.a.MSC) {
            SpeechRecognizerAidl speechRecognizerAidl2 = this.f2789c;
            if (speechRecognizerAidl2 != null && !speechRecognizerAidl2.isAvailable()) {
                this.f2789c.destory();
                this.f2789c = null;
            }
            this.f2789c = new SpeechRecognizerAidl(context.getApplicationContext(), this.f2791e);
            return;
        }
        if (this.f2791e == null || (speechRecognizerAidl = this.f2789c) == null) {
            return;
        }
        speechRecognizerAidl.destory();
        this.f2789c = null;
    }

    public int buildGrammar(String str, String str2, GrammarListener grammarListener) {
        DebugLog.LogD("start engine mode = " + AbstractC3743v.a.MSC.toString());
        C3700al c3700al = this.f2788b;
        if (c3700al == null) {
            return 21001;
        }
        c3700al.setParameter(this.mSessionParams);
        return this.f2788b.m1902a(str, str2, grammarListener);
    }

    public int updateLexicon(String str, String str2, LexiconListener lexiconListener) {
        DebugLog.LogD("start engine mode = " + getStartMode("asr", this.f2789c).toString());
        C3700al c3700al = this.f2788b;
        if (c3700al == null) {
            return 21001;
        }
        c3700al.setParameter(this.mSessionParams);
        return this.f2788b.m1903a(str, str2, lexiconListener);
    }

    public int startListening(RecognizerListener recognizerListener) {
        DebugLog.LogD("start engine mode = " + getStartMode("asr", this.f2789c).toString());
        C3700al c3700al = this.f2788b;
        if (c3700al == null) {
            return 21001;
        }
        c3700al.setParameter(this.mSessionParams);
        return this.f2788b.m1901a(recognizerListener);
    }

    public int writeAudio(byte[] bArr, int i, int i2) {
        C3700al c3700al = this.f2788b;
        if (c3700al != null && c3700al.m1908g()) {
            return this.f2788b.m1904a(bArr, i, i2);
        }
        SpeechRecognizerAidl speechRecognizerAidl = this.f2789c;
        if (speechRecognizerAidl != null && speechRecognizerAidl.isListening()) {
            return this.f2789c.writeAudio(bArr, i, i2);
        }
        DebugLog.LogE("SpeechRecognizer writeAudio failed, is not running");
        return 21004;
    }

    public void stopListening() {
        C3700al c3700al = this.f2788b;
        if (c3700al != null && c3700al.m1908g()) {
            this.f2788b.m1906e();
            return;
        }
        SpeechRecognizerAidl speechRecognizerAidl = this.f2789c;
        if (speechRecognizerAidl != null && speechRecognizerAidl.isListening()) {
            C3664a c3664a = this.f2790d;
            if (c3664a != null) {
                this.f2789c.stopListening(c3664a.f2794a);
                return;
            }
            return;
        }
        DebugLog.LogE("SpeechRecognizer stopListening failed, is not running");
    }

    public boolean isListening() {
        C3700al c3700al = this.f2788b;
        if (c3700al != null && c3700al.m1908g()) {
            return true;
        }
        SpeechRecognizerAidl speechRecognizerAidl = this.f2789c;
        return speechRecognizerAidl != null && speechRecognizerAidl.isListening();
    }

    public void cancel() {
        C3700al c3700al = this.f2788b;
        if (c3700al != null && c3700al.m1908g()) {
            this.f2788b.cancel(false);
            return;
        }
        SpeechRecognizerAidl speechRecognizerAidl = this.f2789c;
        if (speechRecognizerAidl != null && speechRecognizerAidl.isListening()) {
            C3664a c3664a = this.f2790d;
            if (c3664a != null) {
                this.f2789c.cancel(c3664a.f2794a);
                return;
            }
            return;
        }
        DebugLog.LogE("SpeechRecognizer cancel failed, is not running");
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public String getParameter(String str) {
        return super.getParameter(str);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean destroy() {
        SpeechRecognizerAidl speechRecognizerAidl = this.f2789c;
        if (speechRecognizerAidl != null) {
            speechRecognizerAidl.destory();
        }
        synchronized (this) {
            this.f2789c = null;
        }
        C3700al c3700al = this.f2788b;
        boolean destroy = c3700al != null ? c3700al.destroy() : true;
        if (destroy && (destroy = super.destroy())) {
            synchronized (sSync) {
                f2787a = null;
            }
            SpeechUtility utility = SpeechUtility.getUtility();
            if (utility != null) {
                DebugLog.LogD("Destory asr engine.");
                utility.setParameter(ResourceUtil.ENGINE_DESTROY, "engine_destroy=asr");
            }
        }
        return destroy;
    }
}
