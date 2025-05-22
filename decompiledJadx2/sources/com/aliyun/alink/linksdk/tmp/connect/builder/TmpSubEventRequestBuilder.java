package com.aliyun.alink.linksdk.tmp.connect.builder;

import com.aliyun.alink.linksdk.tmp.connect.CommonRequestBuilder;
import com.aliyun.alink.linksdk.tmp.device.payload.event.EventRequestPayload;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpSubEventRequestBuilder extends TmpRequestBuilder<TmpSubEventRequestBuilder, EventRequestPayload> {
    public TmpSubEventRequestBuilder() {
        this.mRequestTye = CommonRequestBuilder.RequestType.RELEATE;
    }

    public static TmpSubEventRequestBuilder createBuilder() {
        return new TmpSubEventRequestBuilder();
    }
}
