package com.aliyun.alink.linksdk.alcs.coap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AlcsCoAPContext {
    protected String mAddress;
    protected long mContextId;
    protected int mPort;
    protected int mWaitTime;

    public long getContextId() {
        return this.mContextId;
    }

    public void setContextId(long j) {
        this.mContextId = j;
    }

    public int getPort() {
        return this.mPort;
    }

    public void setPort(int i) {
        this.mPort = i;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public void setAddress(String str) {
        this.mAddress = str;
    }

    public int getWaitTime() {
        return this.mWaitTime;
    }

    public void setWaitTime(int i) {
        this.mWaitTime = i;
    }
}
