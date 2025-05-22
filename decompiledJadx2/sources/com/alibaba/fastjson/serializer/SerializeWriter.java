package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.IOUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.List;
import kotlin.text.Typography;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class SerializeWriter extends Writer {
    private static final ThreadLocal<char[]> bufLocal = new ThreadLocal<>();
    private static final ThreadLocal<byte[]> bytesBufLocal = new ThreadLocal<>();
    static final int nonDirectFeatures = ((((((((SerializerFeature.UseSingleQuotes.mask | 0) | SerializerFeature.BrowserCompatible.mask) | SerializerFeature.PrettyFormat.mask) | SerializerFeature.WriteEnumUsingToString.mask) | SerializerFeature.WriteNonStringValueAsString.mask) | SerializerFeature.WriteSlashAsSpecial.mask) | SerializerFeature.IgnoreErrorGetter.mask) | SerializerFeature.WriteClassName.mask) | SerializerFeature.NotWriteDefaultValue.mask;
    protected boolean beanToArray;
    protected boolean browserSecure;
    protected char[] buf;
    protected int count;
    protected boolean disableCircularReferenceDetect;
    protected int features;
    protected char keySeperator;
    protected int maxBufSize;
    protected boolean notWriteDefaultValue;
    protected boolean quoteFieldNames;
    protected long sepcialBits;
    protected boolean sortField;
    protected boolean useSingleQuotes;
    protected boolean writeDirect;
    protected boolean writeEnumUsingName;
    protected boolean writeEnumUsingToString;
    protected boolean writeNonStringValueAsString;
    private final Writer writer;

    public SerializeWriter() {
        this((Writer) null);
    }

    public SerializeWriter(Writer writer) {
        this(writer, JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.EMPTY);
    }

    public SerializeWriter(SerializerFeature... serializerFeatureArr) {
        this((Writer) null, serializerFeatureArr);
    }

    public SerializeWriter(Writer writer, SerializerFeature... serializerFeatureArr) {
        this(writer, 0, serializerFeatureArr);
    }

    public SerializeWriter(Writer writer, int i, SerializerFeature... serializerFeatureArr) {
        this.maxBufSize = -1;
        this.writer = writer;
        char[] cArr = bufLocal.get();
        this.buf = cArr;
        if (cArr != null) {
            bufLocal.set(null);
        } else {
            this.buf = new char[2048];
        }
        for (SerializerFeature serializerFeature : serializerFeatureArr) {
            i |= serializerFeature.getMask();
        }
        this.features = i;
        computeFeatures();
    }

    public int getMaxBufSize() {
        return this.maxBufSize;
    }

    public void setMaxBufSize(int i) {
        if (i < this.buf.length) {
            throw new JSONException("must > " + this.buf.length);
        }
        this.maxBufSize = i;
    }

    public int getBufferLength() {
        return this.buf.length;
    }

    public SerializeWriter(int i) {
        this((Writer) null, i);
    }

    public SerializeWriter(Writer writer, int i) {
        this.maxBufSize = -1;
        this.writer = writer;
        if (i <= 0) {
            throw new IllegalArgumentException("Negative initial size: " + i);
        }
        this.buf = new char[i];
        computeFeatures();
    }

    public void config(SerializerFeature serializerFeature, boolean z) {
        if (z) {
            this.features |= serializerFeature.getMask();
            if (serializerFeature == SerializerFeature.WriteEnumUsingToString) {
                this.features &= ~SerializerFeature.WriteEnumUsingName.getMask();
            } else if (serializerFeature == SerializerFeature.WriteEnumUsingName) {
                this.features &= ~SerializerFeature.WriteEnumUsingToString.getMask();
            }
        } else {
            this.features = (~serializerFeature.getMask()) & this.features;
        }
        computeFeatures();
    }

    protected void computeFeatures() {
        long j;
        this.quoteFieldNames = (this.features & SerializerFeature.QuoteFieldNames.mask) != 0;
        this.useSingleQuotes = (this.features & SerializerFeature.UseSingleQuotes.mask) != 0;
        this.sortField = (this.features & SerializerFeature.SortField.mask) != 0;
        this.disableCircularReferenceDetect = (this.features & SerializerFeature.DisableCircularReferenceDetect.mask) != 0;
        this.beanToArray = (this.features & SerializerFeature.BeanToArray.mask) != 0;
        this.writeNonStringValueAsString = (this.features & SerializerFeature.WriteNonStringValueAsString.mask) != 0;
        this.notWriteDefaultValue = (this.features & SerializerFeature.NotWriteDefaultValue.mask) != 0;
        this.writeEnumUsingName = (this.features & SerializerFeature.WriteEnumUsingName.mask) != 0;
        this.writeEnumUsingToString = (this.features & SerializerFeature.WriteEnumUsingToString.mask) != 0;
        this.writeDirect = this.quoteFieldNames && (this.features & nonDirectFeatures) == 0 && (this.beanToArray || this.writeEnumUsingName);
        this.keySeperator = this.useSingleQuotes ? '\'' : '\"';
        boolean z = (this.features & SerializerFeature.BrowserSecure.mask) != 0;
        this.browserSecure = z;
        if (z) {
            j = 5764610843043954687L;
        } else {
            j = (this.features & SerializerFeature.WriteSlashAsSpecial.mask) != 0 ? 140758963191807L : 21474836479L;
        }
        this.sepcialBits = j;
    }

    public boolean isSortField() {
        return this.sortField;
    }

    public boolean isNotWriteDefaultValue() {
        return this.notWriteDefaultValue;
    }

    public boolean isEnabled(SerializerFeature serializerFeature) {
        return (serializerFeature.mask & this.features) != 0;
    }

    public boolean isEnabled(int i) {
        return (i & this.features) != 0;
    }

    @Override // java.io.Writer
    public void write(int i) {
        int i2 = 1;
        int i3 = this.count + 1;
        if (i3 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i3);
            } else {
                flush();
                this.buf[this.count] = (char) i;
                this.count = i2;
            }
        }
        i2 = i3;
        this.buf[this.count] = (char) i;
        this.count = i2;
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        int i3;
        if (i < 0 || i > cArr.length || i2 < 0 || (i3 = i + i2) > cArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (i2 == 0) {
            return;
        }
        int i4 = this.count + i2;
        if (i4 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i4);
            }
            do {
                char[] cArr2 = this.buf;
                int length = cArr2.length;
                int i5 = this.count;
                int i6 = length - i5;
                System.arraycopy(cArr, i, cArr2, i5, i6);
                this.count = this.buf.length;
                flush();
                i2 -= i6;
                i += i6;
            } while (i2 > this.buf.length);
            i4 = i2;
        }
        System.arraycopy(cArr, i, this.buf, this.count, i2);
        this.count = i4;
    }

    public void expandCapacity(int i) {
        int i2 = this.maxBufSize;
        if (i2 != -1 && i >= i2) {
            throw new JSONException("serialize exceeded MAX_OUTPUT_LENGTH=" + this.maxBufSize + ", minimumCapacity=" + i);
        }
        char[] cArr = this.buf;
        int length = cArr.length + (cArr.length >> 1) + 1;
        if (length >= i) {
            i = length;
        }
        char[] cArr2 = new char[i];
        System.arraycopy(this.buf, 0, cArr2, 0, this.count);
        this.buf = cArr2;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public SerializeWriter append(CharSequence charSequence) {
        String charSequence2 = charSequence == null ? "null" : charSequence.toString();
        write(charSequence2, 0, charSequence2.length());
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public SerializeWriter append(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            charSequence = "null";
        }
        String charSequence2 = charSequence.subSequence(i, i2).toString();
        write(charSequence2, 0, charSequence2.length());
        return this;
    }

    @Override // java.io.Writer, java.lang.Appendable
    public SerializeWriter append(char c) {
        write(c);
        return this;
    }

    @Override // java.io.Writer
    public void write(String str, int i, int i2) {
        int i3;
        int i4 = this.count + i2;
        if (i4 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i4);
            } else {
                while (true) {
                    char[] cArr = this.buf;
                    int length = cArr.length;
                    int i5 = this.count;
                    int i6 = length - i5;
                    i3 = i + i6;
                    str.getChars(i, i3, cArr, i5);
                    this.count = this.buf.length;
                    flush();
                    i2 -= i6;
                    if (i2 <= this.buf.length) {
                        break;
                    } else {
                        i = i3;
                    }
                }
                i4 = i2;
                i = i3;
            }
        }
        str.getChars(i, i2 + i, this.buf, this.count);
        this.count = i4;
    }

    public void writeTo(Writer writer) throws IOException {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        writer.write(this.buf, 0, this.count);
    }

    public void writeTo(OutputStream outputStream, String str) throws IOException {
        writeTo(outputStream, Charset.forName(str));
    }

    public void writeTo(OutputStream outputStream, Charset charset) throws IOException {
        writeToEx(outputStream, charset);
    }

    public int writeToEx(OutputStream outputStream, Charset charset) throws IOException {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        if (charset == IOUtils.UTF8) {
            return encodeToUTF8(outputStream);
        }
        byte[] bytes = new String(this.buf, 0, this.count).getBytes(charset);
        outputStream.write(bytes);
        return bytes.length;
    }

    public char[] toCharArray() {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        int i = this.count;
        char[] cArr = new char[i];
        System.arraycopy(this.buf, 0, cArr, 0, i);
        return cArr;
    }

    public char[] toCharArrayForSpringWebSocket() {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        int i = this.count;
        char[] cArr = new char[i - 2];
        System.arraycopy(this.buf, 1, cArr, 0, i - 2);
        return cArr;
    }

    public byte[] toBytes(String str) {
        Charset charset;
        if (str == null || "UTF-8".equals(str)) {
            charset = IOUtils.UTF8;
        } else {
            charset = Charset.forName(str);
        }
        return toBytes(charset);
    }

    public byte[] toBytes(Charset charset) {
        if (this.writer != null) {
            throw new UnsupportedOperationException("writer not null");
        }
        if (charset == IOUtils.UTF8) {
            return encodeToUTF8Bytes();
        }
        return new String(this.buf, 0, this.count).getBytes(charset);
    }

    private int encodeToUTF8(OutputStream outputStream) throws IOException {
        int i = (int) (this.count * 3.0d);
        byte[] bArr = bytesBufLocal.get();
        if (bArr == null) {
            bArr = new byte[8192];
            bytesBufLocal.set(bArr);
        }
        if (bArr.length < i) {
            bArr = new byte[i];
        }
        int encodeUTF8 = IOUtils.encodeUTF8(this.buf, 0, this.count, bArr);
        outputStream.write(bArr, 0, encodeUTF8);
        return encodeUTF8;
    }

    private byte[] encodeToUTF8Bytes() {
        int i = (int) (this.count * 3.0d);
        byte[] bArr = bytesBufLocal.get();
        if (bArr == null) {
            bArr = new byte[8192];
            bytesBufLocal.set(bArr);
        }
        if (bArr.length < i) {
            bArr = new byte[i];
        }
        int encodeUTF8 = IOUtils.encodeUTF8(this.buf, 0, this.count, bArr);
        byte[] bArr2 = new byte[encodeUTF8];
        System.arraycopy(bArr, 0, bArr2, 0, encodeUTF8);
        return bArr2;
    }

    public int size() {
        return this.count;
    }

    public String toString() {
        return new String(this.buf, 0, this.count);
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.writer != null && this.count > 0) {
            flush();
        }
        char[] cArr = this.buf;
        if (cArr.length <= 131072) {
            bufLocal.set(cArr);
        }
        this.buf = null;
    }

    @Override // java.io.Writer
    public void write(String str) {
        if (str == null) {
            writeNull();
        } else {
            write(str, 0, str.length());
        }
    }

    public void writeInt(int i) {
        if (i == Integer.MIN_VALUE) {
            write("-2147483648");
            return;
        }
        int stringSize = i < 0 ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);
        int i2 = this.count + stringSize;
        if (i2 > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i2);
            } else {
                char[] cArr = new char[stringSize];
                IOUtils.getChars(i, stringSize, cArr);
                write(cArr, 0, stringSize);
                return;
            }
        }
        IOUtils.getChars(i, i2, this.buf);
        this.count = i2;
    }

    public void writeByteArray(byte[] bArr) {
        if (isEnabled(SerializerFeature.WriteClassName.mask)) {
            writeHex(bArr);
            return;
        }
        int length = bArr.length;
        char c = this.useSingleQuotes ? '\'' : '\"';
        if (length == 0) {
            write(this.useSingleQuotes ? "''" : "\"\"");
            return;
        }
        char[] cArr = IOUtils.f317CA;
        int i = (length / 3) * 3;
        int i2 = length - 1;
        int i3 = this.count;
        int i4 = (((i2 / 3) + 1) << 2) + i3 + 2;
        if (i4 > this.buf.length) {
            if (this.writer != null) {
                write(c);
                int i5 = 0;
                while (i5 < i) {
                    int i6 = i5 + 1;
                    int i7 = i6 + 1;
                    int i8 = ((bArr[i5] & 255) << 16) | ((bArr[i6] & 255) << 8) | (bArr[i7] & 255);
                    write(cArr[(i8 >>> 18) & 63]);
                    write(cArr[(i8 >>> 12) & 63]);
                    write(cArr[(i8 >>> 6) & 63]);
                    write(cArr[i8 & 63]);
                    i5 = i7 + 1;
                }
                int i9 = length - i;
                if (i9 > 0) {
                    int i10 = ((bArr[i] & 255) << 10) | (i9 == 2 ? (bArr[i2] & 255) << 2 : 0);
                    write(cArr[i10 >> 12]);
                    write(cArr[(i10 >>> 6) & 63]);
                    write(i9 == 2 ? cArr[i10 & 63] : '=');
                    write(61);
                }
                write(c);
                return;
            }
            expandCapacity(i4);
        }
        this.count = i4;
        int i11 = i3 + 1;
        this.buf[i3] = c;
        int i12 = 0;
        while (i12 < i) {
            int i13 = i12 + 1;
            int i14 = i13 + 1;
            int i15 = ((bArr[i12] & 255) << 16) | ((bArr[i13] & 255) << 8);
            int i16 = i14 + 1;
            int i17 = i15 | (bArr[i14] & 255);
            char[] cArr2 = this.buf;
            int i18 = i11 + 1;
            cArr2[i11] = cArr[(i17 >>> 18) & 63];
            int i19 = i18 + 1;
            cArr2[i18] = cArr[(i17 >>> 12) & 63];
            int i20 = i19 + 1;
            cArr2[i19] = cArr[(i17 >>> 6) & 63];
            i11 = i20 + 1;
            cArr2[i20] = cArr[i17 & 63];
            i12 = i16;
        }
        int i21 = length - i;
        if (i21 > 0) {
            int i22 = ((bArr[i] & 255) << 10) | (i21 == 2 ? (bArr[i2] & 255) << 2 : 0);
            char[] cArr3 = this.buf;
            cArr3[i4 - 5] = cArr[i22 >> 12];
            cArr3[i4 - 4] = cArr[(i22 >>> 6) & 63];
            cArr3[i4 - 3] = i21 == 2 ? cArr[i22 & 63] : '=';
            this.buf[i4 - 2] = '=';
        }
        this.buf[i4 - 1] = c;
    }

    public void writeHex(byte[] bArr) {
        int i = 2;
        int length = this.count + (bArr.length * 2) + 3;
        int i2 = 0;
        if (length > this.buf.length) {
            if (this.writer != null) {
                char[] cArr = new char[bArr.length + 3];
                cArr[0] = 'x';
                cArr[1] = '\'';
                while (i2 < bArr.length) {
                    int i3 = bArr[i2] & 255;
                    int i4 = i3 >> 4;
                    int i5 = i3 & 15;
                    int i6 = i + 1;
                    cArr[i] = (char) (i4 + (i4 < 10 ? 48 : 55));
                    i = i6 + 1;
                    cArr[i6] = (char) (i5 + (i5 < 10 ? 48 : 55));
                    i2++;
                }
                cArr[i] = '\'';
                try {
                    this.writer.write(cArr);
                    return;
                } catch (IOException e) {
                    throw new JSONException("writeBytes error.", e);
                }
            }
            expandCapacity(length);
        }
        char[] cArr2 = this.buf;
        int i7 = this.count;
        int i8 = i7 + 1;
        this.count = i8;
        cArr2[i7] = 'x';
        this.count = i8 + 1;
        cArr2[i8] = '\'';
        while (i2 < bArr.length) {
            int i9 = bArr[i2] & 255;
            int i10 = i9 >> 4;
            int i11 = i9 & 15;
            char[] cArr3 = this.buf;
            int i12 = this.count;
            this.count = i12 + 1;
            cArr3[i12] = (char) (i10 + (i10 < 10 ? 48 : 55));
            char[] cArr4 = this.buf;
            int i13 = this.count;
            this.count = i13 + 1;
            cArr4[i13] = (char) (i11 + (i11 < 10 ? 48 : 55));
            i2++;
        }
        char[] cArr5 = this.buf;
        int i14 = this.count;
        this.count = i14 + 1;
        cArr5[i14] = '\'';
    }

    public void writeFloat(float f, boolean z) {
        if (Float.isNaN(f) || Float.isInfinite(f)) {
            writeNull();
            return;
        }
        String f2 = Float.toString(f);
        if (isEnabled(SerializerFeature.WriteNullNumberAsZero) && f2.endsWith(".0")) {
            f2 = f2.substring(0, f2.length() - 2);
        }
        write(f2);
        if (z && isEnabled(SerializerFeature.WriteClassName)) {
            write(70);
        }
    }

    public void writeDouble(double d, boolean z) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            writeNull();
            return;
        }
        String d2 = Double.toString(d);
        if (isEnabled(SerializerFeature.WriteNullNumberAsZero) && d2.endsWith(".0")) {
            d2 = d2.substring(0, d2.length() - 2);
        }
        write(d2);
        if (z && isEnabled(SerializerFeature.WriteClassName)) {
            write(68);
        }
    }

    public void writeEnum(Enum<?> r3) {
        if (r3 == null) {
            writeNull();
            return;
        }
        String str = null;
        if (this.writeEnumUsingName && !this.writeEnumUsingToString) {
            str = r3.name();
        } else if (this.writeEnumUsingToString) {
            str = r3.toString();
        }
        if (str != null) {
            int i = isEnabled(SerializerFeature.UseSingleQuotes) ? 39 : 34;
            write(i);
            write(str);
            write(i);
            return;
        }
        writeInt(r3.ordinal());
    }

    public void writeLong(long j) {
        boolean z = isEnabled(SerializerFeature.BrowserCompatible) && !isEnabled(SerializerFeature.WriteClassName) && (j > 9007199254740991L || j < -9007199254740991L);
        if (j == Long.MIN_VALUE) {
            if (z) {
                write("\"-9223372036854775808\"");
                return;
            } else {
                write("-9223372036854775808");
                return;
            }
        }
        int stringSize = j < 0 ? IOUtils.stringSize(-j) + 1 : IOUtils.stringSize(j);
        int i = this.count + stringSize;
        if (z) {
            i += 2;
        }
        if (i > this.buf.length) {
            if (this.writer == null) {
                expandCapacity(i);
            } else {
                char[] cArr = new char[stringSize];
                IOUtils.getChars(j, stringSize, cArr);
                if (z) {
                    write(34);
                    write(cArr, 0, stringSize);
                    write(34);
                    return;
                }
                write(cArr, 0, stringSize);
                return;
            }
        }
        if (z) {
            char[] cArr2 = this.buf;
            cArr2[this.count] = '\"';
            int i2 = i - 1;
            IOUtils.getChars(j, i2, cArr2);
            this.buf[i2] = '\"';
        } else {
            IOUtils.getChars(j, i, this.buf);
        }
        this.count = i;
    }

    public void writeNull() {
        write("null");
    }

    public void writeNull(SerializerFeature serializerFeature) {
        writeNull(0, serializerFeature.mask);
    }

    public void writeNull(int i, int i2) {
        if ((i & i2) == 0 && (this.features & i2) == 0) {
            writeNull();
            return;
        }
        if (i2 == SerializerFeature.WriteNullListAsEmpty.mask) {
            write("[]");
            return;
        }
        if (i2 == SerializerFeature.WriteNullStringAsEmpty.mask) {
            writeString("");
            return;
        }
        if (i2 == SerializerFeature.WriteNullBooleanAsFalse.mask) {
            write("false");
        } else if (i2 == SerializerFeature.WriteNullNumberAsZero.mask) {
            write(48);
        } else {
            writeNull();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:228:0x047b, code lost:
    
        if (r4 != '>') goto L218;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void writeStringWithDoubleQuote(String str, char c) {
        int i;
        if (str == null) {
            writeNull();
            if (c != 0) {
                write(c);
                return;
            }
            return;
        }
        int length = str.length();
        int i2 = this.count + length + 2;
        if (c != 0) {
            i2++;
        }
        int length2 = this.buf.length;
        char c2 = Typography.greater;
        if (i2 > length2) {
            if (this.writer != null) {
                write(34);
                int i3 = 0;
                while (i3 < str.length()) {
                    char charAt = str.charAt(i3);
                    if (isEnabled(SerializerFeature.BrowserSecure) && (charAt == '(' || charAt == ')' || charAt == '<' || charAt == c2)) {
                        write(92);
                        write(117);
                        write(IOUtils.DIGITS[(charAt >>> '\f') & 15]);
                        write(IOUtils.DIGITS[(charAt >>> '\b') & 15]);
                        write(IOUtils.DIGITS[(charAt >>> 4) & 15]);
                        write(IOUtils.DIGITS[charAt & 15]);
                    } else if (!isEnabled(SerializerFeature.BrowserCompatible)) {
                        if ((charAt < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[charAt] != 0) || (charAt == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                            write(92);
                            if (IOUtils.specicalFlags_doubleQuotes[charAt] == 4) {
                                write(117);
                                write(IOUtils.DIGITS[(charAt >>> '\f') & 15]);
                                write(IOUtils.DIGITS[(charAt >>> '\b') & 15]);
                                write(IOUtils.DIGITS[(charAt >>> 4) & 15]);
                                write(IOUtils.DIGITS[charAt & 15]);
                            } else {
                                write(IOUtils.replaceChars[charAt]);
                            }
                        }
                        write(charAt);
                    } else if (charAt == '\b' || charAt == '\f' || charAt == '\n' || charAt == '\r' || charAt == '\t' || charAt == '\"' || charAt == '/' || charAt == '\\') {
                        write(92);
                        write(IOUtils.replaceChars[charAt]);
                    } else if (charAt < ' ') {
                        write(92);
                        write(117);
                        write(48);
                        write(48);
                        int i4 = charAt * 2;
                        write(IOUtils.ASCII_CHARS[i4]);
                        write(IOUtils.ASCII_CHARS[i4 + 1]);
                    } else {
                        if (charAt >= 127) {
                            write(92);
                            write(117);
                            write(IOUtils.DIGITS[(charAt >>> '\f') & 15]);
                            write(IOUtils.DIGITS[(charAt >>> '\b') & 15]);
                            write(IOUtils.DIGITS[(charAt >>> 4) & 15]);
                            write(IOUtils.DIGITS[charAt & 15]);
                        }
                        write(charAt);
                    }
                    i3++;
                    c2 = Typography.greater;
                }
                write(34);
                if (c != 0) {
                    write(c);
                    return;
                }
                return;
            }
            expandCapacity(i2);
        }
        int i5 = this.count;
        int i6 = i5 + 1;
        int i7 = i6 + length;
        char[] cArr = this.buf;
        cArr[i5] = '\"';
        str.getChars(0, length, cArr, i6);
        this.count = i2;
        int i8 = -1;
        if (isEnabled(SerializerFeature.BrowserCompatible)) {
            for (int i9 = i6; i9 < i7; i9++) {
                char c3 = this.buf[i9];
                if (c3 == '\"' || c3 == '/' || c3 == '\\' || c3 == '\b' || c3 == '\f' || c3 == '\n' || c3 == '\r' || c3 == '\t') {
                    i2++;
                } else if (c3 < ' ' || c3 >= 127) {
                    i2 += 5;
                }
                i8 = i9;
            }
            if (i2 > this.buf.length) {
                expandCapacity(i2);
            }
            this.count = i2;
            while (i8 >= i6) {
                char[] cArr2 = this.buf;
                char c4 = cArr2[i8];
                if (c4 == '\b' || c4 == '\f' || c4 == '\n' || c4 == '\r' || c4 == '\t') {
                    char[] cArr3 = this.buf;
                    int i10 = i8 + 1;
                    System.arraycopy(cArr3, i10, cArr3, i8 + 2, (i7 - i8) - 1);
                    char[] cArr4 = this.buf;
                    cArr4[i8] = '\\';
                    cArr4[i10] = IOUtils.replaceChars[c4];
                } else if (c4 == '\"' || c4 == '/' || c4 == '\\') {
                    char[] cArr5 = this.buf;
                    int i11 = i8 + 1;
                    System.arraycopy(cArr5, i11, cArr5, i8 + 2, (i7 - i8) - 1);
                    char[] cArr6 = this.buf;
                    cArr6[i8] = '\\';
                    cArr6[i11] = c4;
                } else {
                    if (c4 < ' ') {
                        int i12 = i8 + 1;
                        System.arraycopy(cArr2, i12, cArr2, i8 + 6, (i7 - i8) - 1);
                        char[] cArr7 = this.buf;
                        cArr7[i8] = '\\';
                        cArr7[i12] = 'u';
                        cArr7[i8 + 2] = '0';
                        cArr7[i8 + 3] = '0';
                        int i13 = c4 * 2;
                        cArr7[i8 + 4] = IOUtils.ASCII_CHARS[i13];
                        this.buf[i8 + 5] = IOUtils.ASCII_CHARS[i13 + 1];
                    } else if (c4 >= 127) {
                        int i14 = i8 + 1;
                        System.arraycopy(cArr2, i14, cArr2, i8 + 6, (i7 - i8) - 1);
                        char[] cArr8 = this.buf;
                        cArr8[i8] = '\\';
                        cArr8[i14] = 'u';
                        cArr8[i8 + 2] = IOUtils.DIGITS[(c4 >>> '\f') & 15];
                        this.buf[i8 + 3] = IOUtils.DIGITS[(c4 >>> '\b') & 15];
                        this.buf[i8 + 4] = IOUtils.DIGITS[(c4 >>> 4) & 15];
                        this.buf[i8 + 5] = IOUtils.DIGITS[c4 & 15];
                    } else {
                        i8--;
                    }
                    i7 += 5;
                    i8--;
                }
                i7++;
                i8--;
            }
            if (c != 0) {
                char[] cArr9 = this.buf;
                int i15 = this.count;
                cArr9[i15 - 2] = '\"';
                cArr9[i15 - 1] = c;
                return;
            }
            this.buf[this.count - 1] = '\"';
            return;
        }
        int i16 = 0;
        char c5 = 0;
        int i17 = -1;
        int i18 = -1;
        for (int i19 = i6; i19 < i7; i19++) {
            char c6 = this.buf[i19];
            if (c6 >= ']') {
                if (c6 >= 127 && (c6 == 8232 || c6 == 8233 || c6 < 160)) {
                    if (i17 == i8) {
                        i17 = i19;
                    }
                    i16++;
                    i2 += 4;
                    i18 = i19;
                }
            } else if ((c6 < '@' && (this.sepcialBits & (1 << c6)) != 0) || c6 == '\\') {
                i16++;
                if (c6 == '(' || c6 == ')' || c6 == '<' || c6 == '>' || (c6 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[c6] == 4)) {
                    i2 += 4;
                }
                i8 = -1;
                if (i17 == -1) {
                    i17 = i19;
                    i18 = i17;
                }
                i18 = i19;
            } else {
                i8 = -1;
            }
            c5 = c6;
        }
        if (i16 > 0) {
            int i20 = i2 + i16;
            if (i20 > this.buf.length) {
                expandCapacity(i20);
            }
            this.count = i20;
            if (i16 == 1) {
                if (c5 == 8232) {
                    int i21 = i18 + 1;
                    char[] cArr10 = this.buf;
                    System.arraycopy(cArr10, i21, cArr10, i18 + 6, (i7 - i18) - 1);
                    char[] cArr11 = this.buf;
                    cArr11[i18] = '\\';
                    cArr11[i21] = 'u';
                    int i22 = i21 + 1;
                    cArr11[i22] = '2';
                    int i23 = i22 + 1;
                    cArr11[i23] = '0';
                    int i24 = i23 + 1;
                    cArr11[i24] = '2';
                    cArr11[i24 + 1] = '8';
                } else if (c5 == 8233) {
                    int i25 = i18 + 1;
                    char[] cArr12 = this.buf;
                    System.arraycopy(cArr12, i25, cArr12, i18 + 6, (i7 - i18) - 1);
                    char[] cArr13 = this.buf;
                    cArr13[i18] = '\\';
                    cArr13[i25] = 'u';
                    int i26 = i25 + 1;
                    cArr13[i26] = '2';
                    int i27 = i26 + 1;
                    cArr13[i27] = '0';
                    int i28 = i27 + 1;
                    cArr13[i28] = '2';
                    cArr13[i28 + 1] = '9';
                } else if (c5 == '(' || c5 == ')' || c5 == '<' || c5 == '>') {
                    int i29 = i18 + 1;
                    char[] cArr14 = this.buf;
                    System.arraycopy(cArr14, i29, cArr14, i18 + 6, (i7 - i18) - 1);
                    char[] cArr15 = this.buf;
                    cArr15[i18] = '\\';
                    cArr15[i29] = 'u';
                    int i30 = i29 + 1;
                    cArr15[i30] = IOUtils.DIGITS[(c5 >>> '\f') & 15];
                    int i31 = i30 + 1;
                    this.buf[i31] = IOUtils.DIGITS[(c5 >>> '\b') & 15];
                    int i32 = i31 + 1;
                    this.buf[i32] = IOUtils.DIGITS[(c5 >>> 4) & 15];
                    this.buf[i32 + 1] = IOUtils.DIGITS[c5 & 15];
                } else if (c5 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[c5] == 4) {
                    int i33 = i18 + 1;
                    char[] cArr16 = this.buf;
                    System.arraycopy(cArr16, i33, cArr16, i18 + 6, (i7 - i18) - 1);
                    char[] cArr17 = this.buf;
                    cArr17[i18] = '\\';
                    int i34 = i33 + 1;
                    cArr17[i33] = 'u';
                    int i35 = i34 + 1;
                    cArr17[i34] = IOUtils.DIGITS[(c5 >>> '\f') & 15];
                    int i36 = i35 + 1;
                    this.buf[i35] = IOUtils.DIGITS[(c5 >>> '\b') & 15];
                    this.buf[i36] = IOUtils.DIGITS[(c5 >>> 4) & 15];
                    this.buf[i36 + 1] = IOUtils.DIGITS[c5 & 15];
                } else {
                    int i37 = i18 + 1;
                    char[] cArr18 = this.buf;
                    System.arraycopy(cArr18, i37, cArr18, i18 + 2, (i7 - i18) - 1);
                    char[] cArr19 = this.buf;
                    cArr19[i18] = '\\';
                    cArr19[i37] = IOUtils.replaceChars[c5];
                }
            } else if (i16 > 1) {
                for (int i38 = i17 - i6; i38 < str.length(); i38++) {
                    char charAt2 = str.charAt(i38);
                    if (this.browserSecure) {
                        if (charAt2 != '(' && charAt2 != ')') {
                            if (charAt2 != '<') {
                            }
                        }
                        char[] cArr20 = this.buf;
                        int i39 = i17 + 1;
                        cArr20[i17] = '\\';
                        int i40 = i39 + 1;
                        cArr20[i39] = 'u';
                        int i41 = i40 + 1;
                        cArr20[i40] = IOUtils.DIGITS[(charAt2 >>> '\f') & 15];
                        int i42 = i41 + 1;
                        this.buf[i41] = IOUtils.DIGITS[(charAt2 >>> '\b') & 15];
                        int i43 = i42 + 1;
                        this.buf[i42] = IOUtils.DIGITS[(charAt2 >>> 4) & 15];
                        this.buf[i43] = IOUtils.DIGITS[charAt2 & 15];
                        i17 = i43 + 1;
                    }
                    if ((charAt2 >= IOUtils.specicalFlags_doubleQuotes.length || IOUtils.specicalFlags_doubleQuotes[charAt2] == 0) && (charAt2 != '/' || !isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        if (charAt2 == 8232 || charAt2 == 8233) {
                            char[] cArr21 = this.buf;
                            int i44 = i17 + 1;
                            cArr21[i17] = '\\';
                            int i45 = i44 + 1;
                            cArr21[i44] = 'u';
                            int i46 = i45 + 1;
                            cArr21[i45] = IOUtils.DIGITS[(charAt2 >>> '\f') & 15];
                            int i47 = i46 + 1;
                            this.buf[i46] = IOUtils.DIGITS[(charAt2 >>> '\b') & 15];
                            int i48 = i47 + 1;
                            this.buf[i47] = IOUtils.DIGITS[(charAt2 >>> 4) & 15];
                            this.buf[i48] = IOUtils.DIGITS[charAt2 & 15];
                            i17 = i48 + 1;
                        } else {
                            i = i17 + 1;
                            this.buf[i17] = charAt2;
                            i17 = i;
                        }
                    }
                    int i49 = i17 + 1;
                    this.buf[i17] = '\\';
                    if (IOUtils.specicalFlags_doubleQuotes[charAt2] == 4) {
                        char[] cArr22 = this.buf;
                        int i50 = i49 + 1;
                        cArr22[i49] = 'u';
                        int i51 = i50 + 1;
                        cArr22[i50] = IOUtils.DIGITS[(charAt2 >>> '\f') & 15];
                        int i52 = i51 + 1;
                        this.buf[i51] = IOUtils.DIGITS[(charAt2 >>> '\b') & 15];
                        int i53 = i52 + 1;
                        this.buf[i52] = IOUtils.DIGITS[(charAt2 >>> 4) & 15];
                        i = i53 + 1;
                        this.buf[i53] = IOUtils.DIGITS[charAt2 & 15];
                    } else {
                        i = i49 + 1;
                        this.buf[i49] = IOUtils.replaceChars[charAt2];
                    }
                    i17 = i;
                }
            }
        }
        if (c != 0) {
            char[] cArr23 = this.buf;
            int i54 = this.count;
            cArr23[i54 - 2] = '\"';
            cArr23[i54 - 1] = c;
            return;
        }
        this.buf[this.count - 1] = '\"';
    }

    /* JADX WARN: Code restructure failed: missing block: B:228:0x047c, code lost:
    
        if (r3 != '>') goto L219;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void writeStringWithDoubleQuote(char[] cArr, char c) {
        int i;
        int i2;
        int i3;
        if (cArr == null) {
            writeNull();
            if (c != 0) {
                write(c);
                return;
            }
            return;
        }
        int length = cArr.length;
        int i4 = this.count + length + 2;
        if (c != 0) {
            i4++;
        }
        int length2 = this.buf.length;
        char c2 = Typography.greater;
        if (i4 > length2) {
            if (this.writer != null) {
                write(34);
                int i5 = 0;
                while (i5 < cArr.length) {
                    char c3 = cArr[i5];
                    if (isEnabled(SerializerFeature.BrowserSecure) && (c3 == '(' || c3 == ')' || c3 == '<' || c3 == c2)) {
                        write(92);
                        write(117);
                        write(IOUtils.DIGITS[(c3 >>> '\f') & 15]);
                        write(IOUtils.DIGITS[(c3 >>> '\b') & 15]);
                        write(IOUtils.DIGITS[(c3 >>> 4) & 15]);
                        write(IOUtils.DIGITS[c3 & 15]);
                    } else if (!isEnabled(SerializerFeature.BrowserCompatible)) {
                        if ((c3 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[c3] != 0) || (c3 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                            write(92);
                            if (IOUtils.specicalFlags_doubleQuotes[c3] == 4) {
                                write(117);
                                write(IOUtils.DIGITS[(c3 >>> '\f') & 15]);
                                write(IOUtils.DIGITS[(c3 >>> '\b') & 15]);
                                write(IOUtils.DIGITS[(c3 >>> 4) & 15]);
                                write(IOUtils.DIGITS[c3 & 15]);
                            } else {
                                write(IOUtils.replaceChars[c3]);
                            }
                        }
                        write(c3);
                    } else if (c3 == '\b' || c3 == '\f' || c3 == '\n' || c3 == '\r' || c3 == '\t' || c3 == '\"' || c3 == '/' || c3 == '\\') {
                        write(92);
                        write(IOUtils.replaceChars[c3]);
                    } else if (c3 < ' ') {
                        write(92);
                        write(117);
                        write(48);
                        write(48);
                        int i6 = c3 * 2;
                        write(IOUtils.ASCII_CHARS[i6]);
                        write(IOUtils.ASCII_CHARS[i6 + 1]);
                    } else {
                        if (c3 >= 127) {
                            write(92);
                            write(117);
                            write(IOUtils.DIGITS[(c3 >>> '\f') & 15]);
                            write(IOUtils.DIGITS[(c3 >>> '\b') & 15]);
                            write(IOUtils.DIGITS[(c3 >>> 4) & 15]);
                            write(IOUtils.DIGITS[c3 & 15]);
                        }
                        write(c3);
                    }
                    i5++;
                    c2 = Typography.greater;
                }
                write(34);
                if (c != 0) {
                    write(c);
                    return;
                }
                return;
            }
            expandCapacity(i4);
        }
        int i7 = this.count;
        int i8 = i7 + 1;
        int i9 = length + i8;
        char[] cArr2 = this.buf;
        cArr2[i7] = '\"';
        System.arraycopy(cArr, 0, cArr2, i8, cArr.length);
        this.count = i4;
        int i10 = -1;
        if (isEnabled(SerializerFeature.BrowserCompatible)) {
            for (int i11 = i8; i11 < i9; i11++) {
                char c4 = this.buf[i11];
                if (c4 == '\"' || c4 == '/' || c4 == '\\' || c4 == '\b' || c4 == '\f' || c4 == '\n' || c4 == '\r' || c4 == '\t') {
                    i4++;
                } else if (c4 < ' ' || c4 >= 127) {
                    i4 += 5;
                }
                i10 = i11;
            }
            if (i4 > this.buf.length) {
                expandCapacity(i4);
            }
            this.count = i4;
            while (i10 >= i8) {
                char[] cArr3 = this.buf;
                char c5 = cArr3[i10];
                if (c5 == '\b' || c5 == '\f' || c5 == '\n' || c5 == '\r' || c5 == '\t') {
                    char[] cArr4 = this.buf;
                    int i12 = i10 + 1;
                    System.arraycopy(cArr4, i12, cArr4, i10 + 2, (i9 - i10) - 1);
                    char[] cArr5 = this.buf;
                    cArr5[i10] = '\\';
                    cArr5[i12] = IOUtils.replaceChars[c5];
                } else if (c5 == '\"' || c5 == '/' || c5 == '\\') {
                    char[] cArr6 = this.buf;
                    int i13 = i10 + 1;
                    System.arraycopy(cArr6, i13, cArr6, i10 + 2, (i9 - i10) - 1);
                    char[] cArr7 = this.buf;
                    cArr7[i10] = '\\';
                    cArr7[i13] = c5;
                } else {
                    if (c5 < ' ') {
                        int i14 = i10 + 1;
                        System.arraycopy(cArr3, i14, cArr3, i10 + 6, (i9 - i10) - 1);
                        char[] cArr8 = this.buf;
                        cArr8[i10] = '\\';
                        cArr8[i14] = 'u';
                        cArr8[i10 + 2] = '0';
                        cArr8[i10 + 3] = '0';
                        int i15 = c5 * 2;
                        cArr8[i10 + 4] = IOUtils.ASCII_CHARS[i15];
                        this.buf[i10 + 5] = IOUtils.ASCII_CHARS[i15 + 1];
                    } else if (c5 >= 127) {
                        int i16 = i10 + 1;
                        System.arraycopy(cArr3, i16, cArr3, i10 + 6, (i9 - i10) - 1);
                        char[] cArr9 = this.buf;
                        cArr9[i10] = '\\';
                        cArr9[i16] = 'u';
                        cArr9[i10 + 2] = IOUtils.DIGITS[(c5 >>> '\f') & 15];
                        this.buf[i10 + 3] = IOUtils.DIGITS[(c5 >>> '\b') & 15];
                        this.buf[i10 + 4] = IOUtils.DIGITS[(c5 >>> 4) & 15];
                        this.buf[i10 + 5] = IOUtils.DIGITS[c5 & 15];
                    } else {
                        i10--;
                    }
                    i9 += 5;
                    i10--;
                }
                i9++;
                i10--;
            }
            if (c != 0) {
                char[] cArr10 = this.buf;
                int i17 = this.count;
                cArr10[i17 - 2] = '\"';
                cArr10[i17 - 1] = c;
                return;
            }
            this.buf[this.count - 1] = '\"';
            return;
        }
        int i18 = i8;
        int i19 = -1;
        int i20 = 0;
        char c6 = 0;
        int i21 = -1;
        while (i18 < i9) {
            char c7 = this.buf[i18];
            if (c7 >= ']') {
                if (c7 >= 127 && (c7 == 8232 || c7 == 8233 || c7 < 160)) {
                    if (i21 == i10) {
                        i21 = i18;
                    }
                    i20++;
                    i4 += 4;
                    i19 = i18;
                    c6 = c7;
                }
                int i22 = i21;
                i3 = i10;
                i2 = i22;
            } else {
                int i23 = i21;
                if ((c7 < '@' && (this.sepcialBits & (1 << c7)) != 0) || c7 == '\\') {
                    i20++;
                    if (c7 == '(' || c7 == ')' || c7 == '<' || c7 == '>' || (c7 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[c7] == 4)) {
                        i4 += 4;
                    }
                    i2 = i23;
                    i3 = -1;
                    if (i2 == -1) {
                        i2 = i18;
                        i19 = i2;
                    } else {
                        i19 = i18;
                    }
                    c6 = c7;
                } else {
                    i2 = i23;
                    i3 = -1;
                }
            }
            i18++;
            int i24 = i3;
            i21 = i2;
            i10 = i24;
        }
        int i25 = i21;
        if (i20 > 0) {
            int i26 = i4 + i20;
            if (i26 > this.buf.length) {
                expandCapacity(i26);
            }
            this.count = i26;
            if (i20 == 1) {
                if (c6 == 8232) {
                    int i27 = i19 + 1;
                    char[] cArr11 = this.buf;
                    System.arraycopy(cArr11, i27, cArr11, i19 + 6, (i9 - i19) - 1);
                    char[] cArr12 = this.buf;
                    cArr12[i19] = '\\';
                    cArr12[i27] = 'u';
                    int i28 = i27 + 1;
                    cArr12[i28] = '2';
                    int i29 = i28 + 1;
                    cArr12[i29] = '0';
                    int i30 = i29 + 1;
                    cArr12[i30] = '2';
                    cArr12[i30 + 1] = '8';
                } else if (c6 == 8233) {
                    int i31 = i19 + 1;
                    char[] cArr13 = this.buf;
                    System.arraycopy(cArr13, i31, cArr13, i19 + 6, (i9 - i19) - 1);
                    char[] cArr14 = this.buf;
                    cArr14[i19] = '\\';
                    cArr14[i31] = 'u';
                    int i32 = i31 + 1;
                    cArr14[i32] = '2';
                    int i33 = i32 + 1;
                    cArr14[i33] = '0';
                    int i34 = i33 + 1;
                    cArr14[i34] = '2';
                    cArr14[i34 + 1] = '9';
                } else if (c6 == '(' || c6 == ')' || c6 == '<' || c6 == '>') {
                    int i35 = i19 + 1;
                    char[] cArr15 = this.buf;
                    System.arraycopy(cArr15, i35, cArr15, i19 + 6, (i9 - i19) - 1);
                    char[] cArr16 = this.buf;
                    cArr16[i19] = '\\';
                    cArr16[i35] = 'u';
                    int i36 = i35 + 1;
                    cArr16[i36] = IOUtils.DIGITS[(c6 >>> '\f') & 15];
                    int i37 = i36 + 1;
                    this.buf[i37] = IOUtils.DIGITS[(c6 >>> '\b') & 15];
                    int i38 = i37 + 1;
                    this.buf[i38] = IOUtils.DIGITS[(c6 >>> 4) & 15];
                    this.buf[i38 + 1] = IOUtils.DIGITS[c6 & 15];
                } else if (c6 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[c6] == 4) {
                    int i39 = i19 + 1;
                    char[] cArr17 = this.buf;
                    System.arraycopy(cArr17, i39, cArr17, i19 + 6, (i9 - i19) - 1);
                    char[] cArr18 = this.buf;
                    cArr18[i19] = '\\';
                    int i40 = i39 + 1;
                    cArr18[i39] = 'u';
                    int i41 = i40 + 1;
                    cArr18[i40] = IOUtils.DIGITS[(c6 >>> '\f') & 15];
                    int i42 = i41 + 1;
                    this.buf[i41] = IOUtils.DIGITS[(c6 >>> '\b') & 15];
                    this.buf[i42] = IOUtils.DIGITS[(c6 >>> 4) & 15];
                    this.buf[i42 + 1] = IOUtils.DIGITS[c6 & 15];
                } else {
                    int i43 = i19 + 1;
                    char[] cArr19 = this.buf;
                    System.arraycopy(cArr19, i43, cArr19, i19 + 2, (i9 - i19) - 1);
                    char[] cArr20 = this.buf;
                    cArr20[i19] = '\\';
                    cArr20[i43] = IOUtils.replaceChars[c6];
                }
            } else if (i20 > 1) {
                for (int i44 = i25 - i8; i44 < cArr.length; i44++) {
                    char c8 = cArr[i44];
                    if (this.browserSecure) {
                        if (c8 != '(' && c8 != ')') {
                            if (c8 != '<') {
                            }
                        }
                        char[] cArr21 = this.buf;
                        int i45 = i25 + 1;
                        cArr21[i25] = '\\';
                        int i46 = i45 + 1;
                        cArr21[i45] = 'u';
                        int i47 = i46 + 1;
                        cArr21[i46] = IOUtils.DIGITS[(c8 >>> '\f') & 15];
                        int i48 = i47 + 1;
                        this.buf[i47] = IOUtils.DIGITS[(c8 >>> '\b') & 15];
                        int i49 = i48 + 1;
                        this.buf[i48] = IOUtils.DIGITS[(c8 >>> 4) & 15];
                        this.buf[i49] = IOUtils.DIGITS[c8 & 15];
                        i25 = i49 + 1;
                    }
                    if ((c8 >= IOUtils.specicalFlags_doubleQuotes.length || IOUtils.specicalFlags_doubleQuotes[c8] == 0) && (c8 != '/' || !isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        if (c8 == 8232 || c8 == 8233) {
                            char[] cArr22 = this.buf;
                            int i50 = i25 + 1;
                            cArr22[i25] = '\\';
                            int i51 = i50 + 1;
                            cArr22[i50] = 'u';
                            int i52 = i51 + 1;
                            cArr22[i51] = IOUtils.DIGITS[(c8 >>> '\f') & 15];
                            int i53 = i52 + 1;
                            this.buf[i52] = IOUtils.DIGITS[(c8 >>> '\b') & 15];
                            int i54 = i53 + 1;
                            this.buf[i53] = IOUtils.DIGITS[(c8 >>> 4) & 15];
                            this.buf[i54] = IOUtils.DIGITS[c8 & 15];
                            i25 = i54 + 1;
                        } else {
                            i = i25 + 1;
                            this.buf[i25] = c8;
                            i25 = i;
                        }
                    }
                    int i55 = i25 + 1;
                    this.buf[i25] = '\\';
                    if (IOUtils.specicalFlags_doubleQuotes[c8] == 4) {
                        char[] cArr23 = this.buf;
                        int i56 = i55 + 1;
                        cArr23[i55] = 'u';
                        int i57 = i56 + 1;
                        cArr23[i56] = IOUtils.DIGITS[(c8 >>> '\f') & 15];
                        int i58 = i57 + 1;
                        this.buf[i57] = IOUtils.DIGITS[(c8 >>> '\b') & 15];
                        int i59 = i58 + 1;
                        this.buf[i58] = IOUtils.DIGITS[(c8 >>> 4) & 15];
                        i = i59 + 1;
                        this.buf[i59] = IOUtils.DIGITS[c8 & 15];
                    } else {
                        i = i55 + 1;
                        this.buf[i55] = IOUtils.replaceChars[c8];
                    }
                    i25 = i;
                }
            }
        }
        if (c != 0) {
            char[] cArr24 = this.buf;
            int i60 = this.count;
            cArr24[i60 - 2] = '\"';
            cArr24[i60 - 1] = c;
            return;
        }
        this.buf[this.count - 1] = '\"';
    }

    public void writeFieldNameDirect(String str) {
        int length = str.length();
        int i = this.count + length + 3;
        if (i > this.buf.length) {
            expandCapacity(i);
        }
        int i2 = this.count;
        char[] cArr = this.buf;
        cArr[i2] = '\"';
        str.getChars(0, length, cArr, i2 + 1);
        this.count = i;
        char[] cArr2 = this.buf;
        cArr2[i - 2] = '\"';
        cArr2[i - 1] = ':';
    }

    public void write(List<String> list) {
        boolean z;
        int i;
        if (list.isEmpty()) {
            write("[]");
            return;
        }
        int i2 = this.count;
        int size = list.size();
        int i3 = i2;
        int i4 = 0;
        while (i4 < size) {
            String str = list.get(i4);
            if (str == null) {
                z = true;
            } else {
                int length = str.length();
                z = false;
                for (int i5 = 0; i5 < length; i5++) {
                    char charAt = str.charAt(i5);
                    z = charAt < ' ' || charAt > '~' || charAt == '\"' || charAt == '\\';
                    if (z) {
                        break;
                    }
                }
            }
            if (z) {
                this.count = i2;
                write(91);
                for (int i6 = 0; i6 < list.size(); i6++) {
                    String str2 = list.get(i6);
                    if (i6 != 0) {
                        write(44);
                    }
                    if (str2 == null) {
                        write("null");
                    } else {
                        writeStringWithDoubleQuote(str2, (char) 0);
                    }
                }
                write(93);
                return;
            }
            int length2 = str.length() + i3 + 3;
            if (i4 == list.size() - 1) {
                length2++;
            }
            if (length2 > this.buf.length) {
                this.count = i3;
                expandCapacity(length2);
            }
            if (i4 == 0) {
                i = i3 + 1;
                this.buf[i3] = '[';
            } else {
                i = i3 + 1;
                this.buf[i3] = ',';
            }
            int i7 = i + 1;
            this.buf[i] = '\"';
            str.getChars(0, str.length(), this.buf, i7);
            int length3 = i7 + str.length();
            this.buf[length3] = '\"';
            i4++;
            i3 = length3 + 1;
        }
        this.buf[i3] = ']';
        this.count = i3 + 1;
    }

    public void writeFieldValue(char c, String str, char c2) {
        write(c);
        writeFieldName(str);
        if (c2 == 0) {
            writeString("\u0000");
        } else {
            writeString(Character.toString(c2));
        }
    }

    public void writeFieldValue(char c, String str, boolean z) {
        if (!this.quoteFieldNames) {
            write(c);
            writeFieldName(str);
            write(z);
            return;
        }
        int i = z ? 4 : 5;
        int length = str.length();
        int i2 = this.count + length + 4 + i;
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                write(c);
                writeString(str);
                write(58);
                write(z);
                return;
            }
            expandCapacity(i2);
        }
        int i3 = this.count;
        this.count = i2;
        char[] cArr = this.buf;
        cArr[i3] = c;
        int i4 = i3 + length + 1;
        cArr[i3 + 1] = this.keySeperator;
        str.getChars(0, length, cArr, i3 + 2);
        this.buf[i4 + 1] = this.keySeperator;
        if (z) {
            System.arraycopy(":true".toCharArray(), 0, this.buf, i4 + 2, 5);
        } else {
            System.arraycopy(":false".toCharArray(), 0, this.buf, i4 + 2, 6);
        }
    }

    public void write(boolean z) {
        if (z) {
            write("true");
        } else {
            write("false");
        }
    }

    public void writeFieldValue(char c, String str, int i) {
        if (i == Integer.MIN_VALUE || !this.quoteFieldNames) {
            write(c);
            writeFieldName(str);
            writeInt(i);
            return;
        }
        int stringSize = i < 0 ? IOUtils.stringSize(-i) + 1 : IOUtils.stringSize(i);
        int length = str.length();
        int i2 = this.count + length + 4 + stringSize;
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                write(c);
                writeFieldName(str);
                writeInt(i);
                return;
            }
            expandCapacity(i2);
        }
        int i3 = this.count;
        this.count = i2;
        char[] cArr = this.buf;
        cArr[i3] = c;
        int i4 = i3 + length + 1;
        cArr[i3 + 1] = this.keySeperator;
        str.getChars(0, length, cArr, i3 + 2);
        char[] cArr2 = this.buf;
        cArr2[i4 + 1] = this.keySeperator;
        cArr2[i4 + 2] = ':';
        IOUtils.getChars(i, this.count, cArr2);
    }

    public void writeFieldValue(char c, String str, long j) {
        if (j == Long.MIN_VALUE || !this.quoteFieldNames) {
            write(c);
            writeFieldName(str);
            writeLong(j);
            return;
        }
        int stringSize = j < 0 ? IOUtils.stringSize(-j) + 1 : IOUtils.stringSize(j);
        int length = str.length();
        int i = this.count + length + 4 + stringSize;
        if (i > this.buf.length) {
            if (this.writer != null) {
                write(c);
                writeFieldName(str);
                writeLong(j);
                return;
            }
            expandCapacity(i);
        }
        int i2 = this.count;
        this.count = i;
        char[] cArr = this.buf;
        cArr[i2] = c;
        int i3 = i2 + length + 1;
        cArr[i2 + 1] = this.keySeperator;
        str.getChars(0, length, cArr, i2 + 2);
        char[] cArr2 = this.buf;
        cArr2[i3 + 1] = this.keySeperator;
        cArr2[i3 + 2] = ':';
        IOUtils.getChars(j, this.count, cArr2);
    }

    public void writeFieldValue(char c, String str, float f) {
        write(c);
        writeFieldName(str);
        writeFloat(f, false);
    }

    public void writeFieldValue(char c, String str, double d) {
        write(c);
        writeFieldName(str);
        writeDouble(d, false);
    }

    public void writeFieldValue(char c, String str, String str2) {
        if (this.quoteFieldNames) {
            if (this.useSingleQuotes) {
                write(c);
                writeFieldName(str);
                if (str2 == null) {
                    writeNull();
                    return;
                } else {
                    writeString(str2);
                    return;
                }
            }
            if (isEnabled(SerializerFeature.BrowserCompatible)) {
                write(c);
                writeStringWithDoubleQuote(str, ':');
                writeStringWithDoubleQuote(str2, (char) 0);
                return;
            }
            writeFieldValueStringWithDoubleQuoteCheck(c, str, str2);
            return;
        }
        write(c);
        writeFieldName(str);
        if (str2 == null) {
            writeNull();
        } else {
            writeString(str2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0232, code lost:
    
        if (r3 != '>') goto L103;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void writeFieldValueStringWithDoubleQuoteCheck(char c, String str, String str2) {
        int length;
        int i;
        int length2 = str.length();
        int i2 = this.count;
        if (str2 == null) {
            i = i2 + length2 + 8;
            length = 4;
        } else {
            length = str2.length();
            i = i2 + length2 + length + 6;
        }
        if (i > this.buf.length) {
            if (this.writer != null) {
                write(c);
                writeStringWithDoubleQuote(str, ':');
                writeStringWithDoubleQuote(str2, (char) 0);
                return;
            }
            expandCapacity(i);
        }
        char[] cArr = this.buf;
        int i3 = this.count;
        cArr[i3] = c;
        int i4 = i3 + 2;
        int i5 = i4 + length2;
        cArr[i3 + 1] = '\"';
        str.getChars(0, length2, cArr, i4);
        this.count = i;
        char[] cArr2 = this.buf;
        cArr2[i5] = '\"';
        int i6 = i5 + 1;
        int i7 = i6 + 1;
        cArr2[i6] = ':';
        if (str2 == null) {
            int i8 = i7 + 1;
            cArr2[i7] = 'n';
            int i9 = i8 + 1;
            cArr2[i8] = 'u';
            cArr2[i9] = 'l';
            cArr2[i9 + 1] = 'l';
            return;
        }
        int i10 = i7 + 1;
        cArr2[i7] = '\"';
        int i11 = i10 + length;
        str2.getChars(0, length, cArr2, i10);
        int i12 = -1;
        int i13 = -1;
        int i14 = -1;
        int i15 = 0;
        char c2 = 0;
        for (int i16 = i10; i16 < i11; i16++) {
            char c3 = this.buf[i16];
            if (c3 >= ']') {
                if (c3 >= 127 && (c3 == 8232 || c3 == 8233 || c3 < 160)) {
                    if (i13 == i12) {
                        i13 = i16;
                    }
                    i15++;
                    i += 4;
                    i14 = i16;
                }
            } else if ((c3 < '@' && (this.sepcialBits & (1 << c3)) != 0) || c3 == '\\') {
                i15++;
                if (c3 == '(' || c3 == ')' || c3 == '<' || c3 == '>' || (c3 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[c3] == 4)) {
                    i += 4;
                }
                i12 = -1;
                if (i13 == -1) {
                    i13 = i16;
                    i14 = i13;
                }
                i14 = i16;
            } else {
                i12 = -1;
            }
            c2 = c3;
        }
        if (i15 > 0) {
            int i17 = i + i15;
            if (i17 > this.buf.length) {
                expandCapacity(i17);
            }
            this.count = i17;
            if (i15 == 1) {
                if (c2 == 8232) {
                    int i18 = i14 + 1;
                    char[] cArr3 = this.buf;
                    System.arraycopy(cArr3, i18, cArr3, i14 + 6, (i11 - i14) - 1);
                    char[] cArr4 = this.buf;
                    cArr4[i14] = '\\';
                    cArr4[i18] = 'u';
                    int i19 = i18 + 1;
                    cArr4[i19] = '2';
                    int i20 = i19 + 1;
                    cArr4[i20] = '0';
                    int i21 = i20 + 1;
                    cArr4[i21] = '2';
                    cArr4[i21 + 1] = '8';
                } else if (c2 == 8233) {
                    int i22 = i14 + 1;
                    char[] cArr5 = this.buf;
                    System.arraycopy(cArr5, i22, cArr5, i14 + 6, (i11 - i14) - 1);
                    char[] cArr6 = this.buf;
                    cArr6[i14] = '\\';
                    cArr6[i22] = 'u';
                    int i23 = i22 + 1;
                    cArr6[i23] = '2';
                    int i24 = i23 + 1;
                    cArr6[i24] = '0';
                    int i25 = i24 + 1;
                    cArr6[i25] = '2';
                    cArr6[i25 + 1] = '9';
                } else if (c2 == '(' || c2 == ')' || c2 == '<' || c2 == '>') {
                    int i26 = i14 + 1;
                    char[] cArr7 = this.buf;
                    System.arraycopy(cArr7, i26, cArr7, i14 + 6, (i11 - i14) - 1);
                    char[] cArr8 = this.buf;
                    cArr8[i14] = '\\';
                    int i27 = i26 + 1;
                    cArr8[i26] = 'u';
                    int i28 = i27 + 1;
                    cArr8[i27] = IOUtils.DIGITS[(c2 >>> '\f') & 15];
                    int i29 = i28 + 1;
                    this.buf[i28] = IOUtils.DIGITS[(c2 >>> '\b') & 15];
                    this.buf[i29] = IOUtils.DIGITS[(c2 >>> 4) & 15];
                    this.buf[i29 + 1] = IOUtils.DIGITS[c2 & 15];
                } else if (c2 < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[c2] == 4) {
                    int i30 = i14 + 1;
                    char[] cArr9 = this.buf;
                    System.arraycopy(cArr9, i30, cArr9, i14 + 6, (i11 - i14) - 1);
                    char[] cArr10 = this.buf;
                    cArr10[i14] = '\\';
                    int i31 = i30 + 1;
                    cArr10[i30] = 'u';
                    int i32 = i31 + 1;
                    cArr10[i31] = IOUtils.DIGITS[(c2 >>> '\f') & 15];
                    int i33 = i32 + 1;
                    this.buf[i32] = IOUtils.DIGITS[(c2 >>> '\b') & 15];
                    this.buf[i33] = IOUtils.DIGITS[(c2 >>> 4) & 15];
                    this.buf[i33 + 1] = IOUtils.DIGITS[c2 & 15];
                } else {
                    int i34 = i14 + 1;
                    char[] cArr11 = this.buf;
                    System.arraycopy(cArr11, i34, cArr11, i14 + 2, (i11 - i14) - 1);
                    char[] cArr12 = this.buf;
                    cArr12[i14] = '\\';
                    cArr12[i34] = IOUtils.replaceChars[c2];
                }
            } else if (i15 > 1) {
                for (int i35 = i13 - i10; i35 < str2.length(); i35++) {
                    char charAt = str2.charAt(i35);
                    if (this.browserSecure) {
                        if (charAt != '(' && charAt != ')') {
                            if (charAt != '<') {
                            }
                        }
                        char[] cArr13 = this.buf;
                        int i36 = i13 + 1;
                        cArr13[i13] = '\\';
                        int i37 = i36 + 1;
                        cArr13[i36] = 'u';
                        int i38 = i37 + 1;
                        cArr13[i37] = IOUtils.DIGITS[(charAt >>> '\f') & 15];
                        int i39 = i38 + 1;
                        this.buf[i38] = IOUtils.DIGITS[(charAt >>> '\b') & 15];
                        int i40 = i39 + 1;
                        this.buf[i39] = IOUtils.DIGITS[(charAt >>> 4) & 15];
                        i13 = i40 + 1;
                        this.buf[i40] = IOUtils.DIGITS[charAt & 15];
                    }
                    if ((charAt < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[charAt] != 0) || (charAt == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        int i41 = i13 + 1;
                        this.buf[i13] = '\\';
                        if (IOUtils.specicalFlags_doubleQuotes[charAt] == 4) {
                            char[] cArr14 = this.buf;
                            int i42 = i41 + 1;
                            cArr14[i41] = 'u';
                            int i43 = i42 + 1;
                            cArr14[i42] = IOUtils.DIGITS[(charAt >>> '\f') & 15];
                            int i44 = i43 + 1;
                            this.buf[i43] = IOUtils.DIGITS[(charAt >>> '\b') & 15];
                            int i45 = i44 + 1;
                            this.buf[i44] = IOUtils.DIGITS[(charAt >>> 4) & 15];
                            i13 = i45 + 1;
                            this.buf[i45] = IOUtils.DIGITS[charAt & 15];
                        } else {
                            i13 = i41 + 1;
                            this.buf[i41] = IOUtils.replaceChars[charAt];
                        }
                    } else if (charAt == 8232 || charAt == 8233) {
                        char[] cArr15 = this.buf;
                        int i46 = i13 + 1;
                        cArr15[i13] = '\\';
                        int i47 = i46 + 1;
                        cArr15[i46] = 'u';
                        int i48 = i47 + 1;
                        cArr15[i47] = IOUtils.DIGITS[(charAt >>> '\f') & 15];
                        int i49 = i48 + 1;
                        this.buf[i48] = IOUtils.DIGITS[(charAt >>> '\b') & 15];
                        int i50 = i49 + 1;
                        this.buf[i49] = IOUtils.DIGITS[(charAt >>> 4) & 15];
                        i13 = i50 + 1;
                        this.buf[i50] = IOUtils.DIGITS[charAt & 15];
                    } else {
                        this.buf[i13] = charAt;
                        i13++;
                    }
                }
            }
        }
        this.buf[this.count - 1] = '\"';
    }

    public void writeFieldValueStringWithDoubleQuote(char c, String str, String str2) {
        int length = str.length();
        int i = this.count;
        int length2 = str2.length();
        int i2 = i + length + length2 + 6;
        if (i2 > this.buf.length) {
            if (this.writer != null) {
                write(c);
                writeStringWithDoubleQuote(str, ':');
                writeStringWithDoubleQuote(str2, (char) 0);
                return;
            }
            expandCapacity(i2);
        }
        char[] cArr = this.buf;
        int i3 = this.count;
        cArr[i3] = c;
        int i4 = i3 + 2;
        int i5 = i4 + length;
        cArr[i3 + 1] = '\"';
        str.getChars(0, length, cArr, i4);
        this.count = i2;
        char[] cArr2 = this.buf;
        cArr2[i5] = '\"';
        int i6 = i5 + 1;
        int i7 = i6 + 1;
        cArr2[i6] = ':';
        cArr2[i7] = '\"';
        str2.getChars(0, length2, cArr2, i7 + 1);
        this.buf[this.count - 1] = '\"';
    }

    public void writeFieldValue(char c, String str, Enum<?> r4) {
        if (r4 == null) {
            write(c);
            writeFieldName(str);
            writeNull();
        } else if (this.writeEnumUsingName && !this.writeEnumUsingToString) {
            writeEnumFieldValue(c, str, r4.name());
        } else if (this.writeEnumUsingToString) {
            writeEnumFieldValue(c, str, r4.toString());
        } else {
            writeFieldValue(c, str, r4.ordinal());
        }
    }

    private void writeEnumFieldValue(char c, String str, String str2) {
        if (this.useSingleQuotes) {
            writeFieldValue(c, str, str2);
        } else {
            writeFieldValueStringWithDoubleQuote(c, str, str2);
        }
    }

    public void writeFieldValue(char c, String str, BigDecimal bigDecimal) {
        write(c);
        writeFieldName(str);
        if (bigDecimal == null) {
            writeNull();
        } else {
            write(bigDecimal.toString());
        }
    }

    public void writeString(String str, char c) {
        if (this.useSingleQuotes) {
            writeStringWithSingleQuote(str);
            write(c);
        } else {
            writeStringWithDoubleQuote(str, c);
        }
    }

    public void writeString(String str) {
        if (this.useSingleQuotes) {
            writeStringWithSingleQuote(str);
        } else {
            writeStringWithDoubleQuote(str, (char) 0);
        }
    }

    public void writeString(char[] cArr) {
        if (this.useSingleQuotes) {
            writeStringWithSingleQuote(cArr);
        } else {
            writeStringWithDoubleQuote(new String(cArr), (char) 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void writeStringWithSingleQuote(String str) {
        int i = 0;
        if (str == null) {
            int i2 = this.count + 4;
            if (i2 > this.buf.length) {
                expandCapacity(i2);
            }
            "null".getChars(0, 4, this.buf, this.count);
            this.count = i2;
            return;
        }
        int length = str.length();
        int i3 = this.count + length + 2;
        if (i3 > this.buf.length) {
            if (this.writer != null) {
                write(39);
                while (i < str.length()) {
                    char charAt = str.charAt(i);
                    if (charAt <= '\r' || charAt == '\\' || charAt == '\'' || (charAt == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        write(92);
                        write(IOUtils.replaceChars[charAt]);
                    } else {
                        write(charAt);
                    }
                    i++;
                }
                write(39);
                return;
            }
            expandCapacity(i3);
        }
        int i4 = this.count;
        int i5 = i4 + 1;
        int i6 = i5 + length;
        char[] cArr = this.buf;
        cArr[i4] = '\'';
        str.getChars(0, length, cArr, i5);
        this.count = i3;
        int i7 = -1;
        char c = 0;
        for (int i8 = i5; i8 < i6; i8++) {
            char c2 = this.buf[i8];
            if (c2 <= '\r' || c2 == '\\' || c2 == '\'' || (c2 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                i++;
                i7 = i8;
                c = c2;
            }
        }
        int i9 = i3 + i;
        if (i9 > this.buf.length) {
            expandCapacity(i9);
        }
        this.count = i9;
        if (i == 1) {
            char[] cArr2 = this.buf;
            int i10 = i7 + 1;
            System.arraycopy(cArr2, i10, cArr2, i7 + 2, (i6 - i7) - 1);
            char[] cArr3 = this.buf;
            cArr3[i7] = '\\';
            cArr3[i10] = IOUtils.replaceChars[c];
        } else if (i > 1) {
            char[] cArr4 = this.buf;
            int i11 = i7 + 1;
            System.arraycopy(cArr4, i11, cArr4, i7 + 2, (i6 - i7) - 1);
            char[] cArr5 = this.buf;
            cArr5[i7] = '\\';
            cArr5[i11] = IOUtils.replaceChars[c];
            int i12 = i6 + 1;
            for (int i13 = i11 - 2; i13 >= i5; i13--) {
                char c3 = this.buf[i13];
                if (c3 <= '\r' || c3 == '\\' || c3 == '\'' || (c3 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                    char[] cArr6 = this.buf;
                    int i14 = i13 + 1;
                    System.arraycopy(cArr6, i14, cArr6, i13 + 2, (i12 - i13) - 1);
                    char[] cArr7 = this.buf;
                    cArr7[i13] = '\\';
                    cArr7[i14] = IOUtils.replaceChars[c3];
                    i12++;
                }
            }
        }
        this.buf[this.count - 1] = '\'';
    }

    protected void writeStringWithSingleQuote(char[] cArr) {
        int i = 0;
        if (cArr == null) {
            int i2 = this.count + 4;
            if (i2 > this.buf.length) {
                expandCapacity(i2);
            }
            "null".getChars(0, 4, this.buf, this.count);
            this.count = i2;
            return;
        }
        int length = cArr.length;
        int i3 = this.count + length + 2;
        if (i3 > this.buf.length) {
            if (this.writer != null) {
                write(39);
                while (i < cArr.length) {
                    char c = cArr[i];
                    if (c <= '\r' || c == '\\' || c == '\'' || (c == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                        write(92);
                        write(IOUtils.replaceChars[c]);
                    } else {
                        write(c);
                    }
                    i++;
                }
                write(39);
                return;
            }
            expandCapacity(i3);
        }
        int i4 = this.count;
        int i5 = i4 + 1;
        int i6 = length + i5;
        char[] cArr2 = this.buf;
        cArr2[i4] = '\'';
        System.arraycopy(cArr, 0, cArr2, i5, cArr.length);
        this.count = i3;
        int i7 = -1;
        char c2 = 0;
        for (int i8 = i5; i8 < i6; i8++) {
            char c3 = this.buf[i8];
            if (c3 <= '\r' || c3 == '\\' || c3 == '\'' || (c3 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                i++;
                i7 = i8;
                c2 = c3;
            }
        }
        int i9 = i3 + i;
        if (i9 > this.buf.length) {
            expandCapacity(i9);
        }
        this.count = i9;
        if (i == 1) {
            char[] cArr3 = this.buf;
            int i10 = i7 + 1;
            System.arraycopy(cArr3, i10, cArr3, i7 + 2, (i6 - i7) - 1);
            char[] cArr4 = this.buf;
            cArr4[i7] = '\\';
            cArr4[i10] = IOUtils.replaceChars[c2];
        } else if (i > 1) {
            char[] cArr5 = this.buf;
            int i11 = i7 + 1;
            System.arraycopy(cArr5, i11, cArr5, i7 + 2, (i6 - i7) - 1);
            char[] cArr6 = this.buf;
            cArr6[i7] = '\\';
            cArr6[i11] = IOUtils.replaceChars[c2];
            int i12 = i6 + 1;
            for (int i13 = i11 - 2; i13 >= i5; i13--) {
                char c4 = this.buf[i13];
                if (c4 <= '\r' || c4 == '\\' || c4 == '\'' || (c4 == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
                    char[] cArr7 = this.buf;
                    int i14 = i13 + 1;
                    System.arraycopy(cArr7, i14, cArr7, i13 + 2, (i12 - i13) - 1);
                    char[] cArr8 = this.buf;
                    cArr8[i13] = '\\';
                    cArr8[i14] = IOUtils.replaceChars[c4];
                    i12++;
                }
            }
        }
        this.buf[this.count - 1] = '\'';
    }

    public void writeFieldName(String str) {
        writeFieldName(str, false);
    }

    public void writeFieldName(String str, boolean z) {
        if (str == null) {
            write("null:");
            return;
        }
        if (this.useSingleQuotes) {
            if (this.quoteFieldNames) {
                writeStringWithSingleQuote(str);
                write(58);
                return;
            } else {
                writeKeyWithSingleQuoteIfHasSpecial(str);
                return;
            }
        }
        if (this.quoteFieldNames) {
            writeStringWithDoubleQuote(str, ':');
            return;
        }
        boolean z2 = true;
        boolean z3 = str.length() == 0;
        int i = 0;
        while (true) {
            if (i >= str.length()) {
                z2 = z3;
                break;
            }
            char charAt = str.charAt(i);
            if ((charAt < '@' && (this.sepcialBits & (1 << charAt)) != 0) || charAt == '\\') {
                break;
            } else {
                i++;
            }
        }
        if (z2) {
            writeStringWithDoubleQuote(str, ':');
        } else {
            write(str);
            write(58);
        }
    }

    private void writeKeyWithSingleQuoteIfHasSpecial(String str) {
        byte[] bArr = IOUtils.specicalFlags_singleQuotes;
        int length = str.length();
        boolean z = true;
        int i = this.count + length + 1;
        int i2 = 0;
        if (i > this.buf.length) {
            if (this.writer != null) {
                if (length == 0) {
                    write(39);
                    write(39);
                    write(58);
                    return;
                }
                int i3 = 0;
                while (true) {
                    if (i3 < length) {
                        char charAt = str.charAt(i3);
                        if (charAt < bArr.length && bArr[charAt] != 0) {
                            break;
                        } else {
                            i3++;
                        }
                    } else {
                        z = false;
                        break;
                    }
                }
                if (z) {
                    write(39);
                }
                while (i2 < length) {
                    char charAt2 = str.charAt(i2);
                    if (charAt2 < bArr.length && bArr[charAt2] != 0) {
                        write(92);
                        write(IOUtils.replaceChars[charAt2]);
                    } else {
                        write(charAt2);
                    }
                    i2++;
                }
                if (z) {
                    write(39);
                }
                write(58);
                return;
            }
            expandCapacity(i);
        }
        if (length == 0) {
            int i4 = this.count;
            if (i4 + 3 > this.buf.length) {
                expandCapacity(i4 + 3);
            }
            char[] cArr = this.buf;
            int i5 = this.count;
            int i6 = i5 + 1;
            this.count = i6;
            cArr[i5] = '\'';
            int i7 = i6 + 1;
            this.count = i7;
            cArr[i6] = '\'';
            this.count = i7 + 1;
            cArr[i7] = ':';
            return;
        }
        int i8 = this.count;
        int i9 = i8 + length;
        str.getChars(0, length, this.buf, i8);
        this.count = i;
        int i10 = i8;
        boolean z2 = false;
        while (i10 < i9) {
            char[] cArr2 = this.buf;
            char c = cArr2[i10];
            if (c < bArr.length && bArr[c] != 0) {
                if (!z2) {
                    i += 3;
                    if (i > cArr2.length) {
                        expandCapacity(i);
                    }
                    this.count = i;
                    char[] cArr3 = this.buf;
                    int i11 = i10 + 1;
                    System.arraycopy(cArr3, i11, cArr3, i10 + 3, (i9 - i10) - 1);
                    char[] cArr4 = this.buf;
                    System.arraycopy(cArr4, i2, cArr4, 1, i10);
                    char[] cArr5 = this.buf;
                    cArr5[i8] = '\'';
                    cArr5[i11] = '\\';
                    int i12 = i11 + 1;
                    cArr5[i12] = IOUtils.replaceChars[c];
                    i9 += 2;
                    this.buf[this.count - 2] = '\'';
                    i10 = i12;
                    z2 = true;
                } else {
                    i++;
                    if (i > cArr2.length) {
                        expandCapacity(i);
                    }
                    this.count = i;
                    char[] cArr6 = this.buf;
                    int i13 = i10 + 1;
                    System.arraycopy(cArr6, i13, cArr6, i10 + 2, i9 - i10);
                    char[] cArr7 = this.buf;
                    cArr7[i10] = '\\';
                    cArr7[i13] = IOUtils.replaceChars[c];
                    i9++;
                    i10 = i13;
                }
            }
            i10++;
            i2 = 0;
        }
        this.buf[i - 1] = ':';
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        Writer writer = this.writer;
        if (writer == null) {
            return;
        }
        try {
            writer.write(this.buf, 0, this.count);
            this.writer.flush();
            this.count = 0;
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        }
    }
}
