package org.jboss.netty.handler.stream;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.jboss.netty.buffer.ChannelBuffers;

/* loaded from: classes7.dex */
public class ChunkedFile implements ChunkedInput {
    private final int chunkSize;
    private final long endOffset;
    private final RandomAccessFile file;
    private long offset;
    private final long startOffset;

    public ChunkedFile(File file) throws IOException {
        this(file, 8192);
    }

    public ChunkedFile(File file, int i) throws IOException {
        this(new RandomAccessFile(file, "r"), i);
    }

    public ChunkedFile(RandomAccessFile randomAccessFile) throws IOException {
        this(randomAccessFile, 8192);
    }

    public ChunkedFile(RandomAccessFile randomAccessFile, int i) throws IOException {
        this(randomAccessFile, 0L, randomAccessFile.length(), i);
    }

    public ChunkedFile(RandomAccessFile randomAccessFile, long j, long j2, int i) throws IOException {
        if (randomAccessFile == null) {
            throw new NullPointerException("file");
        }
        if (j < 0) {
            throw new IllegalArgumentException("offset: " + j + " (expected: 0 or greater)");
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("length: " + j2 + " (expected: 0 or greater)");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("chunkSize: " + i + " (expected: a positive integer)");
        }
        this.file = randomAccessFile;
        this.startOffset = j;
        this.offset = j;
        this.endOffset = j2 + j;
        this.chunkSize = i;
        randomAccessFile.seek(j);
    }

    public long getStartOffset() {
        return this.startOffset;
    }

    public long getEndOffset() {
        return this.endOffset;
    }

    public long getCurrentOffset() {
        return this.offset;
    }

    @Override // org.jboss.netty.handler.stream.ChunkedInput
    public boolean hasNextChunk() throws Exception {
        return this.offset < this.endOffset && this.file.getChannel().isOpen();
    }

    @Override // org.jboss.netty.handler.stream.ChunkedInput
    public boolean isEndOfInput() throws Exception {
        return !hasNextChunk();
    }

    @Override // org.jboss.netty.handler.stream.ChunkedInput
    public void close() throws Exception {
        this.file.close();
    }

    @Override // org.jboss.netty.handler.stream.ChunkedInput
    public Object nextChunk() throws Exception {
        long j = this.offset;
        long j2 = this.endOffset;
        if (j >= j2) {
            return null;
        }
        int min = (int) Math.min(this.chunkSize, j2 - j);
        byte[] bArr = new byte[min];
        this.file.readFully(bArr);
        this.offset = j + min;
        return ChannelBuffers.wrappedBuffer(bArr);
    }
}
