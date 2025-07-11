package io.opencensus.trace;

import io.opencensus.trace.AttributeValue;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_AttributeValue_AttributeValueDouble extends AttributeValue.AttributeValueDouble {
    private final Double doubleValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AttributeValue_AttributeValueDouble(Double d) {
        if (d == null) {
            throw new NullPointerException("Null doubleValue");
        }
        this.doubleValue = d;
    }

    @Override // io.opencensus.trace.AttributeValue.AttributeValueDouble
    Double getDoubleValue() {
        return this.doubleValue;
    }

    public String toString() {
        return "AttributeValueDouble{doubleValue=" + this.doubleValue + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AttributeValue.AttributeValueDouble) {
            return this.doubleValue.equals(((AttributeValue.AttributeValueDouble) obj).getDoubleValue());
        }
        return false;
    }

    public int hashCode() {
        return this.doubleValue.hashCode() ^ 1000003;
    }
}
