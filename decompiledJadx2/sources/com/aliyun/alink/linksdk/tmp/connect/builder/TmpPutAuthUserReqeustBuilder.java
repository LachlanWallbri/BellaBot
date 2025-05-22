package com.aliyun.alink.linksdk.tmp.connect.builder;

import com.aliyun.alink.linksdk.tmp.device.payload.permission.PutAuthUserRequestPayload;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpPutAuthUserReqeustBuilder extends TmpRequestBuilder<TmpPutAuthUserReqeustBuilder, PutAuthUserRequestPayload> {
    /* JADX WARN: Type inference failed for: r0v1, types: [com.aliyun.alink.linksdk.tmp.device.payload.permission.PutAuthUserRequestPayload, Payload] */
    public TmpPutAuthUserReqeustBuilder(String str, String str2) {
        setProductKey(str);
        setDeviceName(str2);
        setPathMethod("core.service.user");
        this.mPayloadData = new PutAuthUserRequestPayload(str, str2);
    }

    public static TmpPutAuthUserReqeustBuilder createBuilder(String str, String str2) {
        return new TmpPutAuthUserReqeustBuilder(str, str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TmpPutAuthUserReqeustBuilder setUid(String str) {
        ((PutAuthUserRequestPayload) this.mPayloadData).setAuthId(str);
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TmpPutAuthUserReqeustBuilder setToken(String str) {
        ((PutAuthUserRequestPayload) this.mPayloadData).setToken(str);
        return this;
    }
}
