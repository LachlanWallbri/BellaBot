package org.conscrypt;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class OpenSSLBIOInputStream extends FilterInputStream {
    private long ctx;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OpenSSLBIOInputStream(InputStream inputStream, boolean z) {
        super(inputStream);
        this.ctx = NativeCrypto.create_BIO_InputStream(this, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getBioContext() {
        return this.ctx;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void release() {
        NativeCrypto.BIO_free_all(this.ctx);
    }

    int gets(byte[] bArr) throws IOException {
        int read;
        int i = 0;
        if (bArr != null && bArr.length != 0) {
            while (i < bArr.length && (read = read()) != -1) {
                if (read != 10) {
                    bArr[i] = (byte) read;
                    i++;
                } else if (i != 0) {
                    break;
                }
            }
        }
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException("Invalid bounds");
        }
        int i3 = 0;
        if (i2 == 0) {
            return 0;
        }
        do {
            int read = super.read(bArr, i + i3, (i2 - i3) - i);
            if (read == -1) {
                break;
            }
            i3 += read;
        } while (i + i3 < i2);
        if (i3 == 0) {
            return -1;
        }
        return i3;
    }
}
