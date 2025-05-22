package com.aliyun.alink.apiclient;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IoTAPIClientFactory {
    public IoTApiClient getClient() {
        return IoTAPIClientImpl.getInstance();
    }
}
