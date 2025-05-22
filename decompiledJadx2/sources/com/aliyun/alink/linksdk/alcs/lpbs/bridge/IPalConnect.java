package com.aliyun.alink.linksdk.alcs.lpbs.bridge;

import com.aliyun.alink.linksdk.alcs.lpbs.component.cloud.IThingCloudChannel;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalConnectParams;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalReqMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalSubMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalConnectListener;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalDeviceStateListener;
import com.aliyun.alink.linksdk.alcs.lpbs.listener.PalMsgListener;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IPalConnect {
    boolean asyncSendRequest(PalReqMessage palReqMessage, PalMsgListener palMsgListener);

    boolean isDeviceConnected(PalDeviceInfo palDeviceInfo);

    void onCloudChannelCreate(IThingCloudChannel iThingCloudChannel);

    boolean regDeviceStateListener(PalDeviceInfo palDeviceInfo, PalDeviceStateListener palDeviceStateListener);

    void startConnect(PalConnectParams palConnectParams, PalConnectListener palConnectListener);

    void stopConnect(PalDeviceInfo palDeviceInfo);

    boolean subscribe(PalSubMessage palSubMessage, PalMsgListener palMsgListener, PalMsgListener palMsgListener2);

    boolean unregDeviceStateListener(PalDeviceInfo palDeviceInfo, PalDeviceStateListener palDeviceStateListener);

    boolean unsubscribe(PalSubMessage palSubMessage, PalMsgListener palMsgListener);
}
