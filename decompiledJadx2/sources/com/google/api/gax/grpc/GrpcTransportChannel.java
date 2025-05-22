package com.google.api.gax.grpc;

import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.core.ResourceCloseException;
import com.google.api.gax.grpc.AutoValue_GrpcTransportChannel;
import com.google.api.gax.rpc.TransportChannel;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalExtensionOnly
/* loaded from: classes2.dex */
public abstract class GrpcTransportChannel implements TransportChannel {

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public static abstract class Builder {
        public abstract GrpcTransportChannel build();

        public abstract Builder setManagedChannel(ManagedChannel managedChannel);
    }

    public static String getGrpcTransportName() {
        return "grpc";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract ManagedChannel getManagedChannel();

    @Override // com.google.api.gax.rpc.TransportChannel
    public String getTransportName() {
        return getGrpcTransportName();
    }

    @Override // com.google.api.gax.rpc.TransportChannel
    public GrpcCallContext getEmptyCallContext() {
        return GrpcCallContext.createDefault();
    }

    public Channel getChannel() {
        return getManagedChannel();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdown() {
        getManagedChannel().shutdown();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isShutdown() {
        return getManagedChannel().isShutdown();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean isTerminated() {
        return getManagedChannel().isTerminated();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public void shutdownNow() {
        getManagedChannel().shutdownNow();
    }

    @Override // com.google.api.gax.core.BackgroundResource
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return getManagedChannel().awaitTermination(j, timeUnit);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        getManagedChannel().shutdown();
        try {
            awaitTermination(6L, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new ResourceCloseException(e);
        }
    }

    public static Builder newBuilder() {
        return new AutoValue_GrpcTransportChannel.Builder();
    }

    public static GrpcTransportChannel create(ManagedChannel managedChannel) {
        return newBuilder().setManagedChannel(managedChannel).build();
    }
}
