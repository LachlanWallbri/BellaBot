package org.apache.commons.compress.harmony.pack200;

/* loaded from: classes9.dex */
public class CPLong extends CPConstant {
    private final long theLong;

    public CPLong(long j) {
        this.theLong = j;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        long j = this.theLong;
        long j2 = ((CPLong) obj).theLong;
        if (j > j2) {
            return 1;
        }
        return j == j2 ? 0 : -1;
    }

    public long getLong() {
        return this.theLong;
    }

    public String toString() {
        return "" + this.theLong;
    }
}
