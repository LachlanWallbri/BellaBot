package io.minio;

import com.google.common.io.BaseEncoding;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Locale;
import java.util.Objects;
import javax.annotation.Nonnull;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* loaded from: classes7.dex */
class PartReader {
    private static final long CHUNK_SIZE = 2147483647L;
    private byte[] buf16k;
    private ByteBufferStream[] buffers;
    boolean eof;
    private RandomAccessFile file;
    private long objectSize;
    private byte[] oneByte;
    private int partCount;
    private int partNumber;
    private long partSize;
    private InputStream stream;
    private long totalDataRead;

    private PartReader(long j, long j2, int i) {
        this.buf16k = new byte[16384];
        this.oneByte = null;
        this.objectSize = j;
        this.partSize = j2;
        this.partCount = i;
        long j3 = j2 / CHUNK_SIZE;
        j3 = j2 - (CHUNK_SIZE * j3) > 0 ? j3 + 1 : j3;
        this.buffers = new ByteBufferStream[(int) (j3 == 0 ? j3 + 1 : j3)];
    }

    public PartReader(@Nonnull RandomAccessFile randomAccessFile, long j, long j2, int i) {
        this(j, j2, i);
        this.file = (RandomAccessFile) Objects.requireNonNull(randomAccessFile, "file must not be null");
        if (this.objectSize < 0) {
            throw new IllegalArgumentException("object size must be provided");
        }
    }

    public PartReader(@Nonnull InputStream inputStream, long j, long j2, int i) {
        this(j, j2, i);
        this.stream = (InputStream) Objects.requireNonNull(inputStream, "stream must not be null");
        int i2 = 0;
        while (true) {
            ByteBufferStream[] byteBufferStreamArr = this.buffers;
            if (i2 >= byteBufferStreamArr.length) {
                return;
            }
            byteBufferStreamArr[i2] = new ByteBufferStream();
            i2++;
        }
    }

    private long readStreamChunk(ByteBufferStream byteBufferStream, long j, MessageDigest messageDigest, MessageDigest messageDigest2) throws IOException {
        long j2;
        byte[] bArr = this.oneByte;
        if (bArr != null) {
            byteBufferStream.write(bArr);
            messageDigest.update(this.oneByte);
            if (messageDigest2 != null) {
                messageDigest2.update(this.oneByte);
            }
            j2 = 1;
            this.oneByte = null;
        } else {
            j2 = 0;
        }
        while (true) {
            if (j2 >= j) {
                break;
            }
            long j3 = j - j2;
            byte[] bArr2 = this.buf16k;
            if (j3 > bArr2.length) {
                j3 = bArr2.length;
            }
            int read = this.stream.read(this.buf16k, 0, (int) j3);
            this.eof = read < 0;
            if (!this.eof) {
                byteBufferStream.write(this.buf16k, 0, read);
                messageDigest.update(this.buf16k, 0, read);
                if (messageDigest2 != null) {
                    messageDigest2.update(this.buf16k, 0, read);
                }
                j2 += read;
            } else if (this.objectSize >= 0) {
                throw new IOException("unexpected EOF");
            }
        }
        return j2;
    }

    private long readStream(long j, MessageDigest messageDigest, MessageDigest messageDigest2) throws IOException {
        long j2;
        long j3;
        long j4 = j / CHUNK_SIZE;
        long j5 = j - (j4 * CHUNK_SIZE);
        if (j5 > 0) {
            j2 = j4 + 1;
            j3 = j5;
        } else {
            j2 = j4;
            j3 = 2147483647L;
        }
        int i = 0;
        while (true) {
            ByteBufferStream[] byteBufferStreamArr = this.buffers;
            if (i >= byteBufferStreamArr.length) {
                break;
            }
            byteBufferStreamArr[i].reset();
            i++;
        }
        long j6 = 0;
        long j7 = 1;
        while (j7 <= j2 && !this.eof) {
            j6 += readStreamChunk(this.buffers[(int) (j7 - 1)], j7 != j2 ? 2147483647L : j3, messageDigest, messageDigest2);
            j7++;
        }
        if (!this.eof && this.objectSize < 0) {
            this.oneByte = new byte[1];
            this.eof = this.stream.read(this.oneByte) < 0;
        }
        return j6;
    }

    private long readFile(long j, MessageDigest messageDigest, MessageDigest messageDigest2) throws IOException {
        long filePointer = this.file.getFilePointer();
        long j2 = 0;
        while (j2 < j) {
            long j3 = j - j2;
            byte[] bArr = this.buf16k;
            if (j3 > bArr.length) {
                j3 = bArr.length;
            }
            int read = this.file.read(this.buf16k, 0, (int) j3);
            if (read < 0) {
                throw new IOException("unexpected EOF");
            }
            messageDigest.update(this.buf16k, 0, read);
            if (messageDigest2 != null) {
                messageDigest2.update(this.buf16k, 0, read);
            }
            j2 += read;
        }
        this.file.seek(filePointer);
        return j2;
    }

    private long read(long j, MessageDigest messageDigest, MessageDigest messageDigest2) throws IOException {
        return this.file != null ? readFile(j, messageDigest, messageDigest2) : readStream(j, messageDigest, messageDigest2);
    }

    public PartSource getPart(boolean z) throws NoSuchAlgorithmException, IOException {
        int i = this.partNumber;
        if (i == this.partCount) {
            return null;
        }
        this.partNumber = i + 1;
        MessageDigest messageDigest = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        MessageDigest messageDigest2 = z ? MessageDigest.getInstance("SHA-256") : null;
        long j = this.partSize;
        if (this.partNumber == this.partCount) {
            j = this.objectSize - this.totalDataRead;
        }
        long read = read(j, messageDigest, messageDigest2);
        this.totalDataRead += read;
        if (this.objectSize < 0 && this.eof) {
            this.partCount = this.partNumber;
        }
        String encodeToString = Base64.getEncoder().encodeToString(messageDigest.digest());
        String lowerCase = z ? BaseEncoding.base16().encode(messageDigest2.digest()).toLowerCase(Locale.US) : null;
        RandomAccessFile randomAccessFile = this.file;
        if (randomAccessFile != null) {
            return new PartSource(this.partNumber, randomAccessFile, read, encodeToString, lowerCase);
        }
        return new PartSource(this.partNumber, this.buffers, read, encodeToString, lowerCase);
    }

    public int partCount() {
        return this.partCount;
    }
}
