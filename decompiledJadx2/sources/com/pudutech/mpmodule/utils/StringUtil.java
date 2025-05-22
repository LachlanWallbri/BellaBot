package com.pudutech.mpmodule.utils;

import android.telephony.PhoneNumberUtils;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class StringUtil {
    private static final Pattern phonePattern = Pattern.compile("^1\\d{10}$");

    public static String stringFilter(String str) {
        return Pattern.compile("[『』]").matcher(str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!").replaceAll("：", ":")).replaceAll("").trim();
    }

    public static String toDBC(String str) {
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == 12288) {
                charArray[i] = ' ';
            } else if (charArray[i] > 65280 && charArray[i] < 65375) {
                charArray[i] = (char) (charArray[i] - 65248);
            }
        }
        return new String(charArray);
    }

    public static int stringtoint(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception unused) {
            return -1;
        }
    }

    public static long stringtolong(String str) {
        try {
            return Long.parseLong(str);
        } catch (Exception unused) {
            return 0L;
        }
    }

    public static boolean isEmpty(Object obj) {
        return obj == null || obj.toString().trim().equals("");
    }

    public static int getLength(Object obj) {
        if (obj == null || obj.toString().trim().equals("")) {
            return 0;
        }
        return obj.toString().trim().length();
    }

    public static int ObjectToInt(Object obj) {
        if (obj == null || obj.toString().trim().equals("")) {
            return -1;
        }
        return Integer.parseInt(obj.toString());
    }

    public static String rmUnqualified(String str) {
        if (isEmpty(str)) {
            return null;
        }
        return Pattern.compile("\\s*|\t|\r|\n").matcher(str).replaceAll("");
    }

    public static boolean isNotEmpty(Object obj) {
        return (obj == null || obj.toString().trim().equals("")) ? false : true;
    }

    public static boolean equals(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    public static boolean isNumeric(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    public static String getValidPhoneNumber(String str) {
        if (str == null) {
            return "";
        }
        if (str.startsWith("0086")) {
            str = str.substring(4);
        }
        if (str.startsWith("+86")) {
            str = str.substring(3);
        }
        PhoneNumberUtils.stripSeparators(str);
        return str.replace("-", "").replace(" ", "").trim();
    }

    public static boolean isPhone(CharSequence charSequence) {
        if (isEmpty(charSequence)) {
            return false;
        }
        return phonePattern.matcher(charSequence).matches();
    }
}
