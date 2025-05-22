package io.opencensus.internal;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class StringUtils {
    private static boolean isPrintableChar(char c) {
        return c >= ' ' && c <= '~';
    }

    public static boolean isPrintableString(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!isPrintableChar(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private StringUtils() {
    }
}
