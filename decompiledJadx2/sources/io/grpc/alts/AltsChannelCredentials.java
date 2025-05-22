package io.grpc.alts;

import com.google.common.collect.ImmutableList;
import io.grpc.Channel;
import io.grpc.ChannelCredentials;
import io.grpc.Status;
import io.grpc.alts.internal.AltsProtocolNegotiator;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.SharedResourcePool;
import io.grpc.netty.shaded.io.grpc.netty.GrpcHttp2ConnectionHandler;
import io.grpc.netty.shaded.io.grpc.netty.InternalNettyChannelCredentials;
import io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiator;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.util.AsciiString;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AltsChannelCredentials {
    private static final Logger logger = Logger.getLogger(AltsChannelCredentials.class.getName());
    private static final AsciiString SCHEME = AsciiString.m3920of("https");

    private AltsChannelCredentials() {
    }

    public static ChannelCredentials create() {
        return newBuilder().build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Builder {
        private boolean enableUntrustedAlts;
        private final ImmutableList.Builder<String> targetServiceAccountsBuilder = ImmutableList.builder();
        private ObjectPool<Channel> handshakerChannelPool = SharedResourcePool.forResource(HandshakerServiceChannel.SHARED_HANDSHAKER_CHANNEL);

        public Builder addTargetServiceAccount(String str) {
            this.targetServiceAccountsBuilder.add((ImmutableList.Builder<String>) str);
            return this;
        }

        public Builder enableUntrustedAltsForTesting() {
            this.enableUntrustedAlts = true;
            return this;
        }

        public Builder setHandshakerAddressForTesting(String str) {
            this.handshakerChannelPool = SharedResourcePool.forResource(HandshakerServiceChannel.getHandshakerChannelForTesting(str));
            return this;
        }

        public ChannelCredentials build() {
            return InternalNettyChannelCredentials.create(buildProtocolNegotiatorFactory());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public InternalProtocolNegotiator.ClientFactory buildProtocolNegotiatorFactory() {
            if (!InternalCheckGcpEnvironment.isOnGcp()) {
                if (this.enableUntrustedAlts) {
                    AltsChannelCredentials.logger.log(Level.WARNING, "Untrusted ALTS mode is enabled and we cannot guarantee the trustworthiness of the ALTS handshaker service");
                } else {
                    return new FailingProtocolNegotiatorFactory(Status.INTERNAL.withDescription("ALTS is only allowed to run on Google Cloud Platform"));
                }
            }
            return new AltsProtocolNegotiator.ClientAltsProtocolNegotiatorFactory(this.targetServiceAccountsBuilder.build(), this.handshakerChannelPool);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class FailingProtocolNegotiatorFactory implements InternalProtocolNegotiator.ClientFactory {
        private final Status status;

        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator.ClientFactory
        public int getDefaultPort() {
            return 443;
        }

        public FailingProtocolNegotiatorFactory(Status status) {
            this.status = status;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator.ClientFactory
        public InternalProtocolNegotiator.ProtocolNegotiator newNegotiator() {
            return new FailingProtocolNegotiator(this.status);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class FailingProtocolNegotiator implements InternalProtocolNegotiator.ProtocolNegotiator {
        private final Status status;

        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
        public void close() {
        }

        public FailingProtocolNegotiator(Status status) {
            this.status = status;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
        public AsciiString scheme() {
            return AltsChannelCredentials.SCHEME;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator
        public ChannelHandler newHandler(GrpcHttp2ConnectionHandler grpcHttp2ConnectionHandler) {
            return new ChannelHandlerAdapter() { // from class: io.grpc.alts.AltsChannelCredentials.FailingProtocolNegotiator.1
                @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
                public void handlerAdded(ChannelHandlerContext channelHandlerContext) {
                    channelHandlerContext.fireExceptionCaught((Throwable) FailingProtocolNegotiator.this.status.asRuntimeException());
                }
            };
        }
    }
}
