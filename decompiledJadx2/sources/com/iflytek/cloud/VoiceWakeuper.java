package com.iflytek.cloud;

import android.content.Context;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.AbstractC3734m;
import com.iflytek.cloud.thirdparty.AbstractC3743v;
import com.iflytek.cloud.thirdparty.C3705aq;
import com.iflytek.cloud.util.FileDownloadListener;
import com.iflytek.cloud.util.ResourceUtil;

/* loaded from: classes3.dex */
public class VoiceWakeuper extends AbstractC3743v {

    /* renamed from: a */
    private static VoiceWakeuper f2837a;

    /* renamed from: b */
    private C3705aq f2838b;

    public static synchronized VoiceWakeuper createWakeuper(Context context, InitListener initListener) {
        VoiceWakeuper voiceWakeuper;
        synchronized (VoiceWakeuper.class) {
            synchronized (sSync) {
                if (f2837a == null && SpeechUtility.getUtility() != null) {
                    f2837a = new VoiceWakeuper(context, initListener);
                }
            }
            voiceWakeuper = f2837a;
        }
        return voiceWakeuper;
    }

    public static VoiceWakeuper getWakeuper() {
        return f2837a;
    }

    private VoiceWakeuper(Context context, InitListener initListener) {
        this.f2838b = null;
        this.f2838b = new C3705aq(context);
    }

    public int queryResource(String str, RequestListener requestListener) {
        return this.f2838b.m1962a(str, true, requestListener);
    }

    public int downloadResource(String str, String str2, String str3, FileDownloadListener fileDownloadListener) {
        return this.f2838b.m1961a(str, str2, str3, true, fileDownloadListener);
    }

    public int startListening(WakeuperListener wakeuperListener) {
        this.f2838b.setParameter("params", null);
        this.f2838b.setParameter(this.mSessionParams);
        return this.f2838b.m1960a(wakeuperListener);
    }

    public int writeAudio(byte[] bArr, int i, int i2) {
        C3705aq c3705aq = this.f2838b;
        if (c3705aq != null && c3705aq.m1965f()) {
            return this.f2838b.m1963a(bArr, i, i2);
        }
        DebugLog.LogE("VoiceWakeup writeAudio failed, is not running");
        return 21004;
    }

    public void stopListening() {
        this.f2838b.m1964e();
    }

    public boolean isListening() {
        return this.f2838b.m1965f();
    }

    public void cancel() {
        this.f2838b.cancel(false);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean setParameter(String str, String str2) {
        if (str != null && str.startsWith("aimic_on_")) {
            AbstractC3734m m2146a = AbstractC3734m.m2146a();
            if (m2146a == null) {
                return false;
            }
            if (!SpeechConstant.IVW_RESET_AIMIC.equals(str)) {
                return m2146a.mo2150a(str.substring(9), str2) == 0;
            }
            m2146a.mo2159c();
            return false;
        }
        return super.setParameter(str, str2);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public String getParameter(String str) {
        if (str != null && str.startsWith("aimic_on_")) {
            AbstractC3734m m2146a = AbstractC3734m.m2146a();
            if ("aimic_on_channel".equals(str)) {
                return Integer.toString(AbstractC3734m.m2149g());
            }
            if ("aimic_on_version".equals(str)) {
                return AbstractC3734m.m2148f();
            }
            if (m2146a != null) {
                return m2146a.mo2155b(str.substring(9));
            }
            DebugLog.LogE("aimic is null !");
            return null;
        }
        return super.getParameter(str);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean destroy() {
        C3705aq c3705aq = this.f2838b;
        boolean destroy = c3705aq != null ? c3705aq.destroy() : true;
        AbstractC3734m m2146a = AbstractC3734m.m2146a();
        if (m2146a != null) {
            m2146a.mo2156b();
        }
        if (destroy && (destroy = super.destroy())) {
            synchronized (sSync) {
                f2837a = null;
            }
            SpeechUtility utility = SpeechUtility.getUtility();
            if (utility != null) {
                DebugLog.LogD("Destory ivw engine.");
                utility.setParameter(ResourceUtil.ENGINE_DESTROY, "engine_destroy=ivw");
            }
        }
        return destroy;
    }
}
