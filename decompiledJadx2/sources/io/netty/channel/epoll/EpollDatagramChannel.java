package io.netty.channel.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.AddressedEnvelope;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultAddressedEnvelope;
import io.netty.channel.epoll.AbstractEpollChannel;
import io.netty.channel.epoll.NativeDatagramPacketArray;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.unix.DatagramSocketAddress;
import io.netty.channel.unix.IovArray;
import io.netty.channel.unix.UnixChannelUtil;
import io.netty.util.internal.StringUtil;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import kotlin.text.Typography;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class EpollDatagramChannel extends AbstractEpollChannel implements DatagramChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final EpollDatagramChannelConfig config;
    private volatile boolean connected;
    private static final ChannelMetadata METADATA = new ChannelMetadata(true);
    private static final String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName((Class<?>) DatagramPacket.class) + ", " + StringUtil.simpleClassName((Class<?>) AddressedEnvelope.class) + Typography.less + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ", " + StringUtil.simpleClassName((Class<?>) InetSocketAddress.class) + ">, " + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ')';

    @Override // io.netty.channel.epoll.AbstractEpollChannel, io.netty.channel.Channel
    public /* bridge */ /* synthetic */ boolean isOpen() {
        return super.isOpen();
    }

    public EpollDatagramChannel() {
        super(LinuxSocket.newSocketDgram(), Native.EPOLLIN);
        this.config = new EpollDatagramChannelConfig(this);
    }

    public EpollDatagramChannel(int i) {
        this(new LinuxSocket(i));
    }

    EpollDatagramChannel(LinuxSocket linuxSocket) {
        super((Channel) null, linuxSocket, Native.EPOLLIN, true);
        this.config = new EpollDatagramChannelConfig(this);
    }

    @Override // io.netty.channel.AbstractChannel, io.netty.channel.Channel
    public InetSocketAddress remoteAddress() {
        return (InetSocketAddress) super.remoteAddress();
    }

    @Override // io.netty.channel.AbstractChannel, io.netty.channel.Channel
    public InetSocketAddress localAddress() {
        return (InetSocketAddress) super.localAddress();
    }

    @Override // io.netty.channel.epoll.AbstractEpollChannel, io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    @Override // io.netty.channel.epoll.AbstractEpollChannel, io.netty.channel.Channel
    public boolean isActive() {
        return this.socket.isOpen() && ((this.config.getActiveOnOpen() && isRegistered()) || this.active);
    }

    @Override // io.netty.channel.socket.DatagramChannel
    public boolean isConnected() {
        return this.connected;
    }

    @Override // io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress) {
        return joinGroup(inetAddress, newPromise());
    }

    @Override // io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress, ChannelPromise channelPromise) {
        try {
            return joinGroup(inetAddress, NetworkInterface.getByInetAddress(localAddress().getAddress()), null, channelPromise);
        } catch (SocketException e) {
            channelPromise.setFailure((Throwable) e);
            return channelPromise;
        }
    }

    @Override // io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface) {
        return joinGroup(inetSocketAddress, networkInterface, newPromise());
    }

    @Override // io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface, ChannelPromise channelPromise) {
        return joinGroup(inetSocketAddress.getAddress(), networkInterface, null, channelPromise);
    }

    @Override // io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        return joinGroup(inetAddress, networkInterface, inetAddress2, newPromise());
    }

    @Override // io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        if (inetAddress == null) {
            throw new NullPointerException("multicastAddress");
        }
        if (networkInterface == null) {
            throw new NullPointerException("networkInterface");
        }
        channelPromise.setFailure((Throwable) new UnsupportedOperationException("Multicast not supported"));
        return channelPromise;
    }

    @Override // io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress) {
        return leaveGroup(inetAddress, newPromise());
    }

    @Override // io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress, ChannelPromise channelPromise) {
        try {
            return leaveGroup(inetAddress, NetworkInterface.getByInetAddress(localAddress().getAddress()), null, channelPromise);
        } catch (SocketException e) {
            channelPromise.setFailure((Throwable) e);
            return channelPromise;
        }
    }

    @Override // io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface) {
        return leaveGroup(inetSocketAddress, networkInterface, newPromise());
    }

    @Override // io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface, ChannelPromise channelPromise) {
        return leaveGroup(inetSocketAddress.getAddress(), networkInterface, null, channelPromise);
    }

    @Override // io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        return leaveGroup(inetAddress, networkInterface, inetAddress2, newPromise());
    }

    @Override // io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        if (inetAddress == null) {
            throw new NullPointerException("multicastAddress");
        }
        if (networkInterface == null) {
            throw new NullPointerException("networkInterface");
        }
        channelPromise.setFailure((Throwable) new UnsupportedOperationException("Multicast not supported"));
        return channelPromise;
    }

    @Override // io.netty.channel.socket.DatagramChannel
    public ChannelFuture block(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        return block(inetAddress, networkInterface, inetAddress2, newPromise());
    }

    @Override // io.netty.channel.socket.DatagramChannel
    public ChannelFuture block(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        if (inetAddress == null) {
            throw new NullPointerException("multicastAddress");
        }
        if (inetAddress2 == null) {
            throw new NullPointerException("sourceToBlock");
        }
        if (networkInterface == null) {
            throw new NullPointerException("networkInterface");
        }
        channelPromise.setFailure((Throwable) new UnsupportedOperationException("Multicast not supported"));
        return channelPromise;
    }

    @Override // io.netty.channel.socket.DatagramChannel
    public ChannelFuture block(InetAddress inetAddress, InetAddress inetAddress2) {
        return block(inetAddress, inetAddress2, newPromise());
    }

    @Override // io.netty.channel.socket.DatagramChannel
    public ChannelFuture block(InetAddress inetAddress, InetAddress inetAddress2, ChannelPromise channelPromise) {
        try {
            return block(inetAddress, NetworkInterface.getByInetAddress(localAddress().getAddress()), inetAddress2, channelPromise);
        } catch (Throwable th) {
            channelPromise.setFailure(th);
            return channelPromise;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.channel.epoll.AbstractEpollChannel, io.netty.channel.AbstractChannel
    public AbstractEpollChannel.AbstractEpollUnsafe newUnsafe() {
        return new EpollDatagramChannelUnsafe();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.channel.epoll.AbstractEpollChannel, io.netty.channel.AbstractChannel
    public void doBind(SocketAddress socketAddress) throws Exception {
        super.doBind(socketAddress);
        this.active = true;
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        NativeDatagramPacketArray nativeDatagramPacketArray;
        int count;
        while (true) {
            Object current = channelOutboundBuffer.current();
            if (current == null) {
                clearFlag(Native.EPOLLOUT);
                return;
            }
            try {
                boolean z = false;
                if (Native.IS_SUPPORTING_SENDMMSG && channelOutboundBuffer.size() > 1 && (count = (nativeDatagramPacketArray = NativeDatagramPacketArray.getInstance(channelOutboundBuffer)).count()) >= 1) {
                    NativeDatagramPacketArray.NativeDatagramPacket[] packets = nativeDatagramPacketArray.packets();
                    int i = 0;
                    while (count > 0) {
                        int sendmmsg = Native.sendmmsg(this.socket.intValue(), packets, i, count);
                        if (sendmmsg == 0) {
                            setFlag(Native.EPOLLOUT);
                            return;
                        }
                        for (int i2 = 0; i2 < sendmmsg; i2++) {
                            channelOutboundBuffer.remove();
                        }
                        count -= sendmmsg;
                        i += sendmmsg;
                    }
                } else {
                    int writeSpinCount = config().getWriteSpinCount();
                    while (true) {
                        if (writeSpinCount <= 0) {
                            break;
                        }
                        if (doWriteMessage(current)) {
                            z = true;
                            break;
                        }
                        writeSpinCount--;
                    }
                    if (z) {
                        channelOutboundBuffer.remove();
                    } else {
                        setFlag(Native.EPOLLOUT);
                        return;
                    }
                }
            } catch (IOException e) {
                channelOutboundBuffer.remove(e);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean doWriteMessage(Object obj) throws Exception {
        ByteBuf byteBuf;
        InetSocketAddress inetSocketAddress;
        int sendTo;
        long writevAddresses;
        if (obj instanceof AddressedEnvelope) {
            AddressedEnvelope addressedEnvelope = (AddressedEnvelope) obj;
            byteBuf = (ByteBuf) addressedEnvelope.content();
            inetSocketAddress = (InetSocketAddress) addressedEnvelope.recipient();
        } else {
            byteBuf = (ByteBuf) obj;
            inetSocketAddress = null;
        }
        if (byteBuf.readableBytes() == 0) {
            return true;
        }
        if (byteBuf.hasMemoryAddress()) {
            long memoryAddress = byteBuf.memoryAddress();
            if (inetSocketAddress == null) {
                sendTo = this.socket.writeAddress(memoryAddress, byteBuf.readerIndex(), byteBuf.writerIndex());
            } else {
                sendTo = this.socket.sendToAddress(memoryAddress, byteBuf.readerIndex(), byteBuf.writerIndex(), inetSocketAddress.getAddress(), inetSocketAddress.getPort());
            }
        } else if (byteBuf.nioBufferCount() > 1) {
            IovArray cleanArray = ((EpollEventLoop) eventLoop()).cleanArray();
            cleanArray.add(byteBuf);
            int count = cleanArray.count();
            if (inetSocketAddress == null) {
                writevAddresses = this.socket.writevAddresses(cleanArray.memoryAddress(0), count);
                return writevAddresses <= 0;
            }
            sendTo = this.socket.sendToAddresses(cleanArray.memoryAddress(0), count, inetSocketAddress.getAddress(), inetSocketAddress.getPort());
        } else {
            ByteBuffer internalNioBuffer = byteBuf.internalNioBuffer(byteBuf.readerIndex(), byteBuf.readableBytes());
            if (inetSocketAddress == null) {
                sendTo = this.socket.write(internalNioBuffer, internalNioBuffer.position(), internalNioBuffer.limit());
            } else {
                sendTo = this.socket.sendTo(internalNioBuffer, internalNioBuffer.position(), internalNioBuffer.limit(), inetSocketAddress.getAddress(), inetSocketAddress.getPort());
            }
        }
        writevAddresses = sendTo;
        if (writevAddresses <= 0) {
        }
    }

    @Override // io.netty.channel.AbstractChannel
    protected Object filterOutboundMessage(Object obj) {
        if (obj instanceof DatagramPacket) {
            DatagramPacket datagramPacket = (DatagramPacket) obj;
            ByteBuf byteBuf = (ByteBuf) datagramPacket.content();
            return UnixChannelUtil.isBufferCopyNeededForWrite(byteBuf) ? new DatagramPacket(newDirectBuffer(datagramPacket, byteBuf), datagramPacket.recipient()) : obj;
        }
        if (obj instanceof ByteBuf) {
            ByteBuf byteBuf2 = (ByteBuf) obj;
            return UnixChannelUtil.isBufferCopyNeededForWrite(byteBuf2) ? newDirectBuffer(byteBuf2) : byteBuf2;
        }
        if (obj instanceof AddressedEnvelope) {
            AddressedEnvelope addressedEnvelope = (AddressedEnvelope) obj;
            if ((addressedEnvelope.content() instanceof ByteBuf) && (addressedEnvelope.recipient() == null || (addressedEnvelope.recipient() instanceof InetSocketAddress))) {
                ByteBuf byteBuf3 = (ByteBuf) addressedEnvelope.content();
                return UnixChannelUtil.isBufferCopyNeededForWrite(byteBuf3) ? new DefaultAddressedEnvelope(newDirectBuffer(addressedEnvelope, byteBuf3), (InetSocketAddress) addressedEnvelope.recipient()) : addressedEnvelope;
            }
        }
        throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(obj) + EXPECTED_TYPES);
    }

    @Override // io.netty.channel.epoll.AbstractEpollChannel, io.netty.channel.Channel
    public EpollDatagramChannelConfig config() {
        return this.config;
    }

    @Override // io.netty.channel.epoll.AbstractEpollChannel, io.netty.channel.AbstractChannel
    protected void doDisconnect() throws Exception {
        this.socket.disconnect();
        this.active = false;
        this.connected = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.channel.epoll.AbstractEpollChannel
    public boolean doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        if (!super.doConnect(socketAddress, socketAddress2)) {
            return false;
        }
        this.connected = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.channel.epoll.AbstractEpollChannel, io.netty.channel.AbstractChannel
    public void doClose() throws Exception {
        super.doClose();
        this.connected = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public final class EpollDatagramChannelUnsafe extends AbstractEpollChannel.AbstractEpollUnsafe {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        EpollDatagramChannelUnsafe() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0076, code lost:
        
            r1.lastBytesRead(-1);
            r5.release();
         */
        /* JADX WARN: Removed duplicated region for block: B:21:0x00c2 A[Catch: all -> 0x00b8, TRY_LEAVE, TryCatch #0 {all -> 0x00b8, blocks: (B:19:0x00ba, B:21:0x00c2, B:38:0x00b4), top: B:37:0x00b4 }] */
        @Override // io.netty.channel.epoll.AbstractEpollChannel.AbstractEpollUnsafe
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void epollInReady() {
            Throwable th;
            ByteBuf byteBuf;
            DatagramSocketAddress recvFrom;
            EpollDatagramChannelConfig config = EpollDatagramChannel.this.config();
            if (EpollDatagramChannel.this.shouldBreakEpollInReady(config)) {
                clearEpollIn0();
                return;
            }
            EpollRecvByteAllocatorHandle recvBufAllocHandle = recvBufAllocHandle();
            recvBufAllocHandle.edgeTriggered(EpollDatagramChannel.this.isFlagSet(Native.EPOLLET));
            ChannelPipeline pipeline = EpollDatagramChannel.this.pipeline();
            ByteBufAllocator allocator = config.getAllocator();
            recvBufAllocHandle.reset(config);
            epollInBefore();
            while (true) {
                th = null;
                try {
                    byteBuf = recvBufAllocHandle.allocate(allocator);
                } catch (Throwable th2) {
                    th = th2;
                    byteBuf = null;
                }
                try {
                    recvBufAllocHandle.attemptedBytesRead(byteBuf.writableBytes());
                    if (byteBuf.hasMemoryAddress()) {
                        recvFrom = EpollDatagramChannel.this.socket.recvFromAddress(byteBuf.memoryAddress(), byteBuf.writerIndex(), byteBuf.capacity());
                    } else {
                        ByteBuffer internalNioBuffer = byteBuf.internalNioBuffer(byteBuf.writerIndex(), byteBuf.writableBytes());
                        recvFrom = EpollDatagramChannel.this.socket.recvFrom(internalNioBuffer, internalNioBuffer.position(), internalNioBuffer.limit());
                    }
                    if (recvFrom == null) {
                        break;
                    }
                    recvBufAllocHandle.incMessagesRead(1);
                    recvBufAllocHandle.lastBytesRead(recvFrom.receivedAmount());
                    byteBuf.writerIndex(byteBuf.writerIndex() + recvBufAllocHandle.lastBytesRead());
                    this.readPending = false;
                    pipeline.fireChannelRead((Object) new DatagramPacket(byteBuf, (InetSocketAddress) localAddress(), recvFrom));
                    if (!recvBufAllocHandle.continueReading()) {
                        break;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    th = th;
                    if (byteBuf != null) {
                        try {
                            byteBuf.release();
                        } catch (Throwable th4) {
                            epollInFinally(config);
                            throw th4;
                        }
                    }
                    recvBufAllocHandle.readComplete();
                    pipeline.fireChannelReadComplete();
                    if (th != null) {
                    }
                    epollInFinally(config);
                }
            }
            recvBufAllocHandle.readComplete();
            pipeline.fireChannelReadComplete();
            if (th != null) {
                pipeline.fireExceptionCaught(th);
            }
            epollInFinally(config);
        }
    }
}
