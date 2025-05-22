package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.internal.ClientTransport;
import io.grpc.netty.shaded.io.grpc.netty.WriteQueue;
import java.util.concurrent.Executor;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class SendPingCommand extends WriteQueue.AbstractQueuedCommand {
    private final ClientTransport.PingCallback callback;
    private final Executor executor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SendPingCommand(ClientTransport.PingCallback pingCallback, Executor executor) {
        this.callback = pingCallback;
        this.executor = executor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClientTransport.PingCallback callback() {
        return this.callback;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Executor executor() {
        return this.executor;
    }
}
