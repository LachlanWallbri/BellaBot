package io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.Attributes;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.DecompressorRegistry;
import io.grpc.InternalDecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.ServerCall;
import io.grpc.Status;
import io.grpc.internal.StreamListener;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ServerCallImpl<ReqT, RespT> extends ServerCall<ReqT, RespT> {
    static final String MISSING_RESPONSE = "Completed without a response";
    static final String TOO_MANY_RESPONSES = "Too many responses";
    private static final Logger log = Logger.getLogger(ServerCallImpl.class.getName());
    private volatile boolean cancelled;
    private boolean closeCalled;
    private Compressor compressor;
    private final CompressorRegistry compressorRegistry;
    private final Context.CancellableContext context;
    private final DecompressorRegistry decompressorRegistry;
    private final byte[] messageAcceptEncoding;
    private boolean messageSent;
    private final MethodDescriptor<ReqT, RespT> method;
    private boolean sendHeadersCalled;
    private CallTracer serverCallTracer;
    private final ServerStream stream;
    private final Tag tag;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServerCallImpl(ServerStream serverStream, MethodDescriptor<ReqT, RespT> methodDescriptor, Metadata metadata, Context.CancellableContext cancellableContext, DecompressorRegistry decompressorRegistry, CompressorRegistry compressorRegistry, CallTracer callTracer, Tag tag) {
        this.stream = serverStream;
        this.method = methodDescriptor;
        this.context = cancellableContext;
        this.messageAcceptEncoding = (byte[]) metadata.get(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        this.decompressorRegistry = decompressorRegistry;
        this.compressorRegistry = compressorRegistry;
        this.serverCallTracer = callTracer;
        this.serverCallTracer.reportCallStarted();
        this.tag = tag;
    }

    @Override // io.grpc.ServerCall
    public void request(int i) {
        PerfMark.startTask("ServerCall.request", this.tag);
        try {
            this.stream.request(i);
        } finally {
            PerfMark.stopTask("ServerCall.request", this.tag);
        }
    }

    @Override // io.grpc.ServerCall
    public void sendHeaders(Metadata metadata) {
        PerfMark.startTask("ServerCall.sendHeaders", this.tag);
        try {
            sendHeadersInternal(metadata);
        } finally {
            PerfMark.stopTask("ServerCall.sendHeaders", this.tag);
        }
    }

    private void sendHeadersInternal(Metadata metadata) {
        Preconditions.checkState(!this.sendHeadersCalled, "sendHeaders has already been called");
        Preconditions.checkState(!this.closeCalled, "call is closed");
        metadata.discardAll(GrpcUtil.MESSAGE_ENCODING_KEY);
        if (this.compressor == null) {
            this.compressor = Codec.Identity.NONE;
        } else if (this.messageAcceptEncoding != null) {
            if (!GrpcUtil.iterableContains(GrpcUtil.ACCEPT_ENCODING_SPLITTER.split(new String(this.messageAcceptEncoding, GrpcUtil.US_ASCII)), this.compressor.getMessageEncoding())) {
                this.compressor = Codec.Identity.NONE;
            }
        } else {
            this.compressor = Codec.Identity.NONE;
        }
        metadata.put(GrpcUtil.MESSAGE_ENCODING_KEY, this.compressor.getMessageEncoding());
        this.stream.setCompressor(this.compressor);
        metadata.discardAll(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        byte[] rawAdvertisedMessageEncodings = InternalDecompressorRegistry.getRawAdvertisedMessageEncodings(this.decompressorRegistry);
        if (rawAdvertisedMessageEncodings.length != 0) {
            metadata.put(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY, rawAdvertisedMessageEncodings);
        }
        this.sendHeadersCalled = true;
        this.stream.writeHeaders(metadata);
    }

    @Override // io.grpc.ServerCall
    public void sendMessage(RespT respt) {
        PerfMark.startTask("ServerCall.sendMessage", this.tag);
        try {
            sendMessageInternal(respt);
        } finally {
            PerfMark.stopTask("ServerCall.sendMessage", this.tag);
        }
    }

    private void sendMessageInternal(RespT respt) {
        Preconditions.checkState(this.sendHeadersCalled, "sendHeaders has not been called");
        Preconditions.checkState(!this.closeCalled, "call is closed");
        if (this.method.getType().serverSendsOneMessage() && this.messageSent) {
            internalClose(Status.INTERNAL.withDescription(TOO_MANY_RESPONSES));
            return;
        }
        this.messageSent = true;
        try {
            this.stream.writeMessage(this.method.streamResponse(respt));
            this.stream.flush();
        } catch (Error e) {
            close(Status.CANCELLED.withDescription("Server sendMessage() failed with Error"), new Metadata());
            throw e;
        } catch (RuntimeException e2) {
            close(Status.fromThrowable(e2), new Metadata());
        }
    }

    @Override // io.grpc.ServerCall
    public void setMessageCompression(boolean z) {
        this.stream.setMessageCompression(z);
    }

    @Override // io.grpc.ServerCall
    public void setCompression(String str) {
        Preconditions.checkState(!this.sendHeadersCalled, "sendHeaders has been called");
        this.compressor = this.compressorRegistry.lookupCompressor(str);
        Preconditions.checkArgument(this.compressor != null, "Unable to find compressor by name %s", str);
    }

    @Override // io.grpc.ServerCall
    public boolean isReady() {
        return this.stream.isReady();
    }

    @Override // io.grpc.ServerCall
    public void close(Status status, Metadata metadata) {
        PerfMark.startTask("ServerCall.close", this.tag);
        try {
            closeInternal(status, metadata);
        } finally {
            PerfMark.stopTask("ServerCall.close", this.tag);
        }
    }

    private void closeInternal(Status status, Metadata metadata) {
        Preconditions.checkState(!this.closeCalled, "call already closed");
        try {
            this.closeCalled = true;
            if (status.isOk() && this.method.getType().serverSendsOneMessage() && !this.messageSent) {
                internalClose(Status.INTERNAL.withDescription(MISSING_RESPONSE));
            } else {
                this.stream.close(status, metadata);
            }
        } finally {
            this.serverCallTracer.reportCallEnded(status.isOk());
        }
    }

    @Override // io.grpc.ServerCall
    public boolean isCancelled() {
        return this.cancelled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServerStreamListener newServerStreamListener(ServerCall.Listener<ReqT> listener) {
        return new ServerStreamListenerImpl(this, listener, this.context);
    }

    @Override // io.grpc.ServerCall
    public Attributes getAttributes() {
        return this.stream.getAttributes();
    }

    @Override // io.grpc.ServerCall
    public String getAuthority() {
        return this.stream.getAuthority();
    }

    @Override // io.grpc.ServerCall
    public MethodDescriptor<ReqT, RespT> getMethodDescriptor() {
        return this.method;
    }

    private void internalClose(Status status) {
        log.log(Level.WARNING, "Cancelling the stream with status {0}", new Object[]{status});
        this.stream.cancel(status);
        this.serverCallTracer.reportCallEnded(status.isOk());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static final class ServerStreamListenerImpl<ReqT> implements ServerStreamListener {
        private final ServerCallImpl<ReqT, ?> call;
        private final Context.CancellableContext context;
        private final ServerCall.Listener<ReqT> listener;

        public ServerStreamListenerImpl(ServerCallImpl<ReqT, ?> serverCallImpl, ServerCall.Listener<ReqT> listener, Context.CancellableContext cancellableContext) {
            this.call = (ServerCallImpl) Preconditions.checkNotNull(serverCallImpl, NotificationCompat.CATEGORY_CALL);
            this.listener = (ServerCall.Listener) Preconditions.checkNotNull(listener, "listener must not be null");
            this.context = (Context.CancellableContext) Preconditions.checkNotNull(cancellableContext, "context");
            this.context.addListener(new Context.CancellationListener() { // from class: io.grpc.internal.ServerCallImpl.ServerStreamListenerImpl.1
                @Override // io.grpc.Context.CancellationListener
                public void cancelled(Context context) {
                    ServerStreamListenerImpl.this.call.cancelled = true;
                }
            }, MoreExecutors.directExecutor());
        }

        @Override // io.grpc.internal.StreamListener
        public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
            PerfMark.startTask("ServerStreamListener.messagesAvailable", ((ServerCallImpl) this.call).tag);
            try {
                messagesAvailableInternal(messageProducer);
            } finally {
                PerfMark.stopTask("ServerStreamListener.messagesAvailable", ((ServerCallImpl) this.call).tag);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void messagesAvailableInternal(StreamListener.MessageProducer messageProducer) {
            if (((ServerCallImpl) this.call).cancelled) {
                GrpcUtil.closeQuietly(messageProducer);
                return;
            }
            while (true) {
                try {
                    InputStream next = messageProducer.next();
                    if (next == null) {
                        return;
                    }
                    try {
                        this.listener.onMessage(((ServerCallImpl) this.call).method.parseRequest(next));
                        next.close();
                    } finally {
                    }
                } catch (Throwable th) {
                    GrpcUtil.closeQuietly(messageProducer);
                    Throwables.throwIfUnchecked(th);
                    throw new RuntimeException(th);
                }
            }
        }

        @Override // io.grpc.internal.ServerStreamListener
        public void halfClosed() {
            PerfMark.startTask("ServerStreamListener.halfClosed", ((ServerCallImpl) this.call).tag);
            try {
                if (((ServerCallImpl) this.call).cancelled) {
                    return;
                }
                this.listener.onHalfClose();
            } finally {
                PerfMark.stopTask("ServerStreamListener.halfClosed", ((ServerCallImpl) this.call).tag);
            }
        }

        @Override // io.grpc.internal.ServerStreamListener
        public void closed(Status status) {
            PerfMark.startTask("ServerStreamListener.closed", ((ServerCallImpl) this.call).tag);
            try {
                closedInternal(status);
            } finally {
                PerfMark.stopTask("ServerStreamListener.closed", ((ServerCallImpl) this.call).tag);
            }
        }

        private void closedInternal(Status status) {
            try {
                if (!status.isOk()) {
                    ((ServerCallImpl) this.call).cancelled = true;
                    this.listener.onCancel();
                } else {
                    this.listener.onComplete();
                }
            } finally {
                this.context.cancel(null);
            }
        }

        @Override // io.grpc.internal.StreamListener
        public void onReady() {
            PerfMark.startTask("ServerStreamListener.onReady", ((ServerCallImpl) this.call).tag);
            try {
                if (((ServerCallImpl) this.call).cancelled) {
                    return;
                }
                this.listener.onReady();
            } finally {
                PerfMark.stopTask("ServerCall.closed", ((ServerCallImpl) this.call).tag);
            }
        }
    }
}
