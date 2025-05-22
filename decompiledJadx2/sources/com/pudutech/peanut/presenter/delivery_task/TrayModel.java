package com.pudutech.peanut.presenter.delivery_task;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TrayModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0010\u001a\u00020\u0000J\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0004j\b\u0012\u0004\u0012\u00020\u0012`\u0006J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0014\u001a\u00020\u0012R*\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/delivery_task/TrayModel;", "", "()V", "allDestinations", "Ljava/util/ArrayList;", "Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel;", "Lkotlin/collections/ArrayList;", "getAllDestinations", "()Ljava/util/ArrayList;", "setAllDestinations", "(Ljava/util/ArrayList;)V", "current", "getCurrent", "()Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel;", "setCurrent", "(Lcom/pudutech/peanut/presenter/delivery_task/DeliveryModel;)V", "copy", "getAllDestinationStr", "", "getDeliveryModel", "destination", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class TrayModel {
    private ArrayList<DeliveryModel> allDestinations = new ArrayList<>();
    private DeliveryModel current;

    public final ArrayList<DeliveryModel> getAllDestinations() {
        return this.allDestinations;
    }

    public final void setAllDestinations(ArrayList<DeliveryModel> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.allDestinations = arrayList;
    }

    public final DeliveryModel getCurrent() {
        return this.current;
    }

    public final void setCurrent(DeliveryModel deliveryModel) {
        this.current = deliveryModel;
    }

    public final ArrayList<String> getAllDestinationStr() {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = this.allDestinations.iterator();
        while (it.hasNext()) {
            arrayList.add(((DeliveryModel) it.next()).getDestination());
        }
        return new ArrayList<>(CollectionsKt.distinct(arrayList));
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

    public final TrayModel copy() {
        TrayModel trayModel = new TrayModel();
        trayModel.allDestinations.addAll(this.allDestinations);
        return trayModel;
    }
}
