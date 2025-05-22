package io.grpc.netty.shaded.io.netty.handler.codec;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.internal.TypeParameterMatcher;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class MessageToMessageDecoder<I> extends ChannelInboundHandlerAdapter {
    private final TypeParameterMatcher matcher;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void decode(ChannelHandlerContext channelHandlerContext, I i, List<Object> list) throws Exception;

    /* JADX INFO: Access modifiers changed from: protected */
    public MessageToMessageDecoder() {
        this.matcher = TypeParameterMatcher.find(this, MessageToMessageDecoder.class, "I");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MessageToMessageDecoder(Class<? extends I> cls) {
        this.matcher = TypeParameterMatcher.get(cls);
    }

    public boolean acceptInboundMessage(Object obj) throws Exception {
        return this.matcher.match(obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        CodecOutputList newInstance = CodecOutputList.newInstance();
        int i = 0;
        try {
            try {
                try {
                    if (acceptInboundMessage(obj)) {
                        try {
                            decode(channelHandlerContext, obj, newInstance);
                            ReferenceCountUtil.release(obj);
                        } catch (Throwable th) {
                            ReferenceCountUtil.release(obj);
                            throw th;
                        }
                    } else {
                        newInstance.add(obj);
                    }
                    try {
                        int size = newInstance.size();
                        while (i < size) {
                            channelHandlerContext.fireChannelRead(newInstance.getUnsafe(i));
                            i++;
                        }
                    } finally {
                    }
                } catch (Exception e) {
                    throw new DecoderException(e);
                }
            } catch (DecoderException e2) {
                throw e2;
            }
        } catch (Throwable th2) {
            try {
                int size2 = newInstance.size();
                while (i < size2) {
                    channelHandlerContext.fireChannelRead(newInstance.getUnsafe(i));
                    i++;
                }
                throw th2;
            } finally {
            }
        }
    }
}
