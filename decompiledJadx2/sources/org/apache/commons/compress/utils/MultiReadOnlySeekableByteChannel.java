package org.apache.commons.compress.utils;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes9.dex */
public class MultiReadOnlySeekableByteChannel implements SeekableByteChannel {
    private final List<SeekableByteChannel> channels;
    private int currentChannelIdx;
    private long globalPosition;

    public MultiReadOnlySeekableByteChannel(List<SeekableByteChannel> list) {
        this.channels = Collections.unmodifiableList(new ArrayList((Collection) Objects.requireNonNull(list, "channels must not be null")));
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer byteBuffer) throws IOException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        int i = 0;
        if (!byteBuffer.hasRemaining()) {
            return 0;
        }
        while (byteBuffer.hasRemaining() && this.currentChannelIdx < this.channels.size()) {
            SeekableByteChannel seekableByteChannel = this.channels.get(this.currentChannelIdx);
            int read = seekableByteChannel.read(byteBuffer);
            if (read == -1) {
                this.currentChannelIdx++;
            } else {
                if (seekableByteChannel.position() >= seekableByteChannel.size()) {
                    this.currentChannelIdx++;
                }
                i += read;
            }
        }
        if (i <= 0) {
            return -1;
        }
        this.globalPosition += i;
        return i;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Iterator<SeekableByteChannel> it = this.channels.iterator();
        IOException iOException = null;
        while (it.hasNext()) {
            try {
                it.next().close();
            } catch (IOException e) {
                if (iOException == null) {
                    iOException = e;
                }
            }
        }
        if (iOException != null) {
            throw new IOException("failed to close wrapped channel", iOException);
        }
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        Iterator<SeekableByteChannel> it = this.channels.iterator();
        while (it.hasNext()) {
            if (!it.next().isOpen()) {
                return false;
            }
        }
        return true;
    }

    @Override // java.nio.channels.SeekableByteChannel
    public long position() {
        return this.globalPosition;
    }

    public synchronized SeekableByteChannel position(long j, long j2) throws IOException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        for (int i = 0; i < j; i++) {
            j2 += this.channels.get(i).size();
        }
        return position(j2);
    }

    @Override // java.nio.channels.SeekableByteChannel
    public long size() throws IOException {
        if (!isOpen()) {
            throw new ClosedChannelException();
        }
        long j = 0;
        Iterator<SeekableByteChannel> it = this.channels.iterator();
        while (it.hasNext()) {
            j += it.next().size();
        }
        return j;
    }

    @Override // java.nio.channels.SeekableByteChannel
    public SeekableByteChannel truncate(long j) {
        throw new NonWritableChannelException();
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) {
        throw new NonWritableChannelException();
    }

    @Override // java.nio.channels.SeekableByteChannel
    public synchronized SeekableByteChannel position(long j) throws IOException {
        try {
            if (j < 0) {
                throw new IOException("Negative position: " + j);
            }
            if (!isOpen()) {
                throw new ClosedChannelException();
            }
            this.globalPosition = j;
            int i = 0;
            while (i < this.channels.size()) {
                SeekableByteChannel seekableByteChannel = this.channels.get(i);
                long size = seekableByteChannel.size();
                long j2 = -1;
                if (j == -1) {
                    j2 = j;
                    j = 0;
                } else if (j <= size) {
                    this.currentChannelIdx = i;
                } else {
                    j2 = j - size;
                    j = size;
                }
                seekableByteChannel.position(j);
                i++;
                j = j2;
            }
        } catch (Throwable th) {
            throw th;
        }
        return this;
    }

    public static SeekableByteChannel forSeekableByteChannels(SeekableByteChannel... seekableByteChannelArr) {
        if (((SeekableByteChannel[]) Objects.requireNonNull(seekableByteChannelArr, "channels must not be null")).length == 1) {
            return seekableByteChannelArr[0];
        }
        return new MultiReadOnlySeekableByteChannel(Arrays.asList(seekableByteChannelArr));
    }

    public static SeekableByteChannel forFiles(File... fileArr) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (File file : (File[]) Objects.requireNonNull(fileArr, "files must not be null")) {
            arrayList.add(Files.newByteChannel(file.toPath(), StandardOpenOption.READ));
        }
        if (arrayList.size() == 1) {
            return (SeekableByteChannel) arrayList.get(0);
        }
        return new MultiReadOnlySeekableByteChannel(arrayList);
    }
}
