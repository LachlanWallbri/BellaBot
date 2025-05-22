package io.grpc.internal;

import com.google.common.base.Objects;
import io.grpc.Attributes;
import io.grpc.MethodDescriptor;
import io.grpc.ServerStreamTracer;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class ServerCallInfoImpl<ReqT, RespT> extends ServerStreamTracer.ServerCallInfo<ReqT, RespT> {
    private final Attributes attributes;
    private final String authority;
    private final MethodDescriptor<ReqT, RespT> methodDescriptor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServerCallInfoImpl(MethodDescriptor<ReqT, RespT> methodDescriptor, Attributes attributes, @Nullable String str) {
        this.methodDescriptor = methodDescriptor;
        this.attributes = attributes;
        this.authority = str;
    }

    @Override // io.grpc.ServerStreamTracer.ServerCallInfo
    public MethodDescriptor<ReqT, RespT> getMethodDescriptor() {
        return this.methodDescriptor;
    }

    @Override // io.grpc.ServerStreamTracer.ServerCallInfo
    public Attributes getAttributes() {
        return this.attributes;
    }

    @Override // io.grpc.ServerStreamTracer.ServerCallInfo
    @Nullable
    public String getAuthority() {
        return this.authority;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ServerCallInfoImpl)) {
            return false;
        }
        ServerCallInfoImpl serverCallInfoImpl = (ServerCallInfoImpl) obj;
        return Objects.equal(this.methodDescriptor, serverCallInfoImpl.methodDescriptor) && Objects.equal(this.attributes, serverCallInfoImpl.attributes) && Objects.equal(this.authority, serverCallInfoImpl.authority);
    }

    public int hashCode() {
        return Objects.hashCode(this.methodDescriptor, this.attributes, this.authority);
    }
}
