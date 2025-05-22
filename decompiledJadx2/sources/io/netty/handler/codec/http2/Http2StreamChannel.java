package io.netty.handler.codec.http2;

import io.netty.channel.Channel;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface Http2StreamChannel extends Channel {
    Http2FrameStream stream();
}
