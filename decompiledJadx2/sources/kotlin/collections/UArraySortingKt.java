package kotlin.collections;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes7.dex
  classes8.dex
 */
/* compiled from: UArraySorting.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0012\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u0003H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\bH\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000bH\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, m3961d2 = {"partition", "", TmpConstant.TYPE_VALUE_ARRAY, "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "sortArray", "sortArray-GBYM_sE", "([B)V", "sortArray--ajY-9A", "([I)V", "sortArray-QwZRm1k", "([J)V", "sortArray-rL5Bavg", "([S)V", "kotlin-stdlib"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class UArraySortingKt {
    /* renamed from: partition-4UcCI2c, reason: not valid java name */
    private static final int m4830partition4UcCI2c(byte[] bArr, int i, int i2) {
        int i3;
        byte m4577getimpl = UByteArray.m4577getimpl(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                i3 = m4577getimpl & 255;
                if (Intrinsics.compare(UByteArray.m4577getimpl(bArr, i) & 255, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UByteArray.m4577getimpl(bArr, i2) & 255, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte m4577getimpl2 = UByteArray.m4577getimpl(bArr, i);
                UByteArray.m4582setVurrAj0(bArr, i, UByteArray.m4577getimpl(bArr, i2));
                UByteArray.m4582setVurrAj0(bArr, i2, m4577getimpl2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-4UcCI2c, reason: not valid java name */
    private static final void m4834quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int m4830partition4UcCI2c = m4830partition4UcCI2c(bArr, i, i2);
        int i3 = m4830partition4UcCI2c - 1;
        if (i < i3) {
            m4834quickSort4UcCI2c(bArr, i, i3);
        }
        if (m4830partition4UcCI2c < i2) {
            m4834quickSort4UcCI2c(bArr, m4830partition4UcCI2c, i2);
        }
    }

    /* renamed from: partition-Aa5vz7o, reason: not valid java name */
    private static final int m4831partitionAa5vz7o(short[] sArr, int i, int i2) {
        int i3;
        short m4810getimpl = UShortArray.m4810getimpl(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int m4810getimpl2 = UShortArray.m4810getimpl(sArr, i) & UShort.MAX_VALUE;
                i3 = m4810getimpl & UShort.MAX_VALUE;
                if (Intrinsics.compare(m4810getimpl2, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UShortArray.m4810getimpl(sArr, i2) & UShort.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short m4810getimpl3 = UShortArray.m4810getimpl(sArr, i);
                UShortArray.m4815set01HTLdE(sArr, i, UShortArray.m4810getimpl(sArr, i2));
                UShortArray.m4815set01HTLdE(sArr, i2, m4810getimpl3);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-Aa5vz7o, reason: not valid java name */
    private static final void m4835quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int m4831partitionAa5vz7o = m4831partitionAa5vz7o(sArr, i, i2);
        int i3 = m4831partitionAa5vz7o - 1;
        if (i < i3) {
            m4835quickSortAa5vz7o(sArr, i, i3);
        }
        if (m4831partitionAa5vz7o < i2) {
            m4835quickSortAa5vz7o(sArr, m4831partitionAa5vz7o, i2);
        }
    }

    /* renamed from: partition-oBK06Vg, reason: not valid java name */
    private static final int m4832partitionoBK06Vg(int[] iArr, int i, int i2) {
        int m4646getimpl = UIntArray.m4646getimpl(iArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedKt.uintCompare(UIntArray.m4646getimpl(iArr, i), m4646getimpl) < 0) {
                i++;
            }
            while (UnsignedKt.uintCompare(UIntArray.m4646getimpl(iArr, i2), m4646getimpl) > 0) {
                i2--;
            }
            if (i <= i2) {
                int m4646getimpl2 = UIntArray.m4646getimpl(iArr, i);
                UIntArray.m4651setVXSXFK8(iArr, i, UIntArray.m4646getimpl(iArr, i2));
                UIntArray.m4651setVXSXFK8(iArr, i2, m4646getimpl2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort-oBK06Vg, reason: not valid java name */
    private static final void m4836quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int m4832partitionoBK06Vg = m4832partitionoBK06Vg(iArr, i, i2);
        int i3 = m4832partitionoBK06Vg - 1;
        if (i < i3) {
            m4836quickSortoBK06Vg(iArr, i, i3);
        }
        if (m4832partitionoBK06Vg < i2) {
            m4836quickSortoBK06Vg(iArr, m4832partitionoBK06Vg, i2);
        }
    }

    /* renamed from: partition--nroSd4, reason: not valid java name */
    private static final int m4829partitionnroSd4(long[] jArr, int i, int i2) {
        long m4715getimpl = ULongArray.m4715getimpl(jArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedKt.ulongCompare(ULongArray.m4715getimpl(jArr, i), m4715getimpl) < 0) {
                i++;
            }
            while (UnsignedKt.ulongCompare(ULongArray.m4715getimpl(jArr, i2), m4715getimpl) > 0) {
                i2--;
            }
            if (i <= i2) {
                long m4715getimpl2 = ULongArray.m4715getimpl(jArr, i);
                ULongArray.m4720setk8EXiF4(jArr, i, ULongArray.m4715getimpl(jArr, i2));
                ULongArray.m4720setk8EXiF4(jArr, i2, m4715getimpl2);
                i++;
                i2--;
            }
        }
        return i;
    }

    /* renamed from: quickSort--nroSd4, reason: not valid java name */
    private static final void m4833quickSortnroSd4(long[] jArr, int i, int i2) {
        int m4829partitionnroSd4 = m4829partitionnroSd4(jArr, i, i2);
        int i3 = m4829partitionnroSd4 - 1;
        if (i < i3) {
            m4833quickSortnroSd4(jArr, i, i3);
        }
        if (m4829partitionnroSd4 < i2) {
            m4833quickSortnroSd4(jArr, m4829partitionnroSd4, i2);
        }
    }

    /* renamed from: sortArray-GBYM_sE, reason: not valid java name */
    public static final void m4838sortArrayGBYM_sE(byte[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        m4834quickSort4UcCI2c(array, 0, UByteArray.m4578getSizeimpl(array) - 1);
    }

    /* renamed from: sortArray-rL5Bavg, reason: not valid java name */
    public static final void m4840sortArrayrL5Bavg(short[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        m4835quickSortAa5vz7o(array, 0, UShortArray.m4811getSizeimpl(array) - 1);
    }

    /* renamed from: sortArray--ajY-9A, reason: not valid java name */
    public static final void m4837sortArrayajY9A(int[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        m4836quickSortoBK06Vg(array, 0, UIntArray.m4647getSizeimpl(array) - 1);
    }

    /* renamed from: sortArray-QwZRm1k, reason: not valid java name */
    public static final void m4839sortArrayQwZRm1k(long[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        m4833quickSortnroSd4(array, 0, ULongArray.m4716getSizeimpl(array) - 1);
    }
}
