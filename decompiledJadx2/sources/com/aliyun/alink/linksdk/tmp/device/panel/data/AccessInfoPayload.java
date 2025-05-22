package com.aliyun.alink.linksdk.tmp.device.panel.data;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class AccessInfoPayload {
    public int code;
    public List<AlcsDeviceInfo> data;

    /* renamed from: id */
    public String f1023id;

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class AlcsDeviceInfo {
        public String accessKey;
        public String accessToken;
        public String deviceName;
        public String iotId;
        public String productKey;
    }
}
