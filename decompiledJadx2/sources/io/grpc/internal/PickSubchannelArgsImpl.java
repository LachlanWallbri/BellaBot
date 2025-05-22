package io.grpc.internal;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.CallOptions;
import io.grpc.LoadBalancer;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class PickSubchannelArgsImpl extends LoadBalancer.PickSubchannelArgs {
    private final CallOptions callOptions;
    private final Metadata headers;
    private final MethodDescriptor<?, ?> method;

    public PickSubchannelArgsImpl(MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions) {
        this.method = (MethodDescriptor) Preconditions.checkNotNull(methodDescriptor, "method");
        this.headers = (Metadata) Preconditions.checkNotNull(metadata, "headers");
        this.callOptions = (CallOptions) Preconditions.checkNotNull(callOptions, "callOptions");
    }

    @Override // io.grpc.LoadBalancer.PickSubchannelArgs
    public Metadata getHeaders() {
        return this.headers;
    }

    @Override // io.grpc.LoadBalancer.PickSubchannelArgs
    public CallOptions getCallOptions() {
        return this.callOptions;
    }

    @Override // io.grpc.LoadBalancer.PickSubchannelArgs
    public MethodDescriptor<?, ?> getMethodDescriptor() {
        return this.method;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PickSubchannelArgsImpl pickSubchannelArgsImpl = (PickSubchannelArgsImpl) obj;
        return Objects.equal(this.callOptions, pickSubchannelArgsImpl.callOptions) && Objects.equal(this.headers, pickSubchannelArgsImpl.headers) && Objects.equal(this.method, pickSubchannelArgsImpl.method);
    }

    public int hashCode() {
        return Objects.hashCode(this.callOptions, this.headers, this.method);
    }

    public final String toString() {
        return "[method=" + this.method + " headers=" + this.headers + " callOptions=" + this.callOptions + "]";
    }
}
