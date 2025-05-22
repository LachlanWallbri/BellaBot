package io.grpc.netty.shaded.io.netty.handler.codec;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.PromiseCombiner;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.TypeParameterMatcher;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class MessageToMessageEncoder<I> extends ChannelOutboundHandlerAdapter {
    private final TypeParameterMatcher matcher;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void encode(ChannelHandlerContext channelHandlerContext, I i, List<Object> list) throws Exception;

    /* JADX INFO: Access modifiers changed from: protected */
    public MessageToMessageEncoder() {
        this.matcher = TypeParameterMatcher.find(this, MessageToMessageEncoder.class, "I");
    }

    protected MessageToMessageEncoder(Class<? extends I> cls) {
        this.matcher = TypeParameterMatcher.get(cls);
    }

    public boolean acceptOutboundMessage(Object obj) throws Exception {
        return this.matcher.match(obj);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        CodecOutputList codecOutputList = null;
        try {
            try {
                try {
                    if (acceptOutboundMessage(obj)) {
                        codecOutputList = CodecOutputList.newInstance();
                        try {
                            encode(channelHandlerContext, obj, codecOutputList);
                            ReferenceCountUtil.release(obj);
                            if (codecOutputList.isEmpty()) {
                                throw new EncoderException(StringUtil.simpleClassName(this) + " must produce at least one message.");
                            }
                        } catch (Throwable th) {
                            ReferenceCountUtil.release(obj);
                            throw th;
                        }
                    } else {
                        channelHandlerContext.write(obj, channelPromise);
                    }
                    if (codecOutputList != null) {
                        try {
                            int size = codecOutputList.size() - 1;
                            if (size == 0) {
                                channelHandlerContext.write(codecOutputList.getUnsafe(0), channelPromise);
                            } else if (size > 0) {
                                if (channelPromise == channelHandlerContext.voidPromise()) {
                                    writeVoidPromise(channelHandlerContext, codecOutputList);
                                } else {
                                    writePromiseCombiner(channelHandlerContext, codecOutputList, channelPromise);
                                }
                            }
                        } finally {
                        }
                    }
                } catch (EncoderException e) {
                    throw e;
                }
            } catch (Throwable th2) {
                throw new EncoderException(th2);
            }
        } catch (Throwable th3) {
            if (0 != 0) {
                try {
                    int size2 = codecOutputList.size() - 1;
                    if (size2 == 0) {
                        channelHandlerContext.write(codecOutputList.getUnsafe(0), channelPromise);
                    } else if (size2 > 0) {
                        if (channelPromise == channelHandlerContext.voidPromise()) {
                            writeVoidPromise(channelHandlerContext, null);
                        } else {
                            writePromiseCombiner(channelHandlerContext, null, channelPromise);
                        }
                    }
                } finally {
                }
            }
            throw th3;
        }
    }

    private static void writeVoidPromise(ChannelHandlerContext channelHandlerContext, CodecOutputList codecOutputList) {
        ChannelPromise voidPromise = channelHandlerContext.voidPromise();
        for (int i = 0; i < codecOutputList.size(); i++) {
            channelHandlerContext.write(codecOutputList.getUnsafe(i), voidPromise);
        }
    }

    private static void writePromiseCombiner(ChannelHandlerContext channelHandlerContext, CodecOutputList codecOutputList, ChannelPromise channelPromise) {
        PromiseCombiner promiseCombiner = new PromiseCombiner(channelHandlerContext.executor());
        for (int i = 0; i < codecOutputList.size(); i++) {
            promiseCombiner.add(channelHandlerContext.write(codecOutputList.getUnsafe(i)));
        }
        promiseCombiner.finish(channelPromise);
    }
}
