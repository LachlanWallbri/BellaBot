package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.IOUtils;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigDecimal;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class JSONReaderScanner extends JSONLexerBase {
    private static final ThreadLocal<char[]> BUF_LOCAL = new ThreadLocal<>();
    private char[] buf;
    private int bufLength;
    private Reader reader;

    public JSONReaderScanner(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONReaderScanner(String str, int i) {
        this(new StringReader(str), i);
    }

    public JSONReaderScanner(char[] cArr, int i) {
        this(cArr, i, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONReaderScanner(Reader reader) {
        this(reader, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONReaderScanner(Reader reader, int i) {
        super(i);
        this.reader = reader;
        char[] cArr = BUF_LOCAL.get();
        this.buf = cArr;
        if (cArr != null) {
            BUF_LOCAL.set(null);
        }
        if (this.buf == null) {
            this.buf = new char[16384];
        }
        try {
            this.bufLength = reader.read(this.buf);
            this.f311bp = -1;
            next();
            if (this.f312ch == 65279) {
                next();
            }
        } catch (IOException e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public JSONReaderScanner(char[] cArr, int i, int i2) {
        this(new CharArrayReader(cArr, 0, i), i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char charAt(int i) {
        int i2 = this.bufLength;
        if (i >= i2) {
            if (i2 == -1) {
                return i < this.f314sp ? this.buf[i] : JSONLexer.EOI;
            }
            if (this.f311bp == 0) {
                char[] cArr = this.buf;
                int length = (cArr.length * 3) / 2;
                char[] cArr2 = new char[length];
                System.arraycopy(cArr, this.f311bp, cArr2, 0, this.bufLength);
                int i3 = this.bufLength;
                try {
                    this.bufLength += this.reader.read(cArr2, i3, length - i3);
                    this.buf = cArr2;
                } catch (IOException e) {
                    throw new JSONException(e.getMessage(), e);
                }
            } else {
                int i4 = this.bufLength - this.f311bp;
                if (i4 > 0) {
                    System.arraycopy(this.buf, this.f311bp, this.buf, 0, i4);
                }
                try {
                    int read = this.reader.read(this.buf, i4, this.buf.length - i4);
                    this.bufLength = read;
                    if (read == 0) {
                        throw new JSONException("illegal state, textLength is zero");
                    }
                    if (read == -1) {
                        return JSONLexer.EOI;
                    }
                    this.bufLength = read + i4;
                    i -= this.f311bp;
                    this.f313np -= this.f311bp;
                    this.f311bp = 0;
                } catch (IOException e2) {
                    throw new JSONException(e2.getMessage(), e2);
                }
            }
        }
        return this.buf[i];
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final int indexOf(char c, int i) {
        int i2 = i - this.f311bp;
        while (true) {
            char charAt = charAt(this.f311bp + i2);
            if (c == charAt) {
                return i2 + this.f311bp;
            }
            if (charAt == 26) {
                return -1;
            }
            i2++;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String addSymbol(int i, int i2, int i3, SymbolTable symbolTable) {
        return symbolTable.addSymbol(this.buf, i, i2, i3);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final char next() {
        int i = this.f311bp + 1;
        this.f311bp = i;
        int i2 = this.bufLength;
        if (i >= i2) {
            if (i2 == -1) {
                return JSONLexer.EOI;
            }
            if (this.f314sp > 0) {
                int i3 = this.bufLength - this.f314sp;
                if (this.f312ch == '\"' && i3 > 0) {
                    i3--;
                }
                char[] cArr = this.buf;
                System.arraycopy(cArr, i3, cArr, 0, this.f314sp);
            }
            this.f313np = -1;
            int i4 = this.f314sp;
            this.f311bp = i4;
            try {
                int i5 = this.f311bp;
                int length = this.buf.length - i5;
                if (length == 0) {
                    char[] cArr2 = new char[this.buf.length * 2];
                    System.arraycopy(this.buf, 0, cArr2, 0, this.buf.length);
                    this.buf = cArr2;
                    length = cArr2.length - i5;
                }
                int read = this.reader.read(this.buf, this.f311bp, length);
                this.bufLength = read;
                if (read == 0) {
                    throw new JSONException("illegal stat, textLength is zero");
                }
                if (read == -1) {
                    this.f312ch = JSONLexer.EOI;
                    return JSONLexer.EOI;
                }
                this.bufLength = read + this.f311bp;
                i = i4;
            } catch (IOException e) {
                throw new JSONException(e.getMessage(), e);
            }
        }
        char c = this.buf[i];
        this.f312ch = c;
        return c;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    protected final void copyTo(int i, int i2, char[] cArr) {
        System.arraycopy(this.buf, i, cArr, 0, i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final boolean charArrayCompare(char[] cArr) {
        for (int i = 0; i < cArr.length; i++) {
            if (charAt(this.f311bp + i) != cArr[i]) {
                return false;
            }
        }
        return true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public byte[] bytesValue() {
        if (this.token == 26) {
            throw new JSONException("TODO");
        }
        return IOUtils.decodeBase64(this.buf, this.f313np + 1, this.f314sp);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    protected final void arrayCopy(int i, char[] cArr, int i2, int i3) {
        System.arraycopy(this.buf, i, cArr, i2, i3);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String stringVal() {
        if (!this.hasSpecial) {
            int i = this.f313np + 1;
            if (i < 0) {
                throw new IllegalStateException();
            }
            if (i > this.buf.length - this.f314sp) {
                throw new IllegalStateException();
            }
            return new String(this.buf, i, this.f314sp);
        }
        return new String(this.sbuf, 0, this.f314sp);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String subString(int i, int i2) {
        if (i2 < 0) {
            throw new StringIndexOutOfBoundsException(i2);
        }
        return new String(this.buf, i, i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char[] sub_chars(int i, int i2) {
        if (i2 < 0) {
            throw new StringIndexOutOfBoundsException(i2);
        }
        if (i == 0) {
            return this.buf;
        }
        char[] cArr = new char[i2];
        System.arraycopy(this.buf, i, cArr, 0, i2);
        return cArr;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String numberString() {
        int i = this.f313np;
        if (i == -1) {
            i = 0;
        }
        char charAt = charAt((this.f314sp + i) - 1);
        int i2 = this.f314sp;
        if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
            i2--;
        }
        return new String(this.buf, i, i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final BigDecimal decimalValue() {
        int i = this.f313np;
        if (i == -1) {
            i = 0;
        }
        char charAt = charAt((this.f314sp + i) - 1);
        int i2 = this.f314sp;
        if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
            i2--;
        }
        return new BigDecimal(this.buf, i, i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        super.close();
        char[] cArr = this.buf;
        if (cArr.length <= 65536) {
            BUF_LOCAL.set(cArr);
        }
        this.buf = null;
        IOUtils.close(this.reader);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean isEOF() {
        if (this.bufLength == -1 || this.f311bp == this.buf.length) {
            return true;
        }
        return this.f312ch == 26 && this.f311bp + 1 == this.buf.length;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final boolean isBlankInput() {
        int i = 0;
        while (true) {
            char c = this.buf[i];
            if (c == 26) {
                this.token = 20;
                return true;
            }
            if (!isWhitespace(c)) {
                return false;
            }
            i++;
        }
    }
}
