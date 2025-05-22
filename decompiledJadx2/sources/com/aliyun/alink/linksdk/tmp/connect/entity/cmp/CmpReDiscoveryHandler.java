package com.aliyun.alink.linksdk.tmp.connect.entity.cmp;

import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IDiscoveryListener;
import com.aliyun.alink.linksdk.cmp.manager.discovery.DiscoveryMessage;
import com.aliyun.alink.linksdk.tmp.connect.IRequestHandler;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpSyncRequestHandler;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tools.AError;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CmpReDiscoveryHandler extends TmpSyncRequestHandler implements IDiscoveryListener {
    public CmpReDiscoveryHandler(IRequestHandler iRequestHandler, TmpCommonRequest tmpCommonRequest) {
        super(iRequestHandler, tmpCommonRequest);
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IDiscoveryListener
    public void onDiscovery(DiscoveryMessage discoveryMessage) {
        AResponse aResponse = new AResponse();
        aResponse.data = discoveryMessage;
        onLoad(this.mRequest, new CpResponse(aResponse));
    }

    @Override // com.aliyun.alink.linksdk.cmp.core.listener.IDiscoveryListener
    public void onFailure(AError aError) {
        onError(this.mRequest, new ErrorInfo(aError.getCode(), aError.getMsg()));
    }
}
