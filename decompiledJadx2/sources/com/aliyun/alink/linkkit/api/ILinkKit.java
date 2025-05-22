package com.aliyun.alink.linkkit.api;

import android.content.Context;
import com.aliyun.alink.dm.api.IApiClient;
import com.aliyun.alink.dm.api.IDeviceCOTA;
import com.aliyun.alink.dm.api.IDeviceLabel;
import com.aliyun.alink.dm.api.IDeviceShadow;
import com.aliyun.alink.dm.api.IGateway;
import com.aliyun.alink.dm.api.IOta;
import com.aliyun.alink.dm.api.IThing;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSubscribeListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectUnscribeListener;
import com.aliyun.alink.p022h2.stream.api.IStreamSender;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface ILinkKit {
    void deinit();

    @Deprecated
    void deviceRegister(Context context, LinkKitInitParams linkKitInitParams, ARequest aRequest, IConnectSendListener iConnectSendListener);

    IDeviceCOTA getDeviceCOTA();

    IDeviceLabel getDeviceLabel();

    IDeviceShadow getDeviceShadow();

    IThing getDeviceThing();

    IGateway getGateway();

    IStreamSender getH2StreamClient();

    IApiClient getIoTApiClient();

    IOta getOta();

    String getSDKVersion();

    String getToSignString(Map<String, String> map);

    void init(Context context, LinkKitInitParams linkKitInitParams, ILinkKitConnectListener iLinkKitConnectListener);

    void publish(ARequest aRequest, IConnectSendListener iConnectSendListener);

    void registerOnPushListener(IConnectNotifyListener iConnectNotifyListener);

    void reset(IConnectSendListener iConnectSendListener);

    void subscribe(ARequest aRequest, IConnectSubscribeListener iConnectSubscribeListener);

    @Deprecated
    void subscribeRRPC(ARequest aRequest, IConnectRrpcListener iConnectRrpcListener);

    void unRegisterOnPushListener(IConnectNotifyListener iConnectNotifyListener);

    void unsubscribe(ARequest aRequest, IConnectUnscribeListener iConnectUnscribeListener);
}
