package com.amazonaws.services.p048s3.internal.crypto;

import com.amazonaws.internal.SdkFilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class ByteRangeCapturingInputStream extends SdkFilterInputStream {
    private final byte[] block;
    private int blockPosition;
    private final long endingPosition;
    private int markedBlockPosition;
    private long markedStreamPosition;
    private final long startingPosition;
    private long streamPosition;

    public ByteRangeCapturingInputStream(InputStream inputStream, long j, long j2) {
        super(inputStream);
        this.blockPosition = 0;
        if (j >= j2) {
            throw new IllegalArgumentException("Invalid byte range specified: the starting position must be less than the ending position");
        }
        this.startingPosition = j;
        this.endingPosition = j2;
        this.block = new byte[(int) (j2 - j)];
    }

    public byte[] getBlock() {
        return this.block;
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int read = super.read();
        if (read == -1) {
            return -1;
        }
        long j = this.streamPosition;
        if (j >= this.startingPosition && j <= this.endingPosition) {
            byte[] bArr = this.block;
            int i = this.blockPosition;
            this.blockPosition = i + 1;
            bArr[i] = (byte) read;
        }
        this.streamPosition++;
        return read;
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
        super.mark(i);
        if (markSupported()) {
            this.markedStreamPosition = this.streamPosition;
            this.markedBlockPosition = this.blockPosition;
        }
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        super.reset();
        if (markSupported()) {
            this.streamPosition = this.markedStreamPosition;
            this.blockPosition = this.markedBlockPosition;
        }
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = super.read(bArr, i, i2);
        if (read == -1) {
            return -1;
        }
        long j = this.streamPosition;
        long j2 = read;
        if (j + j2 >= this.startingPosition && j <= this.endingPosition) {
            for (int i3 = 0; i3 < read; i3++) {
                long j3 = this.streamPosition;
                long j4 = i3;
                if (j3 + j4 >= this.startingPosition && j3 + j4 < this.endingPosition) {
                    byte[] bArr2 = this.block;
                    int i4 = this.blockPosition;
                    this.blockPosition = i4 + 1;
                    bArr2[i4] = bArr[i + i3];
                }
            }
        }
        this.streamPosition += j2;
        return read;
    }
}
