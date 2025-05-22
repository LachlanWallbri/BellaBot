package com.google.api.gax.grpc;

import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.rpc.StatusCode;
import io.grpc.Status;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalExtensionOnly
/* loaded from: classes2.dex */
public abstract class GrpcStatusCode implements StatusCode {
    @Override // com.google.api.gax.rpc.StatusCode
    public abstract Status.Code getTransportCode();

    /* renamed from: of */
    public static GrpcStatusCode m578of(Status.Code code) {
        return new AutoValue_GrpcStatusCode(code);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static StatusCode.Code grpcCodeToStatusCode(Status.Code code) {
        switch (code) {
            case OK:
                return StatusCode.Code.OK;
            case CANCELLED:
                return StatusCode.Code.CANCELLED;
            case UNKNOWN:
                return StatusCode.Code.UNKNOWN;
            case INVALID_ARGUMENT:
                return StatusCode.Code.INVALID_ARGUMENT;
            case DEADLINE_EXCEEDED:
                return StatusCode.Code.DEADLINE_EXCEEDED;
            case NOT_FOUND:
                return StatusCode.Code.NOT_FOUND;
            case ALREADY_EXISTS:
                return StatusCode.Code.ALREADY_EXISTS;
            case PERMISSION_DENIED:
                return StatusCode.Code.PERMISSION_DENIED;
            case RESOURCE_EXHAUSTED:
                return StatusCode.Code.RESOURCE_EXHAUSTED;
            case FAILED_PRECONDITION:
                return StatusCode.Code.FAILED_PRECONDITION;
            case ABORTED:
                return StatusCode.Code.ABORTED;
            case OUT_OF_RANGE:
                return StatusCode.Code.OUT_OF_RANGE;
            case UNIMPLEMENTED:
                return StatusCode.Code.UNIMPLEMENTED;
            case INTERNAL:
                return StatusCode.Code.INTERNAL;
            case UNAVAILABLE:
                return StatusCode.Code.UNAVAILABLE;
            case DATA_LOSS:
                return StatusCode.Code.DATA_LOSS;
            case UNAUTHENTICATED:
                return StatusCode.Code.UNAUTHENTICATED;
            default:
                throw new IllegalStateException("Unrecognized status code: " + code);
        }
    }

    @Override // com.google.api.gax.rpc.StatusCode
    public StatusCode.Code getCode() {
        return grpcCodeToStatusCode(getTransportCode());
    }
}
