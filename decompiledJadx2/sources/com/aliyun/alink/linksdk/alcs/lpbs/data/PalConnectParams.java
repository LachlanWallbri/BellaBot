package com.aliyun.alink.linksdk.alcs.lpbs.data;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PalConnectParams {
    public Object authInfo;
    public String dataFormat;
    public PalDeviceInfo deviceInfo;

    public String getDevId() {
        PalDeviceInfo palDeviceInfo = this.deviceInfo;
        return palDeviceInfo == null ? "null" : palDeviceInfo.getDevId();
    }
}
