package com.iflytek.cloud.thirdparty;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechListener;
import com.iflytek.cloud.TextUnderstanderListener;
import com.iflytek.cloud.UnderstanderResult;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.AbstractC3744w;
import com.iflytek.msc.MSC;
import java.io.UnsupportedEncodingException;

/* renamed from: com.iflytek.cloud.thirdparty.ap */
/* loaded from: classes3.dex */
public class C3704ap extends AbstractC3744w {
    public C3704ap(Context context) {
        super(context);
    }

    /* renamed from: a */
    public int m1944a(String str, TextUnderstanderListener textUnderstanderListener) {
        int i;
        try {
            if (TextUtils.isEmpty(getParameter(SpeechConstant.ASR_SCH))) {
                setParameter(SpeechConstant.ASR_SCH, "1");
            }
            if (TextUtils.isEmpty(getParameter(SpeechConstant.NLP_VERSION))) {
                setParameter(SpeechConstant.NLP_VERSION, MSC.isIflyVersion() ? "3.0" : "2.0");
            }
            if (TextUtils.isEmpty(getParameter(SpeechConstant.RESULT_TYPE))) {
                setParameter(SpeechConstant.RESULT_TYPE, "json");
            }
            if (mo1797d()) {
                i = 21005;
            } else {
                this.f3436c = new HandlerC3746y(this.f3434a, this.mSessionParams, m2259a("textunderstand"));
                ((HandlerC3746y) this.f3436c).m2270a(new AbstractC3744w.a(new a(textUnderstanderListener)), str);
                i = 0;
            }
            return i;
        } catch (SpeechError e) {
            int errorCode = e.getErrorCode();
            DebugLog.LogE(e);
            return errorCode;
        } catch (Throwable th) {
            DebugLog.LogE(th);
            return 20999;
        }
    }

    /* renamed from: e */
    public boolean m1945e() {
        return mo1797d();
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3744w
    public void cancel(boolean z) {
        super.cancel(z);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean setParameter(String str, String str2) {
        return super.setParameter(str, str2);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3743v
    public String getParameter(String str) {
        return super.getParameter(str);
    }

    @Override // com.iflytek.cloud.thirdparty.AbstractC3744w, com.iflytek.cloud.thirdparty.AbstractC3743v
    public boolean destroy() {
        return super.destroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.iflytek.cloud.thirdparty.ap$a */
    /* loaded from: classes3.dex */
    public class a implements SpeechListener {

        /* renamed from: b */
        private TextUnderstanderListener f3041b;

        @Override // com.iflytek.cloud.SpeechListener
        public void onEvent(int i, Bundle bundle) {
        }

        public a(TextUnderstanderListener textUnderstanderListener) {
            this.f3041b = textUnderstanderListener;
        }

        @Override // com.iflytek.cloud.SpeechListener
        public void onBufferReceived(byte[] bArr) {
            if (bArr != null) {
                try {
                    this.f3041b.onResult(new UnderstanderResult(new String(bArr, "utf-8")));
                } catch (UnsupportedEncodingException e) {
                    DebugLog.LogE(e);
                } catch (NullPointerException e2) {
                    DebugLog.LogE(e2);
                }
            }
        }

        @Override // com.iflytek.cloud.SpeechListener
        public void onCompleted(SpeechError speechError) {
            TextUnderstanderListener textUnderstanderListener = this.f3041b;
            if (textUnderstanderListener == null || speechError == null) {
                return;
            }
            textUnderstanderListener.onError(speechError);
        }
    }
}
