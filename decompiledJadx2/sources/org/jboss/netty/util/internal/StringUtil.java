package org.jboss.netty.util.internal;

import java.util.Formatter;

/* loaded from: classes7.dex */
public final class StringUtil {
    public static final String NEWLINE;

    private StringUtil() {
    }

    static {
        String str;
        try {
            str = new Formatter().format("%n", new Object[0]).toString();
        } catch (Exception unused) {
            str = "\n";
        }
        NEWLINE = str;
    }

    public static String stripControlCharacters(Object obj) {
        if (obj == null) {
            return null;
        }
        return stripControlCharacters(obj.toString());
    }

    public static String stripControlCharacters(String str) {
        boolean z;
        if (str == null) {
            return null;
        }
        int length = str.length() - 1;
        while (true) {
            if (length < 0) {
                z = false;
                break;
            }
            if (Character.isISOControl(str.charAt(length))) {
                z = true;
                break;
            }
            length--;
        }
        if (!z) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.length());
        int i = 0;
        while (i < str.length() && Character.isISOControl(str.charAt(i))) {
            i++;
        }
        boolean z2 = false;
        while (i < str.length()) {
            if (Character.isISOControl(str.charAt(i))) {
                z2 = true;
            } else {
                if (z2) {
                    sb.append(' ');
                    z2 = false;
                }
                sb.append(str.charAt(i));
            }
            i++;
        }
        return sb.toString();
    }
}
