package com.aliyun.alink.linksdk.cmp.connect.alcs;

import com.aliyun.alink.linksdk.cmp.core.base.AConnectConfig;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AlcsConnectConfig extends AConnectConfig {
    protected String iotId;
    protected boolean isSecurity;
    protected String mAccessKey;
    protected String mAccessToken;
    public String mDataFormat;
    protected String mDeviceName;
    protected String mDstAddr;
    protected int mDstPort = 0;
    protected String mProductKey;

    @Override // com.aliyun.alink.linksdk.cmp.core.base.AConnectConfig
    public boolean checkVaild() {
        return true;
    }

    public boolean isNeedAuthInfo() {
        return false;
    }

    public String getIotId() {
        return this.iotId;
    }

    public void setIotId(String str) {
        this.iotId = str;
    }

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

    public boolean isSecurity() {
        return this.isSecurity;
    }

    public void setSecurity(boolean z) {
        this.isSecurity = z;
    }
}
