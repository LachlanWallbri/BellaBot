package com.aliyun.alink.linksdk.tmp.device.panel.data;

import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PropertyNotifyPayload {
    public String method;
    public PropertyParams params;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class ItemData {
        public String time;
        public ValueWrapper value;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class PropertyParams {
        public String deviceName;
        public String iotId;
        public Map<String, ItemData> items;
        public String productKey;
    }
}
