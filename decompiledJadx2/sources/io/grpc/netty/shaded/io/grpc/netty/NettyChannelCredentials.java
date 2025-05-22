package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import io.grpc.ChannelCredentials;
import io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class NettyChannelCredentials extends ChannelCredentials {
    private final ProtocolNegotiator.ClientFactory negotiator;

    @Override // io.grpc.ChannelCredentials
    public ChannelCredentials withoutBearerTokens() {
        return this;
    }

    public static ChannelCredentials create(ProtocolNegotiator.ClientFactory clientFactory) {
        return new NettyChannelCredentials(clientFactory);
    }

    private NettyChannelCredentials(ProtocolNegotiator.ClientFactory clientFactory) {
        this.negotiator = (ProtocolNegotiator.ClientFactory) Preconditions.checkNotNull(clientFactory, "negotiator");
    }

    public ProtocolNegotiator.ClientFactory getNegotiator() {
        return this.negotiator;
    }
}
