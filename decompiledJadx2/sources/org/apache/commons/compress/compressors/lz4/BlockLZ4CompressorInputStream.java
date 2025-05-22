package org.apache.commons.compress.compressors.lz4;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.lz77support.AbstractLZ77CompressorInputStream;
import org.apache.commons.compress.utils.ByteUtils;

/* loaded from: classes8.dex */
public class BlockLZ4CompressorInputStream extends AbstractLZ77CompressorInputStream {
    static final int BACK_REFERENCE_SIZE_MASK = 15;
    static final int LITERAL_SIZE_MASK = 240;
    static final int SIZE_BITS = 4;
    static final int WINDOW_SIZE = 65536;
    private int nextBackReferenceSize;
    private State state;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public enum State {
        NO_BLOCK,
        IN_LITERAL,
        LOOKING_FOR_BACK_REFERENCE,
        IN_BACK_REFERENCE,
        EOF
    }

    public BlockLZ4CompressorInputStream(InputStream inputStream) throws IOException {
        super(inputStream, 65536);
        this.state = State.NO_BLOCK;
    }

    /* renamed from: org.apache.commons.compress.compressors.lz4.BlockLZ4CompressorInputStream$1 */
    /* loaded from: classes8.dex */
    static /* synthetic */ class C81051 {

        /* renamed from: $SwitchMap$org$apache$commons$compress$compressors$lz4$BlockLZ4CompressorInputStream$State */
        static final /* synthetic */ int[] f8935xf30f4b5 = new int[State.values().length];

        static {
            try {
                f8935xf30f4b5[State.EOF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8935xf30f4b5[State.NO_BLOCK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8935xf30f4b5[State.IN_LITERAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f8935xf30f4b5[State.LOOKING_FOR_BACK_REFERENCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f8935xf30f4b5[State.IN_BACK_REFERENCE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int i3 = C81051.f8935xf30f4b5[this.state.ordinal()];
        if (i3 == 1) {
            return -1;
        }
        if (i3 == 2) {
            readSizes();
        } else if (i3 != 3) {
            if (i3 != 4) {
                if (i3 != 5) {
                    throw new IOException("Unknown stream state " + this.state);
                }
            } else if (!initializeBackReference()) {
                this.state = State.EOF;
                return -1;
            }
            int readBackReference = readBackReference(bArr, i, i2);
            if (!hasMoreDataInBlock()) {
                this.state = State.NO_BLOCK;
            }
            return readBackReference > 0 ? readBackReference : read(bArr, i, i2);
        }
        int readLiteral = readLiteral(bArr, i, i2);
        if (!hasMoreDataInBlock()) {
            this.state = State.LOOKING_FOR_BACK_REFERENCE;
        }
        return readLiteral > 0 ? readLiteral : read(bArr, i, i2);
    }

    private void readSizes() throws IOException {
        int readOneByte = readOneByte();
        if (readOneByte == -1) {
            throw new IOException("Premature end of stream while looking for next block");
        }
        this.nextBackReferenceSize = readOneByte & 15;
        long j = (readOneByte & 240) >> 4;
        if (j == 15) {
            j += readSizeBytes();
        }
        if (j < 0) {
            throw new IOException("Illegal block with a negative literal size found");
        }
        startLiteral(j);
        this.state = State.IN_LITERAL;
    }

    private long readSizeBytes() throws IOException {
        int readOneByte;
        long j = 0;
        do {
            readOneByte = readOneByte();
            if (readOneByte == -1) {
                throw new IOException("Premature end of stream while parsing length");
            }
            j += readOneByte;
        } while (readOneByte == 255);
        return j;
    }

    private boolean initializeBackReference() throws IOException {
        try {
            int fromLittleEndian = (int) ByteUtils.fromLittleEndian(this.supplier, 2);
            int i = this.nextBackReferenceSize;
            long j = i;
            if (i == 15) {
                j += readSizeBytes();
            }
            if (j < 0) {
                throw new IOException("Illegal block with a negative match length found");
            }
            try {
                startBackReference(fromLittleEndian, j + 4);
                this.state = State.IN_BACK_REFERENCE;
                return true;
            } catch (IllegalArgumentException e) {
                throw new IOException("Illegal block with bad offset found", e);
            }
        } catch (IOException e2) {
            if (this.nextBackReferenceSize == 0) {
                return false;
            }
            throw e2;
        }
    }
}
