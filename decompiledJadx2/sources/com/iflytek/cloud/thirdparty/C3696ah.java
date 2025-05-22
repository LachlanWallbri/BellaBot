package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.aiui.AIUIConstant;
import com.iflytek.cloud.RequestListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.msc.util.HttpRequest;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.msc.util.log.PerfLogger;
import org.json.JSONObject;

/* renamed from: com.iflytek.cloud.thirdparty.ah */
/* loaded from: classes3.dex */
public class C3696ah extends AbstractC3743v {

    /* renamed from: d */
    private Context f2993d;

    /* renamed from: e */
    private HttpRequest f2994e;

    /* renamed from: f */
    private RequestListener f2995f;

    /* renamed from: b */
    private String f2991b = "http://openapi.openspeech.cn/webapi/wfr.do";

    /* renamed from: c */
    private String f2992c = "pver=1.0";

    /* renamed from: a */
    HttpRequest.HttpRequestListener f2990a = new HttpRequest.HttpRequestListener() { // from class: com.iflytek.cloud.thirdparty.ah.1
        @Override // com.iflytek.cloud.msc.util.HttpRequest.HttpRequestListener
        public void onError(SpeechError speechError) {
            if (speechError != null) {
                DebugLog.LogE("upload error. please check net state:" + speechError.getErrorCode());
            } else {
                DebugLog.LogD("upload succeed");
            }
            if (C3696ah.this.f2995f != null) {
                C3696ah.this.f2995f.onCompleted(speechError);
            }
        }

        @Override // com.iflytek.cloud.msc.util.HttpRequest.HttpRequestListener
        public void onResult(HttpRequest httpRequest, byte[] bArr) {
            if (bArr != null) {
                try {
                    String str = new String(bArr, "utf-8");
                    DebugLog.LogD(str);
                    int parseInt = Integer.parseInt(new JSONObject(str).getString("ret"));
                    if (parseInt == 0) {
                        if (C3696ah.this.f2995f != null) {
                            PerfLogger.appendInfo(PerfLogger.GET_RESULT, null);
                            C3696ah.this.f2995f.onBufferReceived(bArr);
                        }
                        onError(null);
                        return;
                    }
                    onError(new SpeechError(parseInt, SpeechConstant.ENG_WFR));
                } catch (Exception unused) {
                    onError(new SpeechError(20004));
                }
            }
        }
    };

    public C3696ah(Context context, C3692ad c3692ad) {
        this.f2993d = null;
        this.f2994e = null;
        this.mSessionParams = c3692ad;
        this.f2993d = context;
        this.f2994e = new HttpRequest();
    }

    /* renamed from: a */
    public int m1863a(byte[] bArr, RequestListener requestListener) {
        try {
            this.f2995f = requestListener;
            if (SpeechUtility.getUtility() == null) {
                return 10111;
            }
            String m1831d = this.mSessionParams.m1831d(AIUIConstant.KEY_SERVER_URL);
            if (TextUtils.isEmpty(m1831d)) {
                m1831d = this.f2991b;
            }
            String m1851d = C3694af.m1851d(this.f2993d, this.mSessionParams);
            this.f2994e.setTimeOut(this.mSessionParams.m1816a("timeout", 20000));
            this.f2994e.setConectType(1);
            this.f2994e.setRequest(m1831d, this.f2992c, bArr, m1851d);
            this.f2994e.startRequest(this.f2990a);
            PerfLogger.appendInfo(PerfLogger.LAST_DATA_FLAG, null);
            return 0;
        } catch (Exception unused) {
            return 20999;
        }
    }

    /* renamed from: a */
    public void m1864a() {
        this.f2994e.cancel();
        this.f2994e = null;
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean destroy() {
        return super.destroy();
    }
}
