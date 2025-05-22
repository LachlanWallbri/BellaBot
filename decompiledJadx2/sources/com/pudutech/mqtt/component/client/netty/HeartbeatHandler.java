package com.pudutech.mqtt.component.client.netty;

import com.pudutech.base.Pdlog;
import com.pudutech.mqtt.component.client.config.ConnectState;
import com.pudutech.mqtt.component.client.utils.ExecutorServiceFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageFactory;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: HeartbeatHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0018\u00010\bR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/netty/HeartbeatHandler;", "Lio/netty/channel/ChannelInboundHandlerAdapter;", "mqttClient", "Lcom/pudutech/mqtt/component/client/netty/NettyMqttClient;", "(Lcom/pudutech/mqtt/component/client/netty/NettyMqttClient;)V", "heartbeatMessage", "Lio/netty/handler/codec/mqtt/MqttMessage;", "heartbeatTask", "Lcom/pudutech/mqtt/component/client/netty/HeartbeatHandler$HeartbeatTask;", "userEventTriggered", "", "ctx", "Lio/netty/channel/ChannelHandlerContext;", "evt", "", "HeartbeatTask", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class HeartbeatHandler extends ChannelInboundHandlerAdapter {
    private MqttMessage heartbeatMessage;
    private HeartbeatTask heartbeatTask;
    private NettyMqttClient mqttClient;

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[IdleState.values().length];

        static {
            $EnumSwitchMapping$0[IdleState.READER_IDLE.ordinal()] = 1;
            $EnumSwitchMapping$0[IdleState.WRITER_IDLE.ordinal()] = 2;
            $EnumSwitchMapping$0[IdleState.ALL_IDLE.ordinal()] = 3;
        }
    }

    public HeartbeatHandler(NettyMqttClient mqttClient) {
        Intrinsics.checkParameterIsNotNull(mqttClient, "mqttClient");
        this.mqttClient = mqttClient;
        MqttMessage newMessage = MqttMessageFactory.newMessage(new MqttFixedHeader(MqttMessageType.PINGREQ, false, MqttQoS.AT_MOST_ONCE, false, 0), null, null);
        Intrinsics.checkExpressionValueIsNotNull(newMessage, "MqttMessageFactory.newMe… null,\n        null\n    )");
        this.heartbeatMessage = newMessage;
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        IdleState state;
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(evt, "evt");
        super.userEventTriggered(ctx, evt);
        if ((evt instanceof IdleStateEvent) && (state = ((IdleStateEvent) evt).state()) != null) {
            int i = WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
            if (i == 1) {
                Pdlog.m3277w("HeartbeatHandler", "读空闲，触发重连");
                this.mqttClient.callbackConnectState(ConnectState.CONNECT_FAILED);
                this.mqttClient.reconnect(false);
            } else {
                if (i == 2) {
                    Pdlog.m3277w("HeartbeatHandler", "写空闲，忽略");
                    return;
                }
                if (i != 3) {
                    return;
                }
                Pdlog.m3277w("HeartbeatHandler", "读写空闲，发送心跳包");
                if (this.heartbeatTask == null) {
                    Channel channel = ctx.channel();
                    Intrinsics.checkExpressionValueIsNotNull(channel, "ctx.channel()");
                    this.heartbeatTask = new HeartbeatTask(this, channel);
                }
                ExecutorServiceFactory executor = this.mqttClient.getExecutor();
                if (executor != null) {
                    executor.execWorkTask(this.heartbeatTask);
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* compiled from: HeartbeatHandler.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/netty/HeartbeatHandler$HeartbeatTask;", "Ljava/lang/Runnable;", "channel", "Lio/netty/channel/Channel;", "(Lcom/pudutech/mqtt/component/client/netty/HeartbeatHandler;Lio/netty/channel/Channel;)V", "run", "", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    private final class HeartbeatTask implements Runnable {
        private final Channel channel;
        final /* synthetic */ HeartbeatHandler this$0;

        public HeartbeatTask(HeartbeatHandler heartbeatHandler, Channel channel) {
            Intrinsics.checkParameterIsNotNull(channel, "channel");
            this.this$0 = heartbeatHandler;
            this.channel = channel;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.channel.isActive()) {
                Pdlog.m3273d("HeartbeatHandler", "发送心跳包，heartbeatMessage = " + this.this$0.heartbeatMessage);
                this.channel.writeAndFlush(this.this$0.heartbeatMessage);
            }
        }
    }
}
