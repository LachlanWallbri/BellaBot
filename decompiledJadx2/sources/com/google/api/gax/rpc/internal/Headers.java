package com.google.api.gax.rpc.internal;

import com.google.api.core.InternalApi;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi
/* loaded from: classes2.dex */
public class Headers {
    public static ImmutableMap<String, List<String>> mergeHeaders(Map<String, List<String>> map, Map<String, List<String>> map2) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            List<String> list = map2.get(key);
            ImmutableList.Builder builder2 = ImmutableList.builder();
            builder2.addAll((Iterable) value);
            if (list != null) {
                builder2.addAll((Iterable) list);
            }
            builder.put(key, builder2.build());
        }
        for (Map.Entry<String, List<String>> entry2 : map2.entrySet()) {
            String key2 = entry2.getKey();
            if (!map.containsKey(key2)) {
                builder.put(key2, ImmutableList.copyOf((Collection) entry2.getValue()));
            }
        }
        return builder.build();
    }
}
