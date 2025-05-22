package io.netty.handler.codec.http;

import io.netty.util.AsciiString;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public enum HttpStatusClass {
    INFORMATIONAL(100, 200, "Informational"),
    SUCCESS(200, 300, "Success"),
    REDIRECTION(300, 400, "Redirection"),
    CLIENT_ERROR(400, 500, "Client Error"),
    SERVER_ERROR(500, 600, "Server Error"),
    UNKNOWN(0, 0, "Unknown Status") { // from class: io.netty.handler.codec.http.HttpStatusClass.1
        @Override // io.netty.handler.codec.http.HttpStatusClass
        public boolean contains(int i) {
            return i < 100 || i >= 600;
        }
    };

    private final AsciiString defaultReasonPhrase;
    private final int max;
    private final int min;

    private static int digit(char c) {
        return c - '0';
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    public static HttpStatusClass valueOf(int i) {
        if (INFORMATIONAL.contains(i)) {
            return INFORMATIONAL;
        }
        if (SUCCESS.contains(i)) {
            return SUCCESS;
        }
        if (REDIRECTION.contains(i)) {
            return REDIRECTION;
        }
        if (CLIENT_ERROR.contains(i)) {
            return CLIENT_ERROR;
        }
        if (SERVER_ERROR.contains(i)) {
            return SERVER_ERROR;
        }
        return UNKNOWN;
    }

    public static HttpStatusClass valueOf(CharSequence charSequence) {
        if (charSequence != null && charSequence.length() == 3) {
            char charAt = charSequence.charAt(0);
            return (isDigit(charAt) && isDigit(charSequence.charAt(1)) && isDigit(charSequence.charAt(2))) ? valueOf(digit(charAt) * 100) : UNKNOWN;
        }
        return UNKNOWN;
    }

    HttpStatusClass(int i, int i2, String str) {
        this.min = i;
        this.max = i2;
        this.defaultReasonPhrase = AsciiString.cached(str);
    }

    public boolean contains(int i) {
        return i >= this.min && i < this.max;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AsciiString defaultReasonPhrase() {
        return this.defaultReasonPhrase;
    }
}
