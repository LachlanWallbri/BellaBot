package com.pudutech.bumblebee.business.ims.manager;

import com.pudutech.bumblebee.business.ims.config.RobotStatus;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotStatusManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/manager/RobotStatusManager;", "", "()V", "robotStatus", "Lcom/pudutech/bumblebee/business/ims/config/RobotStatus;", "getRobotStatus", "setRobotStatus", "", "status", "Companion", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotStatusManager {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final RobotStatusManager INSTANCE = Companion.SingletonHolder.INSTANCE.getHolder();
    public static final String TAG = "RobotStatusManager";
    private volatile RobotStatus robotStatus;

    private RobotStatusManager() {
        this.robotStatus = RobotStatus.Free;
    }

    public /* synthetic */ RobotStatusManager(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: RobotStatusManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/manager/RobotStatusManager$Companion;", "", "()V", "INSTANCE", "Lcom/pudutech/bumblebee/business/ims/manager/RobotStatusManager;", "getINSTANCE", "()Lcom/pudutech/bumblebee/business/ims/manager/RobotStatusManager;", "TAG", "", "SingletonHolder", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final RobotStatusManager getINSTANCE() {
            return RobotStatusManager.INSTANCE;
        }

        /* compiled from: RobotStatusManager.kt */
        @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/ims/manager/RobotStatusManager$Companion$SingletonHolder;", "", "()V", "holder", "Lcom/pudutech/bumblebee/business/ims/manager/RobotStatusManager;", "getHolder", "()Lcom/pudutech/bumblebee/business/ims/manager/RobotStatusManager;", "setHolder", "(Lcom/pudutech/bumblebee/business/ims/manager/RobotStatusManager;)V", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
        /* loaded from: classes4.dex */
        private static final class SingletonHolder {
            public static final SingletonHolder INSTANCE = new SingletonHolder();
            private static RobotStatusManager holder = new RobotStatusManager(null);

            private SingletonHolder() {
            }

            public final RobotStatusManager getHolder() {
                return holder;
            }

            public final void setHolder(RobotStatusManager robotStatusManager) {
                Intrinsics.checkParameterIsNotNull(robotStatusManager, "<set-?>");
                holder = robotStatusManager;
            }
        }
    }

    public final void setRobotStatus(RobotStatus status) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        this.robotStatus = status;
    }

    public final RobotStatus getRobotStatus() {
        return this.robotStatus;
    }
}
