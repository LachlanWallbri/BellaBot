package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.commons.compress.harmony.unpack200.SegmentUtils;

/* loaded from: classes9.dex */
public class CPNameAndType extends ConstantPoolEntry {
    private int cachedHashCode;
    CPUTF8 descriptor;
    transient int descriptorIndex;
    private boolean hashcodeComputed;
    CPUTF8 name;
    transient int nameIndex;

    public CPNameAndType(CPUTF8 cputf8, CPUTF8 cputf82, int i) {
        super((byte) 12, i);
        this.name = cputf8;
        this.descriptor = cputf82;
        if (cputf8 == null || cputf82 == null) {
            throw new NullPointerException("Null arguments are not allowed");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{this.name, this.descriptor};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.descriptorIndex = classConstantPool.indexOf(this.descriptor);
        this.nameIndex = classConstantPool.indexOf(this.name);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.nameIndex);
        dataOutputStream.writeShort(this.descriptorIndex);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "NameAndType: " + this.name + "(" + this.descriptor + ")";
    }

    private void generateHashCode() {
        this.hashcodeComputed = true;
        this.cachedHashCode = ((this.descriptor.hashCode() + 31) * 31) + this.name.hashCode();
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        if (!this.hashcodeComputed) {
            generateHashCode();
        }
        return this.cachedHashCode;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CPNameAndType cPNameAndType = (CPNameAndType) obj;
        return this.descriptor.equals(cPNameAndType.descriptor) && this.name.equals(cPNameAndType.name);
    }

    public int invokeInterfaceCount() {
        return SegmentUtils.countInvokeInterfaceArgs(this.descriptor.underlyingString()) + 1;
    }
}
