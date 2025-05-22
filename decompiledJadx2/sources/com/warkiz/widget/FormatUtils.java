package com.warkiz.widget;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class FormatUtils {
    private static final char[][] LEADING_DECIMALS = {"0.".toCharArray(), "0.0".toCharArray(), "0.00".toCharArray(), "0.000".toCharArray(), "0.0000".toCharArray(), "0.00000".toCharArray(), "0.000000".toCharArray(), "0.0000000".toCharArray(), "0.00000000".toCharArray(), "0.000000000".toCharArray(), "0.0000000000".toCharArray(), "0.00000000000".toCharArray(), "0.000000000000".toCharArray(), "0.0000000000000".toCharArray(), "0.00000000000000".toCharArray(), "0.000000000000000".toCharArray()};

    FormatUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String fastFormat(double d, int i) {
        char[] cArr;
        int abs = Math.abs(i);
        double abs2 = (Math.abs(d) * Math.pow(10.0d, abs)) + 0.5d;
        if (abs2 > 9.99999999999999E14d || abs > 16) {
            return bigDecFormat(d, abs);
        }
        long nextUp = (long) Math.nextUp(abs2);
        if (nextUp < 1) {
            return "0";
        }
        char[] charArray = Long.toString(nextUp).toCharArray();
        if (charArray.length > abs) {
            int length = charArray.length - 1;
            int length2 = charArray.length - abs;
            while (length >= length2 && charArray[length] == '0') {
                length--;
            }
            if (length >= length2) {
                cArr = new char[length + 2];
                System.arraycopy(charArray, 0, cArr, 0, length2);
                cArr[length2] = FilenameUtils.EXTENSION_SEPARATOR;
                System.arraycopy(charArray, length2, cArr, length2 + 1, (length - length2) + 1);
            } else {
                cArr = new char[length2];
                System.arraycopy(charArray, 0, cArr, 0, length2);
            }
        } else {
            int length3 = charArray.length - 1;
            while (length3 >= 0 && charArray[length3] == '0') {
                length3--;
            }
            char[] cArr2 = LEADING_DECIMALS[abs - charArray.length];
            char[] copyOf = Arrays.copyOf(cArr2, cArr2.length + length3 + 1);
            System.arraycopy(charArray, 0, copyOf, cArr2.length, length3 + 1);
            cArr = copyOf;
        }
        if (Math.signum(d) > 0.0d) {
            return new String(cArr);
        }
        return "-" + new String(cArr);
    }

    private static String bigDecFormat(double d, int i) {
        String bigDecimal = new BigDecimal(Double.toString(d)).setScale(Math.abs(i), RoundingMode.HALF_UP).toString();
        if (i == 0) {
            return bigDecimal;
        }
        int length = bigDecimal.length() - 1;
        while (length >= 0 && bigDecimal.charAt(length) == '0') {
            length--;
        }
        String substring = bigDecimal.substring(0, length + 1);
        return substring.charAt(substring.length() + (-1)) == '.' ? substring.substring(0, substring.length() - 1) : substring;
    }
}
