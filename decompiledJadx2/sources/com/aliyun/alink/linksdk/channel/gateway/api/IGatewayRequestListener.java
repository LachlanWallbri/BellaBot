package com.aliyun.alink.linksdk.channel.gateway.api;

import com.aliyun.alink.linksdk.tools.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IGatewayRequestListener {
    void onFailure(AError aError);

    void onSuccess(String str);
}
