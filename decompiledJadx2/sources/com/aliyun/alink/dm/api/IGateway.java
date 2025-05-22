package com.aliyun.alink.dm.api;

import android.util.Pair;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceActionListener;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceConnectListener;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceRemoveListener;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tools.AError;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IGateway {
    void gatewayAddSubDevice(BaseInfo baseInfo, ISubDeviceConnectListener iSubDeviceConnectListener);

    void gatewayDeleteSubDevice(BaseInfo baseInfo, ISubDeviceRemoveListener iSubDeviceRemoveListener);

    void gatewayDevListUpload(List<? extends BaseInfo> list, IConnectSendListener iConnectSendListener);

    void gatewayGetSubDevices(IConnectSendListener iConnectSendListener);

    @Deprecated
    void gatewaySetSubDeviceDeleteListener(BaseInfo baseInfo, IConnectRrpcListener iConnectRrpcListener);

    void gatewaySetSubDeviceDisableListener(BaseInfo baseInfo, IConnectRrpcListener iConnectRrpcListener);

    void gatewaySubDevicRegister(List<? extends BaseInfo> list, IConnectSendListener iConnectSendListener);

    void gatewaySubDeviceLogin(BaseInfo baseInfo, ISubDeviceActionListener iSubDeviceActionListener);

    void gatewaySubDeviceLogout(BaseInfo baseInfo, ISubDeviceActionListener iSubDeviceActionListener);

    void gatewaySubDevicePublish(String str, String str2, BaseInfo baseInfo, ISubDeviceActionListener iSubDeviceActionListener);

    void gatewaySubDeviceSubscribe(String str, BaseInfo baseInfo, ISubDeviceActionListener iSubDeviceActionListener);

    void gatewaySubDeviceUnsubscribe(String str, BaseInfo baseInfo, ISubDeviceActionListener iSubDeviceActionListener);

    Pair<IDeviceCOTA, AError> getSubDeviceCOTA(BaseInfo baseInfo);

    Pair<IDeviceLabel, AError> getSubDeviceLabel(BaseInfo baseInfo);

    Pair<IDeviceShadow, AError> getSubDeviceShadow(BaseInfo baseInfo);

    Pair<IThing, AError> getSubDeviceThing(BaseInfo baseInfo);

    void initSubDeviceThing(String str, DeviceInfo deviceInfo, Map<String, ValueWrapper> map, IDMCallback<InitResult> iDMCallback);

    void permitJoin(IConnectRrpcListener iConnectRrpcListener);

    void subDevicRegister(ARequest aRequest, IConnectSendListener iConnectSendListener);

    void uninitSubDeviceThing(BaseInfo baseInfo);
}
