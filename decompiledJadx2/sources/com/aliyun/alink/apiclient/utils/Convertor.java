package com.aliyun.alink.apiclient.utils;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Convertor {
    public static Map<String, String> getMap(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue() == null ? "" : entry.getValue().toString());
        }
        return hashMap;
    }
}
