package io.grpc.netty.shaded.io.netty.resolver;

import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Promise;
import io.grpc.netty.shaded.io.netty.util.internal.SocketUtils;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultNameResolver extends InetNameResolver {
    public DefaultNameResolver(EventExecutor eventExecutor) {
        super(eventExecutor);
    }

    @Override // io.grpc.netty.shaded.io.netty.resolver.SimpleNameResolver
    protected void doResolve(String str, Promise<InetAddress> promise) throws Exception {
        try {
            promise.setSuccess(SocketUtils.addressByName(str));
        } catch (UnknownHostException e) {
            promise.setFailure(e);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.resolver.SimpleNameResolver
    protected void doResolveAll(String str, Promise<List<InetAddress>> promise) throws Exception {
        try {
            promise.setSuccess(Arrays.asList(SocketUtils.allAddressesByName(str)));
        } catch (UnknownHostException e) {
            promise.setFailure(e);
        }
    }
}
