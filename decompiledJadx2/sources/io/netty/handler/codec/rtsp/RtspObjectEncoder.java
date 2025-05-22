package io.netty.handler.codec.rtsp;

import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.http.FullHttpMessage;
import io.netty.handler.codec.http.HttpMessage;
import io.netty.handler.codec.http.HttpObjectEncoder;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
@ChannelHandler.Sharable
@Deprecated
/* loaded from: classes.dex */
public abstract class RtspObjectEncoder<H extends HttpMessage> extends HttpObjectEncoder<H> {
    protected RtspObjectEncoder() {
    }

    @Override // io.netty.handler.codec.http.HttpObjectEncoder, io.netty.handler.codec.MessageToMessageEncoder
    public boolean acceptOutboundMessage(Object obj) throws Exception {
        return obj instanceof FullHttpMessage;
    }
}
