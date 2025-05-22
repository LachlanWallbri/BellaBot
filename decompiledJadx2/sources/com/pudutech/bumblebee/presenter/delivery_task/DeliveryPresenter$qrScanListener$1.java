package com.pudutech.bumblebee.presenter.delivery_task;

import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.movementInterface.DeliverInterface;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import com.pudutech.bumblebee.business.peripherals_task.qr_scan_task.QrScanEvent;
import com.pudutech.bumblebee.business.peripherals_task.qr_scan_task.QrScanTaskListener;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeliveryPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, m3961d2 = {"com/pudutech/bumblebee/presenter/delivery_task/DeliveryPresenter$qrScanListener$1", "Lcom/pudutech/bumblebee/business/peripherals_task/qr_scan_task/QrScanTaskListener;", "onQrScanEvent", "", "event", "Lcom/pudutech/bumblebee/business/peripherals_task/qr_scan_task/QrScanEvent;", NotificationCompat.CATEGORY_MESSAGE, "", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DeliveryPresenter$qrScanListener$1 implements QrScanTaskListener {
    final /* synthetic */ DeliveryPresenter this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeliveryPresenter$qrScanListener$1(DeliveryPresenter deliveryPresenter) {
        this.this$0 = deliveryPresenter;
    }

    @Override // com.pudutech.bumblebee.business.peripherals_task.qr_scan_task.QrScanTaskListener
    public void onQrScanEvent(QrScanEvent event, String msg) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (event != QrScanEvent.EMPLOYEES_NO) {
            return;
        }
        Pdlog.m3273d(this.this$0.getTAG(), "onQrScanEvent : event = " + event + "; msg = " + msg + "; ");
        this.this$0.runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.delivery_task.DeliveryPresenter$qrScanListener$1$onQrScanEvent$1
            /* JADX INFO: Access modifiers changed from: package-private */
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
                DeliverInterface deliverInterface;
                viewModel = DeliveryPresenter$qrScanListener$1.this.this$0.viewModel;
                if (viewModel.getDestinations(TaskStatus.ARRIVAL).size() > 0) {
                    DeliveryPresenter.completeDelivery$default(DeliveryPresenter$qrScanListener$1.this.this$0, DeliveryModel.CompleteType.QRCODE, null, 2, null);
                    deliverInterface = DeliveryPresenter$qrScanListener$1.this.this$0.deliverInterface;
                    if (deliverInterface != null) {
                        deliverInterface.setActive(true);
                    }
                }
            }
        });
    }
}
