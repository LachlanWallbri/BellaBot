package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import java.util.List;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class FastLzFrameDecoder extends ByteToMessageDecoder {
    private final Checksum checksum;
    private int chunkLength;
    private int currentChecksum;
    private State currentState;
    private boolean hasChecksum;
    private boolean isCompressed;
    private int originalLength;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private enum State {
        INIT_BLOCK,
        INIT_BLOCK_PARAMS,
        DECOMPRESS_DATA,
        CORRUPTED
    }

    public FastLzFrameDecoder() {
        this(false);
    }

    public FastLzFrameDecoder(boolean z) {
        this(z ? new Adler32() : null);
    }

    public FastLzFrameDecoder(Checksum checksum) {
        this.currentState = State.INIT_BLOCK;
        this.checksum = checksum;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.compression.FastLzFrameDecoder$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C64521 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$compression$FastLzFrameDecoder$State */
        static final /* synthetic */ int[] f8358x8db84534 = new int[State.values().length];

        static {
            try {
                f8358x8db84534[State.INIT_BLOCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8358x8db84534[State.INIT_BLOCK_PARAMS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8358x8db84534[State.DECOMPRESS_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8358x8db84534[State.CORRUPTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x009a A[Catch: Exception -> 0x0167, TryCatch #3 {Exception -> 0x0167, blocks: (B:3:0x0004, B:9:0x001b, B:12:0x0024, B:13:0x0029, B:14:0x0090, B:18:0x009a, B:20:0x00a2, B:50:0x015b, B:51:0x015e, B:60:0x00bb, B:62:0x0056, B:65:0x0061, B:68:0x0068, B:72:0x006d, B:74:0x0071, B:75:0x0077, B:77:0x0083, B:78:0x008a, B:79:0x0088, B:83:0x002a, B:87:0x0032, B:89:0x003b, B:92:0x0046, B:95:0x0050, B:98:0x015f, B:99:0x0166), top: B:2:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x015b A[Catch: Exception -> 0x0167, TRY_ENTER, TryCatch #3 {Exception -> 0x0167, blocks: (B:3:0x0004, B:9:0x001b, B:12:0x0024, B:13:0x0029, B:14:0x0090, B:18:0x009a, B:20:0x00a2, B:50:0x015b, B:51:0x015e, B:60:0x00bb, B:62:0x0056, B:65:0x0061, B:68:0x0068, B:72:0x006d, B:74:0x0071, B:75:0x0077, B:77:0x0083, B:78:0x008a, B:79:0x0088, B:83:0x002a, B:87:0x0032, B:89:0x003b, B:92:0x0046, B:95:0x0050, B:98:0x015f, B:99:0x0166), top: B:2:0x0004 }] */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int i;
        int i2;
        ByteBuf byteBuf2;
        byte[] bArr;
        ByteBuf byteBuf3;
        byte[] bArr2;
        int i3;
        try {
            int i4 = C64521.f8358x8db84534[this.currentState.ordinal()];
            int i5 = 4;
            if (i4 != 1) {
                if (i4 != 2) {
                    if (i4 != 3) {
                        if (i4 == 4) {
                            byteBuf.skipBytes(byteBuf.readableBytes());
                            return;
                        }
                        throw new IllegalStateException();
                    }
                    i = this.chunkLength;
                    if (byteBuf.readableBytes() >= i) {
                        return;
                    }
                    int readerIndex = byteBuf.readerIndex();
                    int i6 = this.originalLength;
                    if (i6 != 0) {
                        ByteBuf heapBuffer = channelHandlerContext.alloc().heapBuffer(i6, i6);
                        byteBuf2 = heapBuffer;
                        bArr = heapBuffer.array();
                        i2 = heapBuffer.arrayOffset() + heapBuffer.writerIndex();
                    } else {
                        i2 = 0;
                        byteBuf2 = null;
                        bArr = EmptyArrays.EMPTY_BYTES;
                    }
                    try {
                        if (this.isCompressed) {
                            if (byteBuf.hasArray()) {
                                bArr2 = byteBuf.array();
                                i3 = readerIndex + byteBuf.arrayOffset();
                            } else {
                                bArr2 = new byte[i];
                                byteBuf.getBytes(readerIndex, bArr2);
                                i3 = 0;
                            }
                            ByteBuf byteBuf4 = byteBuf2;
                            try {
                                int decompress = FastLz.decompress(bArr2, i3, i, bArr, i2, i6);
                                if (i6 != decompress) {
                                    throw new DecompressionException(String.format("stream corrupted: originalLength(%d) and actual length(%d) mismatch", Integer.valueOf(i6), Integer.valueOf(decompress)));
                                }
                                byteBuf3 = byteBuf4;
                            } catch (Throwable th) {
                                th = th;
                                byteBuf3 = byteBuf4;
                                if (byteBuf3 != null) {
                                    byteBuf3.release();
                                }
                                throw th;
                            }
                        } else {
                            byteBuf3 = byteBuf2;
                            try {
                                byteBuf.getBytes(readerIndex, bArr, i2, i);
                            } catch (Throwable th2) {
                                th = th2;
                                if (byteBuf3 != null) {
                                }
                                throw th;
                            }
                        }
                        Checksum checksum = this.checksum;
                        if (this.hasChecksum && checksum != null) {
                            checksum.reset();
                            checksum.update(bArr, i2, i6);
                            int value = (int) checksum.getValue();
                            if (value != this.currentChecksum) {
                                throw new DecompressionException(String.format("stream corrupted: mismatching checksum: %d (expected: %d)", Integer.valueOf(value), Integer.valueOf(this.currentChecksum)));
                            }
                        }
                        if (byteBuf3 != null) {
                            byteBuf3.writerIndex(byteBuf3.writerIndex() + i6);
                            list.add(byteBuf3);
                        }
                        byteBuf.skipBytes(i);
                        this.currentState = State.INIT_BLOCK;
                        return;
                    } catch (Throwable th3) {
                        th = th3;
                        byteBuf3 = byteBuf2;
                    }
                }
            } else {
                if (byteBuf.readableBytes() < 4) {
                    return;
                }
                if (byteBuf.readUnsignedMedium() != 4607066) {
                    throw new DecompressionException("unexpected block identifier");
                }
                byte readByte = byteBuf.readByte();
                this.isCompressed = (readByte & 1) == 1;
                this.hasChecksum = (readByte & 16) == 16;
                this.currentState = State.INIT_BLOCK_PARAMS;
            }
            int readableBytes = byteBuf.readableBytes();
            int i7 = (this.isCompressed ? 2 : 0) + 2;
            if (!this.hasChecksum) {
                i5 = 0;
            }
            if (readableBytes < i7 + i5) {
                return;
            }
            this.currentChecksum = this.hasChecksum ? byteBuf.readInt() : 0;
            this.chunkLength = byteBuf.readUnsignedShort();
            this.originalLength = this.isCompressed ? byteBuf.readUnsignedShort() : this.chunkLength;
            this.currentState = State.DECOMPRESS_DATA;
            i = this.chunkLength;
            if (byteBuf.readableBytes() >= i) {
            }
        } catch (Exception e) {
            this.currentState = State.CORRUPTED;
            throw e;
        }
    }
}
