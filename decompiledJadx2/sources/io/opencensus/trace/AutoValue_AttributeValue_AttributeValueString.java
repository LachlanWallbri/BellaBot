package io.opencensus.trace;

import io.opencensus.trace.AttributeValue;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class AutoValue_AttributeValue_AttributeValueString extends AttributeValue.AttributeValueString {
    private final String stringValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AttributeValue_AttributeValueString(String str) {
        if (str == null) {
            throw new NullPointerException("Null stringValue");
        }
        this.stringValue = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.opencensus.trace.AttributeValue.AttributeValueString
    public String getStringValue() {
        return this.stringValue;
    }

    public String toString() {
        return "AttributeValueString{stringValue=" + this.stringValue + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AttributeValue.AttributeValueString) {
            return this.stringValue.equals(((AttributeValue.AttributeValueString) obj).getStringValue());
        }
        return false;
    }

    public int hashCode() {
        return this.stringValue.hashCode() ^ 1000003;
    }
}
