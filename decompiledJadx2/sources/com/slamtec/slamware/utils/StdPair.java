package com.slamtec.slamware.utils;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class StdPair<FirstElemT, SecondElemT> {
    private FirstElemT first;
    private SecondElemT second;

    public StdPair() {
    }

    public StdPair(FirstElemT firstelemt, SecondElemT secondelemt) {
        this.first = firstelemt;
        this.second = secondelemt;
    }

    public FirstElemT getFirst() {
        return this.first;
    }

    public void setFirst(FirstElemT firstelemt) {
        this.first = firstelemt;
    }

    public SecondElemT getSecond() {
        return this.second;
    }

    public void setSecond(SecondElemT secondelemt) {
        this.second = secondelemt;
    }
}
