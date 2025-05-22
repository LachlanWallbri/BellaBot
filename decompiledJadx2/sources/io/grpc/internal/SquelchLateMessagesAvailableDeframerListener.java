package io.grpc.internal;

import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;
import java.io.Closeable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class SquelchLateMessagesAvailableDeframerListener extends ForwardingDeframerListener {
    private boolean closed;
    private final MessageDeframer.Listener delegate;

    public SquelchLateMessagesAvailableDeframerListener(MessageDeframer.Listener listener) {
        this.delegate = listener;
    }

    @Override // io.grpc.internal.ForwardingDeframerListener
    protected MessageDeframer.Listener delegate() {
        return this.delegate;
    }

    @Override // io.grpc.internal.ForwardingDeframerListener, io.grpc.internal.MessageDeframer.Listener
    public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
        if (this.closed) {
            if (messageProducer instanceof Closeable) {
                GrpcUtil.closeQuietly((Closeable) messageProducer);
                return;
            }
            return;
        }
        super.messagesAvailable(messageProducer);
    }

    @Override // io.grpc.internal.ForwardingDeframerListener, io.grpc.internal.MessageDeframer.Listener
    public void deframerClosed(boolean z) {
        this.closed = true;
        super.deframerClosed(z);
    }

    @Override // io.grpc.internal.ForwardingDeframerListener, io.grpc.internal.MessageDeframer.Listener
    public void deframeFailed(Throwable th) {
        this.closed = true;
        super.deframeFailed(th);
    }
}
