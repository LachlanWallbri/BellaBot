package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.SharedResourcePool;
import io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiator;
import io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator;
import io.grpc.netty.shaded.io.netty.channel.socket.nio.NioSocketChannel;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InternalNettyChannelBuilder {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface ProtocolNegotiatorFactory {
        InternalProtocolNegotiator.ProtocolNegotiator buildProtocolNegotiator();
    }

    public static void disableCheckAuthority(NettyChannelBuilder nettyChannelBuilder) {
        nettyChannelBuilder.disableCheckAuthority();
    }

    public static void enableCheckAuthority(NettyChannelBuilder nettyChannelBuilder) {
        nettyChannelBuilder.enableCheckAuthority();
    }

    public static void setProtocolNegotiatorFactory(NettyChannelBuilder nettyChannelBuilder, final ProtocolNegotiatorFactory protocolNegotiatorFactory) {
        nettyChannelBuilder.protocolNegotiatorFactory(new ProtocolNegotiator.ClientFactory() { // from class: io.grpc.netty.shaded.io.grpc.netty.InternalNettyChannelBuilder.1
            @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator.ClientFactory
            public int getDefaultPort() {
                return 443;
            }

            @Override // io.grpc.netty.shaded.io.grpc.netty.ProtocolNegotiator.ClientFactory
            public ProtocolNegotiator newNegotiator() {
                return ProtocolNegotiatorFactory.this.buildProtocolNegotiator();
            }
        });
    }

    public static void setProtocolNegotiatorFactory(NettyChannelBuilder nettyChannelBuilder, InternalProtocolNegotiator.ClientFactory clientFactory) {
        nettyChannelBuilder.protocolNegotiatorFactory(clientFactory);
    }

    public static void setStatsEnabled(NettyChannelBuilder nettyChannelBuilder, boolean z) {
        nettyChannelBuilder.setStatsEnabled(z);
    }

    public static void setTracingEnabled(NettyChannelBuilder nettyChannelBuilder, boolean z) {
        nettyChannelBuilder.setTracingEnabled(z);
    }

    public static void setStatsRecordStartedRpcs(NettyChannelBuilder nettyChannelBuilder, boolean z) {
        nettyChannelBuilder.setStatsRecordStartedRpcs(z);
    }

    public static void setStatsRecordFinishedRpcs(NettyChannelBuilder nettyChannelBuilder, boolean z) {
        nettyChannelBuilder.setStatsRecordFinishedRpcs(z);
    }

    public static void setStatsRecordRealTimeMetrics(NettyChannelBuilder nettyChannelBuilder, boolean z) {
        nettyChannelBuilder.setStatsRecordRealTimeMetrics(z);
    }

    public static void useNioTransport(NettyChannelBuilder nettyChannelBuilder) {
        nettyChannelBuilder.channelType(NioSocketChannel.class);
        nettyChannelBuilder.eventLoopGroupPool(SharedResourcePool.forResource(Utils.NIO_WORKER_EVENT_LOOP_GROUP));
    }

    public static ClientTransportFactory buildTransportFactory(NettyChannelBuilder nettyChannelBuilder) {
        return nettyChannelBuilder.buildTransportFactory();
    }

    private InternalNettyChannelBuilder() {
    }
}
