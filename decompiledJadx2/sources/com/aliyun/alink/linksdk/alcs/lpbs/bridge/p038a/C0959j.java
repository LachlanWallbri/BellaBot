package com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a;

import com.aliyun.alink.linksdk.alcs.api.ICAConnectListener;
import com.aliyun.alink.linksdk.alcs.api.ICAMsgListener;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAAuthPairs;
import com.aliyun.alink.linksdk.alcs.data.ica.ICAAuthParams;
import com.aliyun.alink.linksdk.alcs.data.ica.ICADeviceInfo;
import com.aliyun.alink.linksdk.alcs.data.ica.ICADiscoveryDeviceInfo;
import com.aliyun.alink.linksdk.alcs.data.ica.ICARspMessage;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.p039a.C0946b;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.p039a.C0949e;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.p039a.InterfaceC0948d;
import com.aliyun.alink.linksdk.alcs.lpbs.bridge.p038a.p039a.InterfaceC0950f;
import com.aliyun.alink.linksdk.alcs.lpbs.component.auth.IAuthProvider;
import com.aliyun.alink.linksdk.alcs.lpbs.component.auth.IAuthProviderListener;
import com.aliyun.alink.linksdk.alcs.lpbs.data.PalDeviceInfo;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: ICALocalAuthProvider.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.j */
/* loaded from: classes.dex */
public class C0959j implements IAuthProvider {

    /* renamed from: c */
    private static final String f853c = "[AlcsLPBS]ICALocalAuthProvider";

    /* renamed from: a */
    protected InterfaceC0948d f854a = new C0949e();

    /* renamed from: b */
    protected InterfaceC0950f f855b;

    public C0959j(InterfaceC0950f interfaceC0950f) {
        this.f855b = interfaceC0950f;
    }

    @Override // com.aliyun.alink.linksdk.alcs.lpbs.component.auth.IAuthProvider
    public void queryAuthInfo(final PalDeviceInfo palDeviceInfo, String str, Object obj, final IAuthProviderListener iAuthProviderListener) {
        InterfaceC0950f interfaceC0950f;
        if (iAuthProviderListener == null) {
            ALog.m480e(f853c, "queryAuthInfo listener null");
            return;
        }
        if (palDeviceInfo == null || obj == null || (interfaceC0950f = this.f855b) == null) {
            ALog.m480e(f853c, "queryAuthInfo palDeviceInfo or params or mStoragenull");
            iAuthProviderListener.onComplete(palDeviceInfo, null);
            return;
        }
        ICAAuthParams mo372a = interfaceC0950f.mo372a(palDeviceInfo.getDevId());
        ALog.m479d(f853c, "mStorage getAccessInfo icaAuthParams:" + mo372a);
        if (mo372a != null) {
            iAuthProviderListener.onComplete(palDeviceInfo, mo372a);
        } else {
            this.f854a.mo379a((ICADiscoveryDeviceInfo) obj, new ICAConnectListener() { // from class: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.j.1
                @Override // com.aliyun.alink.linksdk.alcs.api.ICAConnectListener
                public void onLoad(int i, String str2, ICADeviceInfo iCADeviceInfo) {
                    if (i != 200) {
                        iAuthProviderListener.onComplete(palDeviceInfo, null);
                    } else {
                        final ICAAuthPairs m374a = C0946b.m374a("0");
                        C0959j.this.f854a.mo380a(m374a.authServerParams, new ICAMsgListener() { // from class: com.aliyun.alink.linksdk.alcs.lpbs.bridge.a.j.1.1
                            @Override // com.aliyun.alink.linksdk.alcs.api.ICAMsgListener
                            public void onLoad(ICARspMessage iCARspMessage) {
                                if (iCARspMessage == null || iCARspMessage.code != 0) {
                                    C0959j.this.f854a.mo378a();
                                    iAuthProviderListener.onComplete(palDeviceInfo, null);
                                } else {
                                    C0959j.this.f855b.mo373a(palDeviceInfo.getDevId(), m374a.authParams.accessKey, m374a.authParams.accessToken);
                                    C0959j.this.f854a.mo378a();
                                    iAuthProviderListener.onComplete(palDeviceInfo, m374a.authParams);
                                }
                            }
                        });
                    }
                }
            });
        }
    }
}
