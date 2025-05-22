package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import androidx.core.os.EnvironmentCompat;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;

/* renamed from: com.iflytek.cloud.thirdparty.ac */
/* loaded from: classes3.dex */
public class C3691ac extends AbstractC3741t {

    /* renamed from: a */
    private MSCSessionInfo f2974a = new MSCSessionInfo();

    @Override // com.iflytek.cloud.thirdparty.AbstractC3741t
    public int sessionBegin(Context context, String str, AbstractHandlerC3740s abstractHandlerC3740s) throws SpeechError, UnsupportedEncodingException {
        this.mClientID = null;
        String m1852d = C3694af.m1852d(context, abstractHandlerC3740s);
        DebugLog.LogD("QTTSSessionBegin enter");
        long currentTimeMillis = System.currentTimeMillis();
        byte[] bytes = m1852d.getBytes(abstractHandlerC3740s.getParamEncoding());
        PerfLogger.appendInfo(PerfLogger.MSC_SESSION_BIGNE, null);
        synchronized (C3691ac.class) {
            this.mClientID = MSC.QTTSSessionBegin(bytes, this.f2974a);
        }
        PerfLogger.appendInfo(PerfLogger.SESSION_BEGIN_END, null);
        DebugLog.LogD("QTTSSessionBegin leave:" + (System.currentTimeMillis() - currentTimeMillis) + " ErrorCode:" + this.f2974a.errorcode);
        int i = this.f2974a.errorcode;
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
        DebugLog.LogD("QTTSSessionEnd enter");
        DebugLog.LogD("QTTSSessionEnd leavel:" + MSC.QTTSSessionEnd(this.mClientID, str.getBytes()));
        this.mClientID = null;
        this.mSessionID = null;
    }

    /* renamed from: a */
    public synchronized void m1805a(byte[] bArr) throws SpeechError {
        DebugLog.LogD("QTTSTextPut enter");
        PerfLogger.appendInfo(PerfLogger.LAST_DATA_FLAG, null);
        int QTTSTextPut = MSC.QTTSTextPut(this.mClientID, bArr);
        DebugLog.LogD("QTTSTextPut leavel:" + QTTSTextPut);
        if (QTTSTextPut != 0) {
            throw new SpeechError(QTTSTextPut);
        }
    }

    /* renamed from: a */
    public synchronized byte[] m1806a() throws SpeechError {
        byte[] QTTSAudioGet;
        if (this.mClientID == null) {
            throw new SpeechError(20003);
        }
        DebugLog.LogI("QTTSAudioGet enter");
        QTTSAudioGet = MSC.QTTSAudioGet(this.mClientID, this.f2974a);
        StringBuilder sb = new StringBuilder();
        sb.append("QTTSAudioGet leave:");
        sb.append(this.f2974a.errorcode);
        sb.append("value len = ");
        sb.append(QTTSAudioGet == null ? 0 : QTTSAudioGet.length);
        DebugLog.LogI(sb.toString());
        int i = this.f2974a.errorcode;
        if (i != 0) {
            throw new SpeechError(i);
        }
        return QTTSAudioGet;
    }

    /* renamed from: b */
    public int m1807b() {
        try {
            return Integer.parseInt(m1808b("ced"));
        } catch (Exception unused) {
            return 0;
        }
    }

    /* renamed from: c */
    public String m1809c() {
        try {
            char[] QTTSAudioInfo = MSC.QTTSAudioInfo(this.mClientID);
            return (QTTSAudioInfo == null || QTTSAudioInfo.length <= 0) ? "" : new String(QTTSAudioInfo);
        } catch (Exception e) {
            DebugLog.LogE(e);
            return "";
        }
    }

    /* renamed from: a */
    public synchronized int m1804a(String str) {
        int i = 0;
        if (this.mClientID == null) {
            return 0;
        }
        try {
            String m1808b = m1808b(str);
            if (!TextUtils.isEmpty(m1808b)) {
                i = Integer.parseInt(new String(m1808b));
            }
        } catch (Exception unused) {
        }
        return i;
    }

    /* renamed from: b */
    public synchronized String m1808b(String str) {
        if (this.mClientID == null) {
            return null;
        }
        try {
            if (MSC.QTTSGetParam(this.mClientID, str.getBytes(), this.f2974a) == 0) {
                return new String(this.f2974a.buffer);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* renamed from: d */
    public synchronized boolean m1810d() {
        return 2 == this.f2974a.sesstatus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: e */
    public String m1811e() {
        if (this.mSessionID == null) {
            this.mSessionID = m1808b("sid");
        }
        return this.mSessionID;
    }
}
