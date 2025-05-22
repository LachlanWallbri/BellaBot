package com.aliyun.alink.linksdk.alcs.api.client;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IDeviceStateListener {
    public static final int STATE_CONNECTED = 1;
    public static final int STATE_DISCONNECTED = 0;

    void onDeviceStateChange(int i);
}
