package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import io.grpc.ServerCredentials;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class NettySslContextServerCredentials {
    private NettySslContextServerCredentials() {
    }

    public static ServerCredentials create(SslContext sslContext) {
        Preconditions.checkArgument(sslContext.isServer(), "Client SSL context can not be used for server");
        GrpcSslContexts.ensureAlpnAndH2Enabled(sslContext.applicationProtocolNegotiator());
        return NettyServerCredentials.create(ProtocolNegotiators.serverTlsFactory(sslContext));
    }
}
