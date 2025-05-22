package com.pudutech.mqtt.component.client.netty.handler;

import androidx.exifinterface.media.ExifInterface;
import com.pudutech.mqtt.component.client.netty.NettyMqttClient;
import io.netty.channel.Channel;
import io.netty.handler.codec.mqtt.MqttMessage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: AbstractMessageHandler.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J%\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u0011H&¢\u0006\u0002\u0010\u0012J \u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/netty/handler/AbstractMessageHandler;", ExifInterface.GPS_DIRECTION_TRUE, "Lio/netty/handler/codec/mqtt/MqttMessage;", "Lcom/pudutech/mqtt/component/client/netty/handler/IMessageHandler;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "setTAG", "(Ljava/lang/String;)V", "action", "", "channel", "Lio/netty/channel/Channel;", "message", "mqttClient", "Lcom/pudutech/mqtt/component/client/netty/NettyMqttClient;", "(Lio/netty/channel/Channel;Lio/netty/handler/codec/mqtt/MqttMessage;Lcom/pudutech/mqtt/component/client/netty/NettyMqttClient;)V", "execute", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public abstract class AbstractMessageHandler<T extends MqttMessage> implements IMessageHandler {
    private String TAG = getClass().getSimpleName();

    public abstract void action(Channel channel, T message, NettyMqttClient mqttClient);

    public final String getTAG() {
        return this.TAG;
    }

    public final void setTAG(String str) {
        this.TAG = str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.pudutech.mqtt.component.client.netty.handler.IMessageHandler
    public void execute(Channel channel, MqttMessage message, NettyMqttClient mqttClient) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(mqttClient, "mqttClient");
        action(channel, message, mqttClient);
    }
}
