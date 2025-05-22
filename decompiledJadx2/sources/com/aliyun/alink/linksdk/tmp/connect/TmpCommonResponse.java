package com.aliyun.alink.linksdk.tmp.connect;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public abstract class TmpCommonResponse<T> {
    protected T mResponse;

    public abstract String getAddr();

    public String getIotId() {
        return "";
    }

    public abstract int getPort();

    public abstract byte[] getResponseByte();

    public abstract String getResponseText();

    public abstract boolean isSuccess();

    public void setIotId(String str) {
    }

    public abstract void setResponseText(String str);

    public TmpCommonResponse(T t) {
        this.mResponse = t;
    }

    public T getResponse() {
        return this.mResponse;
    }
}
