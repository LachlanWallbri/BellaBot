package org.apache.commons.compress.harmony.unpack200;

import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;

/* loaded from: classes9.dex */
public class AttributeLayout implements IMatcher {
    public static final String ACC_ABSTRACT = "ACC_ABSTRACT";
    public static final String ACC_ANNOTATION = "ACC_ANNOTATION";
    public static final String ACC_ENUM = "ACC_ENUM";
    public static final String ACC_FINAL = "ACC_FINAL";
    public static final String ACC_INTERFACE = "ACC_INTERFACE";
    public static final String ACC_NATIVE = "ACC_NATIVE";
    public static final String ACC_PRIVATE = "ACC_PRIVATE";
    public static final String ACC_PROTECTED = "ACC_PROTECTED";
    public static final String ACC_PUBLIC = "ACC_PUBLIC";
    public static final String ACC_STATIC = "ACC_STATIC";
    public static final String ACC_STRICT = "ACC_STRICT";
    public static final String ACC_SYNCHRONIZED = "ACC_SYNCHRONIZED";
    public static final String ACC_SYNTHETIC = "ACC_SYNTHETIC";
    public static final String ACC_TRANSIENT = "ACC_TRANSIENT";
    public static final String ACC_VOLATILE = "ACC_VOLATILE";
    public static final String ATTRIBUTE_ANNOTATION_DEFAULT = "AnnotationDefault";
    public static final String ATTRIBUTE_CLASS_FILE_VERSION = "class-file version";
    public static final String ATTRIBUTE_CONSTANT_VALUE = "ConstantValue";
    public static final String ATTRIBUTE_DEPRECATED = "Deprecated";
    public static final String ATTRIBUTE_ENCLOSING_METHOD = "EnclosingMethod";
    public static final String ATTRIBUTE_EXCEPTIONS = "Exceptions";
    public static final String ATTRIBUTE_INNER_CLASSES = "InnerClasses";
    public static final String ATTRIBUTE_LINE_NUMBER_TABLE = "LineNumberTable";
    public static final String ATTRIBUTE_LOCAL_VARIABLE_TABLE = "LocalVariableTable";
    public static final String ATTRIBUTE_LOCAL_VARIABLE_TYPE_TABLE = "LocalVariableTypeTable";
    public static final String ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS = "RuntimeInvisibleAnnotations";
    public static final String ATTRIBUTE_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS = "RuntimeInvisibleParameterAnnotations";
    public static final String ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS = "RuntimeVisibleAnnotations";
    public static final String ATTRIBUTE_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS = "RuntimeVisibleParameterAnnotations";
    public static final String ATTRIBUTE_SIGNATURE = "Signature";
    public static final String ATTRIBUTE_SOURCE_FILE = "SourceFile";
    public static final int CONTEXT_CLASS = 0;
    public static final int CONTEXT_CODE = 3;
    public static final int CONTEXT_FIELD = 1;
    public static final int CONTEXT_METHOD = 2;
    private int backwardsCallCount;
    private final int context;
    private final int index;
    private final boolean isDefault;
    private final String layout;
    private long mask;
    private final String name;
    public static final String ATTRIBUTE_CODE = "Code";
    public static final String[] contextNames = {"Class", "Field", "Method", ATTRIBUTE_CODE};

    private static ClassFileEntry getValue(String str, long j, SegmentConstantPool segmentConstantPool) throws Pack200Exception {
        if (str.startsWith("R")) {
            if (str.indexOf(78) != -1) {
                j--;
            }
            if (str.startsWith("RU")) {
                return segmentConstantPool.getValue(1, j);
            }
            if (str.startsWith("RS")) {
                return segmentConstantPool.getValue(8, j);
            }
        } else if (str.startsWith("K")) {
            char charAt = str.charAt(1);
            if (charAt != 'C') {
                if (charAt == 'D') {
                    return segmentConstantPool.getValue(5, j);
                }
                if (charAt == 'F') {
                    return segmentConstantPool.getValue(3, j);
                }
                if (charAt == 'S') {
                    return segmentConstantPool.getValue(6, j);
                }
                if (charAt != 'I') {
                    if (charAt == 'J') {
                        return segmentConstantPool.getValue(4, j);
                    }
                }
            }
            return segmentConstantPool.getValue(2, j);
        }
        throw new Pack200Exception("Unknown layout encoding: " + str);
    }

    public AttributeLayout(String str, int i, String str2, int i2) throws Pack200Exception {
        this(str, i, str2, i2, true);
    }

    public AttributeLayout(String str, int i, String str2, int i2, boolean z) throws Pack200Exception {
        this.index = i2;
        this.context = i;
        if (i2 >= 0) {
            this.mask = 1 << i2;
        } else {
            this.mask = 0L;
        }
        if (i != 0 && i != 3 && i != 1 && i != 2) {
            throw new Pack200Exception("Attribute context out of range: " + i);
        }
        if (str2 == null) {
            throw new Pack200Exception("Cannot have a null layout");
        }
        if (str == null || str.length() == 0) {
            throw new Pack200Exception("Cannot have an unnamed layout");
        }
        this.name = str;
        this.layout = str2;
        this.isDefault = z;
    }

    public Codec getCodec() {
        if (this.layout.indexOf(79) >= 0) {
            return Codec.BRANCH5;
        }
        if (this.layout.indexOf(80) >= 0) {
            return Codec.BCI5;
        }
        if (this.layout.indexOf(83) >= 0 && this.layout.indexOf("KS") < 0 && this.layout.indexOf("RS") < 0) {
            return Codec.SIGNED5;
        }
        if (this.layout.indexOf(66) >= 0) {
            return Codec.BYTE1;
        }
        return Codec.UNSIGNED5;
    }

    public String getLayout() {
        return this.layout;
    }

    public ClassFileEntry getValue(long j, SegmentConstantPool segmentConstantPool) throws Pack200Exception {
        return getValue(this.layout, j, segmentConstantPool);
    }

    public ClassFileEntry getValue(long j, String str, SegmentConstantPool segmentConstantPool) throws Pack200Exception {
        if (!this.layout.startsWith("KQ")) {
            return getValue(this.layout, j, segmentConstantPool);
        }
        if (str.equals("Ljava/lang/String;")) {
            return getValue("KS", j, segmentConstantPool);
        }
        return getValue("K" + str + this.layout.substring(2), j, segmentConstantPool);
    }

    public int hashCode() {
        String str = this.name;
        int hashCode = str != null ? str.hashCode() + 31 : 1;
        String str2 = this.layout;
        if (str2 != null) {
            hashCode = (hashCode * 31) + str2.hashCode();
        }
        return (((hashCode * 31) + this.index) * 31) + this.context;
    }

    @Override // org.apache.commons.compress.harmony.unpack200.IMatcher
    public boolean matches(long j) {
        return (j & this.mask) != 0;
    }

    public String toString() {
        return contextNames[this.context] + ": " + this.name;
    }

    public int getContext() {
        return this.context;
    }

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return this.name;
    }

    public int numBackwardsCallables() {
        if (this.layout == "*") {
            return 1;
        }
        return this.backwardsCallCount;
    }

    public boolean isDefaultLayout() {
        return this.isDefault;
    }

    public void setBackwardsCallCount(int i) {
        this.backwardsCallCount = i;
    }
}
