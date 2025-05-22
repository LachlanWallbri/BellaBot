package com.iflytek.cloud;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.AbstractC3743v;
import com.iflytek.cloud.thirdparty.C3696ah;

/* loaded from: classes3.dex */
public class FaceRequest extends AbstractC3743v {

    /* renamed from: a */
    private C3696ah f2763a;

    /* renamed from: b */
    private Context f2764b;

    public FaceRequest(Context context) {
        this.f2764b = context;
    }

    public int sendRequest(byte[] bArr, RequestListener requestListener) {
        int m1863a;
        synchronized (this) {
            if (this.f2763a != null) {
                this.f2763a.m1864a();
                this.f2763a = null;
            }
            this.f2763a = new C3696ah(this.f2764b, this.mSessionParams);
            m1863a = this.f2763a.m1863a(bArr, new C3659a(requestListener));
        }
        return m1863a;
    }

    public void cancel() {
        synchronized (this) {
            if (this.f2763a != null) {
                this.f2763a.m1864a();
                this.f2763a = null;
            }
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
        boolean destroy = this.f2763a.destroy();
        return destroy ? super.destroy() : destroy;
    }

    /* renamed from: com.iflytek.cloud.FaceRequest$a */
    /* loaded from: classes3.dex */
    protected class C3659a implements RequestListener {

        /* renamed from: b */
        private RequestListener f2766b;

        /* renamed from: c */
        private Handler f2767c = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.FaceRequest.a.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (C3659a.this.f2766b == null) {
                    return;
                }
                DebugLog.LogD("SpeechListener onMsg = " + message.what);
                int i = message.what;
                if (i == 0) {
                    C3659a.this.f2766b.onEvent(message.arg1, (Bundle) message.obj);
                } else if (i == 1) {
                    C3659a.this.f2766b.onBufferReceived((byte[]) message.obj);
                } else if (i == 2) {
                    C3659a.this.f2766b.onCompleted((SpeechError) message.obj);
                }
                super.handleMessage(message);
            }
        };

        public C3659a(RequestListener requestListener) {
            this.f2766b = null;
            this.f2766b = requestListener;
        }

        @Override // com.iflytek.cloud.RequestListener
        public void onEvent(int i, Bundle bundle) {
            this.f2767c.sendMessage(this.f2767c.obtainMessage(0, i, 0, bundle));
        }

        @Override // com.iflytek.cloud.RequestListener
        public void onCompleted(SpeechError speechError) {
            this.f2767c.sendMessage(this.f2767c.obtainMessage(2, speechError));
        }

        @Override // com.iflytek.cloud.RequestListener
        public void onBufferReceived(byte[] bArr) {
            this.f2767c.sendMessage(this.f2767c.obtainMessage(1, bArr));
        }
    }
}
