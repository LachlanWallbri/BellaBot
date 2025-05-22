package io.opencensus.trace;

import io.opencensus.trace.Tracestate;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_Tracestate_Entry extends Tracestate.Entry {
    private final String key;
    private final String value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Tracestate_Entry(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("Null key");
        }
        this.key = str;
        if (str2 == null) {
            throw new NullPointerException("Null value");
        }
        this.value = str2;
    }

    @Override // io.opencensus.trace.Tracestate.Entry
    public String getKey() {
        return this.key;
    }

    @Override // io.opencensus.trace.Tracestate.Entry
    public String getValue() {
        return this.value;
    }

    public String toString() {
        return "Entry{key=" + this.key + ", value=" + this.value + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Tracestate.Entry)) {
            return false;
        }
        Tracestate.Entry entry = (Tracestate.Entry) obj;
        return this.key.equals(entry.getKey()) && this.value.equals(entry.getValue());
    }

    public int hashCode() {
        return ((this.key.hashCode() ^ 1000003) * 1000003) ^ this.value.hashCode();
    }
}
