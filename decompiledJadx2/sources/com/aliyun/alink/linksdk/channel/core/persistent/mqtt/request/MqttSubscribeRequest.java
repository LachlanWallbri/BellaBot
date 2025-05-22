package com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request;

import com.aliyun.alink.linksdk.channel.core.persistent.PersistentRequest;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class MqttSubscribeRequest extends PersistentRequest {
    public boolean isSubscribe;
    public MqttSubscribeRequestParams subscribeRequestParams;
    public String topic;
}
