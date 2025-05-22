package com.pudutech.robot.peripherals.config;

import kotlin.Metadata;
import kotlin.UByte;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: LightBeltType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0087\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/config/LightBeltType;", "", ES6Iterator.VALUE_PROPERTY, "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getValue", "()B", "B", "toString", "", "Pallet_1_Left", "Pallet_1_Right", "Pallet_2_Left", "Pallet_2_Right", "Pallet_3_Left", "Pallet_3_Right", "Pallet_4_Left", "Pallet_4_Right", "LeftEar", "RightEar", "FunctionButton", "BottomChassis", "BottomChassisLeft", "BottomChassisRight", "BottomChassisRear", "DisinfectionSprayHead", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum LightBeltType {
    Pallet_1_Left(UByte.m4528constructorimpl((byte) 1)),
    Pallet_1_Right(UByte.m4528constructorimpl((byte) 2)),
    Pallet_2_Left(UByte.m4528constructorimpl((byte) 3)),
    Pallet_2_Right(UByte.m4528constructorimpl((byte) 4)),
    Pallet_3_Left(UByte.m4528constructorimpl((byte) 5)),
    Pallet_3_Right(UByte.m4528constructorimpl((byte) 6)),
    Pallet_4_Left(UByte.m4528constructorimpl((byte) 7)),
    Pallet_4_Right(UByte.m4528constructorimpl((byte) 8)),
    LeftEar(UByte.m4528constructorimpl((byte) 9)),
    RightEar(UByte.m4528constructorimpl((byte) 10)),
    FunctionButton(UByte.m4528constructorimpl((byte) 11)),
    BottomChassis(UByte.m4528constructorimpl((byte) 12)),
    BottomChassisLeft(UByte.m4528constructorimpl((byte) 13)),
    BottomChassisRight(UByte.m4528constructorimpl((byte) 14)),
    BottomChassisRear(UByte.m4528constructorimpl((byte) 15)),
    DisinfectionSprayHead(UByte.m4528constructorimpl((byte) 21));

    private final byte value;

    LightBeltType(byte b) {
        this.value = b;
    }

    public final byte getValue() {
        return this.value;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "LightBeltType(value=" + UByte.m4563toStringimpl(this.value) + ')';
    }
}
