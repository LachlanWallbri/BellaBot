package org.apache.commons.compress.archivers.sevenz;

import io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes;
import java.util.Arrays;

/* loaded from: classes8.dex */
public enum SevenZMethod {
    COPY(new byte[]{0}),
    LZMA(new byte[]{3, 1, 1}),
    LZMA2(new byte[]{BinaryMemcacheOpcodes.SASL_AUTH}),
    DEFLATE(new byte[]{4, 1, 8}),
    DEFLATE64(new byte[]{4, 1, 9}),
    BZIP2(new byte[]{4, 2, 2}),
    AES256SHA256(new byte[]{6, -15, 7, 1}),
    BCJ_X86_FILTER(new byte[]{3, 3, 1, 3}),
    BCJ_PPC_FILTER(new byte[]{3, 3, 2, 5}),
    BCJ_IA64_FILTER(new byte[]{3, 3, 4, 1}),
    BCJ_ARM_FILTER(new byte[]{3, 3, 5, 1}),
    BCJ_ARM_THUMB_FILTER(new byte[]{3, 3, 7, 1}),
    BCJ_SPARC_FILTER(new byte[]{3, 3, 8, 5}),
    DELTA_FILTER(new byte[]{3});


    /* renamed from: id */
    private final byte[] f8915id;

    SevenZMethod(byte[] bArr) {
        this.f8915id = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getId() {
        byte[] bArr = this.f8915id;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SevenZMethod byId(byte[] bArr) {
        for (SevenZMethod sevenZMethod : (SevenZMethod[]) SevenZMethod.class.getEnumConstants()) {
            if (Arrays.equals(sevenZMethod.f8915id, bArr)) {
                return sevenZMethod;
            }
        }
        return null;
    }
}
