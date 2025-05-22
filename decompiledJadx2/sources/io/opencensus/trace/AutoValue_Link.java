package io.opencensus.trace;

import io.opencensus.trace.Link;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_Link extends Link {
    private final Map<String, AttributeValue> attributes;
    private final SpanId spanId;
    private final TraceId traceId;
    private final Link.Type type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Link(TraceId traceId, SpanId spanId, Link.Type type, Map<String, AttributeValue> map) {
        if (traceId == null) {
            throw new NullPointerException("Null traceId");
        }
        this.traceId = traceId;
        if (spanId == null) {
            throw new NullPointerException("Null spanId");
        }
        this.spanId = spanId;
        if (type == null) {
            throw new NullPointerException("Null type");
        }
        this.type = type;
        if (map == null) {
            throw new NullPointerException("Null attributes");
        }
        this.attributes = map;
    }

    @Override // io.opencensus.trace.Link
    public TraceId getTraceId() {
        return this.traceId;
    }

    @Override // io.opencensus.trace.Link
    public SpanId getSpanId() {
        return this.spanId;
    }

    @Override // io.opencensus.trace.Link
    public Link.Type getType() {
        return this.type;
    }

    @Override // io.opencensus.trace.Link
    public Map<String, AttributeValue> getAttributes() {
        return this.attributes;
    }

    public String toString() {
        return "Link{traceId=" + this.traceId + ", spanId=" + this.spanId + ", type=" + this.type + ", attributes=" + this.attributes + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Link)) {
            return false;
        }
        Link link = (Link) obj;
        return this.traceId.equals(link.getTraceId()) && this.spanId.equals(link.getSpanId()) && this.type.equals(link.getType()) && this.attributes.equals(link.getAttributes());
    }

    public int hashCode() {
        return ((((((this.traceId.hashCode() ^ 1000003) * 1000003) ^ this.spanId.hashCode()) * 1000003) ^ this.type.hashCode()) * 1000003) ^ this.attributes.hashCode();
    }
}
