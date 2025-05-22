package com.pudutech.pdmqtt.client;

import android.content.Context;
import com.pudutech.pdmqtt.ActionCallback;
import com.pudutech.pdmqtt.GetClientCallback;
import com.pudutech.pdmqtt.MqttManager;
import com.pudutech.pdmqtt.OnMessageChangeListener;
import com.pudutech.pdmqtt.OnPublishCallback;
import com.pudutech.pdmqtt.config.BaseMqttConfig;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* compiled from: interfaces.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000  2\u00020\u0001:\u0001 J \u0010\u0002\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\u0012\u0010\f\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\"\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\r\u001a\u0004\u0018\u00010\u0015H&J*\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\r\u001a\u0004\u0018\u00010\u0015H&J\b\u0010\u0018\u001a\u00020\u0003H&J \u0010\u0019\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00030\u0005j\u0002`\u0007H&J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\tH&J\u001e\u0010\u001b\u001a\u00020\u00032\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u001d2\u0006\u0010\r\u001a\u00020\u001eH&J\u0016\u0010\u001f\u001a\u00020\u00032\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00130\u001dH&¨\u0006!"}, m3961d2 = {"Lcom/pudutech/pdmqtt/client/IMqttClient;", "", "addOnConnectStateChangeLis", "", "lis", "Lkotlin/Function1;", "Lcom/pudutech/pdmqtt/client/MqttConnectState;", "Lcom/pudutech/pdmqtt/client/OnConnectStateChangeListener;", "addOnMessageChangeListener", "Lcom/pudutech/pdmqtt/OnMessageChangeListener;", "clientConfig", "Lcom/pudutech/pdmqtt/config/BaseMqttConfig;", MqttServiceConstants.CONNECT_ACTION, "callback", "Lcom/pudutech/pdmqtt/GetClientCallback;", "isConnect", "", "publish", "topic", "", MqttServiceConstants.PAYLOAD, "Lcom/pudutech/pdmqtt/OnPublishCallback;", MqttServiceConstants.QOS, "", "release", "removeOnConnectStateChangeLis", "removeOnMessageChangeListener", MqttServiceConstants.SUBSCRIBE_ACTION, "topicSet", "", "Lcom/pudutech/pdmqtt/ActionCallback;", MqttServiceConstants.UNSUBSCRIBE_ACTION, "Companion", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IMqttClient {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    void addOnConnectStateChangeLis(Function1<? super MqttConnectState, Unit> lis);

    void addOnMessageChangeListener(OnMessageChangeListener lis);

    /* renamed from: clientConfig */
    BaseMqttConfig getConfig();

    void connect(GetClientCallback callback);

    boolean isConnect();

    void publish(String topic, String payload, int qos, OnPublishCallback callback);

    void publish(String topic, String payload, OnPublishCallback callback);

    void release();

    void removeOnConnectStateChangeLis(Function1<? super MqttConnectState, Unit> lis);

    void removeOnMessageChangeListener(OnMessageChangeListener lis);

    void subscribe(Set<String> topicSet, ActionCallback callback);

    void unsubscribe(Set<String> topicSet);

    /* compiled from: interfaces.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0007\u001a\u00020\b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/pdmqtt/client/IMqttClient$Companion;", "", "()V", "clientProxy", "Lcom/pudutech/pdmqtt/client/IMqttClient;", "manager", "Lcom/pudutech/pdmqtt/MqttManager;", "config", "Lcom/pudutech/pdmqtt/config/BaseMqttConfig;", "remoteClientWrap", "context", "Landroid/content/Context;", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final IMqttClient clientProxy(MqttManager manager, BaseMqttConfig config) {
            Intrinsics.checkParameterIsNotNull(manager, "manager");
            Intrinsics.checkParameterIsNotNull(config, "config");
            return new LocalMqttClientImpl(manager, config);
        }

        public final IMqttClient remoteClientWrap(Context context, BaseMqttConfig config) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(config, "config");
            return new RemoteMqttClientImpl(context, config);
        }
    }
}
