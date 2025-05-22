package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.IOUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.TimeZone;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class JSONScanner extends JSONLexerBase {
    private final int len;
    private final String text;

    static boolean checkDate(char c, char c2, char c3, char c4, char c5, char c6, int i, int i2) {
        if (c >= '1' && c <= '3' && c2 >= '0' && c2 <= '9' && c3 >= '0' && c3 <= '9' && c4 >= '0' && c4 <= '9') {
            if (c5 == '0') {
                if (c6 < '1' || c6 > '9') {
                    return false;
                }
            } else if (c5 != '1' || (c6 != '0' && c6 != '1' && c6 != '2')) {
                return false;
            }
            if (i == 48) {
                return i2 >= 49 && i2 <= 57;
            }
            if (i != 49 && i != 50) {
                return i == 51 && (i2 == 48 || i2 == 49);
            }
            if (i2 >= 48 && i2 <= 57) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x001d, code lost:
    
        if (r6 <= '4') goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean checkTime(char c, char c2, char c3, char c4, char c5, char c6) {
        if (c == '0') {
            if (c2 < '0' || c2 > '9') {
                return false;
            }
        } else {
            if (c != '1') {
                if (c == '2') {
                    if (c2 >= '0') {
                    }
                }
                return false;
            }
            if (c2 < '0' || c2 > '9') {
                return false;
            }
        }
        if (c3 < '0' || c3 > '5') {
            if (c3 != '6' || c4 != '0') {
                return false;
            }
        } else if (c4 < '0' || c4 > '9') {
            return false;
        }
        return (c5 < '0' || c5 > '5') ? c5 == '6' && c6 == '0' : c6 >= '0' && c6 <= '9';
    }

    public JSONScanner(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(String str, int i) {
        super(i);
        this.text = str;
        this.len = str.length();
        this.f311bp = -1;
        next();
        if (this.f312ch == 65279) {
            next();
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char charAt(int i) {
        return i >= this.len ? JSONLexer.EOI : this.text.charAt(i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final char next() {
        int i = this.f311bp + 1;
        this.f311bp = i;
        char charAt = i >= this.len ? JSONLexer.EOI : this.text.charAt(i);
        this.f312ch = charAt;
        return charAt;
    }

    public JSONScanner(char[] cArr, int i) {
        this(cArr, i, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(char[] cArr, int i, int i2) {
        this(new String(cArr, 0, i), i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    protected final void copyTo(int i, int i2, char[] cArr) {
        this.text.getChars(i, i2 + i, cArr, 0);
    }

    static boolean charArrayCompare(String str, int i, char[] cArr) {
        int length = cArr.length;
        if (length + i > str.length()) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (cArr[i2] != str.charAt(i + i2)) {
                return false;
            }
        }
        return true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final boolean charArrayCompare(char[] cArr) {
        return charArrayCompare(this.text, this.f311bp, cArr);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final int indexOf(char c, int i) {
        return this.text.indexOf(c, i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String addSymbol(int i, int i2, int i3, SymbolTable symbolTable) {
        return symbolTable.addSymbol(this.text, i, i2, i3);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public byte[] bytesValue() {
        if (this.token == 26) {
            int i = this.f313np + 1;
            int i2 = this.f314sp;
            if (i2 % 2 != 0) {
                throw new JSONException("illegal state. " + i2);
            }
            int i3 = i2 / 2;
            byte[] bArr = new byte[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                int i5 = (i4 * 2) + i;
                char charAt = this.text.charAt(i5);
                char charAt2 = this.text.charAt(i5 + 1);
                char c = '0';
                int i6 = charAt - (charAt <= '9' ? '0' : '7');
                if (charAt2 > '9') {
                    c = '7';
                }
                bArr[i4] = (byte) ((i6 << 4) | (charAt2 - c));
            }
            return bArr;
        }
        return IOUtils.decodeBase64(this.text, this.f313np + 1, this.f314sp);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String stringVal() {
        if (!this.hasSpecial) {
            return subString(this.f313np + 1, this.f314sp);
        }
        return new String(this.sbuf, 0, this.f314sp);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final String subString(int i, int i2) {
        if (ASMUtils.IS_ANDROID) {
            if (i2 < this.sbuf.length) {
                this.text.getChars(i, i + i2, this.sbuf, 0);
                return new String(this.sbuf, 0, i2);
            }
            char[] cArr = new char[i2];
            this.text.getChars(i, i2 + i, cArr, 0);
            return new String(cArr);
        }
        return this.text.substring(i, i2 + i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public final char[] sub_chars(int i, int i2) {
        if (ASMUtils.IS_ANDROID && i2 < this.sbuf.length) {
            this.text.getChars(i, i2 + i, this.sbuf, 0);
            return this.sbuf;
        }
        char[] cArr = new char[i2];
        this.text.getChars(i, i2 + i, cArr, 0);
        return cArr;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final String numberString() {
        char charAt = charAt((this.f313np + this.f314sp) - 1);
        int i = this.f314sp;
        if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
            i--;
        }
        return subString(this.f313np, i);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final BigDecimal decimalValue() {
        char charAt = charAt((this.f313np + this.f314sp) - 1);
        int i = this.f314sp;
        if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
            i--;
        }
        int i2 = this.f313np;
        if (i < this.sbuf.length) {
            this.text.getChars(i2, i2 + i, this.sbuf, 0);
            return new BigDecimal(this.sbuf, 0, i);
        }
        char[] cArr = new char[i];
        this.text.getChars(i2, i + i2, cArr, 0);
        return new BigDecimal(cArr);
    }

    public boolean scanISO8601DateIfMatch() {
        return scanISO8601DateIfMatch(true);
    }

    public boolean scanISO8601DateIfMatch(boolean z) {
        return scanISO8601DateIfMatch(z, this.len - this.f311bp);
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00e6, code lost:
    
        if (r6 != ' ') goto L47;
     */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01f8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x04be A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x04c0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean scanISO8601DateIfMatch(boolean z, int i) {
        char c;
        char c2;
        int i2;
        char charAt;
        char c3;
        char c4;
        char c5;
        int i3;
        int i4;
        int i5;
        int i6;
        char c6;
        char c7;
        char c8;
        char c9;
        char c10;
        int i7;
        char c11;
        char c12;
        char c13;
        char c14;
        char c15;
        int i8;
        char c16;
        char c17;
        char c18;
        char charAt2;
        int i9;
        int i10;
        char charAt3;
        char c19;
        char c20;
        char charAt4;
        char charAt5;
        if (i < 8) {
            return false;
        }
        char charAt6 = charAt(this.f311bp);
        char charAt7 = charAt(this.f311bp + 1);
        char charAt8 = charAt(this.f311bp + 2);
        int i11 = 3;
        char charAt9 = charAt(this.f311bp + 3);
        char charAt10 = charAt(this.f311bp + 4);
        char charAt11 = charAt(this.f311bp + 5);
        char charAt12 = charAt(this.f311bp + 6);
        char charAt13 = charAt(this.f311bp + 7);
        if (!z && i > 13) {
            char charAt14 = charAt((this.f311bp + i) - 1);
            char charAt15 = charAt((this.f311bp + i) - 2);
            if (charAt6 == '/' && charAt7 == 'D' && charAt8 == 'a' && charAt9 == 't' && charAt10 == 'e' && charAt11 == '(' && charAt14 == '/' && charAt15 == ')') {
                int i12 = -1;
                for (int i13 = 6; i13 < i; i13++) {
                    char charAt16 = charAt(this.f311bp + i13);
                    if (charAt16 != '+') {
                        if (charAt16 < '0' || charAt16 > '9') {
                            break;
                        }
                    } else {
                        i12 = i13;
                    }
                }
                if (i12 == -1) {
                    return false;
                }
                int i14 = this.f311bp + 6;
                long parseLong = Long.parseLong(subString(i14, (this.f311bp + i12) - i14));
                this.calendar = Calendar.getInstance(this.timeZone, this.locale);
                this.calendar.setTimeInMillis(parseLong);
                this.token = 5;
                return true;
            }
        }
        if (i != 8 && i != 14) {
            if (i == 16) {
                char charAt17 = charAt(this.f311bp + 10);
                if (charAt17 != 'T') {
                }
            }
            if (i != 17 || charAt(this.f311bp + 6) == '-') {
                if (i < 9) {
                    return false;
                }
                char charAt18 = charAt(this.f311bp + 8);
                char charAt19 = charAt(this.f311bp + 9);
                if ((charAt10 != '-' || charAt13 != '-') && (charAt10 != '/' || charAt13 != '/')) {
                    if (charAt10 == '-' && charAt12 == '-') {
                        if (charAt18 == ' ') {
                            c17 = charAt8;
                            c18 = charAt11;
                            c12 = charAt13;
                            c13 = charAt9;
                            c14 = charAt6;
                            c15 = charAt7;
                            c11 = '0';
                            c16 = '0';
                            i8 = 8;
                        } else {
                            c17 = charAt8;
                            c12 = charAt18;
                            c14 = charAt6;
                            c11 = '0';
                            i8 = 9;
                            c18 = charAt11;
                            c15 = charAt7;
                            c16 = charAt13;
                            c13 = charAt9;
                        }
                    } else if ((charAt8 == '.' && charAt11 == '.') || (charAt8 == '-' && charAt11 == '-')) {
                        c15 = charAt13;
                        c13 = charAt19;
                        c11 = charAt9;
                        c16 = charAt6;
                        c12 = charAt7;
                        i8 = 10;
                        c18 = charAt10;
                        c14 = charAt12;
                        c17 = charAt18;
                    } else {
                        if (charAt10 != 24180 && charAt10 != 45380) {
                            return false;
                        }
                        if (charAt13 != 26376 && charAt13 != 50900) {
                            if (charAt12 != 26376 && charAt12 != 50900) {
                                return false;
                            }
                            if (charAt18 == 26085 || charAt18 == 51068) {
                                c17 = charAt8;
                                c18 = charAt11;
                                c12 = charAt13;
                                c13 = charAt9;
                                c14 = charAt6;
                                c15 = charAt7;
                                i8 = 10;
                                c11 = '0';
                                c16 = '0';
                            } else {
                                if (charAt19 != 26085 && charAt19 != 51068) {
                                    return false;
                                }
                                c17 = charAt8;
                                c12 = charAt18;
                                c14 = charAt6;
                                i8 = 10;
                                c11 = '0';
                                c18 = charAt11;
                                c15 = charAt7;
                                c16 = charAt13;
                                c13 = charAt9;
                            }
                        } else if (charAt19 == 26085 || charAt19 == 51068) {
                            c11 = charAt11;
                            c12 = charAt18;
                            c13 = charAt9;
                            c14 = charAt6;
                            c15 = charAt7;
                            i8 = 10;
                            c16 = '0';
                            c18 = charAt12;
                            c17 = charAt8;
                        } else {
                            if (charAt(this.f311bp + 10) != 26085 && charAt(this.f311bp + 10) != 51068) {
                                return false;
                            }
                            c12 = charAt19;
                            c13 = charAt9;
                            c14 = charAt6;
                            i8 = 11;
                        }
                    }
                    if (checkDate(c14, c15, c17, c13, c11, c18, c16, c12)) {
                        return false;
                    }
                    setCalendar(c14, c15, c17, c13, c11, c18, c16, c12);
                    char charAt20 = charAt(this.f311bp + i8);
                    if (charAt20 != 'T' && (charAt20 != ' ' || z)) {
                        if (charAt20 == '\"' || charAt20 == 26 || charAt20 == 26085 || charAt20 == 51068) {
                            this.calendar.set(11, 0);
                            this.calendar.set(12, 0);
                            this.calendar.set(13, 0);
                            this.calendar.set(14, 0);
                            int i15 = this.f311bp + i8;
                            this.f311bp = i15;
                            this.f312ch = charAt(i15);
                            this.token = 5;
                            return true;
                        }
                        if ((charAt20 != '+' && charAt20 != '-') || this.len != i8 + 6 || charAt(this.f311bp + i8 + 3) != ':' || charAt(this.f311bp + i8 + 4) != '0' || charAt(this.f311bp + i8 + 5) != '0') {
                            return false;
                        }
                        setTime('0', '0', '0', '0', '0', '0');
                        this.calendar.set(14, 0);
                        setTimeZone(charAt20, charAt(this.f311bp + i8 + 1), charAt(this.f311bp + i8 + 2));
                        return true;
                    }
                    int i16 = i8 + 9;
                    if (i < i16 || charAt(this.f311bp + i8 + 3) != ':' || charAt(this.f311bp + i8 + 6) != ':') {
                        return false;
                    }
                    char charAt21 = charAt(this.f311bp + i8 + 1);
                    char charAt22 = charAt(this.f311bp + i8 + 2);
                    char charAt23 = charAt(this.f311bp + i8 + 4);
                    char charAt24 = charAt(this.f311bp + i8 + 5);
                    char charAt25 = charAt(this.f311bp + i8 + 7);
                    char charAt26 = charAt(this.f311bp + i8 + 8);
                    if (!checkTime(charAt21, charAt22, charAt23, charAt24, charAt25, charAt26)) {
                        return false;
                    }
                    setTime(charAt21, charAt22, charAt23, charAt24, charAt25, charAt26);
                    char charAt27 = charAt(this.f311bp + i8 + 9);
                    if (charAt27 != '.') {
                        this.calendar.set(14, 0);
                        int i17 = this.f311bp + i16;
                        this.f311bp = i17;
                        this.f312ch = charAt(i17);
                        this.token = 5;
                        if (charAt27 != 'Z' || this.calendar.getTimeZone().getRawOffset() == 0) {
                            return true;
                        }
                        String[] availableIDs = TimeZone.getAvailableIDs(0);
                        if (availableIDs.length <= 0) {
                            return true;
                        }
                        this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs[0]));
                        return true;
                    }
                    int i18 = i8 + 11;
                    if (i < i18 || (charAt2 = charAt(this.f311bp + i8 + 10)) < '0' || charAt2 > '9') {
                        return false;
                    }
                    int i19 = charAt2 - '0';
                    if (i <= i18 || (charAt5 = charAt(this.f311bp + i8 + 11)) < '0' || charAt5 > '9') {
                        i9 = 1;
                    } else {
                        i19 = (i19 * 10) + (charAt5 - '0');
                        i9 = 2;
                    }
                    if (i9 != 2 || (charAt4 = charAt(this.f311bp + i8 + 12)) < '0' || charAt4 > '9') {
                        i10 = i9;
                    } else {
                        i19 = (i19 * 10) + (charAt4 - '0');
                        i10 = 3;
                    }
                    this.calendar.set(14, i19);
                    char charAt28 = charAt(this.f311bp + i8 + 10 + i10);
                    if (charAt28 == '+' || charAt28 == '-') {
                        char charAt29 = charAt(this.f311bp + i8 + 10 + i10 + 1);
                        if (charAt29 < '0' || charAt29 > '1' || (charAt3 = charAt(this.f311bp + i8 + 10 + i10 + 2)) < '0' || charAt3 > '9') {
                            return false;
                        }
                        char charAt30 = charAt(this.f311bp + i8 + 10 + i10 + 3);
                        if (charAt30 == ':') {
                            char charAt31 = charAt(this.f311bp + i8 + 10 + i10 + 4);
                            if ((charAt31 != '0' && charAt31 != '3') || (c20 = charAt(this.f311bp + i8 + 10 + i10 + 5)) != '0') {
                                return false;
                            }
                            c19 = charAt31;
                            i11 = 6;
                        } else {
                            if (charAt30 == '0') {
                                char charAt32 = charAt(this.f311bp + i8 + 10 + i10 + 4);
                                if (charAt32 != '0' && charAt32 != '3') {
                                    return false;
                                }
                                c19 = charAt32;
                                i11 = 5;
                            } else {
                                c19 = '0';
                            }
                            c20 = '0';
                        }
                        setTimeZone(charAt28, charAt29, charAt3, c19, c20);
                    } else if (charAt28 == 'Z') {
                        if (this.calendar.getTimeZone().getRawOffset() != 0) {
                            String[] availableIDs2 = TimeZone.getAvailableIDs(0);
                            if (availableIDs2.length > 0) {
                                this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs2[0]));
                            }
                        }
                        i11 = 1;
                    } else {
                        i11 = 0;
                    }
                    int i20 = i8 + 10 + i10 + i11;
                    char charAt33 = charAt(this.f311bp + i20);
                    if (charAt33 != 26 && charAt33 != '\"') {
                        return false;
                    }
                    int i21 = this.f311bp + i20;
                    this.f311bp = i21;
                    this.f312ch = charAt(i21);
                    this.token = 5;
                    return true;
                }
                c12 = charAt19;
                c13 = charAt9;
                c14 = charAt6;
                i8 = 10;
                c11 = charAt11;
                c16 = charAt18;
                c15 = charAt7;
                c18 = charAt12;
                c17 = charAt8;
                if (checkDate(c14, c15, c17, c13, c11, c18, c16, c12)) {
                }
            }
            c = charAt7;
            c2 = '9';
            i2 = 14;
            if (!z) {
                return false;
            }
            char charAt34 = charAt(this.f311bp + 8);
            boolean z2 = charAt10 == '-' && charAt13 == '-';
            boolean z3 = z2 && i == 16;
            boolean z4 = z2 && i == 17;
            if (z4 || z3) {
                charAt = charAt(this.f311bp + 9);
                c3 = charAt11;
                c4 = charAt12;
                c5 = charAt34;
            } else {
                c3 = charAt10;
                c4 = charAt11;
                c5 = charAt12;
                charAt = charAt13;
            }
            int i22 = i2;
            if (!checkDate(charAt6, c, charAt8, charAt9, c3, c4, c5, charAt)) {
                return false;
            }
            setCalendar(charAt6, c, charAt8, charAt9, c3, c4, c5, charAt);
            if (i != 8) {
                char charAt35 = charAt(this.f311bp + 9);
                char charAt36 = charAt(this.f311bp + 10);
                char charAt37 = charAt(this.f311bp + 11);
                char charAt38 = charAt(this.f311bp + 12);
                char charAt39 = charAt(this.f311bp + 13);
                if ((z4 && charAt36 == 'T' && charAt39 == ':' && charAt(this.f311bp + 16) == 'Z') || (z3 && ((charAt36 == ' ' || charAt36 == 'T') && charAt39 == ':'))) {
                    char charAt40 = charAt(this.f311bp + i22);
                    c8 = charAt(this.f311bp + 15);
                    c7 = charAt40;
                    charAt34 = charAt37;
                    c6 = charAt38;
                    c9 = '0';
                    c10 = '0';
                } else {
                    c6 = charAt35;
                    c7 = charAt36;
                    c8 = charAt37;
                    c9 = charAt38;
                    c10 = charAt39;
                }
                if (!checkTime(charAt34, c6, c7, c8, c9, c10)) {
                    return false;
                }
                if (i != 17 || z4) {
                    i7 = 0;
                } else {
                    char charAt41 = charAt(this.f311bp + i22);
                    char charAt42 = charAt(this.f311bp + 15);
                    char charAt43 = charAt(this.f311bp + 16);
                    if (charAt41 < '0' || charAt41 > c2 || charAt42 < '0' || charAt42 > c2 || charAt43 < '0' || charAt43 > c2) {
                        return false;
                    }
                    i7 = ((charAt41 - '0') * 100) + ((charAt42 - '0') * 10) + (charAt43 - '0');
                }
                i4 = ((c9 - '0') * 10) + (c10 - '0');
                i6 = ((charAt34 - '0') * 10) + (c6 - '0');
                i5 = i7;
                i3 = ((c7 - '0') * 10) + (c8 - '0');
            } else {
                i3 = 0;
                i4 = 0;
                i5 = 0;
                i6 = 0;
            }
            this.calendar.set(11, i6);
            this.calendar.set(12, i3);
            this.calendar.set(13, i4);
            this.calendar.set(i22, i5);
            this.token = 5;
            return true;
        }
        c = charAt7;
        c2 = '9';
        i2 = 14;
        if (!z) {
        }
    }

    protected void setTime(char c, char c2, char c3, char c4, char c5, char c6) {
        this.calendar.set(11, ((c - '0') * 10) + (c2 - '0'));
        this.calendar.set(12, ((c3 - '0') * 10) + (c4 - '0'));
        this.calendar.set(13, ((c5 - '0') * 10) + (c6 - '0'));
    }

    protected void setTimeZone(char c, char c2, char c3) {
        setTimeZone(c, c2, c3, '0', '0');
    }

    protected void setTimeZone(char c, char c2, char c3, char c4, char c5) {
        int i = ((((c2 - '0') * 10) + (c3 - '0')) * 3600 * 1000) + ((((c4 - '0') * 10) + (c5 - '0')) * 60 * 1000);
        if (c == '-') {
            i = -i;
        }
        if (this.calendar.getTimeZone().getRawOffset() != i) {
            String[] availableIDs = TimeZone.getAvailableIDs(i);
            if (availableIDs.length > 0) {
                this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs[0]));
            }
        }
    }

    private void setCalendar(char c, char c2, char c3, char c4, char c5, char c6, char c7, char c8) {
        this.calendar = Calendar.getInstance(this.timeZone, this.locale);
        this.calendar.set(1, ((c - '0') * 1000) + ((c2 - '0') * 100) + ((c3 - '0') * 10) + (c4 - '0'));
        this.calendar.set(2, (((c5 - '0') * 10) + (c6 - '0')) - 1);
        this.calendar.set(5, ((c7 - '0') * 10) + (c8 - '0'));
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean isEOF() {
        if (this.f311bp != this.len) {
            return this.f312ch == 26 && this.f311bp + 1 == this.len;
        }
        return true;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public int scanFieldInt(char[] cArr) {
        int i;
        char charAt;
        this.matchStat = 0;
        int i2 = this.f311bp;
        char c = this.f312ch;
        if (!charArrayCompare(this.text, this.f311bp, cArr)) {
            this.matchStat = -2;
            return 0;
        }
        int length = this.f311bp + cArr.length;
        int i3 = length + 1;
        char charAt2 = charAt(length);
        boolean z = charAt2 == '\"';
        if (z) {
            charAt2 = charAt(i3);
            i3++;
        }
        boolean z2 = charAt2 == '-';
        if (z2) {
            charAt2 = charAt(i3);
            i3++;
        }
        if (charAt2 < '0' || charAt2 > '9') {
            this.matchStat = -1;
            return 0;
        }
        int i4 = charAt2 - '0';
        while (true) {
            i = i3 + 1;
            charAt = charAt(i3);
            if (charAt < '0' || charAt > '9') {
                break;
            }
            i4 = (i4 * 10) + (charAt - '0');
            i3 = i;
        }
        if (charAt == '.') {
            this.matchStat = -1;
            return 0;
        }
        if (i4 < 0) {
            this.matchStat = -1;
            return 0;
        }
        if (z) {
            if (charAt != '\"') {
                this.matchStat = -1;
                return 0;
            }
            int i5 = i + 1;
            char charAt3 = charAt(i);
            i = i5;
            charAt = charAt3;
        }
        while (charAt != ',' && charAt != '}') {
            if (isWhitespace(charAt)) {
                int i6 = i + 1;
                char charAt4 = charAt(i);
                i = i6;
                charAt = charAt4;
            } else {
                this.matchStat = -1;
                return 0;
            }
        }
        int i7 = i - 1;
        this.f311bp = i7;
        if (charAt == ',') {
            int i8 = this.f311bp + 1;
            this.f311bp = i8;
            this.f312ch = charAt(i8);
            this.matchStat = 3;
            this.token = 16;
            return z2 ? -i4 : i4;
        }
        if (charAt == '}') {
            this.f311bp = i7;
            int i9 = this.f311bp + 1;
            this.f311bp = i9;
            char charAt5 = charAt(i9);
            while (true) {
                if (charAt5 == ',') {
                    this.token = 16;
                    int i10 = this.f311bp + 1;
                    this.f311bp = i10;
                    this.f312ch = charAt(i10);
                    break;
                }
                if (charAt5 == ']') {
                    this.token = 15;
                    int i11 = this.f311bp + 1;
                    this.f311bp = i11;
                    this.f312ch = charAt(i11);
                    break;
                }
                if (charAt5 == '}') {
                    this.token = 13;
                    int i12 = this.f311bp + 1;
                    this.f311bp = i12;
                    this.f312ch = charAt(i12);
                    break;
                }
                if (charAt5 == 26) {
                    this.token = 20;
                    break;
                }
                if (isWhitespace(charAt5)) {
                    int i13 = this.f311bp + 1;
                    this.f311bp = i13;
                    charAt5 = charAt(i13);
                } else {
                    this.f311bp = i2;
                    this.f312ch = c;
                    this.matchStat = -1;
                    return 0;
                }
            }
            this.matchStat = 4;
        }
        return z2 ? -i4 : i4;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public String scanFieldString(char[] cArr) {
        this.matchStat = 0;
        int i = this.f311bp;
        char c = this.f312ch;
        while (!charArrayCompare(this.text, this.f311bp, cArr)) {
            if (isWhitespace(this.f312ch)) {
                next();
            } else {
                this.matchStat = -2;
                return stringDefaultValue();
            }
        }
        int length = this.f311bp + cArr.length;
        int i2 = length + 1;
        if (charAt(length) != '\"') {
            this.matchStat = -1;
            return stringDefaultValue();
        }
        int indexOf = indexOf('\"', i2);
        if (indexOf == -1) {
            throw new JSONException("unclosed str");
        }
        String subString = subString(i2, indexOf - i2);
        if (subString.indexOf(92) != -1) {
            while (true) {
                int i3 = 0;
                for (int i4 = indexOf - 1; i4 >= 0 && charAt(i4) == '\\'; i4--) {
                    i3++;
                }
                if (i3 % 2 == 0) {
                    break;
                }
                indexOf = indexOf('\"', indexOf + 1);
            }
            int length2 = indexOf - ((this.f311bp + cArr.length) + 1);
            subString = readString(sub_chars(this.f311bp + cArr.length + 1, length2), length2);
        }
        char charAt = charAt(indexOf + 1);
        while (charAt != ',' && charAt != '}') {
            if (isWhitespace(charAt)) {
                indexOf++;
                charAt = charAt(indexOf + 1);
            } else {
                this.matchStat = -1;
                return stringDefaultValue();
            }
        }
        this.f311bp = indexOf + 1;
        this.f312ch = charAt;
        if (charAt == ',') {
            int i5 = this.f311bp + 1;
            this.f311bp = i5;
            this.f312ch = charAt(i5);
            this.matchStat = 3;
            return subString;
        }
        int i6 = this.f311bp + 1;
        this.f311bp = i6;
        char charAt2 = charAt(i6);
        if (charAt2 == ',') {
            this.token = 16;
            int i7 = this.f311bp + 1;
            this.f311bp = i7;
            this.f312ch = charAt(i7);
        } else if (charAt2 == ']') {
            this.token = 15;
            int i8 = this.f311bp + 1;
            this.f311bp = i8;
            this.f312ch = charAt(i8);
        } else if (charAt2 == '}') {
            this.token = 13;
            int i9 = this.f311bp + 1;
            this.f311bp = i9;
            this.f312ch = charAt(i9);
        } else if (charAt2 == 26) {
            this.token = 20;
        } else {
            this.f311bp = i;
            this.f312ch = c;
            this.matchStat = -1;
            return stringDefaultValue();
        }
        this.matchStat = 4;
        return subString;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public Date scanFieldDate(char[] cArr) {
        char c;
        long j;
        char c2;
        Date date;
        int i;
        boolean z = false;
        this.matchStat = 0;
        int i2 = this.f311bp;
        char c3 = this.f312ch;
        if (!charArrayCompare(this.text, this.f311bp, cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = this.f311bp + cArr.length;
        int i3 = length + 1;
        char charAt = charAt(length);
        if (charAt == '\"') {
            int indexOf = indexOf('\"', i3);
            if (indexOf == -1) {
                throw new JSONException("unclosed str");
            }
            this.f311bp = i3;
            if (scanISO8601DateIfMatch(false, indexOf - i3)) {
                date = this.calendar.getTime();
                c2 = charAt(indexOf + 1);
                this.f311bp = i2;
                while (c2 != ',' && c2 != '}') {
                    if (isWhitespace(c2)) {
                        indexOf++;
                        c2 = charAt(indexOf + 1);
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                }
                this.f311bp = indexOf + 1;
                this.f312ch = c2;
            } else {
                this.f311bp = i2;
                this.matchStat = -1;
                return null;
            }
        } else {
            char c4 = '9';
            char c5 = '0';
            if (charAt != '-' && (charAt < '0' || charAt > '9')) {
                this.matchStat = -1;
                return null;
            }
            if (charAt == '-') {
                charAt = charAt(i3);
                i3++;
                z = true;
            }
            if (charAt < '0' || charAt > '9') {
                c = charAt;
                j = 0;
            } else {
                j = charAt - '0';
                while (true) {
                    i = i3 + 1;
                    c = charAt(i3);
                    if (c < c5 || c > c4) {
                        break;
                    }
                    j = (j * 10) + (c - '0');
                    i3 = i;
                    c4 = '9';
                    c5 = '0';
                }
                if (c == ',' || c == '}') {
                    this.f311bp = i - 1;
                }
            }
            if (j < 0) {
                this.matchStat = -1;
                return null;
            }
            if (z) {
                j = -j;
            }
            c2 = c;
            date = new Date(j);
        }
        if (c2 == ',') {
            int i4 = this.f311bp + 1;
            this.f311bp = i4;
            this.f312ch = charAt(i4);
            this.matchStat = 3;
            this.token = 16;
            return date;
        }
        int i5 = this.f311bp + 1;
        this.f311bp = i5;
        char charAt2 = charAt(i5);
        if (charAt2 == ',') {
            this.token = 16;
            int i6 = this.f311bp + 1;
            this.f311bp = i6;
            this.f312ch = charAt(i6);
        } else if (charAt2 == ']') {
            this.token = 15;
            int i7 = this.f311bp + 1;
            this.f311bp = i7;
            this.f312ch = charAt(i7);
        } else if (charAt2 == '}') {
            this.token = 13;
            int i8 = this.f311bp + 1;
            this.f311bp = i8;
            this.f312ch = charAt(i8);
        } else if (charAt2 == 26) {
            this.token = 20;
        } else {
            this.f311bp = i2;
            this.f312ch = c3;
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 4;
        return date;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public long scanFieldSymbol(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(this.text, this.f311bp, cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = this.f311bp + cArr.length;
        int i = length + 1;
        if (charAt(length) != '\"') {
            this.matchStat = -1;
            return 0L;
        }
        long j = -3750763034362895579L;
        while (true) {
            int i2 = i + 1;
            char charAt = charAt(i);
            if (charAt == '\"') {
                this.f311bp = i2;
                char charAt2 = charAt(this.f311bp);
                this.f312ch = charAt2;
                while (charAt2 != ',') {
                    if (charAt2 == '}') {
                        next();
                        skipWhitespace();
                        char current = getCurrent();
                        if (current == ',') {
                            this.token = 16;
                            int i3 = this.f311bp + 1;
                            this.f311bp = i3;
                            this.f312ch = charAt(i3);
                        } else if (current == ']') {
                            this.token = 15;
                            int i4 = this.f311bp + 1;
                            this.f311bp = i4;
                            this.f312ch = charAt(i4);
                        } else if (current == '}') {
                            this.token = 13;
                            int i5 = this.f311bp + 1;
                            this.f311bp = i5;
                            this.f312ch = charAt(i5);
                        } else if (current == 26) {
                            this.token = 20;
                        } else {
                            this.matchStat = -1;
                            return 0L;
                        }
                        this.matchStat = 4;
                        return j;
                    }
                    if (isWhitespace(charAt2)) {
                        int i6 = this.f311bp + 1;
                        this.f311bp = i6;
                        charAt2 = charAt(i6);
                    } else {
                        this.matchStat = -1;
                        return 0L;
                    }
                }
                int i7 = this.f311bp + 1;
                this.f311bp = i7;
                this.f312ch = charAt(i7);
                this.matchStat = 3;
                return j;
            }
            if (i2 > this.len) {
                this.matchStat = -1;
                return 0L;
            }
            j = (j ^ charAt) * 1099511628211L;
            i = i2;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public Collection<String> newCollectionByType(Class<?> cls) {
        if (cls.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (cls.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        try {
            return (Collection) cls.newInstance();
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x00ca, code lost:
    
        if (r1 != ']') goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x00d0, code lost:
    
        if (r3.size() != 0) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x00d2, code lost:
    
        r1 = r5 + 1;
        r2 = charAt(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x00d9, code lost:
    
        r17.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x00db, code lost:
    
        return null;
     */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Collection<String> scanFieldStringArray(char[] cArr, Class<?> cls) {
        char charAt;
        int i;
        boolean z;
        int i2;
        char charAt2;
        int i3 = 0;
        this.matchStat = 0;
        if (!charArrayCompare(this.text, this.f311bp, cArr)) {
            this.matchStat = -2;
            return null;
        }
        Collection<String> newCollectionByType = newCollectionByType(cls);
        int length = this.f311bp + cArr.length;
        int i4 = length + 1;
        if (charAt(length) == '[') {
            int i5 = i4 + 1;
            char charAt3 = charAt(i4);
            while (true) {
                if (charAt3 == '\"') {
                    int indexOf = indexOf('\"', i5);
                    if (indexOf == -1) {
                        throw new JSONException("unclosed str");
                    }
                    String subString = subString(i5, indexOf - i5);
                    if (subString.indexOf(92) != -1) {
                        while (true) {
                            int i6 = i3;
                            for (int i7 = indexOf - 1; i7 >= 0 && charAt(i7) == '\\'; i7--) {
                                i6++;
                            }
                            if (i6 % 2 == 0) {
                                break;
                            }
                            indexOf = indexOf('\"', indexOf + 1);
                            i3 = 0;
                        }
                        int i8 = indexOf - i5;
                        subString = readString(sub_chars(i5, i8), i8);
                    }
                    int i9 = indexOf + 1;
                    i2 = i9 + 1;
                    charAt2 = charAt(i9);
                    newCollectionByType.add(subString);
                } else {
                    if (charAt3 != 'n' || !this.text.startsWith("ull", i5)) {
                        break;
                    }
                    int i10 = i5 + 3;
                    i2 = i10 + 1;
                    charAt2 = charAt(i10);
                    newCollectionByType.add(null);
                }
                if (charAt2 == ',') {
                    i5 = i2 + 1;
                    charAt3 = charAt(i2);
                    i3 = 0;
                } else if (charAt2 == ']') {
                    i = i2 + 1;
                    charAt = charAt(i2);
                    while (isWhitespace(charAt)) {
                        charAt = charAt(i);
                        i++;
                    }
                } else {
                    this.matchStat = -1;
                    return null;
                }
            }
        } else if (this.text.startsWith("ull", i4)) {
            int i11 = i4 + 3;
            newCollectionByType = null;
            charAt = charAt(i11);
            i = i11 + 1;
        } else {
            this.matchStat = -1;
            return null;
        }
        this.f311bp = i;
        if (charAt == ',') {
            this.f312ch = charAt(this.f311bp);
            this.matchStat = 3;
            return newCollectionByType;
        }
        if (charAt == '}') {
            char charAt4 = charAt(this.f311bp);
            do {
                if (charAt4 == ',') {
                    this.token = 16;
                    int i12 = this.f311bp + 1;
                    this.f311bp = i12;
                    this.f312ch = charAt(i12);
                } else if (charAt4 == ']') {
                    this.token = 15;
                    int i13 = this.f311bp + 1;
                    this.f311bp = i13;
                    this.f312ch = charAt(i13);
                } else if (charAt4 == '}') {
                    this.token = 13;
                    int i14 = this.f311bp + 1;
                    this.f311bp = i14;
                    this.f312ch = charAt(i14);
                } else if (charAt4 == 26) {
                    this.token = 20;
                    this.f312ch = charAt4;
                } else {
                    z = false;
                    while (isWhitespace(charAt4)) {
                        int i15 = i + 1;
                        char charAt5 = charAt(i);
                        this.f311bp = i15;
                        z = true;
                        charAt4 = charAt5;
                        i = i15;
                    }
                }
                this.matchStat = 4;
                return newCollectionByType;
            } while (z);
            this.matchStat = -1;
            return null;
        }
        this.matchStat = -1;
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:71:0x0114, code lost:
    
        r20.matchStat = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0117, code lost:
    
        if (r11 == false) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x011a, code lost:
    
        return -r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:?, code lost:
    
        return r2;
     */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public long scanFieldLong(char[] cArr) {
        boolean z;
        int i;
        char charAt;
        this.matchStat = 0;
        int i2 = this.f311bp;
        char c = this.f312ch;
        if (!charArrayCompare(this.text, this.f311bp, cArr)) {
            this.matchStat = -2;
            return 0L;
        }
        int length = this.f311bp + cArr.length;
        int i3 = length + 1;
        char charAt2 = charAt(length);
        boolean z2 = charAt2 == '\"';
        if (z2) {
            charAt2 = charAt(i3);
            i3++;
        }
        if (charAt2 == '-') {
            z = true;
            charAt2 = charAt(i3);
            i3++;
        } else {
            z = false;
        }
        if (charAt2 >= '0') {
            char c2 = '9';
            if (charAt2 <= '9') {
                long j = charAt2 - '0';
                while (true) {
                    i = i3 + 1;
                    charAt = charAt(i3);
                    if (charAt < '0' || charAt > c2) {
                        break;
                    }
                    j = (j * 10) + (charAt - '0');
                    i3 = i;
                    c2 = '9';
                }
                if (charAt == '.') {
                    this.matchStat = -1;
                    return 0L;
                }
                if (z2) {
                    if (charAt != '\"') {
                        this.matchStat = -1;
                        return 0L;
                    }
                    int i4 = i + 1;
                    char charAt3 = charAt(i);
                    i = i4;
                    charAt = charAt3;
                }
                if (charAt == ',' || charAt == '}') {
                    this.f311bp = i - 1;
                }
                if (!(j >= 0 || (j == Long.MIN_VALUE && z))) {
                    this.f311bp = i2;
                    this.f312ch = c;
                    this.matchStat = -1;
                    return 0L;
                }
                while (charAt != ',') {
                    if (charAt == '}') {
                        int i5 = this.f311bp + 1;
                        this.f311bp = i5;
                        char charAt4 = charAt(i5);
                        while (true) {
                            if (charAt4 == ',') {
                                this.token = 16;
                                int i6 = this.f311bp + 1;
                                this.f311bp = i6;
                                this.f312ch = charAt(i6);
                                break;
                            }
                            if (charAt4 == ']') {
                                this.token = 15;
                                int i7 = this.f311bp + 1;
                                this.f311bp = i7;
                                this.f312ch = charAt(i7);
                                break;
                            }
                            if (charAt4 == '}') {
                                this.token = 13;
                                int i8 = this.f311bp + 1;
                                this.f311bp = i8;
                                this.f312ch = charAt(i8);
                                break;
                            }
                            if (charAt4 == 26) {
                                this.token = 20;
                                break;
                            }
                            if (isWhitespace(charAt4)) {
                                int i9 = this.f311bp + 1;
                                this.f311bp = i9;
                                charAt4 = charAt(i9);
                            } else {
                                this.f311bp = i2;
                                this.f312ch = c;
                                this.matchStat = -1;
                                return 0L;
                            }
                        }
                    } else if (isWhitespace(charAt)) {
                        this.f311bp = i;
                        int i10 = i + 1;
                        char charAt5 = charAt(i);
                        i = i10;
                        charAt = charAt5;
                    } else {
                        this.matchStat = -1;
                        return 0L;
                    }
                }
                int i11 = this.f311bp + 1;
                this.f311bp = i11;
                this.f312ch = charAt(i11);
                this.matchStat = 3;
                this.token = 16;
                return z ? -j : j;
            }
        }
        this.f311bp = i2;
        this.f312ch = c;
        this.matchStat = -1;
        return 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x010e  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00fd A[SYNTHETIC] */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean scanFieldBoolean(char[] cArr) {
        char charAt;
        boolean z;
        this.matchStat = 0;
        if (!charArrayCompare(this.text, this.f311bp, cArr)) {
            this.matchStat = -2;
            return false;
        }
        int i = this.f311bp;
        int length = this.f311bp + cArr.length;
        int i2 = length + 1;
        char charAt2 = charAt(length);
        boolean z2 = charAt2 == '\"';
        if (z2) {
            charAt2 = charAt(i2);
            i2++;
        }
        if (charAt2 != 't') {
            if (charAt2 == 'f') {
                int i3 = i2 + 1;
                if (charAt(i2) != 'a') {
                    this.matchStat = -1;
                    return false;
                }
                int i4 = i3 + 1;
                if (charAt(i3) != 'l') {
                    this.matchStat = -1;
                    return false;
                }
                int i5 = i4 + 1;
                if (charAt(i4) != 's') {
                    this.matchStat = -1;
                    return false;
                }
                int i6 = i5 + 1;
                if (charAt(i5) != 'e') {
                    this.matchStat = -1;
                    return false;
                }
                if (z2) {
                    int i7 = i6 + 1;
                    if (charAt(i6) != '\"') {
                        this.matchStat = -1;
                        return false;
                    }
                    i6 = i7;
                }
                this.f311bp = i6;
                charAt = charAt(this.f311bp);
            } else if (charAt2 == '1') {
                if (z2) {
                    int i8 = i2 + 1;
                    if (charAt(i2) != '\"') {
                        this.matchStat = -1;
                        return false;
                    }
                    i2 = i8;
                }
                this.f311bp = i2;
                charAt = charAt(this.f311bp);
            } else if (charAt2 == '0') {
                if (z2) {
                    int i9 = i2 + 1;
                    if (charAt(i2) != '\"') {
                        this.matchStat = -1;
                        return false;
                    }
                    i2 = i9;
                }
                this.f311bp = i2;
                charAt = charAt(this.f311bp);
            } else {
                this.matchStat = -1;
                return false;
            }
            z = false;
            while (true) {
                if (charAt != ',') {
                    int i10 = this.f311bp + 1;
                    this.f311bp = i10;
                    this.f312ch = charAt(i10);
                    this.matchStat = 3;
                    this.token = 16;
                    break;
                }
                if (charAt == '}') {
                    int i11 = this.f311bp + 1;
                    this.f311bp = i11;
                    char charAt3 = charAt(i11);
                    while (true) {
                        if (charAt3 == ',') {
                            this.token = 16;
                            int i12 = this.f311bp + 1;
                            this.f311bp = i12;
                            this.f312ch = charAt(i12);
                            break;
                        }
                        if (charAt3 == ']') {
                            this.token = 15;
                            int i13 = this.f311bp + 1;
                            this.f311bp = i13;
                            this.f312ch = charAt(i13);
                            break;
                        }
                        if (charAt3 == '}') {
                            this.token = 13;
                            int i14 = this.f311bp + 1;
                            this.f311bp = i14;
                            this.f312ch = charAt(i14);
                            break;
                        }
                        if (charAt3 == 26) {
                            this.token = 20;
                            break;
                        }
                        if (isWhitespace(charAt3)) {
                            int i15 = this.f311bp + 1;
                            this.f311bp = i15;
                            charAt3 = charAt(i15);
                        } else {
                            this.matchStat = -1;
                            return false;
                        }
                    }
                    this.matchStat = 4;
                } else if (isWhitespace(charAt)) {
                    int i16 = this.f311bp + 1;
                    this.f311bp = i16;
                    charAt = charAt(i16);
                } else {
                    this.f311bp = i;
                    charAt(this.f311bp);
                    this.matchStat = -1;
                    return false;
                }
            }
            return z;
        }
        int i17 = i2 + 1;
        if (charAt(i2) != 'r') {
            this.matchStat = -1;
            return false;
        }
        int i18 = i17 + 1;
        if (charAt(i17) != 'u') {
            this.matchStat = -1;
            return false;
        }
        int i19 = i18 + 1;
        if (charAt(i18) != 'e') {
            this.matchStat = -1;
            return false;
        }
        if (z2) {
            int i20 = i19 + 1;
            if (charAt(i19) != '\"') {
                this.matchStat = -1;
                return false;
            }
            i19 = i20;
        }
        this.f311bp = i19;
        charAt = charAt(this.f311bp);
        z = true;
        while (true) {
            if (charAt != ',') {
            }
            int i162 = this.f311bp + 1;
            this.f311bp = i162;
            charAt = charAt(i162);
        }
        return z;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public final int scanInt(char c) {
        int i;
        char charAt;
        this.matchStat = 0;
        int i2 = this.f311bp;
        int i3 = i2 + 1;
        char charAt2 = charAt(i2);
        while (isWhitespace(charAt2)) {
            int i4 = i3 + 1;
            char charAt3 = charAt(i3);
            i3 = i4;
            charAt2 = charAt3;
        }
        boolean z = charAt2 == '\"';
        if (z) {
            int i5 = i3 + 1;
            char charAt4 = charAt(i3);
            i3 = i5;
            charAt2 = charAt4;
        }
        boolean z2 = charAt2 == '-';
        if (z2) {
            int i6 = i3 + 1;
            char charAt5 = charAt(i3);
            i3 = i6;
            charAt2 = charAt5;
        }
        if (charAt2 >= '0' && charAt2 <= '9') {
            int i7 = charAt2 - '0';
            while (true) {
                i = i3 + 1;
                charAt = charAt(i3);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                i7 = (i7 * 10) + (charAt - '0');
                i3 = i;
            }
            if (charAt == '.') {
                this.matchStat = -1;
                return 0;
            }
            if (z) {
                if (charAt != '\"') {
                    this.matchStat = -1;
                    return 0;
                }
                char charAt6 = charAt(i);
                i++;
                charAt = charAt6;
            }
            if (i7 < 0) {
                this.matchStat = -1;
                return 0;
            }
            while (charAt != c) {
                if (isWhitespace(charAt)) {
                    charAt = charAt(i);
                    i++;
                } else {
                    this.matchStat = -1;
                    return z2 ? -i7 : i7;
                }
            }
            this.f311bp = i;
            this.f312ch = charAt(this.f311bp);
            this.matchStat = 3;
            this.token = 16;
            return z2 ? -i7 : i7;
        }
        if (charAt2 == 'n') {
            int i8 = i3 + 1;
            if (charAt(i3) == 'u') {
                int i9 = i8 + 1;
                if (charAt(i8) == 'l') {
                    int i10 = i9 + 1;
                    if (charAt(i9) == 'l') {
                        this.matchStat = 5;
                        int i11 = i10 + 1;
                        char charAt7 = charAt(i10);
                        if (z && charAt7 == '\"') {
                            int i12 = i11 + 1;
                            char charAt8 = charAt(i11);
                            i11 = i12;
                            charAt7 = charAt8;
                        }
                        while (charAt7 != ',') {
                            if (charAt7 == ']') {
                                this.f311bp = i11;
                                this.f312ch = charAt(this.f311bp);
                                this.matchStat = 5;
                                this.token = 15;
                                return 0;
                            }
                            if (isWhitespace(charAt7)) {
                                int i13 = i11 + 1;
                                char charAt9 = charAt(i11);
                                i11 = i13;
                                charAt7 = charAt9;
                            } else {
                                this.matchStat = -1;
                                return 0;
                            }
                        }
                        this.f311bp = i11;
                        this.f312ch = charAt(this.f311bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return 0;
                    }
                }
            }
        }
        this.matchStat = -1;
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00c0  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:46:0x00c4 -> B:42:0x00b4). Please report as a decompilation issue!!! */
    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public double scanDouble(char c) {
        int i;
        char charAt;
        long j;
        int i2;
        int i3;
        double parseDouble;
        int i4;
        this.matchStat = 0;
        int i5 = this.f311bp;
        int i6 = i5 + 1;
        char charAt2 = charAt(i5);
        boolean z = charAt2 == '\"';
        if (z) {
            int i7 = i6 + 1;
            char charAt3 = charAt(i6);
            i6 = i7;
            charAt2 = charAt3;
        }
        boolean z2 = charAt2 == '-';
        if (z2) {
            int i8 = i6 + 1;
            char charAt4 = charAt(i6);
            i6 = i8;
            charAt2 = charAt4;
        }
        if (charAt2 >= '0') {
            char c2 = '9';
            if (charAt2 <= '9') {
                long j2 = charAt2 - '0';
                while (true) {
                    i = i6 + 1;
                    charAt = charAt(i6);
                    if (charAt < '0' || charAt > '9') {
                        break;
                    }
                    j2 = (j2 * 10) + (charAt - '0');
                    i6 = i;
                }
                if (charAt == '.') {
                    int i9 = i + 1;
                    char charAt5 = charAt(i);
                    if (charAt5 < '0' || charAt5 > '9') {
                        this.matchStat = -1;
                        return 0.0d;
                    }
                    j2 = (j2 * 10) + (charAt5 - '0');
                    j = 10;
                    while (true) {
                        i4 = i9 + 1;
                        charAt = charAt(i9);
                        if (charAt < '0' || charAt > c2) {
                            break;
                        }
                        j2 = (j2 * 10) + (charAt - '0');
                        j *= 10;
                        i9 = i4;
                        c2 = '9';
                    }
                    i = i4;
                } else {
                    j = 1;
                }
                boolean z3 = charAt == 'e' || charAt == 'E';
                if (z3) {
                    int i10 = i + 1;
                    char charAt6 = charAt(i);
                    if (charAt6 == '+' || charAt6 == '-') {
                        int i11 = i10 + 1;
                        charAt = charAt(i10);
                        i = i11;
                        if (charAt >= '0' && charAt <= '9') {
                            i10 = i + 1;
                            charAt6 = charAt(i);
                        }
                    }
                    i = i10;
                    charAt = charAt6;
                    if (charAt >= '0') {
                        i10 = i + 1;
                        charAt6 = charAt(i);
                        i = i10;
                        charAt = charAt6;
                        if (charAt >= '0') {
                        }
                    }
                }
                if (!z) {
                    i2 = this.f311bp;
                    i3 = (i - i2) - 1;
                } else {
                    if (charAt != '\"') {
                        this.matchStat = -1;
                        return 0.0d;
                    }
                    int i12 = i + 1;
                    char charAt7 = charAt(i);
                    i2 = this.f311bp + 1;
                    i3 = (i12 - i2) - 2;
                    i = i12;
                    charAt = charAt7;
                }
                if (z3 || i3 >= 20) {
                    parseDouble = Double.parseDouble(subString(i2, i3));
                } else {
                    parseDouble = j2 / j;
                    if (z2) {
                        parseDouble = -parseDouble;
                    }
                }
                if (charAt == c) {
                    this.f311bp = i;
                    this.f312ch = charAt(this.f311bp);
                    this.matchStat = 3;
                    this.token = 16;
                    return parseDouble;
                }
                this.matchStat = -1;
                return parseDouble;
            }
        }
        if (charAt2 == 'n') {
            int i13 = i6 + 1;
            if (charAt(i6) == 'u') {
                int i14 = i13 + 1;
                if (charAt(i13) == 'l') {
                    int i15 = i14 + 1;
                    if (charAt(i14) == 'l') {
                        this.matchStat = 5;
                        int i16 = i15 + 1;
                        char charAt8 = charAt(i15);
                        if (z && charAt8 == '\"') {
                            int i17 = i16 + 1;
                            char charAt9 = charAt(i16);
                            i16 = i17;
                            charAt8 = charAt9;
                        }
                        while (charAt8 != ',') {
                            if (charAt8 == ']') {
                                this.f311bp = i16;
                                this.f312ch = charAt(this.f311bp);
                                this.matchStat = 5;
                                this.token = 15;
                                return 0.0d;
                            }
                            if (isWhitespace(charAt8)) {
                                int i18 = i16 + 1;
                                char charAt10 = charAt(i16);
                                i16 = i18;
                                charAt8 = charAt10;
                            } else {
                                this.matchStat = -1;
                                return 0.0d;
                            }
                        }
                        this.f311bp = i16;
                        this.f312ch = charAt(this.f311bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return 0.0d;
                    }
                }
            }
        }
        this.matchStat = -1;
        return 0.0d;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public long scanLong(char c) {
        int i;
        char charAt;
        boolean z = false;
        this.matchStat = 0;
        int i2 = this.f311bp;
        int i3 = i2 + 1;
        char charAt2 = charAt(i2);
        boolean z2 = charAt2 == '\"';
        if (z2) {
            int i4 = i3 + 1;
            char charAt3 = charAt(i3);
            i3 = i4;
            charAt2 = charAt3;
        }
        boolean z3 = charAt2 == '-';
        if (z3) {
            int i5 = i3 + 1;
            char charAt4 = charAt(i3);
            i3 = i5;
            charAt2 = charAt4;
        }
        char c2 = '0';
        if (charAt2 >= '0' && charAt2 <= '9') {
            long j = charAt2 - '0';
            while (true) {
                i = i3 + 1;
                charAt = charAt(i3);
                if (charAt < c2 || charAt > '9') {
                    break;
                }
                j = (j * 10) + (charAt - '0');
                i3 = i;
                c2 = '0';
            }
            if (charAt == '.') {
                this.matchStat = -1;
                return 0L;
            }
            if (z2) {
                if (charAt != '\"') {
                    this.matchStat = -1;
                    return 0L;
                }
                charAt = charAt(i);
                i++;
            }
            if (j >= 0 || (j == Long.MIN_VALUE && z3)) {
                z = true;
            }
            if (!z) {
                this.matchStat = -1;
                return 0L;
            }
            while (charAt != c) {
                if (isWhitespace(charAt)) {
                    charAt = charAt(i);
                    i++;
                } else {
                    this.matchStat = -1;
                    return j;
                }
            }
            this.f311bp = i;
            this.f312ch = charAt(this.f311bp);
            this.matchStat = 3;
            this.token = 16;
            return z3 ? -j : j;
        }
        if (charAt2 == 'n') {
            int i6 = i3 + 1;
            if (charAt(i3) == 'u') {
                int i7 = i6 + 1;
                if (charAt(i6) == 'l') {
                    int i8 = i7 + 1;
                    if (charAt(i7) == 'l') {
                        this.matchStat = 5;
                        int i9 = i8 + 1;
                        char charAt5 = charAt(i8);
                        if (z2 && charAt5 == '\"') {
                            int i10 = i9 + 1;
                            char charAt6 = charAt(i9);
                            i9 = i10;
                            charAt5 = charAt6;
                        }
                        while (charAt5 != ',') {
                            if (charAt5 == ']') {
                                this.f311bp = i9;
                                this.f312ch = charAt(this.f311bp);
                                this.matchStat = 5;
                                this.token = 15;
                                return 0L;
                            }
                            if (isWhitespace(charAt5)) {
                                int i11 = i9 + 1;
                                char charAt7 = charAt(i9);
                                i9 = i11;
                                charAt5 = charAt7;
                            } else {
                                this.matchStat = -1;
                                return 0L;
                            }
                        }
                        this.f311bp = i9;
                        this.f312ch = charAt(this.f311bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return 0L;
                    }
                }
            }
        }
        this.matchStat = -1;
        return 0L;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public Date scanDate(char c) {
        char c2;
        long j;
        Date date;
        int i;
        boolean z = false;
        this.matchStat = 0;
        int i2 = this.f311bp;
        char c3 = this.f312ch;
        int i3 = this.f311bp;
        int i4 = i3 + 1;
        char charAt = charAt(i3);
        if (charAt == '\"') {
            int indexOf = indexOf('\"', i4);
            if (indexOf == -1) {
                throw new JSONException("unclosed str");
            }
            this.f311bp = i4;
            if (scanISO8601DateIfMatch(false, indexOf - i4)) {
                date = this.calendar.getTime();
                c2 = charAt(indexOf + 1);
                this.f311bp = i2;
                while (c2 != ',' && c2 != ']') {
                    if (isWhitespace(c2)) {
                        indexOf++;
                        c2 = charAt(indexOf + 1);
                    } else {
                        this.f311bp = i2;
                        this.f312ch = c3;
                        this.matchStat = -1;
                        return null;
                    }
                }
                this.f311bp = indexOf + 1;
                this.f312ch = c2;
            } else {
                this.f311bp = i2;
                this.f312ch = c3;
                this.matchStat = -1;
                return null;
            }
        } else {
            char c4 = '9';
            char c5 = '0';
            if (charAt != '-' && (charAt < '0' || charAt > '9')) {
                if (charAt == 'n') {
                    int i5 = i4 + 1;
                    if (charAt(i4) == 'u') {
                        int i6 = i5 + 1;
                        if (charAt(i5) == 'l') {
                            int i7 = i6 + 1;
                            if (charAt(i6) == 'l') {
                                c2 = charAt(i7);
                                this.f311bp = i7;
                                date = null;
                            }
                        }
                    }
                }
                this.f311bp = i2;
                this.f312ch = c3;
                this.matchStat = -1;
                return null;
            }
            if (charAt == '-') {
                charAt = charAt(i4);
                i4++;
                z = true;
            }
            if (charAt < '0' || charAt > '9') {
                c2 = charAt;
                j = 0;
            } else {
                j = charAt - '0';
                while (true) {
                    i = i4 + 1;
                    c2 = charAt(i4);
                    if (c2 < c5 || c2 > c4) {
                        break;
                    }
                    j = (j * 10) + (c2 - '0');
                    i4 = i;
                    c4 = '9';
                    c5 = '0';
                }
                if (c2 == ',' || c2 == ']') {
                    this.f311bp = i - 1;
                }
            }
            if (j < 0) {
                this.f311bp = i2;
                this.f312ch = c3;
                this.matchStat = -1;
                return null;
            }
            if (z) {
                j = -j;
            }
            date = new Date(j);
        }
        if (c2 == ',') {
            int i8 = this.f311bp + 1;
            this.f311bp = i8;
            this.f312ch = charAt(i8);
            this.matchStat = 3;
            return date;
        }
        int i9 = this.f311bp + 1;
        this.f311bp = i9;
        char charAt2 = charAt(i9);
        if (charAt2 == ',') {
            this.token = 16;
            int i10 = this.f311bp + 1;
            this.f311bp = i10;
            this.f312ch = charAt(i10);
        } else if (charAt2 == ']') {
            this.token = 15;
            int i11 = this.f311bp + 1;
            this.f311bp = i11;
            this.f312ch = charAt(i11);
        } else if (charAt2 == '}') {
            this.token = 13;
            int i12 = this.f311bp + 1;
            this.f311bp = i12;
            this.f312ch = charAt(i12);
        } else if (charAt2 == 26) {
            this.f312ch = JSONLexer.EOI;
            this.token = 20;
        } else {
            this.f311bp = i2;
            this.f312ch = c3;
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 4;
        return date;
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    protected final void arrayCopy(int i, char[] cArr, int i2, int i3) {
        this.text.getChars(i, i3 + i, cArr, i2);
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase, com.alibaba.fastjson.parser.JSONLexer
    public String info() {
        StringBuilder sb = new StringBuilder();
        sb.append("pos ");
        sb.append(this.f311bp);
        sb.append(", json : ");
        sb.append(this.text.length() < 65536 ? this.text : this.text.substring(0, 65536));
        return sb.toString();
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public String[] scanFieldStringArray(char[] cArr, int i, SymbolTable symbolTable) {
        int i2;
        char c;
        int i3 = this.f311bp;
        char c2 = this.f312ch;
        while (isWhitespace(this.f312ch)) {
            next();
        }
        if (cArr != null) {
            this.matchStat = 0;
            if (!charArrayCompare(cArr)) {
                this.matchStat = -2;
                return null;
            }
            int length = this.f311bp + cArr.length;
            int i4 = length + 1;
            char charAt = this.text.charAt(length);
            while (isWhitespace(charAt)) {
                charAt = this.text.charAt(i4);
                i4++;
            }
            if (charAt == ':') {
                i2 = i4 + 1;
                c = this.text.charAt(i4);
                while (isWhitespace(c)) {
                    c = this.text.charAt(i2);
                    i2++;
                }
            } else {
                this.matchStat = -1;
                return null;
            }
        } else {
            i2 = this.f311bp + 1;
            c = this.f312ch;
        }
        if (c == '[') {
            this.f311bp = i2;
            this.f312ch = this.text.charAt(this.f311bp);
            String[] strArr = i >= 0 ? new String[i] : new String[4];
            int i5 = 0;
            while (true) {
                if (isWhitespace(this.f312ch)) {
                    next();
                } else {
                    if (this.f312ch != '\"') {
                        this.f311bp = i3;
                        this.f312ch = c2;
                        this.matchStat = -1;
                        return null;
                    }
                    String scanSymbol = scanSymbol(symbolTable, '\"');
                    if (i5 == strArr.length) {
                        String[] strArr2 = new String[strArr.length + (strArr.length >> 1) + 1];
                        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
                        strArr = strArr2;
                    }
                    int i6 = i5 + 1;
                    strArr[i5] = scanSymbol;
                    while (isWhitespace(this.f312ch)) {
                        next();
                    }
                    if (this.f312ch == ',') {
                        next();
                        i5 = i6;
                    } else {
                        if (strArr.length != i6) {
                            String[] strArr3 = new String[i6];
                            System.arraycopy(strArr, 0, strArr3, 0, i6);
                            strArr = strArr3;
                        }
                        while (isWhitespace(this.f312ch)) {
                            next();
                        }
                        if (this.f312ch == ']') {
                            next();
                            return strArr;
                        }
                        this.f311bp = i3;
                        this.f312ch = c2;
                        this.matchStat = -1;
                        return null;
                    }
                }
            }
        } else {
            if (c == 'n' && this.text.startsWith("ull", this.f311bp + 1)) {
                this.f311bp += 4;
                this.f312ch = this.text.charAt(this.f311bp);
                return null;
            }
            this.matchStat = -1;
            return null;
        }
    }

    @Override // com.alibaba.fastjson.parser.JSONLexerBase
    public boolean matchField2(char[] cArr) {
        while (isWhitespace(this.f312ch)) {
            next();
        }
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return false;
        }
        int length = this.f311bp + cArr.length;
        int i = length + 1;
        char charAt = this.text.charAt(length);
        while (isWhitespace(charAt)) {
            charAt = this.text.charAt(i);
            i++;
        }
        if (charAt == ':') {
            this.f311bp = i;
            this.f312ch = charAt(this.f311bp);
            return true;
        }
        this.matchStat = -2;
        return false;
    }
}
