package org.objenesis.instantiator.sun;

import com.iflytek.speech.VoiceWakeuperAidl;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.commons.compress.harmony.unpack200.AttributeLayout;
import org.apache.commons.io.FilenameUtils;
import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;
import org.objenesis.instantiator.util.ClassDefinitionUtils;
import org.objenesis.instantiator.util.ClassUtils;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
@Instantiator(Typology.STANDARD)
/* loaded from: classes9.dex */
public class MagicInstantiator<T> implements ObjectInstantiator<T> {
    private static final int CONSTANT_POOL_COUNT = 19;
    private static final String CONSTRUCTOR_DESC = "()V";
    private static final String CONSTRUCTOR_NAME = "<init>";
    private static final int INDEX_CLASS_INTERFACE = 9;
    private static final int INDEX_CLASS_OBJECT = 14;
    private static final int INDEX_CLASS_SUPERCLASS = 2;
    private static final int INDEX_CLASS_THIS = 1;
    private static final int INDEX_CLASS_TYPE = 17;
    private static final int INDEX_METHODREF_OBJECT_CONSTRUCTOR = 13;
    private static final int INDEX_NAMEANDTYPE_DEFAULT_CONSTRUCTOR = 16;
    private static final int INDEX_UTF8_CODE_ATTRIBUTE = 5;
    private static final int INDEX_UTF8_CONSTRUCTOR_DESC = 4;
    private static final int INDEX_UTF8_CONSTRUCTOR_NAME = 3;
    private static final int INDEX_UTF8_INSTANTIATOR_CLASS = 7;
    private static final int INDEX_UTF8_INTERFACE = 10;
    private static final int INDEX_UTF8_NEWINSTANCE_DESC = 12;
    private static final int INDEX_UTF8_NEWINSTANCE_NAME = 11;
    private static final int INDEX_UTF8_OBJECT = 15;
    private static final int INDEX_UTF8_SUPERCLASS = 8;
    private static final int INDEX_UTF8_TYPE = 18;
    private final ObjectInstantiator<T> instantiator;
    private static final String MAGIC_ACCESSOR = getMagicClass();
    private static final byte[] CONSTRUCTOR_CODE = {ClassDefinitionUtils.OPS_aload_0, ClassDefinitionUtils.OPS_invokespecial, 0, 13, ClassDefinitionUtils.OPS_return};
    private static final int CONSTRUCTOR_CODE_ATTRIBUTE_LENGTH = CONSTRUCTOR_CODE.length + 12;
    private static final byte[] NEWINSTANCE_CODE = {-69, 0, 17, ClassDefinitionUtils.OPS_dup, ClassDefinitionUtils.OPS_invokespecial, 0, 13, ClassDefinitionUtils.OPS_areturn};
    private static final int NEWINSTANCE_CODE_ATTRIBUTE_LENGTH = NEWINSTANCE_CODE.length + 12;

    public MagicInstantiator(Class<T> cls) {
        this.instantiator = newInstantiatorOf(cls);
    }

    public ObjectInstantiator<T> getInstantiator() {
        return this.instantiator;
    }

    private ObjectInstantiator<T> newInstantiatorOf(Class<T> cls) {
        String str = getClass().getName() + "$$$" + cls.getSimpleName();
        Class existingClass = ClassUtils.getExistingClass(getClass().getClassLoader(), str);
        if (existingClass == null) {
            try {
                existingClass = ClassDefinitionUtils.defineClass(str, writeExtendingClass(cls, str), cls, getClass().getClassLoader());
            } catch (Exception e) {
                throw new ObjenesisException(e);
            }
        }
        return (ObjectInstantiator) ClassUtils.newInstance(existingClass);
    }

    private byte[] writeExtendingClass(Class<?> cls, String str) {
        String classNameToInternalClassName = ClassUtils.classNameToInternalClassName(str);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1000);
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.write(ClassDefinitionUtils.MAGIC);
                dataOutputStream.write(ClassDefinitionUtils.VERSION);
                dataOutputStream.writeShort(19);
                dataOutputStream.writeByte(7);
                dataOutputStream.writeShort(7);
                dataOutputStream.writeByte(7);
                dataOutputStream.writeShort(8);
                dataOutputStream.writeByte(1);
                dataOutputStream.writeUTF(CONSTRUCTOR_NAME);
                dataOutputStream.writeByte(1);
                dataOutputStream.writeUTF(CONSTRUCTOR_DESC);
                dataOutputStream.writeByte(1);
                dataOutputStream.writeUTF(AttributeLayout.ATTRIBUTE_CODE);
                dataOutputStream.writeByte(1);
                dataOutputStream.writeUTF("L" + classNameToInternalClassName + VoiceWakeuperAidl.PARAMS_SEPARATE);
                dataOutputStream.writeByte(1);
                dataOutputStream.writeUTF(classNameToInternalClassName);
                dataOutputStream.writeByte(1);
                dataOutputStream.writeUTF(MAGIC_ACCESSOR);
                dataOutputStream.writeByte(7);
                dataOutputStream.writeShort(10);
                dataOutputStream.writeByte(1);
                dataOutputStream.writeUTF(ObjectInstantiator.class.getName().replace(FilenameUtils.EXTENSION_SEPARATOR, '/'));
                dataOutputStream.writeByte(1);
                dataOutputStream.writeUTF("newInstance");
                dataOutputStream.writeByte(1);
                dataOutputStream.writeUTF("()Ljava/lang/Object;");
                dataOutputStream.writeByte(10);
                dataOutputStream.writeShort(14);
                dataOutputStream.writeShort(16);
                dataOutputStream.writeByte(7);
                dataOutputStream.writeShort(15);
                dataOutputStream.writeByte(1);
                dataOutputStream.writeUTF("java/lang/Object");
                dataOutputStream.writeByte(12);
                dataOutputStream.writeShort(3);
                dataOutputStream.writeShort(4);
                dataOutputStream.writeByte(7);
                dataOutputStream.writeShort(18);
                dataOutputStream.writeByte(1);
                dataOutputStream.writeUTF(ClassUtils.classNameToInternalClassName(cls.getName()));
                dataOutputStream.writeShort(49);
                dataOutputStream.writeShort(1);
                dataOutputStream.writeShort(2);
                dataOutputStream.writeShort(1);
                dataOutputStream.writeShort(9);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(2);
                dataOutputStream.writeShort(1);
                dataOutputStream.writeShort(3);
                dataOutputStream.writeShort(4);
                dataOutputStream.writeShort(1);
                dataOutputStream.writeShort(5);
                dataOutputStream.writeInt(CONSTRUCTOR_CODE_ATTRIBUTE_LENGTH);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(1);
                dataOutputStream.writeInt(CONSTRUCTOR_CODE.length);
                dataOutputStream.write(CONSTRUCTOR_CODE);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(1);
                dataOutputStream.writeShort(11);
                dataOutputStream.writeShort(12);
                dataOutputStream.writeShort(1);
                dataOutputStream.writeShort(5);
                dataOutputStream.writeInt(NEWINSTANCE_CODE_ATTRIBUTE_LENGTH);
                dataOutputStream.writeShort(2);
                dataOutputStream.writeShort(1);
                dataOutputStream.writeInt(NEWINSTANCE_CODE.length);
                dataOutputStream.write(NEWINSTANCE_CODE);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(0);
                dataOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            } finally {
            }
        } catch (IOException e) {
            throw new ObjenesisException(e);
        }
    }

    @Override // org.objenesis.instantiator.ObjectInstantiator
    public T newInstance() {
        return this.instantiator.newInstance();
    }

    private static String getMagicClass() {
        try {
            Class.forName("sun.reflect.MagicAccessorImpl", false, MagicInstantiator.class.getClassLoader());
            return "sun/reflect/MagicAccessorImpl";
        } catch (ClassNotFoundException unused) {
            return "jdk/internal/reflect/MagicAccessorImpl";
        }
    }
}
