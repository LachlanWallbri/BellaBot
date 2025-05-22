package io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4;

import io.grpc.netty.shaded.io.netty.handler.codec.socksx.AbstractSocksMessage;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.SocksVersion;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractSocks4Message extends AbstractSocksMessage implements Socks4Message {
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.socksx.SocksMessage
    public final SocksVersion version() {
        return SocksVersion.SOCKS4a;
    }
}
