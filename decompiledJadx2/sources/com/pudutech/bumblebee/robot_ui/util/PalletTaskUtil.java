package com.pudutech.bumblebee.robot_ui.util;

import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.presenter.information_system_task.InformationSystemContract;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PalletTaskUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ \u0010\r\u001a\u00020\u00042\u0016\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011H\u0002J\u000e\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/util/PalletTaskUtil;", "", "()V", "hasFootInfo", "", "getHasFootInfo", "()Z", "setHasFootInfo", "(Z)V", "getPalletTaskName", "", "t", "Lcom/pudutech/bumblebee/presenter/delivery_task/TrayModel;", "hasTrayOrders", "foodInfo", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemContract$OrderInfo;", "Lkotlin/collections/ArrayList;", "isTaskFinish", "deliveryModel", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryModel;", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class PalletTaskUtil {
    public static final PalletTaskUtil INSTANCE = new PalletTaskUtil();
    private static boolean hasFootInfo;

    private PalletTaskUtil() {
    }

    public final boolean getHasFootInfo() {
        return hasFootInfo;
    }

    public final void setHasFootInfo(boolean z) {
        hasFootInfo = z;
    }

    public final String getPalletTaskName(TrayModel t) {
        Intrinsics.checkParameterIsNotNull(t, "t");
        StringBuilder sb = new StringBuilder();
        hasFootInfo = false;
        if (t.getAllDestinations().size() == 1) {
            DeliveryModel deliveryModel = t.getAllDestinations().get(0);
            Intrinsics.checkExpressionValueIsNotNull(deliveryModel, "t.allDestinations[0]");
            DeliveryModel deliveryModel2 = deliveryModel;
            if (!isTaskFinish(deliveryModel2)) {
                sb.append(deliveryModel2.getDestination());
                ArrayList<InformationSystemContract.OrderInfo> foodInfo = deliveryModel2.getFoodInfo();
                if (!(foodInfo == null || foodInfo.isEmpty())) {
                    ArrayList<InformationSystemContract.OrderInfo> foodInfo2 = deliveryModel2.getFoodInfo();
                    if (foodInfo2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (hasTrayOrders(foodInfo2)) {
                        sb.append("(");
                        sb.append("...");
                        sb.append(")");
                    } else {
                        sb.append("(x");
                        ArrayList<InformationSystemContract.OrderInfo> foodInfo3 = deliveryModel2.getFoodInfo();
                        if (foodInfo3 == null) {
                            Intrinsics.throwNpe();
                        }
                        sb.append(foodInfo3.size());
                        sb.append(")");
                    }
                    hasFootInfo = true;
                }
            }
        } else if (t.getAllDestinations().size() > 1) {
            int i = 0;
            for (Object obj : t.getAllDestinations()) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                DeliveryModel deliveryModel3 = (DeliveryModel) obj;
                if (!INSTANCE.isTaskFinish(deliveryModel3)) {
                    if (sb.length() == 0) {
                        sb.append(deliveryModel3.getDestination());
                    } else {
                        sb.append(", ");
                        sb.append(deliveryModel3.getDestination());
                    }
                }
                i = i2;
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "text.toString()");
        return sb2;
    }

    private final boolean hasTrayOrders(ArrayList<InformationSystemContract.OrderInfo> foodInfo) {
        Iterator<T> it = foodInfo.iterator();
        while (it.hasNext()) {
            if (((InformationSystemContract.OrderInfo) it.next()).getOrderType() == InformationSystemContract.OrderInfo.Type.TrayOrder) {
                return true;
            }
        }
        return false;
    }

    public final boolean isTaskFinish(DeliveryModel deliveryModel) {
        Intrinsics.checkParameterIsNotNull(deliveryModel, "deliveryModel");
        return deliveryModel.getStatus() == TaskStatus.DONE || deliveryModel.getStatus() == TaskStatus.DONE_BEFORE_ARRIVAL || deliveryModel.getStatus() == TaskStatus.CANCEL;
    }
}
