package org.apache.commons.io.input;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public class ReversedLinesFileReader implements Closeable {
    private final int avoidNewlineSplitBufferSize;
    private final int blockSize;
    private final int byteDecrement;
    private FilePart currentFilePart;
    private final Charset encoding;
    private final byte[][] newLineSequences;
    private final RandomAccessFile randomAccessFile;
    private final long totalBlockCount;
    private final long totalByteLength;
    private boolean trailingNewlineOfFileSkipped;

    @Deprecated
    public ReversedLinesFileReader(File file) throws IOException {
        this(file, 4096, Charset.defaultCharset());
    }

    public ReversedLinesFileReader(File file, Charset charset) throws IOException {
        this(file, 4096, charset);
    }

    public ReversedLinesFileReader(File file, int i, Charset charset) throws IOException {
        int i2;
        this.trailingNewlineOfFileSkipped = false;
        this.blockSize = i;
        this.encoding = charset;
        Charset charset2 = Charsets.toCharset(charset);
        if (charset2.newEncoder().maxBytesPerChar() == 1.0f) {
            this.byteDecrement = 1;
        } else if (charset2 == StandardCharsets.UTF_8) {
            this.byteDecrement = 1;
        } else if (charset2 == Charset.forName("Shift_JIS") || charset2 == Charset.forName("windows-31j") || charset2 == Charset.forName("x-windows-949") || charset2 == Charset.forName("gbk") || charset2 == Charset.forName("x-windows-950")) {
            this.byteDecrement = 1;
        } else if (charset2 == StandardCharsets.UTF_16BE || charset2 == StandardCharsets.UTF_16LE) {
            this.byteDecrement = 2;
        } else {
            if (charset2 == StandardCharsets.UTF_16) {
                throw new UnsupportedEncodingException("For UTF-16, you need to specify the byte order (use UTF-16BE or UTF-16LE)");
            }
            throw new UnsupportedEncodingException("Encoding " + charset + " is not supported yet (feel free to submit a patch)");
        }
        byte[][] bArr = {IOUtils.LINE_SEPARATOR_WINDOWS.getBytes(charset), "\n".getBytes(charset), "\r".getBytes(charset)};
        this.newLineSequences = bArr;
        this.avoidNewlineSplitBufferSize = bArr[0].length;
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        this.randomAccessFile = randomAccessFile;
        long length = randomAccessFile.length();
        this.totalByteLength = length;
        long j = i;
        int i3 = (int) (length % j);
        if (i3 > 0) {
            this.totalBlockCount = (length / j) + 1;
        } else {
            this.totalBlockCount = length / j;
            if (length > 0) {
                i2 = i;
                this.currentFilePart = new FilePart(this.totalBlockCount, i2, null);
            }
        }
        i2 = i3;
        this.currentFilePart = new FilePart(this.totalBlockCount, i2, null);
    }

    public ReversedLinesFileReader(File file, int i, String str) throws IOException {
        this(file, i, Charsets.toCharset(str));
    }

    public String readLine() throws IOException {
        String readLine = this.currentFilePart.readLine();
        while (readLine == null) {
            FilePart rollOver = this.currentFilePart.rollOver();
            this.currentFilePart = rollOver;
            if (rollOver == null) {
                break;
            }
            readLine = rollOver.readLine();
        }
        if (!"".equals(readLine) || this.trailingNewlineOfFileSkipped) {
            return readLine;
        }
        this.trailingNewlineOfFileSkipped = true;
        return readLine();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.randomAccessFile.close();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes9.dex
     */
    /* loaded from: classes2.dex */
    private class FilePart {
        private int currentLastBytePos;
        private final byte[] data;
        private byte[] leftOver;

        /* renamed from: no */
        private final long f8982no;

        private FilePart(long j, int i, byte[] bArr) throws IOException {
            this.f8982no = j;
            this.data = new byte[(bArr != null ? bArr.length : 0) + i];
            long j2 = (j - 1) * ReversedLinesFileReader.this.blockSize;
            if (j > 0) {
                ReversedLinesFileReader.this.randomAccessFile.seek(j2);
                if (ReversedLinesFileReader.this.randomAccessFile.read(this.data, 0, i) != i) {
                    throw new IllegalStateException("Count of requested bytes and actually read bytes don't match");
                }
            }
            if (bArr != null) {
                System.arraycopy(bArr, 0, this.data, i, bArr.length);
            }
            this.currentLastBytePos = this.data.length - 1;
            this.leftOver = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public FilePart rollOver() throws IOException {
            if (this.currentLastBytePos > -1) {
                throw new IllegalStateException("Current currentLastCharPos unexpectedly positive... last readLine() should have returned something! currentLastCharPos=" + this.currentLastBytePos);
            }
            if (this.f8982no > 1) {
                ReversedLinesFileReader reversedLinesFileReader = ReversedLinesFileReader.this;
                return new FilePart(this.f8982no - 1, reversedLinesFileReader.blockSize, this.leftOver);
            }
            if (this.leftOver == null) {
                return null;
            }
            throw new IllegalStateException("Unexpected leftover of the last block: leftOverOfThisFilePart=" + new String(this.leftOver, ReversedLinesFileReader.this.encoding));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String readLine() throws IOException {
            String str;
            boolean z = this.f8982no == 1;
            int i = this.currentLastBytePos;
            while (true) {
                if (i > -1) {
                    if (!z && i < ReversedLinesFileReader.this.avoidNewlineSplitBufferSize) {
                        createLeftOver();
                        break;
                    }
                    int newLineMatchByteCount = getNewLineMatchByteCount(this.data, i);
                    if (newLineMatchByteCount <= 0) {
                        i -= ReversedLinesFileReader.this.byteDecrement;
                        if (i < 0) {
                            createLeftOver();
                            break;
                        }
                    } else {
                        int i2 = i + 1;
                        int i3 = (this.currentLastBytePos - i2) + 1;
                        if (i3 < 0) {
                            throw new IllegalStateException("Unexpected negative line length=" + i3);
                        }
                        byte[] bArr = new byte[i3];
                        System.arraycopy(this.data, i2, bArr, 0, i3);
                        str = new String(bArr, ReversedLinesFileReader.this.encoding);
                        this.currentLastBytePos = i - newLineMatchByteCount;
                    }
                } else {
                    break;
                }
            }
            str = null;
            if (!z || this.leftOver == null) {
                return str;
            }
            String str2 = new String(this.leftOver, ReversedLinesFileReader.this.encoding);
            this.leftOver = null;
            return str2;
        }

        private void createLeftOver() {
            int i = this.currentLastBytePos + 1;
            if (i > 0) {
                byte[] bArr = new byte[i];
                this.leftOver = bArr;
                System.arraycopy(this.data, 0, bArr, 0, i);
            } else {
                this.leftOver = null;
            }
            this.currentLastBytePos = -1;
        }

        private int getNewLineMatchByteCount(byte[] bArr, int i) {
            for (byte[] bArr2 : ReversedLinesFileReader.this.newLineSequences) {
                boolean z = true;
                for (int length = bArr2.length - 1; length >= 0; length--) {
                    int length2 = (i + length) - (bArr2.length - 1);
                    z &= length2 >= 0 && bArr[length2] == bArr2[length];
                }
                if (z) {
                    return bArr2.length;
                }
            }
            return 0;
        }
    }
}
