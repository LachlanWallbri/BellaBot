package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.SystemClock;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import com.iflytek.cloud.thirdparty.AbstractC3741t;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/* renamed from: com.iflytek.cloud.thirdparty.h */
/* loaded from: classes3.dex */
public class C3729h extends AbstractC3741t {

    /* renamed from: a */
    private MSCSessionInfo f3237a = new MSCSessionInfo();

    /* renamed from: b */
    private MSCSessionInfo f3238b = new MSCSessionInfo();

    /* renamed from: c */
    private MSCSessionInfo f3239c = new MSCSessionInfo();

    /* renamed from: d */
    private byte[] f3240d = null;

    @Override // com.iflytek.cloud.thirdparty.AbstractC3741t
    public int sessionBegin(Context context, String str, AbstractHandlerC3740s abstractHandlerC3740s) throws SpeechError, UnsupportedEncodingException {
        String m1844b = C3694af.m1844b(context, abstractHandlerC3740s);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        PerfLogger.appendInfo(PerfLogger.MSC_SESSION_BIGNE, null);
        DebugLog.LogD("QISVSessionBegin begin");
        this.mClientID = MSC.QISVSessionBegin(m1844b.getBytes(abstractHandlerC3740s.getParamEncoding()), str == null ? null : str.getBytes(abstractHandlerC3740s.getParamEncoding()), this.f3237a);
        PerfLogger.appendInfo(PerfLogger.SESSION_BEGIN_END, null);
        DebugLog.LogD("QISVSessionBegin ret: " + this.f3237a.errorcode + " time:" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        int i = this.f3237a.errorcode;
        if (i == 0 || i == 10129 || i == 10113 || i == 10132) {
            return 0;
        }
        throw new SpeechError(i);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3741t
    public void sessionEnd(String str) {
        if (this.mClientID == null) {
            return;
        }
        DebugLog.LogD("isv sessionEnd enter ");
        long currentTimeMillis = System.currentTimeMillis();
        DebugLog.LogD("isv sessionEnd leave:" + (MSC.QISVSessionEnd(this.mClientID, str.getBytes()) == 0) + " time:" + (System.currentTimeMillis() - currentTimeMillis));
        this.mClientID = null;
        this.mSessionID = null;
    }

    /* renamed from: a */
    public synchronized void m2101a(byte[] bArr, int i) throws SpeechError {
        m2097a(bArr, i, 2);
    }

    /* renamed from: a */
    public synchronized void m2100a() throws SpeechError {
        PerfLogger.appendInfo(PerfLogger.LAST_DATA_FLAG, null);
        DebugLog.LogD("IsvSession pushEndFlag");
        m2097a(new byte[0], 0, 4);
    }

    /* renamed from: a */
    private synchronized void m2097a(byte[] bArr, int i, int i2) throws SpeechError {
        int QISVAudioWrite = MSC.QISVAudioWrite(this.mClientID, null, bArr, i, i2, this.f3237a);
        DebugLog.LogI("QISVAudioWrite error:" + QISVAudioWrite);
        if (QISVAudioWrite != 0) {
            throw new SpeechError(QISVAudioWrite);
        }
    }

    /* renamed from: b */
    public synchronized boolean m2102b() {
        return this.f3237a.epstatues >= 3;
    }

    /* renamed from: c */
    public synchronized int m2103c() {
        int i;
        int i2;
        i = 0;
        try {
            i2 = MSC.QISVGetParam(this.mClientID, "volume".getBytes(), this.f3238b);
        } catch (Exception unused) {
            i2 = 0;
        }
        try {
            if (i2 == 0) {
                i = Integer.parseInt(new String(new String(this.f3238b.buffer)));
            } else {
                DebugLog.LogI("VAD CHECK FALSE");
            }
        } catch (Exception unused2) {
            DebugLog.LogI("getAudioVolume Exception vadret = " + i2);
            return i;
        }
        return i;
    }

    /* renamed from: d */
    public byte[] m2104d() {
        return this.f3240d;
    }

    /* renamed from: a */
    public synchronized String m2099a(String str) {
        if (this.mClientID == null) {
            return null;
        }
        try {
            if (MSC.QISVGetParam(this.mClientID, str.getBytes(), this.f3237a) == 0) {
                return new String(this.f3237a.buffer);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0051, code lost:
    
        if (r0 != 5) goto L20;
     */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public AbstractC3741t.a m2105e() throws SpeechError {
        Date date = new Date();
        this.f3240d = MSC.QISVGetResult(this.mClientID, null, this.f3237a);
        Date date2 = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("QISVGetResult leavel:");
        sb.append(this.f3240d != null);
        sb.append(" time:");
        sb.append(date2.getTime() - date.getTime());
        DebugLog.LogI(sb.toString());
        int i = this.f3237a.errorcode;
        if (i == 0) {
            int i2 = this.f3237a.rsltstatus;
            if (i2 != 0) {
                if (i2 == 1) {
                    DebugLog.LogD("ResultStatus: noResult" + i2);
                    throw new SpeechError(20005);
                }
            }
            if (this.f3240d != null) {
                DebugLog.LogD("ResultStatus: hasResult" + i2);
                return AbstractC3741t.a.hasResult;
            }
            return AbstractC3741t.a.noResult;
        }
        DebugLog.LogE("Result: error errorcode is " + i);
        throw new SpeechError(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: f */
    public String m2106f() {
        if (this.mSessionID == null) {
            this.mSessionID = m2099a("sid");
        }
        return this.mSessionID;
    }

    /* renamed from: a */
    public int m2098a(Context context, AbstractHandlerC3740s abstractHandlerC3740s) throws UnsupportedEncodingException, SpeechError {
        int i;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        String m1833e = abstractHandlerC3740s.getParam().m1833e(SpeechConstant.ISV_VID);
        String m1844b = C3694af.m1844b(context, abstractHandlerC3740s);
        DebugLog.LogD("sendRequest enter ");
        PerfLogger.appendInfo(PerfLogger.LAST_DATA_FLAG, null);
        char[] QISVQueDelModel = MSC.QISVQueDelModel(m1833e == null ? null : m1833e.getBytes(abstractHandlerC3740s.getParamEncoding()), m1844b.getBytes(abstractHandlerC3740s.getParamEncoding()), this.f3239c);
        PerfLogger.appendInfo(PerfLogger.GET_RESULT, null);
        MSC.QISVQueDelModelRelease(QISVQueDelModel);
        if (this.f3239c.errorcode != 0) {
            i = this.f3239c.errorcode;
        } else {
            i = "true".equals(new String(this.f3239c.buffer)) ? 0 : -1;
        }
        DebugLog.LogD("sendRequest leave:" + i + " time:" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        if (i == 0 || -1 == i) {
            return i;
        }
        throw new SpeechError(i);
    }
}
