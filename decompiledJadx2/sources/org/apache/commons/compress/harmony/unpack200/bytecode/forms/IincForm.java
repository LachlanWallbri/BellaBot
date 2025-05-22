package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

/* loaded from: classes9.dex */
public class IincForm extends ByteCodeForm {
    public IincForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ByteCodeForm
    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i) {
        byteCode.setOperandBytes(new int[]{operandManager.nextLocal(), operandManager.nextByte()});
    }
}
