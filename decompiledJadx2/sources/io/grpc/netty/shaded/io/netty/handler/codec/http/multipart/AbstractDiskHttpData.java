package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import com.iflytek.cloud.SpeechEvent;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpConstants;
import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractDiskHttpData extends AbstractHttpData {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AbstractDiskHttpData.class);
    private File file;
    private FileChannel fileChannel;
    private boolean isRenamed;

    protected abstract boolean deleteOnExit();

    protected abstract String getBaseDirectory();

    protected abstract String getDiskFilename();

    protected abstract String getPostfix();

    protected abstract String getPrefix();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public boolean isInMemory() {
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractHttpData, io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public HttpData touch() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractHttpData, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public HttpData touch(Object obj) {
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDiskHttpData(String str, Charset charset, long j) {
        super(str, charset, j);
    }

    private File tempFile() throws IOException {
        String postfix;
        File createTempFile;
        String diskFilename = getDiskFilename();
        if (diskFilename != null) {
            postfix = '_' + diskFilename;
        } else {
            postfix = getPostfix();
        }
        if (getBaseDirectory() == null) {
            createTempFile = File.createTempFile(getPrefix(), postfix);
        } else {
            createTempFile = File.createTempFile(getPrefix(), postfix, new File(getBaseDirectory()));
        }
        if (deleteOnExit()) {
            createTempFile.deleteOnExit();
        }
        return createTempFile;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void setContent(ByteBuf byteBuf) throws IOException {
        ObjectUtil.checkNotNull(byteBuf, SpeechEvent.KEY_EVENT_TTS_BUFFER);
        try {
            this.size = byteBuf.readableBytes();
            checkSize(this.size);
            if (this.definedSize > 0 && this.definedSize < this.size) {
                throw new IOException("Out of size: " + this.size + " > " + this.definedSize);
            }
            if (this.file == null) {
                this.file = tempFile();
            }
            if (byteBuf.readableBytes() == 0) {
                if (!this.file.createNewFile()) {
                    if (this.file.length() == 0) {
                        return;
                    }
                    if (!this.file.delete() || !this.file.createNewFile()) {
                        throw new IOException("file exists already: " + this.file);
                    }
                }
                return;
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(this.file, "rw");
            try {
                randomAccessFile.setLength(0L);
                FileChannel channel = randomAccessFile.getChannel();
                ByteBuffer nioBuffer = byteBuf.nioBuffer();
                int i = 0;
                while (i < this.size) {
                    i += channel.write(nioBuffer);
                }
                byteBuf.readerIndex(byteBuf.readerIndex() + i);
                channel.force(false);
                randomAccessFile.close();
                setCompleted();
            } catch (Throwable th) {
                randomAccessFile.close();
                throw th;
            }
        } finally {
            byteBuf.release();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void addContent(ByteBuf byteBuf, boolean z) throws IOException {
        if (byteBuf != null) {
            try {
                int readableBytes = byteBuf.readableBytes();
                long j = readableBytes;
                checkSize(this.size + j);
                if (this.definedSize > 0 && this.definedSize < this.size + j) {
                    throw new IOException("Out of size: " + (this.size + j) + " > " + this.definedSize);
                }
                if (this.file == null) {
                    this.file = tempFile();
                }
                if (this.fileChannel == null) {
                    this.fileChannel = new RandomAccessFile(this.file, "rw").getChannel();
                }
                long position = this.fileChannel.position();
                int readerIndex = byteBuf.readerIndex();
                long j2 = position;
                int i = readableBytes;
                while (i > 0) {
                    int bytes = byteBuf.getBytes(readerIndex, this.fileChannel, j2, i);
                    if (bytes < 0) {
                        break;
                    }
                    i -= bytes;
                    j2 += bytes;
                    readerIndex += bytes;
                }
                this.fileChannel.position(j2);
                byteBuf.readerIndex(readerIndex);
                this.size += readableBytes - i;
            } finally {
                byteBuf.release();
            }
        }
        if (z) {
            if (this.file == null) {
                this.file = tempFile();
            }
            if (this.fileChannel == null) {
                this.fileChannel = new RandomAccessFile(this.file, "rw").getChannel();
            }
            try {
                this.fileChannel.force(false);
                this.fileChannel.close();
                this.fileChannel = null;
                setCompleted();
                return;
            } catch (Throwable th) {
                this.fileChannel.close();
                throw th;
            }
        }
        ObjectUtil.checkNotNull(byteBuf, SpeechEvent.KEY_EVENT_TTS_BUFFER);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void setContent(File file) throws IOException {
        long length = file.length();
        checkSize(length);
        this.size = length;
        if (this.file != null) {
            delete();
        }
        this.file = file;
        this.isRenamed = true;
        setCompleted();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void setContent(InputStream inputStream) throws IOException {
        ObjectUtil.checkNotNull(inputStream, "inputStream");
        if (this.file != null) {
            delete();
        }
        this.file = tempFile();
        RandomAccessFile randomAccessFile = new RandomAccessFile(this.file, "rw");
        try {
            randomAccessFile.setLength(0L);
            FileChannel channel = randomAccessFile.getChannel();
            byte[] bArr = new byte[16384];
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            int read = inputStream.read(bArr);
            int i = 0;
            while (read > 0) {
                wrap.position(read).flip();
                i += channel.write(wrap);
                checkSize(i);
                read = inputStream.read(bArr);
            }
            channel.force(false);
            randomAccessFile.close();
            this.size = i;
            if (this.definedSize > 0 && this.definedSize < this.size) {
                if (!this.file.delete()) {
                    logger.warn("Failed to delete: {}", this.file);
                }
                this.file = null;
                throw new IOException("Out of size: " + this.size + " > " + this.definedSize);
            }
            this.isRenamed = true;
            setCompleted();
        } catch (Throwable th) {
            randomAccessFile.close();
            throw th;
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0012 -> B:7:0x0027). Please report as a decompilation issue!!! */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void delete() {
        FileChannel fileChannel = this.fileChannel;
        if (fileChannel != null) {
            try {
                try {
                    try {
                        fileChannel.force(false);
                        this.fileChannel.close();
                    } catch (Throwable th) {
                        try {
                            this.fileChannel.close();
                        } catch (IOException e) {
                            logger.warn("Failed to close a file.", (Throwable) e);
                        }
                        throw th;
                    }
                } catch (IOException e2) {
                    logger.warn("Failed to force.", (Throwable) e2);
                    this.fileChannel.close();
                }
            } catch (IOException e3) {
                logger.warn("Failed to close a file.", (Throwable) e3);
            }
            this.fileChannel = null;
        }
        if (this.isRenamed) {
            return;
        }
        File file = this.file;
        if (file != null && file.exists() && !this.file.delete()) {
            logger.warn("Failed to delete: {}", this.file);
        }
        this.file = null;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public byte[] get() throws IOException {
        File file = this.file;
        if (file == null) {
            return EmptyArrays.EMPTY_BYTES;
        }
        return readFrom(file);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public ByteBuf getByteBuf() throws IOException {
        File file = this.file;
        if (file == null) {
            return Unpooled.EMPTY_BUFFER;
        }
        return Unpooled.wrappedBuffer(readFrom(file));
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x002a, code lost:
    
        r6.fileChannel.close();
        r6.fileChannel = null;
     */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ByteBuf getChunk(int i) throws IOException {
        File file = this.file;
        if (file == null || i == 0) {
            return Unpooled.EMPTY_BUFFER;
        }
        if (this.fileChannel == null) {
            this.fileChannel = new RandomAccessFile(file, "r").getChannel();
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                break;
            }
            try {
                int read = this.fileChannel.read(allocate);
                if (read == -1) {
                    break;
                }
                i2 += read;
            } catch (IOException e) {
                this.fileChannel.close();
                this.fileChannel = null;
                throw e;
            }
        }
        if (i2 == 0) {
            return Unpooled.EMPTY_BUFFER;
        }
        allocate.flip();
        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(allocate);
        wrappedBuffer.readerIndex(0);
        wrappedBuffer.writerIndex(i2);
        return wrappedBuffer;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public String getString() throws IOException {
        return getString(HttpConstants.DEFAULT_CHARSET);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public String getString(Charset charset) throws IOException {
        File file = this.file;
        if (file == null) {
            return "";
        }
        if (charset == null) {
            return new String(readFrom(file), HttpConstants.DEFAULT_CHARSET.name());
        }
        return new String(readFrom(file), charset.name());
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00d4  */
    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean renameTo(File file) throws IOException {
        Throwable th;
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2;
        IOException iOException;
        ObjectUtil.checkNotNull(file, "dest");
        File file2 = this.file;
        if (file2 == null) {
            throw new IOException("No file defined so cannot be renamed");
        }
        if (!file2.renameTo(file)) {
            long j = 8196;
            long j2 = 0;
            IOException iOException2 = null;
            try {
                randomAccessFile = new RandomAccessFile(this.file, "r");
                try {
                    randomAccessFile2 = new RandomAccessFile(file, "rw");
                } catch (IOException e) {
                    e = e;
                    randomAccessFile2 = null;
                } catch (Throwable th2) {
                    th = th2;
                    randomAccessFile2 = null;
                }
            } catch (IOException e2) {
                e = e2;
                randomAccessFile = null;
                randomAccessFile2 = null;
            } catch (Throwable th3) {
                th = th3;
                randomAccessFile = null;
                randomAccessFile2 = null;
            }
            try {
                FileChannel channel = randomAccessFile.getChannel();
                FileChannel channel2 = randomAccessFile2.getChannel();
                while (j2 < this.size) {
                    if (j < this.size - j2) {
                        j = this.size - j2;
                    }
                    j2 += channel.transferTo(j2, j, channel2);
                }
                try {
                    randomAccessFile.close();
                } catch (IOException e3) {
                    iOException2 = e3;
                }
                try {
                    randomAccessFile2.close();
                } catch (IOException e4) {
                    iOException = e4;
                    if (iOException2 == null) {
                        iOException2 = iOException;
                    }
                    logger.warn("Multiple exceptions detected, the following will be suppressed {}", (Throwable) iOException);
                }
            } catch (IOException e5) {
                e = e5;
                iOException2 = e;
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e6) {
                        logger.warn("Multiple exceptions detected, the following will be suppressed {}", (Throwable) e6);
                    }
                }
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                    } catch (IOException e7) {
                        iOException = e7;
                        logger.warn("Multiple exceptions detected, the following will be suppressed {}", (Throwable) iOException);
                        if (iOException2 != null) {
                        }
                    }
                }
                if (iOException2 != null) {
                }
            } catch (Throwable th4) {
                th = th4;
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e8) {
                        iOException2 = e8;
                    }
                }
                if (randomAccessFile2 != null) {
                    try {
                        randomAccessFile2.close();
                        throw th;
                    } catch (IOException e9) {
                        if (iOException2 != null) {
                            logger.warn("Multiple exceptions detected, the following will be suppressed {}", (Throwable) e9);
                            throw th;
                        }
                        throw th;
                    }
                }
                throw th;
            }
            if (iOException2 != null) {
                throw iOException2;
            }
            if (j2 == this.size) {
                if (!this.file.delete()) {
                    logger.warn("Failed to delete: {}", this.file);
                }
                this.file = file;
                this.isRenamed = true;
                return true;
            }
            if (file.delete()) {
                return false;
            }
            logger.warn("Failed to delete: {}", file);
            return false;
        }
        this.file = file;
        this.isRenamed = true;
        return true;
    }

    private static byte[] readFrom(File file) throws IOException {
        long length = file.length();
        if (length > 2147483647L) {
            throw new IllegalArgumentException("File too big to be loaded in memory");
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        byte[] bArr = new byte[(int) length];
        try {
            FileChannel channel = randomAccessFile.getChannel();
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            for (int i = 0; i < length; i += channel.read(wrap)) {
            }
            return bArr;
        } finally {
            randomAccessFile.close();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public File getFile() throws IOException {
        return this.file;
    }
}
