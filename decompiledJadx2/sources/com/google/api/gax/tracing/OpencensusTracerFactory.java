package com.google.api.gax.tracing;

import com.google.api.core.InternalApi;
import com.google.api.gax.tracing.ApiTracerFactory;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import io.opencensus.trace.AttributeValue;
import io.opencensus.trace.Span;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.Tracing;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For google-cloud-java client use only")
/* loaded from: classes2.dex */
public final class OpencensusTracerFactory implements ApiTracerFactory {

    @Nonnull
    private final Tracer internalTracer;

    @Nonnull
    private final Map<String, AttributeValue> spanAttributes;

    public OpencensusTracerFactory() {
        this(ImmutableMap.m653of());
    }

    public OpencensusTracerFactory(Map<String, String> map) {
        this(Tracing.getTracer(), map);
    }

    @InternalApi("Visible for testing")
    OpencensusTracerFactory(Tracer tracer, @Nonnull Map<String, String> map) {
        this.internalTracer = (Tracer) Preconditions.checkNotNull(tracer, "internalTracer can't be null");
        ImmutableMap.Builder builder = ImmutableMap.builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.put(entry.getKey(), AttributeValue.stringAttributeValue(entry.getValue()));
        }
        this.spanAttributes = builder.build();
    }

    @Override // com.google.api.gax.tracing.ApiTracerFactory
    public ApiTracer newTracer(ApiTracer apiTracer, SpanName spanName, ApiTracerFactory.OperationType operationType) {
        Span currentSpan = this.internalTracer.getCurrentSpan();
        if (apiTracer instanceof OpencensusTracer) {
            currentSpan = ((OpencensusTracer) apiTracer).getSpan();
        }
        Span startSpan = this.internalTracer.spanBuilderWithExplicitParent(spanName.toString(), currentSpan).setRecordEvents(true).startSpan();
        startSpan.putAttributes(this.spanAttributes);
        return new OpencensusTracer(this.internalTracer, startSpan, operationType);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OpencensusTracerFactory opencensusTracerFactory = (OpencensusTracerFactory) obj;
        return Objects.equal(this.internalTracer, opencensusTracerFactory.internalTracer) && Objects.equal(this.spanAttributes, opencensusTracerFactory.spanAttributes);
    }

    public int hashCode() {
        return Objects.hashCode(this.internalTracer, this.spanAttributes);
    }
}
