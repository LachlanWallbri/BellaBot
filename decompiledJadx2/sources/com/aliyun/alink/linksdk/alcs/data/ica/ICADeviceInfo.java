package com.aliyun.alink.linksdk.alcs.data.ica;

import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ICADeviceInfo {
    public String deviceName;

    /* renamed from: ip */
    public String f708ip;
    public String productKey;

    public String getDevId() {
        if (TextUtils.isEmpty(this.productKey)) {
            return this.deviceName;
        }
        return this.productKey + this.deviceName;
    }

    public ICADeviceInfo(String str, String str2) {
        this(str, str2, null);
    }

    public ICADeviceInfo(String str, String str2, String str3) {
        this.productKey = str;
        this.deviceName = str2;
        this.f708ip = str3;
    }

    public String toString() {
        return this.productKey + this.deviceName;
    }
}
