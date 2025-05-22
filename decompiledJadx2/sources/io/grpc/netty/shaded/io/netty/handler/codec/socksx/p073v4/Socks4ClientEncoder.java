package io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder;
import io.grpc.netty.shaded.io.netty.util.NetUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@ChannelHandler.Sharable
/* loaded from: classes7.dex */
public final class Socks4ClientEncoder extends MessageToByteEncoder<Socks4CommandRequest> {
    public static final Socks4ClientEncoder INSTANCE = new Socks4ClientEncoder();
    private static final byte[] IPv4_DOMAIN_MARKER = {0, 0, 0, 1};

    private Socks4ClientEncoder() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.MessageToByteEncoder
    public void encode(ChannelHandlerContext channelHandlerContext, Socks4CommandRequest socks4CommandRequest, ByteBuf byteBuf) throws Exception {
        byteBuf.writeByte(socks4CommandRequest.version().byteValue());
        byteBuf.writeByte(socks4CommandRequest.type().byteValue());
        byteBuf.writeShort(socks4CommandRequest.dstPort());
        if (NetUtil.isValidIpV4Address(socks4CommandRequest.dstAddr())) {
            byteBuf.writeBytes(NetUtil.createByteArrayFromIpAddressString(socks4CommandRequest.dstAddr()));
            ByteBufUtil.writeAscii(byteBuf, socks4CommandRequest.userId());
            byteBuf.writeByte(0);
        } else {
            byteBuf.writeBytes(IPv4_DOMAIN_MARKER);
            ByteBufUtil.writeAscii(byteBuf, socks4CommandRequest.userId());
            byteBuf.writeByte(0);
            ByteBufUtil.writeAscii(byteBuf, socks4CommandRequest.dstAddr());
            byteBuf.writeByte(0);
        }
    }
}
