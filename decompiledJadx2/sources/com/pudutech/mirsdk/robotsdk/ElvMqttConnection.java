package com.pudutech.mirsdk.robotsdk;

import android.content.Context;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.ElevatorConnectionType;
import com.pudutech.mirsdk.base.CommonKt;
import com.pudutech.mqtt.component.client.MqttClientFactory;
import com.pudutech.mqtt.component.client.bean.SubscribeMessage;
import com.pudutech.mqtt.component.client.callback.LoginStateCallback;
import com.pudutech.mqtt.component.client.callback.MessageReceiverListener;
import com.pudutech.mqtt.component.client.callback.SubscribeStateCallback;
import com.pudutech.mqtt.component.client.callback.UnsubscribeStateCallback;
import com.pudutech.mqtt.component.client.config.LoginState;
import com.pudutech.mqtt.component.client.config.MqttParamOptions;
import com.pudutech.mqtt.component.client.config.Qos;
import com.pudutech.mqtt.component.client.config.SubscribeState;
import com.pudutech.mqtt.component.client.config.UnsubscribeState;
import com.pudutech.mqtt.component.client.interf.IMqttClient;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Vector;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ElvMqttConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ0\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000bH\u0016J\u001a\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u000bH\u0016J \u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001e2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010 H\u0016J \u0010!\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\"2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010 H\u0016J\u000e\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020%J\b\u0010&\u001a\u00020\u0013H\u0016J(\u0010'\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000bH\u0016J(\u0010(\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000bH\u0016J0\u0010)\u001a\u00020\u00132\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000bH\u0016J \u0010+\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000bH\u0016J \u0010,\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000bH\u0016J(\u0010-\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000bH\u0016J\b\u0010.\u001a\u00020\u0013H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, m3961d2 = {"Lcom/pudutech/mirsdk/robotsdk/ElvMqttConnection;", "Lcom/pudutech/mqtt/component/client/callback/LoginStateCallback;", "Lcom/pudutech/mqtt/component/client/callback/SubscribeStateCallback;", "Lcom/pudutech/mqtt/component/client/callback/UnsubscribeStateCallback;", "Lcom/pudutech/mirsdk/robotsdk/ElevatorCommunicateInterface;", "context", "Landroid/content/Context;", "connectionType", "Lcom/pudutech/mirsdk/aidl/serialize/ElevatorConnectionType;", "(Landroid/content/Context;Lcom/pudutech/mirsdk/aidl/serialize/ElevatorConnectionType;)V", "TAG", "", "getContext", "()Landroid/content/Context;", "debug", "gson", "Lcom/google/gson/Gson;", "robotId", "callElv", "", "curr", "dst", "seq", "", "elvId", "callbackLoginState", "state", "Lcom/pudutech/mqtt/component/client/config/LoginState;", "errMsg", "callbackSubscribeState", "Lcom/pudutech/mqtt/component/client/config/SubscribeState;", "topicList", "", "callbackUnsubscribeState", "Lcom/pudutech/mqtt/component/client/config/UnsubscribeState;", MqttServiceConstants.CONNECT_ACTION, "recver", "Lcom/pudutech/mirsdk/robotsdk/MqttMsgListener;", MqttServiceConstants.DISCONNECT_ACTION, "elvEntered", "elvLeft", "elvLeftState", "stat", "enterElvAck", "leaveElvAck", "prepareRide", MqttServiceConstants.SUBSCRIBE_ACTION, "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ElvMqttConnection implements LoginStateCallback, SubscribeStateCallback, UnsubscribeStateCallback, ElevatorCommunicateInterface {
    private final String TAG;
    private final ElevatorConnectionType connectionType;
    private final Context context;
    private final String debug;
    private final Gson gson;
    private final String robotId;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ElevatorConnectionType.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;

        static {
            $EnumSwitchMapping$0[ElevatorConnectionType.CommCat.ordinal()] = 1;
            $EnumSwitchMapping$1 = new int[ElevatorConnectionType.values().length];
            $EnumSwitchMapping$1[ElevatorConnectionType.CommCat.ordinal()] = 1;
            $EnumSwitchMapping$2 = new int[LoginState.values().length];
            $EnumSwitchMapping$2[LoginState.LoginSuccessful.ordinal()] = 1;
            $EnumSwitchMapping$2[LoginState.LoginFailure.ordinal()] = 2;
            $EnumSwitchMapping$3 = new int[SubscribeState.values().length];
            $EnumSwitchMapping$3[SubscribeState.SubscribeSuccessful.ordinal()] = 1;
            $EnumSwitchMapping$3[SubscribeState.SubscribeFailure.ordinal()] = 2;
            $EnumSwitchMapping$4 = new int[UnsubscribeState.values().length];
            $EnumSwitchMapping$4[UnsubscribeState.UnsubscribeSuccessful.ordinal()] = 1;
            $EnumSwitchMapping$4[UnsubscribeState.UnsubscribeFailure.ordinal()] = 2;
        }
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void prepareRide(String curr, long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(curr, "curr");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
    }

    public ElvMqttConnection(Context context, ElevatorConnectionType connectionType) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(connectionType, "connectionType");
        this.context = context;
        this.connectionType = connectionType;
        this.TAG = "elv";
        this.gson = new Gson();
        this.robotId = CommonKt.getWIFIMac();
        this.debug = this.connectionType == ElevatorConnectionType.CommCat ? "/public/TEST/" : "";
    }

    public final Context getContext() {
        return this.context;
    }

    public final void connect(final MqttMsgListener recver) {
        MqttParamOptions mqttParamOptions;
        Intrinsics.checkParameterIsNotNull(recver, "recver");
        new Vector();
        String str = WhenMappings.$EnumSwitchMapping$0[this.connectionType.ordinal()] != 1 ? "cshlzn-elv.mqtt.iot.gz.baidubce.com" : "mq.tongxinmao.com";
        int i = WhenMappings.$EnumSwitchMapping$1[this.connectionType.ordinal()] != 1 ? 1883 : 18830;
        if (this.connectionType == ElevatorConnectionType.CommCat) {
            mqttParamOptions = new MqttParamOptions.Builder().hasUserName(false).hasPassword(false).setHost(str).setPort(i).setLoginStateCallback(this).setMessageReceiverListener(new MessageReceiverListener() { // from class: com.pudutech.mirsdk.robotsdk.ElvMqttConnection$connect$mqttParamOptions$1
                @Override // com.pudutech.mqtt.component.client.callback.MessageReceiverListener
                public void onReceiverMessage(String topic, String msg) {
                    Intrinsics.checkParameterIsNotNull(topic, "topic");
                    Intrinsics.checkParameterIsNotNull(msg, "msg");
                    MqttMsgListener.this.onReceiverMessage(topic, msg);
                }
            }).build();
        } else {
            mqttParamOptions = new MqttParamOptions.Builder().hasUserName(true).hasPassword(true).setUserName("cshlzn-elv/robot-elv").setPassword("JEhcVqncL8DDRcAd").setHost(str).setPort(i).setLoginStateCallback(this).setMessageReceiverListener(new MessageReceiverListener() { // from class: com.pudutech.mirsdk.robotsdk.ElvMqttConnection$connect$mqttParamOptions$2
                @Override // com.pudutech.mqtt.component.client.callback.MessageReceiverListener
                public void onReceiverMessage(String topic, String msg) {
                    Intrinsics.checkParameterIsNotNull(topic, "topic");
                    Intrinsics.checkParameterIsNotNull(msg, "msg");
                    MqttMsgListener.this.onReceiverMessage(topic, msg);
                }
            }).build();
        }
        IMqttClient mqttClient = MqttClientFactory.INSTANCE.getMqttClient();
        Context context = this.context;
        Intrinsics.checkExpressionValueIsNotNull(mqttParamOptions, "mqttParamOptions");
        mqttClient.init(context, mqttParamOptions).connect();
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void disconnect() {
        MqttClientFactory.INSTANCE.getMqttClient().close();
    }

    @Override // com.pudutech.mqtt.component.client.callback.LoginStateCallback
    public void callbackLoginState(LoginState state, String errMsg) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        int i = WhenMappings.$EnumSwitchMapping$2[state.ordinal()];
        if (i == 1) {
            Pdlog.m3273d(this.TAG, "登录成功");
            subscribe();
        } else {
            if (i != 2) {
                return;
            }
            Pdlog.m3277w(this.TAG, errMsg);
        }
    }

    @Override // com.pudutech.mqtt.component.client.callback.SubscribeStateCallback
    public void callbackSubscribeState(SubscribeState state, List<String> topicList) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        int i = WhenMappings.$EnumSwitchMapping$3[state.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            Pdlog.m3277w(this.TAG, "主题订阅失败");
        } else {
            Pdlog.m3273d(this.TAG, "主题订阅成功：" + topicList);
        }
    }

    @Override // com.pudutech.mqtt.component.client.callback.UnsubscribeStateCallback
    public void callbackUnsubscribeState(UnsubscribeState state, List<String> topicList) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        int i = WhenMappings.$EnumSwitchMapping$4[state.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            Pdlog.m3277w(this.TAG, "主题取消订阅失败");
        } else {
            Pdlog.m3273d(this.TAG, "主题取消订阅成功：" + topicList);
        }
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void callElv(String curr, String dst, long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(curr, "curr");
        Intrinsics.checkParameterIsNotNull(dst, "dst");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
        Pdlog.m3273d(this.TAG, "callElv()");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("robot_id", robotId);
        linkedHashMap.put("curr_floor", curr);
        linkedHashMap.put("dst_floor", dst);
        linkedHashMap.put("seq_num", Long.valueOf(seq));
        linkedHashMap.put("ts", String.valueOf(System.currentTimeMillis()));
        MqttClientFactory.INSTANCE.getMqttClient().publishMsg(this.debug + "robot/call_elv/" + elvId, this.gson.toJson(linkedHashMap));
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void enterElvAck(long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
        Pdlog.m3273d(this.TAG, "enterElvAck()");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("robot_id", robotId);
        linkedHashMap.put("seq_num", Long.valueOf(seq));
        linkedHashMap.put("ts", String.valueOf(System.currentTimeMillis()));
        MqttClientFactory.INSTANCE.getMqttClient().publishMsg(this.debug + "robot/enter_elv_ack/" + elvId, this.gson.toJson(linkedHashMap));
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void elvEntered(String curr, long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(curr, "curr");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
        Pdlog.m3273d(this.TAG, "elvEntered()");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("robot_id", robotId);
        linkedHashMap.put("curr_floor", curr);
        linkedHashMap.put("seq_num", Long.valueOf(seq));
        linkedHashMap.put("ts", String.valueOf(System.currentTimeMillis()));
        MqttClientFactory.INSTANCE.getMqttClient().publishMsg(this.debug + "robot/elv_entered/" + elvId, this.gson.toJson(linkedHashMap));
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void leaveElvAck(long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
        Pdlog.m3273d(this.TAG, "leaveElvAck()");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("robot_id", robotId);
        linkedHashMap.put("seq_num", Long.valueOf(seq));
        linkedHashMap.put("ts", String.valueOf(System.currentTimeMillis()));
        MqttClientFactory.INSTANCE.getMqttClient().publishMsg(this.debug + "robot/leave_elv_ack/" + elvId, this.gson.toJson(linkedHashMap));
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void elvLeft(String curr, long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(curr, "curr");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
        Pdlog.m3273d(this.TAG, "elvLeft()");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("robot_id", robotId);
        linkedHashMap.put("curr_floor", curr);
        linkedHashMap.put("seq_num", Long.valueOf(seq));
        linkedHashMap.put("ts", String.valueOf(System.currentTimeMillis()));
        MqttClientFactory.INSTANCE.getMqttClient().publishMsg(this.debug + "robot/elv_left/" + elvId, this.gson.toJson(linkedHashMap));
    }

    @Override // com.pudutech.mirsdk.robotsdk.ElevatorCommunicateInterface
    public void elvLeftState(String stat, String curr, long seq, String robotId, String elvId) {
        Intrinsics.checkParameterIsNotNull(stat, "stat");
        Intrinsics.checkParameterIsNotNull(curr, "curr");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(elvId, "elvId");
        Pdlog.m3273d(this.TAG, "elvLeftState()");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("stat", stat);
        linkedHashMap.put("robot_id", robotId);
        linkedHashMap.put("curr_floor", curr);
        linkedHashMap.put("seq_num", Long.valueOf(seq));
        linkedHashMap.put("ts", String.valueOf(System.currentTimeMillis()));
        MqttClientFactory.INSTANCE.getMqttClient().publishMsg(this.debug + "robot/elv_left_stat/" + elvId, this.gson.toJson(linkedHashMap));
    }

    private final void subscribe() {
        Pdlog.m3273d(this.TAG, "订阅主题");
        MqttClientFactory.INSTANCE.getMqttClient().subscribe(new SubscribeMessage[]{new SubscribeMessage(this.debug + "elv_iot/call_elv_ack/" + this.robotId, Qos.AT_MOST_ONCE), new SubscribeMessage(this.debug + "elv_iot/enter_elv/" + this.robotId, Qos.AT_MOST_ONCE), new SubscribeMessage(this.debug + "elv_iot/elv_entered_ack/" + this.robotId, Qos.AT_MOST_ONCE), new SubscribeMessage(this.debug + "elv_iot/leave_elv/" + this.robotId, Qos.AT_MOST_ONCE), new SubscribeMessage(this.debug + "elv_iot/elv_left_ack/" + this.robotId, Qos.AT_MOST_ONCE), new SubscribeMessage(this.debug + "elv_iot/elv_left_stat_ack/" + this.robotId, Qos.AT_MOST_ONCE)}, this);
    }
}
