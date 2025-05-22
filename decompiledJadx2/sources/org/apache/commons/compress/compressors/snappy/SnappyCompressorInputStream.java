package org.apache.commons.compress.compressors.snappy;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.lz77support.AbstractLZ77CompressorInputStream;
import org.apache.commons.compress.utils.ByteUtils;

/* loaded from: classes8.dex */
public class SnappyCompressorInputStream extends AbstractLZ77CompressorInputStream {
    public static final int DEFAULT_BLOCK_SIZE = 32768;
    private static final int TAG_MASK = 3;
    private boolean endReached;
    private final int size;
    private State state;
    private int uncompressedBytesRemaining;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public enum State {
        NO_BLOCK,
        IN_LITERAL,
        IN_BACK_REFERENCE
    }

    public SnappyCompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, 32768);
    }

    public SnappyCompressorInputStream(InputStream inputStream, int i) throws IOException {
        super(inputStream, i);
        this.state = State.NO_BLOCK;
        int readSize = (int) readSize();
        this.size = readSize;
        this.uncompressedBytesRemaining = readSize;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (this.endReached) {
            return -1;
        }
        int i3 = C81131.f8945xeace0a14[this.state.ordinal()];
        if (i3 == 1) {
            fill();
            return read(bArr, i, i2);
        }
        if (i3 == 2) {
            int readLiteral = readLiteral(bArr, i, i2);
            if (!hasMoreDataInBlock()) {
                this.state = State.NO_BLOCK;
            }
            return readLiteral > 0 ? readLiteral : read(bArr, i, i2);
        }
        if (i3 == 3) {
            int readBackReference = readBackReference(bArr, i, i2);
            if (!hasMoreDataInBlock()) {
                this.state = State.NO_BLOCK;
            }
            return readBackReference > 0 ? readBackReference : read(bArr, i, i2);
        }
        throw new IOException("Unknown stream state " + this.state);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.apache.commons.compress.compressors.snappy.SnappyCompressorInputStream$1 */
    /* loaded from: classes8.dex */
    public static /* synthetic */ class C81131 {

        /* renamed from: $SwitchMap$org$apache$commons$compress$compressors$snappy$SnappyCompressorInputStream$State */
        static final /* synthetic */ int[] f8945xeace0a14 = new int[State.values().length];

        static {
            try {
                f8945xeace0a14[State.NO_BLOCK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8945xeace0a14[State.IN_LITERAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8945xeace0a14[State.IN_BACK_REFERENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void fill() throws IOException {
        if (this.uncompressedBytesRemaining == 0) {
            this.endReached = true;
            return;
        }
        int readOneByte = readOneByte();
        if (readOneByte == -1) {
            throw new IOException("Premature end of stream reading block start");
        }
        int i = readOneByte & 3;
        if (i == 0) {
            int readLiteralLength = readLiteralLength(readOneByte);
            if (readLiteralLength < 0) {
                throw new IOException("Illegal block with a negative literal size found");
            }
            this.uncompressedBytesRemaining -= readLiteralLength;
            startLiteral(readLiteralLength);
            this.state = State.IN_LITERAL;
            return;
        }
        if (i == 1) {
            int i2 = ((readOneByte >> 2) & 7) + 4;
            if (i2 < 0) {
                throw new IOException("Illegal block with a negative match length found");
            }
            this.uncompressedBytesRemaining -= i2;
            int i3 = (readOneByte & 224) << 3;
            int readOneByte2 = readOneByte();
            if (readOneByte2 == -1) {
                throw new IOException("Premature end of stream reading back-reference length");
            }
            try {
                startBackReference(i3 | readOneByte2, i2);
                this.state = State.IN_BACK_REFERENCE;
                return;
            } catch (IllegalArgumentException e) {
                throw new IOException("Illegal block with bad offset found", e);
            }
        }
        if (i == 2) {
            int i4 = (readOneByte >> 2) + 1;
            if (i4 < 0) {
                throw new IOException("Illegal block with a negative match length found");
            }
            this.uncompressedBytesRemaining -= i4;
            try {
                startBackReference((int) ByteUtils.fromLittleEndian(this.supplier, 2), i4);
                this.state = State.IN_BACK_REFERENCE;
                return;
            } catch (IllegalArgumentException e2) {
                throw new IOException("Illegal block with bad offset found", e2);
            }
        }
        if (i != 3) {
            return;
        }
        int i5 = (readOneByte >> 2) + 1;
        if (i5 < 0) {
            throw new IOException("Illegal block with a negative match length found");
        }
        this.uncompressedBytesRemaining -= i5;
        try {
            startBackReference(((int) ByteUtils.fromLittleEndian(this.supplier, 4)) & Integer.MAX_VALUE, i5);
            this.state = State.IN_BACK_REFERENCE;
        } catch (IllegalArgumentException e3) {
            throw new IOException("Illegal block with bad offset found", e3);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0002. Please report as an issue. */
    private int readLiteralLength(int i) throws IOException {
        long fromLittleEndian;
        int i2 = i >> 2;
        switch (i2) {
            case 60:
                i2 = readOneByte();
                if (i2 == -1) {
                    throw new IOException("Premature end of stream reading literal length");
                }
                return i2 + 1;
            case 61:
                fromLittleEndian = ByteUtils.fromLittleEndian(this.supplier, 2);
                i2 = (int) fromLittleEndian;
                return i2 + 1;
            case 62:
                fromLittleEndian = ByteUtils.fromLittleEndian(this.supplier, 3);
                i2 = (int) fromLittleEndian;
                return i2 + 1;
            case 63:
                fromLittleEndian = ByteUtils.fromLittleEndian(this.supplier, 4);
                i2 = (int) fromLittleEndian;
                return i2 + 1;
            default:
                return i2 + 1;
        }
    }

    private long readSize() throws IOException {
        int i = 0;
        long j = 0;
        while (true) {
            int readOneByte = readOneByte();
            if (readOneByte == -1) {
                throw new IOException("Premature end of stream reading size");
            }
            int i2 = i + 1;
            j |= (readOneByte & 127) << (i * 7);
            if ((readOneByte & 128) == 0) {
                return j;
            }
            i = i2;
        }
    }

    @Override // org.apache.commons.compress.compressors.lz77support.AbstractLZ77CompressorInputStream
    public int getSize() {
        return this.size;
    }
}
