package com.iflytek.aiui.pro;

import android.net.NetworkInfo;
import android.text.TextUtils;
import com.iflytek.cloud.msc.util.NetworkUtil;
import com.iflytek.speech.VoiceWakeuperAidl;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* renamed from: com.iflytek.aiui.pro.at */
/* loaded from: classes.dex */
public class C3590at {
    /* renamed from: a */
    public static String m1069a(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return "none";
        }
        try {
            if (networkInfo.getType() == 1) {
                return "wifi";
            }
            String lowerCase = networkInfo.getExtraInfo().toLowerCase();
            if (TextUtils.isEmpty(lowerCase)) {
                return "none";
            }
            if (!lowerCase.startsWith("3gwap") && !lowerCase.startsWith(NetworkUtil.NET_UNIWAP)) {
                return lowerCase.startsWith(NetworkUtil.NET_CMWAP) ? NetworkUtil.NET_CMWAP : lowerCase.startsWith(NetworkUtil.NET_CTWAP) ? NetworkUtil.NET_CTWAP : lowerCase.startsWith(NetworkUtil.NET_CTNET) ? NetworkUtil.NET_CTNET : lowerCase;
            }
            return NetworkUtil.NET_UNIWAP;
        } catch (Exception e) {
            C3589as.m1058a(e.toString());
            return "none";
        }
    }

    /* renamed from: b */
    public static String m1070b(NetworkInfo networkInfo) {
        if (networkInfo == null) {
            return "none";
        }
        try {
            if (networkInfo.getType() == 1) {
                return "none";
            }
            return ("" + networkInfo.getSubtypeName()) + VoiceWakeuperAidl.PARAMS_SEPARATE + networkInfo.getSubtype();
        } catch (Exception e) {
            C3589as.m1058a(e.toString());
            return "none";
        }
    }
}
