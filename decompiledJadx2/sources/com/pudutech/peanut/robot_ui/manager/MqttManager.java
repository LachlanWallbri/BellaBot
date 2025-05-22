package com.pudutech.peanut.robot_ui.manager;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import com.loc.C3898x;
import com.pudutech.base.Pdlog;
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
import com.pudutech.peanut.robot_ui.config.Constans;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: MqttManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\bÆ\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0005J\u001a\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0007H\u0016J \u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00182\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u001aH\u0016J \u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u001c2\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u001aH\u0016J\u0006\u0010\u001d\u001a\u00020\u000eJ\b\u0010\u001e\u001a\u00020\u000eH\u0002J3\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u00102#\u0010!\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\nJ\u0018\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020\u00072\u0006\u0010$\u001a\u00020\u0007H\u0016J\u0006\u0010%\u001a\u00020\u000eJ\b\u0010&\u001a\u00020\u000eH\u0002J\b\u0010'\u001a\u00020\u000eH\u0002R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\t\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\nX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, m3961d2 = {"Lcom/pudutech/peanut/robot_ui/manager/MqttManager;", "Lcom/pudutech/mqtt/component/client/callback/SubscribeStateCallback;", "Lcom/pudutech/mqtt/component/client/callback/LoginStateCallback;", "Lcom/pudutech/mqtt/component/client/callback/UnsubscribeStateCallback;", "Lcom/pudutech/mqtt/component/client/callback/MessageReceiverListener;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "mCallback", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", C3898x.f4338g, "", "mContext", "Landroid/content/Context;", "shopId", "", "callbackLoginState", "state", "Lcom/pudutech/mqtt/component/client/config/LoginState;", "errMsg", "callbackSubscribeState", "Lcom/pudutech/mqtt/component/client/config/SubscribeState;", "topicList", "", "callbackUnsubscribeState", "Lcom/pudutech/mqtt/component/client/config/UnsubscribeState;", "closeMqtt", MqttServiceConstants.CONNECT_ACTION, "init", "context", "callback", "onReceiverMessage", "topic", NotificationCompat.CATEGORY_MESSAGE, "releaseMqtt", MqttServiceConstants.SUBSCRIBE_ACTION, MqttServiceConstants.UNSUBSCRIBE_ACTION, "robot_ui_peanutRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MqttManager implements SubscribeStateCallback, LoginStateCallback, UnsubscribeStateCallback, MessageReceiverListener {
    public static final MqttManager INSTANCE;
    private static final String TAG;
    private static Function1<? super String, Unit> mCallback;
    private static Context mContext;
    private static int shopId;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[SubscribeState.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            $EnumSwitchMapping$0[SubscribeState.SubscribeSuccessful.ordinal()] = 1;
            $EnumSwitchMapping$0[SubscribeState.SubscribeFailure.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[UnsubscribeState.values().length];
            $EnumSwitchMapping$1[UnsubscribeState.UnsubscribeSuccessful.ordinal()] = 1;
            $EnumSwitchMapping$1[UnsubscribeState.UnsubscribeFailure.ordinal()] = 2;
            $EnumSwitchMapping$2 = new int[LoginState.values().length];
            $EnumSwitchMapping$2[LoginState.LoginSuccessful.ordinal()] = 1;
            $EnumSwitchMapping$2[LoginState.LoginFailure.ordinal()] = 2;
        }
    }

    static {
        MqttManager mqttManager = new MqttManager();
        INSTANCE = mqttManager;
        TAG = mqttManager.getClass().getSimpleName();
        shopId = Constans.INSTANCE.getShopInfo().getShop_id();
    }

    private MqttManager() {
    }

    public final void init(Context context, Function1<? super String, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(TAG, "init");
        mCallback = callback;
        mContext = context;
        connect();
    }

    private final void connect() {
        Context context;
        if (("emqx-test.pudutech.com".length() == 0) || (context = mContext) == null) {
            return;
        }
        MqttParamOptions mqttParamOptions = new MqttParamOptions.Builder().hasUserName(!Intrinsics.areEqual("robot-queue", "")).hasPassword(true ^ Intrinsics.areEqual("robot-queue", "")).setUserName("robot-queue").setPassword("t1CljAqeLzZOhQKP").setHost("emqx-test.pudutech.com").setPort(1883).setLoginStateCallback(INSTANCE).setMessageReceiverListener(INSTANCE).build();
        IMqttClient mqttClient = MqttClientFactory.INSTANCE.getMqttClient();
        Intrinsics.checkExpressionValueIsNotNull(mqttParamOptions, "mqttParamOptions");
        mqttClient.init(context, mqttParamOptions).connect();
    }

    private final void subscribe() {
        Pdlog.m3273d(TAG, "订阅主题");
        MqttClientFactory.INSTANCE.getMqttClient().subscribe(CollectionsKt.arrayListOf(new SubscribeMessage(shopId + "/queue/call", Qos.AT_LEAST_ONCE)), this);
    }

    private final void unsubscribe() {
        Pdlog.m3273d(TAG, "取消订阅主题");
        MqttClientFactory.INSTANCE.getMqttClient().unsubscribe(new String[]{shopId + "/queue/call"}, this);
    }

    @Override // com.pudutech.mqtt.component.client.callback.SubscribeStateCallback
    public void callbackSubscribeState(SubscribeState state, List<String> topicList) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        int i = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            Pdlog.m3277w(TAG, "主题订阅失败");
        } else {
            Pdlog.m3273d(TAG, "主题订阅成功：" + topicList);
        }
    }

    @Override // com.pudutech.mqtt.component.client.callback.UnsubscribeStateCallback
    public void callbackUnsubscribeState(UnsubscribeState state, List<String> topicList) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        int i = WhenMappings.$EnumSwitchMapping$1[state.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return;
            }
            Pdlog.m3277w(TAG, "主题取消订阅失败");
        } else {
            Pdlog.m3273d(TAG, "主题取消订阅成功：" + topicList);
        }
    }

    @Override // com.pudutech.mqtt.component.client.callback.MessageReceiverListener
    public void onReceiverMessage(String topic, String msg) {
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Pdlog.m3273d(TAG, "topic = " + topic + ", msg = " + msg);
        Function1<? super String, Unit> function1 = mCallback;
        if (function1 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mCallback");
        }
        function1.invoke(msg);
    }

    @Override // com.pudutech.mqtt.component.client.callback.LoginStateCallback
    public void callbackLoginState(LoginState state, String errMsg) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        int i = WhenMappings.$EnumSwitchMapping$2[state.ordinal()];
        if (i == 1) {
            Pdlog.m3273d(TAG, "登录成功");
            subscribe();
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new MqttManager$callbackLoginState$1(null), 2, null);
        } else {
            if (i != 2) {
                return;
            }
            Pdlog.m3277w(TAG, errMsg);
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new MqttManager$callbackLoginState$2(errMsg, null), 2, null);
        }
    }

    public final void closeMqtt() {
        mContext = (Context) null;
        MqttClientFactory.INSTANCE.getMqttClient().close();
        unsubscribe();
    }

    public final void releaseMqtt() {
        MqttClientFactory.INSTANCE.getMqttClient().release();
    }
}
