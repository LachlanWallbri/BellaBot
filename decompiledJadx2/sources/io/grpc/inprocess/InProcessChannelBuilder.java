package io.grpc.inprocess;

import com.google.common.base.Preconditions;
import io.grpc.ChannelCredentials;
import io.grpc.ChannelLogger;
import io.grpc.ManagedChannelBuilder;
import io.grpc.internal.AbstractManagedChannelImplBuilder;
import io.grpc.internal.ClientTransportFactory;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.ManagedChannelImplBuilder;
import io.grpc.internal.SharedResourceHolder;
import java.net.SocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InProcessChannelBuilder extends AbstractManagedChannelImplBuilder<InProcessChannelBuilder> {
    private final ManagedChannelImplBuilder managedChannelImplBuilder;
    private final String name;
    private ScheduledExecutorService scheduledExecutorService;
    private int maxInboundMetadataSize = Integer.MAX_VALUE;
    private boolean transportIncludeStatusCause = false;

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public InProcessChannelBuilder keepAliveTime(long j, TimeUnit timeUnit) {
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public InProcessChannelBuilder keepAliveTimeout(long j, TimeUnit timeUnit) {
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public InProcessChannelBuilder keepAliveWithoutCalls(boolean z) {
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public InProcessChannelBuilder usePlaintext() {
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public InProcessChannelBuilder useTransportSecurity() {
        return this;
    }

    public static InProcessChannelBuilder forName(String str) {
        return new InProcessChannelBuilder(str);
    }

    public static InProcessChannelBuilder forTarget(String str) {
        throw new UnsupportedOperationException("call forName() instead");
    }

    public static InProcessChannelBuilder forAddress(String str, int i) {
        throw new UnsupportedOperationException("call forName() instead");
    }

    private InProcessChannelBuilder(String str) {
        this.name = (String) Preconditions.checkNotNull(str, "name");
        this.managedChannelImplBuilder = new ManagedChannelImplBuilder(new InProcessSocketAddress(str), "localhost", new ManagedChannelImplBuilder.ClientTransportFactoryBuilder() { // from class: io.grpc.inprocess.InProcessChannelBuilder.1InProcessChannelTransportFactoryBuilder
            @Override // io.grpc.internal.ManagedChannelImplBuilder.ClientTransportFactoryBuilder
            public ClientTransportFactory buildClientTransportFactory() {
                return InProcessChannelBuilder.this.buildTransportFactory();
            }
        }, null);
        this.managedChannelImplBuilder.setStatsRecordStartedRpcs(false);
        this.managedChannelImplBuilder.setStatsRecordFinishedRpcs(false);
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder
    protected ManagedChannelBuilder<?> delegate() {
        return this.managedChannelImplBuilder;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public final InProcessChannelBuilder maxInboundMessageSize(int i) {
        return (InProcessChannelBuilder) super.maxInboundMessageSize(i);
    }

    public InProcessChannelBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.scheduledExecutorService = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService");
        return this;
    }

    @Override // io.grpc.internal.AbstractManagedChannelImplBuilder, io.grpc.ManagedChannelBuilder
    public InProcessChannelBuilder maxInboundMetadataSize(int i) {
        Preconditions.checkArgument(i > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = i;
        return this;
    }

    public InProcessChannelBuilder propagateCauseWithStatus(boolean z) {
        this.transportIncludeStatusCause = z;
        return this;
    }

    ClientTransportFactory buildTransportFactory() {
        return new InProcessClientTransportFactory(this.name, this.scheduledExecutorService, this.maxInboundMetadataSize, this.transportIncludeStatusCause);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStatsEnabled(boolean z) {
        this.managedChannelImplBuilder.setStatsEnabled(z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class InProcessClientTransportFactory implements ClientTransportFactory {
        private boolean closed;
        private final boolean includeCauseWithStatus;
        private final int maxInboundMetadataSize;
        private final String name;
        private final ScheduledExecutorService timerService;
        private final boolean useSharedTimer;

        @Override // io.grpc.internal.ClientTransportFactory
        public ClientTransportFactory.SwapChannelCredentialsResult swapChannelCredentials(ChannelCredentials channelCredentials) {
            return null;
        }

        private InProcessClientTransportFactory(String str, @Nullable ScheduledExecutorService scheduledExecutorService, int i, boolean z) {
            this.name = str;
            this.useSharedTimer = scheduledExecutorService == null;
            this.timerService = this.useSharedTimer ? (ScheduledExecutorService) SharedResourceHolder.get(GrpcUtil.TIMER_SERVICE) : scheduledExecutorService;
            this.maxInboundMetadataSize = i;
            this.includeCauseWithStatus = z;
        }

        @Override // io.grpc.internal.ClientTransportFactory
        public ConnectionClientTransport newClientTransport(SocketAddress socketAddress, ClientTransportFactory.ClientTransportOptions clientTransportOptions, ChannelLogger channelLogger) {
            if (this.closed) {
                throw new IllegalStateException("The transport factory is closed.");
            }
            return new InProcessTransport(this.name, this.maxInboundMetadataSize, clientTransportOptions.getAuthority(), clientTransportOptions.getUserAgent(), clientTransportOptions.getEagAttributes(), this.includeCauseWithStatus);
        }

        @Override // io.grpc.internal.ClientTransportFactory
        public ScheduledExecutorService getScheduledExecutorService() {
            return this.timerService;
        }

        @Override // io.grpc.internal.ClientTransportFactory, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            if (this.useSharedTimer) {
                SharedResourceHolder.release(GrpcUtil.TIMER_SERVICE, this.timerService);
            }
        }
    }
}
