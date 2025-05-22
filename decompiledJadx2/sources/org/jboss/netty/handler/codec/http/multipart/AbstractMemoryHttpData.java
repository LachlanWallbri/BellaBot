package org.jboss.netty.handler.codec.http.multipart;

import com.iflytek.cloud.SpeechEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.handler.codec.http.HttpConstants;

/* loaded from: classes7.dex */
public abstract class AbstractMemoryHttpData extends AbstractHttpData {
    private ChannelBuffer channelBuffer;
    private int chunkPosition;
    protected boolean isRenamed;

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void delete() {
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public boolean isInMemory() {
        return true;
    }

    public AbstractMemoryHttpData(String str, Charset charset, long j) {
        super(str, charset, j);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void setContent(ChannelBuffer channelBuffer) throws IOException {
        if (channelBuffer == null) {
            throw new NullPointerException(SpeechEvent.KEY_EVENT_TTS_BUFFER);
        }
        long readableBytes = channelBuffer.readableBytes();
        if (this.definedSize > 0 && this.definedSize < readableBytes) {
            throw new IOException("Out of size: " + readableBytes + " > " + this.definedSize);
        }
        this.channelBuffer = channelBuffer;
        this.size = readableBytes;
        this.completed = true;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void setContent(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new NullPointerException("inputStream");
        }
        ChannelBuffer dynamicBuffer = ChannelBuffers.dynamicBuffer();
        byte[] bArr = new byte[16384];
        int read = inputStream.read(bArr);
        int i = 0;
        while (read > 0) {
            dynamicBuffer.writeBytes(bArr);
            i += read;
            read = inputStream.read(bArr);
        }
        this.size = i;
        if (this.definedSize > 0 && this.definedSize < this.size) {
            throw new IOException("Out of size: " + this.size + " > " + this.definedSize);
        }
        this.channelBuffer = dynamicBuffer;
        this.completed = true;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void addContent(ChannelBuffer channelBuffer, boolean z) throws IOException {
        if (channelBuffer != null) {
            long readableBytes = channelBuffer.readableBytes();
            if (this.definedSize > 0 && this.definedSize < this.size + readableBytes) {
                throw new IOException("Out of size: " + (this.size + readableBytes) + " > " + this.definedSize);
            }
            this.size += readableBytes;
            ChannelBuffer channelBuffer2 = this.channelBuffer;
            if (channelBuffer2 == null) {
                this.channelBuffer = channelBuffer;
            } else {
                this.channelBuffer = ChannelBuffers.wrappedBuffer(channelBuffer2, channelBuffer);
            }
        }
        if (z) {
            this.completed = true;
        } else if (channelBuffer == null) {
            throw new NullPointerException(SpeechEvent.KEY_EVENT_TTS_BUFFER);
        }
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void setContent(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("file");
        }
        long length = file.length();
        if (length > 2147483647L) {
            throw new IllegalArgumentException("File too big to be loaded in memory");
        }
        FileChannel channel = new FileInputStream(file).getChannel();
        ByteBuffer wrap = ByteBuffer.wrap(new byte[(int) length]);
        for (int i = 0; i < length; i += channel.read(wrap)) {
        }
        channel.close();
        wrap.flip();
        this.channelBuffer = ChannelBuffers.wrappedBuffer(wrap);
        this.size = length;
        this.completed = true;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public byte[] get() {
        ChannelBuffer channelBuffer = this.channelBuffer;
        if (channelBuffer == null) {
            return new byte[0];
        }
        byte[] bArr = new byte[channelBuffer.readableBytes()];
        ChannelBuffer channelBuffer2 = this.channelBuffer;
        channelBuffer2.getBytes(channelBuffer2.readerIndex(), bArr);
        return bArr;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public String getString() {
        return getString(HttpConstants.DEFAULT_CHARSET);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public String getString(Charset charset) {
        ChannelBuffer channelBuffer = this.channelBuffer;
        if (channelBuffer == null) {
            return "";
        }
        if (charset == null) {
            return getString(HttpConstants.DEFAULT_CHARSET);
        }
        return channelBuffer.toString(charset);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public ChannelBuffer getChannelBuffer() {
        return this.channelBuffer;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public ChannelBuffer getChunk(int i) throws IOException {
        ChannelBuffer channelBuffer = this.channelBuffer;
        if (channelBuffer == null || i == 0 || channelBuffer.readableBytes() == 0) {
            this.chunkPosition = 0;
            return ChannelBuffers.EMPTY_BUFFER;
        }
        int readableBytes = this.channelBuffer.readableBytes() - this.chunkPosition;
        if (readableBytes == 0) {
            this.chunkPosition = 0;
            return ChannelBuffers.EMPTY_BUFFER;
        }
        if (readableBytes < i) {
            i = readableBytes;
        }
        ChannelBuffer slice = this.channelBuffer.slice(this.chunkPosition, i);
        this.chunkPosition += i;
        return slice;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public boolean renameTo(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("dest");
        }
        ChannelBuffer channelBuffer = this.channelBuffer;
        if (channelBuffer == null) {
            file.createNewFile();
            this.isRenamed = true;
            return true;
        }
        int readableBytes = channelBuffer.readableBytes();
        FileChannel channel = new FileOutputStream(file).getChannel();
        ByteBuffer byteBuffer = this.channelBuffer.toByteBuffer();
        int i = 0;
        while (i < readableBytes) {
            i += channel.write(byteBuffer);
        }
        channel.force(false);
        channel.close();
        this.isRenamed = true;
        return i == readableBytes;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public File getFile() throws IOException {
        throw new IOException("Not represented by a file");
    }
}
