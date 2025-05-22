package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUnderstanderListener;
import com.iflytek.cloud.UnderstanderResult;
import com.iflytek.msc.MSC;

/* renamed from: com.iflytek.cloud.thirdparty.ao */
/* loaded from: classes3.dex */
public class C3703ao {

    /* renamed from: a */
    protected static C3703ao f3036a;

    /* renamed from: b */
    private C3700al f3037b;

    public C3703ao(Context context) {
        this.f3037b = null;
        this.f3037b = new C3700al(context);
    }

    /* renamed from: a */
    public int m1937a(SpeechUnderstanderListener speechUnderstanderListener) {
        a aVar = new a(speechUnderstanderListener);
        if (TextUtils.isEmpty(this.f3037b.getParameter(SpeechConstant.ASR_SCH))) {
            this.f3037b.setParameter(SpeechConstant.ASR_SCH, "1");
        }
        if (TextUtils.isEmpty(this.f3037b.getParameter(SpeechConstant.NLP_VERSION))) {
            this.f3037b.setParameter(SpeechConstant.NLP_VERSION, MSC.isIflyVersion() ? "3.0" : "2.0");
        }
        if (TextUtils.isEmpty(this.f3037b.getParameter(SpeechConstant.RESULT_TYPE))) {
            this.f3037b.setParameter(SpeechConstant.RESULT_TYPE, "json");
        }
        this.f3037b.m1901a(aVar);
        return 0;
    }

    /* renamed from: a */
    public boolean m1940a() {
        return this.f3037b.m1908g();
    }

    /* renamed from: a */
    public int m1938a(byte[] bArr, int i, int i2) {
        return this.f3037b.m1904a(bArr, i, i2);
    }

    /* renamed from: b */
    public void m1942b() {
        this.f3037b.m1906e();
    }

    /* renamed from: a */
    public void m1939a(boolean z) {
        this.f3037b.cancel(z);
    }

    /* renamed from: a */
    public boolean m1941a(C3692ad c3692ad) {
        return this.f3037b.setParameter(c3692ad);
    }

    /* renamed from: c */
    public boolean m1943c() {
        boolean destroy = this.f3037b.destroy();
        if (destroy) {
            f3036a = null;
        }
        return destroy;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.cloud.thirdparty.ao$a */
    /* loaded from: classes3.dex */
    public class a implements RecognizerListener {

        /* renamed from: b */
        private final SpeechUnderstanderListener f3039b;

        public a(SpeechUnderstanderListener speechUnderstanderListener) {
            this.f3039b = speechUnderstanderListener;
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onEndOfSpeech() {
            SpeechUnderstanderListener speechUnderstanderListener = this.f3039b;
            if (speechUnderstanderListener != null) {
                speechUnderstanderListener.onEndOfSpeech();
            }
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onVolumeChanged(int i, byte[] bArr) {
            SpeechUnderstanderListener speechUnderstanderListener = this.f3039b;
            if (speechUnderstanderListener != null) {
                speechUnderstanderListener.onVolumeChanged(i, bArr);
            }
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onBeginOfSpeech() {
            SpeechUnderstanderListener speechUnderstanderListener = this.f3039b;
            if (speechUnderstanderListener != null) {
                speechUnderstanderListener.onBeginOfSpeech();
            }
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onError(SpeechError speechError) {
            SpeechUnderstanderListener speechUnderstanderListener = this.f3039b;
            if (speechUnderstanderListener == null || speechError == null) {
                return;
            }
            speechUnderstanderListener.onError(speechError);
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            SpeechUnderstanderListener speechUnderstanderListener = this.f3039b;
            if (speechUnderstanderListener != null) {
                speechUnderstanderListener.onEvent(i, i2, i3, bundle);
            }
        }

        @Override // com.iflytek.cloud.RecognizerListener
        public void onResult(RecognizerResult recognizerResult, boolean z) {
            SpeechUnderstanderListener speechUnderstanderListener = this.f3039b;
            if (speechUnderstanderListener != null) {
                speechUnderstanderListener.onResult(new UnderstanderResult(recognizerResult.getResultString()));
            }
        }
    }
}
