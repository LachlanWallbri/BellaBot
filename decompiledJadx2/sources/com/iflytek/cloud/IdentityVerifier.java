package com.iflytek.cloud;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.AbstractC3743v;
import com.iflytek.cloud.thirdparty.C3697ai;

/* loaded from: classes3.dex */
public final class IdentityVerifier extends AbstractC3743v {

    /* renamed from: a */
    private static IdentityVerifier f2770a;

    /* renamed from: b */
    private C3697ai f2771b;

    /* renamed from: c */
    private InitListener f2772c;

    /* renamed from: d */
    private Handler f2773d = new Handler(Looper.getMainLooper()) { // from class: com.iflytek.cloud.IdentityVerifier.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (IdentityVerifier.this.f2772c == null) {
                return;
            }
            IdentityVerifier.this.f2772c.onInit(message.what);
        }
    };

    public static synchronized IdentityVerifier createVerifier(Context context, InitListener initListener) {
        IdentityVerifier identityVerifier;
        synchronized (IdentityVerifier.class) {
            synchronized (sSync) {
                if (f2770a == null && SpeechUtility.getUtility() != null) {
                    f2770a = new IdentityVerifier(context, initListener);
                }
            }
            identityVerifier = f2770a;
        }
        return identityVerifier;
    }

    public static IdentityVerifier getVerifier() {
        return f2770a;
    }

    protected IdentityVerifier(Context context, InitListener initListener) {
        this.f2771b = null;
        this.f2772c = null;
        this.f2772c = initListener;
        this.f2771b = new C3697ai(context);
        if (initListener != null) {
            Message.obtain(this.f2773d, 0, 0, 0, null).sendToTarget();
        }
    }

    public int startWorking(IdentityListener identityListener) {
        C3697ai c3697ai = this.f2771b;
        if (c3697ai == null) {
            return 21001;
        }
        c3697ai.setParameter(this.mSessionParams);
        return this.f2771b.m1868a(identityListener);
    }

    public int writeData(String str, String str2, byte[] bArr, int i, int i2) {
        C3697ai c3697ai = this.f2771b;
        if (c3697ai != null && c3697ai.m1873e()) {
            StringBuilder sb = new StringBuilder();
            sb.append("mVerifierImpl is");
            sb.append(this.f2771b != null);
            sb.append("  mVerifierImpl.isWorking() is  ");
            sb.append(this.f2771b.m1873e());
            DebugLog.LogE(sb.toString());
            return this.f2771b.m1870a(str, str2, bArr, i, i2);
        }
        DebugLog.LogE("IdentityVerifier writeAudio failed, is not running");
        return 21004;
    }

    public void stopWrite(String str) {
        C3697ai c3697ai = this.f2771b;
        if (c3697ai != null && c3697ai.m1873e()) {
            this.f2771b.m1872c(str);
        } else {
            DebugLog.LogE("IdentityVerifier stopListening failed, is not running");
        }
    }

    public int execute(String str, String str2, String str3, IdentityListener identityListener) {
        C3697ai c3697ai = this.f2771b;
        if (c3697ai != null) {
            if (c3697ai.setParameter(this.mSessionParams)) {
                return this.f2771b.m1869a(str, str2, str3, identityListener);
            }
            return 20012;
        }
        DebugLog.LogE("IdentityVerifier execute failed, is not running");
        return 21001;
    }

    public boolean isWorking() {
        C3697ai c3697ai = this.f2771b;
        return c3697ai != null && c3697ai.m1873e();
    }

    public void cancel() {
        C3697ai c3697ai = this.f2771b;
        if (c3697ai != null && c3697ai.m1873e()) {
            this.f2771b.cancel(false);
        } else {
            DebugLog.LogE("IdentityVerifier cancel failed, is not running");
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
        C3697ai c3697ai = this.f2771b;
        boolean destroy = c3697ai != null ? c3697ai.destroy() : true;
        if (destroy && (destroy = super.destroy())) {
            synchronized (sSync) {
                f2770a = null;
            }
        }
        return destroy;
    }
}
