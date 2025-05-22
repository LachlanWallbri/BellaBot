package com.aliyun.alink.linksdk.tmp.device.panel.data;

import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class GetPropPayload {
    public Map<String, PropItem> data = new HashMap();

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes.dex */
    public static class PropItem {
        public long time;
        public ValueWrapper value;

        public PropItem(long j, ValueWrapper valueWrapper) {
            this.value = valueWrapper;
            this.time = j;
        }
    }
}
