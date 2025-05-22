package com.aliyun.alink.linksdk.tmp.connect.builder;

import com.aliyun.alink.linksdk.tmp.connect.CommonRequestBuilder;
import com.aliyun.alink.linksdk.tmp.device.payload.event.EventRequestPayload;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpCancelSubEventRequestBuilder extends TmpRequestBuilder<TmpCancelSubEventRequestBuilder, EventRequestPayload> {
    public TmpCancelSubEventRequestBuilder() {
        this.mObserveFlag = false;
        this.mRequestTye = CommonRequestBuilder.RequestType.RELEATE;
    }

    public static TmpCancelSubEventRequestBuilder createBuilder() {
        return new TmpCancelSubEventRequestBuilder();
    }
}
