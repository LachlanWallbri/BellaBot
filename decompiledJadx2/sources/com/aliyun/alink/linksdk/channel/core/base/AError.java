package com.aliyun.alink.linksdk.channel.core.base;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Deprecated
/* loaded from: classes.dex */
public class AError {
    public static final int AKErrorInvokeNetError = 4101;
    public static final int AKErrorInvokeServerError = 4102;
    public static final int AKErrorLoginTokenIllegalError = 4001;
    public static final int AKErrorServerBusinessError = 4103;
    public static final int AKErrorSuccess = 0;
    public static final int AKErrorUnknownError = 4201;
    public static final String ERR_DOMAIN_NAME_ALINK = "alinkErrorDomain";
    private int code;
    private String domain = "alinkErrorDomain";
    private String msg;
    private Object originResponseObj;
    private int subCode;
    private String subDomain;
    private String subMsg;

    public void setDomain(String str) {
        this.domain = str;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public void setSubDomain(String str) {
        this.subDomain = str;
    }

    public void setSubCode(int i) {
        this.subCode = i;
    }

    public void setSubMsg(String str) {
        this.subMsg = str;
    }

    public String getDomain() {
        return this.domain;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getSubDomain() {
        return this.subDomain;
    }

    public int getSubCode() {
        return this.subCode;
    }

    public String getSubMsg() {
        return this.subMsg;
    }

    public Object getOriginResponseObject() {
        return this.originResponseObj;
    }

    public void setOriginResponseObject(Object obj) {
        this.originResponseObj = obj;
    }
}
