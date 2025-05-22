package org.mozilla.javascript;

import kotlin.text.Typography;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class Decompiler {
    public static final int CASE_GAP_PROP = 3;
    private static final int FUNCTION_END = 167;
    public static final int INDENT_GAP_PROP = 2;
    public static final int INITIAL_INDENT_PROP = 1;
    public static final int ONLY_BODY_FLAG = 1;
    public static final int TO_SOURCE_FLAG = 2;
    private static final boolean printSource = false;
    private char[] sourceBuffer = new char[128];
    private int sourceTop;

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getEncodedSource() {
        return sourceToString(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getCurrentOffset() {
        return this.sourceTop;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int markFunctionStart(int i) {
        int currentOffset = getCurrentOffset();
        if (i != 4) {
            addToken(110);
            append((char) i);
        }
        return currentOffset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int markFunctionEnd(int i) {
        int currentOffset = getCurrentOffset();
        append(Typography.section);
        return currentOffset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addToken(int i) {
        if (i < 0 || i > 166) {
            throw new IllegalArgumentException();
        }
        append((char) i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addEOL(int i) {
        if (i < 0 || i > 166) {
            throw new IllegalArgumentException();
        }
        append((char) i);
        append((char) 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addName(String str) {
        addToken(39);
        appendString(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addString(String str) {
        addToken(41);
        appendString(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addRegexp(String str, String str2) {
        addToken(48);
        appendString('/' + str + '/' + str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addNumber(double d) {
        addToken(40);
        long j = (long) d;
        if (j != d) {
            long doubleToLongBits = Double.doubleToLongBits(d);
            append('D');
            append((char) (doubleToLongBits >> 48));
            append((char) (doubleToLongBits >> 32));
            append((char) (doubleToLongBits >> 16));
            append((char) doubleToLongBits);
            return;
        }
        if (j < 0) {
            Kit.codeBug();
        }
        if (j <= 65535) {
            append('S');
            append((char) j);
            return;
        }
        append('J');
        append((char) (j >> 48));
        append((char) (j >> 32));
        append((char) (j >> 16));
        append((char) j);
    }

    private void appendString(String str) {
        int length = str.length();
        int i = this.sourceTop + (length >= 32768 ? 2 : 1) + length;
        if (i > this.sourceBuffer.length) {
            increaseSourceCapacity(i);
        }
        if (length >= 32768) {
            char[] cArr = this.sourceBuffer;
            int i2 = this.sourceTop;
            cArr[i2] = (char) (32768 | (length >>> 16));
            this.sourceTop = i2 + 1;
        }
        char[] cArr2 = this.sourceBuffer;
        int i3 = this.sourceTop;
        cArr2[i3] = (char) length;
        int i4 = i3 + 1;
        this.sourceTop = i4;
        str.getChars(0, length, cArr2, i4);
        this.sourceTop = i;
    }

    private void append(char c) {
        int i = this.sourceTop;
        if (i == this.sourceBuffer.length) {
            increaseSourceCapacity(i + 1);
        }
        char[] cArr = this.sourceBuffer;
        int i2 = this.sourceTop;
        cArr[i2] = c;
        this.sourceTop = i2 + 1;
    }

    private void increaseSourceCapacity(int i) {
        if (i <= this.sourceBuffer.length) {
            Kit.codeBug();
        }
        int length = this.sourceBuffer.length * 2;
        if (length >= i) {
            i = length;
        }
        char[] cArr = new char[i];
        System.arraycopy(this.sourceBuffer, 0, cArr, 0, this.sourceTop);
        this.sourceBuffer = cArr;
    }

    private String sourceToString(int i) {
        if (i < 0 || this.sourceTop < i) {
            Kit.codeBug();
        }
        return new String(this.sourceBuffer, i, this.sourceTop - i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:145:0x022c, code lost:
    
        if (r7 != 167) goto L229;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:57:0x00a3. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:58:0x00a6. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:69:0x00a9. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:70:0x00ac. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:71:0x00af. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static String decompile(String str, int i, UintMap uintMap) {
        char charAt;
        int i2;
        boolean z;
        int i3;
        char c;
        int length = str.length();
        if (length == 0) {
            return "";
        }
        int i4 = uintMap.getInt(1, 0);
        if (i4 < 0) {
            throw new IllegalArgumentException();
        }
        char c2 = 4;
        int i5 = uintMap.getInt(2, 4);
        if (i5 < 0) {
            throw new IllegalArgumentException();
        }
        int i6 = uintMap.getInt(3, 2);
        if (i6 < 0) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder();
        boolean z2 = (i & 1) != 0;
        boolean z3 = (i & 2) != 0;
        if (str.charAt(0) == 137) {
            charAt = 65535;
            i2 = 1;
        } else {
            charAt = str.charAt(1);
            i2 = 0;
        }
        if (!z3) {
            sb.append('\n');
            for (int i7 = 0; i7 < i4; i7++) {
                sb.append(' ');
            }
        } else if (charAt == 2) {
            sb.append('(');
        }
        int i8 = 0;
        boolean z4 = false;
        while (i2 < length) {
            char charAt2 = str.charAt(i2);
            if (charAt2 != 1) {
                if (charAt2 == c2) {
                    sb.append("return");
                    if (83 != getNext(str, length, i2)) {
                        sb.append(' ');
                    }
                } else if (charAt2 == '2') {
                    sb.append("throw ");
                } else if (charAt2 == 'C') {
                    sb.append(": ");
                } else if (charAt2 == 'I') {
                    sb.append("yield ");
                } else if (charAt2 == 161) {
                    sb.append("debugger;\n");
                } else if (charAt2 != 167) {
                    if (charAt2 == '4') {
                        sb.append(" in ");
                    } else if (charAt2 == '5') {
                        sb.append(" instanceof ");
                    } else if (charAt2 == 144) {
                        sb.append("..");
                    } else if (charAt2 == 145) {
                        sb.append("::");
                    } else if (charAt2 == 147) {
                        sb.append(".(");
                    } else if (charAt2 != 148) {
                        if (charAt2 != 164) {
                            if (charAt2 != 165) {
                                switch (charAt2) {
                                    case '\t':
                                        sb.append(" | ");
                                        break;
                                    case '\n':
                                        sb.append(" ^ ");
                                        break;
                                    case 11:
                                        sb.append(" & ");
                                        break;
                                    case '\f':
                                        sb.append(" == ");
                                        break;
                                    case '\r':
                                        sb.append(" != ");
                                        break;
                                    case 14:
                                        sb.append(" < ");
                                        break;
                                    case 15:
                                        sb.append(" <= ");
                                        break;
                                    case 16:
                                        sb.append(" > ");
                                        break;
                                    case 17:
                                        sb.append(" >= ");
                                        break;
                                    case 18:
                                        sb.append(" << ");
                                        break;
                                    case 19:
                                        sb.append(" >> ");
                                        break;
                                    case 20:
                                        sb.append(" >>> ");
                                        break;
                                    case 21:
                                        sb.append(" + ");
                                        break;
                                    case 22:
                                        sb.append(" - ");
                                        break;
                                    case 23:
                                        sb.append(" * ");
                                        break;
                                    case 24:
                                        sb.append(" / ");
                                        break;
                                    case 25:
                                        sb.append(" % ");
                                        break;
                                    case 26:
                                        sb.append('!');
                                        break;
                                    case 27:
                                        sb.append('~');
                                        break;
                                    case 28:
                                        sb.append('+');
                                        break;
                                    case 29:
                                        sb.append(Soundex.SILENT_MARKER);
                                        break;
                                    case 30:
                                        sb.append("new ");
                                        break;
                                    case 31:
                                        sb.append("delete ");
                                        break;
                                    case ' ':
                                        sb.append("typeof ");
                                        break;
                                    default:
                                        switch (charAt2) {
                                            case '\'':
                                            case '0':
                                                c = '(';
                                                i2 = printSourceString(str, i2 + 1, false, sb);
                                                c2 = 4;
                                            case '(':
                                                c = '(';
                                                i2 = printSourceNumber(str, i2 + 1, sb);
                                                c2 = 4;
                                            case ')':
                                                c = '(';
                                                i2 = printSourceString(str, i2 + 1, true, sb);
                                                c2 = 4;
                                            case '*':
                                                sb.append("null");
                                                break;
                                            case '+':
                                                sb.append("this");
                                                break;
                                            case ',':
                                                sb.append("false");
                                                break;
                                            case '-':
                                                sb.append("true");
                                                break;
                                            case '.':
                                                sb.append(" === ");
                                                break;
                                            case '/':
                                                sb.append(" !== ");
                                                break;
                                            default:
                                                switch (charAt2) {
                                                    case 'R':
                                                        sb.append("try ");
                                                        break;
                                                    case 'S':
                                                        sb.append(';');
                                                        if (1 != getNext(str, length, i2)) {
                                                            sb.append(' ');
                                                            break;
                                                        }
                                                        break;
                                                    case 'T':
                                                        sb.append('[');
                                                        break;
                                                    case 'U':
                                                        sb.append(']');
                                                        break;
                                                    case 'V':
                                                        i8++;
                                                        if (1 == getNext(str, length, i2)) {
                                                            i4 += i5;
                                                        }
                                                        sb.append('{');
                                                        break;
                                                    case 'W':
                                                        i8--;
                                                        if (!z2 || i8 != 0) {
                                                            sb.append('}');
                                                            int next = getNext(str, length, i2);
                                                            if (next != 1) {
                                                                if (next != 114 && next != 118) {
                                                                    break;
                                                                } else {
                                                                    i4 -= i5;
                                                                    sb.append(' ');
                                                                    break;
                                                                }
                                                            }
                                                            i4 -= i5;
                                                            break;
                                                        }
                                                        break;
                                                    case 'X':
                                                        sb.append('(');
                                                        break;
                                                    case 'Y':
                                                        sb.append(')');
                                                        if (86 == getNext(str, length, i2)) {
                                                            sb.append(' ');
                                                            break;
                                                        }
                                                        break;
                                                    case 'Z':
                                                        sb.append(", ");
                                                        break;
                                                    case '[':
                                                        sb.append(" = ");
                                                        break;
                                                    case '\\':
                                                        sb.append(" |= ");
                                                        break;
                                                    case ']':
                                                        sb.append(" ^= ");
                                                        break;
                                                    case '^':
                                                        sb.append(" &= ");
                                                        break;
                                                    case '_':
                                                        sb.append(" <<= ");
                                                        break;
                                                    case '`':
                                                        sb.append(" >>= ");
                                                        break;
                                                    case 'a':
                                                        sb.append(" >>>= ");
                                                        break;
                                                    case 'b':
                                                        sb.append(" += ");
                                                        break;
                                                    case 'c':
                                                        sb.append(" -= ");
                                                        break;
                                                    case 'd':
                                                        sb.append(" *= ");
                                                        break;
                                                    case 'e':
                                                        sb.append(" /= ");
                                                        break;
                                                    case 'f':
                                                        sb.append(" %= ");
                                                        break;
                                                    case 'g':
                                                        sb.append(" ? ");
                                                        break;
                                                    case 'h':
                                                        if (1 == getNext(str, length, i2)) {
                                                            sb.append(':');
                                                            break;
                                                        } else {
                                                            sb.append(" : ");
                                                            break;
                                                        }
                                                    case 'i':
                                                        sb.append(" || ");
                                                        break;
                                                    case 'j':
                                                        sb.append(" && ");
                                                        break;
                                                    case 'k':
                                                        sb.append("++");
                                                        break;
                                                    case 'l':
                                                        sb.append("--");
                                                        break;
                                                    case 'm':
                                                        sb.append(FilenameUtils.EXTENSION_SEPARATOR);
                                                        break;
                                                    case 'n':
                                                        i2++;
                                                        sb.append("function ");
                                                        break;
                                                    default:
                                                        switch (charAt2) {
                                                            case 'q':
                                                                sb.append("if ");
                                                                break;
                                                            case 'r':
                                                                sb.append("else ");
                                                                break;
                                                            case 's':
                                                                sb.append("switch ");
                                                                break;
                                                            case 't':
                                                                sb.append("case ");
                                                                break;
                                                            case 'u':
                                                                sb.append("default");
                                                                break;
                                                            case 'v':
                                                                sb.append("while ");
                                                                break;
                                                            case 'w':
                                                                sb.append("do ");
                                                                break;
                                                            case 'x':
                                                                sb.append("for ");
                                                                break;
                                                            case 'y':
                                                                sb.append("break");
                                                                if (39 == getNext(str, length, i2)) {
                                                                    sb.append(' ');
                                                                    break;
                                                                }
                                                                break;
                                                            case 'z':
                                                                sb.append("continue");
                                                                if (39 == getNext(str, length, i2)) {
                                                                    sb.append(' ');
                                                                    break;
                                                                }
                                                                break;
                                                            case '{':
                                                                sb.append("var ");
                                                                break;
                                                            case '|':
                                                                sb.append("with ");
                                                                break;
                                                            case '}':
                                                                sb.append("catch ");
                                                                break;
                                                            case '~':
                                                                sb.append("finally ");
                                                                break;
                                                            case 127:
                                                                sb.append("void ");
                                                                break;
                                                            default:
                                                                switch (charAt2) {
                                                                    case 152:
                                                                    case 153:
                                                                        break;
                                                                    case 154:
                                                                        sb.append("let ");
                                                                        break;
                                                                    case 155:
                                                                        sb.append("const ");
                                                                        break;
                                                                    default:
                                                                        throw new RuntimeException("Token: " + Token.name(str.charAt(i2)));
                                                                }
                                                        }
                                                }
                                        }
                                        break;
                                }
                            } else {
                                sb.append(" => ");
                            }
                        }
                        if (str.charAt(i2) == 152) {
                            sb.append("get ");
                        } else if (str.charAt(i2) == 153) {
                            sb.append("set ");
                        }
                        i2 = printSourceString(str, i2 + 1 + 1, false, sb) + 1;
                    } else {
                        sb.append('@');
                    }
                }
            } else if (!z3) {
                if (z4) {
                    z = true;
                } else if (z2) {
                    sb.setLength(0);
                    i4 -= i5;
                    z4 = true;
                    z = false;
                } else {
                    z = true;
                    z4 = true;
                }
                if (z) {
                    sb.append('\n');
                }
                int i9 = i2 + 1;
                if (i9 < length) {
                    char charAt3 = str.charAt(i9);
                    if (charAt3 == 't' || charAt3 == 'u') {
                        i3 = i5 - i6;
                    } else {
                        i3 = (charAt3 == 'W' || (charAt3 == '\'' && str.charAt(getSourceStringEnd(str, i2 + 2)) == 'h')) ? i5 : 0;
                    }
                    while (i3 < i4) {
                        sb.append(' ');
                        i3++;
                    }
                }
            }
            i2++;
            c2 = 4;
        }
        if (z3) {
            if (charAt == 2) {
                sb.append(')');
            }
        } else if (!z2) {
            sb.append('\n');
        }
        return sb.toString();
    }

    private static int getNext(String str, int i, int i2) {
        int i3 = i2 + 1;
        if (i3 < i) {
            return str.charAt(i3);
        }
        return 0;
    }

    private static int getSourceStringEnd(String str, int i) {
        return printSourceString(str, i, false, null);
    }

    private static int printSourceString(String str, int i, boolean z, StringBuilder sb) {
        int charAt = str.charAt(i);
        int i2 = i + 1;
        if ((32768 & charAt) != 0) {
            charAt = ((charAt & 32767) << 16) | str.charAt(i2);
            i2++;
        }
        if (sb != null) {
            String substring = str.substring(i2, i2 + charAt);
            if (!z) {
                sb.append(substring);
            } else {
                sb.append('\"');
                sb.append(ScriptRuntime.escapeString(substring));
                sb.append('\"');
            }
        }
        return i2 + charAt;
    }

    private static int printSourceNumber(String str, int i, StringBuilder sb) {
        int i2;
        char charAt = str.charAt(i);
        int i3 = i + 1;
        if (charAt == 'S') {
            r1 = sb != null ? str.charAt(i3) : 0.0d;
            i2 = i3 + 1;
        } else {
            if (charAt != 'J' && charAt != 'D') {
                throw new RuntimeException();
            }
            if (sb != null) {
                long charAt2 = (str.charAt(i3) << 48) | (str.charAt(i3 + 1) << 32) | (str.charAt(i3 + 2) << 16) | str.charAt(i3 + 3);
                r1 = charAt == 'J' ? charAt2 : Double.longBitsToDouble(charAt2);
            }
            i2 = i3 + 4;
        }
        if (sb != null) {
            sb.append(ScriptRuntime.numberToString(r1, 10));
        }
        return i2;
    }
}
