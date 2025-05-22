package org.jboss.netty.buffer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.UShort;
import org.jboss.netty.util.internal.DetectionUtil;

/* loaded from: classes7.dex */
public class CompositeChannelBuffer extends AbstractChannelBuffer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private ChannelBuffer[] components;
    private final boolean gathering;
    private int[] indices;
    private int lastAccessedComponentId;
    private final ByteOrder order;

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public boolean hasArray() {
        return false;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public boolean isDirect() {
        return false;
    }

    public CompositeChannelBuffer(ByteOrder byteOrder, List<ChannelBuffer> list, boolean z) {
        this.order = byteOrder;
        this.gathering = z;
        setComponents(list);
    }

    public boolean useGathering() {
        return this.gathering && DetectionUtil.javaVersion() >= 7;
    }

    public List<ChannelBuffer> decompose(int i, int i2) {
        if (i2 == 0) {
            return Collections.emptyList();
        }
        int i3 = i + i2;
        if (i3 > capacity()) {
            throw new IndexOutOfBoundsException("Too many bytes to decompose - Need " + i3 + ", capacity is " + capacity());
        }
        int componentId = componentId(i);
        ArrayList arrayList = new ArrayList(this.components.length);
        ChannelBuffer duplicate = this.components[componentId].duplicate();
        duplicate.readerIndex(i - this.indices[componentId]);
        while (true) {
            int readableBytes = duplicate.readableBytes();
            if (i2 <= readableBytes) {
                duplicate.writerIndex(duplicate.readerIndex() + i2);
                arrayList.add(duplicate);
                break;
            }
            arrayList.add(duplicate);
            i2 -= readableBytes;
            componentId++;
            duplicate = this.components[componentId].duplicate();
            if (i2 <= 0) {
                break;
            }
        }
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            arrayList.set(i4, ((ChannelBuffer) arrayList.get(i4)).slice());
        }
        return arrayList;
    }

    private void setComponents(List<ChannelBuffer> list) {
        this.lastAccessedComponentId = 0;
        this.components = new ChannelBuffer[list.size()];
        int i = 0;
        while (true) {
            ChannelBuffer[] channelBufferArr = this.components;
            if (i < channelBufferArr.length) {
                ChannelBuffer channelBuffer = list.get(i);
                if (channelBuffer.order() != order()) {
                    throw new IllegalArgumentException("All buffers must have the same endianness.");
                }
                this.components[i] = channelBuffer;
                i++;
            } else {
                int i2 = 1;
                this.indices = new int[channelBufferArr.length + 1];
                this.indices[0] = 0;
                while (true) {
                    ChannelBuffer[] channelBufferArr2 = this.components;
                    if (i2 <= channelBufferArr2.length) {
                        int[] iArr = this.indices;
                        int i3 = i2 - 1;
                        iArr[i2] = iArr[i3] + channelBufferArr2[i3].capacity();
                        i2++;
                    } else {
                        setIndex(0, capacity());
                        return;
                    }
                }
            }
        }
    }

    private CompositeChannelBuffer(CompositeChannelBuffer compositeChannelBuffer) {
        this.order = compositeChannelBuffer.order;
        this.gathering = compositeChannelBuffer.gathering;
        this.components = (ChannelBuffer[]) compositeChannelBuffer.components.clone();
        this.indices = (int[]) compositeChannelBuffer.indices.clone();
        setIndex(compositeChannelBuffer.readerIndex(), compositeChannelBuffer.writerIndex());
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBufferFactory factory() {
        return HeapChannelBufferFactory.getInstance(order());
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ByteOrder order() {
        return this.order;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public byte[] array() {
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int arrayOffset() {
        throw new UnsupportedOperationException();
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int capacity() {
        return this.indices[this.components.length];
    }

    public int numComponents() {
        return this.components.length;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public byte getByte(int i) {
        int componentId = componentId(i);
        return this.components[componentId].getByte(i - this.indices[componentId]);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public short getShort(int i) {
        int componentId = componentId(i);
        int i2 = i + 2;
        int[] iArr = this.indices;
        if (i2 <= iArr[componentId + 1]) {
            return this.components[componentId].getShort(i - iArr[componentId]);
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (short) ((getByte(i + 1) & 255) | ((getByte(i) & 255) << 8));
        }
        return (short) (((getByte(i + 1) & 255) << 8) | (getByte(i) & 255));
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int getUnsignedMedium(int i) {
        int componentId = componentId(i);
        int i2 = i + 3;
        int[] iArr = this.indices;
        if (i2 <= iArr[componentId + 1]) {
            return this.components[componentId].getUnsignedMedium(i - iArr[componentId]);
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (getByte(i + 2) & 255) | ((getShort(i) & UShort.MAX_VALUE) << 8);
        }
        return ((getByte(i + 2) & 255) << 16) | (getShort(i) & UShort.MAX_VALUE);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int getInt(int i) {
        int componentId = componentId(i);
        int i2 = i + 4;
        int[] iArr = this.indices;
        if (i2 <= iArr[componentId + 1]) {
            return this.components[componentId].getInt(i - iArr[componentId]);
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (getShort(i + 2) & UShort.MAX_VALUE) | ((getShort(i) & UShort.MAX_VALUE) << 16);
        }
        return ((getShort(i + 2) & UShort.MAX_VALUE) << 16) | (getShort(i) & UShort.MAX_VALUE);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public long getLong(int i) {
        int componentId = componentId(i);
        int i2 = i + 8;
        int[] iArr = this.indices;
        if (i2 <= iArr[componentId + 1]) {
            return this.components[componentId].getLong(i - iArr[componentId]);
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return ((getInt(i) & 4294967295L) << 32) | (getInt(i + 4) & 4294967295L);
        }
        return (getInt(i) & 4294967295L) | ((4294967295L & getInt(i + 4)) << 32);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, byte[] bArr, int i2, int i3) {
        int componentId = componentId(i);
        if (i > capacity() - i3 || i2 > bArr.length - i3) {
            throw new IndexOutOfBoundsException("Too many bytes to read - Needs " + (i + i3) + ", maximum is " + capacity() + " or " + bArr.length);
        }
        while (i3 > 0) {
            ChannelBuffer channelBuffer = this.components[componentId];
            int i4 = i - this.indices[componentId];
            int min = Math.min(i3, channelBuffer.capacity() - i4);
            channelBuffer.getBytes(i4, bArr, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            componentId++;
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, ByteBuffer byteBuffer) {
        int componentId = componentId(i);
        int limit = byteBuffer.limit();
        int remaining = byteBuffer.remaining();
        if (i > capacity() - remaining) {
            throw new IndexOutOfBoundsException("Too many bytes to be read - Needs " + (i + remaining) + ", maximum is " + capacity());
        }
        while (remaining > 0) {
            try {
                ChannelBuffer channelBuffer = this.components[componentId];
                int i2 = i - this.indices[componentId];
                int min = Math.min(remaining, channelBuffer.capacity() - i2);
                byteBuffer.limit(byteBuffer.position() + min);
                channelBuffer.getBytes(i2, byteBuffer);
                i += min;
                remaining -= min;
                componentId++;
            } finally {
                byteBuffer.limit(limit);
            }
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, ChannelBuffer channelBuffer, int i2, int i3) {
        int componentId = componentId(i);
        if (i > capacity() - i3 || i2 > channelBuffer.capacity() - i3) {
            throw new IndexOutOfBoundsException("Too many bytes to be read - Needs " + (i + i3) + " or " + (i2 + i3) + ", maximum is " + capacity() + " or " + channelBuffer.capacity());
        }
        while (i3 > 0) {
            ChannelBuffer channelBuffer2 = this.components[componentId];
            int i4 = i - this.indices[componentId];
            int min = Math.min(i3, channelBuffer2.capacity() - i4);
            channelBuffer2.getBytes(i4, channelBuffer, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            componentId++;
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int getBytes(int i, GatheringByteChannel gatheringByteChannel, int i2) throws IOException {
        if (useGathering()) {
            return (int) gatheringByteChannel.write(toByteBuffers(i, i2));
        }
        return gatheringByteChannel.write(toByteBuffer(i, i2));
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void getBytes(int i, OutputStream outputStream, int i2) throws IOException {
        int componentId = componentId(i);
        if (i > capacity() - i2) {
            throw new IndexOutOfBoundsException("Too many bytes to be read - needs " + (i + i2) + ", maximum of " + capacity());
        }
        while (i2 > 0) {
            ChannelBuffer channelBuffer = this.components[componentId];
            int i3 = i - this.indices[componentId];
            int min = Math.min(i2, channelBuffer.capacity() - i3);
            channelBuffer.getBytes(i3, outputStream, min);
            i += min;
            i2 -= min;
            componentId++;
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setByte(int i, int i2) {
        int componentId = componentId(i);
        this.components[componentId].setByte(i - this.indices[componentId], i2);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setShort(int i, int i2) {
        int componentId = componentId(i);
        int i3 = i + 2;
        int[] iArr = this.indices;
        if (i3 <= iArr[componentId + 1]) {
            this.components[componentId].setShort(i - iArr[componentId], i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            setByte(i, (byte) (i2 >>> 8));
            setByte(i + 1, (byte) i2);
        } else {
            setByte(i, (byte) i2);
            setByte(i + 1, (byte) (i2 >>> 8));
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setMedium(int i, int i2) {
        int componentId = componentId(i);
        int i3 = i + 3;
        int[] iArr = this.indices;
        if (i3 <= iArr[componentId + 1]) {
            this.components[componentId].setMedium(i - iArr[componentId], i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            setShort(i, (short) (i2 >> 8));
            setByte(i + 2, (byte) i2);
        } else {
            setShort(i, (short) i2);
            setByte(i + 2, (byte) (i2 >>> 16));
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setInt(int i, int i2) {
        int componentId = componentId(i);
        int i3 = i + 4;
        int[] iArr = this.indices;
        if (i3 <= iArr[componentId + 1]) {
            this.components[componentId].setInt(i - iArr[componentId], i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            setShort(i, (short) (i2 >>> 16));
            setShort(i + 2, (short) i2);
        } else {
            setShort(i, (short) i2);
            setShort(i + 2, (short) (i2 >>> 16));
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setLong(int i, long j) {
        int componentId = componentId(i);
        int i2 = i + 8;
        int[] iArr = this.indices;
        if (i2 <= iArr[componentId + 1]) {
            this.components[componentId].setLong(i - iArr[componentId], j);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            setInt(i, (int) (j >>> 32));
            setInt(i + 4, (int) j);
        } else {
            setInt(i, (int) j);
            setInt(i + 4, (int) (j >>> 32));
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setBytes(int i, byte[] bArr, int i2, int i3) {
        int componentId = componentId(i);
        if (i > capacity() - i3 || i2 > bArr.length - i3) {
            throw new IndexOutOfBoundsException("Too many bytes to read - needs " + (i + i3) + " or " + (i2 + i3) + ", maximum is " + capacity() + " or " + bArr.length);
        }
        while (i3 > 0) {
            ChannelBuffer channelBuffer = this.components[componentId];
            int i4 = i - this.indices[componentId];
            int min = Math.min(i3, channelBuffer.capacity() - i4);
            channelBuffer.setBytes(i4, bArr, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            componentId++;
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setBytes(int i, ByteBuffer byteBuffer) {
        int componentId = componentId(i);
        int limit = byteBuffer.limit();
        int remaining = byteBuffer.remaining();
        if (i > capacity() - remaining) {
            throw new IndexOutOfBoundsException("Too many bytes to be written - Needs " + (i + remaining) + ", maximum is " + capacity());
        }
        while (remaining > 0) {
            try {
                ChannelBuffer channelBuffer = this.components[componentId];
                int i2 = i - this.indices[componentId];
                int min = Math.min(remaining, channelBuffer.capacity() - i2);
                byteBuffer.limit(byteBuffer.position() + min);
                channelBuffer.setBytes(i2, byteBuffer);
                i += min;
                remaining -= min;
                componentId++;
            } finally {
                byteBuffer.limit(limit);
            }
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public void setBytes(int i, ChannelBuffer channelBuffer, int i2, int i3) {
        int componentId = componentId(i);
        if (i > capacity() - i3 || i2 > channelBuffer.capacity() - i3) {
            throw new IndexOutOfBoundsException("Too many bytes to be written - Needs " + (i + i3) + " or " + (i2 + i3) + ", maximum is " + capacity() + " or " + channelBuffer.capacity());
        }
        while (i3 > 0) {
            ChannelBuffer channelBuffer2 = this.components[componentId];
            int i4 = i - this.indices[componentId];
            int min = Math.min(i3, channelBuffer2.capacity() - i4);
            channelBuffer2.setBytes(i4, channelBuffer, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            componentId++;
        }
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int setBytes(int i, InputStream inputStream, int i2) throws IOException {
        int componentId = componentId(i);
        if (i > capacity() - i2) {
            throw new IndexOutOfBoundsException("Too many bytes to write - Needs " + (i + i2) + ", maximum is " + capacity());
        }
        int i3 = 0;
        while (true) {
            ChannelBuffer channelBuffer = this.components[componentId];
            int i4 = i - this.indices[componentId];
            int min = Math.min(i2, channelBuffer.capacity() - i4);
            int bytes = channelBuffer.setBytes(i4, inputStream, min);
            if (bytes >= 0) {
                if (bytes == min) {
                    i += min;
                    i2 -= min;
                    i3 += min;
                    componentId++;
                } else {
                    i += bytes;
                    i2 -= bytes;
                    i3 += bytes;
                }
                if (i2 <= 0) {
                    break;
                }
            } else if (i3 == 0) {
                return -1;
            }
        }
        return i3;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public int setBytes(int i, ScatteringByteChannel scatteringByteChannel, int i2) throws IOException {
        int componentId = componentId(i);
        if (i > capacity() - i2) {
            throw new IndexOutOfBoundsException("Too many bytes to write - Needs " + (i + i2) + ", maximum is " + capacity());
        }
        int i3 = 0;
        do {
            ChannelBuffer channelBuffer = this.components[componentId];
            int i4 = i - this.indices[componentId];
            int min = Math.min(i2, channelBuffer.capacity() - i4);
            int bytes = channelBuffer.setBytes(i4, scatteringByteChannel, min);
            if (bytes == min) {
                i += min;
                i2 -= min;
                i3 += min;
                componentId++;
            } else {
                i += bytes;
                i2 -= bytes;
                i3 += bytes;
            }
        } while (i2 > 0);
        return i3;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBuffer duplicate() {
        CompositeChannelBuffer compositeChannelBuffer = new CompositeChannelBuffer(this);
        compositeChannelBuffer.setIndex(readerIndex(), writerIndex());
        return compositeChannelBuffer;
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBuffer copy(int i, int i2) {
        int componentId = componentId(i);
        if (i > capacity() - i2) {
            throw new IndexOutOfBoundsException("Too many bytes to copy - Needs " + (i + i2) + ", maximum is " + capacity());
        }
        ChannelBuffer buffer = factory().getBuffer(order(), i2);
        copyTo(i, i2, componentId, buffer);
        return buffer;
    }

    private void copyTo(int i, int i2, int i3, ChannelBuffer channelBuffer) {
        int i4 = 0;
        while (i2 > 0) {
            ChannelBuffer channelBuffer2 = this.components[i3];
            int i5 = i - this.indices[i3];
            int min = Math.min(i2, channelBuffer2.capacity() - i5);
            channelBuffer2.getBytes(i5, channelBuffer, i4, min);
            i += min;
            i4 += min;
            i2 -= min;
            i3++;
        }
        channelBuffer.writerIndex(channelBuffer.capacity());
    }

    public ChannelBuffer getBuffer(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= capacity()) {
            throw new IndexOutOfBoundsException("Invalid index: " + i + " - Bytes needed: " + i + ", maximum is " + capacity());
        }
        return this.components[componentId(i)];
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ChannelBuffer slice(int i, int i2) {
        if (i == 0) {
            if (i2 == 0) {
                return ChannelBuffers.EMPTY_BUFFER;
            }
        } else {
            if (i < 0 || i > capacity() - i2) {
                throw new IndexOutOfBoundsException("Invalid index: " + i + " - Bytes needed: " + (i + i2) + ", maximum is " + capacity());
            }
            if (i2 == 0) {
                return ChannelBuffers.EMPTY_BUFFER;
            }
        }
        List<ChannelBuffer> decompose = decompose(i, i2);
        int size = decompose.size();
        if (size == 0) {
            return ChannelBuffers.EMPTY_BUFFER;
        }
        if (size == 1) {
            return decompose.get(0);
        }
        return new CompositeChannelBuffer(order(), decompose, this.gathering);
    }

    @Override // org.jboss.netty.buffer.ChannelBuffer
    public ByteBuffer toByteBuffer(int i, int i2) {
        ChannelBuffer[] channelBufferArr = this.components;
        if (channelBufferArr.length == 1) {
            return channelBufferArr[0].toByteBuffer(i, i2);
        }
        ByteBuffer[] byteBuffers = toByteBuffers(i, i2);
        ByteBuffer order = ByteBuffer.allocate(i2).order(order());
        for (ByteBuffer byteBuffer : byteBuffers) {
            order.put(byteBuffer);
        }
        order.flip();
        return order;
    }

    @Override // org.jboss.netty.buffer.AbstractChannelBuffer, org.jboss.netty.buffer.ChannelBuffer
    public ByteBuffer[] toByteBuffers(int i, int i2) {
        int componentId = componentId(i);
        int i3 = i + i2;
        if (i3 > capacity()) {
            throw new IndexOutOfBoundsException("Too many bytes to convert - Needs" + i3 + ", maximum is " + capacity());
        }
        ArrayList arrayList = new ArrayList(this.components.length);
        while (i2 > 0) {
            ChannelBuffer channelBuffer = this.components[componentId];
            int i4 = i - this.indices[componentId];
            int min = Math.min(i2, channelBuffer.capacity() - i4);
            arrayList.add(channelBuffer.toByteBuffer(i4, min));
            i += min;
            i2 -= min;
            componentId++;
        }
        return (ByteBuffer[]) arrayList.toArray(new ByteBuffer[arrayList.size()]);
    }

    private int componentId(int i) {
        int i2 = this.lastAccessedComponentId;
        int[] iArr = this.indices;
        if (i >= iArr[i2]) {
            int i3 = i2 + 1;
            if (i < iArr[i3]) {
                return i2;
            }
            while (i3 < this.components.length) {
                int i4 = i3 + 1;
                if (i < this.indices[i4]) {
                    this.lastAccessedComponentId = i3;
                    return i3;
                }
                i3 = i4;
            }
        } else {
            for (int i5 = i2 - 1; i5 >= 0; i5--) {
                if (i >= this.indices[i5]) {
                    this.lastAccessedComponentId = i5;
                    return i5;
                }
            }
        }
        throw new IndexOutOfBoundsException("Invalid index: " + i + ", maximum: " + this.indices.length);
    }

    @Override // org.jboss.netty.buffer.AbstractChannelBuffer, org.jboss.netty.buffer.ChannelBuffer
    public void discardReadBytes() {
        int i;
        int i2;
        int readerIndex = readerIndex();
        if (readerIndex == 0) {
            return;
        }
        int writerIndex = writerIndex();
        List<ChannelBuffer> decompose = decompose(readerIndex, capacity() - readerIndex);
        if (decompose.isEmpty()) {
            decompose = new ArrayList<>(1);
        }
        ChannelBuffer buffer = ChannelBuffers.buffer(order(), readerIndex);
        buffer.writerIndex(readerIndex);
        decompose.add(buffer);
        try {
            resetReaderIndex();
            i = readerIndex();
        } catch (IndexOutOfBoundsException unused) {
            i = readerIndex;
        }
        try {
            resetWriterIndex();
            i2 = writerIndex();
        } catch (IndexOutOfBoundsException unused2) {
            i2 = writerIndex;
        }
        setComponents(decompose);
        setIndex(Math.max(i - readerIndex, 0), Math.max(i2 - readerIndex, 0));
        markReaderIndex();
        markWriterIndex();
        setIndex(0, Math.max(writerIndex - readerIndex, 0));
    }

    @Override // org.jboss.netty.buffer.AbstractChannelBuffer, org.jboss.netty.buffer.ChannelBuffer
    public String toString() {
        return super.toString().substring(0, r0.length() - 1) + ", components=" + this.components.length + ")";
    }
}
