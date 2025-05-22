package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.ChannelCredentials;
import io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiator;
import io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiators;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InternalNettyChannelCredentials {
    private InternalNettyChannelCredentials() {
    }

    public static ChannelCredentials create(InternalProtocolNegotiator.ClientFactory clientFactory) {
        return NettyChannelCredentials.create(clientFactory);
    }

    public static InternalProtocolNegotiator.ClientFactory toNegotiator(ChannelCredentials channelCredentials) {
        final ProtocolNegotiators.FromChannelCredentialsResult from = ProtocolNegotiators.from(channelCredentials);
        if (from.error != null) {
            throw new IllegalArgumentException(from.error);
        }
        return new InternalProtocolNegotiator.ClientFactory() { // from class: io.grpc.netty.shaded.io.grpc.netty.InternalNettyChannelCredentials.1ClientFactory
            @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator.ClientFactory
            public InternalProtocolNegotiator.ProtocolNegotiator newNegotiator() {
                return new InternalProtocolNegotiator.ProtocolNegotiatorAdapter(ProtocolNegotiators.FromChannelCredentialsResult.this.negotiator.newNegotiator());
            }

            @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator.ClientFactory
            public int getDefaultPort() {
                return ProtocolNegotiators.FromChannelCredentialsResult.this.negotiator.getDefaultPort();
            }
        };
    }
}
