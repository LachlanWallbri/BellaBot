package org.jboss.netty.channel.socket.nio;

import org.jboss.netty.channel.ReceiveBufferSizePredictor;
import org.jboss.netty.channel.ReceiveBufferSizePredictorFactory;
import org.jboss.netty.channel.socket.SocketChannelConfig;

/* loaded from: classes7.dex */
public interface NioSocketChannelConfig extends SocketChannelConfig, NioChannelConfig {
    ReceiveBufferSizePredictor getReceiveBufferSizePredictor();

    ReceiveBufferSizePredictorFactory getReceiveBufferSizePredictorFactory();

    void setReceiveBufferSizePredictor(ReceiveBufferSizePredictor receiveBufferSizePredictor);

    void setReceiveBufferSizePredictorFactory(ReceiveBufferSizePredictorFactory receiveBufferSizePredictorFactory);
}
