package org.apache.commons.compress.harmony.pack200;

/* loaded from: classes9.dex */
public class CPNameAndType extends ConstantPoolEntry implements Comparable {
    private final CPUTF8 name;
    private final CPSignature signature;

    public CPNameAndType(CPUTF8 cputf8, CPSignature cPSignature) {
        this.name = cputf8;
        this.signature = cPSignature;
    }

    public String toString() {
        return this.name + ":" + this.signature;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        if (!(obj instanceof CPNameAndType)) {
            return 0;
        }
        CPNameAndType cPNameAndType = (CPNameAndType) obj;
        int compareTo = this.signature.compareTo(cPNameAndType.signature);
        return compareTo == 0 ? this.name.compareTo(cPNameAndType.name) : compareTo;
    }

    public int getNameIndex() {
        return this.name.getIndex();
    }

    public String getName() {
        return this.name.getUnderlyingString();
    }

    public int getTypeIndex() {
        return this.signature.getIndex();
    }
}
