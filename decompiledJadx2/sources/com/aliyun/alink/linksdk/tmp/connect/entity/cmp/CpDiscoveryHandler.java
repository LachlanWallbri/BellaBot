package com.aliyun.alink.linksdk.tmp.connect.entity.cmp;

import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IDiscoveryListener;
import com.aliyun.alink.linksdk.cmp.manager.discovery.DiscoveryMessage;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.event.INotifyHandler;
import com.aliyun.alink.linksdk.tools.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CpDiscoveryHandler implements IDiscoveryListener {
    protected INotifyHandler mHandler;
    protected TmpCommonRequest mRequest = new CpEventNotifyRequest(null);

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IDiscoveryListener
    public void onFailure(AError aError) {
    }

    public CpDiscoveryHandler(INotifyHandler iNotifyHandler) {
        this.mHandler = iNotifyHandler;
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IDiscoveryListener
    public void onDiscovery(DiscoveryMessage discoveryMessage) {
        AResponse aResponse = new AResponse();
        aResponse.data = discoveryMessage;
        this.mHandler.onMessage(this.mRequest, new CpResponse(aResponse));
    }
}
