package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.iflytek.cloud.EvaluatorListener;
import com.iflytek.cloud.EvaluatorResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.FileUtil;
import com.iflytek.cloud.msc.util.FuncAdapter;
import com.iflytek.cloud.msc.util.log.DebugLog;

/* renamed from: com.iflytek.cloud.thirdparty.ak */
/* loaded from: classes3.dex */
public class C3699ak extends AbstractC3744w {

    /* renamed from: e */
    private boolean f3008e;

    public C3699ak(Context context) {
        super(context);
        this.f3008e = false;
    }

    /* renamed from: a */
    public int m1894a(String str, String str2, EvaluatorListener evaluatorListener) {
        int i;
        synchronized (this.f3435b) {
            i = 0;
            try {
                this.f3008e = this.mSessionParams.m1825a(SpeechConstant.KEY_REQUEST_FOCUS, true);
                if (this.f3436c != null && this.f3436c.isRunning()) {
                    this.f3436c.cancel(this.mSessionParams.m1825a(SpeechConstant.ISE_INTERRUPT_ERROR, false));
                }
                this.f3436c = new HandlerC3715b(this.f3434a, this.mSessionParams, m2259a(SpeechConstant.ENG_EVA));
                FuncAdapter.Lock(this.f3434a, Boolean.valueOf(this.f3008e), null);
                ((HandlerC3715b) this.f3436c).m2013a(str, str2, new a(evaluatorListener));
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
    public int m1895a(byte[] bArr, String str, EvaluatorListener evaluatorListener) {
        int i;
        synchronized (this.f3435b) {
            i = 0;
            try {
                this.f3008e = this.mSessionParams.m1825a(SpeechConstant.KEY_REQUEST_FOCUS, true);
                if (this.f3436c != null && this.f3436c.isRunning()) {
                    this.f3436c.cancel(this.mSessionParams.m1825a(SpeechConstant.ISE_INTERRUPT_ERROR, false));
                }
                this.f3436c = new HandlerC3715b(this.f3434a, this.mSessionParams, m2259a(SpeechConstant.ENG_EVA));
                FuncAdapter.Lock(this.f3434a, Boolean.valueOf(this.f3008e), null);
                ((HandlerC3715b) this.f3436c).m2014a(bArr, str, new a(evaluatorListener));
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
    public boolean m1896a(byte[] bArr, int i, int i2) {
        synchronized (this.f3435b) {
            if (this.f3436c == null) {
                DebugLog.LogD("writeAudio error, no active session.");
                return false;
            }
            if (bArr != null && bArr.length > 0) {
                if (bArr.length < i2 + i) {
                    DebugLog.LogD("writeAudio error,buffer length < length.");
                    return false;
                }
                ((HandlerC3715b) this.f3436c).onRecordBuffer(bArr, i, i2);
                return true;
            }
            DebugLog.LogD("writeAudio error,buffer is null.");
            return false;
        }
    }

    /* renamed from: e */
    public void m1897e() {
        synchronized (this.f3435b) {
            if (this.f3436c != null) {
                ((HandlerC3715b) this.f3436c).m2015a(true);
            }
        }
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3744w
    public void cancel(boolean z) {
        synchronized (this.f3435b) {
            m1898f();
        }
        super.cancel(z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractC3744w
    /* renamed from: a_ */
    public boolean mo1643a_() {
        return super.mo1643a_();
    }

    /* renamed from: f */
    protected void m1898f() {
        if (this.f3436c != null) {
            String m1833e = this.f3436c.getParam().m1833e(SpeechConstant.ISE_AUDIO_PATH);
            if (!TextUtils.isEmpty(m1833e) && FileUtil.saveFile(((HandlerC3715b) this.f3436c).m2019c(), m1833e)) {
                FileUtil.formatPcm(this.f3436c.getParam().m1827b(SpeechConstant.AUDIO_FORMAT, (String) null), m1833e, this.f3436c.getParam().m1816a("sample_rate", this.f3436c.mSampleRate));
            }
        }
        FuncAdapter.UnLock(this.f3434a, Boolean.valueOf(this.f3008e), null);
    }

    /* renamed from: com.iflytek.cloud.thirdparty.ak$a */
    /* loaded from: classes3.dex */
    private final class a implements EvaluatorListener {

        /* renamed from: b */
        private EvaluatorListener f3010b;

        /* renamed from: c */
        private Handler f3011c = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.thirdparty.ak.a.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (a.this.f3010b == null) {
                    return;
                }
                switch (message.what) {
                    case 0:
                        a.this.f3010b.onError((SpeechError) message.obj);
                        break;
                    case 1:
                        a.this.f3010b.onVolumeChanged(message.arg1, (byte[]) message.obj);
                        break;
                    case 2:
                        a.this.f3010b.onBeginOfSpeech();
                        break;
                    case 3:
                        a.this.f3010b.onEndOfSpeech();
                        break;
                    case 4:
                        a.this.f3010b.onResult((EvaluatorResult) message.obj, message.arg1 == 1);
                        break;
                    case 6:
                        Message message2 = (Message) message.obj;
                        a.this.f3010b.onEvent(message2.what, message2.arg1, message2.arg2, (Bundle) message2.obj);
                        break;
                }
                super.handleMessage(message);
            }
        };

        public a(EvaluatorListener evaluatorListener) {
            this.f3010b = null;
            this.f3010b = evaluatorListener;
        }

        @Override // com.iflytek.cloud.EvaluatorListener
        public void onEndOfSpeech() {
            this.f3011c.sendMessage(this.f3011c.obtainMessage(3, 0, 0, null));
        }

        @Override // com.iflytek.cloud.EvaluatorListener
        public void onResult(EvaluatorResult evaluatorResult, boolean z) {
            if (z) {
                C3699ak.this.m1898f();
            }
            this.f3011c.sendMessage(this.f3011c.obtainMessage(4, !z ? 0 : 1, 0, evaluatorResult));
        }

        @Override // com.iflytek.cloud.EvaluatorListener
        public void onBeginOfSpeech() {
            DebugLog.LogD("onBeginOfSpeech");
            this.f3011c.sendMessage(this.f3011c.obtainMessage(2, 0, 0, null));
        }

        @Override // com.iflytek.cloud.EvaluatorListener
        public void onError(SpeechError speechError) {
            C3699ak.this.m1898f();
            this.f3011c.sendMessage(this.f3011c.obtainMessage(0, speechError));
        }

        @Override // com.iflytek.cloud.EvaluatorListener
        public void onVolumeChanged(int i, byte[] bArr) {
            this.f3011c.sendMessage(this.f3011c.obtainMessage(1, i, 0, bArr));
        }

        @Override // com.iflytek.cloud.EvaluatorListener
        public void onEvent(int i, int i2, int i3, Bundle bundle) {
            Message message = new Message();
            message.what = i;
            message.arg1 = i2;
            message.arg2 = i3;
            message.obj = bundle;
            Message.obtain(this.f3011c, 6, message).sendToTarget();
        }
    }

    /* renamed from: g */
    public boolean m1899g() {
        return mo1797d();
    }
}
