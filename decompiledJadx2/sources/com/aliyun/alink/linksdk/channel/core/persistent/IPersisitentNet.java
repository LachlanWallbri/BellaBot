package com.aliyun.alink.linksdk.channel.core.persistent;

import android.content.Context;
import com.aliyun.alink.linksdk.channel.core.base.INet;
import com.aliyun.alink.linksdk.channel.core.persistent.mqtt.request.PersisitentNetParams;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IPersisitentNet extends INet {
    void destroy();

    void destroy(long j, Object obj, IMqttActionListener iMqttActionListener);

    PersistentConnectState getConnectState();

    void init(Context context, PersistentInitParams persistentInitParams);

    void openLog(boolean z);

    void subscribe(String str, IOnSubscribeListener iOnSubscribeListener);

    void subscribe(String str, PersisitentNetParams persisitentNetParams, IOnSubscribeListener iOnSubscribeListener);

    void subscribeRrpc(String str, IOnSubscribeRrpcListener iOnSubscribeRrpcListener);

    void unSubscribe(String str, IOnSubscribeListener iOnSubscribeListener);
}
