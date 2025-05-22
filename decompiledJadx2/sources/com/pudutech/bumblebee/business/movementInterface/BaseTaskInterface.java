package com.pudutech.bumblebee.business.movementInterface;

import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.business.movementInterface.TimeTaskContract;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BaseTaskInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0007H&J\u001a\u0010\f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u0007H\u0016R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementInterface/BaseTaskInterface;", "Lcom/pudutech/bumblebee/business/movementInterface/TimeTaskContract;", "moveAction", "Lcom/pudutech/mirsdk/aidl/MoveActionInterface;", "getMoveAction", "()Lcom/pudutech/mirsdk/aidl/MoveActionInterface;", "checkActive", "", "pause", "", "keepTime_ms", "", "setActive", "boolean", "isActive", "isFillIn", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface BaseTaskInterface extends TimeTaskContract {
    boolean checkActive();

    MoveActionInterface getMoveAction();

    void pause(long keepTime_ms);

    void setActive(boolean r1);

    void setActive(boolean isActive, boolean isFillIn);

    /* compiled from: BaseTaskInterface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static void pauseTask(BaseTaskInterface baseTaskInterface) {
            TimeTaskContract.DefaultImpls.pauseTask(baseTaskInterface);
        }

        public static void resumeTask(BaseTaskInterface baseTaskInterface) {
            TimeTaskContract.DefaultImpls.resumeTask(baseTaskInterface);
        }

        public static void setActive(BaseTaskInterface baseTaskInterface, boolean z, boolean z2) {
        }

        public static /* synthetic */ void setActive$default(BaseTaskInterface baseTaskInterface, boolean z, boolean z2, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setActive");
            }
            if ((i & 2) != 0) {
                z2 = false;
            }
            baseTaskInterface.setActive(z, z2);
        }

        public static MoveActionInterface getMoveAction(BaseTaskInterface baseTaskInterface) {
            MoveActionInterface moveActionInterface$module_bumblebee_business_robotRelease = Behavior.INSTANCE.getMoveActionInterface$module_bumblebee_business_robotRelease();
            if (moveActionInterface$module_bumblebee_business_robotRelease == null) {
                Intrinsics.throwNpe();
            }
            return moveActionInterface$module_bumblebee_business_robotRelease;
        }
    }
}
