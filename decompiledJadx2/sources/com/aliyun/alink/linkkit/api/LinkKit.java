package com.aliyun.alink.linkkit.api;

import android.content.Context;
import android.text.TextUtils;
import com.aliyun.alink.dm.api.DMConfigParams;
import com.aliyun.alink.dm.api.DMErrorCode;
import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.dm.api.DeviceManager;
import com.aliyun.alink.dm.api.IApiClient;
import com.aliyun.alink.dm.api.IDMCallback;
import com.aliyun.alink.dm.api.IDeviceCOTA;
import com.aliyun.alink.dm.api.IDeviceLabel;
import com.aliyun.alink.dm.api.IDeviceShadow;
import com.aliyun.alink.dm.api.IGateway;
import com.aliyun.alink.dm.api.IOta;
import com.aliyun.alink.dm.api.IThing;
import com.aliyun.alink.dm.api.InitResult;
import com.aliyun.alink.dm.api.IoTApiClientConfig;
import com.aliyun.alink.dm.api.SignUtils;
import com.aliyun.alink.linkkit.p029a.C0906a;
import com.aliyun.alink.linkkit.p030b.C0910a;
import com.aliyun.alink.linksdk.channel.core.persistent.PersistentNet;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.MqttConfigure;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.connect.channel.PersistentConnectConfig;
import com.aliyun.alink.linksdk.cmp.connect.hubapi.HubApiRequest;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSubscribeListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectUnscribeListener;
import com.aliyun.alink.linksdk.id2.Id2Itls;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.p022h2.api.Constraint;
import com.aliyun.alink.p022h2.api.IAuthCallback;
import com.aliyun.alink.p022h2.api.Profile;
import com.aliyun.alink.p022h2.stream.api.IStreamSender;
import com.aliyun.alink.p022h2.stream.api.StreamSenderFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class LinkKit implements ILinkKit {
    private static final String TAG = "LinkKit";
    private AtomicBoolean isDeiniting;
    private AtomicBoolean isInited;
    private AtomicBoolean isIniting;
    private DeviceInfo mDeviceInfo;
    private IStreamSender mIoTH2StreamClient;

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public String getSDKVersion() {
        return "1.7.0-513adc9";
    }

    private LinkKit() {
        this.isInited = new AtomicBoolean(false);
        this.isIniting = new AtomicBoolean(false);
        this.isDeiniting = new AtomicBoolean(false);
        this.mDeviceInfo = null;
        this.mIoTH2StreamClient = null;
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public void init(Context context, final LinkKitInitParams linkKitInitParams, final ILinkKitConnectListener iLinkKitConnectListener) {
        final IoTMqttClientConfig ioTMqttClientConfig;
        C0910a.m326b(TAG, "init() called with: context = [" + context + "], params = [" + linkKitInitParams + "], listener = [" + iLinkKitConnectListener + "]");
        if (this.isIniting.get() || this.isInited.get()) {
            C0910a.m328d(TAG, "linkkit sdk initing or inited, return");
            if (iLinkKitConnectListener != null) {
                iLinkKitConnectListener.onError(DMErrorCode.getErrorCode(DMErrorCode.ERROR_DUPLICATE_SDK_INIT, 101, "linkkit sdk initing or inited"));
                return;
            }
            return;
        }
        if (context == null) {
            this.isIniting.set(false);
            C0910a.m328d(TAG, "linkkit sdk init with context= null, return.");
            if (iLinkKitConnectListener != null) {
                iLinkKitConnectListener.onError(DMErrorCode.getErrorCode(LinkKitErrorCode.ERROR_SDK_INIT_ERROR, DMErrorCode.ERROR_CALL_INTERFACE_PARAMS_ERROR, "linkkit sdk init with context null"));
                return;
            }
            return;
        }
        if (linkKitInitParams == null || linkKitInitParams.deviceInfo == null || TextUtils.isEmpty(linkKitInitParams.deviceInfo.productKey) || TextUtils.isEmpty(linkKitInitParams.deviceInfo.deviceName)) {
            C0910a.m328d(TAG, "linkkit sdk init params error. pk or dn is null.");
            this.isIniting.set(false);
            if (iLinkKitConnectListener != null) {
                iLinkKitConnectListener.onError(DMErrorCode.getErrorCode(LinkKitErrorCode.ERROR_SDK_INIT_ERROR, 120, "linkkit init device info invalid"));
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(linkKitInitParams.deviceInfo.deviceSecret) && TextUtils.isEmpty(MqttConfigure.mqttUserName)) {
            C0910a.m328d(TAG, "linkkit sdk init params error, ds is null. Consider to call deviceRegister method.");
            this.isIniting.set(false);
            if (iLinkKitConnectListener != null) {
                iLinkKitConnectListener.onError(DMErrorCode.getErrorCode(LinkKitErrorCode.ERROR_SDK_INIT_ERROR, 121, "init params error, ds is null."));
                return;
            }
            return;
        }
        if (this.isDeiniting.get()) {
            C0910a.m327c(TAG, "please wait for deinit to finish.");
            if (iLinkKitConnectListener != null) {
                iLinkKitConnectListener.onError(DMErrorCode.getErrorCode(LinkKitErrorCode.ERROR_SDK_INIT_ERROR, 123, "linkkit is deiniting, please wait for deinit to finish."));
                return;
            }
            return;
        }
        this.mDeviceInfo = linkKitInitParams.deviceInfo;
        if (linkKitInitParams.mqttClientConfig == null) {
            ioTMqttClientConfig = new IoTMqttClientConfig();
            ioTMqttClientConfig.productKey = this.mDeviceInfo.productKey;
            ioTMqttClientConfig.deviceName = this.mDeviceInfo.deviceName;
            ioTMqttClientConfig.deviceSecret = this.mDeviceInfo.deviceSecret;
            ioTMqttClientConfig.productSecret = this.mDeviceInfo.productSecret;
        } else {
            ioTMqttClientConfig = linkKitInitParams.mqttClientConfig;
        }
        if (ioTMqttClientConfig.secureMode == 8 && TextUtils.isEmpty(ioTMqttClientConfig.productSecret)) {
            C0910a.m328d(TAG, "init params error, itls secureMode with ps null. Set productSecret.");
            if (iLinkKitConnectListener != null) {
                iLinkKitConnectListener.onError(DMErrorCode.getErrorCode(LinkKitErrorCode.ERROR_SDK_INIT_ERROR, 122, "init params error, itls secureMode with ps null. Set productSecret.."));
                return;
            }
            return;
        }
        try {
            this.isIniting.set(true);
            setReportVersion();
            DMConfigParams dMConfigParams = new DMConfigParams();
            dMConfigParams.deviceInfo = linkKitInitParams.deviceInfo;
            dMConfigParams.tsl = linkKitInitParams.tsl;
            if (linkKitInitParams.ioTDMConfig != null) {
                dMConfigParams.enableLocalCommunication = linkKitInitParams.ioTDMConfig.enableLocalCommunication;
                dMConfigParams.enableThingModel = linkKitInitParams.ioTDMConfig.enableThingModel;
                dMConfigParams.enableGateway = linkKitInitParams.ioTDMConfig.enableGateway;
            }
            dMConfigParams.propertyValues = linkKitInitParams.propertyValues;
            dMConfigParams.connectConfig = linkKitInitParams.connectConfig;
            dMConfigParams.persistentConnectConfig = linkKitInitParams.mqttClientConfig;
            DeviceManager.getInstance().init(context, dMConfigParams, new IDMCallback<InitResult>() { // from class: com.aliyun.alink.linkkit.api.LinkKit.1
                @Override // com.aliyun.alink.dm.api.IDMCallback
                public void onSuccess(InitResult initResult) {
                    C0910a.m326b(LinkKit.TAG, "onSuccess initResult=" + initResult);
                    LinkKit.this.initH2(ioTMqttClientConfig, linkKitInitParams);
                    LinkKit.this.isInited.set(true);
                    LinkKit.this.isIniting.set(false);
                    ILinkKitConnectListener iLinkKitConnectListener2 = iLinkKitConnectListener;
                    if (iLinkKitConnectListener2 != null) {
                        iLinkKitConnectListener2.onInitDone(initResult);
                    }
                }

                @Override // com.aliyun.alink.dm.api.IDMCallback
                public void onFailure(AError aError) {
                    String str;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onFailure aError=");
                    if (aError == null) {
                        str = "";
                    } else {
                        str = aError.getCode() + aError.getMsg();
                    }
                    sb.append(str);
                    C0910a.m328d(LinkKit.TAG, sb.toString());
                    LinkKit.this.isInited.set(false);
                    LinkKit.this.isIniting.set(false);
                    ILinkKitConnectListener iLinkKitConnectListener2 = iLinkKitConnectListener;
                    if (iLinkKitConnectListener2 != null) {
                        iLinkKitConnectListener2.onError(aError);
                    }
                }
            });
        } catch (Exception e) {
            AError aError = new AError();
            aError.setCode(LinkKitErrorCode.ERROR_SDK_ERROR);
            aError.setMsg("init-connect->exception=" + e);
            C0910a.m328d(TAG, "linkkit sdk init exception=" + e);
            if (iLinkKitConnectListener != null) {
                iLinkKitConnectListener.onError(aError);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initH2(PersistentConnectConfig persistentConnectConfig, LinkKitInitParams linkKitInitParams) {
        Profile deviceProfile;
        C0910a.m325a(TAG, "initH2 called.");
        if (linkKitInitParams.iotH2InitParams != null && !TextUtils.isEmpty(linkKitInitParams.iotH2InitParams.endPoint)) {
            if (persistentConnectConfig.secureMode == 8) {
                final HashMap hashMap = new HashMap();
                hashMap.put("name", Constraint.AUTH_TYPE_ID2);
                hashMap.put(Constraint.PARAM_CLIENT_ID, "h2-client-id");
                hashMap.put(Constraint.PARAM_SIGN_METHOD, "hmacmd5");
                hashMap.put(Constraint.PARAM_PRODUCT_KEY, this.mDeviceInfo.productKey);
                hashMap.put(Constraint.PARAM_DEVICE_NAME, this.mDeviceInfo.deviceName);
                hashMap.put(Constraint.PARAM_SECURE_MODE, "7");
                deviceProfile = Profile.getDeviceProfile(linkKitInitParams.iotH2InitParams.endPoint, hashMap, new IAuthCallback() { // from class: com.aliyun.alink.linkkit.api.LinkKit.2
                    @Override // com.aliyun.alink.p022h2.api.IAuthCallback
                    public Map updateAuthData() {
                        C0910a.m325a(LinkKit.TAG, "updateAuthData called.");
                        return LinkKit.this.getExtraH2Params(hashMap);
                    }
                });
            } else {
                deviceProfile = Profile.getDeviceProfile(linkKitInitParams.iotH2InitParams.endPoint, this.mDeviceInfo.productKey, this.mDeviceInfo.deviceName, this.mDeviceInfo.deviceSecret, linkKitInitParams.iotH2InitParams.clientId);
            }
            this.mIoTH2StreamClient = StreamSenderFactory.streamSender(deviceProfile);
            return;
        }
        C0910a.m327c(TAG, "H2 init params invalid, H2 ability will be unavailable.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> getExtraH2Params(Map<String, String> map) {
        if (map != null) {
            try {
                Id2Itls id2Itls = new Id2Itls();
                String iD2Id = id2Itls.getID2Id();
                map.put(Constraint.PARAM_ID2, iD2Id);
                String valueOf = String.valueOf(System.currentTimeMillis());
                String timestampAuthCode = id2Itls.getTimestampAuthCode(valueOf, null);
                map.put("param-sign", timestampAuthCode);
                C0910a.m325a(TAG, "id2=" + iD2Id + ", timestamp=" + valueOf + ", authCode=" + timestampAuthCode);
            } catch (Throwable unused) {
                C0910a.m327c(TAG, "itls id or authCode get failed.");
            }
        }
        return map;
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public void registerOnPushListener(IConnectNotifyListener iConnectNotifyListener) {
        C0910a.m326b(TAG, "registerOnPushListener() called with: listener = [" + iConnectNotifyListener + "]");
        C0906a.m321a().m322a(iConnectNotifyListener);
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public void unRegisterOnPushListener(IConnectNotifyListener iConnectNotifyListener) {
        C0910a.m326b(TAG, "unRegisterOnPushListener called.");
        C0906a.m321a().m323b(iConnectNotifyListener);
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public void deinit() {
        C0910a.m326b(TAG, "deinit() called");
        this.isInited.set(false);
        this.isIniting.set(false);
        this.isDeiniting.set(true);
        try {
            DeviceManager.getInstance().destroy();
        } catch (Exception unused) {
        }
        try {
            if (this.mIoTH2StreamClient != null) {
                this.mIoTH2StreamClient.disconnect(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.isDeiniting.set(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void internalDeinit() {
        C0910a.m325a(TAG, "internalDeinit() called.");
        deinit();
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public void publish(ARequest aRequest, IConnectSendListener iConnectSendListener) {
        C0910a.m326b(TAG, "publish() called with: request = [" + aRequest + "], listener = [" + iConnectSendListener + "]");
        ConnectSDK.getInstance().send(aRequest, iConnectSendListener);
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public void subscribe(ARequest aRequest, IConnectSubscribeListener iConnectSubscribeListener) {
        C0910a.m326b(TAG, "subscribe() called with: request = [" + aRequest + "], listener = [" + iConnectSubscribeListener + "]");
        ConnectSDK.getInstance().subscribe(ConnectSDK.getInstance().getPersistentConnectId(), aRequest, iConnectSubscribeListener);
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public void unsubscribe(ARequest aRequest, IConnectUnscribeListener iConnectUnscribeListener) {
        C0910a.m326b(TAG, "unsubscribe() called with: request = [" + aRequest + "], listener = [" + iConnectUnscribeListener + "]");
        ConnectSDK.getInstance().unsubscribe(ConnectSDK.getInstance().getPersistentConnectId(), aRequest, iConnectUnscribeListener);
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public void subscribeRRPC(ARequest aRequest, IConnectRrpcListener iConnectRrpcListener) {
        C0910a.m326b(TAG, "subscribeRRPC() called with: request = [" + aRequest + "], listener = [" + iConnectRrpcListener + "]");
        ConnectSDK.getInstance().subscribeRrpc(ConnectSDK.getInstance().getPersistentConnectId(), aRequest, iConnectRrpcListener);
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public IApiClient getIoTApiClient() {
        C0910a.m326b(TAG, "getIoTApiClient() called");
        return DeviceManager.getInstance().getIoTApiClient();
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public IDeviceCOTA getDeviceCOTA() {
        C0910a.m326b(TAG, "getDeviceCOTA() called");
        return DeviceManager.getInstance().getDeviceCOTA();
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public IGateway getGateway() {
        C0910a.m326b(TAG, "getGateway() called");
        return DeviceManager.getInstance().getGateway();
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public IDeviceShadow getDeviceShadow() {
        C0910a.m326b(TAG, "getDeviceShadow() called");
        return DeviceManager.getInstance().getDeviceShadow();
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public IDeviceLabel getDeviceLabel() {
        C0910a.m326b(TAG, "getDeviceLabel() called");
        return DeviceManager.getInstance().getDeviceLabel();
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public IThing getDeviceThing() {
        C0910a.m326b(TAG, "getDeviceThing() called");
        return DeviceManager.getInstance().getDeviceThing();
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public IStreamSender getH2StreamClient() {
        C0910a.m326b(TAG, "getH2StreamClient() called");
        return this.mIoTH2StreamClient;
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public IOta getOta() {
        C0910a.m326b(TAG, "getOta() called");
        return DeviceManager.getInstance().getOta();
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public String getToSignString(Map<String, String> map) {
        return SignUtils.getToSignString(map);
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public void deviceRegister(Context context, LinkKitInitParams linkKitInitParams, ARequest aRequest, IConnectSendListener iConnectSendListener) {
        C0910a.m326b(TAG, "deviceRegister() called with: context = [" + context + "], initParams = [" + linkKitInitParams + "], request = [" + aRequest + "], listener = [" + iConnectSendListener + "]");
        if (linkKitInitParams == null || linkKitInitParams.deviceInfo == null || TextUtils.isEmpty(linkKitInitParams.deviceInfo.productSecret) || TextUtils.isEmpty(linkKitInitParams.deviceInfo.productKey) || TextUtils.isEmpty(linkKitInitParams.deviceInfo.deviceName)) {
            C0910a.m328d(TAG, "deviceRegister params error.");
            if (iConnectSendListener != null) {
                iConnectSendListener.onFailure(aRequest, DMErrorCode.getErrorCode(LinkKitErrorCode.ERROR_SDK_INIT_ERROR, 120, "deviceRegisterParamsError"));
                return;
            }
            return;
        }
        if (aRequest instanceof HubApiRequest) {
            getIoTApiClient().init(context, linkKitInitParams.connectConfig == null ? new IoTApiClientConfig() : linkKitInitParams.connectConfig, linkKitInitParams.deviceInfo);
            getIoTApiClient().sendIoTHTTPRequest(aRequest, iConnectSendListener);
        } else if (iConnectSendListener != null) {
            AError aError = new AError();
            aError.setCode(-4);
            aError.setSubCode(402);
            aError.setMsg("request should be instance of HubApiRequest");
            iConnectSendListener.onFailure(aRequest, aError);
        }
    }

    @Override // com.aliyun.alink.linkkit.api.ILinkKit
    public void reset(final IConnectSendListener iConnectSendListener) {
        C0910a.m326b(TAG, "reset() called with: listener = [" + iConnectSendListener + "]");
        DeviceManager.getInstance().resetDevice(new IConnectSendListener() { // from class: com.aliyun.alink.linkkit.api.LinkKit.3
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onResponse(ARequest aRequest, AResponse aResponse) {
                C0910a.m326b(LinkKit.TAG, "reset success.");
                LinkKit.this.internalDeinit();
                IConnectSendListener iConnectSendListener2 = iConnectSendListener;
                if (iConnectSendListener2 != null) {
                    iConnectSendListener2.onResponse(aRequest, aResponse);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest aRequest, AError aError) {
                LinkKit.this.internalDeinit();
                IConnectSendListener iConnectSendListener2 = iConnectSendListener;
                if (iConnectSendListener2 != null) {
                    iConnectSendListener2.onFailure(aRequest, aError);
                }
            }
        });
    }

    private void setReportVersion() {
        C0910a.m325a(TAG, "setReportVersion() called 1.7.0-513adc9");
        try {
            PersistentNet persistentNet = PersistentNet.getInstance();
            Method declaredMethod = persistentNet.getClass().getDeclaredMethod("setReportVersion", String.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(persistentNet, "1.7.0");
        } catch (IllegalAccessException e) {
            C0910a.m325a(TAG, "setReportVersion IllegalAccessException e=" + e);
        } catch (NoSuchMethodException e2) {
            C0910a.m325a(TAG, "setReportVersion NoSuchMethodException e=" + e2);
        } catch (InvocationTargetException e3) {
            C0910a.m325a(TAG, "setReportVersion InvocationTargetException e=" + e3);
        } catch (Exception e4) {
            C0910a.m325a(TAG, "setReportVersion Exception e=" + e4);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    private static class SingletonHolder {
        private static final ILinkKit INSTANCE = new LinkKit();

        private SingletonHolder() {
        }
    }

    public static ILinkKit getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
