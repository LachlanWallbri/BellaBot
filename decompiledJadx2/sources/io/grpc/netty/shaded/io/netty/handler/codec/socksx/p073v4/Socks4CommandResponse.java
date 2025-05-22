package io.grpc.netty.shaded.io.netty.handler.codec.socksx.p073v4;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Socks4CommandResponse extends Socks4Message {
    String dstAddr();

    int dstPort();

    Socks4CommandStatus status();
}
