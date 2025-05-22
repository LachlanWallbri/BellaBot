package org.objenesis.instantiator.basic;

import com.iflytek.speech.VoiceWakeuperAidl;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.commons.compress.harmony.unpack200.AttributeLayout;
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
public class ProxyingInstantiator<T> implements ObjectInstantiator<T> {
    private static final byte[] CODE = {ClassDefinitionUtils.OPS_aload_0, ClassDefinitionUtils.OPS_return};
    private static final int CODE_ATTRIBUTE_LENGTH = CODE.length + 12;
    private static final int CONSTANT_POOL_COUNT = 9;
    private static final String CONSTRUCTOR_DESC = "()V";
    private static final String CONSTRUCTOR_NAME = "<init>";
    private static final int INDEX_CLASS_SUPERCLASS = 2;
    private static final int INDEX_CLASS_THIS = 1;
    private static final int INDEX_UTF8_CLASS = 7;
    private static final int INDEX_UTF8_CODE_ATTRIBUTE = 5;
    private static final int INDEX_UTF8_CONSTRUCTOR_DESC = 4;
    private static final int INDEX_UTF8_CONSTRUCTOR_NAME = 3;
    private static final int INDEX_UTF8_SUPERCLASS = 8;
    private static final String SUFFIX = "$$$Objenesis";
    private final Class<? extends T> newType;

    public ProxyingInstantiator(Class<T> cls) {
        try {
            this.newType = ClassDefinitionUtils.defineClass(cls.getName() + SUFFIX, writeExtendingClass(cls), cls, cls.getClassLoader());
        } catch (Exception e) {
            throw new ObjenesisException(e);
        }
    }

    @Override // org.objenesis.instantiator.ObjectInstantiator
    public T newInstance() {
        return (T) ClassUtils.newInstance(this.newType);
    }

    private static byte[] writeExtendingClass(Class<?> cls) {
        String classNameToInternalClassName = ClassUtils.classNameToInternalClassName(cls.getName());
        String str = classNameToInternalClassName + SUFFIX;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1000);
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.write(ClassDefinitionUtils.MAGIC);
                dataOutputStream.write(ClassDefinitionUtils.VERSION);
                dataOutputStream.writeShort(9);
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
                dataOutputStream.writeUTF("L" + str + VoiceWakeuperAidl.PARAMS_SEPARATE);
                dataOutputStream.writeByte(1);
                dataOutputStream.writeUTF(str);
                dataOutputStream.writeByte(1);
                dataOutputStream.writeUTF(classNameToInternalClassName);
                dataOutputStream.writeShort(33);
                dataOutputStream.writeShort(1);
                dataOutputStream.writeShort(2);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(1);
                dataOutputStream.writeShort(1);
                dataOutputStream.writeShort(3);
                dataOutputStream.writeShort(4);
                dataOutputStream.writeShort(1);
                dataOutputStream.writeShort(5);
                dataOutputStream.writeInt(CODE_ATTRIBUTE_LENGTH);
                dataOutputStream.writeShort(1);
                dataOutputStream.writeShort(1);
                dataOutputStream.writeInt(CODE.length);
                dataOutputStream.write(CODE);
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
}
