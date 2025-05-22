package com.google.zxing.oned.rss.expanded.decoders;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
abstract class DecodedObject {
    private final int newPosition;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DecodedObject(int i) {
        this.newPosition = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getNewPosition() {
        return this.newPosition;
    }
}
