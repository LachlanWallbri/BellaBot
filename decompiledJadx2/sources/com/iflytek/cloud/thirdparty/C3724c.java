package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.GrammarListener;
import com.iflytek.cloud.LexiconListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;

/* renamed from: com.iflytek.cloud.thirdparty.c */
/* loaded from: classes3.dex */
public class C3724c extends AbstractC3741t {

    /* renamed from: d */
    private static GrammarListener f3187d;

    /* renamed from: e */
    private static LexiconListener f3188e;

    /* renamed from: a */
    private MSCSessionInfo f3189a = new MSCSessionInfo();

    /* renamed from: b */
    private MSCSessionInfo f3190b = new MSCSessionInfo();

    /* renamed from: c */
    private byte[] f3191c = null;

    /* renamed from: f */
    private String f3192f = "";

    @Override // com.iflytek.cloud.thirdparty.AbstractC3741t
    public int sessionBegin(Context context, String str, AbstractHandlerC3740s abstractHandlerC3740s) throws SpeechError, UnsupportedEncodingException {
        String m1839a = C3694af.m1839a(context, str, abstractHandlerC3740s);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        DebugLog.LogD("QISRSessionBegin begin, grammar: " + str);
        synchronized (C3724c.class) {
            PerfLogger.appendInfo(PerfLogger.MSC_SESSION_BIGNE, null);
            if (TextUtils.isEmpty(str)) {
                this.mClientID = MSC.QISRSessionBegin(null, m1839a.getBytes(abstractHandlerC3740s.getParamEncoding()), this.f3189a);
            } else {
                this.mClientID = MSC.QISRSessionBegin(str.getBytes(abstractHandlerC3740s.getParamEncoding()), m1839a.getBytes(abstractHandlerC3740s.getParamEncoding()), this.f3189a);
            }
            PerfLogger.appendInfo(PerfLogger.SESSION_BEGIN_END, null);
        }
        DebugLog.LogD("QISRSessionBegin end: " + this.f3189a.errorcode + " time:" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        int i = this.f3189a.errorcode;
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
        DebugLog.LogD("sessionEnd leavel:" + (MSC.QISRSessionEnd(this.mClientID, str.getBytes()) == 0) + " time:" + (System.currentTimeMillis() - currentTimeMillis));
        this.mClientID = null;
        this.mSessionID = null;
    }

    /* renamed from: a */
    public synchronized void m2054a(byte[] bArr, int i) throws SpeechError {
        m2049a(bArr, i, 2);
    }

    /* renamed from: a */
    public synchronized void m2053a() throws SpeechError {
        PerfLogger.appendInfo(PerfLogger.LAST_DATA_FLAG, null);
        DebugLog.LogD("IsrSession pushEndFlag");
        m2049a(new byte[0], 0, 4);
    }

    /* renamed from: a */
    private synchronized void m2049a(byte[] bArr, int i, int i2) throws SpeechError {
        int QISRAudioWrite = MSC.QISRAudioWrite(this.mClientID, bArr, i, i2, this.f3190b);
        this.f3189a.sesstatus = this.f3190b.sesstatus;
        DebugLog.LogI("QISRAudioWrite length:" + i);
        if (QISRAudioWrite != 0) {
            throw new SpeechError(this.f3190b.errorcode);
        }
    }

    /* renamed from: b */
    public synchronized int m2056b() {
        int i;
        int i2;
        i = 0;
        try {
            i2 = MSC.QISRGetParam(this.mClientID, "volume".getBytes(), this.f3190b);
        } catch (Exception unused) {
            i2 = 0;
        }
        try {
            if (i2 == 0) {
                i = Integer.parseInt(new String(new String(this.f3190b.buffer)));
            } else {
                DebugLog.LogI("VAD CHECK FALSE");
            }
        } catch (Exception unused2) {
            DebugLog.LogI("getAudioVolume Exception vadret = " + i2);
            return i;
        }
        return i;
    }

    /* renamed from: a */
    public synchronized int m2050a(String str) {
        int i = 0;
        if (this.mClientID == null) {
            return 0;
        }
        try {
            String m2057b = m2057b(str);
            if (!TextUtils.isEmpty(m2057b)) {
                i = Integer.parseInt(new String(m2057b));
            }
        } catch (Exception e) {
            DebugLog.LogE(e);
        }
        return i;
    }

    /* renamed from: a */
    public synchronized boolean m2055a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || this.mClientID == null) {
            return false;
        }
        int i = -1;
        try {
            i = MSC.QISRSetParam(this.mClientID, str.getBytes("utf-8"), str2.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            DebugLog.LogE(e);
        }
        return i == 0;
    }

    /* renamed from: b */
    public synchronized String m2057b(String str) {
        if (this.mClientID == null) {
            return null;
        }
        try {
            if (MSC.QISRGetParam(this.mClientID, str.getBytes(), this.f3189a) == 0) {
                return new String(this.f3189a.buffer);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* renamed from: a */
    public int m2051a(String str, String str2, GrammarListener grammarListener, C3692ad c3692ad) {
        f3187d = grammarListener;
        String c3692ad2 = c3692ad.toString();
        String m1827b = c3692ad.m1827b(SpeechConstant.TEXT_ENCODING, "utf-8");
        String m1827b2 = c3692ad.m1827b("pte", "utf-8");
        try {
            byte[] bytes = str2.getBytes(m1827b);
            PerfLogger.appendInfo(PerfLogger.LAST_DATA_FLAG, null);
            DebugLog.LogD("QISRBuildGrammar begin, garmmar: " + str2);
            int QISRBuildGrammar = MSC.QISRBuildGrammar(str.getBytes(m1827b2), bytes, bytes.length, c3692ad2.getBytes(m1827b2), "grammarCallBack", this);
            DebugLog.LogD("QISRBuildGrammar leave: " + QISRBuildGrammar);
            return QISRBuildGrammar;
        } catch (UnsupportedEncodingException e) {
            DebugLog.LogE(e);
            return 20012;
        }
    }

    int grammarCallBack(int i, char[] cArr) {
        DebugLog.LogD("grammarCallBack begin, errorCode: " + i);
        PerfLogger.appendInfo(PerfLogger.GET_RESULT, null);
        GrammarListener grammarListener = f3187d;
        if (grammarListener != null) {
            if (i != 0) {
                grammarListener.onBuildFinish("", i != 0 ? new SpeechError(i) : null);
            } else {
                grammarListener.onBuildFinish(String.valueOf(cArr), null);
            }
        }
        DebugLog.LogD("grammarCallBack begin, leave");
        return 0;
    }

    /* renamed from: a */
    public int m2052a(String str, String str2, LexiconListener lexiconListener, C3692ad c3692ad) {
        f3188e = lexiconListener;
        this.f3192f = str;
        c3692ad.m1823a(SpeechConstant.TEXT_ENCODING, "utf-8", false);
        String m1827b = c3692ad.m1827b(SpeechConstant.TEXT_ENCODING, "utf-8");
        String m1827b2 = c3692ad.m1827b("pte", "utf-8");
        String c3692ad2 = c3692ad.toString();
        try {
            byte[] bytes = str2.getBytes(m1827b);
            DebugLog.LogD("QISRUpdateLexicon begin, name: " + str + "content: " + str2);
            PerfLogger.appendInfo(PerfLogger.LAST_DATA_FLAG, null);
            int QISRUpdateLexicon = MSC.QISRUpdateLexicon(str.getBytes(m1827b2), bytes, bytes.length, c3692ad2.getBytes(m1827b2), "lexiconCallBack", this);
            DebugLog.LogD("QISRUpdateLexicon leave: " + QISRUpdateLexicon);
            return QISRUpdateLexicon;
        } catch (UnsupportedEncodingException e) {
            DebugLog.LogE(e);
            return 20012;
        }
    }

    int lexiconCallBack(int i, char[] cArr) {
        DebugLog.LogD("lexiconCallBack begin, errorCode: " + i);
        PerfLogger.appendInfo(PerfLogger.GET_RESULT, null);
        LexiconListener lexiconListener = f3188e;
        if (lexiconListener != null) {
            if (i != 0) {
                lexiconListener.onLexiconUpdated(this.f3192f, i != 0 ? new SpeechError(i) : null);
            } else {
                lexiconListener.onLexiconUpdated(this.f3192f, null);
            }
        }
        DebugLog.LogD("lexiconCallBack leave");
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public String m2058c() {
        if (this.mSessionID == null) {
            this.mSessionID = m2057b("sid");
        }
        return this.mSessionID;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public String m2059d() {
        return m2057b("audio_url");
    }
}
