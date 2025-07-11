package io.netty.handler.codec.compression;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToByteEncoder;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public abstract class ZlibEncoder extends MessageToByteEncoder<ByteBuf> {
    public abstract ChannelFuture close();

    public abstract ChannelFuture close(ChannelPromise channelPromise);

    public abstract boolean isClosed();

    /* JADX INFO: Access modifiers changed from: protected */
    public ZlibEncoder() {
        super(false);
    }
}
