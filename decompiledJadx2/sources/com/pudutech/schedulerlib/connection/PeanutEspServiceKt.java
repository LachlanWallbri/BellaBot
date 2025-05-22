package com.pudutech.schedulerlib.connection;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.UStringsKt;

/* compiled from: PeanutEspService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007¨\u0006\u0003"}, m3961d2 = {"toHexString", "", "", "schedulerlib_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class PeanutEspServiceKt {
    public static final String toHexString(byte[] toHexString) {
        Intrinsics.checkParameterIsNotNull(toHexString, "$this$toHexString");
        return CollectionsKt.joinToString$default(UByteArray.m4570boximpl(UByteArray.m4572constructorimpl(toHexString)), " ", null, null, 0, null, new Function1<UByte, String>() { // from class: com.pudutech.schedulerlib.connection.PeanutEspServiceKt$toHexString$1
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
