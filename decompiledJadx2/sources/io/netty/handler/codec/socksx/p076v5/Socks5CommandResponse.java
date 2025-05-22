package io.netty.handler.codec.socksx.p076v5;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface Socks5CommandResponse extends Socks5Message {
    String bndAddr();

    Socks5AddressType bndAddrType();

    int bndPort();

    Socks5CommandStatus status();
}
