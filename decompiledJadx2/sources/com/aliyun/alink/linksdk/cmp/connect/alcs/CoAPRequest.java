package com.aliyun.alink.linksdk.cmp.connect.alcs;

import com.aliyun.alink.linksdk.alcs.coap.AlcsCoAPConstant;
import com.aliyun.alink.linksdk.cmp.core.base.ARequest;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class CoAPRequest extends ARequest {
    public AlcsCoAPConstant.Code code;

    /* renamed from: ip */
    public String f1014ip;
    public boolean isSecurity;
    public Object payload;
    public int port;
    public String topic;
    public AlcsCoAPConstant.Type type;
    public String traceId = "";
    public String alinkIdForTracker = "";
}
