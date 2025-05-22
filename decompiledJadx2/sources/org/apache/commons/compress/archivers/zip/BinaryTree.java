package org.apache.commons.compress.archivers.zip;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.commons.compress.utils.IOUtils;
import org.jetbrains.anko.DimensionsKt;

/* loaded from: classes8.dex */
class BinaryTree {
    private static final int NODE = -2;
    private static final int UNDEFINED = -1;
    private final int[] tree;

    public BinaryTree(int i) {
        if (i < 0 || i > 30) {
            throw new IllegalArgumentException("depth must be bigger than 0 and not bigger than 30 but is " + i);
        }
        this.tree = new int[(int) ((1 << (i + 1)) - 1)];
        Arrays.fill(this.tree, -1);
    }

    public void addLeaf(int i, int i2, int i3, int i4) {
        if (i3 == 0) {
            int[] iArr = this.tree;
            if (iArr[i] != -1) {
                throw new IllegalArgumentException("Tree value at index " + i + " has already been assigned (" + this.tree[i] + ")");
            }
            iArr[i] = i4;
            return;
        }
        this.tree[i] = -2;
        addLeaf((i * 2) + 1 + (i2 & 1), i2 >>> 1, i3 - 1, i4);
    }

    public int read(BitStream bitStream) throws IOException {
        int i = 0;
        while (true) {
            int nextBit = bitStream.nextBit();
            if (nextBit == -1) {
                return -1;
            }
            int i2 = (i * 2) + 1 + nextBit;
            int i3 = this.tree[i2];
            if (i3 != -2) {
                if (i3 != -1) {
                    return i3;
                }
                throw new IOException("The child " + nextBit + " of node at index " + i + " is not defined");
            }
            i = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BinaryTree decode(InputStream inputStream, int i) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException("totalNumberOfValues must be bigger than 0, is " + i);
        }
        int read = inputStream.read() + 1;
        if (read == 0) {
            throw new IOException("Cannot read the size of the encoded tree, unexpected end of stream");
        }
        byte[] readRange = IOUtils.readRange(inputStream, read);
        if (readRange.length != read) {
            throw new EOFException();
        }
        int[] iArr = new int[i];
        int length = readRange.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            int i5 = readRange[i2];
            int i6 = ((i5 & DimensionsKt.HDPI) >> 4) + 1;
            if (i3 + i6 > i) {
                throw new IOException("Number of values exceeds given total number of values");
            }
            int i7 = (i5 & 15) + 1;
            int i8 = i3;
            int i9 = 0;
            while (i9 < i6) {
                iArr[i8] = i7;
                i9++;
                i8++;
            }
            i4 = Math.max(i4, i7);
            i2++;
            i3 = i8;
        }
        int length2 = iArr.length;
        int[] iArr2 = new int[length2];
        for (int i10 = 0; i10 < iArr2.length; i10++) {
            iArr2[i10] = i10;
        }
        int[] iArr3 = new int[length2];
        int i11 = 0;
        int i12 = 0;
        while (i11 < length2) {
            int i13 = i12;
            for (int i14 = 0; i14 < length2; i14++) {
                if (iArr[i14] == i11) {
                    iArr3[i13] = i11;
                    iArr2[i13] = i14;
                    i13++;
                }
            }
            i11++;
            i12 = i13;
        }
        int[] iArr4 = new int[i];
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        for (int i18 = i - 1; i18 >= 0; i18--) {
            i15 += i16;
            if (iArr3[i18] != i17) {
                int i19 = iArr3[i18];
                i17 = i19;
                i16 = 1 << (16 - i19);
            }
            iArr4[iArr2[i18]] = i15;
        }
        BinaryTree binaryTree = new BinaryTree(i4);
        for (int i20 = 0; i20 < iArr4.length; i20++) {
            int i21 = iArr[i20];
            if (i21 > 0) {
                binaryTree.addLeaf(0, Integer.reverse(iArr4[i20] << 16), i21, i20);
            }
        }
        return binaryTree;
    }
}
