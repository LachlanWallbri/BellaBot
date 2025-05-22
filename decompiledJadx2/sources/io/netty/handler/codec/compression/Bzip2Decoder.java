package io.netty.handler.codec.compression;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import org.apache.commons.compress.compressors.bzip2.BZip2Constants;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class Bzip2Decoder extends ByteToMessageDecoder {
    private int blockCRC;
    private Bzip2BlockDecompressor blockDecompressor;
    private int blockSize;
    private Bzip2HuffmanStageDecoder huffmanStageDecoder;
    private int streamCRC;
    private State currentState = State.INIT;
    private final Bzip2BitReader reader = new Bzip2BitReader();

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes7.dex
     */
    /* loaded from: classes.dex */
    private enum State {
        INIT,
        INIT_BLOCK,
        INIT_BLOCK_PARAMS,
        RECEIVE_HUFFMAN_USED_MAP,
        RECEIVE_HUFFMAN_USED_BITMAPS,
        RECEIVE_SELECTORS_NUMBER,
        RECEIVE_SELECTORS,
        RECEIVE_HUFFMAN_LENGTH,
        DECODE_HUFFMAN_DATA,
        EOF
    }

    /* JADX WARN: Code restructure failed: missing block: B:105:0x01af, code lost:
    
        r5[r9][r13] = (byte) r7;
        r13 = r13 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x0193, code lost:
    
        r11 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0248, code lost:
    
        throw new io.netty.handler.codec.compression.DecompressionException("incorrect selectors number");
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0258, code lost:
    
        throw new io.netty.handler.codec.compression.DecompressionException("incorrect huffman groups number");
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x0260, code lost:
    
        throw new io.netty.handler.codec.compression.DecompressionException("bad block header");
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0268, code lost:
    
        throw new io.netty.handler.codec.compression.DecompressionException("block size is invalid");
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0021. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01fc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01de A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01db A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:159:0x013a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x00e8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:171:0x00c7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:173:0x00a7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x015a  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x018b  */
    @Override // io.netty.handler.codec.ByteToMessageDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Bzip2BlockDecompressor bzip2BlockDecompressor;
        Bzip2HuffmanStageDecoder bzip2HuffmanStageDecoder;
        int i;
        int i2;
        boolean z;
        int i3;
        int i4;
        int length;
        int i5;
        int bitCount;
        int i6;
        if (byteBuf.isReadable()) {
            Bzip2BitReader bzip2BitReader = this.reader;
            bzip2BitReader.setByteBuf(byteBuf);
            while (true) {
                int i7 = 16;
                boolean z2 = false;
                switch (C71091.$SwitchMap$io$netty$handler$codec$compression$Bzip2Decoder$State[this.currentState.ordinal()]) {
                    case 1:
                        if (byteBuf.readableBytes() < 4) {
                            return;
                        }
                        if (byteBuf.readUnsignedMedium() != 4348520) {
                            throw new DecompressionException("Unexpected stream identifier contents. Mismatched bzip2 protocol version?");
                        }
                        int readByte = byteBuf.readByte() - 48;
                        if (readByte >= 1 && readByte <= 9) {
                            this.blockSize = readByte * BZip2Constants.BASEBLOCKSIZE;
                            this.streamCRC = 0;
                            this.currentState = State.INIT_BLOCK;
                        }
                        break;
                    case 2:
                        if (!bzip2BitReader.hasReadableBytes(10)) {
                            return;
                        }
                        int readBits = bzip2BitReader.readBits(24);
                        int readBits2 = bzip2BitReader.readBits(24);
                        if (readBits == 1536581 && readBits2 == 3690640) {
                            if (bzip2BitReader.readInt() != this.streamCRC) {
                                throw new DecompressionException("stream CRC error");
                            }
                            this.currentState = State.EOF;
                        } else if (readBits == 3227993 && readBits2 == 2511705) {
                            this.blockCRC = bzip2BitReader.readInt();
                            this.currentState = State.INIT_BLOCK_PARAMS;
                            if (bzip2BitReader.hasReadableBits(25)) {
                                return;
                            }
                            this.blockDecompressor = new Bzip2BlockDecompressor(this.blockSize, this.blockCRC, bzip2BitReader.readBoolean(), bzip2BitReader.readBits(24), bzip2BitReader);
                            this.currentState = State.RECEIVE_HUFFMAN_USED_MAP;
                            if (bzip2BitReader.hasReadableBits(16)) {
                                return;
                            }
                            this.blockDecompressor.huffmanInUse16 = bzip2BitReader.readBits(16);
                            this.currentState = State.RECEIVE_HUFFMAN_USED_BITMAPS;
                            Bzip2BlockDecompressor bzip2BlockDecompressor2 = this.blockDecompressor;
                            int i8 = bzip2BlockDecompressor2.huffmanInUse16;
                            bitCount = Integer.bitCount(i8);
                            byte[] bArr = bzip2BlockDecompressor2.huffmanSymbolMap;
                            if (bzip2BitReader.hasReadableBits((bitCount * 16) + 3)) {
                                return;
                            }
                            if (bitCount > 0) {
                                int i9 = 0;
                                i6 = 0;
                                while (i9 < i7) {
                                    if (((32768 >>> i9) & i8) != 0) {
                                        int i10 = i9 << 4;
                                        int i11 = 0;
                                        while (i11 < i7) {
                                            if (bzip2BitReader.readBoolean()) {
                                                bArr[i6] = (byte) i10;
                                                i6++;
                                            }
                                            i11++;
                                            i10++;
                                            i7 = 16;
                                        }
                                    }
                                    i9++;
                                    i7 = 16;
                                }
                            } else {
                                i6 = 0;
                            }
                            bzip2BlockDecompressor2.huffmanEndOfBlockSymbol = i6 + 1;
                            int readBits3 = bzip2BitReader.readBits(3);
                            if (readBits3 >= 2 && readBits3 <= 6) {
                                int i12 = i6 + 2;
                                if (i12 > 258) {
                                    throw new DecompressionException("incorrect alphabet size");
                                }
                                this.huffmanStageDecoder = new Bzip2HuffmanStageDecoder(bzip2BitReader, readBits3, i12);
                                this.currentState = State.RECEIVE_SELECTORS_NUMBER;
                                if (bzip2BitReader.hasReadableBits(15)) {
                                    return;
                                }
                                int readBits4 = bzip2BitReader.readBits(15);
                                if (readBits4 >= 1 && readBits4 <= 18002) {
                                    this.huffmanStageDecoder.selectors = new byte[readBits4];
                                    this.currentState = State.RECEIVE_SELECTORS;
                                    Bzip2HuffmanStageDecoder bzip2HuffmanStageDecoder2 = this.huffmanStageDecoder;
                                    byte[] bArr2 = bzip2HuffmanStageDecoder2.selectors;
                                    length = bArr2.length;
                                    Bzip2MoveToFrontTable bzip2MoveToFrontTable = bzip2HuffmanStageDecoder2.tableMTF;
                                    for (i5 = bzip2HuffmanStageDecoder2.currentSelector; i5 < length; i5++) {
                                        if (!bzip2BitReader.hasReadableBits(6)) {
                                            bzip2HuffmanStageDecoder2.currentSelector = i5;
                                            return;
                                        }
                                        int i13 = 0;
                                        while (bzip2BitReader.readBoolean()) {
                                            i13++;
                                        }
                                        bArr2[i5] = bzip2MoveToFrontTable.indexToFront(i13);
                                    }
                                    this.currentState = State.RECEIVE_HUFFMAN_LENGTH;
                                    bzip2HuffmanStageDecoder = this.huffmanStageDecoder;
                                    i = bzip2HuffmanStageDecoder.totalTables;
                                    byte[][] bArr3 = bzip2HuffmanStageDecoder.tableCodeLengths;
                                    int i14 = bzip2HuffmanStageDecoder.alphabetSize;
                                    i2 = bzip2HuffmanStageDecoder.currentLength;
                                    z = bzip2HuffmanStageDecoder.modifyLength;
                                    i3 = bzip2HuffmanStageDecoder.currentGroup;
                                    while (true) {
                                        if (i3 >= i) {
                                            i4 = 0;
                                        } else if (!bzip2BitReader.hasReadableBits(5)) {
                                            i4 = 0;
                                            break;
                                        } else {
                                            if (i2 < 0) {
                                                i2 = bzip2BitReader.readBits(5);
                                            }
                                            i4 = bzip2HuffmanStageDecoder.currentAlpha;
                                            while (i4 < i14) {
                                                if (!bzip2BitReader.isReadable()) {
                                                    break;
                                                } else {
                                                    while (true) {
                                                        if (z || bzip2BitReader.readBoolean()) {
                                                            if (bzip2BitReader.isReadable()) {
                                                                i2 += bzip2BitReader.readBoolean() ? -1 : 1;
                                                                if (!bzip2BitReader.isReadable()) {
                                                                    z = false;
                                                                    break;
                                                                } else {
                                                                    z = false;
                                                                }
                                                            } else {
                                                                z = true;
                                                                z2 = true;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            bzip2HuffmanStageDecoder.currentAlpha = 0;
                                            i3++;
                                            i2 = -1;
                                            z = false;
                                        }
                                    }
                                    if (z2) {
                                        bzip2HuffmanStageDecoder.currentGroup = i3;
                                        bzip2HuffmanStageDecoder.currentLength = i2;
                                        bzip2HuffmanStageDecoder.currentAlpha = i4;
                                        bzip2HuffmanStageDecoder.modifyLength = z;
                                        return;
                                    }
                                    bzip2HuffmanStageDecoder.createHuffmanDecodingTables();
                                    this.currentState = State.DECODE_HUFFMAN_DATA;
                                    bzip2BlockDecompressor = this.blockDecompressor;
                                    int readerIndex = byteBuf.readerIndex();
                                    if (bzip2BlockDecompressor.decodeHuffmanData(this.huffmanStageDecoder)) {
                                        return;
                                    }
                                    if (byteBuf.readerIndex() == readerIndex && byteBuf.isReadable()) {
                                        bzip2BitReader.refill();
                                    }
                                    ByteBuf buffer = channelHandlerContext.alloc().buffer(bzip2BlockDecompressor.blockLength());
                                    while (true) {
                                        try {
                                            int read = bzip2BlockDecompressor.read();
                                            if (read >= 0) {
                                                buffer.writeByte(read);
                                            } else {
                                                this.streamCRC = bzip2BlockDecompressor.checkCRC() ^ ((this.streamCRC << 1) | (this.streamCRC >>> 31));
                                                list.add(buffer);
                                                this.currentState = State.INIT_BLOCK;
                                            }
                                        } catch (Throwable th) {
                                            buffer.release();
                                            throw th;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case 3:
                        if (bzip2BitReader.hasReadableBits(25)) {
                        }
                        break;
                    case 4:
                        if (bzip2BitReader.hasReadableBits(16)) {
                        }
                        break;
                    case 5:
                        Bzip2BlockDecompressor bzip2BlockDecompressor22 = this.blockDecompressor;
                        int i82 = bzip2BlockDecompressor22.huffmanInUse16;
                        bitCount = Integer.bitCount(i82);
                        byte[] bArr4 = bzip2BlockDecompressor22.huffmanSymbolMap;
                        if (bzip2BitReader.hasReadableBits((bitCount * 16) + 3)) {
                        }
                        break;
                    case 6:
                        if (bzip2BitReader.hasReadableBits(15)) {
                        }
                        break;
                    case 7:
                        Bzip2HuffmanStageDecoder bzip2HuffmanStageDecoder22 = this.huffmanStageDecoder;
                        byte[] bArr22 = bzip2HuffmanStageDecoder22.selectors;
                        length = bArr22.length;
                        Bzip2MoveToFrontTable bzip2MoveToFrontTable2 = bzip2HuffmanStageDecoder22.tableMTF;
                        while (i5 < length) {
                        }
                        this.currentState = State.RECEIVE_HUFFMAN_LENGTH;
                        bzip2HuffmanStageDecoder = this.huffmanStageDecoder;
                        i = bzip2HuffmanStageDecoder.totalTables;
                        byte[][] bArr32 = bzip2HuffmanStageDecoder.tableCodeLengths;
                        int i142 = bzip2HuffmanStageDecoder.alphabetSize;
                        i2 = bzip2HuffmanStageDecoder.currentLength;
                        z = bzip2HuffmanStageDecoder.modifyLength;
                        i3 = bzip2HuffmanStageDecoder.currentGroup;
                        while (true) {
                            if (i3 >= i) {
                            }
                            bzip2HuffmanStageDecoder.currentAlpha = 0;
                            i3++;
                            i2 = -1;
                            z = false;
                        }
                        if (z2) {
                        }
                        break;
                    case 8:
                        bzip2HuffmanStageDecoder = this.huffmanStageDecoder;
                        i = bzip2HuffmanStageDecoder.totalTables;
                        byte[][] bArr322 = bzip2HuffmanStageDecoder.tableCodeLengths;
                        int i1422 = bzip2HuffmanStageDecoder.alphabetSize;
                        i2 = bzip2HuffmanStageDecoder.currentLength;
                        z = bzip2HuffmanStageDecoder.modifyLength;
                        i3 = bzip2HuffmanStageDecoder.currentGroup;
                        while (true) {
                            if (i3 >= i) {
                            }
                            bzip2HuffmanStageDecoder.currentAlpha = 0;
                            i3++;
                            i2 = -1;
                            z = false;
                        }
                        if (z2) {
                        }
                        break;
                    case 9:
                        bzip2BlockDecompressor = this.blockDecompressor;
                        int readerIndex2 = byteBuf.readerIndex();
                        if (bzip2BlockDecompressor.decodeHuffmanData(this.huffmanStageDecoder)) {
                        }
                        break;
                    case 10:
                        byteBuf.skipBytes(byteBuf.readableBytes());
                        return;
                    default:
                        throw new IllegalStateException();
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes7.dex
     */
    /* renamed from: io.netty.handler.codec.compression.Bzip2Decoder$1 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C71091 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$Bzip2Decoder$State;

        static {
            int[] iArr = new int[State.values().length];
            $SwitchMap$io$netty$handler$codec$compression$Bzip2Decoder$State = iArr;
            try {
                iArr[State.INIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Bzip2Decoder$State[State.INIT_BLOCK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Bzip2Decoder$State[State.INIT_BLOCK_PARAMS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Bzip2Decoder$State[State.RECEIVE_HUFFMAN_USED_MAP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Bzip2Decoder$State[State.RECEIVE_HUFFMAN_USED_BITMAPS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Bzip2Decoder$State[State.RECEIVE_SELECTORS_NUMBER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Bzip2Decoder$State[State.RECEIVE_SELECTORS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Bzip2Decoder$State[State.RECEIVE_HUFFMAN_LENGTH.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Bzip2Decoder$State[State.DECODE_HUFFMAN_DATA.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$netty$handler$codec$compression$Bzip2Decoder$State[State.EOF.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public boolean isClosed() {
        return this.currentState == State.EOF;
    }
}
