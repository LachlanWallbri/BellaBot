package io.opencensus.resource;

import java.util.Map;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_Resource extends Resource {
    private final Map<String, String> labels;
    private final String type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Resource(@Nullable String str, Map<String, String> map) {
        this.type = str;
        if (map == null) {
            throw new NullPointerException("Null labels");
        }
        this.labels = map;
    }

    @Override // io.opencensus.resource.Resource
    @Nullable
    public String getType() {
        return this.type;
    }

    @Override // io.opencensus.resource.Resource
    public Map<String, String> getLabels() {
        return this.labels;
    }

    public String toString() {
        return "Resource{type=" + this.type + ", labels=" + this.labels + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Resource)) {
            return false;
        }
        Resource resource = (Resource) obj;
        String str = this.type;
        if (str != null ? str.equals(resource.getType()) : resource.getType() == null) {
            if (this.labels.equals(resource.getLabels())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.type;
        return (((str == null ? 0 : str.hashCode()) ^ 1000003) * 1000003) ^ this.labels.hashCode();
    }
}
