package com.google.api.gax.rpc;

import java.util.Map;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class AutoValue_FixedHeaderProvider extends FixedHeaderProvider {
    private static final long serialVersionUID = -4881534091594970538L;
    private final Map<String, String> headers;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_FixedHeaderProvider(@Nullable Map<String, String> map) {
        this.headers = map;
    }

    @Override // com.google.api.gax.rpc.FixedHeaderProvider, com.google.api.gax.rpc.HeaderProvider
    @Nullable
    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public String toString() {
        return "FixedHeaderProvider{headers=" + this.headers + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FixedHeaderProvider)) {
            return false;
        }
        Map<String, String> map = this.headers;
        Map<String, String> headers = ((FixedHeaderProvider) obj).getHeaders();
        return map == null ? headers == null : map.equals(headers);
    }

    public int hashCode() {
        Map<String, String> map = this.headers;
        return (map == null ? 0 : map.hashCode()) ^ 1000003;
    }
}
