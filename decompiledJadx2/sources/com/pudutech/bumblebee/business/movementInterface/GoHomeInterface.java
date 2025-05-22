package com.pudutech.bumblebee.business.movementInterface;

import com.pudutech.bumblebee.business.movementCallback.GoHomeCallback;
import com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import kotlin.Metadata;

/* compiled from: GoHomeInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001a\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementInterface/GoHomeInterface;", "Lcom/pudutech/bumblebee/business/movementInterface/BaseTaskInterface;", "setCallback", "", "callback", "Lcom/pudutech/bumblebee/business/movementCallback/GoHomeCallback;", "setHome", "destination", "", "performance", "Lcom/pudutech/mirsdk/aidl/serialize/MoveTaskMode;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface GoHomeInterface extends BaseTaskInterface {
    void setCallback(GoHomeCallback callback);

    void setHome(String destination, MoveTaskMode performance);

    /* compiled from: GoHomeInterface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static MoveActionInterface getMoveAction(GoHomeInterface goHomeInterface) {
            return BaseTaskInterface.DefaultImpls.getMoveAction(goHomeInterface);
        }

        public static void pauseTask(GoHomeInterface goHomeInterface) {
            BaseTaskInterface.DefaultImpls.pauseTask(goHomeInterface);
        }

        public static void resumeTask(GoHomeInterface goHomeInterface) {
            BaseTaskInterface.DefaultImpls.resumeTask(goHomeInterface);
        }

        public static void setActive(GoHomeInterface goHomeInterface, boolean z, boolean z2) {
            BaseTaskInterface.DefaultImpls.setActive(goHomeInterface, z, z2);
        }

        public static /* synthetic */ void setHome$default(GoHomeInterface goHomeInterface, String str, MoveTaskMode moveTaskMode, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setHome");
            }
            if ((i & 2) != 0) {
                moveTaskMode = MoveTaskMode.Normal;
            }
            goHomeInterface.setHome(str, moveTaskMode);
        }
    }
}
