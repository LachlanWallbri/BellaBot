package com.pudutech.mqtt.component.client.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: NettyMqttChannelInitializer.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0002H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/netty/NettyMqttChannelInitializer;", "Lio/netty/channel/ChannelInitializer;", "Lio/netty/channel/Channel;", "mqttClient", "Lcom/pudutech/mqtt/component/client/netty/NettyMqttClient;", "(Lcom/pudutech/mqtt/component/client/netty/NettyMqttClient;)V", "initChannel", "", "ch", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class NettyMqttChannelInitializer extends ChannelInitializer<Channel> {
    private final NettyMqttClient mqttClient;

    public NettyMqttChannelInitializer(NettyMqttClient mqttClient) {
        Intrinsics.checkParameterIsNotNull(mqttClient, "mqttClient");
        this.mqttClient = mqttClient;
    }

    @Override // io.netty.channel.ChannelInitializer
    protected void initChannel(Channel ch) {
        Intrinsics.checkParameterIsNotNull(ch, "ch");
        ch.pipeline().addLast("encoder", MqttEncoder.INSTANCE).addLast("decoder", new MqttDecoder()).addLast(Reflection.getOrCreateKotlinClass(NettyMqttReadHandler.class).getSimpleName(), new NettyMqttReadHandler(this.mqttClient));
    }
}
