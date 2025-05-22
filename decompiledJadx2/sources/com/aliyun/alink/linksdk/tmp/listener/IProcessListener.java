package com.aliyun.alink.linksdk.tmp.listener;

import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IProcessListener {
    void onFail(ErrorInfo errorInfo);

    void onSuccess(Object obj);
}
