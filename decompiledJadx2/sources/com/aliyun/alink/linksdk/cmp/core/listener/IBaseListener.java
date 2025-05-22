package com.aliyun.alink.linksdk.cmp.core.listener;

import com.aliyun.alink.linksdk.tools.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IBaseListener {
    void onFailure(AError aError);

    void onSuccess();
}
