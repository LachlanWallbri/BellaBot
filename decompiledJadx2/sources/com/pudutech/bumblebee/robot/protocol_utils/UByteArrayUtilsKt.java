package com.pudutech.bumblebee.robot.protocol_utils;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.unsigned.UArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlin.text.UStringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: UByteArrayUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007*\u00020\u00032\u0006\u0010\b\u001a\u00020\tø\u0001\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a\u0016\u0010\f\u001a\u00020\r*\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0012\u0010\u0010\u001a\u00020\u0003*\u00020\tø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a\u0014\u0010\u0012\u001a\u00020\u0013*\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, m3961d2 = {"canXORChecksum", "Lkotlin/UByte;", "byteArray", "Lkotlin/UByteArray;", "canXORChecksum-GBYM_sE", "([B)B", "split", "", "childSize", "", "split-PpDY95g", "([BI)Ljava/util/List;", "toHexString", "", "toHexString-GBYM_sE", "([B)Ljava/lang/String;", "toUByteArray", "(I)[B", "toUInt", "Lkotlin/UInt;", "toUInt-GBYM_sE", "([B)I", "Robot_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class UByteArrayUtilsKt {
    /* renamed from: toHexString-GBYM_sE, reason: not valid java name */
    public static final String m4334toHexStringGBYM_sE(byte[] toHexString) {
        Intrinsics.checkParameterIsNotNull(toHexString, "$this$toHexString");
        return CollectionsKt.joinToString$default(UByteArray.m4570boximpl(toHexString), " ", null, null, 0, null, new Function1<UByte, String>() { // from class: com.pudutech.bumblebee.robot.protocol_utils.UByteArrayUtilsKt$toHexString$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ String invoke(UByte uByte) {
                return invoke(uByte.getData());
            }

            public final String invoke(byte b) {
                return StringsKt.padStart(UStringsKt.m5471toStringLxnNnR4(b, 16), 2, '0');
            }
        }, 30, null);
    }

    /* renamed from: toUInt-GBYM_sE, reason: not valid java name */
    public static final int m4335toUIntGBYM_sE(byte[] toUInt) {
        Intrinsics.checkParameterIsNotNull(toUInt, "$this$toUInt");
        return UInt.m4595constructorimpl(UInt.m4595constructorimpl(UByteArray.m4577getimpl(toUInt, 3) & 255) | UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(UByteArray.m4577getimpl(toUInt, 0) & 255) << 24) | UInt.m4595constructorimpl(UInt.m4595constructorimpl(UByteArray.m4577getimpl(toUInt, 1) & 255) << 16)) | UInt.m4595constructorimpl(UInt.m4595constructorimpl(UByteArray.m4577getimpl(toUInt, 2) & 255) << 8)));
    }

    public static final byte[] toUByteArray(int i) {
        return new byte[]{UByte.m4528constructorimpl((byte) (i >> 24)), UByte.m4528constructorimpl((byte) (i >> 16)), UByte.m4528constructorimpl((byte) (i >> 8)), UByte.m4528constructorimpl((byte) i)};
    }

    /* renamed from: split-PpDY95g, reason: not valid java name */
    public static final List<UByteArray> m4333splitPpDY95g(byte[] split, int i) {
        Intrinsics.checkParameterIsNotNull(split, "$this$split");
        if (i <= 0) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (true) {
            if (i2 < UByteArray.m4578getSizeimpl(split)) {
                int i3 = i2 + i;
                if (i3 >= UByteArray.m4578getSizeimpl(split)) {
                    arrayList.add(UByteArray.m4570boximpl(UArraysKt.m5292sliceArrayc0bezYM(split, new IntRange(i2, ArraysKt.getLastIndex(split)))));
                    break;
                }
                arrayList.add(UByteArray.m4570boximpl(UArraysKt.m5292sliceArrayc0bezYM(split, RangesKt.until(i2, i3))));
                i2 = i3;
            } else {
                break;
            }
        }
        return arrayList;
    }

    /* renamed from: canXORChecksum-GBYM_sE, reason: not valid java name */
    public static final byte m4332canXORChecksumGBYM_sE(byte[] byteArray) {
        Intrinsics.checkParameterIsNotNull(byteArray, "byteArray");
        int i = 0;
        for (int i2 = 0; i2 <= 6; i2++) {
            i ^= UByteArray.m4577getimpl(byteArray, i2) & 255;
        }
        return UByte.m4528constructorimpl((byte) i);
    }
}
