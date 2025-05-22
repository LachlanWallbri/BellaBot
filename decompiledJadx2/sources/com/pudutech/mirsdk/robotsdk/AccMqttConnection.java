package com.pudutech.mirsdk.robotsdk;

import android.content.Context;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.AccessControlServer;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: AccMqttConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000bH\u0016J \u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000bH\u0016J\u001a\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u000bH\u0016J \u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u001c2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001eH\u0016J \u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020 2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001eH\u0016J\b\u0010!\u001a\u00020\u0012H\u0016J\u001c\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020$2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rJ\b\u0010%\u001a\u00020\u0012H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, m3961d2 = {"Lcom/pudutech/mirsdk/robotsdk/AccMqttConnection;", "Lcom/pudutech/mqtt/component/client/callback/LoginStateCallback;", "Lcom/pudutech/mqtt/component/client/callback/SubscribeStateCallback;", "Lcom/pudutech/mqtt/component/client/callback/UnsubscribeStateCallback;", "Lcom/pudutech/mirsdk/robotsdk/AccConnectionInterface;", "context", "Landroid/content/Context;", "connectionType", "Lcom/pudutech/mirsdk/aidl/serialize/AccessControlServer;", "(Landroid/content/Context;Lcom/pudutech/mirsdk/aidl/serialize/AccessControlServer;)V", "TAG", "", "accessDoorId", "", "debug", "gson", "Lcom/google/gson/Gson;", "callCloseDoor", "", "seq", "robotId", "accId", "callOpenDoor", "callbackLoginState", "state", "Lcom/pudutech/mqtt/component/client/config/LoginState;", "errMsg", "callbackSubscribeState", "Lcom/pudutech/mqtt/component/client/config/SubscribeState;", "topicList", "", "callbackUnsubscribeState", "Lcom/pudutech/mqtt/component/client/config/UnsubscribeState;", MqttServiceConstants.DISCONNECT_ACTION, "init", "recver", "Lcom/pudutech/mqtt/component/client/callback/MessageReceiverListener;", MqttServiceConstants.SUBSCRIBE_ACTION, "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AccMqttConnection implements LoginStateCallback, SubscribeStateCallback, UnsubscribeStateCallback, AccConnectionInterface {
    private final String TAG;
    private List<String> accessDoorId;
    private final AccessControlServer connectionType;
    private final Context context;
    private final String debug;
    private final Gson gson;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[AccessControlServer.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;
        public static final /* synthetic */ int[] $EnumSwitchMapping$6;
        public static final /* synthetic */ int[] $EnumSwitchMapping$7;
        public static final /* synthetic */ int[] $EnumSwitchMapping$8;

        static {
            $EnumSwitchMapping$0[AccessControlServer.CommCat.ordinal()] = 1;
            $EnumSwitchMapping$0[AccessControlServer.XinYiLian.ordinal()] = 2;
            $EnumSwitchMapping$0[AccessControlServer.YouDianTest.ordinal()] = 3;
            $EnumSwitchMapping$0[AccessControlServer.YouDian.ordinal()] = 4;
            $EnumSwitchMapping$1 = new int[AccessControlServer.values().length];
            $EnumSwitchMapping$1[AccessControlServer.CommCat.ordinal()] = 1;
            $EnumSwitchMapping$1[AccessControlServer.XinYiLian.ordinal()] = 2;
            $EnumSwitchMapping$1[AccessControlServer.YouDianTest.ordinal()] = 3;
            $EnumSwitchMapping$1[AccessControlServer.YouDian.ordinal()] = 4;
            $EnumSwitchMapping$2 = new int[AccessControlServer.values().length];
            $EnumSwitchMapping$2[AccessControlServer.CommCat.ordinal()] = 1;
            $EnumSwitchMapping$2[AccessControlServer.XinYiLian.ordinal()] = 2;
            $EnumSwitchMapping$2[AccessControlServer.YouDianTest.ordinal()] = 3;
            $EnumSwitchMapping$2[AccessControlServer.YouDian.ordinal()] = 4;
            $EnumSwitchMapping$3 = new int[LoginState.values().length];
            $EnumSwitchMapping$3[LoginState.LoginSuccessful.ordinal()] = 1;
            $EnumSwitchMapping$3[LoginState.LoginFailure.ordinal()] = 2;
            $EnumSwitchMapping$4 = new int[SubscribeState.values().length];
            $EnumSwitchMapping$4[SubscribeState.SubscribeSuccessful.ordinal()] = 1;
            $EnumSwitchMapping$4[SubscribeState.SubscribeFailure.ordinal()] = 2;
            $EnumSwitchMapping$5 = new int[UnsubscribeState.values().length];
            $EnumSwitchMapping$5[UnsubscribeState.UnsubscribeSuccessful.ordinal()] = 1;
            $EnumSwitchMapping$5[UnsubscribeState.UnsubscribeFailure.ordinal()] = 2;
            $EnumSwitchMapping$6 = new int[AccessControlServer.values().length];
            $EnumSwitchMapping$6[AccessControlServer.YouDian.ordinal()] = 1;
            $EnumSwitchMapping$6[AccessControlServer.YouDianTest.ordinal()] = 2;
            $EnumSwitchMapping$7 = new int[AccessControlServer.values().length];
            $EnumSwitchMapping$7[AccessControlServer.YouDian.ordinal()] = 1;
            $EnumSwitchMapping$7[AccessControlServer.YouDianTest.ordinal()] = 2;
            $EnumSwitchMapping$8 = new int[AccessControlServer.values().length];
            $EnumSwitchMapping$8[AccessControlServer.YouDian.ordinal()] = 1;
            $EnumSwitchMapping$8[AccessControlServer.YouDianTest.ordinal()] = 2;
        }
    }

    public AccMqttConnection(Context context, AccessControlServer connectionType) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(connectionType, "connectionType");
        this.context = context;
        this.connectionType = connectionType;
        this.TAG = "acc";
        this.gson = new Gson();
        this.debug = this.connectionType == AccessControlServer.CommCat ? "/public/TEST/" : "";
    }

    public final void init(MessageReceiverListener recver, List<String> accessDoorId) {
        MqttParamOptions build;
        Intrinsics.checkParameterIsNotNull(recver, "recver");
        Intrinsics.checkParameterIsNotNull(accessDoorId, "accessDoorId");
        this.accessDoorId = accessDoorId;
        int i = WhenMappings.$EnumSwitchMapping$0[this.connectionType.ordinal()];
        String str = i != 1 ? i != 2 ? i != 3 ? i != 4 ? "" : "192.168.1.200" : "broker.hivemq.com" : "xy0023.sinilink.com" : "mq.tongxinmao.com";
        int i2 = WhenMappings.$EnumSwitchMapping$1[this.connectionType.ordinal()];
        int i3 = 1883;
        if (i2 == 1 || (i2 != 2 && i2 != 3 && i2 != 4)) {
            i3 = 18830;
        }
        int i4 = WhenMappings.$EnumSwitchMapping$2[this.connectionType.ordinal()];
        if (i4 == 1) {
            build = new MqttParamOptions.Builder().hasUserName(false).hasPassword(false).setHost(str).setPort(i3).setLoginStateCallback(this).setMessageReceiverListener(recver).build();
        } else if (i4 == 2) {
            build = new MqttParamOptions.Builder().hasUserName(true).hasPassword(true).setUserName("xy0023").setPassword("6f2e4209-09b7-4152-b632-76b55703ea98").setHost(str).setPort(i3).setLoginStateCallback(this).setMessageReceiverListener(recver).build();
        } else if (i4 == 3) {
            build = new MqttParamOptions.Builder().hasUserName(true).hasPassword(true).setUserName("qedoor").setPassword("TheNovelty5").setHost(str).setPort(i3).setLoginStateCallback(this).setMessageReceiverListener(recver).build();
        } else {
            build = i4 != 4 ? null : new MqttParamOptions.Builder().hasUserName(true).hasPassword(true).setUserName("novelte").setPassword("mqtt").setHost(str).setPort(i3).setLoginStateCallback(this).setMessageReceiverListener(recver).build();
        }
        if (build != null) {
            MqttClientFactory.INSTANCE.getMqttClient().init(this.context, build).connect();
        }
    }

    @Override // com.pudutech.mirsdk.robotsdk.AccConnectionInterface
    public void disconnect() {
        MqttClientFactory.INSTANCE.getMqttClient().close();
    }

    @Override // com.pudutech.mqtt.component.client.callback.LoginStateCallback
    public void callbackLoginState(LoginState state, String errMsg) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        int i = WhenMappings.$EnumSwitchMapping$3[state.ordinal()];
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
        int i = WhenMappings.$EnumSwitchMapping$4[state.ordinal()];
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
        int i = WhenMappings.$EnumSwitchMapping$5[state.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            Pdlog.m3277w(this.TAG, "主题取消订阅失败");
        } else {
            Pdlog.m3273d(this.TAG, "主题取消订阅成功：" + topicList);
        }
    }

    @Override // com.pudutech.mirsdk.robotsdk.AccConnectionInterface
    public void callOpenDoor(String seq, String robotId, String accId) {
        Intrinsics.checkParameterIsNotNull(seq, "seq");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(accId, "accId");
        Pdlog.m3273d(this.TAG, "callOpenDoor for access control: " + accId);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = WhenMappings.$EnumSwitchMapping$6[this.connectionType.ordinal()];
        if (i == 1 || i == 2) {
            linkedHashMap.put("msgId", seq);
            linkedHashMap.put("fromId", robotId);
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            linkedHashMap2.put("command", "open");
            linkedHashMap2.put("ext", "{\"access_ids\":[\"" + accId + "\"]}");
            linkedHashMap.put("body", linkedHashMap2);
            MqttClientFactory.INSTANCE.getMqttClient().publishMsg(this.debug + "pdrobot/user/door_control", this.gson.toJson(linkedHashMap));
            return;
        }
        linkedHashMap.put("deviceId", accId);
        linkedHashMap.put("action", "ON");
        linkedHashMap.put("appsecret", "da06b165-61ab-4fdf-8ff0-28a57d4a377c");
        MqttClientFactory.INSTANCE.getMqttClient().publishMsg(this.debug + "switchaction0001", this.gson.toJson(linkedHashMap));
    }

    @Override // com.pudutech.mirsdk.robotsdk.AccConnectionInterface
    public void callCloseDoor(String seq, String robotId, String accId) {
        Intrinsics.checkParameterIsNotNull(seq, "seq");
        Intrinsics.checkParameterIsNotNull(robotId, "robotId");
        Intrinsics.checkParameterIsNotNull(accId, "accId");
        Pdlog.m3273d(this.TAG, "callCloseDoor for access control: " + accId);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = WhenMappings.$EnumSwitchMapping$7[this.connectionType.ordinal()];
        if (i == 1 || i == 2) {
            linkedHashMap.put("msgId", seq);
            linkedHashMap.put("fromId", robotId);
            LinkedHashMap linkedHashMap2 = new LinkedHashMap();
            linkedHashMap2.put("command", "close");
            linkedHashMap2.put("ext", "{\"access_ids\":[\"" + accId + "\"]}");
            linkedHashMap.put("body", linkedHashMap2);
            MqttClientFactory.INSTANCE.getMqttClient().publishMsg(this.debug + "pdrobot/user/door_control", this.gson.toJson(linkedHashMap));
            return;
        }
        linkedHashMap.put("deviceId", accId);
        linkedHashMap.put("action", "OFF");
        linkedHashMap.put("appsecret", "da06b165-61ab-4fdf-8ff0-28a57d4a377c");
        MqttClientFactory.INSTANCE.getMqttClient().publishMsg(this.debug + "switchaction0001", this.gson.toJson(linkedHashMap));
    }

    private final void subscribe() {
        Pdlog.m3273d(this.TAG, "订阅主题");
        int i = WhenMappings.$EnumSwitchMapping$8[this.connectionType.ordinal()];
        if (i == 1 || i == 2) {
            MqttClientFactory.INSTANCE.getMqttClient().subscribe(new SubscribeMessage[]{new SubscribeMessage(this.debug + "pdrobot/user/door_status", Qos.AT_MOST_ONCE)}, this);
            return;
        }
        ArrayList<SubscribeMessage> arrayList = new ArrayList<>();
        List<String> list = this.accessDoorId;
        if (list != null) {
            if (list == null) {
                Intrinsics.throwNpe();
            }
            for (String str : list) {
                Pdlog.m3273d(this.TAG, "subscribe access id " + str);
                arrayList.add(new SubscribeMessage(this.debug + "returnstatus" + str, Qos.AT_MOST_ONCE));
            }
            MqttClientFactory.INSTANCE.getMqttClient().subscribe(arrayList, this);
        }
    }
}
