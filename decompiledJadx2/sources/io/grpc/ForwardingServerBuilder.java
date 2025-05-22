package io.grpc;

import com.google.common.base.MoreObjects;
import io.grpc.ServerBuilder;
import io.grpc.ServerStreamTracer;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ForwardingServerBuilder<T extends ServerBuilder<T>> extends ServerBuilder<T> {
    private T thisT() {
        return this;
    }

    protected abstract ServerBuilder<?> delegate();

    public static ServerBuilder<?> forPort(int i) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    @Override // io.grpc.ServerBuilder
    public T directExecutor() {
        delegate().directExecutor();
        return thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T executor(@Nullable Executor executor) {
        delegate().executor(executor);
        return thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T addService(ServerServiceDefinition serverServiceDefinition) {
        delegate().addService(serverServiceDefinition);
        return thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T addService(BindableService bindableService) {
        delegate().addService(bindableService);
        return thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T intercept(ServerInterceptor serverInterceptor) {
        delegate().intercept(serverInterceptor);
        return thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T addTransportFilter(ServerTransportFilter serverTransportFilter) {
        delegate().addTransportFilter(serverTransportFilter);
        return thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T addStreamTracerFactory(ServerStreamTracer.Factory factory) {
        delegate().addStreamTracerFactory(factory);
        return thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T fallbackHandlerRegistry(@Nullable HandlerRegistry handlerRegistry) {
        delegate().fallbackHandlerRegistry(handlerRegistry);
        return thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T useTransportSecurity(File file, File file2) {
        delegate().useTransportSecurity(file, file2);
        return thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T useTransportSecurity(InputStream inputStream, InputStream inputStream2) {
        delegate().useTransportSecurity(inputStream, inputStream2);
        return thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T decompressorRegistry(@Nullable DecompressorRegistry decompressorRegistry) {
        delegate().decompressorRegistry(decompressorRegistry);
        return thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T compressorRegistry(@Nullable CompressorRegistry compressorRegistry) {
        delegate().compressorRegistry(compressorRegistry);
        return thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T handshakeTimeout(long j, TimeUnit timeUnit) {
        delegate().handshakeTimeout(j, timeUnit);
        return thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T maxInboundMessageSize(int i) {
        delegate().maxInboundMessageSize(i);
        return thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T maxInboundMetadataSize(int i) {
        delegate().maxInboundMetadataSize(i);
        return thisT();
    }

    @Override // io.grpc.ServerBuilder
    public T setBinaryLog(BinaryLog binaryLog) {
        delegate().setBinaryLog(binaryLog);
        return thisT();
    }

    @Override // io.grpc.ServerBuilder
    public Server build() {
        return delegate().build();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
