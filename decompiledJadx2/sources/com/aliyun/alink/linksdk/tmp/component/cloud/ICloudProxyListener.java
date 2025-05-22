package com.aliyun.alink.linksdk.tmp.component.cloud;

import com.aliyun.alink.linksdk.tools.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface ICloudProxyListener {
    void onFailure(String str, AError aError);

    void onResponse(String str, Object obj);
}
