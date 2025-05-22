package com.pudutech.bumblebee.robot;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: CANParser.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006"}, m3961d2 = {"canChecksum", "Lkotlin/UByte;", "byteArray", "Lkotlin/UByteArray;", "canChecksum-GBYM_sE", "([B)B", "Robot_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CANParserKt {
    /* renamed from: canChecksum-GBYM_sE, reason: not valid java name */
    public static final byte m4295canChecksumGBYM_sE(byte[] byteArray) {
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
}
