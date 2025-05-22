package com.aliyun.alink.linksdk.tmp.connect.builder;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.cmp.api.CommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.CommonRequestBuilder;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.entity.cmp.CpRequest;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpRequestBuilder<Builder, Payload> extends CommonRequestBuilder<Builder, Payload> {
    protected String mDeviceName;
    protected Boolean mObserveFlag;
    protected String mPathMethod;
    protected String mProductKey;
    protected int mType;
    protected boolean mIsMulThreadCallback = false;
    protected String mPathPre = "sys";

    public boolean isMulThreadCallback() {
        return this.mIsMulThreadCallback;
    }

    public void setIsMulThreadCallback(boolean z) {
        this.mIsMulThreadCallback = z;
    }

    public Builder setType(int i) {
        this.mType = i;
        return this.mBuilder;
    }

    public Builder setPathPre(String str) {
        this.mPathPre = str;
        return this.mBuilder;
    }

    public Builder setObserveFlag(boolean z) {
        this.mObserveFlag = Boolean.valueOf(z);
        return this.mBuilder;
    }

    public String getProductKey() {
        return this.mProductKey;
    }

    public Builder setProductKey(String str) {
        this.mProductKey = str;
        return this.mBuilder;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public Builder setDeviceName(String str) {
        this.mDeviceName = str;
        return this.mBuilder;
    }

    public String getPathMethod() {
        return this.mPathMethod;
    }

    public Builder setPathMethod(String str) {
        this.mPathMethod = str;
        return this.mBuilder;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.CommonRequestBuilder
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
        CpRequest cpRequest = new CpRequest(commonRequest);
        cpRequest.setProductKey(getProductKey());
        cpRequest.setDeviceName(getDeviceName());
        cpRequest.setTag(this.mTag);
        cpRequest.setIsSecurity(this.mIsSecure);
        return cpRequest;
    }
}
