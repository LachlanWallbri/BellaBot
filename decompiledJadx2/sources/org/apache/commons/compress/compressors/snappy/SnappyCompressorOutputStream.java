package org.apache.commons.compress.compressors.snappy;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.lz77support.LZ77Compressor;
import org.apache.commons.compress.compressors.lz77support.Parameters;
import org.apache.commons.compress.utils.ByteUtils;

/* loaded from: classes8.dex */
public class SnappyCompressorOutputStream extends CompressorOutputStream {
    private static final int FOUR_BYTE_COPY_TAG = 3;
    private static final int FOUR_SIZE_BYTE_MARKER = 252;
    private static final int MAX_LITERAL_SIZE_WITHOUT_SIZE_BYTES = 60;
    private static final int MAX_LITERAL_SIZE_WITH_ONE_SIZE_BYTE = 256;
    private static final int MAX_LITERAL_SIZE_WITH_THREE_SIZE_BYTES = 16777216;
    private static final int MAX_LITERAL_SIZE_WITH_TWO_SIZE_BYTES = 65536;
    private static final int MAX_MATCH_LENGTH = 64;
    private static final int MAX_MATCH_LENGTH_WITH_ONE_OFFSET_BYTE = 11;
    private static final int MAX_OFFSET_WITH_ONE_OFFSET_BYTE = 1024;
    private static final int MAX_OFFSET_WITH_TWO_OFFSET_BYTES = 32768;
    private static final int MIN_MATCH_LENGTH = 4;
    private static final int MIN_MATCH_LENGTH_WITH_ONE_OFFSET_BYTE = 4;
    private static final int ONE_BYTE_COPY_TAG = 1;
    private static final int ONE_SIZE_BYTE_MARKER = 240;
    private static final int THREE_SIZE_BYTE_MARKER = 248;
    private static final int TWO_BYTE_COPY_TAG = 2;
    private static final int TWO_SIZE_BYTE_MARKER = 244;
    private final LZ77Compressor compressor;
    private final ByteUtils.ByteConsumer consumer;
    private boolean finished;
    private final byte[] oneByte;

    /* renamed from: os */
    private final OutputStream f8946os;

    public SnappyCompressorOutputStream(OutputStream outputStream, long j) throws IOException {
        this(outputStream, j, 32768);
    }

    public SnappyCompressorOutputStream(OutputStream outputStream, long j, int i) throws IOException {
        this(outputStream, j, createParameterBuilder(i).build());
    }

    public SnappyCompressorOutputStream(OutputStream outputStream, long j, Parameters parameters) throws IOException {
        this.oneByte = new byte[1];
        this.f8946os = outputStream;
        this.consumer = new ByteUtils.OutputStreamByteConsumer(outputStream);
        this.compressor = new LZ77Compressor(parameters, new LZ77Compressor.Callback() { // from class: org.apache.commons.compress.compressors.snappy.-$$Lambda$SnappyCompressorOutputStream$p5kEa30rMD8jSlMXspZ21suqRas
            @Override // org.apache.commons.compress.compressors.lz77support.LZ77Compressor.Callback
            public final void accept(LZ77Compressor.Block block) {
                SnappyCompressorOutputStream.this.lambda$new$0$SnappyCompressorOutputStream(block);
            }
        });
        writeUncompressedSize(j);
    }

    /* renamed from: org.apache.commons.compress.compressors.snappy.SnappyCompressorOutputStream$1 */
    /* loaded from: classes8.dex */
    static /* synthetic */ class C81141 {

        /* renamed from: $SwitchMap$org$apache$commons$compress$compressors$lz77support$LZ77Compressor$Block$BlockType */
        static final /* synthetic */ int[] f8947x90901988 = new int[LZ77Compressor.Block.BlockType.values().length];

        static {
            try {
                f8947x90901988[LZ77Compressor.Block.BlockType.LITERAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8947x90901988[LZ77Compressor.Block.BlockType.BACK_REFERENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8947x90901988[LZ77Compressor.Block.BlockType.EOD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public /* synthetic */ void lambda$new$0$SnappyCompressorOutputStream(LZ77Compressor.Block block) throws IOException {
        int i = C81141.f8947x90901988[block.getType().ordinal()];
        if (i == 1) {
            writeLiteralBlock((LZ77Compressor.LiteralBlock) block);
        } else {
            if (i != 2) {
                return;
            }
            writeBackReference((LZ77Compressor.BackReference) block);
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.oneByte;
        bArr[0] = (byte) (i & 255);
        write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.compressor.compress(bArr, i, i2);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            finish();
        } finally {
            this.f8946os.close();
        }
    }

    public void finish() throws IOException {
        if (this.finished) {
            return;
        }
        this.compressor.finish();
        this.finished = true;
    }

    private void writeUncompressedSize(long j) throws IOException {
        boolean z;
        do {
            int i = (int) (127 & j);
            z = j > ((long) i);
            if (z) {
                i |= 128;
            }
            this.f8946os.write(i);
            j >>= 7;
        } while (z);
    }

    private void writeLiteralBlock(LZ77Compressor.LiteralBlock literalBlock) throws IOException {
        int length = literalBlock.getLength();
        if (length <= 60) {
            writeLiteralBlockNoSizeBytes(literalBlock, length);
            return;
        }
        if (length <= 256) {
            writeLiteralBlockOneSizeByte(literalBlock, length);
            return;
        }
        if (length <= 65536) {
            writeLiteralBlockTwoSizeBytes(literalBlock, length);
        } else if (length <= 16777216) {
            writeLiteralBlockThreeSizeBytes(literalBlock, length);
        } else {
            writeLiteralBlockFourSizeBytes(literalBlock, length);
        }
    }

    private void writeLiteralBlockNoSizeBytes(LZ77Compressor.LiteralBlock literalBlock, int i) throws IOException {
        writeLiteralBlockWithSize((i - 1) << 2, 0, i, literalBlock);
    }

    private void writeLiteralBlockOneSizeByte(LZ77Compressor.LiteralBlock literalBlock, int i) throws IOException {
        writeLiteralBlockWithSize(240, 1, i, literalBlock);
    }

    private void writeLiteralBlockTwoSizeBytes(LZ77Compressor.LiteralBlock literalBlock, int i) throws IOException {
        writeLiteralBlockWithSize(TWO_SIZE_BYTE_MARKER, 2, i, literalBlock);
    }

    private void writeLiteralBlockThreeSizeBytes(LZ77Compressor.LiteralBlock literalBlock, int i) throws IOException {
        writeLiteralBlockWithSize(248, 3, i, literalBlock);
    }

    private void writeLiteralBlockFourSizeBytes(LZ77Compressor.LiteralBlock literalBlock, int i) throws IOException {
        writeLiteralBlockWithSize(FOUR_SIZE_BYTE_MARKER, 4, i, literalBlock);
    }

    private void writeLiteralBlockWithSize(int i, int i2, int i3, LZ77Compressor.LiteralBlock literalBlock) throws IOException {
        this.f8946os.write(i);
        writeLittleEndian(i2, i3 - 1);
        this.f8946os.write(literalBlock.getData(), literalBlock.getOffset(), i3);
    }

    private void writeLittleEndian(int i, int i2) throws IOException {
        ByteUtils.toLittleEndian(this.consumer, i2, i);
    }

    private void writeBackReference(LZ77Compressor.BackReference backReference) throws IOException {
        int length = backReference.getLength();
        int offset = backReference.getOffset();
        if (length >= 4 && length <= 11 && offset <= 1024) {
            writeBackReferenceWithOneOffsetByte(length, offset);
        } else if (offset < 32768) {
            writeBackReferenceWithTwoOffsetBytes(length, offset);
        } else {
            writeBackReferenceWithFourOffsetBytes(length, offset);
        }
    }

    private void writeBackReferenceWithOneOffsetByte(int i, int i2) throws IOException {
        this.f8946os.write(((i - 4) << 2) | 1 | ((i2 & 1792) >> 3));
        this.f8946os.write(i2 & 255);
    }

    private void writeBackReferenceWithTwoOffsetBytes(int i, int i2) throws IOException {
        writeBackReferenceWithLittleEndianOffset(2, 2, i, i2);
    }

    private void writeBackReferenceWithFourOffsetBytes(int i, int i2) throws IOException {
        writeBackReferenceWithLittleEndianOffset(3, 4, i, i2);
    }

    private void writeBackReferenceWithLittleEndianOffset(int i, int i2, int i3, int i4) throws IOException {
        this.f8946os.write(i | ((i3 - 1) << 2));
        writeLittleEndian(i2, i4);
    }

    public static Parameters.Builder createParameterBuilder(int i) {
        return Parameters.builder(i).withMinBackReferenceLength(4).withMaxBackReferenceLength(64).withMaxOffset(i).withMaxLiteralLength(i);
    }
}
