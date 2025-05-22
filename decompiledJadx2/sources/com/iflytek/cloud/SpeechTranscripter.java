package com.iflytek.cloud;

import android.content.Context;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.AbstractC3743v;
import com.iflytek.cloud.thirdparty.C3702an;
import com.iflytek.msc.MSC;

/* loaded from: classes3.dex */
public final class SpeechTranscripter extends AbstractC3743v {
    private static SpeechTranscripter sInstance;
    private C3702an mTranscripter;

    public static synchronized SpeechTranscripter createTranscripter(Context context, InitListener initListener) {
        SpeechTranscripter speechTranscripter;
        synchronized (SpeechTranscripter.class) {
            synchronized (sSync) {
                if (sInstance == null && SpeechUtility.getUtility() != null) {
                    sInstance = new SpeechTranscripter(context, initListener);
                }
            }
            if (initListener != null) {
                initListener.onInit(0);
            }
            speechTranscripter = sInstance;
        }
        return speechTranscripter;
    }

    public static SpeechTranscripter getTranscripter() {
        return sInstance;
    }

    protected SpeechTranscripter(Context context, InitListener initListener) {
        this.mTranscripter = null;
        if (MSC.isLoaded()) {
            this.mTranscripter = new C3702an(context);
        }
    }

    public synchronized int startTranscripting(TranscripterListener transcripterListener) {
        DebugLog.LogD("startTranscripting enter");
        if (this.mTranscripter == null) {
            return 21001;
        }
        if (isTranscripting()) {
            return 21005;
        }
        this.mTranscripter.setParameter("params", null);
        this.mTranscripter.setParameter("params", this.mSessionParams.toString());
        int m1925a = this.mTranscripter.m1925a(transcripterListener);
        clearOnceParameter();
        return m1925a;
    }

    private void clearOnceParameter() {
        setParameter("sid", null);
        setParameter(SpeechConstant.IST_AUDIO_UPLOADED, null);
        setParameter(SpeechConstant.IST_SYNC_ID, null);
    }

    public boolean writeAudio(byte[] bArr, int i, int i2) {
        C3702an c3702an = this.mTranscripter;
        if (c3702an != null && c3702an.m1929f()) {
            return this.mTranscripter.m1926a(bArr, i, i2);
        }
        DebugLog.LogE("SpeechTranscripter writeAudio failed, is not running");
        return false;
    }

    public void stopTranscripting() {
        DebugLog.LogD("stopTranscripting enter");
        C3702an c3702an = this.mTranscripter;
        if (c3702an != null && c3702an.m1929f()) {
            this.mTranscripter.m1928e();
        } else {
            DebugLog.LogE("SpeechTranscripter stopListening failed, is not running");
        }
    }

    public boolean isTranscripting() {
        C3702an c3702an = this.mTranscripter;
        return c3702an != null && c3702an.m1929f();
    }

    public void cancel() {
        DebugLog.LogD("cancel enter");
        C3702an c3702an = this.mTranscripter;
        if (c3702an != null && c3702an.m1929f()) {
            this.mTranscripter.cancel(false);
        } else {
            DebugLog.LogE("SpeechTranscripter cancel failed, is not running");
        }
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public String getParameter(String str) {
        if (this.mTranscripter != null) {
            if ("sid".equalsIgnoreCase(str)) {
                return this.mTranscripter.m1930g();
            }
            if (SpeechConstant.IST_AUDIO_UPLOADED.equalsIgnoreCase(str)) {
                return String.valueOf(this.mTranscripter.m1931h());
            }
            if (SpeechConstant.IST_SYNC_ID.equalsIgnoreCase(str)) {
                return this.mTranscripter.m1930g();
            }
            if (SpeechConstant.IST_AUDIO_PATH.equalsIgnoreCase(str)) {
                return this.mTranscripter.m1932i();
            }
        }
        return super.getParameter(str);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean destroy() {
        C3702an c3702an = this.mTranscripter;
        boolean destroy = c3702an != null ? c3702an.destroy() : true;
        if (destroy && (destroy = super.destroy())) {
            synchronized (sSync) {
                sInstance = null;
            }
        }
        return destroy;
    }
}
