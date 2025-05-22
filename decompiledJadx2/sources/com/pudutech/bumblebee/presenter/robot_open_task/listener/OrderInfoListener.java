package com.pudutech.bumblebee.presenter.robot_open_task.listener;

import com.pudutech.bumblebee.business.base.BaseListener;
import com.pudutech.bumblebee.presenter.information_system_task.InformationSystemContract;
import java.util.ArrayList;
import kotlin.Metadata;

/* compiled from: OrderInfoListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J*\u0010\u0002\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u00072\b\b\u0002\u0010\b\u001a\u00020\tH&¨\u0006\n"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/OrderInfoListener;", "Lcom/pudutech/bumblebee/business/base/BaseListener;", "onReceiveOrder", "", "orders", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemContract$OrderInfo;", "Lkotlin/collections/ArrayList;", "trayIndex", "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface OrderInfoListener extends BaseListener {
    boolean onReceiveOrder(ArrayList<InformationSystemContract.OrderInfo> orders, int trayIndex);

    /* compiled from: OrderInfoListener.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ boolean onReceiveOrder$default(OrderInfoListener orderInfoListener, ArrayList arrayList, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: onReceiveOrder");
            }
            if ((i2 & 2) != 0) {
                i = 0;
            }
            return orderInfoListener.onReceiveOrder(arrayList, i);
        }
    }
}
