package com.aliyun.alink.dm.api;

import com.aliyun.alink.linksdk.tools.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IDMCallback<T> {
    void onFailure(AError aError);

    void onSuccess(T t);
}
