package org.apache.commons.compress.archivers.sevenz;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.CountingOutputStream;

/* loaded from: classes8.dex */
public class SevenZOutputFile implements Closeable {
    private CountingOutputStream[] additionalCountingStreams;
    private final Map<SevenZArchiveEntry, long[]> additionalSizes;
    private final SeekableByteChannel channel;
    private final CRC32 compressedCrc32;
    private Iterable<? extends SevenZMethodConfiguration> contentMethods;
    private final CRC32 crc32;
    private CountingOutputStream currentOutputStream;
    private long fileBytesWritten;
    private final List<SevenZArchiveEntry> files;
    private boolean finished;
    private int numNonEmptyStreams;

    static /* synthetic */ long access$408(SevenZOutputFile sevenZOutputFile) {
        long j = sevenZOutputFile.fileBytesWritten;
        sevenZOutputFile.fileBytesWritten = 1 + j;
        return j;
    }

    public SevenZOutputFile(File file) throws IOException {
        this(Files.newByteChannel(file.toPath(), EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING), new FileAttribute[0]));
    }

    public SevenZOutputFile(SeekableByteChannel seekableByteChannel) throws IOException {
        this.files = new ArrayList();
        this.crc32 = new CRC32();
        this.compressedCrc32 = new CRC32();
        this.contentMethods = Collections.singletonList(new SevenZMethodConfiguration(SevenZMethod.LZMA2));
        this.additionalSizes = new HashMap();
        this.channel = seekableByteChannel;
        seekableByteChannel.position(32L);
    }

    public void setContentCompression(SevenZMethod sevenZMethod) {
        setContentMethods(Collections.singletonList(new SevenZMethodConfiguration(sevenZMethod)));
    }

    public void setContentMethods(Iterable<? extends SevenZMethodConfiguration> iterable) {
        this.contentMethods = reverse(iterable);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            if (!this.finished) {
                finish();
            }
        } finally {
            this.channel.close();
        }
    }

    public SevenZArchiveEntry createArchiveEntry(File file, String str) throws IOException {
        SevenZArchiveEntry sevenZArchiveEntry = new SevenZArchiveEntry();
        sevenZArchiveEntry.setDirectory(file.isDirectory());
        sevenZArchiveEntry.setName(str);
        sevenZArchiveEntry.setLastModifiedDate(new Date(file.lastModified()));
        return sevenZArchiveEntry;
    }

    public SevenZArchiveEntry createArchiveEntry(Path path, String str, LinkOption... linkOptionArr) throws IOException {
        SevenZArchiveEntry sevenZArchiveEntry = new SevenZArchiveEntry();
        sevenZArchiveEntry.setDirectory(Files.isDirectory(path, linkOptionArr));
        sevenZArchiveEntry.setName(str);
        sevenZArchiveEntry.setLastModifiedDate(new Date(Files.getLastModifiedTime(path, linkOptionArr).toMillis()));
        return sevenZArchiveEntry;
    }

    public void putArchiveEntry(ArchiveEntry archiveEntry) throws IOException {
        this.files.add((SevenZArchiveEntry) archiveEntry);
    }

    public void closeArchiveEntry() throws IOException {
        CountingOutputStream countingOutputStream = this.currentOutputStream;
        if (countingOutputStream != null) {
            countingOutputStream.flush();
            this.currentOutputStream.close();
        }
        List<SevenZArchiveEntry> list = this.files;
        SevenZArchiveEntry sevenZArchiveEntry = list.get(list.size() - 1);
        int i = 0;
        if (this.fileBytesWritten > 0) {
            sevenZArchiveEntry.setHasStream(true);
            this.numNonEmptyStreams++;
            sevenZArchiveEntry.setSize(this.currentOutputStream.getBytesWritten());
            sevenZArchiveEntry.setCompressedSize(this.fileBytesWritten);
            sevenZArchiveEntry.setCrcValue(this.crc32.getValue());
            sevenZArchiveEntry.setCompressedCrcValue(this.compressedCrc32.getValue());
            sevenZArchiveEntry.setHasCrc(true);
            CountingOutputStream[] countingOutputStreamArr = this.additionalCountingStreams;
            if (countingOutputStreamArr != null) {
                long[] jArr = new long[countingOutputStreamArr.length];
                while (true) {
                    CountingOutputStream[] countingOutputStreamArr2 = this.additionalCountingStreams;
                    if (i >= countingOutputStreamArr2.length) {
                        break;
                    }
                    jArr[i] = countingOutputStreamArr2[i].getBytesWritten();
                    i++;
                }
                this.additionalSizes.put(sevenZArchiveEntry, jArr);
            }
        } else {
            sevenZArchiveEntry.setHasStream(false);
            sevenZArchiveEntry.setSize(0L);
            sevenZArchiveEntry.setCompressedSize(0L);
            sevenZArchiveEntry.setHasCrc(false);
        }
        this.currentOutputStream = null;
        this.additionalCountingStreams = null;
        this.crc32.reset();
        this.compressedCrc32.reset();
        this.fileBytesWritten = 0L;
    }

    public void write(int i) throws IOException {
        getCurrentOutputStream().write(i);
    }

    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i2 > 0) {
            getCurrentOutputStream().write(bArr, i, i2);
        }
    }

    public void write(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[8024];
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                return;
            } else {
                write(bArr, 0, read);
            }
        }
    }

    public void write(Path path, OpenOption... openOptionArr) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(path, openOptionArr));
        try {
            write(bufferedInputStream);
            bufferedInputStream.close();
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

    public void finish() throws IOException {
        if (this.finished) {
            throw new IOException("This archive has already been finished");
        }
        this.finished = true;
        long position = this.channel.position();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        writeHeader(dataOutputStream);
        dataOutputStream.flush();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        this.channel.write(ByteBuffer.wrap(byteArray));
        CRC32 crc32 = new CRC32();
        crc32.update(byteArray);
        ByteBuffer order = ByteBuffer.allocate(SevenZFile.sevenZSignature.length + 2 + 4 + 8 + 8 + 4).order(ByteOrder.LITTLE_ENDIAN);
        this.channel.position(0L);
        order.put(SevenZFile.sevenZSignature);
        order.put((byte) 0).put((byte) 2);
        order.putInt(0);
        order.putLong(position - 32).putLong(byteArray.length & 4294967295L).putInt((int) crc32.getValue());
        crc32.reset();
        crc32.update(order.array(), SevenZFile.sevenZSignature.length + 6, 20);
        order.putInt(SevenZFile.sevenZSignature.length + 2, (int) crc32.getValue());
        order.flip();
        this.channel.write(order);
    }

    private OutputStream getCurrentOutputStream() throws IOException {
        if (this.currentOutputStream == null) {
            this.currentOutputStream = setupFileOutputStream();
        }
        return this.currentOutputStream;
    }

    private CountingOutputStream setupFileOutputStream() throws IOException {
        if (this.files.isEmpty()) {
            throw new IllegalStateException("No current 7z entry");
        }
        OutputStream outputStreamWrapper = new OutputStreamWrapper();
        ArrayList arrayList = new ArrayList();
        List<SevenZArchiveEntry> list = this.files;
        boolean z = true;
        for (SevenZMethodConfiguration sevenZMethodConfiguration : getContentMethods(list.get(list.size() - 1))) {
            if (!z) {
                CountingOutputStream countingOutputStream = new CountingOutputStream(outputStreamWrapper);
                arrayList.add(countingOutputStream);
                outputStreamWrapper = countingOutputStream;
            }
            outputStreamWrapper = Coders.addEncoder(outputStreamWrapper, sevenZMethodConfiguration.getMethod(), sevenZMethodConfiguration.getOptions());
            z = false;
        }
        if (!arrayList.isEmpty()) {
            this.additionalCountingStreams = (CountingOutputStream[]) arrayList.toArray(new CountingOutputStream[0]);
        }
        return new CountingOutputStream(outputStreamWrapper) { // from class: org.apache.commons.compress.archivers.sevenz.SevenZOutputFile.1
            @Override // org.apache.commons.compress.utils.CountingOutputStream, java.io.FilterOutputStream, java.io.OutputStream
            public void write(int i) throws IOException {
                super.write(i);
                SevenZOutputFile.this.crc32.update(i);
            }

            @Override // org.apache.commons.compress.utils.CountingOutputStream, java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr) throws IOException {
                super.write(bArr);
                SevenZOutputFile.this.crc32.update(bArr);
            }

            @Override // org.apache.commons.compress.utils.CountingOutputStream, java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) throws IOException {
                super.write(bArr, i, i2);
                SevenZOutputFile.this.crc32.update(bArr, i, i2);
            }
        };
    }

    private Iterable<? extends SevenZMethodConfiguration> getContentMethods(SevenZArchiveEntry sevenZArchiveEntry) {
        Iterable<? extends SevenZMethodConfiguration> contentMethods = sevenZArchiveEntry.getContentMethods();
        return contentMethods == null ? this.contentMethods : contentMethods;
    }

    private void writeHeader(DataOutput dataOutput) throws IOException {
        dataOutput.write(1);
        dataOutput.write(4);
        writeStreamsInfo(dataOutput);
        writeFilesInfo(dataOutput);
        dataOutput.write(0);
    }

    private void writeStreamsInfo(DataOutput dataOutput) throws IOException {
        if (this.numNonEmptyStreams > 0) {
            writePackInfo(dataOutput);
            writeUnpackInfo(dataOutput);
        }
        writeSubStreamsInfo(dataOutput);
        dataOutput.write(0);
    }

    private void writePackInfo(DataOutput dataOutput) throws IOException {
        dataOutput.write(6);
        writeUint64(dataOutput, 0L);
        writeUint64(dataOutput, this.numNonEmptyStreams & 4294967295L);
        dataOutput.write(9);
        for (SevenZArchiveEntry sevenZArchiveEntry : this.files) {
            if (sevenZArchiveEntry.hasStream()) {
                writeUint64(dataOutput, sevenZArchiveEntry.getCompressedSize());
            }
        }
        dataOutput.write(10);
        dataOutput.write(1);
        for (SevenZArchiveEntry sevenZArchiveEntry2 : this.files) {
            if (sevenZArchiveEntry2.hasStream()) {
                dataOutput.writeInt(Integer.reverseBytes((int) sevenZArchiveEntry2.getCompressedCrcValue()));
            }
        }
        dataOutput.write(0);
    }

    private void writeUnpackInfo(DataOutput dataOutput) throws IOException {
        dataOutput.write(7);
        dataOutput.write(11);
        writeUint64(dataOutput, this.numNonEmptyStreams);
        dataOutput.write(0);
        for (SevenZArchiveEntry sevenZArchiveEntry : this.files) {
            if (sevenZArchiveEntry.hasStream()) {
                writeFolder(dataOutput, sevenZArchiveEntry);
            }
        }
        dataOutput.write(12);
        for (SevenZArchiveEntry sevenZArchiveEntry2 : this.files) {
            if (sevenZArchiveEntry2.hasStream()) {
                long[] jArr = this.additionalSizes.get(sevenZArchiveEntry2);
                if (jArr != null) {
                    for (long j : jArr) {
                        writeUint64(dataOutput, j);
                    }
                }
                writeUint64(dataOutput, sevenZArchiveEntry2.getSize());
            }
        }
        dataOutput.write(10);
        dataOutput.write(1);
        for (SevenZArchiveEntry sevenZArchiveEntry3 : this.files) {
            if (sevenZArchiveEntry3.hasStream()) {
                dataOutput.writeInt(Integer.reverseBytes((int) sevenZArchiveEntry3.getCrcValue()));
            }
        }
        dataOutput.write(0);
    }

    private void writeFolder(DataOutput dataOutput, SevenZArchiveEntry sevenZArchiveEntry) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Iterator<? extends SevenZMethodConfiguration> it = getContentMethods(sevenZArchiveEntry).iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            writeSingleCodec(it.next(), byteArrayOutputStream);
        }
        writeUint64(dataOutput, i);
        dataOutput.write(byteArrayOutputStream.toByteArray());
        long j = 0;
        while (j < i - 1) {
            long j2 = 1 + j;
            writeUint64(dataOutput, j2);
            writeUint64(dataOutput, j);
            j = j2;
        }
    }

    private void writeSingleCodec(SevenZMethodConfiguration sevenZMethodConfiguration, OutputStream outputStream) throws IOException {
        byte[] id = sevenZMethodConfiguration.getMethod().getId();
        byte[] optionsAsProperties = Coders.findByMethod(sevenZMethodConfiguration.getMethod()).getOptionsAsProperties(sevenZMethodConfiguration.getOptions());
        int length = id.length;
        if (optionsAsProperties.length > 0) {
            length |= 32;
        }
        outputStream.write(length);
        outputStream.write(id);
        if (optionsAsProperties.length > 0) {
            outputStream.write(optionsAsProperties.length);
            outputStream.write(optionsAsProperties);
        }
    }

    private void writeSubStreamsInfo(DataOutput dataOutput) throws IOException {
        dataOutput.write(8);
        dataOutput.write(0);
    }

    private void writeFilesInfo(DataOutput dataOutput) throws IOException {
        dataOutput.write(5);
        writeUint64(dataOutput, this.files.size());
        writeFileEmptyStreams(dataOutput);
        writeFileEmptyFiles(dataOutput);
        writeFileAntiItems(dataOutput);
        writeFileNames(dataOutput);
        writeFileCTimes(dataOutput);
        writeFileATimes(dataOutput);
        writeFileMTimes(dataOutput);
        writeFileWindowsAttributes(dataOutput);
        dataOutput.write(0);
    }

    private void writeFileEmptyStreams(DataOutput dataOutput) throws IOException {
        int i;
        boolean z;
        Iterator<SevenZArchiveEntry> it = this.files.iterator();
        while (true) {
            if (it.hasNext()) {
                if (!it.next().hasStream()) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            dataOutput.write(14);
            BitSet bitSet = new BitSet(this.files.size());
            for (i = 0; i < this.files.size(); i++) {
                bitSet.set(i, !this.files.get(i).hasStream());
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            writeBits(dataOutputStream, bitSet, this.files.size());
            dataOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            writeUint64(dataOutput, byteArray.length);
            dataOutput.write(byteArray);
        }
    }

    private void writeFileEmptyFiles(DataOutput dataOutput) throws IOException {
        boolean z = false;
        BitSet bitSet = new BitSet(0);
        int i = 0;
        for (SevenZArchiveEntry sevenZArchiveEntry : this.files) {
            if (!sevenZArchiveEntry.hasStream()) {
                boolean isDirectory = sevenZArchiveEntry.isDirectory();
                bitSet.set(i, !isDirectory);
                z |= !isDirectory;
                i++;
            }
        }
        if (z) {
            dataOutput.write(15);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            writeBits(dataOutputStream, bitSet, i);
            dataOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            writeUint64(dataOutput, byteArray.length);
            dataOutput.write(byteArray);
        }
    }

    private void writeFileAntiItems(DataOutput dataOutput) throws IOException {
        boolean z = false;
        BitSet bitSet = new BitSet(0);
        int i = 0;
        for (SevenZArchiveEntry sevenZArchiveEntry : this.files) {
            if (!sevenZArchiveEntry.hasStream()) {
                boolean isAntiItem = sevenZArchiveEntry.isAntiItem();
                bitSet.set(i, isAntiItem);
                z |= isAntiItem;
                i++;
            }
        }
        if (z) {
            dataOutput.write(16);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            writeBits(dataOutputStream, bitSet, i);
            dataOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            writeUint64(dataOutput, byteArray.length);
            dataOutput.write(byteArray);
        }
    }

    private void writeFileNames(DataOutput dataOutput) throws IOException {
        dataOutput.write(17);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.write(0);
        Iterator<SevenZArchiveEntry> it = this.files.iterator();
        while (it.hasNext()) {
            dataOutputStream.write(it.next().getName().getBytes(StandardCharsets.UTF_16LE));
            dataOutputStream.writeShort(0);
        }
        dataOutputStream.flush();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        writeUint64(dataOutput, byteArray.length);
        dataOutput.write(byteArray);
    }

    private void writeFileCTimes(DataOutput dataOutput) throws IOException {
        Iterator<SevenZArchiveEntry> it = this.files.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().getHasCreationDate()) {
                i++;
            }
        }
        if (i > 0) {
            dataOutput.write(18);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            if (i != this.files.size()) {
                dataOutputStream.write(0);
                BitSet bitSet = new BitSet(this.files.size());
                for (int i2 = 0; i2 < this.files.size(); i2++) {
                    bitSet.set(i2, this.files.get(i2).getHasCreationDate());
                }
                writeBits(dataOutputStream, bitSet, this.files.size());
            } else {
                dataOutputStream.write(1);
            }
            dataOutputStream.write(0);
            for (SevenZArchiveEntry sevenZArchiveEntry : this.files) {
                if (sevenZArchiveEntry.getHasCreationDate()) {
                    dataOutputStream.writeLong(Long.reverseBytes(SevenZArchiveEntry.javaTimeToNtfsTime(sevenZArchiveEntry.getCreationDate())));
                }
            }
            dataOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            writeUint64(dataOutput, byteArray.length);
            dataOutput.write(byteArray);
        }
    }

    private void writeFileATimes(DataOutput dataOutput) throws IOException {
        Iterator<SevenZArchiveEntry> it = this.files.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().getHasAccessDate()) {
                i++;
            }
        }
        if (i > 0) {
            dataOutput.write(19);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            if (i != this.files.size()) {
                dataOutputStream.write(0);
                BitSet bitSet = new BitSet(this.files.size());
                for (int i2 = 0; i2 < this.files.size(); i2++) {
                    bitSet.set(i2, this.files.get(i2).getHasAccessDate());
                }
                writeBits(dataOutputStream, bitSet, this.files.size());
            } else {
                dataOutputStream.write(1);
            }
            dataOutputStream.write(0);
            for (SevenZArchiveEntry sevenZArchiveEntry : this.files) {
                if (sevenZArchiveEntry.getHasAccessDate()) {
                    dataOutputStream.writeLong(Long.reverseBytes(SevenZArchiveEntry.javaTimeToNtfsTime(sevenZArchiveEntry.getAccessDate())));
                }
            }
            dataOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            writeUint64(dataOutput, byteArray.length);
            dataOutput.write(byteArray);
        }
    }

    private void writeFileMTimes(DataOutput dataOutput) throws IOException {
        Iterator<SevenZArchiveEntry> it = this.files.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().getHasLastModifiedDate()) {
                i++;
            }
        }
        if (i > 0) {
            dataOutput.write(20);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            if (i != this.files.size()) {
                dataOutputStream.write(0);
                BitSet bitSet = new BitSet(this.files.size());
                for (int i2 = 0; i2 < this.files.size(); i2++) {
                    bitSet.set(i2, this.files.get(i2).getHasLastModifiedDate());
                }
                writeBits(dataOutputStream, bitSet, this.files.size());
            } else {
                dataOutputStream.write(1);
            }
            dataOutputStream.write(0);
            for (SevenZArchiveEntry sevenZArchiveEntry : this.files) {
                if (sevenZArchiveEntry.getHasLastModifiedDate()) {
                    dataOutputStream.writeLong(Long.reverseBytes(SevenZArchiveEntry.javaTimeToNtfsTime(sevenZArchiveEntry.getLastModifiedDate())));
                }
            }
            dataOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            writeUint64(dataOutput, byteArray.length);
            dataOutput.write(byteArray);
        }
    }

    private void writeFileWindowsAttributes(DataOutput dataOutput) throws IOException {
        Iterator<SevenZArchiveEntry> it = this.files.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().getHasWindowsAttributes()) {
                i++;
            }
        }
        if (i > 0) {
            dataOutput.write(21);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            if (i != this.files.size()) {
                dataOutputStream.write(0);
                BitSet bitSet = new BitSet(this.files.size());
                for (int i2 = 0; i2 < this.files.size(); i2++) {
                    bitSet.set(i2, this.files.get(i2).getHasWindowsAttributes());
                }
                writeBits(dataOutputStream, bitSet, this.files.size());
            } else {
                dataOutputStream.write(1);
            }
            dataOutputStream.write(0);
            for (SevenZArchiveEntry sevenZArchiveEntry : this.files) {
                if (sevenZArchiveEntry.getHasWindowsAttributes()) {
                    dataOutputStream.writeInt(Integer.reverseBytes(sevenZArchiveEntry.getWindowsAttributes()));
                }
            }
            dataOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            writeUint64(dataOutput, byteArray.length);
            dataOutput.write(byteArray);
        }
    }

    private void writeUint64(DataOutput dataOutput, long j) throws IOException {
        int i = 0;
        int i2 = 128;
        int i3 = 0;
        while (true) {
            if (i >= 8) {
                break;
            }
            int i4 = i + 1;
            if (j < (1 << (i4 * 7))) {
                i3 = (int) (i3 | (j >>> (i * 8)));
                break;
            } else {
                i3 |= i2;
                i2 >>>= 1;
                i = i4;
            }
        }
        dataOutput.write(i3);
        while (i > 0) {
            dataOutput.write((int) (255 & j));
            j >>>= 8;
            i--;
        }
    }

    private void writeBits(DataOutput dataOutput, BitSet bitSet, int i) throws IOException {
        int i2 = 7;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            i3 |= (bitSet.get(i4) ? 1 : 0) << i2;
            i2--;
            if (i2 < 0) {
                dataOutput.write(i3);
                i2 = 7;
                i3 = 0;
            }
        }
        if (i2 != 7) {
            dataOutput.write(i3);
        }
    }

    private static <T> Iterable<T> reverse(Iterable<T> iterable) {
        LinkedList linkedList = new LinkedList();
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            linkedList.addFirst(it.next());
        }
        return linkedList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public class OutputStreamWrapper extends OutputStream {
        private static final int BUF_SIZE = 8192;
        private final ByteBuffer buffer;

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
        }

        private OutputStreamWrapper() {
            this.buffer = ByteBuffer.allocate(8192);
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            this.buffer.clear();
            this.buffer.put((byte) i).flip();
            SevenZOutputFile.this.channel.write(this.buffer);
            SevenZOutputFile.this.compressedCrc32.update(i);
            SevenZOutputFile.access$408(SevenZOutputFile.this);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            write(bArr, 0, bArr.length);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (i2 > 8192) {
                SevenZOutputFile.this.channel.write(ByteBuffer.wrap(bArr, i, i2));
            } else {
                this.buffer.clear();
                this.buffer.put(bArr, i, i2).flip();
                SevenZOutputFile.this.channel.write(this.buffer);
            }
            SevenZOutputFile.this.compressedCrc32.update(bArr, i, i2);
            SevenZOutputFile.this.fileBytesWritten += i2;
        }
    }
}
