package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;
import com.iflytek.cloud.msc.util.Config;
import com.iflytek.cloud.msc.util.log.DebugLog;

/* renamed from: com.iflytek.cloud.thirdparty.w */
/* loaded from: classes3.dex */
public abstract class AbstractC3744w extends AbstractC3743v {

    /* renamed from: a */
    protected Context f3434a;

    /* renamed from: b */
    protected Object f3435b = new Object();

    /* renamed from: c */
    protected volatile AbstractHandlerC3740s f3436c = null;

    /* renamed from: d */
    protected volatile HandlerThread f3437d = null;

    /* renamed from: b */
    protected void m2260b() throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC3744w(Context context) {
        this.f3434a = null;
        if (context != null) {
            Config.getConfig(context.getApplicationContext());
            this.f3434a = context.getApplicationContext();
            try {
                m2260b();
                return;
            } catch (Exception e) {
                DebugLog.LogE(e);
                return;
            }
        }
        this.f3434a = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public HandlerThread m2259a(String str) throws Throwable {
        this.f3437d = new HandlerThread(str);
        this.f3437d.start();
        return this.f3437d;
    }

    /* renamed from: c */
    protected String m2261c() {
        return getClass().toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public boolean mo1797d() {
        return this.f3436c != null && this.f3436c.isRunning();
    }

    public void cancel(boolean z) {
        if (this.f3436c != null) {
            this.f3436c.cancel(z);
        }
    }

    public int getSampleRate() {
        return this.mSessionParams.m1816a("sample_rate", 16000);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean destroy() {
        boolean z;
        synchronized (this.f3435b) {
            z = false;
            if (mo1797d()) {
                this.f3436c.cancel(false);
            } else {
                z = mo1643a_();
                DebugLog.LogS(m2261c() + "destory =" + z);
            }
        }
        return z ? super.destroy() : z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a_ */
    public boolean mo1643a_() {
        if (this.f3437d == null || !this.f3437d.isAlive()) {
            return true;
        }
        HandlerThread handlerThread = this.f3437d;
        this.f3437d = null;
        handlerThread.interrupt();
        return true;
    }

    protected void finalize() throws Throwable {
        DebugLog.LogD(m2261c() + " finalize called");
        super.finalize();
    }

    /* renamed from: com.iflytek.cloud.thirdparty.w$a */
    /* loaded from: classes3.dex */
    protected class a implements SpeechListener {

        /* renamed from: b */
        private SpeechListener f3439b;

        /* renamed from: c */
        private Handler f3440c = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.thirdparty.w.a.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (a.this.f3439b == null) {
                    return;
                }
                DebugLog.LogD("SpeechListener onMsg = " + message.what);
                int i = message.what;
                if (i == 0) {
                    a.this.f3439b.onEvent(message.arg1, (Bundle) message.obj);
                } else if (i == 1) {
                    a.this.f3439b.onBufferReceived((byte[]) message.obj);
                } else if (i == 2) {
                    a.this.f3439b.onCompleted((SpeechError) message.obj);
                }
                super.handleMessage(message);
            }
        };

        public a(SpeechListener speechListener) {
            this.f3439b = null;
            this.f3439b = speechListener;
        }

        @Override // com.iflytek.cloud.SpeechListener
        public void onBufferReceived(byte[] bArr) {
            this.f3440c.sendMessage(this.f3440c.obtainMessage(1, bArr));
        }

        @Override // com.iflytek.cloud.SpeechListener
        public void onEvent(int i, Bundle bundle) {
            this.f3440c.sendMessage(this.f3440c.obtainMessage(0, i, 0, bundle));
        }

        @Override // com.iflytek.cloud.SpeechListener
        public void onCompleted(SpeechError speechError) {
            this.f3440c.sendMessage(this.f3440c.obtainMessage(2, speechError));
        }
    }
}
