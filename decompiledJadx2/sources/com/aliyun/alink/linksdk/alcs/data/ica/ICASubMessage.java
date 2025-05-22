package com.aliyun.alink.linksdk.alcs.data.ica;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ICASubMessage {
    public ICADeviceInfo deviceInfo;
    public byte[] payload;
    public String topic;

    public String getDevId() {
        ICADeviceInfo iCADeviceInfo = this.deviceInfo;
        if (iCADeviceInfo != null) {
            return iCADeviceInfo.getDevId();
        }
        return null;
    }
}
