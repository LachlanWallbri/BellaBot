package com.aliyun.alink.linksdk.tmp.connect.builder;

import com.aliyun.alink.linksdk.tmp.device.payload.discovery.DevNotifyRequestPayload;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class TmpNotifyRequestBuilder extends TmpRequestBuilder<TmpNotifyRequestBuilder, DevNotifyRequestPayload> {
    /* JADX WARN: Type inference failed for: r0v4, types: [Payload, com.aliyun.alink.linksdk.tmp.device.payload.discovery.DevNotifyRequestPayload] */
    protected TmpNotifyRequestBuilder() {
        this.mIsSecure = false;
        this.mAddr = "224.0.1.187";
        this.mPath = TmpConstant.PATH_NOTIFY;
        setIsMulThreadCallback(true);
        this.mPayloadData = new DevNotifyRequestPayload();
    }

    public static TmpNotifyRequestBuilder createBuilder() {
        return new TmpNotifyRequestBuilder();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TmpNotifyRequestBuilder setDeviceModel(String str) {
        ((DevNotifyRequestPayload) this.mPayloadData).setDeviceModel(str);
        return this;
    }
}
