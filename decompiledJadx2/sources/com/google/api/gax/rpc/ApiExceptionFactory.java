package com.google.api.gax.rpc;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class ApiExceptionFactory {
    private ApiExceptionFactory() {
    }

    public static ApiException createException(Throwable th, StatusCode statusCode, boolean z) {
        switch (statusCode.getCode()) {
            case CANCELLED:
                return new CancelledException(th, statusCode, z);
            case NOT_FOUND:
                return new NotFoundException(th, statusCode, z);
            case UNKNOWN:
                return new UnknownException(th, statusCode, z);
            case INVALID_ARGUMENT:
                return new InvalidArgumentException(th, statusCode, z);
            case DEADLINE_EXCEEDED:
                return new DeadlineExceededException(th, statusCode, z);
            case ALREADY_EXISTS:
                return new AlreadyExistsException(th, statusCode, z);
            case PERMISSION_DENIED:
                return new PermissionDeniedException(th, statusCode, z);
            case RESOURCE_EXHAUSTED:
                return new ResourceExhaustedException(th, statusCode, z);
            case FAILED_PRECONDITION:
                return new FailedPreconditionException(th, statusCode, z);
            case ABORTED:
                return new AbortedException(th, statusCode, z);
            case OUT_OF_RANGE:
                return new OutOfRangeException(th, statusCode, z);
            case UNIMPLEMENTED:
                return new UnimplementedException(th, statusCode, z);
            case INTERNAL:
                return new InternalException(th, statusCode, z);
            case UNAVAILABLE:
                return new UnavailableException(th, statusCode, z);
            case DATA_LOSS:
                return new DataLossException(th, statusCode, z);
            case UNAUTHENTICATED:
                return new UnauthenticatedException(th, statusCode, z);
            default:
                return new UnknownException(th, statusCode, z);
        }
    }

    public static ApiException createException(String str, Throwable th, StatusCode statusCode, boolean z) {
        switch (statusCode.getCode()) {
            case CANCELLED:
                return new CancelledException(str, th, statusCode, z);
            case NOT_FOUND:
                return new NotFoundException(str, th, statusCode, z);
            case UNKNOWN:
                return new UnknownException(str, th, statusCode, z);
            case INVALID_ARGUMENT:
                return new InvalidArgumentException(str, th, statusCode, z);
            case DEADLINE_EXCEEDED:
                return new DeadlineExceededException(str, th, statusCode, z);
            case ALREADY_EXISTS:
                return new AlreadyExistsException(str, th, statusCode, z);
            case PERMISSION_DENIED:
                return new PermissionDeniedException(str, th, statusCode, z);
            case RESOURCE_EXHAUSTED:
                return new ResourceExhaustedException(str, th, statusCode, z);
            case FAILED_PRECONDITION:
                return new FailedPreconditionException(str, th, statusCode, z);
            case ABORTED:
                return new AbortedException(str, th, statusCode, z);
            case OUT_OF_RANGE:
                return new OutOfRangeException(str, th, statusCode, z);
            case UNIMPLEMENTED:
                return new UnimplementedException(str, th, statusCode, z);
            case INTERNAL:
                return new InternalException(str, th, statusCode, z);
            case UNAVAILABLE:
                return new UnavailableException(str, th, statusCode, z);
            case DATA_LOSS:
                return new DataLossException(str, th, statusCode, z);
            case UNAUTHENTICATED:
                return new UnauthenticatedException(str, th, statusCode, z);
            default:
                return new UnknownException(th, statusCode, z);
        }
    }
}
