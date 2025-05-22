package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.CodeAttribute;

/* loaded from: classes9.dex */
public abstract class SwitchForm extends VariableInstructionForm {
    public SwitchForm(int i, String str) {
        super(i, str);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ByteCodeForm
    public void fixUpByteCodeTargets(ByteCode byteCode, CodeAttribute codeAttribute) {
        int[] byteCodeTargets = byteCode.getByteCodeTargets();
        int length = byteCodeTargets.length;
        int[] iArr = new int[length];
        int byteCodeIndex = byteCode.getByteCodeIndex();
        int intValue = ((Integer) codeAttribute.byteCodeOffsets.get(byteCodeIndex)).intValue();
        for (int i = 0; i < length; i++) {
            iArr[i] = ((Integer) codeAttribute.byteCodeOffsets.get(byteCodeTargets[i] + byteCodeIndex)).intValue() - intValue;
        }
        int[] rewrite = byteCode.getRewrite();
        for (int i2 = 0; i2 < length; i2++) {
            setRewrite4Bytes(iArr[i2], rewrite);
        }
    }
}
