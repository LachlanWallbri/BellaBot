package com.pudutech.robot.opensdk;

import android.content.Context;
import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.aliyun.linksdk.alcs.AlcsConstant;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.pdmqtt.ActionCallback;
import com.pudutech.pdmqtt.MqttClientBuilder;
import com.pudutech.pdmqtt.MqttLogger;
import com.pudutech.pdmqtt.OnMessageChangeListener;
import com.pudutech.pdmqtt.OnPublishCallback;
import com.pudutech.pdmqtt.PuduMqttManager;
import com.pudutech.pdmqtt.client.IFetchClientCallback;
import com.pudutech.pdmqtt.client.IMqttClient;
import com.pudutech.pdmqtt.client.MqttConnectState;
import com.pudutech.pdmqtt.config.BaseMqttConfig;
import com.pudutech.robot.opensdk.IotAdapter;
import com.pudutech.robot.opensdk.RobotOpenSdk;
import com.pudutech.robot.opensdk.aliyun.IotRequest;
import com.pudutech.robot.opensdk.aliyun.IotShadow;
import com.pudutech.robot.opensdk.aliyun.bean.AliyunThingParams;
import com.pudutech.robot.opensdk.aliyun.bean.AliyunThingProData;
import com.pudutech.robot.opensdk.aliyun.bean.AliyunThingValue;
import com.pudutech.robot.opensdk.aliyun.bean.BaseMsgBean;
import com.pudutech.robot.opensdk.aliyun.bean.BindCodeData;
import com.pudutech.robot.opensdk.aliyun.bean.ShadowStateValue;
import com.pudutech.robot.opensdk.aliyun.topic.Constant;
import com.pudutech.robot.opensdk.aliyun.topic.IotSubBeeperTopic;
import com.pudutech.robot.opensdk.aliyun.topic.IotSubDisinfectionTopic;
import com.pudutech.robot.opensdk.aliyun.topic.IotSubGroupTopic;
import com.pudutech.robot.opensdk.aliyun.topic.IotSubSdkTopic;
import com.pudutech.robot.opensdk.aliyun.topic.IotSubSeviceTopic;
import com.pudutech.robot.opensdk.aliyun.topic.IotSubShadowTopic;
import com.pudutech.robot.opensdk.bean.pub.BaseNotifyPub;
import com.pudutech.robot.opensdk.bean.pub.IPubServiceMsg;
import com.pudutech.robot.opensdk.interf.IBody;
import com.pudutech.robot.opensdk.interf.ICallback;
import com.pudutech.robot.opensdk.interf.IGenBindCodeCallBack;
import com.pudutech.robot.opensdk.interf.IPubMsg;
import com.pudutech.robot.opensdk.interf.IRemoteConnectStateListener;
import com.pudutech.robot.opensdk.interf.ISubTopic;
import com.pudutech.robot.opensdk.utils.GenRandomUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: IoTAdapter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000¡\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u001f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u00103\u001a\u00020\u0015H\u0002J \u00104\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u000e\u00105\u001a\u00020\u000e2\u0006\u00106\u001a\u00020\u0015J\u001c\u00107\u001a\u00020\u00042\n\u00108\u001a\u0006\u0012\u0002\b\u0003092\u0006\u0010:\u001a\u00020;H\u0002J\u0014\u0010<\u001a\u00020\u00042\n\u00108\u001a\u0006\u0012\u0002\b\u000309H\u0002J\u000f\u0010=\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0002\u0010>J\b\u0010?\u001a\u00020\u000eH\u0002J\u0010\u0010@\u001a\u00020\u000e2\u0006\u0010A\u001a\u00020\nH\u0002J'\u0010B\u001a\u00020\u000e2\u0006\u0010C\u001a\u00020\u00042\u0006\u0010D\u001a\u00020E2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0002\bFJ\"\u0010G\u001a\u00020\u000e2\u0006\u0010C\u001a\u00020\u00042\u0006\u0010D\u001a\u00020E2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0006\u0010H\u001a\u00020\u000eJ-\u0010I\u001a\u00020\u000e2\n\u00108\u001a\u0006\u0012\u0002\b\u0003092\u0006\u0010:\u001a\u00020;2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0000¢\u0006\u0002\bJJ\u001d\u0010K\u001a\u00020\u000e2\u0006\u0010L\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020MH\u0000¢\u0006\u0002\bNJ\b\u0010O\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fj\u0002`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0010\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010'\u001a\u0004\u0018\u00010(X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020/0.X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00100\u001a\b\u0012\u0004\u0012\u00020201X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006P"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/IotAdapter;", "", "()V", "TAG", "", "callBack", "Lcom/pudutech/robot/opensdk/interf/ICallback;", "config", "Lcom/pudutech/pdmqtt/config/BaseMqttConfig;", "connectState", "Lcom/pudutech/robot/opensdk/RemoteConnectState;", "connectStateChangeListener", "Lkotlin/Function1;", "Lcom/pudutech/pdmqtt/client/MqttConnectState;", "", "Lcom/pudutech/pdmqtt/client/OnConnectStateChangeListener;", "context", "Landroid/content/Context;", "gson", "Lcom/google/gson/Gson;", "isEnable", "", "messageChangeListener", "Lcom/pudutech/pdmqtt/OnMessageChangeListener;", "mqttClient", "Lcom/pudutech/pdmqtt/client/IMqttClient;", "getMqttClient", "()Lcom/pudutech/pdmqtt/client/IMqttClient;", "setMqttClient", "(Lcom/pudutech/pdmqtt/client/IMqttClient;)V", "mqttClientCallback", "com/pudutech/robot/opensdk/IotAdapter$mqttClientCallback$1", "Lcom/pudutech/robot/opensdk/IotAdapter$mqttClientCallback$1;", "onConnectListener", "Lcom/pudutech/robot/opensdk/interf/IRemoteConnectStateListener;", "getOnConnectListener$robot_open_sdk_release", "()Lcom/pudutech/robot/opensdk/interf/IRemoteConnectStateListener;", "setOnConnectListener$robot_open_sdk_release", "(Lcom/pudutech/robot/opensdk/interf/IRemoteConnectStateListener;)V", "onMQTTMessage", "Lcom/pudutech/robot/opensdk/RobotOpenSdk$IOnMQTTMessage;", "getOnMQTTMessage", "()Lcom/pudutech/robot/opensdk/RobotOpenSdk$IOnMQTTMessage;", "setOnMQTTMessage", "(Lcom/pudutech/robot/opensdk/RobotOpenSdk$IOnMQTTMessage;)V", "subTopics", "Ljava/util/HashMap;", "Lcom/pudutech/robot/opensdk/interf/ISubTopic;", "waitRespReqs", "Ljava/util/concurrent/CopyOnWriteArraySet;", "Lcom/pudutech/robot/opensdk/aliyun/IotRequest;", "canUse", MqttServiceConstants.CONNECT_ACTION, "enable", "b", "genRespData", "msgContext", "Lcom/pudutech/robot/opensdk/MsgContext;", "body", "Lcom/pudutech/robot/opensdk/interf/IBody;", "genRespTopic", "handlerClient", "()Lkotlin/Unit;", "initSub", "notifyConnectByIot", "state", AlcsConstant.EVENT_PUB, TypedValues.Attributes.S_TARGET, NotificationCompat.CATEGORY_MESSAGE, "Lcom/pudutech/robot/opensdk/interf/IPubMsg;", "pub$robot_open_sdk_release", "pubTarget", "release", "responseMsg", "responseMsg$robot_open_sdk_release", "setBindCode", "original", "Lcom/pudutech/robot/opensdk/interf/IGenBindCodeCallBack;", "setBindCode$robot_open_sdk_release", "subscribeTopic", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class IotAdapter {
    private ICallback callBack;
    private BaseMqttConfig config;
    private Context context;
    private IMqttClient mqttClient;
    private IRemoteConnectStateListener onConnectListener;
    private RobotOpenSdk.IOnMQTTMessage onMQTTMessage;
    private final String TAG = "IotAdapter";
    private final HashMap<String, ISubTopic> subTopics = new HashMap<>();
    private final Gson gson = new Gson();
    private volatile RemoteConnectState connectState = RemoteConnectState.DISCONNECTED;
    private final CopyOnWriteArraySet<IotRequest> waitRespReqs = new CopyOnWriteArraySet<>();
    private volatile boolean isEnable = true;
    private final IotAdapter$mqttClientCallback$1 mqttClientCallback = new IFetchClientCallback() { // from class: com.pudutech.robot.opensdk.IotAdapter$mqttClientCallback$1
        @Override // com.pudutech.pdmqtt.client.IFetchClientCallback
        public void onError(BaseMqttConfig requestConfig, int code, String message) {
            ICallback iCallback;
            Intrinsics.checkParameterIsNotNull(requestConfig, "requestConfig");
            Intrinsics.checkParameterIsNotNull(message, "message");
            iCallback = IotAdapter.this.callBack;
            if (iCallback != null) {
                iCallback.onFailed(new Exception(message));
            }
        }

        @Override // com.pudutech.pdmqtt.client.IFetchClientCallback
        public void onSuccess(BaseMqttConfig requestConfig, IMqttClient client) {
            ICallback iCallback;
            BaseMqttConfig baseMqttConfig;
            String str;
            BaseMqttConfig baseMqttConfig2;
            String deviceName;
            Intrinsics.checkParameterIsNotNull(requestConfig, "requestConfig");
            Intrinsics.checkParameterIsNotNull(client, "client");
            iCallback = IotAdapter.this.callBack;
            if (iCallback != null) {
                iCallback.onSuccess(null);
            }
            IotAdapter.this.setMqttClient(client);
            IotAdapter.this.subscribeTopic();
            IMqttClient mqttClient = IotAdapter.this.getMqttClient();
            if (mqttClient != null) {
                Constant constant = Constant.INSTANCE;
                baseMqttConfig = IotAdapter.this.config;
                String str2 = "";
                if (baseMqttConfig == null || (str = baseMqttConfig.productKey()) == null) {
                    str = "";
                }
                baseMqttConfig2 = IotAdapter.this.config;
                if (baseMqttConfig2 != null && (deviceName = baseMqttConfig2.deviceName()) != null) {
                    str2 = deviceName;
                }
                mqttClient.publish(constant.pubDeviceConnected(str, str2), "1", null);
            }
            IotAdapter.this.handlerClient();
        }
    };
    private final Function1<MqttConnectState, Unit> connectStateChangeListener = new Function1<MqttConnectState, Unit>() { // from class: com.pudutech.robot.opensdk.IotAdapter$connectStateChangeListener$1
        /* JADX INFO: Access modifiers changed from: package-private */
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(MqttConnectState mqttConnectState) {
            invoke2(mqttConnectState);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(MqttConnectState it) {
            Intrinsics.checkParameterIsNotNull(it, "it");
            int i = IotAdapter.WhenMappings.$EnumSwitchMapping$0[it.ordinal()];
            if (i == 1) {
                IotAdapter.this.notifyConnectByIot(RemoteConnectState.CONNECTING);
                return;
            }
            if (i == 2) {
                IotAdapter.this.notifyConnectByIot(RemoteConnectState.DISCONNECTED);
            } else {
                if (i != 3) {
                    return;
                }
                IotAdapter.this.subscribeTopic();
                IotAdapter.this.notifyConnectByIot(RemoteConnectState.CONNECTED);
            }
        }
    };
    private final OnMessageChangeListener messageChangeListener = new OnMessageChangeListener.Stub() { // from class: com.pudutech.robot.opensdk.IotAdapter$messageChangeListener$1
        @Override // com.pudutech.pdmqtt.OnMessageChangeListener
        public void onReceive(String topic, String payload) {
            String str;
            boolean canUse;
            String str2;
            CopyOnWriteArraySet copyOnWriteArraySet;
            HashMap hashMap;
            String str3;
            Intrinsics.checkParameterIsNotNull(topic, "topic");
            Intrinsics.checkParameterIsNotNull(payload, "payload");
            try {
                canUse = IotAdapter.this.canUse();
                if (canUse) {
                    str2 = IotAdapter.this.TAG;
                    Pdlog.m3273d(str2, "messageArrived topic = " + topic + "; message = " + payload + "; ");
                    RobotOpenSdk.IOnMQTTMessage onMQTTMessage = IotAdapter.this.getOnMQTTMessage();
                    if (onMQTTMessage != null) {
                        onMQTTMessage.onReceive(topic, payload);
                    }
                    copyOnWriteArraySet = IotAdapter.this.waitRespReqs;
                    Iterator it = copyOnWriteArraySet.iterator();
                    while (it.hasNext()) {
                        if (((IotRequest) it.next()).doReplayMsgIfNeed(topic, payload)) {
                            str3 = IotAdapter.this.TAG;
                            Pdlog.m3273d(str3, "onNotify : is resp msg ");
                            return;
                        }
                    }
                    if (payload.length() > 0) {
                        hashMap = IotAdapter.this.subTopics;
                        ISubTopic iSubTopic = (ISubTopic) hashMap.get(topic);
                        if (iSubTopic != null) {
                            iSubTopic.onMsg(payload);
                        }
                    }
                }
            } catch (Exception e) {
                str = IotAdapter.this.TAG;
                Pdlog.m3274e(str, Log.getStackTraceString(e));
            }
        }

        @Override // com.pudutech.pdmqtt.OnMessageChangeListener
        public void onSend(String topic, String payload) {
            RobotOpenSdk.IOnMQTTMessage onMQTTMessage = IotAdapter.this.getOnMQTTMessage();
            if (onMQTTMessage != null) {
                if (topic == null) {
                    topic = "";
                }
                if (payload == null) {
                    payload = "";
                }
                onMQTTMessage.onSend(topic, payload);
            }
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[MqttConnectState.values().length];

        static {
            $EnumSwitchMapping$0[MqttConnectState.CONNECTING.ordinal()] = 1;
            $EnumSwitchMapping$0[MqttConnectState.DISCONNECTED.ordinal()] = 2;
            $EnumSwitchMapping$0[MqttConnectState.CONNECTED.ordinal()] = 3;
        }
    }

    /* renamed from: getOnConnectListener$robot_open_sdk_release, reason: from getter */
    public final IRemoteConnectStateListener getOnConnectListener() {
        return this.onConnectListener;
    }

    public final void setOnConnectListener$robot_open_sdk_release(IRemoteConnectStateListener iRemoteConnectStateListener) {
        this.onConnectListener = iRemoteConnectStateListener;
    }

    public final IMqttClient getMqttClient() {
        return this.mqttClient;
    }

    public final void setMqttClient(IMqttClient iMqttClient) {
        this.mqttClient = iMqttClient;
    }

    public final RobotOpenSdk.IOnMQTTMessage getOnMQTTMessage() {
        return this.onMQTTMessage;
    }

    public final void setOnMQTTMessage(RobotOpenSdk.IOnMQTTMessage iOnMQTTMessage) {
        this.onMQTTMessage = iOnMQTTMessage;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyConnectByIot(RemoteConnectState state) {
        if (this.connectState == state) {
            return;
        }
        this.connectState = state;
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new IotAdapter$notifyConnectByIot$1(this, state, null), 2, null);
    }

    public final void connect(Context context, final BaseMqttConfig config, ICallback callBack) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(config, "config");
        Pdlog.m3273d(this.TAG, "init : config = " + config + "; ");
        this.config = config;
        this.context = context;
        this.callBack = callBack;
        initSub();
        PuduMqttManager.INSTANCE.setLog(new MqttLogger.Stub() { // from class: com.pudutech.robot.opensdk.IotAdapter$connect$1
            @Override // com.pudutech.pdmqtt.MqttLogger
            public void log(String tag, String message) {
                Pdlog.m3273d("PuduMqttManager-" + tag, message);
            }
        });
        PuduMqttManager.INSTANCE.fetchClient(context, new Function1<MqttClientBuilder, Unit>() { // from class: com.pudutech.robot.opensdk.IotAdapter$connect$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MqttClientBuilder mqttClientBuilder) {
                invoke2(mqttClientBuilder);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MqttClientBuilder receiver) {
                IotAdapter$mqttClientCallback$1 iotAdapter$mqttClientCallback$1;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                receiver.setMqttConfig(config);
                iotAdapter$mqttClientCallback$1 = IotAdapter.this.mqttClientCallback;
                receiver.setClientCallback(iotAdapter$mqttClientCallback$1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Unit handlerClient() {
        IMqttClient iMqttClient = this.mqttClient;
        if (iMqttClient == null) {
            return null;
        }
        iMqttClient.addOnConnectStateChangeLis(this.connectStateChangeListener);
        iMqttClient.addOnMessageChangeListener(this.messageChangeListener);
        return Unit.INSTANCE;
    }

    private final void initSub() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        String str11;
        String deviceName;
        this.subTopics.clear();
        BaseMqttConfig baseMqttConfig = this.config;
        String str12 = "";
        if (baseMqttConfig == null || (str = baseMqttConfig.productKey()) == null) {
            str = "";
        }
        BaseMqttConfig baseMqttConfig2 = this.config;
        if (baseMqttConfig2 == null || (str2 = baseMqttConfig2.deviceName()) == null) {
            str2 = "";
        }
        IotSubBeeperTopic iotSubBeeperTopic = new IotSubBeeperTopic(str, str2);
        this.subTopics.put(iotSubBeeperTopic.getTopic(), iotSubBeeperTopic);
        BaseMqttConfig baseMqttConfig3 = this.config;
        if (baseMqttConfig3 == null || (str3 = baseMqttConfig3.productKey()) == null) {
            str3 = "";
        }
        BaseMqttConfig baseMqttConfig4 = this.config;
        if (baseMqttConfig4 == null || (str4 = baseMqttConfig4.deviceName()) == null) {
            str4 = "";
        }
        IotSubSdkTopic iotSubSdkTopic = new IotSubSdkTopic(str3, str4);
        this.subTopics.put(iotSubSdkTopic.getTopic(), iotSubSdkTopic);
        BaseMqttConfig baseMqttConfig5 = this.config;
        if (baseMqttConfig5 == null || (str5 = baseMqttConfig5.productKey()) == null) {
            str5 = "";
        }
        BaseMqttConfig baseMqttConfig6 = this.config;
        if (baseMqttConfig6 == null || (str6 = baseMqttConfig6.deviceName()) == null) {
            str6 = "";
        }
        IotSubGroupTopic iotSubGroupTopic = new IotSubGroupTopic(str5, str6);
        this.subTopics.put(iotSubGroupTopic.getTopic(), iotSubGroupTopic);
        BaseMqttConfig baseMqttConfig7 = this.config;
        if (baseMqttConfig7 == null || (str7 = baseMqttConfig7.productKey()) == null) {
            str7 = "";
        }
        BaseMqttConfig baseMqttConfig8 = this.config;
        if (baseMqttConfig8 == null || (str8 = baseMqttConfig8.deviceName()) == null) {
            str8 = "";
        }
        IotSubSeviceTopic iotSubSeviceTopic = new IotSubSeviceTopic(str7, str8);
        this.subTopics.put(iotSubSeviceTopic.getTopic(), iotSubSeviceTopic);
        BaseMqttConfig baseMqttConfig9 = this.config;
        if (baseMqttConfig9 == null || (str9 = baseMqttConfig9.productKey()) == null) {
            str9 = "";
        }
        BaseMqttConfig baseMqttConfig10 = this.config;
        if (baseMqttConfig10 == null || (str10 = baseMqttConfig10.deviceName()) == null) {
            str10 = "";
        }
        IotSubDisinfectionTopic iotSubDisinfectionTopic = new IotSubDisinfectionTopic(str9, str10);
        this.subTopics.put(iotSubDisinfectionTopic.getTopic(), iotSubDisinfectionTopic);
        BaseMqttConfig baseMqttConfig11 = this.config;
        if (baseMqttConfig11 == null || (str11 = baseMqttConfig11.productKey()) == null) {
            str11 = "";
        }
        BaseMqttConfig baseMqttConfig12 = this.config;
        if (baseMqttConfig12 != null && (deviceName = baseMqttConfig12.deviceName()) != null) {
            str12 = deviceName;
        }
        IotSubShadowTopic iotSubShadowTopic = new IotSubShadowTopic(str11, str12);
        this.subTopics.put(iotSubShadowTopic.getTopic(), iotSubShadowTopic);
    }

    public final void setBindCode$robot_open_sdk_release(final String original, final IGenBindCodeCallBack callBack) {
        String str;
        String str2;
        String deviceName;
        Intrinsics.checkParameterIsNotNull(original, "original");
        Intrinsics.checkParameterIsNotNull(callBack, "callBack");
        IMqttClient iMqttClient = this.mqttClient;
        if (iMqttClient != null && !iMqttClient.isConnect()) {
            Pdlog.m3273d(this.TAG, "setBindCode : ");
            return;
        }
        String str3 = GenRandomUtils.getRandom$default(SDKConfig.getBindCodeLength(), false, 2, null) + ":";
        StringBuilder sb = new StringBuilder();
        sb.append(str3);
        BaseMqttConfig baseMqttConfig = this.config;
        String str4 = "";
        if (baseMqttConfig == null || (str = baseMqttConfig.deviceName()) == null) {
            str = "";
        }
        sb.append((Object) str);
        String sb2 = sb.toString();
        AliyunThingProData aliyunThingProData = new AliyunThingProData(new Random().nextInt(Integer.MAX_VALUE), null, new AliyunThingParams(new AliyunThingValue(sb2, System.currentTimeMillis())), null, null, 26, null);
        Pdlog.m3273d(this.TAG, "setBindCode : code = " + sb2 + "; ");
        final BindCodeData bindCodeData = new BindCodeData(sb2, System.currentTimeMillis() + ((long) 300000));
        try {
            IMqttClient iMqttClient2 = this.mqttClient;
            if (iMqttClient2 != null) {
                Constant constant = Constant.INSTANCE;
                BaseMqttConfig baseMqttConfig2 = this.config;
                if (baseMqttConfig2 == null || (str2 = baseMqttConfig2.productKey()) == null) {
                    str2 = "";
                }
                BaseMqttConfig baseMqttConfig3 = this.config;
                if (baseMqttConfig3 != null && (deviceName = baseMqttConfig3.deviceName()) != null) {
                    str4 = deviceName;
                }
                String pubThingProUpdate = constant.pubThingProUpdate(str2, str4);
                String json = this.gson.toJson(aliyunThingProData);
                Intrinsics.checkExpressionValueIsNotNull(json, "gson.toJson(reportData)");
                iMqttClient2.publish(pubThingProUpdate, json, new OnPublishCallback.Stub() { // from class: com.pudutech.robot.opensdk.IotAdapter$setBindCode$1
                    @Override // com.pudutech.pdmqtt.OnPublishCallback
                    public void onSuccess(String topic, String payload) {
                        String str5;
                        Intrinsics.checkParameterIsNotNull(topic, "topic");
                        Intrinsics.checkParameterIsNotNull(payload, "payload");
                        BindCodeData bindCodeData2 = bindCodeData;
                        bindCodeData2.setCode(bindCodeData2.getCode() + ':' + original);
                        str5 = IotAdapter.this.TAG;
                        Pdlog.m3273d(str5, "onSuccess : " + bindCodeData);
                        callBack.onSuccess(bindCodeData);
                    }

                    @Override // com.pudutech.pdmqtt.OnPublishCallback
                    public void onFailue(String topic, String payload, int code, String message) {
                        String str5;
                        Intrinsics.checkParameterIsNotNull(topic, "topic");
                        Intrinsics.checkParameterIsNotNull(payload, "payload");
                        Intrinsics.checkParameterIsNotNull(message, "message");
                        str5 = IotAdapter.this.TAG;
                        Pdlog.m3274e(str5, "reportProperty onError() resId=" + topic + ", error=" + message);
                        callBack.onFailed(new Exception(message));
                    }
                });
            }
        } catch (Exception e) {
            Pdlog.m3274e(this.TAG, "setBindCode : " + Log.getStackTraceString(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void subscribeTopic() {
        String str;
        String deviceName;
        Pdlog.m3273d(this.TAG, "subscribeTopic");
        IMqttClient iMqttClient = this.mqttClient;
        if (iMqttClient != null) {
            Set<String> keySet = this.subTopics.keySet();
            Intrinsics.checkExpressionValueIsNotNull(keySet, "subTopics.keys");
            iMqttClient.subscribe(keySet, new ActionCallback.Stub() { // from class: com.pudutech.robot.opensdk.IotAdapter$subscribeTopic$1
                @Override // com.pudutech.pdmqtt.ActionCallback
                public void actionState(int state, String p1) {
                    String str2;
                    str2 = IotAdapter.this.TAG;
                    Pdlog.m3273d(str2, "subscribeTopic.actionState " + state);
                }
            });
        }
        IotShadow iotShadow = IotShadow.INSTANCE;
        IMqttClient iMqttClient2 = this.mqttClient;
        BaseMqttConfig baseMqttConfig = this.config;
        String str2 = "";
        if (baseMqttConfig == null || (str = baseMqttConfig.productKey()) == null) {
            str = "";
        }
        BaseMqttConfig baseMqttConfig2 = this.config;
        if (baseMqttConfig2 != null && (deviceName = baseMqttConfig2.deviceName()) != null) {
            str2 = deviceName;
        }
        iotShadow.initShadow(iMqttClient2, str, str2);
    }

    public static /* synthetic */ void responseMsg$robot_open_sdk_release$default(IotAdapter iotAdapter, MsgContext msgContext, IBody iBody, ICallback iCallback, int i, Object obj) {
        if ((i & 4) != 0) {
            iCallback = (ICallback) null;
        }
        iotAdapter.responseMsg$robot_open_sdk_release(msgContext, iBody, iCallback);
    }

    public final void responseMsg$robot_open_sdk_release(MsgContext<?> msgContext, IBody body, final ICallback callBack) {
        Intrinsics.checkParameterIsNotNull(msgContext, "msgContext");
        Intrinsics.checkParameterIsNotNull(body, "body");
        IMqttClient iMqttClient = this.mqttClient;
        if ((iMqttClient != null && !iMqttClient.isConnect()) || !this.isEnable) {
            String str = this.TAG;
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder();
            sb.append("responseMsg : isRelease = ");
            IMqttClient iMqttClient2 = this.mqttClient;
            sb.append(iMqttClient2 != null ? Boolean.valueOf(iMqttClient2.isConnect()) : null);
            sb.append(" , isEnable = ");
            sb.append(this.isEnable);
            objArr[0] = sb.toString();
            Pdlog.m3273d(str, objArr);
            return;
        }
        Pdlog.m3273d(this.TAG, "responseMsg : msgContext = " + msgContext + "; body = " + body + "; callBack = " + callBack + "; ");
        IMqttClient iMqttClient3 = this.mqttClient;
        if (iMqttClient3 != null) {
            iMqttClient3.publish(genRespTopic(msgContext), genRespData(msgContext, body), new OnPublishCallback.Stub() { // from class: com.pudutech.robot.opensdk.IotAdapter$responseMsg$1
                @Override // com.pudutech.pdmqtt.OnPublishCallback
                public void onSuccess(String topic, String payload) {
                    String str2;
                    Intrinsics.checkParameterIsNotNull(topic, "topic");
                    Intrinsics.checkParameterIsNotNull(payload, "payload");
                    str2 = IotAdapter.this.TAG;
                    Pdlog.m3273d(str2, "responseMsg onResponse() success");
                    ICallback iCallback = callBack;
                    if (iCallback != null) {
                        ICallback.DefaultImpls.onSuccess$default(iCallback, null, 1, null);
                    }
                }

                @Override // com.pudutech.pdmqtt.OnPublishCallback
                public void onFailue(String topic, String payload, int code, String message) {
                    String str2;
                    Intrinsics.checkParameterIsNotNull(topic, "topic");
                    Intrinsics.checkParameterIsNotNull(payload, "payload");
                    Intrinsics.checkParameterIsNotNull(message, "message");
                    str2 = IotAdapter.this.TAG;
                    Pdlog.m3274e(str2, "responseMsg onFailure() asyncActionToken=" + topic + ", exception=" + message);
                    ICallback iCallback = callBack;
                    if (iCallback != null) {
                        iCallback.onFailed(new Exception(message));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean canUse() {
        IMqttClient iMqttClient = this.mqttClient;
        Boolean valueOf = iMqttClient != null ? Boolean.valueOf(iMqttClient.isConnect()) : null;
        notifyConnectByIot(Intrinsics.areEqual((Object) valueOf, (Object) true) ? RemoteConnectState.CONNECTED : RemoteConnectState.DISCONNECTED);
        Pdlog.m3273d(this.TAG, "pub : connect = " + valueOf + " , isEnable = " + this.isEnable);
        return Intrinsics.areEqual((Object) valueOf, (Object) true) && this.isEnable;
    }

    public final void pub$robot_open_sdk_release(String target, IPubMsg msg, ICallback callBack) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (canUse()) {
            if (target.length() > 0) {
                pubTarget(target, msg, callBack);
                return;
            }
            if (!(msg instanceof BaseNotifyPub)) {
                if (msg instanceof IPubServiceMsg) {
                    pubTarget(target, msg, callBack);
                    return;
                } else {
                    Pdlog.m3274e(this.TAG, "pub : no target ???");
                    return;
                }
            }
            for (String str : ((BaseNotifyPub) msg).getNotifyTarget()) {
                Pdlog.m3273d(this.TAG, "pub BaseNotifyPub , target = " + str);
                pubTarget(str, msg, callBack);
            }
        }
    }

    private final void pubTarget(String target, final IPubMsg msg, final ICallback callBack) {
        String str;
        String deviceName;
        String productKey;
        BaseMqttConfig baseMqttConfig = this.config;
        String str2 = (baseMqttConfig == null || (productKey = baseMqttConfig.productKey()) == null) ? "" : productKey;
        BaseMqttConfig baseMqttConfig2 = this.config;
        String str3 = (baseMqttConfig2 == null || (deviceName = baseMqttConfig2.deviceName()) == null) ? "" : deviceName;
        ShadowStateValue shadowConfig = IotShadow.INSTANCE.getShadowConfig();
        if (shadowConfig == null || (str = shadowConfig.getGroupId()) == null) {
            str = "default";
        }
        final IotRequest iotRequest = new IotRequest(target, str2, str3, str, msg, new IotRequest.CallBack() { // from class: com.pudutech.robot.opensdk.IotAdapter$pubTarget$iotRequest$1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v2, types: [com.pudutech.robot.opensdk.interf.IBody] */
            @Override // com.pudutech.robot.opensdk.aliyun.IotRequest.CallBack
            public void onSuccess(String msgStr) {
                String str4;
                Intrinsics.checkParameterIsNotNull(msgStr, "msgStr");
                BaseMsgBean<?> baseMsgBean = (BaseMsgBean) null;
                try {
                    baseMsgBean = msg.parseObj(msgStr);
                } catch (Exception unused) {
                    str4 = IotAdapter.this.TAG;
                    Pdlog.m3273d(str4, "onSuccess : msgStr = " + msgStr + "; ");
                }
                if (baseMsgBean != null) {
                    ICallback iCallback = callBack;
                    if (iCallback != 0) {
                        iCallback.onSuccess(baseMsgBean.getBody());
                        return;
                    }
                    return;
                }
                ICallback iCallback2 = callBack;
                if (iCallback2 != null) {
                    ICallback.DefaultImpls.onSuccess$default(iCallback2, null, 1, null);
                }
            }

            @Override // com.pudutech.robot.opensdk.aliyun.IotRequest.CallBack
            public void onFailed(Exception e) {
                Intrinsics.checkParameterIsNotNull(e, "e");
                ICallback iCallback = callBack;
                if (iCallback != null) {
                    iCallback.onFailed(e);
                }
            }
        }, this.mqttClient);
        if (iotRequest.needWaitResp()) {
            Iterator<T> it = this.waitRespReqs.iterator();
            while (it.hasNext()) {
                ((IotRequest) it.next()).stopRetryIfNeed$robot_open_sdk_release(target, msg);
            }
            this.waitRespReqs.add(iotRequest);
            iotRequest.setOnCallbackFinish(new Function0<Unit>() { // from class: com.pudutech.robot.opensdk.IotAdapter$pubTarget$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    CopyOnWriteArraySet copyOnWriteArraySet;
                    copyOnWriteArraySet = IotAdapter.this.waitRespReqs;
                    copyOnWriteArraySet.remove(iotRequest);
                }
            });
        }
        iotRequest.doRequest();
    }

    private final String genRespTopic(MsgContext<?> msgContext) {
        String str;
        String deviceName;
        String str2;
        String deviceName2;
        String str3;
        String deviceName3;
        String str4;
        String deviceName4;
        String str5 = "";
        if (Intrinsics.areEqual(msgContext.getRole(), Constant.INSTANCE.getROLE_SDK())) {
            Constant constant = Constant.INSTANCE;
            BaseMqttConfig baseMqttConfig = this.config;
            if (baseMqttConfig == null || (str4 = baseMqttConfig.productKey()) == null) {
                str4 = "";
            }
            BaseMqttConfig baseMqttConfig2 = this.config;
            if (baseMqttConfig2 != null && (deviceName4 = baseMqttConfig2.deviceName()) != null) {
                str5 = deviceName4;
            }
            return constant.pubSdkTopicTemplate(str4, str5);
        }
        if (Intrinsics.areEqual(msgContext.getRole(), Constant.INSTANCE.getROLE_BEEPER())) {
            Constant constant2 = Constant.INSTANCE;
            BaseMqttConfig baseMqttConfig3 = this.config;
            if (baseMqttConfig3 == null || (str3 = baseMqttConfig3.productKey()) == null) {
                str3 = "";
            }
            BaseMqttConfig baseMqttConfig4 = this.config;
            if (baseMqttConfig4 != null && (deviceName3 = baseMqttConfig4.deviceName()) != null) {
                str5 = deviceName3;
            }
            return constant2.pubBeeperTopicTemplate(str3, str5);
        }
        if (Intrinsics.areEqual(msgContext.getRole(), Constant.INSTANCE.getROLE_DISINFECTION())) {
            Constant constant3 = Constant.INSTANCE;
            BaseMqttConfig baseMqttConfig5 = this.config;
            if (baseMqttConfig5 == null || (str2 = baseMqttConfig5.productKey()) == null) {
                str2 = "";
            }
            BaseMqttConfig baseMqttConfig6 = this.config;
            if (baseMqttConfig6 != null && (deviceName2 = baseMqttConfig6.deviceName()) != null) {
                str5 = deviceName2;
            }
            return constant3.pubDisinfectionTopicTemplate(str2, str5);
        }
        Constant constant4 = Constant.INSTANCE;
        BaseMqttConfig baseMqttConfig7 = this.config;
        if (baseMqttConfig7 == null || (str = baseMqttConfig7.productKey()) == null) {
            str = "";
        }
        BaseMqttConfig baseMqttConfig8 = this.config;
        if (baseMqttConfig8 != null && (deviceName = baseMqttConfig8.deviceName()) != null) {
            str5 = deviceName;
        }
        return constant4.pubBeeperTopicTemplate(str, str5);
    }

    private final String genRespData(MsgContext<?> msgContext, IBody body) {
        String str;
        String msgType = msgContext.getMsgType();
        BaseMqttConfig baseMqttConfig = this.config;
        if (baseMqttConfig == null || (str = baseMqttConfig.deviceName()) == null) {
            str = "";
        }
        String payloadObj = this.gson.toJson(new BaseMsgBean(msgType, str, msgContext.getTarget(), msgContext.getMsgId(), body, msgContext.getGroupId()));
        Pdlog.m3273d(this.TAG, "genResp : payloadObj = " + payloadObj);
        Intrinsics.checkExpressionValueIsNotNull(payloadObj, "payloadObj");
        return payloadObj;
    }

    public final synchronized void release() {
        String str;
        String str2;
        Pdlog.m3273d(this.TAG, "release");
        IMqttClient iMqttClient = this.mqttClient;
        if (iMqttClient != null) {
            Constant constant = Constant.INSTANCE;
            BaseMqttConfig baseMqttConfig = this.config;
            if (baseMqttConfig == null || (str = baseMqttConfig.productKey()) == null) {
                str = "";
            }
            BaseMqttConfig baseMqttConfig2 = this.config;
            if (baseMqttConfig2 == null || (str2 = baseMqttConfig2.deviceName()) == null) {
                str2 = "";
            }
            iMqttClient.publish(constant.pubDeviceConnected(str, str2), "0", null);
            iMqttClient.removeOnConnectStateChangeLis(this.connectStateChangeListener);
            iMqttClient.removeOnMessageChangeListener(this.messageChangeListener);
            iMqttClient.release();
        }
        this.mqttClient = (IMqttClient) null;
        IotShadow.INSTANCE.release();
        Iterator<T> it = this.waitRespReqs.iterator();
        while (it.hasNext()) {
            ((IotRequest) it.next()).destroy$robot_open_sdk_release();
        }
        this.waitRespReqs.clear();
        notifyConnectByIot(RemoteConnectState.DISCONNECTED);
    }

    public final void enable(boolean b) {
        Pdlog.m3273d(this.TAG, "enable : b = " + b);
        this.isEnable = b;
    }
}
