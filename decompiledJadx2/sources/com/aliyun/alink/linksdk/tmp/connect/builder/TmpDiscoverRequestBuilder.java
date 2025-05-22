package com.aliyun.alink.linksdk.tmp.connect.builder;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.cmp.api.CommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.CommonRequestBuilder;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.entity.cmp.CpRequest;
import com.aliyun.alink.linksdk.tmp.device.payload.discovery.DiscoveryRequestPayload;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpDiscoverRequestBuilder extends TmpRequestBuilder<TmpDiscoverRequestBuilder, DiscoveryRequestPayload> {
    protected TmpDiscoverRequestBuilder() {
        this.mIsSecure = false;
        this.mAddr = "224.0.1.187";
        this.mPath = TmpConstant.PATH_DISCOVERY;
        this.mRequestTye = CommonRequestBuilder.RequestType.MULTIPLE_RESPONSE;
        setObserveFlag(true);
    }

    public static TmpDiscoverRequestBuilder createBuilder() {
        return new TmpDiscoverRequestBuilder();
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.builder.TmpRequestBuilder, com.aliyun.alink.linksdk.tmp.connect.CommonRequestBuilder
    public TmpCommonRequest createRequest() {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.f1011ip = this.mAddr;
        commonRequest.port = this.mPort;
        commonRequest.topic = this.mPath;
        commonRequest.mothod = getMethod().toCRMethod();
        commonRequest.payload = TextUtils.isEmpty(this.mPayload) ? GsonUtils.toJson(this.mPayloadData) : this.mPayload;
        commonRequest.context = this.mTag;
        commonRequest.type = Integer.valueOf(this.mType);
        commonRequest.isSecurity = false;
        return new CpRequest(commonRequest);
    }
}
