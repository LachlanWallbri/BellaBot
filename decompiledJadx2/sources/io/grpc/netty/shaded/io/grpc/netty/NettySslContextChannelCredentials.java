package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import io.grpc.ChannelCredentials;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class NettySslContextChannelCredentials {
    private NettySslContextChannelCredentials() {
    }

    public static ChannelCredentials create(SslContext sslContext) {
        Preconditions.checkArgument(sslContext.isClient(), "Server SSL context can not be used for client channel");
        GrpcSslContexts.ensureAlpnAndH2Enabled(sslContext.applicationProtocolNegotiator());
        return NettyChannelCredentials.create(ProtocolNegotiators.tlsClientFactory(sslContext));
    }
}
