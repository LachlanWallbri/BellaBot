package com.google.api.gax.grpc;

import io.grpc.Status;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class AutoValue_GrpcStatusCode extends GrpcStatusCode {
    private final Status.Code transportCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_GrpcStatusCode(Status.Code code) {
        if (code == null) {
            throw new NullPointerException("Null transportCode");
        }
        this.transportCode = code;
    }

    @Override // com.google.api.gax.grpc.GrpcStatusCode, com.google.api.gax.rpc.StatusCode
    public Status.Code getTransportCode() {
        return this.transportCode;
    }

    public String toString() {
        return "GrpcStatusCode{transportCode=" + this.transportCode + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GrpcStatusCode) {
            return this.transportCode.equals(((GrpcStatusCode) obj).getTransportCode());
        }
        return false;
    }

    public int hashCode() {
        return this.transportCode.hashCode() ^ 1000003;
    }
}
