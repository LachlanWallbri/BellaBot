package com.iflytek.cloud;

import android.text.TextUtils;
import com.iflytek.cloud.msc.util.log.DebugLog;
import com.iflytek.cloud.thirdparty.AbstractC3743v;
import com.iflytek.msc.MSC;
import com.iflytek.msc.MSCSessionInfo;

/* loaded from: classes3.dex */
public class Version {

    /* renamed from: a */
    private static String f2835a = "";

    /* renamed from: b */
    private static String f2836b = "0";

    public static String getVersion() {
        if (TextUtils.isEmpty(f2835a) || "0".equalsIgnoreCase(f2836b)) {
            f2835a = "4." + getModeVersion() + ".1140." + getMscVersion();
        }
        return f2835a;
    }

    private static String getMscVersion() {
        int i;
        if ("0".equalsIgnoreCase(f2836b)) {
            try {
                if (MSC.isLoaded()) {
                    MSCSessionInfo mSCSessionInfo = new MSCSessionInfo();
                    byte[] QMSPGetVersion = MSC.QMSPGetVersion("ver_msc".getBytes("gb2312"), mSCSessionInfo);
                    if (mSCSessionInfo.errorcode == 0) {
                        String str = new String(QMSPGetVersion, "gb2312");
                        DebugLog.LogD("get msc full version name: " + str);
                        int lastIndexOf = str.lastIndexOf(".");
                        if (lastIndexOf >= 0 && str.length() > (i = lastIndexOf + 1)) {
                            f2836b = str.substring(i);
                        }
                    } else {
                        DebugLog.LogD("get msc version error: " + mSCSessionInfo.errorcode);
                    }
                } else {
                    DebugLog.LogE("get msc version msc is not load.");
                }
            } catch (Throwable th) {
                DebugLog.LogE("get msc version exception:");
                DebugLog.LogE(th);
            }
        }
        return f2836b;
    }

    private static String getModeVersion() {
        return AbstractC3743v.a.MSC == SpeechUtility.DEF_ENGINE_MODE ? "6" : "5";
    }
}
