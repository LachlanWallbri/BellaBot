package com.pudutech.lidar.util;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ByteArrayWrapper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0006J\u000e\u0010\u0010\u001a\u00020\u0011ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u0006\u0010\u0013\u001a\u00020\u0006J\u000e\u0010\u0014\u001a\u00020\u0015ø\u0001\u0000¢\u0006\u0002\u0010\u0016J\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u0011ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u0010\u0010\u001a\u001a\u00020\u001bH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\bJ\u0010\u0010\u001c\u001a\u00020\u001bH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\bJ\u000e\u0010\u001d\u001a\u00020\u001eø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\u001eH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u000e\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u0004J\u0006\u0010#\u001a\u00020\u000eR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, m3961d2 = {"Lcom/pudutech/lidar/util/ByteArrayWrapper;", "", "()V", "data", "", "index", "", "getIndex", "()I", "setIndex", "(I)V", "isLow4Bit", "", "indexPlus", "", "count", "pop4Bit", "Lkotlin/UByte;", "()B", "pop4Byte", "pop8Byte", "Lkotlin/ULong;", "()J", "popByte", "", "popUByte", "popUInt", "Lkotlin/UInt;", "popUIntLowByteFirst", "popUShort", "Lkotlin/UShort;", "()S", "popUShortLowByteFirst", "prepare", "src", "reset", "LidarLib_mirRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class ByteArrayWrapper {
    private byte[] data;
    private int index;
    private boolean isLow4Bit;

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    public final void prepare(byte[] src) {
        Intrinsics.checkParameterIsNotNull(src, "src");
        this.index = 0;
        this.data = src;
    }

    public final void indexPlus(int count) {
        this.index += count;
    }

    public final byte popUByte() {
        byte[] bArr = this.data;
        if (bArr == null) {
            Intrinsics.throwNpe();
        }
        byte m4528constructorimpl = UByte.m4528constructorimpl(bArr[this.index]);
        this.index++;
        return m4528constructorimpl;
    }

    public final byte popByte() {
        byte[] bArr = this.data;
        if (bArr == null) {
            Intrinsics.throwNpe();
        }
        int i = this.index;
        byte b = bArr[i];
        this.index = i + 1;
        return b;
    }

    public final short popUShort() {
        byte[] bArr = this.data;
        if (bArr == null) {
            Intrinsics.throwNpe();
        }
        int m4528constructorimpl = (UByte.m4528constructorimpl(bArr[this.index]) & 255) << 8;
        byte[] bArr2 = this.data;
        if (bArr2 == null) {
            Intrinsics.throwNpe();
        }
        short m4761constructorimpl = UShort.m4761constructorimpl((short) ((m4528constructorimpl | (UByte.m4528constructorimpl(bArr2[this.index + 1]) & 255)) & 65535));
        this.index += 2;
        return m4761constructorimpl;
    }

    public final short popUShortLowByteFirst() {
        byte[] bArr = this.data;
        if (bArr == null) {
            Intrinsics.throwNpe();
        }
        int m4528constructorimpl = (UByte.m4528constructorimpl(bArr[this.index + 1]) & 255) << 8;
        byte[] bArr2 = this.data;
        if (bArr2 == null) {
            Intrinsics.throwNpe();
        }
        short m4761constructorimpl = UShort.m4761constructorimpl((short) ((m4528constructorimpl | (UByte.m4528constructorimpl(bArr2[this.index]) & 255)) & 65535));
        this.index += 2;
        return m4761constructorimpl;
    }

    public final int popUInt() {
        byte[] bArr = this.data;
        if (bArr == null) {
            Intrinsics.throwNpe();
        }
        int m4528constructorimpl = (UByte.m4528constructorimpl(bArr[this.index]) & 255) << 24;
        byte[] bArr2 = this.data;
        if (bArr2 == null) {
            Intrinsics.throwNpe();
        }
        int m4528constructorimpl2 = m4528constructorimpl | ((UByte.m4528constructorimpl(bArr2[this.index + 1]) & 255) << 16);
        byte[] bArr3 = this.data;
        if (bArr3 == null) {
            Intrinsics.throwNpe();
        }
        int m4528constructorimpl3 = m4528constructorimpl2 | ((UByte.m4528constructorimpl(bArr3[this.index + 2]) & 255) << 8);
        byte[] bArr4 = this.data;
        if (bArr4 == null) {
            Intrinsics.throwNpe();
        }
        int m4595constructorimpl = UInt.m4595constructorimpl((m4528constructorimpl3 | (UByte.m4528constructorimpl(bArr4[this.index + 3]) & 255)) & ((int) 4294967295L));
        this.index += 4;
        return m4595constructorimpl;
    }

    public final int popUIntLowByteFirst() {
        byte[] bArr = this.data;
        if (bArr == null) {
            Intrinsics.throwNpe();
        }
        int m4528constructorimpl = (UByte.m4528constructorimpl(bArr[this.index + 3]) & 255) << 24;
        byte[] bArr2 = this.data;
        if (bArr2 == null) {
            Intrinsics.throwNpe();
        }
        int m4528constructorimpl2 = m4528constructorimpl | ((UByte.m4528constructorimpl(bArr2[this.index + 2]) & 255) << 16);
        byte[] bArr3 = this.data;
        if (bArr3 == null) {
            Intrinsics.throwNpe();
        }
        int m4528constructorimpl3 = m4528constructorimpl2 | ((UByte.m4528constructorimpl(bArr3[this.index + 1]) & 255) << 8);
        byte[] bArr4 = this.data;
        if (bArr4 == null) {
            Intrinsics.throwNpe();
        }
        int m4595constructorimpl = UInt.m4595constructorimpl((m4528constructorimpl3 | (UByte.m4528constructorimpl(bArr4[this.index]) & 255)) & ((int) 4294967295L));
        this.index += 4;
        return m4595constructorimpl;
    }

    public final int pop4Byte() {
        byte[] bArr = this.data;
        if (bArr == null) {
            Intrinsics.throwNpe();
        }
        int i = bArr[this.index] << 24;
        byte[] bArr2 = this.data;
        if (bArr2 == null) {
            Intrinsics.throwNpe();
        }
        int i2 = i | (bArr2[this.index + 1] << 16);
        byte[] bArr3 = this.data;
        if (bArr3 == null) {
            Intrinsics.throwNpe();
        }
        int i3 = i2 | (bArr3[this.index + 2] << 8);
        byte[] bArr4 = this.data;
        if (bArr4 == null) {
            Intrinsics.throwNpe();
        }
        int i4 = this.index;
        int i5 = (i3 | bArr4[i4 + 3]) & ((int) 4294967295L);
        this.index = i4 + 4;
        return i5;
    }

    public final long pop8Byte() {
        if (this.data == null) {
            Intrinsics.throwNpe();
        }
        long j = (r0[this.index + 7] & 255) << 56;
        if (this.data == null) {
            Intrinsics.throwNpe();
        }
        long j2 = j | ((r4[this.index + 6] & 255) << 48);
        if (this.data == null) {
            Intrinsics.throwNpe();
        }
        long j3 = j2 | ((r4[this.index + 5] & 255) << 40);
        if (this.data == null) {
            Intrinsics.throwNpe();
        }
        long j4 = j3 | ((r4[this.index + 4] & 255) << 32);
        if (this.data == null) {
            Intrinsics.throwNpe();
        }
        long j5 = j4 | ((r4[this.index + 3] & 255) << 24);
        if (this.data == null) {
            Intrinsics.throwNpe();
        }
        long j6 = j5 | ((r4[this.index + 2] & 255) << 16);
        if (this.data == null) {
            Intrinsics.throwNpe();
        }
        long j7 = j6 | ((r4[this.index + 1] & 255) << 8);
        if (this.data == null) {
            Intrinsics.throwNpe();
        }
        long j8 = j7 | (255 & r4[r5]);
        this.index = this.index + 8;
        return ULong.m4664constructorimpl(j8);
    }

    public final byte pop4Bit() {
        byte m4528constructorimpl;
        if (this.isLow4Bit) {
            byte[] bArr = this.data;
            if (bArr == null) {
                Intrinsics.throwNpe();
            }
            m4528constructorimpl = UByte.m4528constructorimpl((byte) (bArr[this.index] & 15));
            this.index++;
        } else {
            byte[] bArr2 = this.data;
            if (bArr2 == null) {
                Intrinsics.throwNpe();
            }
            m4528constructorimpl = UByte.m4528constructorimpl((byte) ((bArr2[this.index] >>> 4) & 15));
        }
        this.isLow4Bit = !this.isLow4Bit;
        return m4528constructorimpl;
    }

    public final void reset() {
        this.data = (byte[]) null;
        this.index = 0;
    }
}
