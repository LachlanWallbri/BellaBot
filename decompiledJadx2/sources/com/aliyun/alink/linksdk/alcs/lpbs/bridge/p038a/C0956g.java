package com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a;

import com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalAuthRegister;
import com.aliyun.alink.linksdk.alcs.lpbs.component.auth.IAuthProvider;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ICABridgeAuthRegister.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.g */
/* loaded from: classes.dex */
public class C0956g implements IPalAuthRegister {

    /* renamed from: a */
    private IAuthProvider f847a;

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalAuthRegister
    public boolean setAuthProvider(IAuthProvider iAuthProvider) {
        this.f847a = iAuthProvider;
        return true;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.bridge.IPalAuthRegister
    public IAuthProvider getProvider() {
        return this.f847a;
    }
}
