package com.aliyun.alink.linksdk.cmp.manager.discovery;

import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DiscoveryMessage extends AMessage {
    public String deviceName;
    public ARequest discoveryRequest;

    /* renamed from: ip */
    private String f1015ip;
    public String modelType;
    private int port;
    public String productKey;

    public String getIp() {
        return this.f1015ip;
    }

    public void setIp(String str) {
        this.f1015ip = str;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int i) {
        this.port = i;
    }
}
