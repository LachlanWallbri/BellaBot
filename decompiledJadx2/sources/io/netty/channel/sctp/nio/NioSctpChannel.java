package io.netty.channel.sctp.nio;

import com.sun.nio.sctp.Association;
import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.NotificationHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.nio.AbstractNioMessageChannel;
import io.netty.channel.sctp.DefaultSctpChannelConfig;
import io.netty.channel.sctp.SctpChannel;
import io.netty.channel.sctp.SctpChannelConfig;
import io.netty.channel.sctp.SctpMessage;
import io.netty.channel.sctp.SctpNotificationHandler;
import io.netty.channel.sctp.SctpServerChannel;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public class NioSctpChannel extends AbstractNioMessageChannel implements SctpChannel {
    private static final ChannelMetadata METADATA = new ChannelMetadata(false);
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) NioSctpChannel.class);
    private final SctpChannelConfig config;
    private final NotificationHandler<?> notificationHandler;

    private static com.sun.nio.sctp.SctpChannel newSctpChannel() {
        try {
            return com.sun.nio.sctp.SctpChannel.open();
        } catch (IOException e) {
            throw new ChannelException("Failed to open a sctp channel.", e);
        }
    }

    public NioSctpChannel() {
        this(newSctpChannel());
    }

    public NioSctpChannel(com.sun.nio.sctp.SctpChannel sctpChannel) {
        this(null, sctpChannel);
    }

    public NioSctpChannel(Channel channel, com.sun.nio.sctp.SctpChannel sctpChannel) {
        super(channel, sctpChannel, 1);
        try {
            sctpChannel.configureBlocking(false);
            this.config = new NioSctpChannelConfig(this, sctpChannel);
            this.notificationHandler = new SctpNotificationHandler(this);
        } catch (IOException e) {
            try {
                sctpChannel.close();
            } catch (IOException e2) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Failed to close a partially initialized sctp channel.", (Throwable) e2);
                }
            }
            throw new ChannelException("Failed to enter non-blocking mode.", e);
        }
    }

    @Override // io.netty.channel.AbstractChannel, io.netty.channel.Channel
    public InetSocketAddress localAddress() {
        return (InetSocketAddress) super.localAddress();
    }

    @Override // io.netty.channel.AbstractChannel, io.netty.channel.Channel
    public InetSocketAddress remoteAddress() {
        return (InetSocketAddress) super.remoteAddress();
    }

    @Override // io.netty.channel.AbstractChannel, io.netty.channel.Channel
    public SctpServerChannel parent() {
        return (SctpServerChannel) super.parent();
    }

    @Override // io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    @Override // io.netty.channel.sctp.SctpChannel
    public Association association() {
        try {
            return mo4506javaChannel().association();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // io.netty.channel.sctp.SctpChannel
    public Set<InetSocketAddress> allLocalAddresses() {
        try {
            Set allLocalAddresses = mo4506javaChannel().getAllLocalAddresses();
            LinkedHashSet linkedHashSet = new LinkedHashSet(allLocalAddresses.size());
            Iterator it = allLocalAddresses.iterator();
            while (it.hasNext()) {
                linkedHashSet.add((InetSocketAddress) ((SocketAddress) it.next()));
            }
            return linkedHashSet;
        } catch (Throwable unused) {
            return Collections.emptySet();
        }
    }

    @Override // io.netty.channel.Channel
    public SctpChannelConfig config() {
        return this.config;
    }

    @Override // io.netty.channel.sctp.SctpChannel
    public Set<InetSocketAddress> allRemoteAddresses() {
        try {
            Set remoteAddresses = mo4506javaChannel().getRemoteAddresses();
            HashSet hashSet = new HashSet(remoteAddresses.size());
            Iterator it = remoteAddresses.iterator();
            while (it.hasNext()) {
                hashSet.add((InetSocketAddress) ((SocketAddress) it.next()));
            }
            return hashSet;
        } catch (Throwable unused) {
            return Collections.emptySet();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.channel.nio.AbstractNioChannel
    /* renamed from: javaChannel, reason: merged with bridge method [inline-methods] */
    public com.sun.nio.sctp.SctpChannel mo4506javaChannel() {
        return super.mo4506javaChannel();
    }

    @Override // io.netty.channel.Channel
    public boolean isActive() {
        return mo4506javaChannel().isOpen() && association() != null;
    }

    @Override // io.netty.channel.AbstractChannel
    protected SocketAddress localAddress0() {
        try {
            Iterator it = mo4506javaChannel().getAllLocalAddresses().iterator();
            if (it.hasNext()) {
                return (SocketAddress) it.next();
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // io.netty.channel.AbstractChannel
    protected SocketAddress remoteAddress0() {
        try {
            Iterator it = mo4506javaChannel().getRemoteAddresses().iterator();
            if (it.hasNext()) {
                return (SocketAddress) it.next();
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doBind(SocketAddress socketAddress) throws Exception {
        mo4506javaChannel().bind(socketAddress);
    }

    @Override // io.netty.channel.nio.AbstractNioChannel
    protected boolean doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        if (socketAddress2 != null) {
            mo4506javaChannel().bind(socketAddress2);
        }
        try {
            boolean connect = mo4506javaChannel().connect(socketAddress);
            if (!connect) {
                selectionKey().interestOps(8);
            }
            return connect;
        } catch (Throwable th) {
            doClose();
            throw th;
        }
    }

    @Override // io.netty.channel.nio.AbstractNioChannel
    protected void doFinishConnect() throws Exception {
        if (!mo4506javaChannel().finishConnect()) {
            throw new Error();
        }
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doDisconnect() throws Exception {
        doClose();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.channel.nio.AbstractNioChannel, io.netty.channel.AbstractChannel
    public void doClose() throws Exception {
        mo4506javaChannel().close();
    }

    @Override // io.netty.channel.nio.AbstractNioMessageChannel
    protected int doReadMessages(List<Object> list) throws Exception {
        com.sun.nio.sctp.SctpChannel mo4506javaChannel = mo4506javaChannel();
        RecvByteBufAllocator.Handle recvBufAllocHandle = unsafe().recvBufAllocHandle();
        ByteBuf allocate = recvBufAllocHandle.allocate(config().getAllocator());
        try {
            ByteBuffer internalNioBuffer = allocate.internalNioBuffer(allocate.writerIndex(), allocate.writableBytes());
            int position = internalNioBuffer.position();
            MessageInfo receive = mo4506javaChannel.receive(internalNioBuffer, (Object) null, this.notificationHandler);
            if (receive != null) {
                recvBufAllocHandle.lastBytesRead(internalNioBuffer.position() - position);
                list.add(new SctpMessage(receive, allocate.writerIndex(allocate.writerIndex() + recvBufAllocHandle.lastBytesRead())));
                return 1;
            }
            return 0;
        } catch (Throwable th) {
            try {
                PlatformDependent.throwException(th);
                return -1;
            } finally {
                allocate.release();
            }
        }
    }

    @Override // io.netty.channel.nio.AbstractNioMessageChannel
    protected boolean doWriteMessage(Object obj, ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        ByteBuffer nioBuffer;
        SctpMessage sctpMessage = (SctpMessage) obj;
        ByteBuf content = sctpMessage.content();
        int readableBytes = content.readableBytes();
        if (readableBytes == 0) {
            return true;
        }
        ByteBufAllocator alloc = alloc();
        boolean z = content.nioBufferCount() != 1;
        if (!z && !content.isDirect() && alloc.isDirectBufferPooled()) {
            z = true;
        }
        if (!z) {
            nioBuffer = content.nioBuffer();
        } else {
            nioBuffer = alloc.directBuffer(readableBytes).writeBytes(content).nioBuffer();
        }
        MessageInfo createOutgoing = MessageInfo.createOutgoing(association(), (SocketAddress) null, sctpMessage.streamIdentifier());
        createOutgoing.payloadProtocolID(sctpMessage.protocolIdentifier());
        createOutgoing.streamNumber(sctpMessage.streamIdentifier());
        createOutgoing.unordered(sctpMessage.isUnordered());
        return mo4506javaChannel().send(nioBuffer, createOutgoing) > 0;
    }

    @Override // io.netty.channel.AbstractChannel
    protected final Object filterOutboundMessage(Object obj) throws Exception {
        if (obj instanceof SctpMessage) {
            SctpMessage sctpMessage = (SctpMessage) obj;
            ByteBuf content = sctpMessage.content();
            return (content.isDirect() && content.nioBufferCount() == 1) ? sctpMessage : new SctpMessage(sctpMessage.protocolIdentifier(), sctpMessage.streamIdentifier(), sctpMessage.isUnordered(), newDirectBuffer(sctpMessage, content));
        }
        throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(obj) + " (expected: " + StringUtil.simpleClassName((Class<?>) SctpMessage.class));
    }

    @Override // io.netty.channel.sctp.SctpChannel
    public ChannelFuture bindAddress(InetAddress inetAddress) {
        return bindAddress(inetAddress, newPromise());
    }

    @Override // io.netty.channel.sctp.SctpChannel
    public ChannelFuture bindAddress(final InetAddress inetAddress, final ChannelPromise channelPromise) {
        if (eventLoop().inEventLoop()) {
            try {
                mo4506javaChannel().bindAddress(inetAddress);
                channelPromise.setSuccess();
            } catch (Throwable th) {
                channelPromise.setFailure(th);
            }
        } else {
            eventLoop().execute(new Runnable() { // from class: io.netty.channel.sctp.nio.NioSctpChannel.1
                @Override // java.lang.Runnable
                public void run() {
                    NioSctpChannel.this.bindAddress(inetAddress, channelPromise);
                }
            });
        }
        return channelPromise;
    }

    @Override // io.netty.channel.sctp.SctpChannel
    public ChannelFuture unbindAddress(InetAddress inetAddress) {
        return unbindAddress(inetAddress, newPromise());
    }

    @Override // io.netty.channel.sctp.SctpChannel
    public ChannelFuture unbindAddress(final InetAddress inetAddress, final ChannelPromise channelPromise) {
        if (eventLoop().inEventLoop()) {
            try {
                mo4506javaChannel().unbindAddress(inetAddress);
                channelPromise.setSuccess();
            } catch (Throwable th) {
                channelPromise.setFailure(th);
            }
        } else {
            eventLoop().execute(new Runnable() { // from class: io.netty.channel.sctp.nio.NioSctpChannel.2
                @Override // java.lang.Runnable
                public void run() {
                    NioSctpChannel.this.unbindAddress(inetAddress, channelPromise);
                }
            });
        }
        return channelPromise;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    private final class NioSctpChannelConfig extends DefaultSctpChannelConfig {
        private NioSctpChannelConfig(NioSctpChannel nioSctpChannel, com.sun.nio.sctp.SctpChannel sctpChannel) {
            super(nioSctpChannel, sctpChannel);
        }

        @Override // io.netty.channel.DefaultChannelConfig
        protected void autoReadCleared() {
            NioSctpChannel.this.clearReadPending();
        }
    }
}
