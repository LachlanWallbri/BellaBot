package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import com.ning.compress.BufferRecycler;
import com.ning.compress.lzf.ChunkDecoder;
import com.ning.compress.lzf.util.ChunkDecoderFactory;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class LzfDecoder extends ByteToMessageDecoder {
    private static final short MAGIC_NUMBER = 23126;
    private int chunkLength;
    private State currentState;
    private ChunkDecoder decoder;
    private boolean isCompressed;
    private int originalLength;
    private BufferRecycler recycler;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private enum State {
        INIT_BLOCK,
        INIT_ORIGINAL_LENGTH,
        DECOMPRESS_DATA,
        CORRUPTED
    }

    public LzfDecoder() {
        this(false);
    }

    public LzfDecoder(boolean z) {
        ChunkDecoder optimalInstance;
        this.currentState = State.INIT_BLOCK;
        if (z) {
            optimalInstance = ChunkDecoderFactory.safeInstance();
        } else {
            optimalInstance = ChunkDecoderFactory.optimalInstance();
        }
        this.decoder = optimalInstance;
        this.recycler = BufferRecycler.instance();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.compression.LzfDecoder$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C64651 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$LzfDecoder$State = new int[State.values().length];

        static {
            try {
                $SwitchMap$io$netty$handler$codec$compression$LzfDecoder$State[State.INIT_BLOCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$LzfDecoder$State[State.INIT_ORIGINAL_LENGTH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$LzfDecoder$State[State.DECOMPRESS_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$LzfDecoder$State[State.CORRUPTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0092 A[Catch: Exception -> 0x010b, TryCatch #1 {Exception -> 0x010b, blocks: (B:2:0x0000, B:8:0x0017, B:11:0x0020, B:12:0x0025, B:13:0x0088, B:17:0x0092, B:19:0x0098, B:21:0x00a2, B:22:0x00b8, B:26:0x00e4, B:28:0x00ea, B:29:0x00fe, B:33:0x00f1, B:34:0x00f4, B:35:0x00ad, B:37:0x00f7, B:38:0x0076, B:42:0x007e, B:43:0x0026, B:47:0x002f, B:49:0x0037, B:52:0x003f, B:53:0x006c, B:57:0x0046, B:58:0x0065, B:59:0x0066, B:60:0x0103, B:61:0x010a, B:25:0x00ce), top: B:1:0x0000, inners: #0 }] */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int i;
        int i2;
        byte[] bArr;
        try {
            int i3 = C64651.$SwitchMap$io$netty$handler$codec$compression$LzfDecoder$State[this.currentState.ordinal()];
            if (i3 != 1) {
                if (i3 != 2) {
                    if (i3 != 3) {
                        if (i3 == 4) {
                            byteBuf.skipBytes(byteBuf.readableBytes());
                            return;
                        }
                        throw new IllegalStateException();
                    }
                    i = this.chunkLength;
                    if (byteBuf.readableBytes() >= i) {
                        return;
                    }
                    int i4 = this.originalLength;
                    if (this.isCompressed) {
                        int readerIndex = byteBuf.readerIndex();
                        if (byteBuf.hasArray()) {
                            bArr = byteBuf.array();
                            i2 = readerIndex + byteBuf.arrayOffset();
                        } else {
                            byte[] allocInputBuffer = this.recycler.allocInputBuffer(i);
                            byteBuf.getBytes(readerIndex, allocInputBuffer, 0, i);
                            i2 = 0;
                            bArr = allocInputBuffer;
                        }
                        ByteBuf heapBuffer = channelHandlerContext.alloc().heapBuffer(i4, i4);
                        byte[] array = heapBuffer.array();
                        int arrayOffset = heapBuffer.arrayOffset() + heapBuffer.writerIndex();
                        try {
                            this.decoder.decodeChunk(bArr, i2, array, arrayOffset, arrayOffset + i4);
                            heapBuffer.writerIndex(heapBuffer.writerIndex() + i4);
                            list.add(heapBuffer);
                            byteBuf.skipBytes(i);
                            if (!byteBuf.hasArray()) {
                                this.recycler.releaseInputBuffer(bArr);
                            }
                        } catch (Throwable th) {
                            heapBuffer.release();
                            throw th;
                        }
                    } else if (i > 0) {
                        list.add(byteBuf.readRetainedSlice(i));
                    }
                    this.currentState = State.INIT_BLOCK;
                    return;
                }
            } else {
                if (byteBuf.readableBytes() < 5) {
                    return;
                }
                if (byteBuf.readUnsignedShort() != 23126) {
                    throw new DecompressionException("unexpected block identifier");
                }
                byte readByte = byteBuf.readByte();
                if (readByte == 0) {
                    this.isCompressed = false;
                    this.currentState = State.DECOMPRESS_DATA;
                } else if (readByte == 1) {
                    this.isCompressed = true;
                    this.currentState = State.INIT_ORIGINAL_LENGTH;
                } else {
                    throw new DecompressionException(String.format("unknown type of chunk: %d (expected: %d or %d)", Integer.valueOf(readByte), 0, 1));
                }
                this.chunkLength = byteBuf.readUnsignedShort();
                if (readByte != 1) {
                    return;
                }
            }
            if (byteBuf.readableBytes() < 2) {
                return;
            }
            this.originalLength = byteBuf.readUnsignedShort();
            this.currentState = State.DECOMPRESS_DATA;
            i = this.chunkLength;
            if (byteBuf.readableBytes() >= i) {
            }
        } catch (Exception e) {
            this.currentState = State.CORRUPTED;
            this.decoder = null;
            this.recycler = null;
            throw e;
        }
    }
}
