package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;

/* renamed from: com.iflytek.cloud.thirdparty.f */
/* loaded from: classes3.dex */
public class HandlerC3727f extends AbstractHandlerC3740s {

    /* renamed from: a */
    private C3729h f3217a;

    /* renamed from: b */
    private SpeechListener f3218b;

    public HandlerC3727f(Context context, HandlerThread handlerThread) {
        super(context, handlerThread);
        this.f3217a = new C3729h();
    }

    /* renamed from: a */
    public void m2079a(C3692ad c3692ad, a aVar) {
        this.f3218b = aVar;
        setParams(c3692ad);
        Message obtain = Message.obtain();
        obtain.what = 13;
        sendMsg(obtain);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onMsgProcess(Message message) throws Throwable, SpeechError {
        if (message.what != 13) {
            return;
        }
        synchronized (HandlerC3727f.class) {
            String format = String.format("{'ret':%d,'cmd':%s}", Integer.valueOf(this.f3217a.m2098a(this.mContext, this)), getParam().m1833e("cmd"));
            if (this.f3218b != null) {
                this.f3218b.onBufferReceived(format.getBytes(getParamEncoding()));
            }
            sendMsg(21);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public void onEnd(SpeechError speechError) {
        super.onEnd(speechError);
        SpeechListener speechListener = this.f3218b;
        if (speechListener != null) {
            speechListener.onCompleted(speechError);
        }
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getSessionID() {
        return this.f3217a.m2106f();
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractHandlerC3740s
    public String getClientID() {
        return this.f3217a.getClientID();
    }

    /* renamed from: com.iflytek.cloud.thirdparty.f$a */
    /* loaded from: classes3.dex */
    public static final class a implements SpeechListener {

        /* renamed from: a */
        private SpeechListener f3219a;

        /* renamed from: b */
        private Handler f3220b = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.thirdparty.f.a.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (a.this.f3219a == null) {
                    return;
                }
                int i = message.what;
                if (i == 0) {
                    a.this.f3219a.onEvent(message.arg1, (Bundle) message.obj);
                } else if (i == 1) {
                    a.this.f3219a.onBufferReceived((byte[]) message.obj);
                } else {
                    if (i != 2) {
                        return;
                    }
                    a.this.f3219a.onCompleted((SpeechError) message.obj);
                }
            }
        };

        public a(SpeechListener speechListener) {
            this.f3219a = null;
            this.f3219a = speechListener;
        }

        @Override // com.iflytek.cloud.SpeechListener
        public void onEvent(int i, Bundle bundle) {
            this.f3220b.sendMessage(this.f3220b.obtainMessage(0, i, 0, bundle));
        }

        @Override // com.iflytek.cloud.SpeechListener
        public void onBufferReceived(byte[] bArr) {
            this.f3220b.sendMessage(this.f3220b.obtainMessage(1, bArr));
        }

        @Override // com.iflytek.cloud.SpeechListener
        public void onCompleted(SpeechError speechError) {
            this.f3220b.sendMessage(this.f3220b.obtainMessage(2, speechError));
        }
    }
}
