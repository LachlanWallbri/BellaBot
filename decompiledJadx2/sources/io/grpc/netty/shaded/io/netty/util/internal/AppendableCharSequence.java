package io.grpc.netty.shaded.io.netty.util.internal;

import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AppendableCharSequence implements CharSequence, Appendable {
    private char[] chars;
    private int pos;

    public AppendableCharSequence(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("length: " + i + " (length: >= 1)");
        }
        this.chars = new char[i];
    }

    private AppendableCharSequence(char[] cArr) {
        if (cArr.length < 1) {
            throw new IllegalArgumentException("length: " + cArr.length + " (length: >= 1)");
        }
        this.chars = cArr;
        this.pos = cArr.length;
    }

    public void setLength(int i) {
        if (i < 0 || i > this.pos) {
            throw new IllegalArgumentException("length: " + i + " (length: >= 0, <= " + this.pos + ')');
        }
        this.pos = i;
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.pos;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        if (i > this.pos) {
            throw new IndexOutOfBoundsException();
        }
        return this.chars[i];
    }

    public char charAtUnsafe(int i) {
        return this.chars[i];
    }

    @Override // java.lang.CharSequence
    public AppendableCharSequence subSequence(int i, int i2) {
        if (i == i2) {
            return new AppendableCharSequence(Math.min(16, this.chars.length));
        }
        return new AppendableCharSequence(Arrays.copyOfRange(this.chars, i, i2));
    }

    @Override // java.lang.Appendable
    public AppendableCharSequence append(char c) {
        int i = this.pos;
        char[] cArr = this.chars;
        if (i == cArr.length) {
            this.chars = new char[cArr.length << 1];
            System.arraycopy(cArr, 0, this.chars, 0, cArr.length);
        }
        char[] cArr2 = this.chars;
        int i2 = this.pos;
        this.pos = i2 + 1;
        cArr2[i2] = c;
        return this;
    }

    @Override // java.lang.Appendable
    public AppendableCharSequence append(CharSequence charSequence) {
        return append(charSequence, 0, charSequence.length());
    }

    @Override // java.lang.Appendable
    public AppendableCharSequence append(CharSequence charSequence, int i, int i2) {
        if (charSequence.length() < i2) {
            throw new IndexOutOfBoundsException("expected: csq.length() >= (" + i2 + "),but actual is (" + charSequence.length() + ")");
        }
        int i3 = i2 - i;
        char[] cArr = this.chars;
        int length = cArr.length;
        int i4 = this.pos;
        if (i3 > length - i4) {
            this.chars = expand(cArr, i4 + i3, i4);
        }
        if (charSequence instanceof AppendableCharSequence) {
            System.arraycopy(((AppendableCharSequence) charSequence).chars, i, this.chars, this.pos, i3);
            this.pos += i3;
            return this;
        }
        while (i < i2) {
            char[] cArr2 = this.chars;
            int i5 = this.pos;
            this.pos = i5 + 1;
            cArr2[i5] = charSequence.charAt(i);
            i++;
        }
        return this;
    }

    public void reset() {
        this.pos = 0;
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return new String(this.chars, 0, this.pos);
    }

    public String substring(int i, int i2) {
        int i3 = i2 - i;
        int i4 = this.pos;
        if (i > i4 || i3 > i4) {
            throw new IndexOutOfBoundsException("expected: start and length <= (" + this.pos + ")");
        }
        return new String(this.chars, i, i3);
    }

    public String subStringUnsafe(int i, int i2) {
        return new String(this.chars, i, i2 - i);
    }

    private static char[] expand(char[] cArr, int i, int i2) {
        int length = cArr.length;
        do {
            length <<= 1;
            if (length < 0) {
                throw new IllegalStateException();
            }
        } while (i > length);
        char[] cArr2 = new char[length];
        System.arraycopy(cArr, 0, cArr2, 0, i2);
        return cArr2;
    }
}
