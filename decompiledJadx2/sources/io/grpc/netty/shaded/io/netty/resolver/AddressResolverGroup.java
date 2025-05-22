package io.grpc.netty.shaded.io.netty.resolver;

import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.FutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.Closeable;
import java.net.SocketAddress;
import java.util.IdentityHashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AddressResolverGroup<T extends SocketAddress> implements Closeable {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AddressResolverGroup.class);
    private final Map<EventExecutor, AddressResolver<T>> resolvers = new IdentityHashMap();
    private final Map<EventExecutor, GenericFutureListener<Future<Object>>> executorTerminationListeners = new IdentityHashMap();

    protected abstract AddressResolver<T> newResolver(EventExecutor eventExecutor) throws Exception;

    public AddressResolver<T> getResolver(final EventExecutor eventExecutor) {
        final AddressResolver<T> addressResolver;
        ObjectUtil.checkNotNull(eventExecutor, "executor");
        if (eventExecutor.isShuttingDown()) {
            throw new IllegalStateException("executor not accepting a task");
        }
        synchronized (this.resolvers) {
            addressResolver = this.resolvers.get(eventExecutor);
            if (addressResolver == null) {
                try {
                    addressResolver = newResolver(eventExecutor);
                    this.resolvers.put(eventExecutor, addressResolver);
                    FutureListener<Object> futureListener = new FutureListener<Object>() { // from class: io.grpc.netty.shaded.io.netty.resolver.AddressResolverGroup.1
                        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                        public void operationComplete(Future<Object> future) {
                            synchronized (AddressResolverGroup.this.resolvers) {
                                AddressResolverGroup.this.resolvers.remove(eventExecutor);
                                AddressResolverGroup.this.executorTerminationListeners.remove(eventExecutor);
                            }
                            addressResolver.close();
                        }
                    };
                    this.executorTerminationListeners.put(eventExecutor, futureListener);
                    eventExecutor.terminationFuture().addListener(futureListener);
                } catch (Exception e) {
                    throw new IllegalStateException("failed to create a new resolver", e);
                }
            }
        }
        return addressResolver;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        int i;
        AddressResolver[] addressResolverArr;
        Map.Entry[] entryArr;
        synchronized (this.resolvers) {
            addressResolverArr = (AddressResolver[]) this.resolvers.values().toArray(new AddressResolver[0]);
            this.resolvers.clear();
            entryArr = (Map.Entry[]) this.executorTerminationListeners.entrySet().toArray(new Map.Entry[0]);
            this.executorTerminationListeners.clear();
        }
        for (Map.Entry entry : entryArr) {
            ((EventExecutor) entry.getKey()).terminationFuture().removeListener((GenericFutureListener) entry.getValue());
        }
        for (AddressResolver addressResolver : addressResolverArr) {
            try {
                addressResolver.close();
            } catch (Throwable th) {
                logger.warn("Failed to close a resolver:", th);
            }
        }
    }
}
