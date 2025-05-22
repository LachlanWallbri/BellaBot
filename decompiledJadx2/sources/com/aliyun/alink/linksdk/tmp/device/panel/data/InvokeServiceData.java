package com.aliyun.alink.linksdk.tmp.device.panel.data;

import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class InvokeServiceData {
    public static final String CALL_TYPE_ASYNC = "async";
    public static final String CALL_TYPE_SYNC = "sync";
    public Map<String, ValueWrapper> args;
    public String callType;
    public String identifier;
    public String iotId;
}
