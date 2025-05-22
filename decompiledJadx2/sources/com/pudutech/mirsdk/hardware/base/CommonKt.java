package com.pudutech.mirsdk.hardware.base;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.UStringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: Common.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0002\b\u0003\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\f\u0010\u0006\u001a\u00020\u0007*\u00020\bH\u0007\u001a\u0016\u0010\u0006\u001a\u00020\u0007*\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, m3961d2 = {"canChecksum", "Lkotlin/UByte;", "byteArray", "Lkotlin/UByteArray;", "canChecksum-GBYM_sE", "([B)B", "toHexString", "", "", "toHexString-GBYM_sE", "([B)Ljava/lang/String;", "mirhardware_packRelease"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class CommonKt {
    /* renamed from: canChecksum-GBYM_sE, reason: not valid java name */
    public static final byte m4420canChecksumGBYM_sE(byte[] byteArray) {
        int i;
        Intrinsics.checkParameterIsNotNull(byteArray, "byteArray");
        int i2 = 0;
        if (UByteArray.m4578getSizeimpl(byteArray) < 7) {
            return (byte) 0;
        }
        if (UByteArray.m4577getimpl(byteArray, 0) == UByte.m4528constructorimpl((byte) 22)) {
            i = 0;
            while (i2 <= 6) {
                i += UByteArray.m4577getimpl(byteArray, i2) & 255;
                i2++;
            }
        } else {
            i = 0;
            while (i2 <= 6) {
                i ^= UByteArray.m4577getimpl(byteArray, i2) & 255;
                i2++;
            }
        }
        return UByte.m4528constructorimpl((byte) i);
    }

    public static final String toHexString(byte[] toHexString) {
        Intrinsics.checkParameterIsNotNull(toHexString, "$this$toHexString");
        return CollectionsKt.joinToString$default(UByteArray.m4570boximpl(UByteArray.m4572constructorimpl(toHexString)), " ", null, null, 0, null, new Function1<UByte, String>() { // from class: com.pudutech.mirsdk.hardware.base.CommonKt$toHexString$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ String invoke(UByte uByte) {
                return invoke(uByte.getData());
            }

            public final String invoke(byte b) {
                return StringsKt.padStart(UStringsKt.m5471toStringLxnNnR4(b, 16), 2, '0');
            }
        }, 30, null);
    }

    /* renamed from: toHexString-GBYM_sE, reason: not valid java name */
    public static final String m4421toHexStringGBYM_sE(byte[] toHexString) {
        Intrinsics.checkParameterIsNotNull(toHexString, "$this$toHexString");
        return CollectionsKt.joinToString$default(UByteArray.m4570boximpl(toHexString), " ", null, null, 0, null, new Function1<UByte, String>() { // from class: com.pudutech.mirsdk.hardware.base.CommonKt$toHexString$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ String invoke(UByte uByte) {
                return invoke(uByte.getData());
            }

            public final String invoke(byte b) {
                return StringsKt.padStart(UStringsKt.m5471toStringLxnNnR4(b, 16), 2, '0');
            }
        }, 30, null);
    }
}
