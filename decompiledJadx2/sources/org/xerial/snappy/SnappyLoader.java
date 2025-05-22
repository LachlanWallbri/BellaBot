package org.xerial.snappy;

import androidx.core.os.EnvironmentCompat;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.UUID;
import org.xerial.snappy.pure.PureJavaSnappy;

/* loaded from: classes9.dex */
public class SnappyLoader {
    public static final String KEY_SNAPPY_DISABLE_BUNDLED_LIBS = "org.xerial.snappy.disable.bundled.libs";
    public static final String KEY_SNAPPY_LIB_NAME = "org.xerial.snappy.lib.name";
    public static final String KEY_SNAPPY_LIB_PATH = "org.xerial.snappy.lib.path";
    public static final String KEY_SNAPPY_PUREJAVA = "org.xerial.snappy.purejava";
    public static final String KEY_SNAPPY_TEMPDIR = "org.xerial.snappy.tempdir";
    public static final String KEY_SNAPPY_USE_SYSTEMLIB = "org.xerial.snappy.use.systemlib";
    public static final String SNAPPY_SYSTEM_PROPERTIES_FILE = "org-xerial-snappy.properties";
    private static volatile BitShuffleNative bitshuffleApi = null;
    private static boolean isLoaded = false;
    private static File nativeLibFile;
    private static volatile SnappyApi snappyApi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void cleanUpExtractedNativeLib() {
        File file = nativeLibFile;
        if (file == null || !file.exists()) {
            return;
        }
        nativeLibFile.delete();
        snappyApi = null;
        bitshuffleApi = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void setSnappyApi(SnappyApi snappyApi2) {
        synchronized (SnappyLoader.class) {
            snappyApi = snappyApi2;
        }
    }

    private static void loadSnappySystemProperties() {
        try {
            InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(SNAPPY_SYSTEM_PROPERTIES_FILE);
            if (resourceAsStream == null) {
                return;
            }
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            resourceAsStream.close();
            Enumeration<?> propertyNames = properties.propertyNames();
            while (propertyNames.hasMoreElements()) {
                String str = (String) propertyNames.nextElement();
                if (str.startsWith("org.xerial.snappy.") && System.getProperty(str) == null) {
                    System.setProperty(str, properties.getProperty(str));
                }
            }
        } catch (Throwable th) {
            System.err.println("Could not load 'org-xerial-snappy.properties' from classpath: " + th.toString());
        }
    }

    static {
        loadSnappySystemProperties();
    }

    static synchronized boolean isPureJava() {
        boolean z;
        synchronized (SnappyLoader.class) {
            if (snappyApi != null) {
                z = PureJavaSnappy.class.isAssignableFrom(snappyApi.getClass());
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized SnappyApi loadSnappyApi() {
        synchronized (SnappyLoader.class) {
            if (snappyApi != null) {
                return snappyApi;
            }
            try {
                if (Boolean.parseBoolean(System.getProperty(KEY_SNAPPY_PUREJAVA, "false"))) {
                    setSnappyApi(new PureJavaSnappy());
                } else {
                    loadNativeLibrary();
                    setSnappyApi(new SnappyNative());
                }
            } catch (Throwable unused) {
                setSnappyApi(new PureJavaSnappy());
            }
            return snappyApi;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized BitShuffleNative loadBitShuffleApi() {
        synchronized (SnappyLoader.class) {
            if (bitshuffleApi != null) {
                return bitshuffleApi;
            }
            loadNativeLibrary();
            bitshuffleApi = new BitShuffleNative();
            return bitshuffleApi;
        }
    }

    private static synchronized void loadNativeLibrary() {
        synchronized (SnappyLoader.class) {
            if (!isLoaded) {
                try {
                    nativeLibFile = findNativeLibrary();
                    if (nativeLibFile != null) {
                        System.load(nativeLibFile.getAbsolutePath());
                    } else {
                        System.loadLibrary(SnappyBundleActivator.LIBRARY_NAME);
                    }
                    isLoaded = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new SnappyError(SnappyErrorCode.FAILED_TO_LOAD_NATIVE_LIBRARY, e.getMessage());
                }
            }
        }
    }

    private static boolean contentsEquals(InputStream inputStream, InputStream inputStream2) throws IOException {
        if (!(inputStream instanceof BufferedInputStream)) {
            inputStream = new BufferedInputStream(inputStream);
        }
        if (!(inputStream2 instanceof BufferedInputStream)) {
            inputStream2 = new BufferedInputStream(inputStream2);
        }
        for (int read = inputStream.read(); read != -1; read = inputStream.read()) {
            if (read != inputStream2.read()) {
                return false;
            }
        }
        return inputStream2.read() == -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00bf A[Catch: IOException -> 0x00c6, TRY_ENTER, TryCatch #2 {IOException -> 0x00c6, blocks: (B:15:0x0057, B:16:0x005a, B:18:0x0063, B:20:0x0069, B:30:0x007e, B:31:0x0081, B:39:0x00a6, B:41:0x00ab, B:42:0x00ae, B:62:0x00bf, B:63:0x00c2, B:64:0x00c5), top: B:2:0x003a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static File extractLibraryFile(String str, String str2, String str3) {
        FileOutputStream fileOutputStream;
        InputStream inputStream;
        FileInputStream fileInputStream;
        String str4 = str + "/" + str2;
        String format = String.format("snappy-%s-%s-%s", getVersion(), UUID.randomUUID().toString(), str2);
        File file = new File(str3, format);
        InputStream inputStream2 = null;
        try {
            try {
                try {
                    InputStream resourceAsStream = SnappyLoader.class.getResourceAsStream(str4);
                    try {
                        fileOutputStream = new FileOutputStream(file);
                        try {
                            byte[] bArr = new byte[8192];
                            while (true) {
                                int read = resourceAsStream.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            }
                            fileOutputStream.close();
                            if (resourceAsStream != null) {
                                resourceAsStream.close();
                            }
                            file.deleteOnExit();
                            if (file.setReadable(true) && file.setWritable(true, true)) {
                                file.setExecutable(true);
                            }
                            try {
                                inputStream = SnappyLoader.class.getResourceAsStream(str4);
                                try {
                                    fileInputStream = new FileInputStream(file);
                                } catch (Throwable th) {
                                    th = th;
                                    fileInputStream = null;
                                }
                                try {
                                    if (!contentsEquals(inputStream, fileInputStream)) {
                                        throw new SnappyError(SnappyErrorCode.FAILED_TO_LOAD_NATIVE_LIBRARY, String.format("Failed to write a native library file at %s", file));
                                    }
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    fileInputStream.close();
                                    return new File(str3, format);
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (inputStream != null) {
                                        inputStream.close();
                                    }
                                    if (fileInputStream != null) {
                                        fileInputStream.close();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                inputStream = null;
                                fileInputStream = null;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        fileOutputStream = null;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    if (0 != 0) {
                    }
                    file.deleteOnExit();
                    throw th;
                }
            } catch (Throwable th7) {
                th = th7;
                if (0 != 0) {
                    inputStream2.close();
                }
                file.deleteOnExit();
                throw th;
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
            return null;
        }
    }

    static File findNativeLibrary() {
        boolean parseBoolean = Boolean.parseBoolean(System.getProperty(KEY_SNAPPY_USE_SYSTEMLIB, "false"));
        boolean parseBoolean2 = Boolean.parseBoolean(System.getProperty(KEY_SNAPPY_DISABLE_BUNDLED_LIBS, "false"));
        if (parseBoolean || parseBoolean2) {
            return null;
        }
        String property = System.getProperty(KEY_SNAPPY_LIB_PATH);
        String property2 = System.getProperty(KEY_SNAPPY_LIB_NAME);
        if (property2 == null) {
            property2 = System.mapLibraryName(SnappyBundleActivator.LIBRARY_NAME);
        }
        if (property != null) {
            File file = new File(property, property2);
            if (file.exists()) {
                return file;
            }
        }
        String str = "/org/xerial/snappy/native/" + OSInfo.getNativeLibFolderPathForCurrentOS();
        boolean hasResource = hasResource(str + "/" + property2);
        if (!hasResource && OSInfo.getOSName().equals("Mac")) {
            if (hasResource(str + "/libsnappyjava.dylib")) {
                hasResource = true;
                property2 = "libsnappyjava.dylib";
            }
        }
        if (!hasResource) {
            throw new SnappyError(SnappyErrorCode.FAILED_TO_LOAD_NATIVE_LIBRARY, String.format("no native library is found for os.name=%s and os.arch=%s", OSInfo.getOSName(), OSInfo.getArchName()));
        }
        File file2 = new File(System.getProperty(KEY_SNAPPY_TEMPDIR, System.getProperty("java.io.tmpdir")));
        if (!file2.exists()) {
            file2.mkdirs();
        }
        return extractLibraryFile(str, property2, file2.getAbsolutePath());
    }

    private static boolean hasResource(String str) {
        return SnappyLoader.class.getResource(str) != null;
    }

    public static String getVersion() {
        URL resource = SnappyLoader.class.getResource("/META-INF/maven/org.xerial.snappy/snappy-java/pom.properties");
        if (resource == null) {
            resource = SnappyLoader.class.getResource("/org/xerial/snappy/VERSION");
        }
        String str = EnvironmentCompat.MEDIA_UNKNOWN;
        if (resource == null) {
            return EnvironmentCompat.MEDIA_UNKNOWN;
        }
        try {
            Properties properties = new Properties();
            properties.load(resource.openStream());
            String property = properties.getProperty("version", EnvironmentCompat.MEDIA_UNKNOWN);
            try {
                return (property.equals(EnvironmentCompat.MEDIA_UNKNOWN) ? properties.getProperty("SNAPPY_VERSION", property) : property).trim().replaceAll("[^0-9M\\.]", "");
            } catch (IOException e) {
                e = e;
                str = property;
                System.err.println(e);
                return str;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }
}
