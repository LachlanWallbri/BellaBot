package com.pudutech.bumblebee.presenter.recycle_task;

import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.business.behavior.DelayResumeActive;
import com.pudutech.bumblebee.business.movementCallback.DeliverCallback;
import com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface;
import com.pudutech.bumblebee.business.movementInterface.CruiseInterface;
import com.pudutech.bumblebee.business.movementInterface.DeliverInterface;
import com.pudutech.bumblebee.business.movementInterface.GoHomeInterface;
import com.pudutech.bumblebee.business.movementInterface.HangOutInterface;
import com.pudutech.bumblebee.business.movementInterface.IdleInterface;
import com.pudutech.bumblebee.business.movementInterface.RecycleInterface;
import com.pudutech.bumblebee.business.movementInterface.TaskStatus;
import com.pudutech.bumblebee.business.movementInterface.TempMoveInterface;
import com.pudutech.bumblebee.business.movementTask.CruiseTask;
import com.pudutech.bumblebee.business.movementTask.DeliverTask;
import com.pudutech.bumblebee.business.movementTask.GoHomeTask;
import com.pudutech.bumblebee.business.movementTask.HangoutTask;
import com.pudutech.bumblebee.business.movementTask.IdleTask;
import com.pudutech.bumblebee.business.movementTask.RecycleTask;
import com.pudutech.bumblebee.business.movementTask.TempTask;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.peripherals_task.lora_notice_task.LoRaNoticeTask;
import com.pudutech.bumblebee.business.utils.ActiveModel;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.recycle_task.TransferDishesContract;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportDelivery;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdkwrap.lib.map.Destination;
import com.pudutech.mirsdkwrap.lib.map.DestinationType;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.resources.resource.ResUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: TransferDishesPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0016J\b\u0010\u0019\u001a\u00020\u0017H\u0016J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u001b\u001a\u00020\u0017H\u0016J\b\u0010\u001c\u001a\u00020\u0017H\u0016J\u0018\u0010\u001d\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u0013H\u0002J\b\u0010\u001f\u001a\u00020\u0017H\u0016J\u001c\u0010 \u001a\u00020\u00172\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010\u0007H\u0016J \u0010$\u001a\u00020\u00172\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010%\u001a\u00020&H\u0016J\u0018\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\r2\u0006\u0010)\u001a\u00020\u0007H\u0016J\u0010\u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020,H\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000¨\u0006-"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/recycle_task/TransferDishesPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/recycle_task/TransferDishesContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/recycle_task/TransferDishesContract$PresenterInterface;", "Lcom/pudutech/bumblebee/business/movementCallback/DeliverCallback;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "activeModel", "Lcom/pudutech/bumblebee/business/utils/ActiveModel;", "autoMoveActive", "", "Ljava/lang/Boolean;", "deliverInterface", "Lcom/pudutech/bumblebee/business/movementInterface/DeliverInterface;", "destination", "taskStatus", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "type", "Lcom/pudutech/bumblebee/presenter/recycle_task/TransferDishesContract$DestinationType;", "actionActive", "", "actionCancelTask", "actionFinish", "actionGo", "actionPause", "actionPauseNoTimer", "controlNoticeVIP", "status", "onDone", "onMovementChanged", "state", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "description", "onStatusChanged", "range", "", "onTaskSetup", "isLegalTask", "info", "showOnUI", "event", "Lcom/pudutech/bumblebee/presenter/recycle_task/TransferDishesContract$TransferEvent;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TransferDishesPresenter extends BaseOneViewPresenter<TransferDishesContract.ViewInterface> implements TransferDishesContract.PresenterInterface, DeliverCallback {
    private Boolean autoMoveActive;
    private DeliverInterface deliverInterface;
    private TaskStatus taskStatus;
    private TransferDishesContract.DestinationType type;
    private final String TAG = "TransferDishesPresenter";
    private String destination = "";
    private final ActiveModel activeModel = new ActiveModel();

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TaskStatus.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[TaskStatus.AWAIT.ordinal()] = 1;
            $EnumSwitchMapping$0[TaskStatus.ON_THE_WAY.ordinal()] = 2;
            $EnumSwitchMapping$0[TaskStatus.ARRIVAL.ordinal()] = 3;
            $EnumSwitchMapping$0[TaskStatus.DONE.ordinal()] = 4;
            $EnumSwitchMapping$0[TaskStatus.CANCEL.ordinal()] = 5;
            $EnumSwitchMapping$1 = new int[TaskStatus.values().length];
            $EnumSwitchMapping$1[TaskStatus.APPROACHING.ordinal()] = 1;
            $EnumSwitchMapping$1[TaskStatus.DONE.ordinal()] = 2;
            $EnumSwitchMapping$1[TaskStatus.CANCEL.ordinal()] = 3;
            $EnumSwitchMapping$1[TaskStatus.DONE_BEFORE_ARRIVAL.ordinal()] = 4;
        }
    }

    public static final /* synthetic */ TransferDishesContract.DestinationType access$getType$p(TransferDishesPresenter transferDishesPresenter) {
        TransferDishesContract.DestinationType destinationType = transferDishesPresenter.type;
        if (destinationType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("type");
        }
        return destinationType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.TransferDishesContract.PresenterInterface
    public void actionGo(final TransferDishesContract.DestinationType type) {
        Intrinsics.checkParameterIsNotNull(type, "type");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.TransferDishesPresenter$actionGo$1
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
                ArrayList arrayList;
                DeliverInterface deliverInterface;
                DeliverInterface deliverInterface2;
                ReportDelivery.INSTANCE.toCloud();
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                DeliverInterface deliverInterface3 = null;
                TransferDishesPresenter.this.autoMoveActive = (Boolean) null;
                TransferDishesPresenter.this.type = type;
                if (type == TransferDishesContract.DestinationType.DISHWASHING_ROOM) {
                    ArrayList<Destination> destinationByType = RobotMapManager.INSTANCE.getDestinationByType(DestinationType.DISHWASHING);
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(destinationByType, 10));
                    Iterator<T> it = destinationByType.iterator();
                    while (it.hasNext()) {
                        arrayList2.add(((Destination) it.next()).getName());
                    }
                    arrayList = arrayList2;
                } else {
                    ArrayList<Destination> destinationByType2 = RobotMapManager.INSTANCE.getDestinationByType(DestinationType.TRANSLIST);
                    ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(destinationByType2, 10));
                    Iterator<T> it2 = destinationByType2.iterator();
                    while (it2.hasNext()) {
                        arrayList3.add(((Destination) it2.next()).getName());
                    }
                    arrayList = arrayList3;
                }
                ArrayList arrayList4 = arrayList;
                if (arrayList4.size() == 0) {
                    Pdlog.m3277w(TransferDishesPresenter.this.getTAG(), "no " + type + " in map");
                    TransferDishesPresenter.this.showOnUI(TransferDishesContract.TransferEvent.NO_DESTINATION);
                    return;
                }
                TransferDishesPresenter transferDishesPresenter = TransferDishesPresenter.this;
                Behavior behavior = Behavior.INSTANCE;
                TypeToken<DeliverInterface> typeToken = new TypeToken<DeliverInterface>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.TransferDishesPresenter$actionGo$1$$special$$inlined$changeMovementTask$1
                };
                Class<? super DeliverInterface> rawType = typeToken.getRawType();
                Pdlog.m3275i("Behavior", "change task=" + rawType + ' ' + typeToken.getType() + ' ' + typeToken.getClass() + ' ' + typeToken);
                if (!behavior.getInitDone()) {
                    Pdlog.m3274e("Behavior", "not init yet");
                } else {
                    DelayResumeActive.INSTANCE.cancelTask();
                    if (Intrinsics.areEqual(rawType, CruiseInterface.class)) {
                        behavior.setMovementTask(new CruiseTask());
                    } else if (Intrinsics.areEqual(rawType, DeliverInterface.class)) {
                        behavior.setMovementTask(new DeliverTask());
                    } else if (Intrinsics.areEqual(rawType, GoHomeInterface.class)) {
                        behavior.setMovementTask(new GoHomeTask());
                    } else if (Intrinsics.areEqual(rawType, HangOutInterface.class)) {
                        behavior.setMovementTask(new HangoutTask());
                    } else if (Intrinsics.areEqual(rawType, RecycleInterface.class)) {
                        behavior.setMovementTask(new RecycleTask());
                    } else if (Intrinsics.areEqual(rawType, TempMoveInterface.class)) {
                        behavior.setMovementTask(new TempTask());
                    } else if (Intrinsics.areEqual(rawType, IdleInterface.class)) {
                        behavior.setMovementTask(new IdleTask());
                    } else {
                        Pdlog.m3274e("Behavior", rawType + " not supported");
                    }
                    Pdlog.m3275i("Behavior", "change task done");
                    BaseTaskInterface movementTask = behavior.getMovementTask();
                    if (movementTask == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.business.movementInterface.DeliverInterface");
                    }
                    deliverInterface3 = (DeliverInterface) movementTask;
                }
                transferDishesPresenter.deliverInterface = deliverInterface3;
                deliverInterface = TransferDishesPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.setCallback(TransferDishesPresenter.this);
                }
                deliverInterface2 = TransferDishesPresenter.this.deliverInterface;
                if (deliverInterface2 != null) {
                    DeliverInterface.DefaultImpls.setDestinations$default(deliverInterface2, arrayList4, null, null, 6, null);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.TransferDishesContract.PresenterInterface
    public void actionActive() {
        Pdlog.m3275i(getTAG(), "actionActive");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.TransferDishesPresenter$actionActive$1
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
                DeliverInterface deliverInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                deliverInterface = TransferDishesPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.setActive(true);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.TransferDishesContract.PresenterInterface
    public void actionPause() {
        Pdlog.m3275i(getTAG(), "actionPause");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.TransferDishesPresenter$actionPause$1
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
                DeliverInterface deliverInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                deliverInterface = TransferDishesPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.pause(BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms());
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.TransferDishesContract.PresenterInterface
    public void actionFinish() {
        Pdlog.m3275i(getTAG(), "actionFinish ");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.TransferDishesPresenter$actionFinish$1
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
                DeliverInterface deliverInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                deliverInterface = TransferDishesPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.setActive(true);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.TransferDishesContract.PresenterInterface
    public void actionPauseNoTimer() {
        Pdlog.m3275i(getTAG(), "actionPauseNoTimer");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.TransferDishesPresenter$actionPauseNoTimer$1
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
                DeliverInterface deliverInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(true);
                deliverInterface = TransferDishesPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.setActive(false);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.TransferDishesContract.PresenterInterface
    public void actionCancelTask() {
        Pdlog.m3275i(getTAG(), "actionCancelTask");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.TransferDishesPresenter$actionCancelTask$1
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
                DeliverInterface deliverInterface;
                String str;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                deliverInterface = TransferDishesPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    str = TransferDishesPresenter.this.destination;
                    deliverInterface.set(str, TaskStatus.CANCEL);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnUI(final TransferDishesContract.TransferEvent event) {
        final TransferDishesContract.DestinationType destinationType = this.type;
        if (destinationType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("type");
        }
        final String str = this.destination;
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.TransferDishesPresenter$showOnUI$1
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
                TransferDishesContract.ViewInterface theView;
                theView = TransferDishesPresenter.this.getTheView();
                if (theView != null) {
                    theView.showTransferEvent(destinationType, str, event);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.DeliverCallback
    public void onStatusChanged(String destination, TaskStatus status, double range) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(status, "status");
        Pdlog.m3275i(getTAG(), "onStatusChanged destination=" + destination + "  status=" + status);
        this.destination = destination;
        this.taskStatus = status;
        int i = WhenMappings.$EnumSwitchMapping$0[status.ordinal()];
        if (i != 1) {
            if (i == 2) {
                showOnUI(TransferDishesContract.TransferEvent.ON_THE_WAY);
            } else if (i == 3) {
                showOnUI(TransferDishesContract.TransferEvent.ARRIVAL);
                this.autoMoveActive = false;
            } else if (i == 4 || i == 5) {
                showOnUI(TransferDishesContract.TransferEvent.DONE);
            }
        }
        controlNoticeVIP(destination, status);
    }

    private final void controlNoticeVIP(String destination, TaskStatus status) {
        int i = WhenMappings.$EnumSwitchMapping$1[status.ordinal()];
        if (i != 1) {
            if (i == 2 || i == 3 || i == 4) {
                Peripherals.INSTANCE.getLora().stopNoticeVIP();
                return;
            }
            return;
        }
        LoRaNoticeTask lora = Peripherals.INSTANCE.getLora();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {destination};
        String format = String.format(ResUtil.INSTANCE.getString("pdStr2_31"), Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        lora.noticeVIP(format);
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onTaskSetup(boolean isLegalTask, String info) {
        DeliverInterface deliverInterface;
        Intrinsics.checkParameterIsNotNull(info, "info");
        Pdlog.m3275i(getTAG(), "isLegalTask=" + isLegalTask + ' ' + info);
        if (!isLegalTask || (deliverInterface = this.deliverInterface) == null) {
            return;
        }
        deliverInterface.setActive(true);
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onMovementChanged(RobotState state, String description) {
        String tag = getTAG();
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("movement changed state: ");
        sb.append(state != null ? state.name() : null);
        sb.append(" autoMoveActive=");
        sb.append(this.autoMoveActive);
        objArr[0] = sb.toString();
        Pdlog.m3273d(tag, objArr);
        boolean checkActive = state != null ? this.activeModel.checkActive(state) : false;
        if (Intrinsics.areEqual(Boolean.valueOf(checkActive), this.autoMoveActive)) {
            return;
        }
        this.autoMoveActive = Boolean.valueOf(checkActive);
        if ((state == RobotState.Arrive || state == RobotState.Idle) && !checkActive) {
            return;
        }
        showOnUI(checkActive ? TransferDishesContract.TransferEvent.ACTIVE : TransferDishesContract.TransferEvent.PAUSE);
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onDone() {
        showOnUI(TransferDishesContract.TransferEvent.DONE);
    }
}
