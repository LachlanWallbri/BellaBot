package com.google.common.escape;

import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public abstract class UnicodeEscaper extends Escaper {
    private static final int DEST_PAD = 32;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract char[] escape(int i);

    @Override // com.google.common.escape.Escaper
    public String escape(String str) {
        Preconditions.checkNotNull(str);
        int length = str.length();
        int nextEscapeIndex = nextEscapeIndex(str, 0, length);
        return nextEscapeIndex == length ? str : escapeSlow(str, nextEscapeIndex);
    }

    protected int nextEscapeIndex(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            int codePointAt = codePointAt(charSequence, i, i2);
            if (codePointAt < 0 || escape(codePointAt) != null) {
                break;
            }
            i += Character.isSupplementaryCodePoint(codePointAt) ? 2 : 1;
        }
        return i;
    }

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
                    charBufferFromThreadLocal = growBuffer(charBufferFromThreadLocal, i4, length2 + (length - i) + 32);
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
        Preconditions.checkNotNull(charSequence);
        if (i < i2) {
            int i3 = i + 1;
            char charAt = charSequence.charAt(i);
            if (charAt < 55296 || charAt > 57343) {
                return charAt;
            }
            if (charAt > 56319) {
                String valueOf = String.valueOf(charSequence);
                StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 88);
                sb.append("Unexpected low surrogate character '");
                sb.append(charAt);
                sb.append("' with value ");
                sb.append((int) charAt);
                sb.append(" at index ");
                sb.append(i3 - 1);
                sb.append(" in '");
                sb.append(valueOf);
                sb.append("'");
                throw new IllegalArgumentException(sb.toString());
            }
            if (i3 == i2) {
                return -charAt;
            }
            char charAt2 = charSequence.charAt(i3);
            if (Character.isLowSurrogate(charAt2)) {
                return Character.toCodePoint(charAt, charAt2);
            }
            String valueOf2 = String.valueOf(charSequence);
            StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf2).length() + 89);
            sb2.append("Expected low surrogate but got char '");
            sb2.append(charAt2);
            sb2.append("' with value ");
            sb2.append((int) charAt2);
            sb2.append(" at index ");
            sb2.append(i3);
            sb2.append(" in '");
            sb2.append(valueOf2);
            sb2.append("'");
            throw new IllegalArgumentException(sb2.toString());
        }
        throw new IndexOutOfBoundsException("Index exceeds specified range");
    }

    private static char[] growBuffer(char[] cArr, int i, int i2) {
        if (i2 < 0) {
            throw new AssertionError("Cannot increase internal buffer any further");
        }
        char[] cArr2 = new char[i2];
        if (i > 0) {
            System.arraycopy(cArr, 0, cArr2, 0, i);
        }
        return cArr2;
    }
}
