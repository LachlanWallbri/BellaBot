package io.netty.channel.kqueue;

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
import io.netty.channel.kqueue.AbstractKQueueChannel;
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
public final class KQueueDatagramChannel extends AbstractKQueueChannel implements DatagramChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final KQueueDatagramChannelConfig config;
    private volatile boolean connected;
    private static final ChannelMetadata METADATA = new ChannelMetadata(true);
    private static final String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName((Class<?>) DatagramPacket.class) + ", " + StringUtil.simpleClassName((Class<?>) AddressedEnvelope.class) + Typography.less + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ", " + StringUtil.simpleClassName((Class<?>) InetSocketAddress.class) + ">, " + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ')';

    @Override // io.netty.channel.kqueue.AbstractKQueueChannel, io.netty.channel.Channel
    public /* bridge */ /* synthetic */ boolean isOpen() {
        return super.isOpen();
    }

    public KQueueDatagramChannel() {
        super((Channel) null, BsdSocket.newSocketDgram(), false);
        this.config = new KQueueDatagramChannelConfig(this);
    }

    public KQueueDatagramChannel(int i) {
        this(new BsdSocket(i), true);
    }

    KQueueDatagramChannel(BsdSocket bsdSocket, boolean z) {
        super((Channel) null, bsdSocket, z);
        this.config = new KQueueDatagramChannelConfig(this);
    }

    @Override // io.netty.channel.AbstractChannel, io.netty.channel.Channel
    public InetSocketAddress remoteAddress() {
        return (InetSocketAddress) super.remoteAddress();
    }

    @Override // io.netty.channel.AbstractChannel, io.netty.channel.Channel
    public InetSocketAddress localAddress() {
        return (InetSocketAddress) super.localAddress();
    }

    @Override // io.netty.channel.kqueue.AbstractKQueueChannel, io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    @Override // io.netty.channel.kqueue.AbstractKQueueChannel, io.netty.channel.Channel
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
    @Override // io.netty.channel.kqueue.AbstractKQueueChannel, io.netty.channel.AbstractChannel
    public AbstractKQueueChannel.AbstractKQueueUnsafe newUnsafe() {
        return new KQueueDatagramChannelUnsafe();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.channel.kqueue.AbstractKQueueChannel, io.netty.channel.AbstractChannel
    public void doBind(SocketAddress socketAddress) throws Exception {
        super.doBind(socketAddress);
        this.active = true;
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        while (true) {
            Object current = channelOutboundBuffer.current();
            boolean z = false;
            if (current == null) {
                writeFilter(false);
                return;
            }
            try {
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
            } catch (IOException e) {
                channelOutboundBuffer.remove(e);
            }
            if (z) {
                channelOutboundBuffer.remove();
            } else {
                writeFilter(true);
                return;
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
            IovArray cleanArray = ((KQueueEventLoop) eventLoop()).cleanArray();
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

    @Override // io.netty.channel.kqueue.AbstractKQueueChannel, io.netty.channel.Channel
    public KQueueDatagramChannelConfig config() {
        return this.config;
    }

    @Override // io.netty.channel.kqueue.AbstractKQueueChannel, io.netty.channel.AbstractChannel
    protected void doDisconnect() throws Exception {
        this.socket.disconnect();
        this.active = false;
        this.connected = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.channel.kqueue.AbstractKQueueChannel
    public boolean doConnect(SocketAddress socketAddress, SocketAddress socketAddress2) throws Exception {
        if (!super.doConnect(socketAddress, socketAddress2)) {
            return false;
        }
        this.connected = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.channel.kqueue.AbstractKQueueChannel, io.netty.channel.AbstractChannel
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
    public final class KQueueDatagramChannelUnsafe extends AbstractKQueueChannel.AbstractKQueueUnsafe {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        KQueueDatagramChannelUnsafe() {
            super();
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x0067, code lost:
        
            r11.lastBytesRead(-1);
            r4.release();
         */
        /* JADX WARN: Removed duplicated region for block: B:22:0x00b3 A[Catch: all -> 0x00a9, TRY_LEAVE, TryCatch #1 {all -> 0x00a9, blocks: (B:20:0x00ab, B:22:0x00b3, B:39:0x00a5), top: B:38:0x00a5 }] */
        @Override // io.netty.channel.kqueue.AbstractKQueueChannel.AbstractKQueueUnsafe
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        void readReady(KQueueRecvByteAllocatorHandle kQueueRecvByteAllocatorHandle) {
            Throwable th;
            ByteBuf byteBuf;
            DatagramSocketAddress recvFrom;
            KQueueDatagramChannelConfig config = KQueueDatagramChannel.this.config();
            if (KQueueDatagramChannel.this.shouldBreakReadReady(config)) {
                clearReadFilter0();
                return;
            }
            ChannelPipeline pipeline = KQueueDatagramChannel.this.pipeline();
            ByteBufAllocator allocator = config.getAllocator();
            kQueueRecvByteAllocatorHandle.reset(config);
            readReadyBefore();
            while (true) {
                th = null;
                try {
                    byteBuf = kQueueRecvByteAllocatorHandle.allocate(allocator);
                    try {
                        kQueueRecvByteAllocatorHandle.attemptedBytesRead(byteBuf.writableBytes());
                        if (byteBuf.hasMemoryAddress()) {
                            recvFrom = KQueueDatagramChannel.this.socket.recvFromAddress(byteBuf.memoryAddress(), byteBuf.writerIndex(), byteBuf.capacity());
                        } else {
                            ByteBuffer internalNioBuffer = byteBuf.internalNioBuffer(byteBuf.writerIndex(), byteBuf.writableBytes());
                            recvFrom = KQueueDatagramChannel.this.socket.recvFrom(internalNioBuffer, internalNioBuffer.position(), internalNioBuffer.limit());
                        }
                        if (recvFrom == null) {
                            break;
                        }
                        kQueueRecvByteAllocatorHandle.incMessagesRead(1);
                        kQueueRecvByteAllocatorHandle.lastBytesRead(recvFrom.receivedAmount());
                        byteBuf.writerIndex(byteBuf.writerIndex() + kQueueRecvByteAllocatorHandle.lastBytesRead());
                        this.readPending = false;
                        pipeline.fireChannelRead((Object) new DatagramPacket(byteBuf, (InetSocketAddress) localAddress(), recvFrom));
                        if (!kQueueRecvByteAllocatorHandle.continueReading()) {
                            break;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        th = th;
                        if (byteBuf != null) {
                            try {
                                byteBuf.release();
                            } catch (Throwable th3) {
                                readReadyFinally(config);
                                throw th3;
                            }
                        }
                        kQueueRecvByteAllocatorHandle.readComplete();
                        pipeline.fireChannelReadComplete();
                        if (th != null) {
                        }
                        readReadyFinally(config);
                    }
                } catch (Throwable th4) {
                    th = th4;
                    byteBuf = null;
                }
            }
            kQueueRecvByteAllocatorHandle.readComplete();
            pipeline.fireChannelReadComplete();
            if (th != null) {
                pipeline.fireExceptionCaught(th);
            }
            readReadyFinally(config);
        }
    }
}
