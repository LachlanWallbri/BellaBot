package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalExtensionOnly;
import com.google.auth.Credentials;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalExtensionOnly
/* loaded from: classes2.dex */
public interface TransportChannelProvider {
    @BetaApi("The surface for customizing pool size is not stable yet and may change in the future.")
    boolean acceptsPoolSize();

    TransportChannel getTransportChannel() throws IOException;

    String getTransportName();

    @BetaApi("The surface to customize credentials is not stable yet and may change in the future.")
    boolean needsCredentials();

    boolean needsEndpoint();

    boolean needsExecutor();

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    boolean needsHeaders();

    boolean shouldAutoClose();

    @BetaApi("The surface to customize credentials is not stable yet and may change in the future.")
    TransportChannelProvider withCredentials(Credentials credentials);

    TransportChannelProvider withEndpoint(String str);

    TransportChannelProvider withExecutor(Executor executor);

    @Deprecated
    TransportChannelProvider withExecutor(ScheduledExecutorService scheduledExecutorService);

    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
    TransportChannelProvider withHeaders(Map<String, String> map);

    @BetaApi("The surface for customizing pool size is not stable yet and may change in the future.")
    TransportChannelProvider withPoolSize(int i);
}
