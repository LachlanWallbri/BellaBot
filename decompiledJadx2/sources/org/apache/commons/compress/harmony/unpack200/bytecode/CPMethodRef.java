package org.apache.commons.compress.harmony.unpack200.bytecode;

/* loaded from: classes9.dex */
public class CPMethodRef extends CPRef {
    private int cachedHashCode;
    private boolean hashcodeComputed;

    public CPMethodRef(CPClass cPClass, CPNameAndType cPNameAndType, int i) {
        super((byte) 10, cPClass, cPNameAndType, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.CPRef, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{this.className, this.nameAndType};
    }

    private void generateHashCode() {
        this.hashcodeComputed = true;
        this.cachedHashCode = ((this.className.hashCode() + 31) * 31) + this.nameAndType.hashCode();
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        if (!this.hashcodeComputed) {
            generateHashCode();
        }
        return this.cachedHashCode;
    }
}
