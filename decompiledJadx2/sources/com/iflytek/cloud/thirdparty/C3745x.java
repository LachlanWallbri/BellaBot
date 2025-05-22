package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/* renamed from: com.iflytek.cloud.thirdparty.x */
/* loaded from: classes3.dex */
public class C3745x {

    /* renamed from: a */
    public static Object f3442a = new Object();

    /* renamed from: b */
    private MSCSessionInfo f3443b = new MSCSessionInfo();

    /* renamed from: a */
    public static void m2263a(Context context, String str, String str2, AbstractHandlerC3740s abstractHandlerC3740s) throws SpeechError, IOException {
        byte[] bArr;
        synchronized (f3442a) {
            String m1838a = C3694af.m1838a(context, abstractHandlerC3740s);
            if (TextUtils.isEmpty(str)) {
                if (context != null) {
                    String m1837a = C3694af.m1837a(context);
                    if (!TextUtils.isEmpty(m1837a)) {
                        bArr = m1837a.getBytes(abstractHandlerC3740s.getParamEncoding());
                    }
                }
                bArr = null;
            } else {
                bArr = str.getBytes("utf-8");
            }
            try {
                int QMSPLogin = MSC.QMSPLogin(bArr, TextUtils.isEmpty(str2) ? null : str2.getBytes(abstractHandlerC3740s.getParamEncoding()), m1838a.getBytes(abstractHandlerC3740s.getParamEncoding()));
                DebugLog.LogD("[MSPLogin]ret:" + QMSPLogin);
                if (QMSPLogin != 0) {
                    throw new SpeechError(QMSPLogin);
                }
            } catch (UnsatisfiedLinkError e) {
                DebugLog.LogE(e);
                throw new SpeechError(20021);
            }
        }
    }

    /* renamed from: a */
    public static boolean m2264a() {
        boolean z;
        if (!AbstractHandlerC3740s.isEmpty()) {
            DebugLog.LogD("MscHandler is not empty while logout.");
            return false;
        }
        synchronized (f3442a) {
            z = MSC.QMSPLogOut() == 0;
        }
        return z;
    }

    /* renamed from: a */
    public synchronized byte[] m2267a(Context context, String str, byte[] bArr, AbstractHandlerC3740s abstractHandlerC3740s) throws SpeechError, UnsupportedEncodingException {
        byte[] QMSPUploadData;
        synchronized (f3442a) {
            String m1848c = C3694af.m1848c(context, abstractHandlerC3740s);
            DebugLog.LogD("[MSPSession uploadData]enter time:" + System.currentTimeMillis());
            QMSPUploadData = MSC.QMSPUploadData(str.getBytes(abstractHandlerC3740s.getParamEncoding()), bArr, bArr.length, m1848c.getBytes("utf-8"), this.f3443b);
            StringBuilder sb = new StringBuilder();
            sb.append("[MSPSession uploaddData]leavel:");
            sb.append(this.f3443b.errorcode);
            sb.append(",data len = ");
            sb.append(QMSPUploadData == null ? 0 : QMSPUploadData.length);
            DebugLog.LogD(sb.toString());
            int i = this.f3443b.errorcode;
            if (i != 0 || QMSPUploadData == null) {
                throw new SpeechError(i);
            }
        }
        return QMSPUploadData;
    }

    /* renamed from: a */
    public synchronized byte[] m2265a(Context context, AbstractHandlerC3740s abstractHandlerC3740s) throws SpeechError, UnsupportedEncodingException {
        byte[] QMSPDownloadData;
        synchronized (f3442a) {
            String m1848c = C3694af.m1848c(context, abstractHandlerC3740s);
            DebugLog.LogD("[MSPSession downloadData]enter time:" + System.currentTimeMillis());
            PerfLogger.appendInfo(PerfLogger.LAST_DATA_FLAG, null);
            QMSPDownloadData = MSC.QMSPDownloadData(m1848c.getBytes(abstractHandlerC3740s.getParamEncoding()), this.f3443b);
            PerfLogger.appendInfo(PerfLogger.GET_RESULT, null);
            StringBuilder sb = new StringBuilder();
            sb.append("[MSPSession downloadData]leavel:");
            sb.append(this.f3443b.errorcode);
            sb.append(",data len = ");
            sb.append(QMSPDownloadData == null ? 0 : QMSPDownloadData.length);
            DebugLog.LogD(sb.toString());
            int i = this.f3443b.errorcode;
            if (i != 0 || QMSPDownloadData == null) {
                throw new SpeechError(i);
            }
        }
        return QMSPDownloadData;
    }

    /* renamed from: a */
    public synchronized byte[] m2266a(Context context, AbstractHandlerC3740s abstractHandlerC3740s, String str) throws SpeechError, UnsupportedEncodingException {
        byte[] QMSPSearch;
        synchronized (f3442a) {
            String m1848c = C3694af.m1848c(context, abstractHandlerC3740s);
            DebugLog.LogD("[MSPSession searchResult]enter time:" + System.currentTimeMillis());
            byte[] bytes = str.getBytes("utf-8");
            PerfLogger.appendInfo(PerfLogger.LAST_DATA_FLAG, null);
            QMSPSearch = MSC.QMSPSearch(m1848c.getBytes(abstractHandlerC3740s.getParamEncoding()), bytes, this.f3443b);
            PerfLogger.appendInfo(PerfLogger.GET_RESULT, null);
            StringBuilder sb = new StringBuilder();
            sb.append("[QMSPSearch searchResult]leavel:");
            sb.append(this.f3443b.errorcode);
            sb.append(",data len = ");
            sb.append(QMSPSearch == null ? 0 : QMSPSearch.length);
            DebugLog.LogD(sb.toString());
            int i = this.f3443b.errorcode;
            if (i != 0 || QMSPSearch == null) {
                throw new SpeechError(i);
            }
        }
        return QMSPSearch;
    }
}
