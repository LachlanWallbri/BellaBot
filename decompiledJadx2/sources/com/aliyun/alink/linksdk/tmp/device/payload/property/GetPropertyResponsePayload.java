package com.aliyun.alink.linksdk.tmp.device.payload.property;

import com.aliyun.alink.linksdk.tmp.device.payload.CommonResponsePayload;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class GetPropertyResponsePayload extends CommonResponsePayload<Map<String, ValueWrapper>> {
    public Map<String, ValueWrapper> getProperty() {
        return (Map) this.data;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setProperty(Map<String, ValueWrapper> map) {
        this.data = map;
    }
}
