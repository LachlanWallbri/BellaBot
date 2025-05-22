package com.aliyun.alink.linksdk.tmp.connect.builder;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.cmp.api.CommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.entity.cmp.CpRequest;
import com.aliyun.alink.linksdk.tmp.device.payload.property.SetPropertyRequestPayload;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpSetPropertyRequestBuilder extends TmpRequestBuilder<TmpSetPropertyRequestBuilder, SetPropertyRequestPayload> {
    protected int mPerformanceId = 0;

    public static TmpSetPropertyRequestBuilder createBuilder() {
        return new TmpSetPropertyRequestBuilder();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TmpSetPropertyRequestBuilder setPerformanceId(int i) {
        this.mPerformanceId = i;
        return (TmpSetPropertyRequestBuilder) this.mBuilder;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.aliyun.alink.linksdk.tmp.connect.builder.TmpRequestBuilder, com.aliyun.alink.linksdk.tmp.connect.CommonRequestBuilder
    public TmpCommonRequest createRequest() {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.f1011ip = this.mAddr;
        commonRequest.port = this.mPort;
        commonRequest.topic = formatPath(getProductKey(), getDeviceName(), getPathMethod(), this.mPathPre);
        commonRequest.mothod = getMethod().toCRMethod();
        commonRequest.payload = TextUtils.isEmpty(this.mPayload) ? GsonUtils.toJson(this.mPayloadData) : this.mPayload;
        commonRequest.context = this.mTag;
        commonRequest.type = Integer.valueOf(this.mType);
        commonRequest.isSecurity = this.mIsSecure;
        commonRequest.traceId = String.valueOf(this.mPerformanceId);
        commonRequest.alinkIdForTracker = String.valueOf(this.mPayloadData == 0 ? "" : ((SetPropertyRequestPayload) this.mPayloadData).getId());
        CpRequest cpRequest = new CpRequest(commonRequest);
        cpRequest.setProductKey(getProductKey());
        cpRequest.setDeviceName(getDeviceName());
        cpRequest.setTag(this.mTag);
        cpRequest.setIsSecurity(this.mIsSecure);
        return cpRequest;
    }
}
