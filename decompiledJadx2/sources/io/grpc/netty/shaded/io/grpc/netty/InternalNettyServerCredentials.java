package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.ServerCredentials;
import io.grpc.internal.ObjectPool;
import io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiator;
import io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiators;
import java.util.concurrent.Executor;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InternalNettyServerCredentials {
    private InternalNettyServerCredentials() {
    }

    public static ServerCredentials create(InternalProtocolNegotiator.ProtocolNegotiator protocolNegotiator) {
        return NettyServerCredentials.create(ProtocolNegotiators.fixedServerFactory(protocolNegotiator));
    }

    public static ServerCredentials create(InternalProtocolNegotiator.ServerFactory serverFactory) {
        return NettyServerCredentials.create(serverFactory);
    }

    public static InternalProtocolNegotiator.ServerFactory toNegotiator(ServerCredentials serverCredentials) {
        final ProtocolNegotiators.FromServerCredentialsResult from = ProtocolNegotiators.from(serverCredentials);
        if (from.error != null) {
            throw new IllegalArgumentException(from.error);
        }
        return new InternalProtocolNegotiator.ServerFactory() { // from class: io.grpc.netty.shaded.io.grpc.netty.InternalNettyServerCredentials.1ServerFactory
            @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator.ServerFactory
            public /* bridge */ /* synthetic */ ProtocolNegotiator newNegotiator(ObjectPool objectPool) {
                return newNegotiator((ObjectPool<? extends Executor>) objectPool);
            }

            @Override // io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiator.ServerFactory, io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator.ServerFactory
            public InternalProtocolNegotiator.ProtocolNegotiator newNegotiator(ObjectPool<? extends Executor> objectPool) {
                return new InternalProtocolNegotiator.ProtocolNegotiatorAdapter(ProtocolNegotiators.FromServerCredentialsResult.this.negotiator.newNegotiator(objectPool));
            }
        };
    }
}
