package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.os.EnvironmentCompat;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import com.iflytek.cloud.thirdparty.AbstractC3741t;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/* renamed from: com.iflytek.cloud.thirdparty.a */
/* loaded from: classes3.dex */
public class C3688a extends AbstractC3741t {

    /* renamed from: a */
    private MSCSessionInfo f2948a = new MSCSessionInfo();

    /* renamed from: b */
    private MSCSessionInfo f2949b = new MSCSessionInfo();

    /* renamed from: c */
    private byte[] f2950c = null;

    @Override // com.iflytek.cloud.thirdparty.AbstractC3741t
    public int sessionBegin(Context context, String str, AbstractHandlerC3740s abstractHandlerC3740s) throws SpeechError, UnsupportedEncodingException {
        this.mClientID = null;
        String m1855e = C3694af.m1855e(context, abstractHandlerC3740s);
        PerfLogger.appendInfo(PerfLogger.MSC_SESSION_BIGNE, null);
        DebugLog.LogD("QISESessionBegin enter");
        if (TextUtils.isEmpty(str)) {
            this.mClientID = MSC.QISESessionBegin(m1855e.getBytes(abstractHandlerC3740s.getParamEncoding()), null, this.f2948a);
        } else {
            this.mClientID = MSC.QISESessionBegin(m1855e.getBytes(abstractHandlerC3740s.getParamEncoding()), str.getBytes(abstractHandlerC3740s.getParamEncoding()), this.f2948a);
            DebugLog.LogD("sessionBegin userModelId:" + str);
        }
        DebugLog.LogD("QISESessionBegin leave: " + this.f2948a.errorcode);
        PerfLogger.appendInfo(PerfLogger.SESSION_BEGIN_END, null);
        int i = this.f2948a.errorcode;
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
        if (TextUtils.isEmpty(str)) {
            str = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        DebugLog.LogD("ISESessionEnd enter ");
        long currentTimeMillis = System.currentTimeMillis();
        DebugLog.LogD("ISESessionEnd leave: " + MSC.QISESessionEnd(this.mClientID, str.getBytes()) + " time:" + (System.currentTimeMillis() - currentTimeMillis));
        this.mClientID = null;
        this.mSessionID = null;
    }

    /* renamed from: a */
    public synchronized void m1763a(byte[] bArr, byte[] bArr2) throws SpeechError {
        DebugLog.LogD("QISETextPut enter");
        int QISETextPut = MSC.QISETextPut(this.mClientID, bArr, bArr2);
        DebugLog.LogD("QISETextPut leave: " + QISETextPut);
        if (QISETextPut != 0) {
            throw new SpeechError(QISETextPut);
        }
    }

    /* renamed from: a */
    public synchronized void m1762a(byte[] bArr, int i) throws SpeechError {
        m1759a(bArr, i, 2);
    }

    /* renamed from: a */
    public synchronized void m1761a() throws SpeechError {
        PerfLogger.appendInfo(PerfLogger.LAST_DATA_FLAG, null);
        DebugLog.LogD("IseSession pushEndFlag");
        m1759a(new byte[0], 0, 4);
    }

    /* renamed from: a */
    private synchronized void m1759a(byte[] bArr, int i, int i2) throws SpeechError {
        DebugLog.LogI("QISEAudioWrite enter, length: " + i);
        int QISEAudioWrite = MSC.QISEAudioWrite(this.mClientID, bArr, i, i2, this.f2949b);
        DebugLog.LogI("QISEAudioWrite leave: " + QISEAudioWrite);
        this.f2948a.sesstatus = this.f2949b.sesstatus;
        if (QISEAudioWrite != 0) {
            throw new SpeechError(this.f2949b.errorcode);
        }
    }

    /* renamed from: b */
    public synchronized int m1764b() {
        return this.f2949b.epstatues;
    }

    /* renamed from: c */
    public synchronized int m1765c() {
        int i;
        int i2 = 0;
        if (this.mClientID == null) {
            return 0;
        }
        try {
            i = MSC.QISEGetParam(this.mClientID, "volume".getBytes(), this.f2949b);
            try {
                if (i == 0) {
                    i = Integer.parseInt(new String(new String(this.f2949b.buffer)));
                    i2 = i;
                } else {
                    DebugLog.LogI("VAD CHECK FALSE");
                }
            } catch (Throwable unused) {
                DebugLog.LogI("getAudioVolume Exception vadret = " + i);
                return i2;
            }
        } catch (Throwable unused2) {
            i = 0;
        }
        return i2;
    }

    /* renamed from: a */
    public synchronized String m1760a(String str) {
        if (this.mClientID == null) {
            return null;
        }
        try {
            if (MSC.QISEGetParam(this.mClientID, str.getBytes(), this.f2948a) == 0) {
                return new String(this.f2948a.buffer);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* renamed from: d */
    public byte[] m1766d() {
        return this.f2950c;
    }

    /* renamed from: e */
    public AbstractC3741t.a m1767e() throws SpeechError {
        Date date = new Date();
        this.f2950c = MSC.QISEGetResult(this.mClientID, this.f2948a);
        Date date2 = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append("QISRGetResult leave: ");
        sb.append(this.f2950c != null);
        sb.append(" time:");
        sb.append(date2.getTime() - date.getTime());
        DebugLog.LogI(sb.toString());
        int i = this.f2948a.errorcode;
        if (i == 0) {
            int i2 = this.f2948a.rsltstatus;
            if (i2 == 0) {
                DebugLog.LogD("ResultStatus: hasResult" + i2);
                return AbstractC3741t.a.hasResult;
            }
            if (i2 == 2) {
                DebugLog.LogI("ResultStatus: noResult" + i2);
                return AbstractC3741t.a.noResult;
            }
            if (i2 == 5) {
                DebugLog.LogD("ResultStatus: resultOver" + i2);
                return AbstractC3741t.a.resultOver;
            }
            DebugLog.LogD("IseSession getResult get unmatched result status: " + i2);
            return AbstractC3741t.a.noResult;
        }
        DebugLog.LogE("Result: error " + i);
        throw new SpeechError(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: f */
    public String m1768f() {
        if (this.mSessionID == null) {
            this.mSessionID = m1760a("sid");
        }
        return this.mSessionID;
    }
}
