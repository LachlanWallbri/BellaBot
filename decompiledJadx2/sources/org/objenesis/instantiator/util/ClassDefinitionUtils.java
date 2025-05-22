package org.objenesis.instantiator.util;

import com.felhr.usbserial.FTDISerialDevice;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public final class ClassDefinitionUtils {
    public static final int ACC_ABSTRACT = 1024;
    public static final int ACC_ANNOTATION = 8192;
    public static final int ACC_ENUM = 16384;
    public static final int ACC_FINAL = 16;
    public static final int ACC_INTERFACE = 512;
    public static final int ACC_PUBLIC = 1;
    public static final int ACC_SUPER = 32;
    public static final int ACC_SYNTHETIC = 4096;
    public static final int CONSTANT_Class = 7;
    public static final int CONSTANT_Double = 6;
    public static final int CONSTANT_Fieldref = 9;
    public static final int CONSTANT_Float = 4;
    public static final int CONSTANT_Integer = 3;
    public static final int CONSTANT_InterfaceMethodref = 11;
    public static final int CONSTANT_InvokeDynamic = 18;
    public static final int CONSTANT_Long = 5;
    public static final int CONSTANT_MethodHandle = 15;
    public static final int CONSTANT_MethodType = 16;
    public static final int CONSTANT_Methodref = 10;
    public static final int CONSTANT_NameAndType = 12;
    public static final int CONSTANT_String = 8;
    public static final int CONSTANT_Utf8 = 1;
    public static final byte OPS_aload_0 = 42;
    public static final byte OPS_areturn = -80;
    public static final byte OPS_dup = 89;
    public static final byte OPS_invokespecial = -73;
    public static final byte OPS_new = -69;
    public static final byte OPS_return = -79;
    private static final ProtectionDomain PROTECTION_DOMAIN;
    public static final byte[] MAGIC = {-54, -2, -70, -66};
    public static final byte[] VERSION = {0, 0, 0, TarConstants.LF_LINK};

    static {
        final Class<ClassDefinitionUtils> cls = ClassDefinitionUtils.class;
        ClassDefinitionUtils.class.getClass();
        PROTECTION_DOMAIN = (ProtectionDomain) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.objenesis.instantiator.util.-$$Lambda$ClassDefinitionUtils$fruZ3Sk_5Bues_nVl5d-IEdvIMc
            @Override // java.security.PrivilegedAction
            public final Object run() {
                ProtectionDomain protectionDomain;
                protectionDomain = cls.getProtectionDomain();
                return protectionDomain;
            }
        });
    }

    private ClassDefinitionUtils() {
    }

    public static <T> Class<T> defineClass(String str, byte[] bArr, Class<?> cls, ClassLoader classLoader) throws Exception {
        Class<T> cls2 = (Class<T>) DefineClassHelper.defineClass(str, bArr, 0, bArr.length, cls, classLoader, PROTECTION_DOMAIN);
        Class.forName(str, true, classLoader);
        return cls2;
    }

    public static byte[] readClass(String str) throws IOException {
        String classNameToResource = ClassUtils.classNameToResource(str);
        byte[] bArr = new byte[FTDISerialDevice.FTDI_BAUDRATE_1200];
        InputStream resourceAsStream = ClassDefinitionUtils.class.getClassLoader().getResourceAsStream(classNameToResource);
        try {
            int read = resourceAsStream.read(bArr);
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
            if (read >= 2500) {
                throw new IllegalArgumentException("The class is longer that 2500 bytes which is currently unsupported");
            }
            byte[] bArr2 = new byte[read];
            System.arraycopy(bArr, 0, bArr2, 0, read);
            return bArr2;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (resourceAsStream != null) {
                    try {
                        resourceAsStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public static void writeClass(String str, byte[] bArr) throws IOException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
        try {
            bufferedOutputStream.write(bArr);
            bufferedOutputStream.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    bufferedOutputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }
}
