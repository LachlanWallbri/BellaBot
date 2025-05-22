package org.apache.commons.compress.harmony.pack200;

/* loaded from: classes9.dex */
public class CPMethodOrField extends ConstantPoolEntry implements Comparable {
    private final CPClass className;
    private int indexInClass = -1;
    private int indexInClassForConstructor = -1;
    private final CPNameAndType nameAndType;

    public CPMethodOrField(CPClass cPClass, CPNameAndType cPNameAndType) {
        this.className = cPClass;
        this.nameAndType = cPNameAndType;
    }

    public String toString() {
        return this.className + ": " + this.nameAndType;
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        if (!(obj instanceof CPMethodOrField)) {
            return 0;
        }
        CPMethodOrField cPMethodOrField = (CPMethodOrField) obj;
        int compareTo = this.className.compareTo(cPMethodOrField.className);
        return compareTo == 0 ? this.nameAndType.compareTo(cPMethodOrField.nameAndType) : compareTo;
    }

    public int getClassIndex() {
        return this.className.getIndex();
    }

    public CPClass getClassName() {
        return this.className;
    }

    public int getDescIndex() {
        return this.nameAndType.getIndex();
    }

    public CPNameAndType getDesc() {
        return this.nameAndType;
    }

    public int getIndexInClass() {
        return this.indexInClass;
    }

    public void setIndexInClass(int i) {
        this.indexInClass = i;
    }

    public int getIndexInClassForConstructor() {
        return this.indexInClassForConstructor;
    }

    public void setIndexInClassForConstructor(int i) {
        this.indexInClassForConstructor = i;
    }
}
