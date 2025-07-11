package org.mozilla.javascript.regexp;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* compiled from: NativeRegExp.java */
/* loaded from: classes2.dex */
final class RECharSet implements Serializable {
    static final long serialVersionUID = 7931787979395898394L;
    volatile transient byte[] bits;
    volatile transient boolean converted;
    final int length;
    final boolean sense;
    final int startIndex;
    final int strlength;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RECharSet(int i, int i2, int i3, boolean z) {
        this.length = i;
        this.startIndex = i2;
        this.strlength = i3;
        this.sense = z;
    }
}
