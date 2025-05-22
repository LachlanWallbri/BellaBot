package com.google.api.gax.rpc;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class InvalidArgumentException extends ApiException {
    public InvalidArgumentException(Throwable th, StatusCode statusCode, boolean z) {
        super(th, statusCode, z);
    }

    public InvalidArgumentException(String str, Throwable th, StatusCode statusCode, boolean z) {
        super(str, th, statusCode, z);
    }
}
