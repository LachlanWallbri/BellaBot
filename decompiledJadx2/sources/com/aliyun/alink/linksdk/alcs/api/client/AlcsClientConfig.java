package com.aliyun.alink.linksdk.alcs.api.client;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AlcsClientConfig {
    protected String mAccessKey;
    protected String mAccessToken;
    public String mDataFormat;
    protected String mDeviceName;
    protected String mDstAddr;
    protected int mDstPort;
    protected int mHeartBeatTimeout = 60000;
    protected boolean mIsNeddAuth;
    protected String mProductKey;

    public int getDstPort() {
        return this.mDstPort;
    }

    public void setDstPort(int i) {
        this.mDstPort = i;
    }

    public String getDstAddr() {
        return this.mDstAddr;
    }

    public void setDstAddr(String str) {
        this.mDstAddr = str;
    }

    public String getAccessKey() {
        return this.mAccessKey;
    }

    public void setAccessKey(String str) {
        this.mAccessKey = str;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public void setAccessToken(String str) {
        this.mAccessToken = str;
    }

    public String getProductKey() {
        return this.mProductKey;
    }

    public void setProductKey(String str) {
        this.mProductKey = str;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public void setDeviceName(String str) {
        this.mDeviceName = str;
    }

    public boolean isNeddAuth() {
        return this.mIsNeddAuth;
    }

    public void setIsNeddAuth(boolean z) {
        this.mIsNeddAuth = z;
    }
}
