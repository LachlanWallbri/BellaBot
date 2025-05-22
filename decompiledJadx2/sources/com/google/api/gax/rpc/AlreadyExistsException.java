package com.google.api.gax.rpc;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class AlreadyExistsException extends ApiException {
    public AlreadyExistsException(Throwable th, StatusCode statusCode, boolean z) {
        super(th, statusCode, z);
    }

    public AlreadyExistsException(String str, Throwable th, StatusCode statusCode, boolean z) {
        super(str, th, statusCode, z);
    }
}
