package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.compress.harmony.unpack200.Segment;

/* loaded from: classes9.dex */
public class CodeAttribute extends BCIRenumberedAttribute {
    private static CPUTF8 attributeName;
    public List attributes;
    public List byteCodeOffsets;
    public List byteCodes;
    public int codeLength;
    public List exceptionTable;
    public int maxLocals;
    public int maxStack;

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute
    protected int[] getStartPCs() {
        return null;
    }

    public CodeAttribute(int i, int i2, byte[] bArr, Segment segment, OperandManager operandManager, List list) {
        super(attributeName);
        this.attributes = new ArrayList();
        this.byteCodeOffsets = new ArrayList();
        this.byteCodes = new ArrayList();
        this.maxLocals = i2;
        this.maxStack = i;
        this.codeLength = 0;
        this.exceptionTable = list;
        this.byteCodeOffsets.add(0);
        int i3 = 0;
        int i4 = 0;
        while (i3 < bArr.length) {
            ByteCode byteCode = ByteCode.getByteCode(bArr[i3] & 255);
            byteCode.setByteCodeIndex(i4);
            i4++;
            byteCode.extractOperands(operandManager, segment, this.codeLength);
            this.byteCodes.add(byteCode);
            this.codeLength += byteCode.getLength();
            int intValue = ((Integer) this.byteCodeOffsets.get(r1.size() - 1)).intValue();
            if (byteCode.hasMultipleByteCodes()) {
                this.byteCodeOffsets.add(Integer.valueOf(intValue + 1));
                i4++;
            }
            if (i3 < bArr.length - 1) {
                this.byteCodeOffsets.add(Integer.valueOf(intValue + byteCode.getLength()));
            }
            if (byteCode.getOpcode() == 196) {
                i3++;
            }
            i3++;
        }
        for (int i5 = 0; i5 < this.byteCodes.size(); i5++) {
            ((ByteCode) this.byteCodes.get(i5)).applyByteCodeTargetFixup(this);
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        int i = 0;
        for (int i2 = 0; i2 < this.attributes.size(); i2++) {
            i += ((Attribute) this.attributes.get(i2)).getLengthIncludingHeader();
        }
        return this.codeLength + 8 + 2 + (this.exceptionTable.size() * 8) + 2 + i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        ArrayList arrayList = new ArrayList(this.attributes.size() + this.byteCodes.size() + 10);
        arrayList.add(getAttributeName());
        arrayList.addAll(this.byteCodes);
        arrayList.addAll(this.attributes);
        for (int i = 0; i < this.exceptionTable.size(); i++) {
            CPClass catchType = ((ExceptionTableEntry) this.exceptionTable.get(i)).getCatchType();
            if (catchType != null) {
                arrayList.add(catchType);
            }
        }
        ClassFileEntry[] classFileEntryArr = new ClassFileEntry[arrayList.size()];
        arrayList.toArray(classFileEntryArr);
        return classFileEntryArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        for (int i = 0; i < this.attributes.size(); i++) {
            ((Attribute) this.attributes.get(i)).resolve(classConstantPool);
        }
        for (int i2 = 0; i2 < this.byteCodes.size(); i2++) {
            ((ByteCode) this.byteCodes.get(i2)).resolve(classConstantPool);
        }
        for (int i3 = 0; i3 < this.exceptionTable.size(); i3++) {
            ((ExceptionTableEntry) this.exceptionTable.get(i3)).resolve(classConstantPool);
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "Code: " + getLength() + " bytes";
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.maxStack);
        dataOutputStream.writeShort(this.maxLocals);
        dataOutputStream.writeInt(this.codeLength);
        for (int i = 0; i < this.byteCodes.size(); i++) {
            ((ByteCode) this.byteCodes.get(i)).write(dataOutputStream);
        }
        dataOutputStream.writeShort(this.exceptionTable.size());
        for (int i2 = 0; i2 < this.exceptionTable.size(); i2++) {
            ((ExceptionTableEntry) this.exceptionTable.get(i2)).write(dataOutputStream);
        }
        dataOutputStream.writeShort(this.attributes.size());
        for (int i3 = 0; i3 < this.attributes.size(); i3++) {
            ((Attribute) this.attributes.get(i3)).write(dataOutputStream);
        }
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
        if (attribute instanceof LocalVariableTableAttribute) {
            ((LocalVariableTableAttribute) attribute).setCodeLength(this.codeLength);
        }
        if (attribute instanceof LocalVariableTypeTableAttribute) {
            ((LocalVariableTypeTableAttribute) attribute).setCodeLength(this.codeLength);
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute
    public void renumber(List list) {
        for (int i = 0; i < this.exceptionTable.size(); i++) {
            ((ExceptionTableEntry) this.exceptionTable.get(i)).renumber(list);
        }
    }

    public static void setAttributeName(CPUTF8 cputf8) {
        attributeName = cputf8;
    }
}
