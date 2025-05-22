package com.pudutech.bumblebee.business.movementInterface;

import com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import kotlin.Metadata;

/* compiled from: TempMoveInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementInterface/TempMoveInterface;", "Lcom/pudutech/bumblebee/business/movementInterface/BaseTaskInterface;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface TempMoveInterface extends BaseTaskInterface {

    /* compiled from: TempMoveInterface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static MoveActionInterface getMoveAction(TempMoveInterface tempMoveInterface) {
            return BaseTaskInterface.DefaultImpls.getMoveAction(tempMoveInterface);
        }

        public static void pauseTask(TempMoveInterface tempMoveInterface) {
            BaseTaskInterface.DefaultImpls.pauseTask(tempMoveInterface);
        }

        public static void resumeTask(TempMoveInterface tempMoveInterface) {
            BaseTaskInterface.DefaultImpls.resumeTask(tempMoveInterface);
        }

        public static void setActive(TempMoveInterface tempMoveInterface, boolean z, boolean z2) {
            BaseTaskInterface.DefaultImpls.setActive(tempMoveInterface, z, z2);
        }
    }
}
