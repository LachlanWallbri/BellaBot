package com.aliyun.alink.linksdk.channel.gateway.api;

import com.aliyun.alink.linksdk.cmp.connect.channel.PersistentConnectConfig;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class GatewayConnectConfig extends PersistentConnectConfig {
    public GatewayConnectConfig(String str, String str2, String str3) {
        this.productKey = str;
        this.deviceName = str2;
        this.deviceSecret = str3;
    }
}
