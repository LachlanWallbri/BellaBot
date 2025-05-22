package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.utils.CloseShieldFilterInputStream;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;

/* loaded from: classes8.dex */
class ExplodingInputStream extends InputStream implements InputStreamStatistics {
    private BitStream bits;
    private final CircularBuffer buffer = new CircularBuffer(32768);
    private final int dictionarySize;
    private BinaryTree distanceTree;

    /* renamed from: in */
    private final InputStream f8916in;
    private BinaryTree lengthTree;
    private BinaryTree literalTree;
    private final int minimumMatchLength;
    private final int numberOfTrees;
    private long treeSizes;
    private long uncompressedCount;

    public ExplodingInputStream(int i, int i2, InputStream inputStream) {
        if (i != 4096 && i != 8192) {
            throw new IllegalArgumentException("The dictionary size must be 4096 or 8192");
        }
        if (i2 != 2 && i2 != 3) {
            throw new IllegalArgumentException("The number of trees must be 2 or 3");
        }
        this.dictionarySize = i;
        this.numberOfTrees = i2;
        this.minimumMatchLength = i2;
        this.f8916in = inputStream;
    }

    private void init() throws IOException {
        if (this.bits == null) {
            CountingInputStream countingInputStream = new CountingInputStream(new CloseShieldFilterInputStream(this.f8916in));
            try {
                if (this.numberOfTrees == 3) {
                    this.literalTree = BinaryTree.decode(countingInputStream, 256);
                }
                this.lengthTree = BinaryTree.decode(countingInputStream, 64);
                this.distanceTree = BinaryTree.decode(countingInputStream, 64);
                this.treeSizes += countingInputStream.getBytesRead();
                countingInputStream.close();
                this.bits = new BitStream(this.f8916in);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        countingInputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (!this.buffer.available()) {
            try {
                fillBuffer();
            } catch (IllegalArgumentException e) {
                throw new IOException("bad IMPLODE stream", e);
            }
        }
        int i = this.buffer.get();
        if (i > -1) {
            this.uncompressedCount++;
        }
        return i;
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getCompressedCount() {
        return this.bits.getBytesRead() + this.treeSizes;
    }

    @Override // org.apache.commons.compress.utils.InputStreamStatistics
    public long getUncompressedCount() {
        return this.uncompressedCount;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f8916in.close();
    }

    private void fillBuffer() throws IOException {
        int nextByte;
        init();
        int nextBit = this.bits.nextBit();
        if (nextBit == -1) {
            return;
        }
        if (nextBit == 1) {
            BinaryTree binaryTree = this.literalTree;
            if (binaryTree != null) {
                nextByte = binaryTree.read(this.bits);
            } else {
                nextByte = this.bits.nextByte();
            }
            if (nextByte == -1) {
                return;
            }
            this.buffer.put(nextByte);
            return;
        }
        int i = this.dictionarySize == 4096 ? 6 : 7;
        int nextBits = (int) this.bits.nextBits(i);
        int read = this.distanceTree.read(this.bits);
        if (read != -1 || nextBits > 0) {
            int i2 = (read << i) | nextBits;
            int read2 = this.lengthTree.read(this.bits);
            if (read2 == 63) {
                long nextBits2 = this.bits.nextBits(8);
                if (nextBits2 == -1) {
                    return;
                } else {
                    read2 = (int) (read2 + nextBits2);
                }
            }
            this.buffer.copy(i2 + 1, read2 + this.minimumMatchLength);
        }
    }
}
