package com.pudutech.robot.peripherals.hola;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: TouchPlace.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0086\u0001\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u0012\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0004R\u0016\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/hola/TouchPlace;", "", ES6Iterator.VALUE_PROPERTY, "Lkotlin/UByte;", "(Ljava/lang/String;IB)V", "getValue", "()B", "B", "LeftEar", "RightEar", "FunctionButton", "Head", "TouchKey", "NonTouchKey", "Companion", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum TouchPlace {
    LeftEar((byte) 1),
    RightEar((byte) 2),
    FunctionButton((byte) 3),
    Head((byte) 4),
    TouchKey((byte) 5),
    NonTouchKey((byte) 6);


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final byte value;

    TouchPlace(byte b) {
        this.value = b;
    }

    public final byte getValue() {
        return this.value;
    }

    /* compiled from: TouchPlace.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/hola/TouchPlace$Companion;", "", "()V", "valueOf", "Lcom/pudutech/robot/peripherals/hola/TouchPlace;", ES6Iterator.VALUE_PROPERTY, "Lkotlin/UByte;", "valueOf-7apg3OU", "(B)Lcom/pudutech/robot/peripherals/hola/TouchPlace;", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* renamed from: valueOf-7apg3OU, reason: not valid java name */
        public final TouchPlace m4501valueOf7apg3OU(byte value) {
            for (TouchPlace touchPlace : TouchPlace.values()) {
                if (value == touchPlace.getValue()) {
                    return touchPlace;
                }
            }
            return TouchPlace.Head;
        }
    }
}
