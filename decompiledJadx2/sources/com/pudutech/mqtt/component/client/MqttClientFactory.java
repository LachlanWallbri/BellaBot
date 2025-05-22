package com.pudutech.mqtt.component.client;

import com.pudutech.mqtt.component.client.interf.IMqttClient;
import com.pudutech.mqtt.component.client.netty.NettyMqttClient;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: MqttClientFactory.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/MqttClientFactory;", "", "()V", "getMqttClient", "Lcom/pudutech/mqtt/component/client/interf/IMqttClient;", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MqttClientFactory {
    public static final MqttClientFactory INSTANCE = new MqttClientFactory();

    private MqttClientFactory() {
    }

    public final IMqttClient getMqttClient() {
        return NettyMqttClient.INSTANCE.getINSTANCE();
    }
}
