package io.grpc.netty.shaded.io.netty.buffer;

import io.grpc.netty.shaded.io.netty.util.ByteProcessor;
import io.grpc.netty.shaded.io.netty.util.ResourceLeakDetector;
import io.grpc.netty.shaded.io.netty.util.ResourceLeakTracker;
import io.grpc.netty.shaded.io.netty.util.internal.SystemPropertyUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class AdvancedLeakAwareByteBuf extends SimpleLeakAwareByteBuf {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AdvancedLeakAwareByteBuf.class);
    private static final String PROP_ACQUIRE_AND_RELEASE_ONLY = "io.grpc.netty.shaded.io.netty.leakDetection.acquireAndReleaseOnly";
    private static final boolean ACQUIRE_AND_RELEASE_ONLY = SystemPropertyUtil.getBoolean(PROP_ACQUIRE_AND_RELEASE_ONLY, false);

    @Override // io.grpc.netty.shaded.io.netty.buffer.SimpleLeakAwareByteBuf
    protected /* bridge */ /* synthetic */ SimpleLeakAwareByteBuf newLeakAwareByteBuf(ByteBuf byteBuf, ByteBuf byteBuf2, ResourceLeakTracker resourceLeakTracker) {
        return newLeakAwareByteBuf(byteBuf, byteBuf2, (ResourceLeakTracker<ByteBuf>) resourceLeakTracker);
    }

    static {
        if (logger.isDebugEnabled()) {
            logger.debug("-D{}: {}", PROP_ACQUIRE_AND_RELEASE_ONLY, Boolean.valueOf(ACQUIRE_AND_RELEASE_ONLY));
        }
        ResourceLeakDetector.addExclusions(AdvancedLeakAwareByteBuf.class, "touch", "recordLeakNonRefCountingOperation");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdvancedLeakAwareByteBuf(ByteBuf byteBuf, ResourceLeakTracker<ByteBuf> resourceLeakTracker) {
        super(byteBuf, resourceLeakTracker);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdvancedLeakAwareByteBuf(ByteBuf byteBuf, ByteBuf byteBuf2, ResourceLeakTracker<ByteBuf> resourceLeakTracker) {
        super(byteBuf, byteBuf2, resourceLeakTracker);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void recordLeakNonRefCountingOperation(ResourceLeakTracker<ByteBuf> resourceLeakTracker) {
        if (ACQUIRE_AND_RELEASE_ONLY) {
            return;
        }
        resourceLeakTracker.record();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SimpleLeakAwareByteBuf, io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf order(ByteOrder byteOrder) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.order(byteOrder);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SimpleLeakAwareByteBuf, io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf slice() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.slice();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SimpleLeakAwareByteBuf, io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf slice(int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.slice(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SimpleLeakAwareByteBuf, io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedSlice() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.retainedSlice();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SimpleLeakAwareByteBuf, io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedSlice(int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.retainedSlice(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SimpleLeakAwareByteBuf, io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf retainedDuplicate() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.retainedDuplicate();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SimpleLeakAwareByteBuf, io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readRetainedSlice(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readRetainedSlice(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SimpleLeakAwareByteBuf, io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf duplicate() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.duplicate();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SimpleLeakAwareByteBuf, io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readSlice(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readSlice(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf discardReadBytes() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.discardReadBytes();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf discardSomeReadBytes() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.discardSomeReadBytes();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf ensureWritable(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.ensureWritable(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int ensureWritable(int i, boolean z) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.ensureWritable(i, z);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean getBoolean(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getBoolean(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte getByte(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getByte(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short getUnsignedByte(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getUnsignedByte(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short getShort(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getShort(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedShort(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getUnsignedShort(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getMedium(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getMedium(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedMedium(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getUnsignedMedium(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getInt(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getInt(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getUnsignedInt(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getUnsignedInt(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getLong(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getLong(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public char getChar(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getChar(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public float getFloat(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getFloat(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public double getDouble(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getDouble(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuf byteBuf) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getBytes(i, byteBuf);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuf byteBuf, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getBytes(i, byteBuf, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getBytes(i, byteBuf, i2, i3);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, byte[] bArr) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getBytes(i, bArr);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, byte[] bArr, int i2, int i3) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getBytes(i, bArr, i2, i3);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, ByteBuffer byteBuffer) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getBytes(i, byteBuffer);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf getBytes(int i, OutputStream outputStream, int i2) throws IOException {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getBytes(i, outputStream, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getBytes(int i, GatheringByteChannel gatheringByteChannel, int i2) throws IOException {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getBytes(i, gatheringByteChannel, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CharSequence getCharSequence(int i, int i2, Charset charset) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getCharSequence(i, i2, charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBoolean(int i, boolean z) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setBoolean(i, z);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setByte(int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setByte(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setShort(int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setShort(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setMedium(int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setMedium(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setInt(int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setInt(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setLong(int i, long j) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setLong(i, j);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setChar(int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setChar(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setFloat(int i, float f) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setFloat(i, f);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setDouble(int i, double d) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setDouble(i, d);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuf byteBuf) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setBytes(i, byteBuf);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuf byteBuf, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setBytes(i, byteBuf, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuf byteBuf, int i2, int i3) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setBytes(i, byteBuf, i2, i3);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, byte[] bArr) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setBytes(i, bArr);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, byte[] bArr, int i2, int i3) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setBytes(i, bArr, i2, i3);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setBytes(int i, ByteBuffer byteBuffer) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setBytes(i, byteBuffer);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, InputStream inputStream, int i2) throws IOException {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setBytes(i, inputStream, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, ScatteringByteChannel scatteringByteChannel, int i2) throws IOException {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setBytes(i, scatteringByteChannel, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setZero(int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setZero(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setCharSequence(int i, CharSequence charSequence, Charset charset) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setCharSequence(i, charSequence, charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public boolean readBoolean() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readBoolean();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public byte readByte() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readByte();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short readUnsignedByte() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readUnsignedByte();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short readShort() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readShort();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readUnsignedShort() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readUnsignedShort();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readMedium() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readMedium();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readUnsignedMedium() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readUnsignedMedium();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readInt() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readInt();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long readUnsignedInt() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readUnsignedInt();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long readLong() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readLong();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public char readChar() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readChar();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public float readFloat() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readFloat();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public double readDouble() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readDouble();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readBytes(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(ByteBuf byteBuf) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readBytes(byteBuf);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(ByteBuf byteBuf, int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readBytes(byteBuf, i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(ByteBuf byteBuf, int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readBytes(byteBuf, i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(byte[] bArr) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readBytes(bArr);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(byte[] bArr, int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readBytes(bArr, i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(ByteBuffer byteBuffer) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readBytes(byteBuffer);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf readBytes(OutputStream outputStream, int i) throws IOException {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readBytes(outputStream, i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readBytes(GatheringByteChannel gatheringByteChannel, int i) throws IOException {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readBytes(gatheringByteChannel, i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public CharSequence readCharSequence(int i, Charset charset) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readCharSequence(i, charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf skipBytes(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.skipBytes(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBoolean(boolean z) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeBoolean(z);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeByte(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeByte(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeShort(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeShort(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeMedium(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeMedium(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeInt(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeInt(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeLong(long j) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeLong(j);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeChar(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeChar(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeFloat(float f) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeFloat(f);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeDouble(double d) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeDouble(d);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(ByteBuf byteBuf) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeBytes(byteBuf);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(ByteBuf byteBuf, int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeBytes(byteBuf, i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(ByteBuf byteBuf, int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeBytes(byteBuf, i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(byte[] bArr) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeBytes(bArr);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(byte[] bArr, int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeBytes(bArr, i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeBytes(ByteBuffer byteBuffer) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeBytes(byteBuffer);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writeBytes(InputStream inputStream, int i) throws IOException {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeBytes(inputStream, i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writeBytes(ScatteringByteChannel scatteringByteChannel, int i) throws IOException {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeBytes(scatteringByteChannel, i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeZero(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeZero(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int indexOf(int i, int i2, byte b) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.indexOf(i, i2, b);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int bytesBefore(byte b) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.bytesBefore(b);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int bytesBefore(int i, byte b) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.bytesBefore(i, b);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int bytesBefore(int i, int i2, byte b) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.bytesBefore(i, i2, b);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByte(ByteProcessor byteProcessor) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.forEachByte(byteProcessor);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByte(int i, int i2, ByteProcessor byteProcessor) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.forEachByte(i, i2, byteProcessor);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByteDesc(ByteProcessor byteProcessor) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.forEachByteDesc(byteProcessor);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int forEachByteDesc(int i, int i2, ByteProcessor byteProcessor) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.forEachByteDesc(i, i2, byteProcessor);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf copy() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.copy();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf copy(int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.copy(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int nioBufferCount() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.nioBufferCount();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer nioBuffer() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.nioBuffer();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer nioBuffer(int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.nioBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer[] nioBuffers() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.nioBuffers();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer[] nioBuffers(int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.nioBuffers(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuffer internalNioBuffer(int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.internalNioBuffer(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public String toString(Charset charset) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.toString(charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public String toString(int i, int i2, Charset charset) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.toString(i, i2, charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf capacity(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.capacity(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short getShortLE(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getShortLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedShortLE(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getUnsignedShortLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getMediumLE(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getMediumLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getUnsignedMediumLE(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getUnsignedMediumLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getIntLE(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getIntLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getUnsignedIntLE(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getUnsignedIntLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long getLongLE(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getLongLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setShortLE(int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setShortLE(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setIntLE(int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setIntLE(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setMediumLE(int i, int i2) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setMediumLE(i, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf setLongLE(int i, long j) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setLongLE(i, j);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public short readShortLE() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readShortLE();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readUnsignedShortLE() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readUnsignedShortLE();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readMediumLE() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readMediumLE();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readUnsignedMediumLE() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readUnsignedMediumLE();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readIntLE() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readIntLE();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long readUnsignedIntLE() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readUnsignedIntLE();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public long readLongLE() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readLongLE();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeShortLE(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeShortLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeMediumLE(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeMediumLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeIntLE(int i) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeIntLE(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf writeLongLE(long j) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeLongLE(j);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writeCharSequence(CharSequence charSequence, Charset charset) {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeCharSequence(charSequence, charset);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int getBytes(int i, FileChannel fileChannel, long j, int i2) throws IOException {
        recordLeakNonRefCountingOperation(this.leak);
        return super.getBytes(i, fileChannel, j, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int setBytes(int i, FileChannel fileChannel, long j, int i2) throws IOException {
        recordLeakNonRefCountingOperation(this.leak);
        return super.setBytes(i, fileChannel, j, i2);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int readBytes(FileChannel fileChannel, long j, int i) throws IOException {
        recordLeakNonRefCountingOperation(this.leak);
        return super.readBytes(fileChannel, j, i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public int writeBytes(FileChannel fileChannel, long j, int i) throws IOException {
        recordLeakNonRefCountingOperation(this.leak);
        return super.writeBytes(fileChannel, j, i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SimpleLeakAwareByteBuf, io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf
    public ByteBuf asReadOnly() {
        recordLeakNonRefCountingOperation(this.leak);
        return super.asReadOnly();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf retain() {
        this.leak.record();
        return super.retain();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf retain(int i) {
        this.leak.record();
        return super.retain(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SimpleLeakAwareByteBuf, io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release() {
        this.leak.record();
        return super.release();
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SimpleLeakAwareByteBuf, io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public boolean release(int i) {
        this.leak.record();
        return super.release(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SimpleLeakAwareByteBuf, io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf touch() {
        this.leak.record();
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SimpleLeakAwareByteBuf, io.grpc.netty.shaded.io.netty.buffer.WrappedByteBuf, io.grpc.netty.shaded.io.netty.buffer.ByteBuf, io.grpc.netty.shaded.io.netty.util.ReferenceCounted
    public ByteBuf touch(Object obj) {
        this.leak.record(obj);
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.buffer.SimpleLeakAwareByteBuf
    protected AdvancedLeakAwareByteBuf newLeakAwareByteBuf(ByteBuf byteBuf, ByteBuf byteBuf2, ResourceLeakTracker<ByteBuf> resourceLeakTracker) {
        return new AdvancedLeakAwareByteBuf(byteBuf, byteBuf2, resourceLeakTracker);
    }
}
