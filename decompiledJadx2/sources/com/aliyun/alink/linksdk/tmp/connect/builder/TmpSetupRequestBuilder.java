package com.aliyun.alink.linksdk.tmp.connect.builder;

import com.aliyun.alink.linksdk.tmp.connect.CommonRequestBuilder;
import com.aliyun.alink.linksdk.tmp.device.payload.setup.SetupRequestPayload;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpSetupRequestBuilder extends TmpRequestBuilder<TmpSetupRequestBuilder, SetupRequestPayload> {
    /* JADX WARN: Type inference failed for: r0v3, types: [com.aliyun.alink.linksdk.tmp.device.payload.setup.SetupRequestPayload, Payload] */
    public TmpSetupRequestBuilder(String str, String str2) {
        setProductKey(str);
        setDeviceName(str2);
        setMethod(CommonRequestBuilder.Method.PUT);
        setPathMethod("core.service.setup");
        setPathPre("dev");
        this.mPayloadData = new SetupRequestPayload(str, str2);
    }

    public static TmpSetupRequestBuilder createBuilder(String str, String str2) {
        return new TmpSetupRequestBuilder(str, str2);
    }
}
