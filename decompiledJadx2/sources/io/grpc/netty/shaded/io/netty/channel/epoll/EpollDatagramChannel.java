package io.grpc.netty.shaded.io.netty.channel.epoll;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.AddressedEnvelope;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelMetadata;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.DefaultAddressedEnvelope;
import io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel;
import io.grpc.netty.shaded.io.netty.channel.epoll.NativeDatagramPacketArray;
import io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel;
import io.grpc.netty.shaded.io.netty.channel.socket.DatagramPacket;
import io.grpc.netty.shaded.io.netty.channel.socket.InternetProtocolFamily;
import io.grpc.netty.shaded.io.netty.channel.unix.DatagramSocketAddress;
import io.grpc.netty.shaded.io.netty.channel.unix.Errors;
import io.grpc.netty.shaded.io.netty.channel.unix.IovArray;
import io.grpc.netty.shaded.io.netty.channel.unix.Socket;
import io.grpc.netty.shaded.io.netty.channel.unix.UnixChannelUtil;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.RecyclableArrayList;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import kotlin.text.Typography;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class EpollDatagramChannel extends AbstractEpollChannel implements DatagramChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final EpollDatagramChannelConfig config;
    private volatile boolean connected;
    private static final ChannelMetadata METADATA = new ChannelMetadata(true);
    private static final String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName((Class<?>) DatagramPacket.class) + ", " + StringUtil.simpleClassName((Class<?>) AddressedEnvelope.class) + Typography.less + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ", " + StringUtil.simpleClassName((Class<?>) InetSocketAddress.class) + ">, " + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ')';

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public /* bridge */ /* synthetic */ boolean isOpen() {
        return super.isOpen();
    }

    public EpollDatagramChannel() {
        this((InternetProtocolFamily) null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public EpollDatagramChannel(InternetProtocolFamily internetProtocolFamily) {
        this(LinuxSocket.newSocketDgram(r3), false);
        boolean z;
        if (internetProtocolFamily == null) {
            z = Socket.isIPv6Preferred();
        } else {
            z = internetProtocolFamily == InternetProtocolFamily.IPv6;
        }
    }

    public EpollDatagramChannel(int i) {
        this(new LinuxSocket(i), true);
    }

    private EpollDatagramChannel(LinuxSocket linuxSocket, boolean z) {
        super((Channel) null, linuxSocket, z);
        this.config = new EpollDatagramChannelConfig(this);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public InetSocketAddress remoteAddress() {
        return (InetSocketAddress) super.remoteAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public InetSocketAddress localAddress() {
        return (InetSocketAddress) super.localAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isActive() {
        return this.socket.isOpen() && ((this.config.getActiveOnOpen() && isRegistered()) || this.active);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public boolean isConnected() {
        return this.connected;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress) {
        return joinGroup(inetAddress, newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress, ChannelPromise channelPromise) {
        try {
            return joinGroup(inetAddress, NetworkInterface.getByInetAddress(localAddress().getAddress()), null, channelPromise);
        } catch (IOException e) {
            channelPromise.setFailure((Throwable) e);
            return channelPromise;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface) {
        return joinGroup(inetSocketAddress, networkInterface, newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface, ChannelPromise channelPromise) {
        return joinGroup(inetSocketAddress.getAddress(), networkInterface, null, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        return joinGroup(inetAddress, networkInterface, inetAddress2, newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture joinGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        ObjectUtil.checkNotNull(inetAddress, "multicastAddress");
        ObjectUtil.checkNotNull(networkInterface, "networkInterface");
        try {
            this.socket.joinGroup(inetAddress, networkInterface, inetAddress2);
            channelPromise.setSuccess();
        } catch (IOException e) {
            channelPromise.setFailure((Throwable) e);
        }
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress) {
        return leaveGroup(inetAddress, newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress, ChannelPromise channelPromise) {
        try {
            return leaveGroup(inetAddress, NetworkInterface.getByInetAddress(localAddress().getAddress()), null, channelPromise);
        } catch (IOException e) {
            channelPromise.setFailure((Throwable) e);
            return channelPromise;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface) {
        return leaveGroup(inetSocketAddress, networkInterface, newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetSocketAddress inetSocketAddress, NetworkInterface networkInterface, ChannelPromise channelPromise) {
        return leaveGroup(inetSocketAddress.getAddress(), networkInterface, null, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        return leaveGroup(inetAddress, networkInterface, inetAddress2, newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture leaveGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        ObjectUtil.checkNotNull(inetAddress, "multicastAddress");
        ObjectUtil.checkNotNull(networkInterface, "networkInterface");
        try {
            this.socket.leaveGroup(inetAddress, networkInterface, inetAddress2);
            channelPromise.setSuccess();
        } catch (IOException e) {
            channelPromise.setFailure((Throwable) e);
        }
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture block(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) {
        return block(inetAddress, networkInterface, inetAddress2, newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture block(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2, ChannelPromise channelPromise) {
        ObjectUtil.checkNotNull(inetAddress, "multicastAddress");
        ObjectUtil.checkNotNull(inetAddress2, "sourceToBlock");
        ObjectUtil.checkNotNull(networkInterface, "networkInterface");
        channelPromise.setFailure((Throwable) new UnsupportedOperationException("Multicast not supported"));
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture block(InetAddress inetAddress, InetAddress inetAddress2) {
        return block(inetAddress, inetAddress2, newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DatagramChannel
    public ChannelFuture block(InetAddress inetAddress, InetAddress inetAddress2, ChannelPromise channelPromise) {
        try {
            return block(inetAddress, NetworkInterface.getByInetAddress(localAddress().getAddress()), inetAddress2, channelPromise);
        } catch (Throwable th) {
            channelPromise.setFailure(th);
            return channelPromise;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    public AbstractEpollChannel.AbstractEpollUnsafe newUnsafe() {
        return new EpollDatagramChannelUnsafe();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    public void doBind(SocketAddress socketAddress) throws Exception {
        if (socketAddress instanceof InetSocketAddress) {
            InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddress;
            if (inetSocketAddress.getAddress().isAnyLocalAddress() && (inetSocketAddress.getAddress() instanceof Inet4Address) && Socket.isIPv6Preferred()) {
                socketAddress = new InetSocketAddress(LinuxSocket.INET6_ANY, inetSocketAddress.getPort());
            }
        }
        super.doBind(socketAddress);
        this.active = true;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        boolean z;
        while (true) {
            Object current = channelOutboundBuffer.current();
            if (current == null) {
                clearFlag(Native.EPOLLOUT);
                return;
            }
            try {
                z = false;
            } catch (IOException e) {
                channelOutboundBuffer.remove(e);
            }
            if (Native.IS_SUPPORTING_SENDMMSG && channelOutboundBuffer.size() > 1) {
                NativeDatagramPacketArray cleanDatagramPacketArray = cleanDatagramPacketArray();
                cleanDatagramPacketArray.add(channelOutboundBuffer, isConnected());
                int count = cleanDatagramPacketArray.count();
                if (count >= 1) {
                    NativeDatagramPacketArray.NativeDatagramPacket[] packets = cleanDatagramPacketArray.packets();
                    int i = 0;
                    while (count > 0) {
                        int sendmmsg = this.socket.sendmmsg(packets, i, count);
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
                }
            }
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
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x00cd  */
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
            IovArray cleanIovArray = ((EpollEventLoop) eventLoop()).cleanIovArray();
            cleanIovArray.add(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes());
            int count = cleanIovArray.count();
            if (inetSocketAddress == null) {
                writevAddresses = this.socket.writevAddresses(cleanIovArray.memoryAddress(0), count);
                return writevAddresses <= 0;
            }
            sendTo = this.socket.sendToAddresses(cleanIovArray.memoryAddress(0), count, inetSocketAddress.getAddress(), inetSocketAddress.getPort());
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

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
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

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public EpollDatagramChannelConfig config() {
        return this.config;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doDisconnect() throws Exception {
        this.socket.disconnect();
        this.active = false;
        this.connected = false;
        resetCachedAddresses();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel
    public boolean doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        if (!super.doConnect(socketAddress, socketAddress2)) {
            return false;
        }
        this.connected = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    public void doClose() throws Exception {
        super.doClose();
        this.connected = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class EpollDatagramChannelUnsafe extends AbstractEpollChannel.AbstractEpollUnsafe {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        EpollDatagramChannelUnsafe() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel.AbstractEpollUnsafe
        public void epollInReady() {
            boolean isConnected;
            int i;
            boolean scatteringRead;
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
            Throwable th = null;
            try {
                isConnected = EpollDatagramChannel.this.isConnected();
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                do {
                    ByteBuf allocate = recvBufAllocHandle.allocate(allocator);
                    int maxDatagramPayloadSize = EpollDatagramChannel.this.config().getMaxDatagramPayloadSize();
                    if (Native.IS_SUPPORTING_RECVMMSG) {
                        i = maxDatagramPayloadSize == 0 ? 1 : allocate.writableBytes() / maxDatagramPayloadSize;
                    } else {
                        i = 0;
                    }
                    if (i > 1) {
                        scatteringRead = EpollDatagramChannel.this.scatteringRead(recvBufAllocHandle, allocate, maxDatagramPayloadSize, i);
                    } else if (isConnected) {
                        try {
                            scatteringRead = EpollDatagramChannel.this.connectedRead(recvBufAllocHandle, allocate, maxDatagramPayloadSize);
                        } catch (Errors.NativeIoException e) {
                            if (isConnected) {
                                throw EpollDatagramChannel.this.translateForConnected(e);
                            }
                            throw e;
                        }
                    } else {
                        scatteringRead = EpollDatagramChannel.this.read(recvBufAllocHandle, allocate, maxDatagramPayloadSize);
                    }
                    if (scatteringRead) {
                        this.readPending = false;
                    }
                    break;
                } while (recvBufAllocHandle.continueReading());
                break;
                recvBufAllocHandle.readComplete();
                pipeline.fireChannelReadComplete();
                if (th != null) {
                    pipeline.fireExceptionCaught(th);
                }
            } finally {
                epollInFinally(config);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean connectedRead(EpollRecvByteAllocatorHandle epollRecvByteAllocatorHandle, ByteBuf byteBuf, int i) throws Exception {
        int writableBytes;
        int read;
        try {
            if (i != 0) {
                writableBytes = Math.min(byteBuf.writableBytes(), i);
            } else {
                writableBytes = byteBuf.writableBytes();
            }
            epollRecvByteAllocatorHandle.attemptedBytesRead(writableBytes);
            int writerIndex = byteBuf.writerIndex();
            if (byteBuf.hasMemoryAddress()) {
                read = this.socket.readAddress(byteBuf.memoryAddress(), writerIndex, writerIndex + writableBytes);
            } else {
                ByteBuffer internalNioBuffer = byteBuf.internalNioBuffer(writerIndex, writableBytes);
                read = this.socket.read(internalNioBuffer, internalNioBuffer.position(), internalNioBuffer.limit());
            }
            if (read <= 0) {
                epollRecvByteAllocatorHandle.lastBytesRead(read);
                return false;
            }
            byteBuf.writerIndex(writerIndex + read);
            if (i <= 0) {
                writableBytes = read;
            }
            epollRecvByteAllocatorHandle.lastBytesRead(writableBytes);
            DatagramPacket datagramPacket = new DatagramPacket(byteBuf, localAddress(), remoteAddress());
            epollRecvByteAllocatorHandle.incMessagesRead(1);
            pipeline().fireChannelRead((Object) datagramPacket);
            return true;
        } finally {
            if (byteBuf != null) {
                byteBuf.release();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IOException translateForConnected(Errors.NativeIoException nativeIoException) {
        if (nativeIoException.expectedErr() != Errors.ERROR_ECONNREFUSED_NEGATIVE) {
            return nativeIoException;
        }
        PortUnreachableException portUnreachableException = new PortUnreachableException(nativeIoException.getMessage());
        portUnreachableException.initCause(nativeIoException);
        return portUnreachableException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean scatteringRead(EpollRecvByteAllocatorHandle epollRecvByteAllocatorHandle, ByteBuf byteBuf, int i, int i2) throws IOException {
        RecyclableArrayList recyclableArrayList = null;
        try {
            int writerIndex = byteBuf.writerIndex();
            NativeDatagramPacketArray cleanDatagramPacketArray = cleanDatagramPacketArray();
            int i3 = writerIndex;
            int i4 = 0;
            while (i4 < i2 && cleanDatagramPacketArray.addWritable(byteBuf, i3, i)) {
                i4++;
                i3 += i;
            }
            epollRecvByteAllocatorHandle.attemptedBytesRead(i3 - byteBuf.writerIndex());
            NativeDatagramPacketArray.NativeDatagramPacket[] packets = cleanDatagramPacketArray.packets();
            int recvmmsg = this.socket.recvmmsg(packets, 0, cleanDatagramPacketArray.count());
            if (recvmmsg == 0) {
                epollRecvByteAllocatorHandle.lastBytesRead(-1);
                if (byteBuf != null) {
                    byteBuf.release();
                }
                return false;
            }
            int i5 = recvmmsg * i;
            byteBuf.writerIndex(i5);
            InetSocketAddress localAddress = localAddress();
            if (recvmmsg == 1) {
                DatagramPacket newDatagramPacket = packets[0].newDatagramPacket(byteBuf, localAddress);
                epollRecvByteAllocatorHandle.lastBytesRead(i);
                epollRecvByteAllocatorHandle.incMessagesRead(1);
                pipeline().fireChannelRead((Object) newDatagramPacket);
                return true;
            }
            RecyclableArrayList newInstance = RecyclableArrayList.newInstance();
            for (int i6 = 0; i6 < recvmmsg; i6++) {
                newInstance.add(packets[i6].newDatagramPacket(byteBuf.readRetainedSlice(i), localAddress));
            }
            epollRecvByteAllocatorHandle.lastBytesRead(i5);
            epollRecvByteAllocatorHandle.incMessagesRead(recvmmsg);
            for (int i7 = 0; i7 < recvmmsg; i7++) {
                pipeline().fireChannelRead(newInstance.set(i7, Unpooled.EMPTY_BUFFER));
            }
            newInstance.recycle();
            if (byteBuf != null) {
                byteBuf.release();
            }
            return true;
        } catch (Throwable th) {
            if (byteBuf != null) {
                byteBuf.release();
            }
            if (0 != 0) {
                for (int i8 = 0; i8 < recyclableArrayList.size(); i8++) {
                    ReferenceCountUtil.release(recyclableArrayList.get(i8));
                }
                recyclableArrayList.recycle();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean read(EpollRecvByteAllocatorHandle epollRecvByteAllocatorHandle, ByteBuf byteBuf, int i) throws IOException {
        int writableBytes;
        DatagramSocketAddress recvFrom;
        try {
            if (i != 0) {
                writableBytes = Math.min(byteBuf.writableBytes(), i);
            } else {
                writableBytes = byteBuf.writableBytes();
            }
            epollRecvByteAllocatorHandle.attemptedBytesRead(writableBytes);
            int writerIndex = byteBuf.writerIndex();
            if (byteBuf.hasMemoryAddress()) {
                recvFrom = this.socket.recvFromAddress(byteBuf.memoryAddress(), writerIndex, writerIndex + writableBytes);
            } else {
                ByteBuffer internalNioBuffer = byteBuf.internalNioBuffer(writerIndex, writableBytes);
                recvFrom = this.socket.recvFrom(internalNioBuffer, internalNioBuffer.position(), internalNioBuffer.limit());
            }
            if (recvFrom == null) {
                epollRecvByteAllocatorHandle.lastBytesRead(-1);
                return false;
            }
            InetSocketAddress localAddress = recvFrom.localAddress();
            if (localAddress == null) {
                localAddress = localAddress();
            }
            int receivedAmount = recvFrom.receivedAmount();
            if (i <= 0) {
                writableBytes = receivedAmount;
            }
            epollRecvByteAllocatorHandle.lastBytesRead(writableBytes);
            byteBuf.writerIndex(writerIndex + receivedAmount);
            epollRecvByteAllocatorHandle.incMessagesRead(1);
            pipeline().fireChannelRead((Object) new DatagramPacket(byteBuf, localAddress, recvFrom));
            return true;
        } finally {
            if (byteBuf != null) {
                byteBuf.release();
            }
        }
    }

    private NativeDatagramPacketArray cleanDatagramPacketArray() {
        return ((EpollEventLoop) eventLoop()).cleanDatagramPacketArray();
    }
}
