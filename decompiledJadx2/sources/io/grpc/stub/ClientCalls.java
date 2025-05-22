package io.grpc.stub;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.StatusRuntimeException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ClientCalls {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger logger = Logger.getLogger(ClientCalls.class.getName());
    static final CallOptions.Key<StubType> STUB_TYPE_OPTION = CallOptions.Key.create("internal-stub-type");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum StubType {
        BLOCKING,
        FUTURE,
        ASYNC
    }

    private ClientCalls() {
    }

    public static <ReqT, RespT> void asyncUnaryCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt, StreamObserver<RespT> streamObserver) {
        asyncUnaryRequestCall(clientCall, reqt, streamObserver, false);
    }

    public static <ReqT, RespT> void asyncServerStreamingCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt, StreamObserver<RespT> streamObserver) {
        asyncUnaryRequestCall(clientCall, reqt, streamObserver, true);
    }

    public static <ReqT, RespT> StreamObserver<ReqT> asyncClientStreamingCall(ClientCall<ReqT, RespT> clientCall, StreamObserver<RespT> streamObserver) {
        return asyncStreamingRequestCall(clientCall, streamObserver, false);
    }

    public static <ReqT, RespT> StreamObserver<ReqT> asyncBidiStreamingCall(ClientCall<ReqT, RespT> clientCall, StreamObserver<RespT> streamObserver) {
        return asyncStreamingRequestCall(clientCall, streamObserver, true);
    }

    public static <ReqT, RespT> RespT blockingUnaryCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt) {
        try {
            return (RespT) getUnchecked(futureUnaryCall(clientCall, reqt));
        } catch (Error e) {
            throw cancelThrow(clientCall, e);
        } catch (RuntimeException e2) {
            throw cancelThrow(clientCall, e2);
        }
    }

    public static <ReqT, RespT> RespT blockingUnaryCall(Channel channel, MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, ReqT reqt) {
        ThreadlessExecutor threadlessExecutor = new ThreadlessExecutor();
        ClientCall newCall = channel.newCall(methodDescriptor, callOptions.withOption(STUB_TYPE_OPTION, StubType.BLOCKING).withExecutor(threadlessExecutor));
        boolean z = false;
        try {
            try {
                ListenableFuture futureUnaryCall = futureUnaryCall(newCall, reqt);
                while (!futureUnaryCall.isDone()) {
                    try {
                        threadlessExecutor.waitAndDrain();
                    } catch (InterruptedException e) {
                        try {
                            newCall.cancel("Thread interrupted", e);
                            z = true;
                        } catch (Error e2) {
                            e = e2;
                            throw cancelThrow(newCall, e);
                        } catch (RuntimeException e3) {
                            e = e3;
                            throw cancelThrow(newCall, e);
                        } catch (Throwable th) {
                            th = th;
                            z = true;
                            if (z) {
                                Thread.currentThread().interrupt();
                            }
                            throw th;
                        }
                    }
                }
                RespT respt = (RespT) getUnchecked(futureUnaryCall);
                if (z) {
                    Thread.currentThread().interrupt();
                }
                return respt;
            } catch (Error e4) {
                e = e4;
            } catch (RuntimeException e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static <ReqT, RespT> Iterator<RespT> blockingServerStreamingCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt) {
        BlockingResponseStream blockingResponseStream = new BlockingResponseStream(clientCall);
        asyncUnaryRequestCall(clientCall, reqt, blockingResponseStream.listener());
        return blockingResponseStream;
    }

    public static <ReqT, RespT> Iterator<RespT> blockingServerStreamingCall(Channel channel, MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, ReqT reqt) {
        ThreadlessExecutor threadlessExecutor = new ThreadlessExecutor();
        ClientCall newCall = channel.newCall(methodDescriptor, callOptions.withOption(STUB_TYPE_OPTION, StubType.BLOCKING).withExecutor(threadlessExecutor));
        BlockingResponseStream blockingResponseStream = new BlockingResponseStream(newCall, threadlessExecutor);
        asyncUnaryRequestCall(newCall, reqt, blockingResponseStream.listener());
        return blockingResponseStream;
    }

    public static <ReqT, RespT> ListenableFuture<RespT> futureUnaryCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt) {
        GrpcFuture grpcFuture = new GrpcFuture(clientCall);
        asyncUnaryRequestCall(clientCall, reqt, new UnaryStreamToFuture(grpcFuture));
        return grpcFuture;
    }

    private static <V> V getUnchecked(Future<V> future) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw Status.CANCELLED.withDescription("Thread interrupted").withCause(e).asRuntimeException();
        } catch (ExecutionException e2) {
            throw toStatusRuntimeException(e2.getCause());
        }
    }

    private static StatusRuntimeException toStatusRuntimeException(Throwable th) {
        for (Throwable th2 = (Throwable) Preconditions.checkNotNull(th, "t"); th2 != null; th2 = th2.getCause()) {
            if (th2 instanceof StatusException) {
                StatusException statusException = (StatusException) th2;
                return new StatusRuntimeException(statusException.getStatus(), statusException.getTrailers());
            }
            if (th2 instanceof StatusRuntimeException) {
                StatusRuntimeException statusRuntimeException = (StatusRuntimeException) th2;
                return new StatusRuntimeException(statusRuntimeException.getStatus(), statusRuntimeException.getTrailers());
            }
        }
        return Status.UNKNOWN.withDescription("unexpected exception").withCause(th).asRuntimeException();
    }

    private static RuntimeException cancelThrow(ClientCall<?, ?> clientCall, Throwable th) {
        try {
            clientCall.cancel(null, th);
        } catch (Throwable th2) {
            logger.log(Level.SEVERE, "RuntimeException encountered while closing call", th2);
        }
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        throw new AssertionError(th);
    }

    private static <ReqT, RespT> void asyncUnaryRequestCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt, StreamObserver<RespT> streamObserver, boolean z) {
        asyncUnaryRequestCall(clientCall, reqt, new StreamObserverToCallListenerAdapter(streamObserver, new CallToStreamObserverAdapter(clientCall, z)));
    }

    private static <ReqT, RespT> void asyncUnaryRequestCall(ClientCall<ReqT, RespT> clientCall, ReqT reqt, StartableListener<RespT> startableListener) {
        startCall(clientCall, startableListener);
        try {
            clientCall.sendMessage(reqt);
            clientCall.halfClose();
        } catch (Error e) {
            throw cancelThrow(clientCall, e);
        } catch (RuntimeException e2) {
            throw cancelThrow(clientCall, e2);
        }
    }

    private static <ReqT, RespT> StreamObserver<ReqT> asyncStreamingRequestCall(ClientCall<ReqT, RespT> clientCall, StreamObserver<RespT> streamObserver, boolean z) {
        CallToStreamObserverAdapter callToStreamObserverAdapter = new CallToStreamObserverAdapter(clientCall, z);
        startCall(clientCall, new StreamObserverToCallListenerAdapter(streamObserver, callToStreamObserverAdapter));
        return callToStreamObserverAdapter;
    }

    private static <ReqT, RespT> void startCall(ClientCall<ReqT, RespT> clientCall, StartableListener<RespT> startableListener) {
        clientCall.start(startableListener, new Metadata());
        startableListener.onStart();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class StartableListener<T> extends ClientCall.Listener<T> {
        abstract void onStart();

        private StartableListener() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class CallToStreamObserverAdapter<T> extends ClientCallStreamObserver<T> {
        private final ClientCall<T, ?> call;
        private boolean frozen;
        private Runnable onReadyHandler;
        private final boolean streamingResponse;
        private int initialRequest = 1;
        private boolean autoRequestEnabled = true;
        private boolean aborted = false;
        private boolean completed = false;

        CallToStreamObserverAdapter(ClientCall<T, ?> clientCall, boolean z) {
            this.call = clientCall;
            this.streamingResponse = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void freeze() {
            this.frozen = true;
        }

        @Override // io.grpc.stub.StreamObserver
        public void onNext(T t) {
            Preconditions.checkState(!this.aborted, "Stream was terminated by error, no further calls are allowed");
            Preconditions.checkState(!this.completed, "Stream is already completed, no further calls are allowed");
            this.call.sendMessage(t);
        }

        @Override // io.grpc.stub.StreamObserver
        public void onError(Throwable th) {
            this.call.cancel("Cancelled by client with StreamObserver.onError()", th);
            this.aborted = true;
        }

        @Override // io.grpc.stub.StreamObserver
        public void onCompleted() {
            this.call.halfClose();
            this.completed = true;
        }

        @Override // io.grpc.stub.ClientCallStreamObserver, io.grpc.stub.CallStreamObserver
        public boolean isReady() {
            return this.call.isReady();
        }

        @Override // io.grpc.stub.ClientCallStreamObserver, io.grpc.stub.CallStreamObserver
        public void setOnReadyHandler(Runnable runnable) {
            if (this.frozen) {
                throw new IllegalStateException("Cannot alter onReadyHandler after call started. Use ClientResponseObserver");
            }
            this.onReadyHandler = runnable;
        }

        @Override // io.grpc.stub.CallStreamObserver
        @Deprecated
        public void disableAutoInboundFlowControl() {
            disableAutoRequestWithInitial(1);
        }

        @Override // io.grpc.stub.ClientCallStreamObserver
        public void disableAutoRequestWithInitial(int i) {
            if (this.frozen) {
                throw new IllegalStateException("Cannot disable auto flow control after call started. Use ClientResponseObserver");
            }
            Preconditions.checkArgument(i >= 0, "Initial requests must be non-negative");
            this.initialRequest = i;
            this.autoRequestEnabled = false;
        }

        @Override // io.grpc.stub.ClientCallStreamObserver, io.grpc.stub.CallStreamObserver
        public void request(int i) {
            if (!this.streamingResponse && i == 1) {
                this.call.request(2);
            } else {
                this.call.request(i);
            }
        }

        @Override // io.grpc.stub.ClientCallStreamObserver, io.grpc.stub.CallStreamObserver
        public void setMessageCompression(boolean z) {
            this.call.setMessageCompression(z);
        }

        @Override // io.grpc.stub.ClientCallStreamObserver
        public void cancel(@Nullable String str, @Nullable Throwable th) {
            this.call.cancel(str, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class StreamObserverToCallListenerAdapter<ReqT, RespT> extends StartableListener<RespT> {
        private final CallToStreamObserverAdapter<ReqT> adapter;
        private boolean firstResponseReceived;
        private final StreamObserver<RespT> observer;

        @Override // io.grpc.ClientCall.Listener
        public void onHeaders(Metadata metadata) {
        }

        StreamObserverToCallListenerAdapter(StreamObserver<RespT> streamObserver, CallToStreamObserverAdapter<ReqT> callToStreamObserverAdapter) {
            super();
            this.observer = streamObserver;
            this.adapter = callToStreamObserverAdapter;
            if (streamObserver instanceof ClientResponseObserver) {
                ((ClientResponseObserver) streamObserver).beforeStart(callToStreamObserverAdapter);
            }
            callToStreamObserverAdapter.freeze();
        }

        @Override // io.grpc.ClientCall.Listener
        public void onMessage(RespT respt) {
            if (this.firstResponseReceived && !((CallToStreamObserverAdapter) this.adapter).streamingResponse) {
                throw Status.INTERNAL.withDescription("More than one responses received for unary or client-streaming call").asRuntimeException();
            }
            this.firstResponseReceived = true;
            this.observer.onNext(respt);
            if (((CallToStreamObserverAdapter) this.adapter).streamingResponse && ((CallToStreamObserverAdapter) this.adapter).autoRequestEnabled) {
                this.adapter.request(1);
            }
        }

        @Override // io.grpc.ClientCall.Listener
        public void onClose(Status status, Metadata metadata) {
            if (status.isOk()) {
                this.observer.onCompleted();
            } else {
                this.observer.onError(status.asRuntimeException(metadata));
            }
        }

        @Override // io.grpc.ClientCall.Listener
        public void onReady() {
            if (((CallToStreamObserverAdapter) this.adapter).onReadyHandler != null) {
                ((CallToStreamObserverAdapter) this.adapter).onReadyHandler.run();
            }
        }

        @Override // io.grpc.stub.ClientCalls.StartableListener
        void onStart() {
            if (((CallToStreamObserverAdapter) this.adapter).initialRequest > 0) {
                CallToStreamObserverAdapter<ReqT> callToStreamObserverAdapter = this.adapter;
                callToStreamObserverAdapter.request(((CallToStreamObserverAdapter) callToStreamObserverAdapter).initialRequest);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class UnaryStreamToFuture<RespT> extends StartableListener<RespT> {
        private final GrpcFuture<RespT> responseFuture;
        private RespT value;

        @Override // io.grpc.ClientCall.Listener
        public void onHeaders(Metadata metadata) {
        }

        UnaryStreamToFuture(GrpcFuture<RespT> grpcFuture) {
            super();
            this.responseFuture = grpcFuture;
        }

        @Override // io.grpc.ClientCall.Listener
        public void onMessage(RespT respt) {
            if (this.value != null) {
                throw Status.INTERNAL.withDescription("More than one value received for unary call").asRuntimeException();
            }
            this.value = respt;
        }

        @Override // io.grpc.ClientCall.Listener
        public void onClose(Status status, Metadata metadata) {
            if (status.isOk()) {
                if (this.value == null) {
                    this.responseFuture.setException(Status.INTERNAL.withDescription("No value received for unary call").asRuntimeException(metadata));
                }
                this.responseFuture.set(this.value);
                return;
            }
            this.responseFuture.setException(status.asRuntimeException(metadata));
        }

        @Override // io.grpc.stub.ClientCalls.StartableListener
        void onStart() {
            ((GrpcFuture) this.responseFuture).call.request(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class GrpcFuture<RespT> extends AbstractFuture<RespT> {
        private final ClientCall<?, RespT> call;

        GrpcFuture(ClientCall<?, RespT> clientCall) {
            this.call = clientCall;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        protected void interruptTask() {
            this.call.cancel("GrpcFuture was cancelled", null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractFuture
        public boolean set(@Nullable RespT respt) {
            return super.set(respt);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractFuture
        public boolean setException(Throwable th) {
            return super.setException(th);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractFuture
        public String pendingToString() {
            return MoreObjects.toStringHelper(this).add("clientCall", this.call).toString();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class BlockingResponseStream<T> implements Iterator<T> {
        private final BlockingQueue<Object> buffer;
        private final ClientCall<?, T> call;
        private Object last;
        private final StartableListener<T> listener;
        private final ThreadlessExecutor threadless;

        BlockingResponseStream(ClientCall<?, T> clientCall) {
            this(clientCall, null);
        }

        BlockingResponseStream(ClientCall<?, T> clientCall, ThreadlessExecutor threadlessExecutor) {
            this.buffer = new ArrayBlockingQueue(3);
            this.listener = new QueuingListener();
            this.call = clientCall;
            this.threadless = threadlessExecutor;
        }

        StartableListener<T> listener() {
            return this.listener;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0 */
        /* JADX WARN: Type inference failed for: r0v10 */
        /* JADX WARN: Type inference failed for: r0v11 */
        /* JADX WARN: Type inference failed for: r0v3 */
        /* JADX WARN: Type inference failed for: r0v5 */
        /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v9 */
        private Object waitForNext() {
            Object poll;
            ?? r0 = 1;
            r0 = 1;
            r0 = 1;
            r0 = 1;
            boolean z = false;
            try {
                try {
                    if (this.threadless == null) {
                        while (true) {
                            try {
                                r0 = this.buffer.take();
                                break;
                            } catch (InterruptedException e) {
                                this.call.cancel("Thread interrupted", e);
                                z = r0;
                            }
                        }
                        if (z) {
                            Thread.currentThread().interrupt();
                        }
                        return r0;
                    }
                    while (true) {
                        poll = this.buffer.poll();
                        if (poll != null) {
                            break;
                        }
                        try {
                            this.threadless.waitAndDrain();
                        } catch (InterruptedException e2) {
                            this.call.cancel("Thread interrupted", e2);
                            z = true;
                        }
                    }
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                    return poll;
                } catch (Throwable th) {
                    z = r0;
                    th = th;
                }
                z = r0;
                th = th;
            } catch (Throwable th2) {
                th = th2;
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
            throw th;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Object obj;
            while (true) {
                obj = this.last;
                if (obj != null) {
                    break;
                }
                this.last = waitForNext();
            }
            if (!(obj instanceof StatusRuntimeException)) {
                return obj != this;
            }
            StatusRuntimeException statusRuntimeException = (StatusRuntimeException) obj;
            throw statusRuntimeException.getStatus().asRuntimeException(statusRuntimeException.getTrailers());
        }

        @Override // java.util.Iterator
        public T next() {
            Object obj = this.last;
            if (!(obj instanceof StatusRuntimeException) && obj != this) {
                this.call.request(1);
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T t = (T) this.last;
            this.last = null;
            return t;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes7.dex */
        private final class QueuingListener extends StartableListener<T> {
            private boolean done;

            @Override // io.grpc.ClientCall.Listener
            public void onHeaders(Metadata metadata) {
            }

            QueuingListener() {
                super();
                this.done = false;
            }

            @Override // io.grpc.ClientCall.Listener
            public void onMessage(T t) {
                Preconditions.checkState(!this.done, "ClientCall already closed");
                BlockingResponseStream.this.buffer.add(t);
            }

            @Override // io.grpc.ClientCall.Listener
            public void onClose(Status status, Metadata metadata) {
                Preconditions.checkState(!this.done, "ClientCall already closed");
                if (status.isOk()) {
                    BlockingResponseStream.this.buffer.add(BlockingResponseStream.this);
                } else {
                    BlockingResponseStream.this.buffer.add(status.asRuntimeException(metadata));
                }
                this.done = true;
            }

            @Override // io.grpc.stub.ClientCalls.StartableListener
            void onStart() {
                BlockingResponseStream.this.call.request(1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class ThreadlessExecutor extends ConcurrentLinkedQueue<Runnable> implements Executor {
        private static final Logger log = Logger.getLogger(ThreadlessExecutor.class.getName());
        private volatile Thread waiter;

        ThreadlessExecutor() {
        }

        public void waitAndDrain() throws InterruptedException {
            Runnable poll;
            throwIfInterrupted();
            Runnable poll2 = poll();
            if (poll2 == null) {
                this.waiter = Thread.currentThread();
                while (true) {
                    try {
                        poll = poll();
                        if (poll != null) {
                            break;
                        }
                        LockSupport.park(this);
                        throwIfInterrupted();
                    } catch (Throwable th) {
                        this.waiter = null;
                        throw th;
                    }
                }
                this.waiter = null;
                poll2 = poll;
            }
            do {
                try {
                    poll2.run();
                } catch (Throwable th2) {
                    log.log(Level.WARNING, "Runnable threw exception", th2);
                }
                poll2 = poll();
            } while (poll2 != null);
        }

        private static void throwIfInterrupted() throws InterruptedException {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            add(runnable);
            LockSupport.unpark(this.waiter);
        }
    }
}
