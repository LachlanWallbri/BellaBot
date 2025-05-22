package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.Attributes;
import io.grpc.ServerStreamTracer;
import io.grpc.internal.SharedResourcePool;
import io.grpc.internal.TransportTracer;
import io.grpc.netty.shaded.io.netty.channel.socket.nio.NioServerSocketChannel;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InternalNettyServerBuilder {
    public static NettyServer buildTransportServers(NettyServerBuilder nettyServerBuilder, List<? extends ServerStreamTracer.Factory> list) {
        return nettyServerBuilder.buildTransportServers(list);
    }

    public static void setTransportTracerFactory(NettyServerBuilder nettyServerBuilder, TransportTracer.Factory factory) {
        nettyServerBuilder.setTransportTracerFactory(factory);
    }

    public static void setStatsEnabled(NettyServerBuilder nettyServerBuilder, boolean z) {
        nettyServerBuilder.setStatsEnabled(z);
    }

    public static void setStatsRecordStartedRpcs(NettyServerBuilder nettyServerBuilder, boolean z) {
        nettyServerBuilder.setStatsRecordStartedRpcs(z);
    }

    public static void setStatsRecordRealTimeMetrics(NettyServerBuilder nettyServerBuilder, boolean z) {
        nettyServerBuilder.setStatsRecordRealTimeMetrics(z);
    }

    public static void setTracingEnabled(NettyServerBuilder nettyServerBuilder, boolean z) {
        nettyServerBuilder.setTracingEnabled(z);
    }

    public static void setForceHeapBuffer(NettyServerBuilder nettyServerBuilder, boolean z) {
        nettyServerBuilder.setForceHeapBuffer(z);
    }

    public static void useNioTransport(NettyServerBuilder nettyServerBuilder) {
        nettyServerBuilder.channelType(NioServerSocketChannel.class);
        nettyServerBuilder.bossEventLoopGroupPool(SharedResourcePool.forResource(Utils.NIO_BOSS_EVENT_LOOP_GROUP));
        nettyServerBuilder.workerEventLoopGroupPool(SharedResourcePool.forResource(Utils.NIO_WORKER_EVENT_LOOP_GROUP));
    }

    public static void eagAttributes(NettyServerBuilder nettyServerBuilder, Attributes attributes) {
        nettyServerBuilder.eagAttributes(attributes);
    }

    private InternalNettyServerBuilder() {
    }
}
