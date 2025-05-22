package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import com.iflytek.cloud.util.VerifierUtil;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.iflytek.cloud.thirdparty.ag */
/* loaded from: classes3.dex */
public class C3695ag {

    /* renamed from: a */
    private boolean f2988a;

    /* renamed from: b */
    private MSCSessionInfo f2989b;

    public C3695ag(Context context, String str) {
        try {
            if (SpeechUtility.getUtility() == null) {
                throw new SpeechError(10111);
            }
            this.f2988a = false;
            m1857a(str);
        } catch (SpeechError e) {
            DebugLog.LogE(e.getPlainDescription(true));
        }
    }

    /* renamed from: a */
    private void m1857a(String str) {
        if (MSC.isLoaded()) {
            DebugLog.LogD("MSC isLoaded：" + MSC.isLoaded());
            this.f2989b = new MSCSessionInfo();
            PerfLogger.appendInfo(PerfLogger.MSC_SESSION_BIGNE, null);
            if (!TextUtils.isEmpty(str)) {
                MSC.QIFDInit(str.getBytes(), this.f2989b);
            } else {
                MSC.QIFDInit(null, this.f2989b);
            }
            PerfLogger.appendInfo(PerfLogger.SESSION_BEGIN_END, null);
            if (this.f2989b.errorcode != 0) {
                DebugLog.LogE("QIFDINIT INIT FAIL, ERRORCODE:" + this.f2989b.errorcode);
                return;
            }
            DebugLog.LogD("QIFDINIT INIT SUCCESS");
        }
    }

    /* renamed from: a */
    public String m1858a(Bitmap bitmap) {
        if (bitmap != null && Bitmap.Config.ARGB_8888.equals(bitmap.getConfig())) {
            return m1861b(VerifierUtil.ARGB2Gray(bitmap));
        }
        DebugLog.LogE("Method detectARGB:null parameter or not ARGB bitmap");
        return null;
    }

    /* renamed from: b */
    public String m1861b(Bitmap bitmap) {
        if (bitmap == null || !Bitmap.Config.ALPHA_8.equals(bitmap.getConfig())) {
            DebugLog.LogE("Method detectGray:null parameter or not gray bitmap");
            return null;
        }
        this.f2989b = new MSCSessionInfo();
        PerfLogger.appendInfo(PerfLogger.LAST_DATA_FLAG, null);
        byte[] QIFDFacedetect = MSC.QIFDFacedetect(bitmap, VerifierUtil.getBitmapsize(bitmap), this.f2989b);
        PerfLogger.appendInfo(PerfLogger.GET_RESULT, null);
        return m1856a(QIFDFacedetect, this.f2989b);
    }

    /* renamed from: a */
    public String m1859a(byte[] bArr, int i, int i2, int i3, int i4) {
        if (bArr == null || i <= 0 || i2 <= 0 || i4 < 0 || i4 > 3) {
            DebugLog.LogE("Method trackNV21:invalid parameters");
            return null;
        }
        this.f2989b = new MSCSessionInfo();
        PerfLogger.appendInfo(PerfLogger.LAST_DATA_FLAG, null);
        byte[] QIFDMultitracker = MSC.QIFDMultitracker(bArr, bArr.length, i, i2, i3, i4, this.f2989b);
        PerfLogger.appendInfo(PerfLogger.GET_RESULT, null);
        return m1856a(QIFDMultitracker, this.f2989b);
    }

    /* renamed from: a */
    private String m1856a(byte[] bArr, MSCSessionInfo mSCSessionInfo) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (mSCSessionInfo.errorcode == 0) {
                jSONObject = new JSONObject(new String(bArr));
            }
            jSONObject.put("ret", mSCSessionInfo.errorcode);
        } catch (JSONException unused) {
            DebugLog.LogE("face result add errorinfo exception");
        }
        return jSONObject.toString();
    }

    /* renamed from: a */
    public void m1860a() {
        if (this.f2988a) {
            return;
        }
        DebugLog.LogD("QIFDFINIT");
        PerfLogger.appendInfo(PerfLogger.SESSION_END_BEGIN, null);
        DebugLog.LogD("MSC.QIFDFini result is " + MSC.QIFDFini());
        PerfLogger.appendInfo(PerfLogger.SESSION_END_END, null);
        this.f2988a = true;
    }
}
