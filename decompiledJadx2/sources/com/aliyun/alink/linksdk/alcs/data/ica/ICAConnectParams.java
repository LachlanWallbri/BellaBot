package com.aliyun.alink.linksdk.alcs.data.ica;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ICAConnectParams {
    public ICAAuthParams authInfo;
    public ICADeviceInfo deviceInfo;
    public String pal;

    public ICAConnectParams(ICADeviceInfo iCADeviceInfo, String str, ICAAuthParams iCAAuthParams) {
        this.deviceInfo = iCADeviceInfo;
        this.pal = str;
        this.authInfo = iCAAuthParams;
    }
}
