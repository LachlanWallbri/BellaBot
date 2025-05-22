package org.jboss.netty.buffer;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/* loaded from: classes7.dex */
public abstract class HeapChannelBuffer extends AbstractChannelBuffer {
    protected final byte[] array;

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int arrayOffset() {
        return 0;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public boolean hasArray() {
        return true;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public boolean isDirect() {
        return false;
    }

    public HeapChannelBuffer(int i) {
        this(new byte[i], 0, 0);
    }

    public HeapChannelBuffer(byte[] bArr) {
        this(bArr, 0, bArr.length);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HeapChannelBuffer(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException(TmpConstant.TYPE_VALUE_ARRAY);
        }
        this.array = bArr;
        setIndex(i, i2);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int capacity() {
        return this.array.length;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public byte[] array() {
        return this.array;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public byte getByte(int i) {
        return this.array[i];
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, ChannelBuffer channelBuffer, int i2, int i3) {
        if (channelBuffer instanceof HeapChannelBuffer) {
            getBytes(i, ((HeapChannelBuffer) channelBuffer).array, i2, i3);
        } else {
            channelBuffer.setBytes(i2, this.array, i, i3);
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, byte[] bArr, int i2, int i3) {
        System.arraycopy(this.array, i, bArr, i2, i3);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, ByteBuffer byteBuffer) {
        byteBuffer.put(this.array, i, Math.min(capacity() - i, byteBuffer.remaining()));
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, OutputStream outputStream, int i2) throws IOException {
        outputStream.write(this.array, i, i2);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int getBytes(int i, GatheringByteChannel gatheringByteChannel, int i2) throws IOException {
        return gatheringByteChannel.write(ByteBuffer.wrap(this.array, i, i2));
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setByte(int i, int i2) {
        this.array[i] = (byte) i2;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setBytes(int i, ChannelBuffer channelBuffer, int i2, int i3) {
        if (channelBuffer instanceof HeapChannelBuffer) {
            setBytes(i, ((HeapChannelBuffer) channelBuffer).array, i2, i3);
        } else {
            channelBuffer.getBytes(i2, this.array, i, i3);
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setBytes(int i, byte[] bArr, int i2, int i3) {
        System.arraycopy(bArr, i2, this.array, i, i3);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setBytes(int i, ByteBuffer byteBuffer) {
        byteBuffer.get(this.array, i, byteBuffer.remaining());
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0012, code lost:
    
        return r0;
     */
    @Override // org.jboss.netty.buffer.ChannelBuffer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int setBytes(int i, InputStream inputStream, int i2) throws IOException {
        int i3 = 0;
        while (true) {
            int read = inputStream.read(this.array, i, i2);
            if (read >= 0) {
                i3 += read;
                i += read;
                i2 -= read;
                if (i2 <= 0) {
                    break;
                }
            } else if (i3 == 0) {
                return -1;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0019, code lost:
    
        return r0;
     */
    @Override // org.jboss.netty.buffer.ChannelBuffer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int setBytes(int i, ScatteringByteChannel scatteringByteChannel, int i2) throws IOException {
        int i3;
        ByteBuffer wrap = ByteBuffer.wrap(this.array, i, i2);
        int i4 = 0;
        while (true) {
            try {
                i3 = scatteringByteChannel.read(wrap);
            } catch (ClosedChannelException unused) {
                i3 = -1;
            }
            if (i3 >= 0) {
                if (i3 == 0 || (i4 = i4 + i3) >= i2) {
                    break;
                }
            } else if (i4 == 0) {
                return -1;
            }
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBuffer slice(int i, int i2) {
        if (i != 0) {
            if (i2 == 0) {
                return ChannelBuffers.EMPTY_BUFFER;
            }
            return new SlicedChannelBuffer(this, i, i2);
        }
        if (i2 == 0) {
            return ChannelBuffers.EMPTY_BUFFER;
        }
        if (i2 == this.array.length) {
            ChannelBuffer duplicate = duplicate();
            duplicate.setIndex(0, i2);
            return duplicate;
        }
        return new TruncatedChannelBuffer(this, i2);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ByteBuffer toByteBuffer(int i, int i2) {
        return ByteBuffer.wrap(this.array, i, i2).order(order());
    }
}
