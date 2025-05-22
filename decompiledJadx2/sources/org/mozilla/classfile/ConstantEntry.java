package org.mozilla.classfile;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes2.dex */
final class ConstantEntry {
    private int hashcode;
    private int intval;
    private long longval;
    private String str1;
    private String str2;
    private int type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConstantEntry(int i, int i2, String str, String str2) {
        this.type = i;
        this.intval = i2;
        this.str1 = str;
        this.str2 = str2;
        this.hashcode = i ^ (i2 + (str.hashCode() * str2.hashCode()));
    }

    public int hashCode() {
        return this.hashcode;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConstantEntry)) {
            return false;
        }
        ConstantEntry constantEntry = (ConstantEntry) obj;
        int i = this.type;
        if (i != constantEntry.type) {
            return false;
        }
        if (i == 3 || i == 4) {
            return this.intval == constantEntry.intval;
        }
        if (i == 5 || i == 6) {
            return this.longval == constantEntry.longval;
        }
        if (i == 12) {
            return this.str1.equals(constantEntry.str1) && this.str2.equals(constantEntry.str2);
        }
        if (i == 18) {
            return this.intval == constantEntry.intval && this.str1.equals(constantEntry.str1) && this.str2.equals(constantEntry.str2);
        }
        throw new RuntimeException("unsupported constant type");
    }
}
