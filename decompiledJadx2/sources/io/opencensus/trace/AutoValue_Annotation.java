package io.opencensus.trace;

import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_Annotation extends Annotation {
    private final Map<String, AttributeValue> attributes;
    private final String description;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Annotation(String str, Map<String, AttributeValue> map) {
        if (str == null) {
            throw new NullPointerException("Null description");
        }
        this.description = str;
        if (map == null) {
            throw new NullPointerException("Null attributes");
        }
        this.attributes = map;
    }

    @Override // io.opencensus.trace.Annotation
    public String getDescription() {
        return this.description;
    }

    @Override // io.opencensus.trace.Annotation
    public Map<String, AttributeValue> getAttributes() {
        return this.attributes;
    }

    public String toString() {
        return "Annotation{description=" + this.description + ", attributes=" + this.attributes + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Annotation)) {
            return false;
        }
        Annotation annotation = (Annotation) obj;
        return this.description.equals(annotation.getDescription()) && this.attributes.equals(annotation.getAttributes());
    }

    public int hashCode() {
        return ((this.description.hashCode() ^ 1000003) * 1000003) ^ this.attributes.hashCode();
    }
}
