package com.aliyun.alink.linksdk.tmp.connect;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpCommonRequest<T> {
    protected String mDeviceName;
    protected boolean mIsSecurity;
    protected String mProductKey;
    protected Object mTag;
    protected T mWrapperRequest;

    public String getTopic() {
        return null;
    }

    public boolean isMulThreadCallback() {
        return false;
    }

    public TmpCommonRequest(T t) {
        this.mWrapperRequest = t;
    }

    public Object getTag() {
        return this.mTag;
    }

    public void setTag(Object obj) {
        this.mTag = obj;
    }

    public T getRequest() {
        return this.mWrapperRequest;
    }

    public void setProductKey(String str) {
        this.mProductKey = str;
    }

    public void setDeviceName(String str) {
        this.mDeviceName = str;
    }

    public String getProductKey() {
        return this.mProductKey;
    }

    public boolean isSecurity() {
        return this.mIsSecurity;
    }

    public void setIsSecurity(boolean z) {
        this.mIsSecurity = z;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }
}
