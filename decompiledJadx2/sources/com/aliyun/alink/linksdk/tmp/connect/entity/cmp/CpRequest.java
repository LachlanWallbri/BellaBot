package com.aliyun.alink.linksdk.tmp.connect.entity.cmp;

import com.aliyun.alink.linksdk.cmp.api.CommonRequest;
import com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CpRequest extends TmpCommonRequest<CommonRequest> {
    @Override // com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest
    public boolean isMulThreadCallback() {
        return false;
    }

    public CpRequest(CommonRequest commonRequest) {
        super(commonRequest);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest
    public String getTopic() {
        return ((CommonRequest) this.mWrapperRequest).topic;
    }
}
