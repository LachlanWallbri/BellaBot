package com.google.common.collect;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
public enum BoundType {
    OPEN(false),
    CLOSED(true);

    final boolean inclusive;

    BoundType(boolean z) {
        this.inclusive = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BoundType forBoolean(boolean z) {
        return z ? CLOSED : OPEN;
    }

    BoundType flip() {
        return forBoolean(!this.inclusive);
    }
}
