package io.grpc.netty.shaded.io.netty.handler.codec;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.channel.socket.ChannelInputShutdownEvent;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ByteToMessageDecoder extends ChannelInboundHandlerAdapter {
    private static final byte STATE_CALLING_CHILD_DECODE = 1;
    private static final byte STATE_HANDLER_REMOVED_PENDING = 2;
    private static final byte STATE_INIT = 0;
    ByteBuf cumulation;
    private Cumulator cumulator = MERGE_CUMULATOR;
    private byte decodeState = 0;
    private int discardAfterReads = 16;
    private boolean firedChannelRead;
    private boolean first;
    private int numReads;
    private boolean singleDecode;
    public static final Cumulator MERGE_CUMULATOR = new Cumulator() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder.1
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder.Cumulator
        public ByteBuf cumulate(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, ByteBuf byteBuf2) {
            if (!byteBuf.isReadable() && byteBuf2.isContiguous()) {
                byteBuf.release();
                return byteBuf2;
            }
            try {
                int readableBytes = byteBuf2.readableBytes();
                if (readableBytes <= byteBuf.maxWritableBytes() && ((readableBytes <= byteBuf.maxFastWritableBytes() || byteBuf.refCnt() <= 1) && !byteBuf.isReadOnly())) {
                    byteBuf.writeBytes(byteBuf2, byteBuf2.readerIndex(), readableBytes);
                    byteBuf2.readerIndex(byteBuf2.writerIndex());
                    return byteBuf;
                }
                return ByteToMessageDecoder.expandCumulation(byteBufAllocator, byteBuf, byteBuf2);
            } finally {
                byteBuf2.release();
            }
        }
    };
    public static final Cumulator COMPOSITE_CUMULATOR = new Cumulator() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder.2
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder.Cumulator
        public ByteBuf cumulate(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, ByteBuf byteBuf2) {
            CompositeByteBuf addFlattenedComponents;
            if (!byteBuf.isReadable()) {
                byteBuf.release();
                return byteBuf2;
            }
            CompositeByteBuf compositeByteBuf = null;
            try {
                if ((byteBuf instanceof CompositeByteBuf) && byteBuf.refCnt() == 1) {
                    addFlattenedComponents = (CompositeByteBuf) byteBuf;
                    try {
                        if (addFlattenedComponents.writerIndex() != addFlattenedComponents.capacity()) {
                            addFlattenedComponents.capacity(addFlattenedComponents.writerIndex());
                        }
                    } catch (Throwable th) {
                        compositeByteBuf = addFlattenedComponents;
                        th = th;
                        if (byteBuf2 != null) {
                            byteBuf2.release();
                            if (compositeByteBuf != null && compositeByteBuf != byteBuf) {
                                compositeByteBuf.release();
                            }
                        }
                        throw th;
                    }
                } else {
                    addFlattenedComponents = byteBufAllocator.compositeBuffer(Integer.MAX_VALUE).addFlattenedComponents(true, byteBuf);
                }
                compositeByteBuf = addFlattenedComponents;
                compositeByteBuf.addFlattenedComponents(true, byteBuf2);
                return compositeByteBuf;
            } catch (Throwable th2) {
                th = th2;
            }
        }
    };

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface Cumulator {
        ByteBuf cumulate(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, ByteBuf byteBuf2);
    }

    protected abstract void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception;

    /* JADX INFO: Access modifiers changed from: protected */
    public void handlerRemoved0(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ByteToMessageDecoder() {
        ensureNotSharable();
    }

    public void setSingleDecode(boolean z) {
        this.singleDecode = z;
    }

    public boolean isSingleDecode() {
        return this.singleDecode;
    }

    public void setCumulator(Cumulator cumulator) {
        this.cumulator = (Cumulator) ObjectUtil.checkNotNull(cumulator, "cumulator");
    }

    public void setDiscardAfterReads(int i) {
        ObjectUtil.checkPositive(i, "discardAfterReads");
        this.discardAfterReads = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int actualReadableBytes() {
        return internalBuffer().readableBytes();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ByteBuf internalBuffer() {
        ByteBuf byteBuf = this.cumulation;
        return byteBuf != null ? byteBuf : Unpooled.EMPTY_BUFFER;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public final void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.decodeState == 1) {
            this.decodeState = (byte) 2;
            return;
        }
        ByteBuf byteBuf = this.cumulation;
        if (byteBuf != null) {
            this.cumulation = null;
            this.numReads = 0;
            if (byteBuf.readableBytes() > 0) {
                channelHandlerContext.fireChannelRead((Object) byteBuf);
                channelHandlerContext.fireChannelReadComplete();
            } else {
                byteBuf.release();
            }
        }
        handlerRemoved0(channelHandlerContext);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (obj instanceof ByteBuf) {
            CodecOutputList newInstance = CodecOutputList.newInstance();
            try {
                try {
                    this.first = this.cumulation == null;
                    this.cumulation = this.cumulator.cumulate(channelHandlerContext.alloc(), this.first ? Unpooled.EMPTY_BUFFER : this.cumulation, (ByteBuf) obj);
                    callDecode(channelHandlerContext, this.cumulation, newInstance);
                    try {
                        if (this.cumulation != null && !this.cumulation.isReadable()) {
                            this.numReads = 0;
                            this.cumulation.release();
                            this.cumulation = null;
                        } else {
                            int i = this.numReads + 1;
                            this.numReads = i;
                            if (i >= this.discardAfterReads) {
                                this.numReads = 0;
                                discardSomeReadBytes();
                            }
                        }
                        int size = newInstance.size();
                        this.firedChannelRead |= newInstance.insertSinceRecycled();
                        fireChannelRead(channelHandlerContext, newInstance, size);
                        return;
                    } finally {
                    }
                } catch (DecoderException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new DecoderException(e2);
                }
            } catch (Throwable th) {
                try {
                    if (this.cumulation != null && !this.cumulation.isReadable()) {
                        this.numReads = 0;
                        this.cumulation.release();
                        this.cumulation = null;
                        int size2 = newInstance.size();
                        this.firedChannelRead |= newInstance.insertSinceRecycled();
                        fireChannelRead(channelHandlerContext, newInstance, size2);
                        throw th;
                    }
                    int i2 = this.numReads + 1;
                    this.numReads = i2;
                    if (i2 >= this.discardAfterReads) {
                        this.numReads = 0;
                        discardSomeReadBytes();
                    }
                    int size22 = newInstance.size();
                    this.firedChannelRead |= newInstance.insertSinceRecycled();
                    fireChannelRead(channelHandlerContext, newInstance, size22);
                    throw th;
                } finally {
                }
            }
        }
        channelHandlerContext.fireChannelRead(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void fireChannelRead(ChannelHandlerContext channelHandlerContext, List<Object> list, int i) {
        if (list instanceof CodecOutputList) {
            fireChannelRead(channelHandlerContext, (CodecOutputList) list, i);
            return;
        }
        for (int i2 = 0; i2 < i; i2++) {
            channelHandlerContext.fireChannelRead(list.get(i2));
        }
    }

    static void fireChannelRead(ChannelHandlerContext channelHandlerContext, CodecOutputList codecOutputList, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            channelHandlerContext.fireChannelRead(codecOutputList.getUnsafe(i2));
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.numReads = 0;
        discardSomeReadBytes();
        if (!this.firedChannelRead && !channelHandlerContext.channel().config().isAutoRead()) {
            channelHandlerContext.read();
        }
        this.firedChannelRead = false;
        channelHandlerContext.fireChannelReadComplete();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void discardSomeReadBytes() {
        ByteBuf byteBuf = this.cumulation;
        if (byteBuf == null || this.first || byteBuf.refCnt() != 1) {
            return;
        }
        this.cumulation.discardSomeReadBytes();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelInputClosed(channelHandlerContext, true);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (obj instanceof ChannelInputShutdownEvent) {
            channelInputClosed(channelHandlerContext, false);
        }
        super.userEventTriggered(channelHandlerContext, obj);
    }

    private void channelInputClosed(ChannelHandlerContext channelHandlerContext, boolean z) {
        CodecOutputList newInstance = CodecOutputList.newInstance();
        try {
            try {
                channelInputClosed(channelHandlerContext, newInstance);
                try {
                    if (this.cumulation != null) {
                        this.cumulation.release();
                        this.cumulation = null;
                    }
                    int size = newInstance.size();
                    fireChannelRead(channelHandlerContext, newInstance, size);
                    if (size > 0) {
                        channelHandlerContext.fireChannelReadComplete();
                    }
                    if (z) {
                        channelHandlerContext.fireChannelInactive();
                    }
                } finally {
                }
            } catch (DecoderException e) {
                throw e;
            } catch (Exception e2) {
                throw new DecoderException(e2);
            }
        } catch (Throwable th) {
            try {
                if (this.cumulation != null) {
                    this.cumulation.release();
                    this.cumulation = null;
                }
                int size2 = newInstance.size();
                fireChannelRead(channelHandlerContext, newInstance, size2);
                if (size2 > 0) {
                    channelHandlerContext.fireChannelReadComplete();
                }
                if (z) {
                    channelHandlerContext.fireChannelInactive();
                }
                throw th;
            } finally {
            }
        }
    }

    void channelInputClosed(ChannelHandlerContext channelHandlerContext, List<Object> list) throws Exception {
        ByteBuf byteBuf = this.cumulation;
        if (byteBuf != null) {
            callDecode(channelHandlerContext, byteBuf, list);
            decodeLast(channelHandlerContext, this.cumulation, list);
        } else {
            decodeLast(channelHandlerContext, Unpooled.EMPTY_BUFFER, list);
        }
    }

    protected void callDecode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        while (byteBuf.isReadable()) {
            try {
                int size = list.size();
                if (size > 0) {
                    fireChannelRead(channelHandlerContext, list, size);
                    list.clear();
                    if (channelHandlerContext.isRemoved()) {
                        return;
                    } else {
                        size = 0;
                    }
                }
                int readableBytes = byteBuf.readableBytes();
                decodeRemovalReentryProtection(channelHandlerContext, byteBuf, list);
                if (channelHandlerContext.isRemoved()) {
                    return;
                }
                if (size == list.size()) {
                    if (readableBytes == byteBuf.readableBytes()) {
                        return;
                    }
                } else {
                    if (readableBytes == byteBuf.readableBytes()) {
                        throw new DecoderException(StringUtil.simpleClassName(getClass()) + ".decode() did not read anything but decoded a message.");
                    }
                    if (isSingleDecode()) {
                        return;
                    }
                }
            } catch (DecoderException e) {
                throw e;
            } catch (Exception e2) {
                throw new DecoderException(e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void decodeRemovalReentryProtection(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        this.decodeState = (byte) 1;
        try {
            decode(channelHandlerContext, byteBuf, list);
        } finally {
            r0 = this.decodeState != 2 ? (byte) 0 : (byte) 1;
            this.decodeState = (byte) 0;
            if (r0 != 0) {
                fireChannelRead(channelHandlerContext, list, list.size());
                list.clear();
                handlerRemoved(channelHandlerContext);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void decodeLast(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.isReadable()) {
            decodeRemovalReentryProtection(channelHandlerContext, byteBuf, list);
        }
    }

    static ByteBuf expandCumulation(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, ByteBuf byteBuf2) {
        int readableBytes = byteBuf.readableBytes();
        int readableBytes2 = byteBuf2.readableBytes();
        int i = readableBytes + readableBytes2;
        ByteBuf buffer = byteBufAllocator.buffer(byteBufAllocator.calculateNewCapacity(i, Integer.MAX_VALUE));
        try {
            buffer.setBytes(0, byteBuf, byteBuf.readerIndex(), readableBytes).setBytes(readableBytes, byteBuf2, byteBuf2.readerIndex(), readableBytes2).writerIndex(i);
            byteBuf2.readerIndex(byteBuf2.writerIndex());
            byteBuf.release();
            return buffer;
        } catch (Throwable th) {
            buffer.release();
            throw th;
        }
    }
}
