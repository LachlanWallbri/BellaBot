package org.apache.commons.compress.harmony.pack200;

/* loaded from: classes9.dex */
public class CPString extends CPConstant {
    private final String string;
    private final CPUTF8 utf8;

    public CPString(CPUTF8 cputf8) {
        this.utf8 = cputf8;
        this.string = cputf8.getUnderlyingString();
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return this.string.compareTo(((CPString) obj).string);
    }

    public String toString() {
        return this.string;
    }

    public int getIndexInCpUtf8() {
        return this.utf8.getIndex();
    }
}
