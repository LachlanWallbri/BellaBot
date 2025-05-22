package org.mozilla.javascript.regexp;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* compiled from: NativeRegExp.java */
/* loaded from: classes2.dex */
class RENode {
    int bmsize;
    char chr;
    int flatIndex;
    boolean greedy;
    int index;
    RENode kid;
    RENode kid2;
    int kidlen;
    int length;
    int max;
    int min;
    RENode next;

    /* renamed from: op */
    byte f10261op;
    int parenCount;
    int parenIndex;
    boolean sense;
    int startIndex;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RENode(byte b) {
        this.f10261op = b;
    }
}
