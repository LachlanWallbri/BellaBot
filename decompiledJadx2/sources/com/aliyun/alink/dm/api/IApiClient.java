package com.aliyun.alink.dm.api;

import android.content.Context;
import com.aliyun.alink.apiclient.CommonRequest;
import com.aliyun.alink.apiclient.IoTCallback;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IApiClient {
    void init(Context context, IoTApiClientConfig ioTApiClientConfig, DeviceInfo deviceInfo);

    void sendIoTHTTPRequest(CommonRequest commonRequest, IoTCallback ioTCallback);

    @Deprecated
    void sendIoTHTTPRequest(ARequest aRequest, IConnectSendListener iConnectSendListener);
}
