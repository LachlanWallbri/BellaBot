package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

/* loaded from: classes9.dex */
public class NarrowClassRefForm extends ClassRefForm {
    public NarrowClassRefForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    public NarrowClassRefForm(int i, String str, int[] iArr, boolean z) {
        super(i, str, iArr, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ClassRefForm, org.apache.commons.compress.harmony.unpack200.bytecode.forms.ReferenceForm
    public void setNestedEntries(ByteCode byteCode, OperandManager operandManager, int i) throws Pack200Exception {
        super.setNestedEntries(byteCode, operandManager, i);
        if (this.widened) {
            return;
        }
        byteCode.setNestedPositions(new int[][]{new int[]{0, 1}});
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ByteCodeForm
    public boolean nestedMustStartClassPool() {
        return !this.widened;
    }
}
