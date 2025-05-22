package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;

/* renamed from: com.iflytek.cloud.thirdparty.q */
/* loaded from: classes3.dex */
public class C3738q extends AbstractC3741t {

    /* renamed from: a */
    private MSCSessionInfo f3391a = new MSCSessionInfo();

    /* renamed from: b */
    private MSCSessionInfo f3392b = new MSCSessionInfo();

    /* renamed from: c */
    private byte[] f3393c = null;

    @Override // com.iflytek.cloud.thirdparty.AbstractC3741t
    public int sessionBegin(Context context, String str, AbstractHandlerC3740s abstractHandlerC3740s) throws SpeechError, UnsupportedEncodingException {
        String m1853d = C3694af.m1853d(context, str, abstractHandlerC3740s);
        DebugLog.LogS("sessionBegin Params:" + m1853d);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        PerfLogger.appendInfo(PerfLogger.MSC_SESSION_BIGNE, null);
        synchronized (C3738q.class) {
            this.mClientID = MSC.QMFVSessionBegin(m1853d.getBytes(abstractHandlerC3740s.getParamEncoding()), this.f3391a);
        }
        PerfLogger.appendInfo(PerfLogger.SESSION_BEGIN_END, null);
        DebugLog.LogD("sessionBegin ErrCode:" + this.f3391a.errorcode + " time:" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        int i = this.f3391a.errorcode;
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
        DebugLog.LogD("sessionEnd leavel:" + (MSC.QMFVSessionEnd(this.mClientID, str.getBytes()) == 0) + " time:" + (System.currentTimeMillis() - currentTimeMillis));
        this.mClientID = null;
        this.mSessionID = null;
    }

    /* renamed from: a */
    public synchronized void m2234a(StringBuffer stringBuffer, byte[] bArr, int i, boolean z) throws SpeechError {
        StringBuffer stringBuffer2 = new StringBuffer(stringBuffer.toString());
        stringBuffer2.append(",data_status=");
        if (z) {
            stringBuffer2.append(1);
        } else {
            stringBuffer2.append(2);
        }
        DebugLog.LogS("pushAudioData, param:" + stringBuffer2.toString());
        m2231a(stringBuffer2.toString(), bArr, i);
    }

    /* renamed from: a */
    public synchronized void m2233a(String str) throws SpeechError {
        PerfLogger.appendInfo(PerfLogger.LAST_DATA_FLAG, null);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ssub=" + str);
        stringBuffer.append(",data_status=4");
        DebugLog.LogD("mfv pushEndFlag, param:" + stringBuffer.toString());
        m2231a(stringBuffer.toString(), new byte[0], 0);
    }

    /* renamed from: a */
    private synchronized void m2231a(String str, byte[] bArr, int i) throws SpeechError {
        int QMFVDataWrite = MSC.QMFVDataWrite(this.mClientID, str.getBytes(), bArr, i, this.f3392b);
        this.f3391a.sesstatus = this.f3392b.sesstatus;
        DebugLog.LogI("QISRAudioWrite length:" + i);
        if (QMFVDataWrite != 0) {
            throw new SpeechError(this.f3392b.errorcode);
        }
    }

    /* renamed from: a */
    public synchronized int m2232a() {
        int i;
        int i2;
        i = 0;
        try {
            i2 = MSC.QMFVGetParam(this.mClientID, "volume".getBytes(), this.f3392b);
        } catch (Exception unused) {
            i2 = 0;
        }
        try {
            if (i2 == 0) {
                i = Integer.parseInt(new String(new String(this.f3392b.buffer)));
            } else {
                DebugLog.LogI("VAD CHECK FALSE");
            }
        } catch (Exception unused2) {
            DebugLog.LogI("getAudioVolume Exception vadret = " + i2);
            return i;
        }
        return i;
    }

    /* renamed from: b */
    public synchronized int m2236b(String str) {
        int i = 0;
        if (this.mClientID == null) {
            return 0;
        }
        try {
            String m2238c = m2238c(str);
            if (!TextUtils.isEmpty(m2238c)) {
                i = Integer.parseInt(new String(m2238c));
            }
        } catch (Exception e) {
            DebugLog.LogE(e);
        }
        return i;
    }

    /* renamed from: a */
    public synchronized boolean m2235a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || this.mClientID == null) {
            return false;
        }
        int i = -1;
        try {
            i = MSC.QMFVSetParam(this.mClientID, str.getBytes("utf-8"), str2.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            DebugLog.LogE(e);
        }
        return i == 0;
    }

    /* renamed from: c */
    public synchronized String m2238c(String str) {
        if (this.mClientID == null) {
            return null;
        }
        try {
            if (MSC.QMFVGetParam(this.mClientID, str.getBytes(), this.f3391a) == 0) {
                return new String(this.f3391a.buffer);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public String m2237b() {
        if (this.mSessionID == null) {
            this.mSessionID = m2238c("sid");
        }
        return this.mSessionID;
    }
}
