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
public abstract class AbstractDiskHttpData extends AbstractHttpData {
    protected File file;
    private FileChannel fileChannel;
    private boolean isRenamed;

    protected abstract boolean deleteOnExit();

    protected abstract String getBaseDirectory();

    protected abstract String getDiskFilename();

    protected abstract String getPostfix();

    protected abstract String getPrefix();

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public boolean isInMemory() {
        return false;
    }

    public AbstractDiskHttpData(String str, Charset charset, long j) {
        super(str, charset, j);
    }

    private File tempFile() throws IOException {
        String postfix;
        File createTempFile;
        String diskFilename = getDiskFilename();
        if (diskFilename != null) {
            postfix = "_" + diskFilename;
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

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void setContent(ChannelBuffer channelBuffer) throws IOException {
        if (channelBuffer == null) {
            throw new NullPointerException(SpeechEvent.KEY_EVENT_TTS_BUFFER);
        }
        this.size = channelBuffer.readableBytes();
        if (this.definedSize > 0 && this.definedSize < this.size) {
            throw new IOException("Out of size: " + this.size + " > " + this.definedSize);
        }
        if (this.file == null) {
            this.file = tempFile();
        }
        if (channelBuffer.readableBytes() == 0) {
            this.file.createNewFile();
            return;
        }
        FileChannel channel = new FileOutputStream(this.file).getChannel();
        ByteBuffer byteBuffer = channelBuffer.toByteBuffer();
        int i = 0;
        while (i < this.size) {
            i += channel.write(byteBuffer);
        }
        channelBuffer.readerIndex(channelBuffer.readerIndex() + i);
        channel.force(false);
        channel.close();
        this.completed = true;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void addContent(ChannelBuffer channelBuffer, boolean z) throws IOException {
        if (channelBuffer != null) {
            int readableBytes = channelBuffer.readableBytes();
            if (this.definedSize > 0) {
                long j = readableBytes;
                if (this.definedSize < this.size + j) {
                    throw new IOException("Out of size: " + (this.size + j) + " > " + this.definedSize);
                }
            }
            ByteBuffer byteBuffer = channelBuffer.toByteBuffer();
            if (this.file == null) {
                this.file = tempFile();
            }
            if (this.fileChannel == null) {
                this.fileChannel = new FileOutputStream(this.file).getChannel();
            }
            int i = 0;
            while (i < readableBytes) {
                i += this.fileChannel.write(byteBuffer);
            }
            this.size += readableBytes;
            channelBuffer.readerIndex(channelBuffer.readerIndex() + i);
        }
        if (!z) {
            if (channelBuffer == null) {
                throw new NullPointerException(SpeechEvent.KEY_EVENT_TTS_BUFFER);
            }
            return;
        }
        if (this.file == null) {
            this.file = tempFile();
        }
        if (this.fileChannel == null) {
            this.fileChannel = new FileOutputStream(this.file).getChannel();
        }
        this.fileChannel.force(false);
        this.fileChannel.close();
        this.fileChannel = null;
        this.completed = true;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void setContent(File file) throws IOException {
        if (this.file != null) {
            delete();
        }
        this.file = file;
        this.size = file.length();
        this.isRenamed = true;
        this.completed = true;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void setContent(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new NullPointerException("inputStream");
        }
        if (this.file != null) {
            delete();
        }
        this.file = tempFile();
        FileChannel channel = new FileOutputStream(this.file).getChannel();
        byte[] bArr = new byte[16384];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        int read = inputStream.read(bArr);
        int i = 0;
        while (read > 0) {
            wrap.position(read).flip();
            i += channel.write(wrap);
            read = inputStream.read(bArr);
        }
        channel.force(false);
        channel.close();
        this.size = i;
        if (this.definedSize > 0 && this.definedSize < this.size) {
            this.file.delete();
            this.file = null;
            throw new IOException("Out of size: " + this.size + " > " + this.definedSize);
        }
        this.isRenamed = true;
        this.completed = true;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void delete() {
        File file;
        if (this.isRenamed || (file = this.file) == null) {
            return;
        }
        file.delete();
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public byte[] get() throws IOException {
        File file = this.file;
        return file == null ? new byte[0] : readFrom(file);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public ChannelBuffer getChannelBuffer() throws IOException {
        File file = this.file;
        if (file == null) {
            return ChannelBuffers.EMPTY_BUFFER;
        }
        return ChannelBuffers.wrappedBuffer(readFrom(file));
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public ChannelBuffer getChunk(int i) throws IOException {
        File file = this.file;
        if (file == null || i == 0) {
            return ChannelBuffers.EMPTY_BUFFER;
        }
        if (this.fileChannel == null) {
            this.fileChannel = new FileInputStream(file).getChannel();
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                break;
            }
            int read = this.fileChannel.read(allocate);
            if (read == -1) {
                this.fileChannel.close();
                this.fileChannel = null;
                break;
            }
            i2 += read;
        }
        if (i2 == 0) {
            return ChannelBuffers.EMPTY_BUFFER;
        }
        allocate.flip();
        ChannelBuffer wrappedBuffer = ChannelBuffers.wrappedBuffer(allocate);
        wrappedBuffer.readerIndex(0);
        wrappedBuffer.writerIndex(i2);
        return wrappedBuffer;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public String getString() throws IOException {
        return getString(HttpConstants.DEFAULT_CHARSET);
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
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

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public boolean renameTo(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("dest");
        }
        if (!this.file.renameTo(file)) {
            FileInputStream fileInputStream = new FileInputStream(this.file);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            FileChannel channel = fileInputStream.getChannel();
            FileChannel channel2 = fileOutputStream.getChannel();
            int i = 8196;
            long j = 0;
            while (j < this.size) {
                if (i < this.size - j) {
                    i = (int) (this.size - j);
                }
                int i2 = i;
                j += channel.transferTo(j, i2, channel2);
                i = i2;
            }
            channel.close();
            channel2.close();
            if (j == this.size) {
                this.file.delete();
                this.file = file;
                this.isRenamed = true;
                return true;
            }
            file.delete();
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
        FileChannel channel = new FileInputStream(file).getChannel();
        byte[] bArr = new byte[(int) length];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        for (int i = 0; i < length; i += channel.read(wrap)) {
        }
        channel.close();
        return bArr;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public File getFile() throws IOException {
        return this.file;
    }
}
