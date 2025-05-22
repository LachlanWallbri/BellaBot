package com.aliyun.alink.linksdk.tmp.listener;

import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IDevRawDataListener {
    void onFail(Object obj, ErrorInfo errorInfo);

    void onSuccess(Object obj, Object obj2);
}
