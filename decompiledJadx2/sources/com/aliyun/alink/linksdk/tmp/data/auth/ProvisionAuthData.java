package com.aliyun.alink.linksdk.tmp.data.auth;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.utils.TextHelper;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ProvisionAuthData {
    public String accessKey;
    public String accessToken;
    public String authCode;
    public String authSecret;
    public String deviceName;
    public String productKey;

    public String getId() {
        return TextHelper.combineStr(this.productKey, this.deviceName);
    }

    public ProvisionAuthData(String str, String str2, AuthPairData authPairData) {
        this.productKey = str;
        this.deviceName = str2;
        this.accessKey = authPairData.accessKey;
        this.accessToken = authPairData.accessToken;
        this.authCode = authPairData.authCode;
        this.authSecret = authPairData.authSecret;
    }

    public ProvisionAuthData() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TextUtils.isEmpty(this.productKey) ? "null" : this.productKey);
        sb.append(TextUtils.isEmpty(this.deviceName) ? "null" : this.deviceName);
        sb.append(TextUtils.isEmpty(this.accessKey) ? "null" : this.accessKey);
        sb.append(TextUtils.isEmpty(this.accessToken) ? "null" : this.accessToken);
        sb.append(TextUtils.isEmpty(this.authCode) ? "null" : this.authCode);
        sb.append(TextUtils.isEmpty(this.authSecret) ? "null" : this.authSecret);
        return sb.toString();
    }
}
