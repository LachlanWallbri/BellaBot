package com.aliyun.alink.linksdk.alcs.lpbs.data;

import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PalDeviceInfo {
    public String deviceId;
    public String productModel;

    public String getDevId() {
        if (TextUtils.isEmpty(this.productModel)) {
            return this.deviceId;
        }
        return this.productModel + this.deviceId;
    }

    public PalDeviceInfo(String str, String str2) {
        this.productModel = str;
        this.deviceId = str2;
    }

    public String toString() {
        return this.productModel + this.deviceId;
    }
}
