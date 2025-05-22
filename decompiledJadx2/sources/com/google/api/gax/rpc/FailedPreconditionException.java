package com.google.api.gax.rpc;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class FailedPreconditionException extends ApiException {
    public FailedPreconditionException(Throwable th, StatusCode statusCode, boolean z) {
        super(th, statusCode, z);
    }

    public FailedPreconditionException(String str, Throwable th, StatusCode statusCode, boolean z) {
        super(str, th, statusCode, z);
    }
}
