package org.nanohttpd.protocols.http.response;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class ChunkedOutputStream extends FilterOutputStream {
    public ChunkedOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i}, 0, 1);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return;
        }
        this.out.write(String.format("%x\r\n", Integer.valueOf(i2)).getBytes());
        this.out.write(bArr, i, i2);
        this.out.write(IOUtils.LINE_SEPARATOR_WINDOWS.getBytes());
    }

    public void finish() throws IOException {
        this.out.write("0\r\n\r\n".getBytes());
    }
}
