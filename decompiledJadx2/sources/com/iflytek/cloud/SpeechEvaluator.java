package com.iflytek.cloud;

import android.content.Context;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.AbstractC3743v;
import com.iflytek.cloud.thirdparty.C3699ak;

/* loaded from: classes3.dex */
public class SpeechEvaluator extends AbstractC3743v {

    /* renamed from: a */
    private static SpeechEvaluator f2785a;

    /* renamed from: b */
    private C3699ak f2786b;

    public static SpeechEvaluator createEvaluator(Context context, InitListener initListener) {
        synchronized (sSync) {
            if (f2785a == null && SpeechUtility.getUtility() != null) {
                f2785a = new SpeechEvaluator(context, null);
            }
        }
        return f2785a;
    }

    public static SpeechEvaluator getEvaluator() {
        return f2785a;
    }

    protected SpeechEvaluator(Context context, InitListener initListener) {
        this.f2786b = null;
        this.f2786b = new C3699ak(context);
    }

    public int startEvaluating(String str, String str2, EvaluatorListener evaluatorListener) {
        C3699ak c3699ak = this.f2786b;
        if (c3699ak == null) {
            return 21001;
        }
        c3699ak.setParameter(this.mSessionParams);
        return this.f2786b.m1894a(str, str2, evaluatorListener);
    }

    public int startEvaluating(byte[] bArr, String str, EvaluatorListener evaluatorListener) {
        C3699ak c3699ak = this.f2786b;
        if (c3699ak == null) {
            return 21001;
        }
        c3699ak.setParameter(this.mSessionParams);
        return this.f2786b.m1895a(bArr, str, evaluatorListener);
    }

    public boolean writeAudio(byte[] bArr, int i, int i2) {
        C3699ak c3699ak = this.f2786b;
        if (c3699ak != null && c3699ak.m1899g()) {
            return this.f2786b.m1896a(bArr, i, i2);
        }
        DebugLog.LogE("SpeechEvaluator writeAudio failed, is not running");
        return false;
    }

    public boolean isEvaluating() {
        C3699ak c3699ak = this.f2786b;
        return c3699ak != null && c3699ak.m1899g();
    }

    public void stopEvaluating() {
        C3699ak c3699ak = this.f2786b;
        if (c3699ak != null && c3699ak.m1899g()) {
            this.f2786b.m1897e();
        } else {
            DebugLog.LogE("SpeechEvaluator stopEvaluating failed, is not running");
        }
    }

    public void cancel() {
        C3699ak c3699ak = this.f2786b;
        if (c3699ak == null || !c3699ak.m1899g()) {
            return;
        }
        this.f2786b.cancel(false);
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
        C3699ak c3699ak = this.f2786b;
        boolean destroy = c3699ak != null ? c3699ak.destroy() : true;
        if (destroy && (destroy = super.destroy())) {
            synchronized (sSync) {
                f2785a = null;
            }
        }
        return destroy;
    }
}
