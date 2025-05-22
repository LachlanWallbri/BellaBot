package com.aliyun.alink.apiclient;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IoTApiClient {
    void deinit();

    void init(InitializeConfig initializeConfig);

    void send(CommonRequest commonRequest, IoTCallback ioTCallback);
}
