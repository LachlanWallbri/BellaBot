package com.aliyun.alink.linksdk.tmp.resource;

import com.aliyun.alink.linksdk.cmp.api.ResourceRequest;
import com.aliyun.alink.linksdk.cmp.core.base.AResource;
import com.aliyun.alink.linksdk.cmp.core.base.AResponse;
import com.aliyun.alink.linksdk.cmp.core.listener.IResourceResponseListener;
import com.aliyun.alink.linksdk.tmp.listener.ITResResponseCallback;
import com.aliyun.alink.linksdk.tmp.utils.ErrorInfo;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TRawResRespnseCallback implements ITResResponseCallback {
    protected IResourceResponseListener mListener;
    protected ResourceRequest mRequest;
    protected AResource mResource;

    public TRawResRespnseCallback(ResourceRequest resourceRequest, AResource aResource, IResourceResponseListener iResourceResponseListener) {
        this.mRequest = resourceRequest;
        this.mListener = iResourceResponseListener;
        this.mResource = aResource;
    }

    @Override // com.aliyun.alink.linksdk.tmp.listener.ITResResponseCallback
    public void onComplete(String str, ErrorInfo errorInfo, Object obj) {
        if (errorInfo != null) {
            errorInfo.isSuccess();
        }
        new AResponse().data = obj;
        this.mListener.onResponse(this.mResource, this.mRequest, null);
    }
}
