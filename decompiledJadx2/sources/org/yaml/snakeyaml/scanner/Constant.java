package org.yaml.snakeyaml.scanner;

import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class Constant {
    boolean[] contains = new boolean[128];
    private String content;
    boolean noASCII;
    private static final String LINEBR_S = "\n\u0085\u2028\u2029";
    public static final Constant LINEBR = new Constant(LINEBR_S);
    private static final String FULL_LINEBR_S = "\r\n\u0085\u2028\u2029";
    public static final Constant FULL_LINEBR = new Constant(FULL_LINEBR_S);
    private static final String NULL_OR_LINEBR_S = "\u0000\r\n\u0085\u2028\u2029";
    public static final Constant NULL_OR_LINEBR = new Constant(NULL_OR_LINEBR_S);
    private static final String NULL_BL_LINEBR_S = " \u0000\r\n\u0085\u2028\u2029";
    public static final Constant NULL_BL_LINEBR = new Constant(NULL_BL_LINEBR_S);
    private static final String NULL_BL_T_LINEBR_S = "\t \u0000\r\n\u0085\u2028\u2029";
    public static final Constant NULL_BL_T_LINEBR = new Constant(NULL_BL_T_LINEBR_S);
    private static final String NULL_BL_T_S = "\u0000 \t";
    public static final Constant NULL_BL_T = new Constant(NULL_BL_T_S);
    private static final String URI_CHARS_S = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-_-;/?:@&=+$,_.!~*'()[]%";
    public static final Constant URI_CHARS = new Constant(URI_CHARS_S);
    private static final String ALPHA_S = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-_";
    public static final Constant ALPHA = new Constant(ALPHA_S);

    private Constant(String str) {
        this.noASCII = false;
        Arrays.fill(this.contains, false);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt < 128) {
                this.contains[charAt] = true;
            } else {
                sb.append(charAt);
            }
        }
        if (sb.length() > 0) {
            this.noASCII = true;
            this.content = sb.toString();
        }
    }

    public boolean has(char c) {
        if (c < 128) {
            return this.contains[c];
        }
        return this.noASCII && this.content.indexOf(c, 0) != -1;
    }

    public boolean hasNo(char c) {
        return !has(c);
    }

    public boolean has(char c, String str) {
        return has(c) || str.indexOf(c, 0) != -1;
    }

    public boolean hasNo(char c, String str) {
        return !has(c, str);
    }
}
