package org.jboss.netty.buffer;

import com.iflytek.cloud.SpeechEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/* loaded from: classes7.dex */
public class ByteBufferBackedChannelBuffer extends AbstractChannelBuffer {
    private final ByteBuffer buffer;
    private final int capacity;
    private final ByteOrder order;

    public ByteBufferBackedChannelBuffer(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            throw new NullPointerException(SpeechEvent.KEY_EVENT_TTS_BUFFER);
        }
        this.order = byteBuffer.order();
        this.buffer = byteBuffer.slice().order(this.order);
        this.capacity = byteBuffer.remaining();
        writerIndex(this.capacity);
    }

    private ByteBufferBackedChannelBuffer(ByteBufferBackedChannelBuffer byteBufferBackedChannelBuffer) {
        this.buffer = byteBufferBackedChannelBuffer.buffer;
        this.order = byteBufferBackedChannelBuffer.order;
        this.capacity = byteBufferBackedChannelBuffer.capacity;
        setIndex(byteBufferBackedChannelBuffer.readerIndex(), byteBufferBackedChannelBuffer.writerIndex());
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBufferFactory factory() {
        if (this.buffer.isDirect()) {
            return DirectChannelBufferFactory.getInstance(order());
        }
        return HeapChannelBufferFactory.getInstance(order());
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public boolean isDirect() {
        return this.buffer.isDirect();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ByteOrder order() {
        return this.order;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int capacity() {
        return this.capacity;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public boolean hasArray() {
        return this.buffer.hasArray();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public byte[] array() {
        return this.buffer.array();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int arrayOffset() {
        return this.buffer.arrayOffset();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public byte getByte(int i) {
        return this.buffer.get(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public short getShort(int i) {
        return this.buffer.getShort(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int getUnsignedMedium(int i) {
        return ((getByte(i + 2) & 255) << 0) | ((getByte(i) & 255) << 16) | ((getByte(i + 1) & 255) << 8);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int getInt(int i) {
        return this.buffer.getInt(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public long getLong(int i) {
        return this.buffer.getLong(i);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, ChannelBuffer channelBuffer, int i2, int i3) {
        if (channelBuffer instanceof ByteBufferBackedChannelBuffer) {
            ByteBuffer duplicate = ((ByteBufferBackedChannelBuffer) channelBuffer).buffer.duplicate();
            duplicate.limit(i3 + i2).position(i2);
            getBytes(i, duplicate);
        } else if (this.buffer.hasArray()) {
            channelBuffer.setBytes(i2, this.buffer.array(), i + this.buffer.arrayOffset(), i3);
        } else {
            channelBuffer.setBytes(i2, this, i, i3);
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, byte[] bArr, int i2, int i3) {
        ByteBuffer duplicate = this.buffer.duplicate();
        int i4 = i + i3;
        try {
            duplicate.limit(i4).position(i);
            duplicate.get(bArr, i2, i3);
        } catch (IllegalArgumentException unused) {
            throw new IndexOutOfBoundsException("Too many bytes to read - Need " + i4 + ", maximum is " + duplicate.limit());
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, ByteBuffer byteBuffer) {
        ByteBuffer duplicate = this.buffer.duplicate();
        int min = Math.min(capacity() - i, byteBuffer.remaining()) + i;
        try {
            duplicate.limit(min).position(i);
            byteBuffer.put(duplicate);
        } catch (IllegalArgumentException unused) {
            throw new IndexOutOfBoundsException("Too many bytes to read - Need " + min + ", maximum is " + duplicate.limit());
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setByte(int i, int i2) {
        this.buffer.put(i, (byte) i2);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setShort(int i, int i2) {
        this.buffer.putShort(i, (short) i2);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setMedium(int i, int i2) {
        setByte(i, (byte) (i2 >>> 16));
        setByte(i + 1, (byte) (i2 >>> 8));
        setByte(i + 2, (byte) (i2 >>> 0));
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setInt(int i, int i2) {
        this.buffer.putInt(i, i2);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setLong(int i, long j) {
        this.buffer.putLong(i, j);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setBytes(int i, ChannelBuffer channelBuffer, int i2, int i3) {
        if (channelBuffer instanceof ByteBufferBackedChannelBuffer) {
            ByteBuffer duplicate = ((ByteBufferBackedChannelBuffer) channelBuffer).buffer.duplicate();
            duplicate.limit(i3 + i2).position(i2);
            setBytes(i, duplicate);
        } else if (this.buffer.hasArray()) {
            channelBuffer.getBytes(i2, this.buffer.array(), i + this.buffer.arrayOffset(), i3);
        } else {
            channelBuffer.getBytes(i2, this, i, i3);
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setBytes(int i, byte[] bArr, int i2, int i3) {
        ByteBuffer duplicate = this.buffer.duplicate();
        duplicate.limit(i + i3).position(i);
        duplicate.put(bArr, i2, i3);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setBytes(int i, ByteBuffer byteBuffer) {
        ByteBuffer duplicate = this.buffer.duplicate();
        duplicate.limit(byteBuffer.remaining() + i).position(i);
        duplicate.put(byteBuffer);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, OutputStream outputStream, int i2) throws IOException {
        if (i2 == 0) {
            return;
        }
        if (this.buffer.hasArray()) {
            outputStream.write(this.buffer.array(), i + this.buffer.arrayOffset(), i2);
            return;
        }
        byte[] bArr = new byte[i2];
        ((ByteBuffer) this.buffer.duplicate().position(i)).get(bArr);
        outputStream.write(bArr);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int getBytes(int i, GatheringByteChannel gatheringByteChannel, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        return gatheringByteChannel.write((ByteBuffer) this.buffer.duplicate().position(i).limit(i + i2));
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int setBytes(int i, InputStream inputStream, int i2) throws IOException {
        int i3 = 0;
        if (this.buffer.hasArray()) {
            int arrayOffset = i + this.buffer.arrayOffset();
            do {
                int read = inputStream.read(this.buffer.array(), arrayOffset, i2);
                if (read < 0) {
                    if (i3 == 0) {
                        return -1;
                    }
                    return i3;
                }
                i3 += read;
                arrayOffset += read;
                i2 -= read;
            } while (i2 > 0);
            return i3;
        }
        byte[] bArr = new byte[i2];
        int i4 = 0;
        while (true) {
            int read2 = inputStream.read(bArr, i3, bArr.length - i3);
            if (read2 >= 0) {
                i4 += read2;
                i3 += i4;
                if (i3 >= bArr.length) {
                    break;
                }
            } else if (i4 == 0) {
                return -1;
            }
        }
        int i5 = i4;
        ((ByteBuffer) this.buffer.duplicate().position(i)).put(bArr);
        return i5;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int setBytes(int i, ScatteringByteChannel scatteringByteChannel, int i2) throws IOException {
        int i3;
        ByteBuffer byteBuffer = (ByteBuffer) this.buffer.duplicate().limit(i + i2).position(i);
        int i4 = 0;
        while (i4 < i2) {
            try {
                i3 = scatteringByteChannel.read(byteBuffer);
            } catch (ClosedChannelException unused) {
                i3 = -1;
            }
            if (i3 < 0) {
                if (i4 == 0) {
                    return -1;
                }
                return i4;
            }
            if (i3 == 0) {
                break;
            }
            i4 += i3;
        }
        return i4;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ByteBuffer toByteBuffer(int i, int i2) {
        if (i == 0 && i2 == capacity()) {
            return this.buffer.duplicate().order(order());
        }
        return ((ByteBuffer) this.buffer.duplicate().position(i).limit(i + i2)).slice().order(order());
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBuffer slice(int i, int i2) {
        if (i == 0 && i2 == capacity()) {
            ChannelBuffer duplicate = duplicate();
            duplicate.setIndex(0, i2);
            return duplicate;
        }
        if (i >= 0 && i2 == 0) {
            return ChannelBuffers.EMPTY_BUFFER;
        }
        return new ByteBufferBackedChannelBuffer(((ByteBuffer) this.buffer.duplicate().position(i).limit(i + i2)).order(order()));
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBuffer duplicate() {
        return new ByteBufferBackedChannelBuffer(this);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBuffer copy(int i, int i2) {
        try {
            ByteBuffer byteBuffer = (ByteBuffer) this.buffer.duplicate().position(i).limit(i + i2);
            ByteBuffer allocateDirect = this.buffer.isDirect() ? ByteBuffer.allocateDirect(i2) : ByteBuffer.allocate(i2);
            allocateDirect.put(byteBuffer);
            allocateDirect.order(order());
            allocateDirect.clear();
            return new ByteBufferBackedChannelBuffer(allocateDirect);
        } catch (IllegalArgumentException unused) {
            throw new IndexOutOfBoundsException("Too many bytes to read - Need " + (i + i2));
        }
    }
}
