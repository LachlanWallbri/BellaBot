package io.grpc.inprocess;

import com.google.common.base.Preconditions;
import io.grpc.Deadline;
import io.grpc.ServerBuilder;
import io.grpc.ServerStreamTracer;
import io.grpc.internal.AbstractServerImplBuilder;
import io.grpc.internal.FixedObjectPool;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.InternalServer;
import io.grpc.internal.ObjectPool;
import io.grpc.internal.ServerImplBuilder;
import io.grpc.internal.SharedResourcePool;
import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class InProcessServerBuilder extends AbstractServerImplBuilder<InProcessServerBuilder> {
    final String name;
    int maxInboundMetadataSize = Integer.MAX_VALUE;
    ObjectPool<ScheduledExecutorService> schedulerPool = SharedResourcePool.forResource(GrpcUtil.TIMER_SERVICE);
    private final ServerImplBuilder serverImplBuilder = new ServerImplBuilder(new ServerImplBuilder.ClientTransportServersBuilder() { // from class: io.grpc.inprocess.InProcessServerBuilder.1InProcessClientTransportServersBuilder
        @Override // io.grpc.internal.ServerImplBuilder.ClientTransportServersBuilder
        public InternalServer buildClientTransportServers(List<? extends ServerStreamTracer.Factory> list) {
            return InProcessServerBuilder.this.buildTransportServers(list);
        }
    });

    public static InProcessServerBuilder forName(String str) {
        return new InProcessServerBuilder(str);
    }

    public static InProcessServerBuilder forPort(int i) {
        throw new UnsupportedOperationException("call forName() instead");
    }

    public static String generateName() {
        return UUID.randomUUID().toString();
    }

    private InProcessServerBuilder(String str) {
        this.name = (String) Preconditions.checkNotNull(str, "name");
        this.serverImplBuilder.setStatsRecordStartedRpcs(false);
        this.serverImplBuilder.setStatsRecordFinishedRpcs(false);
        handshakeTimeout(Long.MAX_VALUE, TimeUnit.SECONDS);
    }

    @Override // io.grpc.internal.AbstractServerImplBuilder
    protected ServerBuilder<?> delegate() {
        return this.serverImplBuilder;
    }

    public InProcessServerBuilder scheduledExecutorService(ScheduledExecutorService scheduledExecutorService) {
        this.schedulerPool = new FixedObjectPool(Preconditions.checkNotNull(scheduledExecutorService, "scheduledExecutorService"));
        return this;
    }

    public InProcessServerBuilder deadlineTicker(Deadline.Ticker ticker) {
        this.serverImplBuilder.setDeadlineTicker(ticker);
        return this;
    }

    @Override // io.grpc.internal.AbstractServerImplBuilder, io.grpc.ServerBuilder
    public InProcessServerBuilder maxInboundMetadataSize(int i) {
        Preconditions.checkArgument(i > 0, "maxInboundMetadataSize must be > 0");
        this.maxInboundMetadataSize = i;
        return this;
    }

    InProcessServer buildTransportServers(List<? extends ServerStreamTracer.Factory> list) {
        return new InProcessServer(this, list);
    }

    @Override // io.grpc.internal.AbstractServerImplBuilder, io.grpc.ServerBuilder
    public InProcessServerBuilder useTransportSecurity(File file, File file2) {
        throw new UnsupportedOperationException("TLS not supported in InProcessServer");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStatsEnabled(boolean z) {
        this.serverImplBuilder.setStatsEnabled(z);
    }
}
