package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: classes9.dex */
public abstract class Attribute extends ClassFileEntry {
    protected final CPUTF8 attributeName;
    private int attributeNameIndex;

    protected abstract int getLength();

    public boolean hasBCIRenumbering() {
        return false;
    }

    public boolean isSourceFileAttribute() {
        return false;
    }

    protected abstract void writeBody(DataOutputStream dataOutputStream) throws IOException;

    public Attribute(CPUTF8 cputf8) {
        this.attributeName = cputf8;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void doWrite(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.attributeNameIndex);
        dataOutputStream.writeInt(getLength());
        writeBody(dataOutputStream);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Attribute attribute = (Attribute) obj;
        CPUTF8 cputf8 = this.attributeName;
        if (cputf8 == null) {
            if (attribute.attributeName != null) {
                return false;
            }
        } else if (!cputf8.equals(attribute.attributeName)) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CPUTF8 getAttributeName() {
        return this.attributeName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int getLengthIncludingHeader() {
        return getLength() + 2 + 4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{getAttributeName()};
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        CPUTF8 cputf8 = this.attributeName;
        return 31 + (cputf8 == null ? 0 : cputf8.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.attributeNameIndex = classConstantPool.indexOf(this.attributeName);
    }
}
