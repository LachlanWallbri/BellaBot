package org.mozilla.classfile;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes2.dex */
final class FieldOrMethodRef {
    private String className;
    private int hashCode = -1;
    private String name;
    private String type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FieldOrMethodRef(String str, String str2, String str3) {
        this.className = str;
        this.name = str2;
        this.type = str3;
    }

    public String getClassName() {
        return this.className;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FieldOrMethodRef)) {
            return false;
        }
        FieldOrMethodRef fieldOrMethodRef = (FieldOrMethodRef) obj;
        return this.className.equals(fieldOrMethodRef.className) && this.name.equals(fieldOrMethodRef.name) && this.type.equals(fieldOrMethodRef.type);
    }

    public int hashCode() {
        if (this.hashCode == -1) {
            this.hashCode = (this.className.hashCode() ^ this.name.hashCode()) ^ this.type.hashCode();
        }
        return this.hashCode;
    }
}
