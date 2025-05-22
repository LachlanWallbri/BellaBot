package com.aliyun.alink.dm.coap;

import com.aliyun.alink.dm.coap.p009a.C0850b;
import com.aliyun.alink.dm.p017k.C0859a;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPContext;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPResponse;
import com.aliyun.alink.linksdk.alcs.coap.IAlcsCoAPResHandler;
import com.aliyun.alink.linksdk.alcs.coap.resources.AlcsCoAPResource;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: CoAPClient.java */
/* renamed from: com.aliyun.alink.dm.coap.b */
/* loaded from: classes.dex */
public class C0851b {

    /* renamed from: c */
    private static C0851b f367c;

    /* renamed from: a */
    private C0848a f368a;

    /* renamed from: b */
    private C0850b f369b = null;

    /* renamed from: a */
    public static C0851b m111a() {
        if (f367c == null) {
            synchronized (C0851b.class) {
                if (f367c == null) {
                    f367c = new C0851b();
                }
            }
        }
        return f367c;
    }

    private C0851b() {
        this.f368a = null;
        this.f368a = new C0848a();
        m114b();
    }

    /* renamed from: b */
    public void m114b() {
        C0859a.m131a("CoAPClient", "initCoapService");
        if (this.f368a.m102a() == AlcsCoAPServiceStatus.IDLE) {
            AlcsCoAPContext alcsCoAPContext = new AlcsCoAPContext();
            C0850b c0850b = this.f369b;
            if (c0850b == null) {
                this.f369b = new C0850b();
            } else {
                c0850b.m109b();
            }
            AlcsCoAPResource alcsCoAPResource = new AlcsCoAPResource("TokenService", true);
            alcsCoAPResource.setPath("/sys/device/info/get");
            alcsCoAPResource.setPermission(3);
            alcsCoAPResource.setHandler(this.f369b);
            alcsCoAPContext.setPort(5683);
            this.f368a.m103a(alcsCoAPContext, alcsCoAPResource);
            this.f368a.m105b();
            return;
        }
        if (this.f368a.m102a() == AlcsCoAPServiceStatus.INITED) {
            this.f368a.m105b();
        } else if (this.f368a.m102a() == AlcsCoAPServiceStatus.STOPPED) {
            this.f368a.m105b();
        }
    }

    /* renamed from: a */
    public void m112a(IAlcsCoAPResHandler iAlcsCoAPResHandler) {
        this.f369b.m108a((C0850b) iAlcsCoAPResHandler);
        m114b();
    }

    /* renamed from: b */
    public void m115b(IAlcsCoAPResHandler iAlcsCoAPResHandler) {
        this.f369b.m110b(iAlcsCoAPResHandler);
    }

    /* renamed from: a */
    public boolean m113a(AlcsCoAPResponse alcsCoAPResponse) {
        String str;
        if (alcsCoAPResponse == null) {
            str = "";
        } else {
            str = ",response=[token=" + alcsCoAPResponse.getTokenString() + ",payload=" + alcsCoAPResponse.getPayloadString() + "]";
        }
        C0859a.m133b("CoAPClient", "sendResponse " + str);
        m114b();
        return this.f368a.m104a(alcsCoAPResponse);
    }
}
