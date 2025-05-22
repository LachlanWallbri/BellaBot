package com.iflytek.cloud;

import android.content.Context;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.AbstractC3744w;
import com.iflytek.cloud.thirdparty.HandlerC3746y;

/* loaded from: classes3.dex */
public class DataUploader extends AbstractC3744w {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractC3744w
    /* renamed from: a_ */
    public boolean mo1643a_() {
        return true;
    }

    public DataUploader(Context context) {
        super(context);
    }

    public int uploadData(SpeechListener speechListener, String str, byte[] bArr) {
        try {
            this.f3436c = new HandlerC3746y(this.f3434a, this.mSessionParams, m2259a("upload"));
            ((HandlerC3746y) this.f3436c).m2271a(new AbstractC3744w.a(speechListener), str, bArr);
            return 0;
        } catch (SpeechError e) {
            int errorCode = e.getErrorCode();
            DebugLog.LogE(e);
            return errorCode;
        } catch (Throwable th) {
            DebugLog.LogE(th);
            return 20999;
        }
    }
}
