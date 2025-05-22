package com.aliyun.alink.linksdk.tmp.config;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DeviceConfig {
    protected DeviceBasicData mBasicData;
    protected DeviceType mDeviceType;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public enum DeviceType {
        CLIENT,
        SERVER,
        PROVISION,
        PROVISION_RECEIVER
    }

    public DeviceBasicData getBasicData() {
        return this.mBasicData;
    }

    public void setBasicData(DeviceBasicData deviceBasicData) {
        this.mBasicData = deviceBasicData;
    }

    public DeviceType getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.mDeviceType = deviceType;
    }

    public String getDevId() {
        DeviceBasicData deviceBasicData = this.mBasicData;
        if (deviceBasicData != null) {
            return deviceBasicData.getDevId();
        }
        return null;
    }
}
