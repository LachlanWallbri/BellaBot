package io.grpc.netty.shaded.io.netty.handler.codec.spdy;

import io.grpc.netty.shaded.io.netty.channel.CombinedChannelDuplexHandler;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class SpdyHttpCodec extends CombinedChannelDuplexHandler<SpdyHttpDecoder, SpdyHttpEncoder> {
    public SpdyHttpCodec(SpdyVersion spdyVersion, int i) {
        super(new SpdyHttpDecoder(spdyVersion, i), new SpdyHttpEncoder(spdyVersion));
    }

    public SpdyHttpCodec(SpdyVersion spdyVersion, int i, boolean z) {
        super(new SpdyHttpDecoder(spdyVersion, i, z), new SpdyHttpEncoder(spdyVersion));
    }
}
