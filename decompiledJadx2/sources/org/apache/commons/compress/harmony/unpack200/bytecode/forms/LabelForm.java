package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.CodeAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

/* loaded from: classes9.dex */
public class LabelForm extends ByteCodeForm {
    protected boolean widened;

    public LabelForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    public LabelForm(int i, String str, int[] iArr, boolean z) {
        this(i, str, iArr);
        this.widened = z;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ByteCodeForm
    public void fixUpByteCodeTargets(ByteCode byteCode, CodeAttribute codeAttribute) {
        int i = byteCode.getByteCodeTargets()[0];
        int byteCodeIndex = byteCode.getByteCodeIndex();
        byteCode.setOperandSigned2Bytes(((Integer) codeAttribute.byteCodeOffsets.get(i + byteCodeIndex)).intValue() - ((Integer) codeAttribute.byteCodeOffsets.get(byteCodeIndex)).intValue(), 0);
        if (this.widened) {
            byteCode.setNestedPositions(new int[][]{new int[]{0, 4}});
        } else {
            byteCode.setNestedPositions(new int[][]{new int[]{0, 2}});
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ByteCodeForm
    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i) {
        byteCode.setByteCodeTargets(new int[]{operandManager.nextLabel()});
    }
}
