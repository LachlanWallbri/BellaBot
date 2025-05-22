package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.ServerCredentials;
import io.grpc.ServerProvider;
import io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiators;
import java.net.InetSocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class NettyServerProvider extends ServerProvider {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.ServerProvider
    public boolean isAvailable() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.ServerProvider
    public int priority() {
        return 5;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.ServerProvider
    public NettyServerBuilder builderForPort(int i) {
        return NettyServerBuilder.forPort(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.ServerProvider
    public ServerProvider.NewServerBuilderResult newServerBuilderForPort(int i, ServerCredentials serverCredentials) {
        ProtocolNegotiators.FromServerCredentialsResult from = ProtocolNegotiators.from(serverCredentials);
        if (from.error != null) {
            return ServerProvider.NewServerBuilderResult.error(from.error);
        }
        return ServerProvider.NewServerBuilderResult.serverBuilder(new NettyServerBuilder(new InetSocketAddress(i), from.negotiator));
    }
}
