package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.compress.harmony.unpack200.bytecode.AnnotationsAttribute;

/* loaded from: classes9.dex */
public class AnnotationDefaultAttribute extends AnnotationsAttribute {
    private static CPUTF8 attributeName;
    private final AnnotationsAttribute.ElementValue element_value;

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        return this == obj;
    }

    public static void setAttributeName(CPUTF8 cputf8) {
        attributeName = cputf8;
    }

    public AnnotationDefaultAttribute(AnnotationsAttribute.ElementValue elementValue) {
        super(attributeName);
        this.element_value = elementValue;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        return this.element_value.getLength();
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dataOutputStream) throws IOException {
        this.element_value.writeBody(dataOutputStream);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.element_value.resolve(classConstantPool);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return "AnnotationDefault: " + this.element_value;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(attributeName);
        arrayList.addAll(this.element_value.getClassFileEntries());
        ClassFileEntry[] classFileEntryArr = new ClassFileEntry[arrayList.size()];
        for (int i = 0; i < classFileEntryArr.length; i++) {
            classFileEntryArr[i] = (ClassFileEntry) arrayList.get(i);
        }
        return classFileEntryArr;
    }
}
