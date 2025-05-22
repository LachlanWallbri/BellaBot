package com.google.api.gax.grpc;

import com.google.api.gax.rpc.ApiException;
import com.google.api.gax.rpc.ApiExceptionFactory;
import com.google.api.gax.rpc.StatusCode;
import com.google.common.collect.ImmutableSet;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class GrpcApiExceptionFactory {
    private final ImmutableSet<StatusCode.Code> retryableCodes;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GrpcApiExceptionFactory(Set<StatusCode.Code> set) {
        this.retryableCodes = ImmutableSet.copyOf((Collection) set);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiException create(Throwable th) {
        if (th instanceof StatusException) {
            return create(th, ((StatusException) th).getStatus().getCode());
        }
        if (th instanceof StatusRuntimeException) {
            return create(th, ((StatusRuntimeException) th).getStatus().getCode());
        }
        if (th instanceof ApiException) {
            return (ApiException) th;
        }
        return ApiExceptionFactory.createException(th, GrpcStatusCode.m578of(Status.Code.UNKNOWN), false);
    }

    private ApiException create(Throwable th, Status.Code code) {
        return ApiExceptionFactory.createException(th, GrpcStatusCode.m578of(code), this.retryableCodes.contains(GrpcStatusCode.grpcCodeToStatusCode(code)));
    }
}
