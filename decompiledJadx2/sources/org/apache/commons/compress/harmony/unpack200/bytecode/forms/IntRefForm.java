package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

/* loaded from: classes9.dex */
public class IntRefForm extends SingleByteReferenceForm {
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.SingleByteReferenceForm, org.apache.commons.compress.harmony.unpack200.bytecode.forms.ReferenceForm
    protected int getPoolID() {
        return 2;
    }

    public IntRefForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    public IntRefForm(int i, String str, int[] iArr, boolean z) {
        this(i, str, iArr);
        this.widened = z;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.SingleByteReferenceForm, org.apache.commons.compress.harmony.unpack200.bytecode.forms.ReferenceForm
    protected int getOffset(OperandManager operandManager) {
        return operandManager.nextIntRef();
    }
}
