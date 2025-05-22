package io.grpc.netty.shaded.io.netty.resolver;

import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import java.net.InetSocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class DefaultAddressResolverGroup extends AddressResolverGroup<InetSocketAddress> {
    public static final DefaultAddressResolverGroup INSTANCE = new DefaultAddressResolverGroup();

    private DefaultAddressResolverGroup() {
    }

    @Override // io.grpc.netty.shaded.io.netty.resolver.AddressResolverGroup
    protected AddressResolver<InetSocketAddress> newResolver(EventExecutor eventExecutor) throws Exception {
        return new DefaultNameResolver(eventExecutor).asAddressResolver();
    }
}
