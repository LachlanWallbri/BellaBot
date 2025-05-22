package io.netty.util.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class StringUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final char CARRIAGE_RETURN = '\r';
    public static final char COMMA = ',';
    private static final int CSV_NUMBER_ESCAPE_CHARACTERS = 7;
    public static final char DOUBLE_QUOTE = '\"';
    public static final String EMPTY_STRING = "";
    public static final char LINE_FEED = '\n';
    private static final char PACKAGE_SEPARATOR_CHAR = '.';
    public static final char SPACE = ' ';
    public static final char TAB = '\t';
    public static final String NEWLINE = SystemPropertyUtil.get("line.separator", "\n");
    private static final String[] BYTE2HEX_PAD = new String[256];
    private static final String[] BYTE2HEX_NOPAD = new String[256];

    public static int decodeHexNibble(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - '7';
        }
        if (c < 'a' || c > 'f') {
            return -1;
        }
        return c - 'W';
    }

    private static boolean isDoubleQuote(char c) {
        return c == '\"';
    }

    private static boolean isOws(char c) {
        return c == ' ' || c == '\t';
    }

    public static boolean isSurrogate(char c) {
        return c >= 55296 && c <= 57343;
    }

    static {
        for (int i = 0; i < BYTE2HEX_PAD.length; i++) {
            String hexString = Integer.toHexString(i);
            BYTE2HEX_PAD[i] = i > 15 ? hexString : '0' + hexString;
            BYTE2HEX_NOPAD[i] = hexString;
        }
    }

    private StringUtil() {
    }

    public static String substringAfter(String str, char c) {
        int indexOf = str.indexOf(c);
        if (indexOf >= 0) {
            return str.substring(indexOf + 1);
        }
        return null;
    }

    public static boolean commonSuffixOfLength(String str, String str2, int i) {
        return str != null && str2 != null && i >= 0 && str.regionMatches(str.length() - i, str2, str2.length() - i, i);
    }

    public static String byteToHexStringPadded(int i) {
        return BYTE2HEX_PAD[i & 255];
    }

    public static <T extends Appendable> T byteToHexStringPadded(T t, int i) {
        try {
            t.append(byteToHexStringPadded(i));
        } catch (IOException e) {
            PlatformDependent.throwException(e);
        }
        return t;
    }

    public static String toHexStringPadded(byte[] bArr) {
        return toHexStringPadded(bArr, 0, bArr.length);
    }

    public static String toHexStringPadded(byte[] bArr, int i, int i2) {
        return ((StringBuilder) toHexStringPadded(new StringBuilder(i2 << 1), bArr, i, i2)).toString();
    }

    public static <T extends Appendable> T toHexStringPadded(T t, byte[] bArr) {
        return (T) toHexStringPadded(t, bArr, 0, bArr.length);
    }

    public static <T extends Appendable> T toHexStringPadded(T t, byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        while (i < i3) {
            byteToHexStringPadded(t, bArr[i]);
            i++;
        }
        return t;
    }

    public static String byteToHexString(int i) {
        return BYTE2HEX_NOPAD[i & 255];
    }

    public static <T extends Appendable> T byteToHexString(T t, int i) {
        try {
            t.append(byteToHexString(i));
        } catch (IOException e) {
            PlatformDependent.throwException(e);
        }
        return t;
    }

    public static String toHexString(byte[] bArr) {
        return toHexString(bArr, 0, bArr.length);
    }

    public static String toHexString(byte[] bArr, int i, int i2) {
        return ((StringBuilder) toHexString(new StringBuilder(i2 << 1), bArr, i, i2)).toString();
    }

    public static <T extends Appendable> T toHexString(T t, byte[] bArr) {
        return (T) toHexString(t, bArr, 0, bArr.length);
    }

    public static <T extends Appendable> T toHexString(T t, byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return t;
        }
        int i3 = i2 + i;
        int i4 = i3 - 1;
        while (i < i4 && bArr[i] == 0) {
            i++;
        }
        int i5 = i + 1;
        byteToHexString(t, bArr[i]);
        toHexStringPadded(t, bArr, i5, i3 - i5);
        return t;
    }

    public static byte decodeHexByte(CharSequence charSequence, int i) {
        int decodeHexNibble = decodeHexNibble(charSequence.charAt(i));
        int decodeHexNibble2 = decodeHexNibble(charSequence.charAt(i + 1));
        if (decodeHexNibble == -1 || decodeHexNibble2 == -1) {
            throw new IllegalArgumentException(String.format("invalid hex byte '%s' at index %d of '%s'", charSequence.subSequence(i, i + 2), Integer.valueOf(i), charSequence));
        }
        return (byte) ((decodeHexNibble << 4) + decodeHexNibble2);
    }

    public static byte[] decodeHexDump(CharSequence charSequence, int i, int i2) {
        if (i2 < 0 || (i2 & 1) != 0) {
            throw new IllegalArgumentException("length: " + i2);
        }
        if (i2 == 0) {
            return EmptyArrays.EMPTY_BYTES;
        }
        byte[] bArr = new byte[i2 >>> 1];
        for (int i3 = 0; i3 < i2; i3 += 2) {
            bArr[i3 >>> 1] = decodeHexByte(charSequence, i + i3);
        }
        return bArr;
    }

    public static byte[] decodeHexDump(CharSequence charSequence) {
        return decodeHexDump(charSequence, 0, charSequence.length());
    }

    public static String simpleClassName(Object obj) {
        return obj == null ? "null_object" : simpleClassName(obj.getClass());
    }

    public static String simpleClassName(Class<?> cls) {
        String name = ((Class) ObjectUtil.checkNotNull(cls, "clazz")).getName();
        int lastIndexOf = name.lastIndexOf(46);
        return lastIndexOf > -1 ? name.substring(lastIndexOf + 1) : name;
    }

    public static CharSequence escapeCsv(CharSequence charSequence) {
        return escapeCsv(charSequence, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00b9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static CharSequence escapeCsv(CharSequence charSequence, boolean z) {
        int i;
        int i2;
        boolean z2;
        int i3;
        int i4;
        int i5;
        int i6;
        int length = ((CharSequence) ObjectUtil.checkNotNull(charSequence, ES6Iterator.VALUE_PROPERTY)).length();
        boolean z3 = false;
        if (z) {
            i2 = indexOfFirstNonOwsChar(charSequence, length);
            i = indexOfLastNonOwsChar(charSequence, i2, length);
        } else {
            i = length - 1;
            i2 = 0;
        }
        if (i2 > i) {
            return "";
        }
        if (isDoubleQuote(charSequence.charAt(i2))) {
            if (isDoubleQuote(charSequence.charAt(i)) && i > i2) {
                z3 = true;
            }
            if (!z3) {
                z2 = z3;
                i3 = i;
                i4 = i2;
                if (i2 < 0) {
                    if (z2) {
                        i5 = i4;
                        while (i5 <= i3) {
                            if (isDoubleQuote(charSequence.charAt(i5))) {
                                if (i5 != i3) {
                                    int i7 = i5 + 1;
                                    if (isDoubleQuote(charSequence.charAt(i7))) {
                                        i5 = i7;
                                    }
                                }
                                i2 = i5;
                                break;
                            }
                            i5++;
                        }
                        if (i2 < 0) {
                            if (z2) {
                                i4--;
                                i6 = i3 + 2;
                            } else {
                                i6 = i3 + 1;
                            }
                            return charSequence.subSequence(i4, i6);
                        }
                    } else {
                        i5 = i4;
                        while (i5 <= i3) {
                            char charAt = charSequence.charAt(i5);
                            if (charAt != '\n' && charAt != '\r' && charAt != ',') {
                                if (isDoubleQuote(charAt)) {
                                    if (i5 != i3) {
                                        int i8 = i5 + 1;
                                        if (isDoubleQuote(charSequence.charAt(i8))) {
                                            i5 = i8;
                                        }
                                    }
                                }
                                i5++;
                            }
                            i2 = i5;
                            break;
                        }
                        if (i2 < 0) {
                        }
                    }
                }
                StringBuilder sb = new StringBuilder((i3 - i4) + 1 + 7);
                sb.append('\"');
                sb.append(charSequence, i4, i2);
                while (i2 <= i3) {
                    char charAt2 = charSequence.charAt(i2);
                    if (isDoubleQuote(charAt2)) {
                        sb.append('\"');
                        if (i2 < i3) {
                            int i9 = i2 + 1;
                            if (isDoubleQuote(charSequence.charAt(i9))) {
                                i2 = i9;
                            }
                        }
                    }
                    sb.append(charAt2);
                    i2++;
                }
                sb.append('\"');
                return sb;
            }
            i2++;
            i--;
        }
        z2 = z3;
        i3 = i;
        i4 = i2;
        i2 = -1;
        if (i2 < 0) {
        }
        StringBuilder sb2 = new StringBuilder((i3 - i4) + 1 + 7);
        sb2.append('\"');
        sb2.append(charSequence, i4, i2);
        while (i2 <= i3) {
        }
        sb2.append('\"');
        return sb2;
    }

    public static CharSequence unescapeCsv(CharSequence charSequence) {
        int length = ((CharSequence) ObjectUtil.checkNotNull(charSequence, ES6Iterator.VALUE_PROPERTY)).length();
        if (length == 0) {
            return charSequence;
        }
        int i = length - 1;
        boolean z = false;
        if (isDoubleQuote(charSequence.charAt(0)) && isDoubleQuote(charSequence.charAt(i)) && length != 1) {
            z = true;
        }
        if (!z) {
            validateCsvFormat(charSequence);
            return charSequence;
        }
        StringBuilder stringBuilder = InternalThreadLocalMap.get().stringBuilder();
        int i2 = 1;
        while (i2 < i) {
            char charAt = charSequence.charAt(i2);
            if (charAt == '\"') {
                int i3 = i2 + 1;
                if (!isDoubleQuote(charSequence.charAt(i3)) || i3 == i) {
                    throw newInvalidEscapedCsvFieldException(charSequence, i2);
                }
                i2 = i3;
            }
            stringBuilder.append(charAt);
            i2++;
        }
        return stringBuilder.toString();
    }

    public static List<CharSequence> unescapeCsvFields(CharSequence charSequence) {
        ArrayList arrayList = new ArrayList(2);
        StringBuilder stringBuilder = InternalThreadLocalMap.get().stringBuilder();
        int length = charSequence.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            char charAt = charSequence.charAt(i);
            if (!z) {
                if (charAt != '\n' && charAt != '\r') {
                    if (charAt != '\"') {
                        if (charAt == ',') {
                            arrayList.add(stringBuilder.toString());
                            stringBuilder.setLength(0);
                        } else {
                            stringBuilder.append(charAt);
                        }
                    } else if (stringBuilder.length() == 0) {
                        z = true;
                    }
                }
                throw newInvalidEscapedCsvFieldException(charSequence, i);
            }
            if (charAt != '\"') {
                stringBuilder.append(charAt);
            } else {
                if (i == length) {
                    arrayList.add(stringBuilder.toString());
                    return arrayList;
                }
                i++;
                char charAt2 = charSequence.charAt(i);
                if (charAt2 == '\"') {
                    stringBuilder.append('\"');
                } else if (charAt2 == ',') {
                    arrayList.add(stringBuilder.toString());
                    stringBuilder.setLength(0);
                    z = false;
                } else {
                    throw newInvalidEscapedCsvFieldException(charSequence, i - 1);
                }
            }
            i++;
        }
        if (z) {
            throw newInvalidEscapedCsvFieldException(charSequence, length);
        }
        arrayList.add(stringBuilder.toString());
        return arrayList;
    }

    private static void validateCsvFormat(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            char charAt = charSequence.charAt(i);
            if (charAt == '\n' || charAt == '\r' || charAt == '\"' || charAt == ',') {
                throw newInvalidEscapedCsvFieldException(charSequence, i);
            }
        }
    }

    private static IllegalArgumentException newInvalidEscapedCsvFieldException(CharSequence charSequence, int i) {
        return new IllegalArgumentException("invalid escaped CSV field: " + ((Object) charSequence) + " index: " + i);
    }

    public static int length(String str) {
        if (str == null) {
            return 0;
        }
        return str.length();
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static int indexOfNonWhiteSpace(CharSequence charSequence, int i) {
        while (i < charSequence.length()) {
            if (!Character.isWhitespace(charSequence.charAt(i))) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static boolean endsWith(CharSequence charSequence, char c) {
        int length = charSequence.length();
        return length > 0 && charSequence.charAt(length - 1) == c;
    }

    public static CharSequence trimOws(CharSequence charSequence) {
        int length = charSequence.length();
        if (length == 0) {
            return charSequence;
        }
        int indexOfFirstNonOwsChar = indexOfFirstNonOwsChar(charSequence, length);
        int indexOfLastNonOwsChar = indexOfLastNonOwsChar(charSequence, indexOfFirstNonOwsChar, length);
        return (indexOfFirstNonOwsChar == 0 && indexOfLastNonOwsChar == length + (-1)) ? charSequence : charSequence.subSequence(indexOfFirstNonOwsChar, indexOfLastNonOwsChar + 1);
    }

    private static int indexOfFirstNonOwsChar(CharSequence charSequence, int i) {
        int i2 = 0;
        while (i2 < i && isOws(charSequence.charAt(i2))) {
            i2++;
        }
        return i2;
    }

    private static int indexOfLastNonOwsChar(CharSequence charSequence, int i, int i2) {
        int i3 = i2 - 1;
        while (i3 > i && isOws(charSequence.charAt(i3))) {
            i3--;
        }
        return i3;
    }
}
