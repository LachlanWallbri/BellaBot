package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import io.grpc.internal.ObjectPool;
import io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import java.util.concurrent.Executor;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InternalProtocolNegotiator {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface ClientFactory extends ProtocolNegotiator.ClientFactory {
        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator.ClientFactory
        ProtocolNegotiator newNegotiator();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface ProtocolNegotiator extends io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface ServerFactory extends ProtocolNegotiator.ServerFactory {
        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator.ServerFactory
        ProtocolNegotiator newNegotiator(ObjectPool<? extends Executor> objectPool);
    }

    private InternalProtocolNegotiator() {
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static final class ProtocolNegotiatorAdapter implements ProtocolNegotiator {
        private final io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator negotiator;

        public ProtocolNegotiatorAdapter(io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator protocolNegotiator) {
            this.negotiator = (io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator) Preconditions.checkNotNull(protocolNegotiator, "negotiator");
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
        public AsciiString scheme() {
            return this.negotiator.scheme();
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
        public ChannelHandler newHandler(GrpcHttp2ConnectionHandler grpcHttp2ConnectionHandler) {
            return this.negotiator.newHandler(grpcHttp2ConnectionHandler);
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
        public void close() {
            this.negotiator.close();
        }
    }
}
