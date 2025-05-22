package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;

/* renamed from: com.iflytek.cloud.thirdparty.j */
/* loaded from: classes3.dex */
public class C3731j extends AbstractC3741t {

    /* renamed from: a */
    private MSCSessionInfo f3253a = new MSCSessionInfo();

    /* renamed from: b */
    private MSCSessionInfo f3254b = new MSCSessionInfo();

    @Override // com.iflytek.cloud.thirdparty.AbstractC3741t
    public int sessionBegin(Context context, String str, AbstractHandlerC3740s abstractHandlerC3740s) throws SpeechError, UnsupportedEncodingException {
        String m1845b = C3694af.m1845b(context, str, abstractHandlerC3740s);
        String m1833e = abstractHandlerC3740s.getParam().m1833e(SpeechConstant.CLOUD_GRAMMAR);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        DebugLog.LogD("ivw sessionbegin begin");
        PerfLogger.appendInfo(PerfLogger.MSC_SESSION_BIGNE, null);
        if (TextUtils.isEmpty(m1833e)) {
            this.mClientID = MSC.QIVWSessionBegin(null, m1845b.getBytes(abstractHandlerC3740s.getParamEncoding()), this.f3253a);
        } else {
            this.mClientID = MSC.QIVWSessionBegin(m1833e.getBytes(abstractHandlerC3740s.getParamEncoding()), m1845b.getBytes(abstractHandlerC3740s.getParamEncoding()), this.f3253a);
        }
        PerfLogger.appendInfo(PerfLogger.SESSION_BEGIN_END, null);
        DebugLog.LogD("ivw sessionBegin ErrCode:" + this.f3253a.errorcode + " time:" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        int i = this.f3253a.errorcode;
        if (i == 0 || i == 10129 || i == 10113 || i == 10132) {
            return i;
        }
        throw new SpeechError(i);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3741t
    public void sessionEnd(String str) {
        if (this.mClientID == null) {
            return;
        }
        DebugLog.LogD("sessionEnd enter ");
        long currentTimeMillis = System.currentTimeMillis();
        DebugLog.LogD("sessionEnd leave: " + (MSC.QIVWSessionEnd(this.mClientID, str.getBytes()) == 0) + " time:" + (System.currentTimeMillis() - currentTimeMillis));
        this.mClientID = null;
        this.mSessionID = null;
    }

    /* renamed from: a */
    public synchronized void m2123a(byte[] bArr, int i) throws SpeechError {
        m2121a(bArr, i, 2);
    }

    /* renamed from: a */
    private synchronized void m2121a(byte[] bArr, int i, int i2) throws SpeechError {
        int QIVWAudioWrite = MSC.QIVWAudioWrite(this.mClientID, bArr, i, i2, this.f3254b);
        this.f3253a.sesstatus = this.f3254b.sesstatus;
        DebugLog.LogI("QIVWAudioWrite length:" + i);
        if (QIVWAudioWrite != 0) {
            throw new SpeechError(this.f3254b.errorcode);
        }
    }

    /* renamed from: a */
    public synchronized void m2122a() throws SpeechError {
        PerfLogger.appendInfo(PerfLogger.LAST_DATA_FLAG, null);
        DebugLog.LogD("ivw session pushEndFlag");
        m2121a(new byte[0], 0, 4);
    }
}
