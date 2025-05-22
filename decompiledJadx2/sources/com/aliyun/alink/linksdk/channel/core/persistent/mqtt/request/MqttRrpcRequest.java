package com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request;

import com.aliyun.alink.linksdk.channel.core.persistent.PersistentRequest;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class MqttRrpcRequest extends PersistentRequest {
    public String replyTopic;
    public String topic;

    public void setTopic(String str) {
        this.topic = str;
        this.replyTopic = str + TmpConstant.URI_TOPIC_REPLY_POST;
    }
}
