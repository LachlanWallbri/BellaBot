package org.jboss.netty.util.internal;

import java.util.regex.Pattern;

/* loaded from: classes7.dex */
public final class SystemPropertyUtil {
    public static String get(String str) {
        try {
            return System.getProperty(str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String get(String str, String str2) {
        String str3 = get(str);
        return str3 == null ? str2 : str3;
    }

    public static int get(String str, int i) {
        String str2 = get(str);
        return (str2 != null && Pattern.matches("-?[0-9]+", str2)) ? Integer.parseInt(str2) : i;
    }

    private SystemPropertyUtil() {
    }
}
