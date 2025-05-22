package com.loc;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* compiled from: StrictLineReader.java */
/* renamed from: com.loc.bg */
/* loaded from: classes4.dex */
public final class C3832bg implements Closeable {

    /* renamed from: a */
    private final InputStream f3670a;

    /* renamed from: b */
    private final Charset f3671b;

    /* renamed from: c */
    private byte[] f3672c;

    /* renamed from: d */
    private int f3673d;

    /* renamed from: e */
    private int f3674e;

    public C3832bg(InputStream inputStream, Charset charset) {
        this(inputStream, charset, (byte) 0);
    }

    private C3832bg(InputStream inputStream, Charset charset, byte b) {
        if (inputStream == null || charset == null) {
            throw new NullPointerException();
        }
        if (!charset.equals(C3833bh.f3676a)) {
            throw new IllegalArgumentException("Unsupported encoding");
        }
        this.f3670a = inputStream;
        this.f3671b = charset;
        this.f3672c = new byte[8192];
    }

    /* renamed from: b */
    private void m2596b() throws IOException {
        InputStream inputStream = this.f3670a;
        byte[] bArr = this.f3672c;
        int read = inputStream.read(bArr, 0, bArr.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.f3673d = 0;
        this.f3674e = read;
    }

    /* renamed from: a */
    public final String m2597a() throws IOException {
        int i;
        int i2;
        synchronized (this.f3670a) {
            if (this.f3672c == null) {
                throw new IOException("LineReader is closed");
            }
            if (this.f3673d >= this.f3674e) {
                m2596b();
            }
            for (int i3 = this.f3673d; i3 != this.f3674e; i3++) {
                if (this.f3672c[i3] == 10) {
                    if (i3 != this.f3673d) {
                        i2 = i3 - 1;
                        if (this.f3672c[i2] == 13) {
                            String str = new String(this.f3672c, this.f3673d, i2 - this.f3673d, this.f3671b.name());
                            this.f3673d = i3 + 1;
                            return str;
                        }
                    }
                    i2 = i3;
                    String str2 = new String(this.f3672c, this.f3673d, i2 - this.f3673d, this.f3671b.name());
                    this.f3673d = i3 + 1;
                    return str2;
                }
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((this.f3674e - this.f3673d) + 80) { // from class: com.loc.bg.1
                @Override // java.io.ByteArrayOutputStream
                public final String toString() {
                    try {
                        return new String(this.buf, 0, (this.count <= 0 || this.buf[this.count + (-1)] != 13) ? this.count : this.count - 1, C3832bg.this.f3671b.name());
                    } catch (UnsupportedEncodingException e) {
                        throw new AssertionError(e);
                    }
                }
            };
            loop1: while (true) {
                byteArrayOutputStream.write(this.f3672c, this.f3673d, this.f3674e - this.f3673d);
                this.f3674e = -1;
                m2596b();
                i = this.f3673d;
                while (i != this.f3674e) {
                    if (this.f3672c[i] == 10) {
                        break loop1;
                    }
                    i++;
                }
            }
            if (i != this.f3673d) {
                byteArrayOutputStream.write(this.f3672c, this.f3673d, i - this.f3673d);
            }
            this.f3673d = i + 1;
            return byteArrayOutputStream.toString();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        synchronized (this.f3670a) {
            if (this.f3672c != null) {
                this.f3672c = null;
                this.f3670a.close();
            }
        }
    }
}
