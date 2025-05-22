package com.pudutech.factory_test.esp32;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.UInt;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.ShortCompanionObject;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: ESPCRC.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0012\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0011R\u0013\u0010\u0003\u001a\u00020\u0004X\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0005R\u0013\u0010\u0006\u001a\u00020\u0007X\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\bR#\u0010\n\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007@BX\u0086\u000eø\u0001\u0000¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000b\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/factory_test/esp32/ESPCRC;", "", "()V", "cpoly", "Lkotlin/UInt;", "I", "crcIn", "Lkotlin/UShort;", ExifInterface.LATITUDE_SOUTH, "<set-?>", ES6Iterator.VALUE_PROPERTY, "getValue", "()S", "update", "byte", "", "byteArray", "", "factorytest_3.11_2021-06-12_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ESPCRC {
    private short value;
    private short crcIn = UShort.m4761constructorimpl((short) 65535);
    private int cpoly = 4129;

    public final short getValue() {
        return this.value;
    }

    public final ESPCRC update(byte r3) {
        return update(new byte[]{r3});
    }

    public final ESPCRC update(byte[] byteArray) {
        Intrinsics.checkParameterIsNotNull(byteArray, "byteArray");
        for (byte b : byteArray) {
            this.crcIn = UShort.m4761constructorimpl((short) (UShort.m4761constructorimpl((short) UInt.m4595constructorimpl(UInt.m4595constructorimpl(b) << 8)) ^ this.crcIn));
            for (int i = 0; i < 8; i++) {
                if (UnsignedKt.uintCompare(UInt.m4595constructorimpl(UShort.m4761constructorimpl((short) (this.crcIn & ShortCompanionObject.MIN_VALUE)) & UShort.MAX_VALUE), 0) > 0) {
                    this.crcIn = UShort.m4761constructorimpl((short) UInt.m4595constructorimpl(UInt.m4595constructorimpl(UInt.m4595constructorimpl(this.crcIn & UShort.MAX_VALUE) << 1) ^ this.cpoly));
                } else {
                    this.crcIn = UShort.m4761constructorimpl((short) UInt.m4595constructorimpl(UInt.m4595constructorimpl(this.crcIn & UShort.MAX_VALUE) << 1));
                }
            }
        }
        this.value = this.crcIn;
        return this;
    }
}
