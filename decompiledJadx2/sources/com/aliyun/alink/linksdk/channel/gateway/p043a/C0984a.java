package com.aliyun.alink.linksdk.channel.gateway.p043a;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.aliyun.alink.dm.api.DMErrorCode;
import com.aliyun.alink.linksdk.alcs.api.utils.ErrorCode;
import com.aliyun.alink.linksdk.channel.core.persistent.PersistentConnectState;
import com.aliyun.alink.linksdk.channel.core.persistent.PersistentNet;
import com.aliyun.alink.linksdk.channel.core.persistent.event.IOnPushListener;
import com.aliyun.alink.linksdk.channel.core.persistent.event.PersistentEventDispatcher;
import com.aliyun.alink.linksdk.channel.gateway.api.GatewayChannel;
import com.aliyun.alink.linksdk.channel.gateway.api.GatewayConnectConfig;
import com.aliyun.alink.linksdk.channel.gateway.api.GatewayConnectState;
import com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel;
import com.aliyun.alink.linksdk.channel.gateway.api.IGatewayConnectListener;
import com.aliyun.alink.linksdk.channel.gateway.api.IGatewayDownstreamListener;
import com.aliyun.alink.linksdk.channel.gateway.api.IGatewayRequestListener;
import com.aliyun.alink.linksdk.channel.gateway.api.IGatewaySubscribeListener;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ErrorResponse;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceActionListener;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceChannel;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceConnectListener;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceRemoveListener;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.SubDeviceInfo;
import com.aliyun.alink.linksdk.channel.gateway.api.subdevice.SubDeviceLoginState;
import com.aliyun.alink.linksdk.channel.mobile.api.MobileChannel;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttSubscribeRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.PersistentConnectConfig;
import com.aliyun.alink.linksdk.cmp.core.base.AMessage;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.base.ConnectState;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.manager.connect.ConnectManager;
import com.aliyun.alink.linksdk.cmp.manager.connect.IRegisterConnectListener;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: GatewayChannelImpl.java */
/* renamed from: com.aliyun.alink.linksdk.channel.gateway.a.a */
/* loaded from: classes.dex */
public class C0984a implements IGatewayChannel {

    /* renamed from: e */
    private Context f951e;

    /* renamed from: f */
    private IGatewayConnectListener f952f;

    /* renamed from: h */
    private PersistentConnectConfig f954h;

    /* renamed from: i */
    private Map<String, SubDeviceInfo> f955i;

    /* renamed from: j */
    private Map<String, ISubDeviceConnectListener> f956j;

    /* renamed from: k */
    private Map<String, ISubDeviceChannel> f957k;

    /* renamed from: g */
    private GatewayConnectState f953g = GatewayConnectState.DISCONNECTED;

    /* renamed from: l */
    private b f958l = null;

    /* renamed from: m */
    private AtomicBoolean f959m = new AtomicBoolean(false);

    /* renamed from: n */
    private boolean f960n = false;

    /* renamed from: o */
    private String f961o = null;

    /* renamed from: p */
    private String f962p = "427";

    /* renamed from: q */
    private String f963q = "520";

    /* renamed from: r */
    private String f964r = "521";

    /* renamed from: s */
    private String f965s = "522";

    /* renamed from: t */
    private String f966t = "6401";

    /* renamed from: a */
    String f947a = "thing/sub/register";

    /* renamed from: b */
    String f948b = "thing.sub.register";

    /* renamed from: c */
    String f949c = "thing/sub/unregister";

    /* renamed from: d */
    String f950d = "thing.sub.unregister";

    /* renamed from: u */
    private final String f967u = "thing/list/found";

    /* renamed from: v */
    private final String f968v = "thing.list.found";

    /* renamed from: w */
    private final String f969w = "thing/topo/get";

    /* renamed from: x */
    private final String f970x = "thing.topo.get";

    /* renamed from: y */
    private final String f971y = "thing/topo/add";

    /* renamed from: z */
    private final String f972z = "thing.topo.add";

    /* renamed from: A */
    private final String f944A = "thing/topo/delete";

    /* renamed from: B */
    private final String f945B = "thing.topo.delete";

    /* renamed from: C */
    private IConnectNotifyListener f946C = new IConnectNotifyListener() { // from class: com.aliyun.alink.linksdk.channel.gateway.a.a.5
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
        public void onNotify(String str, String str2, AMessage aMessage) {
            if (aMessage == null || C0984a.this.f954h == null || TextUtils.isEmpty(C0984a.this.f961o) || !C0984a.this.f961o.equals(str2)) {
                return;
            }
            try {
                if (aMessage.data instanceof byte[]) {
                    String str3 = new String((byte[]) aMessage.data, "UTF-8");
                    ErrorResponse errorResponse = (ErrorResponse) JSONObject.parseObject(str3, new TypeReference<ErrorResponse<SubDeviceInfo>>() { // from class: com.aliyun.alink.linksdk.channel.gateway.a.a.5.1
                    }.getType(), new Feature[0]);
                    String str4 = errorResponse.code;
                    ALog.m484w("GatewayChannelImpl", "device error received. " + str3);
                    SubDeviceInfo subDeviceInfo = (SubDeviceInfo) errorResponse.data;
                    if (subDeviceInfo != null && subDeviceInfo.checkValid()) {
                        if (!subDeviceInfo.productKey.equals(C0984a.this.f954h.productKey) || !subDeviceInfo.deviceName.equals(C0984a.this.f954h.deviceName)) {
                            if (!C0984a.this.f965s.equals(str4)) {
                                if (!C0984a.this.f964r.equals(str4) && !C0984a.this.f966t.equals(str4)) {
                                    if (!C0984a.this.f962p.equals(str4)) {
                                        if (C0984a.this.f963q.equals(str4)) {
                                            ALog.m484w("GatewayChannelImpl", "device session error. devInfo=" + str3);
                                            C0984a.this.f957k.remove(subDeviceInfo.getDeviceId());
                                            return;
                                        }
                                        return;
                                    }
                                    ALog.m484w("GatewayChannelImpl", "device login by other device. device need login again. devInfo=" + str3);
                                    C0984a.this.f957k.remove(subDeviceInfo.getDeviceId());
                                    return;
                                }
                                ALog.m483i("GatewayChannelImpl", "remove device topo relation! deviceInfo=" + subDeviceInfo);
                                C0984a.this.f957k.remove(subDeviceInfo.getDeviceId());
                                C0984a.this.f956j.remove(subDeviceInfo.getDeviceId());
                                C0984a.this.f955i.remove(subDeviceInfo.getDeviceId());
                                return;
                            }
                            ALog.m483i("GatewayChannelImpl", "device was forbidden. deviceInfo=" + subDeviceInfo);
                            return;
                        }
                        ALog.m484w("GatewayChannelImpl", "gateway device error.");
                    }
                }
            } catch (Exception unused) {
            }
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
        public boolean shouldHandle(String str, String str2) {
            return (C0984a.this.f954h == null || TextUtils.isEmpty(C0984a.this.f961o) || !C0984a.this.f961o.equals(str2)) ? false : true;
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener
        public void onConnectStateChange(String str, ConnectState connectState) {
            if (ConnectSDK.getInstance().getPersistentConnectId().equals(str)) {
                C0984a.this.f953g = GatewayConnectState.toGatewayConnectState(connectState);
                if (connectState == ConnectState.CONNECTED) {
                    C0984a.this.f960n = true;
                    C0984a.this.m452b();
                }
                if (C0984a.this.f952f != null) {
                    C0984a.this.f952f.onConnectStateChange(C0984a.this.f953g);
                }
            }
        }
    };

    public C0984a() {
        this.f955i = null;
        this.f956j = null;
        this.f957k = null;
        this.f955i = new ConcurrentHashMap();
        this.f956j = new ConcurrentHashMap();
        this.f957k = new ConcurrentHashMap();
    }

    /* renamed from: a */
    public void m465a(boolean z) {
        this.f959m.set(z);
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public void startConnect(Context context, PersistentConnectConfig persistentConnectConfig, IGatewayConnectListener iGatewayConnectListener) {
        ALog.m479d("GatewayChannelImpl", "startConnect()");
        if (context == null || !m450a(persistentConnectConfig)) {
            ALog.m480e("GatewayChannelImpl", "startConnect(), param error, config is empty");
            return;
        }
        if (this.f960n || this.f953g == GatewayConnectState.CONNECTING || this.f953g == GatewayConnectState.CONNECTED) {
            ALog.m479d("GatewayChannelImpl", "startConnect(), channel is connecting or connected");
            return;
        }
        this.f951e = context;
        this.f952f = iGatewayConnectListener;
        this.f954h = persistentConnectConfig;
        this.f961o = "/ext/error/" + this.f954h.productKey + "/" + this.f954h.deviceName;
        m444a(context, persistentConnectConfig);
    }

    /* renamed from: a */
    private boolean m450a(PersistentConnectConfig persistentConnectConfig) {
        return (persistentConnectConfig == null || TextUtils.isEmpty(persistentConnectConfig.productKey) || TextUtils.isEmpty(persistentConnectConfig.deviceName)) ? false : true;
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public void startConnectReuseMobileChannel(Context context, IGatewayConnectListener iGatewayConnectListener) {
        String str;
        String str2;
        ALog.m479d("GatewayChannelImpl", "startConnectReuseMobileChannel()");
        String clientId = MobileChannel.getInstance().getClientId();
        if (TextUtils.isEmpty(clientId)) {
            str = "";
            str2 = str;
        } else {
            str2 = clientId.split("&")[1];
            str = clientId.split("&")[0];
        }
        GatewayConnectConfig gatewayConnectConfig = new GatewayConnectConfig(str2, str, "");
        if (!m450a(gatewayConnectConfig)) {
            ALog.m479d("GatewayChannelImpl", "startConnectReuseMobileChannel(), get mobile client id error,mark sure MobileConnect connected firstly");
            if (iGatewayConnectListener != null) {
                iGatewayConnectListener.onConnectStateChange(GatewayConnectState.CONNECTFAIL);
                return;
            }
            return;
        }
        startConnect(context, gatewayConnectConfig, iGatewayConnectListener);
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public GatewayConnectState getGatewayConnectState() {
        return this.f953g;
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public void subDeviceRegister(List<SubDeviceInfo> list, IConnectSendListener iConnectSendListener) {
        ALog.m479d("GatewayChannelImpl", "subDeviceRegister()");
        if (list == null || list.size() < 1) {
            if (iConnectSendListener != null) {
                AError aError = new AError();
                aError.setCode(1101400);
                aError.setMsg("subDeviceRegister infoList empty");
                iConnectSendListener.onFailure(null, aError);
                return;
            }
            return;
        }
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            SubDeviceInfo subDeviceInfo = list.get(i);
            if (subDeviceInfo != null && !TextUtils.isEmpty(subDeviceInfo.productKey) && !TextUtils.isEmpty(subDeviceInfo.deviceName)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("deviceName", (Object) subDeviceInfo.deviceName);
                jSONObject.put("productKey", (Object) subDeviceInfo.productKey);
                jSONArray.add(jSONObject);
            }
        }
        ConnectSDK.getInstance().send(new C0985b(true, this.f954h, this.f947a, this.f948b, null, jSONArray), iConnectSendListener);
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public void subDeviceRegister(ARequest aRequest, IConnectSendListener iConnectSendListener) {
        ALog.m483i("GatewayChannelImpl", "subDeviceRegister() called with: requestData = [" + aRequest + "], listener = [" + iConnectSendListener + "]");
        if (!(aRequest instanceof MqttPublishRequest) || iConnectSendListener == null) {
            if (iConnectSendListener != null) {
                AError aError = new AError();
                aError.setCode(DMErrorCode.ERROR_CALL_INTERFACE_PARAMS_ERROR);
                aError.setMsg("subDeviceRegister listener is null or requestData not instance of MqttPublishRequest.");
                iConnectSendListener.onFailure(null, aError);
                return;
            }
            return;
        }
        MqttPublishRequest mqttPublishRequest = (MqttPublishRequest) aRequest;
        if (mqttPublishRequest.payloadObj == null) {
            AError aError2 = new AError();
            aError2.setCode(DMErrorCode.ERROR_CALL_INTERFACE_PARAMS_ERROR);
            aError2.setMsg("subDeviceRegister request payload is null.");
            iConnectSendListener.onFailure(null, aError2);
            return;
        }
        if (TextUtils.isEmpty(mqttPublishRequest.topic)) {
            mqttPublishRequest.topic = "/sys/" + this.f954h.productKey + "/" + this.f954h.deviceName + GatewayChannel.TOPIC_PRESET_SUBDEV_REGITER;
            mqttPublishRequest.isRPC = true;
        }
        try {
            ConnectSDK.getInstance().send(aRequest, iConnectSendListener);
        } catch (Exception e) {
            AError aError3 = new AError();
            aError3.setCode(4201);
            aError3.setMsg("subDeviceRegister send exception=" + e);
            iConnectSendListener.onFailure(aRequest, aError3);
        }
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public void subDevicUnregister(List<SubDeviceInfo> list, IConnectSendListener iConnectSendListener) {
        ALog.m479d("GatewayChannelImpl", "subDevicUnregister() called， interface deprecated!");
        if (iConnectSendListener != null) {
            AError aError = new AError();
            aError.setCode(DMErrorCode.ERROR_CALL_DEPRECATED_INTERFACE);
            aError.setMsg("interface deprecated!");
            iConnectSendListener.onFailure(null, aError);
        }
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public void addSubDevice(SubDeviceInfo subDeviceInfo, ISubDeviceConnectListener iSubDeviceConnectListener) {
        ALog.m479d("GatewayChannelImpl", "addSubDevice()");
        if (subDeviceInfo == null || !subDeviceInfo.checkValid()) {
            ALog.m479d("GatewayChannelImpl", "addSubDevice(), params error");
            return;
        }
        if (this.f953g == GatewayConnectState.CONNECTED) {
            this.f956j.put(subDeviceInfo.getDeviceId(), iSubDeviceConnectListener);
            m446a(subDeviceInfo, iSubDeviceConnectListener);
        } else if (iSubDeviceConnectListener != null) {
            AError aError = new AError();
            aError.setCode(4101);
            aError.setMsg("addSubDevice device not connected");
            iSubDeviceConnectListener.onConnectResult(false, null, aError);
        }
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public void removeSubDevice(SubDeviceInfo subDeviceInfo, ISubDeviceRemoveListener iSubDeviceRemoveListener) {
        ALog.m479d("GatewayChannelImpl", "removeSubDevice()");
        if (subDeviceInfo == null || !subDeviceInfo.checkValid()) {
            ALog.m479d("GatewayChannelImpl", "removeSubDevice(), params error");
            return;
        }
        if (this.f953g == GatewayConnectState.CONNECTED) {
            m447a(subDeviceInfo, iSubDeviceRemoveListener);
        } else if (iSubDeviceRemoveListener != null) {
            AError aError = new AError();
            aError.setCode(4101);
            aError.setMsg("removeSubDevice device not connected");
            iSubDeviceRemoveListener.onFailed(aError);
        }
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public void getSubDevices(IConnectSendListener iConnectSendListener) {
        m448a(iConnectSendListener);
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public void deviceListUpload(List<SubDeviceInfo> list, IConnectSendListener iConnectSendListener) {
        ALog.m479d("GatewayChannelImpl", "deviceListUpload()");
        if (list != null && list.size() >= 1) {
            ConnectSDK.getInstance().send(new C0985b(true, this.f954h, "thing/list/found", "thing.list.found", null, list), iConnectSendListener);
        } else if (iConnectSendListener != null) {
            AError aError = new AError();
            aError.setCode(1101400);
            aError.setMsg("deviceListUpload infoList empty");
            iConnectSendListener.onFailure(null, aError);
        }
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public void asyncSendRequest(String str, String str2, Map<String, Object> map, Object obj, IGatewayRequestListener iGatewayRequestListener) {
        ALog.m479d("GatewayChannelImpl", "asyncSendRequest()");
        if (TextUtils.isEmpty(str)) {
            ALog.m480e("GatewayChannelImpl", "asyncSendRequest(), params error");
            return;
        }
        if (this.f953g == GatewayConnectState.CONNECTED) {
            ConnectSDK.getInstance().send(new C0985b(true, this.f954h, str, str2, map, obj), new a(iGatewayRequestListener));
        } else if (iGatewayRequestListener != null) {
            AError aError = new AError();
            aError.setCode(4101);
            aError.setMsg("device not connected");
            iGatewayRequestListener.onFailure(aError);
        }
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public void ayncSendPublishRequest(String str, String str2, Map<String, Object> map, Object obj, IGatewayRequestListener iGatewayRequestListener) {
        ALog.m479d("GatewayChannelImpl", "ayncSendPublishRequest()");
        if (TextUtils.isEmpty(str)) {
            ALog.m480e("GatewayChannelImpl", "ayncSendPublishRequest(), params error");
            return;
        }
        if (this.f953g == GatewayConnectState.CONNECTED) {
            ConnectSDK.getInstance().send(new C0985b(false, this.f954h, str, str2, map, obj), new a(iGatewayRequestListener));
        } else if (iGatewayRequestListener != null) {
            AError aError = new AError();
            aError.setCode(4101);
            aError.setMsg("device not connected");
            iGatewayRequestListener.onFailure(aError);
        }
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public void subscribe(String str, IGatewaySubscribeListener iGatewaySubscribeListener) {
        PersistentConnectConfig persistentConnectConfig;
        ALog.m479d("GatewayChannelImpl", "subscribe(), topic = " + str);
        if (TextUtils.isEmpty(str)) {
            ALog.m480e("GatewayChannelImpl", "subscribe(), topic is empty!");
            return;
        }
        if (this.f953g != GatewayConnectState.CONNECTED) {
            if (iGatewaySubscribeListener != null) {
                AError aError = new AError();
                aError.setCode(4101);
                aError.setMsg("subscribe device not connected");
                iGatewaySubscribeListener.onFailure(aError);
                return;
            }
            return;
        }
        if (!str.startsWith("/sys/") && !str.startsWith("/ota/") && (persistentConnectConfig = this.f954h) != null && m450a(persistentConnectConfig)) {
            str = ("/sys/" + this.f954h.productKey + "/" + this.f954h.deviceName + "/" + str).replace("//", "/");
        }
        MqttSubscribeRequest mqttSubscribeRequest = new MqttSubscribeRequest();
        mqttSubscribeRequest.topic = str;
        ConnectSDK.getInstance().subscribe(ConnectSDK.getInstance().getPersistentConnectId(), mqttSubscribeRequest, iGatewaySubscribeListener);
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public void unSubscribe(String str, IGatewaySubscribeListener iGatewaySubscribeListener) {
        ALog.m479d("GatewayChannelImpl", "unSubscribe()");
        if (TextUtils.isEmpty(str)) {
            ALog.m480e("GatewayChannelImpl", "unSubscribe(), topic is empty!");
            return;
        }
        if (this.f953g != GatewayConnectState.CONNECTED) {
            if (iGatewaySubscribeListener != null) {
                AError aError = new AError();
                aError.setCode(4101);
                aError.setMsg("subscribe device not connected");
                iGatewaySubscribeListener.onFailure(aError);
                return;
            }
            return;
        }
        if (!str.startsWith("/sys/") && !str.startsWith("/ota/") && m450a(this.f954h)) {
            str = ("/sys/" + this.f954h.productKey + "/" + this.f954h.deviceName + "/" + str).replace("//", "/");
        }
        MqttSubscribeRequest mqttSubscribeRequest = new MqttSubscribeRequest();
        mqttSubscribeRequest.topic = str;
        ConnectSDK.getInstance().subscribe(ConnectSDK.getInstance().getPersistentConnectId(), mqttSubscribeRequest, iGatewaySubscribeListener);
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public void registerDownstreamListener(boolean z, IGatewayDownstreamListener iGatewayDownstreamListener) {
        PersistentEventDispatcher.getInstance().registerOnPushListener(iGatewayDownstreamListener, z);
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public void unRegisterDownstreamListener(IGatewayDownstreamListener iGatewayDownstreamListener) {
        PersistentEventDispatcher.getInstance().unregisterOnPushListener(iGatewayDownstreamListener);
    }

    /* renamed from: a */
    private void m444a(Context context, PersistentConnectConfig persistentConnectConfig) {
        ALog.m479d("GatewayChannelImpl", "connect()");
        if (this.f960n || this.f953g == GatewayConnectState.CONNECTING || this.f953g == GatewayConnectState.CONNECTED) {
            ALog.m479d("GatewayChannelImpl", "connect(), channel is connecting or connected now");
            return;
        }
        ConnectSDK.getInstance().registerNofityListener(ConnectSDK.getInstance().getPersistentConnectId(), this.f946C);
        if (PersistentNet.getInstance().getConnectState() == PersistentConnectState.CONNECTED) {
            ALog.m479d("GatewayChannelImpl", "connect(), Persistent already Connected!");
            this.f953g = GatewayConnectState.CONNECTED;
            this.f960n = true;
            IGatewayConnectListener iGatewayConnectListener = this.f952f;
            if (iGatewayConnectListener != null) {
                iGatewayConnectListener.onConnectStateChange(GatewayConnectState.CONNECTED);
            }
            m443a();
            return;
        }
        ALog.m479d("GatewayChannelImpl", "connect(), connecting...");
        this.f953g = GatewayConnectState.CONNECTING;
        IGatewayConnectListener iGatewayConnectListener2 = this.f952f;
        if (iGatewayConnectListener2 != null) {
            iGatewayConnectListener2.onConnectStateChange(GatewayConnectState.CONNECTING);
        }
        ConnectSDK.getInstance().registerPersistentConnect(context, persistentConnectConfig, new IRegisterConnectListener() { // from class: com.aliyun.alink.linksdk.channel.gateway.a.a.1
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
            public void onSuccess() {
                C0984a.this.f953g = GatewayConnectState.CONNECTED;
                C0984a.this.f960n = true;
                C0984a.this.m443a();
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IBaseListener
            public void onFailure(AError aError) {
                C0984a.this.f953g = GatewayConnectState.CONNECTFAIL;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m443a() {
        m448a((IConnectSendListener) null);
        if (this.f958l == null) {
            this.f958l = new b();
            PersistentEventDispatcher.getInstance().registerOnPushListener(this.f958l, true);
        }
    }

    /* renamed from: a */
    private void m448a(final IConnectSendListener iConnectSendListener) {
        ALog.m479d("GatewayChannelImpl", "topoGetReq()");
        ConnectSDK.getInstance().send(new C0985b(true, this.f954h, "thing/topo/get", "thing.topo.get", null, null), new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.channel.gateway.a.a.2
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onResponse(ARequest aRequest, AResponse aResponse) {
                JSONArray jSONArray;
                StringBuilder sb = new StringBuilder();
                sb.append("topoGetReq(), onSuceess, rsp = ");
                sb.append((aResponse == null || aResponse.data == null) ? "" : aResponse.data);
                ALog.m479d("GatewayChannelImpl", sb.toString());
                try {
                    JSONObject parseObject = JSONObject.parseObject((String) aResponse.data);
                    if (200 == parseObject.getIntValue("code") && (jSONArray = parseObject.getJSONArray("data")) != null && jSONArray.size() != 0) {
                        for (int i = 0; i < jSONArray.size(); i++) {
                            SubDeviceInfo subDeviceInfo = (SubDeviceInfo) jSONArray.getObject(i, SubDeviceInfo.class);
                            C0984a.this.f955i.put(subDeviceInfo.getDeviceId(), subDeviceInfo);
                        }
                    }
                } catch (Exception e) {
                    ALog.m479d("GatewayChannelImpl", "topoGetReq(), onSuccess(), parse error, e" + e.toString());
                    e.printStackTrace();
                }
                IConnectSendListener iConnectSendListener2 = iConnectSendListener;
                if (iConnectSendListener2 != null) {
                    iConnectSendListener2.onResponse(aRequest, aResponse);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest aRequest, AError aError) {
                ALog.m479d("GatewayChannelImpl", "topoGetReq(), onFailed");
                IConnectSendListener iConnectSendListener2 = iConnectSendListener;
                if (iConnectSendListener2 != null) {
                    iConnectSendListener2.onFailure(aRequest, aError);
                }
            }
        });
    }

    /* renamed from: a */
    private void m446a(final SubDeviceInfo subDeviceInfo, final ISubDeviceConnectListener iSubDeviceConnectListener) {
        String clientId;
        ALog.m479d("GatewayChannelImpl", "topoAdd()");
        if (subDeviceInfo == null || !subDeviceInfo.checkValid() || iSubDeviceConnectListener == null) {
            return;
        }
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(TmpConstant.KEY_SIGN_VALUE, (Object) iSubDeviceConnectListener.getSignValue());
        jSONObject.put(TmpConstant.KEY_SIGN_METHOD, (Object) iSubDeviceConnectListener.getSignMethod());
        Map<String, Object> signExtraData = iSubDeviceConnectListener.getSignExtraData();
        if (signExtraData != null && !signExtraData.isEmpty()) {
            ALog.m479d("GatewayChannelImpl", "topoAdd(), get extra data " + signExtraData);
            jSONObject.putAll(signExtraData);
        }
        jSONObject.put("deviceName", (Object) subDeviceInfo.deviceName);
        jSONObject.put("productKey", (Object) subDeviceInfo.productKey);
        if (TextUtils.isEmpty(iSubDeviceConnectListener.getClientId())) {
            clientId = subDeviceInfo.deviceName + "&" + subDeviceInfo.productKey;
        } else {
            clientId = iSubDeviceConnectListener.getClientId();
        }
        jSONObject.put(TmpConstant.KEY_CLIENT_ID, (Object) clientId);
        jSONArray.add(jSONObject);
        ConnectSDK.getInstance().send(new C0985b(true, this.f954h, "thing/topo/add", "thing.topo.add", null, jSONArray), new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.channel.gateway.a.a.3
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onResponse(ARequest aRequest, AResponse aResponse) {
                ISubDeviceChannel iSubDeviceChannel;
                StringBuilder sb = new StringBuilder();
                sb.append("topoAdd(), onSuceess, rsp = ");
                sb.append((aResponse == null || aResponse.data == null) ? "" : aResponse.data);
                ALog.m479d("GatewayChannelImpl", sb.toString());
                try {
                    int intValue = JSONObject.parseObject((String) aResponse.data).getIntValue("code");
                    if (intValue == 200) {
                        C0984a.this.f955i.put(subDeviceInfo.getDeviceId(), subDeviceInfo);
                        if (C0984a.this.f957k.containsKey(subDeviceInfo.getDeviceId())) {
                            iSubDeviceChannel = (ISubDeviceChannel) C0984a.this.f957k.get(subDeviceInfo.getDeviceId());
                        } else {
                            iSubDeviceChannel = new C0986c(C0984a.this.f954h, subDeviceInfo, iSubDeviceConnectListener);
                            C0984a.this.f957k.put(subDeviceInfo.getDeviceId(), iSubDeviceChannel);
                        }
                        iSubDeviceConnectListener.onConnectResult(true, iSubDeviceChannel, null);
                        return;
                    }
                    AError aError = new AError();
                    aError.setCode(intValue);
                    aError.setMsg("topo add failed, server error code =" + intValue);
                    iSubDeviceConnectListener.onConnectResult(false, null, aError);
                } catch (Exception e) {
                    ALog.m479d("GatewayChannelImpl", "topoAdd(), onSuccess(), parse error, e" + e.toString());
                    e.printStackTrace();
                    AError aError2 = new AError();
                    aError2.setCode(4103);
                    aError2.setMsg("reqSuccess, parse error, e" + e.toString());
                    iSubDeviceConnectListener.onConnectResult(false, null, aError2);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest aRequest, AError aError) {
                ALog.m479d("GatewayChannelImpl", "topoAdd(), onFailed");
                iSubDeviceConnectListener.onConnectResult(false, null, aError);
            }
        });
    }

    /* renamed from: a */
    private void m447a(SubDeviceInfo subDeviceInfo, final ISubDeviceRemoveListener iSubDeviceRemoveListener) {
        ALog.m479d("GatewayChannelImpl", "topoDelete()");
        if (subDeviceInfo == null || !subDeviceInfo.checkValid()) {
            return;
        }
        this.f955i.remove(subDeviceInfo.getDeviceId());
        this.f956j.remove(subDeviceInfo.getDeviceId());
        this.f957k.remove(subDeviceInfo.getDeviceId());
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("deviceName", (Object) subDeviceInfo.deviceName);
        jSONObject.put("productKey", (Object) subDeviceInfo.productKey);
        jSONArray.add(jSONObject);
        ConnectSDK.getInstance().send(new C0985b(true, this.f954h, "thing/topo/delete", "thing.topo.delete", null, jSONArray), new IConnectSendListener() { // from class: com.aliyun.alink.linksdk.channel.gateway.a.a.4
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onResponse(ARequest aRequest, AResponse aResponse) {
                StringBuilder sb = new StringBuilder();
                sb.append("topoDelete(), onSuceess, rsp = ");
                sb.append((aResponse == null || aResponse.data == null) ? "" : aResponse.data);
                ALog.m479d("GatewayChannelImpl", sb.toString());
                try {
                    String string = JSONObject.parseObject((String) aResponse.data).getString("code");
                    if (ErrorCode.UNKNOWN_SUCCESS_CODE.equals(string)) {
                        if (iSubDeviceRemoveListener != null) {
                            iSubDeviceRemoveListener.onSuceess();
                            return;
                        }
                        return;
                    }
                    AError aError = new AError();
                    if (!TextUtils.isEmpty(string)) {
                        aError.setCode(Integer.getInteger(string).intValue());
                    }
                    aError.setMsg("code =" + string);
                    iSubDeviceRemoveListener.onFailed(aError);
                } catch (Exception e) {
                    ALog.m479d("GatewayChannelImpl", "topoDelete(), onSuccess(), parse error, e" + e.toString());
                    e.printStackTrace();
                    AError aError2 = new AError();
                    aError2.setMsg("reqSuccess, parse error, e" + e.toString());
                    iSubDeviceRemoveListener.onFailed(aError2);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest aRequest, AError aError) {
                ALog.m479d("GatewayChannelImpl", "topoDelete(), onFailed");
                iSubDeviceRemoveListener.onFailed(aError);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: GatewayChannelImpl.java */
    /* renamed from: com.aliyun.alink.linksdk.channel.gateway.a.a$a */
    /* loaded from: classes.dex */
    public class a implements IConnectSendListener {

        /* renamed from: b */
        private IGatewayRequestListener f985b;

        public a(IGatewayRequestListener iGatewayRequestListener) {
            this.f985b = iGatewayRequestListener;
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
        public void onResponse(ARequest aRequest, AResponse aResponse) {
            StringBuilder sb = new StringBuilder();
            sb.append("GatewayOnCallListener, onSuccess, rsp = ");
            sb.append((aResponse == null || aResponse.data == null) ? "null" : aResponse.data.toString());
            ALog.m479d("GatewayChannelImpl", sb.toString());
            this.f985b.onSuccess((aResponse == null || aResponse.data == null) ? null : aResponse.data.toString());
        }

        @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
        public void onFailure(ARequest aRequest, AError aError) {
            this.f985b.onFailure(aError);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: GatewayChannelImpl.java */
    /* renamed from: com.aliyun.alink.linksdk.channel.gateway.a.a$b */
    /* loaded from: classes.dex */
    public class b implements IOnPushListener {
        @Override // com.aliyun.alink.linksdk.channel.core.persistent.event.IOnPushListener
        public boolean shouldHandle(String str) {
            return true;
        }

        private b() {
        }

        @Override // com.aliyun.alink.linksdk.channel.core.persistent.event.IOnPushListener
        public void onCommand(String str, byte[] bArr) {
            if (TextUtils.isEmpty(str) || str.contains(C0984a.this.f954h.deviceName)) {
                return;
            }
            for (String str2 : C0984a.this.f956j.keySet()) {
                if (!TextUtils.isEmpty(str2) && str2.contains(GatewayChannel.DID_SEPARATOR)) {
                    try {
                        String str3 = str2.split(GatewayChannel.DID_SEPARATOR)[0];
                        String str4 = str2.split(GatewayChannel.DID_SEPARATOR)[1];
                        if (str.contains(str3) && str.contains(str4)) {
                            ISubDeviceConnectListener iSubDeviceConnectListener = (ISubDeviceConnectListener) C0984a.this.f956j.get(str2);
                            AMessage aMessage = new AMessage();
                            aMessage.data = bArr;
                            iSubDeviceConnectListener.onDataPush(str, aMessage);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public ISubDeviceChannel getSubDeviceChannel(String str) {
        Map<String, ISubDeviceChannel> map = this.f957k;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return this.f957k.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m452b() {
        ALog.m479d("GatewayChannelImpl", "autoLogin() called");
        if (this.f959m.get()) {
            for (Map.Entry<String, ISubDeviceChannel> entry : this.f957k.entrySet()) {
                if (entry != null) {
                    ISubDeviceChannel value = entry.getValue();
                    if (value.getSubDeviceInfo() != null && value.getSubDeviceInfo().checkValid()) {
                        if (ConnectState.CONNECTED != ConnectSDK.getInstance().getConnectState(ConnectSDK.getInstance().getPersistentConnectId())) {
                            return;
                        }
                        if (value.getSubDeviceInfo().loginState == SubDeviceLoginState.ONLINE) {
                            ALog.m479d("GatewayChannelImpl", "autoLogin onLine & enabled. entry=" + entry);
                            value.online(new ISubDeviceActionListener() { // from class: com.aliyun.alink.linksdk.channel.gateway.a.a.6
                                @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceActionListener
                                public void onSuccess() {
                                    ALog.m479d("GatewayChannelImpl", "autoLogin onSuccess");
                                }

                                @Override // com.aliyun.alink.linksdk.channel.gateway.api.subdevice.ISubDeviceActionListener
                                public void onFailed(AError aError) {
                                    String str;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("autoLogin aError=");
                                    if (aError == null) {
                                        str = "";
                                    } else {
                                        str = aError.getCode() + aError.getMsg();
                                    }
                                    sb.append(str);
                                    ALog.m479d("GatewayChannelImpl", sb.toString());
                                }
                            });
                        } else {
                            ALog.m484w("GatewayChannelImpl", "autoLogin offline or disabled. entry=" + entry);
                        }
                    }
                }
            }
        }
    }

    @Override // com.aliyun.alink.linksdk.channel.gateway.api.IGatewayChannel
    public void destroyConnect() {
        try {
            this.f960n = false;
            this.f953g = GatewayConnectState.DISCONNECTED;
            if (this.f957k != null) {
                this.f957k.clear();
            }
            if (this.f956j != null) {
                this.f956j.clear();
            }
            if (this.f955i != null) {
                this.f955i.clear();
            }
            ConnectManager.getInstance().unregisterConnect(ConnectSDK.getInstance().getPersistentConnectId());
        } catch (Exception e) {
            ALog.m484w("GatewayChannelImpl", "destroyConnect exception=" + e);
        }
        try {
            ConnectSDK.getInstance().unregisterNofityListener(this.f946C);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
