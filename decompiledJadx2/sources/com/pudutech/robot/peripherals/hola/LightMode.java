package com.pudutech.robot.peripherals.hola;

import kotlin.Metadata;
import kotlin.UByte;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: ExpressionType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/hola/LightMode;", "", ES6Iterator.VALUE_PROPERTY, "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getValue", "()B", "B", "LIGHT_CLOSE", "LIGHT_ALWAYS_BRIGHT", "LIGHT_FLASHING_CLOSE", "LIGHT_BREATHE", "LIGHT_FLASHING_BRIGHT", "LIGHT_RGB", "LIGHT_BOOT", "LIGHT_TURN_LEFT", "LIGHT_TURN_RIGHT", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum LightMode {
    LIGHT_CLOSE(UByte.m4528constructorimpl((byte) 0)),
    LIGHT_ALWAYS_BRIGHT(UByte.m4528constructorimpl((byte) 1)),
    LIGHT_FLASHING_CLOSE(UByte.m4528constructorimpl((byte) 2)),
    LIGHT_BREATHE(UByte.m4528constructorimpl((byte) 3)),
    LIGHT_FLASHING_BRIGHT(UByte.m4528constructorimpl((byte) 4)),
    LIGHT_RGB(UByte.m4528constructorimpl((byte) 5)),
    LIGHT_BOOT(UByte.m4528constructorimpl((byte) 6)),
    LIGHT_TURN_LEFT(UByte.m4528constructorimpl((byte) 7)),
    LIGHT_TURN_RIGHT(UByte.m4528constructorimpl((byte) 8));

    private final byte value;

    LightMode(byte b) {
        this.value = b;
    }

    public final byte getValue() {
        return this.value;
    }
}
