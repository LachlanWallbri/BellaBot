package com.aliyun.alink.linksdk.tmp.event;

import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonResponse;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface INotifyHandler {
    void onMessage(TmpCommonRequest tmpCommonRequest, TmpCommonResponse tmpCommonResponse);
}
