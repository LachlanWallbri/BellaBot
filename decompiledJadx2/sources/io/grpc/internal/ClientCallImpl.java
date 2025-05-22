package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.InternalConfigSelector;
import io.grpc.InternalDecompressorRegistry;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.ManagedChannelServiceConfig;
import io.grpc.internal.StreamListener;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import org.apache.commons.codec.language.Soundex;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ClientCallImpl<ReqT, RespT> extends ClientCall<ReqT, RespT> {
    private final Executor callExecutor;
    private final boolean callExecutorIsDirect;
    private CallOptions callOptions;
    private boolean cancelCalled;
    private volatile boolean cancelListenersShouldBeRemoved;
    private final CallTracer channelCallsTracer;
    private final ClientStreamProvider clientStreamProvider;
    private final Context context;
    private final ScheduledExecutorService deadlineCancellationExecutor;
    private volatile ScheduledFuture<?> deadlineCancellationFuture;
    private boolean fullStreamDecompression;
    private boolean halfCloseCalled;
    private final MethodDescriptor<ReqT, RespT> method;
    private ClientStream stream;
    private final Tag tag;
    private final boolean unaryRequest;
    private static final Logger log = Logger.getLogger(ClientCallImpl.class.getName());
    private static final byte[] FULL_STREAM_DECOMPRESSION_ENCODINGS = "gzip".getBytes(Charset.forName("US-ASCII"));
    private final ClientCallImpl<ReqT, RespT>.ContextCancellationListener cancellationListener = new ContextCancellationListener();
    private DecompressorRegistry decompressorRegistry = DecompressorRegistry.getDefaultInstance();
    private CompressorRegistry compressorRegistry = CompressorRegistry.getDefaultInstance();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface ClientStreamProvider {
        ClientStream newStream(MethodDescriptor<?, ?> methodDescriptor, CallOptions callOptions, Metadata metadata, Context context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClientCallImpl(MethodDescriptor<ReqT, RespT> methodDescriptor, Executor executor, CallOptions callOptions, ClientStreamProvider clientStreamProvider, ScheduledExecutorService scheduledExecutorService, CallTracer callTracer, @Nullable InternalConfigSelector internalConfigSelector) {
        this.method = methodDescriptor;
        this.tag = PerfMark.createTag(methodDescriptor.getFullMethodName(), System.identityHashCode(this));
        boolean z = true;
        if (executor == MoreExecutors.directExecutor()) {
            this.callExecutor = new SerializeReentrantCallsDirectExecutor();
            this.callExecutorIsDirect = true;
        } else {
            this.callExecutor = new SerializingExecutor(executor);
            this.callExecutorIsDirect = false;
        }
        this.channelCallsTracer = callTracer;
        this.context = Context.current();
        if (methodDescriptor.getType() != MethodDescriptor.MethodType.UNARY && methodDescriptor.getType() != MethodDescriptor.MethodType.SERVER_STREAMING) {
            z = false;
        }
        this.unaryRequest = z;
        this.callOptions = callOptions;
        this.clientStreamProvider = clientStreamProvider;
        this.deadlineCancellationExecutor = scheduledExecutorService;
        PerfMark.event("ClientCall.<init>", this.tag);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class ContextCancellationListener implements Context.CancellationListener {
        private ContextCancellationListener() {
        }

        @Override // io.grpc.Context.CancellationListener
        public void cancelled(Context context) {
            ClientCallImpl.this.stream.cancel(Contexts.statusFromCancelled(context));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClientCallImpl<ReqT, RespT> setFullStreamDecompression(boolean z) {
        this.fullStreamDecompression = z;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClientCallImpl<ReqT, RespT> setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
        this.decompressorRegistry = decompressorRegistry;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ClientCallImpl<ReqT, RespT> setCompressorRegistry(CompressorRegistry compressorRegistry) {
        this.compressorRegistry = compressorRegistry;
        return this;
    }

    static void prepareHeaders(Metadata metadata, DecompressorRegistry decompressorRegistry, Compressor compressor, boolean z) {
        metadata.discardAll(GrpcUtil.MESSAGE_ENCODING_KEY);
        if (compressor != Codec.Identity.NONE) {
            metadata.put(GrpcUtil.MESSAGE_ENCODING_KEY, compressor.getMessageEncoding());
        }
        metadata.discardAll(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        byte[] rawAdvertisedMessageEncodings = InternalDecompressorRegistry.getRawAdvertisedMessageEncodings(decompressorRegistry);
        if (rawAdvertisedMessageEncodings.length != 0) {
            metadata.put(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY, rawAdvertisedMessageEncodings);
        }
        metadata.discardAll(GrpcUtil.CONTENT_ENCODING_KEY);
        metadata.discardAll(GrpcUtil.CONTENT_ACCEPT_ENCODING_KEY);
        if (z) {
            metadata.put(GrpcUtil.CONTENT_ACCEPT_ENCODING_KEY, FULL_STREAM_DECOMPRESSION_ENCODINGS);
        }
    }

    @Override // io.grpc.ClientCall
    public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
        PerfMark.startTask("ClientCall.start", this.tag);
        try {
            startInternal(listener, metadata);
        } finally {
            PerfMark.stopTask("ClientCall.start", this.tag);
        }
    }

    private void startInternal(final ClientCall.Listener<RespT> listener, Metadata metadata) {
        Compressor compressor;
        boolean z = false;
        Preconditions.checkState(this.stream == null, "Already started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkNotNull(listener, "observer");
        Preconditions.checkNotNull(metadata, "headers");
        if (this.context.isCancelled()) {
            this.stream = NoopClientStream.INSTANCE;
            this.callExecutor.execute(new ContextRunnable() { // from class: io.grpc.internal.ClientCallImpl.1ClosedByContext
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(ClientCallImpl.this.context);
                }

                @Override // io.grpc.internal.ContextRunnable
                public void runInContext() {
                    ClientCallImpl clientCallImpl = ClientCallImpl.this;
                    clientCallImpl.closeObserver(listener, Contexts.statusFromCancelled(clientCallImpl.context), new Metadata());
                }
            });
            return;
        }
        applyMethodConfig();
        final String compressor2 = this.callOptions.getCompressor();
        if (compressor2 != null) {
            compressor = this.compressorRegistry.lookupCompressor(compressor2);
            if (compressor == null) {
                this.stream = NoopClientStream.INSTANCE;
                this.callExecutor.execute(new ContextRunnable() { // from class: io.grpc.internal.ClientCallImpl.1ClosedByNotFoundCompressor
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(ClientCallImpl.this.context);
                    }

                    @Override // io.grpc.internal.ContextRunnable
                    public void runInContext() {
                        ClientCallImpl.this.closeObserver(listener, Status.INTERNAL.withDescription(String.format("Unable to find compressor by name %s", compressor2)), new Metadata());
                    }
                });
                return;
            }
        } else {
            compressor = Codec.Identity.NONE;
        }
        prepareHeaders(metadata, this.decompressorRegistry, compressor, this.fullStreamDecompression);
        Deadline effectiveDeadline = effectiveDeadline();
        if (effectiveDeadline != null && effectiveDeadline.isExpired()) {
            z = true;
        }
        if (!z) {
            logIfContextNarrowedTimeout(effectiveDeadline, this.context.getDeadline(), this.callOptions.getDeadline());
            this.stream = this.clientStreamProvider.newStream(this.method, this.callOptions, metadata, this.context);
        } else {
            this.stream = new FailingClientStream(Status.DEADLINE_EXCEEDED.withDescription("ClientCall started after deadline exceeded: " + effectiveDeadline));
        }
        if (this.callExecutorIsDirect) {
            this.stream.optimizeForDirectExecutor();
        }
        if (this.callOptions.getAuthority() != null) {
            this.stream.setAuthority(this.callOptions.getAuthority());
        }
        if (this.callOptions.getMaxInboundMessageSize() != null) {
            this.stream.setMaxInboundMessageSize(this.callOptions.getMaxInboundMessageSize().intValue());
        }
        if (this.callOptions.getMaxOutboundMessageSize() != null) {
            this.stream.setMaxOutboundMessageSize(this.callOptions.getMaxOutboundMessageSize().intValue());
        }
        if (effectiveDeadline != null) {
            this.stream.setDeadline(effectiveDeadline);
        }
        this.stream.setCompressor(compressor);
        boolean z2 = this.fullStreamDecompression;
        if (z2) {
            this.stream.setFullStreamDecompression(z2);
        }
        this.stream.setDecompressorRegistry(this.decompressorRegistry);
        this.channelCallsTracer.reportCallStarted();
        this.stream.start(new ClientStreamListenerImpl(listener));
        this.context.addListener(this.cancellationListener, MoreExecutors.directExecutor());
        if (effectiveDeadline != null && !effectiveDeadline.equals(this.context.getDeadline()) && this.deadlineCancellationExecutor != null) {
            this.deadlineCancellationFuture = startDeadlineTimer(effectiveDeadline);
        }
        if (this.cancelListenersShouldBeRemoved) {
            removeContextListenerAndCancelDeadlineFuture();
        }
    }

    private void applyMethodConfig() {
        ManagedChannelServiceConfig.MethodInfo methodInfo = (ManagedChannelServiceConfig.MethodInfo) this.callOptions.getOption(ManagedChannelServiceConfig.MethodInfo.KEY);
        if (methodInfo == null) {
            return;
        }
        if (methodInfo.timeoutNanos != null) {
            Deadline after = Deadline.after(methodInfo.timeoutNanos.longValue(), TimeUnit.NANOSECONDS);
            Deadline deadline = this.callOptions.getDeadline();
            if (deadline == null || after.compareTo(deadline) < 0) {
                this.callOptions = this.callOptions.withDeadline(after);
            }
        }
        if (methodInfo.waitForReady != null) {
            this.callOptions = methodInfo.waitForReady.booleanValue() ? this.callOptions.withWaitForReady() : this.callOptions.withoutWaitForReady();
        }
        if (methodInfo.maxInboundMessageSize != null) {
            Integer maxInboundMessageSize = this.callOptions.getMaxInboundMessageSize();
            if (maxInboundMessageSize != null) {
                this.callOptions = this.callOptions.withMaxInboundMessageSize(Math.min(maxInboundMessageSize.intValue(), methodInfo.maxInboundMessageSize.intValue()));
            } else {
                this.callOptions = this.callOptions.withMaxInboundMessageSize(methodInfo.maxInboundMessageSize.intValue());
            }
        }
        if (methodInfo.maxOutboundMessageSize != null) {
            Integer maxOutboundMessageSize = this.callOptions.getMaxOutboundMessageSize();
            if (maxOutboundMessageSize != null) {
                this.callOptions = this.callOptions.withMaxOutboundMessageSize(Math.min(maxOutboundMessageSize.intValue(), methodInfo.maxOutboundMessageSize.intValue()));
            } else {
                this.callOptions = this.callOptions.withMaxOutboundMessageSize(methodInfo.maxOutboundMessageSize.intValue());
            }
        }
    }

    private static void logIfContextNarrowedTimeout(Deadline deadline, @Nullable Deadline deadline2, @Nullable Deadline deadline3) {
        if (log.isLoggable(Level.FINE) && deadline != null && deadline.equals(deadline2)) {
            StringBuilder sb = new StringBuilder(String.format("Call timeout set to '%d' ns, due to context deadline.", Long.valueOf(Math.max(0L, deadline.timeRemaining(TimeUnit.NANOSECONDS)))));
            if (deadline3 == null) {
                sb.append(" Explicit call timeout was not set.");
            } else {
                sb.append(String.format(" Explicit call timeout was '%d' ns.", Long.valueOf(deadline3.timeRemaining(TimeUnit.NANOSECONDS))));
            }
            log.fine(sb.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeContextListenerAndCancelDeadlineFuture() {
        this.context.removeListener(this.cancellationListener);
        ScheduledFuture<?> scheduledFuture = this.deadlineCancellationFuture;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public class DeadlineTimer implements Runnable {
        private final long remainingNanos;

        DeadlineTimer(long j) {
            this.remainingNanos = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            InsightBuilder insightBuilder = new InsightBuilder();
            ClientCallImpl.this.stream.appendTimeoutInsight(insightBuilder);
            long abs = Math.abs(this.remainingNanos) / TimeUnit.SECONDS.toNanos(1L);
            long abs2 = Math.abs(this.remainingNanos) % TimeUnit.SECONDS.toNanos(1L);
            StringBuilder sb = new StringBuilder();
            sb.append("deadline exceeded after ");
            if (this.remainingNanos < 0) {
                sb.append(Soundex.SILENT_MARKER);
            }
            sb.append(abs);
            sb.append(String.format(Locale.US, ".%09d", Long.valueOf(abs2)));
            sb.append("s. ");
            sb.append(insightBuilder);
            ClientCallImpl.this.stream.cancel(Status.DEADLINE_EXCEEDED.augmentDescription(sb.toString()));
        }
    }

    private ScheduledFuture<?> startDeadlineTimer(Deadline deadline) {
        long timeRemaining = deadline.timeRemaining(TimeUnit.NANOSECONDS);
        return this.deadlineCancellationExecutor.schedule(new LogExceptionRunnable(new DeadlineTimer(timeRemaining)), timeRemaining, TimeUnit.NANOSECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public Deadline effectiveDeadline() {
        return min(this.callOptions.getDeadline(), this.context.getDeadline());
    }

    @Nullable
    private static Deadline min(@Nullable Deadline deadline, @Nullable Deadline deadline2) {
        return deadline == null ? deadline2 : deadline2 == null ? deadline : deadline.minimum(deadline2);
    }

    @Override // io.grpc.ClientCall
    public void request(int i) {
        PerfMark.startTask("ClientCall.request", this.tag);
        try {
            boolean z = true;
            Preconditions.checkState(this.stream != null, "Not started");
            if (i < 0) {
                z = false;
            }
            Preconditions.checkArgument(z, "Number requested must be non-negative");
            this.stream.request(i);
        } finally {
            PerfMark.stopTask("ClientCall.request", this.tag);
        }
    }

    @Override // io.grpc.ClientCall
    public void cancel(@Nullable String str, @Nullable Throwable th) {
        PerfMark.startTask("ClientCall.cancel", this.tag);
        try {
            cancelInternal(str, th);
        } finally {
            PerfMark.stopTask("ClientCall.cancel", this.tag);
        }
    }

    private void cancelInternal(@Nullable String str, @Nullable Throwable th) {
        Status withDescription;
        if (str == null && th == null) {
            th = new CancellationException("Cancelled without a message or cause");
            log.log(Level.WARNING, "Cancelling without a message or cause is suboptimal", th);
        }
        if (this.cancelCalled) {
            return;
        }
        this.cancelCalled = true;
        try {
            if (this.stream != null) {
                Status status = Status.CANCELLED;
                if (str != null) {
                    withDescription = status.withDescription(str);
                } else {
                    withDescription = status.withDescription("Call cancelled without message");
                }
                if (th != null) {
                    withDescription = withDescription.withCause(th);
                }
                this.stream.cancel(withDescription);
            }
        } finally {
            removeContextListenerAndCancelDeadlineFuture();
        }
    }

    @Override // io.grpc.ClientCall
    public void halfClose() {
        PerfMark.startTask("ClientCall.halfClose", this.tag);
        try {
            halfCloseInternal();
        } finally {
            PerfMark.stopTask("ClientCall.halfClose", this.tag);
        }
    }

    private void halfCloseInternal() {
        Preconditions.checkState(this.stream != null, "Not started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkState(!this.halfCloseCalled, "call already half-closed");
        this.halfCloseCalled = true;
        this.stream.halfClose();
    }

    @Override // io.grpc.ClientCall
    public void sendMessage(ReqT reqt) {
        PerfMark.startTask("ClientCall.sendMessage", this.tag);
        try {
            sendMessageInternal(reqt);
        } finally {
            PerfMark.stopTask("ClientCall.sendMessage", this.tag);
        }
    }

    private void sendMessageInternal(ReqT reqt) {
        Preconditions.checkState(this.stream != null, "Not started");
        Preconditions.checkState(!this.cancelCalled, "call was cancelled");
        Preconditions.checkState(!this.halfCloseCalled, "call was half-closed");
        try {
            if (this.stream instanceof RetriableStream) {
                ((RetriableStream) this.stream).sendMessage(reqt);
            } else {
                this.stream.writeMessage(this.method.streamRequest(reqt));
            }
            if (this.unaryRequest) {
                return;
            }
            this.stream.flush();
        } catch (Error e) {
            this.stream.cancel(Status.CANCELLED.withDescription("Client sendMessage() failed with Error"));
            throw e;
        } catch (RuntimeException e2) {
            this.stream.cancel(Status.CANCELLED.withCause(e2).withDescription("Failed to stream message"));
        }
    }

    @Override // io.grpc.ClientCall
    public void setMessageCompression(boolean z) {
        Preconditions.checkState(this.stream != null, "Not started");
        this.stream.setMessageCompression(z);
    }

    @Override // io.grpc.ClientCall
    public boolean isReady() {
        return this.stream.isReady();
    }

    @Override // io.grpc.ClientCall
    public Attributes getAttributes() {
        ClientStream clientStream = this.stream;
        if (clientStream != null) {
            return clientStream.getAttributes();
        }
        return Attributes.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeObserver(ClientCall.Listener<RespT> listener, Status status, Metadata metadata) {
        listener.onClose(status, metadata);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("method", this.method).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public class ClientStreamListenerImpl implements ClientStreamListener {
        private Status exceptionStatus;
        private final ClientCall.Listener<RespT> observer;

        public ClientStreamListenerImpl(ClientCall.Listener<RespT> listener) {
            this.observer = (ClientCall.Listener) Preconditions.checkNotNull(listener, "observer");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void exceptionThrown(Status status) {
            this.exceptionStatus = status;
            ClientCallImpl.this.stream.cancel(status);
        }

        @Override // io.grpc.internal.ClientStreamListener
        public void headersRead(final Metadata metadata) {
            PerfMark.startTask("ClientStreamListener.headersRead", ClientCallImpl.this.tag);
            final Link linkOut = PerfMark.linkOut();
            try {
                ClientCallImpl.this.callExecutor.execute(new ContextRunnable() { // from class: io.grpc.internal.ClientCallImpl.ClientStreamListenerImpl.1HeadersRead
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(ClientCallImpl.this.context);
                    }

                    @Override // io.grpc.internal.ContextRunnable
                    public void runInContext() {
                        PerfMark.startTask("ClientCall$Listener.headersRead", ClientCallImpl.this.tag);
                        PerfMark.linkIn(linkOut);
                        try {
                            runInternal();
                        } finally {
                            PerfMark.stopTask("ClientCall$Listener.headersRead", ClientCallImpl.this.tag);
                        }
                    }

                    private void runInternal() {
                        if (ClientStreamListenerImpl.this.exceptionStatus != null) {
                            return;
                        }
                        try {
                            ClientStreamListenerImpl.this.observer.onHeaders(metadata);
                        } catch (Throwable th) {
                            ClientStreamListenerImpl.this.exceptionThrown(Status.CANCELLED.withCause(th).withDescription("Failed to read headers"));
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ClientStreamListener.headersRead", ClientCallImpl.this.tag);
            }
        }

        @Override // io.grpc.internal.StreamListener
        public void messagesAvailable(final StreamListener.MessageProducer messageProducer) {
            PerfMark.startTask("ClientStreamListener.messagesAvailable", ClientCallImpl.this.tag);
            final Link linkOut = PerfMark.linkOut();
            try {
                ClientCallImpl.this.callExecutor.execute(new ContextRunnable() { // from class: io.grpc.internal.ClientCallImpl.ClientStreamListenerImpl.1MessagesAvailable
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(ClientCallImpl.this.context);
                    }

                    @Override // io.grpc.internal.ContextRunnable
                    public void runInContext() {
                        PerfMark.startTask("ClientCall$Listener.messagesAvailable", ClientCallImpl.this.tag);
                        PerfMark.linkIn(linkOut);
                        try {
                            runInternal();
                        } finally {
                            PerfMark.stopTask("ClientCall$Listener.messagesAvailable", ClientCallImpl.this.tag);
                        }
                    }

                    private void runInternal() {
                        if (ClientStreamListenerImpl.this.exceptionStatus != null) {
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
                                    ClientStreamListenerImpl.this.observer.onMessage(ClientCallImpl.this.method.parseResponse(next));
                                    next.close();
                                } catch (Throwable th) {
                                    GrpcUtil.closeQuietly(next);
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                GrpcUtil.closeQuietly(messageProducer);
                                ClientStreamListenerImpl.this.exceptionThrown(Status.CANCELLED.withCause(th2).withDescription("Failed to read message."));
                                return;
                            }
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ClientStreamListener.messagesAvailable", ClientCallImpl.this.tag);
            }
        }

        @Override // io.grpc.internal.ClientStreamListener
        public void closed(Status status, Metadata metadata) {
            closed(status, ClientStreamListener.RpcProgress.PROCESSED, metadata);
        }

        @Override // io.grpc.internal.ClientStreamListener
        public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
            PerfMark.startTask("ClientStreamListener.closed", ClientCallImpl.this.tag);
            try {
                closedInternal(status, rpcProgress, metadata);
            } finally {
                PerfMark.stopTask("ClientStreamListener.closed", ClientCallImpl.this.tag);
            }
        }

        private void closedInternal(final Status status, ClientStreamListener.RpcProgress rpcProgress, final Metadata metadata) {
            Deadline effectiveDeadline = ClientCallImpl.this.effectiveDeadline();
            if (status.getCode() == Status.Code.CANCELLED && effectiveDeadline != null && effectiveDeadline.isExpired()) {
                InsightBuilder insightBuilder = new InsightBuilder();
                ClientCallImpl.this.stream.appendTimeoutInsight(insightBuilder);
                status = Status.DEADLINE_EXCEEDED.augmentDescription("ClientCall was cancelled at or after deadline. " + insightBuilder);
                metadata = new Metadata();
            }
            final Link linkOut = PerfMark.linkOut();
            ClientCallImpl.this.callExecutor.execute(new ContextRunnable() { // from class: io.grpc.internal.ClientCallImpl.ClientStreamListenerImpl.1StreamClosed
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(ClientCallImpl.this.context);
                }

                @Override // io.grpc.internal.ContextRunnable
                public void runInContext() {
                    PerfMark.startTask("ClientCall$Listener.onClose", ClientCallImpl.this.tag);
                    PerfMark.linkIn(linkOut);
                    try {
                        runInternal();
                    } finally {
                        PerfMark.stopTask("ClientCall$Listener.onClose", ClientCallImpl.this.tag);
                    }
                }

                private void runInternal() {
                    Status status2 = status;
                    Metadata metadata2 = metadata;
                    if (ClientStreamListenerImpl.this.exceptionStatus != null) {
                        status2 = ClientStreamListenerImpl.this.exceptionStatus;
                        metadata2 = new Metadata();
                    }
                    ClientCallImpl.this.cancelListenersShouldBeRemoved = true;
                    try {
                        ClientCallImpl.this.closeObserver(ClientStreamListenerImpl.this.observer, status2, metadata2);
                    } finally {
                        ClientCallImpl.this.removeContextListenerAndCancelDeadlineFuture();
                        ClientCallImpl.this.channelCallsTracer.reportCallEnded(status2.isOk());
                    }
                }
            });
        }

        @Override // io.grpc.internal.StreamListener
        public void onReady() {
            if (ClientCallImpl.this.method.getType().clientSendsOneMessage()) {
                return;
            }
            PerfMark.startTask("ClientStreamListener.onReady", ClientCallImpl.this.tag);
            final Link linkOut = PerfMark.linkOut();
            try {
                ClientCallImpl.this.callExecutor.execute(new ContextRunnable() { // from class: io.grpc.internal.ClientCallImpl.ClientStreamListenerImpl.1StreamOnReady
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(ClientCallImpl.this.context);
                    }

                    @Override // io.grpc.internal.ContextRunnable
                    public void runInContext() {
                        PerfMark.startTask("ClientCall$Listener.onReady", ClientCallImpl.this.tag);
                        PerfMark.linkIn(linkOut);
                        try {
                            runInternal();
                        } finally {
                            PerfMark.stopTask("ClientCall$Listener.onReady", ClientCallImpl.this.tag);
                        }
                    }

                    private void runInternal() {
                        if (ClientStreamListenerImpl.this.exceptionStatus != null) {
                            return;
                        }
                        try {
                            ClientStreamListenerImpl.this.observer.onReady();
                        } catch (Throwable th) {
                            ClientStreamListenerImpl.this.exceptionThrown(Status.CANCELLED.withCause(th).withDescription("Failed to call onReady."));
                        }
                    }
                });
            } finally {
                PerfMark.stopTask("ClientStreamListener.onReady", ClientCallImpl.this.tag);
            }
        }
    }
}
