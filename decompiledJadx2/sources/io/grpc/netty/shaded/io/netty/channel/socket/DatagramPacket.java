package io.grpc.netty.shaded.io.netty.channel.socket;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder;
import io.grpc.netty.shaded.io.netty.channel.DefaultAddressedEnvelope;
import java.net.InetSocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class DatagramPacket extends DefaultAddressedEnvelope<ByteBuf, InetSocketAddress> implements ByteBufHolder {
    @Override // io.grpc.netty.shaded.io.netty.channel.DefaultAddressedEnvelope, io.grpc.netty.shaded.io.netty.channel.AddressedEnvelope
    public /* bridge */ /* synthetic */ ByteBuf content() {
        return (ByteBuf) super.content();
    }

    public DatagramPacket(ByteBuf byteBuf, InetSocketAddress inetSocketAddress) {
        super(byteBuf, inetSocketAddress);
    }

    public DatagramPacket(ByteBuf byteBuf, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2) {
        super(byteBuf, inetSocketAddress, inetSocketAddress2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public DatagramPacket copy() {
        return replace(((ByteBuf) content()).copy());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public DatagramPacket duplicate() {
        return replace(((ByteBuf) content()).duplicate());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public DatagramPacket retainedDuplicate() {
        return replace(((ByteBuf) content()).retainedDuplicate());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBufHolder
    public DatagramPacket replace(ByteBuf byteBuf) {
        return new DatagramPacket(byteBuf, recipient(), sender());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.DefaultAddressedEnvelope, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public DatagramPacket retain() {
        super.retain();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.DefaultAddressedEnvelope, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public DatagramPacket retain(int i) {
        super.retain(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.DefaultAddressedEnvelope, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public DatagramPacket touch() {
        super.touch();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.DefaultAddressedEnvelope, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public DatagramPacket touch(Object obj) {
        super.touch(obj);
        return this;
    }
}
