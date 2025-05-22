package io.netty.handler.codec.serialization;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.StreamCorruptedException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class ObjectDecoderInputStream extends InputStream implements ObjectInput {
    private final ClassResolver classResolver;

    /* renamed from: in */
    private final DataInputStream f8516in;
    private final int maxObjectSize;

    public ObjectDecoderInputStream(InputStream inputStream) {
        this(inputStream, (ClassLoader) null);
    }

    public ObjectDecoderInputStream(InputStream inputStream, ClassLoader classLoader) {
        this(inputStream, classLoader, 1048576);
    }

    public ObjectDecoderInputStream(InputStream inputStream, int i) {
        this(inputStream, null, i);
    }

    public ObjectDecoderInputStream(InputStream inputStream, ClassLoader classLoader, int i) {
        if (inputStream == null) {
            throw new NullPointerException("in");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("maxObjectSize: " + i);
        }
        if (inputStream instanceof DataInputStream) {
            this.f8516in = (DataInputStream) inputStream;
        } else {
            this.f8516in = new DataInputStream(inputStream);
        }
        this.classResolver = ClassResolvers.weakCachingResolver(classLoader);
        this.maxObjectSize = i;
    }

    @Override // java.io.ObjectInput
    public Object readObject() throws ClassNotFoundException, IOException {
        int readInt = readInt();
        if (readInt <= 0) {
            throw new StreamCorruptedException("invalid data length: " + readInt);
        }
        if (readInt > this.maxObjectSize) {
            throw new StreamCorruptedException("data length too big: " + readInt + " (max: " + this.maxObjectSize + ')');
        }
        return new CompactObjectInputStream(this.f8516in, this.classResolver).readObject();
    }

    @Override // java.io.InputStream, java.io.ObjectInput
    public int available() throws IOException {
        return this.f8516in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.ObjectInput
    public void close() throws IOException {
        this.f8516in.close();
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.f8516in.mark(i);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f8516in.markSupported();
    }

    @Override // java.io.InputStream, java.io.ObjectInput
    public int read() throws IOException {
        return this.f8516in.read();
    }

    @Override // java.io.InputStream, java.io.ObjectInput
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        return this.f8516in.read(bArr, i, i2);
    }

    @Override // java.io.InputStream, java.io.ObjectInput
    public final int read(byte[] bArr) throws IOException {
        return this.f8516in.read(bArr);
    }

    @Override // java.io.DataInput
    public final boolean readBoolean() throws IOException {
        return this.f8516in.readBoolean();
    }

    @Override // java.io.DataInput
    public final byte readByte() throws IOException {
        return this.f8516in.readByte();
    }

    @Override // java.io.DataInput
    public final char readChar() throws IOException {
        return this.f8516in.readChar();
    }

    @Override // java.io.DataInput
    public final double readDouble() throws IOException {
        return this.f8516in.readDouble();
    }

    @Override // java.io.DataInput
    public final float readFloat() throws IOException {
        return this.f8516in.readFloat();
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] bArr, int i, int i2) throws IOException {
        this.f8516in.readFully(bArr, i, i2);
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] bArr) throws IOException {
        this.f8516in.readFully(bArr);
    }

    @Override // java.io.DataInput
    public final int readInt() throws IOException {
        return this.f8516in.readInt();
    }

    @Override // java.io.DataInput
    @Deprecated
    public final String readLine() throws IOException {
        return this.f8516in.readLine();
    }

    @Override // java.io.DataInput
    public final long readLong() throws IOException {
        return this.f8516in.readLong();
    }

    @Override // java.io.DataInput
    public final short readShort() throws IOException {
        return this.f8516in.readShort();
    }

    @Override // java.io.DataInput
    public final int readUnsignedByte() throws IOException {
        return this.f8516in.readUnsignedByte();
    }

    @Override // java.io.DataInput
    public final int readUnsignedShort() throws IOException {
        return this.f8516in.readUnsignedShort();
    }

    @Override // java.io.DataInput
    public final String readUTF() throws IOException {
        return this.f8516in.readUTF();
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        this.f8516in.reset();
    }

    @Override // java.io.InputStream, java.io.ObjectInput
    public long skip(long j) throws IOException {
        return this.f8516in.skip(j);
    }

    @Override // java.io.DataInput
    public final int skipBytes(int i) throws IOException {
        return this.f8516in.skipBytes(i);
    }
}
