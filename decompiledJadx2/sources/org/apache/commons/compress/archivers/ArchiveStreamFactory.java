package org.apache.commons.compress.archivers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.compress.archivers.ar.ArArchiveInputStream;
import org.apache.commons.compress.archivers.ar.ArArchiveOutputStream;
import org.apache.commons.compress.archivers.arj.ArjArchiveInputStream;
import org.apache.commons.compress.archivers.cpio.CpioArchiveInputStream;
import org.apache.commons.compress.archivers.cpio.CpioArchiveOutputStream;
import org.apache.commons.compress.archivers.dump.DumpArchiveInputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveInputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveOutputStream;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.compress.utils.ServiceLoaderIterator;
import org.apache.commons.compress.utils.Sets;

/* loaded from: classes7.dex */
public class ArchiveStreamFactory implements ArchiveStreamProvider {

    /* renamed from: AR */
    public static final String f8911AR = "ar";
    public static final String ARJ = "arj";
    public static final String CPIO = "cpio";
    public static final ArchiveStreamFactory DEFAULT = new ArchiveStreamFactory();
    public static final String DUMP = "dump";
    private static final int DUMP_SIGNATURE_SIZE = 32;
    public static final String JAR = "jar";
    public static final String SEVEN_Z = "7z";
    private static final int SIGNATURE_SIZE = 12;
    public static final String TAR = "tar";
    private static final int TAR_HEADER_SIZE = 512;
    public static final String ZIP = "zip";
    private SortedMap<String, ArchiveStreamProvider> archiveInputStreamProviders;
    private SortedMap<String, ArchiveStreamProvider> archiveOutputStreamProviders;
    private final String encoding;
    private volatile String entryEncoding;

    private static ArrayList<ArchiveStreamProvider> findArchiveStreamProviders() {
        return Lists.newArrayList(serviceLoaderIterator());
    }

    static void putAll(Set<String> set, ArchiveStreamProvider archiveStreamProvider, TreeMap<String, ArchiveStreamProvider> treeMap) {
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            treeMap.put(toKey(it.next()), archiveStreamProvider);
        }
    }

    private static Iterator<ArchiveStreamProvider> serviceLoaderIterator() {
        return new ServiceLoaderIterator(ArchiveStreamProvider.class);
    }

    private static String toKey(String str) {
        return str.toUpperCase(Locale.ROOT);
    }

    public static SortedMap<String, ArchiveStreamProvider> findAvailableArchiveInputStreamProviders() {
        return (SortedMap) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.commons.compress.archivers.-$$Lambda$ArchiveStreamFactory$uTVdFLMm8Z0dA3GR7_eDzEeDlEc
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return ArchiveStreamFactory.lambda$findAvailableArchiveInputStreamProviders$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SortedMap lambda$findAvailableArchiveInputStreamProviders$0() {
        TreeMap treeMap = new TreeMap();
        putAll(DEFAULT.getInputStreamArchiveNames(), DEFAULT, treeMap);
        Iterator<ArchiveStreamProvider> it = findArchiveStreamProviders().iterator();
        while (it.hasNext()) {
            ArchiveStreamProvider next = it.next();
            putAll(next.getInputStreamArchiveNames(), next, treeMap);
        }
        return treeMap;
    }

    public static SortedMap<String, ArchiveStreamProvider> findAvailableArchiveOutputStreamProviders() {
        return (SortedMap) AccessController.doPrivileged(new PrivilegedAction() { // from class: org.apache.commons.compress.archivers.-$$Lambda$ArchiveStreamFactory$c2NR6dOTyyeuNM9d3xQ5MJhORJQ
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return ArchiveStreamFactory.lambda$findAvailableArchiveOutputStreamProviders$1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SortedMap lambda$findAvailableArchiveOutputStreamProviders$1() {
        TreeMap treeMap = new TreeMap();
        putAll(DEFAULT.getOutputStreamArchiveNames(), DEFAULT, treeMap);
        Iterator<ArchiveStreamProvider> it = findArchiveStreamProviders().iterator();
        while (it.hasNext()) {
            ArchiveStreamProvider next = it.next();
            putAll(next.getOutputStreamArchiveNames(), next, treeMap);
        }
        return treeMap;
    }

    public ArchiveStreamFactory() {
        this(null);
    }

    public ArchiveStreamFactory(String str) {
        this.encoding = str;
        this.entryEncoding = str;
    }

    public String getEntryEncoding() {
        return this.entryEncoding;
    }

    @Deprecated
    public void setEntryEncoding(String str) {
        if (this.encoding != null) {
            throw new IllegalStateException("Cannot overide encoding set by the constructor");
        }
        this.entryEncoding = str;
    }

    public ArchiveInputStream createArchiveInputStream(String str, InputStream inputStream) throws ArchiveException {
        return createArchiveInputStream(str, inputStream, this.entryEncoding);
    }

    @Override // org.apache.commons.compress.archivers.ArchiveStreamProvider
    public ArchiveInputStream createArchiveInputStream(String str, InputStream inputStream, String str2) throws ArchiveException {
        if (str == null) {
            throw new IllegalArgumentException("Archivername must not be null.");
        }
        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream must not be null.");
        }
        if (f8911AR.equalsIgnoreCase(str)) {
            return new ArArchiveInputStream(inputStream);
        }
        if (ARJ.equalsIgnoreCase(str)) {
            if (str2 != null) {
                return new ArjArchiveInputStream(inputStream, str2);
            }
            return new ArjArchiveInputStream(inputStream);
        }
        if (ZIP.equalsIgnoreCase(str)) {
            if (str2 != null) {
                return new ZipArchiveInputStream(inputStream, str2);
            }
            return new ZipArchiveInputStream(inputStream);
        }
        if (TAR.equalsIgnoreCase(str)) {
            if (str2 != null) {
                return new TarArchiveInputStream(inputStream, str2);
            }
            return new TarArchiveInputStream(inputStream);
        }
        if (JAR.equalsIgnoreCase(str)) {
            if (str2 != null) {
                return new JarArchiveInputStream(inputStream, str2);
            }
            return new JarArchiveInputStream(inputStream);
        }
        if (CPIO.equalsIgnoreCase(str)) {
            if (str2 != null) {
                return new CpioArchiveInputStream(inputStream, str2);
            }
            return new CpioArchiveInputStream(inputStream);
        }
        if (DUMP.equalsIgnoreCase(str)) {
            if (str2 != null) {
                return new DumpArchiveInputStream(inputStream, str2);
            }
            return new DumpArchiveInputStream(inputStream);
        }
        if (SEVEN_Z.equalsIgnoreCase(str)) {
            throw new StreamingNotSupportedException(SEVEN_Z);
        }
        ArchiveStreamProvider archiveStreamProvider = getArchiveInputStreamProviders().get(toKey(str));
        if (archiveStreamProvider != null) {
            return archiveStreamProvider.createArchiveInputStream(str, inputStream, str2);
        }
        throw new ArchiveException("Archiver: " + str + " not found.");
    }

    public ArchiveOutputStream createArchiveOutputStream(String str, OutputStream outputStream) throws ArchiveException {
        return createArchiveOutputStream(str, outputStream, this.entryEncoding);
    }

    @Override // org.apache.commons.compress.archivers.ArchiveStreamProvider
    public ArchiveOutputStream createArchiveOutputStream(String str, OutputStream outputStream, String str2) throws ArchiveException {
        if (str == null) {
            throw new IllegalArgumentException("Archivername must not be null.");
        }
        if (outputStream == null) {
            throw new IllegalArgumentException("OutputStream must not be null.");
        }
        if (f8911AR.equalsIgnoreCase(str)) {
            return new ArArchiveOutputStream(outputStream);
        }
        if (ZIP.equalsIgnoreCase(str)) {
            ZipArchiveOutputStream zipArchiveOutputStream = new ZipArchiveOutputStream(outputStream);
            if (str2 != null) {
                zipArchiveOutputStream.setEncoding(str2);
            }
            return zipArchiveOutputStream;
        }
        if (TAR.equalsIgnoreCase(str)) {
            if (str2 != null) {
                return new TarArchiveOutputStream(outputStream, str2);
            }
            return new TarArchiveOutputStream(outputStream);
        }
        if (JAR.equalsIgnoreCase(str)) {
            if (str2 != null) {
                return new JarArchiveOutputStream(outputStream, str2);
            }
            return new JarArchiveOutputStream(outputStream);
        }
        if (CPIO.equalsIgnoreCase(str)) {
            if (str2 != null) {
                return new CpioArchiveOutputStream(outputStream, str2);
            }
            return new CpioArchiveOutputStream(outputStream);
        }
        if (SEVEN_Z.equalsIgnoreCase(str)) {
            throw new StreamingNotSupportedException(SEVEN_Z);
        }
        ArchiveStreamProvider archiveStreamProvider = getArchiveOutputStreamProviders().get(toKey(str));
        if (archiveStreamProvider != null) {
            return archiveStreamProvider.createArchiveOutputStream(str, outputStream, str2);
        }
        throw new ArchiveException("Archiver: " + str + " not found.");
    }

    public ArchiveInputStream createArchiveInputStream(InputStream inputStream) throws ArchiveException {
        return createArchiveInputStream(detect(inputStream), inputStream);
    }

    public static String detect(InputStream inputStream) throws ArchiveException {
        if (inputStream == null) {
            throw new IllegalArgumentException("Stream must not be null.");
        }
        if (!inputStream.markSupported()) {
            throw new IllegalArgumentException("Mark is not supported.");
        }
        byte[] bArr = new byte[12];
        inputStream.mark(bArr.length);
        try {
            int readFully = IOUtils.readFully(inputStream, bArr);
            inputStream.reset();
            if (ZipArchiveInputStream.matches(bArr, readFully)) {
                return ZIP;
            }
            if (JarArchiveInputStream.matches(bArr, readFully)) {
                return JAR;
            }
            if (ArArchiveInputStream.matches(bArr, readFully)) {
                return f8911AR;
            }
            if (CpioArchiveInputStream.matches(bArr, readFully)) {
                return CPIO;
            }
            if (ArjArchiveInputStream.matches(bArr, readFully)) {
                return ARJ;
            }
            if (SevenZFile.matches(bArr, readFully)) {
                return SEVEN_Z;
            }
            byte[] bArr2 = new byte[32];
            inputStream.mark(bArr2.length);
            try {
                int readFully2 = IOUtils.readFully(inputStream, bArr2);
                inputStream.reset();
                if (DumpArchiveInputStream.matches(bArr2, readFully2)) {
                    return DUMP;
                }
                byte[] bArr3 = new byte[512];
                inputStream.mark(bArr3.length);
                try {
                    int readFully3 = IOUtils.readFully(inputStream, bArr3);
                    inputStream.reset();
                    if (TarArchiveInputStream.matches(bArr3, readFully3)) {
                        return TAR;
                    }
                    if (readFully3 >= 512) {
                        TarArchiveInputStream tarArchiveInputStream = null;
                        try {
                            TarArchiveInputStream tarArchiveInputStream2 = new TarArchiveInputStream(new ByteArrayInputStream(bArr3));
                            try {
                                if (tarArchiveInputStream2.getNextTarEntry().isCheckSumOK()) {
                                    IOUtils.closeQuietly(tarArchiveInputStream2);
                                    return TAR;
                                }
                                IOUtils.closeQuietly(tarArchiveInputStream2);
                            } catch (Exception unused) {
                                tarArchiveInputStream = tarArchiveInputStream2;
                                IOUtils.closeQuietly(tarArchiveInputStream);
                                throw new ArchiveException("No Archiver found for the stream signature");
                            } catch (Throwable th) {
                                th = th;
                                tarArchiveInputStream = tarArchiveInputStream2;
                                IOUtils.closeQuietly(tarArchiveInputStream);
                                throw th;
                            }
                        } catch (Exception unused2) {
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    throw new ArchiveException("No Archiver found for the stream signature");
                } catch (IOException e) {
                    throw new ArchiveException("IOException while reading tar signature", e);
                }
            } catch (IOException e2) {
                throw new ArchiveException("IOException while reading dump signature", e2);
            }
        } catch (IOException e3) {
            throw new ArchiveException("IOException while reading signature.", e3);
        }
    }

    public SortedMap<String, ArchiveStreamProvider> getArchiveInputStreamProviders() {
        if (this.archiveInputStreamProviders == null) {
            this.archiveInputStreamProviders = Collections.unmodifiableSortedMap(findAvailableArchiveInputStreamProviders());
        }
        return this.archiveInputStreamProviders;
    }

    public SortedMap<String, ArchiveStreamProvider> getArchiveOutputStreamProviders() {
        if (this.archiveOutputStreamProviders == null) {
            this.archiveOutputStreamProviders = Collections.unmodifiableSortedMap(findAvailableArchiveOutputStreamProviders());
        }
        return this.archiveOutputStreamProviders;
    }

    @Override // org.apache.commons.compress.archivers.ArchiveStreamProvider
    public Set<String> getInputStreamArchiveNames() {
        return Sets.newHashSet(f8911AR, ARJ, ZIP, TAR, JAR, CPIO, DUMP, SEVEN_Z);
    }

    @Override // org.apache.commons.compress.archivers.ArchiveStreamProvider
    public Set<String> getOutputStreamArchiveNames() {
        return Sets.newHashSet(f8911AR, ZIP, TAR, JAR, CPIO, SEVEN_Z);
    }
}
