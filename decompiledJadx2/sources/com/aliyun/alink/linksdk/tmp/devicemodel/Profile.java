package com.aliyun.alink.linksdk.tmp.devicemodel;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Profile {
    public String addr;
    private String deviceName;
    public int port;
    private String productKey;

    public String getName() {
        return this.deviceName;
    }

    public void setName(String str) {
        this.deviceName = str;
    }

    public String getProdKey() {
        return this.productKey;
    }

    public void setProdKey(String str) {
        this.productKey = str;
    }
}
