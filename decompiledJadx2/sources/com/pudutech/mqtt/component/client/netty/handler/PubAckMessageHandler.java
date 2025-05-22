package com.pudutech.mqtt.component.client.netty.handler;

import com.pudutech.base.Pdlog;
import com.pudutech.mqtt.component.client.netty.NettyMqttClient;
import io.netty.channel.Channel;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.mqtt.MqttPubAckMessage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: PubAckMessageHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/netty/handler/PubAckMessageHandler;", "Lcom/pudutech/mqtt/component/client/netty/handler/AbstractMessageHandler;", "Lio/netty/handler/codec/mqtt/MqttPubAckMessage;", "()V", "action", "", "channel", "Lio/netty/channel/Channel;", "message", "mqttClient", "Lcom/pudutech/mqtt/component/client/netty/NettyMqttClient;", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class PubAckMessageHandler extends AbstractMessageHandler<MqttPubAckMessage> {
    @Override // com.pudutech.mqtt.component.client.netty.handler.AbstractMessageHandler
    public void action(Channel channel, MqttPubAckMessage message, NettyMqttClient mqttClient) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(mqttClient, "mqttClient");
        Pdlog.m3273d(getTAG(), "收到消息发送响应：channel = " + channel + ", message = " + message);
        DecoderResult decoderResult = message.decoderResult();
        Intrinsics.checkExpressionValueIsNotNull(decoderResult, "message.decoderResult()");
        if (decoderResult.isFailure()) {
            Pdlog.m3277w(getTAG(), "消息发送响应解析失败");
        }
    }
}
