package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.base.GeneratorBase;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public class UTF32Reader extends Reader {
    protected static final int LAST_VALID_UNICODE_CHAR = 1114111;

    /* renamed from: NC */
    protected static final char f1257NC = 0;
    protected final boolean _bigEndian;
    protected byte[] _buffer;
    protected int _byteCount;
    protected int _charCount;
    protected final IOContext _context;
    protected InputStream _in;
    protected int _length;
    protected final boolean _managedBuffers;
    protected int _ptr;
    protected char _surrogate = 0;
    protected char[] _tmpBuf;

    public UTF32Reader(IOContext iOContext, InputStream inputStream, byte[] bArr, int i, int i2, boolean z) {
        this._context = iOContext;
        this._in = inputStream;
        this._buffer = bArr;
        this._ptr = i;
        this._length = i2;
        this._bigEndian = z;
        this._managedBuffers = inputStream != null;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        InputStream inputStream = this._in;
        if (inputStream != null) {
            this._in = null;
            freeBuffers();
            inputStream.close();
        }
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        if (this._tmpBuf == null) {
            this._tmpBuf = new char[1];
        }
        if (read(this._tmpBuf, 0, 1) < 1) {
            return -1;
        }
        return this._tmpBuf[0];
    }

    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int i3;
        int i4;
        int i5;
        int i6;
        if (this._buffer == null) {
            return -1;
        }
        if (i2 < 1) {
            return i2;
        }
        if (i < 0 || i + i2 > cArr.length) {
            reportBounds(cArr, i, i2);
        }
        int i7 = i2 + i;
        char c = this._surrogate;
        if (c != 0) {
            i3 = i + 1;
            cArr[i] = c;
            this._surrogate = (char) 0;
        } else {
            int i8 = this._length - this._ptr;
            if (i8 < 4 && !loadMore(i8)) {
                if (i8 == 0) {
                    return -1;
                }
                reportUnexpectedEOF(this._length - this._ptr, 4);
            }
            i3 = i;
        }
        int i9 = this._length - 4;
        while (true) {
            if (i3 >= i7) {
                i4 = i3;
                break;
            }
            int i10 = this._ptr;
            if (this._bigEndian) {
                byte[] bArr = this._buffer;
                int i11 = (bArr[i10] << 8) | (bArr[i10 + 1] & 255);
                i5 = (bArr[i10 + 3] & 255) | ((bArr[i10 + 2] & 255) << 8);
                i6 = i11;
            } else {
                byte[] bArr2 = this._buffer;
                i5 = (bArr2[i10] & 255) | ((bArr2[i10 + 1] & 255) << 8);
                i6 = (bArr2[i10 + 3] << 8) | (bArr2[i10 + 2] & 255);
            }
            this._ptr += 4;
            if (i6 != 0) {
                int i12 = i6 & 65535;
                int i13 = ((i12 - 1) << 16) | i5;
                if (i12 > 16) {
                    reportInvalid(i13, i3 - i, String.format(" (above 0x%08x)", 1114111));
                }
                i4 = i3 + 1;
                cArr[i3] = (char) ((i13 >> 10) + GeneratorBase.SURR1_FIRST);
                i5 = (i13 & 1023) | GeneratorBase.SURR2_FIRST;
                if (i4 >= i7) {
                    this._surrogate = (char) i13;
                    break;
                }
                i3 = i4;
            }
            i4 = i3 + 1;
            cArr[i3] = (char) i5;
            if (this._ptr > i9) {
                break;
            }
            i3 = i4;
        }
        int i14 = i4 - i;
        this._charCount += i14;
        return i14;
    }

    private void reportUnexpectedEOF(int i, int i2) throws IOException {
        int i3 = this._byteCount + i;
        throw new CharConversionException("Unexpected EOF in the middle of a 4-byte UTF-32 char: got " + i + ", needed " + i2 + ", at char #" + this._charCount + ", byte #" + i3 + ")");
    }

    private void reportInvalid(int i, int i2, String str) throws IOException {
        int i3 = (this._byteCount + this._ptr) - 1;
        throw new CharConversionException("Invalid UTF-32 character 0x" + Integer.toHexString(i) + str + " at char #" + (this._charCount + i2) + ", byte #" + i3 + ")");
    }

    private boolean loadMore(int i) throws IOException {
        int read;
        this._byteCount += this._length - i;
        if (i > 0) {
            int i2 = this._ptr;
            if (i2 > 0) {
                byte[] bArr = this._buffer;
                System.arraycopy(bArr, i2, bArr, 0, i);
                this._ptr = 0;
            }
            this._length = i;
        } else {
            this._ptr = 0;
            InputStream inputStream = this._in;
            int read2 = inputStream == null ? -1 : inputStream.read(this._buffer);
            if (read2 < 1) {
                this._length = 0;
                if (read2 < 0) {
                    if (this._managedBuffers) {
                        freeBuffers();
                    }
                    return false;
                }
                reportStrangeStream();
            }
            this._length = read2;
        }
        while (true) {
            int i3 = this._length;
            if (i3 >= 4) {
                return true;
            }
            InputStream inputStream2 = this._in;
            if (inputStream2 == null) {
                read = -1;
            } else {
                byte[] bArr2 = this._buffer;
                read = inputStream2.read(bArr2, i3, bArr2.length - i3);
            }
            if (read < 1) {
                if (read < 0) {
                    if (this._managedBuffers) {
                        freeBuffers();
                    }
                    reportUnexpectedEOF(this._length, 4);
                }
                reportStrangeStream();
            }
            this._length += read;
        }
    }

    private void freeBuffers() {
        byte[] bArr = this._buffer;
        if (bArr != null) {
            this._buffer = null;
            this._context.releaseReadIOBuffer(bArr);
        }
    }

    private void reportBounds(char[] cArr, int i, int i2) throws IOException {
        throw new ArrayIndexOutOfBoundsException("read(buf," + i + "," + i2 + "), cbuf[" + cArr.length + "]");
    }

    private void reportStrangeStream() throws IOException {
        throw new IOException("Strange I/O stream, returned 0 bytes on read");
    }
}
