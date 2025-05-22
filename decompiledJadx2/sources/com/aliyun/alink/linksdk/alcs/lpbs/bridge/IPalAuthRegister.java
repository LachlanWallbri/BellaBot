package com.aliyun.alink.linksdk.alcs.lpbs.bridge;

import com.aliyun.alink.linksdk.alcs.lpbs.component.auth.IAuthProvider;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface IPalAuthRegister {
    IAuthProvider getProvider();

    boolean setAuthProvider(IAuthProvider iAuthProvider);
}
