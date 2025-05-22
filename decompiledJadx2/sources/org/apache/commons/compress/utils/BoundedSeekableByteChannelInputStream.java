package org.apache.commons.compress.utils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;

/* loaded from: classes9.dex */
public class BoundedSeekableByteChannelInputStream extends BoundedArchiveInputStream {
    private final SeekableByteChannel channel;

    public BoundedSeekableByteChannelInputStream(long j, long j2, SeekableByteChannel seekableByteChannel) {
        super(j, j2);
        this.channel = seekableByteChannel;
    }

    @Override // org.apache.commons.compress.utils.BoundedArchiveInputStream
    protected int read(long j, ByteBuffer byteBuffer) throws IOException {
        int read;
        synchronized (this.channel) {
            this.channel.position(j);
            read = this.channel.read(byteBuffer);
        }
        byteBuffer.flip();
        return read;
    }
}
