package io.grpc.netty.shaded.io.netty.util.internal;

import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.PosixFilePermission;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;
import org.apache.commons.io.FilenameUtils;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class NativeLibraryLoader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean DELETE_NATIVE_LIB_AFTER_LOADING;
    private static final String NATIVE_RESOURCE_HOME = "META-INF/native/";
    private static final boolean TRY_TO_PATCH_SHADED_ID;
    private static final File WORKDIR;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) NativeLibraryLoader.class);
    private static final byte[] UNIQUE_ID_BYTES = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes(CharsetUtil.US_ASCII);

    static {
        String str = SystemPropertyUtil.get("io.grpc.netty.shaded.io.netty.native.workdir");
        if (str != null) {
            File file = new File(str);
            file.mkdirs();
            try {
                file = file.getAbsoluteFile();
            } catch (Exception unused) {
            }
            WORKDIR = file;
            logger.debug("-Dio.netty.native.workdir: " + WORKDIR);
        } else {
            WORKDIR = PlatformDependent.tmpdir();
            logger.debug("-Dio.netty.native.workdir: " + WORKDIR + " (io.netty.tmpdir)");
        }
        DELETE_NATIVE_LIB_AFTER_LOADING = SystemPropertyUtil.getBoolean("io.grpc.netty.shaded.io.netty.native.deleteLibAfterLoading", true);
        logger.debug("-Dio.netty.native.deleteLibAfterLoading: {}", Boolean.valueOf(DELETE_NATIVE_LIB_AFTER_LOADING));
        TRY_TO_PATCH_SHADED_ID = SystemPropertyUtil.getBoolean("io.grpc.netty.shaded.io.netty.native.tryPatchShadedId", true);
        logger.debug("-Dio.netty.native.tryPatchShadedId: {}", Boolean.valueOf(TRY_TO_PATCH_SHADED_ID));
    }

    public static void loadFirstAvailable(ClassLoader classLoader, String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            try {
                load(str, classLoader);
                return;
            } catch (Throwable th) {
                arrayList.add(th);
                logger.debug("Unable to load the library '{}', trying next name...", str, th);
            }
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Failed to load any of the given libraries: " + Arrays.toString(strArr));
        ThrowableUtil.addSuppressedAndClear(illegalArgumentException, arrayList);
        throw illegalArgumentException;
    }

    private static String calculatePackagePrefix() {
        String name = NativeLibraryLoader.class.getName();
        String replace = "io!netty!util!internal!NativeLibraryLoader".replace('!', FilenameUtils.EXTENSION_SEPARATOR);
        if (!name.endsWith(replace)) {
            throw new UnsatisfiedLinkError(String.format("Could not find prefix added to %s to get %s. When shading, only adding a package prefix is supported", replace, name));
        }
        return name.substring(0, name.length() - replace.length());
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x015f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void load(String str, ClassLoader classLoader) {
        URL resource;
        File file;
        FileOutputStream fileOutputStream;
        InputStream inputStream;
        String str2;
        URL resource2;
        String replace = calculatePackagePrefix().replace(FilenameUtils.EXTENSION_SEPARATOR, '_');
        String str3 = replace + str;
        ArrayList arrayList = new ArrayList();
        try {
            loadLibrary(classLoader, str3, false);
        } catch (Throwable th) {
            arrayList.add(th);
            if (logger.isDebugEnabled()) {
                logger.debug("{} cannot be loaded from java.library.path, now trying export to -Dio.netty.native.workdir: {}", str3, WORKDIR, th);
            }
            String mapLibraryName = System.mapLibraryName(str3);
            String str4 = NATIVE_RESOURCE_HOME + mapLibraryName;
            if (classLoader == null) {
                resource = ClassLoader.getSystemResource(str4);
            } else {
                resource = classLoader.getResource(str4);
            }
            InputStream inputStream2 = null;
            inputStream2 = null;
            inputStream2 = null;
            inputStream2 = null;
            File file2 = null;
            if (resource == null) {
                try {
                    if (PlatformDependent.isOsx()) {
                        if (str4.endsWith(".jnilib")) {
                            str2 = "META-INF/native/lib" + str3 + ".dynlib";
                        } else {
                            str2 = "META-INF/native/lib" + str3 + ".jnilib";
                        }
                        if (classLoader == null) {
                            resource2 = ClassLoader.getSystemResource(str2);
                        } else {
                            resource2 = classLoader.getResource(str2);
                        }
                        resource = resource2;
                        if (resource == null) {
                            FileNotFoundException fileNotFoundException = new FileNotFoundException(str2);
                            ThrowableUtil.addSuppressedAndClear(fileNotFoundException, arrayList);
                            throw fileNotFoundException;
                        }
                    } else {
                        FileNotFoundException fileNotFoundException2 = new FileNotFoundException(str4);
                        ThrowableUtil.addSuppressedAndClear(fileNotFoundException2, arrayList);
                        throw fileNotFoundException2;
                    }
                } catch (Exception e) {
                    e = e;
                    file = null;
                    fileOutputStream = null;
                    try {
                        UnsatisfiedLinkError unsatisfiedLinkError = new UnsatisfiedLinkError("could not load a native library: " + str3);
                        unsatisfiedLinkError.initCause(e);
                        ThrowableUtil.addSuppressedAndClear(unsatisfiedLinkError, arrayList);
                        throw unsatisfiedLinkError;
                    } catch (Throwable th2) {
                        th = th2;
                        closeQuietly(inputStream2);
                        closeQuietly(fileOutputStream);
                        if (file != null) {
                            file.deleteOnExit();
                        }
                        throw th;
                    }
                } catch (UnsatisfiedLinkError e2) {
                    e = e2;
                    inputStream = null;
                    fileOutputStream = null;
                    try {
                        if (file2 != null) {
                            try {
                                if (file2.isFile() && file2.canRead() && !NoexecVolumeDetector.canExecuteExecutable(file2)) {
                                    logger.info("{} exists but cannot be executed even when execute permissions set; check volume for \"noexec\" flag; use -D{}=[path] to set native working directory separately.", file2.getPath(), "io.grpc.netty.shaded.io.netty.native.workdir");
                                }
                            } catch (Throwable th3) {
                                arrayList.add(th3);
                                logger.debug("Error checking if {} is on a file store mounted with noexec", file2, th3);
                            }
                        }
                        ThrowableUtil.addSuppressedAndClear(e, arrayList);
                        throw e;
                    } catch (Throwable th4) {
                        th = th4;
                        file = file2;
                        inputStream2 = inputStream;
                        closeQuietly(inputStream2);
                        closeQuietly(fileOutputStream);
                        if (file != null && (!DELETE_NATIVE_LIB_AFTER_LOADING || !file.delete())) {
                            file.deleteOnExit();
                        }
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    file = null;
                    fileOutputStream = null;
                    closeQuietly(inputStream2);
                    closeQuietly(fileOutputStream);
                    if (file != null) {
                    }
                    throw th;
                }
            }
            int lastIndexOf = mapLibraryName.lastIndexOf(46);
            file = File.createTempFile(mapLibraryName.substring(0, lastIndexOf), mapLibraryName.substring(lastIndexOf), WORKDIR);
            try {
                inputStream = resource.openStream();
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (Exception e3) {
                    e = e3;
                    fileOutputStream = null;
                } catch (UnsatisfiedLinkError e4) {
                    e = e4;
                    fileOutputStream = null;
                } catch (Throwable th6) {
                    th = th6;
                    fileOutputStream = null;
                }
                try {
                    if (!shouldShadedLibraryIdBePatched(replace)) {
                        byte[] bArr = new byte[8192];
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            } else {
                                fileOutputStream.write(bArr, 0, read);
                            }
                        }
                    } else {
                        patchShadedLibraryId(inputStream, fileOutputStream, str, str3);
                    }
                    fileOutputStream.flush();
                    closeQuietly(fileOutputStream);
                    loadLibrary(classLoader, file.getPath(), true);
                    closeQuietly(inputStream);
                    closeQuietly(null);
                    if (file != null) {
                        if (DELETE_NATIVE_LIB_AFTER_LOADING && file.delete()) {
                            return;
                        }
                        file.deleteOnExit();
                    }
                } catch (Exception e5) {
                    e = e5;
                    inputStream2 = inputStream;
                    UnsatisfiedLinkError unsatisfiedLinkError2 = new UnsatisfiedLinkError("could not load a native library: " + str3);
                    unsatisfiedLinkError2.initCause(e);
                    ThrowableUtil.addSuppressedAndClear(unsatisfiedLinkError2, arrayList);
                    throw unsatisfiedLinkError2;
                } catch (UnsatisfiedLinkError e6) {
                    e = e6;
                    file2 = file;
                    if (file2 != null) {
                    }
                    ThrowableUtil.addSuppressedAndClear(e, arrayList);
                    throw e;
                } catch (Throwable th7) {
                    th = th7;
                    inputStream2 = inputStream;
                    closeQuietly(inputStream2);
                    closeQuietly(fileOutputStream);
                    if (file != null) {
                    }
                    throw th;
                }
            } catch (Exception e7) {
                e = e7;
                fileOutputStream = null;
            } catch (UnsatisfiedLinkError e8) {
                e = e8;
                inputStream = null;
                fileOutputStream = null;
            } catch (Throwable th8) {
                th = th8;
                fileOutputStream = null;
                closeQuietly(inputStream2);
                closeQuietly(fileOutputStream);
                if (file != null) {
                }
                throw th;
            }
        }
    }

    static boolean patchShadedLibraryId(InputStream inputStream, OutputStream outputStream, String str, String str2) throws IOException {
        boolean z;
        byte[] bArr = new byte[8192];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(inputStream.available());
        while (true) {
            int read = inputStream.read(bArr);
            if (read <= 0) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
        byteArrayOutputStream.flush();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        if (patchShadedLibraryId(byteArray, str, str2)) {
            z = true;
        } else {
            String str3 = "_" + PlatformDependent.normalizedOs() + "_" + PlatformDependent.normalizedArch();
            z = str.endsWith(str3) ? patchShadedLibraryId(byteArray, str.substring(0, str.length() - str3.length()), str2) : false;
        }
        outputStream.write(byteArray, 0, byteArray.length);
        return z;
    }

    private static boolean shouldShadedLibraryIdBePatched(String str) {
        return TRY_TO_PATCH_SHADED_ID && PlatformDependent.isOsx() && !str.isEmpty();
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0028, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean patchShadedLibraryId(byte[] bArr, String str, String str2) {
        byte[] bytes = str.getBytes(CharsetUtil.UTF_8);
        int i = 0;
        loop0: while (i < bArr.length && bArr.length - i >= bytes.length) {
            int i2 = 0;
            int i3 = i;
            while (i2 < bytes.length) {
                int i4 = i3 + 1;
                int i5 = i2 + 1;
                if (bArr[i3] != bytes[i2]) {
                    break;
                }
                if (i5 == bytes.length) {
                    break loop0;
                }
                i3 = i4;
                i2 = i5;
            }
            i++;
        }
        i = -1;
        if (i == -1) {
            logger.debug("Was not able to find the ID of the shaded native library {}, can't adjust it.", str2);
            return false;
        }
        for (int i6 = 0; i6 < bytes.length; i6++) {
            bArr[i + i6] = UNIQUE_ID_BYTES[PlatformDependent.threadLocalRandom().nextInt(UNIQUE_ID_BYTES.length)];
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Found the ID of the shaded native library {}. Replacing ID part {} with {}", str2, str, new String(bArr, i, bytes.length, CharsetUtil.UTF_8));
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.lang.ClassLoader] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r3v5, types: [io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger] */
    private static void loadLibrary(ClassLoader classLoader, String str, boolean z) {
        try {
            try {
                loadLibraryByHelper(tryToLoadClass(classLoader, NativeLibraryUtil.class), str, z);
                classLoader = logger;
                classLoader.debug("Successfully loaded the library {}", str);
            } catch (Exception e) {
                logger.debug("Unable to load the library '{}', trying other loading mechanism.", str, e);
                NativeLibraryUtil.loadLibrary(str, z);
                logger.debug("Successfully loaded the library {}", str);
            } catch (UnsatisfiedLinkError e2) {
                logger.debug("Unable to load the library '{}', trying other loading mechanism.", str, e2);
                NativeLibraryUtil.loadLibrary(str, z);
                logger.debug("Successfully loaded the library {}", str);
            }
        } catch (UnsatisfiedLinkError e3) {
            ThrowableUtil.addSuppressed(e3, (Throwable) classLoader);
            throw e3;
        }
    }

    private static void loadLibraryByHelper(final Class<?> cls, final String str, final boolean z) throws UnsatisfiedLinkError {
        Object doPrivileged = AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: io.grpc.netty.shaded.io.netty.util.internal.NativeLibraryLoader.1
            @Override // java.security.PrivilegedAction
            public Object run() {
                try {
                    Method method = cls.getMethod("loadLibrary", String.class, Boolean.TYPE);
                    method.setAccessible(true);
                    return method.invoke(null, str, Boolean.valueOf(z));
                } catch (Exception e) {
                    return e;
                }
            }
        });
        if (doPrivileged instanceof Throwable) {
            Throwable th = (Throwable) doPrivileged;
            Throwable cause = th.getCause();
            if (cause instanceof UnsatisfiedLinkError) {
                throw ((UnsatisfiedLinkError) cause);
            }
            UnsatisfiedLinkError unsatisfiedLinkError = new UnsatisfiedLinkError(th.getMessage());
            unsatisfiedLinkError.initCause(th);
            throw unsatisfiedLinkError;
        }
    }

    private static Class<?> tryToLoadClass(final ClassLoader classLoader, final Class<?> cls) throws ClassNotFoundException {
        try {
            return Class.forName(cls.getName(), false, classLoader);
        } catch (ClassNotFoundException e) {
            if (classLoader == null) {
                throw e;
            }
            try {
                final byte[] classToByteArray = classToByteArray(cls);
                return (Class) AccessController.doPrivileged(new PrivilegedAction<Class<?>>() { // from class: io.grpc.netty.shaded.io.netty.util.internal.NativeLibraryLoader.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.security.PrivilegedAction
                    public Class<?> run() {
                        try {
                            Method declaredMethod = ClassLoader.class.getDeclaredMethod("defineClass", String.class, byte[].class, Integer.TYPE, Integer.TYPE);
                            declaredMethod.setAccessible(true);
                            return (Class) declaredMethod.invoke(classLoader, cls.getName(), classToByteArray, 0, Integer.valueOf(classToByteArray.length));
                        } catch (Exception e2) {
                            throw new IllegalStateException("Define class failed!", e2);
                        }
                    }
                });
            } catch (ClassNotFoundException e2) {
                ThrowableUtil.addSuppressed(e2, e);
                throw e2;
            } catch (Error e3) {
                ThrowableUtil.addSuppressed(e3, e);
                throw e3;
            } catch (RuntimeException e4) {
                ThrowableUtil.addSuppressed(e4, e);
                throw e4;
            }
        }
    }

    private static byte[] classToByteArray(Class<?> cls) throws ClassNotFoundException {
        String name = cls.getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf > 0) {
            name = name.substring(lastIndexOf + 1);
        }
        URL resource = cls.getResource(name + ".class");
        if (resource == null) {
            throw new ClassNotFoundException(cls.getName());
        }
        byte[] bArr = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4096);
        InputStream inputStream = null;
        try {
            try {
                inputStream = resource.openStream();
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        return byteArrayOutputStream.toByteArray();
                    }
                }
            } catch (IOException e) {
                throw new ClassNotFoundException(cls.getName(), e);
            }
        } finally {
            closeQuietly(inputStream);
            closeQuietly(byteArrayOutputStream);
        }
    }

    private static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    private NativeLibraryLoader() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class NoexecVolumeDetector {
        /* JADX INFO: Access modifiers changed from: private */
        public static boolean canExecuteExecutable(File file) throws IOException {
            if (PlatformDependent.javaVersion() < 7 || file.canExecute()) {
                return true;
            }
            Set<PosixFilePermission> posixFilePermissions = Files.getPosixFilePermissions(file.toPath(), new LinkOption[0]);
            EnumSet of = EnumSet.of(PosixFilePermission.OWNER_EXECUTE, PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.OTHERS_EXECUTE);
            if (posixFilePermissions.containsAll(of)) {
                return false;
            }
            EnumSet copyOf = EnumSet.copyOf((Collection) posixFilePermissions);
            copyOf.addAll(of);
            Files.setPosixFilePermissions(file.toPath(), copyOf);
            return file.canExecute();
        }

        private NoexecVolumeDetector() {
        }
    }
}
