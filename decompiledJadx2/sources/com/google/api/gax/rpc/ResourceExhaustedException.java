package com.google.api.gax.rpc;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class ResourceExhaustedException extends ApiException {
    public ResourceExhaustedException(Throwable th, StatusCode statusCode, boolean z) {
        super(th, statusCode, z);
    }

    public ResourceExhaustedException(String str, Throwable th, StatusCode statusCode, boolean z) {
        super(str, th, statusCode, z);
    }
}
