package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.commons.compress.harmony.unpack200.AttributeLayout;

/* loaded from: classes9.dex */
public class EnclosingMethodAttribute extends Attribute {
    private static CPUTF8 attributeName;
    private int class_index;
    private final CPClass cpClass;
    private final CPNameAndType method;
    private int method_index;

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected int getLength() {
        return 4;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        return AttributeLayout.ATTRIBUTE_ENCLOSING_METHOD;
    }

    public static void setAttributeName(CPUTF8 cputf8) {
        attributeName = cputf8;
    }

    public EnclosingMethodAttribute(CPClass cPClass, CPNameAndType cPNameAndType) {
        super(attributeName);
        this.cpClass = cPClass;
        this.method = cPNameAndType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        CPNameAndType cPNameAndType = this.method;
        return cPNameAndType != null ? new ClassFileEntry[]{attributeName, this.cpClass, cPNameAndType} : new ClassFileEntry[]{attributeName, this.cpClass};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.cpClass.resolve(classConstantPool);
        this.class_index = classConstantPool.indexOf(this.cpClass);
        CPNameAndType cPNameAndType = this.method;
        if (cPNameAndType != null) {
            cPNameAndType.resolve(classConstantPool);
            this.method_index = classConstantPool.indexOf(this.method);
        } else {
            this.method_index = 0;
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.Attribute
    protected void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.class_index);
        dataOutputStream.writeShort(this.method_index);
    }
}
