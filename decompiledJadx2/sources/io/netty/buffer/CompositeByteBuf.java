package io.netty.buffer;

import com.iflytek.cloud.SpeechEvent;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import kotlin.UShort;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class CompositeByteBuf extends AbstractReferenceCountedByteBuf implements Iterable<ByteBuf> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final ByteBufAllocator alloc;
    private final ComponentList components;
    private final boolean direct;
    private boolean freed;
    private final int maxNumComponents;
    private static final ByteBuffer EMPTY_NIO_BUFFER = Unpooled.EMPTY_BUFFER.nioBuffer();
    private static final Iterator<ByteBuf> EMPTY_ITERATOR = Collections.emptyList().iterator();

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    interface ByteWrapper<T> {
        boolean isEmpty(T t);

        ByteBuf wrap(T t);
    }

    @Override // io.netty.buffer.AbstractReferenceCountedByteBuf, io.netty.buffer.ByteBuf, io.netty.util.ReferenceCounted
    public CompositeByteBuf touch() {
        return this;
    }

    @Override // io.netty.buffer.AbstractReferenceCountedByteBuf, io.netty.buffer.ByteBuf, io.netty.util.ReferenceCounted
    public CompositeByteBuf touch(Object obj) {
        return this;
    }

    @Override // io.netty.buffer.ByteBuf
    public ByteBuf unwrap() {
        return null;
    }

    public CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i) {
        super(Integer.MAX_VALUE);
        if (byteBufAllocator == null) {
            throw new NullPointerException("alloc");
        }
        this.alloc = byteBufAllocator;
        this.direct = z;
        this.maxNumComponents = i;
        this.components = newList(i);
    }

    public CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, ByteBuf... byteBufArr) {
        this(byteBufAllocator, z, i, byteBufArr, 0, byteBufArr.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, ByteBuf[] byteBufArr, int i2, int i3) {
        super(Integer.MAX_VALUE);
        if (byteBufAllocator == null) {
            throw new NullPointerException("alloc");
        }
        if (i < 2) {
            throw new IllegalArgumentException("maxNumComponents: " + i + " (expected: >= 2)");
        }
        this.alloc = byteBufAllocator;
        this.direct = z;
        this.maxNumComponents = i;
        this.components = newList(i);
        addComponents0(false, 0, byteBufArr, i2, i3);
        consolidateIfNeeded();
        setIndex(0, capacity());
    }

    public CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, Iterable<ByteBuf> iterable) {
        super(Integer.MAX_VALUE);
        if (byteBufAllocator == null) {
            throw new NullPointerException("alloc");
        }
        if (i < 2) {
            throw new IllegalArgumentException("maxNumComponents: " + i + " (expected: >= 2)");
        }
        this.alloc = byteBufAllocator;
        this.direct = z;
        this.maxNumComponents = i;
        this.components = newList(i);
        addComponents0(false, 0, iterable);
        consolidateIfNeeded();
        setIndex(0, capacity());
    }

    private static ComponentList newList(int i) {
        return new ComponentList(Math.min(16, i));
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.netty.buffer.CompositeByteBuf$2 */
    /* loaded from: classes7.dex */
    static class C69082 implements ByteWrapper<ByteBuffer> {
        C69082() {
        }

        @Override // io.netty.buffer.CompositeByteBuf.ByteWrapper
        public ByteBuf wrap(ByteBuffer byteBuffer) {
            return Unpooled.wrappedBuffer(byteBuffer);
        }

        @Override // io.netty.buffer.CompositeByteBuf.ByteWrapper
        public boolean isEmpty(ByteBuffer byteBuffer) {
            return !byteBuffer.hasRemaining();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompositeByteBuf(ByteBufAllocator byteBufAllocator) {
        super(Integer.MAX_VALUE);
        this.alloc = byteBufAllocator;
        this.direct = false;
        this.maxNumComponents = 0;
        this.components = null;
    }

    public CompositeByteBuf addComponent(ByteBuf byteBuf) {
        return addComponent(false, byteBuf);
    }

    public CompositeByteBuf addComponents(ByteBuf... byteBufArr) {
        return addComponents(false, byteBufArr);
    }

    public CompositeByteBuf addComponents(Iterable<ByteBuf> iterable) {
        return addComponents(false, iterable);
    }

    public CompositeByteBuf addComponent(int i, ByteBuf byteBuf) {
        return addComponent(false, i, byteBuf);
    }

    public CompositeByteBuf addComponent(boolean z, ByteBuf byteBuf) {
        ObjectUtil.checkNotNull(byteBuf, SpeechEvent.KEY_EVENT_TTS_BUFFER);
        addComponent0(z, this.components.size(), byteBuf);
        consolidateIfNeeded();
        return this;
    }

    public CompositeByteBuf addComponents(boolean z, ByteBuf... byteBufArr) {
        addComponents0(z, this.components.size(), byteBufArr, 0, byteBufArr.length);
        consolidateIfNeeded();
        return this;
    }

    public CompositeByteBuf addComponents(boolean z, Iterable<ByteBuf> iterable) {
        addComponents0(z, this.components.size(), iterable);
        consolidateIfNeeded();
        return this;
    }

    public CompositeByteBuf addComponent(boolean z, int i, ByteBuf byteBuf) {
        ObjectUtil.checkNotNull(byteBuf, SpeechEvent.KEY_EVENT_TTS_BUFFER);
        addComponent0(z, i, byteBuf);
        consolidateIfNeeded();
        return this;
    }

    private int addComponent0(boolean z, int i, ByteBuf byteBuf) {
        boolean z2 = false;
        try {
            checkComponentIndex(i);
            int readableBytes = byteBuf.readableBytes();
            Component component = new Component(byteBuf.order(ByteOrder.BIG_ENDIAN).slice());
            if (i == this.components.size()) {
                z2 = this.components.add(component);
                if (i == 0) {
                    component.endOffset = readableBytes;
                } else {
                    component.offset = this.components.get(i - 1).endOffset;
                    component.endOffset = component.offset + readableBytes;
                }
            } else {
                this.components.add(i, component);
                if (readableBytes != 0) {
                    try {
                        updateComponentOffsets(i);
                    } catch (Throwable th) {
                        th = th;
                        z2 = true;
                        if (!z2) {
                            byteBuf.release();
                        }
                        throw th;
                    }
                }
                z2 = true;
            }
            if (z) {
                writerIndex(writerIndex() + byteBuf.readableBytes());
            }
            if (!z2) {
                byteBuf.release();
            }
            return i;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public CompositeByteBuf addComponents(int i, ByteBuf... byteBufArr) {
        addComponents0(false, i, byteBufArr, 0, byteBufArr.length);
        consolidateIfNeeded();
        return this;
    }

    private int addComponents0(boolean z, int i, ByteBuf[] byteBufArr, int i2, int i3) {
        ObjectUtil.checkNotNull(byteBufArr, "buffers");
        try {
            checkComponentIndex(i);
            while (true) {
                if (i2 >= i3) {
                    break;
                }
                int i4 = i2 + 1;
                try {
                    ByteBuf byteBuf = byteBufArr[i2];
                    if (byteBuf == null) {
                        i2 = i4;
                        break;
                    }
                    i = addComponent0(z, i, byteBuf) + 1;
                    int size = this.components.size();
                    if (i > size) {
                        i = size;
                    }
                    i2 = i4;
                } catch (Throwable th) {
                    th = th;
                    i2 = i4;
                    while (i2 < i3) {
                        ByteBuf byteBuf2 = byteBufArr[i2];
                        if (byteBuf2 != null) {
                            try {
                                byteBuf2.release();
                            } catch (Throwable unused) {
                            }
                        }
                        i2++;
                    }
                    throw th;
                }
            }
            while (i2 < i3) {
                ByteBuf byteBuf3 = byteBufArr[i2];
                if (byteBuf3 != null) {
                    try {
                        byteBuf3.release();
                    } catch (Throwable unused2) {
                    }
                }
                i2++;
            }
            return i;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public CompositeByteBuf addComponents(int i, Iterable<ByteBuf> iterable) {
        addComponents0(false, i, iterable);
        consolidateIfNeeded();
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private int addComponents0(boolean z, int i, Iterable<ByteBuf> iterable) {
        if (iterable instanceof ByteBuf) {
            return addComponent0(z, i, (ByteBuf) iterable);
        }
        ObjectUtil.checkNotNull(iterable, "buffers");
        boolean z2 = iterable instanceof Collection;
        ArrayList arrayList = iterable;
        if (!z2) {
            ArrayList<ByteBuf> arrayList2 = new ArrayList();
            try {
                Iterator it = iterable.iterator();
                while (it.hasNext()) {
                    arrayList2.add((ByteBuf) it.next());
                }
                if (arrayList2 != arrayList2) {
                    for (ByteBuf byteBuf : arrayList2) {
                        if (byteBuf != null) {
                            try {
                                byteBuf.release();
                            } catch (Throwable unused) {
                            }
                        }
                    }
                }
                arrayList = arrayList2;
            } catch (Throwable th) {
                if (iterable != arrayList2) {
                    for (ByteBuf byteBuf2 : iterable) {
                        if (byteBuf2 != null) {
                            try {
                                byteBuf2.release();
                            } catch (Throwable unused2) {
                            }
                        }
                    }
                }
                throw th;
            }
        }
        ArrayList arrayList3 = arrayList;
        return addComponents0(z, i, (ByteBuf[]) arrayList3.toArray(new ByteBuf[arrayList3.size()]), 0, arrayList3.size());
    }

    private void consolidateIfNeeded() {
        int size = this.components.size();
        if (size > this.maxNumComponents) {
            ByteBuf allocBuffer = allocBuffer(this.components.get(size - 1).endOffset);
            for (int i = 0; i < size; i++) {
                Component component = this.components.get(i);
                allocBuffer.writeBytes(component.buf);
                component.freeIfNecessary();
            }
            Component component2 = new Component(allocBuffer);
            component2.endOffset = component2.length;
            this.components.clear();
            this.components.add(component2);
        }
    }

    private void checkComponentIndex(int i) {
        ensureAccessible();
        if (i < 0 || i > this.components.size()) {
            throw new IndexOutOfBoundsException(String.format("cIndex: %d (expected: >= 0 && <= numComponents(%d))", Integer.valueOf(i), Integer.valueOf(this.components.size())));
        }
    }

    private void checkComponentIndex(int i, int i2) {
        ensureAccessible();
        if (i < 0 || i + i2 > this.components.size()) {
            throw new IndexOutOfBoundsException(String.format("cIndex: %d, numComponents: %d (expected: cIndex >= 0 && cIndex + numComponents <= totalNumComponents(%d))", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(this.components.size())));
        }
    }

    private void updateComponentOffsets(int i) {
        int size = this.components.size();
        if (size <= i) {
            return;
        }
        Component component = this.components.get(i);
        if (i == 0) {
            component.offset = 0;
            component.endOffset = component.length;
            i++;
        }
        while (i < size) {
            Component component2 = this.components.get(i - 1);
            Component component3 = this.components.get(i);
            component3.offset = component2.endOffset;
            component3.endOffset = component3.offset + component3.length;
            i++;
        }
    }

    public CompositeByteBuf removeComponent(int i) {
        checkComponentIndex(i);
        Component remove = this.components.remove(i);
        remove.freeIfNecessary();
        if (remove.length > 0) {
            updateComponentOffsets(i);
        }
        return this;
    }

    public CompositeByteBuf removeComponents(int i, int i2) {
        checkComponentIndex(i, i2);
        if (i2 == 0) {
            return this;
        }
        int i3 = i2 + i;
        boolean z = false;
        for (int i4 = i; i4 < i3; i4++) {
            Component component = this.components.get(i4);
            if (component.length > 0) {
                z = true;
            }
            component.freeIfNecessary();
        }
        this.components.removeRange(i, i3);
        if (z) {
            updateComponentOffsets(i);
        }
        return this;
    }

    public Iterator<ByteBuf> iterator() {
        ensureAccessible();
        if (this.components.isEmpty()) {
            return EMPTY_ITERATOR;
        }
        return new CompositeByteBufIterator();
    }

    public List<ByteBuf> decompose(int i, int i2) {
        checkIndex(i, i2);
        if (i2 == 0) {
            return Collections.emptyList();
        }
        int componentIndex = toComponentIndex(i);
        ArrayList arrayList = new ArrayList(this.components.size());
        Component component = this.components.get(componentIndex);
        ByteBuf duplicate = component.buf.duplicate();
        duplicate.readerIndex(i - component.offset);
        while (true) {
            int readableBytes = duplicate.readableBytes();
            if (i2 <= readableBytes) {
                duplicate.writerIndex(duplicate.readerIndex() + i2);
                arrayList.add(duplicate);
                break;
            }
            arrayList.add(duplicate);
            i2 -= readableBytes;
            componentIndex++;
            duplicate = this.components.get(componentIndex).buf.duplicate();
            if (i2 <= 0) {
                break;
            }
        }
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            arrayList.set(i3, ((ByteBuf) arrayList.get(i3)).slice());
        }
        return arrayList;
    }

    @Override // io.netty.buffer.ByteBuf
    public boolean isDirect() {
        int size = this.components.size();
        if (size == 0) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.components.get(i).buf.isDirect()) {
                return false;
            }
        }
        return true;
    }

    @Override // io.netty.buffer.ByteBuf
    public boolean hasArray() {
        int size = this.components.size();
        if (size == 0) {
            return true;
        }
        if (size != 1) {
            return false;
        }
        return this.components.get(0).buf.hasArray();
    }

    @Override // io.netty.buffer.ByteBuf
    public byte[] array() {
        int size = this.components.size();
        if (size == 0) {
            return EmptyArrays.EMPTY_BYTES;
        }
        if (size == 1) {
            return this.components.get(0).buf.array();
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.buffer.ByteBuf
    public int arrayOffset() {
        int size = this.components.size();
        if (size == 0) {
            return 0;
        }
        if (size == 1) {
            return this.components.get(0).buf.arrayOffset();
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.buffer.ByteBuf
    public boolean hasMemoryAddress() {
        int size = this.components.size();
        if (size == 0) {
            return Unpooled.EMPTY_BUFFER.hasMemoryAddress();
        }
        if (size != 1) {
            return false;
        }
        return this.components.get(0).buf.hasMemoryAddress();
    }

    @Override // io.netty.buffer.ByteBuf
    public long memoryAddress() {
        int size = this.components.size();
        if (size == 0) {
            return Unpooled.EMPTY_BUFFER.memoryAddress();
        }
        if (size == 1) {
            return this.components.get(0).buf.memoryAddress();
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.buffer.ByteBuf
    public int capacity() {
        int size = this.components.size();
        if (size == 0) {
            return 0;
        }
        return this.components.get(size - 1).endOffset;
    }

    @Override // io.netty.buffer.ByteBuf
    public CompositeByteBuf capacity(int i) {
        checkNewCapacity(i);
        int capacity = capacity();
        if (i > capacity) {
            int i2 = i - capacity;
            if (this.components.size() < this.maxNumComponents) {
                ByteBuf allocBuffer = allocBuffer(i2);
                allocBuffer.setIndex(0, i2);
                addComponent0(false, this.components.size(), allocBuffer);
            } else {
                ByteBuf allocBuffer2 = allocBuffer(i2);
                allocBuffer2.setIndex(0, i2);
                addComponent0(false, this.components.size(), allocBuffer2);
                consolidateIfNeeded();
            }
        } else if (i < capacity) {
            int i3 = capacity - i;
            ComponentList componentList = this.components;
            ListIterator<Component> listIterator = componentList.listIterator(componentList.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    break;
                }
                Component previous = listIterator.previous();
                if (i3 >= previous.length) {
                    i3 -= previous.length;
                    listIterator.remove();
                } else {
                    Component component = new Component(previous.buf.slice(0, previous.length - i3));
                    component.offset = previous.offset;
                    component.endOffset = component.offset + component.length;
                    listIterator.set(component);
                    break;
                }
            }
            if (readerIndex() > i) {
                setIndex(i, i);
            } else if (writerIndex() > i) {
                writerIndex(i);
            }
        }
        return this;
    }

    @Override // io.netty.buffer.ByteBuf
    public ByteBufAllocator alloc() {
        return this.alloc;
    }

    @Override // io.netty.buffer.ByteBuf
    public ByteOrder order() {
        return ByteOrder.BIG_ENDIAN;
    }

    public int numComponents() {
        return this.components.size();
    }

    public int maxNumComponents() {
        return this.maxNumComponents;
    }

    public int toComponentIndex(int i) {
        checkIndex(i);
        int size = this.components.size();
        int i2 = 0;
        while (i2 <= size) {
            int i3 = (i2 + size) >>> 1;
            Component component = this.components.get(i3);
            if (i >= component.endOffset) {
                i2 = i3 + 1;
            } else {
                if (i >= component.offset) {
                    return i3;
                }
                size = i3 - 1;
            }
        }
        throw new Error("should not reach here");
    }

    public int toByteIndex(int i) {
        checkComponentIndex(i);
        return this.components.get(i).offset;
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public byte getByte(int i) {
        return _getByte(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public byte _getByte(int i) {
        Component findComponent = findComponent(i);
        return findComponent.buf.getByte(i - findComponent.offset);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public short _getShort(int i) {
        Component findComponent = findComponent(i);
        if (i + 2 <= findComponent.endOffset) {
            return findComponent.buf.getShort(i - findComponent.offset);
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (short) ((_getByte(i + 1) & 255) | ((_getByte(i) & 255) << 8));
        }
        return (short) (((_getByte(i + 1) & 255) << 8) | (_getByte(i) & 255));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public short _getShortLE(int i) {
        Component findComponent = findComponent(i);
        if (i + 2 <= findComponent.endOffset) {
            return findComponent.buf.getShortLE(i - findComponent.offset);
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (short) (((_getByte(i + 1) & 255) << 8) | (_getByte(i) & 255));
        }
        return (short) ((_getByte(i + 1) & 255) | ((_getByte(i) & 255) << 8));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public int _getUnsignedMedium(int i) {
        Component findComponent = findComponent(i);
        if (i + 3 <= findComponent.endOffset) {
            return findComponent.buf.getUnsignedMedium(i - findComponent.offset);
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (_getByte(i + 2) & 255) | ((_getShort(i) & UShort.MAX_VALUE) << 8);
        }
        return ((_getByte(i + 2) & 255) << 16) | (_getShort(i) & UShort.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public int _getUnsignedMediumLE(int i) {
        Component findComponent = findComponent(i);
        if (i + 3 <= findComponent.endOffset) {
            return findComponent.buf.getUnsignedMediumLE(i - findComponent.offset);
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return ((_getByte(i + 2) & 255) << 16) | (_getShortLE(i) & UShort.MAX_VALUE);
        }
        return (_getByte(i + 2) & 255) | ((_getShortLE(i) & UShort.MAX_VALUE) << 8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public int _getInt(int i) {
        Component findComponent = findComponent(i);
        if (i + 4 <= findComponent.endOffset) {
            return findComponent.buf.getInt(i - findComponent.offset);
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (_getShort(i + 2) & UShort.MAX_VALUE) | ((_getShort(i) & UShort.MAX_VALUE) << 16);
        }
        return ((_getShort(i + 2) & UShort.MAX_VALUE) << 16) | (_getShort(i) & UShort.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public int _getIntLE(int i) {
        Component findComponent = findComponent(i);
        if (i + 4 <= findComponent.endOffset) {
            return findComponent.buf.getIntLE(i - findComponent.offset);
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return ((_getShortLE(i + 2) & UShort.MAX_VALUE) << 16) | (_getShortLE(i) & UShort.MAX_VALUE);
        }
        return (_getShortLE(i + 2) & UShort.MAX_VALUE) | ((_getShortLE(i) & UShort.MAX_VALUE) << 16);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public long _getLong(int i) {
        Component findComponent = findComponent(i);
        if (i + 8 <= findComponent.endOffset) {
            return findComponent.buf.getLong(i - findComponent.offset);
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return ((_getInt(i) & 4294967295L) << 32) | (_getInt(i + 4) & 4294967295L);
        }
        return (_getInt(i) & 4294967295L) | ((4294967295L & _getInt(i + 4)) << 32);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public long _getLongLE(int i) {
        Component findComponent = findComponent(i);
        if (i + 8 <= findComponent.endOffset) {
            return findComponent.buf.getLongLE(i - findComponent.offset);
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (_getIntLE(i) & 4294967295L) | ((4294967295L & _getIntLE(i + 4)) << 32);
        }
        return ((_getIntLE(i) & 4294967295L) << 32) | (_getIntLE(i + 4) & 4294967295L);
    }

    @Override // io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, byte[] bArr, int i2, int i3) {
        checkDstIndex(i, i3, i2, bArr.length);
        if (i3 == 0) {
            return this;
        }
        int componentIndex = toComponentIndex(i);
        while (i3 > 0) {
            Component component = this.components.get(componentIndex);
            ByteBuf byteBuf = component.buf;
            int i4 = i - component.offset;
            int min = Math.min(i3, byteBuf.capacity() - i4);
            byteBuf.getBytes(i4, bArr, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            componentIndex++;
        }
        return this;
    }

    @Override // io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, ByteBuffer byteBuffer) {
        int limit = byteBuffer.limit();
        int remaining = byteBuffer.remaining();
        checkIndex(i, remaining);
        if (remaining == 0) {
            return this;
        }
        int componentIndex = toComponentIndex(i);
        while (remaining > 0) {
            try {
                Component component = this.components.get(componentIndex);
                ByteBuf byteBuf = component.buf;
                int i2 = i - component.offset;
                int min = Math.min(remaining, byteBuf.capacity() - i2);
                byteBuffer.limit(byteBuffer.position() + min);
                byteBuf.getBytes(i2, byteBuffer);
                i += min;
                remaining -= min;
                componentIndex++;
            } finally {
                byteBuffer.limit(limit);
            }
        }
        return this;
    }

    @Override // io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        checkDstIndex(i, i3, i2, byteBuf.capacity());
        if (i3 == 0) {
            return this;
        }
        int componentIndex = toComponentIndex(i);
        while (i3 > 0) {
            Component component = this.components.get(componentIndex);
            ByteBuf byteBuf2 = component.buf;
            int i4 = i - component.offset;
            int min = Math.min(i3, byteBuf2.capacity() - i4);
            byteBuf2.getBytes(i4, byteBuf, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            componentIndex++;
        }
        return this;
    }

    @Override // io.netty.buffer.ByteBuf
    public int getBytes(int i, GatheringByteChannel gatheringByteChannel, int i2) throws IOException {
        if (nioBufferCount() == 1) {
            return gatheringByteChannel.write(internalNioBuffer(i, i2));
        }
        long write = gatheringByteChannel.write(nioBuffers(i, i2));
        if (write > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) write;
    }

    @Override // io.netty.buffer.ByteBuf
    public int getBytes(int i, FileChannel fileChannel, long j, int i2) throws IOException {
        if (nioBufferCount() == 1) {
            return fileChannel.write(internalNioBuffer(i, i2), j);
        }
        long j2 = 0;
        for (int i3 = 0; i3 < nioBuffers(i, i2).length; i3++) {
            j2 += fileChannel.write(r7[i3], j + j2);
        }
        if (j2 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j2;
    }

    @Override // io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, OutputStream outputStream, int i2) throws IOException {
        checkIndex(i, i2);
        if (i2 == 0) {
            return this;
        }
        int componentIndex = toComponentIndex(i);
        while (i2 > 0) {
            Component component = this.components.get(componentIndex);
            ByteBuf byteBuf = component.buf;
            int i3 = i - component.offset;
            int min = Math.min(i2, byteBuf.capacity() - i3);
            byteBuf.getBytes(i3, outputStream, min);
            i += min;
            i2 -= min;
            componentIndex++;
        }
        return this;
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf setByte(int i, int i2) {
        Component findComponent = findComponent(i);
        findComponent.buf.setByte(i - findComponent.offset, i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public void _setByte(int i, int i2) {
        setByte(i, i2);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf setShort(int i, int i2) {
        return (CompositeByteBuf) super.setShort(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public void _setShort(int i, int i2) {
        Component findComponent = findComponent(i);
        if (i + 2 <= findComponent.endOffset) {
            findComponent.buf.setShort(i - findComponent.offset, i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setByte(i, (byte) (i2 >>> 8));
            _setByte(i + 1, (byte) i2);
        } else {
            _setByte(i, (byte) i2);
            _setByte(i + 1, (byte) (i2 >>> 8));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public void _setShortLE(int i, int i2) {
        Component findComponent = findComponent(i);
        if (i + 2 <= findComponent.endOffset) {
            findComponent.buf.setShortLE(i - findComponent.offset, i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setByte(i, (byte) i2);
            _setByte(i + 1, (byte) (i2 >>> 8));
        } else {
            _setByte(i, (byte) (i2 >>> 8));
            _setByte(i + 1, (byte) i2);
        }
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf setMedium(int i, int i2) {
        return (CompositeByteBuf) super.setMedium(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public void _setMedium(int i, int i2) {
        Component findComponent = findComponent(i);
        if (i + 3 <= findComponent.endOffset) {
            findComponent.buf.setMedium(i - findComponent.offset, i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setShort(i, (short) (i2 >> 8));
            _setByte(i + 2, (byte) i2);
        } else {
            _setShort(i, (short) i2);
            _setByte(i + 2, (byte) (i2 >>> 16));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public void _setMediumLE(int i, int i2) {
        Component findComponent = findComponent(i);
        if (i + 3 <= findComponent.endOffset) {
            findComponent.buf.setMediumLE(i - findComponent.offset, i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setShortLE(i, (short) i2);
            _setByte(i + 2, (byte) (i2 >>> 16));
        } else {
            _setShortLE(i, (short) (i2 >> 8));
            _setByte(i + 2, (byte) i2);
        }
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf setInt(int i, int i2) {
        return (CompositeByteBuf) super.setInt(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public void _setInt(int i, int i2) {
        Component findComponent = findComponent(i);
        if (i + 4 <= findComponent.endOffset) {
            findComponent.buf.setInt(i - findComponent.offset, i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setShort(i, (short) (i2 >>> 16));
            _setShort(i + 2, (short) i2);
        } else {
            _setShort(i, (short) i2);
            _setShort(i + 2, (short) (i2 >>> 16));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public void _setIntLE(int i, int i2) {
        Component findComponent = findComponent(i);
        if (i + 4 <= findComponent.endOffset) {
            findComponent.buf.setIntLE(i - findComponent.offset, i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setShortLE(i, (short) i2);
            _setShortLE(i + 2, (short) (i2 >>> 16));
        } else {
            _setShortLE(i, (short) (i2 >>> 16));
            _setShortLE(i + 2, (short) i2);
        }
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf setLong(int i, long j) {
        return (CompositeByteBuf) super.setLong(i, j);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public void _setLong(int i, long j) {
        Component findComponent = findComponent(i);
        if (i + 8 <= findComponent.endOffset) {
            findComponent.buf.setLong(i - findComponent.offset, j);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setInt(i, (int) (j >>> 32));
            _setInt(i + 4, (int) j);
        } else {
            _setInt(i, (int) j);
            _setInt(i + 4, (int) (j >>> 32));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractByteBuf
    public void _setLongLE(int i, long j) {
        Component findComponent = findComponent(i);
        if (i + 8 <= findComponent.endOffset) {
            findComponent.buf.setLongLE(i - findComponent.offset, j);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setIntLE(i, (int) j);
            _setIntLE(i + 4, (int) (j >>> 32));
        } else {
            _setIntLE(i, (int) (j >>> 32));
            _setIntLE(i + 4, (int) j);
        }
    }

    @Override // io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, byte[] bArr, int i2, int i3) {
        checkSrcIndex(i, i3, i2, bArr.length);
        if (i3 == 0) {
            return this;
        }
        int componentIndex = toComponentIndex(i);
        while (i3 > 0) {
            Component component = this.components.get(componentIndex);
            ByteBuf byteBuf = component.buf;
            int i4 = i - component.offset;
            int min = Math.min(i3, byteBuf.capacity() - i4);
            byteBuf.setBytes(i4, bArr, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            componentIndex++;
        }
        return this;
    }

    @Override // io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, ByteBuffer byteBuffer) {
        int limit = byteBuffer.limit();
        int remaining = byteBuffer.remaining();
        checkIndex(i, remaining);
        if (remaining == 0) {
            return this;
        }
        int componentIndex = toComponentIndex(i);
        while (remaining > 0) {
            try {
                Component component = this.components.get(componentIndex);
                ByteBuf byteBuf = component.buf;
                int i2 = i - component.offset;
                int min = Math.min(remaining, byteBuf.capacity() - i2);
                byteBuffer.limit(byteBuffer.position() + min);
                byteBuf.setBytes(i2, byteBuffer);
                i += min;
                remaining -= min;
                componentIndex++;
            } finally {
                byteBuffer.limit(limit);
            }
        }
        return this;
    }

    @Override // io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        checkSrcIndex(i, i3, i2, byteBuf.capacity());
        if (i3 == 0) {
            return this;
        }
        int componentIndex = toComponentIndex(i);
        while (i3 > 0) {
            Component component = this.components.get(componentIndex);
            ByteBuf byteBuf2 = component.buf;
            int i4 = i - component.offset;
            int min = Math.min(i3, byteBuf2.capacity() - i4);
            byteBuf2.setBytes(i4, byteBuf, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            componentIndex++;
        }
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0042 A[EDGE_INSN: B:12:0x0042->B:13:0x0042 BREAK  A[LOOP:0: B:7:0x0011->B:14:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[LOOP:0: B:7:0x0011->B:14:?, LOOP_END, SYNTHETIC] */
    @Override // io.netty.buffer.ByteBuf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int setBytes(int i, InputStream inputStream, int i2) throws IOException {
        checkIndex(i, i2);
        if (i2 == 0) {
            return inputStream.read(EmptyArrays.EMPTY_BYTES);
        }
        int componentIndex = toComponentIndex(i);
        int i3 = 0;
        while (true) {
            Component component = this.components.get(componentIndex);
            ByteBuf byteBuf = component.buf;
            int i4 = i - component.offset;
            int min = Math.min(i2, byteBuf.capacity() - i4);
            if (min != 0) {
                int bytes = byteBuf.setBytes(i4, inputStream, min);
                if (bytes < 0) {
                    if (i3 == 0) {
                        return -1;
                    }
                } else if (bytes == min) {
                    i += min;
                    i2 -= min;
                    i3 += min;
                } else {
                    i += bytes;
                    i2 -= bytes;
                    i3 += bytes;
                    if (i2 > 0) {
                        break;
                    }
                }
            }
            componentIndex++;
            if (i2 > 0) {
            }
        }
        return i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0045 A[EDGE_INSN: B:12:0x0045->B:13:0x0045 BREAK  A[LOOP:0: B:7:0x0011->B:14:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[LOOP:0: B:7:0x0011->B:14:?, LOOP_END, SYNTHETIC] */
    @Override // io.netty.buffer.ByteBuf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int setBytes(int i, ScatteringByteChannel scatteringByteChannel, int i2) throws IOException {
        checkIndex(i, i2);
        if (i2 == 0) {
            return scatteringByteChannel.read(EMPTY_NIO_BUFFER);
        }
        int componentIndex = toComponentIndex(i);
        int i3 = 0;
        while (true) {
            Component component = this.components.get(componentIndex);
            ByteBuf byteBuf = component.buf;
            int i4 = i - component.offset;
            int min = Math.min(i2, byteBuf.capacity() - i4);
            if (min != 0) {
                int bytes = byteBuf.setBytes(i4, scatteringByteChannel, min);
                if (bytes == 0) {
                    break;
                }
                if (bytes < 0) {
                    if (i3 == 0) {
                        return -1;
                    }
                } else if (bytes == min) {
                    i += min;
                    i2 -= min;
                    i3 += min;
                } else {
                    i += bytes;
                    i2 -= bytes;
                    i3 += bytes;
                    if (i2 > 0) {
                        break;
                    }
                }
            }
            componentIndex++;
            if (i2 > 0) {
            }
        }
        return i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004b A[EDGE_INSN: B:12:0x004b->B:13:0x004b BREAK  A[LOOP:0: B:7:0x0011->B:14:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[LOOP:0: B:7:0x0011->B:14:?, LOOP_END, SYNTHETIC] */
    @Override // io.netty.buffer.ByteBuf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int setBytes(int i, FileChannel fileChannel, long j, int i2) throws IOException {
        checkIndex(i, i2);
        if (i2 == 0) {
            return fileChannel.read(EMPTY_NIO_BUFFER, j);
        }
        int componentIndex = toComponentIndex(i);
        int i3 = 0;
        while (true) {
            Component component = this.components.get(componentIndex);
            ByteBuf byteBuf = component.buf;
            int i4 = i - component.offset;
            int min = Math.min(i2, byteBuf.capacity() - i4);
            if (min != 0) {
                int bytes = byteBuf.setBytes(i4, fileChannel, j + i3, min);
                if (bytes == 0) {
                    break;
                }
                if (bytes < 0) {
                    if (i3 == 0) {
                        return -1;
                    }
                } else if (bytes == min) {
                    i += min;
                    i2 -= min;
                    i3 += min;
                } else {
                    i += bytes;
                    i2 -= bytes;
                    i3 += bytes;
                    if (i2 > 0) {
                        break;
                    }
                }
            }
            componentIndex++;
            if (i2 > 0) {
            }
        }
        return i3;
    }

    @Override // io.netty.buffer.ByteBuf
    public ByteBuf copy(int i, int i2) {
        checkIndex(i, i2);
        ByteBuf allocBuffer = allocBuffer(i2);
        if (i2 != 0) {
            copyTo(i, i2, toComponentIndex(i), allocBuffer);
        }
        return allocBuffer;
    }

    private void copyTo(int i, int i2, int i3, ByteBuf byteBuf) {
        int i4 = 0;
        while (i2 > 0) {
            Component component = this.components.get(i3);
            ByteBuf byteBuf2 = component.buf;
            int i5 = i - component.offset;
            int min = Math.min(i2, byteBuf2.capacity() - i5);
            byteBuf2.getBytes(i5, byteBuf, i4, min);
            i += min;
            i4 += min;
            i2 -= min;
            i3++;
        }
        byteBuf.writerIndex(byteBuf.capacity());
    }

    public ByteBuf component(int i) {
        return internalComponent(i).duplicate();
    }

    public ByteBuf componentAtOffset(int i) {
        return internalComponentAtOffset(i).duplicate();
    }

    public ByteBuf internalComponent(int i) {
        checkComponentIndex(i);
        return this.components.get(i).buf;
    }

    public ByteBuf internalComponentAtOffset(int i) {
        return findComponent(i).buf;
    }

    private Component findComponent(int i) {
        checkIndex(i);
        int size = this.components.size();
        int i2 = 0;
        while (i2 <= size) {
            int i3 = (i2 + size) >>> 1;
            Component component = this.components.get(i3);
            if (i >= component.endOffset) {
                i2 = i3 + 1;
            } else {
                if (i >= component.offset) {
                    return component;
                }
                size = i3 - 1;
            }
        }
        throw new Error("should not reach here");
    }

    @Override // io.netty.buffer.ByteBuf
    public int nioBufferCount() {
        int size = this.components.size();
        if (size == 0) {
            return 1;
        }
        if (size == 1) {
            return this.components.get(0).buf.nioBufferCount();
        }
        int size2 = this.components.size();
        int i = 0;
        for (int i2 = 0; i2 < size2; i2++) {
            i += this.components.get(i2).buf.nioBufferCount();
        }
        return i;
    }

    @Override // io.netty.buffer.ByteBuf
    public ByteBuffer internalNioBuffer(int i, int i2) {
        int size = this.components.size();
        if (size == 0) {
            return EMPTY_NIO_BUFFER;
        }
        if (size == 1) {
            return this.components.get(0).buf.internalNioBuffer(i, i2);
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.netty.buffer.ByteBuf
    public ByteBuffer nioBuffer(int i, int i2) {
        checkIndex(i, i2);
        int size = this.components.size();
        if (size == 0) {
            return EMPTY_NIO_BUFFER;
        }
        if (size == 1 && this.components.get(0).buf.nioBufferCount() == 1) {
            return this.components.get(0).buf.nioBuffer(i, i2);
        }
        ByteBuffer order = ByteBuffer.allocate(i2).order(order());
        for (ByteBuffer byteBuffer : nioBuffers(i, i2)) {
            order.put(byteBuffer);
        }
        order.flip();
        return order;
    }

    @Override // io.netty.buffer.ByteBuf
    public ByteBuffer[] nioBuffers(int i, int i2) {
        checkIndex(i, i2);
        if (i2 == 0) {
            return new ByteBuffer[]{EMPTY_NIO_BUFFER};
        }
        ArrayList arrayList = new ArrayList(this.components.size());
        int componentIndex = toComponentIndex(i);
        while (i2 > 0) {
            Component component = this.components.get(componentIndex);
            ByteBuf byteBuf = component.buf;
            int i3 = i - component.offset;
            int min = Math.min(i2, byteBuf.capacity() - i3);
            int nioBufferCount = byteBuf.nioBufferCount();
            if (nioBufferCount == 0) {
                throw new UnsupportedOperationException();
            }
            if (nioBufferCount == 1) {
                arrayList.add(byteBuf.nioBuffer(i3, min));
            } else {
                Collections.addAll(arrayList, byteBuf.nioBuffers(i3, min));
            }
            i += min;
            i2 -= min;
            componentIndex++;
        }
        return (ByteBuffer[]) arrayList.toArray(new ByteBuffer[arrayList.size()]);
    }

    public CompositeByteBuf consolidate() {
        ensureAccessible();
        int numComponents = numComponents();
        if (numComponents <= 1) {
            return this;
        }
        ByteBuf allocBuffer = allocBuffer(this.components.get(numComponents - 1).endOffset);
        for (int i = 0; i < numComponents; i++) {
            Component component = this.components.get(i);
            allocBuffer.writeBytes(component.buf);
            component.freeIfNecessary();
        }
        this.components.clear();
        this.components.add(new Component(allocBuffer));
        updateComponentOffsets(0);
        return this;
    }

    public CompositeByteBuf consolidate(int i, int i2) {
        checkComponentIndex(i, i2);
        if (i2 <= 1) {
            return this;
        }
        int i3 = i2 + i;
        ByteBuf allocBuffer = allocBuffer(this.components.get(i3 - 1).endOffset - this.components.get(i).offset);
        for (int i4 = i; i4 < i3; i4++) {
            Component component = this.components.get(i4);
            allocBuffer.writeBytes(component.buf);
            component.freeIfNecessary();
        }
        this.components.removeRange(i + 1, i3);
        this.components.set(i, new Component(allocBuffer));
        updateComponentOffsets(i);
        return this;
    }

    public CompositeByteBuf discardReadComponents() {
        ensureAccessible();
        int readerIndex = readerIndex();
        if (readerIndex == 0) {
            return this;
        }
        int writerIndex = writerIndex();
        if (readerIndex == writerIndex && writerIndex == capacity()) {
            int size = this.components.size();
            for (int i = 0; i < size; i++) {
                this.components.get(i).freeIfNecessary();
            }
            this.components.clear();
            setIndex(0, 0);
            adjustMarkers(readerIndex);
            return this;
        }
        int componentIndex = toComponentIndex(readerIndex);
        for (int i2 = 0; i2 < componentIndex; i2++) {
            this.components.get(i2).freeIfNecessary();
        }
        this.components.removeRange(0, componentIndex);
        int i3 = this.components.get(0).offset;
        updateComponentOffsets(0);
        setIndex(readerIndex - i3, writerIndex - i3);
        adjustMarkers(i3);
        return this;
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf discardReadBytes() {
        ensureAccessible();
        int readerIndex = readerIndex();
        if (readerIndex == 0) {
            return this;
        }
        int writerIndex = writerIndex();
        if (readerIndex == writerIndex && writerIndex == capacity()) {
            int size = this.components.size();
            for (int i = 0; i < size; i++) {
                this.components.get(i).freeIfNecessary();
            }
            this.components.clear();
            setIndex(0, 0);
            adjustMarkers(readerIndex);
            return this;
        }
        int componentIndex = toComponentIndex(readerIndex);
        for (int i2 = 0; i2 < componentIndex; i2++) {
            this.components.get(i2).freeIfNecessary();
        }
        Component component = this.components.get(componentIndex);
        int i3 = readerIndex - component.offset;
        if (i3 == component.length) {
            componentIndex++;
        } else {
            this.components.set(componentIndex, new Component(component.buf.slice(i3, component.length - i3)));
        }
        this.components.removeRange(0, componentIndex);
        updateComponentOffsets(0);
        setIndex(0, writerIndex - readerIndex);
        adjustMarkers(readerIndex);
        return this;
    }

    private ByteBuf allocBuffer(int i) {
        return this.direct ? alloc().directBuffer(i) : alloc().heapBuffer(i);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public String toString() {
        return super.toString().substring(0, r0.length() - 1) + ", components=" + this.components.size() + ')';
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes.dex */
    public static final class Component {
        final ByteBuf buf;
        int endOffset;
        final int length;
        int offset;

        Component(ByteBuf byteBuf) {
            this.buf = byteBuf;
            this.length = byteBuf.readableBytes();
        }

        void freeIfNecessary() {
            this.buf.release();
        }
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf readerIndex(int i) {
        return (CompositeByteBuf) super.readerIndex(i);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf writerIndex(int i) {
        return (CompositeByteBuf) super.writerIndex(i);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf setIndex(int i, int i2) {
        return (CompositeByteBuf) super.setIndex(i, i2);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf clear() {
        return (CompositeByteBuf) super.clear();
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf markReaderIndex() {
        return (CompositeByteBuf) super.markReaderIndex();
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf resetReaderIndex() {
        return (CompositeByteBuf) super.resetReaderIndex();
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf markWriterIndex() {
        return (CompositeByteBuf) super.markWriterIndex();
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf resetWriterIndex() {
        return (CompositeByteBuf) super.resetWriterIndex();
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf ensureWritable(int i) {
        return (CompositeByteBuf) super.ensureWritable(i);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, ByteBuf byteBuf) {
        return (CompositeByteBuf) super.getBytes(i, byteBuf);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, ByteBuf byteBuf, int i2) {
        return (CompositeByteBuf) super.getBytes(i, byteBuf, i2);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, byte[] bArr) {
        return (CompositeByteBuf) super.getBytes(i, bArr);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf setBoolean(int i, boolean z) {
        return (CompositeByteBuf) super.setBoolean(i, z);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf setChar(int i, int i2) {
        return (CompositeByteBuf) super.setChar(i, i2);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf setFloat(int i, float f) {
        return (CompositeByteBuf) super.setFloat(i, f);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf setDouble(int i, double d) {
        return (CompositeByteBuf) super.setDouble(i, d);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, ByteBuf byteBuf) {
        return (CompositeByteBuf) super.setBytes(i, byteBuf);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, ByteBuf byteBuf, int i2) {
        return (CompositeByteBuf) super.setBytes(i, byteBuf, i2);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, byte[] bArr) {
        return (CompositeByteBuf) super.setBytes(i, bArr);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf setZero(int i, int i2) {
        return (CompositeByteBuf) super.setZero(i, i2);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(ByteBuf byteBuf) {
        return (CompositeByteBuf) super.readBytes(byteBuf);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(ByteBuf byteBuf, int i) {
        return (CompositeByteBuf) super.readBytes(byteBuf, i);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(ByteBuf byteBuf, int i, int i2) {
        return (CompositeByteBuf) super.readBytes(byteBuf, i, i2);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(byte[] bArr) {
        return (CompositeByteBuf) super.readBytes(bArr);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(byte[] bArr, int i, int i2) {
        return (CompositeByteBuf) super.readBytes(bArr, i, i2);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(ByteBuffer byteBuffer) {
        return (CompositeByteBuf) super.readBytes(byteBuffer);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(OutputStream outputStream, int i) throws IOException {
        return (CompositeByteBuf) super.readBytes(outputStream, i);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf skipBytes(int i) {
        return (CompositeByteBuf) super.skipBytes(i);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBoolean(boolean z) {
        return (CompositeByteBuf) super.writeBoolean(z);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf writeByte(int i) {
        return (CompositeByteBuf) super.writeByte(i);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf writeShort(int i) {
        return (CompositeByteBuf) super.writeShort(i);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf writeMedium(int i) {
        return (CompositeByteBuf) super.writeMedium(i);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf writeInt(int i) {
        return (CompositeByteBuf) super.writeInt(i);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf writeLong(long j) {
        return (CompositeByteBuf) super.writeLong(j);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf writeChar(int i) {
        return (CompositeByteBuf) super.writeChar(i);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf writeFloat(float f) {
        return (CompositeByteBuf) super.writeFloat(f);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf writeDouble(double d) {
        return (CompositeByteBuf) super.writeDouble(d);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(ByteBuf byteBuf) {
        return (CompositeByteBuf) super.writeBytes(byteBuf);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(ByteBuf byteBuf, int i) {
        return (CompositeByteBuf) super.writeBytes(byteBuf, i);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(ByteBuf byteBuf, int i, int i2) {
        return (CompositeByteBuf) super.writeBytes(byteBuf, i, i2);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(byte[] bArr) {
        return (CompositeByteBuf) super.writeBytes(bArr);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(byte[] bArr, int i, int i2) {
        return (CompositeByteBuf) super.writeBytes(bArr, i, i2);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(ByteBuffer byteBuffer) {
        return (CompositeByteBuf) super.writeBytes(byteBuffer);
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf writeZero(int i) {
        return (CompositeByteBuf) super.writeZero(i);
    }

    @Override // io.netty.buffer.AbstractReferenceCountedByteBuf, io.netty.buffer.ByteBuf, io.netty.util.ReferenceCounted
    public CompositeByteBuf retain(int i) {
        return (CompositeByteBuf) super.retain(i);
    }

    @Override // io.netty.buffer.AbstractReferenceCountedByteBuf, io.netty.buffer.ByteBuf, io.netty.util.ReferenceCounted
    public CompositeByteBuf retain() {
        return (CompositeByteBuf) super.retain();
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public ByteBuffer[] nioBuffers() {
        return nioBuffers(readerIndex(), readableBytes());
    }

    @Override // io.netty.buffer.AbstractByteBuf, io.netty.buffer.ByteBuf
    public CompositeByteBuf discardSomeReadBytes() {
        return discardReadComponents();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.AbstractReferenceCountedByteBuf
    public void deallocate() {
        if (this.freed) {
            return;
        }
        this.freed = true;
        int size = this.components.size();
        for (int i = 0; i < size; i++) {
            this.components.get(i).freeIfNecessary();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes.dex */
    private final class CompositeByteBufIterator implements Iterator<ByteBuf> {
        private int index;
        private final int size;

        private CompositeByteBufIterator() {
            this.size = CompositeByteBuf.this.components.size();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.size > this.index;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public ByteBuf next() {
            if (this.size != CompositeByteBuf.this.components.size()) {
                throw new ConcurrentModificationException();
            }
            if (hasNext()) {
                try {
                    ComponentList componentList = CompositeByteBuf.this.components;
                    int i = this.index;
                    this.index = i + 1;
                    return componentList.get(i).buf;
                } catch (IndexOutOfBoundsException unused) {
                    throw new ConcurrentModificationException();
                }
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read-Only");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class ComponentList extends ArrayList<Component> {
        ComponentList(int i) {
            super(i);
        }

        @Override // java.util.ArrayList, java.util.AbstractList
        public void removeRange(int i, int i2) {
            super.removeRange(i, i2);
        }
    }
}
