package com.pudutech.bumblebee.presenter.robot_open_task.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import androidx.collection.ArraySet;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.BusinessContext;
import com.pudutech.bumblebee.presenter.robot_open_task.config.Topic;
import com.pudutech.disinfect.baselib.base.viewmodel.ext.BaseViewModelExtKt;
import com.pudutech.disinfect.baselib.state.AppException;
import com.pudutech.pdmqtt.ActionCallback;
import com.pudutech.pdmqtt.GetClientCallback;
import com.pudutech.pdmqtt.MqttClientBuilder;
import com.pudutech.pdmqtt.MqttLogger;
import com.pudutech.pdmqtt.OnMessageChangeListener;
import com.pudutech.pdmqtt.PuduMqttManager;
import com.pudutech.pdmqtt.client.IFetchClientCallback;
import com.pudutech.pdmqtt.client.IMqttClient;
import com.pudutech.pdmqtt.client.MqttConnectState;
import com.pudutech.pdmqtt.config.BaseKt;
import com.pudutech.pdmqtt.config.BaseMqttConfig;
import com.pudutech.pdmqtt.config.MqttRegisterInfo;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.eclipse.paho.client.mqttv3.MqttException;

/* compiled from: MqttManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000s\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0014\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010&\u001a\u00020\u000e2\b\u0010'\u001a\u0004\u0018\u00010(J\u0006\u0010)\u001a\u00020\u000eJ$\u0010*\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020,2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\"\u0010/\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020,2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u000e0\rJ$\u00100\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020,2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\r\u00101\u001a\u0004\u0018\u00010.¢\u0006\u0002\u00102J6\u00103\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\b2\u0006\u00104\u001a\u00020\b2\u001e\b\u0002\u0010'\u001a\u0018\u0012\u0004\u0012\u00020.\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u001dJ\b\u00105\u001a\u00020\u000eH\u0002J\u001c\u00105\u001a\u00020\u000e2\f\u00106\u001a\b\u0012\u0004\u0012\u00020\b072\u0006\u0010'\u001a\u000208J\u0006\u00109\u001a\u00020\u000eJ\u0014\u00109\u001a\u00020\u000e2\f\u00106\u001a\b\u0012\u0004\u0012\u00020\b07R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0019\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR(\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u001bX\u0082\u000e¢\u0006\u0002\n\u0000RL\u0010\u001c\u001a4\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b( \u0012\u0013\u0012\u00110\b¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u0006:"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/manager/MqttManager;", "", "()V", "DELAY_TIME", "", "FETCH_CLIENT_WHAT", "", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "connectStatusListener", "Lkotlin/Function1;", "", "getConnectStatusListener", "()Lkotlin/jvm/functions/Function1;", "setConnectStatusListener", "(Lkotlin/jvm/functions/Function1;)V", "handler", "com/pudutech/bumblebee/presenter/robot_open_task/manager/MqttManager$handler$1", "Lcom/pudutech/bumblebee/presenter/robot_open_task/manager/MqttManager$handler$1;", "mqttClient", "Lcom/pudutech/pdmqtt/client/IMqttClient;", "registerInfo", "Lcom/pudutech/pdmqtt/config/MqttRegisterInfo;", "subTopicSet", "Landroidx/collection/ArraySet;", "subscribeListener", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "topic", MqttServiceConstants.PAYLOAD, "getSubscribeListener", "()Lkotlin/jvm/functions/Function2;", "setSubscribeListener", "(Lkotlin/jvm/functions/Function2;)V", MqttServiceConstants.CONNECT_ACTION, "callback", "Lcom/pudutech/pdmqtt/GetClientCallback;", "destroy", "fetchClient", "context", "Landroid/content/Context;", "initResult", "", "init", "initMqtt", "isConnect", "()Ljava/lang/Boolean;", "sendMessage", "message", MqttServiceConstants.SUBSCRIBE_ACTION, "topicSet", "", "Lcom/pudutech/pdmqtt/ActionCallback;", "unSubscribe", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MqttManager {
    private static final long DELAY_TIME = 100;
    private static final int FETCH_CLIENT_WHAT = 1;
    public static final MqttManager INSTANCE;
    private static final String TAG;
    private static Function1<? super Integer, Unit> connectStatusListener;
    private static final MqttManager$handler$1 handler;
    private static IMqttClient mqttClient;
    private static MqttRegisterInfo registerInfo;
    private static ArraySet<String> subTopicSet;
    private static Function2<? super String, ? super String, Unit> subscribeListener;

    /* JADX WARN: Type inference failed for: r0v4, types: [com.pudutech.bumblebee.presenter.robot_open_task.manager.MqttManager$handler$1] */
    static {
        MqttManager mqttManager = new MqttManager();
        INSTANCE = mqttManager;
        TAG = mqttManager.getClass().getSimpleName();
        subTopicSet = new ArraySet<>();
        final Looper mainLooper = Looper.getMainLooper();
        handler = new Handler(mainLooper) { // from class: com.pudutech.bumblebee.presenter.robot_open_task.manager.MqttManager$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg == null || msg.what != 1) {
                    return;
                }
                MqttManager.INSTANCE.fetchClient(BusinessContext.INSTANCE.getContext(), new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.manager.MqttManager$handler$1$handleMessage$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                        invoke(bool.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(boolean z) {
                        if (z) {
                            return;
                        }
                        sendEmptyMessageDelayed(1, 100L);
                    }
                });
            }
        };
        PuduMqttManager.INSTANCE.onServiceUnbind(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.manager.MqttManager.1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                Pdlog.m3273d(MqttManager.INSTANCE.getTAG(), "onServiceUnbind");
                MqttManager mqttManager2 = MqttManager.INSTANCE;
                MqttManager.mqttClient = (IMqttClient) null;
                MqttManager.access$getHandler$p(MqttManager.INSTANCE).sendEmptyMessageDelayed(1, MqttManager.DELAY_TIME);
            }
        });
    }

    private MqttManager() {
    }

    public static final /* synthetic */ MqttManager$handler$1 access$getHandler$p(MqttManager mqttManager) {
        return handler;
    }

    public final String getTAG() {
        return TAG;
    }

    public final Function2<String, String, Unit> getSubscribeListener() {
        return subscribeListener;
    }

    public final void setSubscribeListener(Function2<? super String, ? super String, Unit> function2) {
        subscribeListener = function2;
    }

    public final Function1<Integer, Unit> getConnectStatusListener() {
        return connectStatusListener;
    }

    public final void setConnectStatusListener(Function1<? super Integer, Unit> function1) {
        connectStatusListener = function1;
    }

    public final void init(Context context, Function1<? super Boolean, Unit> initResult) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(initResult, "initResult");
        initMqtt(context, initResult);
    }

    private final void initMqtt(final Context context, final Function1<? super Boolean, Unit> initResult) {
        BaseViewModelExtKt.requestNetworkData$default(new MqttManager$initMqtt$1(null), new Function1<MqttRegisterInfo, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.manager.MqttManager$initMqtt$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MqttRegisterInfo mqttRegisterInfo) {
                invoke2(mqttRegisterInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MqttRegisterInfo it) {
                MqttRegisterInfo mqttRegisterInfo;
                String str;
                MqttRegisterInfo mqttRegisterInfo2;
                ArraySet arraySet;
                ArraySet arraySet2;
                ArraySet arraySet3;
                String device_name;
                Intrinsics.checkParameterIsNotNull(it, "it");
                Pdlog.m3273d(MqttManager.INSTANCE.getTAG(), "initMqtt registerInfo:" + it + ' ');
                MqttManager mqttManager = MqttManager.INSTANCE;
                MqttManager.registerInfo = it;
                Topic topic = Topic.INSTANCE;
                MqttManager mqttManager2 = MqttManager.INSTANCE;
                mqttRegisterInfo = MqttManager.registerInfo;
                String str2 = "";
                if (mqttRegisterInfo == null || (str = mqttRegisterInfo.getProduct_key()) == null) {
                    str = "";
                }
                MqttManager mqttManager3 = MqttManager.INSTANCE;
                mqttRegisterInfo2 = MqttManager.registerInfo;
                if (mqttRegisterInfo2 != null && (device_name = mqttRegisterInfo2.getDevice_name()) != null) {
                    str2 = device_name;
                }
                topic.initTopic(str, str2);
                MqttManager mqttManager4 = MqttManager.INSTANCE;
                arraySet = MqttManager.subTopicSet;
                arraySet.clear();
                MqttManager mqttManager5 = MqttManager.INSTANCE;
                arraySet2 = MqttManager.subTopicSet;
                arraySet2.addAll(Topic.INSTANCE.getCallTopic());
                String tag = MqttManager.INSTANCE.getTAG();
                StringBuilder sb = new StringBuilder();
                sb.append("initMqtt()  subTopicSet = ");
                MqttManager mqttManager6 = MqttManager.INSTANCE;
                arraySet3 = MqttManager.subTopicSet;
                sb.append(arraySet3);
                Pdlog.m3273d(tag, sb.toString());
                PuduMqttManager.INSTANCE.setLog(new MqttLogger.Stub() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.manager.MqttManager$initMqtt$2.1
                    @Override // com.pudutech.pdmqtt.MqttLogger
                    public void log(String tag2, String message) {
                        Pdlog.m3273d(MqttManager.INSTANCE.getTAG(), tag2 + " ==> " + message);
                    }
                });
                MqttManager.INSTANCE.fetchClient(context, initResult);
            }
        }, new Function1<AppException, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.manager.MqttManager$initMqtt$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AppException appException) {
                invoke2(appException);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AppException it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Function1.this.invoke(false);
                Pdlog.m3273d(MqttManager.INSTANCE.getTAG(), "initMqtt: " + Log.getStackTraceString(it));
            }
        }, null, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fetchClient(Context context, final Function1<? super Boolean, Unit> initResult) {
        PuduMqttManager.INSTANCE.fetchClient(context, new Function1<MqttClientBuilder, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.manager.MqttManager$fetchClient$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                MqttRegisterInfo mqttRegisterInfo;
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                MqttManager mqttManager = MqttManager.INSTANCE;
                mqttRegisterInfo = MqttManager.registerInfo;
                receiver.setMqttConfig(mqttRegisterInfo != null ? mqttRegisterInfo.toMqttConfig() : null);
                String tag = MqttManager.INSTANCE.getTAG();
                Object[] objArr = new Object[1];
                StringBuilder sb = new StringBuilder();
                sb.append("init: ");
                BaseMqttConfig mqttConfig = receiver.getMqttConfig();
                sb.append(mqttConfig != null ? BaseKt.toStr(mqttConfig) : null);
                objArr[0] = sb.toString();
                Pdlog.m3273d(tag, objArr);
                receiver.setClientCallback(new IFetchClientCallback() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.manager.MqttManager$fetchClient$1.1
                    @Override // com.pudutech.pdmqtt.client.IFetchClientCallback
                    public void onError(BaseMqttConfig requestConfig, int code, String message) {
                        Intrinsics.checkParameterIsNotNull(requestConfig, "requestConfig");
                        Intrinsics.checkParameterIsNotNull(message, "message");
                        Pdlog.m3274e(MqttManager.INSTANCE.getTAG(), "连接失败： requestConfig:" + requestConfig + ",message:" + message + ",code:" + code);
                        Function1.this.invoke(false);
                    }

                    @Override // com.pudutech.pdmqtt.client.IFetchClientCallback
                    public void onSuccess(BaseMqttConfig requestConfig, IMqttClient client) {
                        Intrinsics.checkParameterIsNotNull(requestConfig, "requestConfig");
                        Intrinsics.checkParameterIsNotNull(client, "client");
                        Pdlog.m3273d(MqttManager.INSTANCE.getTAG(), "连接成功");
                        Function1.this.invoke(true);
                        MqttManager mqttManager2 = MqttManager.INSTANCE;
                        MqttManager.mqttClient = client;
                        MqttManager.INSTANCE.subscribe();
                        client.addOnConnectStateChangeLis(new Function1<MqttConnectState, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.manager.MqttManager$fetchClient$1$1$onSuccess$1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(MqttConnectState mqttConnectState) {
                                invoke2(mqttConnectState);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(MqttConnectState it) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                Pdlog.m3273d(MqttManager.INSTANCE.getTAG(), "连接状态： " + it);
                                Function1<Integer, Unit> connectStatusListener2 = MqttManager.INSTANCE.getConnectStatusListener();
                                if (connectStatusListener2 != null) {
                                    connectStatusListener2.invoke(Integer.valueOf(it.ordinal()));
                                }
                            }
                        });
                        client.addOnMessageChangeListener(new OnMessageChangeListener.Stub() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.manager.MqttManager$fetchClient$1$1$onSuccess$2
                            @Override // com.pudutech.pdmqtt.OnMessageChangeListener
                            public void onReceive(String topic, String payload) {
                                ArraySet arraySet;
                                Function2<String, String, Unit> subscribeListener2;
                                Intrinsics.checkParameterIsNotNull(topic, "topic");
                                Intrinsics.checkParameterIsNotNull(payload, "payload");
                                Pdlog.m3274e(MqttManager.INSTANCE.getTAG(), "收到消息：" + topic + " ===> " + payload);
                                MqttManager mqttManager3 = MqttManager.INSTANCE;
                                arraySet = MqttManager.subTopicSet;
                                if (!arraySet.contains(topic) || (subscribeListener2 = MqttManager.INSTANCE.getSubscribeListener()) == null) {
                                    return;
                                }
                                subscribeListener2.invoke(topic, payload);
                            }

                            @Override // com.pudutech.pdmqtt.OnMessageChangeListener
                            public void onSend(String topic, String payload) {
                                Intrinsics.checkParameterIsNotNull(topic, "topic");
                                Intrinsics.checkParameterIsNotNull(payload, "payload");
                                Pdlog.m3273d(MqttManager.INSTANCE.getTAG(), "发送成功： " + topic + ' ' + payload);
                            }
                        });
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void subscribe() {
        subscribe(subTopicSet, new ActionCallback.Stub() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.manager.MqttManager$subscribe$1
            @Override // com.pudutech.pdmqtt.ActionCallback
            public void actionState(int p0, String p1) {
                Pdlog.m3273d(MqttManager.INSTANCE.getTAG(), "actionState " + p0 + ' ' + p1);
            }
        });
    }

    public final void subscribe(Set<String> topicSet, ActionCallback callback) {
        Intrinsics.checkParameterIsNotNull(topicSet, "topicSet");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Pdlog.m3273d(TAG, "subscribe() topicSet = " + topicSet + "  callback = " + callback);
        IMqttClient iMqttClient = mqttClient;
        if (iMqttClient != null) {
            iMqttClient.subscribe(topicSet, callback);
        }
        Pdlog.m3273d(TAG, "subscribe() end mqttClient = " + mqttClient);
    }

    public final void unSubscribe() {
        unSubscribe(subTopicSet);
    }

    public final void unSubscribe(Set<String> topicSet) {
        Intrinsics.checkParameterIsNotNull(topicSet, "topicSet");
        IMqttClient iMqttClient = mqttClient;
        if (iMqttClient != null) {
            iMqttClient.unsubscribe(topicSet);
        }
    }

    public final Boolean isConnect() {
        IMqttClient iMqttClient = mqttClient;
        if (iMqttClient != null) {
            return Boolean.valueOf(iMqttClient.isConnect());
        }
        return null;
    }

    public final void connect(GetClientCallback callback) {
        IMqttClient iMqttClient = mqttClient;
        if (iMqttClient != null) {
            iMqttClient.connect(callback);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void sendMessage$default(MqttManager mqttManager, String str, String str2, Function2 function2, int i, Object obj) {
        if ((i & 4) != 0) {
            function2 = (Function2) null;
        }
        mqttManager.sendMessage(str, str2, function2);
    }

    public final void sendMessage(String topic, String message, Function2<? super Boolean, ? super String, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(topic, "topic");
        Intrinsics.checkParameterIsNotNull(message, "message");
        try {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new MqttManager$sendMessage$1(message, topic, callback, null), 3, null);
        } catch (MqttException e) {
            Pdlog.m3274e(TAG, "发送消息异常：" + e.getMessage());
            if (callback != null) {
                callback.invoke(false, e.getMessage());
            }
        }
    }

    public final void destroy() {
        IMqttClient iMqttClient = mqttClient;
        if (iMqttClient != null) {
            iMqttClient.release();
        }
    }
}
