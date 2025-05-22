package io.netty.handler.codec.compression;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.internal.EmptyArrays;
import java.util.List;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class FastLzFrameDecoder extends ByteToMessageDecoder {
    private final Checksum checksum;
    private int chunkLength;
    private int currentChecksum;
    private State currentState;
    private boolean hasChecksum;
    private boolean isCompressed;
    private int originalLength;

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes7.dex
     */
    /* loaded from: classes.dex */
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
      classes4.dex
      classes7.dex
     */
    /* renamed from: io.netty.handler.codec.compression.FastLzFrameDecoder$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C71141 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$compression$FastLzFrameDecoder$State */
        static final /* synthetic */ int[] f8476x8db84534;

        static {
            int[] iArr = new int[State.values().length];
            f8476x8db84534 = iArr;
            try {
                iArr[State.INIT_BLOCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8476x8db84534[State.INIT_BLOCK_PARAMS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8476x8db84534[State.DECOMPRESS_DATA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8476x8db84534[State.CORRUPTED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0097 A[Catch: Exception -> 0x015d, TryCatch #0 {Exception -> 0x015d, blocks: (B:3:0x0004, B:9:0x001b, B:12:0x0024, B:13:0x0029, B:14:0x008d, B:18:0x0097, B:20:0x009f, B:48:0x0151, B:49:0x0154, B:50:0x00b8, B:52:0x0056, B:55:0x0061, B:58:0x0068, B:62:0x006d, B:64:0x0071, B:65:0x0077, B:67:0x0083, B:68:0x0087, B:72:0x002a, B:76:0x0032, B:78:0x003b, B:81:0x0046, B:84:0x0050, B:87:0x0155, B:88:0x015c, B:22:0x00be, B:24:0x00c2, B:26:0x00c8, B:27:0x00d8, B:30:0x0103, B:33:0x010b, B:36:0x011b, B:37:0x0136, B:39:0x0139, B:40:0x0146, B:42:0x00e5, B:43:0x00fe, B:44:0x00d2, B:45:0x00ff), top: B:2:0x0004, inners: #1 }] */
    @Override // io.netty.handler.codec.ByteToMessageDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int i;
        int i2;
        ByteBuf byteBuf2;
        byte[] bArr;
        int i3;
        byte[] bArr2;
        int i4;
        try {
            int i5 = C71141.f8476x8db84534[this.currentState.ordinal()];
            int i6 = 4;
            if (i5 != 1) {
                if (i5 != 2) {
                    if (i5 != 3) {
                        if (i5 == 4) {
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
                    int i7 = this.originalLength;
                    if (i7 != 0) {
                        ByteBuf heapBuffer = channelHandlerContext.alloc().heapBuffer(i7, i7);
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
                                i4 = byteBuf.arrayOffset() + readerIndex;
                            } else {
                                bArr2 = new byte[i];
                                byteBuf.getBytes(readerIndex, bArr2);
                                i4 = 0;
                            }
                            int i8 = i2;
                            int decompress = FastLz.decompress(bArr2, i4, i, bArr, i2, i7);
                            if (i7 != decompress) {
                                throw new DecompressionException(String.format("stream corrupted: originalLength(%d) and actual length(%d) mismatch", Integer.valueOf(i7), Integer.valueOf(decompress)));
                            }
                            i3 = i8;
                        } else {
                            i3 = i2;
                            byteBuf.getBytes(readerIndex, bArr, i3, i);
                        }
                        Checksum checksum = this.checksum;
                        if (this.hasChecksum && checksum != null) {
                            checksum.reset();
                            checksum.update(bArr, i3, i7);
                            int value = (int) checksum.getValue();
                            if (value != this.currentChecksum) {
                                throw new DecompressionException(String.format("stream corrupted: mismatching checksum: %d (expected: %d)", Integer.valueOf(value), Integer.valueOf(this.currentChecksum)));
                            }
                        }
                        if (byteBuf2 != null) {
                            byteBuf2.writerIndex(byteBuf2.writerIndex() + i7);
                            list.add(byteBuf2);
                        }
                        byteBuf.skipBytes(i);
                        this.currentState = State.INIT_BLOCK;
                        return;
                    } catch (Throwable th) {
                        if (byteBuf2 != null) {
                            byteBuf2.release();
                        }
                        throw th;
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
            int i9 = (this.isCompressed ? 2 : 0) + 2;
            if (!this.hasChecksum) {
                i6 = 0;
            }
            if (readableBytes < i9 + i6) {
                return;
            }
            this.currentChecksum = this.hasChecksum ? byteBuf.readInt() : 0;
            int readUnsignedShort = byteBuf.readUnsignedShort();
            this.chunkLength = readUnsignedShort;
            if (this.isCompressed) {
                readUnsignedShort = byteBuf.readUnsignedShort();
            }
            this.originalLength = readUnsignedShort;
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
