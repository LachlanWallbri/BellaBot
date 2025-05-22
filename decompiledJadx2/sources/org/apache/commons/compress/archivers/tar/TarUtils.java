package org.apache.commons.compress.archivers.tar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.IOUtils;

/* loaded from: classes8.dex */
public class TarUtils {
    private static final int BYTE_MASK = 255;
    static final ZipEncoding DEFAULT_ENCODING = ZipEncodingHelper.getZipEncoding(null);
    static final ZipEncoding FALLBACK_ENCODING = new ZipEncoding() { // from class: org.apache.commons.compress.archivers.tar.TarUtils.1
        @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
        public boolean canEncode(String str) {
            return true;
        }

        @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
        public ByteBuffer encode(String str) {
            int length = str.length();
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr[i] = (byte) str.charAt(i);
            }
            return ByteBuffer.wrap(bArr);
        }

        @Override // org.apache.commons.compress.archivers.zip.ZipEncoding
        public String decode(byte[] bArr) {
            StringBuilder sb = new StringBuilder(bArr.length);
            for (byte b : bArr) {
                if (b == 0) {
                    break;
                }
                sb.append((char) (b & 255));
            }
            return sb.toString();
        }
    };

    private TarUtils() {
    }

    public static long parseOctal(byte[] bArr, int i, int i2) {
        int i3 = i + i2;
        if (i2 < 2) {
            throw new IllegalArgumentException("Length " + i2 + " must be at least 2");
        }
        long j = 0;
        if (bArr[i] == 0) {
            return 0L;
        }
        int i4 = i;
        while (i4 < i3 && bArr[i4] == 32) {
            i4++;
        }
        byte b = bArr[i3 - 1];
        while (i4 < i3 && (b == 0 || b == 32)) {
            i3--;
            b = bArr[i3 - 1];
        }
        while (i4 < i3) {
            byte b2 = bArr[i4];
            if (b2 < 48 || b2 > 55) {
                throw new IllegalArgumentException(exceptionMessage(bArr, i, i2, i4, b2));
            }
            j = (j << 3) + (b2 - 48);
            i4++;
        }
        return j;
    }

    public static long parseOctalOrBinary(byte[] bArr, int i, int i2) {
        if ((bArr[i] & 128) == 0) {
            return parseOctal(bArr, i, i2);
        }
        boolean z = bArr[i] == -1;
        if (i2 < 9) {
            return parseBinaryLong(bArr, i, i2, z);
        }
        return parseBinaryBigInteger(bArr, i, i2, z);
    }

    private static long parseBinaryLong(byte[] bArr, int i, int i2, boolean z) {
        if (i2 >= 9) {
            throw new IllegalArgumentException("At offset " + i + ", " + i2 + " byte binary number exceeds maximum signed long value");
        }
        long j = 0;
        for (int i3 = 1; i3 < i2; i3++) {
            j = (j << 8) + (bArr[i + i3] & 255);
        }
        if (z) {
            j = (j - 1) ^ (((long) Math.pow(2.0d, (i2 - 1) * 8.0d)) - 1);
        }
        return z ? -j : j;
    }

    private static long parseBinaryBigInteger(byte[] bArr, int i, int i2, boolean z) {
        int i3 = i2 - 1;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i + 1, bArr2, 0, i3);
        BigInteger bigInteger = new BigInteger(bArr2);
        if (z) {
            bigInteger = bigInteger.add(BigInteger.valueOf(-1L)).not();
        }
        if (bigInteger.bitLength() > 63) {
            throw new IllegalArgumentException("At offset " + i + ", " + i2 + " byte binary number exceeds maximum signed long value");
        }
        long longValue = bigInteger.longValue();
        return z ? -longValue : longValue;
    }

    public static boolean parseBoolean(byte[] bArr, int i) {
        return bArr[i] == 1;
    }

    private static String exceptionMessage(byte[] bArr, int i, int i2, int i3, byte b) {
        return "Invalid byte " + ((int) b) + " at offset " + (i3 - i) + " in '" + new String(bArr, i, i2).replace("\u0000", "{NUL}") + "' len=" + i2;
    }

    public static String parseName(byte[] bArr, int i, int i2) {
        try {
            try {
                return parseName(bArr, i, i2, DEFAULT_ENCODING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException unused) {
            return parseName(bArr, i, i2, FALLBACK_ENCODING);
        }
    }

    public static String parseName(byte[] bArr, int i, int i2, ZipEncoding zipEncoding) throws IOException {
        int i3 = 0;
        for (int i4 = i; i3 < i2 && bArr[i4] != 0; i4++) {
            i3++;
        }
        if (i3 <= 0) {
            return "";
        }
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i, bArr2, 0, i3);
        return zipEncoding.decode(bArr2);
    }

    public static TarArchiveStructSparse parseSparse(byte[] bArr, int i) {
        return new TarArchiveStructSparse(parseOctalOrBinary(bArr, i, 12), parseOctalOrBinary(bArr, i + 12, 12));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<TarArchiveStructSparse> readSparseStructs(byte[] bArr, int i, int i2) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < i2; i3++) {
            try {
                TarArchiveStructSparse parseSparse = parseSparse(bArr, (i3 * 24) + i);
                if (parseSparse.getOffset() < 0) {
                    throw new IOException("Corrupted TAR archive, sparse entry with negative offset");
                }
                if (parseSparse.getNumbytes() < 0) {
                    throw new IOException("Corrupted TAR archive, sparse entry with negative numbytes");
                }
                arrayList.add(parseSparse);
            } catch (IllegalArgumentException e) {
                throw new IOException("Corrupted TAR archive, sparse entry is invalid", e);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static int formatNameBytes(String str, byte[] bArr, int i, int i2) {
        try {
            try {
                return formatNameBytes(str, bArr, i, i2, DEFAULT_ENCODING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException unused) {
            return formatNameBytes(str, bArr, i, i2, FALLBACK_ENCODING);
        }
    }

    public static int formatNameBytes(String str, byte[] bArr, int i, int i2, ZipEncoding zipEncoding) throws IOException {
        int length = str.length();
        ByteBuffer encode = zipEncoding.encode(str);
        while (encode.limit() > i2 && length > 0) {
            length--;
            encode = zipEncoding.encode(str.substring(0, length));
        }
        int limit = encode.limit() - encode.position();
        System.arraycopy(encode.array(), encode.arrayOffset(), bArr, i, limit);
        while (limit < i2) {
            bArr[i + limit] = 0;
            limit++;
        }
        return i + i2;
    }

    public static void formatUnsignedOctalString(long j, byte[] bArr, int i, int i2) {
        int i3;
        int i4 = i2 - 1;
        if (j == 0) {
            i3 = i4 - 1;
            bArr[i4 + i] = TarConstants.LF_NORMAL;
        } else {
            long j2 = j;
            while (i4 >= 0 && j2 != 0) {
                bArr[i + i4] = (byte) (((byte) (7 & j2)) + TarConstants.LF_NORMAL);
                j2 >>>= 3;
                i4--;
            }
            if (j2 != 0) {
                throw new IllegalArgumentException(j + "=" + Long.toOctalString(j) + " will not fit in octal number buffer of length " + i2);
            }
            i3 = i4;
        }
        while (i3 >= 0) {
            bArr[i + i3] = TarConstants.LF_NORMAL;
            i3--;
        }
    }

    public static int formatOctalBytes(long j, byte[] bArr, int i, int i2) {
        int i3 = i2 - 2;
        formatUnsignedOctalString(j, bArr, i, i3);
        bArr[i3 + i] = 32;
        bArr[i3 + 1 + i] = 0;
        return i + i2;
    }

    public static int formatLongOctalBytes(long j, byte[] bArr, int i, int i2) {
        int i3 = i2 - 1;
        formatUnsignedOctalString(j, bArr, i, i3);
        bArr[i3 + i] = 32;
        return i + i2;
    }

    public static int formatLongOctalOrBinaryBytes(long j, byte[] bArr, int i, int i2) {
        long j2 = i2 == 8 ? TarConstants.MAXID : TarConstants.MAXSIZE;
        boolean z = j < 0;
        if (!z && j <= j2) {
            return formatLongOctalBytes(j, bArr, i, i2);
        }
        if (i2 < 9) {
            formatLongBinary(j, bArr, i, i2, z);
        } else {
            formatBigIntegerBinary(j, bArr, i, i2, z);
        }
        bArr[i] = (byte) (z ? 255 : 128);
        return i + i2;
    }

    private static void formatLongBinary(long j, byte[] bArr, int i, int i2, boolean z) {
        int i3 = (i2 - 1) * 8;
        long j2 = 1 << i3;
        long abs = Math.abs(j);
        if (abs < 0 || abs >= j2) {
            throw new IllegalArgumentException("Value " + j + " is too large for " + i2 + " byte field.");
        }
        if (z) {
            abs = ((abs ^ (j2 - 1)) + 1) | (255 << i3);
        }
        for (int i4 = (i2 + i) - 1; i4 >= i; i4--) {
            bArr[i4] = (byte) abs;
            abs >>= 8;
        }
    }

    private static void formatBigIntegerBinary(long j, byte[] bArr, int i, int i2, boolean z) {
        byte[] byteArray = BigInteger.valueOf(j).toByteArray();
        int length = byteArray.length;
        if (length > i2 - 1) {
            throw new IllegalArgumentException("Value " + j + " is too large for " + i2 + " byte field.");
        }
        int i3 = (i2 + i) - length;
        System.arraycopy(byteArray, 0, bArr, i3, length);
        byte b = (byte) (z ? 255 : 0);
        while (true) {
            i++;
            if (i >= i3) {
                return;
            } else {
                bArr[i] = b;
            }
        }
    }

    public static int formatCheckSumOctalBytes(long j, byte[] bArr, int i, int i2) {
        int i3 = i2 - 2;
        formatUnsignedOctalString(j, bArr, i, i3);
        bArr[i3 + i] = 0;
        bArr[i3 + 1 + i] = 32;
        return i + i2;
    }

    public static long computeCheckSum(byte[] bArr) {
        long j = 0;
        for (byte b : bArr) {
            j += b & 255;
        }
        return j;
    }

    public static boolean verifyCheckSum(byte[] bArr) {
        long parseOctal = parseOctal(bArr, 148, 8);
        long j = 0;
        long j2 = 0;
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            if (148 <= i && i < 156) {
                b = 32;
            }
            j2 += b & 255;
            j += b;
        }
        return parseOctal == j2 || parseOctal == j;
    }

    @Deprecated
    protected static Map<String, String> parsePaxHeaders(InputStream inputStream, List<TarArchiveStructSparse> list, Map<String, String> map) throws IOException {
        return parsePaxHeaders(inputStream, list, map, -1L);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x015f, code lost:
    
        throw new java.io.IOException("Failed to read Paxheader. Encountered a non-number while reading length");
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0164, code lost:
    
        r2 = -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Map<String, String> parsePaxHeaders(InputStream inputStream, List<TarArchiveStructSparse> list, Map<String, String> map, long j) throws IOException {
        int i;
        int i2;
        Long l;
        long j2;
        HashMap hashMap = new HashMap(map);
        int i3 = 0;
        int i4 = 0;
        Long l2 = null;
        loop0: while (true) {
            int i5 = i3;
            int i6 = i4;
            int i7 = i5;
            while (true) {
                int read = inputStream.read();
                int i8 = -1;
                long j3 = 0;
                if (read == -1) {
                    i4 = i6;
                    i = read;
                    break;
                }
                i7++;
                i6++;
                if (read == 10) {
                    i4 = i6;
                    i = read;
                    i2 = -1;
                    break;
                }
                if (read == 32) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        i = inputStream.read();
                        if (i == i8) {
                            break;
                        }
                        i7++;
                        i6++;
                        if (i6 < 0 || (j >= j3 && i6 >= j)) {
                            break;
                        }
                        if (i == 61) {
                            String byteArrayOutputStream2 = byteArrayOutputStream.toString("UTF-8");
                            int i9 = i5 - i7;
                            if (i9 <= 1) {
                                hashMap.remove(byteArrayOutputStream2);
                            } else {
                                if (j >= j3 && i9 > j - i6) {
                                    throw new IOException("Paxheader value size " + i9 + " exceeds size of header record");
                                }
                                byte[] readRange = IOUtils.readRange(inputStream, i9);
                                int length = readRange.length;
                                if (length != i9) {
                                    throw new IOException("Failed to read Paxheader. Expected " + i9 + " bytes, read " + length);
                                }
                                i6 += i9;
                                int i10 = i9 - 1;
                                if (readRange[i10] != 10) {
                                    throw new IOException("Failed to read Paxheader.Value should end with a newline");
                                }
                                String str = new String(readRange, i3, i10, StandardCharsets.UTF_8);
                                hashMap.put(byteArrayOutputStream2, str);
                                if (byteArrayOutputStream2.equals("GNU.sparse.offset")) {
                                    if (l2 != null) {
                                        j2 = 0;
                                        list.add(new TarArchiveStructSparse(l2.longValue(), 0L));
                                    } else {
                                        j2 = 0;
                                    }
                                    try {
                                        Long valueOf = Long.valueOf(str);
                                        if (valueOf.longValue() < j2) {
                                            throw new IOException("Failed to read Paxheader.GNU.sparse.offset contains negative value");
                                        }
                                        l = valueOf;
                                    } catch (NumberFormatException unused) {
                                        throw new IOException("Failed to read Paxheader.GNU.sparse.offset contains a non-numeric value");
                                    }
                                } else {
                                    l = l2;
                                }
                                if (byteArrayOutputStream2.equals("GNU.sparse.numbytes")) {
                                    if (l == null) {
                                        throw new IOException("Failed to read Paxheader.GNU.sparse.offset is expected before GNU.sparse.numbytes shows up.");
                                    }
                                    try {
                                        long parseLong = Long.parseLong(str);
                                        if (parseLong < 0) {
                                            throw new IOException("Failed to read Paxheader.GNU.sparse.numbytes contains negative value");
                                        }
                                        list.add(new TarArchiveStructSparse(l.longValue(), parseLong));
                                        l = null;
                                    } catch (NumberFormatException unused2) {
                                        throw new IOException("Failed to read Paxheader.GNU.sparse.numbytes contains a non-numeric value.");
                                    }
                                }
                                l2 = l;
                            }
                        } else {
                            byteArrayOutputStream.write((byte) i);
                            i3 = 0;
                            i8 = -1;
                            j3 = 0;
                        }
                    }
                    i4 = i6;
                } else {
                    if (read < 48 || read > 57) {
                        break loop0;
                    }
                    i5 = (i5 * 10) + (read - 48);
                    i3 = 0;
                }
            }
            if (i == i2) {
                if (l2 != null) {
                    list.add(new TarArchiveStructSparse(l2.longValue(), 0L));
                }
                return hashMap;
            }
            i3 = 0;
        }
    }

    protected static List<TarArchiveStructSparse> parsePAX01SparseHeaders(String str) {
        try {
            return parseFromPAX01SparseHeaders(str);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static List<TarArchiveStructSparse> parseFromPAX01SparseHeaders(String str) throws IOException {
        ArrayList arrayList = new ArrayList();
        String[] split = str.split(",");
        if (split.length % 2 == 1) {
            throw new IOException("Corrupted TAR archive. Bad format in GNU.sparse.map PAX Header");
        }
        for (int i = 0; i < split.length; i += 2) {
            try {
                long parseLong = Long.parseLong(split[i]);
                if (parseLong < 0) {
                    throw new IOException("Corrupted TAR archive. Sparse struct offset contains negative value");
                }
                try {
                    long parseLong2 = Long.parseLong(split[i + 1]);
                    if (parseLong2 < 0) {
                        throw new IOException("Corrupted TAR archive. Sparse struct numbytes contains negative value");
                    }
                    arrayList.add(new TarArchiveStructSparse(parseLong, parseLong2));
                } catch (NumberFormatException unused) {
                    throw new IOException("Corrupted TAR archive. Sparse struct numbytes contains a non-numeric value");
                }
            } catch (NumberFormatException unused2) {
                throw new IOException("Corrupted TAR archive. Sparse struct offset contains a non-numeric value");
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static List<TarArchiveStructSparse> parsePAX1XSparseHeaders(InputStream inputStream, int i) throws IOException {
        ArrayList arrayList = new ArrayList();
        long[] readLineOfNumberForPax1X = readLineOfNumberForPax1X(inputStream);
        long j = readLineOfNumberForPax1X[0];
        if (j < 0) {
            throw new IOException("Corrupted TAR archive. Negative value in sparse headers block");
        }
        long j2 = readLineOfNumberForPax1X[1] + 0;
        while (true) {
            long j3 = j - 1;
            if (j > 0) {
                long[] readLineOfNumberForPax1X2 = readLineOfNumberForPax1X(inputStream);
                long j4 = readLineOfNumberForPax1X2[0];
                if (j4 < 0) {
                    throw new IOException("Corrupted TAR archive. Sparse header block offset contains negative value");
                }
                long j5 = j2 + readLineOfNumberForPax1X2[1];
                long[] readLineOfNumberForPax1X3 = readLineOfNumberForPax1X(inputStream);
                long j6 = readLineOfNumberForPax1X3[0];
                if (j6 < 0) {
                    throw new IOException("Corrupted TAR archive. Sparse header block numbytes contains negative value");
                }
                j2 = j5 + readLineOfNumberForPax1X3[1];
                arrayList.add(new TarArchiveStructSparse(j4, j6));
                j = j3;
            } else {
                long j7 = i;
                IOUtils.skip(inputStream, j7 - (j2 % j7));
                return arrayList;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0028, code lost:
    
        throw new java.io.IOException("Corrupted TAR archive. Non-numeric value in sparse headers block");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static long[] readLineOfNumberForPax1X(InputStream inputStream) throws IOException {
        long j = 0;
        long j2 = 0;
        while (true) {
            int read = inputStream.read();
            if (read == 10) {
                return new long[]{j2, j + 1};
            }
            j++;
            if (read == -1) {
                throw new IOException("Unexpected EOF when reading parse information of 1.X PAX format");
            }
            if (read < 48 || read > 57) {
                break;
            }
            j2 = (j2 * 10) + (read - 48);
        }
    }
}
