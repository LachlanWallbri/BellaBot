package org.mozilla.javascript.json;

import java.util.ArrayList;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class JsonParser {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    /* renamed from: cx */
    private Context f10253cx;
    private int length;
    private int pos;
    private Scriptable scope;
    private String src;

    private int fromHex(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        char c2 = 'A';
        if (c < 'A' || c > 'F') {
            c2 = 'a';
            if (c < 'a' || c > 'f') {
                return -1;
            }
        }
        return (c - c2) + 10;
    }

    public JsonParser(Context context, Scriptable scriptable) {
        this.f10253cx = context;
        this.scope = scriptable;
    }

    public synchronized Object parseValue(String str) throws ParseException {
        Object readValue;
        try {
            if (str == null) {
                throw new ParseException("Input string may not be null");
            }
            this.pos = 0;
            this.length = str.length();
            this.src = str;
            readValue = readValue();
            consumeWhitespace();
            if (this.pos < this.length) {
                throw new ParseException("Expected end of stream at char " + this.pos);
            }
        } catch (Throwable th) {
            throw th;
        }
        return readValue;
    }

    private Object readValue() throws ParseException {
        consumeWhitespace();
        int i = this.pos;
        if (i < this.length) {
            String str = this.src;
            this.pos = i + 1;
            char charAt = str.charAt(i);
            if (charAt == '\"') {
                return readString();
            }
            if (charAt != '-') {
                if (charAt == '[') {
                    return readArray();
                }
                if (charAt == 'f') {
                    return readFalse();
                }
                if (charAt == 'n') {
                    return readNull();
                }
                if (charAt == 't') {
                    return readTrue();
                }
                if (charAt == '{') {
                    return readObject();
                }
                switch (charAt) {
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                        break;
                    default:
                        throw new ParseException("Unexpected token: " + charAt);
                }
            }
            return readNumber(charAt);
        }
        throw new ParseException("Empty JSON string");
    }

    private Object readObject() throws ParseException {
        consumeWhitespace();
        Scriptable newObject = this.f10253cx.newObject(this.scope);
        int i = this.pos;
        if (i < this.length && this.src.charAt(i) == '}') {
            this.pos++;
            return newObject;
        }
        boolean z = false;
        while (true) {
            int i2 = this.pos;
            if (i2 < this.length) {
                String str = this.src;
                this.pos = i2 + 1;
                char charAt = str.charAt(i2);
                if (charAt != '\"') {
                    if (charAt != ',') {
                        if (charAt != '}') {
                            throw new ParseException("Unexpected token in object literal");
                        }
                        if (z) {
                            return newObject;
                        }
                        throw new ParseException("Unexpected comma in object literal");
                    }
                    if (!z) {
                        throw new ParseException("Unexpected comma in object literal");
                    }
                    z = false;
                } else {
                    if (z) {
                        throw new ParseException("Missing comma in object literal");
                    }
                    String readString = readString();
                    consume(':');
                    Object readValue = readValue();
                    long indexFromString = ScriptRuntime.indexFromString(readString);
                    if (indexFromString < 0) {
                        newObject.put(readString, newObject, readValue);
                    } else {
                        newObject.put((int) indexFromString, newObject, readValue);
                    }
                    z = true;
                }
                consumeWhitespace();
            } else {
                throw new ParseException("Unterminated object literal");
            }
        }
    }

    private Object readArray() throws ParseException {
        consumeWhitespace();
        int i = this.pos;
        if (i < this.length && this.src.charAt(i) == ']') {
            this.pos++;
            return this.f10253cx.newArray(this.scope, 0);
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (true) {
            int i2 = this.pos;
            if (i2 < this.length) {
                char charAt = this.src.charAt(i2);
                if (charAt != ',') {
                    if (charAt == ']') {
                        if (!z) {
                            throw new ParseException("Unexpected comma in array literal");
                        }
                        this.pos++;
                        return this.f10253cx.newArray(this.scope, arrayList.toArray());
                    }
                    if (z) {
                        throw new ParseException("Missing comma in array literal");
                    }
                    arrayList.add(readValue());
                    z = true;
                } else {
                    if (!z) {
                        throw new ParseException("Unexpected comma in array literal");
                    }
                    this.pos++;
                    z = false;
                }
                consumeWhitespace();
            } else {
                throw new ParseException("Unterminated array literal");
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x0037, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private String readString() throws ParseException {
        char charAt;
        char charAt2;
        int i = this.pos;
        do {
            int i2 = this.pos;
            if (i2 < this.length) {
                String str = this.src;
                this.pos = i2 + 1;
                charAt2 = str.charAt(i2);
                if (charAt2 <= 31) {
                    throw new ParseException("String contains control character");
                }
                if (charAt2 == '\\') {
                }
            }
            StringBuilder sb = new StringBuilder();
            while (true) {
                int i3 = this.pos;
                if (i3 < this.length) {
                    sb.append((CharSequence) this.src, i, i3 - 1);
                    int i4 = this.pos;
                    if (i4 >= this.length) {
                        throw new ParseException("Unterminated string");
                    }
                    String str2 = this.src;
                    this.pos = i4 + 1;
                    char charAt3 = str2.charAt(i4);
                    if (charAt3 == '\"') {
                        sb.append('\"');
                    } else if (charAt3 == '/') {
                        sb.append('/');
                    } else if (charAt3 == '\\') {
                        sb.append('\\');
                    } else if (charAt3 == 'b') {
                        sb.append('\b');
                    } else if (charAt3 == 'f') {
                        sb.append('\f');
                    } else if (charAt3 == 'n') {
                        sb.append('\n');
                    } else if (charAt3 == 'r') {
                        sb.append('\r');
                    } else if (charAt3 == 't') {
                        sb.append('\t');
                    } else if (charAt3 == 'u') {
                        int i5 = this.length;
                        int i6 = this.pos;
                        if (i5 - i6 < 5) {
                            throw new ParseException("Invalid character code: \\u" + this.src.substring(this.pos));
                        }
                        int fromHex = (fromHex(this.src.charAt(i6 + 0)) << 12) | (fromHex(this.src.charAt(this.pos + 1)) << 8) | (fromHex(this.src.charAt(this.pos + 2)) << 4) | fromHex(this.src.charAt(this.pos + 3));
                        if (fromHex < 0) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("Invalid character code: ");
                            String str3 = this.src;
                            int i7 = this.pos;
                            sb2.append(str3.substring(i7, i7 + 4));
                            throw new ParseException(sb2.toString());
                        }
                        this.pos += 4;
                        sb.append((char) fromHex);
                    } else {
                        throw new ParseException("Unexpected character in string: '\\" + charAt3 + "'");
                    }
                    i = this.pos;
                    do {
                        int i8 = this.pos;
                        if (i8 < this.length) {
                            String str4 = this.src;
                            this.pos = i8 + 1;
                            charAt = str4.charAt(i8);
                            if (charAt <= 31) {
                                throw new ParseException("String contains control character");
                            }
                            if (charAt == '\\') {
                                break;
                            }
                        }
                    } while (charAt != '\"');
                    sb.append((CharSequence) this.src, i, this.pos - 1);
                    return sb.toString();
                }
                throw new ParseException("Unterminated string literal");
            }
        } while (charAt2 != '\"');
        return this.src.substring(i, this.pos - 1);
    }

    private Number readNumber(char c) throws ParseException {
        char charAt;
        int i = this.pos - 1;
        if (c == '-' && ((c = nextOrNumberError(i)) < '0' || c > '9')) {
            throw numberError(i, this.pos);
        }
        if (c != '0') {
            readDigits();
        }
        int i2 = this.pos;
        if (i2 < this.length && this.src.charAt(i2) == '.') {
            this.pos++;
            char nextOrNumberError = nextOrNumberError(i);
            if (nextOrNumberError < '0' || nextOrNumberError > '9') {
                throw numberError(i, this.pos);
            }
            readDigits();
        }
        int i3 = this.pos;
        if (i3 < this.length && ((charAt = this.src.charAt(i3)) == 'e' || charAt == 'E')) {
            this.pos++;
            char nextOrNumberError2 = nextOrNumberError(i);
            if (nextOrNumberError2 == '-' || nextOrNumberError2 == '+') {
                nextOrNumberError2 = nextOrNumberError(i);
            }
            if (nextOrNumberError2 < '0' || nextOrNumberError2 > '9') {
                throw numberError(i, this.pos);
            }
            readDigits();
        }
        double parseDouble = Double.parseDouble(this.src.substring(i, this.pos));
        int i4 = (int) parseDouble;
        if (i4 == parseDouble) {
            return Integer.valueOf(i4);
        }
        return Double.valueOf(parseDouble);
    }

    private ParseException numberError(int i, int i2) {
        return new ParseException("Unsupported number format: " + this.src.substring(i, i2));
    }

    private char nextOrNumberError(int i) throws ParseException {
        int i2 = this.pos;
        int i3 = this.length;
        if (i2 >= i3) {
            throw numberError(i, i3);
        }
        String str = this.src;
        this.pos = i2 + 1;
        return str.charAt(i2);
    }

    private void readDigits() {
        char charAt;
        while (true) {
            int i = this.pos;
            if (i >= this.length || (charAt = this.src.charAt(i)) < '0' || charAt > '9') {
                return;
            } else {
                this.pos++;
            }
        }
    }

    private Boolean readTrue() throws ParseException {
        int i = this.length;
        int i2 = this.pos;
        if (i - i2 < 3 || this.src.charAt(i2) != 'r' || this.src.charAt(this.pos + 1) != 'u' || this.src.charAt(this.pos + 2) != 'e') {
            throw new ParseException("Unexpected token: t");
        }
        this.pos += 3;
        return Boolean.TRUE;
    }

    private Boolean readFalse() throws ParseException {
        int i = this.length;
        int i2 = this.pos;
        if (i - i2 < 4 || this.src.charAt(i2) != 'a' || this.src.charAt(this.pos + 1) != 'l' || this.src.charAt(this.pos + 2) != 's' || this.src.charAt(this.pos + 3) != 'e') {
            throw new ParseException("Unexpected token: f");
        }
        this.pos += 4;
        return Boolean.FALSE;
    }

    private Object readNull() throws ParseException {
        int i = this.length;
        int i2 = this.pos;
        if (i - i2 < 3 || this.src.charAt(i2) != 'u' || this.src.charAt(this.pos + 1) != 'l' || this.src.charAt(this.pos + 2) != 'l') {
            throw new ParseException("Unexpected token: n");
        }
        this.pos += 3;
        return null;
    }

    private void consumeWhitespace() {
        while (true) {
            int i = this.pos;
            if (i >= this.length) {
                return;
            }
            char charAt = this.src.charAt(i);
            if (charAt != '\t' && charAt != '\n' && charAt != '\r' && charAt != ' ') {
                return;
            } else {
                this.pos++;
            }
        }
    }

    private void consume(char c) throws ParseException {
        consumeWhitespace();
        int i = this.pos;
        if (i >= this.length) {
            throw new ParseException("Expected " + c + " but reached end of stream");
        }
        String str = this.src;
        this.pos = i + 1;
        char charAt = str.charAt(i);
        if (charAt == c) {
            return;
        }
        throw new ParseException("Expected " + c + " found " + charAt);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes8.dex
     */
    /* loaded from: classes2.dex */
    public static class ParseException extends Exception {
        static final long serialVersionUID = 4804542791749920772L;

        ParseException(String str) {
            super(str);
        }

        ParseException(Exception exc) {
            super(exc);
        }
    }
}
