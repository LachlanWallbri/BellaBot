package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import org.apache.commons.compress.utils.BitInputStream;

/* loaded from: classes8.dex */
class BitStream extends BitInputStream {
    /* JADX INFO: Access modifiers changed from: package-private */
    public BitStream(InputStream inputStream) {
        super(inputStream, ByteOrder.LITTLE_ENDIAN);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int nextBit() throws IOException {
        return (int) readBits(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long nextBits(int i) throws IOException {
        if (i < 0 || i > 8) {
            throw new IOException("Trying to read " + i + " bits, at most 8 are allowed");
        }
        return readBits(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int nextByte() throws IOException {
        return (int) readBits(8);
    }
}
