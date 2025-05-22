package com.aliyun.alink.linksdk.tmp.connect.builder;

import com.aliyun.alink.linksdk.cmp.connect.apigw.ApiGatewayRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.device.payload.discovery.GetTslRequestPayload;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpGetTslRequestBuilder extends TmpRequestBuilder<TmpGetTslRequestBuilder, GetTslRequestPayload> {
    protected String mPath = "/thing/tsl/get";
    protected String mVersion = "1.0.0";

    /* JADX WARN: Type inference failed for: r0v2, types: [Payload, com.aliyun.alink.linksdk.tmp.device.payload.discovery.GetTslRequestPayload] */
    public TmpGetTslRequestBuilder() {
        this.mPayloadData = new GetTslRequestPayload(null, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.aliyun.alink.linksdk.tmp.connect.builder.TmpRequestBuilder, com.aliyun.alink.linksdk.tmp.connect.CommonRequestBuilder
    public TmpCommonRequest createRequest() {
        return new TmpCommonRequest(ApiGatewayRequest.build(this.mPath, this.mVersion, ((GetTslRequestPayload) this.mPayloadData).getParams()));
    }

    public static TmpGetTslRequestBuilder createBuilder() {
        return new TmpGetTslRequestBuilder();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TmpGetTslRequestBuilder setIotId(String str) {
        ((GetTslRequestPayload) this.mPayloadData).putIotId(str);
        return this;
    }
}
