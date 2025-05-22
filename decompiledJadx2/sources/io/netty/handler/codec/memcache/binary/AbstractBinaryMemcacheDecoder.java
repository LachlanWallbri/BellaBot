package io.netty.handler.codec.memcache.binary;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.memcache.AbstractMemcacheObjectDecoder;
import io.netty.handler.codec.memcache.DefaultLastMemcacheContent;
import io.netty.handler.codec.memcache.DefaultMemcacheContent;
import io.netty.handler.codec.memcache.LastMemcacheContent;
import io.netty.handler.codec.memcache.MemcacheContent;
import io.netty.handler.codec.memcache.binary.BinaryMemcacheMessage;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class AbstractBinaryMemcacheDecoder<M extends BinaryMemcacheMessage> extends AbstractMemcacheObjectDecoder {
    public static final int DEFAULT_MAX_CHUNK_SIZE = 8192;
    private int alreadyReadChunkSize;
    private final int chunkSize;
    private M currentMessage;
    private State state;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public enum State {
        READ_HEADER,
        READ_EXTRAS,
        READ_KEY,
        READ_CONTENT,
        BAD_MESSAGE
    }

    protected abstract M buildInvalidMessage();

    protected abstract M decodeHeader(ByteBuf byteBuf);

    protected AbstractBinaryMemcacheDecoder() {
        this(8192);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractBinaryMemcacheDecoder(int i) {
        this.state = State.READ_HEADER;
        if (i < 0) {
            throw new IllegalArgumentException("chunkSize must be a positive integer: " + i);
        }
        this.chunkSize = i;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* renamed from: io.netty.handler.codec.memcache.binary.AbstractBinaryMemcacheDecoder$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C72481 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$memcache$binary$AbstractBinaryMemcacheDecoder$State */
        static final /* synthetic */ int[] f8514x4c8421ed;

        static {
            int[] iArr = new int[State.values().length];
            f8514x4c8421ed = iArr;
            try {
                iArr[State.READ_HEADER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8514x4c8421ed[State.READ_EXTRAS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8514x4c8421ed[State.READ_KEY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8514x4c8421ed[State.READ_CONTENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8514x4c8421ed[State.BAD_MESSAGE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00d9 A[Catch: Exception -> 0x00e6, TryCatch #2 {Exception -> 0x00e6, blocks: (B:17:0x0091, B:21:0x00ae, B:23:0x00b2, B:24:0x00b4, B:27:0x00bb, B:29:0x00c6, B:30:0x00d1, B:33:0x00de, B:36:0x00cc, B:37:0x00d9), top: B:16:0x0091 }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0074 A[Catch: Exception -> 0x00f2, TryCatch #3 {Exception -> 0x00f2, blocks: (B:42:0x006c, B:44:0x0074, B:47:0x007b, B:48:0x0084), top: B:41:0x006c }] */
    @Override // io.netty.handler.codec.ByteToMessageDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        short keyLength;
        int keyLength2;
        Object defaultMemcacheContent;
        int i = C72481.f8514x4c8421ed[this.state.ordinal()];
        try {
            try {
                try {
                    if (i == 1) {
                        try {
                            if (byteBuf.readableBytes() < 24) {
                                return;
                            }
                            resetDecoder();
                            this.currentMessage = decodeHeader(byteBuf);
                            this.state = State.READ_EXTRAS;
                        } catch (Exception e) {
                            resetDecoder();
                            list.add(invalidMessage(e));
                            return;
                        }
                    } else if (i != 2) {
                        if (i != 3) {
                            if (i != 4) {
                                if (i == 5) {
                                    byteBuf.skipBytes(actualReadableBytes());
                                    return;
                                }
                                throw new Error("Unknown state reached: " + this.state);
                            }
                            keyLength2 = (this.currentMessage.totalBodyLength() - this.currentMessage.keyLength()) - this.currentMessage.extrasLength();
                            int readableBytes = byteBuf.readableBytes();
                            if (keyLength2 <= 0) {
                                list.add(LastMemcacheContent.EMPTY_LAST_CONTENT);
                            } else {
                                if (readableBytes == 0) {
                                    return;
                                }
                                if (readableBytes > this.chunkSize) {
                                    readableBytes = this.chunkSize;
                                }
                                int i2 = keyLength2 - this.alreadyReadChunkSize;
                                if (readableBytes > i2) {
                                    readableBytes = i2;
                                }
                                ByteBuf readRetainedSlice = byteBuf.readRetainedSlice(readableBytes);
                                int i3 = this.alreadyReadChunkSize + readableBytes;
                                this.alreadyReadChunkSize = i3;
                                if (i3 >= keyLength2) {
                                    defaultMemcacheContent = new DefaultLastMemcacheContent(readRetainedSlice);
                                } else {
                                    defaultMemcacheContent = new DefaultMemcacheContent(readRetainedSlice);
                                }
                                list.add(defaultMemcacheContent);
                                if (this.alreadyReadChunkSize < keyLength2) {
                                    return;
                                }
                            }
                            resetDecoder();
                            this.state = State.READ_HEADER;
                            return;
                        }
                        keyLength = this.currentMessage.keyLength();
                        if (keyLength > 0) {
                            if (byteBuf.readableBytes() < keyLength) {
                                return;
                            } else {
                                this.currentMessage.setKey(byteBuf.readRetainedSlice(keyLength));
                            }
                        }
                        list.add(this.currentMessage.retain());
                        this.state = State.READ_CONTENT;
                        keyLength2 = (this.currentMessage.totalBodyLength() - this.currentMessage.keyLength()) - this.currentMessage.extrasLength();
                        int readableBytes2 = byteBuf.readableBytes();
                        if (keyLength2 <= 0) {
                        }
                        resetDecoder();
                        this.state = State.READ_HEADER;
                        return;
                    }
                    keyLength2 = (this.currentMessage.totalBodyLength() - this.currentMessage.keyLength()) - this.currentMessage.extrasLength();
                    int readableBytes22 = byteBuf.readableBytes();
                    if (keyLength2 <= 0) {
                    }
                    resetDecoder();
                    this.state = State.READ_HEADER;
                    return;
                } catch (Exception e2) {
                    resetDecoder();
                    list.add(invalidChunk(e2));
                    return;
                }
                keyLength = this.currentMessage.keyLength();
                if (keyLength > 0) {
                }
                list.add(this.currentMessage.retain());
                this.state = State.READ_CONTENT;
            } catch (Exception e3) {
                resetDecoder();
                list.add(invalidMessage(e3));
                return;
            }
            byte extrasLength = this.currentMessage.extrasLength();
            if (extrasLength > 0) {
                if (byteBuf.readableBytes() < extrasLength) {
                    return;
                } else {
                    this.currentMessage.setExtras(byteBuf.readRetainedSlice(extrasLength));
                }
            }
            this.state = State.READ_KEY;
        } catch (Exception e4) {
            resetDecoder();
            list.add(invalidMessage(e4));
        }
    }

    private M invalidMessage(Exception exc) {
        this.state = State.BAD_MESSAGE;
        M buildInvalidMessage = buildInvalidMessage();
        buildInvalidMessage.setDecoderResult(DecoderResult.failure(exc));
        return buildInvalidMessage;
    }

    private MemcacheContent invalidChunk(Exception exc) {
        this.state = State.BAD_MESSAGE;
        DefaultLastMemcacheContent defaultLastMemcacheContent = new DefaultLastMemcacheContent(Unpooled.EMPTY_BUFFER);
        defaultLastMemcacheContent.setDecoderResult(DecoderResult.failure(exc));
        return defaultLastMemcacheContent;
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.channelInactive(channelHandlerContext);
        resetDecoder();
    }

    protected void resetDecoder() {
        M m = this.currentMessage;
        if (m != null) {
            m.release();
            this.currentMessage = null;
        }
        this.alreadyReadChunkSize = 0;
    }
}
