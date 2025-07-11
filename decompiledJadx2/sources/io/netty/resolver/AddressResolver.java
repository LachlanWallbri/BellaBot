package io.netty.resolver;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.Promise;
import java.io.Closeable;
import java.net.SocketAddress;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface AddressResolver<T extends SocketAddress> extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    boolean isResolved(SocketAddress socketAddress);

    boolean isSupported(SocketAddress socketAddress);

    Future<T> resolve(SocketAddress socketAddress);

    Future<T> resolve(SocketAddress socketAddress, Promise<T> promise);

    Future<List<T>> resolveAll(SocketAddress socketAddress);

    Future<List<T>> resolveAll(SocketAddress socketAddress, Promise<List<T>> promise);
}
