package org.xerial.snappy;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;
import java.util.zip.Checksum;
import org.xerial.snappy.pool.BufferPool;
import org.xerial.snappy.pool.DefaultPoolFactory;

/* loaded from: classes9.dex */
public final class SnappyFramedInputStream extends InputStream implements ReadableByteChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private byte[] buffer;
    private final BufferPool bufferPool;
    private boolean closed;
    private final Checksum crc32;
    private boolean eof;
    private final ByteBuffer frameHeader;
    private ByteBuffer input;
    private int position;
    private final ReadableByteChannel rbc;
    private ByteBuffer uncompressedDirect;
    private int valid;
    private final boolean verifyChecksums;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public enum FrameAction {
        RAW,
        SKIP,
        UNCOMPRESS
    }

    public SnappyFramedInputStream(InputStream inputStream) throws IOException {
        this(inputStream, true, DefaultPoolFactory.getDefaultPool());
    }

    public SnappyFramedInputStream(InputStream inputStream, BufferPool bufferPool) throws IOException {
        this(inputStream, true, bufferPool);
    }

    public SnappyFramedInputStream(InputStream inputStream, boolean z) throws IOException {
        this(inputStream, z, DefaultPoolFactory.getDefaultPool());
    }

    public SnappyFramedInputStream(InputStream inputStream, boolean z, BufferPool bufferPool) throws IOException {
        this(Channels.newChannel(inputStream), z, bufferPool);
    }

    public SnappyFramedInputStream(ReadableByteChannel readableByteChannel, BufferPool bufferPool) throws IOException {
        this(readableByteChannel, true, bufferPool);
    }

    public SnappyFramedInputStream(ReadableByteChannel readableByteChannel) throws IOException {
        this(readableByteChannel, true);
    }

    public SnappyFramedInputStream(ReadableByteChannel readableByteChannel, boolean z) throws IOException {
        this(readableByteChannel, z, DefaultPoolFactory.getDefaultPool());
    }

    public SnappyFramedInputStream(ReadableByteChannel readableByteChannel, boolean z, BufferPool bufferPool) throws IOException {
        this.crc32 = SnappyFramed.getCRC32C();
        if (readableByteChannel == null) {
            throw new NullPointerException("in is null");
        }
        if (bufferPool == null) {
            throw new NullPointerException("bufferPool is null");
        }
        this.bufferPool = bufferPool;
        this.rbc = readableByteChannel;
        this.verifyChecksums = z;
        allocateBuffersBasedOnSize(65541);
        this.frameHeader = ByteBuffer.allocate(4);
        byte[] bArr = SnappyFramed.HEADER_BYTES;
        byte[] bArr2 = new byte[bArr.length];
        if (SnappyFramed.readBytes(readableByteChannel, ByteBuffer.wrap(bArr2)) < bArr.length) {
            throw new EOFException("encountered EOF while reading stream header");
        }
        if (!Arrays.equals(bArr, bArr2)) {
            throw new IOException("invalid stream header");
        }
    }

    private void allocateBuffersBasedOnSize(int i) {
        ByteBuffer byteBuffer = this.input;
        if (byteBuffer != null) {
            this.bufferPool.releaseDirect(byteBuffer);
        }
        ByteBuffer byteBuffer2 = this.uncompressedDirect;
        if (byteBuffer2 != null) {
            this.bufferPool.releaseDirect(byteBuffer2);
        }
        byte[] bArr = this.buffer;
        if (bArr != null) {
            this.bufferPool.releaseArray(bArr);
        }
        this.input = this.bufferPool.allocateDirect(i);
        int maxCompressedLength = Snappy.maxCompressedLength(i);
        this.uncompressedDirect = this.bufferPool.allocateDirect(maxCompressedLength);
        this.buffer = this.bufferPool.allocateArray(maxCompressedLength);
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.closed || !ensureBuffer()) {
            return -1;
        }
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (bArr == null) {
            throw new IllegalArgumentException("output is null");
        }
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            throw new IllegalArgumentException("invalid offset [" + i + "] and length [" + i2 + ']');
        }
        if (this.closed) {
            throw new ClosedChannelException();
        }
        if (i2 == 0) {
            return 0;
        }
        if (!ensureBuffer()) {
            return -1;
        }
        int min = Math.min(i2, available());
        System.arraycopy(this.buffer, this.position, bArr, i, min);
        this.position += min;
        return min;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        if (this.closed) {
            return 0;
        }
        return this.valid - this.position;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.closed;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("dst is null");
        }
        if (this.closed) {
            throw new ClosedChannelException();
        }
        if (byteBuffer.remaining() == 0) {
            return 0;
        }
        if (!ensureBuffer()) {
            return -1;
        }
        int min = Math.min(byteBuffer.remaining(), available());
        byteBuffer.put(this.buffer, this.position, min);
        this.position += min;
        return min;
    }

    @Override // java.io.InputStream
    public long transferTo(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("os is null");
        }
        if (this.closed) {
            throw new ClosedChannelException();
        }
        long j = 0;
        while (ensureBuffer()) {
            int available = available();
            outputStream.write(this.buffer, this.position, available);
            this.position += available;
            j += available;
        }
        return j;
    }

    public long transferTo(WritableByteChannel writableByteChannel) throws IOException {
        if (writableByteChannel == null) {
            throw new IllegalArgumentException("wbc is null");
        }
        if (this.closed) {
            throw new ClosedChannelException();
        }
        ByteBuffer wrap = ByteBuffer.wrap(this.buffer);
        long j = 0;
        while (ensureBuffer()) {
            wrap.clear();
            wrap.position(this.position);
            wrap.limit(this.position + available());
            writableByteChannel.write(wrap);
            int position = wrap.position();
            int i = this.position;
            int i2 = position - i;
            this.position = i + i2;
            j += i2;
        }
        return j;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public void close() throws IOException {
        boolean z;
        try {
            this.rbc.close();
            if (z) {
                return;
            }
        } finally {
            if (!this.closed) {
                this.closed = true;
                ByteBuffer byteBuffer = this.input;
                if (byteBuffer != null) {
                    this.bufferPool.releaseDirect(byteBuffer);
                    this.input = null;
                }
                ByteBuffer byteBuffer2 = this.uncompressedDirect;
                if (byteBuffer2 != null) {
                    this.bufferPool.releaseDirect(byteBuffer2);
                    this.uncompressedDirect = null;
                }
                byte[] bArr = this.buffer;
                if (bArr != null) {
                    this.bufferPool.releaseArray(bArr);
                    this.buffer = null;
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    public static final class FrameMetaData {
        final FrameAction frameAction;
        final int length;

        public FrameMetaData(FrameAction frameAction, int i) {
            this.frameAction = frameAction;
            this.length = i;
        }
    }

    /* loaded from: classes9.dex */
    public static final class FrameData {
        final int checkSum;
        final int offset;

        public FrameData(int i, int i2) {
            this.checkSum = i;
            this.offset = i2;
        }
    }

    private boolean ensureBuffer() throws IOException {
        if (available() > 0) {
            return true;
        }
        if (this.eof) {
            return false;
        }
        if (!readBlockHeader()) {
            this.eof = true;
            return false;
        }
        FrameMetaData frameMetaData = getFrameMetaData(this.frameHeader);
        if (FrameAction.SKIP == frameMetaData.frameAction) {
            SnappyFramed.skip(this.rbc, frameMetaData.length, ByteBuffer.wrap(this.buffer));
            return ensureBuffer();
        }
        if (frameMetaData.length > this.input.capacity()) {
            allocateBuffersBasedOnSize(frameMetaData.length);
        }
        this.input.clear();
        this.input.limit(frameMetaData.length);
        if (SnappyFramed.readBytes(this.rbc, this.input) != frameMetaData.length) {
            throw new EOFException("unexpectd EOF when reading frame");
        }
        this.input.flip();
        FrameData frameData = getFrameData(this.input);
        if (FrameAction.UNCOMPRESS == frameMetaData.frameAction) {
            this.input.position(frameData.offset);
            int uncompressedLength = Snappy.uncompressedLength(this.input);
            if (uncompressedLength > this.uncompressedDirect.capacity()) {
                this.bufferPool.releaseDirect(this.uncompressedDirect);
                this.bufferPool.releaseArray(this.buffer);
                this.uncompressedDirect = this.bufferPool.allocateDirect(uncompressedLength);
                this.buffer = this.bufferPool.allocateArray(uncompressedLength);
            }
            this.uncompressedDirect.clear();
            this.valid = Snappy.uncompress(this.input, this.uncompressedDirect);
            this.uncompressedDirect.get(this.buffer, 0, this.valid);
            this.position = 0;
        } else {
            this.input.position(frameData.offset);
            this.position = 0;
            this.valid = this.input.remaining();
            ByteBuffer byteBuffer = this.input;
            byteBuffer.get(this.buffer, 0, byteBuffer.remaining());
        }
        if (this.verifyChecksums) {
            Checksum checksum = this.crc32;
            byte[] bArr = this.buffer;
            int i = this.position;
            if (frameData.checkSum != SnappyFramed.maskedCrc32c(checksum, bArr, i, this.valid - i)) {
                throw new IOException("Corrupt input: invalid checksum");
            }
        }
        return true;
    }

    private boolean readBlockHeader() throws IOException {
        this.frameHeader.clear();
        int readBytes = SnappyFramed.readBytes(this.rbc, this.frameHeader);
        if (readBytes == -1) {
            return false;
        }
        if (readBytes < this.frameHeader.capacity()) {
            throw new EOFException("encountered EOF while reading block header");
        }
        this.frameHeader.flip();
        return true;
    }

    private FrameMetaData getFrameMetaData(ByteBuffer byteBuffer) throws IOException {
        FrameAction frameAction;
        byte[] array = byteBuffer.array();
        int i = (array[1] & 255) | ((array[2] & 255) << 8) | ((array[3] & 255) << 16);
        int i2 = array[0] & 255;
        int i3 = 5;
        if (i2 == 0) {
            frameAction = FrameAction.UNCOMPRESS;
        } else if (i2 == 1) {
            frameAction = FrameAction.RAW;
        } else if (i2 != 255) {
            if (i2 <= 127) {
                throw new IOException("unsupported unskippable chunk: " + Integer.toHexString(i2));
            }
            frameAction = FrameAction.SKIP;
            i3 = 0;
        } else {
            if (i != 6) {
                throw new IOException("stream identifier chunk with invalid length: " + i);
            }
            frameAction = FrameAction.SKIP;
            i3 = 6;
        }
        if (i < i3) {
            throw new IOException("invalid length: " + i + " for chunk flag: " + Integer.toHexString(i2));
        }
        return new FrameMetaData(frameAction, i);
    }

    private FrameData getFrameData(ByteBuffer byteBuffer) throws IOException {
        return new FrameData(getCrc32c(byteBuffer), 4);
    }

    private int getCrc32c(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        return (byteBuffer.get(position) & 255) | ((byteBuffer.get(position + 3) & 255) << 24) | ((byteBuffer.get(position + 2) & 255) << 16) | ((byteBuffer.get(position + 1) & 255) << 8);
    }
}
