package com.aliyun.alink.dm.coap.p009a;

import com.aliyun.alink.dm.p017k.C0859a;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPContext;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPRequest;
import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPResponse;
import com.aliyun.alink.linksdk.alcs.coap.IAlcsCoAPResHandler;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: CoAPNotifyDeliver.java */
/* renamed from: com.aliyun.alink.dm.coap.a.b */
/* loaded from: classes.dex */
public class C0850b extends C0849a<IAlcsCoAPResHandler> implements IAlcsCoAPResHandler {

    /* renamed from: b */
    private AlcsCoAPResponse f366b = null;

    @Override // com.aliyun.alink.linksdk.alcs.coap.IAlcsCoAPResHandler
    public void onRecRequest(AlcsCoAPContext alcsCoAPContext, AlcsCoAPRequest alcsCoAPRequest) {
        StringBuilder sb = new StringBuilder();
        sb.append("onRecRequest request=");
        sb.append(alcsCoAPRequest != null ? alcsCoAPRequest.getPayloadString() : "null");
        C0859a.m131a("CoAPNotifyDeliver", sb.toString());
        if (this.f364a == null || this.f364a.size() == 0) {
            C0859a.m131a("CoAPNotifyDeliver", "onRecRequest chainList empty. to handle cache.");
            return;
        }
        for (int i = 0; i < this.f364a.size(); i++) {
            if (this.f364a != null && this.f364a.get(i) != null) {
                ((IAlcsCoAPResHandler) this.f364a.get(i)).onRecRequest(alcsCoAPContext, alcsCoAPRequest);
            }
        }
    }
}
