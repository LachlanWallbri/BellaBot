package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.commons.compress.harmony.unpack200.Segment;
import org.apache.commons.compress.harmony.unpack200.bytecode.forms.ByteCodeForm;

/* loaded from: classes9.dex */
public class ByteCode extends ClassFileEntry {
    private static ByteCode[] noArgByteCodes = new ByteCode[255];
    private final ByteCodeForm byteCodeForm;
    private int byteCodeOffset;
    private int[] byteCodeTargets;
    private ClassFileEntry[] nested;
    private int[][] nestedPositions;
    private int[] rewrite;

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        return this == obj;
    }

    public static ByteCode getByteCode(int i) {
        int i2 = i & 255;
        if (ByteCodeForm.get(i2).hasNoOperand()) {
            ByteCode[] byteCodeArr = noArgByteCodes;
            if (byteCodeArr[i2] == null) {
                byteCodeArr[i2] = new ByteCode(i2);
            }
            return noArgByteCodes[i2];
        }
        return new ByteCode(i2);
    }

    protected ByteCode(int i) {
        this(i, ClassFileEntry.NONE);
    }

    protected ByteCode(int i, ClassFileEntry[] classFileEntryArr) {
        this.byteCodeOffset = -1;
        this.byteCodeForm = ByteCodeForm.get(i);
        this.rewrite = this.byteCodeForm.getRewriteCopy();
        this.nested = classFileEntryArr;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    protected void doWrite(DataOutputStream dataOutputStream) throws IOException {
        int i = 0;
        while (true) {
            int[] iArr = this.rewrite;
            if (i >= iArr.length) {
                return;
            }
            dataOutputStream.writeByte(iArr[i]);
            i++;
        }
    }

    public void extractOperands(OperandManager operandManager, Segment segment, int i) {
        getByteCodeForm().setByteCodeOperands(this, operandManager, i);
    }

    protected ByteCodeForm getByteCodeForm() {
        return this.byteCodeForm;
    }

    public int getLength() {
        return this.rewrite.length;
    }

    public String getName() {
        return getByteCodeForm().getName();
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        return this.nested;
    }

    public int getOpcode() {
        return getByteCodeForm().getOpcode();
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        return objectHashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        if (this.nested.length > 0) {
            for (int i = 0; i < this.nested.length; i++) {
                int i2 = getNestedPosition(i)[1];
                if (i2 == 1) {
                    setOperandByte(classConstantPool.indexOf(this.nested[i]), getNestedPosition(i)[0]);
                } else if (i2 == 2) {
                    setOperand2Bytes(classConstantPool.indexOf(this.nested[i]), getNestedPosition(i)[0]);
                } else {
                    throw new Error("Unhandled resolve " + this);
                }
            }
        }
    }

    public void setOperandBytes(int[] iArr) {
        int firstOperandIndex = getByteCodeForm().firstOperandIndex();
        int operandLength = getByteCodeForm().operandLength();
        if (firstOperandIndex < 1) {
            throw new Error("Trying to rewrite " + this + " that has no rewrite");
        }
        if (operandLength == iArr.length) {
            for (int i = 0; i < operandLength; i++) {
                this.rewrite[i + firstOperandIndex] = iArr[i] & 255;
            }
            return;
        }
        throw new Error("Trying to rewrite " + this + " with " + iArr.length + " but bytecode has length " + this.byteCodeForm.operandLength());
    }

    public void setOperand2Bytes(int i, int i2) {
        int firstOperandIndex = getByteCodeForm().firstOperandIndex();
        int length = getByteCodeForm().getRewrite().length;
        if (firstOperandIndex < 1) {
            throw new Error("Trying to rewrite " + this + " that has no rewrite");
        }
        int i3 = firstOperandIndex + i2;
        int i4 = i3 + 1;
        if (i4 > length) {
            throw new Error("Trying to rewrite " + this + " with an int at position " + i2 + " but this won't fit in the rewrite array");
        }
        int[] iArr = this.rewrite;
        iArr[i3] = (65280 & i) >> 8;
        iArr[i4] = i & 255;
    }

    public void setOperandSigned2Bytes(int i, int i2) {
        if (i >= 0) {
            setOperand2Bytes(i, i2);
        } else {
            setOperand2Bytes(i + 65536, i2);
        }
    }

    public void setOperandByte(int i, int i2) {
        int firstOperandIndex = getByteCodeForm().firstOperandIndex();
        int operandLength = getByteCodeForm().operandLength();
        if (firstOperandIndex < 1) {
            throw new Error("Trying to rewrite " + this + " that has no rewrite");
        }
        int i3 = firstOperandIndex + i2;
        if (i3 > operandLength) {
            throw new Error("Trying to rewrite " + this + " with an byte at position " + i2 + " but this won't fit in the rewrite array");
        }
        this.rewrite[i3] = i & 255;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return getByteCodeForm().getName();
    }

    public void setNested(ClassFileEntry[] classFileEntryArr) {
        this.nested = classFileEntryArr;
    }

    public void setNestedPositions(int[][] iArr) {
        this.nestedPositions = iArr;
    }

    public int[][] getNestedPositions() {
        return this.nestedPositions;
    }

    public int[] getNestedPosition(int i) {
        return getNestedPositions()[i];
    }

    public boolean hasMultipleByteCodes() {
        return getByteCodeForm().hasMultipleByteCodes();
    }

    public void setByteCodeIndex(int i) {
        this.byteCodeOffset = i;
    }

    public int getByteCodeIndex() {
        return this.byteCodeOffset;
    }

    public void setByteCodeTargets(int[] iArr) {
        this.byteCodeTargets = iArr;
    }

    public int[] getByteCodeTargets() {
        return this.byteCodeTargets;
    }

    public void applyByteCodeTargetFixup(CodeAttribute codeAttribute) {
        getByteCodeForm().fixUpByteCodeTargets(this, codeAttribute);
    }

    public void setRewrite(int[] iArr) {
        this.rewrite = iArr;
    }

    public int[] getRewrite() {
        return this.rewrite;
    }

    public boolean nestedMustStartClassPool() {
        return this.byteCodeForm.nestedMustStartClassPool();
    }
}
