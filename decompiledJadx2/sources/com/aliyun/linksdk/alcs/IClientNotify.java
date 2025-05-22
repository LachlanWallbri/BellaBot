package com.aliyun.linksdk.alcs;

import com.aliyun.alink.linksdk.alcs.lpbs.data.PalRspMessage;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IClientNotify {
    void onNotify(String str, PalRspMessage palRspMessage);

    void onServerStateChange(boolean z);
}
