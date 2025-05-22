package com.aliyun.alink.dm.api;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.linksdk.cmp.connect.channel.PersistentConnectConfig;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DMConfigParams {
    public String tsl;
    public boolean enableLocalCommunication = false;
    public boolean enableThingModel = false;
    public boolean enableGateway = false;
    public DeviceInfo deviceInfo = null;
    public Map<String, ValueWrapper> propertyValues = null;
    public IoTApiClientConfig connectConfig = null;
    public PersistentConnectConfig persistentConnectConfig = null;

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
