package io.netty.handler.codec.compression;

import io.netty.buffer.ByteBuf;
import java.lang.reflect.Array;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
final class Bzip2HuffmanStageEncoder {
    private static final int HUFFMAN_HIGH_SYMBOL_COST = 15;
    private final int[][] huffmanCodeLengths;
    private final int[][] huffmanMergedCodeSymbols;
    private final int mtfAlphabetSize;
    private final char[] mtfBlock;
    private final int mtfLength;
    private final int[] mtfSymbolFrequencies;
    private final byte[] selectors;
    private final Bzip2BitWriter writer;

    private static int selectTableCount(int i) {
        if (i >= 2400) {
            return 6;
        }
        if (i >= 1200) {
            return 5;
        }
        if (i >= 600) {
            return 4;
        }
        return i >= 200 ? 3 : 2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bzip2HuffmanStageEncoder(Bzip2BitWriter bzip2BitWriter, char[] cArr, int i, int i2, int[] iArr) {
        this.writer = bzip2BitWriter;
        this.mtfBlock = cArr;
        this.mtfLength = i;
        this.mtfAlphabetSize = i2;
        this.mtfSymbolFrequencies = iArr;
        int selectTableCount = selectTableCount(i);
        this.huffmanCodeLengths = (int[][]) Array.newInstance((Class<?>) int.class, selectTableCount, i2);
        this.huffmanMergedCodeSymbols = (int[][]) Array.newInstance((Class<?>) int.class, selectTableCount, i2);
        this.selectors = new byte[((i + 50) - 1) / 50];
    }

    private static void generateHuffmanCodeLengths(int i, int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[i];
        int[] iArr4 = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr3[i2] = (iArr[i2] << 9) | i2;
        }
        Arrays.sort(iArr3);
        for (int i3 = 0; i3 < i; i3++) {
            iArr4[i3] = iArr3[i3] >>> 9;
        }
        Bzip2HuffmanAllocator.allocateHuffmanCodeLengths(iArr4, 20);
        for (int i4 = 0; i4 < i; i4++) {
            iArr2[iArr3[i4] & 511] = iArr4[i4];
        }
    }

    private void generateHuffmanOptimisationSeeds() {
        int[][] iArr = this.huffmanCodeLengths;
        int[] iArr2 = this.mtfSymbolFrequencies;
        int i = this.mtfAlphabetSize;
        int length = iArr.length;
        int i2 = this.mtfLength;
        int i3 = -1;
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = length - i4;
            int i6 = i2 / i5;
            int i7 = i3 + 1;
            int i8 = 0;
            while (i8 < i6 && i3 < i - 1) {
                i3++;
                i8 += iArr2[i3];
            }
            if (i3 > i7 && i4 != 0 && i4 != length - 1 && (i5 & 1) == 0) {
                i8 -= iArr2[i3];
                i3--;
            }
            int[] iArr3 = iArr[i4];
            for (int i9 = 0; i9 < i; i9++) {
                if (i9 < i7 || i9 > i3) {
                    iArr3[i9] = 15;
                }
            }
            i2 -= i8;
        }
    }

    private void optimiseSelectorsAndHuffmanTables(boolean z) {
        char[] cArr = this.mtfBlock;
        byte[] bArr = this.selectors;
        int[][] iArr = this.huffmanCodeLengths;
        int i = this.mtfLength;
        int i2 = this.mtfAlphabetSize;
        int length = iArr.length;
        int i3 = 1;
        byte b = 0;
        int[][] iArr2 = (int[][]) Array.newInstance((Class<?>) int.class, length, i2);
        int i4 = 0;
        int i5 = 0;
        while (i4 < i) {
            int min = Math.min(i4 + 50, i) - i3;
            short[] sArr = new short[length];
            int i6 = i4;
            while (i6 <= min) {
                char c = cArr[i6];
                for (int i7 = b; i7 < length; i7++) {
                    sArr[i7] = (short) (sArr[i7] + iArr[i7][c]);
                }
                i6++;
                b = 0;
            }
            byte b2 = b;
            short s = sArr[b2];
            byte b3 = b2;
            for (byte b4 = 1; b4 < length; b4 = (byte) (b4 + 1)) {
                short s2 = sArr[b4];
                if (s2 < s) {
                    s = s2;
                    b3 = b4;
                }
            }
            int[] iArr3 = iArr2[b3];
            while (i4 <= min) {
                char c2 = cArr[i4];
                iArr3[c2] = iArr3[c2] + 1;
                i4++;
            }
            if (z) {
                bArr[i5] = b3;
                i5++;
            }
            i4 = min + 1;
            i3 = 1;
            b = 0;
        }
        for (int i8 = 0; i8 < length; i8++) {
            generateHuffmanCodeLengths(i2, iArr2[i8], iArr[i8]);
        }
    }

    private void assignHuffmanCodeSymbols() {
        int[][] iArr = this.huffmanMergedCodeSymbols;
        int[][] iArr2 = this.huffmanCodeLengths;
        int i = this.mtfAlphabetSize;
        int length = iArr2.length;
        for (int i2 = 0; i2 < length; i2++) {
            int[] iArr3 = iArr2[i2];
            int i3 = 32;
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                int i6 = iArr3[i5];
                if (i6 > i4) {
                    i4 = i6;
                }
                if (i6 < i3) {
                    i3 = i6;
                }
            }
            int i7 = 0;
            while (i3 <= i4) {
                for (int i8 = 0; i8 < i; i8++) {
                    if ((iArr2[i2][i8] & 255) == i3) {
                        iArr[i2][i8] = (i3 << 24) | i7;
                        i7++;
                    }
                }
                i7 <<= 1;
                i3++;
            }
        }
    }

    private void writeSelectorsAndHuffmanTables(ByteBuf byteBuf) {
        Bzip2BitWriter bzip2BitWriter = this.writer;
        byte[] bArr = this.selectors;
        int length = bArr.length;
        int[][] iArr = this.huffmanCodeLengths;
        int length2 = iArr.length;
        int i = this.mtfAlphabetSize;
        long j = length2;
        int i2 = 3;
        bzip2BitWriter.writeBits(byteBuf, 3, j);
        bzip2BitWriter.writeBits(byteBuf, 15, length);
        Bzip2MoveToFrontTable bzip2MoveToFrontTable = new Bzip2MoveToFrontTable();
        for (byte b : bArr) {
            bzip2BitWriter.writeUnary(byteBuf, bzip2MoveToFrontTable.valueToFront(b));
        }
        int length3 = iArr.length;
        int i3 = 0;
        while (i3 < length3) {
            int[] iArr2 = iArr[i3];
            int i4 = iArr2[0];
            bzip2BitWriter.writeBits(byteBuf, 5, i4);
            int i5 = 0;
            while (i5 < i) {
                int i6 = iArr2[i5];
                int i7 = i4 < i6 ? 2 : i2;
                int abs = Math.abs(i6 - i4);
                while (true) {
                    int i8 = abs - 1;
                    if (abs > 0) {
                        bzip2BitWriter.writeBits(byteBuf, 2, i7);
                        i = i;
                        abs = i8;
                    }
                }
                bzip2BitWriter.writeBoolean(byteBuf, false);
                i5++;
                i4 = i6;
                i2 = 3;
            }
            i3++;
            i2 = 3;
        }
    }

    private void writeBlockData(ByteBuf byteBuf) {
        Bzip2BitWriter bzip2BitWriter = this.writer;
        int[][] iArr = this.huffmanMergedCodeSymbols;
        byte[] bArr = this.selectors;
        char[] cArr = this.mtfBlock;
        int i = this.mtfLength;
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int min = Math.min(i2 + 50, i) - 1;
            int i4 = i3 + 1;
            int[] iArr2 = iArr[bArr[i3]];
            while (i2 <= min) {
                int i5 = i2 + 1;
                int i6 = iArr2[cArr[i2]];
                bzip2BitWriter.writeBits(byteBuf, i6 >>> 24, i6);
                i2 = i5;
            }
            i3 = i4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void encode(ByteBuf byteBuf) {
        generateHuffmanOptimisationSeeds();
        int i = 3;
        while (i >= 0) {
            optimiseSelectorsAndHuffmanTables(i == 0);
            i--;
        }
        assignHuffmanCodeSymbols();
        writeSelectorsAndHuffmanTables(byteBuf);
        writeBlockData(byteBuf);
    }
}
