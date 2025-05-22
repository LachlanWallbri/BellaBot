package com.aliyun.alink.linksdk.channel.core.persistent.event;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IConnectionStateListener {
    void onConnectFail(String str);

    void onConnected();

    void onDisconnect();
}
