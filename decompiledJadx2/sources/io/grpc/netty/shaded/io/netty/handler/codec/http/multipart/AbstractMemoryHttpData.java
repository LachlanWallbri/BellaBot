package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

import com.iflytek.cloud.SpeechEvent;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpConstants;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
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
public abstract class AbstractMemoryHttpData extends AbstractHttpData {
    private ByteBuf byteBuf;
    private int chunkPosition;

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public boolean isInMemory() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractMemoryHttpData(String str, Charset charset, long j) {
        super(str, charset, j);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void setContent(ByteBuf byteBuf) throws IOException {
        ObjectUtil.checkNotNull(byteBuf, SpeechEvent.KEY_EVENT_TTS_BUFFER);
        long readableBytes = byteBuf.readableBytes();
        checkSize(readableBytes);
        if (this.definedSize > 0 && this.definedSize < readableBytes) {
            throw new IOException("Out of size: " + readableBytes + " > " + this.definedSize);
        }
        ByteBuf byteBuf2 = this.byteBuf;
        if (byteBuf2 != null) {
            byteBuf2.release();
        }
        this.byteBuf = byteBuf;
        this.size = readableBytes;
        setCompleted();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void setContent(InputStream inputStream) throws IOException {
        ObjectUtil.checkNotNull(inputStream, "inputStream");
        byte[] bArr = new byte[16384];
        ByteBuf buffer = Unpooled.buffer();
        try {
            int read = inputStream.read(bArr);
            int i = 0;
            while (read > 0) {
                buffer.writeBytes(bArr, 0, read);
                i += read;
                checkSize(i);
                read = inputStream.read(bArr);
            }
            this.size = i;
            if (this.definedSize > 0 && this.definedSize < this.size) {
                buffer.release();
                throw new IOException("Out of size: " + this.size + " > " + this.definedSize);
            }
            ByteBuf byteBuf = this.byteBuf;
            if (byteBuf != null) {
                byteBuf.release();
            }
            this.byteBuf = buffer;
            setCompleted();
        } catch (IOException e) {
            buffer.release();
            throw e;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void addContent(ByteBuf byteBuf, boolean z) throws IOException {
        if (byteBuf != null) {
            long readableBytes = byteBuf.readableBytes();
            checkSize(this.size + readableBytes);
            if (this.definedSize > 0 && this.definedSize < this.size + readableBytes) {
                throw new IOException("Out of size: " + (this.size + readableBytes) + " > " + this.definedSize);
            }
            this.size += readableBytes;
            ByteBuf byteBuf2 = this.byteBuf;
            if (byteBuf2 == null) {
                this.byteBuf = byteBuf;
            } else if (byteBuf2 instanceof CompositeByteBuf) {
                ((CompositeByteBuf) byteBuf2).addComponent(true, byteBuf);
            } else {
                CompositeByteBuf compositeBuffer = Unpooled.compositeBuffer(Integer.MAX_VALUE);
                compositeBuffer.addComponents(true, this.byteBuf, byteBuf);
                this.byteBuf = compositeBuffer;
            }
        }
        if (z) {
            setCompleted();
        } else {
            ObjectUtil.checkNotNull(byteBuf, SpeechEvent.KEY_EVENT_TTS_BUFFER);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void setContent(File file) throws IOException {
        ObjectUtil.checkNotNull(file, "file");
        long length = file.length();
        if (length > 2147483647L) {
            throw new IllegalArgumentException("File too big to be loaded in memory");
        }
        checkSize(length);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        try {
            FileChannel channel = randomAccessFile.getChannel();
            try {
                ByteBuffer wrap = ByteBuffer.wrap(new byte[(int) length]);
                for (int i = 0; i < length; i += channel.read(wrap)) {
                }
                randomAccessFile.close();
                wrap.flip();
                ByteBuf byteBuf = this.byteBuf;
                if (byteBuf != null) {
                    byteBuf.release();
                }
                this.byteBuf = Unpooled.wrappedBuffer(Integer.MAX_VALUE, wrap);
                this.size = length;
                setCompleted();
            } finally {
                channel.close();
            }
        } catch (Throwable th) {
            randomAccessFile.close();
            throw th;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public void delete() {
        ByteBuf byteBuf = this.byteBuf;
        if (byteBuf != null) {
            byteBuf.release();
            this.byteBuf = null;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public byte[] get() {
        ByteBuf byteBuf = this.byteBuf;
        if (byteBuf == null) {
            return Unpooled.EMPTY_BUFFER.array();
        }
        byte[] bArr = new byte[byteBuf.readableBytes()];
        ByteBuf byteBuf2 = this.byteBuf;
        byteBuf2.getBytes(byteBuf2.readerIndex(), bArr);
        return bArr;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public String getString() {
        return getString(HttpConstants.DEFAULT_CHARSET);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public String getString(Charset charset) {
        if (this.byteBuf == null) {
            return "";
        }
        if (charset == null) {
            charset = HttpConstants.DEFAULT_CHARSET;
        }
        return this.byteBuf.toString(charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public ByteBuf getByteBuf() {
        return this.byteBuf;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public ByteBuf getChunk(int i) throws IOException {
        ByteBuf byteBuf = this.byteBuf;
        if (byteBuf == null || i == 0 || byteBuf.readableBytes() == 0) {
            this.chunkPosition = 0;
            return Unpooled.EMPTY_BUFFER;
        }
        int readableBytes = this.byteBuf.readableBytes() - this.chunkPosition;
        if (readableBytes == 0) {
            this.chunkPosition = 0;
            return Unpooled.EMPTY_BUFFER;
        }
        if (readableBytes < i) {
            i = readableBytes;
        }
        ByteBuf retainedSlice = this.byteBuf.retainedSlice(this.chunkPosition, i);
        this.chunkPosition += i;
        return retainedSlice;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public boolean renameTo(File file) throws IOException {
        int i;
        ObjectUtil.checkNotNull(file, "dest");
        ByteBuf byteBuf = this.byteBuf;
        if (byteBuf == null) {
            if (file.createNewFile()) {
                return true;
            }
            throw new IOException("file exists already: " + file);
        }
        int readableBytes = byteBuf.readableBytes();
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        try {
            FileChannel channel = randomAccessFile.getChannel();
            try {
                if (this.byteBuf.nioBufferCount() == 1) {
                    ByteBuffer nioBuffer = this.byteBuf.nioBuffer();
                    i = 0;
                    while (i < readableBytes) {
                        i += channel.write(nioBuffer);
                    }
                } else {
                    ByteBuffer[] nioBuffers = this.byteBuf.nioBuffers();
                    i = 0;
                    while (i < readableBytes) {
                        i = (int) (i + channel.write(nioBuffers));
                    }
                }
                channel.force(false);
                return i == readableBytes;
            } finally {
                channel.close();
            }
        } finally {
            randomAccessFile.close();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.HttpData
    public File getFile() throws IOException {
        throw new IOException("Not represented by a file");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractHttpData, io.grpc.netty.shaded.io.netty.util.AbstractReferenceCounted, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public HttpData touch() {
        return touch((Object) null);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.multipart.AbstractHttpData, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public HttpData touch(Object obj) {
        ByteBuf byteBuf = this.byteBuf;
        if (byteBuf != null) {
            byteBuf.touch(obj);
        }
        return this;
    }
}
