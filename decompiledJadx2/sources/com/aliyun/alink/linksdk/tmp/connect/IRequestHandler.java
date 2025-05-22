package com.aliyun.alink.linksdk.tmp.connect;

import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IRequestHandler {
    void onError(TmpCommonRequest tmpCommonRequest, ErrorInfo errorInfo);

    void onLoad(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse);
}
