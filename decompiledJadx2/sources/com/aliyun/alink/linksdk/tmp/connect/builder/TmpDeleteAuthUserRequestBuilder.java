package com.aliyun.alink.linksdk.tmp.connect.builder;

import com.aliyun.alink.linksdk.tmp.device.payload.permission.DeleteAuthUserRequestPayload;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpDeleteAuthUserRequestBuilder extends TmpRequestBuilder<TmpDeleteAuthUserRequestBuilder, DeleteAuthUserRequestPayload> {
    /* JADX WARN: Type inference failed for: r0v1, types: [com.aliyun.alink.linksdk.tmp.device.payload.permission.DeleteAuthUserRequestPayload, Payload] */
    public TmpDeleteAuthUserRequestBuilder(String str, String str2) {
        setProductKey(str);
        setDeviceName(str2);
        setPathMethod("core.service.user");
        this.mPayloadData = new DeleteAuthUserRequestPayload(str, str2);
    }

    public static TmpDeleteAuthUserRequestBuilder createBuilder(String str, String str2) {
        return new TmpDeleteAuthUserRequestBuilder(str, str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TmpDeleteAuthUserRequestBuilder setUids(List<String> list) {
        ((DeleteAuthUserRequestPayload) this.mPayloadData).setIds(list);
        return this;
    }
}
