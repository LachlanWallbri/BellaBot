package io.grpc.netty.shaded.io.netty.handler.codec.compression;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class SnappyFrameDecoder extends ByteToMessageDecoder {
    private static final int MAX_UNCOMPRESSED_DATA_SIZE = 65540;
    private static final int SNAPPY_IDENTIFIER_LEN = 6;
    private boolean corrupted;
    private final Snappy snappy;
    private boolean started;
    private final boolean validateChecksums;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum ChunkType {
        STREAM_IDENTIFIER,
        COMPRESSED_DATA,
        UNCOMPRESSED_DATA,
        RESERVED_UNSKIPPABLE,
        RESERVED_SKIPPABLE
    }

    public SnappyFrameDecoder() {
        this(false);
    }

    public SnappyFrameDecoder(boolean z) {
        this.snappy = new Snappy();
        this.validateChecksums = z;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (this.corrupted) {
            byteBuf.skipBytes(byteBuf.readableBytes());
            return;
        }
        try {
            int readerIndex = byteBuf.readerIndex();
            int readableBytes = byteBuf.readableBytes();
            if (readableBytes < 4) {
                return;
            }
            short unsignedByte = byteBuf.getUnsignedByte(readerIndex);
            ChunkType mapChunkType = mapChunkType((byte) unsignedByte);
            int unsignedMediumLE = byteBuf.getUnsignedMediumLE(readerIndex + 1);
            int i = C64671.f8363xce0a49f3[mapChunkType.ordinal()];
            if (i == 1) {
                if (unsignedMediumLE != 6) {
                    throw new DecompressionException("Unexpected length of stream identifier: " + unsignedMediumLE);
                }
                if (readableBytes < 10) {
                    return;
                }
                byteBuf.skipBytes(4);
                int readerIndex2 = byteBuf.readerIndex();
                byteBuf.skipBytes(6);
                int i2 = readerIndex2 + 1;
                checkByte(byteBuf.getByte(readerIndex2), (byte) 115);
                int i3 = i2 + 1;
                checkByte(byteBuf.getByte(i2), (byte) 78);
                int i4 = i3 + 1;
                checkByte(byteBuf.getByte(i3), (byte) 97);
                int i5 = i4 + 1;
                checkByte(byteBuf.getByte(i4), (byte) 80);
                checkByte(byteBuf.getByte(i5), (byte) 112);
                checkByte(byteBuf.getByte(i5 + 1), ClassDefinitionUtils.OPS_dup);
                this.started = true;
                return;
            }
            if (i == 2) {
                if (!this.started) {
                    throw new DecompressionException("Received RESERVED_SKIPPABLE tag before STREAM_IDENTIFIER");
                }
                int i6 = unsignedMediumLE + 4;
                if (readableBytes < i6) {
                    return;
                }
                byteBuf.skipBytes(i6);
                return;
            }
            if (i == 3) {
                throw new DecompressionException("Found reserved unskippable chunk type: 0x" + Integer.toHexString(unsignedByte));
            }
            if (i == 4) {
                if (!this.started) {
                    throw new DecompressionException("Received UNCOMPRESSED_DATA tag before STREAM_IDENTIFIER");
                }
                if (unsignedMediumLE > 65540) {
                    throw new DecompressionException("Received UNCOMPRESSED_DATA larger than 65540 bytes");
                }
                if (readableBytes < unsignedMediumLE + 4) {
                    return;
                }
                byteBuf.skipBytes(4);
                if (this.validateChecksums) {
                    Snappy.validateChecksum(byteBuf.readIntLE(), byteBuf, byteBuf.readerIndex(), unsignedMediumLE - 4);
                } else {
                    byteBuf.skipBytes(4);
                }
                list.add(byteBuf.readRetainedSlice(unsignedMediumLE - 4));
                return;
            }
            if (i != 5) {
                return;
            }
            if (!this.started) {
                throw new DecompressionException("Received COMPRESSED_DATA tag before STREAM_IDENTIFIER");
            }
            if (readableBytes < unsignedMediumLE + 4) {
                return;
            }
            byteBuf.skipBytes(4);
            int readIntLE = byteBuf.readIntLE();
            ByteBuf buffer = channelHandlerContext.alloc().buffer();
            try {
                if (this.validateChecksums) {
                    int writerIndex = byteBuf.writerIndex();
                    try {
                        byteBuf.writerIndex((byteBuf.readerIndex() + unsignedMediumLE) - 4);
                        this.snappy.decode(byteBuf, buffer);
                        byteBuf.writerIndex(writerIndex);
                        Snappy.validateChecksum(readIntLE, buffer, 0, buffer.writerIndex());
                    } catch (Throwable th) {
                        byteBuf.writerIndex(writerIndex);
                        throw th;
                    }
                } else {
                    this.snappy.decode(byteBuf.readSlice(unsignedMediumLE - 4), buffer);
                }
                list.add(buffer);
                this.snappy.reset();
            } catch (Throwable th2) {
                if (buffer != null) {
                    buffer.release();
                }
                throw th2;
            }
        } catch (Exception e) {
            this.corrupted = true;
            throw e;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.handler.codec.compression.SnappyFrameDecoder$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C64671 {

        /* renamed from: $SwitchMap$io$netty$handler$codec$compression$SnappyFrameDecoder$ChunkType */
        static final /* synthetic */ int[] f8363xce0a49f3 = new int[ChunkType.values().length];

        static {
            try {
                f8363xce0a49f3[ChunkType.STREAM_IDENTIFIER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8363xce0a49f3[ChunkType.RESERVED_SKIPPABLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8363xce0a49f3[ChunkType.RESERVED_UNSKIPPABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8363xce0a49f3[ChunkType.UNCOMPRESSED_DATA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8363xce0a49f3[ChunkType.COMPRESSED_DATA.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    private static void checkByte(byte b, byte b2) {
        if (b != b2) {
            throw new DecompressionException("Unexpected stream identifier contents. Mismatched snappy protocol version?");
        }
    }

    private static ChunkType mapChunkType(byte b) {
        if (b == 0) {
            return ChunkType.COMPRESSED_DATA;
        }
        if (b == 1) {
            return ChunkType.UNCOMPRESSED_DATA;
        }
        if (b == -1) {
            return ChunkType.STREAM_IDENTIFIER;
        }
        if ((b & 128) == 128) {
            return ChunkType.RESERVED_SKIPPABLE;
        }
        return ChunkType.RESERVED_UNSKIPPABLE;
    }
}
