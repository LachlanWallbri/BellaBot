package com.pudutech.mqtt.component.client.bean;

import com.pudutech.mqtt.component.client.config.Qos;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* compiled from: SubscribeMessage.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\b\u0010\u0017\u001a\u00020\u0003H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/mqtt/component/client/bean/SubscribeMessage;", "", "topic", "", MqttServiceConstants.QOS, "Lcom/pudutech/mqtt/component/client/config/Qos;", "(Ljava/lang/String;Lcom/pudutech/mqtt/component/client/config/Qos;)V", "getQos", "()Lcom/pudutech/mqtt/component/client/config/Qos;", "setQos", "(Lcom/pudutech/mqtt/component/client/config/Qos;)V", "getTopic", "()Ljava/lang/String;", "setTopic", "(Ljava/lang/String;)V", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "component_mqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class SubscribeMessage {
    private Qos qos;
    private String topic;

    public static /* synthetic */ SubscribeMessage copy$default(SubscribeMessage subscribeMessage, String str, Qos qos, int i, Object obj) {
        if ((i & 1) != 0) {
            str = subscribeMessage.topic;
        }
        if ((i & 2) != 0) {
            qos = subscribeMessage.qos;
        }
        return subscribeMessage.copy(str, qos);
    }

    /* renamed from: component1, reason: from getter */
    public final String getTopic() {
        return this.topic;
    }

    /* renamed from: component2, reason: from getter */
    public final Qos getQos() {
        return this.qos;
    }

    public final SubscribeMessage copy(String topic, Qos qos) {
        Intrinsics.checkParameterIsNotNull(qos, "qos");
        return new SubscribeMessage(topic, qos);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SubscribeMessage)) {
            return false;
        }
        SubscribeMessage subscribeMessage = (SubscribeMessage) other;
        return Intrinsics.areEqual(this.topic, subscribeMessage.topic) && Intrinsics.areEqual(this.qos, subscribeMessage.qos);
    }

    public int hashCode() {
        String str = this.topic;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Qos qos = this.qos;
        return hashCode + (qos != null ? qos.hashCode() : 0);
    }

    public SubscribeMessage(String str, Qos qos) {
        Intrinsics.checkParameterIsNotNull(qos, "qos");
        this.topic = str;
        this.qos = qos;
    }

    public final Qos getQos() {
        return this.qos;
    }

    public final String getTopic() {
        return this.topic;
    }

    public final void setQos(Qos qos) {
        Intrinsics.checkParameterIsNotNull(qos, "<set-?>");
        this.qos = qos;
    }

    public final void setTopic(String str) {
        this.topic = str;
    }

    public String toString() {
        return "SubscribeMessage(topic='" + this.topic + "', qos=" + this.qos + ')';
    }
}
