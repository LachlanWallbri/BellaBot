package org.yaml.snakeyaml.reader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class UnicodeReader extends Reader {
    private static final int BOM_SIZE = 3;
    PushbackInputStream internalIn;
    InputStreamReader internalIn2 = null;
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final Charset UTF16BE = Charset.forName("UTF-16BE");
    private static final Charset UTF16LE = Charset.forName("UTF-16LE");

    public UnicodeReader(InputStream inputStream) {
        this.internalIn = new PushbackInputStream(inputStream, 3);
    }

    public String getEncoding() {
        return this.internalIn2.getEncoding();
    }

    protected void init() throws IOException {
        Charset charset;
        int i;
        if (this.internalIn2 != null) {
            return;
        }
        byte[] bArr = new byte[3];
        int read = this.internalIn.read(bArr, 0, bArr.length);
        if (bArr[0] == -17 && bArr[1] == -69 && bArr[2] == -65) {
            charset = UTF8;
            i = read - 3;
        } else {
            if (bArr[0] == -2 && bArr[1] == -1) {
                charset = UTF16BE;
            } else if (bArr[0] == -1 && bArr[1] == -2) {
                charset = UTF16LE;
            } else {
                charset = UTF8;
                i = read;
            }
            i = read - 2;
        }
        if (i > 0) {
            this.internalIn.unread(bArr, read - i, i);
        }
        this.internalIn2 = new InputStreamReader(this.internalIn, charset.newDecoder().onUnmappableCharacter(CodingErrorAction.REPORT));
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        init();
        this.internalIn2.close();
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        init();
        return this.internalIn2.read(cArr, i, i2);
    }
}
