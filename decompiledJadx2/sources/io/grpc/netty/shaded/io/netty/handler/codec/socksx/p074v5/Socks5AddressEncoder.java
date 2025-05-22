package io.grpc.netty.shaded.io.netty.handler.codec.socksx.p074v5;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.handler.codec.EncoderException;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.NetUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Socks5AddressEncoder {
    public static final Socks5AddressEncoder DEFAULT = new Socks5AddressEncoder() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.socksx.v5.Socks5AddressEncoder.1
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.socksx.p074v5.Socks5AddressEncoder
        public void encodeAddress(Socks5AddressType socks5AddressType, String str, ByteBuf byteBuf) throws Exception {
            byte byteValue = socks5AddressType.byteValue();
            if (byteValue == Socks5AddressType.IPv4.byteValue()) {
                if (str != null) {
                    byteBuf.writeBytes(NetUtil.createByteArrayFromIpAddressString(str));
                    return;
                } else {
                    byteBuf.writeInt(0);
                    return;
                }
            }
            if (byteValue == Socks5AddressType.DOMAIN.byteValue()) {
                if (str != null) {
                    byteBuf.writeByte(str.length());
                    byteBuf.writeCharSequence(str, CharsetUtil.US_ASCII);
                    return;
                } else {
                    byteBuf.writeByte(0);
                    return;
                }
            }
            if (byteValue != Socks5AddressType.IPv6.byteValue()) {
                throw new EncoderException("unsupported addrType: " + (socks5AddressType.byteValue() & 255));
            }
            if (str != null) {
                byteBuf.writeBytes(NetUtil.createByteArrayFromIpAddressString(str));
            } else {
                byteBuf.writeLong(0L);
                byteBuf.writeLong(0L);
            }
        }
    };

    void encodeAddress(Socks5AddressType socks5AddressType, String str, ByteBuf byteBuf) throws Exception;
}
