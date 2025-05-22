package com.aliyun.alink.linksdk.alcs.lpbs.component.auth;

import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IAuthProvider {
    public static final String AUTH_PATH_PRODUCT_IDTOKEY = "productIdToProductKey";

    void queryAuthInfo(PalDeviceInfo palDeviceInfo, String str, Object obj, IAuthProviderListener iAuthProviderListener);
}
