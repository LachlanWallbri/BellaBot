package com.pudutech.mqtt.component.client.netty;

import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.mqtt.component.client.config.ConnectState;
import com.pudutech.mqtt.component.client.netty.handler.IMessageHandler;
import com.pudutech.mqtt.component.client.netty.handler.MessageHandlerFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: NettyMqttReadHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/netty/NettyMqttReadHandler;", "Lio/netty/channel/ChannelInboundHandlerAdapter;", "mqttClient", "Lcom/pudutech/mqtt/component/client/netty/NettyMqttClient;", "(Lcom/pudutech/mqtt/component/client/netty/NettyMqttClient;)V", "channelActive", "", "ctx", "Lio/netty/channel/ChannelHandlerContext;", "channelInactive", "channelRead", NotificationCompat.CATEGORY_MESSAGE, "", "channelReadComplete", "exceptionCaught", "cause", "", "Companion", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class NettyMqttReadHandler extends ChannelInboundHandlerAdapter {
    private static final String TAG = "NettyMqttReadHandler";
    private final NettyMqttClient mqttClient;

    public NettyMqttReadHandler(NettyMqttClient mqttClient) {
        Intrinsics.checkParameterIsNotNull(mqttClient, "mqttClient");
        this.mqttClient = mqttClient;
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelActive(ChannelHandlerContext ctx) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Pdlog.m3273d(TAG, "channelActive() ctx = " + ctx);
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext ctx) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Pdlog.m3273d(TAG, "channelInactive() ctx = " + ctx);
        Channel channel = ctx.channel();
        if (channel != null) {
            channel.close();
        }
        ctx.close();
        this.mqttClient.callbackConnectState(ConnectState.CONNECT_FAILED);
        this.mqttClient.reconnect(false);
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        Pdlog.m3273d(TAG, "channelRead() ctx = " + ctx + ", msg = " + msg);
        if (!(msg instanceof MqttMessage)) {
            Pdlog.m3277w(TAG, "channelRead() msg !instanceof MqttMessage, passed.");
            return;
        }
        try {
            MqttFixedHeader fixedHeader = ((MqttMessage) msg).fixedHeader();
            if (fixedHeader == null) {
                Pdlog.m3277w(TAG, "channedRead() fixedHeader is null, passed.");
                return;
            }
            MqttMessageType messageType = fixedHeader.messageType();
            MessageHandlerFactory messageHandlerFactory = MessageHandlerFactory.INSTANCE;
            Intrinsics.checkExpressionValueIsNotNull(messageType, "messageType");
            IMessageHandler messageHandler = messageHandlerFactory.getMessageHandler(messageType);
            if (messageHandler == null) {
                Pdlog.m3277w(TAG, "channelRead() messageHandler is null, passed.");
                return;
            }
            Pdlog.m3273d(TAG, "Found messageHandler, class = " + messageHandler.getClass().getSimpleName());
            Channel channel = ctx.channel();
            Intrinsics.checkExpressionValueIsNotNull(channel, "ctx.channel()");
            messageHandler.execute(channel, (MqttMessage) msg, this.mqttClient);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelReadComplete(ChannelHandlerContext ctx) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Pdlog.m3273d(TAG, "channelReadComplete() ctx = " + ctx);
        ctx.flush();
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler, io.netty.channel.ChannelInboundHandler
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Intrinsics.checkParameterIsNotNull(ctx, "ctx");
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        Pdlog.m3273d(TAG, "exceptionCaught() ctx = " + ctx + ", cause = " + cause);
        Channel channel = ctx.channel();
        if (channel != null) {
            channel.close();
        }
        ctx.close();
        this.mqttClient.callbackConnectState(ConnectState.CONNECT_FAILED);
        this.mqttClient.reconnect(false);
    }
}
