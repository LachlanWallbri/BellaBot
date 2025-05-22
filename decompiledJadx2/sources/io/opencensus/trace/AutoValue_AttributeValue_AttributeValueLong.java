package io.opencensus.trace;

import io.opencensus.trace.AttributeValue;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class AutoValue_AttributeValue_AttributeValueLong extends AttributeValue.AttributeValueLong {
    private final Long longValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AttributeValue_AttributeValueLong(Long l) {
        if (l == null) {
            throw new NullPointerException("Null longValue");
        }
        this.longValue = l;
    }

    @Override // io.opencensus.trace.AttributeValue.AttributeValueLong
    Long getLongValue() {
        return this.longValue;
    }

    public String toString() {
        return "AttributeValueLong{longValue=" + this.longValue + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AttributeValue.AttributeValueLong) {
            return this.longValue.equals(((AttributeValue.AttributeValueLong) obj).getLongValue());
        }
        return false;
    }

    public int hashCode() {
        return this.longValue.hashCode() ^ 1000003;
    }
}
