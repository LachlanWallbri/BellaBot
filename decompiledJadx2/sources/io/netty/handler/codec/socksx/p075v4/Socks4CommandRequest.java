package io.netty.handler.codec.socksx.p075v4;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface Socks4CommandRequest extends Socks4Message {
    String dstAddr();

    int dstPort();

    Socks4CommandType type();

    String userId();
}
