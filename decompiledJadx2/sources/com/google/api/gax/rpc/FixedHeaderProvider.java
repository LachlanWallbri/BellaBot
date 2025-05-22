package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.common.collect.ImmutableMap;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public abstract class FixedHeaderProvider implements HeaderProvider, Serializable {
    private static final long serialVersionUID = -4881534091594970538L;

    @Override // com.google.api.gax.rpc.HeaderProvider
    @Nullable
    public abstract Map<String, String> getHeaders();

    public static FixedHeaderProvider create(Map<String, String> map) {
        checkKeys(map.keySet());
        return new AutoValue_FixedHeaderProvider(ImmutableMap.copyOf((Map) map));
    }

    public static FixedHeaderProvider create(String... strArr) {
        if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("The keyValuePairs var-arg parameter must contain an even number of elements");
        }
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (int i = 0; i < strArr.length; i += 2) {
            builder.put(strArr[i], strArr[i + 1]);
        }
        ImmutableMap build = builder.build();
        checkKeys(build.keySet());
        return new AutoValue_FixedHeaderProvider(build);
    }

    private static void checkKeys(Collection<String> collection) {
        HashSet hashSet = new HashSet();
        for (String str : collection) {
            if (!hashSet.add(str.toLowerCase())) {
                throw new IllegalArgumentException("The header key '" + str + "' is not case insensitively unique");
            }
        }
    }
}
