package org.apache.commons.compress.harmony.pack200;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.apache.commons.compress.harmony.pack200.Archive;

/* loaded from: classes9.dex */
public class PackingUtils {
    private static PackingLogger packingLogger = new PackingLogger("org.harmony.apache.pack200", null);

    static {
        LogManager.getLogManager().addLogger(packingLogger);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class PackingLogger extends Logger {
        private boolean verbose;

        protected PackingLogger(String str, String str2) {
            super(str, str2);
            this.verbose = false;
        }

        @Override // java.util.logging.Logger
        public void log(LogRecord logRecord) {
            if (this.verbose) {
                super.log(logRecord);
            }
        }

        public void setVerbose(boolean z) {
            this.verbose = z;
        }
    }

    public static void config(PackingOptions packingOptions) throws IOException {
        String logFile = packingOptions.getLogFile();
        if (logFile != null) {
            FileHandler fileHandler = new FileHandler(logFile, false);
            fileHandler.setFormatter(new SimpleFormatter());
            packingLogger.addHandler(fileHandler);
            packingLogger.setUseParentHandlers(false);
        }
        packingLogger.setVerbose(packingOptions.isVerbose());
    }

    public static void log(String str) {
        packingLogger.log(Level.INFO, str);
    }

    public static void copyThroughJar(JarInputStream jarInputStream, OutputStream outputStream) throws IOException {
        JarOutputStream jarOutputStream = new JarOutputStream(outputStream, jarInputStream.getManifest());
        jarOutputStream.setComment("PACK200");
        log("Packed META-INF/MANIFEST.MF");
        byte[] bArr = new byte[16384];
        while (true) {
            JarEntry nextJarEntry = jarInputStream.getNextJarEntry();
            if (nextJarEntry != null) {
                jarOutputStream.putNextEntry(nextJarEntry);
                while (true) {
                    int read = jarInputStream.read(bArr);
                    if (read != -1) {
                        jarOutputStream.write(bArr, 0, read);
                    }
                }
                log("Packed " + nextJarEntry.getName());
            } else {
                jarInputStream.close();
                jarOutputStream.close();
                return;
            }
        }
    }

    public static void copyThroughJar(JarFile jarFile, OutputStream outputStream) throws IOException {
        JarOutputStream jarOutputStream = new JarOutputStream(outputStream);
        jarOutputStream.setComment("PACK200");
        byte[] bArr = new byte[16384];
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry nextElement = entries.nextElement();
            jarOutputStream.putNextEntry(nextElement);
            InputStream inputStream = jarFile.getInputStream(nextElement);
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    jarOutputStream.write(bArr, 0, read);
                }
            }
            jarOutputStream.closeEntry();
            log("Packed " + nextElement.getName());
        }
        jarFile.close();
        jarOutputStream.close();
    }

    public static List getPackingFileListFromJar(JarInputStream jarInputStream, boolean z) throws IOException {
        ArrayList arrayList = new ArrayList();
        Manifest manifest = jarInputStream.getManifest();
        if (manifest != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            manifest.write(byteArrayOutputStream);
            arrayList.add(new Archive.PackingFile("META-INF/MANIFEST.MF", byteArrayOutputStream.toByteArray(), 0L));
        }
        while (true) {
            JarEntry nextJarEntry = jarInputStream.getNextJarEntry();
            if (nextJarEntry == null) {
                break;
            }
            arrayList.add(new Archive.PackingFile(readJarEntry(nextJarEntry, new BufferedInputStream(jarInputStream)), nextJarEntry));
        }
        if (!z) {
            reorderPackingFiles(arrayList);
        }
        return arrayList;
    }

    public static List getPackingFileListFromJar(JarFile jarFile, boolean z) throws IOException {
        ArrayList arrayList = new ArrayList();
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            JarEntry nextElement = entries.nextElement();
            arrayList.add(new Archive.PackingFile(readJarEntry(nextElement, new BufferedInputStream(jarFile.getInputStream(nextElement))), nextElement));
        }
        if (!z) {
            reorderPackingFiles(arrayList);
        }
        return arrayList;
    }

    private static byte[] readJarEntry(JarEntry jarEntry, InputStream inputStream) throws IOException {
        long size = jarEntry.getSize();
        if (size > 2147483647L) {
            throw new RuntimeException("Large Class!");
        }
        if (size < 0) {
            size = 0;
        }
        byte[] bArr = new byte[(int) size];
        if (inputStream.read(bArr) == size) {
            return bArr;
        }
        throw new RuntimeException("Error reading from stream");
    }

    private static void reorderPackingFiles(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            if (((Archive.PackingFile) it.next()).isDirectory()) {
                it.remove();
            }
        }
        Collections.sort(list, new Comparator() { // from class: org.apache.commons.compress.harmony.pack200.-$$Lambda$PackingUtils$n6X08nYBFV6Zv0gTe_yzQ61FQl4
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return PackingUtils.lambda$reorderPackingFiles$0(obj, obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$reorderPackingFiles$0(Object obj, Object obj2) {
        if ((obj instanceof Archive.PackingFile) && (obj2 instanceof Archive.PackingFile)) {
            String name = ((Archive.PackingFile) obj).getName();
            String name2 = ((Archive.PackingFile) obj2).getName();
            if (name.equals(name2)) {
                return 0;
            }
            if ("META-INF/MANIFEST.MF".equals(name)) {
                return -1;
            }
            if ("META-INF/MANIFEST.MF".equals(name2)) {
                return 1;
            }
            return name.compareTo(name2);
        }
        throw new IllegalArgumentException();
    }
}
