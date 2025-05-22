package com.aliyun.alink.linksdk.channel.gateway.api.subdevice;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.channel.gateway.api.GatewayChannel;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class SubDeviceInfo {
    public String deviceName;
    public SubDeviceLoginState loginState = SubDeviceLoginState.OFFLINE;
    public String productKey;

    public SubDeviceInfo() {
    }

    public SubDeviceInfo(String str, String str2) {
        this.productKey = str;
        this.deviceName = str2;
    }

    public boolean checkValid() {
        return (TextUtils.isEmpty(this.productKey) || TextUtils.isEmpty(this.deviceName)) ? false : true;
    }

    public String getDeviceId() {
        return this.productKey + GatewayChannel.DID_SEPARATOR + this.deviceName;
    }
}
