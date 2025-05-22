package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

/* loaded from: classes9.dex */
public class LookupSwitchForm extends SwitchForm {
    public LookupSwitchForm(int i, String str) {
        super(i, str);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.forms.ByteCodeForm
    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i) {
        int nextCaseCount = operandManager.nextCaseCount();
        int nextLabel = operandManager.nextLabel();
        int[] iArr = new int[nextCaseCount];
        for (int i2 = 0; i2 < nextCaseCount; i2++) {
            iArr[i2] = operandManager.nextCaseValues();
        }
        int[] iArr2 = new int[nextCaseCount];
        for (int i3 = 0; i3 < nextCaseCount; i3++) {
            iArr2[i3] = operandManager.nextLabel();
        }
        int i4 = nextCaseCount + 1;
        int[] iArr3 = new int[i4];
        iArr3[0] = nextLabel;
        for (int i5 = 1; i5 < i4; i5++) {
            iArr3[i5] = iArr2[i5 - 1];
        }
        byteCode.setByteCodeTargets(iArr3);
        int i6 = 3 - (i % 4);
        int[] iArr4 = new int[i6 + 1 + 4 + 4 + (iArr.length * 4) + (iArr2.length * 4)];
        iArr4[0] = byteCode.getOpcode();
        int i7 = 1;
        int i8 = 0;
        while (i8 < i6) {
            iArr4[i7] = 0;
            i8++;
            i7++;
        }
        int i9 = i7 + 1;
        iArr4[i7] = -1;
        int i10 = i9 + 1;
        iArr4[i9] = -1;
        int i11 = i10 + 1;
        iArr4[i10] = -1;
        int i12 = i11 + 1;
        iArr4[i11] = -1;
        setRewrite4Bytes(iArr.length, i12, iArr4);
        int i13 = i12 + 4;
        for (int i14 : iArr) {
            setRewrite4Bytes(i14, i13, iArr4);
            int i15 = i13 + 4;
            int i16 = i15 + 1;
            iArr4[i15] = -1;
            int i17 = i16 + 1;
            iArr4[i16] = -1;
            int i18 = i17 + 1;
            iArr4[i17] = -1;
            i13 = i18 + 1;
            iArr4[i18] = -1;
        }
        byteCode.setRewrite(iArr4);
    }
}
