package com.iflytek.cloud;

import android.content.Context;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.AbstractC3744w;
import com.iflytek.cloud.thirdparty.HandlerC3746y;

/* loaded from: classes3.dex */
public class DataDownloader extends AbstractC3744w {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.iflytek.cloud.thirdparty.AbstractC3744w
    /* renamed from: a_ */
    public boolean mo1643a_() {
        return true;
    }

    public DataDownloader(Context context) {
        super(context);
    }

    public int downloadData(SpeechListener speechListener) {
        try {
            this.f3436c = new HandlerC3746y(this.f3434a, this.mSessionParams, m2259a("download"));
            ((HandlerC3746y) this.f3436c).m2269a(new AbstractC3744w.a(speechListener));
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
