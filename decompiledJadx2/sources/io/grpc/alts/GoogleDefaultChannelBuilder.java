package io.grpc.alts;

import io.grpc.ForwardingChannelBuilder;
import io.grpc.internal.GrpcUtil;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class GoogleDefaultChannelBuilder extends ForwardingChannelBuilder<GoogleDefaultChannelBuilder> {
    private final NettyChannelBuilder delegate;

    private GoogleDefaultChannelBuilder(String str) {
        this.delegate = NettyChannelBuilder.forTarget(str, GoogleDefaultChannelCredentials.create());
    }

    public static final GoogleDefaultChannelBuilder forTarget(String str) {
        return new GoogleDefaultChannelBuilder(str);
    }

    public static GoogleDefaultChannelBuilder forAddress(String str, int i) {
        return forTarget(GrpcUtil.authorityFromHostAndPort(str, i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.ForwardingChannelBuilder
    public NettyChannelBuilder delegate() {
        return this.delegate;
    }
}
