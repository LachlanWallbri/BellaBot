package io.opencensus.trace.export;

import io.opencensus.common.Timestamp;
import io.opencensus.trace.Annotation;
import io.opencensus.trace.MessageEvent;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.SpanId;
import io.opencensus.trace.Status;
import io.opencensus.trace.export.SpanData;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class AutoValue_SpanData extends SpanData {
    private final SpanData.TimedEvents<Annotation> annotations;
    private final SpanData.Attributes attributes;
    private final Integer childSpanCount;
    private final SpanContext context;
    private final Timestamp endTimestamp;
    private final Boolean hasRemoteParent;
    private final Span.Kind kind;
    private final SpanData.Links links;
    private final SpanData.TimedEvents<MessageEvent> messageEvents;
    private final String name;
    private final SpanId parentSpanId;
    private final Timestamp startTimestamp;
    private final Status status;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SpanData(SpanContext spanContext, @Nullable SpanId spanId, @Nullable Boolean bool, String str, @Nullable Span.Kind kind, Timestamp timestamp, SpanData.Attributes attributes, SpanData.TimedEvents<Annotation> timedEvents, SpanData.TimedEvents<MessageEvent> timedEvents2, SpanData.Links links, @Nullable Integer num, @Nullable Status status, @Nullable Timestamp timestamp2) {
        if (spanContext == null) {
            throw new NullPointerException("Null context");
        }
        this.context = spanContext;
        this.parentSpanId = spanId;
        this.hasRemoteParent = bool;
        if (str == null) {
            throw new NullPointerException("Null name");
        }
        this.name = str;
        this.kind = kind;
        if (timestamp == null) {
            throw new NullPointerException("Null startTimestamp");
        }
        this.startTimestamp = timestamp;
        if (attributes == null) {
            throw new NullPointerException("Null attributes");
        }
        this.attributes = attributes;
        if (timedEvents == null) {
            throw new NullPointerException("Null annotations");
        }
        this.annotations = timedEvents;
        if (timedEvents2 == null) {
            throw new NullPointerException("Null messageEvents");
        }
        this.messageEvents = timedEvents2;
        if (links == null) {
            throw new NullPointerException("Null links");
        }
        this.links = links;
        this.childSpanCount = num;
        this.status = status;
        this.endTimestamp = timestamp2;
    }

    @Override // io.opencensus.trace.export.SpanData
    public SpanContext getContext() {
        return this.context;
    }

    @Override // io.opencensus.trace.export.SpanData
    @Nullable
    public SpanId getParentSpanId() {
        return this.parentSpanId;
    }

    @Override // io.opencensus.trace.export.SpanData
    @Nullable
    public Boolean getHasRemoteParent() {
        return this.hasRemoteParent;
    }

    @Override // io.opencensus.trace.export.SpanData
    public String getName() {
        return this.name;
    }

    @Override // io.opencensus.trace.export.SpanData
    @Nullable
    public Span.Kind getKind() {
        return this.kind;
    }

    @Override // io.opencensus.trace.export.SpanData
    public Timestamp getStartTimestamp() {
        return this.startTimestamp;
    }

    @Override // io.opencensus.trace.export.SpanData
    public SpanData.Attributes getAttributes() {
        return this.attributes;
    }

    @Override // io.opencensus.trace.export.SpanData
    public SpanData.TimedEvents<Annotation> getAnnotations() {
        return this.annotations;
    }

    @Override // io.opencensus.trace.export.SpanData
    public SpanData.TimedEvents<MessageEvent> getMessageEvents() {
        return this.messageEvents;
    }

    @Override // io.opencensus.trace.export.SpanData
    public SpanData.Links getLinks() {
        return this.links;
    }

    @Override // io.opencensus.trace.export.SpanData
    @Nullable
    public Integer getChildSpanCount() {
        return this.childSpanCount;
    }

    @Override // io.opencensus.trace.export.SpanData
    @Nullable
    public Status getStatus() {
        return this.status;
    }

    @Override // io.opencensus.trace.export.SpanData
    @Nullable
    public Timestamp getEndTimestamp() {
        return this.endTimestamp;
    }

    public String toString() {
        return "SpanData{context=" + this.context + ", parentSpanId=" + this.parentSpanId + ", hasRemoteParent=" + this.hasRemoteParent + ", name=" + this.name + ", kind=" + this.kind + ", startTimestamp=" + this.startTimestamp + ", attributes=" + this.attributes + ", annotations=" + this.annotations + ", messageEvents=" + this.messageEvents + ", links=" + this.links + ", childSpanCount=" + this.childSpanCount + ", status=" + this.status + ", endTimestamp=" + this.endTimestamp + "}";
    }

    public boolean equals(Object obj) {
        SpanId spanId;
        Boolean bool;
        Span.Kind kind;
        Integer num;
        Status status;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SpanData)) {
            return false;
        }
        SpanData spanData = (SpanData) obj;
        if (this.context.equals(spanData.getContext()) && ((spanId = this.parentSpanId) != null ? spanId.equals(spanData.getParentSpanId()) : spanData.getParentSpanId() == null) && ((bool = this.hasRemoteParent) != null ? bool.equals(spanData.getHasRemoteParent()) : spanData.getHasRemoteParent() == null) && this.name.equals(spanData.getName()) && ((kind = this.kind) != null ? kind.equals(spanData.getKind()) : spanData.getKind() == null) && this.startTimestamp.equals(spanData.getStartTimestamp()) && this.attributes.equals(spanData.getAttributes()) && this.annotations.equals(spanData.getAnnotations()) && this.messageEvents.equals(spanData.getMessageEvents()) && this.links.equals(spanData.getLinks()) && ((num = this.childSpanCount) != null ? num.equals(spanData.getChildSpanCount()) : spanData.getChildSpanCount() == null) && ((status = this.status) != null ? status.equals(spanData.getStatus()) : spanData.getStatus() == null)) {
            Timestamp timestamp = this.endTimestamp;
            if (timestamp == null) {
                if (spanData.getEndTimestamp() == null) {
                    return true;
                }
            } else if (timestamp.equals(spanData.getEndTimestamp())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.context.hashCode() ^ 1000003) * 1000003;
        SpanId spanId = this.parentSpanId;
        int hashCode2 = (hashCode ^ (spanId == null ? 0 : spanId.hashCode())) * 1000003;
        Boolean bool = this.hasRemoteParent;
        int hashCode3 = (((hashCode2 ^ (bool == null ? 0 : bool.hashCode())) * 1000003) ^ this.name.hashCode()) * 1000003;
        Span.Kind kind = this.kind;
        int hashCode4 = (((((((((((hashCode3 ^ (kind == null ? 0 : kind.hashCode())) * 1000003) ^ this.startTimestamp.hashCode()) * 1000003) ^ this.attributes.hashCode()) * 1000003) ^ this.annotations.hashCode()) * 1000003) ^ this.messageEvents.hashCode()) * 1000003) ^ this.links.hashCode()) * 1000003;
        Integer num = this.childSpanCount;
        int hashCode5 = (hashCode4 ^ (num == null ? 0 : num.hashCode())) * 1000003;
        Status status = this.status;
        int hashCode6 = (hashCode5 ^ (status == null ? 0 : status.hashCode())) * 1000003;
        Timestamp timestamp = this.endTimestamp;
        return hashCode6 ^ (timestamp != null ? timestamp.hashCode() : 0);
    }
}
