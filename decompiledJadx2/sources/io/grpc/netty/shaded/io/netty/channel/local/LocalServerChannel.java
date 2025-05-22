package io.grpc.netty.shaded.io.netty.channel.local;

import io.grpc.netty.shaded.io.netty.channel.AbstractServerChannel;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.channel.PreferHeapByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.SingleThreadEventLoop;
import io.grpc.netty.shaded.io.netty.util.concurrent.SingleThreadEventExecutor;
import java.net.SocketAddress;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class LocalServerChannel extends AbstractServerChannel {
    private volatile boolean acceptInProgress;
    private volatile LocalAddress localAddress;
    private volatile int state;
    private final ChannelConfig config = new DefaultChannelConfig(this);
    private final Queue<Object> inboundBuffer = new ArrayDeque();
    private final Runnable shutdownHook = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.local.LocalServerChannel.1
        @Override // java.lang.Runnable
        public void run() {
            LocalServerChannel.this.unsafe().close(LocalServerChannel.this.unsafe().voidPromise());
        }
    };

    public LocalServerChannel() {
        config().setAllocator(new PreferHeapByteBufAllocator(this.config.getAllocator()));
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelConfig config() {
        return this.config;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public LocalAddress localAddress() {
        return (LocalAddress) super.localAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractServerChannel, io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public LocalAddress remoteAddress() {
        return (LocalAddress) super.remoteAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isOpen() {
        return this.state < 2;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isActive() {
        return this.state == 1;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected boolean isCompatible(EventLoop eventLoop) {
        return eventLoop instanceof SingleThreadEventLoop;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected SocketAddress localAddress0() {
        return this.localAddress;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doRegister() throws Exception {
        ((SingleThreadEventExecutor) eventLoop()).addShutdownHook(this.shutdownHook);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doBind(SocketAddress socketAddress) throws Exception {
        this.localAddress = LocalChannelRegistry.register(this, this.localAddress, socketAddress);
        this.state = 1;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doClose() throws Exception {
        if (this.state <= 1) {
            if (this.localAddress != null) {
                LocalChannelRegistry.unregister(this.localAddress);
                this.localAddress = null;
            }
            this.state = 2;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doDeregister() throws Exception {
        ((SingleThreadEventExecutor) eventLoop()).removeShutdownHook(this.shutdownHook);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doBeginRead() throws Exception {
        if (this.acceptInProgress) {
            return;
        }
        if (this.inboundBuffer.isEmpty()) {
            this.acceptInProgress = true;
        } else {
            readInbound();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocalChannel serve(LocalChannel localChannel) {
        final LocalChannel newLocalChannel = newLocalChannel(localChannel);
        if (eventLoop().inEventLoop()) {
            serve0(newLocalChannel);
        } else {
            eventLoop().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.local.LocalServerChannel.2
                @Override // java.lang.Runnable
                public void run() {
                    LocalServerChannel.this.serve0(newLocalChannel);
                }
            });
        }
        return newLocalChannel;
    }

    private void readInbound() {
        RecvByteBufAllocator.Handle recvBufAllocHandle = unsafe().recvBufAllocHandle();
        recvBufAllocHandle.reset(config());
        ChannelPipeline pipeline = pipeline();
        do {
            Object poll = this.inboundBuffer.poll();
            if (poll == null) {
                break;
            } else {
                pipeline.fireChannelRead(poll);
            }
        } while (recvBufAllocHandle.continueReading());
        pipeline.fireChannelReadComplete();
    }

    protected LocalChannel newLocalChannel(LocalChannel localChannel) {
        return new LocalChannel(this, localChannel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void serve0(LocalChannel localChannel) {
        this.inboundBuffer.add(localChannel);
        if (this.acceptInProgress) {
            this.acceptInProgress = false;
            readInbound();
        }
    }
}
