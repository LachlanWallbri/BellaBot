package com.aliyun.alink.linksdk.alcs.api.client;

import com.aliyun.alink.linksdk.alcs.api.utils.ErrorInfo;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IDeviceHandler {
    void onFail(Object obj, ErrorInfo errorInfo);

    void onSuccess(Object obj);
}
