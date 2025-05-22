package com.aliyun.alink.linksdk.tmp.data.auth;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AuthenRegisterReqPayload {

    /* renamed from: id */
    public int f1017id;
    public String method;
    public List<AuthenRegisterParams> params;
    public String version;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class AuthenRegisterParams {
        public String deviceName;
        public String productKey;

        public AuthenRegisterParams(String str, String str2) {
            this.productKey = str;
            this.deviceName = str2;
        }
    }
}
