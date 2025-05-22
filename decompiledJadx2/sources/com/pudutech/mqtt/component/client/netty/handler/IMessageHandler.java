package com.pudutech.mqtt.component.client.netty.handler;

import com.pudutech.mqtt.component.client.netty.NettyMqttClient;
import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.MqttMessage;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: IMessageHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/netty/handler/IMessageHandler;", "", "execute", "", "channel", "Lio/netty/channel/Channel;", "message", "Lio/netty/handler/codec/mqtt/MqttMessage;", "mqttClient", "Lcom/pudutech/mqtt/component/client/netty/NettyMqttClient;", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface IMessageHandler {
    void execute(Channel channel, MqttMessage message, NettyMqttClient mqttClient);
}
