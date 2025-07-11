package io.netty.handler.codec.base64;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
@ChannelHandler.Sharable
/* loaded from: classes.dex */
public class Base64Encoder extends MessageToMessageEncoder<ByteBuf> {
    private final boolean breakLines;
    private final Base64Dialect dialect;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.MessageToMessageEncoder
    public /* bridge */ /* synthetic */ void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
        encode2(channelHandlerContext, byteBuf, (List<Object>) list);
    }

    public Base64Encoder() {
        this(true);
    }

    public Base64Encoder(boolean z) {
        this(z, Base64Dialect.STANDARD);
    }

    public Base64Encoder(boolean z, Base64Dialect base64Dialect) {
        if (base64Dialect == null) {
            throw new NullPointerException("dialect");
        }
        this.breakLines = z;
        this.dialect = base64Dialect;
    }

    /* renamed from: encode, reason: avoid collision after fix types in other method */
    protected void encode2(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(Base64.encode(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes(), this.breakLines, this.dialect));
    }
}
