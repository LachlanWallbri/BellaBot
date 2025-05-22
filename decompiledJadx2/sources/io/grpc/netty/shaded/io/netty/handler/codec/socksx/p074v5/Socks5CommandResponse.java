package io.grpc.netty.shaded.io.netty.handler.codec.socksx.p074v5;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Socks5CommandResponse extends Socks5Message {
    String bndAddr();

    Socks5AddressType bndAddrType();

    int bndPort();

    Socks5CommandStatus status();
}
