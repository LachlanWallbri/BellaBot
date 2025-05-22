package org.jboss.netty.handler.codec.serialization;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.StreamCorruptedException;

/* loaded from: classes7.dex */
public class ObjectDecoderInputStream extends InputStream implements ObjectInput {
    private final ClassResolver classResolver;

    /* renamed from: in */
    private final DataInputStream f10039in;
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
            this.f10039in = (DataInputStream) inputStream;
        } else {
            this.f10039in = new DataInputStream(inputStream);
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
        return new CompactObjectInputStream(this.f10039in, this.classResolver).readObject();
    }

    @Override // java.io.InputStream, java.io.ObjectInput
    public int available() throws IOException {
        return this.f10039in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.ObjectInput
    public void close() throws IOException {
        this.f10039in.close();
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.f10039in.mark(i);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f10039in.markSupported();
    }

    @Override // java.io.InputStream, java.io.ObjectInput
    public int read() throws IOException {
        return this.f10039in.read();
    }

    @Override // java.io.InputStream, java.io.ObjectInput
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        return this.f10039in.read(bArr, i, i2);
    }

    @Override // java.io.InputStream, java.io.ObjectInput
    public final int read(byte[] bArr) throws IOException {
        return this.f10039in.read(bArr);
    }

    @Override // java.io.DataInput
    public final boolean readBoolean() throws IOException {
        return this.f10039in.readBoolean();
    }

    @Override // java.io.DataInput
    public final byte readByte() throws IOException {
        return this.f10039in.readByte();
    }

    @Override // java.io.DataInput
    public final char readChar() throws IOException {
        return this.f10039in.readChar();
    }

    @Override // java.io.DataInput
    public final double readDouble() throws IOException {
        return this.f10039in.readDouble();
    }

    @Override // java.io.DataInput
    public final float readFloat() throws IOException {
        return this.f10039in.readFloat();
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] bArr, int i, int i2) throws IOException {
        this.f10039in.readFully(bArr, i, i2);
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] bArr) throws IOException {
        this.f10039in.readFully(bArr);
    }

    @Override // java.io.DataInput
    public final int readInt() throws IOException {
        return this.f10039in.readInt();
    }

    @Override // java.io.DataInput
    @Deprecated
    public final String readLine() throws IOException {
        return this.f10039in.readLine();
    }

    @Override // java.io.DataInput
    public final long readLong() throws IOException {
        return this.f10039in.readLong();
    }

    @Override // java.io.DataInput
    public final short readShort() throws IOException {
        return this.f10039in.readShort();
    }

    @Override // java.io.DataInput
    public final int readUnsignedByte() throws IOException {
        return this.f10039in.readUnsignedByte();
    }

    @Override // java.io.DataInput
    public final int readUnsignedShort() throws IOException {
        return this.f10039in.readUnsignedShort();
    }

    @Override // java.io.DataInput
    public final String readUTF() throws IOException {
        return this.f10039in.readUTF();
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        this.f10039in.reset();
    }

    @Override // java.io.InputStream, java.io.ObjectInput
    public long skip(long j) throws IOException {
        return this.f10039in.skip(j);
    }

    @Override // java.io.DataInput
    public final int skipBytes(int i) throws IOException {
        return this.f10039in.skipBytes(i);
    }
}
