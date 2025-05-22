package io.netty.handler.codec.serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class ObjectEncoderOutputStream extends OutputStream implements ObjectOutput {
    private final int estimatedLength;
    private final DataOutputStream out;

    public ObjectEncoderOutputStream(OutputStream outputStream) {
        this(outputStream, 512);
    }

    public ObjectEncoderOutputStream(OutputStream outputStream, int i) {
        if (outputStream == null) {
            throw new NullPointerException("out");
        }
        if (i < 0) {
            throw new IllegalArgumentException("estimatedLength: " + i);
        }
        if (outputStream instanceof DataOutputStream) {
            this.out = (DataOutputStream) outputStream;
        } else {
            this.out = new DataOutputStream(outputStream);
        }
        this.estimatedLength = i;
    }

    @Override // java.io.ObjectOutput
    public void writeObject(Object obj) throws IOException {
        ByteBuf buffer = Unpooled.buffer(this.estimatedLength);
        try {
            CompactObjectOutputStream compactObjectOutputStream = new CompactObjectOutputStream(new ByteBufOutputStream(buffer));
            try {
                compactObjectOutputStream.writeObject(obj);
                compactObjectOutputStream.flush();
                compactObjectOutputStream.close();
                int readableBytes = buffer.readableBytes();
                writeInt(readableBytes);
                buffer.getBytes(0, this, readableBytes);
            } catch (Throwable th) {
                compactObjectOutputStream.close();
                throw th;
            }
        } finally {
            buffer.release();
        }
    }

    @Override // java.io.OutputStream, java.io.ObjectOutput, java.io.DataOutput
    public void write(int i) throws IOException {
        this.out.write(i);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.ObjectOutput
    public void close() throws IOException {
        this.out.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable, java.io.ObjectOutput
    public void flush() throws IOException {
        this.out.flush();
    }

    public final int size() {
        return this.out.size();
    }

    @Override // java.io.OutputStream, java.io.ObjectOutput, java.io.DataOutput
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
    }

    @Override // java.io.OutputStream, java.io.ObjectOutput, java.io.DataOutput
    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
    }

    @Override // java.io.DataOutput
    public final void writeBoolean(boolean z) throws IOException {
        this.out.writeBoolean(z);
    }

    @Override // java.io.DataOutput
    public final void writeByte(int i) throws IOException {
        this.out.writeByte(i);
    }

    @Override // java.io.DataOutput
    public final void writeBytes(String str) throws IOException {
        this.out.writeBytes(str);
    }

    @Override // java.io.DataOutput
    public final void writeChar(int i) throws IOException {
        this.out.writeChar(i);
    }

    @Override // java.io.DataOutput
    public final void writeChars(String str) throws IOException {
        this.out.writeChars(str);
    }

    @Override // java.io.DataOutput
    public final void writeDouble(double d) throws IOException {
        this.out.writeDouble(d);
    }

    @Override // java.io.DataOutput
    public final void writeFloat(float f) throws IOException {
        this.out.writeFloat(f);
    }

    @Override // java.io.DataOutput
    public final void writeInt(int i) throws IOException {
        this.out.writeInt(i);
    }

    @Override // java.io.DataOutput
    public final void writeLong(long j) throws IOException {
        this.out.writeLong(j);
    }

    @Override // java.io.DataOutput
    public final void writeShort(int i) throws IOException {
        this.out.writeShort(i);
    }

    @Override // java.io.DataOutput
    public final void writeUTF(String str) throws IOException {
        this.out.writeUTF(str);
    }
}
