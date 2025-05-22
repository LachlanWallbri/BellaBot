package io.grpc.netty.shaded.io.netty.buffer;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class Unpooled {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final ByteBufAllocator ALLOC = UnpooledByteBufAllocator.DEFAULT;
    public static final ByteOrder BIG_ENDIAN = ByteOrder.BIG_ENDIAN;
    public static final ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
    public static final ByteBuf EMPTY_BUFFER = ALLOC.buffer(0, 0);

    public static ByteBuf buffer() {
        return ALLOC.heapBuffer();
    }

    public static ByteBuf directBuffer() {
        return ALLOC.directBuffer();
    }

    public static ByteBuf buffer(int i) {
        return ALLOC.heapBuffer(i);
    }

    public static ByteBuf directBuffer(int i) {
        return ALLOC.directBuffer(i);
    }

    public static ByteBuf buffer(int i, int i2) {
        return ALLOC.heapBuffer(i, i2);
    }

    public static ByteBuf directBuffer(int i, int i2) {
        return ALLOC.directBuffer(i, i2);
    }

    public static ByteBuf wrappedBuffer(byte[] bArr) {
        if (bArr.length == 0) {
            return EMPTY_BUFFER;
        }
        return new UnpooledHeapByteBuf(ALLOC, bArr, bArr.length);
    }

    public static ByteBuf wrappedBuffer(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return EMPTY_BUFFER;
        }
        if (i == 0 && i2 == bArr.length) {
            return wrappedBuffer(bArr);
        }
        return wrappedBuffer(bArr).slice(i, i2);
    }

    public static ByteBuf wrappedBuffer(ByteBuffer byteBuffer) {
        if (!byteBuffer.hasRemaining()) {
            return EMPTY_BUFFER;
        }
        if (!byteBuffer.isDirect() && byteBuffer.hasArray()) {
            return wrappedBuffer(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()).order(byteBuffer.order());
        }
        if (PlatformDependent.hasUnsafe()) {
            if (byteBuffer.isReadOnly()) {
                if (byteBuffer.isDirect()) {
                    return new ReadOnlyUnsafeDirectByteBuf(ALLOC, byteBuffer);
                }
                return new ReadOnlyByteBufferBuf(ALLOC, byteBuffer);
            }
            return new UnpooledUnsafeDirectByteBuf(ALLOC, byteBuffer, byteBuffer.remaining());
        }
        if (byteBuffer.isReadOnly()) {
            return new ReadOnlyByteBufferBuf(ALLOC, byteBuffer);
        }
        return new UnpooledDirectByteBuf(ALLOC, byteBuffer, byteBuffer.remaining());
    }

    public static ByteBuf wrappedBuffer(long j, int i, boolean z) {
        return new WrappedUnpooledUnsafeDirectByteBuf(ALLOC, j, i, z);
    }

    public static ByteBuf wrappedBuffer(ByteBuf byteBuf) {
        if (byteBuf.isReadable()) {
            return byteBuf.slice();
        }
        byteBuf.release();
        return EMPTY_BUFFER;
    }

    public static ByteBuf wrappedBuffer(byte[]... bArr) {
        return wrappedBuffer(bArr.length, bArr);
    }

    public static ByteBuf wrappedBuffer(ByteBuf... byteBufArr) {
        return wrappedBuffer(byteBufArr.length, byteBufArr);
    }

    public static ByteBuf wrappedBuffer(ByteBuffer... byteBufferArr) {
        return wrappedBuffer(byteBufferArr.length, byteBufferArr);
    }

    static <T> ByteBuf wrappedBuffer(int i, CompositeByteBuf.ByteWrapper<T> byteWrapper, T[] tArr) {
        int length = tArr.length;
        if (length != 0) {
            if (length == 1) {
                if (!byteWrapper.isEmpty(tArr[0])) {
                    return byteWrapper.wrap(tArr[0]);
                }
            } else {
                int length2 = tArr.length;
                for (int i2 = 0; i2 < length2; i2++) {
                    T t = tArr[i2];
                    if (t == null) {
                        return EMPTY_BUFFER;
                    }
                    if (!byteWrapper.isEmpty(t)) {
                        return new CompositeByteBuf(ALLOC, false, i, byteWrapper, tArr, i2);
                    }
                }
            }
        }
        return EMPTY_BUFFER;
    }

    public static ByteBuf wrappedBuffer(int i, byte[]... bArr) {
        return wrappedBuffer(i, CompositeByteBuf.BYTE_ARRAY_WRAPPER, bArr);
    }

    public static ByteBuf wrappedBuffer(int i, ByteBuf... byteBufArr) {
        int length = byteBufArr.length;
        if (length != 0) {
            if (length == 1) {
                ByteBuf byteBuf = byteBufArr[0];
                if (byteBuf.isReadable()) {
                    return wrappedBuffer(byteBuf.order(BIG_ENDIAN));
                }
                byteBuf.release();
            } else {
                for (int i2 = 0; i2 < byteBufArr.length; i2++) {
                    ByteBuf byteBuf2 = byteBufArr[i2];
                    if (byteBuf2.isReadable()) {
                        return new CompositeByteBuf(ALLOC, false, i, byteBufArr, i2);
                    }
                    byteBuf2.release();
                }
            }
        }
        return EMPTY_BUFFER;
    }

    public static ByteBuf wrappedBuffer(int i, ByteBuffer... byteBufferArr) {
        return wrappedBuffer(i, CompositeByteBuf.BYTE_BUFFER_WRAPPER, byteBufferArr);
    }

    public static CompositeByteBuf compositeBuffer() {
        return compositeBuffer(16);
    }

    public static CompositeByteBuf compositeBuffer(int i) {
        return new CompositeByteBuf(ALLOC, false, i);
    }

    public static ByteBuf copiedBuffer(byte[] bArr) {
        if (bArr.length == 0) {
            return EMPTY_BUFFER;
        }
        return wrappedBuffer((byte[]) bArr.clone());
    }

    public static ByteBuf copiedBuffer(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return EMPTY_BUFFER;
        }
        byte[] allocateUninitializedArray = PlatformDependent.allocateUninitializedArray(i2);
        System.arraycopy(bArr, i, allocateUninitializedArray, 0, i2);
        return wrappedBuffer(allocateUninitializedArray);
    }

    public static ByteBuf copiedBuffer(ByteBuffer byteBuffer) {
        int remaining = byteBuffer.remaining();
        if (remaining == 0) {
            return EMPTY_BUFFER;
        }
        byte[] allocateUninitializedArray = PlatformDependent.allocateUninitializedArray(remaining);
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.get(allocateUninitializedArray);
        return wrappedBuffer(allocateUninitializedArray).order(duplicate.order());
    }

    public static ByteBuf copiedBuffer(ByteBuf byteBuf) {
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes > 0) {
            ByteBuf buffer = buffer(readableBytes);
            buffer.writeBytes(byteBuf, byteBuf.readerIndex(), readableBytes);
            return buffer;
        }
        return EMPTY_BUFFER;
    }

    public static ByteBuf copiedBuffer(byte[]... bArr) {
        int length = bArr.length;
        if (length == 0) {
            return EMPTY_BUFFER;
        }
        if (length == 1) {
            if (bArr[0].length == 0) {
                return EMPTY_BUFFER;
            }
            return copiedBuffer(bArr[0]);
        }
        int i = 0;
        for (byte[] bArr2 : bArr) {
            if (Integer.MAX_VALUE - i < bArr2.length) {
                throw new IllegalArgumentException("The total length of the specified arrays is too big.");
            }
            i += bArr2.length;
        }
        if (i == 0) {
            return EMPTY_BUFFER;
        }
        byte[] allocateUninitializedArray = PlatformDependent.allocateUninitializedArray(i);
        int i2 = 0;
        for (byte[] bArr3 : bArr) {
            System.arraycopy(bArr3, 0, allocateUninitializedArray, i2, bArr3.length);
            i2 += bArr3.length;
        }
        return wrappedBuffer(allocateUninitializedArray);
    }

    public static ByteBuf copiedBuffer(ByteBuf... byteBufArr) {
        int length = byteBufArr.length;
        if (length == 0) {
            return EMPTY_BUFFER;
        }
        if (length == 1) {
            return copiedBuffer(byteBufArr[0]);
        }
        ByteOrder byteOrder = null;
        int i = 0;
        for (ByteBuf byteBuf : byteBufArr) {
            int readableBytes = byteBuf.readableBytes();
            if (readableBytes > 0) {
                if (Integer.MAX_VALUE - i < readableBytes) {
                    throw new IllegalArgumentException("The total length of the specified buffers is too big.");
                }
                i += readableBytes;
                if (byteOrder != null) {
                    if (!byteOrder.equals(byteBuf.order())) {
                        throw new IllegalArgumentException("inconsistent byte order");
                    }
                } else {
                    byteOrder = byteBuf.order();
                }
            }
        }
        if (i == 0) {
            return EMPTY_BUFFER;
        }
        byte[] allocateUninitializedArray = PlatformDependent.allocateUninitializedArray(i);
        int i2 = 0;
        for (ByteBuf byteBuf2 : byteBufArr) {
            int readableBytes2 = byteBuf2.readableBytes();
            byteBuf2.getBytes(byteBuf2.readerIndex(), allocateUninitializedArray, i2, readableBytes2);
            i2 += readableBytes2;
        }
        return wrappedBuffer(allocateUninitializedArray).order(byteOrder);
    }

    public static ByteBuf copiedBuffer(ByteBuffer... byteBufferArr) {
        int length = byteBufferArr.length;
        if (length == 0) {
            return EMPTY_BUFFER;
        }
        if (length == 1) {
            return copiedBuffer(byteBufferArr[0]);
        }
        ByteOrder byteOrder = null;
        int i = 0;
        for (ByteBuffer byteBuffer : byteBufferArr) {
            int remaining = byteBuffer.remaining();
            if (remaining > 0) {
                if (Integer.MAX_VALUE - i < remaining) {
                    throw new IllegalArgumentException("The total length of the specified buffers is too big.");
                }
                i += remaining;
                if (byteOrder != null) {
                    if (!byteOrder.equals(byteBuffer.order())) {
                        throw new IllegalArgumentException("inconsistent byte order");
                    }
                } else {
                    byteOrder = byteBuffer.order();
                }
            }
        }
        if (i == 0) {
            return EMPTY_BUFFER;
        }
        byte[] allocateUninitializedArray = PlatformDependent.allocateUninitializedArray(i);
        int i2 = 0;
        for (ByteBuffer byteBuffer2 : byteBufferArr) {
            ByteBuffer duplicate = byteBuffer2.duplicate();
            int remaining2 = duplicate.remaining();
            duplicate.get(allocateUninitializedArray, i2, remaining2);
            i2 += remaining2;
        }
        return wrappedBuffer(allocateUninitializedArray).order(byteOrder);
    }

    public static ByteBuf copiedBuffer(CharSequence charSequence, Charset charset) {
        ObjectUtil.checkNotNull(charSequence, "string");
        if (CharsetUtil.UTF_8.equals(charset)) {
            return copiedBufferUtf8(charSequence);
        }
        if (CharsetUtil.US_ASCII.equals(charset)) {
            return copiedBufferAscii(charSequence);
        }
        if (charSequence instanceof CharBuffer) {
            return copiedBuffer((CharBuffer) charSequence, charset);
        }
        return copiedBuffer(CharBuffer.wrap(charSequence), charset);
    }

    private static ByteBuf copiedBufferUtf8(CharSequence charSequence) {
        ByteBuf heapBuffer = ALLOC.heapBuffer(ByteBufUtil.utf8Bytes(charSequence));
        try {
            ByteBufUtil.writeUtf8(heapBuffer, charSequence);
            return heapBuffer;
        } catch (Throwable th) {
            heapBuffer.release();
            throw th;
        }
    }

    private static ByteBuf copiedBufferAscii(CharSequence charSequence) {
        ByteBuf heapBuffer = ALLOC.heapBuffer(charSequence.length());
        try {
            ByteBufUtil.writeAscii(heapBuffer, charSequence);
            return heapBuffer;
        } catch (Throwable th) {
            heapBuffer.release();
            throw th;
        }
    }

    public static ByteBuf copiedBuffer(CharSequence charSequence, int i, int i2, Charset charset) {
        ObjectUtil.checkNotNull(charSequence, "string");
        if (i2 == 0) {
            return EMPTY_BUFFER;
        }
        if (charSequence instanceof CharBuffer) {
            CharBuffer charBuffer = (CharBuffer) charSequence;
            if (charBuffer.hasArray()) {
                return copiedBuffer(charBuffer.array(), charBuffer.arrayOffset() + charBuffer.position() + i, i2, charset);
            }
            CharBuffer slice = charBuffer.slice();
            slice.limit(i2);
            slice.position(i);
            return copiedBuffer(slice, charset);
        }
        return copiedBuffer(CharBuffer.wrap(charSequence, i, i2 + i), charset);
    }

    public static ByteBuf copiedBuffer(char[] cArr, Charset charset) {
        ObjectUtil.checkNotNull(cArr, TmpConstant.TYPE_VALUE_ARRAY);
        return copiedBuffer(cArr, 0, cArr.length, charset);
    }

    public static ByteBuf copiedBuffer(char[] cArr, int i, int i2, Charset charset) {
        ObjectUtil.checkNotNull(cArr, TmpConstant.TYPE_VALUE_ARRAY);
        if (i2 == 0) {
            return EMPTY_BUFFER;
        }
        return copiedBuffer(CharBuffer.wrap(cArr, i, i2), charset);
    }

    private static ByteBuf copiedBuffer(CharBuffer charBuffer, Charset charset) {
        return ByteBufUtil.encodeString0(ALLOC, true, charBuffer, charset, 0);
    }

    @Deprecated
    public static ByteBuf unmodifiableBuffer(ByteBuf byteBuf) {
        ByteOrder order = byteBuf.order();
        ByteOrder byteOrder = BIG_ENDIAN;
        if (order == byteOrder) {
            return new ReadOnlyByteBuf(byteBuf);
        }
        return new ReadOnlyByteBuf(byteBuf.order(byteOrder)).order(LITTLE_ENDIAN);
    }

    public static ByteBuf copyInt(int i) {
        ByteBuf buffer = buffer(4);
        buffer.writeInt(i);
        return buffer;
    }

    public static ByteBuf copyInt(int... iArr) {
        if (iArr == null || iArr.length == 0) {
            return EMPTY_BUFFER;
        }
        ByteBuf buffer = buffer(iArr.length * 4);
        for (int i : iArr) {
            buffer.writeInt(i);
        }
        return buffer;
    }

    public static ByteBuf copyShort(int i) {
        ByteBuf buffer = buffer(2);
        buffer.writeShort(i);
        return buffer;
    }

    public static ByteBuf copyShort(short... sArr) {
        if (sArr == null || sArr.length == 0) {
            return EMPTY_BUFFER;
        }
        ByteBuf buffer = buffer(sArr.length * 2);
        for (short s : sArr) {
            buffer.writeShort(s);
        }
        return buffer;
    }

    public static ByteBuf copyShort(int... iArr) {
        if (iArr == null || iArr.length == 0) {
            return EMPTY_BUFFER;
        }
        ByteBuf buffer = buffer(iArr.length * 2);
        for (int i : iArr) {
            buffer.writeShort(i);
        }
        return buffer;
    }

    public static ByteBuf copyMedium(int i) {
        ByteBuf buffer = buffer(3);
        buffer.writeMedium(i);
        return buffer;
    }

    public static ByteBuf copyMedium(int... iArr) {
        if (iArr == null || iArr.length == 0) {
            return EMPTY_BUFFER;
        }
        ByteBuf buffer = buffer(iArr.length * 3);
        for (int i : iArr) {
            buffer.writeMedium(i);
        }
        return buffer;
    }

    public static ByteBuf copyLong(long j) {
        ByteBuf buffer = buffer(8);
        buffer.writeLong(j);
        return buffer;
    }

    public static ByteBuf copyLong(long... jArr) {
        if (jArr == null || jArr.length == 0) {
            return EMPTY_BUFFER;
        }
        ByteBuf buffer = buffer(jArr.length * 8);
        for (long j : jArr) {
            buffer.writeLong(j);
        }
        return buffer;
    }

    public static ByteBuf copyBoolean(boolean z) {
        ByteBuf buffer = buffer(1);
        buffer.writeBoolean(z);
        return buffer;
    }

    public static ByteBuf copyBoolean(boolean... zArr) {
        if (zArr == null || zArr.length == 0) {
            return EMPTY_BUFFER;
        }
        ByteBuf buffer = buffer(zArr.length);
        for (boolean z : zArr) {
            buffer.writeBoolean(z);
        }
        return buffer;
    }

    public static ByteBuf copyFloat(float f) {
        ByteBuf buffer = buffer(4);
        buffer.writeFloat(f);
        return buffer;
    }

    public static ByteBuf copyFloat(float... fArr) {
        if (fArr == null || fArr.length == 0) {
            return EMPTY_BUFFER;
        }
        ByteBuf buffer = buffer(fArr.length * 4);
        for (float f : fArr) {
            buffer.writeFloat(f);
        }
        return buffer;
    }

    public static ByteBuf copyDouble(double d) {
        ByteBuf buffer = buffer(8);
        buffer.writeDouble(d);
        return buffer;
    }

    public static ByteBuf copyDouble(double... dArr) {
        if (dArr == null || dArr.length == 0) {
            return EMPTY_BUFFER;
        }
        ByteBuf buffer = buffer(dArr.length * 8);
        for (double d : dArr) {
            buffer.writeDouble(d);
        }
        return buffer;
    }

    public static ByteBuf unreleasableBuffer(ByteBuf byteBuf) {
        return new UnreleasableByteBuf(byteBuf);
    }

    @Deprecated
    public static ByteBuf unmodifiableBuffer(ByteBuf... byteBufArr) {
        return wrappedUnmodifiableBuffer(true, byteBufArr);
    }

    public static ByteBuf wrappedUnmodifiableBuffer(ByteBuf... byteBufArr) {
        return wrappedUnmodifiableBuffer(false, byteBufArr);
    }

    private static ByteBuf wrappedUnmodifiableBuffer(boolean z, ByteBuf... byteBufArr) {
        int length = byteBufArr.length;
        if (length == 0) {
            return EMPTY_BUFFER;
        }
        if (length == 1) {
            return byteBufArr[0].asReadOnly();
        }
        if (z) {
            byteBufArr = (ByteBuf[]) Arrays.copyOf(byteBufArr, byteBufArr.length, ByteBuf[].class);
        }
        return new FixedCompositeByteBuf(ALLOC, byteBufArr);
    }

    private Unpooled() {
    }
}
