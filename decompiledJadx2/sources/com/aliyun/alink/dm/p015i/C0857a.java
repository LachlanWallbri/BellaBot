package com.aliyun.alink.dm.p015i;

import android.text.TextUtils;
import android.util.Pair;
import com.aliyun.alink.dm.api.BaseInfo;
import com.aliyun.alink.dm.api.DMErrorCode;
import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.dm.api.IDMCallback;
import com.aliyun.alink.dm.api.IDeviceCOTA;
import com.aliyun.alink.dm.api.IDeviceLabel;
import com.aliyun.alink.dm.api.IDeviceShadow;
import com.aliyun.alink.dm.api.IGateway;
import com.aliyun.alink.dm.api.IThing;
import com.aliyun.alink.dm.api.InitResult;
import com.aliyun.alink.dm.p011e.C0853a;
import com.aliyun.alink.dm.p016j.C0858a;
import com.aliyun.alink.dm.p017k.C0859a;
import com.aliyun.alink.dm.p020n.C0865a;
import com.aliyun.alink.dm.p021o.C0866a;
import com.aliyun.alink.dm.shadow.C0871a;
import com.aliyun.alink.linksdk.channel.gateway.api.GatewayChannel;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceActionListener;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceChannel;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceConnectListener;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceRemoveListener;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.SubDeviceInfo;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttRrpcRegisterRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.PersistentConnectInfo;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.AError;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: GatewayImpl.java */
/* renamed from: com.aliyun.alink.dm.i.a */
/* loaded from: classes.dex */
public class C0857a implements IGateway {

    /* renamed from: a */
    private Map<String, IThing> f406a;

    /* renamed from: b */
    private final Object f407b = new Object();

    public C0857a() {
        this.f406a = null;
        this.f406a = new ConcurrentHashMap();
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public void gatewaySubDevicRegister(List<? extends BaseInfo> list, IConnectSendListener iConnectSendListener) {
        C0859a.m131a("GatewayImpl", "gatewaySubDevicRegister() called with: subDevices = [" + list + "], listener = [" + iConnectSendListener + "]");
        GatewayChannel.getInstance().subDeviceRegister(C0866a.m181a(list), iConnectSendListener);
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public void subDevicRegister(ARequest aRequest, IConnectSendListener iConnectSendListener) {
        C0859a.m133b("GatewayImpl", "subDevicRegister() called with: request = [" + aRequest + "], listener = [" + iConnectSendListener + "]");
        if ((aRequest instanceof MqttPublishRequest) && iConnectSendListener != null && ((MqttPublishRequest) aRequest).payloadObj != null) {
            GatewayChannel.getInstance().subDeviceRegister(aRequest, iConnectSendListener);
        } else if (iConnectSendListener != null) {
            AError aError = new AError();
            aError.setCode(DMErrorCode.ERROR_CALL_INTERFACE_PARAMS_ERROR);
            aError.setMsg("subDevicRegister with wrong params. request must be instance of MqttPublishRequest and payload cannot be null. payload can use RequestModel");
            iConnectSendListener.onFailure(null, aError);
        }
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public void gatewayGetSubDevices(IConnectSendListener iConnectSendListener) {
        C0859a.m133b("GatewayImpl", "gatewayTopoGet() called with: listener = [" + iConnectSendListener + "]");
        GatewayChannel.getInstance().getSubDevices(iConnectSendListener);
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public void permitJoin(IConnectRrpcListener iConnectRrpcListener) {
        C0859a.m133b("GatewayImpl", "permitJoin() called with: listener = [" + iConnectRrpcListener + "]");
        if (!(ConnectSDK.getInstance().getConnectInfo(ConnectSDK.getInstance().getPersistentConnectId()) instanceof PersistentConnectInfo)) {
            C0859a.m134c("GatewayImpl", "permitJoin connect info error.");
            if (iConnectRrpcListener != null) {
                AError aError = new AError();
                aError.setCode(1101312);
                aError.setMsg("permitJoinConnectInfoEmpty");
                iConnectRrpcListener.onSubscribeFailed(null, aError);
                return;
            }
            return;
        }
        PersistentConnectInfo persistentConnectInfo = (PersistentConnectInfo) ConnectSDK.getInstance().getConnectInfo(ConnectSDK.getInstance().getPersistentConnectId());
        if (TextUtils.isEmpty(persistentConnectInfo.productKey) || TextUtils.isEmpty(persistentConnectInfo.deviceName)) {
            C0859a.m134c("GatewayImpl", "permitJoin connect info error, pk or dn is null. pk=" + persistentConnectInfo.productKey + ", dn=" + persistentConnectInfo.deviceName);
            if (iConnectRrpcListener != null) {
                AError aError2 = new AError();
                aError2.setCode(1101312);
                aError2.setMsg("permitJoinConnectInfoPKOrDnNull");
                iConnectRrpcListener.onSubscribeFailed(null, aError2);
                return;
            }
            return;
        }
        MqttRrpcRegisterRequest mqttRrpcRegisterRequest = new MqttRrpcRegisterRequest();
        mqttRrpcRegisterRequest.topic = "/sys/" + persistentConnectInfo.productKey + "/" + persistentConnectInfo.deviceName + "/thing/gateway/permit";
        StringBuilder sb = new StringBuilder();
        sb.append(mqttRrpcRegisterRequest.topic);
        sb.append(TmpConstant.URI_TOPIC_REPLY_POST);
        mqttRrpcRegisterRequest.replyTopic = sb.toString();
        ConnectSDK.getInstance().subscribeRrpc(ConnectSDK.getInstance().getPersistentConnectId(), mqttRrpcRegisterRequest, iConnectRrpcListener);
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public void gatewayAddSubDevice(BaseInfo baseInfo, ISubDeviceConnectListener iSubDeviceConnectListener) {
        C0859a.m133b("GatewayImpl", "gatewayTopoAdd() called with: subDevInfo = [" + baseInfo + "], listener = [" + iSubDeviceConnectListener + "]");
        GatewayChannel.getInstance().addSubDevice(new SubDeviceInfo(baseInfo.productKey, baseInfo.deviceName), iSubDeviceConnectListener);
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public void gatewayDeleteSubDevice(BaseInfo baseInfo, ISubDeviceRemoveListener iSubDeviceRemoveListener) {
        C0859a.m133b("GatewayImpl", "gatewayTopoDelete() called with: subDevInfo = [" + baseInfo + "], listener = [" + iSubDeviceRemoveListener + "]");
        GatewayChannel.getInstance().removeSubDevice(new SubDeviceInfo(baseInfo.productKey, baseInfo.deviceName), iSubDeviceRemoveListener);
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public void gatewayDevListUpload(List<? extends BaseInfo> list, IConnectSendListener iConnectSendListener) {
        C0859a.m131a("GatewayImpl", "gatewayDevListUpload() called with: foundList = [" + list + "], listener = [" + iConnectSendListener + "]");
        GatewayChannel.getInstance().deviceListUpload(C0866a.m181a(list), iConnectSendListener);
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public void gatewaySubDeviceLogin(BaseInfo baseInfo, ISubDeviceActionListener iSubDeviceActionListener) {
        C0859a.m133b("GatewayImpl", "gatewaySubDeviceLogin() called with: subDevInfo = [" + baseInfo + "], listener = [" + iSubDeviceActionListener + "]");
        ISubDeviceChannel subDevChannel = getSubDevChannel(baseInfo);
        if (subDevChannel == null) {
            C0859a.m135d("GatewayImpl", "gatewaySubDeviceLogin subDevice not added, return");
            if (iSubDeviceActionListener != null) {
                AError aError = new AError();
                aError.setCode(DMErrorCode.ERROR_GATEWAY_TOPO_NOT_ADDED);
                aError.setMsg("gatewaySubDeviceLoginErrorNotAddTopo");
                iSubDeviceActionListener.onFailed(aError);
                return;
            }
            return;
        }
        subDevChannel.online(iSubDeviceActionListener);
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public void gatewaySubDeviceLogout(BaseInfo baseInfo, ISubDeviceActionListener iSubDeviceActionListener) {
        C0859a.m133b("GatewayImpl", "gatewaySubDeviceLogout() called with: subDevInfo = [" + baseInfo + "], listener = [" + iSubDeviceActionListener + "]");
        ISubDeviceChannel subDevChannel = getSubDevChannel(baseInfo);
        if (subDevChannel == null) {
            C0859a.m135d("GatewayImpl", "gatewaySubDeviceLogout subDevice not added, return");
            if (iSubDeviceActionListener != null) {
                AError aError = new AError();
                aError.setCode(DMErrorCode.ERROR_GATEWAY_TOPO_NOT_ADDED);
                aError.setMsg("gatewaySubDeviceLogoutErrorNotAddTopo");
                iSubDeviceActionListener.onFailed(aError);
                return;
            }
            return;
        }
        subDevChannel.offline(iSubDeviceActionListener);
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public void gatewaySetSubDeviceDisableListener(BaseInfo baseInfo, IConnectRrpcListener iConnectRrpcListener) {
        C0859a.m133b("GatewayImpl", "gatewaySubDeviceDisable() called with: subDevInfo = [" + baseInfo + "], listener = [" + iConnectRrpcListener + "]");
        ISubDeviceChannel subDevChannel = getSubDevChannel(baseInfo);
        if (subDevChannel == null) {
            C0859a.m135d("GatewayImpl", "gatewaySubDeviceDisable subDevice not added, return");
            if (iConnectRrpcListener != null) {
                AError aError = new AError();
                aError.setCode(DMErrorCode.ERROR_GATEWAY_TOPO_NOT_ADDED);
                aError.setMsg("gatewaySubDeviceDisableErrorNotAddTopo");
                iConnectRrpcListener.onSubscribeFailed(null, aError);
                return;
            }
            return;
        }
        subDevChannel.setDisableListener(iConnectRrpcListener);
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public void gatewaySetSubDeviceDeleteListener(BaseInfo baseInfo, IConnectRrpcListener iConnectRrpcListener) {
        C0859a.m133b("GatewayImpl", "gatewaySubDeviceDelete() called with: not support");
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public void gatewaySubDevicePublish(String str, String str2, BaseInfo baseInfo, ISubDeviceActionListener iSubDeviceActionListener) {
        C0859a.m133b("GatewayImpl", "gatewaySubDevicePublish() called with: topic = [" + str + "], data = [" + str2 + "], subDevInfo = [" + baseInfo + "], listener = [" + iSubDeviceActionListener + "]");
        if (TextUtils.isEmpty(str)) {
            C0859a.m135d("GatewayImpl", "gatewaySubDevicePublish topic=null");
            if (iSubDeviceActionListener != null) {
                AError aError = new AError();
                aError.setCode(DMErrorCode.ERROR_CALL_INTERFACE_PARAMS_ERROR);
                aError.setMsg("gatewaySubDevicePublishErrorTopicEmpty");
                iSubDeviceActionListener.onFailed(aError);
                return;
            }
            return;
        }
        ISubDeviceChannel subDevChannel = getSubDevChannel(baseInfo);
        if (subDevChannel == null) {
            C0859a.m135d("GatewayImpl", "gatewaySubDevicePublish subDevice not added, return");
            if (iSubDeviceActionListener != null) {
                AError aError2 = new AError();
                aError2.setCode(DMErrorCode.ERROR_GATEWAY_TOPO_NOT_ADDED);
                aError2.setMsg("gatewaySubDevicePublishErrorNotAddTopo");
                iSubDeviceActionListener.onFailed(aError2);
                return;
            }
            return;
        }
        subDevChannel.uploadData(str, str2, iSubDeviceActionListener);
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public void gatewaySubDeviceSubscribe(String str, BaseInfo baseInfo, ISubDeviceActionListener iSubDeviceActionListener) {
        C0859a.m133b("GatewayImpl", "gatewaySubDeviceSubscribe() called with: topic = [" + str + "], subDevInfo = [" + baseInfo + "], listener = [" + iSubDeviceActionListener + "]");
        if (TextUtils.isEmpty(str)) {
            C0859a.m135d("GatewayImpl", "gatewaySubDeviceSubscribe topic=null");
            if (iSubDeviceActionListener != null) {
                AError aError = new AError();
                aError.setCode(DMErrorCode.ERROR_CALL_INTERFACE_PARAMS_ERROR);
                aError.setMsg("gatewaySubDeviceSubscribeErrorTopicEmpty");
                iSubDeviceActionListener.onFailed(aError);
                return;
            }
            return;
        }
        ISubDeviceChannel subDevChannel = getSubDevChannel(baseInfo);
        if (subDevChannel == null) {
            C0859a.m135d("GatewayImpl", "gatewaySubDeviceSubscribe subDevice not added, return");
            if (iSubDeviceActionListener != null) {
                AError aError2 = new AError();
                aError2.setCode(DMErrorCode.ERROR_GATEWAY_TOPO_NOT_ADDED);
                aError2.setMsg("gatewaySubDeviceSubscribeErrorNotAddTopo");
                iSubDeviceActionListener.onFailed(aError2);
                return;
            }
            return;
        }
        subDevChannel.subscribe(str, iSubDeviceActionListener);
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public void gatewaySubDeviceUnsubscribe(String str, BaseInfo baseInfo, ISubDeviceActionListener iSubDeviceActionListener) {
        C0859a.m133b("GatewayImpl", "gatewaySubDeviceUnsubscribe() called with: topic = [" + str + "], subDevInfo = [" + baseInfo + "], listener = [" + iSubDeviceActionListener + "]");
        if (TextUtils.isEmpty(str)) {
            C0859a.m135d("GatewayImpl", "gatewaySubDeviceUnsubscribe topic=null");
            if (iSubDeviceActionListener != null) {
                AError aError = new AError();
                aError.setCode(DMErrorCode.ERROR_CALL_INTERFACE_PARAMS_ERROR);
                aError.setMsg("gatewaySubDeviceUnsubscribeErrorTopicEmpty");
                iSubDeviceActionListener.onFailed(aError);
                return;
            }
            return;
        }
        ISubDeviceChannel subDevChannel = getSubDevChannel(baseInfo);
        if (subDevChannel == null) {
            C0859a.m135d("GatewayImpl", "gatewaySubDeviceUnsubscribe subDevice not added, return");
            if (iSubDeviceActionListener != null) {
                AError aError2 = new AError();
                aError2.setCode(DMErrorCode.ERROR_GATEWAY_TOPO_NOT_ADDED);
                aError2.setMsg("gatewaySubDeviceUnsubscribeErrorNotAddTopo");
                iSubDeviceActionListener.onFailed(aError2);
                return;
            }
            return;
        }
        subDevChannel.unSubscribe(str, iSubDeviceActionListener);
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public void initSubDeviceThing(String str, DeviceInfo deviceInfo, Map<String, ValueWrapper> map, IDMCallback<InitResult> iDMCallback) {
        C0859a.m133b("GatewayImpl", "initSubDeviceThing() called with: tsl = [" + str + "], deviceInfo = [" + deviceInfo + "], propertyValues = [" + map + "], listener = [" + iDMCallback + "]");
        if (!m129a(deviceInfo)) {
            C0859a.m135d("GatewayImpl", "initSubDeviceThing deviceInfo invalid.");
            if (iDMCallback != null) {
                AError aError = new AError();
                aError.setCode(DMErrorCode.ERROR_GATEWAY_LABEL_PARAMS_INVALID);
                aError.setMsg("initSubDeviceThing deviceInfo invalid.");
                iDMCallback.onFailure(aError);
                return;
            }
            return;
        }
        if (getSubDevChannel(deviceInfo) == null) {
            C0859a.m135d("GatewayImpl", "gatewaySubDeviceSubscribe subDevice not added, return");
            if (iDMCallback != null) {
                AError aError2 = new AError();
                aError2.setCode(DMErrorCode.ERROR_GATEWAY_TOPO_NOT_ADDED);
                aError2.setMsg("gatewaySubDeviceSubscribeErrorNotAddTopo");
                iDMCallback.onFailure(aError2);
                return;
            }
            return;
        }
        IThing iThing = (IThing) getSubDeviceThing(deviceInfo).first;
        if (!(iThing instanceof C0865a)) {
            AError aError3 = new AError();
            aError3.setCode(1101312);
            aError3.setMsg("initSubDeviceThing thing type invalid.");
            iDMCallback.onFailure(aError3);
            return;
        }
        ((C0865a) iThing).m180a(str, deviceInfo, map, iDMCallback);
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public void uninitSubDeviceThing(BaseInfo baseInfo) {
        if (!m129a(baseInfo)) {
            C0859a.m135d("GatewayImpl", "uninitSubDeviceThing failed, device info invalid.");
        } else {
            this.f406a.remove(baseInfo.getDevId());
        }
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public Pair<IThing, AError> getSubDeviceThing(BaseInfo baseInfo) {
        IThing iThing;
        if (!m129a(baseInfo)) {
            AError aError = new AError();
            aError.setCode(DMErrorCode.ERROR_CALL_INTERFACE_PARAMS_ERROR);
            aError.setMsg("getSubDeviceThing subDevInfo invalid.");
            return new Pair<>(null, aError);
        }
        synchronized (this.f407b) {
            if (this.f406a.containsKey(baseInfo.getDevId())) {
                iThing = this.f406a.get(baseInfo.getDevId());
            } else {
                C0865a c0865a = new C0865a(baseInfo, false);
                this.f406a.put(baseInfo.getDevId(), c0865a);
                iThing = c0865a;
            }
        }
        C0859a.m134c("GatewayImpl", "getSubDeviceThing subDevThing=true.");
        return new Pair<>(iThing, null);
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public Pair<IDeviceLabel, AError> getSubDeviceLabel(BaseInfo baseInfo) {
        if (m129a(baseInfo)) {
            AError aError = new AError();
            aError.setCode(DMErrorCode.ERROR_CALL_INTERFACE_PARAMS_ERROR);
            aError.setMsg("getSubDeviceLabel subDevInfo invalid.");
            return new Pair<>(null, aError);
        }
        return new Pair<>(new C0858a(baseInfo), null);
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public Pair<IDeviceShadow, AError> getSubDeviceShadow(BaseInfo baseInfo) {
        if (m129a(baseInfo)) {
            AError aError = new AError();
            aError.setCode(DMErrorCode.ERROR_CALL_INTERFACE_PARAMS_ERROR);
            aError.setMsg("getSubDeviceShadow subDevInfo invalid.");
            return new Pair<>(null, aError);
        }
        return new Pair<>(new C0871a(baseInfo), null);
    }

    @Override // com.aliyun.alink.dm.api.IGateway
    public Pair<IDeviceCOTA, AError> getSubDeviceCOTA(BaseInfo baseInfo) {
        if (m129a(baseInfo)) {
            AError aError = new AError();
            aError.setCode(DMErrorCode.ERROR_CALL_INTERFACE_PARAMS_ERROR);
            aError.setMsg("getSubDeviceCOTA subDevInfo invalid.");
            return new Pair<>(null, aError);
        }
        return new Pair<>(new C0853a(baseInfo), null);
    }

    private ISubDeviceChannel getSubDevChannel(BaseInfo baseInfo) {
        if (!m129a(baseInfo)) {
            C0859a.m135d("GatewayImpl", "getSubDevChannel subDevInfo invalid, subDevInfo=" + baseInfo);
            return null;
        }
        return GatewayChannel.getInstance().getSubDeviceChannel(baseInfo.productKey + GatewayChannel.DID_SEPARATOR + baseInfo.deviceName);
    }

    /* renamed from: a */
    private boolean m129a(BaseInfo baseInfo) {
        return (baseInfo == null || TextUtils.isEmpty(baseInfo.productKey) || TextUtils.isEmpty(baseInfo.deviceName)) ? false : true;
    }

    /* renamed from: a */
    public void m130a() {
        GatewayChannel.getInstance().destroyConnect();
    }
}
