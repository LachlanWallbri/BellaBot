package org.apache.commons.compress.harmony.pack200;

import org.apache.commons.compress.harmony.pack200.Segment;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Label;

/* loaded from: classes9.dex */
public class NewAttribute extends Attribute {
    private char[] buf;
    private ClassReader classReader;
    private int codeOff;
    private byte[] contents;
    private boolean contextClass;
    private boolean contextCode;
    private boolean contextField;
    private boolean contextMethod;
    private Label[] labels;
    private final String layout;

    public boolean isUnknown() {
        return false;
    }

    public NewAttribute(String str, String str2, int i) {
        super(str);
        this.contextClass = false;
        this.contextMethod = false;
        this.contextField = false;
        this.contextCode = false;
        this.layout = str2;
        addContext(i);
    }

    public NewAttribute(ClassReader classReader, String str, String str2, byte[] bArr, char[] cArr, int i, Label[] labelArr) {
        super(str);
        this.contextClass = false;
        this.contextMethod = false;
        this.contextField = false;
        this.contextCode = false;
        this.classReader = classReader;
        this.contents = bArr;
        this.layout = str2;
        this.codeOff = i;
        this.labels = labelArr;
        this.buf = cArr;
    }

    public void addContext(int i) {
        if (i == 0) {
            this.contextClass = true;
            return;
        }
        if (i == 1) {
            this.contextField = true;
        } else if (i == 2) {
            this.contextMethod = true;
        } else {
            if (i != 3) {
                return;
            }
            this.contextCode = true;
        }
    }

    public boolean isContextClass() {
        return this.contextClass;
    }

    public boolean isContextMethod() {
        return this.contextMethod;
    }

    public boolean isContextField() {
        return this.contextField;
    }

    public boolean isContextCode() {
        return this.contextCode;
    }

    public String getLayout() {
        return this.layout;
    }

    public boolean isCodeAttribute() {
        return this.codeOff != -1;
    }

    protected Attribute read(ClassReader classReader, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        byte[] bArr = new byte[i2];
        System.arraycopy(classReader.b, i, bArr, 0, i2);
        return new NewAttribute(classReader, this.type, this.layout, bArr, cArr, i3, labelArr);
    }

    public boolean isUnknown(int i) {
        if (i == 0) {
            return !this.contextClass;
        }
        if (i == 1) {
            return !this.contextField;
        }
        if (i == 2) {
            return !this.contextMethod;
        }
        if (i != 3) {
            return false;
        }
        return !this.contextCode;
    }

    public String readUTF8(int i) {
        return this.classReader.readUTF8(i, this.buf);
    }

    public String readClass(int i) {
        return this.classReader.readClass(i, this.buf);
    }

    public Object readConst(int i) {
        return this.classReader.readConst(i, this.buf);
    }

    public byte[] getBytes() {
        return this.contents;
    }

    public Label getLabel(int i) {
        return this.labels[i];
    }

    /* loaded from: classes9.dex */
    public static class ErrorAttribute extends NewAttribute {
        public ErrorAttribute(String str, int i) {
            super(str, "", i);
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttribute
        protected Attribute read(ClassReader classReader, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
            throw new Error("Attribute " + this.type + " was found");
        }
    }

    /* loaded from: classes9.dex */
    public static class StripAttribute extends NewAttribute {
        @Override // org.apache.commons.compress.harmony.pack200.NewAttribute
        protected Attribute read(ClassReader classReader, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
            return null;
        }

        public StripAttribute(String str, int i) {
            super(str, "", i);
        }
    }

    /* loaded from: classes9.dex */
    public static class PassAttribute extends NewAttribute {
        public PassAttribute(String str, int i) {
            super(str, "", i);
        }

        @Override // org.apache.commons.compress.harmony.pack200.NewAttribute
        protected Attribute read(ClassReader classReader, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
            throw new Segment.PassException();
        }
    }
}
