package com.aliyun.alink.linksdk.tmp.listener;

import com.aliyun.alink.linksdk.tools.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IPublishResourceListener {
    void onError(String str, AError aError);

    void onSuccess(String str, Object obj);
}
