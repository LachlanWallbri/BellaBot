package com.pudutech.robot.opensdk.aliyun;

import android.os.SystemClock;
import android.util.Log;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.amitshekhar.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.pdmqtt.OnPublishCallback;
import com.pudutech.pdmqtt.client.IMqttClient;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.aliyun.IotShadow;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowAuthConfig;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowAuthConfigSdkBean;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowData;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowMetadata;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowMetadataValue;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowResp;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowState;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowStateValue;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowUpdate;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowUpdateTime;
import com.pudutech.robot.opensdk.aliyun.topic.Constant;
import com.pudutech.robot.opensdk.bean.DeviceOnlineBody;
import com.pudutech.robot.opensdk.bean.IotDeviceState;
import com.pudutech.robot.opensdk.bean.pub.PubServiceGetDeviceOnlineState;
import com.pudutech.robot.opensdk.bean.pub.PubServiceInitShadow;
import com.pudutech.robot.opensdk.interf.IBody;
import com.pudutech.robot.opensdk.interf.ICallback;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: IotShadow.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001/B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0004J\u0015\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u001fJ\b\u0010 \u001a\u0004\u0018\u00010\u0011J \u0010!\u001a\u00020\u001b2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0004J!\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u00172\b\u0010'\u001a\u0004\u0018\u00010\u0017H\u0002¢\u0006\u0002\u0010(J\u0006\u0010)\u001a\u00020\u001bJ\b\u0010*\u001a\u00020\u001bH\u0002J\u000e\u0010+\u001a\u00020%2\u0006\u0010\u001c\u001a\u00020\u0004J\u0010\u0010,\u001a\u00020\u001b2\b\u0010-\u001a\u0004\u0018\u00010.R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R!\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u000bj\b\u0012\u0004\u0012\u00020\u0004`\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0012\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00140\u0013j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0014`\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010\u0016\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00170\u0013j\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0017`\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u00060"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/IotShadow;", "", "()V", "TAG", "", "dn", "gson", "Lcom/google/gson/Gson;", "mqttClient", "Lcom/pudutech/pdmqtt/client/IMqttClient;", "normalNotifyType", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getNormalNotifyType", "()Ljava/util/ArrayList;", Constants.f1200PK, "reportedState", "Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowStateValue;", "sdkDeviceStates", "Ljava/util/HashMap;", "Lcom/pudutech/robot/opensdk/aliyun/IotShadow$DeviceStateUpdate;", "Lkotlin/collections/HashMap;", "whiteDevices", "", "getWhiteDevices", "()Ljava/util/HashMap;", "activeSdkDeviceOnlineIfNeed", "", "id", "changeShadowConfig", "aResponse", "changeShadowConfig$robot_open_sdk_release", "getShadowConfig", "initShadow", "productKey", "deviceName", "isNeedUpdate", "", "desTime", "reportTime", "(Ljava/lang/Long;Ljava/lang/Long;)Z", "release", "requestSdkDeviceState", "sdkDeviceOnline", "updateSdkDeviceOnline", "deviceOnlineBody", "Lcom/pudutech/robot/opensdk/bean/DeviceOnlineBody;", "DeviceStateUpdate", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class IotShadow {
    private static IMqttClient mqttClient;
    private static ShadowStateValue reportedState;
    public static final IotShadow INSTANCE = new IotShadow();
    private static final String TAG = TAG;
    private static final String TAG = TAG;
    private static final Gson gson = new Gson();
    private static final HashMap<String, DeviceStateUpdate> sdkDeviceStates = new HashMap<>();
    private static String pk = "";
    private static String dn = "";
    private static final HashMap<String, Long> whiteDevices = new HashMap<>();
    private static final ArrayList<String> normalNotifyType = CollectionsKt.arrayListOf(Constant.INSTANCE.getMSG_TYPE_NOTIFY_QR_CODE_CONTENT$robot_open_sdk_release(), Constant.INSTANCE.getMSG_TYPE_NOTIFY_CUSTOM_CALL$robot_open_sdk_release());

    private IotShadow() {
    }

    public final HashMap<String, Long> getWhiteDevices() {
        return whiteDevices;
    }

    public final ArrayList<String> getNormalNotifyType() {
        return normalNotifyType;
    }

    public final void initShadow(IMqttClient mqttClient2, String productKey, String deviceName) {
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        mqttClient = mqttClient2;
        pk = productKey;
        dn = deviceName;
        if (mqttClient2 != null) {
            String shadowConfig = Constant.INSTANCE.getShadowConfig(productKey, deviceName);
            String json = gson.toJson(new ShadowUpdate(TmpConstant.PROPERTY_IDENTIFIER_GET, null, null, 6, null));
            Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(ShadowUpdate(\"get\"))");
            mqttClient2.publish(shadowConfig, json, new OnPublishCallback.Stub() { // from class: com.pudutech.robot.opensdk.aliyun.IotShadow$initShadow$1
                @Override // com.pudutech.pdmqtt.OnPublishCallback
                public void onSuccess(String topic, String payload) {
                    String str;
                    IotShadow iotShadow = IotShadow.INSTANCE;
                    str = IotShadow.TAG;
                    Pdlog.m3273d(str, "responseMsg onResponse() success");
                }

                @Override // com.pudutech.pdmqtt.OnPublishCallback
                public void onFailue(String topic, String payload, int code, String message) {
                    String str;
                    IotShadow iotShadow = IotShadow.INSTANCE;
                    str = IotShadow.TAG;
                    Pdlog.m3274e(str, "responseMsg onFailure() topic=" + topic + ", message=" + message);
                }
            });
        }
    }

    public final synchronized ShadowStateValue getShadowConfig() {
        return reportedState;
    }

    public final void changeShadowConfig$robot_open_sdk_release(String aResponse) {
        boolean z;
        ShadowMetadataValue reported;
        ShadowUpdateTime groupId;
        ShadowMetadataValue desired;
        ShadowUpdateTime groupId2;
        ShadowMetadataValue reported2;
        ShadowUpdateTime authConfig;
        ShadowMetadataValue desired2;
        ShadowUpdateTime authConfig2;
        Intrinsics.checkParameterIsNotNull(aResponse, "aResponse");
        try {
            Pdlog.m3273d(TAG, "shadowUpload onResponse : dataStr = " + aResponse);
            Object fromJson = gson.fromJson(aResponse, new TypeToken<ShadowResp>() { // from class: com.pudutech.robot.opensdk.aliyun.IotShadow$changeShadowConfig$type$1
            }.getType());
            Intrinsics.checkExpressionValueIsNotNull(fromJson, "gson.fromJson(dataStr, type)");
            ShadowResp shadowResp = (ShadowResp) fromJson;
            Pdlog.m3273d(TAG, "onResponse : shadowResponse.payload = " + shadowResp.getPayload());
            if (Intrinsics.areEqual(shadowResp.getPayload().getStatus(), "error")) {
                Pdlog.m3274e(TAG, "get shadow error");
                RobotOpenSdk.INSTANCE.publishMsg(new PubServiceInitShadow(), new ICallback() { // from class: com.pudutech.robot.opensdk.aliyun.IotShadow$changeShadowConfig$1
                    @Override // com.pudutech.robot.opensdk.interf.ICallback
                    public void onSuccess(IBody result) {
                        String str;
                        IotShadow iotShadow = IotShadow.INSTANCE;
                        str = IotShadow.TAG;
                        Pdlog.m3273d(str, "PubServiceInitShadowData onSuccess ");
                    }

                    @Override // com.pudutech.robot.opensdk.interf.ICallback
                    public void onFailed(Exception e) {
                        String str;
                        Intrinsics.checkParameterIsNotNull(e, "e");
                        IotShadow iotShadow = IotShadow.INSTANCE;
                        str = IotShadow.TAG;
                        Pdlog.m3274e(str, "PubServiceInitShadowData onFailed : " + Log.getStackTraceString(e));
                    }
                });
                return;
            }
            if (shadowResp.getPayload().getMetadata() == null) {
                return;
            }
            ShadowData payload = shadowResp.getPayload();
            ShadowMetadata metadata = payload.getMetadata();
            Long valueOf = (metadata == null || (desired2 = metadata.getDesired()) == null || (authConfig2 = desired2.getAuthConfig()) == null) ? null : Long.valueOf(authConfig2.getTimestamp());
            ShadowMetadata metadata2 = payload.getMetadata();
            if (isNeedUpdate(valueOf, (metadata2 == null || (reported2 = metadata2.getReported()) == null || (authConfig = reported2.getAuthConfig()) == null) ? null : Long.valueOf(authConfig.getTimestamp()))) {
                ShadowState state = payload.getState();
                if (state == null) {
                    Intrinsics.throwNpe();
                }
                if (state.getReported() == null) {
                    ShadowState state2 = payload.getState();
                    if (state2 == null) {
                        Intrinsics.throwNpe();
                    }
                    state2.setReported(new ShadowStateValue(null, null));
                }
                ShadowState state3 = payload.getState();
                if (state3 == null) {
                    Intrinsics.throwNpe();
                }
                ShadowStateValue reported3 = state3.getReported();
                if (reported3 == null) {
                    Intrinsics.throwNpe();
                }
                ShadowState state4 = payload.getState();
                ShadowStateValue desired3 = state4 != null ? state4.getDesired() : null;
                if (desired3 == null) {
                    Intrinsics.throwNpe();
                }
                reported3.setAuthConfig(desired3.getAuthConfig());
                z = true;
            } else {
                z = false;
            }
            ShadowMetadata metadata3 = payload.getMetadata();
            Long valueOf2 = (metadata3 == null || (desired = metadata3.getDesired()) == null || (groupId2 = desired.getGroupId()) == null) ? null : Long.valueOf(groupId2.getTimestamp());
            ShadowMetadata metadata4 = payload.getMetadata();
            if (isNeedUpdate(valueOf2, (metadata4 == null || (reported = metadata4.getReported()) == null || (groupId = reported.getGroupId()) == null) ? null : Long.valueOf(groupId.getTimestamp()))) {
                ShadowState state5 = payload.getState();
                if (state5 == null) {
                    Intrinsics.throwNpe();
                }
                ShadowStateValue reported4 = state5.getReported();
                if (reported4 == null) {
                    Intrinsics.throwNpe();
                }
                ShadowState state6 = payload.getState();
                ShadowStateValue desired4 = state6 != null ? state6.getDesired() : null;
                if (desired4 == null) {
                    Intrinsics.throwNpe();
                }
                reported4.setGroupId(desired4.getGroupId());
                z = true;
            }
            ShadowState state7 = payload.getState();
            if (state7 == null) {
                Intrinsics.throwNpe();
            }
            reportedState = state7.getReported();
            if (z) {
                long version = shadowResp.getVersion() + 1;
                ShadowState state8 = payload.getState();
                if (state8 == null) {
                    Intrinsics.throwNpe();
                }
                ShadowUpdate shadowUpdate = new ShadowUpdate("update", new ShadowState(null, state8.getReported()), Long.valueOf(version));
                IMqttClient iMqttClient = mqttClient;
                if (iMqttClient != null) {
                    String shadowConfig = Constant.INSTANCE.getShadowConfig(pk, dn);
                    String json = gson.toJson(shadowUpdate);
                    Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(shadowUpdate)");
                    iMqttClient.publish(shadowConfig, json, new OnPublishCallback.Stub() { // from class: com.pudutech.robot.opensdk.aliyun.IotShadow$changeShadowConfig$2
                        @Override // com.pudutech.pdmqtt.OnPublishCallback
                        public void onSuccess(String topic, String payload2) {
                            String str;
                            IotShadow iotShadow = IotShadow.INSTANCE;
                            str = IotShadow.TAG;
                            Pdlog.m3273d(str, "responseMsg onResponse() success");
                        }

                        @Override // com.pudutech.pdmqtt.OnPublishCallback
                        public void onFailue(String topic, String payload2, int code, String message) {
                            String str;
                            IotShadow iotShadow = IotShadow.INSTANCE;
                            str = IotShadow.TAG;
                            Pdlog.m3274e(str, "responseMsg onFailure() topic=" + topic + ", message=" + message);
                        }
                    });
                }
            } else {
                Pdlog.m3273d(TAG, "changeShadowConfig : do not need update ");
            }
            requestSdkDeviceState();
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "onResponse : " + Log.getStackTraceString(e));
        }
    }

    private final void requestSdkDeviceState() {
        ShadowAuthConfig authConfig;
        ArrayList<ShadowAuthConfigSdkBean> sdk;
        ShadowStateValue shadowStateValue = reportedState;
        if (shadowStateValue == null || (authConfig = shadowStateValue.getAuthConfig()) == null || (sdk = authConfig.getSdk()) == null) {
            return;
        }
        for (final ShadowAuthConfigSdkBean shadowAuthConfigSdkBean : sdk) {
            if (!sdkDeviceStates.containsKey(shadowAuthConfigSdkBean.getId())) {
                RobotOpenSdk.INSTANCE.publishMsg(shadowAuthConfigSdkBean.getId(), new PubServiceGetDeviceOnlineState(), new ICallback() { // from class: com.pudutech.robot.opensdk.aliyun.IotShadow$requestSdkDeviceState$1$1
                    @Override // com.pudutech.robot.opensdk.interf.ICallback
                    public void onSuccess(IBody result) {
                        String str;
                        HashMap hashMap;
                        if (result != null) {
                            DeviceOnlineBody deviceOnlineBody = (DeviceOnlineBody) result;
                            IotShadow iotShadow = IotShadow.INSTANCE;
                            str = IotShadow.TAG;
                            Pdlog.m3273d(str, "requestSdkDeviceState " + ShadowAuthConfigSdkBean.this.getId() + " onSuccess : result = " + result + "; ");
                            IotShadow iotShadow2 = IotShadow.INSTANCE;
                            hashMap = IotShadow.sdkDeviceStates;
                            hashMap.put(ShadowAuthConfigSdkBean.this.getId(), new IotShadow.DeviceStateUpdate(IotDeviceState.INSTANCE.parse(deviceOnlineBody.getState()), SystemClock.elapsedRealtime()));
                            return;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.robot.opensdk.bean.DeviceOnlineBody");
                    }

                    @Override // com.pudutech.robot.opensdk.interf.ICallback
                    public void onFailed(Exception e) {
                        String str;
                        Intrinsics.checkParameterIsNotNull(e, "e");
                        IotShadow iotShadow = IotShadow.INSTANCE;
                        str = IotShadow.TAG;
                        Pdlog.m3273d(str, "requestSdkDeviceState " + ShadowAuthConfigSdkBean.this.getId() + " onFailed : result = " + Log.getStackTraceString(e) + "; ");
                    }
                });
            }
        }
    }

    public final boolean sdkDeviceOnline(String id) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        DeviceStateUpdate deviceStateUpdate = sdkDeviceStates.get(id);
        return (deviceStateUpdate != null ? deviceStateUpdate.getState() : null) == IotDeviceState.ONLINE;
    }

    public final void updateSdkDeviceOnline(DeviceOnlineBody deviceOnlineBody) {
        Pdlog.m3273d(TAG, "updateSdkDeviceOnline : deviceOnlineBody = " + deviceOnlineBody + "; ");
        if (deviceOnlineBody == null) {
            return;
        }
        sdkDeviceStates.put(deviceOnlineBody.getSource(), new DeviceStateUpdate(IotDeviceState.INSTANCE.parse(deviceOnlineBody.getState()), SystemClock.elapsedRealtime()));
    }

    public final void activeSdkDeviceOnlineIfNeed(String id) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        if (id.length() == 0) {
            return;
        }
        DeviceStateUpdate deviceStateUpdate = sdkDeviceStates.get(id);
        if (deviceStateUpdate == null) {
            Pdlog.m3273d(TAG, "activeSdkDeviceOnlineIfNeed :add new sdk state, id = " + id + "; ");
            sdkDeviceStates.put(id, new DeviceStateUpdate(IotDeviceState.ONLINE, SystemClock.elapsedRealtime()));
            return;
        }
        if (deviceStateUpdate.getState() != IotDeviceState.ONLINE) {
            Pdlog.m3273d(TAG, "activeSdkDeviceOnlineIfNeed : change online , id = " + id + "; ");
            deviceStateUpdate.setState(IotDeviceState.ONLINE);
            deviceStateUpdate.setTime(SystemClock.elapsedRealtime());
        }
    }

    public final void release() {
        Pdlog.m3273d(TAG, "release ");
        mqttClient = (IMqttClient) null;
    }

    private final boolean isNeedUpdate(Long desTime, Long reportTime) {
        if (desTime == null) {
            return false;
        }
        return desTime.longValue() > (reportTime != null ? reportTime.longValue() : 0L);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: IotShadow.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/IotShadow$DeviceStateUpdate;", "", "state", "Lcom/pudutech/robot/opensdk/bean/IotDeviceState;", "time", "", "(Lcom/pudutech/robot/opensdk/bean/IotDeviceState;J)V", "getState", "()Lcom/pudutech/robot/opensdk/bean/IotDeviceState;", "setState", "(Lcom/pudutech/robot/opensdk/bean/IotDeviceState;)V", "getTime", "()J", "setTime", "(J)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final /* data */ class DeviceStateUpdate {
        private IotDeviceState state;
        private long time;

        public static /* synthetic */ DeviceStateUpdate copy$default(DeviceStateUpdate deviceStateUpdate, IotDeviceState iotDeviceState, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                iotDeviceState = deviceStateUpdate.state;
            }
            if ((i & 2) != 0) {
                j = deviceStateUpdate.time;
            }
            return deviceStateUpdate.copy(iotDeviceState, j);
        }

        /* renamed from: component1, reason: from getter */
        public final IotDeviceState getState() {
            return this.state;
        }

        /* renamed from: component2, reason: from getter */
        public final long getTime() {
            return this.time;
        }

        public final DeviceStateUpdate copy(IotDeviceState state, long time) {
            return new DeviceStateUpdate(state, time);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DeviceStateUpdate)) {
                return false;
            }
            DeviceStateUpdate deviceStateUpdate = (DeviceStateUpdate) other;
            return Intrinsics.areEqual(this.state, deviceStateUpdate.state) && this.time == deviceStateUpdate.time;
        }

        public int hashCode() {
            IotDeviceState iotDeviceState = this.state;
            return ((iotDeviceState != null ? iotDeviceState.hashCode() : 0) * 31) + Long.hashCode(this.time);
        }

        public String toString() {
            return "DeviceStateUpdate(state=" + this.state + ", time=" + this.time + ")";
        }

        public DeviceStateUpdate(IotDeviceState iotDeviceState, long j) {
            this.state = iotDeviceState;
            this.time = j;
        }

        public final IotDeviceState getState() {
            return this.state;
        }

        public final long getTime() {
            return this.time;
        }

        public final void setState(IotDeviceState iotDeviceState) {
            this.state = iotDeviceState;
        }

        public final void setTime(long j) {
            this.time = j;
        }
    }
}
