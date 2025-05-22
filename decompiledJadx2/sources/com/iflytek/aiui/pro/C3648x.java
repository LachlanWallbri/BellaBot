package com.iflytek.aiui.pro;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* renamed from: com.iflytek.aiui.pro.x */
/* loaded from: classes.dex */
public class C3648x implements Closeable {

    /* renamed from: a */
    private final InputStream f2703a;

    /* renamed from: b */
    private final Charset f2704b;

    /* renamed from: c */
    private byte[] f2705c;

    /* renamed from: d */
    private int f2706d;

    /* renamed from: e */
    private int f2707e;

    /* renamed from: com.iflytek.aiui.pro.x$a */
    /* loaded from: classes4.dex */
    class a extends ByteArrayOutputStream {
        a(int i) {
            super(i);
        }

        @Override // java.io.ByteArrayOutputStream
        public String toString() {
            int i = ((ByteArrayOutputStream) this).count;
            if (i > 0) {
                int i2 = i - 1;
                if (((ByteArrayOutputStream) this).buf[i2] == 13) {
                    i = i2;
                }
            }
            try {
                return new String(((ByteArrayOutputStream) this).buf, 0, i, C3648x.b(C3648x.this).name());
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }
    }

    public C3648x(InputStream inputStream, int i, Charset charset) {
        if (inputStream == null || charset == null) {
            throw null;
        }
        if (i < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        }
        if (!charset.equals(C3650y.f2726a)) {
            throw new IllegalArgumentException("Unsupported encoding");
        }
        this.f2703a = inputStream;
        this.f2704b = charset;
        this.f2705c = new byte[i];
    }

    public C3648x(InputStream inputStream, Charset charset) {
        this(inputStream, 8192, charset);
    }

    /* renamed from: b */
    private void m1608b() throws IOException {
        InputStream inputStream = this.f2703a;
        byte[] bArr = this.f2705c;
        int read = inputStream.read(bArr, 0, bArr.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.f2706d = 0;
        this.f2707e = read;
    }

    /* renamed from: a */
    public String m1609a() throws IOException {
        int i;
        int i2;
        synchronized (this.f2703a) {
            if (this.f2705c == null) {
                throw new IOException("LineReader is closed");
            }
            if (this.f2706d >= this.f2707e) {
                m1608b();
            }
            for (int i3 = this.f2706d; i3 != this.f2707e; i3++) {
                if (this.f2705c[i3] == 10) {
                    if (i3 != this.f2706d) {
                        i2 = i3 - 1;
                        if (this.f2705c[i2] == 13) {
                            String str = new String(this.f2705c, this.f2706d, i2 - this.f2706d, this.f2704b.name());
                            this.f2706d = i3 + 1;
                            return str;
                        }
                    }
                    i2 = i3;
                    String str2 = new String(this.f2705c, this.f2706d, i2 - this.f2706d, this.f2704b.name());
                    this.f2706d = i3 + 1;
                    return str2;
                }
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((this.f2707e - this.f2706d) + 80) { // from class: com.iflytek.aiui.pro.x.1
                @Override // java.io.ByteArrayOutputStream
                public String toString() {
                    try {
                        return new String(this.buf, 0, (this.count <= 0 || this.buf[this.count + (-1)] != 13) ? this.count : this.count - 1, C3648x.this.f2704b.name());
                    } catch (UnsupportedEncodingException e) {
                        throw new AssertionError(e);
                    }
                }
            };
            loop1: while (true) {
                byteArrayOutputStream.write(this.f2705c, this.f2706d, this.f2707e - this.f2706d);
                this.f2707e = -1;
                m1608b();
                i = this.f2706d;
                while (i != this.f2707e) {
                    if (this.f2705c[i] == 10) {
                        break loop1;
                    }
                    i++;
                }
            }
            if (i != this.f2706d) {
                byteArrayOutputStream.write(this.f2705c, this.f2706d, i - this.f2706d);
            }
            this.f2706d = i + 1;
            return byteArrayOutputStream.toString();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this.f2703a) {
            if (this.f2705c != null) {
                this.f2705c = null;
                this.f2703a.close();
            }
        }
    }
}
