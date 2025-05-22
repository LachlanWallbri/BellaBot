package com.google.api.gax.tracing;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.gax.rpc.ApiException;
import com.google.api.gax.rpc.StatusCode;
import com.google.api.gax.tracing.ApiTracer;
import com.google.api.gax.tracing.ApiTracerFactory;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.opencensus.common.Scope;
import io.opencensus.trace.AttributeValue;
import io.opencensus.trace.EndSpanOptions;
import io.opencensus.trace.Span;
import io.opencensus.trace.Status;
import io.opencensus.trace.Tracer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nonnull;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("Surface for tracing is not yet stable")
/* loaded from: classes2.dex */
public class OpencensusTracer implements ApiTracer {
    private volatile long currentAttemptId;
    private volatile String lastConnectionId;
    private final ApiTracerFactory.OperationType operationType;
    private final Span span;
    private final Tracer tracer;
    private AtomicLong attemptSentMessages = new AtomicLong(0);
    private long attemptReceivedMessages = 0;
    private AtomicLong totalSentMessages = new AtomicLong(0);
    private long totalReceivedMessages = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OpencensusTracer(@Nonnull Tracer tracer, @Nonnull Span span, @Nonnull ApiTracerFactory.OperationType operationType) {
        this.tracer = (Tracer) Preconditions.checkNotNull(tracer, "tracer can't be null");
        this.span = (Span) Preconditions.checkNotNull(span, "span can't be null");
        this.operationType = (ApiTracerFactory.OperationType) Preconditions.checkNotNull(operationType, "operationType can't be null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Span getSpan() {
        return this.span;
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public ApiTracer.Scope inScope() {
        final Scope withSpan = this.tracer.withSpan(this.span);
        return new ApiTracer.Scope() { // from class: com.google.api.gax.tracing.OpencensusTracer.1
            @Override // com.google.api.gax.tracing.ApiTracer.Scope, java.lang.AutoCloseable
            public void close() {
                withSpan.close();
            }
        };
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void operationSucceeded() {
        this.span.putAttributes(baseOperationAttributes());
        this.span.end();
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void operationCancelled() {
        this.span.putAttributes(baseOperationAttributes());
        this.span.end(EndSpanOptions.builder().setStatus(Status.CANCELLED.withDescription("Cancelled by caller")).build());
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void operationFailed(Throwable th) {
        this.span.putAttributes(baseOperationAttributes());
        this.span.end(EndSpanOptions.builder().setStatus(convertErrorToStatus(th)).build());
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void lroStartFailed(Throwable th) {
        HashMap hashMap = new HashMap();
        populateError(hashMap, th);
        this.span.addAnnotation("Operation failed to start", hashMap);
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void lroStartSucceeded() {
        this.span.addAnnotation("Operation started");
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void connectionSelected(String str) {
        this.lastConnectionId = str;
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void attemptStarted(int i) {
        this.currentAttemptId = i;
        this.attemptSentMessages.set(0L);
        this.attemptReceivedMessages = 0L;
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void attemptSucceeded() {
        Map<String, AttributeValue> baseAttemptAttributes = baseAttemptAttributes();
        if (this.operationType == ApiTracerFactory.OperationType.LongRunning) {
            this.span.addAnnotation("Polling completed", baseAttemptAttributes);
        } else {
            this.span.addAnnotation("Attempt succeeded", baseAttemptAttributes);
        }
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void attemptCancelled() {
        Map<String, AttributeValue> baseAttemptAttributes = baseAttemptAttributes();
        if (this.operationType == ApiTracerFactory.OperationType.LongRunning) {
            this.span.addAnnotation("Polling was cancelled", baseAttemptAttributes);
        } else {
            this.span.addAnnotation("Attempt cancelled", baseAttemptAttributes);
        }
        this.lastConnectionId = null;
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void attemptFailed(Throwable th, Duration duration) {
        Map<String, AttributeValue> baseAttemptAttributes = baseAttemptAttributes();
        baseAttemptAttributes.put("delay ms", AttributeValue.longAttributeValue(duration.toMillis()));
        populateError(baseAttemptAttributes, th);
        if (this.operationType == ApiTracerFactory.OperationType.LongRunning) {
            this.span.addAnnotation("Scheduling next poll", baseAttemptAttributes);
        } else {
            this.span.addAnnotation("Attempt failed, scheduling next attempt", baseAttemptAttributes);
        }
        this.lastConnectionId = null;
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void attemptFailedRetriesExhausted(Throwable th) {
        Map<String, AttributeValue> baseAttemptAttributes = baseAttemptAttributes();
        populateError(baseAttemptAttributes, th);
        if (this.operationType == ApiTracerFactory.OperationType.LongRunning) {
            this.span.addAnnotation("Polling attempts exhausted", baseAttemptAttributes);
        } else {
            this.span.addAnnotation("Attempts exhausted", baseAttemptAttributes);
        }
        this.lastConnectionId = null;
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void attemptPermanentFailure(Throwable th) {
        Map<String, AttributeValue> baseAttemptAttributes = baseAttemptAttributes();
        populateError(baseAttemptAttributes, th);
        if (this.operationType == ApiTracerFactory.OperationType.LongRunning) {
            this.span.addAnnotation("Polling failed", baseAttemptAttributes);
        } else {
            this.span.addAnnotation("Attempt failed, error not retryable", baseAttemptAttributes);
        }
        this.lastConnectionId = null;
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void responseReceived() {
        this.attemptReceivedMessages++;
        this.totalReceivedMessages++;
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void requestSent() {
        this.attemptSentMessages.incrementAndGet();
        this.totalSentMessages.incrementAndGet();
    }

    @Override // com.google.api.gax.tracing.ApiTracer
    public void batchRequestSent(long j, long j2) {
        this.span.putAttribute("batch count", AttributeValue.longAttributeValue(j));
        this.span.putAttribute("batch size", AttributeValue.longAttributeValue(j2));
    }

    private Map<String, AttributeValue> baseOperationAttributes() {
        HashMap hashMap = new HashMap();
        hashMap.put("attempt count", AttributeValue.longAttributeValue(this.currentAttemptId + 1));
        long j = this.totalSentMessages.get();
        if (j > 0) {
            hashMap.put("total request count", AttributeValue.longAttributeValue(j));
        }
        long j2 = this.totalReceivedMessages;
        if (j2 > 0) {
            hashMap.put("total response count", AttributeValue.longAttributeValue(j2));
        }
        return hashMap;
    }

    private Map<String, AttributeValue> baseAttemptAttributes() {
        HashMap hashMap = new HashMap();
        populateAttemptNumber(hashMap);
        long j = this.attemptSentMessages.get();
        if (j > 0) {
            hashMap.put("attempt request count", AttributeValue.longAttributeValue(j));
        }
        long j2 = this.attemptReceivedMessages;
        if (j2 > 0) {
            hashMap.put("attempt response count", AttributeValue.longAttributeValue(j2));
        }
        String str = this.lastConnectionId;
        if (str != null) {
            hashMap.put("connection", AttributeValue.stringAttributeValue(str));
        }
        return hashMap;
    }

    private void populateAttemptNumber(Map<String, AttributeValue> map) {
        map.put("attempt", AttributeValue.longAttributeValue(this.currentAttemptId));
    }

    private void populateError(Map<String, AttributeValue> map, Throwable th) {
        if (th == null) {
            map.put("status", AttributeValue.stringAttributeValue("OK"));
            return;
        }
        Status convertErrorToStatus = convertErrorToStatus(th);
        map.put("status", AttributeValue.stringAttributeValue(convertErrorToStatus.getCanonicalCode().toString()));
        if (Strings.isNullOrEmpty(convertErrorToStatus.getDescription())) {
            return;
        }
        map.put("status message", AttributeValue.stringAttributeValue(convertErrorToStatus.getDescription()));
    }

    @InternalApi("Visible for testing")
    static Status convertErrorToStatus(Throwable th) {
        Status.CanonicalCode canonicalCode;
        StatusCode.Code code = StatusCode.Code.UNKNOWN;
        if (th instanceof ApiException) {
            code = ((ApiException) th).getStatusCode().getCode();
        } else if (th.getCause() instanceof ApiException) {
            code = ((ApiException) th.getCause()).getStatusCode().getCode();
        }
        try {
            canonicalCode = Status.CanonicalCode.valueOf(code.name());
        } catch (IllegalArgumentException unused) {
            canonicalCode = Status.CanonicalCode.UNKNOWN;
        }
        return canonicalCode.toStatus().withDescription(th.getMessage());
    }
}
