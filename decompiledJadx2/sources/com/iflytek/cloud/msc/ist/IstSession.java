package com.iflytek.cloud.msc.ist;

import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import com.iflytek.cloud.thirdparty.AbstractC3741t;
import com.iflytek.cloud.thirdparty.AbstractHandlerC3740s;
import com.iflytek.cloud.thirdparty.C3694af;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.UnsupportedEncodingException;

/* loaded from: classes3.dex */
public class IstSession extends AbstractC3741t {
    private static final int IST_AUDIO_SAMPLE_CONTINUE = 2;
    private static final int IST_AUDIO_SAMPLE_LAST = 4;
    private MSCSessionInfo mSessionOut = new MSCSessionInfo();
    private MSCSessionInfo mWriteout = new MSCSessionInfo();
    private byte[] mResult = null;

    @Override // com.iflytek.cloud.thirdparty.AbstractC3741t
    public int sessionBegin(Context context, String str, AbstractHandlerC3740s abstractHandlerC3740s) throws SpeechError, UnsupportedEncodingException {
        String m1849c = C3694af.m1849c(context, str, abstractHandlerC3740s);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        synchronized (IstSession.class) {
            PerfLogger.appendInfo(PerfLogger.MSC_SESSION_BIGNE, null);
            if (TextUtils.isEmpty(str)) {
                DebugLog.LogD(m1849c);
                this.mClientID = MSC.QISRSessionBegin(null, m1849c.getBytes(abstractHandlerC3740s.getParamEncoding()), this.mSessionOut);
            } else {
                this.mClientID = MSC.QISRSessionBegin(str.getBytes(abstractHandlerC3740s.getParamEncoding()), m1849c.getBytes(abstractHandlerC3740s.getParamEncoding()), this.mSessionOut);
                DebugLog.LogD("sessionBegin grammarId:" + str);
            }
            PerfLogger.appendInfo(PerfLogger.SESSION_BEGIN_END, null);
        }
        DebugLog.LogD("sessionBegin ErrCode:" + this.mSessionOut.errorcode + " time:" + (SystemClock.elapsedRealtime() - elapsedRealtime));
        int i = this.mSessionOut.errorcode;
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

    public synchronized void pushAudioData(byte[] bArr, int i) throws SpeechError {
        writeData(bArr, i, 2);
    }

    public synchronized void pushEndFlag() throws SpeechError {
        PerfLogger.appendInfo(PerfLogger.LAST_DATA_FLAG, null);
        DebugLog.LogD("IstSession pushEndFlag");
        writeData(new byte[0], 0, 4);
    }

    private synchronized void writeData(byte[] bArr, int i, int i2) throws SpeechError {
        int QISRAudioWrite = MSC.QISRAudioWrite(this.mClientID, bArr, i, i2, this.mWriteout);
        this.mSessionOut.sesstatus = this.mWriteout.sesstatus;
        DebugLog.LogI("QISRAudioWrite length:" + i + ", aus=" + i2);
        if (QISRAudioWrite != 0) {
            throw new SpeechError(this.mWriteout.errorcode);
        }
    }

    public synchronized int getEpStatus() {
        return this.mWriteout.epstatues;
    }

    public synchronized int getAudioVolume() {
        int i;
        int i2;
        i = 0;
        try {
            i2 = MSC.QISRGetParam(this.mClientID, "volume".getBytes(), this.mWriteout);
        } catch (Exception unused) {
            i2 = 0;
        }
        try {
            if (i2 == 0) {
                i = Integer.parseInt(new String(new String(this.mWriteout.buffer)));
            } else {
                DebugLog.LogI("VAD CHECK FALSE");
            }
        } catch (Exception unused2) {
            DebugLog.LogD("getAudioVolume Exception vadret = " + i2);
            return i;
        }
        return i;
    }

    public synchronized int getIntValue(String str) {
        int i = 0;
        if (this.mClientID == null) {
            return 0;
        }
        try {
            String stringValue = getStringValue(str);
            if (!TextUtils.isEmpty(stringValue)) {
                i = Integer.parseInt(new String(stringValue));
            }
        } catch (Exception e) {
            DebugLog.LogE(e);
        }
        return i;
    }

    public synchronized int getIntValue(String str, int i) {
        if (this.mClientID == null) {
            return i;
        }
        try {
            String stringValue = getStringValue(str);
            if (!TextUtils.isEmpty(stringValue)) {
                i = Integer.parseInt(new String(stringValue));
            }
        } catch (Exception e) {
            DebugLog.LogE(e);
        }
        return i;
    }

    public synchronized boolean setParam(String str, String str2) {
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

    public synchronized String getStringValue(String str) {
        if (this.mClientID == null) {
            return null;
        }
        try {
            if (MSC.QISRGetParam(this.mClientID, str.getBytes(), this.mSessionOut) == 0) {
                return new String(this.mSessionOut.buffer);
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static int getIntByTag(String str) {
        try {
            String stringByTag = getStringByTag(str);
            if (TextUtils.isEmpty(stringByTag)) {
                return 0;
            }
            return Integer.parseInt(stringByTag);
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String getStringByTag(String str) {
        try {
            MSCSessionInfo mSCSessionInfo = new MSCSessionInfo();
            if (MSC.QISRGetParam(null, str.getBytes(), mSCSessionInfo) == 0) {
                return new String(mSCSessionInfo.buffer).trim();
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public synchronized boolean hasResult() {
        return this.mSessionOut.sesstatus == 0;
    }

    public byte[] getResultData() {
        return this.mResult;
    }

    public AbstractC3741t.a getStatus() throws SpeechError {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.mResult = MSC.QISRGetResult(this.mClientID, this.mSessionOut);
        StringBuilder sb = new StringBuilder();
        sb.append("QISRGetResult leave: ");
        sb.append(this.mResult != null);
        sb.append(" time:");
        sb.append(SystemClock.elapsedRealtime() - elapsedRealtime);
        DebugLog.LogI(sb.toString());
        int i = this.mSessionOut.errorcode;
        if (i == 0) {
            int i2 = this.mSessionOut.rsltstatus;
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
            return AbstractC3741t.a.noResult;
        }
        DebugLog.LogE("Result: error errorcode is " + i);
        throw new SpeechError(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getSessionID() {
        if (this.mSessionID == null) {
            this.mSessionID = getStringValue("sid");
        }
        return this.mSessionID;
    }

    protected String getAudioUrl() {
        return getStringValue("audio_url");
    }
}
