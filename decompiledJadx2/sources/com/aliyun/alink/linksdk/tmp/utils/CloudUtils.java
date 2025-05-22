package com.aliyun.alink.linksdk.tmp.utils;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.cmp.api.CommonRequest;
import com.aliyun.alink.linksdk.cmp.api.ConnectSDK;
import com.aliyun.alink.linksdk.cmp.connect.apigw.ApiGatewayRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttRrpcRegisterRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttSubscribeRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.PersistentConnectConfig;
import com.aliyun.alink.linksdk.cmp.connect.channel.PersistentConnectInfo;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectNotifyListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.cmp.manager.connect.IRegisterConnectListener;
import com.aliyun.alink.linksdk.tmp.TmpSdk;
import com.aliyun.alink.linksdk.tmp.api.DevFoundOutputParams;
import com.aliyun.alink.linksdk.tmp.api.TmpInitConfig;
import com.aliyun.alink.linksdk.tmp.component.cloud.ICloudProxyListener;
import com.aliyun.alink.linksdk.tmp.connect.entity.cmp.CmpNotifyManager;
import com.aliyun.alink.linksdk.tmp.data.auth.AuthenRegisterReqPayload;
import com.aliyun.alink.linksdk.tmp.data.script.ScriptGetRequestPayload;
import com.aliyun.alink.linksdk.tmp.data.script.ScriptRequestItem;
import com.aliyun.alink.linksdk.tmp.data.service.UpdateDevInfoParam;
import com.aliyun.alink.linksdk.tmp.device.payload.CommonRequestPayload;
import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.device.payload.property.SetPropertyRequestPayload;
import com.aliyun.alink.linksdk.tools.AError;
import com.aliyun.alink.linksdk.tools.ALog;
import com.aliyun.iot.aep.sdk.apiclient.IoTAPIClientFactory;
import com.aliyun.iot.aep.sdk.apiclient.callback.IoTCallback;
import com.aliyun.iot.aep.sdk.apiclient.callback.IoTResponse;
import com.aliyun.iot.aep.sdk.apiclient.emuns.Scheme;
import com.aliyun.iot.aep.sdk.apiclient.request.IoTRequest;
import com.aliyun.iot.aep.sdk.apiclient.request.IoTRequestBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.bouncycastle.cms.CMSAttributeTableGenerator;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CloudUtils {
    private static OkHttpClient OKHttpClientInstance = new OkHttpClient();
    protected static final String TAG = "[Tmp]CloudUtils";

    public static void queryAccessInfo(String str, IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "queryAccessInfo iotId:" + str);
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        hashMap.put("iotIdList", arrayList);
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), ApiGatewayRequest.build("/alcs/device/accessInfo/get", "1.0.0", hashMap), iConnectSendListener);
    }

    public static void queryPrefixSecret(String str, String str2, String str3, String str4, IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "queryPrefx start product:" + str3 + " deviceName:" + str4 + " gateWayProductKey:" + str + " gateWayDeviceName:" + str2);
        HashMap hashMap = new HashMap();
        hashMap.put("productKey", str3);
        hashMap.put("deviceName", str4);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("id", 1);
        hashMap2.put("version", "1.0");
        hashMap2.put("method", "thing.lan.prefix.get");
        hashMap2.put("params", hashMap);
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.payloadObj = GsonUtils.toJson(hashMap2);
        mqttPublishRequest.topic = "/sys/" + str + "/" + str2 + "/thing/lan/prefix/get";
        StringBuilder sb = new StringBuilder();
        sb.append(mqttPublishRequest.topic);
        sb.append(TmpConstant.URI_TOPIC_REPLY_POST);
        mqttPublishRequest.replyTopic = sb.toString();
        mqttPublishRequest.isRPC = true;
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getPersistentConnectId(), mqttPublishRequest, iConnectSendListener);
    }

    public static void queryPrefixSecret(String str, String str2, IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "queryPrefx start product:" + str + " deviceName:" + str2);
        HashMap hashMap = new HashMap();
        hashMap.put("id", 1);
        hashMap.put("version", "1.0");
        hashMap.put("method", "thing.lan.prefix.get");
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.payloadObj = GsonUtils.toJson(hashMap);
        mqttPublishRequest.topic = "/sys/" + str + "/" + str2 + "/thing/lan/prefix/get";
        StringBuilder sb = new StringBuilder();
        sb.append(mqttPublishRequest.topic);
        sb.append(TmpConstant.URI_TOPIC_REPLY_POST);
        mqttPublishRequest.replyTopic = sb.toString();
        mqttPublishRequest.isRPC = true;
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getPersistentConnectId(), mqttPublishRequest, iConnectSendListener);
    }

    public static void getProperties(String str, IConnectSendListener iConnectSendListener) {
        HashMap hashMap = new HashMap();
        hashMap.put(TmpConstant.DEVICE_IOTID, str);
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), ApiGatewayRequest.build("/thing/properties/get", "1.0.0", hashMap), iConnectSendListener);
    }

    public static void setProperties(Map<String, Object> map, IConnectSendListener iConnectSendListener) {
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), ApiGatewayRequest.build("/thing/properties/set", "1.0.0", map), iConnectSendListener);
    }

    public static void getEvents(String str, IConnectSendListener iConnectSendListener) {
        HashMap hashMap = new HashMap();
        hashMap.put(TmpConstant.DEVICE_IOTID, str);
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), ApiGatewayRequest.build("/thing/events/get", "1.0.0", hashMap), iConnectSendListener);
    }

    public static void invokeService(Map<String, Object> map, IConnectSendListener iConnectSendListener) {
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), ApiGatewayRequest.build("/thing/service/invoke", "1.0.5", map), iConnectSendListener);
    }

    public static void getStatus(String str, IConnectSendListener iConnectSendListener) {
        HashMap hashMap = new HashMap();
        hashMap.put(TmpConstant.DEVICE_IOTID, str);
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), ApiGatewayRequest.build("/thing/status/get", "1.0.0", hashMap), iConnectSendListener);
    }

    public static void getTsl(String str, IConnectSendListener iConnectSendListener) {
        HashMap hashMap = new HashMap();
        hashMap.put(TmpConstant.DEVICE_IOTID, str);
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), ApiGatewayRequest.build("/thing/tsl/get", "1.0.2", hashMap), iConnectSendListener);
    }

    public static void subAllEvent(IConnectNotifyListener iConnectNotifyListener, int i) {
        CmpNotifyManager.getInstance().addHandler(i, ConnectSDK.getInstance().getPersistentConnectId(), TmpConstant.MQTT_TOPIC_PROPERTIES, iConnectNotifyListener);
        CmpNotifyManager.getInstance().addHandler(i, ConnectSDK.getInstance().getPersistentConnectId(), TmpConstant.MQTT_TOPIC_EVENTS, iConnectNotifyListener);
        CmpNotifyManager.getInstance().addHandler(i, ConnectSDK.getInstance().getPersistentConnectId(), TmpConstant.MQTT_TOPIC_STATUS, iConnectNotifyListener);
    }

    public static void subAllEvent(String str, String str2, IConnectNotifyListener iConnectNotifyListener, int i) {
        ALog.m479d(TAG, "subAllEvent prodKey:" + str + " deviceName: listener:" + iConnectNotifyListener);
        String str3 = "/sys/" + str + "/" + str2 + "/app/down/" + MqttTopic.MULTI_LEVEL_WILDCARD;
        MqttSubscribeRequest mqttSubscribeRequest = new MqttSubscribeRequest();
        mqttSubscribeRequest.topic = str3;
        mqttSubscribeRequest.isSubscribe = true;
        CmpNotifyManager.getInstance().addHandler(i, ConnectSDK.getInstance().getPersistentConnectId(), TmpConstant.MQTT_TOPIC_PROPERTIES, iConnectNotifyListener);
        CmpNotifyManager.getInstance().addHandler(i, ConnectSDK.getInstance().getPersistentConnectId(), TmpConstant.MQTT_TOPIC_EVENTS, iConnectNotifyListener);
        CmpNotifyManager.getInstance().addHandler(i, ConnectSDK.getInstance().getPersistentConnectId(), TmpConstant.MQTT_TOPIC_STATUS, iConnectNotifyListener);
        ConnectSDK.getInstance().subscribe(ConnectSDK.getInstance().getPersistentConnectId(), mqttSubscribeRequest, null);
    }

    public static void subStatusEvent(String str, String str2, int i, IConnectNotifyListener iConnectNotifyListener) {
        String str3 = "/sys/" + str + "/" + str2 + "/app/down/thing/status";
        CmpNotifyManager.getInstance().addHandler(i, ConnectSDK.getInstance().getPersistentConnectId(), str3, iConnectNotifyListener);
        MqttSubscribeRequest mqttSubscribeRequest = new MqttSubscribeRequest();
        mqttSubscribeRequest.topic = str3;
        mqttSubscribeRequest.isSubscribe = true;
        ConnectSDK.getInstance().subscribe(ConnectSDK.getInstance().getPersistentConnectId(), mqttSubscribeRequest, null);
    }

    public static void subPrefixUpdateRrpc(String str, String str2, IConnectRrpcListener iConnectRrpcListener) {
        StringBuilder sb = new StringBuilder("/");
        sb.append("sys");
        sb.append("/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        sb.append(TmpConstant.URI_PREFIX_UPDATE_POST);
        String sb2 = sb.toString();
        sb.append(TmpConstant.URI_PREFIX_UPDATE_REPLY_POST);
        sb.toString();
        subMqttTopicRrpc(sb2, iConnectRrpcListener);
    }

    public static void subBlacklistUpdateRrpc(String str, String str2, IConnectRrpcListener iConnectRrpcListener) {
        StringBuilder sb = new StringBuilder("/");
        sb.append("sys");
        sb.append("/");
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        sb.append(TmpConstant.URI_BLACKLIST_UPDATE_POST);
        String sb2 = sb.toString();
        sb.append(TmpConstant.URI_BLACKLIST_UPDATE_REPLY_POST);
        sb.toString();
        subMqttTopicRrpc(sb2, iConnectRrpcListener);
    }

    public static void subMqttTopicRrpc(String str, IConnectRrpcListener iConnectRrpcListener) {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.topic = str;
        ConnectSDK.getInstance().subscribeRrpc(ConnectSDK.getInstance().getPersistentConnectId(), commonRequest, iConnectRrpcListener);
    }

    public static void registerPersistentConnect(String str, String str2, String str3, IRegisterConnectListener iRegisterConnectListener) {
        ALog.m479d(TAG, "registerPersistentConnect iotProductKey:" + str + " iotDevName:" + str2);
        PersistentConnectConfig persistentConnectConfig = new PersistentConnectConfig();
        persistentConnectConfig.productKey = str;
        persistentConnectConfig.deviceName = str2;
        persistentConnectConfig.deviceSecret = str3;
        TmpInitConfig config = TmpSdk.getConfig();
        if (config != null) {
            persistentConnectConfig.channelHost = config.mMqttChannelHost;
            persistentConnectConfig.isCheckChannelRootCrt = config.mIsCheckChannelRootCrt;
        }
        ConnectSDK.getInstance().registerPersistentConnect(TmpSdk.getContext(), persistentConnectConfig, iRegisterConnectListener);
    }

    public static void createAlcsGroup(String str, IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "createAlcsGroup  groupName:" + str);
        HashMap hashMap = new HashMap();
        hashMap.put("groupName", str);
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), ApiGatewayRequest.build("/alcs/device/group/create", "1.0.0", hashMap), iConnectSendListener);
    }

    public static void deleteAlcsGroup(String str, IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "deleteAlcsGroup groupId:" + str);
        HashMap hashMap = new HashMap();
        hashMap.put("groupId", str);
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), ApiGatewayRequest.build("/alcs/device/group/delete", "1.0.0", hashMap), iConnectSendListener);
    }

    public static void addDeviceToAlcsGroup(String str, String str2, String str3, IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "addDeviceToAlcsGroup  iotId:" + str + " groupId:" + str3);
        HashMap hashMap = new HashMap();
        hashMap.put(TmpConstant.DEVICE_IOTID, str);
        hashMap.put("role", str2);
        hashMap.put("groupId", str3);
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), ApiGatewayRequest.build("/alcs/device/group/addDevice", "1.0.0", hashMap), iConnectSendListener);
    }

    public static void removeDeviceFromAlcsGroup(String str, String str2, String str3, IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "removeDeviceFromAlcsGroup  iotId:" + str + " groupId:" + str3);
        HashMap hashMap = new HashMap();
        hashMap.put(TmpConstant.DEVICE_IOTID, str);
        hashMap.put("role", str2);
        hashMap.put("groupId", str3);
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), ApiGatewayRequest.build("/alcs/device/group/removeDevice", "1.0.0", hashMap), iConnectSendListener);
    }

    public static void listGroup(IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "listGroup ");
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), ApiGatewayRequest.build("/alcs/device/group/list", "1.0.0", new HashMap()), iConnectSendListener);
    }

    public static void listDeviceInGroup(String str, IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "listDeviceInGroup groupId:" + str);
        HashMap hashMap = new HashMap();
        hashMap.put("groupId", str);
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), ApiGatewayRequest.build("/alcs/device/group/listDevice", "1.0.0", hashMap), iConnectSendListener);
    }

    public static void queryProductInfo(String str, IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "queryProductInfo iotid:" + str);
        HashMap hashMap = new HashMap();
        hashMap.put(TmpConstant.DEVICE_IOTID, str);
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), ApiGatewayRequest.build("/thing/detailInfo/queryProductInfo", "1.1.3", hashMap), iConnectSendListener);
    }

    public static void reportDeviceProperties(String str, String str2, Map<String, ValueWrapper> map, IConnectSendListener iConnectSendListener) {
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.topic = "/sys/" + str + "/" + str2 + "/" + TmpConstant.EVENT_PROPERTY_URI_PRE + "/post";
        StringBuilder sb = new StringBuilder();
        sb.append(mqttPublishRequest.topic);
        sb.append(TmpConstant.URI_TOPIC_REPLY_POST);
        mqttPublishRequest.replyTopic = sb.toString();
        SetPropertyRequestPayload setPropertyRequestPayload = new SetPropertyRequestPayload(null, null);
        setPropertyRequestPayload.setParams(map);
        mqttPublishRequest.payloadObj = GsonUtils.toJson(setPropertyRequestPayload);
        ConnectSDK.getInstance().send(mqttPublishRequest, iConnectSendListener);
    }

    public static void subPropertiesChange(String str, String str2, IConnectRrpcListener iConnectRrpcListener) {
        subMqttTopicRrpc("/sys/" + str + "/" + str2 + TmpConstant.URI_PROPERTY_SET, iConnectRrpcListener);
    }

    public static void listBindingByDev(String str, IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "listBindingByDev iotId:" + str);
        HashMap hashMap = new HashMap();
        hashMap.put(TmpConstant.DEVICE_IOTID, str);
        hashMap.put("pageNo", 1);
        hashMap.put("pageSize", 100);
        ApiGatewayRequest build = ApiGatewayRequest.build("/uc/listBindingByDev", "1.0.2", hashMap);
        build.scheme = Scheme.HTTPS;
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), build, iConnectSendListener);
    }

    public static void queryProductJsCode(String str, String str2, String str3, String str4, String str5, IConnectSendListener iConnectSendListener) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ScriptRequestItem(str3, str4, str5));
        ScriptGetRequestPayload scriptGetRequestPayload = new ScriptGetRequestPayload(arrayList);
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.payloadObj = GsonUtils.toJson(scriptGetRequestPayload);
        mqttPublishRequest.topic = "/sys/" + str + "/" + str2 + "/thing/script/get";
        StringBuilder sb = new StringBuilder();
        sb.append(mqttPublishRequest.topic);
        sb.append(TmpConstant.URI_TOPIC_REPLY_POST);
        mqttPublishRequest.replyTopic = sb.toString();
        mqttPublishRequest.isRPC = true;
        ALog.m479d(TAG, "queryProductJsCode productKey:" + str3 + " topicDeviceName：" + str2 + " topic：" + mqttPublishRequest.topic + " payload：" + mqttPublishRequest.payloadObj);
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getPersistentConnectId(), mqttPublishRequest, iConnectSendListener);
    }

    public static void queryProductJsCode(String str, String str2, String str3, IConnectSendListener iConnectSendListener) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        HashMap hashMap2 = new HashMap();
        hashMap2.put(TmpConstant.DEVICE_IOTID, str);
        hashMap2.put(CMSAttributeTableGenerator.DIGEST, str2);
        hashMap2.put("digestMethod", str3);
        arrayList.add(hashMap2);
        hashMap.put("scriptQuery", arrayList);
        ApiGatewayRequest build = ApiGatewayRequest.build("/thing/script/get", "1.0.4", hashMap);
        ALog.m479d(TAG, "queryProductJsCode iotId:" + str + " digest：" + str2 + " digestMethod：" + str3);
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), build, iConnectSendListener);
    }

    public static void queryHttpUrl(String str, Callback callback) {
        OKHttpClientInstance.newCall(new Request.Builder().url(str).get().build()).enqueue(callback);
    }

    public static void getProductKey(final String str, final ICloudProxyListener iCloudProxyListener) {
        HashMap hashMap = new HashMap();
        hashMap.put("productId", "" + str);
        new IoTAPIClientFactory().getClient().send(new IoTRequestBuilder().setApiVersion("1.1.4").setPath("/thing/productInfo/queryProductKey").setAuthType("iotAuth").setParams(hashMap).build(), new IoTCallback() { // from class: com.aliyun.alink.linksdk.tmp.utils.CloudUtils.1
            public void onFailure(IoTRequest ioTRequest, Exception exc) {
                ALog.m484w(CloudUtils.TAG, "onFailure" + exc.toString());
                ICloudProxyListener.this.onFailure(str, null);
            }

            public void onResponse(IoTRequest ioTRequest, IoTResponse ioTResponse) {
                try {
                    int code = ioTResponse.getCode();
                    Object data = ioTResponse.getData();
                    if (code != 200) {
                        String str2 = "onResponse. code:" + code + " data:" + data;
                        if (code == 200) {
                            ALog.m479d(CloudUtils.TAG, str2);
                        } else {
                            ALog.m484w(CloudUtils.TAG, str2);
                        }
                    }
                    if (code == 200 && data != null && !TextUtils.isEmpty(data.toString())) {
                        ICloudProxyListener.this.onResponse(String.valueOf(str), data);
                    } else {
                        ICloudProxyListener.this.onFailure(String.valueOf(str), null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ICloudProxyListener.this.onFailure(String.valueOf(str), null);
                }
            }
        });
    }

    public static void setDeviceExtendProperty(String str, String str2, String str3, IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "setDeviceExtendProperty iotId:" + str + " dataKey:" + str2 + " dataValue:" + str3);
        HashMap hashMap = new HashMap();
        hashMap.put(TmpConstant.DEVICE_IOTID, str);
        hashMap.put("dataKey", str2);
        hashMap.put("dataValue", str3);
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), ApiGatewayRequest.build("/thing/extended/property/set", "1.0.2", hashMap), iConnectSendListener);
    }

    public static void getDeviceExtendProperty(String str, String str2, IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "getDeviceExtendProperty iotId:" + str + " dataKey:" + str2);
        HashMap hashMap = new HashMap();
        hashMap.put(TmpConstant.DEVICE_IOTID, str);
        hashMap.put("dataKey", str2);
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), ApiGatewayRequest.build("/thing/extended/property/get", "1.0.2", hashMap), iConnectSendListener);
    }

    public static void subPushDeviceInfo(String str, String str2, IConnectRrpcListener iConnectRrpcListener) {
        PersistentConnectInfo persistentConnectInfo = (PersistentConnectInfo) ConnectSDK.getInstance().getConnectInfo(ConnectSDK.getInstance().getPersistentConnectId());
        MqttRrpcRegisterRequest mqttRrpcRegisterRequest = new MqttRrpcRegisterRequest();
        if (persistentConnectInfo == null) {
            iConnectRrpcListener.onSubscribeFailed(mqttRrpcRegisterRequest, new AError());
            return;
        }
        mqttRrpcRegisterRequest.topic = "/sys/" + persistentConnectInfo.productKey + "/" + persistentConnectInfo.deviceName + "/thing/push/device/info";
        StringBuilder sb = new StringBuilder();
        sb.append(mqttRrpcRegisterRequest.topic);
        sb.append(TmpConstant.URI_TOPIC_REPLY_POST);
        mqttRrpcRegisterRequest.replyTopic = sb.toString();
        ConnectSDK.getInstance().subscribeRrpc(ConnectSDK.getInstance().getPersistentConnectId(), mqttRrpcRegisterRequest, iConnectRrpcListener);
    }

    public static void subDevAuthenRegister(String str, String str2, IConnectSendListener iConnectSendListener) {
        PersistentConnectInfo persistentConnectInfo = (PersistentConnectInfo) ConnectSDK.getInstance().getConnectInfo(ConnectSDK.getInstance().getPersistentConnectId());
        String formatReplaceTopic = TextHelper.formatReplaceTopic(TmpConstant.URI_AUTHEN_REGISTER, persistentConnectInfo.productKey, persistentConnectInfo.deviceName);
        String formatReplaceTopic2 = TextHelper.formatReplaceTopic(TmpConstant.URI_AUTHEN_REGISTER_REPLY, persistentConnectInfo.productKey, persistentConnectInfo.deviceName);
        AuthenRegisterReqPayload authenRegisterReqPayload = new AuthenRegisterReqPayload();
        authenRegisterReqPayload.f1017id = 123;
        authenRegisterReqPayload.method = "thing.authen.sub.register";
        authenRegisterReqPayload.version = "1.0";
        authenRegisterReqPayload.params = new ArrayList();
        authenRegisterReqPayload.params.add(new AuthenRegisterReqPayload.AuthenRegisterParams(str, str2));
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.payloadObj = GsonUtils.toJson(authenRegisterReqPayload);
        mqttPublishRequest.topic = formatReplaceTopic;
        mqttPublishRequest.replyTopic = formatReplaceTopic2;
        mqttPublishRequest.isRPC = true;
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getPersistentConnectId(), mqttPublishRequest, iConnectSendListener);
    }

    public static void updateBreezeMac(String str, String str2, String str3, IConnectSendListener iConnectSendListener) {
        registerPersistentConnect(null, null, null, null);
        String formatReplaceTopic = TextHelper.formatReplaceTopic(TmpConstant.URI_UPDATE_DEVICE_INFO, str, str2);
        String formatReplaceTopic2 = TextHelper.formatReplaceTopic(TmpConstant.URI_UPDATE_DEVICE_INFO_REPLY, str, str2);
        UpdateDevInfoParam updateDevInfoParam = new UpdateDevInfoParam();
        updateDevInfoParam.attrKey = TmpConstant.DATA_KEY_DEVICENAME;
        updateDevInfoParam.attrValue = str3;
        ArrayList arrayList = new ArrayList();
        arrayList.add(updateDevInfoParam);
        CommonRequestPayload commonRequestPayload = new CommonRequestPayload();
        commonRequestPayload.setId("123");
        commonRequestPayload.setMethod("thing.deviceinfo.update");
        commonRequestPayload.setVersion("1.0");
        commonRequestPayload.setParams(arrayList);
        MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
        mqttPublishRequest.payloadObj = GsonUtils.toJson(commonRequestPayload);
        mqttPublishRequest.topic = formatReplaceTopic;
        mqttPublishRequest.replyTopic = formatReplaceTopic2;
        mqttPublishRequest.isRPC = true;
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getPersistentConnectId(), mqttPublishRequest, iConnectSendListener);
    }

    public static void getLcaDeviceList(String str, String str2, IConnectSendListener iConnectSendListener) {
        ALog.m479d(TAG, "getLcaDeviceList gatewayIotId:" + str2 + " groupId:" + str + " listener:" + iConnectSendListener);
        HashMap hashMap = new HashMap();
        hashMap.put("pageNo", 1);
        hashMap.put("pageSize", 1000);
        hashMap.put("groupId", str);
        hashMap.put(DevFoundOutputParams.PARAMS_GATEWAY_IOTID, str2);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("request", hashMap);
        ApiGatewayRequest build = ApiGatewayRequest.build("/awss/enrollee/lca/product/list", "1.0.5", hashMap2);
        build.scheme = Scheme.HTTPS;
        ConnectSDK.getInstance().send(ConnectSDK.getInstance().getApiGatewayConnectId(), build, iConnectSendListener);
    }
}
