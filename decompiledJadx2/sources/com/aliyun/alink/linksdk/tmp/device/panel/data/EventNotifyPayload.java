package com.aliyun.alink.linksdk.tmp.device.panel.data;

import com.aliyun.alink.linksdk.tmp.api.OutputParams;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class EventNotifyPayload {
    public String method;
    public EventParams params;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class EventParams {
        public String deviceName;
        public String identifier;
        public String iotId;
        public String name;
        public String productKey;
        public long time;
        public String type;
        public OutputParams value;
    }
}
