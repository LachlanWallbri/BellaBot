package com.google.api.client.util.escape;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class UnicodeEscaper extends Escaper {
    private static final int DEST_PAD = 32;

    @Override // com.google.api.client.util.escape.Escaper
    public abstract String escape(String str);

    protected abstract char[] escape(int i);

    protected abstract int nextEscapeIndex(CharSequence charSequence, int i, int i2);

    /* JADX INFO: Access modifiers changed from: protected */
    public final String escapeSlow(String str, int i) {
        int i2;
        int length = str.length();
        char[] charBufferFromThreadLocal = Platform.charBufferFromThreadLocal();
        int i3 = 0;
        int i4 = 0;
        while (i < length) {
            int codePointAt = codePointAt(str, i, length);
            if (codePointAt < 0) {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
            char[] escape = escape(codePointAt);
            int i5 = (Character.isSupplementaryCodePoint(codePointAt) ? 2 : 1) + i;
            if (escape != null) {
                int i6 = i - i3;
                int i7 = i4 + i6;
                int length2 = escape.length + i7;
                if (charBufferFromThreadLocal.length < length2) {
                    charBufferFromThreadLocal = growBuffer(charBufferFromThreadLocal, i4, ((length2 + length) - i) + 32);
                }
                if (i6 > 0) {
                    str.getChars(i3, i, charBufferFromThreadLocal, i4);
                    i4 = i7;
                }
                if (escape.length > 0) {
                    System.arraycopy(escape, 0, charBufferFromThreadLocal, i4, escape.length);
                    i4 += escape.length;
                }
                i3 = i5;
            }
            i = nextEscapeIndex(str, i5, length);
        }
        int i8 = length - i3;
        if (i8 > 0) {
            i2 = i8 + i4;
            if (charBufferFromThreadLocal.length < i2) {
                charBufferFromThreadLocal = growBuffer(charBufferFromThreadLocal, i4, i2);
            }
            str.getChars(i3, length, charBufferFromThreadLocal, i4);
        } else {
            i2 = i4;
        }
        return new String(charBufferFromThreadLocal, 0, i2);
    }

    protected static int codePointAt(CharSequence charSequence, int i, int i2) {
        if (i < i2) {
            int i3 = i + 1;
            char charAt = charSequence.charAt(i);
            if (charAt < 55296 || charAt > 57343) {
                return charAt;
            }
            if (charAt > 56319) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected low surrogate character '");
                sb.append(charAt);
                sb.append("' with value ");
                sb.append((int) charAt);
                sb.append(" at index ");
                sb.append(i3 - 1);
                throw new IllegalArgumentException(sb.toString());
            }
            if (i3 == i2) {
                return -charAt;
            }
            char charAt2 = charSequence.charAt(i3);
            if (Character.isLowSurrogate(charAt2)) {
                return Character.toCodePoint(charAt, charAt2);
            }
            throw new IllegalArgumentException("Expected low surrogate but got char '" + charAt2 + "' with value " + ((int) charAt2) + " at index " + i3);
        }
        throw new IndexOutOfBoundsException("Index exceeds specified range");
    }

    private static char[] growBuffer(char[] cArr, int i, int i2) {
        char[] cArr2 = new char[i2];
        if (i > 0) {
            System.arraycopy(cArr, 0, cArr2, 0, i);
        }
        return cArr2;
    }
}
