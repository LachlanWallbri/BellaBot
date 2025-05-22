package com.google.api.gax.grpc;

import com.google.api.gax.grpc.GrpcTransportChannel;
import io.grpc.ManagedChannel;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class AutoValue_GrpcTransportChannel extends GrpcTransportChannel {
    private final ManagedChannel managedChannel;

    private AutoValue_GrpcTransportChannel(ManagedChannel managedChannel) {
        this.managedChannel = managedChannel;
    }

    @Override // com.google.api.gax.grpc.GrpcTransportChannel
    ManagedChannel getManagedChannel() {
        return this.managedChannel;
    }

    public String toString() {
        return "GrpcTransportChannel{managedChannel=" + this.managedChannel + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof GrpcTransportChannel) {
            return this.managedChannel.equals(((GrpcTransportChannel) obj).getManagedChannel());
        }
        return false;
    }

    public int hashCode() {
        return this.managedChannel.hashCode() ^ 1000003;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    static final class Builder extends GrpcTransportChannel.Builder {
        private ManagedChannel managedChannel;

        @Override // com.google.api.gax.grpc.GrpcTransportChannel.Builder
        public GrpcTransportChannel.Builder setManagedChannel(ManagedChannel managedChannel) {
            if (managedChannel == null) {
                throw new NullPointerException("Null managedChannel");
            }
            this.managedChannel = managedChannel;
            return this;
        }

        @Override // com.google.api.gax.grpc.GrpcTransportChannel.Builder
        public GrpcTransportChannel build() {
            String str = "";
            if (this.managedChannel == null) {
                str = " managedChannel";
            }
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_GrpcTransportChannel(this.managedChannel);
        }
    }
}
