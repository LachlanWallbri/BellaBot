package io.opencensus.metrics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_LabelKey extends LabelKey {
    private final String description;
    private final String key;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_LabelKey(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("Null key");
        }
        this.key = str;
        if (str2 == null) {
            throw new NullPointerException("Null description");
        }
        this.description = str2;
    }

    @Override // io.opencensus.metrics.LabelKey
    public String getKey() {
        return this.key;
    }

    @Override // io.opencensus.metrics.LabelKey
    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "LabelKey{key=" + this.key + ", description=" + this.description + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LabelKey)) {
            return false;
        }
        LabelKey labelKey = (LabelKey) obj;
        return this.key.equals(labelKey.getKey()) && this.description.equals(labelKey.getDescription());
    }

    public int hashCode() {
        return ((this.key.hashCode() ^ 1000003) * 1000003) ^ this.description.hashCode();
    }
}
