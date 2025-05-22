package io.grpc.alts;

import io.grpc.BindableService;
import io.grpc.CompressorRegistry;
import io.grpc.DecompressorRegistry;
import io.grpc.ForwardingServerBuilder;
import io.grpc.HandlerRegistry;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptor;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServerStreamTracer;
import io.grpc.ServerTransportFilter;
import io.grpc.alts.AltsServerCredentials;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import java.io.File;
import java.net.InetSocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AltsServerBuilder extends ForwardingServerBuilder<AltsServerBuilder> {
    private final AltsServerCredentials.Builder credentialsBuilder = new AltsServerCredentials.Builder();
    private final NettyServerBuilder delegate;

    private AltsServerBuilder(NettyServerBuilder nettyServerBuilder) {
        this.delegate = nettyServerBuilder;
    }

    public static AltsServerBuilder forPort(int i) {
        return new AltsServerBuilder(NettyServerBuilder.forAddress(new InetSocketAddress(i)));
    }

    public AltsServerBuilder enableUntrustedAltsForTesting() {
        this.credentialsBuilder.enableUntrustedAltsForTesting();
        return this;
    }

    public AltsServerBuilder setHandshakerAddressForTesting(String str) {
        this.credentialsBuilder.setHandshakerAddressForTesting(str);
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder
    protected ServerBuilder<?> delegate() {
        return this.delegate;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public AltsServerBuilder handshakeTimeout(long j, TimeUnit timeUnit) {
        this.delegate.handshakeTimeout(j, timeUnit);
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public AltsServerBuilder directExecutor() {
        this.delegate.directExecutor();
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public AltsServerBuilder addStreamTracerFactory(ServerStreamTracer.Factory factory) {
        this.delegate.addStreamTracerFactory(factory);
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public AltsServerBuilder addTransportFilter(ServerTransportFilter serverTransportFilter) {
        this.delegate.addTransportFilter(serverTransportFilter);
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public AltsServerBuilder executor(Executor executor) {
        this.delegate.executor(executor);
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public AltsServerBuilder addService(ServerServiceDefinition serverServiceDefinition) {
        this.delegate.addService(serverServiceDefinition);
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public AltsServerBuilder addService(BindableService bindableService) {
        this.delegate.addService(bindableService);
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public AltsServerBuilder fallbackHandlerRegistry(HandlerRegistry handlerRegistry) {
        this.delegate.fallbackHandlerRegistry(handlerRegistry);
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public AltsServerBuilder useTransportSecurity(File file, File file2) {
        throw new UnsupportedOperationException("Can't set TLS settings for ALTS");
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public AltsServerBuilder decompressorRegistry(DecompressorRegistry decompressorRegistry) {
        this.delegate.decompressorRegistry(decompressorRegistry);
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public AltsServerBuilder compressorRegistry(CompressorRegistry compressorRegistry) {
        this.delegate.compressorRegistry(compressorRegistry);
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public AltsServerBuilder intercept(ServerInterceptor serverInterceptor) {
        this.delegate.intercept(serverInterceptor);
        return this;
    }

    @Override // io.grpc.ForwardingServerBuilder, io.grpc.ServerBuilder
    public Server build() {
        this.delegate.protocolNegotiator(this.credentialsBuilder.buildProtocolNegotiator());
        return this.delegate.build();
    }
}
