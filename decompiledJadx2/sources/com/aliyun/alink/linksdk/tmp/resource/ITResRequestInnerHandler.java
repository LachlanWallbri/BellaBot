package com.aliyun.alink.linksdk.tmp.resource;

import com.aliyun.alink.linksdk.tmp.listener.IDevListener;
import com.aliyun.alink.linksdk.tmp.listener.ITResResponseCallback;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface ITResRequestInnerHandler extends IDevListener {
    void onProcess(String str, String str2, Object obj, ITResResponseCallback iTResResponseCallback);
}
