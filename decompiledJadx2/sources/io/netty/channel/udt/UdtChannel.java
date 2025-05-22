package io.netty.channel.udt;

import io.netty.channel.Channel;
import java.net.InetSocketAddress;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
@Deprecated
/* loaded from: classes.dex */
public interface UdtChannel extends Channel {
    @Override // io.netty.channel.Channel
    UdtChannelConfig config();

    @Override // io.netty.channel.Channel
    InetSocketAddress localAddress();

    @Override // io.netty.channel.Channel
    InetSocketAddress remoteAddress();
}
