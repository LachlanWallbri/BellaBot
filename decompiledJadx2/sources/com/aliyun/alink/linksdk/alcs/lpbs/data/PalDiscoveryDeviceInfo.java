package com.aliyun.alink.linksdk.alcs.lpbs.data;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PalDiscoveryDeviceInfo {
    public String dataFormat;
    public PalDeviceInfo deviceInfo;
    public boolean isDataToCloud;
    public String modelType;
    public String pluginId;
    public String tslData;

    public boolean isPkDnNeedConvert() {
        return true;
    }

    public PalDiscoveryDeviceInfo() {
        this.isDataToCloud = true;
        this.modelType = "0";
    }

    public PalDiscoveryDeviceInfo(PalDeviceInfo palDeviceInfo) {
        this();
        this.deviceInfo = palDeviceInfo;
    }

    public String getDevId() {
        PalDeviceInfo palDeviceInfo = this.deviceInfo;
        if (palDeviceInfo != null) {
            return palDeviceInfo.getDevId();
        }
        return null;
    }
}
