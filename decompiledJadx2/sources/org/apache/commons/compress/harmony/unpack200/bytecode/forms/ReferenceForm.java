package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

/* loaded from: classes9.dex */
public abstract class ReferenceForm extends ByteCodeForm {
    protected abstract int getOffset(OperandManager operandManager);

    protected abstract int getPoolID();

    public ReferenceForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setNestedEntries(ByteCode byteCode, OperandManager operandManager, int i) throws Pack200Exception {
        ClassFileEntry[] classFileEntryArr = {operandManager.globalConstantPool().getConstantPoolEntry(getPoolID(), i)};
        if (classFileEntryArr[0] == null) {
            throw new NullPointerException("Null nested entries are not allowed");
        }
        byteCode.setNested(classFileEntryArr);
        byteCode.setNestedPositions(new int[][]{new int[]{0, 2}});
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ByteCodeForm
    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i) {
        try {
            setNestedEntries(byteCode, operandManager, getOffset(operandManager));
        } catch (Pack200Exception unused) {
            throw new Error("Got a pack200 exception. What to do?");
        }
    }
}
