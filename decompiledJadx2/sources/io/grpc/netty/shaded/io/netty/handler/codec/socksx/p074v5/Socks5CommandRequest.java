package io.grpc.netty.shaded.io.netty.handler.codec.socksx.p074v5;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Socks5CommandRequest extends Socks5Message {
    String dstAddr();

    Socks5AddressType dstAddrType();

    int dstPort();

    Socks5CommandType type();
}
