package io.netty.channel.sctp.oio;

import com.sun.nio.sctp.Association;
import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.NotificationHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.oio.AbstractOioMessageChannel;
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
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public class OioSctpChannel extends AbstractOioMessageChannel implements SctpChannel {

    /* renamed from: ch */
    private final com.sun.nio.sctp.SctpChannel f8458ch;
    private final SctpChannelConfig config;
    private final Selector connectSelector;
    private final NotificationHandler<?> notificationHandler;
    private final Selector readSelector;
    private final Selector writeSelector;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) OioSctpChannel.class);
    private static final ChannelMetadata METADATA = new ChannelMetadata(false);
    private static final String EXPECTED_TYPE = " (expected: " + StringUtil.simpleClassName((Class<?>) SctpMessage.class) + ')';

    private static com.sun.nio.sctp.SctpChannel openChannel() {
        try {
            return com.sun.nio.sctp.SctpChannel.open();
        } catch (IOException e) {
            throw new ChannelException("Failed to open a sctp channel.", e);
        }
    }

    public OioSctpChannel() {
        this(openChannel());
    }

    public OioSctpChannel(com.sun.nio.sctp.SctpChannel sctpChannel) {
        this(null, sctpChannel);
    }

    public OioSctpChannel(Channel channel, com.sun.nio.sctp.SctpChannel sctpChannel) {
        super(channel);
        this.f8458ch = sctpChannel;
        try {
            try {
                sctpChannel.configureBlocking(false);
                this.readSelector = Selector.open();
                this.writeSelector = Selector.open();
                this.connectSelector = Selector.open();
                sctpChannel.register(this.readSelector, 1);
                sctpChannel.register(this.writeSelector, 4);
                sctpChannel.register(this.connectSelector, 8);
                this.config = new OioSctpChannelConfig(this, sctpChannel);
                this.notificationHandler = new SctpNotificationHandler(this);
            } catch (Throwable th) {
                try {
                    sctpChannel.close();
                } catch (IOException e) {
                    logger.warn("Failed to close a sctp channel.", (Throwable) e);
                }
                throw th;
            }
        } catch (Exception e2) {
            throw new ChannelException("failed to initialize a sctp channel", e2);
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

    @Override // io.netty.channel.Channel
    public SctpChannelConfig config() {
        return this.config;
    }

    @Override // io.netty.channel.Channel
    public boolean isOpen() {
        return this.f8458ch.isOpen();
    }

    @Override // io.netty.channel.oio.AbstractOioMessageChannel
    protected int doReadMessages(List<Object> list) throws Exception {
        if (!this.readSelector.isOpen()) {
            return 0;
        }
        if (!(this.readSelector.select(1000L) > 0)) {
            return 0;
        }
        this.readSelector.selectedKeys().clear();
        RecvByteBufAllocator.Handle recvBufAllocHandle = unsafe().recvBufAllocHandle();
        ByteBuf allocate = recvBufAllocHandle.allocate(config().getAllocator());
        try {
            ByteBuffer nioBuffer = allocate.nioBuffer(allocate.writerIndex(), allocate.writableBytes());
            MessageInfo receive = this.f8458ch.receive(nioBuffer, (Object) null, this.notificationHandler);
            if (receive == null) {
                return 0;
            }
            nioBuffer.flip();
            recvBufAllocHandle.lastBytesRead(nioBuffer.remaining());
            list.add(new SctpMessage(receive, allocate.writerIndex(allocate.writerIndex() + recvBufAllocHandle.lastBytesRead())));
            return 1;
        } catch (Throwable th) {
            try {
                PlatformDependent.throwException(th);
                return 0;
            } finally {
                allocate.release();
            }
        }
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        ByteBuffer byteBuffer;
        if (this.writeSelector.isOpen()) {
            int size = channelOutboundBuffer.size();
            if (this.writeSelector.select(1000L) > 0) {
                Set<SelectionKey> selectedKeys = this.writeSelector.selectedKeys();
                if (selectedKeys.isEmpty()) {
                    return;
                }
                Iterator<SelectionKey> it = selectedKeys.iterator();
                int i = 0;
                while (i != size) {
                    it.next();
                    it.remove();
                    SctpMessage sctpMessage = (SctpMessage) channelOutboundBuffer.current();
                    if (sctpMessage == null) {
                        return;
                    }
                    ByteBuf content = sctpMessage.content();
                    int readableBytes = content.readableBytes();
                    if (content.nioBufferCount() != -1) {
                        byteBuffer = content.nioBuffer();
                    } else {
                        ByteBuffer allocate = ByteBuffer.allocate(readableBytes);
                        content.getBytes(content.readerIndex(), allocate);
                        allocate.flip();
                        byteBuffer = allocate;
                    }
                    MessageInfo createOutgoing = MessageInfo.createOutgoing(association(), (SocketAddress) null, sctpMessage.streamIdentifier());
                    createOutgoing.payloadProtocolID(sctpMessage.protocolIdentifier());
                    createOutgoing.streamNumber(sctpMessage.streamIdentifier());
                    createOutgoing.unordered(sctpMessage.isUnordered());
                    this.f8458ch.send(byteBuffer, createOutgoing);
                    i++;
                    channelOutboundBuffer.remove();
                    if (!it.hasNext()) {
                        return;
                    }
                }
            }
        }
    }

    @Override // io.netty.channel.AbstractChannel
    protected Object filterOutboundMessage(Object obj) throws Exception {
        if (obj instanceof SctpMessage) {
            return obj;
        }
        throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(obj) + EXPECTED_TYPE);
    }

    @Override // io.netty.channel.sctp.SctpChannel
    public Association association() {
        try {
            return this.f8458ch.association();
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // io.netty.channel.Channel
    public boolean isActive() {
        return isOpen() && association() != null;
    }

    @Override // io.netty.channel.AbstractChannel
    protected SocketAddress localAddress0() {
        try {
            Iterator it = this.f8458ch.getAllLocalAddresses().iterator();
            if (it.hasNext()) {
                return (SocketAddress) it.next();
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // io.netty.channel.sctp.SctpChannel
    public Set<InetSocketAddress> allLocalAddresses() {
        try {
            Set allLocalAddresses = this.f8458ch.getAllLocalAddresses();
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

    @Override // io.netty.channel.AbstractChannel
    protected SocketAddress remoteAddress0() {
        try {
            Iterator it = this.f8458ch.getRemoteAddresses().iterator();
            if (it.hasNext()) {
                return (SocketAddress) it.next();
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // io.netty.channel.sctp.SctpChannel
    public Set<InetSocketAddress> allRemoteAddresses() {
        try {
            Set remoteAddresses = this.f8458ch.getRemoteAddresses();
            LinkedHashSet linkedHashSet = new LinkedHashSet(remoteAddresses.size());
            Iterator it = remoteAddresses.iterator();
            while (it.hasNext()) {
                linkedHashSet.add((InetSocketAddress) ((SocketAddress) it.next()));
            }
            return linkedHashSet;
        } catch (Throwable unused) {
            return Collections.emptySet();
        }
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doBind(SocketAddress socketAddress) throws Exception {
        this.f8458ch.bind(socketAddress);
    }

    @Override // io.netty.channel.oio.AbstractOioChannel
    protected void doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        if (socketAddress2 != null) {
            this.f8458ch.bind(socketAddress2);
        }
        try {
            this.f8458ch.connect(socketAddress);
            boolean z = false;
            while (!z) {
                if (this.connectSelector.select(1000L) >= 0) {
                    Set<SelectionKey> selectedKeys = this.connectSelector.selectedKeys();
                    Iterator<SelectionKey> it = selectedKeys.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        if (it.next().isConnectable()) {
                            selectedKeys.clear();
                            z = true;
                            break;
                        }
                    }
                    selectedKeys.clear();
                }
            }
            if (this.f8458ch.finishConnect()) {
            }
        } finally {
            doClose();
        }
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doDisconnect() throws Exception {
        doClose();
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doClose() throws Exception {
        closeSelector("read", this.readSelector);
        closeSelector("write", this.writeSelector);
        closeSelector(MqttServiceConstants.CONNECT_ACTION, this.connectSelector);
        this.f8458ch.close();
    }

    private static void closeSelector(String str, Selector selector) {
        try {
            selector.close();
        } catch (IOException e) {
            logger.warn("Failed to close a " + str + " selector.", (Throwable) e);
        }
    }

    @Override // io.netty.channel.sctp.SctpChannel
    public ChannelFuture bindAddress(InetAddress inetAddress) {
        return bindAddress(inetAddress, newPromise());
    }

    @Override // io.netty.channel.sctp.SctpChannel
    public ChannelFuture bindAddress(final InetAddress inetAddress, final ChannelPromise channelPromise) {
        if (eventLoop().inEventLoop()) {
            try {
                this.f8458ch.bindAddress(inetAddress);
                channelPromise.setSuccess();
            } catch (Throwable th) {
                channelPromise.setFailure(th);
            }
        } else {
            eventLoop().execute(new Runnable() { // from class: io.netty.channel.sctp.oio.OioSctpChannel.1
                @Override // java.lang.Runnable
                public void run() {
                    OioSctpChannel.this.bindAddress(inetAddress, channelPromise);
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
                this.f8458ch.unbindAddress(inetAddress);
                channelPromise.setSuccess();
            } catch (Throwable th) {
                channelPromise.setFailure(th);
            }
        } else {
            eventLoop().execute(new Runnable() { // from class: io.netty.channel.sctp.oio.OioSctpChannel.2
                @Override // java.lang.Runnable
                public void run() {
                    OioSctpChannel.this.unbindAddress(inetAddress, channelPromise);
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
    private final class OioSctpChannelConfig extends DefaultSctpChannelConfig {
        private OioSctpChannelConfig(OioSctpChannel oioSctpChannel, com.sun.nio.sctp.SctpChannel sctpChannel) {
            super(oioSctpChannel, sctpChannel);
        }

        @Override // io.netty.channel.DefaultChannelConfig
        protected void autoReadCleared() {
            OioSctpChannel.this.clearReadPending();
        }
    }
}
