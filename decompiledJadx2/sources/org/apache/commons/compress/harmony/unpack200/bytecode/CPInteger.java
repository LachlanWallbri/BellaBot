package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: classes9.dex */
public class CPInteger extends CPConstantNumber {
    public CPInteger(Integer num, int i) {
        super((byte) 3, num, i);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry
    protected void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(getNumber().intValue());
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "Integer: " + getValue();
    }
}
