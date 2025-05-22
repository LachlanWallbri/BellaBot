package io.grpc.netty.shaded.io.netty.channel.socket.oio;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.channel.AddressedEnvelope;
import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelMetadata;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.oio.AbstractOioMessageChannel;
import io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel;
import io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannelConfig;
import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.channels.NotYetConnectedException;
import java.util.List;
import java.util.Locale;
import kotlin.text.Typography;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public class OioDatagramChannel extends AbstractOioMessageChannel implements DatagramChannel {
    private final OioDatagramChannelConfig config;
    private final MulticastSocket socket;
    private final DatagramPacket tmpPacket;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) OioDatagramChannel.class);
    private static final ChannelMetadata METADATA = new ChannelMetadata(true);
    private static final String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName((Class<?>) io.grpc.netty.shaded.io.netty.channel.socket.DatagramPacket.class) + ", " + StringUtil.simpleClassName((Class<?>) AddressedEnvelope.class) + Typography.less + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ", " + StringUtil.simpleClassName((Class<?>) SocketAddress.class) + ">, " + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ')';

    private static MulticastSocket newSocket() {
        try {
            return new MulticastSocket((SocketAddress) null);
        } catch (Exception e) {
            throw new ChannelException("failed to create a new socket", e);
        }
    }

    public OioDatagramChannel() {
        this(newSocket());
    }

    public OioDatagramChannel(MulticastSocket multicastSocket) {
        super(null);
        this.tmpPacket = new DatagramPacket(EmptyArrays.EMPTY_BYTES, 0);
        try {
            try {
                multicastSocket.setSoTimeout(1000);
                multicastSocket.setBroadcast(false);
                this.socket = multicastSocket;
                this.config = new DefaultOioDatagramChannelConfig(this, multicastSocket);
            } catch (SocketException e) {
                throw new ChannelException("Failed to configure the datagram socket timeout.", e);
            }
        } catch (Throwable th) {
            multicastSocket.close();
            throw th;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public DatagramChannelConfig config() {
        return this.config;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isOpen() {
        return !this.socket.isClosed();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isActive() {
        return isOpen() && ((((Boolean) this.config.getOption(ChannelOption.DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION)).booleanValue() && isRegistered()) || this.socket.isBound());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public boolean isConnected() {
        return this.socket.isConnected();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected SocketAddress localAddress0() {
        return this.socket.getLocalSocketAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected SocketAddress remoteAddress0() {
        return this.socket.getRemoteSocketAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doBind(SocketAddress socketAddress) throws Exception {
        this.socket.bind(socketAddress);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public InetSocketAddress localAddress() {
        return (InetSocketAddress) super.localAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public InetSocketAddress remoteAddress() {
        return (InetSocketAddress) super.remoteAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.oio.AbstractOioChannel
    protected void doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        if (socketAddress2 != null) {
            this.socket.bind(socketAddress2);
        }
        try {
            this.socket.connect(socketAddress);
        } catch (Throwable th) {
            try {
                this.socket.close();
            } catch (Throwable th2) {
                logger.warn("Failed to close a socket.", th2);
            }
            throw th;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doDisconnect() throws Exception {
        this.socket.disconnect();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doClose() throws Exception {
        this.socket.close();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.oio.AbstractOioMessageChannel
    protected int doReadMessages(List<Object> list) throws Exception {
        DatagramChannelConfig config = config();
        RecvByteBufAllocator.Handle recvBufAllocHandle = unsafe().recvBufAllocHandle();
        ByteBuf heapBuffer = config.getAllocator().heapBuffer(recvBufAllocHandle.guess());
        try {
            try {
                try {
                    this.tmpPacket.setAddress(null);
                    this.tmpPacket.setData(heapBuffer.array(), heapBuffer.arrayOffset(), heapBuffer.capacity());
                    this.socket.receive(this.tmpPacket);
                    InetSocketAddress inetSocketAddress = (InetSocketAddress) this.tmpPacket.getSocketAddress();
                    recvBufAllocHandle.lastBytesRead(this.tmpPacket.getLength());
                    list.add(new io.grpc.netty.shaded.io.netty.channel.socket.DatagramPacket(heapBuffer.writerIndex(recvBufAllocHandle.lastBytesRead()), localAddress(), inetSocketAddress));
                    return 1;
                } catch (SocketException e) {
                    if (!e.getMessage().toLowerCase(Locale.US).contains("socket closed")) {
                        throw e;
                    }
                    heapBuffer.release();
                    return -1;
                } catch (SocketTimeoutException unused) {
                    heapBuffer.release();
                    return 0;
                }
            } catch (Throwable th) {
                PlatformDependent.throwException(th);
                heapBuffer.release();
                return -1;
            }
        } catch (Throwable th2) {
            heapBuffer.release();
            throw th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0039 A[Catch: Exception -> 0x006a, TryCatch #0 {Exception -> 0x006a, blocks: (B:29:0x0022, B:22:0x0033, B:24:0x0039, B:25:0x0059, B:27:0x004c, B:8:0x0028, B:21:0x002e, B:11:0x0064, B:12:0x0069), top: B:28:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004c A[Catch: Exception -> 0x006a, TryCatch #0 {Exception -> 0x006a, blocks: (B:29:0x0022, B:22:0x0033, B:24:0x0039, B:25:0x0059, B:27:0x004c, B:8:0x0028, B:21:0x002e, B:11:0x0064, B:12:0x0069), top: B:28:0x0022 }] */
    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        ByteBuf byteBuf;
        SocketAddress socketAddress;
        while (true) {
            Object current = channelOutboundBuffer.current();
            if (current != null) {
                if (current instanceof AddressedEnvelope) {
                    AddressedEnvelope addressedEnvelope = (AddressedEnvelope) current;
                    socketAddress = addressedEnvelope.recipient();
                    byteBuf = (ByteBuf) addressedEnvelope.content();
                } else {
                    byteBuf = (ByteBuf) current;
                    socketAddress = null;
                }
                int readableBytes = byteBuf.readableBytes();
                if (socketAddress != null) {
                    try {
                        this.tmpPacket.setSocketAddress(socketAddress);
                        if (!byteBuf.hasArray()) {
                            this.tmpPacket.setData(byteBuf.array(), byteBuf.arrayOffset() + byteBuf.readerIndex(), readableBytes);
                        } else {
                            this.tmpPacket.setData(ByteBufUtil.getBytes(byteBuf, byteBuf.readerIndex(), readableBytes));
                        }
                        this.socket.send(this.tmpPacket);
                        channelOutboundBuffer.remove();
                    } catch (Exception e) {
                        channelOutboundBuffer.remove(e);
                    }
                } else {
                    if (!isConnected()) {
                        throw new NotYetConnectedException();
                        break;
                    }
                    this.tmpPacket.setAddress(null);
                    if (!byteBuf.hasArray()) {
                    }
                    this.socket.send(this.tmpPacket);
                    channelOutboundBuffer.remove();
                }
            } else {
                return;
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected Object filterOutboundMessage(Object obj) {
        if ((obj instanceof io.grpc.netty.shaded.io.netty.channel.socket.DatagramPacket) || (obj instanceof ByteBuf)) {
            return obj;
        }
        if ((obj instanceof AddressedEnvelope) && (((AddressedEnvelope) obj).content() instanceof ByteBuf)) {
            return obj;
        }
        throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(obj) + EXPECTED_TYPES);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress) {
        return joinGroup(inetAddress, newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress, ChannelPromise channelPromise) {
        ensureBound();
        try {
            this.socket.joinGroup(inetAddress);
            channelPromise.setSuccess();
        } catch (IOException e) {
            channelPromise.setFailure((Throwable) e);
        }
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface) {
        return joinGroup(inetSocketAddress, networkInterface, newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface, ChannelPromise channelPromise) {
        ensureBound();
        try {
            this.socket.joinGroup(inetSocketAddress, networkInterface);
            channelPromise.setSuccess();
        } catch (IOException e) {
            channelPromise.setFailure((Throwable) e);
        }
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        return newFailedFuture(new UnsupportedOperationException());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        channelPromise.setFailure((Throwable) new UnsupportedOperationException());
        return channelPromise;
    }

    private void ensureBound() {
        if (isActive()) {
            return;
        }
        throw new IllegalStateException(DatagramChannel.class.getName() + " must be bound to join a group.");
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress) {
        return leaveGroup(inetAddress, newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress, ChannelPromise channelPromise) {
        try {
            this.socket.leaveGroup(inetAddress);
            channelPromise.setSuccess();
        } catch (IOException e) {
            channelPromise.setFailure((Throwable) e);
        }
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface) {
        return leaveGroup(inetSocketAddress, networkInterface, newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface, ChannelPromise channelPromise) {
        try {
            this.socket.leaveGroup(inetSocketAddress, networkInterface);
            channelPromise.setSuccess();
        } catch (IOException e) {
            channelPromise.setFailure((Throwable) e);
        }
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        return newFailedFuture(new UnsupportedOperationException());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        channelPromise.setFailure((Throwable) new UnsupportedOperationException());
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture block(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        return newFailedFuture(new UnsupportedOperationException());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture block(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        channelPromise.setFailure((Throwable) new UnsupportedOperationException());
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture block(InetAddress inetAddress, InetAddress inetAddress2) {
        return newFailedFuture(new UnsupportedOperationException());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture block(InetAddress inetAddress, InetAddress inetAddress2, ChannelPromise channelPromise) {
        channelPromise.setFailure((Throwable) new UnsupportedOperationException());
        return channelPromise;
    }
}
