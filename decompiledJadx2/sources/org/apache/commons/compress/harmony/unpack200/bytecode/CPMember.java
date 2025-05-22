package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/* loaded from: classes9.dex */
public class CPMember extends ClassFileEntry {
    List attributes;
    protected final CPUTF8 descriptor;
    transient int descriptorIndex;
    short flags;
    CPUTF8 name;
    transient int nameIndex;

    public CPMember(CPUTF8 cputf8, CPUTF8 cputf82, long j, List list) {
        this.name = cputf8;
        this.descriptor = cputf82;
        this.flags = (short) j;
        this.attributes = list == null ? Collections.EMPTY_LIST : list;
        if (cputf8 == null || cputf82 == null) {
            throw new NullPointerException("Null arguments are not allowed");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        int size = this.attributes.size();
        ClassFileEntry[] classFileEntryArr = new ClassFileEntry[size + 2];
        classFileEntryArr[0] = this.name;
        classFileEntryArr[1] = this.descriptor;
        for (int i = 0; i < size; i++) {
            classFileEntryArr[i + 2] = (Attribute) this.attributes.get(i);
        }
        return classFileEntryArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.nameIndex = classConstantPool.indexOf(this.name);
        this.descriptorIndex = classConstantPool.indexOf(this.descriptor);
        for (int i = 0; i < this.attributes.size(); i++) {
            ((Attribute) this.attributes.get(i)).resolve(classConstantPool);
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        return ((((((this.attributes.hashCode() + 31) * 31) + this.descriptor.hashCode()) * 31) + this.flags) * 31) + this.name.hashCode();
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "CPMember: " + this.name + "(" + this.descriptor + ")";
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CPMember cPMember = (CPMember) obj;
        return this.attributes.equals(cPMember.attributes) && this.descriptor.equals(cPMember.descriptor) && this.flags == cPMember.flags && this.name.equals(cPMember.name);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    protected void doWrite(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.flags);
        dataOutputStream.writeShort(this.nameIndex);
        dataOutputStream.writeShort(this.descriptorIndex);
        int size = this.attributes.size();
        dataOutputStream.writeShort(size);
        for (int i = 0; i < size; i++) {
            ((Attribute) this.attributes.get(i)).doWrite(dataOutputStream);
        }
    }
}
