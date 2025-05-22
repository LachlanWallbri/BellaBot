package io.grpc.alts;

import io.grpc.ForwardingChannelBuilder;
import io.grpc.internal.GrpcUtil;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ComputeEngineChannelBuilder extends ForwardingChannelBuilder<ComputeEngineChannelBuilder> {
    private final NettyChannelBuilder delegate;

    private ComputeEngineChannelBuilder(String str) {
        this.delegate = NettyChannelBuilder.forTarget(str, ComputeEngineChannelCredentials.create());
    }

    public static final ComputeEngineChannelBuilder forTarget(String str) {
        return new ComputeEngineChannelBuilder(str);
    }

    public static ComputeEngineChannelBuilder forAddress(String str, int i) {
        return forTarget(GrpcUtil.authorityFromHostAndPort(str, i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.ForwardingChannelBuilder
    public NettyChannelBuilder delegate() {
        return this.delegate;
    }
}
