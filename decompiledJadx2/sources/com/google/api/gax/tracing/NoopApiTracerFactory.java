package com.google.api.gax.tracing;

import com.google.api.core.InternalApi;
import com.google.api.gax.tracing.ApiTracerFactory;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi
/* loaded from: classes2.dex */
public final class NoopApiTracerFactory implements ApiTracerFactory {
    private static final NoopApiTracerFactory INSTANCE = new NoopApiTracerFactory();

    public static NoopApiTracerFactory getInstance() {
        return INSTANCE;
    }

    private NoopApiTracerFactory() {
    }

    @Override // com.google.api.gax.tracing.ApiTracerFactory
    public ApiTracer newTracer(ApiTracer apiTracer, SpanName spanName, ApiTracerFactory.OperationType operationType) {
        return NoopApiTracer.getInstance();
    }
}
