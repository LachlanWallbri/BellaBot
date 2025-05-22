package io.grpc.netty.shaded.io.netty.handler.codec.socksx.p074v5;

import io.grpc.netty.shaded.io.netty.handler.codec.socksx.AbstractSocksMessage;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.SocksVersion;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractSocks5Message extends AbstractSocksMessage implements Socks5Message {
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.socksx.SocksMessage
    public final SocksVersion version() {
        return SocksVersion.SOCKS5;
    }
}
