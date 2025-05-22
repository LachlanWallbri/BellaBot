package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

/* loaded from: classes9.dex */
public class NewClassRefForm extends ClassRefForm {
    public NewClassRefForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ReferenceForm, org.apache.commons.compress.harmony.unpack200.bytecode.forms.ByteCodeForm
    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i) {
        int offset = getOffset(operandManager);
        if (offset == 0) {
            byteCode.setNested(new ClassFileEntry[]{operandManager.globalConstantPool().getClassPoolEntry(operandManager.getCurrentClass())});
            byteCode.setNestedPositions(new int[][]{new int[]{0, 2}});
        } else {
            try {
                setNestedEntries(byteCode, operandManager, offset);
            } catch (Pack200Exception unused) {
                throw new Error("Got a pack200 exception. What to do?");
            }
        }
        operandManager.setNewClass(((CPClass) byteCode.getNestedClassFileEntries()[0]).getName());
    }
}
