package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;

/* loaded from: classes9.dex */
public class LocalVariableTableAttribute extends BCIRenumberedAttribute {
    private static CPUTF8 attributeName;
    private int codeLength;
    private int[] descriptor_indexes;
    private final CPUTF8[] descriptors;
    private final int[] indexes;
    private final int[] lengths;
    private final int local_variable_table_length;
    private int[] name_indexes;
    private final CPUTF8[] names;
    private final int[] start_pcs;

    public static void setAttributeName(CPUTF8 cputf8) {
        attributeName = cputf8;
    }

    public LocalVariableTableAttribute(int i, int[] iArr, int[] iArr2, CPUTF8[] cputf8Arr, CPUTF8[] cputf8Arr2, int[] iArr3) {
        super(attributeName);
        this.local_variable_table_length = i;
        this.start_pcs = iArr;
        this.lengths = iArr2;
        this.names = cputf8Arr;
        this.descriptors = cputf8Arr2;
        this.indexes = iArr3;
    }

    public void setCodeLength(int i) {
        this.codeLength = i;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        return (this.local_variable_table_length * 10) + 2;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.local_variable_table_length);
        for (int i = 0; i < this.local_variable_table_length; i++) {
            dataOutputStream.writeShort(this.start_pcs[i]);
            dataOutputStream.writeShort(this.lengths[i]);
            dataOutputStream.writeShort(this.name_indexes[i]);
            dataOutputStream.writeShort(this.descriptor_indexes[i]);
            dataOutputStream.writeShort(this.indexes[i]);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getAttributeName());
        for (int i = 0; i < this.local_variable_table_length; i++) {
            arrayList.add(this.names[i]);
            arrayList.add(this.descriptors[i]);
        }
        ClassFileEntry[] classFileEntryArr = new ClassFileEntry[arrayList.size()];
        arrayList.toArray(classFileEntryArr);
        return classFileEntryArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        int i = this.local_variable_table_length;
        this.name_indexes = new int[i];
        this.descriptor_indexes = new int[i];
        for (int i2 = 0; i2 < this.local_variable_table_length; i2++) {
            this.names[i2].resolve(classConstantPool);
            this.descriptors[i2].resolve(classConstantPool);
            this.name_indexes[i2] = classConstantPool.indexOf(this.names[i2]);
            this.descriptor_indexes[i2] = classConstantPool.indexOf(this.descriptors[i2]);
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "LocalVariableTable: " + this.local_variable_table_length + " variables";
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute
    protected int[] getStartPCs() {
        return this.start_pcs;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute
    public void renumber(List list) throws Pack200Exception {
        int[] iArr = this.start_pcs;
        int[] iArr2 = new int[iArr.length];
        int i = 0;
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        super.renumber(list);
        int i2 = this.codeLength;
        while (true) {
            int[] iArr3 = this.lengths;
            if (i >= iArr3.length) {
                return;
            }
            int i3 = this.start_pcs[i];
            int i4 = iArr2[i] + iArr3[i];
            if (i4 < 0) {
                throw new Pack200Exception("Error renumbering bytecode indexes");
            }
            this.lengths[i] = i4 == list.size() ? i2 - i3 : ((Integer) list.get(i4)).intValue() - i3;
            i++;
        }
    }
}
