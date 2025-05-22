package io.opencensus.metrics.data;

import io.opencensus.common.Timestamp;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_Exemplar extends Exemplar {
    private final Map<String, AttachmentValue> attachments;
    private final Timestamp timestamp;
    private final double value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Exemplar(double d, Timestamp timestamp, Map<String, AttachmentValue> map) {
        this.value = d;
        if (timestamp == null) {
            throw new NullPointerException("Null timestamp");
        }
        this.timestamp = timestamp;
        if (map == null) {
            throw new NullPointerException("Null attachments");
        }
        this.attachments = map;
    }

    @Override // io.opencensus.metrics.data.Exemplar
    public double getValue() {
        return this.value;
    }

    @Override // io.opencensus.metrics.data.Exemplar
    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    @Override // io.opencensus.metrics.data.Exemplar
    public Map<String, AttachmentValue> getAttachments() {
        return this.attachments;
    }

    public String toString() {
        return "Exemplar{value=" + this.value + ", timestamp=" + this.timestamp + ", attachments=" + this.attachments + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Exemplar)) {
            return false;
        }
        Exemplar exemplar = (Exemplar) obj;
        return Double.doubleToLongBits(this.value) == Double.doubleToLongBits(exemplar.getValue()) && this.timestamp.equals(exemplar.getTimestamp()) && this.attachments.equals(exemplar.getAttachments());
    }

    public int hashCode() {
        return this.attachments.hashCode() ^ (((((int) (1000003 ^ ((Double.doubleToLongBits(this.value) >>> 32) ^ Double.doubleToLongBits(this.value)))) * 1000003) ^ this.timestamp.hashCode()) * 1000003);
    }
}
