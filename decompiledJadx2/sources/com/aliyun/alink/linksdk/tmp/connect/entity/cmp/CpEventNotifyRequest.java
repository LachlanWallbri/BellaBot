package com.aliyun.alink.linksdk.tmp.connect.entity.cmp;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CpEventNotifyRequest extends CpRequest {
    protected String mTopic;

    public CpEventNotifyRequest(String str) {
        super(null);
        this.mTopic = str;
    }

    @Override // com.aliyun.alink.linksdk.tmp.connect.entity.cmp.CpRequest, com.aliyun.alink.linksdk.tmp.connect.TmpCommonRequest
    public String getTopic() {
        return this.mTopic;
    }
}
