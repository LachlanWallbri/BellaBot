package org.apache.commons.compress.harmony.unpack200.bytecode;

import androidx.core.os.EnvironmentCompat;
import java.io.DataOutputStream;
import java.io.IOException;
import org.eclipse.paho.client.mqttv3.MqttTopic;

/* loaded from: classes9.dex */
public abstract class CPRef extends ConstantPoolEntry {
    protected String cachedToString;
    CPClass className;
    transient int classNameIndex;
    protected CPNameAndType nameAndType;
    transient int nameAndTypeIndex;

    public CPRef(byte b, CPClass cPClass, CPNameAndType cPNameAndType, int i) {
        super(b, i);
        this.className = cPClass;
        this.nameAndType = cPNameAndType;
        if (cPNameAndType == null || cPClass == null) {
            throw new NullPointerException("Null arguments are not allowed");
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || hashCode() != obj.hashCode()) {
            return false;
        }
        CPRef cPRef = (CPRef) obj;
        return this.className.equals(cPRef.className) && this.nameAndType.equals(cPRef.nameAndType);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{this.className, this.nameAndType};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.nameAndTypeIndex = classConstantPool.indexOf(this.nameAndType);
        this.classNameIndex = classConstantPool.indexOf(this.className);
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public String toString() {
        String str;
        if (this.cachedToString == null) {
            if (getTag() == 9) {
                str = "FieldRef";
            } else if (getTag() == 10) {
                str = "MethoddRef";
            } else {
                str = getTag() == 11 ? "InterfaceMethodRef" : EnvironmentCompat.MEDIA_UNKNOWN;
            }
            this.cachedToString = str + ": " + this.className + MqttTopic.MULTI_LEVEL_WILDCARD + this.nameAndType;
        }
        return this.cachedToString;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry
    protected void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.classNameIndex);
        dataOutputStream.writeShort(this.nameAndTypeIndex);
    }
}
