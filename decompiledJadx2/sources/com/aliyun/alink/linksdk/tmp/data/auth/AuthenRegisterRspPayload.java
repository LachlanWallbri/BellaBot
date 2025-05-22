package com.aliyun.alink.linksdk.tmp.data.auth;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AuthenRegisterRspPayload {
    public int code;
    public List<AuthenRegisterRspData> data;

    /* renamed from: id */
    public int f1018id;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class AuthenRegisterRspData {
        public String deviceName;
        public String deviceSecret;
        public String productKey;
    }
}
