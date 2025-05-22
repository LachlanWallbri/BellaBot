package com.iflytek.cloud;

import android.content.Context;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.AbstractC3743v;
import com.iflytek.cloud.thirdparty.C3698aj;

/* loaded from: classes3.dex */
public class SpeakerVerifier extends AbstractC3743v {

    /* renamed from: a */
    private static SpeakerVerifier f2781a;

    /* renamed from: b */
    private C3698aj f2782b;

    public static SpeakerVerifier createVerifier(Context context, InitListener initListener) {
        synchronized (sSync) {
            if (f2781a == null && SpeechUtility.getUtility() != null) {
                f2781a = new SpeakerVerifier(context, initListener);
            }
        }
        return f2781a;
    }

    public static SpeakerVerifier getVerifier() {
        return f2781a;
    }

    protected SpeakerVerifier(Context context, InitListener initListener) {
        this.f2782b = null;
        this.f2782b = new C3698aj(context);
    }

    public void getPasswordList(SpeechListener speechListener) {
        C3698aj c3698aj = this.f2782b;
        if (c3698aj != null) {
            c3698aj.setParameter("params", null);
            this.mSessionParams.m1823a(SpeechConstant.SUBJECT, SpeechConstant.ENG_IVP, true);
            this.mSessionParams.m1823a("rse", "gb2312", false);
            this.f2782b.setParameter(this.mSessionParams);
            this.f2782b.m1889a(speechListener);
            return;
        }
        DebugLog.LogE("SpeakerVerifier getPasswordList failed, is not running");
    }

    public String generatePassword(int i) {
        C3698aj c3698aj = this.f2782b;
        if (c3698aj != null) {
            return c3698aj.m1888a(i);
        }
        DebugLog.LogE("SpeakerVerifier getPasswordList failed, is not running");
        return null;
    }

    public int startListening(VerifierListener verifierListener) {
        C3698aj c3698aj = this.f2782b;
        if (c3698aj == null) {
            return 21001;
        }
        c3698aj.setParameter(this.mSessionParams);
        return this.f2782b.m1885a(verifierListener);
    }

    public void stopListening() {
        C3698aj c3698aj = this.f2782b;
        if (c3698aj != null && c3698aj.m1891f()) {
            this.f2782b.m1890e();
        } else {
            DebugLog.LogE("SpeakerVerifier stopListening failed, is not running");
        }
    }

    public int writeAudio(byte[] bArr, int i, int i2) {
        C3698aj c3698aj = this.f2782b;
        if (c3698aj != null && c3698aj.m1891f()) {
            return this.f2782b.m1887a(bArr, i, i2);
        }
        DebugLog.LogE("SpeakerVerifier writeAudio failed, is not running");
        return 21004;
    }

    public boolean isListening() {
        C3698aj c3698aj = this.f2782b;
        return c3698aj != null && c3698aj.m1891f();
    }

    public void cancel() {
        C3698aj c3698aj = this.f2782b;
        if (c3698aj == null || !c3698aj.m1891f()) {
            return;
        }
        this.f2782b.cancel(false);
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
        C3698aj c3698aj = this.f2782b;
        boolean destroy = c3698aj != null ? c3698aj.destroy() : true;
        if (destroy && (destroy = super.destroy())) {
            synchronized (sSync) {
                f2781a = null;
            }
        }
        return destroy;
    }

    public int sendRequest(String str, String str2, SpeechListener speechListener) {
        if (this.f2782b.setParameter(this.mSessionParams)) {
            return this.f2782b.m1886a(str, str2, speechListener);
        }
        return 20012;
    }
}
