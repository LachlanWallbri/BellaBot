package com.aliyun.alink.dm.api;

import com.aliyun.alink.dm.p021o.C0867b;
import com.aliyun.alink.linksdk.channel.gateway.api.GatewayChannel;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class BaseInfo {
    public String productKey = null;
    public String deviceName = null;

    public String getDevId() {
        return this.productKey + GatewayChannel.DID_SEPARATOR + this.deviceName;
    }

    public String toString() {
        return C0867b.m182a(this);
    }
}
