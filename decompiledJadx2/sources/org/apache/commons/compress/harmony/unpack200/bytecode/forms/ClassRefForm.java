package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

/* loaded from: classes9.dex */
public class ClassRefForm extends ReferenceForm {
    protected boolean widened;

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ReferenceForm
    protected int getPoolID() {
        return 7;
    }

    public ClassRefForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    public ClassRefForm(int i, String str, int[] iArr, boolean z) {
        this(i, str, iArr);
        this.widened = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ReferenceForm
    public void setNestedEntries(ByteCode byteCode, OperandManager operandManager, int i) throws Pack200Exception {
        if (i != 0) {
            super.setNestedEntries(byteCode, operandManager, i - 1);
        } else {
            byteCode.setNested(new ClassFileEntry[]{operandManager.globalConstantPool().getClassPoolEntry(operandManager.getCurrentClass())});
            byteCode.setNestedPositions(new int[][]{new int[]{0, 2}});
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ReferenceForm
    public int getOffset(OperandManager operandManager) {
        return operandManager.nextClassRef();
    }
}
