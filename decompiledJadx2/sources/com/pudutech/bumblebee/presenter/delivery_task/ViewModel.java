package com.pudutech.bumblebee.presenter.delivery_task;

import android.os.SystemClock;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J1\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\t2!\u0010\u0017\u001a\u001d\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u001c\u0012\u0004\u0012\u00020\u00150\u0018J\u0016\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\t0\u000ej\b\u0012\u0004\u0012\u00020\t`\u0010J/\u0010\u001e\u001a\u0012\u0012\u0004\u0012\u00020\t0\u000ej\b\u0012\u0004\u0012\u00020\t`\u00102\u0012\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020!0 \"\u00020!¢\u0006\u0002\u0010\"J\u001e\u0010#\u001a\u0012\u0012\u0004\u0012\u00020$0\u000ej\b\u0012\u0004\u0012\u00020$`\u00102\u0006\u0010\u0016\u001a\u00020\tJ(\u0010%\u001a\u00020\u00152\b\b\u0002\u0010&\u001a\u00020\u00042\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010J\b\u0010'\u001a\u00020\tH\u0016R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR>\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u00102\u0016\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006("}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/delivery_task/ViewModel;", "", "()V", "<set-?>", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryMode;", "deliveryMode", "getDeliveryMode", "()Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryMode;", "destination", "", "getDestination", "()Ljava/lang/String;", "setDestination", "(Ljava/lang/String;)V", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/delivery_task/TrayModel;", "Lkotlin/collections/ArrayList;", "trays", "getTrays", "()Ljava/util/ArrayList;", "forEachDeliveryModel", "", "des", "method", "Lkotlin/Function1;", "Lcom/pudutech/bumblebee/presenter/delivery_task/DeliveryModel;", "Lkotlin/ParameterName;", "name", "model", "getAllDestination", "getDestinations", "status", "", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "([Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;)Ljava/util/ArrayList;", "getTrayIndexes", "", "init", "mode", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ViewModel {
    private String destination = "";
    private ArrayList<TrayModel> trays = new ArrayList<>();
    private DeliveryMode deliveryMode = DeliveryMode.GENERAL;

    public final String getDestination() {
        return this.destination;
    }

    public final void setDestination(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.destination = str;
    }

    public final ArrayList<TrayModel> getTrays() {
        return this.trays;
    }

    public final DeliveryMode getDeliveryMode() {
        return this.deliveryMode;
    }

    public static /* synthetic */ void init$default(ViewModel viewModel, DeliveryMode deliveryMode, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            deliveryMode = DeliveryMode.GENERAL;
        }
        viewModel.init(deliveryMode, arrayList);
    }

    public final void init(DeliveryMode mode, ArrayList<TrayModel> trays) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(trays, "trays");
        this.deliveryMode = mode;
        this.trays = trays;
        for (TrayModel trayModel : this.trays) {
            Iterator<T> it = trayModel.getAllDestinations().iterator();
            while (it.hasNext()) {
                ((DeliveryModel) it.next()).setInitTimestamp_ms(SystemClock.elapsedRealtime());
            }
            trayModel.resetDeliveryStatus();
        }
    }

    public final ArrayList<Integer> getTrayIndexes(String des) {
        Intrinsics.checkParameterIsNotNull(des, "des");
        ArrayList<Integer> arrayList = new ArrayList<>();
        int i = 0;
        for (Object obj : this.trays) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            if (((TrayModel) obj).getDeliveryModel(des) != null) {
                arrayList.add(Integer.valueOf(i));
            }
            i = i2;
        }
        return arrayList;
    }

    public final ArrayList<String> getDestinations(TaskStatus... status) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = this.trays.iterator();
        while (it.hasNext()) {
            for (DeliveryModel deliveryModel : ((TrayModel) it.next()).getAllDestinations()) {
                if (ArraysKt.contains(status, deliveryModel.getStatus())) {
                    arrayList.add(deliveryModel.getDestination());
                }
            }
        }
        return new ArrayList<>(CollectionsKt.distinct(arrayList));
    }

    public final ArrayList<String> getAllDestination() {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = this.trays.iterator();
        while (it.hasNext()) {
            arrayList.addAll(((TrayModel) it.next()).getAllDestinationStr());
        }
        return new ArrayList<>(CollectionsKt.distinct(arrayList));
    }

    public final void forEachDeliveryModel(String des, Function1<? super DeliveryModel, Unit> method) {
        Intrinsics.checkParameterIsNotNull(des, "des");
        Intrinsics.checkParameterIsNotNull(method, "method");
        Iterator<T> it = this.trays.iterator();
        while (it.hasNext()) {
            DeliveryModel deliveryModel = ((TrayModel) it.next()).getDeliveryModel(des);
            if (deliveryModel != null) {
                method.invoke(deliveryModel);
            }
        }
    }

    public String toString() {
        return String.valueOf(this.trays);
    }
}
