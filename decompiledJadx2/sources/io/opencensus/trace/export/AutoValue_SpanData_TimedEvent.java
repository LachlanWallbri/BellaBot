package io.opencensus.trace.export;

import io.opencensus.common.Timestamp;
import io.opencensus.trace.export.SpanData;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class AutoValue_SpanData_TimedEvent<T> extends SpanData.TimedEvent<T> {
    private final T event;
    private final Timestamp timestamp;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SpanData_TimedEvent(Timestamp timestamp, T t) {
        if (timestamp == null) {
            throw new NullPointerException("Null timestamp");
        }
        this.timestamp = timestamp;
        if (t == null) {
            throw new NullPointerException("Null event");
        }
        this.event = t;
    }

    @Override // io.opencensus.trace.export.SpanData.TimedEvent
    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    @Override // io.opencensus.trace.export.SpanData.TimedEvent
    public T getEvent() {
        return this.event;
    }

    public String toString() {
        return "TimedEvent{timestamp=" + this.timestamp + ", event=" + this.event + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SpanData.TimedEvent)) {
            return false;
        }
        SpanData.TimedEvent timedEvent = (SpanData.TimedEvent) obj;
        return this.timestamp.equals(timedEvent.getTimestamp()) && this.event.equals(timedEvent.getEvent());
    }

    public int hashCode() {
        return ((this.timestamp.hashCode() ^ 1000003) * 1000003) ^ this.event.hashCode();
    }
}
