package com.aliyun.alink.linksdk.tmp.disklrucache;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
class StrictLineReader implements Closeable {

    /* renamed from: CR */
    private static final byte f1036CR = 13;

    /* renamed from: LF */
    private static final byte f1037LF = 10;
    private byte[] buf;
    private final Charset charset;
    private int end;

    /* renamed from: in */
    private final InputStream f1038in;
    private int pos;

    public StrictLineReader(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    public StrictLineReader(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw null;
        }
        if (i < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        }
        if (!charset.equals(Util.US_ASCII)) {
            throw new IllegalArgumentException("Unsupported encoding");
        }
        this.f1038in = inputStream;
        this.charset = charset;
        this.buf = new byte[i];
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.f1038in) {
            if (this.buf != null) {
                this.buf = null;
                this.f1038in.close();
            }
        }
    }

    public String readLine() throws IOException {
        int i;
        int i2;
        synchronized (this.f1038in) {
            if (this.buf == null) {
                throw new IOException("LineReader is closed");
            }
            if (this.pos >= this.end) {
                fillBuf();
            }
            for (int i3 = this.pos; i3 != this.end; i3++) {
                if (this.buf[i3] == 10) {
                    if (i3 != this.pos) {
                        i2 = i3 - 1;
                        if (this.buf[i2] == 13) {
                            String str = new String(this.buf, this.pos, i2 - this.pos, this.charset.name());
                            this.pos = i3 + 1;
                            return str;
                        }
                    }
                    i2 = i3;
                    String str2 = new String(this.buf, this.pos, i2 - this.pos, this.charset.name());
                    this.pos = i3 + 1;
                    return str2;
                }
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((this.end - this.pos) + 80) { // from class: com.aliyun.alink.linksdk.tmp.disklrucache.StrictLineReader.1
                @Override // java.io.ByteArrayOutputStream
                public String toString() {
                    try {
                        return new String(this.buf, 0, (this.count <= 0 || this.buf[this.count + (-1)] != 13) ? this.count : this.count - 1, StrictLineReader.this.charset.name());
                    } catch (UnsupportedEncodingException e) {
                        throw new AssertionError(e);
                    }
                }
            };
            loop1: while (true) {
                byteArrayOutputStream.write(this.buf, this.pos, this.end - this.pos);
                this.end = -1;
                fillBuf();
                i = this.pos;
                while (i != this.end) {
                    if (this.buf[i] == 10) {
                        break loop1;
                    }
                    i++;
                }
            }
            if (i != this.pos) {
                byteArrayOutputStream.write(this.buf, this.pos, i - this.pos);
            }
            this.pos = i + 1;
            return byteArrayOutputStream.toString();
        }
    }

    private void fillBuf() throws IOException {
        InputStream inputStream = this.f1038in;
        byte[] bArr = this.buf;
        int read = inputStream.read(bArr, 0, bArr.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.pos = 0;
        this.end = read;
    }
}
