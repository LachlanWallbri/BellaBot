package org.mozilla.javascript;

import java.io.Serializable;
import java.util.ArrayDeque;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes2.dex */
public class ConsString implements CharSequence, Serializable {
    private static final long serialVersionUID = -8432806714471372570L;
    private boolean isFlat = false;
    private CharSequence left;
    private final int length;
    private CharSequence right;

    public ConsString(CharSequence charSequence, CharSequence charSequence2) {
        this.left = charSequence;
        this.right = charSequence2;
        this.length = charSequence.length() + this.right.length();
    }

    private Object writeReplace() {
        return toString();
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return this.isFlat ? (String) this.left : flatten();
    }

    private synchronized String flatten() {
        if (!this.isFlat) {
            char[] cArr = new char[this.length];
            int i = this.length;
            ArrayDeque arrayDeque = new ArrayDeque();
            arrayDeque.addFirst(this.left);
            CharSequence charSequence = this.right;
            do {
                if (charSequence instanceof ConsString) {
                    ConsString consString = (ConsString) charSequence;
                    if (consString.isFlat) {
                        charSequence = consString.left;
                    } else {
                        arrayDeque.addFirst(consString.left);
                        charSequence = consString.right;
                    }
                }
                String str = (String) charSequence;
                i -= str.length();
                str.getChars(0, str.length(), cArr, i);
                charSequence = arrayDeque.isEmpty() ? null : (CharSequence) arrayDeque.removeFirst();
            } while (charSequence != null);
            this.left = new String(cArr);
            this.right = "";
            this.isFlat = true;
        }
        return (String) this.left;
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.length;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        return (this.isFlat ? (String) this.left : flatten()).charAt(i);
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return (this.isFlat ? (String) this.left : flatten()).substring(i, i2);
    }
}
