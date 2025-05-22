package com.pudutech.remotemaintenance.aliyun;

import androidx.core.app.NotificationCompat;
import com.alibaba.fastjson.JSON;
import com.aliyun.alink.linkkit.api.ILinkKitConnectListener;
import com.aliyun.alink.linkkit.api.IoTMqttClientConfig;
import com.aliyun.alink.linkkit.api.LinkKit;
import com.aliyun.alink.linkkit.api.LinkKitInitParams;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.MqttConfigure;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttPublishRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttRrpcRegisterRequest;
import com.aliyun.alink.linksdk.cmp.connect.channel.MqttRrpcRequest;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcHandle;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener;
import com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener;
import com.aliyun.alink.linksdk.tools.AError;
import com.pudutech.base.Pdlog;
import com.pudutech.remotemaintenance.App;
import com.pudutech.remotemaintenance.ReportTask;
import com.pudutech.remotemaintenance.aliyun.config.AliyunMsgType;
import com.pudutech.remotemaintenance.config.ConnectState;
import com.pudutech.remotemaintenance.handler.IMsgHandler;
import com.pudutech.remotemaintenance.handler.MsgHandlerFactory;
import com.pudutech.remotemaintenance.interf.IoTInterface;
import com.pudutech.remotemaintenance.listener.ConnectStateCallback;
import com.pudutech.remotemaintenance.net.IoTServerApiManager;
import com.pudutech.remotemaintenance.utils.SystemTool;
import com.pudutech.remotemaintenance.utils.preferences.CSharedPreferencesUtil;
import io.reactivex.disposables.Disposable;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.apache.commons.io.FilenameUtils;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: AliyunIoTManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0018*\u0002\u0005\u0012\u0018\u0000 >2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001>B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J.\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u001c\u001a\u00020\u00182\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020\u001bH\u0002J\b\u0010\"\u001a\u00020\u001bH\u0016J\b\u0010#\u001a\u00020\u001bH\u0016J\n\u0010$\u001a\u0004\u0018\u00010\fH\u0002J\b\u0010%\u001a\u00020\u001bH\u0002J\n\u0010&\u001a\u0004\u0018\u00010'H\u0002J\n\u0010(\u001a\u0004\u0018\u00010\u0002H\u0016J\n\u0010)\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010*\u001a\u00020\u001b2\u0006\u0010+\u001a\u00020\u0002H\u0002J\u0018\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0012\u0010-\u001a\u00020\u001b2\b\b\u0002\u0010.\u001a\u00020 H\u0002J\u0010\u0010/\u001a\u00020\u001b2\u0006\u00100\u001a\u00020\u001eH\u0002J\u0012\u00101\u001a\u00020\u001b2\b\u00102\u001a\u0004\u0018\u00010'H\u0002J\u0012\u00103\u001a\u00020\u001b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\u0012\u00104\u001a\u00020\u001b2\b\u0010+\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u00105\u001a\u00020\u001b2\b\u0010+\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u00106\u001a\u00020\u001b2\u0006\u00107\u001a\u00020 H\u0016J\u0012\u00108\u001a\u00020\u001b2\b\u0010+\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u00109\u001a\u00020\u001b2\u0006\u0010:\u001a\u00020\u0018H\u0016J\b\u0010;\u001a\u00020\u001bH\u0016J\b\u0010<\u001a\u00020\u001bH\u0002J\b\u0010=\u001a\u00020\u001bH\u0002R\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0002X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTManager;", "Lcom/pudutech/remotemaintenance/interf/IoTInterface;", "Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTMsg;", "()V", "connectRRPCListener", "com/pudutech/remotemaintenance/aliyun/AliyunIoTManager$connectRRPCListener$1", "Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTManager$connectRRPCListener$1;", "connectState", "Lcom/pudutech/remotemaintenance/config/ConnectState;", "connectStateCallback", "Lcom/pudutech/remotemaintenance/listener/ConnectStateCallback;", "deviceInfo", "Lcom/pudutech/remotemaintenance/aliyun/DeviceInfo;", "disposeGetAreaInfo", "Lio/reactivex/disposables/Disposable;", "disposeRegister", "mapSyncMsg", "msgSendStateListener", "com/pudutech/remotemaintenance/aliyun/AliyunIoTManager$msgSendStateListener$1", "Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTManager$msgSendStateListener$1;", "reportMsg", "reportTask", "Lcom/pudutech/remotemaintenance/ReportTask;", "requestAreaCount", "", "requestRegisterCount", "callbackConnectState", "", "errCode", "errMsg", "", "autoReconnect", "", "checkLocalAreaInfo", MqttServiceConstants.CONNECT_ACTION, MqttServiceConstants.DISCONNECT_ACTION, "getDeviceInfo", "getIotAreaInfo", "getLocalAreaInfo", "Lcom/pudutech/remotemaintenance/aliyun/AreaInfo;", "getMapSyncMsg", "getReportMsg", "handleMsg", NotificationCompat.CATEGORY_MESSAGE, "init", "reconnect", "isFail", "register", "url", "saveAreaInfo", "areaInfo", "saveDeviceInfo", "sendMsg", "setMapSyncMsg", "setNetworkAvailable", "isNetworkAvailable", "setReportMsg", "startReport", "count", "stopReport", "subscribeRRPC", "toServer", "Companion", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class AliyunIoTManager implements IoTInterface<AliyunIoTMsg> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final AliyunIoTManager INSTANCE = Companion.SingletonHolder.INSTANCE.getHolder();
    public static final String KEY_AREA_INFO = "key_area_info";
    public static final String KEY_AREA_INFO_TEST = "key_area_info_test";
    public static final String KEY_DEVICE_INFO = "key_device_info";
    public static final String KEY_DEVICE_INFO_TEST = "key_device_info_test";
    public static final String TAG = "AliyunIoTManager";
    private AliyunIoTManager$connectRRPCListener$1 connectRRPCListener;
    private ConnectState connectState;
    private ConnectStateCallback connectStateCallback;
    private DeviceInfo deviceInfo;
    private Disposable disposeGetAreaInfo;
    private Disposable disposeRegister;
    private AliyunIoTMsg mapSyncMsg;
    private AliyunIoTManager$msgSendStateListener$1 msgSendStateListener;
    private AliyunIoTMsg reportMsg;
    private ReportTask reportTask;
    private int requestAreaCount;
    private int requestRegisterCount;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ConnectState.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[ConnectState.CONNECTING.ordinal()] = 1;
            $EnumSwitchMapping$0[ConnectState.CONNECTED.ordinal()] = 2;
            $EnumSwitchMapping$0[ConnectState.CONNECT_FAILURE.ordinal()] = 3;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.pudutech.remotemaintenance.aliyun.AliyunIoTManager$connectRRPCListener$1] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.pudutech.remotemaintenance.aliyun.AliyunIoTManager$msgSendStateListener$1] */
    private AliyunIoTManager() {
        this.connectState = ConnectState.CONNECT_FAILURE;
        this.connectRRPCListener = new IConnectRrpcListener() { // from class: com.pudutech.remotemaintenance.aliyun.AliyunIoTManager$connectRRPCListener$1
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onSubscribeSuccess(ARequest aRequest) {
                if (aRequest == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.aliyun.alink.linksdk.cmp.connect.channel.MqttRrpcRegisterRequest");
                }
                Pdlog.m3273d(AliyunIoTManager.TAG, "connectRRPCListener onSubscribeSuccess() aRequest[" + JSON.toJSONString(aRequest) + "], topic[" + ((MqttRrpcRegisterRequest) aRequest).topic + ']');
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onSubscribeFailed(ARequest aRequest, AError aError) {
                if (aRequest == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.aliyun.alink.linksdk.cmp.connect.channel.MqttRrpcRegisterRequest");
                }
                Pdlog.m3273d(AliyunIoTManager.TAG, "connectRRPCListener onSubscribeFailed() aRequest[$" + aRequest + "], aError[" + aError + "], topic[" + ((MqttRrpcRegisterRequest) aRequest).topic + ']');
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onReceived(ARequest aRequest, IConnectRrpcHandle handle) {
                try {
                    if (aRequest != null) {
                        MqttRrpcRequest mqttRrpcRequest = (MqttRrpcRequest) aRequest;
                        Object obj = mqttRrpcRequest.payloadObj;
                        if (obj != null) {
                            String str = new String((byte[]) obj, Charsets.UTF_8);
                            Pdlog.m3273d(AliyunIoTManager.TAG, "connectRRPCListener onReceived() aRequest[" + JSON.toJSONString(aRequest) + "], handle[$" + String.valueOf(handle) + "], content[" + str + ']');
                            AliyunIoTMsg msg = (AliyunIoTMsg) JSON.parseObject(str, AliyunIoTMsg.class);
                            if (Intrinsics.areEqual(msg.getType(), AliyunMsgType.RRPC.getType())) {
                                AResponse aResponse = new AResponse();
                                aResponse.data = "{\"ret\":0}";
                                String str2 = mqttRrpcRequest.topic;
                                Intrinsics.checkExpressionValueIsNotNull(str2, "request.topic");
                                String replace$default = StringsKt.replace$default(str2, "request", "response", false, 4, (Object) null);
                                if (handle != null) {
                                    handle.onRrpcResponse(replace$default, aResponse);
                                }
                            }
                            AliyunIoTManager aliyunIoTManager = AliyunIoTManager.this;
                            Intrinsics.checkExpressionValueIsNotNull(msg, "msg");
                            aliyunIoTManager.handleMsg(msg);
                            return;
                        }
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.ByteArray");
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.aliyun.alink.linksdk.cmp.connect.channel.MqttRrpcRequest");
                } catch (Exception e) {
                    e.printStackTrace();
                    Pdlog.m3274e(AliyunIoTManager.TAG, e);
                }
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onResponseSuccess(ARequest aRequest) {
                Pdlog.m3273d(AliyunIoTManager.TAG, "connectRRPCListener onResponseSuccess() aRequest[" + String.valueOf(aRequest));
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectRrpcListener
            public void onResponseFailed(ARequest aRequest, AError aError) {
                Pdlog.m3273d(AliyunIoTManager.TAG, "connectRRPCListener onResponseFailed() aRequest[" + JSON.toJSONString(aRequest) + "], aError[" + JSON.toJSONString(aError) + ']');
            }
        };
        this.msgSendStateListener = new IConnectSendListener() { // from class: com.pudutech.remotemaintenance.aliyun.AliyunIoTManager$msgSendStateListener$1
            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onResponse(ARequest aRequest, AResponse aResponse) {
                Pdlog.m3273d(AliyunIoTManager.TAG, "msgSendStateListener onResponse() aRequest[" + JSON.toJSONString(aRequest) + "], aResponse[" + JSON.toJSONString(aResponse) + ']');
            }

            @Override // com.aliyun.alink.linksdk.cmp.core.listener.IConnectSendListener
            public void onFailure(ARequest aRequest, AError aError) {
                Pdlog.m3273d(AliyunIoTManager.TAG, "msgSendStateListener onResponse() aRequest[" + JSON.toJSONString(aRequest) + "], aError[" + JSON.toJSONString(aError) + ']');
            }
        };
    }

    public /* synthetic */ AliyunIoTManager(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: AliyunIoTManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTManager$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTManager;", "getINSTANCE", "()Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTManager;", "KEY_AREA_INFO", "", "KEY_AREA_INFO_TEST", "KEY_DEVICE_INFO", "KEY_DEVICE_INFO_TEST", "TAG", "SingletonHolder", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AliyunIoTManager getINSTANCE() {
            return AliyunIoTManager.INSTANCE;
        }

        /* compiled from: AliyunIoTManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTManager$Companion$SingletonHolder;", "", "()V", "holder", "Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTManager;", "getHolder", "()Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTManager;", "setHolder", "(Lcom/pudutech/remotemaintenance/aliyun/AliyunIoTManager;)V", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes.dex */
        private static final class SingletonHolder {
            public static final SingletonHolder INSTANCE = new SingletonHolder();
            private static AliyunIoTManager holder = new AliyunIoTManager(null);

            private SingletonHolder() {
            }

            public final AliyunIoTManager getHolder() {
                return holder;
            }

            public final void setHolder(AliyunIoTManager aliyunIoTManager) {
                Intrinsics.checkParameterIsNotNull(aliyunIoTManager, "<set-?>");
                holder = aliyunIoTManager;
            }
        }
    }

    @Override // com.pudutech.remotemaintenance.interf.IoTInterface
    public IoTInterface<AliyunIoTMsg> init(ConnectStateCallback connectStateCallback) {
        this.connectStateCallback = connectStateCallback;
        MqttConfigure.automaticReconnect = false;
        return this;
    }

    @Override // com.pudutech.remotemaintenance.interf.IoTInterface
    public void connect() {
        reconnect$default(this, false, 1, null);
    }

    @Override // com.pudutech.remotemaintenance.interf.IoTInterface
    public void sendMsg(AliyunIoTMsg msg) {
        if (msg == null) {
            Pdlog.m3274e(TAG, "sendMsg() failure, msg is null.");
            return;
        }
        String mac = msg.getMac();
        if (mac == null) {
            mac = SystemTool.INSTANCE.getMac(App.INSTANCE.getInstance());
        }
        msg.setMac(mac);
        msg.setTopicType("/user/report");
        DeviceInfo deviceInfo = this.deviceInfo;
        if (deviceInfo != null) {
            MqttPublishRequest mqttPublishRequest = new MqttPublishRequest();
            mqttPublishRequest.topic = '/' + deviceInfo.getProductKey() + '/' + deviceInfo.getDeviceName() + "/user/report";
            mqttPublishRequest.replyTopic = "";
            mqttPublishRequest.payloadObj = JSON.toJSONString(msg);
            LinkKit.getInstance().publish(mqttPublishRequest, this.msgSendStateListener);
            Pdlog.m3273d(TAG, "sendMsg successful, msg[" + msg + ']');
        }
    }

    @Override // com.pudutech.remotemaintenance.interf.IoTInterface
    public void disconnect() {
        Pdlog.m3277w(TAG, "断开连接");
        this.requestAreaCount = -1;
        this.requestRegisterCount = -1;
        Disposable disposable = this.disposeGetAreaInfo;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposable2 = this.disposeRegister;
        if (disposable2 != null) {
            disposable2.dispose();
        }
        Disposable disposable3 = (Disposable) null;
        this.disposeGetAreaInfo = disposable3;
        this.disposeRegister = disposable3;
        stopReport();
        LinkKit.getInstance().deinit();
        callbackConnectState(ConnectState.CONNECT_FAILURE, -2, "主动断开连接", false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toServer() {
        String str;
        DeviceInfo deviceInfo = this.deviceInfo;
        if (deviceInfo != null) {
            String productKey = deviceInfo.getProductKey();
            String deviceName = deviceInfo.getDeviceName();
            String deviceSecret = deviceInfo.getDeviceSecret();
            String str2 = productKey;
            if (!(str2 == null || str2.length() == 0)) {
                String str3 = deviceName;
                if (!(str3 == null || str3.length() == 0)) {
                    String str4 = deviceSecret;
                    if (!(str4 == null || str4.length() == 0)) {
                        com.aliyun.alink.dm.api.DeviceInfo deviceInfo2 = new com.aliyun.alink.dm.api.DeviceInfo();
                        deviceInfo2.productKey = productKey;
                        deviceInfo2.deviceName = deviceName;
                        deviceInfo2.deviceSecret = deviceSecret;
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        IoTMqttClientConfig ioTMqttClientConfig = new IoTMqttClientConfig(productKey, deviceName, deviceSecret);
                        LinkKitInitParams linkKitInitParams = new LinkKitInitParams();
                        linkKitInitParams.deviceInfo = deviceInfo2;
                        linkKitInitParams.propertyValues = linkedHashMap;
                        linkKitInitParams.mqttClientConfig = ioTMqttClientConfig;
                        StringBuilder sb = new StringBuilder();
                        sb.append(productKey);
                        sb.append(".iot-as-mqtt.");
                        AreaInfo localAreaInfo = getLocalAreaInfo();
                        if (localAreaInfo == null || (str = localAreaInfo.getRegion_id()) == null) {
                            str = "cn-shanghai";
                        }
                        sb.append(str);
                        sb.append(".aliyuncs.com:1883");
                        MqttConfigure.mqttHost = sb.toString();
                        LinkKit.getInstance().init(App.INSTANCE.getInstance(), linkKitInitParams, new ILinkKitConnectListener() { // from class: com.pudutech.remotemaintenance.aliyun.AliyunIoTManager$toServer$$inlined$let$lambda$1
                            @Override // com.aliyun.alink.linkkit.api.ILinkKitConnectListener
                            public void onInitDone(Object obj) {
                                Pdlog.m3273d(AliyunIoTManager.TAG, "toServer()->init()->onInitDone() connect successful, data[" + obj + "].");
                                AliyunIoTManager.callbackConnectState$default(AliyunIoTManager.this, ConnectState.CONNECTED, 0, null, false, 14, null);
                                AliyunIoTManager.this.subscribeRRPC();
                            }

                            @Override // com.aliyun.alink.linkkit.api.ILinkKitConnectListener
                            public void onError(AError aError) {
                                String str5;
                                Object[] objArr = new Object[1];
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("toServer()->init()->onError() connect failure, errCode = ");
                                sb2.append(aError != null ? Integer.valueOf(aError.getCode()) : null);
                                sb2.append(", errMsg = ");
                                sb2.append(aError != null ? aError.getMsg() : null);
                                sb2.append(FilenameUtils.EXTENSION_SEPARATOR);
                                objArr[0] = sb2.toString();
                                Pdlog.m3273d(AliyunIoTManager.TAG, objArr);
                                int code = aError != null ? aError.getCode() : -1;
                                if (aError == null || (str5 = aError.getMsg()) == null) {
                                    str5 = "unknown reason.";
                                }
                                AliyunIoTManager.callbackConnectState$default(AliyunIoTManager.this, ConnectState.CONNECT_FAILURE, code, str5, false, 8, null);
                            }
                        });
                        return;
                    }
                }
            }
            Pdlog.m3274e(TAG, "toServer() connect failure, please check productKey|deviceName|deviceSecret is null or empty.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void callbackConnectState$default(AliyunIoTManager aliyunIoTManager, ConnectState connectState, int i, String str, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = -1;
        }
        if ((i2 & 4) != 0) {
            str = "unknown reason.";
        }
        if ((i2 & 8) != 0) {
            z = true;
        }
        aliyunIoTManager.callbackConnectState(connectState, i, str, z);
    }

    private final void callbackConnectState(ConnectState connectState, int errCode, String errMsg, boolean autoReconnect) {
        this.connectState = connectState;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AliyunIoTManager$callbackConnectState$1(this, connectState, errCode, errMsg, autoReconnect, null), 2, null);
    }

    static /* synthetic */ void reconnect$default(AliyunIoTManager aliyunIoTManager, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        aliyunIoTManager.reconnect(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reconnect(boolean isFail) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AliyunIoTManager$reconnect$1(this, isFail, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getIotAreaInfo() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AliyunIoTManager$getIotAreaInfo$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void checkLocalAreaInfo() {
        Pdlog.m3273d(TAG, "checkLocalAreaInfo");
        AreaInfo localAreaInfo = getLocalAreaInfo();
        this.deviceInfo = getDeviceInfo();
        Pdlog.m3273d(TAG, "checkLocalAreaInfo--" + String.valueOf(localAreaInfo) + " -- " + String.valueOf(this.deviceInfo));
        if (localAreaInfo == null && this.deviceInfo != null) {
            Pdlog.m3274e(TAG, "节点信息为空,密钥信息不为空,说明获取节点失败,且未存储过节点,直接连接");
            toServer();
            return;
        }
        String str = IoTServerApiManager.DEBUG_URL;
        if (localAreaInfo == null) {
            Pdlog.m3274e(TAG, "节点信息为空,密钥信息为空,说明获取节点失败,使用上海节点注册");
            if (!IoTServerApiManager.INSTANCE.isTestServer()) {
                str = IoTServerApiManager.PRODUCT_URL;
            }
            register(str);
            return;
        }
        DeviceInfo deviceInfo = this.deviceInfo;
        if (deviceInfo != null) {
            String region_id = deviceInfo != null ? deviceInfo.getRegion_id() : null;
            if (region_id == null || region_id.length() == 0) {
                if (Intrinsics.areEqual(localAreaInfo.getRegion_id(), "cn-shanghai")) {
                    Pdlog.m3274e(TAG, "当前节点为上海,密钥信息不为空且不包含节点数据,直接连接");
                    toServer();
                    return;
                }
                Pdlog.m3274e(TAG, "当前节点不为上海,密钥信息不为空且不包含节点数据,使用新节点重新注册");
                String url = localAreaInfo.getUrl();
                if (url != null) {
                    str = url;
                } else if (!IoTServerApiManager.INSTANCE.isTestServer()) {
                    str = IoTServerApiManager.PRODUCT_URL;
                }
                register(str);
                return;
            }
        }
        DeviceInfo deviceInfo2 = this.deviceInfo;
        if (deviceInfo2 != null) {
            if (Intrinsics.areEqual(deviceInfo2 != null ? deviceInfo2.getRegion_id() : null, localAreaInfo.getRegion_id())) {
                Pdlog.m3274e(TAG, "存储的密钥数据就是该节点的密钥数据,直接连接");
                toServer();
                return;
            }
        }
        Pdlog.m3274e(TAG, "密钥数据为空或当前节点不对应,重新注册");
        String url2 = localAreaInfo.getUrl();
        if (url2 != null) {
            str = url2;
        } else if (!IoTServerApiManager.INSTANCE.isTestServer()) {
            str = IoTServerApiManager.PRODUCT_URL;
        }
        register(str);
    }

    private final void register(String url) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AliyunIoTManager$register$1(this, url, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void subscribeRRPC() {
        DeviceInfo deviceInfo = this.deviceInfo;
        if (deviceInfo != null) {
            MqttRrpcRegisterRequest mqttRrpcRegisterRequest = new MqttRrpcRegisterRequest();
            String str = "/sys/" + deviceInfo.getProductKey() + '/' + deviceInfo.getDeviceName() + "/rrpc/request/+";
            Pdlog.m3273d(TAG, "topic[" + str + ']');
            mqttRrpcRegisterRequest.topic = str;
            LinkKit.getInstance().subscribeRRPC(mqttRrpcRegisterRequest, this.connectRRPCListener);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleMsg(AliyunIoTMsg msg) {
        try {
            String instruct = msg.getInstruct();
            if (instruct != null) {
                IMsgHandler<AliyunIoTMsg, AliyunIoTManager> handler = MsgHandlerFactory.INSTANCE.getHandler(instruct);
                if (handler == null) {
                    Pdlog.m3277w(TAG, "cannot find message handler, msgType[" + instruct + ']');
                    return;
                }
                Pdlog.m3277w(TAG, "Find the message handler, is being distributed to [" + handler + "] processing");
                handler.execute(msg, this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveDeviceInfo(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return;
        }
        try {
            if (IoTServerApiManager.INSTANCE.isTestServer()) {
                CSharedPreferencesUtil.getInstance().put(KEY_DEVICE_INFO_TEST, JSON.toJSONString(deviceInfo));
            } else {
                CSharedPreferencesUtil.getInstance().put(KEY_DEVICE_INFO, JSON.toJSONString(deviceInfo));
            }
        } catch (Exception e) {
            Pdlog.m3273d(TAG, "saveDeviceInfo--" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveAreaInfo(AreaInfo areaInfo) {
        if (areaInfo == null) {
            return;
        }
        try {
            if (IoTServerApiManager.INSTANCE.isTestServer()) {
                CSharedPreferencesUtil.getInstance().put(KEY_AREA_INFO_TEST, JSON.toJSONString(areaInfo));
            } else {
                CSharedPreferencesUtil.getInstance().put(KEY_AREA_INFO, JSON.toJSONString(areaInfo));
            }
        } catch (Exception e) {
            Pdlog.m3273d(TAG, "saveAreaInfo--" + e.getMessage());
        }
    }

    private final DeviceInfo getDeviceInfo() {
        String string;
        if (IoTServerApiManager.INSTANCE.isTestServer()) {
            string = CSharedPreferencesUtil.getInstance().getString(KEY_DEVICE_INFO_TEST);
        } else {
            string = CSharedPreferencesUtil.getInstance().getString(KEY_DEVICE_INFO);
        }
        String str = string;
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            return (DeviceInfo) JSON.parseObject(string, DeviceInfo.class);
        } catch (Exception e) {
            Pdlog.m3273d(TAG, "getDeviceInfo--" + e.getMessage());
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AreaInfo getLocalAreaInfo() {
        String string;
        if (IoTServerApiManager.INSTANCE.isTestServer()) {
            string = CSharedPreferencesUtil.getInstance().getString(KEY_AREA_INFO_TEST);
        } else {
            string = CSharedPreferencesUtil.getInstance().getString(KEY_AREA_INFO);
        }
        String str = string;
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            return (AreaInfo) JSON.parseObject(string, AreaInfo.class);
        } catch (Exception e) {
            Pdlog.m3273d(TAG, "getLocalAreaInfo--" + e.getMessage());
            return null;
        }
    }

    @Override // com.pudutech.remotemaintenance.interf.IoTInterface
    public void startReport(int count) {
        ReportTask reportTask = this.reportTask;
        if (reportTask != null) {
            reportTask.startReport(count);
        }
    }

    @Override // com.pudutech.remotemaintenance.interf.IoTInterface
    public void stopReport() {
        ReportTask reportTask = this.reportTask;
        if (reportTask != null) {
            reportTask.stopReport();
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.pudutech.remotemaintenance.interf.IoTInterface
    public AliyunIoTMsg getReportMsg() {
        return this.reportMsg;
    }

    @Override // com.pudutech.remotemaintenance.interf.IoTInterface
    public void setReportMsg(AliyunIoTMsg msg) {
        this.reportMsg = msg;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.pudutech.remotemaintenance.interf.IoTInterface
    public AliyunIoTMsg getMapSyncMsg() {
        return this.mapSyncMsg;
    }

    @Override // com.pudutech.remotemaintenance.interf.IoTInterface
    public void setMapSyncMsg(AliyunIoTMsg msg) {
        this.mapSyncMsg = msg;
    }

    @Override // com.pudutech.remotemaintenance.interf.IoTInterface
    public void setNetworkAvailable(boolean isNetworkAvailable) {
        if (isNetworkAvailable) {
            reconnect$default(this, false, 1, null);
        } else {
            disconnect();
        }
    }
}
