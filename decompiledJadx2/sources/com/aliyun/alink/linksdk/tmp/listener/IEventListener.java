package com.aliyun.alink.linksdk.tmp.listener;

import com.aliyun.alink.linksdk.tmp.api.OutputParams;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IEventListener extends IDevListener {
    void onMessage(String str, String str2, OutputParams outputParams);
}
