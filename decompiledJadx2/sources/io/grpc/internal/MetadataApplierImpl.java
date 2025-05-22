package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.CallCredentials;
import io.grpc.CallOptions;
import io.grpc.Context;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class MetadataApplierImpl extends CallCredentials.MetadataApplier {
    private final CallOptions callOptions;
    DelayedStream delayedStream;
    boolean finalized;
    private final MetadataApplierListener listener;
    private final MethodDescriptor<?, ?> method;
    private final Metadata origHeaders;

    @Nullable
    private ClientStream returnedStream;
    private final ClientTransport transport;
    private final Object lock = new Object();
    private final Context ctx = Context.current();

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface MetadataApplierListener {
        void onComplete();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MetadataApplierImpl(ClientTransport clientTransport, MethodDescriptor<?, ?> methodDescriptor, Metadata metadata, CallOptions callOptions, MetadataApplierListener metadataApplierListener) {
        this.transport = clientTransport;
        this.method = methodDescriptor;
        this.origHeaders = metadata;
        this.callOptions = callOptions;
        this.listener = metadataApplierListener;
    }

    @Override // io.grpc.CallCredentials.MetadataApplier
    public void apply(Metadata metadata) {
        Preconditions.checkState(!this.finalized, "apply() or fail() already called");
        Preconditions.checkNotNull(metadata, "headers");
        this.origHeaders.merge(metadata);
        Context attach = this.ctx.attach();
        try {
            ClientStream newStream = this.transport.newStream(this.method, this.origHeaders, this.callOptions);
            this.ctx.detach(attach);
            finalizeWith(newStream);
        } catch (Throwable th) {
            this.ctx.detach(attach);
            throw th;
        }
    }

    @Override // io.grpc.CallCredentials.MetadataApplier
    public void fail(Status status) {
        Preconditions.checkArgument(!status.isOk(), "Cannot fail with OK status");
        Preconditions.checkState(!this.finalized, "apply() or fail() already called");
        finalizeWith(new FailingClientStream(status));
    }

    private void finalizeWith(ClientStream clientStream) {
        boolean z;
        Preconditions.checkState(!this.finalized, "already finalized");
        this.finalized = true;
        synchronized (this.lock) {
            if (this.returnedStream == null) {
                this.returnedStream = clientStream;
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            this.listener.onComplete();
            return;
        }
        Preconditions.checkState(this.delayedStream != null, "delayedStream is null");
        Runnable stream = this.delayedStream.setStream(clientStream);
        if (stream != null) {
            stream.run();
        }
        this.listener.onComplete();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClientStream returnStream() {
        synchronized (this.lock) {
            if (this.returnedStream == null) {
                this.delayedStream = new DelayedStream();
                DelayedStream delayedStream = this.delayedStream;
                this.returnedStream = delayedStream;
                return delayedStream;
            }
            return this.returnedStream;
        }
    }
}
