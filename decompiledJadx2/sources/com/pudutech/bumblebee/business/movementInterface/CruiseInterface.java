package com.pudutech.bumblebee.business.movementInterface;

import com.pudutech.bumblebee.business.movementCallback.CruiseCallback;
import com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface;
import com.pudutech.mirsdk.aidl.MoveActionInterface;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: CruiseInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J*\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH&¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementInterface/CruiseInterface;", "Lcom/pudutech/bumblebee/business/movementInterface/BaseTaskInterface;", "setCallback", "", "callback", "Lcom/pudutech/bumblebee/business/movementCallback/CruiseCallback;", "setPath", "pathID", "", "performance", "Lcom/pudutech/mirsdk/aidl/serialize/MoveTaskMode;", "stops", "", "", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface CruiseInterface extends BaseTaskInterface {
    void setCallback(CruiseCallback callback);

    void setPath(int pathID, MoveTaskMode performance, List<String> stops);

    /* compiled from: CruiseInterface.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static MoveActionInterface getMoveAction(CruiseInterface cruiseInterface) {
            return BaseTaskInterface.DefaultImpls.getMoveAction(cruiseInterface);
        }

        public static void pauseTask(CruiseInterface cruiseInterface) {
            BaseTaskInterface.DefaultImpls.pauseTask(cruiseInterface);
        }

        public static void resumeTask(CruiseInterface cruiseInterface) {
            BaseTaskInterface.DefaultImpls.resumeTask(cruiseInterface);
        }

        public static void setActive(CruiseInterface cruiseInterface, boolean z, boolean z2) {
            BaseTaskInterface.DefaultImpls.setActive(cruiseInterface, z, z2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void setPath$default(CruiseInterface cruiseInterface, int i, MoveTaskMode moveTaskMode, List list, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setPath");
            }
            if ((i2 & 2) != 0) {
                moveTaskMode = MoveTaskMode.Normal;
            }
            if ((i2 & 4) != 0) {
                list = CollectionsKt.emptyList();
            }
            cruiseInterface.setPath(i, moveTaskMode, list);
        }
    }
}
