package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class InnerClassesAttribute extends Attribute {
    private static CPUTF8 attributeName;
    private final List innerClasses;
    private final List nestedClassFileEntries;

    public static void setAttributeName(CPUTF8 cputf8) {
        attributeName = cputf8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class InnerClassesEntry {
        int inner_class_access_flags;
        CPClass inner_class_info;
        CPUTF8 inner_class_name;
        CPClass outer_class_info;
        int inner_class_info_index = -1;
        int outer_class_info_index = -1;
        int inner_name_index = -1;

        public InnerClassesEntry(CPClass cPClass, CPClass cPClass2, CPUTF8 cputf8, int i) {
            this.inner_class_access_flags = -1;
            this.inner_class_info = cPClass;
            this.outer_class_info = cPClass2;
            this.inner_class_name = cputf8;
            this.inner_class_access_flags = i;
        }

        public void resolve(ClassConstantPool classConstantPool) {
            CPClass cPClass = this.inner_class_info;
            if (cPClass != null) {
                cPClass.resolve(classConstantPool);
                this.inner_class_info_index = classConstantPool.indexOf(this.inner_class_info);
            } else {
                this.inner_class_info_index = 0;
            }
            CPUTF8 cputf8 = this.inner_class_name;
            if (cputf8 != null) {
                cputf8.resolve(classConstantPool);
                this.inner_name_index = classConstantPool.indexOf(this.inner_class_name);
            } else {
                this.inner_name_index = 0;
            }
            CPClass cPClass2 = this.outer_class_info;
            if (cPClass2 != null) {
                cPClass2.resolve(classConstantPool);
                this.outer_class_info_index = classConstantPool.indexOf(this.outer_class_info);
            } else {
                this.outer_class_info_index = 0;
            }
        }

        public void write(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeShort(this.inner_class_info_index);
            dataOutputStream.writeShort(this.outer_class_info_index);
            dataOutputStream.writeShort(this.inner_name_index);
            dataOutputStream.writeShort(this.inner_class_access_flags);
        }
    }

    public InnerClassesAttribute(String str) {
        super(attributeName);
        this.innerClasses = new ArrayList();
        this.nestedClassFileEntries = new ArrayList();
        this.nestedClassFileEntries.add(getAttributeName());
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        InnerClassesAttribute innerClassesAttribute = (InnerClassesAttribute) obj;
        if (getAttributeName() == null) {
            if (innerClassesAttribute.getAttributeName() != null) {
                return false;
            }
        } else if (!getAttributeName().equals(innerClassesAttribute.getAttributeName())) {
            return false;
        }
        return true;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        return (this.innerClasses.size() * 8) + 2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        ClassFileEntry[] classFileEntryArr = new ClassFileEntry[this.nestedClassFileEntries.size()];
        for (int i = 0; i < classFileEntryArr.length; i++) {
            classFileEntryArr[i] = (ClassFileEntry) this.nestedClassFileEntries.get(i);
        }
        return classFileEntryArr;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        return (super.hashCode() * 31) + (getAttributeName() == null ? 0 : getAttributeName().hashCode());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        for (int i = 0; i < this.innerClasses.size(); i++) {
            ((InnerClassesEntry) this.innerClasses.get(i)).resolve(classConstantPool);
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "InnerClasses: " + getAttributeName();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void doWrite(DataOutputStream dataOutputStream) throws IOException {
        super.doWrite(dataOutputStream);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.innerClasses.size());
        for (int i = 0; i < this.innerClasses.size(); i++) {
            ((InnerClassesEntry) this.innerClasses.get(i)).write(dataOutputStream);
        }
    }

    public void addInnerClassesEntry(CPClass cPClass, CPClass cPClass2, CPUTF8 cputf8, int i) {
        if (cPClass != null) {
            this.nestedClassFileEntries.add(cPClass);
        }
        if (cPClass2 != null) {
            this.nestedClassFileEntries.add(cPClass2);
        }
        if (cputf8 != null) {
            this.nestedClassFileEntries.add(cputf8);
        }
        addInnerClassesEntry(new InnerClassesEntry(cPClass, cPClass2, cputf8, i));
    }

    private void addInnerClassesEntry(InnerClassesEntry innerClassesEntry) {
        this.innerClasses.add(innerClassesEntry);
    }
}
