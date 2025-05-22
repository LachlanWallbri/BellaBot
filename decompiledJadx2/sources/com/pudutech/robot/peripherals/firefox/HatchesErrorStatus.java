package com.pudutech.robot.peripherals.firefox;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: HatchesErrorStatus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0001\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/firefox/HatchesErrorStatus;", "", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;II)V", "getValue", "()I", "SwitchError", "NoTurning", "DriverChipError", "HandNip", "HardwardOverStream", "BreakWithHands", "NotUsed", "Companion", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum HatchesErrorStatus {
    SwitchError(0),
    NoTurning(1),
    DriverChipError(2),
    HandNip(3),
    HardwardOverStream(4),
    BreakWithHands(5),
    NotUsed(6);


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int value;

    HatchesErrorStatus(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }

    /* compiled from: HatchesErrorStatus.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/firefox/HatchesErrorStatus$Companion;", "", "()V", "byValue", "Lcom/pudutech/robot/peripherals/firefox/HatchesErrorStatus;", ES6Iterator.VALUE_PROPERTY, "", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final HatchesErrorStatus byValue(int value) {
            for (HatchesErrorStatus hatchesErrorStatus : HatchesErrorStatus.values()) {
                if (hatchesErrorStatus.getValue() == value) {
                    return hatchesErrorStatus;
                }
            }
            return HatchesErrorStatus.NotUsed;
        }
    }
}
