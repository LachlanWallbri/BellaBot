package com.aliyun.alink.linksdk.tmp.device.payload;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CommonResponsePayload<T> {
    protected int code;
    protected T data;

    /* renamed from: id */
    protected String f1027id;
    protected String msg;

    public T getData() {
        return this.data;
    }

    public void setData(T t) {
        this.data = t;
    }

    public String getId() {
        return this.f1027id;
    }

    public void setId(String str) {
        this.f1027id = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public boolean payloadSuccess() {
        int i = this.code;
        return i >= 200 && i < 300;
    }
}
