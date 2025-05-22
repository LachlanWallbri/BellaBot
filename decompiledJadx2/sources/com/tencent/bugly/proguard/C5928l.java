package com.tencent.bugly.proguard;

import java.nio.ByteBuffer;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.l */
/* loaded from: classes7.dex */
public final class C5928l {
    /* renamed from: a */
    public static boolean m3713a(int i, int i2) {
        return i == i2;
    }

    /* renamed from: a */
    public static boolean m3714a(long j, long j2) {
        return j == j2;
    }

    /* renamed from: a */
    public static boolean m3716a(boolean z, boolean z2) {
        return z == z2;
    }

    /* renamed from: a */
    public static boolean m3715a(Object obj, Object obj2) {
        return obj.equals(obj2);
    }

    /* renamed from: a */
    public static byte[] m3717a(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[byteBuffer.position()];
        System.arraycopy(byteBuffer.array(), 0, bArr, 0, bArr.length);
        return bArr;
    }

    static {
        byte[] bArr = {TarConstants.LF_NORMAL, TarConstants.LF_LINK, TarConstants.LF_SYMLINK, TarConstants.LF_CHR, TarConstants.LF_BLK, TarConstants.LF_DIR, TarConstants.LF_FIFO, TarConstants.LF_CONTIG, 56, 57, 65, 66, 67, 68, 69, 70};
        byte[] bArr2 = new byte[256];
        byte[] bArr3 = new byte[256];
        for (int i = 0; i < 256; i++) {
            bArr2[i] = bArr[i >>> 4];
            bArr3[i] = bArr[i & 15];
        }
    }
}
