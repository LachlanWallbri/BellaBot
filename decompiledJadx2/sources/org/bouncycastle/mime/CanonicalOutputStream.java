package org.bouncycastle.mime;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.bouncycastle.mime.smime.SMimeParserContext;

/* loaded from: classes9.dex */
public class CanonicalOutputStream extends FilterOutputStream {
    protected static byte[] newline = new byte[2];
    private final boolean is7Bit;
    protected int lastb;

    static {
        byte[] bArr = newline;
        bArr[0] = 13;
        bArr[1] = 10;
    }

    public CanonicalOutputStream(SMimeParserContext sMimeParserContext, Headers headers, OutputStream outputStream) {
        super(outputStream);
        this.lastb = -1;
        this.is7Bit = headers.getContentType() != null ? (headers.getContentType() == null || headers.getContentType().equals("binary")) ? false : true : sMimeParserContext.getDefaultContentTransferEncoding().equals("7bit");
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        if (this.is7Bit) {
            if (i == 13) {
                this.out.write(newline);
            } else if (i == 10) {
                if (this.lastb != 13) {
                    this.out.write(newline);
                }
            }
            this.lastb = i;
        }
        this.out.write(i);
        this.lastb = i;
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        for (int i3 = i; i3 != i + i2; i3++) {
            write(bArr[i3]);
        }
    }

    public void writeln() throws IOException {
        ((FilterOutputStream) this).out.write(newline);
    }
}
