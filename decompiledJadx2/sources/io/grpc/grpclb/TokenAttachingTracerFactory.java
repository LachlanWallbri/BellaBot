package io.grpc.grpclb;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.ClientStreamTracer;
import io.grpc.Metadata;
import io.grpc.internal.GrpcAttributes;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class TokenAttachingTracerFactory extends ClientStreamTracer.Factory {
    private static final ClientStreamTracer NOOP_TRACER = new ClientStreamTracer() { // from class: io.grpc.grpclb.TokenAttachingTracerFactory.1
    };

    @Nullable
    private final ClientStreamTracer.Factory delegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TokenAttachingTracerFactory(@Nullable ClientStreamTracer.Factory factory) {
        this.delegate = factory;
    }

    @Override // io.grpc.ClientStreamTracer.Factory
    public ClientStreamTracer newClientStreamTracer(ClientStreamTracer.StreamInfo streamInfo, Metadata metadata) {
        String str = (String) ((Attributes) Preconditions.checkNotNull(((Attributes) Preconditions.checkNotNull(streamInfo.getTransportAttrs(), "transportAttrs")).get(GrpcAttributes.ATTR_CLIENT_EAG_ATTRS), "eagAttrs")).get(GrpclbConstants.TOKEN_ATTRIBUTE_KEY);
        metadata.discardAll(GrpclbConstants.TOKEN_METADATA_KEY);
        if (str != null) {
            metadata.put(GrpclbConstants.TOKEN_METADATA_KEY, str);
        }
        ClientStreamTracer.Factory factory = this.delegate;
        if (factory != null) {
            return factory.newClientStreamTracer(streamInfo, metadata);
        }
        return NOOP_TRACER;
    }

    public int hashCode() {
        return Objects.hashCode(this.delegate);
    }

    public boolean equals(Object obj) {
        if (obj instanceof TokenAttachingTracerFactory) {
            return Objects.equal(this.delegate, ((TokenAttachingTracerFactory) obj).delegate);
        }
        return false;
    }
}
