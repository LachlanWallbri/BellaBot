package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

/* loaded from: classes9.dex */
public class WideForm extends VariableInstructionForm {
    public WideForm(int i, String str) {
        super(i, str);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ByteCodeForm
    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i) {
        int nextWideByteCode = operandManager.nextWideByteCode();
        if (nextWideByteCode == 132) {
            setByteCodeOperandsFormat2(nextWideByteCode, byteCode, operandManager, i);
        } else {
            setByteCodeOperandsFormat1(nextWideByteCode, byteCode, operandManager, i);
        }
    }

    protected void setByteCodeOperandsFormat1(int i, ByteCode byteCode, OperandManager operandManager, int i2) {
        int nextLocal = operandManager.nextLocal();
        int[] iArr = {byteCode.getOpcode(), i};
        setRewrite2Bytes(nextLocal, 2, iArr);
        byteCode.setRewrite(iArr);
    }

    protected void setByteCodeOperandsFormat2(int i, ByteCode byteCode, OperandManager operandManager, int i2) {
        int nextLocal = operandManager.nextLocal();
        int nextShort = operandManager.nextShort();
        int[] iArr = new int[6];
        iArr[0] = byteCode.getOpcode();
        iArr[1] = i;
        setRewrite2Bytes(nextLocal, 2, iArr);
        setRewrite2Bytes(nextShort, 4, iArr);
        byteCode.setRewrite(iArr);
    }
}
