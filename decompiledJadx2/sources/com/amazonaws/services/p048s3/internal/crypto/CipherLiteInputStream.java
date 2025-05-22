package com.amazonaws.services.p048s3.internal.crypto;

import com.amazonaws.internal.SdkFilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

@Deprecated
/* loaded from: classes.dex */
public class CipherLiteInputStream extends SdkFilterInputStream {
    private static final int BYTE_MASK = 255;
    private static final int DEFAULT_IN_BUFFER_SIZE = 512;
    private static final int MAX_RETRY = 1000;
    private final byte[] bufin;
    private byte[] bufout;
    private CipherLite cipherLite;
    private int currPos;
    private boolean eof;
    private final boolean lastMultiPart;
    private int maxPos;
    private final boolean multipart;

    public CipherLiteInputStream(InputStream inputStream, CipherLite cipherLite) {
        this(inputStream, cipherLite, 512, false, false);
    }

    public CipherLiteInputStream(InputStream inputStream, CipherLite cipherLite, int i) {
        this(inputStream, cipherLite, i, false, false);
    }

    public CipherLiteInputStream(InputStream inputStream, CipherLite cipherLite, int i, boolean z, boolean z2) {
        super(inputStream);
        this.eof = false;
        this.currPos = 0;
        this.maxPos = 0;
        if (z2 && !z) {
            throw new IllegalArgumentException("lastMultiPart can only be true if multipart is true");
        }
        this.multipart = z;
        this.lastMultiPart = z2;
        this.cipherLite = cipherLite;
        if (i <= 0 || i % 512 != 0) {
            throw new IllegalArgumentException("buffsize (" + i + ") must be a positive multiple of 512");
        }
        this.bufin = new byte[i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CipherLiteInputStream(InputStream inputStream) {
        this(inputStream, CipherLite.NULL, 512, false, false);
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.currPos >= this.maxPos) {
            if (this.eof) {
                return -1;
            }
            int i = 0;
            while (i <= 1000) {
                int nextChunk = nextChunk();
                i++;
                if (nextChunk != 0) {
                    if (nextChunk == -1) {
                        return -1;
                    }
                }
            }
            throw new IOException("exceeded maximum number of attempts to read next chunk of data");
        }
        byte[] bArr = this.bufout;
        int i2 = this.currPos;
        this.currPos = i2 + 1;
        return bArr[i2] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.currPos >= this.maxPos) {
            if (this.eof) {
                return -1;
            }
            int i3 = 0;
            while (i3 <= 1000) {
                int nextChunk = nextChunk();
                i3++;
                if (nextChunk != 0) {
                    if (nextChunk == -1) {
                        return -1;
                    }
                }
            }
            throw new IOException("exceeded maximum number of attempts to read next chunk of data");
        }
        if (i2 <= 0) {
            return 0;
        }
        int i4 = this.maxPos - this.currPos;
        if (i2 >= i4) {
            i2 = i4;
        }
        System.arraycopy(this.bufout, this.currPos, bArr, i, i2);
        this.currPos += i2;
        return i2;
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        abortIfNeeded();
        long j2 = this.maxPos - this.currPos;
        if (j > j2) {
            j = j2;
        }
        if (j < 0) {
            return 0L;
        }
        this.currPos = (int) (this.currPos + j);
        return j;
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public int available() {
        abortIfNeeded();
        return this.maxPos - this.currPos;
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.in.close();
        if (!this.multipart && !S3CryptoScheme.isAesGcm(this.cipherLite.getCipherAlgorithm())) {
            try {
                this.cipherLite.doFinal();
            } catch (BadPaddingException | IllegalBlockSizeException unused) {
            }
        }
        this.currPos = 0;
        this.maxPos = 0;
        abortIfNeeded();
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        abortIfNeeded();
        return this.in.markSupported() && this.cipherLite.markSupported();
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public void mark(int i) {
        abortIfNeeded();
        this.in.mark(i);
        this.cipherLite.mark();
    }

    @Override // com.amazonaws.internal.SdkFilterInputStream, java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        abortIfNeeded();
        this.in.reset();
        this.cipherLite.reset();
        resetInternal();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void resetInternal() {
        if (markSupported()) {
            this.currPos = 0;
            this.maxPos = 0;
            this.eof = false;
        }
    }

    private int nextChunk() throws IOException {
        abortIfNeeded();
        if (this.eof) {
            return -1;
        }
        this.bufout = null;
        int read = this.in.read(this.bufin);
        if (read == -1) {
            this.eof = true;
            if (!this.multipart || this.lastMultiPart) {
                try {
                    this.bufout = this.cipherLite.doFinal();
                    if (this.bufout == null) {
                        return -1;
                    }
                    this.currPos = 0;
                    this.maxPos = this.bufout.length;
                    return this.maxPos;
                } catch (BadPaddingException e) {
                    if (S3CryptoScheme.isAesGcm(this.cipherLite.getCipherAlgorithm())) {
                        throw new SecurityException(e);
                    }
                } catch (IllegalBlockSizeException unused) {
                }
            }
            return -1;
        }
        this.bufout = this.cipherLite.update(this.bufin, 0, read);
        this.currPos = 0;
        byte[] bArr = this.bufout;
        this.maxPos = bArr != null ? bArr.length : 0;
        return this.maxPos;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void renewCipherLite() {
        this.cipherLite = this.cipherLite.recreate();
    }
}
