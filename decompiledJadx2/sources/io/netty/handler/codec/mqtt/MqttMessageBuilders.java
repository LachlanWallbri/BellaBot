package io.netty.handler.codec.mqtt;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class MqttMessageBuilders {

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class PublishBuilder {
        private int messageId;
        private ByteBuf payload;
        private MqttQoS qos;
        private boolean retained;
        private String topic;

        PublishBuilder() {
        }

        public PublishBuilder topicName(String str) {
            this.topic = str;
            return this;
        }

        public PublishBuilder retained(boolean z) {
            this.retained = z;
            return this;
        }

        public PublishBuilder qos(MqttQoS mqttQoS) {
            this.qos = mqttQoS;
            return this;
        }

        public PublishBuilder payload(ByteBuf byteBuf) {
            this.payload = byteBuf;
            return this;
        }

        public PublishBuilder messageId(int i) {
            this.messageId = i;
            return this;
        }

        public MqttPublishMessage build() {
            return new MqttPublishMessage(new MqttFixedHeader(MqttMessageType.PUBLISH, false, this.qos, this.retained, 0), new MqttPublishVariableHeader(this.topic, this.messageId), Unpooled.buffer().writeBytes(this.payload));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class ConnectBuilder {
        private boolean cleanSession;
        private String clientId;
        private boolean hasPassword;
        private boolean hasUser;
        private int keepAliveSecs;
        private byte[] password;
        private String username;
        private boolean willFlag;
        private byte[] willMessage;
        private boolean willRetain;
        private String willTopic;
        private MqttVersion version = MqttVersion.MQTT_3_1_1;
        private MqttQoS willQos = MqttQoS.AT_MOST_ONCE;

        ConnectBuilder() {
        }

        public ConnectBuilder protocolVersion(MqttVersion mqttVersion) {
            this.version = mqttVersion;
            return this;
        }

        public ConnectBuilder clientId(String str) {
            this.clientId = str;
            return this;
        }

        public ConnectBuilder cleanSession(boolean z) {
            this.cleanSession = z;
            return this;
        }

        public ConnectBuilder keepAlive(int i) {
            this.keepAliveSecs = i;
            return this;
        }

        public ConnectBuilder willFlag(boolean z) {
            this.willFlag = z;
            return this;
        }

        public ConnectBuilder willQoS(MqttQoS mqttQoS) {
            this.willQos = mqttQoS;
            return this;
        }

        public ConnectBuilder willTopic(String str) {
            this.willTopic = str;
            return this;
        }

        @Deprecated
        public ConnectBuilder willMessage(String str) {
            willMessage(str == null ? null : str.getBytes(CharsetUtil.UTF_8));
            return this;
        }

        public ConnectBuilder willMessage(byte[] bArr) {
            this.willMessage = bArr;
            return this;
        }

        public ConnectBuilder willRetain(boolean z) {
            this.willRetain = z;
            return this;
        }

        public ConnectBuilder hasUser(boolean z) {
            this.hasUser = z;
            return this;
        }

        public ConnectBuilder hasPassword(boolean z) {
            this.hasPassword = z;
            return this;
        }

        public ConnectBuilder username(String str) {
            this.hasUser = str != null;
            this.username = str;
            return this;
        }

        @Deprecated
        public ConnectBuilder password(String str) {
            password(str == null ? null : str.getBytes(CharsetUtil.UTF_8));
            return this;
        }

        public ConnectBuilder password(byte[] bArr) {
            this.hasPassword = bArr != null;
            this.password = bArr;
            return this;
        }

        public MqttConnectMessage build() {
            return new MqttConnectMessage(new MqttFixedHeader(MqttMessageType.CONNECT, false, MqttQoS.AT_MOST_ONCE, false, 0), new MqttConnectVariableHeader(this.version.protocolName(), this.version.protocolLevel(), this.hasUser, this.hasPassword, this.willRetain, this.willQos.value(), this.willFlag, this.cleanSession, this.keepAliveSecs), new MqttConnectPayload(this.clientId, this.willTopic, this.willMessage, this.username, this.password));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class SubscribeBuilder {
        private int messageId;
        private List<MqttTopicSubscription> subscriptions;

        SubscribeBuilder() {
        }

        public SubscribeBuilder addSubscription(MqttQoS mqttQoS, String str) {
            if (this.subscriptions == null) {
                this.subscriptions = new ArrayList(5);
            }
            this.subscriptions.add(new MqttTopicSubscription(str, mqttQoS));
            return this;
        }

        public SubscribeBuilder messageId(int i) {
            this.messageId = i;
            return this;
        }

        public MqttSubscribeMessage build() {
            return new MqttSubscribeMessage(new MqttFixedHeader(MqttMessageType.SUBSCRIBE, false, MqttQoS.AT_LEAST_ONCE, false, 0), MqttMessageIdVariableHeader.from(this.messageId), new MqttSubscribePayload(this.subscriptions));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class UnsubscribeBuilder {
        private int messageId;
        private List<String> topicFilters;

        UnsubscribeBuilder() {
        }

        public UnsubscribeBuilder addTopicFilter(String str) {
            if (this.topicFilters == null) {
                this.topicFilters = new ArrayList(5);
            }
            this.topicFilters.add(str);
            return this;
        }

        public UnsubscribeBuilder messageId(int i) {
            this.messageId = i;
            return this;
        }

        public MqttUnsubscribeMessage build() {
            return new MqttUnsubscribeMessage(new MqttFixedHeader(MqttMessageType.UNSUBSCRIBE, false, MqttQoS.AT_LEAST_ONCE, false, 0), MqttMessageIdVariableHeader.from(this.messageId), new MqttUnsubscribePayload(this.topicFilters));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public static final class ConnAckBuilder {
        private MqttConnectReturnCode returnCode;
        private boolean sessionPresent;

        ConnAckBuilder() {
        }

        public ConnAckBuilder returnCode(MqttConnectReturnCode mqttConnectReturnCode) {
            this.returnCode = mqttConnectReturnCode;
            return this;
        }

        public ConnAckBuilder sessionPresent(boolean z) {
            this.sessionPresent = z;
            return this;
        }

        public MqttConnAckMessage build() {
            return new MqttConnAckMessage(new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0), new MqttConnAckVariableHeader(this.returnCode, this.sessionPresent));
        }
    }

    public static ConnectBuilder connect() {
        return new ConnectBuilder();
    }

    public static ConnAckBuilder connAck() {
        return new ConnAckBuilder();
    }

    public static PublishBuilder publish() {
        return new PublishBuilder();
    }

    public static SubscribeBuilder subscribe() {
        return new SubscribeBuilder();
    }

    public static UnsubscribeBuilder unsubscribe() {
        return new UnsubscribeBuilder();
    }

    private MqttMessageBuilders() {
    }
}
