package com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.p039a;

import com.aliyun.alink.linksdk.alcs.api.ICAConnectListener;
import com.aliyun.alink.linksdk.alcs.api.ICAMsgListener;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAAuthServerParams;
import com.aliyun.alink.linksdk.alcs.data.ica.ICADiscoveryDeviceInfo;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ICAProvisioner.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.a.d */
/* loaded from: classes.dex */
public interface InterfaceC0948d {

    /* renamed from: a */
    public static final String f817a = "ServerAuthInfo";

    /* renamed from: b */
    public static final String f818b = "core.service.setup";

    /* renamed from: c */
    public static final String f819c = "1.0";

    /* renamed from: a */
    void mo378a();

    /* renamed from: a */
    void mo379a(ICADiscoveryDeviceInfo iCADiscoveryDeviceInfo, ICAConnectListener iCAConnectListener);

    /* renamed from: a */
    boolean mo380a(ICAAuthServerParams iCAAuthServerParams, ICAMsgListener iCAMsgListener);
}
