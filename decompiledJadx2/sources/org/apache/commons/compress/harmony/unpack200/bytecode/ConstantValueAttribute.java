package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: classes9.dex */
public class ConstantValueAttribute extends Attribute {
    private static CPUTF8 attributeName;
    private int constantIndex;
    private final ClassFileEntry entry;

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        return 2;
    }

    public static void setAttributeName(CPUTF8 cputf8) {
        attributeName = cputf8;
    }

    public ConstantValueAttribute(ClassFileEntry classFileEntry) {
        super(attributeName);
        if (classFileEntry == null) {
            throw new NullPointerException();
        }
        this.entry = classFileEntry;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        ConstantValueAttribute constantValueAttribute = (ConstantValueAttribute) obj;
        ClassFileEntry classFileEntry = this.entry;
        if (classFileEntry == null) {
            if (constantValueAttribute.entry != null) {
                return false;
            }
        } else if (!classFileEntry.equals(constantValueAttribute.entry)) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{getAttributeName(), this.entry};
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        ClassFileEntry classFileEntry = this.entry;
        return hashCode + (classFileEntry == null ? 0 : classFileEntry.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.entry.resolve(classConstantPool);
        this.constantIndex = classConstantPool.indexOf(this.entry);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "Constant:" + this.entry;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.constantIndex);
    }
}
