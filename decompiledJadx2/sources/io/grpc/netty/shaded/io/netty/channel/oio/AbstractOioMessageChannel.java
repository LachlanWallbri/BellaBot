package io.grpc.netty.shaded.io.netty.channel.oio;

import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public abstract class AbstractOioMessageChannel extends AbstractOioChannel {
    private final List<Object> readBuf;

    protected abstract int doReadMessages(List<Object> list) throws Exception;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractOioMessageChannel(Channel channel) {
        super(channel);
        this.readBuf = new ArrayList();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.oio.AbstractOioChannel
    protected void doRead() {
        boolean z;
        if (this.readPending) {
            boolean z2 = false;
            this.readPending = false;
            ChannelConfig config = config();
            ChannelPipeline pipeline = pipeline();
            RecvByteBufAllocator.Handle recvBufAllocHandle = unsafe().recvBufAllocHandle();
            recvBufAllocHandle.reset(config);
            Throwable th = null;
            do {
                try {
                    int doReadMessages = doReadMessages(this.readBuf);
                    if (doReadMessages == 0) {
                        break;
                    }
                    if (doReadMessages < 0) {
                        z = true;
                        break;
                    }
                    recvBufAllocHandle.incMessagesRead(doReadMessages);
                } catch (Throwable th2) {
                    th = th2;
                }
            } while (recvBufAllocHandle.continueReading());
            z = false;
            int size = this.readBuf.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    this.readPending = false;
                    pipeline.fireChannelRead(this.readBuf.get(i));
                }
                this.readBuf.clear();
                recvBufAllocHandle.readComplete();
                pipeline.fireChannelReadComplete();
                z2 = true;
            }
            if (th != null) {
                if (th instanceof IOException) {
                    z = true;
                }
                pipeline.fireExceptionCaught(th);
            }
            if (z) {
                if (isOpen()) {
                    unsafe().close(unsafe().voidPromise());
                }
            } else if (this.readPending || config.isAutoRead() || (!z2 && isActive())) {
                read();
            }
        }
    }
}
