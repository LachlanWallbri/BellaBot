package org.apache.commons.compress.harmony.unpack200.bytecode;

/* loaded from: classes9.dex */
public abstract class CPConstant extends ConstantPoolEntry {
    private final Object value;

    public CPConstant(byte b, Object obj, int i) {
        super(b, i);
        this.value = obj;
        if (obj == null) {
            throw new NullPointerException("Null arguments are not allowed");
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CPConstant cPConstant = (CPConstant) obj;
        Object obj2 = this.value;
        if (obj2 == null) {
            if (cPConstant.value != null) {
                return false;
            }
        } else if (!obj2.equals(cPConstant.value)) {
            return false;
        }
        return true;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry, org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry
    public int hashCode() {
        Object obj = this.value;
        return 31 + (obj == null ? 0 : obj.hashCode());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object getValue() {
        return this.value;
    }
}
