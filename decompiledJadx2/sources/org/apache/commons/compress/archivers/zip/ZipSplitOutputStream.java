package org.apache.commons.compress.archivers.zip;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import org.apache.commons.compress.utils.FileNameUtils;

/* loaded from: classes8.dex */
class ZipSplitOutputStream extends OutputStream {
    private static final long ZIP_SEGMENT_MAX_SIZE = 4294967295L;
    private static final long ZIP_SEGMENT_MIN_SIZE = 65536;
    private long currentSplitSegmentBytesWritten;
    private int currentSplitSegmentIndex;
    private boolean finished;
    private OutputStream outputStream;
    private final byte[] singleByte = new byte[1];
    private final long splitSize;
    private File zipFile;

    public ZipSplitOutputStream(File file, long j) throws IllegalArgumentException, IOException {
        if (j < 65536 || j > 4294967295L) {
            throw new IllegalArgumentException("zip split segment size should between 64K and 4,294,967,295");
        }
        this.zipFile = file;
        this.splitSize = j;
        this.outputStream = Files.newOutputStream(file.toPath(), new OpenOption[0]);
        writeZipSplitSignature();
    }

    public void prepareToWriteUnsplittableContent(long j) throws IllegalArgumentException, IOException {
        long j2 = this.splitSize;
        if (j > j2) {
            throw new IllegalArgumentException("The unsplittable content size is bigger than the split segment size");
        }
        if (j2 - this.currentSplitSegmentBytesWritten < j) {
            openNewSplitSegment();
        }
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.singleByte;
        bArr[0] = (byte) (i & 255);
        write(bArr);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i2 <= 0) {
            return;
        }
        long j = this.currentSplitSegmentBytesWritten;
        long j2 = this.splitSize;
        if (j >= j2) {
            openNewSplitSegment();
            write(bArr, i, i2);
            return;
        }
        long j3 = i2;
        if (j + j3 > j2) {
            int i3 = ((int) j2) - ((int) j);
            write(bArr, i, i3);
            openNewSplitSegment();
            write(bArr, i + i3, i2 - i3);
            return;
        }
        this.outputStream.write(bArr, i, i2);
        this.currentSplitSegmentBytesWritten += j3;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.finished) {
            return;
        }
        finish();
    }

    private void finish() throws IOException {
        if (this.finished) {
            throw new IOException("This archive has already been finished");
        }
        String baseName = FileNameUtils.getBaseName(this.zipFile.getName());
        File file = new File(this.zipFile.getParentFile(), baseName + ".zip");
        this.outputStream.close();
        if (!this.zipFile.renameTo(file)) {
            throw new IOException("Failed to rename " + this.zipFile + " to " + file);
        }
        this.finished = true;
    }

    private void openNewSplitSegment() throws IOException {
        if (this.currentSplitSegmentIndex == 0) {
            this.outputStream.close();
            File createNewSplitSegmentFile = createNewSplitSegmentFile(1);
            if (!this.zipFile.renameTo(createNewSplitSegmentFile)) {
                throw new IOException("Failed to rename " + this.zipFile + " to " + createNewSplitSegmentFile);
            }
        }
        File createNewSplitSegmentFile2 = createNewSplitSegmentFile(null);
        this.outputStream.close();
        this.outputStream = Files.newOutputStream(createNewSplitSegmentFile2.toPath(), new OpenOption[0]);
        this.currentSplitSegmentBytesWritten = 0L;
        this.zipFile = createNewSplitSegmentFile2;
        this.currentSplitSegmentIndex++;
    }

    private void writeZipSplitSignature() throws IOException {
        this.outputStream.write(ZipArchiveOutputStream.DD_SIG);
        this.currentSplitSegmentBytesWritten += ZipArchiveOutputStream.DD_SIG.length;
    }

    private File createNewSplitSegmentFile(Integer num) throws IOException {
        String str;
        int intValue = num == null ? this.currentSplitSegmentIndex + 2 : num.intValue();
        String baseName = FileNameUtils.getBaseName(this.zipFile.getName());
        if (intValue <= 9) {
            str = ".z0" + intValue;
        } else {
            str = ".z" + intValue;
        }
        File file = new File(this.zipFile.getParent(), baseName + str);
        if (!file.exists()) {
            return file;
        }
        throw new IOException("split zip segment " + baseName + str + " already exists");
    }

    public int getCurrentSplitSegmentIndex() {
        return this.currentSplitSegmentIndex;
    }

    public long getCurrentSplitSegmentBytesWritten() {
        return this.currentSplitSegmentBytesWritten;
    }
}
