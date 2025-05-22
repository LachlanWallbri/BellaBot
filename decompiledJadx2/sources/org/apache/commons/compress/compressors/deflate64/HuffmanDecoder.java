package org.apache.commons.compress.compressors.deflate64;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.Arrays;
import org.apache.commons.compress.utils.BitInputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.bouncycastle.crypto.tls.AlertDescription;
import org.bouncycastle.crypto.tls.CipherSuite;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public class HuffmanDecoder implements Closeable {
    private static final int[] FIXED_DISTANCE;
    private boolean finalBlock;

    /* renamed from: in */
    private final InputStream f8933in;
    private final DecodingMemory memory;
    private BitInputStream reader;
    private DecoderState state;
    private static final short[] RUN_LENGTH_TABLE = {96, 128, 160, 192, 224, 256, 288, 320, 353, 417, 481, 545, 610, 738, 866, 994, 1123, 1379, 1635, 1891, 2148, 2660, 3172, 3684, 4197, 5221, 6245, 7269, AlertDescription.unrecognized_name};
    private static final int[] DISTANCE_TABLE = {16, 32, 48, 64, 81, 113, 146, 210, 275, 403, 532, 788, 1045, 1557, 2070, 3094, 4119, 6167, 8216, 12312, 16409, 24601, 32794, CipherSuite.TLS_SRP_SHA_WITH_3DES_EDE_CBC_SHA, 65563, 98331, 131100, 196636, 262173, 393245, 524318, 786462};
    private static final int[] CODE_LENGTHS_ORDER = {16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15};
    private static final int[] FIXED_LITERALS = new int[288];

    static {
        Arrays.fill(FIXED_LITERALS, 0, 144, 8);
        Arrays.fill(FIXED_LITERALS, 144, 256, 9);
        Arrays.fill(FIXED_LITERALS, 256, 280, 7);
        Arrays.fill(FIXED_LITERALS, 280, 288, 8);
        FIXED_DISTANCE = new int[32];
        Arrays.fill(FIXED_DISTANCE, 5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HuffmanDecoder(InputStream inputStream) {
        this.memory = new DecodingMemory();
        this.reader = new BitInputStream(inputStream, ByteOrder.LITTLE_ENDIAN);
        this.f8933in = inputStream;
        this.state = new InitialState();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.state = new InitialState();
        this.reader = null;
    }

    public int decode(byte[] bArr) throws IOException {
        return decode(bArr, 0, bArr.length);
    }

    public int decode(byte[] bArr, int i, int i2) throws IOException {
        while (true) {
            if (this.finalBlock && !this.state.hasData()) {
                return -1;
            }
            if (this.state.state() == HuffmanState.INITIAL) {
                this.finalBlock = readBits(1) == 1;
                int readBits = (int) readBits(2);
                if (readBits == 0) {
                    switchToUncompressedState();
                } else if (readBits == 1) {
                    this.state = new HuffmanCodes(HuffmanState.FIXED_CODES, FIXED_LITERALS, FIXED_DISTANCE);
                } else if (readBits == 2) {
                    int[][] readDynamicTables = readDynamicTables();
                    this.state = new HuffmanCodes(HuffmanState.DYNAMIC_CODES, readDynamicTables[0], readDynamicTables[1]);
                } else {
                    throw new IllegalStateException("Unsupported compression: " + readBits);
                }
            } else {
                int read = this.state.read(bArr, i, i2);
                if (read != 0) {
                    return read;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getBytesRead() {
        return this.reader.getBytesRead();
    }

    private void switchToUncompressedState() throws IOException {
        this.reader.alignWithByteBoundary();
        long readBits = readBits(16);
        if ((65535 & (readBits ^ 65535)) != readBits(16)) {
            throw new IllegalStateException("Illegal LEN / NLEN values");
        }
        this.state = new UncompressedState(readBits);
    }

    private int[][] readDynamicTables() throws IOException {
        int[][] iArr = {new int[(int) (readBits(5) + 257)], new int[(int) (readBits(5) + 1)]};
        populateDynamicTables(this.reader, iArr[0], iArr[1]);
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int available() throws IOException {
        return this.state.available();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public static abstract class DecoderState {
        abstract int available() throws IOException;

        abstract boolean hasData();

        abstract int read(byte[] bArr, int i, int i2) throws IOException;

        abstract HuffmanState state();

        private DecoderState() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public class UncompressedState extends DecoderState {
        private final long blockLength;
        private long read;

        private UncompressedState(long j) {
            super();
            this.blockLength = j;
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        HuffmanState state() {
            return this.read < this.blockLength ? HuffmanState.STORED : HuffmanState.INITIAL;
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        int read(byte[] bArr, int i, int i2) throws IOException {
            int read;
            int i3 = 0;
            if (i2 == 0) {
                return 0;
            }
            int min = (int) Math.min(this.blockLength - this.read, i2);
            while (i3 < min) {
                if (HuffmanDecoder.this.reader.bitsCached() > 0) {
                    bArr[i + i3] = HuffmanDecoder.this.memory.add((byte) HuffmanDecoder.this.readBits(8));
                    read = 1;
                } else {
                    int i4 = i + i3;
                    read = HuffmanDecoder.this.f8933in.read(bArr, i4, min - i3);
                    if (read != -1) {
                        HuffmanDecoder.this.memory.add(bArr, i4, read);
                    } else {
                        throw new EOFException("Truncated Deflate64 Stream");
                    }
                }
                this.read += read;
                i3 += read;
            }
            return min;
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        boolean hasData() {
            return this.read < this.blockLength;
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        int available() throws IOException {
            return (int) Math.min(this.blockLength - this.read, HuffmanDecoder.this.reader.bitsAvailable() / 8);
        }
    }

    /* loaded from: classes8.dex */
    private static class InitialState extends DecoderState {
        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        int available() {
            return 0;
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        boolean hasData() {
            return false;
        }

        private InitialState() {
            super();
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        HuffmanState state() {
            return HuffmanState.INITIAL;
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        int read(byte[] bArr, int i, int i2) throws IOException {
            if (i2 == 0) {
                return 0;
            }
            throw new IllegalStateException("Cannot read in this state");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public class HuffmanCodes extends DecoderState {
        private final BinaryTreeNode distanceTree;
        private boolean endOfBlock;
        private final BinaryTreeNode lengthTree;
        private byte[] runBuffer;
        private int runBufferLength;
        private int runBufferPos;
        private final HuffmanState state;

        HuffmanCodes(HuffmanState huffmanState, int[] iArr, int[] iArr2) {
            super();
            this.runBuffer = ByteUtils.EMPTY_BYTE_ARRAY;
            this.state = huffmanState;
            this.lengthTree = HuffmanDecoder.buildTree(iArr);
            this.distanceTree = HuffmanDecoder.buildTree(iArr2);
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        HuffmanState state() {
            return this.endOfBlock ? HuffmanState.INITIAL : this.state;
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        int read(byte[] bArr, int i, int i2) throws IOException {
            if (i2 == 0) {
                return 0;
            }
            return decodeNext(bArr, i, i2);
        }

        private int decodeNext(byte[] bArr, int i, int i2) throws IOException {
            if (this.endOfBlock) {
                return -1;
            }
            int copyFromRunBuffer = copyFromRunBuffer(bArr, i, i2);
            while (true) {
                if (copyFromRunBuffer < i2) {
                    int nextSymbol = HuffmanDecoder.nextSymbol(HuffmanDecoder.this.reader, this.lengthTree);
                    if (nextSymbol >= 256) {
                        if (nextSymbol > 256) {
                            int readBits = (int) ((r1 >>> 5) + HuffmanDecoder.this.readBits(HuffmanDecoder.RUN_LENGTH_TABLE[nextSymbol - 257] & 31));
                            int readBits2 = (int) ((r2 >>> 4) + HuffmanDecoder.this.readBits(HuffmanDecoder.DISTANCE_TABLE[HuffmanDecoder.nextSymbol(HuffmanDecoder.this.reader, this.distanceTree)] & 15));
                            if (this.runBuffer.length < readBits) {
                                this.runBuffer = new byte[readBits];
                            }
                            this.runBufferLength = readBits;
                            this.runBufferPos = 0;
                            HuffmanDecoder.this.memory.recordToBuffer(readBits2, readBits, this.runBuffer);
                            copyFromRunBuffer += copyFromRunBuffer(bArr, i + copyFromRunBuffer, i2 - copyFromRunBuffer);
                        } else {
                            this.endOfBlock = true;
                            break;
                        }
                    } else {
                        bArr[copyFromRunBuffer + i] = HuffmanDecoder.this.memory.add((byte) nextSymbol);
                        copyFromRunBuffer++;
                    }
                } else {
                    break;
                }
            }
            return copyFromRunBuffer;
        }

        private int copyFromRunBuffer(byte[] bArr, int i, int i2) {
            int i3 = this.runBufferLength - this.runBufferPos;
            if (i3 <= 0) {
                return 0;
            }
            int min = Math.min(i2, i3);
            System.arraycopy(this.runBuffer, this.runBufferPos, bArr, i, min);
            this.runBufferPos += min;
            return min;
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        boolean hasData() {
            return !this.endOfBlock;
        }

        @Override // org.apache.commons.compress.compressors.deflate64.HuffmanDecoder.DecoderState
        int available() {
            return this.runBufferLength - this.runBufferPos;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int nextSymbol(BitInputStream bitInputStream, BinaryTreeNode binaryTreeNode) throws IOException {
        while (binaryTreeNode != null && binaryTreeNode.literal == -1) {
            binaryTreeNode = readBits(bitInputStream, 1) == 0 ? binaryTreeNode.leftNode : binaryTreeNode.rightNode;
        }
        if (binaryTreeNode != null) {
            return binaryTreeNode.literal;
        }
        return -1;
    }

    private static void populateDynamicTables(BitInputStream bitInputStream, int[] iArr, int[] iArr2) throws IOException {
        long readBits;
        int readBits2 = (int) (readBits(bitInputStream, 4) + 4);
        int[] iArr3 = new int[19];
        for (int i = 0; i < readBits2; i++) {
            iArr3[CODE_LENGTHS_ORDER[i]] = (int) readBits(bitInputStream, 3);
        }
        BinaryTreeNode buildTree = buildTree(iArr3);
        int[] iArr4 = new int[iArr.length + iArr2.length];
        int i2 = 0;
        int i3 = -1;
        int i4 = 0;
        while (i4 < iArr4.length) {
            if (i2 > 0) {
                iArr4[i4] = i3;
                i2--;
                i4++;
            } else {
                int nextSymbol = nextSymbol(bitInputStream, buildTree);
                if (nextSymbol < 16) {
                    iArr4[i4] = nextSymbol;
                    i4++;
                    i3 = nextSymbol;
                } else {
                    switch (nextSymbol) {
                        case 16:
                            i2 = (int) (readBits(bitInputStream, 2) + 3);
                            continue;
                        case 17:
                            readBits = readBits(bitInputStream, 3) + 3;
                            break;
                        case 18:
                            readBits = readBits(bitInputStream, 7) + 11;
                            break;
                    }
                    i2 = (int) readBits;
                    i3 = 0;
                }
            }
        }
        System.arraycopy(iArr4, 0, iArr, 0, iArr.length);
        System.arraycopy(iArr4, iArr.length, iArr2, 0, iArr2.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public static class BinaryTreeNode {
        private final int bits;
        BinaryTreeNode leftNode;
        int literal;
        BinaryTreeNode rightNode;

        private BinaryTreeNode(int i) {
            this.literal = -1;
            this.bits = i;
        }

        void leaf(int i) {
            this.literal = i;
            this.leftNode = null;
            this.rightNode = null;
        }

        BinaryTreeNode left() {
            if (this.leftNode == null && this.literal == -1) {
                this.leftNode = new BinaryTreeNode(this.bits + 1);
            }
            return this.leftNode;
        }

        BinaryTreeNode right() {
            if (this.rightNode == null && this.literal == -1) {
                this.rightNode = new BinaryTreeNode(this.bits + 1);
            }
            return this.rightNode;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BinaryTreeNode buildTree(int[] iArr) {
        int[] codes = getCodes(iArr);
        int i = 0;
        BinaryTreeNode binaryTreeNode = new BinaryTreeNode(i);
        while (i < iArr.length) {
            int i2 = iArr[i];
            if (i2 != 0) {
                int i3 = i2 - 1;
                int i4 = codes[i3];
                BinaryTreeNode binaryTreeNode2 = binaryTreeNode;
                for (int i5 = i3; i5 >= 0; i5--) {
                    binaryTreeNode2 = ((1 << i5) & i4) == 0 ? binaryTreeNode2.left() : binaryTreeNode2.right();
                    if (binaryTreeNode2 == null) {
                        throw new IllegalStateException("node doesn't exist in Huffman tree");
                    }
                }
                binaryTreeNode2.leaf(i);
                codes[i3] = codes[i3] + 1;
            }
            i++;
        }
        return binaryTreeNode;
    }

    private static int[] getCodes(int[] iArr) {
        int[] iArr2 = new int[65];
        int i = 0;
        for (int i2 : iArr) {
            if (i2 < 0 || i2 > 64) {
                throw new IllegalArgumentException("Invalid code " + i2 + " in literal table");
            }
            i = Math.max(i, i2);
            iArr2[i2] = iArr2[i2] + 1;
        }
        int i3 = i + 1;
        int[] copyOf = Arrays.copyOf(iArr2, i3);
        int[] iArr3 = new int[i3];
        int i4 = 0;
        for (int i5 = 0; i5 <= i; i5++) {
            i4 = (i4 + copyOf[i5]) << 1;
            iArr3[i5] = i4;
        }
        return iArr3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public static class DecodingMemory {
        private final int mask;
        private final byte[] memory;
        private int wHead;
        private boolean wrappedAround;

        private DecodingMemory() {
            this(16);
        }

        private DecodingMemory(int i) {
            this.memory = new byte[1 << i];
            this.mask = this.memory.length - 1;
        }

        byte add(byte b) {
            byte[] bArr = this.memory;
            int i = this.wHead;
            bArr[i] = b;
            this.wHead = incCounter(i);
            return b;
        }

        void add(byte[] bArr, int i, int i2) {
            for (int i3 = i; i3 < i + i2; i3++) {
                add(bArr[i3]);
            }
        }

        void recordToBuffer(int i, int i2, byte[] bArr) {
            if (i > this.memory.length) {
                throw new IllegalStateException("Illegal distance parameter: " + i);
            }
            int i3 = this.wHead;
            int i4 = (i3 - i) & this.mask;
            if (!this.wrappedAround && i4 >= i3) {
                throw new IllegalStateException("Attempt to read beyond memory: dist=" + i);
            }
            int i5 = 0;
            while (i5 < i2) {
                bArr[i5] = add(this.memory[i4]);
                i5++;
                i4 = incCounter(i4);
            }
        }

        private int incCounter(int i) {
            int i2 = (i + 1) & this.mask;
            if (!this.wrappedAround && i2 < i) {
                this.wrappedAround = true;
            }
            return i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long readBits(int i) throws IOException {
        return readBits(this.reader, i);
    }

    private static long readBits(BitInputStream bitInputStream, int i) throws IOException {
        long readBits = bitInputStream.readBits(i);
        if (readBits != -1) {
            return readBits;
        }
        throw new EOFException("Truncated Deflate64 Stream");
    }
}
