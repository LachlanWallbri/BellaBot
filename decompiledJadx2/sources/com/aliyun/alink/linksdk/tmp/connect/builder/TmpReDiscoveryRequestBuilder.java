package com.aliyun.alink.linksdk.tmp.connect.builder;

import com.aliyun.alink.linksdk.cmp.manager.discovery.DiscoveryRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpReDiscoveryRequestBuilder extends TmpRequestBuilder<TmpReDiscoveryRequestBuilder, Object> {
    public static TmpReDiscoveryRequestBuilder createBuilder() {
        return new TmpReDiscoveryRequestBuilder();
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.builder.TmpRequestBuilder, com.aliyun.alink.linksdk.tmp.connect.CommonRequestBuilder
    public TmpCommonRequest createRequest() {
        DiscoveryRequest discoveryRequest = new DiscoveryRequest();
        discoveryRequest.productKey = getProductKey();
        discoveryRequest.deviceName = getDeviceName();
        TmpCommonRequest tmpCommonRequest = new TmpCommonRequest(discoveryRequest);
        tmpCommonRequest.setProductKey(getProductKey());
        tmpCommonRequest.setDeviceName(getDeviceName());
        return tmpCommonRequest;
    }
}
