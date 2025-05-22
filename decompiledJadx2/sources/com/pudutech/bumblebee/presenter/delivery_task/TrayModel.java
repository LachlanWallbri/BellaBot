package com.pudutech.bumblebee.presenter.delivery_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TrayModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u0004j\b\u0012\u0004\u0012\u00020\u001b`\u0006J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001d\u001a\u00020\u001bJ\u0006\u0010\u001e\u001a\u00020\u001fJ\b\u0010 \u001a\u00020\u001bH\u0016R*\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006!"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/TrayModel;", "", "()V", "allDestinations", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryModel;", "Lkotlin/collections/ArrayList;", "getAllDestinations", "()Ljava/util/ArrayList;", "setAllDestinations", "(Ljava/util/ArrayList;)V", "current", "getCurrent", "()Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryModel;", "setCurrent", "(Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryModel;)V", "isEmpty", "", "()Z", "setEmpty", "(Z)V", "palletTtsScheme", "getPalletTtsScheme", "()Ljava/lang/Object;", "setPalletTtsScheme", "(Ljava/lang/Object;)V", "getAllDestinationStr", "", "getDeliveryModel", "destination", "resetDeliveryStatus", "", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TrayModel {
    private DeliveryModel current;
    private Object palletTtsScheme;
    private boolean isEmpty = true;
    private ArrayList<DeliveryModel> allDestinations = new ArrayList<>();

    public final Object getPalletTtsScheme() {
        return this.palletTtsScheme;
    }

    public final void setPalletTtsScheme(Object obj) {
        this.palletTtsScheme = obj;
    }

    /* renamed from: isEmpty, reason: from getter */
    public final boolean getIsEmpty() {
        return this.isEmpty;
    }

    public final void setEmpty(boolean z) {
        this.isEmpty = z;
    }

    public final DeliveryModel getCurrent() {
        return this.current;
    }

    public final void setCurrent(DeliveryModel deliveryModel) {
        this.current = deliveryModel;
    }

    public final ArrayList<DeliveryModel> getAllDestinations() {
        return this.allDestinations;
    }

    public final void setAllDestinations(ArrayList<DeliveryModel> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.allDestinations = arrayList;
    }

    public final DeliveryModel getDeliveryModel(String destination) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        for (DeliveryModel deliveryModel : this.allDestinations) {
            if (Intrinsics.areEqual(deliveryModel.getDestination(), destination)) {
                return deliveryModel;
            }
        }
        return null;
    }

    public final ArrayList<String> getAllDestinationStr() {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = this.allDestinations.iterator();
        while (it.hasNext()) {
            arrayList.add(((DeliveryModel) it.next()).getDestination());
        }
        return new ArrayList<>(CollectionsKt.distinct(arrayList));
    }

    public final void resetDeliveryStatus() {
        Pdlog.m3273d("TrayModel", "resetDeliveryStatus ");
        this.isEmpty = false;
        this.current = (DeliveryModel) null;
        Iterator<T> it = this.allDestinations.iterator();
        while (it.hasNext()) {
            ((DeliveryModel) it.next()).setStatus(TaskStatus.AWAIT);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        ArrayList<DeliveryModel> arrayList = this.allDestinations;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            if (((DeliveryModel) obj).getStatus() == TaskStatus.AWAIT) {
                arrayList2.add(obj);
            }
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            sb.append(((DeliveryModel) it.next()).getDestination());
            sb.append(" ");
        }
        return "\n " + this.isEmpty + ' ' + this.current + " [" + ((Object) sb) + ']';
    }
}
