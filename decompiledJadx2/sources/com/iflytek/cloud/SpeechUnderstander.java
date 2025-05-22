package com.iflytek.cloud;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.AbstractC3743v;
import com.iflytek.cloud.thirdparty.C3703ao;
import com.iflytek.speech.SpeechUnderstanderAidl;

/* loaded from: classes3.dex */
public class SpeechUnderstander extends AbstractC3743v {

    /* renamed from: a */
    protected static SpeechUnderstander f2806a;

    /* renamed from: b */
    private C3703ao f2807b;

    /* renamed from: c */
    private SpeechUnderstanderAidl f2808c;

    /* renamed from: e */
    private InitListener f2810e;

    /* renamed from: d */
    private C3668a f2809d = null;

    /* renamed from: f */
    private Handler f2811f = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.SpeechUnderstander.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (SpeechUnderstander.this.f2810e == null) {
                return;
            }
            SpeechUnderstander.this.f2810e.onInit(0);
        }
    };

    public static synchronized SpeechUnderstander createUnderstander(Context context, InitListener initListener) {
        SpeechUnderstander speechUnderstander;
        synchronized (SpeechUnderstander.class) {
            synchronized (sSync) {
                if (f2806a == null && SpeechUtility.getUtility() != null) {
                    f2806a = new SpeechUnderstander(context, initListener);
                }
            }
            speechUnderstander = f2806a;
        }
        return speechUnderstander;
    }

    public static SpeechUnderstander getUnderstander() {
        return f2806a;
    }

    protected SpeechUnderstander(Context context, InitListener initListener) {
        this.f2807b = null;
        this.f2808c = null;
        this.f2810e = null;
        this.f2810e = initListener;
        this.f2807b = new C3703ao(context);
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null && utility.m1667a() && utility.getEngineMode() != AbstractC3743v.a.MSC) {
            this.f2808c = new SpeechUnderstanderAidl(context.getApplicationContext(), initListener);
        } else if (initListener != null) {
            Message.obtain(this.f2811f, 0, 0, 0, null).sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m1658a(Context context) {
        SpeechUnderstanderAidl speechUnderstanderAidl;
        SpeechUtility utility = SpeechUtility.getUtility();
        if (utility != null && utility.m1667a() && utility.getEngineMode() != AbstractC3743v.a.MSC) {
            SpeechUnderstanderAidl speechUnderstanderAidl2 = this.f2808c;
            if (speechUnderstanderAidl2 != null && !speechUnderstanderAidl2.isAvailable()) {
                this.f2808c.destory();
                this.f2808c = null;
            }
            this.f2808c = new SpeechUnderstanderAidl(context.getApplicationContext(), this.f2810e);
            return;
        }
        if (this.f2810e == null || (speechUnderstanderAidl = this.f2808c) == null) {
            return;
        }
        speechUnderstanderAidl.destory();
        this.f2808c = null;
    }

    public int startUnderstanding(SpeechUnderstanderListener speechUnderstanderListener) {
        DebugLog.LogD("start engine mode = " + getStartMode(SpeechConstant.ENG_NLU, this.f2808c).toString());
        C3703ao c3703ao = this.f2807b;
        if (c3703ao == null) {
            return 21001;
        }
        c3703ao.m1941a(this.mSessionParams);
        return this.f2807b.m1937a(speechUnderstanderListener);
    }

    public boolean isUnderstanding() {
        C3703ao c3703ao = this.f2807b;
        if (c3703ao != null && c3703ao.m1940a()) {
            return true;
        }
        SpeechUnderstanderAidl speechUnderstanderAidl = this.f2808c;
        return speechUnderstanderAidl != null && speechUnderstanderAidl.isUnderstanding();
    }

    public int writeAudio(byte[] bArr, int i, int i2) {
        C3703ao c3703ao = this.f2807b;
        if (c3703ao != null && c3703ao.m1940a()) {
            return this.f2807b.m1938a(bArr, i, i2);
        }
        SpeechUnderstanderAidl speechUnderstanderAidl = this.f2808c;
        if (speechUnderstanderAidl != null && speechUnderstanderAidl.isUnderstanding()) {
            return this.f2808c.writeAudio(bArr, i, i2);
        }
        DebugLog.LogD("SpeechUnderstander writeAudio, is not understanding");
        return 21004;
    }

    public void stopUnderstanding() {
        C3703ao c3703ao = this.f2807b;
        if (c3703ao != null && c3703ao.m1940a()) {
            this.f2807b.m1942b();
            return;
        }
        SpeechUnderstanderAidl speechUnderstanderAidl = this.f2808c;
        if (speechUnderstanderAidl == null || !speechUnderstanderAidl.isUnderstanding()) {
            DebugLog.LogD("SpeechUnderstander stopUnderstanding, is not understanding");
        } else {
            this.f2808c.stopUnderstanding(this.f2809d.f2813a);
        }
    }

    public void cancel() {
        C3703ao c3703ao = this.f2807b;
        if (c3703ao != null && c3703ao.m1940a()) {
            this.f2807b.m1939a(false);
            return;
        }
        SpeechUnderstanderAidl speechUnderstanderAidl = this.f2808c;
        if (speechUnderstanderAidl == null || !speechUnderstanderAidl.isUnderstanding()) {
            DebugLog.LogE("SpeechUnderstander cancel failed, is not running");
        } else {
            this.f2808c.cancel(this.f2809d.f2813a);
        }
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
        SpeechUnderstanderAidl speechUnderstanderAidl = this.f2808c;
        if (speechUnderstanderAidl != null) {
            speechUnderstanderAidl.destory();
        }
        synchronized (this) {
            this.f2808c = null;
        }
        C3703ao c3703ao = this.f2807b;
        boolean m1943c = c3703ao != null ? c3703ao.m1943c() : true;
        if (m1943c && (m1943c = super.destroy())) {
            synchronized (sSync) {
                f2806a = null;
            }
        }
        return m1943c;
    }

    /* renamed from: com.iflytek.cloud.SpeechUnderstander$a */
    /* loaded from: classes3.dex */
    private final class C3668a implements SpeechUnderstanderListener {

        /* renamed from: a */
        private com.iflytek.speech.SpeechUnderstanderListener f2813a;

        /* renamed from: b */
        private Handler f2814b;

        @Override // com.iflytek.cloud.SpeechUnderstanderListener
        public void onVolumeChanged(int i, byte[] bArr) {
            this.f2814b.sendMessage(this.f2814b.obtainMessage(1, i, 0, bArr));
        }

        @Override // com.iflytek.cloud.SpeechUnderstanderListener
        public void onEndOfSpeech() {
            this.f2814b.sendMessage(this.f2814b.obtainMessage(3, 0, 0, null));
        }

        @Override // com.iflytek.cloud.SpeechUnderstanderListener
        public void onResult(UnderstanderResult understanderResult) {
            this.f2814b.sendMessage(this.f2814b.obtainMessage(4, understanderResult));
        }

        @Override // com.iflytek.cloud.SpeechUnderstanderListener
        public void onBeginOfSpeech() {
            this.f2814b.sendMessage(this.f2814b.obtainMessage(2, 0, 0, null));
        }

        @Override // com.iflytek.cloud.SpeechUnderstanderListener
        public void onError(SpeechError speechError) {
            this.f2814b.sendMessage(this.f2814b.obtainMessage(0, speechError));
        }

        @Override // com.iflytek.cloud.SpeechUnderstanderListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            Message message = new Message();
            message.what = i;
            message.arg1 = i2;
            message.arg2 = i3;
            message.obj = bundle;
            this.f2814b.sendMessage(this.f2814b.obtainMessage(6, 0, 0, message));
        }
    }
}
