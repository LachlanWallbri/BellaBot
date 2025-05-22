package org.jboss.netty.channel;

/* loaded from: classes7.dex */
public interface ReceiveBufferSizePredictor {
    int nextReceiveBufferSize();

    void previousReceiveBufferSize(int i);
}
