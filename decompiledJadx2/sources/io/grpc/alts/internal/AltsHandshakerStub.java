package io.grpc.alts.internal;

import com.google.common.base.Optional;
import io.grpc.alts.internal.HandshakerServiceGrpc;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class AltsHandshakerStub {
    private static final long HANDSHAKE_RPC_DEADLINE_SECS = 20;
    private final AtomicReference<String> exceptionMessage;
    private final StreamObserver<HandshakerResp> reader;
    private final ArrayBlockingQueue<Optional<HandshakerResp>> responseQueue;
    private final HandshakerServiceGrpc.HandshakerServiceStub serviceStub;
    private StreamObserver<HandshakerReq> writer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AltsHandshakerStub(HandshakerServiceGrpc.HandshakerServiceStub handshakerServiceStub) {
        this.reader = new Reader();
        this.responseQueue = new ArrayBlockingQueue<>(1);
        this.exceptionMessage = new AtomicReference<>();
        this.serviceStub = handshakerServiceStub;
    }

    AltsHandshakerStub() {
        this.reader = new Reader();
        this.responseQueue = new ArrayBlockingQueue<>(1);
        this.exceptionMessage = new AtomicReference<>();
        this.serviceStub = null;
    }

    AltsHandshakerStub(StreamObserver<HandshakerReq> streamObserver) {
        this.reader = new Reader();
        this.responseQueue = new ArrayBlockingQueue<>(1);
        this.exceptionMessage = new AtomicReference<>();
        this.writer = streamObserver;
        this.serviceStub = null;
    }

    StreamObserver<HandshakerResp> getReaderForTest() {
        return this.reader;
    }

    public HandshakerResp send(HandshakerReq handshakerReq) throws InterruptedException, IOException {
        createWriterIfNull();
        maybeThrowIoException();
        if (!this.responseQueue.isEmpty()) {
            throw new IOException("Received an unexpected response.");
        }
        this.writer.onNext(handshakerReq);
        Optional<HandshakerResp> take = this.responseQueue.take();
        if (!take.isPresent()) {
            maybeThrowIoException();
        }
        return take.get();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void createWriterIfNull() {
        if (this.writer == null) {
            this.writer = ((HandshakerServiceGrpc.HandshakerServiceStub) this.serviceStub.withDeadlineAfter(HANDSHAKE_RPC_DEADLINE_SECS, TimeUnit.SECONDS)).doHandshake(this.reader);
        }
    }

    private void maybeThrowIoException() throws IOException {
        if (this.exceptionMessage.get() != null) {
            throw new IOException(this.exceptionMessage.get());
        }
    }

    public void close() {
        StreamObserver<HandshakerReq> streamObserver = this.writer;
        if (streamObserver != null) {
            streamObserver.onCompleted();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private class Reader implements StreamObserver<HandshakerResp> {
        private Reader() {
        }

        @Override // io.grpc.stub.StreamObserver
        public void onNext(HandshakerResp handshakerResp) {
            try {
                AltsHandshakerStub.this.responseQueue.add(Optional.m610of(handshakerResp));
            } catch (IllegalStateException unused) {
                AltsHandshakerStub.this.exceptionMessage.compareAndSet(null, "Received an unexpected response.");
                AltsHandshakerStub.this.close();
            }
        }

        @Override // io.grpc.stub.StreamObserver
        public void onError(Throwable th) {
            AltsHandshakerStub.this.exceptionMessage.compareAndSet(null, "Received a terminating error: " + th.toString());
            AltsHandshakerStub.this.responseQueue.offer(Optional.absent());
        }

        @Override // io.grpc.stub.StreamObserver
        public void onCompleted() {
            AltsHandshakerStub.this.exceptionMessage.compareAndSet(null, "Response stream closed.");
            AltsHandshakerStub.this.responseQueue.offer(Optional.absent());
        }
    }
}
