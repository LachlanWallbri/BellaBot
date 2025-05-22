package com.google.common.collect;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes3.dex */
final class Hashing {

    /* renamed from: C1 */
    private static final long f1951C1 = -862048943;

    /* renamed from: C2 */
    private static final long f1952C2 = 461845907;
    private static final int MAX_TABLE_SIZE = 1073741824;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean needsResizing(int i, int i2, double d) {
        return ((double) i) > d * ((double) i2) && i2 < 1073741824;
    }

    private Hashing() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int smear(int i) {
        return (int) (Integer.rotateLeft((int) (i * f1951C1), 15) * f1952C2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int smearedHash(Object obj) {
        return smear(obj == null ? 0 : obj.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int closedTableSize(int i, double d) {
        int max = Math.max(i, 2);
        int highestOneBit = Integer.highestOneBit(max);
        if (max <= ((int) (d * highestOneBit))) {
            return highestOneBit;
        }
        int i2 = highestOneBit << 1;
        if (i2 > 0) {
            return i2;
        }
        return 1073741824;
    }
}
