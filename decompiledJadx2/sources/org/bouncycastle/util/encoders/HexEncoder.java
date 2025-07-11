package org.bouncycastle.util.encoders;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* loaded from: classes9.dex */
public class HexEncoder implements Encoder {
    protected final byte[] encodingTable = {TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 97, 98, 99, 100, 101, 102};
    protected final byte[] decodingTable = new byte[128];

    public HexEncoder() {
        initialiseDecodingTable();
    }

    private static boolean ignore(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(String str, OutputStream outputStream) throws IOException {
        int length = str.length();
        while (length > 0 && ignore(str.charAt(length - 1))) {
            length--;
        }
        int i = 0;
        int i2 = 0;
        while (i < length) {
            while (i < length && ignore(str.charAt(i))) {
                i++;
            }
            int i3 = i + 1;
            byte b = this.decodingTable[str.charAt(i)];
            while (i3 < length && ignore(str.charAt(i3))) {
                i3++;
            }
            int i4 = i3 + 1;
            byte b2 = this.decodingTable[str.charAt(i3)];
            if ((b | b2) < 0) {
                throw new IOException("invalid characters encountered in Hex string");
            }
            outputStream.write((b << 4) | b2);
            i2++;
            i = i4;
        }
        return i2;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int decode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        int i3 = i2 + i;
        while (i3 > i && ignore((char) bArr[i3 - 1])) {
            i3--;
        }
        int i4 = 0;
        while (i < i3) {
            while (i < i3 && ignore((char) bArr[i])) {
                i++;
            }
            int i5 = i + 1;
            byte b = this.decodingTable[bArr[i]];
            while (i5 < i3 && ignore((char) bArr[i5])) {
                i5++;
            }
            int i6 = i5 + 1;
            byte b2 = this.decodingTable[bArr[i5]];
            if ((b | b2) < 0) {
                throw new IOException("invalid characters encountered in Hex data");
            }
            outputStream.write((b << 4) | b2);
            i4++;
            i = i6;
        }
        return i4;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] decodeStrict(String str, int i, int i2) throws IOException {
        if (str == null) {
            throw new NullPointerException("'str' cannot be null");
        }
        if (i < 0 || i2 < 0 || i > str.length() - i2) {
            throw new IndexOutOfBoundsException("invalid offset and/or length specified");
        }
        if ((i2 & 1) != 0) {
            throw new IOException("a hexadecimal encoding must have an even number of characters");
        }
        int i3 = i2 >>> 1;
        byte[] bArr = new byte[i3];
        int i4 = 0;
        while (i4 < i3) {
            int i5 = i + 1;
            int i6 = i5 + 1;
            int i7 = (this.decodingTable[str.charAt(i)] << 4) | this.decodingTable[str.charAt(i5)];
            if (i7 < 0) {
                throw new IOException("invalid characters encountered in Hex string");
            }
            bArr[i4] = (byte) i7;
            i4++;
            i = i6;
        }
        return bArr;
    }

    @Override // org.bouncycastle.util.encoders.Encoder
    public int encode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        for (int i3 = i; i3 < i + i2; i3++) {
            int i4 = bArr[i3] & 255;
            outputStream.write(this.encodingTable[i4 >>> 4]);
            outputStream.write(this.encodingTable[i4 & 15]);
        }
        return i2 * 2;
    }

    protected void initialiseDecodingTable() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.decodingTable;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        while (true) {
            byte[] bArr2 = this.encodingTable;
            if (i >= bArr2.length) {
                byte[] bArr3 = this.decodingTable;
                bArr3[65] = bArr3[97];
                bArr3[66] = bArr3[98];
                bArr3[67] = bArr3[99];
                bArr3[68] = bArr3[100];
                bArr3[69] = bArr3[101];
                bArr3[70] = bArr3[102];
                return;
            }
            this.decodingTable[bArr2[i]] = (byte) i;
            i++;
        }
    }
}
