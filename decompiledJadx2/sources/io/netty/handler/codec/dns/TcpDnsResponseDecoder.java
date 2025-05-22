package io.netty.handler.codec.dns;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import java.net.SocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes7.dex */
public final class TcpDnsResponseDecoder extends LengthFieldBasedFrameDecoder {
    private final DnsResponseDecoder<SocketAddress> responseDecoder;

    public TcpDnsResponseDecoder() {
        this(DnsRecordDecoder.DEFAULT, 65536);
    }

    public TcpDnsResponseDecoder(DnsRecordDecoder dnsRecordDecoder, int i) {
        super(i, 0, 2, 0, 2);
        this.responseDecoder = new DnsResponseDecoder<SocketAddress>(dnsRecordDecoder) { // from class: io.netty.handler.codec.dns.TcpDnsResponseDecoder.1
            @Override // io.netty.handler.codec.dns.DnsResponseDecoder
            protected DnsResponse newResponse(SocketAddress socketAddress, SocketAddress socketAddress2, int i2, DnsOpCode dnsOpCode, DnsResponseCode dnsResponseCode) {
                return new DefaultDnsResponse(i2, dnsOpCode, dnsResponseCode);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.LengthFieldBasedFrameDecoder
    public Object decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        ByteBuf byteBuf2 = (ByteBuf) super.decode(channelHandlerContext, byteBuf);
        if (byteBuf2 == null) {
            return null;
        }
        try {
            return this.responseDecoder.decode(channelHandlerContext.channel().remoteAddress(), channelHandlerContext.channel().localAddress(), byteBuf2.slice());
        } finally {
            byteBuf2.release();
        }
    }

    @Override // io.netty.handler.codec.LengthFieldBasedFrameDecoder
    protected ByteBuf extractFrame(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, int i, int i2) {
        return byteBuf.copy(i, i2);
    }
}
