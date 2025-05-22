package com.google.api.gax.tracing;

import com.google.api.core.InternalApi;
import com.google.api.core.InternalExtensionOnly;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalExtensionOnly
@InternalApi("For internal use by google-cloud-java clients only")
/* loaded from: classes2.dex */
public interface ApiTracerFactory {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public enum OperationType {
        Unary,
        Batching,
        LongRunning,
        ServerStreaming,
        ClientStreaming,
        BidiStreaming
    }

    ApiTracer newTracer(ApiTracer apiTracer, SpanName spanName, OperationType operationType);
}
