package org.apache.commons.compress.archivers.examples;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;
import java.util.Enumeration;
import java.util.Iterator;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;

/* loaded from: classes8.dex */
public class Expander {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public interface ArchiveEntrySupplier {
        ArchiveEntry getNextReadableEntry() throws IOException;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public interface EntryWriter {
        void writeEntryDataTo(ArchiveEntry archiveEntry, OutputStream outputStream) throws IOException;
    }

    public void expand(File file, File file2) throws IOException, ArchiveException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(file.toPath(), new OpenOption[0]));
        try {
            String detect = ArchiveStreamFactory.detect(bufferedInputStream);
            bufferedInputStream.close();
            expand(detect, file, file2);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    bufferedInputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public void expand(String str, File file, File file2) throws IOException, ArchiveException {
        if (prefersSeekableByteChannel(str)) {
            FileChannel open = FileChannel.open(file.toPath(), StandardOpenOption.READ);
            try {
                expand(str, open, file2, CloseableConsumer.CLOSING_CONSUMER);
                if (open != null) {
                    open.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(file.toPath(), new OpenOption[0]));
        try {
            expand(str, bufferedInputStream, file2, CloseableConsumer.CLOSING_CONSUMER);
            bufferedInputStream.close();
        } catch (Throwable th4) {
            try {
                throw th4;
            } catch (Throwable th5) {
                try {
                    bufferedInputStream.close();
                } catch (Throwable th6) {
                    th4.addSuppressed(th6);
                }
                throw th5;
            }
        }
    }

    @Deprecated
    public void expand(InputStream inputStream, File file) throws IOException, ArchiveException {
        expand(inputStream, file, CloseableConsumer.NULL_CONSUMER);
    }

    public void expand(InputStream inputStream, File file, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
        CloseableConsumerAdapter closeableConsumerAdapter = new CloseableConsumerAdapter(closeableConsumer);
        try {
            expand((ArchiveInputStream) closeableConsumerAdapter.track(ArchiveStreamFactory.DEFAULT.createArchiveInputStream(inputStream)), file);
            closeableConsumerAdapter.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    closeableConsumerAdapter.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Deprecated
    public void expand(String str, InputStream inputStream, File file) throws IOException, ArchiveException {
        expand(str, inputStream, file, CloseableConsumer.NULL_CONSUMER);
    }

    public void expand(String str, InputStream inputStream, File file, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
        CloseableConsumerAdapter closeableConsumerAdapter = new CloseableConsumerAdapter(closeableConsumer);
        try {
            expand((ArchiveInputStream) closeableConsumerAdapter.track(ArchiveStreamFactory.DEFAULT.createArchiveInputStream(str, inputStream)), file);
            closeableConsumerAdapter.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    closeableConsumerAdapter.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Deprecated
    public void expand(String str, SeekableByteChannel seekableByteChannel, File file) throws IOException, ArchiveException {
        expand(str, seekableByteChannel, file, CloseableConsumer.NULL_CONSUMER);
    }

    public void expand(String str, SeekableByteChannel seekableByteChannel, File file, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
        CloseableConsumerAdapter closeableConsumerAdapter = new CloseableConsumerAdapter(closeableConsumer);
        try {
            if (!prefersSeekableByteChannel(str)) {
                expand(str, (InputStream) closeableConsumerAdapter.track(Channels.newInputStream(seekableByteChannel)), file);
            } else if (ArchiveStreamFactory.TAR.equalsIgnoreCase(str)) {
                expand((TarFile) closeableConsumerAdapter.track(new TarFile(seekableByteChannel)), file);
            } else if (ArchiveStreamFactory.ZIP.equalsIgnoreCase(str)) {
                expand((ZipFile) closeableConsumerAdapter.track(new ZipFile(seekableByteChannel)), file);
            } else if (ArchiveStreamFactory.SEVEN_Z.equalsIgnoreCase(str)) {
                expand((SevenZFile) closeableConsumerAdapter.track(new SevenZFile(seekableByteChannel)), file);
            } else {
                throw new ArchiveException("Don't know how to handle format " + str);
            }
            closeableConsumerAdapter.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    closeableConsumerAdapter.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public void expand(final ArchiveInputStream archiveInputStream, File file) throws IOException, ArchiveException {
        expand(new ArchiveEntrySupplier() { // from class: org.apache.commons.compress.archivers.examples.-$$Lambda$Expander$bU6GHYbqstJmcM4F92xfM7i6YTg
            @Override // org.apache.commons.compress.archivers.examples.Expander.ArchiveEntrySupplier
            public final ArchiveEntry getNextReadableEntry() {
                return Expander.lambda$expand$0(ArchiveInputStream.this);
            }
        }, new EntryWriter() { // from class: org.apache.commons.compress.archivers.examples.-$$Lambda$Expander$dYwAG9VSm91mIA616YpsXpJULwA
            @Override // org.apache.commons.compress.archivers.examples.Expander.EntryWriter
            public final void writeEntryDataTo(ArchiveEntry archiveEntry, OutputStream outputStream) {
                IOUtils.copy(ArchiveInputStream.this, outputStream);
            }
        }, file);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ArchiveEntry lambda$expand$0(ArchiveInputStream archiveInputStream) throws IOException {
        ArchiveEntry nextEntry = archiveInputStream.getNextEntry();
        while (nextEntry != null && !archiveInputStream.canReadEntryData(nextEntry)) {
            nextEntry = archiveInputStream.getNextEntry();
        }
        return nextEntry;
    }

    public void expand(final TarFile tarFile, File file) throws IOException, ArchiveException {
        final Iterator<TarArchiveEntry> it = tarFile.getEntries().iterator();
        expand(new ArchiveEntrySupplier() { // from class: org.apache.commons.compress.archivers.examples.-$$Lambda$Expander$cmDc6c10tBCGo9ZatTf2Tx4jWUs
            @Override // org.apache.commons.compress.archivers.examples.Expander.ArchiveEntrySupplier
            public final ArchiveEntry getNextReadableEntry() {
                return Expander.lambda$expand$2(it);
            }
        }, new EntryWriter() { // from class: org.apache.commons.compress.archivers.examples.-$$Lambda$Expander$w1OmYGGWYzhLcdCfjVt6MhSF3FI
            @Override // org.apache.commons.compress.archivers.examples.Expander.EntryWriter
            public final void writeEntryDataTo(ArchiveEntry archiveEntry, OutputStream outputStream) {
                Expander.lambda$expand$3(TarFile.this, archiveEntry, outputStream);
            }
        }, file);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ ArchiveEntry lambda$expand$2(Iterator it) throws IOException {
        if (it.hasNext()) {
            return (ArchiveEntry) it.next();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$expand$3(TarFile tarFile, ArchiveEntry archiveEntry, OutputStream outputStream) throws IOException {
        InputStream inputStream = tarFile.getInputStream((TarArchiveEntry) archiveEntry);
        try {
            IOUtils.copy(inputStream, outputStream);
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public void expand(final ZipFile zipFile, File file) throws IOException, ArchiveException {
        final Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
        expand(new ArchiveEntrySupplier() { // from class: org.apache.commons.compress.archivers.examples.-$$Lambda$Expander$ybBuwWvpqR-vvKCd9yxmLnWAT18
            @Override // org.apache.commons.compress.archivers.examples.Expander.ArchiveEntrySupplier
            public final ArchiveEntry getNextReadableEntry() {
                return Expander.lambda$expand$4(entries, zipFile);
            }
        }, new EntryWriter() { // from class: org.apache.commons.compress.archivers.examples.-$$Lambda$Expander$lo_DXd3iHPC6T25ocp35gVVSLAM
            @Override // org.apache.commons.compress.archivers.examples.Expander.EntryWriter
            public final void writeEntryDataTo(ArchiveEntry archiveEntry, OutputStream outputStream) {
                Expander.lambda$expand$5(ZipFile.this, archiveEntry, outputStream);
            }
        }, file);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x000f, code lost:
    
        r0 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static /* synthetic */ ArchiveEntry lambda$expand$4(Enumeration enumeration, ZipFile zipFile) throws IOException {
        ZipArchiveEntry zipArchiveEntry;
        if (enumeration.hasMoreElements()) {
            zipArchiveEntry = (ZipArchiveEntry) enumeration.nextElement();
            while (zipArchiveEntry != null && !zipFile.canReadEntryData(zipArchiveEntry)) {
                if (enumeration.hasMoreElements()) {
                    zipArchiveEntry = (ZipArchiveEntry) enumeration.nextElement();
                }
            }
            return zipArchiveEntry;
        }
        zipArchiveEntry = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$expand$5(ZipFile zipFile, ArchiveEntry archiveEntry, OutputStream outputStream) throws IOException {
        InputStream inputStream = zipFile.getInputStream((ZipArchiveEntry) archiveEntry);
        try {
            IOUtils.copy(inputStream, outputStream);
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public void expand(final SevenZFile sevenZFile, File file) throws IOException, ArchiveException {
        sevenZFile.getClass();
        expand(new ArchiveEntrySupplier() { // from class: org.apache.commons.compress.archivers.examples.-$$Lambda$p5y4yZ4QaXzefrcVKMv74Bl50nU
            @Override // org.apache.commons.compress.archivers.examples.Expander.ArchiveEntrySupplier
            public final ArchiveEntry getNextReadableEntry() {
                return SevenZFile.this.getNextEntry();
            }
        }, new EntryWriter() { // from class: org.apache.commons.compress.archivers.examples.-$$Lambda$Expander$XCB-otS5iPm2bXQ4C8_wisV4avc
            @Override // org.apache.commons.compress.archivers.examples.Expander.EntryWriter
            public final void writeEntryDataTo(ArchiveEntry archiveEntry, OutputStream outputStream) {
                Expander.lambda$expand$6(SevenZFile.this, archiveEntry, outputStream);
            }
        }, file);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$expand$6(SevenZFile sevenZFile, ArchiveEntry archiveEntry, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int read = sevenZFile.read(bArr);
            if (-1 == read) {
                return;
            } else {
                outputStream.write(bArr, 0, read);
            }
        }
    }

    private boolean prefersSeekableByteChannel(String str) {
        return ArchiveStreamFactory.TAR.equalsIgnoreCase(str) || ArchiveStreamFactory.ZIP.equalsIgnoreCase(str) || ArchiveStreamFactory.SEVEN_Z.equalsIgnoreCase(str);
    }

    private void expand(ArchiveEntrySupplier archiveEntrySupplier, EntryWriter entryWriter, File file) throws IOException {
        String canonicalPath = file.getCanonicalPath();
        if (!canonicalPath.endsWith(File.separator)) {
            canonicalPath = canonicalPath + File.separator;
        }
        ArchiveEntry nextReadableEntry = archiveEntrySupplier.getNextReadableEntry();
        while (nextReadableEntry != null) {
            File file2 = new File(file, nextReadableEntry.getName());
            if (!file2.getCanonicalPath().startsWith(canonicalPath)) {
                throw new IOException("Expanding " + nextReadableEntry.getName() + " would create file outside of " + file);
            }
            if (nextReadableEntry.isDirectory()) {
                if (!file2.isDirectory() && !file2.mkdirs()) {
                    throw new IOException("Failed to create directory " + file2);
                }
            } else {
                File parentFile = file2.getParentFile();
                if (!parentFile.isDirectory() && !parentFile.mkdirs()) {
                    throw new IOException("Failed to create directory " + parentFile);
                }
                OutputStream newOutputStream = Files.newOutputStream(file2.toPath(), new OpenOption[0]);
                try {
                    entryWriter.writeEntryDataTo(nextReadableEntry, newOutputStream);
                    if (newOutputStream != null) {
                        newOutputStream.close();
                    }
                } finally {
                }
            }
            nextReadableEntry = archiveEntrySupplier.getNextReadableEntry();
        }
    }
}
