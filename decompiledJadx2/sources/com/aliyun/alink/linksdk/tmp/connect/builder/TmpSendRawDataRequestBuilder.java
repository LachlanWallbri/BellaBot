package com.aliyun.alink.linksdk.tmp.connect.builder;

import com.aliyun.alink.linksdk.cmp.api.CommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.entity.cmp.CpRequest;
import com.aliyun.alink.linksdk.tmp.device.payload.rawdata.SendRawdataRequestPayload;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpSendRawDataRequestBuilder extends TmpRequestBuilder<TmpSendRawDataRequestBuilder, SendRawdataRequestPayload> {
    /* JADX WARN: Type inference failed for: r1v1, types: [com.aliyun.alink.linksdk.tmp.device.payload.rawdata.SendRawdataRequestPayload, Payload] */
    protected TmpSendRawDataRequestBuilder(String str, String str2) {
        setProductKey(str);
        setDeviceName(str2);
        this.mPayloadData = new SendRawdataRequestPayload();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TmpSendRawDataRequestBuilder setData(byte[] bArr) {
        ((SendRawdataRequestPayload) this.mPayloadData).setData(bArr);
        return this;
    }

    public static TmpSendRawDataRequestBuilder createBuilder(String str, String str2) {
        return new TmpSendRawDataRequestBuilder(str, str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.aliyun.alink.linksdk.tmp.connect.builder.TmpRequestBuilder, com.aliyun.alink.linksdk.tmp.connect.CommonRequestBuilder
    public TmpCommonRequest createRequest() {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.f1011ip = this.mAddr;
        commonRequest.port = this.mPort;
        commonRequest.topic = "/" + this.mPathPre + "/" + this.mProductKey + "/" + this.mDeviceName + TmpConstant.URI_THING + TmpConstant.URI_MODEL + "/up_raw";
        commonRequest.mothod = getMethod().toCRMethod();
        commonRequest.payload = ((SendRawdataRequestPayload) this.mPayloadData).getData();
        commonRequest.context = this.mTag;
        commonRequest.type = Integer.valueOf(this.mType);
        commonRequest.isSecurity = this.mIsSecure;
        CpRequest cpRequest = new CpRequest(commonRequest);
        cpRequest.setProductKey(getProductKey());
        cpRequest.setDeviceName(getDeviceName());
        cpRequest.setTag(this.mTag);
        cpRequest.setIsSecurity(this.mIsSecure);
        return cpRequest;
    }
}
