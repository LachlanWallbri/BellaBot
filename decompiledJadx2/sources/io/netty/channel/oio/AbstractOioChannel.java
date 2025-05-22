package io.netty.channel.oio;

import io.netty.channel.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.channel.ThreadPerChannelEventLoop;
import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public abstract class AbstractOioChannel extends AbstractChannel {
    protected static final int SO_TIMEOUT = 1000;
    private final Runnable clearReadPendingRunnable;
    boolean readPending;
    private final Runnable readTask;

    protected abstract void doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception;

    protected abstract void doRead();

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractOioChannel(Channel channel) {
        super(channel);
        this.readTask = new Runnable() { // from class: io.netty.channel.oio.AbstractOioChannel.1
            @Override // java.lang.Runnable
            public void run() {
                AbstractOioChannel.this.doRead();
            }
        };
        this.clearReadPendingRunnable = new Runnable() { // from class: io.netty.channel.oio.AbstractOioChannel.2
            @Override // java.lang.Runnable
            public void run() {
                AbstractOioChannel.this.readPending = false;
            }
        };
    }

    @Override // io.netty.channel.AbstractChannel
    protected AbstractChannel.AbstractUnsafe newUnsafe() {
        return new DefaultOioUnsafe();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    private final class DefaultOioUnsafe extends AbstractChannel.AbstractUnsafe {
        private DefaultOioUnsafe() {
            super();
        }

        @Override // io.netty.channel.Channel.Unsafe
        public void connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
            if (channelPromise.setUncancellable() && ensureOpen(channelPromise)) {
                try {
                    boolean isActive = AbstractOioChannel.this.isActive();
                    AbstractOioChannel.this.doConnect(socketAddress, socketAddress2);
                    boolean isActive2 = AbstractOioChannel.this.isActive();
                    safeSetSuccess(channelPromise);
                    if (isActive || !isActive2) {
                        return;
                    }
                    AbstractOioChannel.this.pipeline().fireChannelActive();
                } catch (Throwable th) {
                    safeSetFailure(channelPromise, annotateConnectException(th, socketAddress));
                    closeIfClosed();
                }
            }
        }
    }

    @Override // io.netty.channel.AbstractChannel
    protected boolean isCompatible(EventLoop eventLoop) {
        return eventLoop instanceof ThreadPerChannelEventLoop;
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doBeginRead() throws Exception {
        if (this.readPending) {
            return;
        }
        this.readPending = true;
        eventLoop().execute(this.readTask);
    }

    @Deprecated
    protected boolean isReadPending() {
        return this.readPending;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Deprecated
    public void setReadPending(final boolean z) {
        if (isRegistered()) {
            EventLoop eventLoop = eventLoop();
            if (eventLoop.inEventLoop()) {
                this.readPending = z;
                return;
            } else {
                eventLoop.execute(new Runnable() { // from class: io.netty.channel.oio.AbstractOioChannel.3
                    @Override // java.lang.Runnable
                    public void run() {
                        AbstractOioChannel.this.readPending = z;
                    }
                });
                return;
            }
        }
        this.readPending = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void clearReadPending() {
        if (isRegistered()) {
            EventLoop eventLoop = eventLoop();
            if (eventLoop.inEventLoop()) {
                this.readPending = false;
                return;
            } else {
                eventLoop.execute(this.clearReadPendingRunnable);
                return;
            }
        }
        this.readPending = false;
    }
}
