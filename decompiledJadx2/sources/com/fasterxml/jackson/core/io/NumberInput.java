package com.fasterxml.jackson.core.io;

import java.math.BigDecimal;
import org.apache.commons.compress.compressors.bzip2.BZip2Constants;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
/* loaded from: classes2.dex */
public final class NumberInput {
    static final long L_BILLION = 1000000000;
    public static final String NASTY_SMALL_DOUBLE = "2.2250738585072012e-308";
    static final String MIN_LONG_STR_NO_SIGN = String.valueOf(Long.MIN_VALUE).substring(1);
    static final String MAX_LONG_STR = String.valueOf(Long.MAX_VALUE);

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0008. Please report as an issue. */
    public static int parseInt(char[] cArr, int i, int i2) {
        int i3 = cArr[(i + i2) - 1] - '0';
        switch (i2) {
            case 2:
                return i3 + ((cArr[i] - '0') * 10);
            case 3:
                i3 += (cArr[i] - '0') * 100;
                i++;
                return i3 + ((cArr[i] - '0') * 10);
            case 4:
                i3 += (cArr[i] - '0') * 1000;
                i++;
                i3 += (cArr[i] - '0') * 100;
                i++;
                return i3 + ((cArr[i] - '0') * 10);
            case 5:
                i3 += (cArr[i] - '0') * 10000;
                i++;
                i3 += (cArr[i] - '0') * 1000;
                i++;
                i3 += (cArr[i] - '0') * 100;
                i++;
                return i3 + ((cArr[i] - '0') * 10);
            case 6:
                i3 += (cArr[i] - '0') * BZip2Constants.BASEBLOCKSIZE;
                i++;
                i3 += (cArr[i] - '0') * 10000;
                i++;
                i3 += (cArr[i] - '0') * 1000;
                i++;
                i3 += (cArr[i] - '0') * 100;
                i++;
                return i3 + ((cArr[i] - '0') * 10);
            case 7:
                i3 += (cArr[i] - '0') * 1000000;
                i++;
                i3 += (cArr[i] - '0') * BZip2Constants.BASEBLOCKSIZE;
                i++;
                i3 += (cArr[i] - '0') * 10000;
                i++;
                i3 += (cArr[i] - '0') * 1000;
                i++;
                i3 += (cArr[i] - '0') * 100;
                i++;
                return i3 + ((cArr[i] - '0') * 10);
            case 8:
                i3 += (cArr[i] - '0') * 10000000;
                i++;
                i3 += (cArr[i] - '0') * 1000000;
                i++;
                i3 += (cArr[i] - '0') * BZip2Constants.BASEBLOCKSIZE;
                i++;
                i3 += (cArr[i] - '0') * 10000;
                i++;
                i3 += (cArr[i] - '0') * 1000;
                i++;
                i3 += (cArr[i] - '0') * 100;
                i++;
                return i3 + ((cArr[i] - '0') * 10);
            case 9:
                i3 += (cArr[i] - '0') * 100000000;
                i++;
                i3 += (cArr[i] - '0') * 10000000;
                i++;
                i3 += (cArr[i] - '0') * 1000000;
                i++;
                i3 += (cArr[i] - '0') * BZip2Constants.BASEBLOCKSIZE;
                i++;
                i3 += (cArr[i] - '0') * 10000;
                i++;
                i3 += (cArr[i] - '0') * 1000;
                i++;
                i3 += (cArr[i] - '0') * 100;
                i++;
                return i3 + ((cArr[i] - '0') * 10);
            default:
                return i3;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0075, code lost:
    
        return java.lang.Integer.parseInt(r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int parseInt(String str) {
        char charAt = str.charAt(0);
        int length = str.length();
        int i = 1;
        boolean z = charAt == '-';
        if (z) {
            if (length == 1 || length > 10) {
                return Integer.parseInt(str);
            }
            i = 2;
            charAt = str.charAt(1);
        } else if (length > 9) {
            return Integer.parseInt(str);
        }
        if (charAt > '9' || charAt < '0') {
            return Integer.parseInt(str);
        }
        int i2 = charAt - '0';
        if (i < length) {
            int i3 = i + 1;
            char charAt2 = str.charAt(i);
            if (charAt2 > '9' || charAt2 < '0') {
                return Integer.parseInt(str);
            }
            i2 = (i2 * 10) + (charAt2 - '0');
            if (i3 < length) {
                int i4 = i3 + 1;
                char charAt3 = str.charAt(i3);
                if (charAt3 > '9' || charAt3 < '0') {
                    return Integer.parseInt(str);
                }
                i2 = (i2 * 10) + (charAt3 - '0');
                if (i4 < length) {
                    while (true) {
                        int i5 = i4 + 1;
                        char charAt4 = str.charAt(i4);
                        if (charAt4 > '9' || charAt4 < '0') {
                            break;
                        }
                        i2 = (i2 * 10) + (charAt4 - '0');
                        if (i5 >= length) {
                            break;
                        }
                        i4 = i5;
                    }
                }
            }
        }
        return z ? -i2 : i2;
    }

    public static long parseLong(char[] cArr, int i, int i2) {
        int i3 = i2 - 9;
        return (parseInt(cArr, i, i3) * L_BILLION) + parseInt(cArr, i + i3, 9);
    }

    public static long parseLong(String str) {
        if (str.length() <= 9) {
            return parseInt(str);
        }
        return Long.parseLong(str);
    }

    public static boolean inLongRange(char[] cArr, int i, int i2, boolean z) {
        String str = z ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str.length();
        if (i2 < length) {
            return true;
        }
        if (i2 > length) {
            return false;
        }
        for (int i3 = 0; i3 < length; i3++) {
            int charAt = cArr[i + i3] - str.charAt(i3);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static boolean inLongRange(String str, boolean z) {
        String str2 = z ? MIN_LONG_STR_NO_SIGN : MAX_LONG_STR;
        int length = str2.length();
        int length2 = str.length();
        if (length2 < length) {
            return true;
        }
        if (length2 > length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            int charAt = str.charAt(i) - str2.charAt(i);
            if (charAt != 0) {
                return charAt < 0;
            }
        }
        return true;
    }

    public static int parseAsInt(String str, int i) {
        String trim;
        int length;
        if (str == null || (length = (trim = str.trim()).length()) == 0) {
            return i;
        }
        int i2 = 0;
        if (length > 0) {
            char charAt = trim.charAt(0);
            if (charAt == '+') {
                trim = trim.substring(1);
                length = trim.length();
            } else if (charAt == '-') {
                i2 = 1;
            }
        }
        while (i2 < length) {
            char charAt2 = trim.charAt(i2);
            if (charAt2 > '9' || charAt2 < '0') {
                try {
                    return (int) parseDouble(trim);
                } catch (NumberFormatException unused) {
                    return i;
                }
            }
            i2++;
        }
        try {
            return Integer.parseInt(trim);
        } catch (NumberFormatException unused2) {
            return i;
        }
    }

    public static long parseAsLong(String str, long j) {
        String trim;
        int length;
        if (str == null || (length = (trim = str.trim()).length()) == 0) {
            return j;
        }
        int i = 0;
        if (length > 0) {
            char charAt = trim.charAt(0);
            if (charAt == '+') {
                trim = trim.substring(1);
                length = trim.length();
            } else if (charAt == '-') {
                i = 1;
            }
        }
        while (i < length) {
            char charAt2 = trim.charAt(i);
            if (charAt2 > '9' || charAt2 < '0') {
                try {
                    return (long) parseDouble(trim);
                } catch (NumberFormatException unused) {
                    return j;
                }
            }
            i++;
        }
        try {
            return Long.parseLong(trim);
        } catch (NumberFormatException unused2) {
            return j;
        }
    }

    public static double parseAsDouble(String str, double d) {
        if (str == null) {
            return d;
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            return d;
        }
        try {
            return parseDouble(trim);
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public static double parseDouble(String str) throws NumberFormatException {
        if (NASTY_SMALL_DOUBLE.equals(str)) {
            return Double.MIN_VALUE;
        }
        return Double.parseDouble(str);
    }

    public static BigDecimal parseBigDecimal(String str) throws NumberFormatException {
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException unused) {
            throw _badBD(str);
        }
    }

    public static BigDecimal parseBigDecimal(char[] cArr) throws NumberFormatException {
        return parseBigDecimal(cArr, 0, cArr.length);
    }

    public static BigDecimal parseBigDecimal(char[] cArr, int i, int i2) throws NumberFormatException {
        try {
            return new BigDecimal(cArr, i, i2);
        } catch (NumberFormatException unused) {
            throw _badBD(new String(cArr, i, i2));
        }
    }

    private static NumberFormatException _badBD(String str) {
        return new NumberFormatException("Value \"" + str + "\" can not be represented as BigDecimal");
    }
}
