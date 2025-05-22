package com.aliyun.alink.linksdk.channel.core.persistent.event;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IOnPushListener {
    void onCommand(String str, byte[] bArr);

    boolean shouldHandle(String str);
}
