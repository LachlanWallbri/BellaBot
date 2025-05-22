package com.pudutech.mqtt.component.client.netty.handler;

import io.netty.handler.codec.mqtt.MqttMessageType;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: MessageHandlerFactory.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\u0005R*\u0010\u0003\u001a\u001e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004j\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/netty/handler/MessageHandlerFactory;", "", "()V", "HANDLERS", "Ljava/util/HashMap;", "Lio/netty/handler/codec/mqtt/MqttMessageType;", "Lcom/pudutech/mqtt/component/client/netty/handler/IMessageHandler;", "Lkotlin/collections/HashMap;", "getMessageHandler", "messageType", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MessageHandlerFactory {
    public static final MessageHandlerFactory INSTANCE = new MessageHandlerFactory();
    private static final HashMap<MqttMessageType, IMessageHandler> HANDLERS = new HashMap<>();

    static {
        HANDLERS.put(MqttMessageType.CONNACK, new ConnAckMessageHandler());
        HANDLERS.put(MqttMessageType.PINGRESP, new HeartbeatMessageHandler());
        HANDLERS.put(MqttMessageType.SUBACK, new SubAckMessageHandler());
        HANDLERS.put(MqttMessageType.UNSUBACK, new UnsubAckMessageHandler());
        HANDLERS.put(MqttMessageType.PUBACK, new PubAckMessageHandler());
        HANDLERS.put(MqttMessageType.PUBLISH, new PublishMessageHandler());
    }

    private MessageHandlerFactory() {
    }

    public final IMessageHandler getMessageHandler(MqttMessageType messageType) {
        Intrinsics.checkParameterIsNotNull(messageType, "messageType");
        return HANDLERS.get(messageType);
    }
}
