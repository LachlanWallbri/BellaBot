package com.aliyun.alink.linkkit.api;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.dm.api.IoTApiClientConfig;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LinkKitInitParams {
    public String tsl;
    public DeviceInfo deviceInfo = null;
    public Map<String, ValueWrapper> propertyValues = null;
    public IoTApiClientConfig connectConfig = null;
    public IoTMqttClientConfig mqttClientConfig = null;
    public IoTDMConfig ioTDMConfig = null;
    public IoTH2Config iotH2InitParams = null;

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
