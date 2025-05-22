package com.aliyun.alink.linksdk.tmp.api;

import com.aliyun.alink.linksdk.tmp.device.payload.ValueWrapper;
import com.aliyun.alink.linksdk.tmp.utils.GsonUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class OutputParams extends HashMap<String, ValueWrapper> {
    public OutputParams() {
    }

    public OutputParams(Map<String, ValueWrapper> map) {
        this();
        if (map != null) {
            putAll(map);
        }
    }

    public OutputParams(String str, ValueWrapper valueWrapper) {
        this();
        if (valueWrapper != null) {
            put(str, valueWrapper);
        }
    }

    public String toJson() {
        if (isEmpty()) {
            return null;
        }
        return GsonUtils.toJson(this);
    }
}
