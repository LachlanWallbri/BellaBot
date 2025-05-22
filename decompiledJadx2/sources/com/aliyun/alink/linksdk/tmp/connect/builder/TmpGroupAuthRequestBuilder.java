package com.aliyun.alink.linksdk.tmp.connect.builder;

import com.aliyun.alink.linksdk.tmp.device.payload.permission.GroupAuthRequestPayload;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpGroupAuthRequestBuilder extends TmpRequestBuilder<TmpGroupAuthRequestBuilder, GroupAuthRequestPayload> {
    public static TmpGroupAuthRequestBuilder createBuilder(String str, String str2) {
        return new TmpGroupAuthRequestBuilder(str, str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.aliyun.alink.linksdk.tmp.device.payload.permission.GroupAuthRequestPayload, Payload] */
    public TmpGroupAuthRequestBuilder(String str, String str2) {
        setProductKey(str);
        setDeviceName(str2);
        setPathPre("dev");
        setPathMethod(TmpConstant.METHOD_SERVICE_AUTH_INFO);
        this.mPayloadData = new GroupAuthRequestPayload(str, str2);
        ((GroupAuthRequestPayload) this.mPayloadData).setMethod(TmpConstant.METHOD_SERVICE_AUTH_INFO);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TmpGroupAuthRequestBuilder setOp(String str) {
        ((GroupAuthRequestPayload) this.mPayloadData).setOp(str);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TmpGroupAuthRequestBuilder setDataType(String str) {
        ((GroupAuthRequestPayload) this.mPayloadData).setDataType(str);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TmpGroupAuthRequestBuilder setGroupId(String str) {
        ((GroupAuthRequestPayload) this.mPayloadData).setGroupId(str);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TmpGroupAuthRequestBuilder setParam1(String str) {
        ((GroupAuthRequestPayload) this.mPayloadData).setParam1(str);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TmpGroupAuthRequestBuilder setParam2(String str) {
        ((GroupAuthRequestPayload) this.mPayloadData).setParam2(str);
        return this;
    }
}
