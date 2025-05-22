package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.Decompressor;
import io.grpc.internal.ApplicationThreadDeframerListener;
import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import java.io.Closeable;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class MigratingThreadDeframer implements ThreadOptimizedDeframer {
    private final ApplicationThreadDeframerListener appListener;
    private final MessageDeframer deframer;
    private boolean deframerOnTransportThread;
    private boolean messageProducerEnqueued;
    private final MigratingDeframerListener migratingListener;
    private final ApplicationThreadDeframerListener.TransportExecutor transportExecutor;
    private final MessageDeframer.Listener transportListener;
    private final DeframeMessageProducer messageProducer = new DeframeMessageProducer();
    private final Object lock = new Object();
    private final Queue<InterfaceC6158Op> opQueue = new ArrayDeque();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.internal.MigratingThreadDeframer$Op */
    /* loaded from: classes7.dex */
    public interface InterfaceC6158Op {
        void run(boolean z);
    }

    public MigratingThreadDeframer(MessageDeframer.Listener listener, ApplicationThreadDeframerListener.TransportExecutor transportExecutor, MessageDeframer messageDeframer) {
        this.transportListener = new SquelchLateMessagesAvailableDeframerListener((MessageDeframer.Listener) Preconditions.checkNotNull(listener, "listener"));
        this.transportExecutor = (ApplicationThreadDeframerListener.TransportExecutor) Preconditions.checkNotNull(transportExecutor, "transportExecutor");
        this.appListener = new ApplicationThreadDeframerListener(this.transportListener, transportExecutor);
        this.migratingListener = new MigratingDeframerListener(this.appListener);
        messageDeframer.setListener(this.migratingListener);
        this.deframer = messageDeframer;
    }

    @Override // io.grpc.internal.Deframer
    public void setMaxInboundMessageSize(int i) {
        this.deframer.setMaxInboundMessageSize(i);
    }

    @Override // io.grpc.internal.Deframer
    public void setDecompressor(Decompressor decompressor) {
        this.deframer.setDecompressor(decompressor);
    }

    @Override // io.grpc.internal.Deframer
    public void setFullStreamDecompressor(GzipInflatingBuffer gzipInflatingBuffer) {
        this.deframer.setFullStreamDecompressor(gzipInflatingBuffer);
    }

    private boolean runWhereAppropriate(InterfaceC6158Op interfaceC6158Op) {
        return runWhereAppropriate(interfaceC6158Op, true);
    }

    private boolean runWhereAppropriate(InterfaceC6158Op interfaceC6158Op, boolean z) {
        boolean z2;
        boolean z3;
        synchronized (this.lock) {
            z2 = this.deframerOnTransportThread;
            z3 = this.messageProducerEnqueued;
            if (!z2) {
                this.opQueue.offer(interfaceC6158Op);
                this.messageProducerEnqueued = true;
            }
        }
        if (z2) {
            interfaceC6158Op.run(true);
            return true;
        }
        if (z3) {
            return false;
        }
        if (z) {
            PerfMark.startTask("MigratingThreadDeframer.messageAvailable");
            try {
                this.transportListener.messagesAvailable(this.messageProducer);
                return false;
            } finally {
                PerfMark.stopTask("MigratingThreadDeframer.messageAvailable");
            }
        }
        final Link linkOut = PerfMark.linkOut();
        this.transportExecutor.runOnTransportThread(new Runnable() { // from class: io.grpc.internal.MigratingThreadDeframer.1
            @Override // java.lang.Runnable
            public void run() {
                PerfMark.startTask("MigratingThreadDeframer.messageAvailable");
                PerfMark.linkIn(linkOut);
                try {
                    MigratingThreadDeframer.this.transportListener.messagesAvailable(MigratingThreadDeframer.this.messageProducer);
                } finally {
                    PerfMark.stopTask("MigratingThreadDeframer.messageAvailable");
                }
            }
        });
        return false;
    }

    @Override // io.grpc.internal.ThreadOptimizedDeframer, io.grpc.internal.Deframer
    public void request(final int i) {
        runWhereAppropriate(new InterfaceC6158Op() { // from class: io.grpc.internal.MigratingThreadDeframer.1RequestOp
            @Override // io.grpc.internal.MigratingThreadDeframer.InterfaceC6158Op
            public void run(boolean z) {
                if (z) {
                    final Link linkOut = PerfMark.linkOut();
                    MigratingThreadDeframer.this.transportExecutor.runOnTransportThread(new Runnable() { // from class: io.grpc.internal.MigratingThreadDeframer.1RequestOp.1
                        @Override // java.lang.Runnable
                        public void run() {
                            PerfMark.startTask("MigratingThreadDeframer.request");
                            PerfMark.linkIn(linkOut);
                            try {
                                MigratingThreadDeframer.this.requestFromTransportThread(i);
                            } finally {
                                PerfMark.stopTask("MigratingThreadDeframer.request");
                            }
                        }
                    });
                } else {
                    PerfMark.startTask("MigratingThreadDeframer.request");
                    try {
                        MigratingThreadDeframer.this.deframer.request(i);
                    } finally {
                        try {
                        } finally {
                        }
                    }
                }
            }
        }, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestFromTransportThread(final int i) {
        runWhereAppropriate(new InterfaceC6158Op() { // from class: io.grpc.internal.MigratingThreadDeframer.1RequestAgainOp
            @Override // io.grpc.internal.MigratingThreadDeframer.InterfaceC6158Op
            public void run(boolean z) {
                if (z) {
                    try {
                        MigratingThreadDeframer.this.deframer.request(i);
                    } catch (Throwable th) {
                        MigratingThreadDeframer.this.appListener.deframeFailed(th);
                        MigratingThreadDeframer.this.deframer.close();
                    }
                    if (MigratingThreadDeframer.this.deframer.hasPendingDeliveries()) {
                        return;
                    }
                    synchronized (MigratingThreadDeframer.this.lock) {
                        PerfMark.event("MigratingThreadDeframer.deframerOnApplicationThread");
                        MigratingThreadDeframer.this.migratingListener.setDelegate(MigratingThreadDeframer.this.appListener);
                        MigratingThreadDeframer.this.deframerOnTransportThread = false;
                    }
                    return;
                }
                MigratingThreadDeframer.this.request(i);
            }
        });
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.internal.MigratingThreadDeframer$1DeframeOp, reason: invalid class name */
    /* loaded from: classes7.dex */
    class C1DeframeOp implements InterfaceC6158Op, Closeable {
        final /* synthetic */ ReadableBuffer val$data;

        C1DeframeOp(ReadableBuffer readableBuffer) {
            this.val$data = readableBuffer;
        }

        @Override // io.grpc.internal.MigratingThreadDeframer.InterfaceC6158Op
        public void run(boolean z) {
            PerfMark.startTask("MigratingThreadDeframer.deframe");
            try {
                if (z) {
                    MigratingThreadDeframer.this.deframer.deframe(this.val$data);
                    return;
                }
                try {
                    MigratingThreadDeframer.this.deframer.deframe(this.val$data);
                } catch (Throwable th) {
                    MigratingThreadDeframer.this.appListener.deframeFailed(th);
                    MigratingThreadDeframer.this.deframer.close();
                }
            } finally {
                PerfMark.stopTask("MigratingThreadDeframer.deframe");
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.val$data.close();
        }
    }

    @Override // io.grpc.internal.Deframer
    public void deframe(ReadableBuffer readableBuffer) {
        runWhereAppropriate(new C1DeframeOp(readableBuffer));
    }

    @Override // io.grpc.internal.Deframer
    public void closeWhenComplete() {
        runWhereAppropriate(new InterfaceC6158Op() { // from class: io.grpc.internal.MigratingThreadDeframer.1CloseWhenCompleteOp
            @Override // io.grpc.internal.MigratingThreadDeframer.InterfaceC6158Op
            public void run(boolean z) {
                MigratingThreadDeframer.this.deframer.closeWhenComplete();
            }
        });
    }

    @Override // io.grpc.internal.Deframer
    public void close() {
        if (runWhereAppropriate(new InterfaceC6158Op() { // from class: io.grpc.internal.MigratingThreadDeframer.1CloseOp
            @Override // io.grpc.internal.MigratingThreadDeframer.InterfaceC6158Op
            public void run(boolean z) {
                MigratingThreadDeframer.this.deframer.close();
            }
        })) {
            return;
        }
        this.deframer.stopDelivery();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public class DeframeMessageProducer implements StreamListener.MessageProducer, Closeable {
        DeframeMessageProducer() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:12:0x002d, code lost:
        
            if (r4.this$0.deframer.hasPendingDeliveries() == false) goto L12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x002f, code lost:
        
            io.perfmark.PerfMark.event("MigratingThreadDeframer.deframerOnTransportThread");
            r4.this$0.migratingListener.setDelegate(r4.this$0.transportListener);
            r4.this$0.deframerOnTransportThread = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0049, code lost:
        
            r4.this$0.messageProducerEnqueued = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0050, code lost:
        
            return null;
         */
        @Override // io.grpc.internal.StreamListener.MessageProducer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public InputStream next() {
            InterfaceC6158Op interfaceC6158Op;
            while (true) {
                InputStream messageReadQueuePoll = MigratingThreadDeframer.this.appListener.messageReadQueuePoll();
                if (messageReadQueuePoll != null) {
                    return messageReadQueuePoll;
                }
                synchronized (MigratingThreadDeframer.this.lock) {
                    interfaceC6158Op = (InterfaceC6158Op) MigratingThreadDeframer.this.opQueue.poll();
                    if (interfaceC6158Op == null) {
                        break;
                    }
                }
                interfaceC6158Op.run(false);
            }
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            InterfaceC6158Op interfaceC6158Op;
            while (true) {
                synchronized (MigratingThreadDeframer.this.lock) {
                    do {
                        interfaceC6158Op = (InterfaceC6158Op) MigratingThreadDeframer.this.opQueue.poll();
                        if (interfaceC6158Op == null) {
                            break;
                        }
                    } while (!(interfaceC6158Op instanceof Closeable));
                    if (interfaceC6158Op == null) {
                        MigratingThreadDeframer.this.messageProducerEnqueued = false;
                        return;
                    }
                }
                GrpcUtil.closeQuietly((Closeable) interfaceC6158Op);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static class MigratingDeframerListener extends ForwardingDeframerListener {
        private MessageDeframer.Listener delegate;

        public MigratingDeframerListener(MessageDeframer.Listener listener) {
            setDelegate(listener);
        }

        @Override // io.grpc.internal.ForwardingDeframerListener
        protected MessageDeframer.Listener delegate() {
            return this.delegate;
        }

        public void setDelegate(MessageDeframer.Listener listener) {
            this.delegate = (MessageDeframer.Listener) Preconditions.checkNotNull(listener, "delegate");
        }
    }
}
