package com.aliyun.alink.linksdk.cmp.core.listener;

import com.aliyun.alink.linksdk.cmp.core.base.AResource;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IConnectResourceRegister {
    void publishResource(AResource aResource, IBaseListener iBaseListener);

    void registerResource(AResource aResource, IResourceRequestListener iResourceRequestListener);

    void unregisterResource(AResource aResource, IBaseListener iBaseListener);
}
