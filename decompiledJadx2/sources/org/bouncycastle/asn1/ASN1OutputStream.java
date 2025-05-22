package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

/* loaded from: classes9.dex */
public class ASN1OutputStream {

    /* renamed from: os */
    private OutputStream f9017os;

    public ASN1OutputStream(OutputStream outputStream) {
        this.f9017os = outputStream;
    }

    public static ASN1OutputStream create(OutputStream outputStream) {
        return new ASN1OutputStream(outputStream);
    }

    public static ASN1OutputStream create(OutputStream outputStream, String str) {
        return str.equals(ASN1Encoding.DER) ? new DEROutputStream(outputStream) : str.equals(ASN1Encoding.f9016DL) ? new DLOutputStream(outputStream) : new ASN1OutputStream(outputStream);
    }

    public void close() throws IOException {
        this.f9017os.close();
    }

    public void flush() throws IOException {
        this.f9017os.flush();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void flushInternal() throws IOException {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DEROutputStream getDERSubStream() {
        return new DEROutputStream(this.f9017os);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1OutputStream getDLSubStream() {
        return new DLOutputStream(this.f9017os);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void write(int i) throws IOException {
        this.f9017os.write(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        this.f9017os.write(bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeElements(Enumeration enumeration) throws IOException {
        while (enumeration.hasMoreElements()) {
            writePrimitive(((ASN1Encodable) enumeration.nextElement()).toASN1Primitive(), true);
        }
    }

    final void writeElements(ASN1Encodable[] aSN1EncodableArr) throws IOException {
        for (ASN1Encodable aSN1Encodable : aSN1EncodableArr) {
            writePrimitive(aSN1Encodable.toASN1Primitive(), true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeEncoded(boolean z, int i, byte b) throws IOException {
        if (z) {
            write(i);
        }
        writeLength(1);
        write(b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeEncoded(boolean z, int i, byte b, byte[] bArr) throws IOException {
        if (z) {
            write(i);
        }
        writeLength(bArr.length + 1);
        write(b);
        write(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeEncoded(boolean z, int i, byte b, byte[] bArr, int i2, int i3, byte b2) throws IOException {
        if (z) {
            write(i);
        }
        writeLength(i3 + 2);
        write(b);
        write(bArr, i2, i3);
        write(b2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeEncoded(boolean z, int i, int i2, byte[] bArr) throws IOException {
        writeTag(z, i, i2);
        writeLength(bArr.length);
        write(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeEncoded(boolean z, int i, byte[] bArr) throws IOException {
        if (z) {
            write(i);
        }
        writeLength(bArr.length);
        write(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeEncoded(boolean z, int i, byte[] bArr, int i2, int i3) throws IOException {
        if (z) {
            write(i);
        }
        writeLength(i3);
        write(bArr, i2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeEncodedIndef(boolean z, int i, int i2, byte[] bArr) throws IOException {
        writeTag(z, i, i2);
        write(128);
        write(bArr, 0, bArr.length);
        write(0);
        write(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeEncodedIndef(boolean z, int i, Enumeration enumeration) throws IOException {
        if (z) {
            write(i);
        }
        write(128);
        writeElements(enumeration);
        write(0);
        write(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeEncodedIndef(boolean z, int i, ASN1Encodable[] aSN1EncodableArr) throws IOException {
        if (z) {
            write(i);
        }
        write(128);
        writeElements(aSN1EncodableArr);
        write(0);
        write(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeLength(int i) throws IOException {
        if (i <= 127) {
            write((byte) i);
            return;
        }
        int i2 = i;
        int i3 = 1;
        while (true) {
            i2 >>>= 8;
            if (i2 == 0) {
                break;
            } else {
                i3++;
            }
        }
        write((byte) (i3 | 128));
        for (int i4 = (i3 - 1) * 8; i4 >= 0; i4 -= 8) {
            write((byte) (i >> i4));
        }
    }

    protected void writeNull() throws IOException {
        write(5);
        write(0);
    }

    public void writeObject(ASN1Encodable aSN1Encodable) throws IOException {
        if (aSN1Encodable == null) {
            throw new IOException("null object detected");
        }
        writePrimitive(aSN1Encodable.toASN1Primitive(), true);
        flushInternal();
    }

    public void writeObject(ASN1Primitive aSN1Primitive) throws IOException {
        if (aSN1Primitive == null) {
            throw new IOException("null object detected");
        }
        writePrimitive(aSN1Primitive, true);
        flushInternal();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writePrimitive(ASN1Primitive aSN1Primitive, boolean z) throws IOException {
        aSN1Primitive.encode(this, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void writeTag(boolean z, int i, int i2) throws IOException {
        if (z) {
            if (i2 < 31) {
                write(i | i2);
                return;
            }
            write(31 | i);
            if (i2 < 128) {
                write(i2);
                return;
            }
            byte[] bArr = new byte[5];
            int length = bArr.length - 1;
            bArr[length] = (byte) (i2 & 127);
            do {
                i2 >>= 7;
                length--;
                bArr[length] = (byte) ((i2 & 127) | 128);
            } while (i2 > 127);
            write(bArr, length, bArr.length - length);
        }
    }
}
