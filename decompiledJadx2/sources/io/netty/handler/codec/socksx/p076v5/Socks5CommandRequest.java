package io.netty.handler.codec.socksx.p076v5;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface Socks5CommandRequest extends Socks5Message {
    String dstAddr();

    Socks5AddressType dstAddrType();

    int dstPort();

    Socks5CommandType type();
}
