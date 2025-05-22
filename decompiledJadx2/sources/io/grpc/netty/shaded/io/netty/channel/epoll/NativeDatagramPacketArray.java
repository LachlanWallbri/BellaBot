package io.grpc.netty.shaded.io.netty.channel.epoll;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer;
import io.grpc.netty.shaded.io.netty.channel.socket.DatagramPacket;
import io.grpc.netty.shaded.io.netty.channel.unix.IovArray;
import io.grpc.netty.shaded.io.netty.channel.unix.Limits;
import io.grpc.netty.shaded.io.netty.channel.unix.NativeInetAddress;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class NativeDatagramPacketArray {
    private int count;
    private final NativeDatagramPacket[] packets = new NativeDatagramPacket[Limits.UIO_MAX_IOV];
    private final IovArray iovArray = new IovArray();
    private final byte[] ipv4Bytes = new byte[4];
    private final MyMessageProcessor processor = new MyMessageProcessor();

    /* JADX INFO: Access modifiers changed from: package-private */
    public NativeDatagramPacketArray() {
        int i = 0;
        while (true) {
            NativeDatagramPacket[] nativeDatagramPacketArr = this.packets;
            if (i >= nativeDatagramPacketArr.length) {
                return;
            }
            nativeDatagramPacketArr[i] = new NativeDatagramPacket();
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean addWritable(ByteBuf byteBuf, int i, int i2) {
        return add0(byteBuf, i, i2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean add0(ByteBuf byteBuf, int i, int i2, InetSocketAddress inetSocketAddress) {
        if (this.count == this.packets.length) {
            return false;
        }
        if (i2 == 0) {
            return true;
        }
        int count = this.iovArray.count();
        if (count == Limits.IOV_MAX || !this.iovArray.add(byteBuf, i, i2)) {
            return false;
        }
        this.packets[this.count].init(this.iovArray.memoryAddress(count), this.iovArray.count() - count, inetSocketAddress);
        this.count++;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void add(ChannelOutboundBuffer channelOutboundBuffer, boolean z) throws Exception {
        this.processor.connected = z;
        channelOutboundBuffer.forEachFlushedMessage(this.processor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int count() {
        return this.count;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NativeDatagramPacket[] packets() {
        return this.packets;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clear() {
        this.count = 0;
        this.iovArray.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void release() {
        this.iovArray.release();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class MyMessageProcessor implements ChannelOutboundBuffer.MessageProcessor {
        private boolean connected;

        private MyMessageProcessor() {
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer.MessageProcessor
        public boolean processMessage(Object obj) {
            if (obj instanceof DatagramPacket) {
                DatagramPacket datagramPacket = (DatagramPacket) obj;
                ByteBuf byteBuf = (ByteBuf) datagramPacket.content();
                return NativeDatagramPacketArray.this.add0(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes(), datagramPacket.recipient());
            }
            if (!(obj instanceof ByteBuf) || !this.connected) {
                return false;
            }
            ByteBuf byteBuf2 = (ByteBuf) obj;
            return NativeDatagramPacketArray.this.add0(byteBuf2, byteBuf2.readerIndex(), byteBuf2.readableBytes(), null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class NativeDatagramPacket {
        private final byte[] addr = new byte[16];
        private int addrLen;
        private int count;
        private long memoryAddress;
        private int port;
        private int scopeId;

        NativeDatagramPacket() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void init(long j, int i, InetSocketAddress inetSocketAddress) {
            this.memoryAddress = j;
            this.count = i;
            if (inetSocketAddress == null) {
                this.scopeId = 0;
                this.port = 0;
                this.addrLen = 0;
                return;
            }
            InetAddress address = inetSocketAddress.getAddress();
            if (address instanceof Inet6Address) {
                byte[] address2 = address.getAddress();
                byte[] bArr = this.addr;
                System.arraycopy(address2, 0, bArr, 0, bArr.length);
                this.scopeId = ((Inet6Address) address).getScopeId();
            } else {
                NativeInetAddress.copyIpv4MappedIpv6Address(address.getAddress(), this.addr);
                this.scopeId = 0;
            }
            this.addrLen = this.addr.length;
            this.port = inetSocketAddress.getPort();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public DatagramPacket newDatagramPacket(ByteBuf byteBuf, InetSocketAddress inetSocketAddress) throws UnknownHostException {
            InetAddress byAddress;
            if (this.addrLen == NativeDatagramPacketArray.this.ipv4Bytes.length) {
                System.arraycopy(this.addr, 0, NativeDatagramPacketArray.this.ipv4Bytes, 0, this.addrLen);
                byAddress = InetAddress.getByAddress(NativeDatagramPacketArray.this.ipv4Bytes);
            } else {
                byAddress = Inet6Address.getByAddress((String) null, this.addr, this.scopeId);
            }
            return new DatagramPacket(byteBuf.writerIndex(this.count), inetSocketAddress, new InetSocketAddress(byAddress, this.port));
        }
    }
}
