package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: classes9.dex */
public class CPClass extends ConstantPoolEntry {
    private int cachedHashCode;
    private boolean hashcodeComputed;
    private int index;
    public String name;
    private final CPUTF8 utf8;

    public CPClass(CPUTF8 cputf8, int i) {
        super((byte) 7, i);
        if (cputf8 == null) {
            throw new NullPointerException("Null arguments are not allowed");
        }
        this.name = cputf8.underlyingString();
        this.utf8 = cputf8;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return this.utf8.equals(((CPClass) obj).utf8);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{this.utf8};
    }

    private void generateHashCode() {
        this.hashcodeComputed = true;
        this.cachedHashCode = this.utf8.hashCode();
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        if (!this.hashcodeComputed) {
            generateHashCode();
        }
        return this.cachedHashCode;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.index = classConstantPool.indexOf(this.utf8);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "Class: " + getName();
    }

    public String getName() {
        return this.name;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry
    protected void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.index);
    }
}
