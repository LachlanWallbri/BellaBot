package com.iflytek.cloud.thirdparty;

import android.content.Context;
import com.iflytek.cloud.SpeechError;
import java.io.UnsupportedEncodingException;

/* renamed from: com.iflytek.cloud.thirdparty.t */
/* loaded from: classes3.dex */
public abstract class AbstractC3741t {
    public static final String TAG_AUDIO_URL = "audio_url";
    public static final String TAG_SID = "sid";
    public char[] mClientID = null;
    public String mSessionID = null;

    /* renamed from: com.iflytek.cloud.thirdparty.t$a */
    /* loaded from: classes3.dex */
    public enum a {
        hasResult,
        undefined_1,
        noResult,
        undefined_3,
        undefined_4,
        resultOver
    }

    public abstract int sessionBegin(Context context, String str, AbstractHandlerC3740s abstractHandlerC3740s) throws SpeechError, UnsupportedEncodingException;

    public abstract void sessionEnd(String str);

    public String getClientID() {
        char[] cArr = this.mClientID;
        if (cArr != null) {
            return new String(cArr);
        }
        return null;
    }
}
