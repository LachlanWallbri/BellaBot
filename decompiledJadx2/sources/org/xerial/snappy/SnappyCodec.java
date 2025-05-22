package org.xerial.snappy;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* loaded from: classes9.dex */
public class SnappyCodec {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int DEFAULT_VERSION = 1;
    public static final int HEADER_SIZE;
    static final byte[] MAGIC_HEADER = {-126, TarConstants.LF_GNUTYPE_SPARSE, 78, 65, 80, 80, ClassDefinitionUtils.OPS_dup, 0};
    public static final int MAGIC_HEADER_HEAD;
    public static final int MAGIC_LEN;
    public static final int MINIMUM_COMPATIBLE_VERSION = 1;
    public static final SnappyCodec currentHeader;
    public final int compatibleVersion;
    private final byte[] headerArray;
    public final byte[] magic;
    public final int version;

    static {
        byte[] bArr = MAGIC_HEADER;
        MAGIC_LEN = bArr.length;
        HEADER_SIZE = MAGIC_LEN + 8;
        MAGIC_HEADER_HEAD = SnappyOutputStream.readInt(bArr, 0);
        currentHeader = new SnappyCodec(MAGIC_HEADER, 1, 1);
    }

    private SnappyCodec(byte[] bArr, int i, int i2) {
        this.magic = bArr;
        this.version = i;
        this.compatibleVersion = i2;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(HEADER_SIZE);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.write(bArr, 0, MAGIC_LEN);
            dataOutputStream.writeInt(i);
            dataOutputStream.writeInt(i2);
            dataOutputStream.close();
            this.headerArray = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] getMagicHeader() {
        return (byte[]) MAGIC_HEADER.clone();
    }

    public String toString() {
        return String.format("version:%d, compatible version:%d", Integer.valueOf(this.version), Integer.valueOf(this.compatibleVersion));
    }

    public static int headerSize() {
        return HEADER_SIZE;
    }

    public int writeHeader(byte[] bArr, int i) {
        byte[] bArr2 = this.headerArray;
        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
        return this.headerArray.length;
    }

    public int writeHeader(OutputStream outputStream) throws IOException {
        byte[] bArr = this.headerArray;
        outputStream.write(bArr, 0, bArr.length);
        return this.headerArray.length;
    }

    public boolean isValidMagicHeader() {
        return Arrays.equals(MAGIC_HEADER, this.magic);
    }

    public static boolean hasMagicHeaderPrefix(byte[] bArr) {
        int min = Math.min(MAGIC_LEN, bArr.length);
        for (int i = 0; i < min; i++) {
            if (bArr[i] != MAGIC_HEADER[i]) {
                return false;
            }
        }
        return true;
    }

    public static SnappyCodec readHeader(InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        int i = MAGIC_LEN;
        byte[] bArr = new byte[i];
        dataInputStream.readFully(bArr, 0, i);
        return new SnappyCodec(bArr, dataInputStream.readInt(), dataInputStream.readInt());
    }
}
