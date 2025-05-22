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
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointContract;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportDelivery;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import com.pudutech.resources.resource.ResUtil;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

/* compiled from: ToRecyclingPointPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\b\u0010\u0017\u001a\u00020\u0015H\u0016J\b\u0010\u0018\u001a\u00020\u0015H\u0016J\b\u0010\u0019\u001a\u00020\u0015H\u0016J\b\u0010\u001a\u001a\u00020\u0015H\u0016J\b\u0010\u001b\u001a\u00020\u0015H\u0016J\u0018\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u0013H\u0002J\b\u0010\u001e\u001a\u00020\u0015H\u0016J\u001c\u0010\u001f\u001a\u00020\u00152\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010\u0007H\u0016J \u0010#\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010$\u001a\u00020%H\u0016J\u0018\u0010&\u001a\u00020\u00152\u0006\u0010'\u001a\u00020\r2\u0006\u0010(\u001a\u00020\u0007H\u0016J\u0010\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020+H\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/recycle_task/ToRecyclingPointPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/recycle_task/ToRecyclingPointContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/recycle_task/ToRecyclingPointContract$PresenterInterface;", "Lcom/pudutech/bumblebee/business/movementCallback/DeliverCallback;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "activeModel", "Lcom/pudutech/bumblebee/business/utils/ActiveModel;", "autoMoveActive", "", "Ljava/lang/Boolean;", "deliverInterface", "Lcom/pudutech/bumblebee/business/movementInterface/RecycleInterface;", "destination", "taskStatus", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "actionActive", "", "actionArrived", "actionCancelTask", "actionFinish", "actionGo", "actionPause", "actionPauseNoTimer", "controlNoticeVIP", "status", "onDone", "onMovementChanged", "state", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "description", "onStatusChanged", "range", "", "onTaskSetup", "isLegalTask", "info", "showOnUI", "event", "Lcom/pudutech/bumblebee/presenter/recycle_task/ToRecyclingPointContract$TransferEvent;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class ToRecyclingPointPresenter extends BaseOneViewPresenter<ToRecyclingPointContract.ViewInterface> implements ToRecyclingPointContract.PresenterInterface, DeliverCallback {
    private Boolean autoMoveActive;
    private RecycleInterface deliverInterface;
    private TaskStatus taskStatus;
    private final String TAG = "TransferDishesPresenter";
    private String destination = "";
    private final ActiveModel activeModel = new ActiveModel();

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[TaskStatus.AWAIT.ordinal()] = 1;
            $EnumSwitchMapping$0[TaskStatus.APPROACHING.ordinal()] = 2;
            $EnumSwitchMapping$0[TaskStatus.ON_THE_WAY.ordinal()] = 3;
            $EnumSwitchMapping$0[TaskStatus.ARRIVAL.ordinal()] = 4;
            $EnumSwitchMapping$0[TaskStatus.CANCEL.ordinal()] = 5;
            $EnumSwitchMapping$0[TaskStatus.DONE.ordinal()] = 6;
            $EnumSwitchMapping$1 = new int[TaskStatus.values().length];
            $EnumSwitchMapping$1[TaskStatus.APPROACHING.ordinal()] = 1;
            $EnumSwitchMapping$1[TaskStatus.DONE.ordinal()] = 2;
            $EnumSwitchMapping$1[TaskStatus.CANCEL.ordinal()] = 3;
            $EnumSwitchMapping$1[TaskStatus.DONE_BEFORE_ARRIVAL.ordinal()] = 4;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointContract.PresenterInterface
    public void actionGo() {
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointPresenter$actionGo$1
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
                RecycleInterface recycleInterface;
                RecycleInterface recycleInterface2;
                ReportDelivery.INSTANCE.toCloud();
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                Peripherals.INSTANCE.getFunctionButton().setRecyclePauseKeepTime_ms(Constant.INSTANCE.getRecyclePauseTime());
                RecycleInterface recycleInterface3 = null;
                ToRecyclingPointPresenter.this.autoMoveActive = (Boolean) null;
                ArrayList arrayList = new ArrayList();
                String dashWashChosen = RobotMapManager.INSTANCE.getDashWashChosen();
                String str = dashWashChosen;
                if (!(str == null || StringsKt.isBlank(str))) {
                    arrayList.add(dashWashChosen);
                }
                if (arrayList.size() == 0) {
                    Pdlog.m3277w(ToRecyclingPointPresenter.this.getTAG(), "no recycle point in map");
                    ToRecyclingPointPresenter.this.showOnUI(ToRecyclingPointContract.TransferEvent.NO_DESTINATION);
                    return;
                }
                ToRecyclingPointPresenter toRecyclingPointPresenter = ToRecyclingPointPresenter.this;
                Behavior behavior = Behavior.INSTANCE;
                TypeToken<RecycleInterface> typeToken = new TypeToken<RecycleInterface>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointPresenter$actionGo$1$$special$$inlined$changeMovementTask$1
                };
                Class<? super RecycleInterface> rawType = typeToken.getRawType();
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
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.business.movementInterface.RecycleInterface");
                    }
                    recycleInterface3 = (RecycleInterface) movementTask;
                }
                toRecyclingPointPresenter.deliverInterface = recycleInterface3;
                recycleInterface = ToRecyclingPointPresenter.this.deliverInterface;
                if (recycleInterface != null) {
                    recycleInterface.setCallback(ToRecyclingPointPresenter.this);
                }
                recycleInterface2 = ToRecyclingPointPresenter.this.deliverInterface;
                if (recycleInterface2 != null) {
                    RecycleInterface.DefaultImpls.setDestinations$default(recycleInterface2, arrayList, null, null, 6, null);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointContract.PresenterInterface
    public void actionActive() {
        Pdlog.m3275i(getTAG(), "actionActive");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointPresenter$actionActive$1
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
                RecycleInterface recycleInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                recycleInterface = ToRecyclingPointPresenter.this.deliverInterface;
                if (recycleInterface != null) {
                    recycleInterface.setActive(true);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointContract.PresenterInterface
    public void actionArrived() {
        Pdlog.m3275i(getTAG(), "actionArrived");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointPresenter$actionArrived$1
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
                RecycleInterface recycleInterface;
                RecycleInterface recycleInterface2;
                String str;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                recycleInterface = ToRecyclingPointPresenter.this.deliverInterface;
                if (recycleInterface != null) {
                    str = ToRecyclingPointPresenter.this.destination;
                    recycleInterface.set(str, TaskStatus.ARRIVAL);
                }
                recycleInterface2 = ToRecyclingPointPresenter.this.deliverInterface;
                if (recycleInterface2 != null) {
                    recycleInterface2.setActive(false);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointContract.PresenterInterface
    public void actionPause() {
        Pdlog.m3275i(getTAG(), "actionPause");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointPresenter$actionPause$1
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
                RecycleInterface recycleInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                recycleInterface = ToRecyclingPointPresenter.this.deliverInterface;
                if (recycleInterface != null) {
                    recycleInterface.pause(Constant.INSTANCE.getRecyclePauseTime());
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointContract.PresenterInterface
    public void actionFinish() {
        Pdlog.m3275i(getTAG(), "actionFinish ");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointPresenter$actionFinish$1
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
                RecycleInterface recycleInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                recycleInterface = ToRecyclingPointPresenter.this.deliverInterface;
                if (recycleInterface != null) {
                    recycleInterface.setActive(true);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointContract.PresenterInterface
    public void actionPauseNoTimer() {
        Pdlog.m3275i(getTAG(), "actionPauseNoTimer");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointPresenter$actionPauseNoTimer$1
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
                RecycleInterface recycleInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(true);
                recycleInterface = ToRecyclingPointPresenter.this.deliverInterface;
                if (recycleInterface != null) {
                    recycleInterface.setActive(false);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointContract.PresenterInterface
    public void actionCancelTask() {
        Pdlog.m3275i(getTAG(), "actionCancelTask");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointPresenter$actionCancelTask$1
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
                RecycleInterface recycleInterface;
                String str;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                recycleInterface = ToRecyclingPointPresenter.this.deliverInterface;
                if (recycleInterface != null) {
                    str = ToRecyclingPointPresenter.this.destination;
                    recycleInterface.set(str, TaskStatus.CANCEL);
                }
                ToRecyclingPointPresenter.this.deliverInterface = (RecycleInterface) null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnUI(final ToRecyclingPointContract.TransferEvent event) {
        final String str = this.destination;
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.recycle_task.ToRecyclingPointPresenter$showOnUI$1
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
                ToRecyclingPointContract.ViewInterface theView;
                theView = ToRecyclingPointPresenter.this.getTheView();
                if (theView != null) {
                    theView.showTransferEvent(str, event);
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
        switch (status) {
            case APPROACHING:
                showOnUI(ToRecyclingPointContract.TransferEvent.APPROACHING);
                break;
            case ON_THE_WAY:
                showOnUI(ToRecyclingPointContract.TransferEvent.ON_THE_WAY);
                break;
            case ARRIVAL:
                showOnUI(ToRecyclingPointContract.TransferEvent.ARRIVAL);
                this.autoMoveActive = false;
                break;
            case DONE:
                showOnUI(ToRecyclingPointContract.TransferEvent.DONE);
                break;
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
        RecycleInterface recycleInterface;
        Intrinsics.checkParameterIsNotNull(info, "info");
        Pdlog.m3275i(getTAG(), "isLegalTask=" + isLegalTask + ' ' + info);
        if (!isLegalTask || (recycleInterface = this.deliverInterface) == null) {
            return;
        }
        recycleInterface.setActive(true);
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
        showOnUI(checkActive ? ToRecyclingPointContract.TransferEvent.ACTIVE : ToRecyclingPointContract.TransferEvent.PAUSE);
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onDone() {
        showOnUI(ToRecyclingPointContract.TransferEvent.DONE);
    }
}
