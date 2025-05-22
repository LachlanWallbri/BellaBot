package io.grpc.netty.shaded.io.netty.buffer;

import com.iflytek.cloud.SpeechEvent;
import io.grpc.netty.shaded.io.netty.util.ByteProcessor;
import io.grpc.netty.shaded.io.netty.util.IllegalReferenceCountException;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.internal.EmptyArrays;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.RecyclableArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.UShort;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class CompositeByteBuf extends AbstractReferenceCountedByteBuf implements Iterable<ByteBuf> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final ByteBufAllocator alloc;
    private int componentCount;
    private Component[] components;
    private final boolean direct;
    private boolean freed;
    private Component lastAccessed;
    private final int maxNumComponents;
    private static final ByteBuffer EMPTY_NIO_BUFFER = Unpooled.EMPTY_BUFFER.nioBuffer();
    private static final Iterator<ByteBuf> EMPTY_ITERATOR = Collections.emptyList().iterator();
    static final ByteWrapper<byte[]> BYTE_ARRAY_WRAPPER = new ByteWrapper<byte[]>() { // from class: io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.1
        @Override // io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.ByteWrapper
        public ByteBuf wrap(byte[] bArr) {
            return Unpooled.wrappedBuffer(bArr);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.ByteWrapper
        public boolean isEmpty(byte[] bArr) {
            return bArr.length == 0;
        }
    };
    static final ByteWrapper<ByteBuffer> BYTE_BUFFER_WRAPPER = new ByteWrapper<ByteBuffer>() { // from class: io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.2
        @Override // io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.ByteWrapper
        public ByteBuf wrap(ByteBuffer byteBuffer) {
            return Unpooled.wrappedBuffer(byteBuffer);
        }

        @Override // io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf.ByteWrapper
        public boolean isEmpty(ByteBuffer byteBuffer) {
            return !byteBuffer.hasRemaining();
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface ByteWrapper<T> {
        boolean isEmpty(T t);

        ByteBuf wrap(T t);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public CompositeByteBuf touch() {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public CompositeByteBuf touch(Object obj) {
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf unwrap() {
        return null;
    }

    private CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, int i2) {
        super(Integer.MAX_VALUE);
        this.alloc = (ByteBufAllocator) ObjectUtil.checkNotNull(byteBufAllocator, "alloc");
        if (i < 1) {
            throw new IllegalArgumentException("maxNumComponents: " + i + " (expected: >= 1)");
        }
        this.direct = z;
        this.maxNumComponents = i;
        this.components = newCompArray(i2, i);
    }

    public CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i) {
        this(byteBufAllocator, z, i, 0);
    }

    public CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, ByteBuf... byteBufArr) {
        this(byteBufAllocator, z, i, byteBufArr, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, ByteBuf[] byteBufArr, int i2) {
        this(byteBufAllocator, z, i, byteBufArr.length - i2);
        addComponents0(false, 0, byteBufArr, i2);
        consolidateIfNeeded();
        setIndex0(0, capacity());
    }

    public CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, Iterable<ByteBuf> iterable) {
        this(byteBufAllocator, z, i, iterable instanceof Collection ? ((Collection) iterable).size() : 0);
        addComponents(false, 0, iterable);
        setIndex(0, capacity());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <T> CompositeByteBuf(ByteBufAllocator byteBufAllocator, boolean z, int i, ByteWrapper<T> byteWrapper, T[] tArr, int i2) {
        this(byteBufAllocator, z, i, tArr.length - i2);
        addComponents0(false, 0, byteWrapper, tArr, i2);
        consolidateIfNeeded();
        setIndex(0, capacity());
    }

    private static Component[] newCompArray(int i, int i2) {
        return new Component[Math.max(i, Math.min(16, i2))];
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
        return addComponent(z, this.componentCount, byteBuf);
    }

    public CompositeByteBuf addComponents(boolean z, ByteBuf... byteBufArr) {
        ObjectUtil.checkNotNull(byteBufArr, "buffers");
        addComponents0(z, this.componentCount, byteBufArr, 0);
        consolidateIfNeeded();
        return this;
    }

    public CompositeByteBuf addComponents(boolean z, Iterable<ByteBuf> iterable) {
        return addComponents(z, this.componentCount, iterable);
    }

    public CompositeByteBuf addComponent(boolean z, int i, ByteBuf byteBuf) {
        ObjectUtil.checkNotNull(byteBuf, SpeechEvent.KEY_EVENT_TTS_BUFFER);
        addComponent0(z, i, byteBuf);
        consolidateIfNeeded();
        return this;
    }

    private static void checkForOverflow(int i, int i2) {
        if (i + i2 >= 0) {
            return;
        }
        throw new IllegalArgumentException("Can't increase by " + i2 + " as capacity(" + i + ") would overflow 2147483647");
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0037 A[Catch: all -> 0x0026, TRY_LEAVE, TryCatch #0 {all -> 0x0026, blocks: (B:18:0x001d, B:20:0x0022, B:8:0x0037, B:6:0x002a), top: B:17:0x001d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int addComponent0(boolean z, int i, ByteBuf byteBuf) {
        boolean z2 = true;
        try {
            checkComponentIndex(i);
            Component newComponent = newComponent(ensureAccessible(byteBuf), 0);
            int length = newComponent.length();
            checkForOverflow(capacity(), length);
            addComp(i, newComponent);
            if (length > 0) {
                try {
                    if (i < this.componentCount - 1) {
                        updateComponentOffsets(i);
                        if (z) {
                            this.writerIndex += length;
                        }
                        return i;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (!z2) {
                        byteBuf.release();
                    }
                    throw th;
                }
            }
            if (i > 0) {
                newComponent.reposition(this.components[i - 1].endOffset);
            }
            if (z) {
            }
            return i;
        } catch (Throwable th2) {
            th = th2;
            z2 = false;
        }
    }

    private static ByteBuf ensureAccessible(ByteBuf byteBuf) {
        if (!checkAccessible || byteBuf.isAccessible()) {
            return byteBuf;
        }
        throw new IllegalReferenceCountException(0);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Component newComponent(ByteBuf byteBuf, int i) {
        int i2;
        int i3;
        int readerIndex = byteBuf.readerIndex();
        int readableBytes = byteBuf.readableBytes();
        ByteBuf byteBuf2 = byteBuf;
        while (true) {
            if (!(byteBuf2 instanceof WrappedByteBuf) && !(byteBuf2 instanceof SwappedByteBuf)) {
                break;
            }
            byteBuf2 = byteBuf2.unwrap();
        }
        if (byteBuf2 instanceof AbstractUnpooledSlicedByteBuf) {
            i3 = ((AbstractUnpooledSlicedByteBuf) byteBuf2).idx(0) + readerIndex;
            byteBuf2 = byteBuf2.unwrap();
        } else if (byteBuf2 instanceof PooledSlicedByteBuf) {
            i3 = ((PooledSlicedByteBuf) byteBuf2).adjustment + readerIndex;
            byteBuf2 = byteBuf2.unwrap();
        } else {
            if ((byteBuf2 instanceof DuplicatedByteBuf) || (byteBuf2 instanceof PooledDuplicatedByteBuf)) {
                byteBuf2 = byteBuf2.unwrap();
            }
            i2 = readerIndex;
            return new Component(byteBuf.order(ByteOrder.BIG_ENDIAN), readerIndex, byteBuf2.order(ByteOrder.BIG_ENDIAN), i2, i, readableBytes, byteBuf.capacity() != readableBytes ? byteBuf : null);
        }
        i2 = i3;
        return new Component(byteBuf.order(ByteOrder.BIG_ENDIAN), readerIndex, byteBuf2.order(ByteOrder.BIG_ENDIAN), i2, i, readableBytes, byteBuf.capacity() != readableBytes ? byteBuf : null);
    }

    public CompositeByteBuf addComponents(int i, ByteBuf... byteBufArr) {
        ObjectUtil.checkNotNull(byteBufArr, "buffers");
        addComponents0(false, i, byteBufArr, 0);
        consolidateIfNeeded();
        return this;
    }

    private CompositeByteBuf addComponents0(boolean z, int i, ByteBuf[] byteBufArr, int i2) {
        int length = byteBufArr.length;
        int i3 = length - i2;
        int capacity = capacity();
        int i4 = 0;
        for (ByteBuf byteBuf : byteBufArr) {
            i4 += byteBuf.readableBytes();
            checkForOverflow(capacity, i4);
        }
        int i5 = Integer.MAX_VALUE;
        try {
            checkComponentIndex(i);
            shiftComps(i, i3);
            int i6 = i > 0 ? this.components[i - 1].endOffset : 0;
            i5 = i;
            while (i2 < length) {
                ByteBuf byteBuf2 = byteBufArr[i2];
                if (byteBuf2 == null) {
                    break;
                }
                Component newComponent = newComponent(ensureAccessible(byteBuf2), i6);
                this.components[i5] = newComponent;
                i6 = newComponent.endOffset;
                i2++;
                i5++;
            }
            return this;
        } finally {
            if (i5 < this.componentCount) {
                int i7 = i3 + i;
                if (i5 < i7) {
                    removeCompRange(i5, i7);
                    while (i2 < length) {
                        ReferenceCountUtil.safeRelease(byteBufArr[i2]);
                        i2++;
                    }
                }
                updateComponentOffsets(i5);
            }
            if (z && i5 > i && i5 <= this.componentCount) {
                this.writerIndex += this.components[i5 - 1].endOffset - this.components[i].offset;
            }
        }
    }

    private <T> int addComponents0(boolean z, int i, ByteWrapper<T> byteWrapper, T[] tArr, int i2) {
        int i3;
        checkComponentIndex(i);
        int length = tArr.length;
        while (i2 < length) {
            T t = tArr[i2];
            if (t == null) {
                break;
            }
            if (!byteWrapper.isEmpty(t) && (i = addComponent0(z, i, byteWrapper.wrap(t)) + 1) > (i3 = this.componentCount)) {
                i = i3;
            }
            i2++;
        }
        return i;
    }

    public CompositeByteBuf addComponents(int i, Iterable<ByteBuf> iterable) {
        return addComponents(false, i, iterable);
    }

    public CompositeByteBuf addFlattenedComponents(boolean z, ByteBuf byteBuf) {
        CompositeByteBuf compositeByteBuf;
        int i;
        int i2;
        Component[] componentArr;
        int i3;
        ObjectUtil.checkNotNull(byteBuf, SpeechEvent.KEY_EVENT_TTS_BUFFER);
        int readerIndex = byteBuf.readerIndex();
        int writerIndex = byteBuf.writerIndex();
        if (readerIndex == writerIndex) {
            byteBuf.release();
            return this;
        }
        if (!(byteBuf instanceof CompositeByteBuf)) {
            addComponent0(z, this.componentCount, byteBuf);
            consolidateIfNeeded();
            return this;
        }
        if (byteBuf instanceof WrappedCompositeByteBuf) {
            compositeByteBuf = (CompositeByteBuf) byteBuf.unwrap();
        } else {
            compositeByteBuf = (CompositeByteBuf) byteBuf;
        }
        int i4 = writerIndex - readerIndex;
        compositeByteBuf.checkIndex(readerIndex, i4);
        Component[] componentArr2 = compositeByteBuf.components;
        int i5 = this.componentCount;
        int i6 = this.writerIndex;
        try {
            int componentIndex0 = compositeByteBuf.toComponentIndex0(readerIndex);
            int capacity = capacity();
            while (true) {
                Component component = componentArr2[componentIndex0];
                int max = Math.max(readerIndex, component.offset);
                int min = Math.min(writerIndex, component.endOffset);
                int i7 = min - max;
                if (i7 > 0) {
                    i2 = readerIndex;
                    componentArr = componentArr2;
                    i = i5;
                    i3 = min;
                    try {
                        addComp(this.componentCount, new Component(component.srcBuf.retain(), component.srcIdx(max), component.buf, component.idx(max), capacity, i7, null));
                    } catch (Throwable th) {
                        th = th;
                        if (byteBuf != null) {
                            if (z) {
                                this.writerIndex = i6;
                            }
                            int i8 = i;
                            for (int i9 = this.componentCount - 1; i9 >= i8; i9--) {
                                this.components[i9].free();
                                removeComp(i9);
                            }
                        }
                        throw th;
                    }
                } else {
                    i2 = readerIndex;
                    componentArr = componentArr2;
                    i = i5;
                    i3 = min;
                }
                if (writerIndex == i3) {
                    break;
                }
                capacity += i7;
                componentIndex0++;
                readerIndex = i2;
                componentArr2 = componentArr;
                i5 = i;
            }
            if (z) {
                this.writerIndex = i4 + i6;
            }
            consolidateIfNeeded();
            byteBuf.release();
            return this;
        } catch (Throwable th2) {
            th = th2;
            i = i5;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private CompositeByteBuf addComponents(boolean z, int i, Iterable<ByteBuf> iterable) {
        ByteBuf byteBuf;
        if (iterable instanceof ByteBuf) {
            return addComponent(z, i, (ByteBuf) iterable);
        }
        ObjectUtil.checkNotNull(iterable, "buffers");
        Iterator it = iterable.iterator();
        try {
            checkComponentIndex(i);
            while (it.hasNext() && (byteBuf = (ByteBuf) it.next()) != null) {
                i = Math.min(addComponent0(z, i, byteBuf) + 1, this.componentCount);
            }
            consolidateIfNeeded();
            return this;
        } finally {
            while (it.hasNext()) {
                ReferenceCountUtil.safeRelease(it.next());
            }
        }
    }

    private void consolidateIfNeeded() {
        int i = this.componentCount;
        if (i > this.maxNumComponents) {
            consolidate0(0, i);
        }
    }

    private void checkComponentIndex(int i) {
        ensureAccessible();
        if (i < 0 || i > this.componentCount) {
            throw new IndexOutOfBoundsException(String.format("cIndex: %d (expected: >= 0 && <= numComponents(%d))", Integer.valueOf(i), Integer.valueOf(this.componentCount)));
        }
    }

    private void checkComponentIndex(int i, int i2) {
        ensureAccessible();
        if (i < 0 || i + i2 > this.componentCount) {
            throw new IndexOutOfBoundsException(String.format("cIndex: %d, numComponents: %d (expected: cIndex >= 0 && cIndex + numComponents <= totalNumComponents(%d))", Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(this.componentCount)));
        }
    }

    private void updateComponentOffsets(int i) {
        int i2 = this.componentCount;
        if (i2 <= i) {
            return;
        }
        int i3 = i > 0 ? this.components[i - 1].endOffset : 0;
        while (i < i2) {
            Component component = this.components[i];
            component.reposition(i3);
            i3 = component.endOffset;
            i++;
        }
    }

    public CompositeByteBuf removeComponent(int i) {
        checkComponentIndex(i);
        Component component = this.components[i];
        if (this.lastAccessed == component) {
            this.lastAccessed = null;
        }
        component.free();
        removeComp(i);
        if (component.length() > 0) {
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
            Component component = this.components[i4];
            if (component.length() > 0) {
                z = true;
            }
            if (this.lastAccessed == component) {
                this.lastAccessed = null;
            }
            component.free();
        }
        removeCompRange(i, i3);
        if (z) {
            updateComponentOffsets(i);
        }
        return this;
    }

    public Iterator<ByteBuf> iterator() {
        ensureAccessible();
        return this.componentCount == 0 ? EMPTY_ITERATOR : new CompositeByteBufIterator();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int forEachByteAsc0(int i, int i2, ByteProcessor byteProcessor) throws Exception {
        int forEachByte;
        if (i2 <= i) {
            return -1;
        }
        int componentIndex0 = toComponentIndex0(i);
        int i3 = i2 - i;
        while (i3 > 0) {
            Component component = this.components[componentIndex0];
            if (component.offset != component.endOffset) {
                ByteBuf byteBuf = component.buf;
                int idx = component.idx(i);
                int min = Math.min(i3, component.endOffset - i);
                if (byteBuf instanceof AbstractByteBuf) {
                    forEachByte = ((AbstractByteBuf) byteBuf).forEachByteAsc0(idx, idx + min, byteProcessor);
                } else {
                    forEachByte = byteBuf.forEachByte(idx, min, byteProcessor);
                }
                if (forEachByte != -1) {
                    return forEachByte - component.adjustment;
                }
                i += min;
                i3 -= min;
            }
            componentIndex0++;
        }
        return -1;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    protected int forEachByteDesc0(int i, int i2, ByteProcessor byteProcessor) throws Exception {
        int forEachByteDesc;
        if (i2 > i) {
            return -1;
        }
        int componentIndex0 = toComponentIndex0(i);
        int i3 = (i + 1) - i2;
        while (i3 > 0) {
            Component component = this.components[componentIndex0];
            if (component.offset != component.endOffset) {
                ByteBuf byteBuf = component.buf;
                int idx = component.idx(i3 + i2);
                int min = Math.min(i3, idx);
                int i4 = idx - min;
                if (byteBuf instanceof AbstractByteBuf) {
                    forEachByteDesc = ((AbstractByteBuf) byteBuf).forEachByteDesc0(idx - 1, i4, byteProcessor);
                } else {
                    forEachByteDesc = byteBuf.forEachByteDesc(i4, min, byteProcessor);
                }
                if (forEachByteDesc != -1) {
                    return forEachByteDesc - component.adjustment;
                }
                i3 -= min;
            }
            componentIndex0--;
        }
        return -1;
    }

    public List<ByteBuf> decompose(int i, int i2) {
        checkIndex(i, i2);
        if (i2 == 0) {
            return Collections.emptyList();
        }
        int componentIndex0 = toComponentIndex0(i);
        Component component = this.components[componentIndex0];
        ByteBuf slice = component.buf.slice(component.idx(i), Math.min(component.endOffset - i, i2));
        int readableBytes = i2 - slice.readableBytes();
        if (readableBytes == 0) {
            return Collections.singletonList(slice);
        }
        ArrayList arrayList = new ArrayList(this.componentCount - componentIndex0);
        arrayList.add(slice);
        do {
            componentIndex0++;
            Component component2 = this.components[componentIndex0];
            ByteBuf slice2 = component2.buf.slice(component2.idx(component2.offset), Math.min(component2.length(), readableBytes));
            readableBytes -= slice2.readableBytes();
            arrayList.add(slice2);
        } while (readableBytes > 0);
        return arrayList;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isDirect() {
        int i = this.componentCount;
        if (i == 0) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (!this.components[i2].buf.isDirect()) {
                return false;
            }
        }
        return true;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasArray() {
        int i = this.componentCount;
        if (i == 0) {
            return true;
        }
        if (i != 1) {
            return false;
        }
        return this.components[0].buf.hasArray();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte[] array() {
        int i = this.componentCount;
        if (i == 0) {
            return EmptyArrays.EMPTY_BYTES;
        }
        if (i == 1) {
            return this.components[0].buf.array();
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int arrayOffset() {
        int i = this.componentCount;
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            Component component = this.components[0];
            return component.idx(component.buf.arrayOffset());
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean hasMemoryAddress() {
        int i = this.componentCount;
        if (i == 0) {
            return Unpooled.EMPTY_BUFFER.hasMemoryAddress();
        }
        if (i != 1) {
            return false;
        }
        return this.components[0].buf.hasMemoryAddress();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long memoryAddress() {
        int i = this.componentCount;
        if (i == 0) {
            return Unpooled.EMPTY_BUFFER.memoryAddress();
        }
        if (i == 1) {
            return this.components[0].buf.memoryAddress() + r0.adjustment;
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int capacity() {
        int i = this.componentCount;
        if (i > 0) {
            return this.components[i - 1].endOffset;
        }
        return 0;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf capacity(int i) {
        checkNewCapacity(i);
        int i2 = this.componentCount;
        int capacity = capacity();
        if (i > capacity) {
            int i3 = i - capacity;
            addComponent0(false, i2, allocBuffer(i3).setIndex(0, i3));
            if (this.componentCount >= this.maxNumComponents) {
                consolidateIfNeeded();
            }
        } else if (i < capacity) {
            this.lastAccessed = null;
            int i4 = i2 - 1;
            int i5 = capacity - i;
            while (true) {
                if (i4 < 0) {
                    break;
                }
                Component component = this.components[i4];
                int length = component.length();
                if (i5 < length) {
                    component.endOffset -= i5;
                    ByteBuf byteBuf = component.slice;
                    if (byteBuf != null) {
                        component.slice = byteBuf.slice(0, component.length());
                    }
                } else {
                    component.free();
                    i5 -= length;
                    i4--;
                }
            }
            removeCompRange(i4 + 1, i2);
            if (readerIndex() > i) {
                setIndex0(i, i);
            } else if (this.writerIndex > i) {
                this.writerIndex = i;
            }
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBufAllocator alloc() {
        return this.alloc;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteOrder order() {
        return ByteOrder.BIG_ENDIAN;
    }

    public int numComponents() {
        return this.componentCount;
    }

    public int maxNumComponents() {
        return this.maxNumComponents;
    }

    public int toComponentIndex(int i) {
        checkIndex(i);
        return toComponentIndex0(i);
    }

    private int toComponentIndex0(int i) {
        int i2 = this.componentCount;
        int i3 = 0;
        if (i == 0) {
            for (int i4 = 0; i4 < i2; i4++) {
                if (this.components[i4].endOffset > 0) {
                    return i4;
                }
            }
        }
        if (i2 <= 2) {
            return (i2 == 1 || i < this.components[0].endOffset) ? 0 : 1;
        }
        while (i3 <= i2) {
            int i5 = (i3 + i2) >>> 1;
            Component component = this.components[i5];
            if (i >= component.endOffset) {
                i3 = i5 + 1;
            } else {
                if (i >= component.offset) {
                    return i5;
                }
                i2 = i5 - 1;
            }
        }
        throw new Error("should not reach here");
    }

    public int toByteIndex(int i) {
        checkComponentIndex(i);
        return this.components[i].offset;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte getByte(int i) {
        Component findComponent = findComponent(i);
        return findComponent.buf.getByte(findComponent.idx(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public byte _getByte(int i) {
        Component findComponent0 = findComponent0(i);
        return findComponent0.buf.getByte(findComponent0.idx(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public short _getShort(int i) {
        Component findComponent0 = findComponent0(i);
        if (i + 2 <= findComponent0.endOffset) {
            return findComponent0.buf.getShort(findComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (short) ((_getByte(i + 1) & 255) | ((_getByte(i) & 255) << 8));
        }
        return (short) (((_getByte(i + 1) & 255) << 8) | (_getByte(i) & 255));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public short _getShortLE(int i) {
        Component findComponent0 = findComponent0(i);
        if (i + 2 <= findComponent0.endOffset) {
            return findComponent0.buf.getShortLE(findComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (short) (((_getByte(i + 1) & 255) << 8) | (_getByte(i) & 255));
        }
        return (short) ((_getByte(i + 1) & 255) | ((_getByte(i) & 255) << 8));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public int _getUnsignedMedium(int i) {
        Component findComponent0 = findComponent0(i);
        if (i + 3 <= findComponent0.endOffset) {
            return findComponent0.buf.getUnsignedMedium(findComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (_getByte(i + 2) & 255) | ((_getShort(i) & UShort.MAX_VALUE) << 8);
        }
        return ((_getByte(i + 2) & 255) << 16) | (_getShort(i) & UShort.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public int _getUnsignedMediumLE(int i) {
        Component findComponent0 = findComponent0(i);
        if (i + 3 <= findComponent0.endOffset) {
            return findComponent0.buf.getUnsignedMediumLE(findComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return ((_getByte(i + 2) & 255) << 16) | (_getShortLE(i) & UShort.MAX_VALUE);
        }
        return (_getByte(i + 2) & 255) | ((_getShortLE(i) & UShort.MAX_VALUE) << 8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public int _getInt(int i) {
        Component findComponent0 = findComponent0(i);
        if (i + 4 <= findComponent0.endOffset) {
            return findComponent0.buf.getInt(findComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (_getShort(i + 2) & UShort.MAX_VALUE) | ((_getShort(i) & UShort.MAX_VALUE) << 16);
        }
        return ((_getShort(i + 2) & UShort.MAX_VALUE) << 16) | (_getShort(i) & UShort.MAX_VALUE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public int _getIntLE(int i) {
        Component findComponent0 = findComponent0(i);
        if (i + 4 <= findComponent0.endOffset) {
            return findComponent0.buf.getIntLE(findComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return ((_getShortLE(i + 2) & UShort.MAX_VALUE) << 16) | (_getShortLE(i) & UShort.MAX_VALUE);
        }
        return (_getShortLE(i + 2) & UShort.MAX_VALUE) | ((_getShortLE(i) & UShort.MAX_VALUE) << 16);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public long _getLong(int i) {
        Component findComponent0 = findComponent0(i);
        if (i + 8 <= findComponent0.endOffset) {
            return findComponent0.buf.getLong(findComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return ((_getInt(i) & 4294967295L) << 32) | (_getInt(i + 4) & 4294967295L);
        }
        return (_getInt(i) & 4294967295L) | ((4294967295L & _getInt(i + 4)) << 32);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public long _getLongLE(int i) {
        Component findComponent0 = findComponent0(i);
        if (i + 8 <= findComponent0.endOffset) {
            return findComponent0.buf.getLongLE(findComponent0.idx(i));
        }
        if (order() == ByteOrder.BIG_ENDIAN) {
            return (_getIntLE(i) & 4294967295L) | ((4294967295L & _getIntLE(i + 4)) << 32);
        }
        return ((_getIntLE(i) & 4294967295L) << 32) | (_getIntLE(i + 4) & 4294967295L);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, byte[] bArr, int i2, int i3) {
        checkDstIndex(i, i3, i2, bArr.length);
        if (i3 == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (i3 > 0) {
            Component component = this.components[componentIndex0];
            int min = Math.min(i3, component.endOffset - i);
            component.buf.getBytes(component.idx(i), bArr, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            componentIndex0++;
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, ByteBuffer byteBuffer) {
        int limit = byteBuffer.limit();
        int remaining = byteBuffer.remaining();
        checkIndex(i, remaining);
        if (remaining == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (remaining > 0) {
            try {
                Component component = this.components[componentIndex0];
                int min = Math.min(remaining, component.endOffset - i);
                byteBuffer.limit(byteBuffer.position() + min);
                component.buf.getBytes(component.idx(i), byteBuffer);
                i += min;
                remaining -= min;
                componentIndex0++;
            } finally {
                byteBuffer.limit(limit);
            }
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        checkDstIndex(i, i3, i2, byteBuf.capacity());
        if (i3 == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (i3 > 0) {
            Component component = this.components[componentIndex0];
            int min = Math.min(i3, component.endOffset - i);
            component.buf.getBytes(component.idx(i), byteBuf, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            componentIndex0++;
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
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

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
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

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, OutputStream outputStream, int i2) throws IOException {
        checkIndex(i, i2);
        if (i2 == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (i2 > 0) {
            Component component = this.components[componentIndex0];
            int min = Math.min(i2, component.endOffset - i);
            component.buf.getBytes(component.idx(i), outputStream, min);
            i += min;
            i2 -= min;
            componentIndex0++;
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setByte(int i, int i2) {
        Component findComponent = findComponent(i);
        findComponent.buf.setByte(findComponent.idx(i), i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setByte(int i, int i2) {
        Component findComponent0 = findComponent0(i);
        findComponent0.buf.setByte(findComponent0.idx(i), i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setShort(int i, int i2) {
        checkIndex(i, 2);
        _setShort(i, i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setShort(int i, int i2) {
        Component findComponent0 = findComponent0(i);
        if (i + 2 <= findComponent0.endOffset) {
            findComponent0.buf.setShort(findComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setByte(i, (byte) (i2 >>> 8));
            _setByte(i + 1, (byte) i2);
        } else {
            _setByte(i, (byte) i2);
            _setByte(i + 1, (byte) (i2 >>> 8));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setShortLE(int i, int i2) {
        Component findComponent0 = findComponent0(i);
        if (i + 2 <= findComponent0.endOffset) {
            findComponent0.buf.setShortLE(findComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setByte(i, (byte) i2);
            _setByte(i + 1, (byte) (i2 >>> 8));
        } else {
            _setByte(i, (byte) (i2 >>> 8));
            _setByte(i + 1, (byte) i2);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setMedium(int i, int i2) {
        checkIndex(i, 3);
        _setMedium(i, i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setMedium(int i, int i2) {
        Component findComponent0 = findComponent0(i);
        if (i + 3 <= findComponent0.endOffset) {
            findComponent0.buf.setMedium(findComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setShort(i, (short) (i2 >> 8));
            _setByte(i + 2, (byte) i2);
        } else {
            _setShort(i, (short) i2);
            _setByte(i + 2, (byte) (i2 >>> 16));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setMediumLE(int i, int i2) {
        Component findComponent0 = findComponent0(i);
        if (i + 3 <= findComponent0.endOffset) {
            findComponent0.buf.setMediumLE(findComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setShortLE(i, (short) i2);
            _setByte(i + 2, (byte) (i2 >>> 16));
        } else {
            _setShortLE(i, (short) (i2 >> 8));
            _setByte(i + 2, (byte) i2);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setInt(int i, int i2) {
        checkIndex(i, 4);
        _setInt(i, i2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setInt(int i, int i2) {
        Component findComponent0 = findComponent0(i);
        if (i + 4 <= findComponent0.endOffset) {
            findComponent0.buf.setInt(findComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setShort(i, (short) (i2 >>> 16));
            _setShort(i + 2, (short) i2);
        } else {
            _setShort(i, (short) i2);
            _setShort(i + 2, (short) (i2 >>> 16));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setIntLE(int i, int i2) {
        Component findComponent0 = findComponent0(i);
        if (i + 4 <= findComponent0.endOffset) {
            findComponent0.buf.setIntLE(findComponent0.idx(i), i2);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setShortLE(i, (short) i2);
            _setShortLE(i + 2, (short) (i2 >>> 16));
        } else {
            _setShortLE(i, (short) (i2 >>> 16));
            _setShortLE(i + 2, (short) i2);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setLong(int i, long j) {
        checkIndex(i, 8);
        _setLong(i, j);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setLong(int i, long j) {
        Component findComponent0 = findComponent0(i);
        if (i + 8 <= findComponent0.endOffset) {
            findComponent0.buf.setLong(findComponent0.idx(i), j);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setInt(i, (int) (j >>> 32));
            _setInt(i + 4, (int) j);
        } else {
            _setInt(i, (int) j);
            _setInt(i + 4, (int) (j >>> 32));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf
    public void _setLongLE(int i, long j) {
        Component findComponent0 = findComponent0(i);
        if (i + 8 <= findComponent0.endOffset) {
            findComponent0.buf.setLongLE(findComponent0.idx(i), j);
        } else if (order() == ByteOrder.BIG_ENDIAN) {
            _setIntLE(i, (int) j);
            _setIntLE(i + 4, (int) (j >>> 32));
        } else {
            _setIntLE(i, (int) (j >>> 32));
            _setIntLE(i + 4, (int) j);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, byte[] bArr, int i2, int i3) {
        checkSrcIndex(i, i3, i2, bArr.length);
        if (i3 == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (i3 > 0) {
            Component component = this.components[componentIndex0];
            int min = Math.min(i3, component.endOffset - i);
            component.buf.setBytes(component.idx(i), bArr, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            componentIndex0++;
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, ByteBuffer byteBuffer) {
        int limit = byteBuffer.limit();
        int remaining = byteBuffer.remaining();
        checkIndex(i, remaining);
        if (remaining == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (remaining > 0) {
            try {
                Component component = this.components[componentIndex0];
                int min = Math.min(remaining, component.endOffset - i);
                byteBuffer.limit(byteBuffer.position() + min);
                component.buf.setBytes(component.idx(i), byteBuffer);
                i += min;
                remaining -= min;
                componentIndex0++;
            } finally {
                byteBuffer.limit(limit);
            }
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        checkSrcIndex(i, i3, i2, byteBuf.capacity());
        if (i3 == 0) {
            return this;
        }
        int componentIndex0 = toComponentIndex0(i);
        while (i3 > 0) {
            Component component = this.components[componentIndex0];
            int min = Math.min(i3, component.endOffset - i);
            component.buf.setBytes(component.idx(i), byteBuf, i2, min);
            i += min;
            i2 += min;
            i3 -= min;
            componentIndex0++;
        }
        return this;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0039 A[EDGE_INSN: B:12:0x0039->B:13:0x0039 BREAK  A[LOOP:0: B:7:0x0011->B:14:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[LOOP:0: B:7:0x0011->B:14:?, LOOP_END, SYNTHETIC] */
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int setBytes(int i, InputStream inputStream, int i2) throws IOException {
        checkIndex(i, i2);
        if (i2 == 0) {
            return inputStream.read(EmptyArrays.EMPTY_BYTES);
        }
        int componentIndex0 = toComponentIndex0(i);
        int i3 = 0;
        while (true) {
            Component component = this.components[componentIndex0];
            int min = Math.min(i2, component.endOffset - i);
            if (min != 0) {
                int bytes = component.buf.setBytes(component.idx(i), inputStream, min);
                if (bytes >= 0) {
                    i += bytes;
                    i2 -= bytes;
                    i3 += bytes;
                    if (bytes != min) {
                        if (i2 > 0) {
                            break;
                        }
                    }
                } else if (i3 == 0) {
                    return -1;
                }
            }
            componentIndex0++;
            if (i2 > 0) {
            }
        }
        return i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003c A[EDGE_INSN: B:12:0x003c->B:13:0x003c BREAK  A[LOOP:0: B:7:0x0011->B:14:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[LOOP:0: B:7:0x0011->B:14:?, LOOP_END, SYNTHETIC] */
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int setBytes(int i, ScatteringByteChannel scatteringByteChannel, int i2) throws IOException {
        checkIndex(i, i2);
        if (i2 == 0) {
            return scatteringByteChannel.read(EMPTY_NIO_BUFFER);
        }
        int componentIndex0 = toComponentIndex0(i);
        int i3 = 0;
        while (true) {
            Component component = this.components[componentIndex0];
            int min = Math.min(i2, component.endOffset - i);
            if (min != 0) {
                int bytes = component.buf.setBytes(component.idx(i), scatteringByteChannel, min);
                if (bytes == 0) {
                    break;
                }
                if (bytes >= 0) {
                    i += bytes;
                    i2 -= bytes;
                    i3 += bytes;
                    if (bytes != min) {
                        if (i2 > 0) {
                            break;
                        }
                    }
                } else if (i3 == 0) {
                    return -1;
                }
            }
            componentIndex0++;
            if (i2 > 0) {
            }
        }
        return i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0041 A[EDGE_INSN: B:12:0x0041->B:13:0x0041 BREAK  A[LOOP:0: B:7:0x0011->B:14:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[LOOP:0: B:7:0x0011->B:14:?, LOOP_END, SYNTHETIC] */
    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int setBytes(int i, FileChannel fileChannel, long j, int i2) throws IOException {
        checkIndex(i, i2);
        if (i2 == 0) {
            return fileChannel.read(EMPTY_NIO_BUFFER, j);
        }
        int componentIndex0 = toComponentIndex0(i);
        int i3 = 0;
        while (true) {
            Component component = this.components[componentIndex0];
            int min = Math.min(i2, component.endOffset - i);
            if (min != 0) {
                int bytes = component.buf.setBytes(component.idx(i), fileChannel, j + i3, min);
                if (bytes == 0) {
                    break;
                }
                if (bytes >= 0) {
                    i += bytes;
                    i2 -= bytes;
                    i3 += bytes;
                    if (bytes != min) {
                        if (i2 > 0) {
                            break;
                        }
                    }
                } else if (i3 == 0) {
                    return -1;
                }
            }
            componentIndex0++;
            if (i2 > 0) {
            }
        }
        return i3;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf copy(int i, int i2) {
        checkIndex(i, i2);
        ByteBuf allocBuffer = allocBuffer(i2);
        if (i2 != 0) {
            copyTo(i, i2, toComponentIndex0(i), allocBuffer);
        }
        return allocBuffer;
    }

    private void copyTo(int i, int i2, int i3, ByteBuf byteBuf) {
        int i4 = 0;
        while (i2 > 0) {
            Component component = this.components[i3];
            int min = Math.min(i2, component.endOffset - i);
            component.buf.getBytes(component.idx(i), byteBuf, i4, min);
            i += min;
            i4 += min;
            i2 -= min;
            i3++;
        }
        byteBuf.writerIndex(byteBuf.capacity());
    }

    public ByteBuf component(int i) {
        checkComponentIndex(i);
        return this.components[i].duplicate();
    }

    public ByteBuf componentAtOffset(int i) {
        return findComponent(i).duplicate();
    }

    public ByteBuf internalComponent(int i) {
        checkComponentIndex(i);
        return this.components[i].slice();
    }

    public ByteBuf internalComponentAtOffset(int i) {
        return findComponent(i).slice();
    }

    private Component findComponent(int i) {
        Component component = this.lastAccessed;
        if (component != null && i >= component.offset && i < component.endOffset) {
            ensureAccessible();
            return component;
        }
        checkIndex(i);
        return findIt(i);
    }

    private Component findComponent0(int i) {
        Component component = this.lastAccessed;
        return (component == null || i < component.offset || i >= component.endOffset) ? findIt(i) : component;
    }

    private Component findIt(int i) {
        int i2 = this.componentCount;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            Component component = this.components[i4];
            if (i >= component.endOffset) {
                i3 = i4 + 1;
            } else {
                if (i >= component.offset) {
                    this.lastAccessed = component;
                    return component;
                }
                i2 = i4 - 1;
            }
        }
        throw new Error("should not reach here");
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int nioBufferCount() {
        int i = this.componentCount;
        if (i == 0) {
            return 1;
        }
        if (i == 1) {
            return this.components[0].buf.nioBufferCount();
        }
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += this.components[i3].buf.nioBufferCount();
        }
        return i2;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer internalNioBuffer(int i, int i2) {
        int i3 = this.componentCount;
        if (i3 == 0) {
            return EMPTY_NIO_BUFFER;
        }
        if (i3 == 1) {
            return this.components[0].internalNioBuffer(i, i2);
        }
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer nioBuffer(int i, int i2) {
        checkIndex(i, i2);
        int i3 = this.componentCount;
        if (i3 == 0) {
            return EMPTY_NIO_BUFFER;
        }
        if (i3 == 1) {
            Component component = this.components[0];
            ByteBuf byteBuf = component.buf;
            if (byteBuf.nioBufferCount() == 1) {
                return byteBuf.nioBuffer(component.idx(i), i2);
            }
        }
        ByteBuffer[] nioBuffers = nioBuffers(i, i2);
        if (nioBuffers.length == 1) {
            return nioBuffers[0];
        }
        ByteBuffer order = ByteBuffer.allocate(i2).order(order());
        for (ByteBuffer byteBuffer : nioBuffers) {
            order.put(byteBuffer);
        }
        order.flip();
        return order;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer[] nioBuffers(int i, int i2) {
        checkIndex(i, i2);
        if (i2 == 0) {
            return new ByteBuffer[]{EMPTY_NIO_BUFFER};
        }
        RecyclableArrayList newInstance = RecyclableArrayList.newInstance(this.componentCount);
        try {
            int componentIndex0 = toComponentIndex0(i);
            while (i2 > 0) {
                Component component = this.components[componentIndex0];
                ByteBuf byteBuf = component.buf;
                int min = Math.min(i2, component.endOffset - i);
                int nioBufferCount = byteBuf.nioBufferCount();
                if (nioBufferCount == 0) {
                    throw new UnsupportedOperationException();
                }
                if (nioBufferCount == 1) {
                    newInstance.add(byteBuf.nioBuffer(component.idx(i), min));
                } else {
                    Collections.addAll(newInstance, byteBuf.nioBuffers(component.idx(i), min));
                }
                i += min;
                i2 -= min;
                componentIndex0++;
            }
            return (ByteBuffer[]) newInstance.toArray(new ByteBuffer[0]);
        } finally {
            newInstance.recycle();
        }
    }

    public CompositeByteBuf consolidate() {
        ensureAccessible();
        consolidate0(0, this.componentCount);
        return this;
    }

    public CompositeByteBuf consolidate(int i, int i2) {
        checkComponentIndex(i, i2);
        consolidate0(i, i2);
        return this;
    }

    private void consolidate0(int i, int i2) {
        if (i2 <= 1) {
            return;
        }
        int i3 = i + i2;
        ByteBuf allocBuffer = allocBuffer(this.components[i3 - 1].endOffset - (i != 0 ? this.components[i].offset : 0));
        for (int i4 = i; i4 < i3; i4++) {
            this.components[i4].transferTo(allocBuffer);
        }
        this.lastAccessed = null;
        removeCompRange(i + 1, i3);
        this.components[i] = newComponent(allocBuffer, 0);
        if (i == 0 && i2 == this.componentCount) {
            return;
        }
        updateComponentOffsets(i);
    }

    public CompositeByteBuf discardReadComponents() {
        ensureAccessible();
        int readerIndex = readerIndex();
        if (readerIndex == 0) {
            return this;
        }
        int writerIndex = writerIndex();
        if (readerIndex == writerIndex && writerIndex == capacity()) {
            int i = this.componentCount;
            for (int i2 = 0; i2 < i; i2++) {
                this.components[i2].free();
            }
            this.lastAccessed = null;
            clearComps();
            setIndex(0, 0);
            adjustMarkers(readerIndex);
            return this;
        }
        int i3 = this.componentCount;
        Component component = null;
        int i4 = 0;
        while (i4 < i3) {
            component = this.components[i4];
            if (component.endOffset > readerIndex) {
                break;
            }
            component.free();
            i4++;
        }
        if (i4 == 0) {
            return this;
        }
        Component component2 = this.lastAccessed;
        if (component2 != null && component2.endOffset <= readerIndex) {
            this.lastAccessed = null;
        }
        removeCompRange(0, i4);
        int i5 = component.offset;
        updateComponentOffsets(0);
        setIndex(readerIndex - i5, writerIndex - i5);
        adjustMarkers(i5);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf discardReadBytes() {
        ensureAccessible();
        int readerIndex = readerIndex();
        if (readerIndex == 0) {
            return this;
        }
        int writerIndex = writerIndex();
        if (readerIndex == writerIndex && writerIndex == capacity()) {
            int i = this.componentCount;
            for (int i2 = 0; i2 < i; i2++) {
                this.components[i2].free();
            }
            this.lastAccessed = null;
            clearComps();
            setIndex(0, 0);
            adjustMarkers(readerIndex);
            return this;
        }
        int i3 = this.componentCount;
        Component component = null;
        int i4 = 0;
        while (i4 < i3) {
            component = this.components[i4];
            if (component.endOffset > readerIndex) {
                break;
            }
            component.free();
            i4++;
        }
        int i5 = readerIndex - component.offset;
        component.offset = 0;
        component.endOffset -= readerIndex;
        component.srcAdjustment += readerIndex;
        component.adjustment += readerIndex;
        ByteBuf byteBuf = component.slice;
        if (byteBuf != null) {
            component.slice = byteBuf.slice(i5, component.length());
        }
        Component component2 = this.lastAccessed;
        if (component2 != null && component2.endOffset <= readerIndex) {
            this.lastAccessed = null;
        }
        removeCompRange(0, i4);
        updateComponentOffsets(0);
        setIndex(0, writerIndex - readerIndex);
        adjustMarkers(readerIndex);
        return this;
    }

    private ByteBuf allocBuffer(int i) {
        return this.direct ? alloc().directBuffer(i) : alloc().heapBuffer(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public String toString() {
        return super.toString().substring(0, r0.length() - 1) + ", components=" + this.componentCount + ')';
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class Component {
        int adjustment;
        final ByteBuf buf;
        int endOffset;
        int offset;
        private ByteBuf slice;
        int srcAdjustment;
        final ByteBuf srcBuf;

        Component(ByteBuf byteBuf, int i, ByteBuf byteBuf2, int i2, int i3, int i4, ByteBuf byteBuf3) {
            this.srcBuf = byteBuf;
            this.srcAdjustment = i - i3;
            this.buf = byteBuf2;
            this.adjustment = i2 - i3;
            this.offset = i3;
            this.endOffset = i3 + i4;
            this.slice = byteBuf3;
        }

        int srcIdx(int i) {
            return i + this.srcAdjustment;
        }

        int idx(int i) {
            return i + this.adjustment;
        }

        int length() {
            return this.endOffset - this.offset;
        }

        void reposition(int i) {
            int i2 = i - this.offset;
            this.endOffset += i2;
            this.srcAdjustment -= i2;
            this.adjustment -= i2;
            this.offset = i;
        }

        void transferTo(ByteBuf byteBuf) {
            byteBuf.writeBytes(this.buf, idx(this.offset), length());
            free();
        }

        ByteBuf slice() {
            ByteBuf byteBuf = this.slice;
            if (byteBuf != null) {
                return byteBuf;
            }
            ByteBuf slice = this.srcBuf.slice(srcIdx(this.offset), length());
            this.slice = slice;
            return slice;
        }

        ByteBuf duplicate() {
            return this.srcBuf.duplicate();
        }

        ByteBuffer internalNioBuffer(int i, int i2) {
            return this.srcBuf.internalNioBuffer(srcIdx(i), i2);
        }

        void free() {
            this.slice = null;
            this.srcBuf.release();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf readerIndex(int i) {
        super.readerIndex(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writerIndex(int i) {
        super.writerIndex(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setIndex(int i, int i2) {
        super.setIndex(i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf clear() {
        super.clear();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf markReaderIndex() {
        super.markReaderIndex();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf resetReaderIndex() {
        super.resetReaderIndex();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf markWriterIndex() {
        super.markWriterIndex();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf resetWriterIndex() {
        super.resetWriterIndex();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf ensureWritable(int i) {
        super.ensureWritable(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, ByteBuf byteBuf) {
        return getBytes(i, byteBuf, byteBuf.writableBytes());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, ByteBuf byteBuf, int i2) {
        getBytes(i, byteBuf, byteBuf.writerIndex(), i2);
        byteBuf.writerIndex(byteBuf.writerIndex() + i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf getBytes(int i, byte[] bArr) {
        return getBytes(i, bArr, 0, bArr.length);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setBoolean(int i, boolean z) {
        return setByte(i, z ? 1 : 0);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setChar(int i, int i2) {
        return setShort(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setFloat(int i, float f) {
        return setInt(i, Float.floatToRawIntBits(f));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setDouble(int i, double d) {
        return setLong(i, Double.doubleToRawLongBits(d));
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, ByteBuf byteBuf) {
        super.setBytes(i, byteBuf, byteBuf.readableBytes());
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, ByteBuf byteBuf, int i2) {
        super.setBytes(i, byteBuf, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setBytes(int i, byte[] bArr) {
        return setBytes(i, bArr, 0, bArr.length);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf setZero(int i, int i2) {
        super.setZero(i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(ByteBuf byteBuf) {
        super.readBytes(byteBuf, byteBuf.writableBytes());
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(ByteBuf byteBuf, int i) {
        super.readBytes(byteBuf, i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(ByteBuf byteBuf, int i, int i2) {
        super.readBytes(byteBuf, i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(byte[] bArr) {
        super.readBytes(bArr, 0, bArr.length);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(byte[] bArr, int i, int i2) {
        super.readBytes(bArr, i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(ByteBuffer byteBuffer) {
        super.readBytes(byteBuffer);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf readBytes(OutputStream outputStream, int i) throws IOException {
        super.readBytes(outputStream, i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf skipBytes(int i) {
        super.skipBytes(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBoolean(boolean z) {
        writeByte(z ? 1 : 0);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeByte(int i) {
        ensureWritable0(1);
        int i2 = this.writerIndex;
        this.writerIndex = i2 + 1;
        _setByte(i2, i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeShort(int i) {
        super.writeShort(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeMedium(int i) {
        super.writeMedium(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeInt(int i) {
        super.writeInt(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeLong(long j) {
        super.writeLong(j);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeChar(int i) {
        super.writeShort(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeFloat(float f) {
        super.writeInt(Float.floatToRawIntBits(f));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeDouble(double d) {
        super.writeLong(Double.doubleToRawLongBits(d));
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(ByteBuf byteBuf) {
        super.writeBytes(byteBuf, byteBuf.readableBytes());
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(ByteBuf byteBuf, int i) {
        super.writeBytes(byteBuf, i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(ByteBuf byteBuf, int i, int i2) {
        super.writeBytes(byteBuf, i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(byte[] bArr) {
        super.writeBytes(bArr, 0, bArr.length);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(byte[] bArr, int i, int i2) {
        super.writeBytes(bArr, i, i2);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeBytes(ByteBuffer byteBuffer) {
        super.writeBytes(byteBuffer);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf writeZero(int i) {
        super.writeZero(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public CompositeByteBuf retain(int i) {
        super.retain(i);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public CompositeByteBuf retain() {
        super.retain();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer[] nioBuffers() {
        return nioBuffers(readerIndex(), readableBytes());
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CompositeByteBuf discardSomeReadBytes() {
        return discardReadComponents();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf
    public void deallocate() {
        if (this.freed) {
            return;
        }
        this.freed = true;
        int i = this.componentCount;
        for (int i2 = 0; i2 < i; i2++) {
            this.components[i2].free();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.grpc.netty.shaded.io.netty.buffer.AbstractReferenceCountedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean isAccessible() {
        return !this.freed;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class CompositeByteBufIterator implements Iterator<ByteBuf> {
        private int index;
        private final int size;

        private CompositeByteBufIterator() {
            this.size = CompositeByteBuf.this.numComponents();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.size > this.index;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        public ByteBuf next() {
            if (this.size != CompositeByteBuf.this.numComponents()) {
                throw new ConcurrentModificationException();
            }
            if (hasNext()) {
                try {
                    Component[] componentArr = CompositeByteBuf.this.components;
                    int i = this.index;
                    this.index = i + 1;
                    return componentArr[i].slice();
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

    private void clearComps() {
        removeCompRange(0, this.componentCount);
    }

    private void removeComp(int i) {
        removeCompRange(i, i + 1);
    }

    private void removeCompRange(int i, int i2) {
        if (i >= i2) {
            return;
        }
        int i3 = this.componentCount;
        if (i2 < i3) {
            Component[] componentArr = this.components;
            System.arraycopy(componentArr, i2, componentArr, i, i3 - i2);
        }
        int i4 = (i3 - i2) + i;
        for (int i5 = i4; i5 < i3; i5++) {
            this.components[i5] = null;
        }
        this.componentCount = i4;
    }

    private void addComp(int i, Component component) {
        shiftComps(i, 1);
        this.components[i] = component;
    }

    private void shiftComps(int i, int i2) {
        Component[] componentArr;
        int i3 = this.componentCount;
        int i4 = i3 + i2;
        Component[] componentArr2 = this.components;
        if (i4 > componentArr2.length) {
            int max = Math.max((i3 >> 1) + i3, i4);
            if (i == i3) {
                componentArr = (Component[]) Arrays.copyOf(this.components, max, Component[].class);
            } else {
                Component[] componentArr3 = new Component[max];
                if (i > 0) {
                    System.arraycopy(this.components, 0, componentArr3, 0, i);
                }
                if (i < i3) {
                    System.arraycopy(this.components, i, componentArr3, i2 + i, i3 - i);
                }
                componentArr = componentArr3;
            }
            this.components = componentArr;
        } else if (i < i3) {
            System.arraycopy(componentArr2, i, componentArr2, i2 + i, i3 - i);
        }
        this.componentCount = i4;
    }
}
