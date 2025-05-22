package com.aliyun.alink.linksdk.alcs.data.ica;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ICAAuthPairs {
    public ICAAuthParams authParams;
    public ICAAuthServerParams authServerParams;

    public ICAAuthPairs(String str, String str2, String str3, String str4) {
        ICAAuthParams iCAAuthParams = new ICAAuthParams();
        this.authParams = iCAAuthParams;
        iCAAuthParams.accessKey = str;
        this.authParams.accessToken = str2;
        ICAAuthServerParams iCAAuthServerParams = new ICAAuthServerParams();
        this.authServerParams = iCAAuthServerParams;
        iCAAuthServerParams.authCode = str3;
        this.authServerParams.authSecret = str4;
    }
}
