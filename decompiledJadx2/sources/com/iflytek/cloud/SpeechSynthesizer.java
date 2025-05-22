package com.iflytek.cloud;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.AbstractC3743v;
import com.iflytek.cloud.thirdparty.C3701am;
import com.iflytek.cloud.util.ResourceUtil;
import com.iflytek.speech.SpeechSynthesizerAidl;

/* loaded from: classes3.dex */
public class SpeechSynthesizer extends AbstractC3743v {

    /* renamed from: b */
    private static SpeechSynthesizer f2796b;

    /* renamed from: a */
    InitListener f2797a;

    /* renamed from: c */
    private C3701am f2798c;

    /* renamed from: d */
    private SpeechSynthesizerAidl f2799d;

    /* renamed from: e */
    private C3666a f2800e = null;

    /* renamed from: f */
    private Handler f2801f = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.SpeechSynthesizer.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (SpeechSynthesizer.this.f2797a == null) {
                return;
            }
            SpeechSynthesizer.this.f2797a.onInit(0);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.cloud.SpeechSynthesizer$a */
    /* loaded from: classes3.dex */
    public final class C3666a implements SynthesizerListener {

        /* renamed from: a */
        private SynthesizerListener f2803a;

        /* renamed from: b */
        private com.iflytek.speech.SynthesizerListener f2804b;

        /* renamed from: c */
        private Handler f2805c;

        @Override // com.iflytek.cloud.SynthesizerListener
        public void onSpeakBegin() {
            if (this.f2803a != null) {
                Message.obtain(this.f2805c, 1).sendToTarget();
            }
        }

        @Override // com.iflytek.cloud.SynthesizerListener
        public void onBufferProgress(int i, int i2, int i3, String str) {
            if (this.f2803a != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("percent", i);
                bundle.putInt("begpos", i2);
                bundle.putInt("endpos", i3);
                bundle.putString("spellinfo", str);
                if (this.f2803a != null) {
                    Message.obtain(this.f2805c, 2, bundle).sendToTarget();
                }
            }
        }

        @Override // com.iflytek.cloud.SynthesizerListener
        public void onSpeakPaused() {
            if (this.f2803a != null) {
                Message.obtain(this.f2805c, 3).sendToTarget();
            }
        }

        @Override // com.iflytek.cloud.SynthesizerListener
        public void onSpeakResumed() {
            if (this.f2803a != null) {
                Message.obtain(this.f2805c, 4).sendToTarget();
            }
        }

        @Override // com.iflytek.cloud.SynthesizerListener
        public void onSpeakProgress(int i, int i2, int i3) {
            if (this.f2803a != null) {
                Message.obtain(this.f2805c, 5, i, i2, Integer.valueOf(i3)).sendToTarget();
            }
        }

        @Override // com.iflytek.cloud.SynthesizerListener
        public void onCompleted(SpeechError speechError) {
            if (this.f2803a != null) {
                Message.obtain(this.f2805c, 6, speechError).sendToTarget();
            }
        }

        @Override // com.iflytek.cloud.SynthesizerListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            if (this.f2803a != null) {
                Message obtain = Message.obtain();
                obtain.what = i;
                obtain.arg1 = 0;
                obtain.arg2 = 0;
                obtain.obj = bundle;
                Message.obtain(this.f2805c, 7, 0, 0, obtain).sendToTarget();
            }
        }
    }

    public static SpeechSynthesizer createSynthesizer(Context context, InitListener initListener) {
        synchronized (sSync) {
            if (f2796b == null && SpeechUtility.getUtility() != null) {
                f2796b = new SpeechSynthesizer(context, initListener);
            }
        }
        return f2796b;
    }

    public static SpeechSynthesizer getSynthesizer() {
        return f2796b;
    }

    protected SpeechSynthesizer(Context context, InitListener initListener) {
        this.f2798c = null;
        this.f2799d = null;
        this.f2797a = null;
        this.f2797a = initListener;
        this.f2798c = new C3701am(context);
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null && utility.m1667a() && utility.getEngineMode() != AbstractC3743v.a.MSC) {
            this.f2799d = new SpeechSynthesizerAidl(context.getApplicationContext(), initListener);
        } else {
            Message.obtain(this.f2801f, 0, 0, 0, null).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m1655a(Context context) {
        SpeechSynthesizerAidl speechSynthesizerAidl;
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null && utility.m1667a() && utility.getEngineMode() != AbstractC3743v.a.MSC) {
            SpeechSynthesizerAidl speechSynthesizerAidl2 = this.f2799d;
            if (speechSynthesizerAidl2 != null && !speechSynthesizerAidl2.isAvailable()) {
                this.f2799d.destory();
                this.f2799d = null;
            }
            this.f2799d = new SpeechSynthesizerAidl(context.getApplicationContext(), this.f2797a);
            return;
        }
        if (this.f2797a == null || (speechSynthesizerAidl = this.f2799d) == null) {
            return;
        }
        speechSynthesizerAidl.destory();
        this.f2799d = null;
    }

    public int startSpeaking(String str, SynthesizerListener synthesizerListener) {
        DebugLog.LogD("stop all current session in new session");
        stopSpeaking();
        getStartMode("tts", this.f2799d);
        C3701am c3701am = this.f2798c;
        if (c3701am == null) {
            return 21001;
        }
        c3701am.setParameter(this.mSessionParams);
        this.mSessionParams.m1829c(SpeechConstant.NEXT_TEXT);
        return this.f2798c.m1916a(str, synthesizerListener);
    }

    public int synthesizeToUri(String str, String str2, SynthesizerListener synthesizerListener) {
        C3701am c3701am = this.f2798c;
        if (c3701am == null) {
            return 21001;
        }
        c3701am.setParameter(this.mSessionParams);
        return this.f2798c.m1917a(str, str2, synthesizerListener);
    }

    public void pauseSpeaking() {
        C3666a c3666a;
        C3701am c3701am = this.f2798c;
        if (c3701am != null && c3701am.m1921g()) {
            this.f2798c.m1919e();
            return;
        }
        SpeechSynthesizerAidl speechSynthesizerAidl = this.f2799d;
        if (speechSynthesizerAidl == null || !speechSynthesizerAidl.isSpeaking() || (c3666a = this.f2800e) == null) {
            return;
        }
        this.f2799d.pauseSpeaking(c3666a.f2804b);
    }

    public void resumeSpeaking() {
        C3666a c3666a;
        C3701am c3701am = this.f2798c;
        if (c3701am != null && c3701am.m1921g()) {
            this.f2798c.m1920f();
            return;
        }
        SpeechSynthesizerAidl speechSynthesizerAidl = this.f2799d;
        if (speechSynthesizerAidl == null || !speechSynthesizerAidl.isSpeaking() || (c3666a = this.f2800e) == null) {
            return;
        }
        this.f2799d.resumeSpeaking(c3666a.f2804b);
    }

    public void stopSpeaking() {
        C3666a c3666a;
        C3701am c3701am = this.f2798c;
        if (c3701am != null && c3701am.m1921g()) {
            this.f2798c.m1918a(false);
        }
        SpeechSynthesizerAidl speechSynthesizerAidl = this.f2799d;
        if (speechSynthesizerAidl == null || !speechSynthesizerAidl.isSpeaking() || (c3666a = this.f2800e) == null) {
            return;
        }
        this.f2799d.stopSpeaking(c3666a.f2804b);
    }

    public boolean isSpeaking() {
        C3701am c3701am = this.f2798c;
        if (c3701am != null && c3701am.m1921g()) {
            return true;
        }
        SpeechSynthesizerAidl speechSynthesizerAidl = this.f2799d;
        return speechSynthesizerAidl != null && speechSynthesizerAidl.isSpeaking();
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public String getParameter(String str) {
        SpeechSynthesizerAidl speechSynthesizerAidl;
        if (SpeechConstant.LOCAL_SPEAKERS.equals(str) && (speechSynthesizerAidl = this.f2799d) != null) {
            return speechSynthesizerAidl.getParameter(str);
        }
        if (SpeechConstant.TTS_PLAY_STATE.equals(str) && this.f2798c != null) {
            return "" + this.f2798c.m1922h();
        }
        return super.getParameter(str);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean destroy() {
        SpeechSynthesizerAidl speechSynthesizerAidl = this.f2799d;
        if (speechSynthesizerAidl != null) {
            speechSynthesizerAidl.destory();
        }
        C3701am c3701am = this.f2798c;
        boolean destroy = c3701am != null ? c3701am.destroy() : true;
        if (destroy && (destroy = super.destroy())) {
            synchronized (sSync) {
                f2796b = null;
            }
            SpeechUtility utility = SpeechUtility.getUtility();
            if (utility != null) {
                DebugLog.LogD("Destory tts engine.");
                utility.setParameter(ResourceUtil.ENGINE_DESTROY, "engine_destroy=tts");
            }
        }
        return destroy;
    }
}
