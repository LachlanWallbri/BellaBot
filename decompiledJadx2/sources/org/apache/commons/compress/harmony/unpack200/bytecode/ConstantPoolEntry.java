package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: classes9.dex */
public abstract class ConstantPoolEntry extends ClassFileEntry {
    public static final byte CP_Class = 7;
    public static final byte CP_Double = 6;
    public static final byte CP_Fieldref = 9;
    public static final byte CP_Float = 4;
    public static final byte CP_Integer = 3;
    public static final byte CP_InterfaceMethodref = 11;
    public static final byte CP_Long = 5;
    public static final byte CP_Methodref = 10;
    public static final byte CP_NameAndType = 12;
    public static final byte CP_String = 8;
    public static final byte CP_UTF8 = 1;
    protected int globalIndex;
    byte tag;

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public abstract boolean equals(Object obj);

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public abstract int hashCode();

    protected abstract void writeBody(DataOutputStream dataOutputStream) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConstantPoolEntry(byte b, int i) {
        this.tag = b;
        this.globalIndex = i;
    }

    public byte getTag() {
        return this.tag;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void doWrite(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(this.tag);
        writeBody(dataOutputStream);
    }

    public int getGlobalIndex() {
        return this.globalIndex;
    }
}
