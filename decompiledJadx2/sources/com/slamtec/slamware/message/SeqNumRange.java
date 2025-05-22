package com.slamtec.slamware.message;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class SeqNumRange {
    private long first;
    private long last;

    public SeqNumRange() {
        this.first = -1L;
        this.last = -1L;
    }

    public SeqNumRange(long j, long j2) {
        this.first = -1L;
        this.last = -1L;
        this.first = j;
        this.last = j2;
    }

    public long getFirst() {
        return this.first;
    }

    public void setFirst(long j) {
        this.first = j;
    }

    public long getLast() {
        return this.last;
    }

    public void setLast(long j) {
        this.last = j;
    }
}
