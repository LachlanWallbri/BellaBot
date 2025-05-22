package com.aliyun.alink.linksdk.tmp.resource;

import com.aliyun.alink.linksdk.cmp.api.ResourceRequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResource;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IResourceResponseListener;
import com.aliyun.alink.linksdk.tmp.device.payload.CommonResponsePayload;
import com.aliyun.alink.linksdk.tmp.listener.ITResResponseCallback;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TResResponseCallback implements ITResResponseCallback {
    private static final String TAG = "[Tmp]TResResponseCallback";
    protected IResourceResponseListener mListener;
    protected ResourceRequest mRequest;
    private String mRequestId;
    protected AResource mResource;

    public TResResponseCallback(ResourceRequest resourceRequest, AResource aResource, String str, IResourceResponseListener iResourceResponseListener) {
        this.mRequest = resourceRequest;
        this.mListener = iResourceResponseListener;
        this.mResource = aResource;
        this.mRequestId = str;
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.ITResResponseCallback
    public void onComplete(String str, ErrorInfo errorInfo, Object obj) {
        boolean z = errorInfo == null || errorInfo.isSuccess();
        ALog.m479d(TAG, "onComplete identifer:" + str + " mRequestId:" + this.mRequestId + " errorInfo:" + errorInfo + " result:" + obj);
        CommonResponsePayload commonResponsePayload = new CommonResponsePayload();
        commonResponsePayload.setId(this.mRequestId);
        if (z) {
            commonResponsePayload.setCode(200);
            commonResponsePayload.setData(obj);
        } else {
            commonResponsePayload.setCode(300);
            commonResponsePayload.setData(errorInfo.getErrorMsg());
        }
        AResponse aResponse = new AResponse();
        aResponse.data = GsonUtils.toJson(commonResponsePayload);
        this.mListener.onResponse(this.mResource, this.mRequest, aResponse);
    }
}
