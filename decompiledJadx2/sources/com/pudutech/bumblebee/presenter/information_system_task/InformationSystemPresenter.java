package com.pudutech.bumblebee.presenter.information_system_task;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.movementInterface.SortType;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.peripherals_task.qr_scan_task.QrScanEvent;
import com.pudutech.bumblebee.business.peripherals_task.qr_scan_task.QrScanTaskListener;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryContract;
import com.pudutech.bumblebee.presenter.delivery_task.DeliveryModel;
import com.pudutech.bumblebee.presenter.delivery_task.TrayModel;
import com.pudutech.bumblebee.presenter.information_system_task.InformationSystemContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.robot_open_task.RobotOpenManager;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.OrderInfoListener;
import com.pudutech.bumblebee.presenter.robot_open_task.listener.RemoteTaskListener;
import com.pudutech.bumblebee.presenter.utils.SoundPoolVoiceUtil;
import com.pudutech.robot.opensdk.bean.DeliverySort;
import com.pudutech.robot.opensdk.bean.DeliveryTaskBody;
import com.pudutech.robot.opensdk.bean.DeliveryTaskType;
import com.pudutech.robot.opensdk.bean.DestinationTask;
import com.pudutech.robot.opensdk.bean.TrayDestinationTasks;
import com.pudutech.robot.opensdk.bean.TrayTaskChangeType;
import com.pudutech.robot.opensdk.bean.pub.OrderIdData;
import com.pudutech.robot.opensdk.bean.pub.OrderState;
import com.pudutech.robot.opensdk.interf.IBody;
import com.pudutech.robot.opensdk.interf.ICallback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.jetbrains.anko.DimensionsKt;

/* compiled from: InformationSystemPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006B\u0005¢\u0006\u0002\u0010\u0007J0\u0010\f\u001a\u00020\r2\u0016\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\tH\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\tH\u0016J(\u0010\u001d\u001a\u00020\u00162\u0016\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u00112\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\rH\u0014J\b\u0010!\u001a\u00020\rH\u0014R\u0014\u0010\b\u001a\u00020\tX\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\""}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemContract$PresenterInterface;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/OrderInfoListener;", "Lcom/pudutech/bumblebee/business/peripherals_task/qr_scan_task/QrScanTaskListener;", "Lcom/pudutech/bumblebee/presenter/robot_open_task/listener/RemoteTaskListener;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "actionOrderStart", "", "orders", "Ljava/util/ArrayList;", "Lcom/pudutech/bumblebee/presenter/information_system_task/InformationSystemContract$OrderInfo;", "Lkotlin/collections/ArrayList;", "callback", "Lcom/pudutech/robot/opensdk/interf/ICallback;", "employees", "onDeliveryTask", "", "deliveryTaskBody", "Lcom/pudutech/robot/opensdk/bean/DeliveryTaskBody;", "onQrScanEvent", "event", "Lcom/pudutech/bumblebee/business/peripherals_task/qr_scan_task/QrScanEvent;", NotificationCompat.CATEGORY_MESSAGE, "onReceiveOrder", "trayIndex", "", "onViewAttach", "onViewRemoved", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class InformationSystemPresenter extends BaseOneViewPresenter<InformationSystemContract.ViewInterface> implements InformationSystemContract.PresenterInterface, OrderInfoListener, QrScanTaskListener, RemoteTaskListener {
    private final String TAG = "InformationSystemPresenter";

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[DeliveryContract.DeliveryTaskSortType.values().length];

        static {
            $EnumSwitchMapping$0[DeliveryContract.DeliveryTaskSortType.AUTO.ordinal()] = 1;
            $EnumSwitchMapping$0[DeliveryContract.DeliveryTaskSortType.FIXED.ordinal()] = 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewAttach() {
        super.onViewAttach();
        InformationSystemPresenter informationSystemPresenter = this;
        Peripherals.INSTANCE.getQrScanTask().addListener(informationSystemPresenter);
        RobotOpenManager.INSTANCE.getOrderInfoListener().addListener(informationSystemPresenter);
        RobotOpenManager.INSTANCE.getRemoteTaskListener().addListener(informationSystemPresenter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        InformationSystemPresenter informationSystemPresenter = this;
        Peripherals.INSTANCE.getQrScanTask().removeListener(informationSystemPresenter);
        RobotOpenManager.INSTANCE.getOrderInfoListener().removeListener(informationSystemPresenter);
        RobotOpenManager.INSTANCE.getRemoteTaskListener().removeListener(informationSystemPresenter);
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.listener.RemoteTaskListener
    public boolean onDeliveryTask(DeliveryTaskBody deliveryTaskBody) {
        DeliveryContract.DeliveryTaskSortType deliveryTaskSortType;
        SortType sortType;
        Intrinsics.checkParameterIsNotNull(deliveryTaskBody, "deliveryTaskBody");
        if (!Intrinsics.areEqual(deliveryTaskBody.getType(), TrayTaskChangeType.NEW.getId())) {
            return false;
        }
        if (Intrinsics.areEqual(deliveryTaskBody.getDeliverySort(), DeliverySort.FIXED.getId())) {
            deliveryTaskSortType = DeliveryContract.DeliveryTaskSortType.FIXED;
        } else {
            deliveryTaskSortType = DeliveryContract.DeliveryTaskSortType.AUTO;
        }
        ArrayList<TrayModel> arrayList = new ArrayList<>();
        for (TrayDestinationTasks trayDestinationTasks : deliveryTaskBody.getTrays()) {
            TrayModel trayModel = new TrayModel();
            for (DestinationTask destinationTask : trayDestinationTasks.getDestinations()) {
                trayModel.getAllDestinations().add(new DeliveryModel(destinationTask.getDestination(), null, destinationTask.getId(), DeliveryTaskType.REMOTE.getType()));
            }
            arrayList.add(trayModel);
        }
        int i = WhenMappings.$EnumSwitchMapping$0[deliveryTaskSortType.ordinal()];
        if (i == 1) {
            sortType = SortType.AUTO;
        } else {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            sortType = SortType.FIXED;
        }
        InformationSystemContract.ViewInterface theView = getTheView();
        if (theView != null) {
            return theView.onReceiveDeliveryTask(sortType, arrayList, deliveryTaskBody.getExecuteTask());
        }
        return false;
    }

    @Override // com.pudutech.bumblebee.business.peripherals_task.qr_scan_task.QrScanTaskListener
    public void onQrScanEvent(QrScanEvent event, String msg) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        if (event != QrScanEvent.ONE_FOOD_ORDER) {
            return;
        }
        Pdlog.m3273d(getTAG(), "onQrScanEvent : event = " + event + "; msg = " + msg + "; ");
        List split$default = StringsKt.split$default((CharSequence) StringsKt.trim((CharSequence) msg).toString(), new String[]{"|"}, false, 0, 6, (Object) null);
        try {
            String str = (String) split$default.get(0);
            if (str == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            String obj = StringsKt.trim((CharSequence) str).toString();
            String str2 = (String) split$default.get(1);
            if (str2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            String obj2 = StringsKt.trim((CharSequence) str2).toString();
            String str3 = (String) split$default.get(2);
            if (str3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            String obj3 = StringsKt.trim((CharSequence) str3).toString();
            String str4 = (String) split$default.get(3);
            if (str4 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            double parseDouble = Double.parseDouble(StringsKt.trim((CharSequence) str4).toString());
            String str5 = (String) split$default.get(4);
            if (str5 != null) {
                final InformationSystemContract.OrderInfo orderInfo = new InformationSystemContract.OrderInfo(obj, obj2, obj3, parseDouble, StringsKt.trim((CharSequence) str5).toString(), null, null, 0L, null, DimensionsKt.XXHDPI, null);
                runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.information_system_task.InformationSystemPresenter$onQrScanEvent$1
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
                        InformationSystemContract.ViewInterface theView;
                        theView = InformationSystemPresenter.this.getTheView();
                        Boolean valueOf = theView != null ? Boolean.valueOf(InformationSystemContract.ViewInterface.DefaultImpls.onReceiveOrderInfo$default(theView, CollectionsKt.arrayListOf(orderInfo), InformationSystemContract.OrderInfoType.SCAN, 0, 4, null)) : null;
                        if (Intrinsics.areEqual((Object) valueOf, (Object) true)) {
                            SoundPoolVoiceUtil.INSTANCE.play(SoundPoolVoiceUtil.Voice.SCAN, BusinessSetting.INSTANCE.getBtnVoice() / 100.0f);
                        } else if (Intrinsics.areEqual((Object) valueOf, (Object) false)) {
                            SoundPoolVoiceUtil.INSTANCE.play(SoundPoolVoiceUtil.Voice.SCAN_FAILED, BusinessSetting.INSTANCE.getBtnVoice() / 100.0f);
                        }
                    }
                });
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        } catch (Exception e) {
            Pdlog.m3274e(getTAG(), Log.getStackTraceString(e));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v9, types: [T, java.lang.String] */
    @Override // com.pudutech.bumblebee.presenter.information_system_task.InformationSystemContract.PresenterInterface
    public void actionOrderStart(final ArrayList<InformationSystemContract.OrderInfo> orders, final ICallback callback, String employees) {
        Intrinsics.checkParameterIsNotNull(orders, "orders");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Intrinsics.checkParameterIsNotNull(employees, "employees");
        ArrayList<OrderIdData> arrayList = new ArrayList<>();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        ArrayList<InformationSystemContract.OrderInfo> arrayList2 = orders;
        Iterator<T> it = arrayList2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            InformationSystemContract.OrderInfo orderInfo = (InformationSystemContract.OrderInfo) it.next();
            arrayList.add(new OrderIdData(orderInfo.getScid(), 0L));
            if (orderInfo.getNotifyTarget().length() > 0) {
                objectRef.element = orderInfo.getNotifyTarget();
            }
        }
        if (((String) objectRef.element).length() == 0) {
            for (InformationSystemContract.OrderInfo orderInfo2 : arrayList2) {
                orderInfo2.setStartTime(System.currentTimeMillis());
                orderInfo2.setOrderState(OrderState.Start.name());
            }
        }
        Pdlog.m3273d(getTAG(), "actionOrderStart : orders = " + orders + "; target = " + ((String) objectRef.element));
        RobotOpenManager.INSTANCE.deliveryOrderStart(employees, (String) objectRef.element, arrayList, new ICallback() { // from class: com.pudutech.bumblebee.presenter.information_system_task.InformationSystemPresenter$actionOrderStart$3
            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onFailed(Exception e) {
                Intrinsics.checkParameterIsNotNull(e, "e");
                ICallback.this.onFailed(e);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.pudutech.robot.opensdk.interf.ICallback
            public void onSuccess(IBody result) {
                if (((String) objectRef.element).length() > 0) {
                    for (InformationSystemContract.OrderInfo orderInfo3 : orders) {
                        orderInfo3.setStartTime(System.currentTimeMillis());
                        orderInfo3.setOrderState(OrderState.Start.name());
                    }
                }
                ICallback.this.onSuccess(result);
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.robot_open_task.listener.OrderInfoListener
    public boolean onReceiveOrder(ArrayList<InformationSystemContract.OrderInfo> orders, int trayIndex) {
        Intrinsics.checkParameterIsNotNull(orders, "orders");
        Pdlog.m3273d(getTAG(), "onReceiveOrder orders=" + orders);
        InformationSystemContract.ViewInterface theView = getTheView();
        if (theView != null) {
            return theView.onReceiveOrderInfo(orders, InformationSystemContract.OrderInfoType.INFORMATION_SYS, trayIndex);
        }
        return false;
    }
}
