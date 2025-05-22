package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

/* loaded from: classes9.dex */
public abstract class VariableInstructionForm extends ByteCodeForm {
    public VariableInstructionForm(int i, String str) {
        super(i, str);
    }

    public void setRewrite4Bytes(int i, int[] iArr) {
        int i2 = 0;
        while (true) {
            if (i2 >= iArr.length - 3) {
                i2 = -1;
                break;
            } else if (iArr[i2] == -1 && iArr[i2 + 1] == -1 && iArr[i2 + 2] == -1 && iArr[i2 + 3] == -1) {
                break;
            } else {
                i2++;
            }
        }
        setRewrite4Bytes(i, i2, iArr);
    }

    public void setRewrite4Bytes(int i, int i2, int[] iArr) {
        if (i2 < 0) {
            throw new Error("Trying to rewrite " + this + " but there is no room for 4 bytes");
        }
        int i3 = i2 + 3;
        if (i3 > iArr.length) {
            throw new Error("Trying to rewrite " + this + " with an int at position " + i2 + " but this won't fit in the rewrite array");
        }
        iArr[i2] = ((-16777216) & i) >> 24;
        iArr[i2 + 1] = (16711680 & i) >> 16;
        iArr[i2 + 2] = (65280 & i) >> 8;
        iArr[i3] = i & 255;
    }

    public void setRewrite2Bytes(int i, int i2, int[] iArr) {
        if (i2 < 0) {
            throw new Error("Trying to rewrite " + this + " but there is no room for 4 bytes");
        }
        int i3 = i2 + 1;
        if (i3 > iArr.length) {
            throw new Error("Trying to rewrite " + this + " with an int at position " + i2 + " but this won't fit in the rewrite array");
        }
        iArr[i2] = (65280 & i) >> 8;
        iArr[i3] = i & 255;
    }
}
