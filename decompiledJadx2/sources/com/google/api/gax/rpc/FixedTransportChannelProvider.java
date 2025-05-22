package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalExtensionOnly;
import com.google.auth.Credentials;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalExtensionOnly
/* loaded from: classes2.dex */
public class FixedTransportChannelProvider implements TransportChannelProvider {
    private final TransportChannel transportChannel;

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    @BetaApi("The surface for customizing pool size is not stable yet and may change in the future.")
    public boolean acceptsPoolSize() {
        return false;
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public boolean needsCredentials() {
        return false;
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public boolean needsEndpoint() {
        return false;
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public boolean needsExecutor() {
        return false;
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public boolean needsHeaders() {
        return false;
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public boolean shouldAutoClose() {
        return false;
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public /* bridge */ /* synthetic */ TransportChannelProvider withHeaders(Map map) {
        return withHeaders((Map<String, String>) map);
    }

    private FixedTransportChannelProvider(TransportChannel transportChannel) {
        this.transportChannel = (TransportChannel) Preconditions.checkNotNull(transportChannel);
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public FixedTransportChannelProvider withExecutor(ScheduledExecutorService scheduledExecutorService) {
        return withExecutor((Executor) scheduledExecutorService);
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public FixedTransportChannelProvider withExecutor(Executor executor) {
        throw new UnsupportedOperationException("FixedTransportChannelProvider doesn't need an executor");
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    public FixedTransportChannelProvider withHeaders(Map<String, String> map) {
        throw new UnsupportedOperationException("FixedTransportChannelProvider doesn't need headers");
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public TransportChannelProvider withEndpoint(String str) {
        throw new UnsupportedOperationException("FixedTransportChannelProvider doesn't need an endpoint");
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    @BetaApi("The surface for customizing pool size is not stable yet and may change in the future.")
    public TransportChannelProvider withPoolSize(int i) {
        throw new UnsupportedOperationException("FixedTransportChannelProvider doesn't allow pool size customization");
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public TransportChannel getTransportChannel() throws IOException {
        return this.transportChannel;
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public String getTransportName() {
        return this.transportChannel.getTransportName();
    }

    @Override // com.google.api.gax.rpc.TransportChannelProvider
    public TransportChannelProvider withCredentials(Credentials credentials) {
        throw new UnsupportedOperationException("FixedTransportChannelProvider doesn't need credentials");
    }

    public static FixedTransportChannelProvider create(TransportChannel transportChannel) {
        return new FixedTransportChannelProvider(transportChannel);
    }
}
