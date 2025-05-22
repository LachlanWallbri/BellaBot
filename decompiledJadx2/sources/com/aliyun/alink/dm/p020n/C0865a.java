package com.aliyun.alink.dm.p020n;

import android.text.TextUtils;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.alink.dm.api.BaseInfo;
import com.aliyun.alink.dm.api.DMErrorCode;
import com.aliyun.alink.dm.api.DeviceInfo;
import com.aliyun.alink.dm.api.IDMCallback;
import com.aliyun.alink.dm.api.IThing;
import com.aliyun.alink.dm.api.InitResult;
import com.aliyun.alink.dm.model.RequestModel;
import com.aliyun.alink.dm.p010d.C0852a;
import com.aliyun.alink.dm.p017k.C0859a;
import com.aliyun.alink.dm.p021o.C0868c;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tmp.api.DeviceBasicData;
import com.aliyun.alink.linksdk.tmp.api.DeviceManager;
import com.aliyun.alink.linksdk.tmp.api.IDevice;
import com.aliyun.alink.linksdk.tmp.api.OutputParams;
import com.aliyun.alink.linksdk.tmp.config.DefaultServerConfig;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.devicemodel.Event;
import com.aliyun.alink.linksdk.tmp.devicemodel.Property;
import com.aliyun.alink.linksdk.tmp.devicemodel.Service;
import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.listener.IDevRawDataListener;
import com.aliyun.alink.linksdk.tmp.listener.IPublishResourceListener;
import com.aliyun.alink.linksdk.tmp.listener.ITRawDataRequestHandler;
import com.aliyun.alink.linksdk.tmp.listener.ITResRequestHandler;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.linksdk.tools.AError;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ThingImpl.java */
/* renamed from: com.aliyun.alink.dm.n.a */
/* loaded from: classes.dex */
public class C0865a implements IThing {

    /* renamed from: b */
    BaseInfo f465b;

    /* renamed from: f */
    private boolean f469f;

    /* renamed from: a */
    IDevice f464a = null;

    /* renamed from: c */
    private String f466c = null;

    /* renamed from: d */
    private boolean f467d = false;

    /* renamed from: e */
    private boolean f468e = false;

    public C0865a(BaseInfo baseInfo, boolean z) {
        this.f465b = null;
        this.f469f = true;
        this.f465b = baseInfo;
        this.f469f = z;
    }

    @Override // com.aliyun.alink.dm.api.IThing
    public boolean isThingInited() {
        return this.f467d;
    }

    @Override // com.aliyun.alink.dm.api.IThing
    public List<Property> getProperties() {
        IDevice iDevice = this.f464a;
        if (iDevice == null) {
            return null;
        }
        return iDevice.getProperties();
    }

    @Override // com.aliyun.alink.dm.api.IThing
    public List<Service> getServices() {
        IDevice iDevice = this.f464a;
        if (iDevice == null) {
            return null;
        }
        return iDevice.getServices();
    }

    @Override // com.aliyun.alink.dm.api.IThing
    public List<Event> getEvents() {
        IDevice iDevice = this.f464a;
        if (iDevice == null) {
            return null;
        }
        return iDevice.getEvents();
    }

    @Override // com.aliyun.alink.dm.api.IThing
    public ValueWrapper getPropertyValue(String str) {
        IDevice iDevice = this.f464a;
        if (iDevice == null) {
            return null;
        }
        return iDevice.getPropertyValue(str);
    }

    @Override // com.aliyun.alink.dm.api.IThing
    public Map<String, ValueWrapper> getAllPropertyValue() {
        IDevice iDevice = this.f464a;
        if (iDevice == null) {
            return null;
        }
        return iDevice.getAllPropertyValue();
    }

    @Override // com.aliyun.alink.dm.api.IThing
    public void thingPropertyPost(Map<String, ValueWrapper> map, IPublishResourceListener iPublishResourceListener) {
        C0859a.m131a("ThingImpl", "thingPropertyPost() called with: params = [" + map + "], listener = [" + iPublishResourceListener + "]");
        IDevice iDevice = this.f464a;
        if (iDevice == null) {
            return;
        }
        iDevice.setPropertyValue(map, true, iPublishResourceListener);
    }

    @Override // com.aliyun.alink.dm.api.IThing
    public void setServiceHandler(String str, ITResRequestHandler iTResRequestHandler) {
        C0859a.m131a("ThingImpl", "setServiceHandler() called with: identifyID = [" + str + "], handler = [" + iTResRequestHandler + "]");
        IDevice iDevice = this.f464a;
        if (iDevice == null) {
            return;
        }
        iDevice.regRes(str, false, iTResRequestHandler);
    }

    @Override // com.aliyun.alink.dm.api.IThing
    public void thingEventPost(String str, OutputParams outputParams, IPublishResourceListener iPublishResourceListener) {
        C0859a.m131a("ThingImpl", "thingEventPost() called with: identifyID = [" + str + "], params = [" + outputParams + "], listenerer = [" + iPublishResourceListener + "]");
        IDevice iDevice = this.f464a;
        if (iDevice == null) {
            return;
        }
        iDevice.triggerRes(str, outputParams, iPublishResourceListener);
    }

    @Override // com.aliyun.alink.dm.api.IThing
    public void thingServiceRegister(String str, ITResRequestHandler iTResRequestHandler) {
        C0859a.m131a("ThingImpl", "thingServiceRegister() called with: identifyID = [" + str + "], handler = [" + iTResRequestHandler + "]");
        IDevice iDevice = this.f464a;
        if (iDevice == null) {
            return;
        }
        iDevice.regRes(str, false, iTResRequestHandler);
    }

    @Override // com.aliyun.alink.dm.api.IThing
    public void thingRawPropertiesPost(byte[] bArr, IDevRawDataListener iDevRawDataListener) {
        C0859a.m131a("ThingImpl", "thingRawPropertiesPost() called with: rawData = [" + bArr + "], listener = [" + iDevRawDataListener + "]");
        IDevice iDevice = this.f464a;
        if (iDevice == null) {
            return;
        }
        iDevice.sendRawData(bArr, iDevRawDataListener);
    }

    @Override // com.aliyun.alink.dm.api.IThing
    public void setRawPropertyChangeListener(boolean z, ITRawDataRequestHandler iTRawDataRequestHandler) {
        C0859a.m131a("ThingImpl", "setRawPropertyChangeListener() called with: isNeedAuth = [" + z + "], handler = [" + iTRawDataRequestHandler + "]");
        IDevice iDevice = this.f464a;
        if (iDevice == null) {
            return;
        }
        iDevice.regRawRes(false, iTRawDataRequestHandler);
    }

    @Override // com.aliyun.alink.dm.api.IThing
    public void thingUnubscribe(String str, ITResRequestHandler iTResRequestHandler) {
        C0859a.m131a("ThingImpl", "thingUnubscribe() called with: identifyId = [" + str + "], handler = [" + iTResRequestHandler + "]");
        IDevice iDevice = this.f464a;
        if (iDevice == null) {
            return;
        }
        iDevice.unRegRes(str, iTResRequestHandler);
    }

    @Override // com.aliyun.alink.dm.api.IThing
    public void uninit() {
        C0859a.m131a("ThingImpl", "uninit() called");
        try {
            this.f467d = false;
            this.f468e = false;
            if (this.f464a != null) {
                this.f464a.unInit();
            }
        } catch (Exception e) {
            C0859a.m134c("ThingImpl", "uninit exception=" + e);
        }
    }

    /* renamed from: a */
    private void m175a(BaseInfo baseInfo, IConnectSendListener iConnectSendListener) {
        C0859a.m131a("ThingImpl", "getTSL() called with: info = [" + baseInfo + "], listener = [" + iConnectSendListener + "]");
        if (baseInfo == null || TextUtils.isEmpty(baseInfo.productKey) || TextUtils.isEmpty(baseInfo.deviceName)) {
            C0859a.m134c("ThingImpl", "getTSL failed, baseInfo Empty.");
            if (iConnectSendListener != null) {
                AError aError = new AError();
                aError.setCode(DMErrorCode.ERROR_DM_GET_TSL_INFO_INVALID);
                aError.setMsg("BaseInfoEmpty.");
                iConnectSendListener.onFailure(null, aError);
                return;
            }
            return;
        }
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.topic = C0852a.f381j.replace(TmpConstant.URI_PRODUCT_PRODUCT_REPLACE, baseInfo.productKey).replace(TmpConstant.URI_PRODUCT_DEVICE_REPLACE, baseInfo.deviceName);
        mqttPublishRequest.replyTopic = C0852a.f382k.replace(TmpConstant.URI_PRODUCT_PRODUCT_REPLACE, baseInfo.productKey).replace(TmpConstant.URI_PRODUCT_DEVICE_REPLACE, baseInfo.deviceName);
        mqttPublishRequest.isRPC = true;
        RequestModel requestModel = new RequestModel();
        requestModel.f462id = String.valueOf(C0868c.m183a());
        requestModel.method = C0852a.f385n;
        requestModel.params = "{}";
        requestModel.version = "1.0";
        mqttPublishRequest.payloadObj = requestModel.toString();
        C0859a.m131a("ThingImpl", "getTSL: payloadObj=" + mqttPublishRequest.payloadObj);
        ConnectSDK.getInstance().send(mqttPublishRequest, iConnectSendListener);
    }

    /* renamed from: a */
    public void m180a(String str, final DeviceInfo deviceInfo, final Map<String, ValueWrapper> map, final IDMCallback<InitResult> iDMCallback) {
        C0859a.m131a("ThingImpl", "initThing() called with: tsl = [" + str + "], deviceInfo = [" + deviceInfo + "], propertyValues = [" + map + "], listener = [" + iDMCallback + "]");
        if (this.f467d) {
            C0859a.m134c("ThingImpl", "initThing inited, return.");
            if (iDMCallback != null) {
                InitResult initResult = new InitResult();
                initResult.tsl = this.f466c;
                iDMCallback.onSuccess(initResult);
                return;
            }
            return;
        }
        this.f467d = false;
        if (iDMCallback == null) {
            throw new RuntimeException("getTMPManagerListenerNull.");
        }
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.productKey) || TextUtils.isEmpty(deviceInfo.deviceName)) {
            AError aError = new AError();
            aError.setCode(DMErrorCode.ERROR_DM_INIT_THING_PARAMS_INVALID);
            aError.setMsg("getTMPManagerDeviceInfoNull.");
            iDMCallback.onFailure(aError);
            return;
        }
        if (this.f468e) {
            C0859a.m134c("ThingImpl", "initThing is initing, return.");
            AError aError2 = new AError();
            aError2.setCode(DMErrorCode.ERROR_DM_INIT_THING_FAILED_IS_INITING);
            aError2.setMsg("initThing is initing, return.");
            iDMCallback.onFailure(aError2);
            return;
        }
        this.f468e = true;
        this.f466c = str;
        if (TextUtils.isEmpty(str)) {
            try {
                m175a(deviceInfo, new IConnectSendListener() { // from class: com.aliyun.alink.dm.n.a.1
                    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                    public void onResponse(ARequest aRequest, AResponse aResponse) {
                        C0859a.m131a("ThingImpl", "onResponse() called with: aRequest = [" + aRequest + "], aResponse = [" + aResponse + "]");
                        try {
                            if ((aRequest instanceof MqttPublishRequest) && aResponse.data != null) {
                                C0865a.this.m178b(JSONObject.parseObject(aResponse.data.toString()).get("data").toString(), deviceInfo, map, iDMCallback);
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        C0865a.this.f467d = false;
                        C0865a.this.f468e = false;
                        AError aError3 = new AError();
                        aError3.setCode(DMErrorCode.DM_INIT_GET_TSL_RESPONSE_ERROR);
                        aError3.setMsg("onResponseDataError");
                        iDMCallback.onFailure(aError3);
                    }

                    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
                    public void onFailure(ARequest aRequest, AError aError3) {
                        C0859a.m131a("ThingImpl", "onFailure() called with: aRequest = [" + aRequest + "], aError = [" + aError3 + "]");
                        C0865a.this.f467d = false;
                        C0865a.this.f468e = false;
                        iDMCallback.onFailure(aError3);
                    }
                });
                return;
            } catch (Exception e) {
                e.printStackTrace();
                this.f467d = false;
                this.f468e = false;
                AError aError3 = new AError();
                aError3.setCode(DMErrorCode.ERROR_DM_GET_TSL_INFO_INVALID);
                aError3.setMsg("getTSLException." + e);
                iDMCallback.onFailure(aError3);
                return;
            }
        }
        try {
            m178b(str, deviceInfo, map, iDMCallback);
        } catch (Exception e2) {
            e2.printStackTrace();
            this.f467d = false;
            this.f468e = false;
            AError aError4 = new AError();
            aError4.setCode(DMErrorCode.ERROR_DM_GET_TMP_IDEVICE);
            aError4.setMsg("getIDeviceException." + e2);
            iDMCallback.onFailure(aError4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m178b(String str, DeviceInfo deviceInfo, Map<String, ValueWrapper> map, final IDMCallback<InitResult> iDMCallback) {
        C0859a.m131a("ThingImpl", "getIDevice() called with: tsl = [" + str + "], deviceInfo = [" + deviceInfo + "], propertyValues = [" + map + "], listener = [" + iDMCallback + "], enableLocalCommunication = [" + this.f469f + "]");
        this.f466c = str;
        DefaultServerConfig defaultServerConfig = new DefaultServerConfig();
        defaultServerConfig.mIotProductKey = deviceInfo.productKey;
        defaultServerConfig.mIotDeviceName = deviceInfo.deviceName;
        defaultServerConfig.mIotSecret = deviceInfo.deviceSecret;
        if (this.f469f) {
            C0859a.m131a("ThingImpl", "enableLocalCommunication=true.");
            defaultServerConfig.setConnectType(DefaultServerConfig.ConnectType.COAP_AND_MQTT);
        } else {
            defaultServerConfig.setConnectType(DefaultServerConfig.ConnectType.MQTT);
        }
        DeviceBasicData deviceBasicData = new DeviceBasicData();
        deviceBasicData.setProductKey(deviceInfo.productKey);
        deviceBasicData.setDeviceName(deviceInfo.deviceName);
        deviceBasicData.setDeviceModelJson(str);
        deviceBasicData.setLocal(true);
        deviceBasicData.setPort(5683);
        defaultServerConfig.setBasicData(deviceBasicData);
        defaultServerConfig.setPropertValues(map);
        IDevice createDevice = DeviceManager.getInstance().createDevice(defaultServerConfig);
        this.f464a = createDevice;
        createDevice.init(null, new IDevListener() { // from class: com.aliyun.alink.dm.n.a.2
            @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
            public void onSuccess(Object obj, OutputParams outputParams) {
                C0859a.m131a("ThingImpl", "onSuccess() called with: o = [" + obj + "], o1 = [" + outputParams + "]");
                C0865a.this.f467d = true;
                InitResult initResult = new InitResult();
                initResult.tsl = C0865a.this.f466c;
                iDMCallback.onSuccess(initResult);
                C0865a.this.f468e = false;
            }

            @Override // com.aliyun.alink.linksdk.tmp.listener.IDevListener
            public void onFail(Object obj, ErrorInfo errorInfo) {
                C0859a.m131a("ThingImpl", "onFail() called with: o = [" + obj + "], errorInfo = [" + errorInfo + "]");
                C0865a.this.f467d = false;
                AError aError = new AError();
                if (errorInfo != null) {
                    aError.setCode(errorInfo.getErrorCode());
                    aError.setMsg(errorInfo.getErrorMsg());
                }
                iDMCallback.onFailure(aError);
                C0865a.this.f468e = false;
            }
        });
    }
}
