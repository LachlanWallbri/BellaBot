package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

/* loaded from: classes9.dex */
public class ClassFile {
    public int accessFlags;
    public Attribute[] attributes;
    public ClassFileEntry[] fields;
    public int[] interfaces;
    public int major;
    public ClassFileEntry[] methods;
    public int minor;
    public int superClass;
    public int thisClass;
    private final int magic = -889275714;
    public ClassConstantPool pool = new ClassConstantPool();

    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(-889275714);
        dataOutputStream.writeShort(this.minor);
        dataOutputStream.writeShort(this.major);
        dataOutputStream.writeShort(this.pool.size() + 1);
        int i = 1;
        while (i <= this.pool.size()) {
            ConstantPoolEntry constantPoolEntry = (ConstantPoolEntry) this.pool.get(i);
            constantPoolEntry.doWrite(dataOutputStream);
            if (constantPoolEntry.getTag() == 6 || constantPoolEntry.getTag() == 5) {
                i++;
            }
            i++;
        }
        dataOutputStream.writeShort(this.accessFlags);
        dataOutputStream.writeShort(this.thisClass);
        dataOutputStream.writeShort(this.superClass);
        dataOutputStream.writeShort(this.interfaces.length);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.interfaces;
            if (i3 >= iArr.length) {
                break;
            }
            dataOutputStream.writeShort(iArr[i3]);
            i3++;
        }
        dataOutputStream.writeShort(this.fields.length);
        int i4 = 0;
        while (true) {
            ClassFileEntry[] classFileEntryArr = this.fields;
            if (i4 >= classFileEntryArr.length) {
                break;
            }
            classFileEntryArr[i4].write(dataOutputStream);
            i4++;
        }
        dataOutputStream.writeShort(this.methods.length);
        int i5 = 0;
        while (true) {
            ClassFileEntry[] classFileEntryArr2 = this.methods;
            if (i5 >= classFileEntryArr2.length) {
                break;
            }
            classFileEntryArr2[i5].write(dataOutputStream);
            i5++;
        }
        dataOutputStream.writeShort(this.attributes.length);
        while (true) {
            Attribute[] attributeArr = this.attributes;
            if (i2 >= attributeArr.length) {
                return;
            }
            attributeArr[i2].write(dataOutputStream);
            i2++;
        }
    }
}
