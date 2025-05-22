package com.aliyun.alink.linksdk.tmp.device.payload.cloud;

import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class EncryptGroupAuthInfo {
    public String encryptAccessKey;
    public String encryptAccessToken;
    public String encryptGroupKeyPrefix;
    public String encryptGroupSecret;

    public String toString() {
        super.toString();
        return GsonUtils.toJson(this);
    }
}
