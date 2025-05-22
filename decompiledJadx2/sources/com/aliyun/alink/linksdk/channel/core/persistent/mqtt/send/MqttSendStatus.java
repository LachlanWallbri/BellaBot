package com.aliyun.alink.linksdk.channel.core.persistent.mqtt.send;

import com.aliyun.alink.linksdk.channel.core.base.ISendStatus;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public enum MqttSendStatus implements ISendStatus {
    waitingToSend,
    waitingToSubReply,
    subReplyed,
    waitingToPublish,
    published,
    waitingToComplete,
    completed
}
