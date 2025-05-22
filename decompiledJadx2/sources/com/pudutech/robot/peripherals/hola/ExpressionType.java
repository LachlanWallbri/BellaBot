package com.pudutech.robot.peripherals.hola;

import kotlin.Metadata;
import kotlin.UByte;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: ExpressionType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/hola/ExpressionType;", "", ES6Iterator.VALUE_PROPERTY, "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getValue", "()B", "B", "EYES_CLOSE", "EYES_FAST", "EYES_LEFT_TO_RIGHT", "EYES_SMILE", "EYES_WAIT", "EYES_DOWN", "EYES_BLINK", "EYES_ERROR", "EYES_YELLOW", "EYES_ORANGE", "EYES_RED", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum ExpressionType {
    EYES_CLOSE(UByte.m4528constructorimpl((byte) 0)),
    EYES_FAST(UByte.m4528constructorimpl((byte) 1)),
    EYES_LEFT_TO_RIGHT(UByte.m4528constructorimpl((byte) 2)),
    EYES_SMILE(UByte.m4528constructorimpl((byte) 3)),
    EYES_WAIT(UByte.m4528constructorimpl((byte) 4)),
    EYES_DOWN(UByte.m4528constructorimpl((byte) 5)),
    EYES_BLINK(UByte.m4528constructorimpl((byte) 6)),
    EYES_ERROR(UByte.m4528constructorimpl((byte) 7)),
    EYES_YELLOW(UByte.m4528constructorimpl((byte) 8)),
    EYES_ORANGE(UByte.m4528constructorimpl((byte) 9)),
    EYES_RED(UByte.m4528constructorimpl((byte) 10));

    private final byte value;

    ExpressionType(byte b) {
        this.value = b;
    }

    public final byte getValue() {
        return this.value;
    }
}
