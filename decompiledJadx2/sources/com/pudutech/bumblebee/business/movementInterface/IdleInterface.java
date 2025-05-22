package com.pudutech.bumblebee.business.movementInterface;

import com.pudutech.bumblebee.business.movementCallback.IdleCallback;
import com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import kotlin.Metadata;

/* compiled from: IdleInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementInterface/IdleInterface;", "Lcom/pudutech/bumblebee/business/movementInterface/BaseTaskInterface;", "setCallback", "", "callback", "Lcom/pudutech/bumblebee/business/movementCallback/IdleCallback;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface IdleInterface extends BaseTaskInterface {

    /* compiled from: IdleInterface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static MoveActionInterface getMoveAction(IdleInterface idleInterface) {
            return BaseTaskInterface.DefaultImpls.getMoveAction(idleInterface);
        }

        public static void pauseTask(IdleInterface idleInterface) {
            BaseTaskInterface.DefaultImpls.pauseTask(idleInterface);
        }

        public static void resumeTask(IdleInterface idleInterface) {
            BaseTaskInterface.DefaultImpls.resumeTask(idleInterface);
        }

        public static void setActive(IdleInterface idleInterface, boolean z, boolean z2) {
            BaseTaskInterface.DefaultImpls.setActive(idleInterface, z, z2);
        }
    }

    void setCallback(IdleCallback callback);
}
