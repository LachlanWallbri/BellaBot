package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.ChannelLogger;
import io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiator;
import io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiators;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.grpc.netty.shaded.io.netty.util.AsciiString;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InternalProtocolNegotiators {
    private InternalProtocolNegotiators() {
    }

    public static InternalProtocolNegotiator.ProtocolNegotiator tls(SslContext sslContext) {
        final ProtocolNegotiator tls = ProtocolNegotiators.tls(sslContext);
        return new InternalProtocolNegotiator.ProtocolNegotiator() { // from class: io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiators.1TlsNegotiator
            @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
            public AsciiString scheme() {
                return ProtocolNegotiator.this.scheme();
            }

            @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
            public ChannelHandler newHandler(GrpcHttp2ConnectionHandler grpcHttp2ConnectionHandler) {
                return ProtocolNegotiator.this.newHandler(grpcHttp2ConnectionHandler);
            }

            @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
            public void close() {
                ProtocolNegotiator.this.close();
            }
        };
    }

    public static InternalProtocolNegotiator.ProtocolNegotiator serverTls(SslContext sslContext) {
        final ProtocolNegotiator serverTls = ProtocolNegotiators.serverTls(sslContext);
        return new InternalProtocolNegotiator.ProtocolNegotiator() { // from class: io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiators.1ServerTlsNegotiator
            @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
            public AsciiString scheme() {
                return ProtocolNegotiator.this.scheme();
            }

            @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
            public ChannelHandler newHandler(GrpcHttp2ConnectionHandler grpcHttp2ConnectionHandler) {
                return ProtocolNegotiator.this.newHandler(grpcHttp2ConnectionHandler);
            }

            @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
            public void close() {
                ProtocolNegotiator.this.close();
            }
        };
    }

    public static InternalProtocolNegotiator.ProtocolNegotiator plaintext() {
        final ProtocolNegotiator plaintext = ProtocolNegotiators.plaintext();
        return new InternalProtocolNegotiator.ProtocolNegotiator() { // from class: io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiators.1PlaintextNegotiator
            @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
            public AsciiString scheme() {
                return ProtocolNegotiator.this.scheme();
            }

            @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
            public ChannelHandler newHandler(GrpcHttp2ConnectionHandler grpcHttp2ConnectionHandler) {
                return ProtocolNegotiator.this.newHandler(grpcHttp2ConnectionHandler);
            }

            @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
            public void close() {
                ProtocolNegotiator.this.close();
            }
        };
    }

    public static InternalProtocolNegotiator.ProtocolNegotiator serverPlaintext() {
        final ProtocolNegotiator serverPlaintext = ProtocolNegotiators.serverPlaintext();
        return new InternalProtocolNegotiator.ProtocolNegotiator() { // from class: io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiators.1ServerPlaintextNegotiator
            @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
            public AsciiString scheme() {
                return ProtocolNegotiator.this.scheme();
            }

            @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
            public ChannelHandler newHandler(GrpcHttp2ConnectionHandler grpcHttp2ConnectionHandler) {
                return ProtocolNegotiator.this.newHandler(grpcHttp2ConnectionHandler);
            }

            @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
            public void close() {
                ProtocolNegotiator.this.close();
            }
        };
    }

    public static ChannelHandler waitUntilActiveHandler(ChannelHandler channelHandler, ChannelLogger channelLogger) {
        return new ProtocolNegotiators.WaitUntilActiveHandler(channelHandler, channelLogger);
    }

    public static ChannelHandler grpcNegotiationHandler(GrpcHttp2ConnectionHandler grpcHttp2ConnectionHandler) {
        return new ProtocolNegotiators.GrpcNegotiationHandler(grpcHttp2ConnectionHandler);
    }

    public static ChannelHandler clientTlsHandler(ChannelHandler channelHandler, SslContext sslContext, String str, ChannelLogger channelLogger) {
        return new ProtocolNegotiators.ClientTlsHandler(channelHandler, sslContext, str, null, channelLogger);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class ProtocolNegotiationHandler extends ProtocolNegotiators.ProtocolNegotiationHandler {
        protected ProtocolNegotiationHandler(ChannelHandler channelHandler, String str, ChannelLogger channelLogger) {
            super(channelHandler, str, channelLogger);
        }

        protected ProtocolNegotiationHandler(ChannelHandler channelHandler, ChannelLogger channelLogger) {
            super(channelHandler, channelLogger);
        }
    }
}
