package com.aliyun.alink.linksdk.alcs.coap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IAuthHandler {
    public static final int AUTH_ACCESS_TOKEN_INVALID = 502;
    public static final int AUTH_ACCESS_TOKEN_REVOKE = 501;
    public static final int AUTH_AUTH_ILLEGALSIGN = 506;
    public static final int AUTH_SUCCESS = 200;

    void onAuthResult(String str, int i, int i2);
}
