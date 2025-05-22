package com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request;

import com.aliyun.alink.linksdk.channel.core.persistent.PersistentRequest;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class MqttPublishRequest extends PersistentRequest {
    public boolean isRPC = false;
    public String msgId = "";
    public int qos = 0;
    public String replyTopic;
    public String topic;
}
