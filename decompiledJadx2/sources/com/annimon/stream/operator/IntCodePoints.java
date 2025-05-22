package com.annimon.stream.operator;

import com.annimon.stream.iterator.PrimitiveIterator;
import java.util.NoSuchElementException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IntCodePoints extends PrimitiveIterator.OfInt {
    private final CharSequence charSequence;
    private final boolean isString;
    private int current = 0;
    private int length = -1;

    public IntCodePoints(CharSequence charSequence) {
        this.charSequence = charSequence;
        this.isString = charSequence instanceof String;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.current < ensureLength();
    }

    @Override // com.annimon.stream.iterator.PrimitiveIterator.OfInt
    public int nextInt() {
        int i;
        int ensureLength = ensureLength();
        int i2 = this.current;
        if (i2 >= ensureLength) {
            throw new NoSuchElementException();
        }
        CharSequence charSequence = this.charSequence;
        this.current = i2 + 1;
        char charAt = charSequence.charAt(i2);
        if (Character.isHighSurrogate(charAt) && (i = this.current) < ensureLength) {
            char charAt2 = this.charSequence.charAt(i);
            if (Character.isLowSurrogate(charAt2)) {
                this.current++;
                return Character.toCodePoint(charAt, charAt2);
            }
        }
        return charAt;
    }

    private int ensureLength() {
        if (this.isString) {
            if (this.length == -1) {
                this.length = this.charSequence.length();
            }
            return this.length;
        }
        return this.charSequence.length();
    }
}
