package com.pudutech.bumblebee.business.movementInterface;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.bumblebee.business.movementCallback.DeliverCallback;
import com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import java.util.List;
import kotlin.Metadata;

/* compiled from: RecycleInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH&J*\u0010\u000b\u001a\u00020\u00032\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H&¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementInterface/RecycleInterface;", "Lcom/pudutech/bumblebee/business/movementInterface/BaseTaskInterface;", TmpConstant.PROPERTY_IDENTIFIER_SET, "", "destination", "", "status", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "setCallback", "deliverCallback", "Lcom/pudutech/bumblebee/business/movementCallback/DeliverCallback;", "setDestinations", "destinations", "", "sortType", "Lcom/pudutech/bumblebee/business/movementInterface/SortType;", "performance", "Lcom/pudutech/mirsdk/aidl/serialize/MoveTaskMode;", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface RecycleInterface extends BaseTaskInterface {
    void set(String destination, TaskStatus status);

    void setCallback(DeliverCallback deliverCallback);

    void setDestinations(List<String> destinations, SortType sortType, MoveTaskMode performance);

    /* compiled from: RecycleInterface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static MoveActionInterface getMoveAction(RecycleInterface recycleInterface) {
            return BaseTaskInterface.DefaultImpls.getMoveAction(recycleInterface);
        }

        public static void pauseTask(RecycleInterface recycleInterface) {
            BaseTaskInterface.DefaultImpls.pauseTask(recycleInterface);
        }

        public static void resumeTask(RecycleInterface recycleInterface) {
            BaseTaskInterface.DefaultImpls.resumeTask(recycleInterface);
        }

        public static void setActive(RecycleInterface recycleInterface, boolean z, boolean z2) {
            BaseTaskInterface.DefaultImpls.setActive(recycleInterface, z, z2);
        }

        public static /* synthetic */ void setDestinations$default(RecycleInterface recycleInterface, List list, SortType sortType, MoveTaskMode moveTaskMode, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setDestinations");
            }
            if ((i & 2) != 0) {
                sortType = SortType.AUTO;
            }
            if ((i & 4) != 0) {
                moveTaskMode = MoveTaskMode.Normal;
            }
            recycleInterface.setDestinations(list, sortType, moveTaskMode);
        }
    }
}
