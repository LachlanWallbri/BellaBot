package org.apache.commons.compress.archivers.dump;

import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.utils.ByteUtils;

/* loaded from: classes7.dex */
class DumpArchiveUtil {
    private DumpArchiveUtil() {
    }

    public static int calculateChecksum(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            i += convert32(bArr, i2 * 4);
        }
        return DumpArchiveConstants.CHECKSUM - (i - convert32(bArr, 28));
    }

    public static final boolean verify(byte[] bArr) {
        return convert32(bArr, 24) == 60012 && convert32(bArr, 28) == calculateChecksum(bArr);
    }

    public static final int getIno(byte[] bArr) {
        return convert32(bArr, 20);
    }

    public static final long convert64(byte[] bArr, int i) {
        return ByteUtils.fromLittleEndian(bArr, i, 8);
    }

    public static final int convert32(byte[] bArr, int i) {
        return (int) ByteUtils.fromLittleEndian(bArr, i, 4);
    }

    public static final int convert16(byte[] bArr, int i) {
        return (int) ByteUtils.fromLittleEndian(bArr, i, 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String decode(ZipEncoding zipEncoding, byte[] bArr, int i, int i2) throws IOException {
        return zipEncoding.decode(Arrays.copyOfRange(bArr, i, i2 + i));
    }
}
