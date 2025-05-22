package io.grpc.internal;

import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
abstract class ForwardingDeframerListener implements MessageDeframer.Listener {
    protected abstract MessageDeframer.Listener delegate();

    @Override // io.grpc.internal.MessageDeframer.Listener
    public void bytesRead(int i) {
        delegate().bytesRead(i);
    }

    @Override // io.grpc.internal.MessageDeframer.Listener
    public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
        delegate().messagesAvailable(messageProducer);
    }

    @Override // io.grpc.internal.MessageDeframer.Listener
    public void deframerClosed(boolean z) {
        delegate().deframerClosed(z);
    }

    @Override // io.grpc.internal.MessageDeframer.Listener
    public void deframeFailed(Throwable th) {
        delegate().deframeFailed(th);
    }
}
