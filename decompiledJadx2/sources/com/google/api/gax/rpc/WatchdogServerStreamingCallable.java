package com.google.api.gax.rpc;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import org.threeten.p095bp.Duration;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class WatchdogServerStreamingCallable<RequestT, ResponseT> extends ServerStreamingCallable<RequestT, ResponseT> {
    private final ServerStreamingCallable<RequestT, ResponseT> inner;
    private final Watchdog watchdog;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WatchdogServerStreamingCallable(ServerStreamingCallable<RequestT, ResponseT> serverStreamingCallable, Watchdog watchdog) {
        Preconditions.checkNotNull(serverStreamingCallable);
        Preconditions.checkNotNull(watchdog);
        this.inner = serverStreamingCallable;
        this.watchdog = watchdog;
    }

    @Override // com.google.api.gax.rpc.ServerStreamingCallable
    public void call(RequestT requestt, ResponseObserver<ResponseT> responseObserver, ApiCallContext apiCallContext) {
        this.inner.call(requestt, this.watchdog.watch(responseObserver, (Duration) MoreObjects.firstNonNull(apiCallContext.getStreamWaitTimeout(), Duration.ZERO), (Duration) MoreObjects.firstNonNull(apiCallContext.getStreamIdleTimeout(), Duration.ZERO)), apiCallContext);
    }
}
