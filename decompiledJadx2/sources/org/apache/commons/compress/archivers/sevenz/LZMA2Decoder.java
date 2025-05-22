package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.MemoryLimitException;
import org.tukaani.xz.FinishableWrapperOutputStream;
import org.tukaani.xz.LZMA2InputStream;
import org.tukaani.xz.LZMA2Options;

/* loaded from: classes8.dex */
class LZMA2Decoder extends CoderBase {
    /* JADX INFO: Access modifiers changed from: package-private */
    public LZMA2Decoder() {
        super(LZMA2Options.class, Number.class);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.compress.archivers.sevenz.CoderBase
    public InputStream decode(String str, InputStream inputStream, long j, Coder coder, byte[] bArr, int i) throws IOException {
        try {
            int dictionarySize = getDictionarySize(coder);
            int memoryUsage = LZMA2InputStream.getMemoryUsage(dictionarySize);
            if (memoryUsage > i) {
                throw new MemoryLimitException(memoryUsage, i);
            }
            return new LZMA2InputStream(inputStream, dictionarySize);
        } catch (IllegalArgumentException e) {
            throw new IOException(e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.compress.archivers.sevenz.CoderBase
    public OutputStream encode(OutputStream outputStream, Object obj) throws IOException {
        return getOptions(obj).getOutputStream(new FinishableWrapperOutputStream(outputStream));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.compress.archivers.sevenz.CoderBase
    public byte[] getOptionsAsProperties(Object obj) {
        return new byte[]{(byte) (((19 - Integer.numberOfLeadingZeros(getDictSize(obj))) * 2) + ((r3 >>> (30 - r0)) - 2))};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.apache.commons.compress.archivers.sevenz.CoderBase
    public Object getOptionsFromCoder(Coder coder, InputStream inputStream) throws IOException {
        return Integer.valueOf(getDictionarySize(coder));
    }

    private int getDictSize(Object obj) {
        if (obj instanceof LZMA2Options) {
            return ((LZMA2Options) obj).getDictSize();
        }
        return numberOptionOrDefault(obj);
    }

    private int getDictionarySize(Coder coder) throws IOException {
        if (coder.properties == null) {
            throw new IOException("Missing LZMA2 properties");
        }
        if (coder.properties.length < 1) {
            throw new IOException("LZMA2 properties too short");
        }
        int i = coder.properties[0] & 255;
        if ((i & (-64)) != 0) {
            throw new IOException("Unsupported LZMA2 property bits");
        }
        if (i > 40) {
            throw new IOException("Dictionary larger than 4GiB maximum size");
        }
        if (i == 40) {
            return -1;
        }
        return ((i & 1) | 2) << ((i / 2) + 11);
    }

    private LZMA2Options getOptions(Object obj) throws IOException {
        if (obj instanceof LZMA2Options) {
            return (LZMA2Options) obj;
        }
        LZMA2Options lZMA2Options = new LZMA2Options();
        lZMA2Options.setDictSize(numberOptionOrDefault(obj));
        return lZMA2Options;
    }

    private int numberOptionOrDefault(Object obj) {
        return numberOptionOrDefault(obj, 8388608);
    }
}
