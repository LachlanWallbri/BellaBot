package com.google.api.gax.rpc;

import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = -4375114339928877996L;
    private final boolean retryable;
    private final StatusCode statusCode;

    public ApiException(Throwable th, StatusCode statusCode, boolean z) {
        super(th);
        this.statusCode = (StatusCode) Preconditions.checkNotNull(statusCode);
        this.retryable = z;
    }

    public ApiException(String str, Throwable th, StatusCode statusCode, boolean z) {
        super(str, th);
        this.statusCode = (StatusCode) Preconditions.checkNotNull(statusCode);
        this.retryable = z;
    }

    public boolean isRetryable() {
        return this.retryable;
    }

    public StatusCode getStatusCode() {
        return this.statusCode;
    }
}
