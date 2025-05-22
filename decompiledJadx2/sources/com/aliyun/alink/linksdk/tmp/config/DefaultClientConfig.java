package com.aliyun.alink.linksdk.tmp.config;

import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.config.DeviceConfig;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DefaultClientConfig extends DeviceConfig {
    public String mAccessKey;
    public String mAccessToken;
    public String mDateFormat;

    public DefaultClientConfig(DeviceBasicData deviceBasicData) {
        setBasicData(deviceBasicData);
        this.mDeviceType = DeviceConfig.DeviceType.CLIENT;
    }

    public String getAccessKey() {
        return this.mAccessKey;
    }

    public void setAccessKey(String str) {
        this.mAccessKey = str;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public void setAccessToken(String str) {
        this.mAccessToken = str;
    }
}
