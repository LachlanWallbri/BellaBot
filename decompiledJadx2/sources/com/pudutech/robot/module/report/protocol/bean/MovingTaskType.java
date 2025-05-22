package com.pudutech.robot.module.report.protocol.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: MovingTaskType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\b\u0086\u0001\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/bean/MovingTaskType;", "", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;II)V", "getValue", "()I", "NotUsed", "Delivery", "GO_CHARGING", "GO_HOME", "DIRECT", "BIRTHDAY", "SPECIAL", "CALL_DIRECT", "GO_WELCOME_AREA", "SEAT_GUEST", "Companion", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public enum MovingTaskType {
    NotUsed(0),
    Delivery(1),
    GO_CHARGING(2),
    GO_HOME(3),
    DIRECT(4),
    BIRTHDAY(5),
    SPECIAL(6),
    CALL_DIRECT(7),
    GO_WELCOME_AREA(8),
    SEAT_GUEST(9);


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int value;

    MovingTaskType(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* compiled from: MovingTaskType.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/robot/module/report/protocol/bean/MovingTaskType$Companion;", "", "()V", "byValue", "Lcom/pudutech/robot/module/report/protocol/bean/MovingTaskType;", ES6Iterator.VALUE_PROPERTY, "", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes7.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final MovingTaskType byValue(int value) {
            for (MovingTaskType movingTaskType : MovingTaskType.values()) {
                if (movingTaskType.getValue() == value) {
                    return movingTaskType;
                }
            }
            return MovingTaskType.NotUsed;
        }
    }
}
