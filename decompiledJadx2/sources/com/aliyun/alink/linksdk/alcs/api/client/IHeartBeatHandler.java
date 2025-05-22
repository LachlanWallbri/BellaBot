package com.aliyun.alink.linksdk.alcs.api.client;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IHeartBeatHandler {
    void onBeat(String str, int i);

    void onTimeout(String str, int i);
}
