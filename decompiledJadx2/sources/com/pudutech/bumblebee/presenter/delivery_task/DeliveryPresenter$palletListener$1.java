package com.pudutech.bumblebee.presenter.delivery_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletTaskEvent;
import com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletTaskListener;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeliveryPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/bumblebee/presenter/delivery_task/DeliveryPresenter$palletListener$1", "Lcom/pudutech/bumblebee/business/peripherals_task/pallet_task/PalletTaskListener;", "onEvent", "", "id", "", "event", "Lcom/pudutech/bumblebee/business/peripherals_task/pallet_task/PalletTaskEvent;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DeliveryPresenter$palletListener$1 implements PalletTaskListener {
    final /* synthetic */ DeliveryPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeliveryPresenter$palletListener$1(DeliveryPresenter deliveryPresenter) {
        this.this$0 = deliveryPresenter;
    }

    @Override // com.pudutech.bumblebee.business.peripherals_task.pallet_task.PalletTaskListener
    public void onEvent(final int id, final PalletTaskEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Pdlog.m3275i(this.this$0.getTAG(), "on pallet event " + id + ' ' + event);
        final int i = id + (-1);
        this.this$0.runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$palletListener$1$onEvent$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                ViewModel viewModel;
                ViewModel viewModel2;
                ViewModel viewModel3;
                ViewModel viewModel4;
                ViewModel viewModel5;
                ViewModel viewModel6;
                boolean isStopAndPalletChange;
                ViewModel viewModel7;
                ViewModel viewModel8;
                ViewModel viewModel9;
                ViewModel viewModel10;
                boolean isStopAndPalletChange2;
                ViewModel viewModel11;
                ViewModel viewModel12;
                ViewModel viewModel13;
                int i2 = i;
                viewModel = DeliveryPresenter$palletListener$1.this.this$0.viewModel;
                if (i2 >= viewModel.getTrays().size()) {
                    String tag = DeliveryPresenter$palletListener$1.this.this$0.getTAG();
                    StringBuilder sb = new StringBuilder();
                    sb.append("pallet id=");
                    sb.append(id);
                    sb.append(" not in viewModel. size=");
                    viewModel13 = DeliveryPresenter$palletListener$1.this.this$0.viewModel;
                    sb.append(viewModel13.getTrays().size());
                    Pdlog.m3277w(tag, sb.toString());
                    return;
                }
                int i3 = DeliveryPresenter.WhenMappings.$EnumSwitchMapping$2[event.ordinal()];
                if (i3 == 1) {
                    viewModel2 = DeliveryPresenter$palletListener$1.this.this$0.viewModel;
                    DeliveryModel current = viewModel2.getTrays().get(i).getCurrent();
                    if (current == null || current.getStatus() != TaskStatus.ARRIVAL) {
                        return;
                    }
                    viewModel3 = DeliveryPresenter$palletListener$1.this.this$0.viewModel;
                    if (viewModel3.getDeliveryMode() == DeliveryMode.BIRTHDAY) {
                        return;
                    }
                    current.setStatus(TaskStatus.DONE);
                    viewModel4 = DeliveryPresenter$palletListener$1.this.this$0.viewModel;
                    if (viewModel4.getDestinations(TaskStatus.ARRIVAL).isEmpty()) {
                        Pdlog.m3275i(DeliveryPresenter$palletListener$1.this.this$0.getTAG(), "task done by tray sensor detect.");
                        DeliveryPresenter.completeDelivery$default(DeliveryPresenter$palletListener$1.this.this$0, DeliveryModel.CompleteType.TRAY_EMPTY, null, 2, null);
                        DeliveryPresenter$palletListener$1.this.this$0.showPalletOnUIThread(DeliveryContract.DeliveryEvent.EMPTY, i);
                        return;
                    }
                    return;
                }
                if (i3 == 2) {
                    DeliveryPresenter deliveryPresenter = DeliveryPresenter$palletListener$1.this.this$0;
                    viewModel5 = DeliveryPresenter$palletListener$1.this.this$0.viewModel;
                    ArrayList<TrayModel> trays = viewModel5.getTrays();
                    viewModel6 = DeliveryPresenter$palletListener$1.this.this$0.viewModel;
                    TrayModel trayModel = viewModel6.getTrays().get(i);
                    Intrinsics.checkExpressionValueIsNotNull(trayModel, "viewModel.trays[index]");
                    isStopAndPalletChange = deliveryPresenter.isStopAndPalletChange(trays, trayModel);
                    if (isStopAndPalletChange) {
                        DeliveryPresenter$palletListener$1.this.this$0.showPalletOnUIThread(DeliveryContract.DeliveryEvent.PALLET_PLACE, i);
                    }
                    viewModel7 = DeliveryPresenter$palletListener$1.this.this$0.viewModel;
                    DeliveryModel current2 = viewModel7.getTrays().get(i).getCurrent();
                    if (current2 == null || current2.getStatus() != TaskStatus.ARRIVAL) {
                        return;
                    }
                    viewModel8 = DeliveryPresenter$palletListener$1.this.this$0.viewModel;
                    viewModel8.getTrays().get(i).setEmpty(false);
                    DeliveryPresenter$palletListener$1.this.this$0.showPalletOnUIThread(DeliveryContract.DeliveryEvent.PALLET_CHANGE, i);
                    return;
                }
                if (i3 != 3) {
                    return;
                }
                DeliveryPresenter deliveryPresenter2 = DeliveryPresenter$palletListener$1.this.this$0;
                viewModel9 = DeliveryPresenter$palletListener$1.this.this$0.viewModel;
                ArrayList<TrayModel> trays2 = viewModel9.getTrays();
                viewModel10 = DeliveryPresenter$palletListener$1.this.this$0.viewModel;
                TrayModel trayModel2 = viewModel10.getTrays().get(i);
                Intrinsics.checkExpressionValueIsNotNull(trayModel2, "viewModel.trays[index]");
                isStopAndPalletChange2 = deliveryPresenter2.isStopAndPalletChange(trays2, trayModel2);
                if (isStopAndPalletChange2) {
                    DeliveryPresenter$palletListener$1.this.this$0.showPalletOnUIThread(DeliveryContract.DeliveryEvent.PALLET_TAKE, i);
                }
                viewModel11 = DeliveryPresenter$palletListener$1.this.this$0.viewModel;
                DeliveryModel current3 = viewModel11.getTrays().get(i).getCurrent();
                if (current3 == null || current3.getStatus() != TaskStatus.ARRIVAL) {
                    return;
                }
                viewModel12 = DeliveryPresenter$palletListener$1.this.this$0.viewModel;
                viewModel12.getTrays().get(i).setEmpty(true);
                DeliveryPresenter$palletListener$1.this.this$0.showPalletOnUIThread(DeliveryContract.DeliveryEvent.PALLET_CHANGE, i);
            }
        });
    }
}
