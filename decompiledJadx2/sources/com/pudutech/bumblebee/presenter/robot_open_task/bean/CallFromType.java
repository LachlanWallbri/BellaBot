package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: CallFromType.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CallFromType;", "", "type", "", "(Ljava/lang/String;II)V", "getType", "()I", "DEFAULT", "OPEN_API", "CLOUD", "LORA", "Companion", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public enum CallFromType {
    DEFAULT(0),
    OPEN_API(1),
    CLOUD(2),
    LORA(3);


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int type;

    CallFromType(int i) {
        this.type = i;
    }

    public final int getType() {
        return this.type;
    }

    /* compiled from: CallFromType.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CallFromType$Companion;", "", "()V", "fromType", "Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/CallFromType;", "type", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final CallFromType fromType(int type) {
            if (type == 1) {
                return CallFromType.OPEN_API;
            }
            if (type == 2) {
                return CallFromType.CLOUD;
            }
            if (type == 3) {
                return CallFromType.LORA;
            }
            return CallFromType.DEFAULT;
        }
    }
}
