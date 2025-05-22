package org.mozilla.javascript.regexp;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* compiled from: NativeRegExp.java */
/* loaded from: classes2.dex */
public class REBackTrackData {
    final int continuationOp;
    final int continuationPc;

    /* renamed from: cp */
    final int f10257cp;

    /* renamed from: op */
    final int f10258op;
    final long[] parens;

    /* renamed from: pc */
    final int f10259pc;
    final REBackTrackData previous;
    final REProgState stateStackTop;

    /* JADX INFO: Access modifiers changed from: package-private */
    public REBackTrackData(REGlobalData rEGlobalData, int i, int i2, int i3, int i4, int i5) {
        this.previous = rEGlobalData.backTrackStackTop;
        this.f10258op = i;
        this.f10259pc = i2;
        this.f10257cp = i3;
        this.continuationOp = i4;
        this.continuationPc = i5;
        this.parens = rEGlobalData.parens;
        this.stateStackTop = rEGlobalData.stateStackTop;
    }
}
