package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

/* loaded from: classes9.dex */
public abstract class ClassSpecificReferenceForm extends ReferenceForm {
    protected abstract String context(OperandManager operandManager);

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ReferenceForm
    protected abstract int getOffset(OperandManager operandManager);

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ReferenceForm
    protected abstract int getPoolID();

    public ClassSpecificReferenceForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ReferenceForm
    public void setNestedEntries(ByteCode byteCode, OperandManager operandManager, int i) throws Pack200Exception {
        byteCode.setNested(new ClassFileEntry[]{operandManager.globalConstantPool().getClassSpecificPoolEntry(getPoolID(), i, context(operandManager))});
        byteCode.setNestedPositions(new int[][]{new int[]{0, 2}});
    }
}
