package com.aliyun.alink.linksdk.alcs.lpbs.data;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PalSubMessage {
    public PalDeviceInfo deviceInfo;
    public byte[] payload;
    public String topic;

    public String getDevId() {
        PalDeviceInfo palDeviceInfo = this.deviceInfo;
        if (palDeviceInfo != null) {
            return palDeviceInfo.getDevId();
        }
        return null;
    }
}
