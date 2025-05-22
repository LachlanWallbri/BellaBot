package com.iflytek.cloud.thirdparty;

import android.text.TextUtils;
import com.iflytek.speech.ISpeechModule;

/* renamed from: com.iflytek.cloud.thirdparty.v */
/* loaded from: classes3.dex */
public abstract class AbstractC3743v {
    protected static final Object sSync = new Object();
    protected C3692ad mSessionParams = new C3692ad();

    /* renamed from: com.iflytek.cloud.thirdparty.v$a */
    /* loaded from: classes3.dex */
    public enum a {
        MSC
    }

    public boolean destroy() {
        return true;
    }

    public boolean setParameter(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.equals("params")) {
            if (TextUtils.isEmpty(str2)) {
                this.mSessionParams.m1818a();
            } else {
                this.mSessionParams.m1828b(str2);
            }
            return true;
        }
        if (TextUtils.isEmpty(str2)) {
            return this.mSessionParams.m1829c(str).booleanValue();
        }
        this.mSessionParams.m1822a(str, str2);
        return true;
    }

    public boolean setParameter(C3692ad c3692ad) {
        this.mSessionParams = c3692ad.clone();
        return true;
    }

    public String getParameter(String str) {
        if ("params".equals(str)) {
            return this.mSessionParams.toString();
        }
        return this.mSessionParams.m1833e(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public a getStartMode(String str, ISpeechModule iSpeechModule) {
        return a.MSC;
    }
}
