package com.pudutech.mqtt.component.client.netty;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.mqtt.component.client.bean.SubscribeMessage;
import com.pudutech.mqtt.component.client.callback.SubscribeStateCallback;
import com.pudutech.mqtt.component.client.callback.UnsubscribeStateCallback;
import com.pudutech.mqtt.component.client.config.CConfig;
import com.pudutech.mqtt.component.client.config.ConnectState;
import com.pudutech.mqtt.component.client.config.MqttParamOptions;
import com.pudutech.mqtt.component.client.config.Qos;
import com.pudutech.mqtt.component.client.interf.IMqttClient;
import com.pudutech.mqtt.component.client.utils.CertificateManager;
import com.pudutech.mqtt.component.client.utils.ContextExtKt;
import com.pudutech.mqtt.component.client.utils.ExecutorServiceFactory;
import com.pudutech.mqtt.component.client.utils.SystemTool;
import com.pudutech.mqtt.component.client.utils.UUID;
import io.netty.bootstrap.AbstractBootstrapConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttConnectPayload;
import io.netty.handler.codec.mqtt.MqttConnectVariableHeader;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageFactory;
import io.netty.handler.codec.mqtt.MqttMessageIdVariableHeader;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttPublishVariableHeader;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.netty.handler.codec.mqtt.MqttSubscribeMessage;
import io.netty.handler.codec.mqtt.MqttSubscribePayload;
import io.netty.handler.codec.mqtt.MqttTopicSubscription;
import io.netty.handler.codec.mqtt.MqttUnsubscribeMessage;
import io.netty.handler.codec.mqtt.MqttUnsubscribePayload;
import io.netty.handler.codec.mqtt.MqttVersion;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: NettyMqttClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 V2\u00020\u0001:\u0002VWB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u00107\u001a\u000208J\u000e\u00109\u001a\u0002082\u0006\u0010:\u001a\u00020\fJ\b\u0010;\u001a\u000208H\u0016J\b\u0010<\u001a\u000208H\u0002J\b\u0010=\u001a\u000208H\u0002J\b\u0010>\u001a\u000208H\u0016J\b\u0010?\u001a\u00020\u0011H\u0002J\u0018\u0010@\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020#H\u0016J\b\u0010A\u001a\u000208H\u0002J$\u0010B\u001a\u0002082\b\u0010C\u001a\u0004\u0018\u00010\u00112\u0006\u0010D\u001a\u00020E2\b\u0010F\u001a\u0004\u0018\u00010\u0011H\u0016J\u001c\u0010B\u001a\u0002082\b\u0010C\u001a\u0004\u0018\u00010\u00112\b\u0010F\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u0010G\u001a\u0002082\u0006\u0010H\u001a\u00020\u0004H\u0016J\b\u0010I\u001a\u000208H\u0016J\u0012\u0010J\u001a\u0002082\b\u0010K\u001a\u0004\u0018\u00010LH\u0002J\u000e\u0010M\u001a\u0002082\u0006\u0010\u0003\u001a\u00020\u0004J/\u0010N\u001a\u0002082\u0016\u0010O\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010Q0P\"\u0004\u0018\u00010Q2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016¢\u0006\u0002\u0010RJ.\u0010N\u001a\u0002082\u001a\u0010O\u001a\u0016\u0012\u0004\u0012\u00020Q\u0018\u00010\u0010j\n\u0012\u0004\u0012\u00020Q\u0018\u0001`\u00122\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J/\u0010S\u001a\u0002082\u0016\u0010T\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110P\"\u0004\u0018\u00010\u00112\b\u00101\u001a\u0004\u0018\u000102H\u0016¢\u0006\u0002\u0010UR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010j\n\u0012\u0004\u0012\u00020\u0011\u0018\u0001`\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R.\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010j\n\u0012\u0004\u0012\u00020\u0011\u0018\u0001`\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R\u001c\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010$\u001a\u00020#2\u0006\u0010\"\u001a\u00020#8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b)\u0010*\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001c\u0010+\u001a\u0004\u0018\u00010,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u00101\u001a\u0004\u0018\u000102X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106¨\u0006X"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/netty/NettyMqttClient;", "Lcom/pudutech/mqtt/component/client/interf/IMqttClient;", "()V", "allowAutoReconnect", "", "bootstrap", "Lio/netty/bootstrap/Bootstrap;", "certificateManager", "Lcom/pudutech/mqtt/component/client/utils/CertificateManager;", "channel", "Lio/netty/channel/Channel;", "connectState", "Lcom/pudutech/mqtt/component/client/config/ConnectState;", "context", "Landroid/content/Context;", "currentSubTopics", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getCurrentSubTopics", "()Ljava/util/ArrayList;", "setCurrentSubTopics", "(Ljava/util/ArrayList;)V", "currentUnsubTopics", "getCurrentUnsubTopics", "setCurrentUnsubTopics", "executor", "Lcom/pudutech/mqtt/component/client/utils/ExecutorServiceFactory;", "getExecutor", "()Lcom/pudutech/mqtt/component/client/utils/ExecutorServiceFactory;", "setExecutor", "(Lcom/pudutech/mqtt/component/client/utils/ExecutorServiceFactory;)V", "isClosed", "isReconnecting", "<set-?>", "Lcom/pudutech/mqtt/component/client/config/MqttParamOptions;", "paramsOptions", "getParamsOptions", "()Lcom/pudutech/mqtt/component/client/config/MqttParamOptions;", "setParamsOptions", "(Lcom/pudutech/mqtt/component/client/config/MqttParamOptions;)V", "paramsOptions$delegate", "Lkotlin/properties/ReadWriteProperty;", "subscribeStateCallback", "Lcom/pudutech/mqtt/component/client/callback/SubscribeStateCallback;", "getSubscribeStateCallback", "()Lcom/pudutech/mqtt/component/client/callback/SubscribeStateCallback;", "setSubscribeStateCallback", "(Lcom/pudutech/mqtt/component/client/callback/SubscribeStateCallback;)V", "unsubscribeStateCallback", "Lcom/pudutech/mqtt/component/client/callback/UnsubscribeStateCallback;", "getUnsubscribeStateCallback", "()Lcom/pudutech/mqtt/component/client/callback/UnsubscribeStateCallback;", "setUnsubscribeStateCallback", "(Lcom/pudutech/mqtt/component/client/callback/UnsubscribeStateCallback;)V", "addHeartbeatHandler", "", "callbackConnectState", "state", "close", "closeBootstrap", "closeChannel", MqttServiceConstants.CONNECT_ACTION, "generateClientId", "init", "initBootstrap", "publishMsg", "topic", MqttServiceConstants.QOS, "Lcom/pudutech/mqtt/component/client/config/Qos;", MqttServiceConstants.PAYLOAD, "reconnect", "isFirstConnect", "release", "sendMsg", "mqttMessage", "Lio/netty/handler/codec/mqtt/MqttMessage;", "setAllowAutoReconnect", MqttServiceConstants.SUBSCRIBE_ACTION, "subscribeMessages", "", "Lcom/pudutech/mqtt/component/client/bean/SubscribeMessage;", "([Lcom/pudutech/mqtt/component/client/bean/SubscribeMessage;Lcom/pudutech/mqtt/component/client/callback/SubscribeStateCallback;)V", MqttServiceConstants.UNSUBSCRIBE_ACTION, "topics", "([Ljava/lang/String;Lcom/pudutech/mqtt/component/client/callback/UnsubscribeStateCallback;)V", "Companion", "ReconnectTask", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class NettyMqttClient implements IMqttClient {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(NettyMqttClient.class), "paramsOptions", "getParamsOptions()Lcom/pudutech/mqtt/component/client/config/MqttParamOptions;"))};

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final NettyMqttClient INSTANCE = new NettyMqttClient();
    private static final String TAG = "NettyMqttClient";
    private Bootstrap bootstrap;
    private CertificateManager certificateManager;
    private Channel channel;
    private Context context;
    private ArrayList<String> currentSubTopics;
    private ArrayList<String> currentUnsubTopics;
    private ExecutorServiceFactory executor;
    private volatile boolean isReconnecting;
    private SubscribeStateCallback subscribeStateCallback;
    private UnsubscribeStateCallback unsubscribeStateCallback;
    private volatile boolean isClosed = true;

    /* renamed from: paramsOptions$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty paramsOptions = Delegates.INSTANCE.notNull();
    private ConnectState connectState = ConnectState.UNCONNECTED;
    private boolean allowAutoReconnect = true;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ConnectState.values().length];

        static {
            $EnumSwitchMapping$0[ConnectState.UNCONNECTED.ordinal()] = 1;
            $EnumSwitchMapping$0[ConnectState.CONNECTING.ordinal()] = 2;
            $EnumSwitchMapping$0[ConnectState.CONNECTED.ordinal()] = 3;
            $EnumSwitchMapping$0[ConnectState.CONNECT_FAILED.ordinal()] = 4;
        }
    }

    public final MqttParamOptions getParamsOptions() {
        return (MqttParamOptions) this.paramsOptions.getValue(this, $$delegatedProperties[0]);
    }

    public final void setParamsOptions(MqttParamOptions mqttParamOptions) {
        Intrinsics.checkParameterIsNotNull(mqttParamOptions, "<set-?>");
        this.paramsOptions.setValue(this, $$delegatedProperties[0], mqttParamOptions);
    }

    private NettyMqttClient() {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* compiled from: NettyMqttClient.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/netty/NettyMqttClient$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/mqtt/component/client/netty/NettyMqttClient;", "getINSTANCE", "()Lcom/pudutech/mqtt/component/client/netty/NettyMqttClient;", "TAG", "", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NettyMqttClient getINSTANCE() {
            return NettyMqttClient.INSTANCE;
        }
    }

    public final ExecutorServiceFactory getExecutor() {
        return this.executor;
    }

    public final void setExecutor(ExecutorServiceFactory executorServiceFactory) {
        this.executor = executorServiceFactory;
    }

    public final SubscribeStateCallback getSubscribeStateCallback() {
        return this.subscribeStateCallback;
    }

    public final void setSubscribeStateCallback(SubscribeStateCallback subscribeStateCallback) {
        this.subscribeStateCallback = subscribeStateCallback;
    }

    public final UnsubscribeStateCallback getUnsubscribeStateCallback() {
        return this.unsubscribeStateCallback;
    }

    public final void setUnsubscribeStateCallback(UnsubscribeStateCallback unsubscribeStateCallback) {
        this.unsubscribeStateCallback = unsubscribeStateCallback;
    }

    public final ArrayList<String> getCurrentSubTopics() {
        return this.currentSubTopics;
    }

    public final void setCurrentSubTopics(ArrayList<String> arrayList) {
        this.currentSubTopics = arrayList;
    }

    public final ArrayList<String> getCurrentUnsubTopics() {
        return this.currentUnsubTopics;
    }

    public final void setCurrentUnsubTopics(ArrayList<String> arrayList) {
        this.currentUnsubTopics = arrayList;
    }

    @Override // com.pudutech.mqtt.component.client.interf.IMqttClient
    public IMqttClient init(Context context, MqttParamOptions paramsOptions) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(paramsOptions, "paramsOptions");
        Pdlog.m3273d(TAG, "init() context = " + context + ", paramsOptions = " + paramsOptions);
        this.context = context;
        setParamsOptions(paramsOptions);
        BuildersKt__BuildersKt.runBlocking$default(null, new NettyMqttClient$init$1(null), 1, null);
        this.executor = new ExecutorServiceFactory();
        ExecutorServiceFactory executorServiceFactory = this.executor;
        if (executorServiceFactory != null) {
            executorServiceFactory.initBossLoopGroup();
        }
        this.certificateManager = CertificateManager.INSTANCE.getInstance(context);
        return this;
    }

    @Override // com.pudutech.mqtt.component.client.interf.IMqttClient
    public void connect() {
        Pdlog.m3273d(TAG, "connect()");
        this.isClosed = false;
        reconnect(true);
    }

    @Override // com.pudutech.mqtt.component.client.interf.IMqttClient
    public void reconnect(boolean isFirstConnect) {
        Pdlog.m3273d(TAG, "reconnect() isFirstConnect = " + isFirstConnect);
        if (this.connectState == ConnectState.CONNECTING || this.connectState == ConnectState.CONNECTED) {
            Pdlog.m3277w(TAG, "No need to reconnect(), current connectState is connecting or connected.");
            return;
        }
        if (!this.allowAutoReconnect) {
            Pdlog.m3273d(TAG, "reconnect() failure, Not allow auto reconnect.");
            callbackConnectState(ConnectState.CONNECT_FAILED);
            return;
        }
        CertificateManager certificateManager = this.certificateManager;
        if (certificateManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("certificateManager");
        }
        if (!certificateManager.hasCertificateFile()) {
            Pdlog.m3273d(TAG, "reconnect() failure, Hasn't certificate file, please check assets folder.");
            callbackConnectState(ConnectState.CONNECT_FAILED);
            return;
        }
        if (!isFirstConnect) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                callbackConnectState(ConnectState.CONNECT_FAILED);
            }
        }
        if (this.isClosed || this.isReconnecting) {
            return;
        }
        synchronized (this) {
            if (!this.isClosed && !this.isReconnecting && this.connectState != ConnectState.CONNECTING && this.connectState != ConnectState.CONNECTED) {
                this.isReconnecting = true;
                ExecutorServiceFactory executorServiceFactory = this.executor;
                if (executorServiceFactory != null) {
                    executorServiceFactory.execBossTask(new ReconnectTask());
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.pudutech.mqtt.component.client.interf.IMqttClient
    public void close() {
        Pdlog.m3273d(TAG, "The netty mqtt client is stopping.");
        if (this.isClosed) {
            return;
        }
        try {
            try {
                sendMsg(new MqttMessage(new MqttFixedHeader(MqttMessageType.DISCONNECT, false, MqttQoS.AT_MOST_ONCE, false, 2)));
                closeChannel();
                closeBootstrap();
                this.isClosed = true;
                callbackConnectState(ConnectState.UNCONNECTED);
                Pdlog.m3273d(TAG, "The netty mqtt client is stopped.");
            } catch (Exception e) {
                e.printStackTrace();
                this.isClosed = true;
                callbackConnectState(ConnectState.UNCONNECTED);
                Pdlog.m3273d(TAG, "The netty mqtt client is stopped.");
            }
        } catch (Throwable th) {
            this.isClosed = true;
            callbackConnectState(ConnectState.UNCONNECTED);
            Pdlog.m3273d(TAG, "The netty mqtt client is stopped.");
            throw th;
        }
    }

    @Override // com.pudutech.mqtt.component.client.interf.IMqttClient
    public void release() {
        close();
        this.context = (Context) null;
        ExecutorServiceFactory executorServiceFactory = this.executor;
        if (executorServiceFactory != null) {
            executorServiceFactory.destroy();
        }
        this.executor = (ExecutorServiceFactory) null;
    }

    @Override // com.pudutech.mqtt.component.client.interf.IMqttClient
    public void subscribe(SubscribeMessage[] subscribeMessages, SubscribeStateCallback subscribeStateCallback) {
        Intrinsics.checkParameterIsNotNull(subscribeMessages, "subscribeMessages");
        Pdlog.m3273d(TAG, "subscribe() subscribeMessages = " + subscribeMessages);
        this.subscribeStateCallback = subscribeStateCallback;
        if (subscribeMessages.length == 0) {
            Pdlog.m3277w(TAG, "subscribe() failure, reason: subscribeMessage is null or empty.");
            return;
        }
        if (this.currentSubTopics == null) {
            this.currentSubTopics = new ArrayList<>();
        }
        ArrayList<String> arrayList = this.currentSubTopics;
        if (arrayList != null) {
            arrayList.clear();
        }
        int length = subscribeMessages.length;
        LinkedList linkedList = (LinkedList) null;
        for (int i = 0; i < length; i++) {
            SubscribeMessage subscribeMessage = subscribeMessages[i];
            String topic = subscribeMessage != null ? subscribeMessage.getTopic() : null;
            if (topic == null || topic.length() == 0) {
                Pdlog.m3277w(TAG, "subscribe topic failure, reason: Topic is null or empty.");
            } else {
                String topic2 = subscribeMessage != null ? subscribeMessage.getTopic() : null;
                if (topic2 == null) {
                    Intrinsics.throwNpe();
                }
                MqttTopicSubscription mqttTopicSubscription = new MqttTopicSubscription(topic2, MqttQoS.valueOf(subscribeMessage.getQos().getLevel()));
                LinkedList linkedList2 = linkedList;
                if (linkedList2 == null || linkedList2.isEmpty()) {
                    linkedList = new LinkedList();
                }
                linkedList.add(mqttTopicSubscription);
                ArrayList<String> arrayList2 = this.currentSubTopics;
                if (arrayList2 != null) {
                    arrayList2.add(topic2);
                }
            }
        }
        LinkedList linkedList3 = linkedList;
        if (linkedList3 == null || linkedList3.isEmpty()) {
            Pdlog.m3277w(TAG, "subscribe() failure, reason: subscriptionList is null or empty.");
            return;
        }
        MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.SUBSCRIBE, false, MqttQoS.AT_LEAST_ONCE, false, 0);
        MessageIdGenerate messageIdGenerate = MessageIdGenerate.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(messageIdGenerate, "MessageIdGenerate.getInstance()");
        sendMsg(new MqttSubscribeMessage(mqttFixedHeader, MqttMessageIdVariableHeader.from(messageIdGenerate.getMessageId()), new MqttSubscribePayload(linkedList)));
    }

    @Override // com.pudutech.mqtt.component.client.interf.IMqttClient
    public void subscribe(ArrayList<SubscribeMessage> subscribeMessages, SubscribeStateCallback subscribeStateCallback) {
        ArrayList<SubscribeMessage> arrayList = subscribeMessages;
        if (arrayList == null || arrayList.isEmpty()) {
            Pdlog.m3277w(TAG, "subscribe() failure, reason: subscribeMessage is null or empty.");
            return;
        }
        int size = subscribeMessages.size();
        SubscribeMessage[] subscribeMessageArr = new SubscribeMessage[size];
        for (int i = 0; i < size; i++) {
            subscribeMessageArr[i] = subscribeMessages.get(i);
        }
        subscribe((SubscribeMessage[]) Arrays.copyOf(subscribeMessageArr, subscribeMessageArr.length), subscribeStateCallback);
    }

    @Override // com.pudutech.mqtt.component.client.interf.IMqttClient
    public void unsubscribe(String[] topics, UnsubscribeStateCallback unsubscribeStateCallback) {
        Intrinsics.checkParameterIsNotNull(topics, "topics");
        Pdlog.m3273d(TAG, "unsubscribe() topics = " + topics);
        this.unsubscribeStateCallback = unsubscribeStateCallback;
        if (topics.length == 0) {
            Pdlog.m3277w(TAG, "unsubscribe() failure, reason: topics is null or empty.");
            return;
        }
        if (this.currentUnsubTopics == null) {
            this.currentUnsubTopics = new ArrayList<>();
        }
        ArrayList<String> arrayList = this.currentUnsubTopics;
        if (arrayList != null) {
            arrayList.clear();
            Iterator it = ArrayIteratorKt.iterator(topics);
            while (it.hasNext()) {
                String str = (String) it.next();
                if (str != null) {
                    arrayList.add(str);
                }
            }
        }
        MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.UNSUBSCRIBE, false, MqttQoS.AT_LEAST_ONCE, false, 2);
        MessageIdGenerate messageIdGenerate = MessageIdGenerate.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(messageIdGenerate, "MessageIdGenerate.getInstance()");
        sendMsg(new MqttUnsubscribeMessage(mqttFixedHeader, MqttMessageIdVariableHeader.from(messageIdGenerate.getMessageId()), new MqttUnsubscribePayload(ArraysKt.toList(topics))));
    }

    @Override // com.pudutech.mqtt.component.client.interf.IMqttClient
    public void publishMsg(String topic, String payload) {
        publishMsg(topic, Qos.AT_MOST_ONCE, payload);
    }

    @Override // com.pudutech.mqtt.component.client.interf.IMqttClient
    public void publishMsg(String topic, Qos qos, String payload) {
        Intrinsics.checkParameterIsNotNull(qos, "qos");
        Pdlog.m3273d(TAG, "publishMsg() topic = " + topic + ", qos = " + qos + ", payload = " + payload);
        String str = topic;
        if (str == null || str.length() == 0) {
            Pdlog.m3277w(TAG, "publishMsg() failure, reason: topic is null or empty.");
            return;
        }
        String str2 = payload;
        if (str2 == null || str2.length() == 0) {
            Pdlog.m3277w(TAG, "publishMsg() failure, reason: payload is null or empty.");
            return;
        }
        MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.PUBLISH, false, MqttQoS.valueOf(qos.getLevel()), false, 0);
        MessageIdGenerate messageIdGenerate = MessageIdGenerate.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(messageIdGenerate, "MessageIdGenerate.getInstance()");
        MqttPublishVariableHeader mqttPublishVariableHeader = new MqttPublishVariableHeader(topic, messageIdGenerate.getMessageId());
        ByteBuf buffer = Unpooled.buffer();
        Charset charset = Charsets.UTF_8;
        if (payload == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = payload.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        sendMsg(MqttMessageFactory.newMessage(mqttFixedHeader, mqttPublishVariableHeader, buffer.writeBytes(bytes)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initBootstrap() {
        closeBootstrap();
        Pdlog.m3273d(TAG, "initBootstrap()");
        try {
            this.isClosed = false;
            NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
            this.bootstrap = new Bootstrap();
            Bootstrap bootstrap = this.bootstrap;
            if (bootstrap != null) {
                bootstrap.group(nioEventLoopGroup).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true).option(ChannelOption.TCP_NODELAY, true).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 15000).handler(new LoggingHandler(LogLevel.INFO)).handler(new NettyMqttChannelInitializer(this));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void closeChannel() {
        EventLoop eventLoop;
        Pdlog.m3273d(TAG, "closeChannel()");
        try {
            try {
                Channel channel = this.channel;
                if (channel != null) {
                    channel.close();
                }
                Channel channel2 = this.channel;
                if (channel2 != null && (eventLoop = channel2.eventLoop()) != null) {
                    eventLoop.shutdownGracefully();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            this.channel = (Channel) null;
        }
    }

    private final void closeBootstrap() {
        AbstractBootstrapConfig<Bootstrap, Channel> config;
        EventLoopGroup group;
        Pdlog.m3273d(TAG, "closeBootstrap()");
        Bootstrap bootstrap = null;
        try {
            try {
                Bootstrap bootstrap2 = this.bootstrap;
                if (bootstrap2 != null && (config = bootstrap2.config()) != null && (group = config.group()) != null) {
                    group.shutdownGracefully();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            this.bootstrap = bootstrap;
        }
    }

    public final void callbackConnectState(ConnectState state) {
        byte[] bArr;
        Intrinsics.checkParameterIsNotNull(state, "state");
        Pdlog.m3273d(TAG, "callbackConnectState() state = " + state);
        this.connectState = state;
        int i = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
        if (i != 1 && i != 2 && i == 3) {
            MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.CONNECT, false, MqttQoS.AT_MOST_ONCE, false, 10);
            MqttVersion mqttVersion = MqttVersion.MQTT_3_1_1;
            MqttConnectVariableHeader mqttConnectVariableHeader = new MqttConnectVariableHeader(mqttVersion.protocolName(), mqttVersion.protocolLevel(), getParamsOptions().isHasUserName(), getParamsOptions().isHasPassword(), false, 0, false, false, 60);
            String generateClientId = generateClientId();
            String userName = getParamsOptions().getUserName();
            String str = userName != null ? userName : null;
            String password = getParamsOptions().getPassword();
            if (password == null || password.length() == 0) {
                bArr = null;
            } else {
                String password2 = getParamsOptions().getPassword();
                Intrinsics.checkExpressionValueIsNotNull(password2, "paramsOptions.password");
                Charset charset = Charsets.UTF_8;
                if (password2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
                byte[] bytes = password2.getBytes(charset);
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                bArr = bytes;
            }
            MqttConnectMessage mqttConnectMessage = new MqttConnectMessage(mqttFixedHeader, mqttConnectVariableHeader, new MqttConnectPayload(generateClientId, (String) null, (byte[]) null, str, bArr));
            Pdlog.m3273d(TAG, "mqttConnectMessage = " + mqttConnectMessage);
            Channel channel = this.channel;
            if (channel != null) {
                channel.writeAndFlush(mqttConnectMessage);
            }
        }
    }

    private final String generateClientId() {
        if (this.context == null) {
            return "";
        }
        SystemTool systemTool = SystemTool.INSTANCE;
        Context context = this.context;
        if (context == null) {
            Intrinsics.throwNpe();
        }
        String replace$default = StringsKt.replace$default(systemTool.getMac(context), ":", "", false, 4, (Object) null);
        if (replace$default == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = replace$default.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
        UUID.INSTANCE.generateShortUUID();
        Pdlog.m3273d(TAG, "generateClientId()  clientId = " + lowerCase + "  time " + System.currentTimeMillis());
        return lowerCase;
    }

    private final void sendMsg(MqttMessage mqttMessage) {
        Pdlog.m3273d(TAG, "sendMsg() mqttMessage = " + mqttMessage);
        if (mqttMessage == null) {
            Pdlog.m3277w(TAG, "sendMsg() failure, reason: MqttMessage is null.");
            return;
        }
        Channel channel = this.channel;
        if (channel == null) {
            Pdlog.m3277w(TAG, "sendMsg() failure, reason: channel is null.");
            return;
        }
        if (channel != null) {
            if (!channel.isActive()) {
                Pdlog.m3277w(TAG, "sendMsg() failure, reason: channel is inactive.");
            } else {
                channel.writeAndFlush(mqttMessage);
                Pdlog.m3273d(TAG, "sendMsg successful");
            }
        }
    }

    public final void setAllowAutoReconnect(boolean allowAutoReconnect) {
        this.allowAutoReconnect = allowAutoReconnect;
    }

    public final void addHeartbeatHandler() {
        Channel channel = this.channel;
        if (channel == null || channel == null || !channel.isActive() || channel.pipeline() == null) {
            return;
        }
        try {
            if (channel.pipeline().get(Reflection.getOrCreateKotlinClass(IdleStateHandler.class).getSimpleName()) != null) {
                channel.pipeline().remove(Reflection.getOrCreateKotlinClass(IdleStateHandler.class).getSimpleName());
            }
            channel.pipeline().addFirst(Reflection.getOrCreateKotlinClass(IdleStateHandler.class).getSimpleName(), new IdleStateHandler(135000L, 0L, CConfig.MQTT_HEARTBEAT_INTERVAL_TIME, TimeUnit.MILLISECONDS));
            if (channel.pipeline().get(Reflection.getOrCreateKotlinClass(HeartbeatHandler.class).getSimpleName()) != null) {
                channel.pipeline().remove(Reflection.getOrCreateKotlinClass(HeartbeatHandler.class).getSimpleName());
            }
            channel.pipeline().addAfter(Reflection.getOrCreateKotlinClass(IdleStateHandler.class).getSimpleName(), Reflection.getOrCreateKotlinClass(HeartbeatHandler.class).getSimpleName(), new HeartbeatHandler(this));
            Pdlog.m3273d(TAG, "添加心跳管理handler成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* compiled from: NettyMqttClient.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\u001a\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\u0004H\u0002J\b\u0010\f\u001a\u00020\u0006H\u0016¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/netty/NettyMqttClient$ReconnectTask;", "Ljava/lang/Runnable;", "(Lcom/pudutech/mqtt/component/client/netty/NettyMqttClient;)V", "connectServer", "Lcom/pudutech/mqtt/component/client/config/ConnectState;", "realConnectServer", "", "host", "", "port", "", "reconnect", "run", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class ReconnectTask implements Runnable {
        public ReconnectTask() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ExecutorServiceFactory executor = NettyMqttClient.this.getExecutor();
                if (executor != null) {
                    executor.destroyWorkLoopGroup();
                }
                while (!NettyMqttClient.this.isClosed) {
                    if (NettyMqttClient.this.context != null) {
                        Context context = NettyMqttClient.this.context;
                        if (context == null) {
                            Intrinsics.throwNpe();
                        }
                        if (!ContextExtKt.isNetworkAvailable(context)) {
                            Pdlog.m3273d(NettyMqttClient.TAG, "network is unavailable, delay to continue");
                            Thread.sleep(5000);
                        }
                    }
                    if (reconnect() == ConnectState.CONNECTED) {
                        Pdlog.m3273d(NettyMqttClient.TAG, "The netty mqtt client connected.");
                        NettyMqttClient.this.callbackConnectState(ConnectState.CONNECTED);
                        return;
                    }
                }
                NettyMqttClient.this.callbackConnectState(ConnectState.CONNECT_FAILED);
            } finally {
                NettyMqttClient.this.isReconnecting = false;
            }
        }

        private final ConnectState reconnect() {
            NettyMqttClient.this.initBootstrap();
            return connectServer();
        }

        private final ConnectState connectServer() {
            String host = NettyMqttClient.this.getParamsOptions().getHost();
            if (host == null || host.length() == 0) {
                Pdlog.m3277w(NettyMqttClient.TAG, "connectServer() failure, host is null or empty.");
                return ConnectState.CONNECT_FAILED;
            }
            if (NettyMqttClient.this.getParamsOptions().getPort() != 0) {
                if (!NettyMqttClient.this.isClosed) {
                    if (NettyMqttClient.this.context != null) {
                        Context context = NettyMqttClient.this.context;
                        if (context == null) {
                            Intrinsics.throwNpe();
                        }
                        if (!ContextExtKt.isNetworkAvailable(context)) {
                            Pdlog.m3277w(NettyMqttClient.TAG, "connectServer() failure, Network is unavailable.");
                            return ConnectState.CONNECT_FAILED;
                        }
                    }
                    for (int i = 0; i < 3; i++) {
                        try {
                            realConnectServer(NettyMqttClient.this.getParamsOptions().getHost(), NettyMqttClient.this.getParamsOptions().getPort());
                            if (NettyMqttClient.this.channel != null) {
                                Channel channel = NettyMqttClient.this.channel;
                                Boolean valueOf = channel != null ? Boolean.valueOf(channel.isActive()) : null;
                                if (valueOf == null) {
                                    Intrinsics.throwNpe();
                                }
                                if (valueOf.booleanValue()) {
                                    Pdlog.m3273d(NettyMqttClient.TAG, "connectServer() successfully.");
                                    return ConnectState.CONNECTED;
                                }
                            }
                            Pdlog.m3277w(NettyMqttClient.TAG, "connectServer() failure, wait 5000 milliseconds to reconnect.");
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            NettyMqttClient.this.close();
                        }
                    }
                    return ConnectState.CONNECT_FAILED;
                }
                Pdlog.m3277w(NettyMqttClient.TAG, "connectServer() failure, The netty mqtt client is closed.");
                return ConnectState.CONNECT_FAILED;
            }
            Pdlog.m3277w(NettyMqttClient.TAG, "connectServer() failure, port is zero.");
            return ConnectState.CONNECT_FAILED;
        }

        /* JADX WARN: Type inference failed for: r6v6, types: [io.netty.channel.ChannelFuture] */
        private final void realConnectServer(String host, int port) {
            ChannelFuture connect;
            ?? sync;
            NettyMqttClient nettyMqttClient = NettyMqttClient.this;
            Channel channel = null;
            try {
                Bootstrap bootstrap = nettyMqttClient.bootstrap;
                if (bootstrap != null && (connect = bootstrap.connect(host, port)) != null && (sync = connect.sync()) != 0) {
                    channel = sync.channel();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Pdlog.m3274e(NettyMqttClient.TAG, "realConnectServer() exception, reason = " + e.getMessage());
            }
            nettyMqttClient.channel = channel;
        }
    }
}
