package com.pudutech.bumblebee.presenter.report_cloud.protocol.delivery;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: TaskType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\b\u0086\u0001\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0012B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0013"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/protocol/delivery/TaskType;", "", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;II)V", "getValue", "()I", "NotUsed", "Delivery", "GO_CHARGING", "GO_HOME", "DIRECT", "BIRTHDAY", "SPECIAL", "CALL_DIRECT", "GO_WELCOME_AREA", "SEAT_GUEST", "CUSTOM_CALL", "Companion", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public enum TaskType {
    NotUsed(0),
    Delivery(1),
    GO_CHARGING(2),
    GO_HOME(3),
    DIRECT(4),
    BIRTHDAY(5),
    SPECIAL(6),
    CALL_DIRECT(7),
    GO_WELCOME_AREA(8),
    SEAT_GUEST(9),
    CUSTOM_CALL(10);


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int value;

    TaskType(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }

    /* compiled from: TaskType.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/report_cloud/protocol/delivery/TaskType$Companion;", "", "()V", "byValue", "Lcom/pudutech/bumblebee/presenter/report_cloud/protocol/delivery/TaskType;", ES6Iterator.VALUE_PROPERTY, "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TaskType byValue(int value) {
            for (TaskType taskType : TaskType.values()) {
                if (taskType.getValue() == value) {
                    return taskType;
                }
            }
            return TaskType.NotUsed;
        }
    }
}
