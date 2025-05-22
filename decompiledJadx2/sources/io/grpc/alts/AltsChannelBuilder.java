package io.grpc.alts;

import io.grpc.ForwardingChannelBuilder;
import io.grpc.ManagedChannel;
import io.grpc.alts.AltsChannelCredentials;
import io.grpc.internal.GrpcUtil;
import io.grpc.netty.shaded.io.grpc.netty.InternalNettyChannelBuilder;
import io.grpc.netty.shaded.io.grpc.netty.InternalProtocolNegotiator;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AltsChannelBuilder extends ForwardingChannelBuilder<AltsChannelBuilder> {
    private final AltsChannelCredentials.Builder credentialsBuilder = new AltsChannelCredentials.Builder();
    private final NettyChannelBuilder delegate;

    public static final AltsChannelBuilder forTarget(String str) {
        return new AltsChannelBuilder(str);
    }

    public static AltsChannelBuilder forAddress(String str, int i) {
        return forTarget(GrpcUtil.authorityFromHostAndPort(str, i));
    }

    private AltsChannelBuilder(String str) {
        this.delegate = NettyChannelBuilder.forTarget(str);
    }

    public AltsChannelBuilder addTargetServiceAccount(String str) {
        this.credentialsBuilder.addTargetServiceAccount(str);
        return this;
    }

    public AltsChannelBuilder enableUntrustedAltsForTesting() {
        this.credentialsBuilder.enableUntrustedAltsForTesting();
        return this;
    }

    public AltsChannelBuilder setHandshakerAddressForTesting(String str) {
        this.credentialsBuilder.setHandshakerAddressForTesting(str);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.ForwardingChannelBuilder
    public NettyChannelBuilder delegate() {
        return this.delegate;
    }

    @Override // io.grpc.ForwardingChannelBuilder, io.grpc.ManagedChannelBuilder
    public ManagedChannel build() {
        InternalNettyChannelBuilder.setProtocolNegotiatorFactory(delegate(), this.credentialsBuilder.buildProtocolNegotiatorFactory());
        return delegate().build();
    }

    @Nullable
    InternalProtocolNegotiator.ProtocolNegotiator getProtocolNegotiatorForTest() {
        return this.credentialsBuilder.buildProtocolNegotiatorFactory().newNegotiator();
    }
}
