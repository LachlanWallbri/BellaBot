package com.pudutech.bumblebee.presenter.greeter_task;

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
import com.pudutech.bumblebee.business.utils.ActiveModel2;
import com.pudutech.bumblebee.business.utils.FactoryTestHelper;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.greeter_task.GreeterContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.report_cloud.protocol.delivery.TaskType;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportDelivery;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* compiled from: GreeterPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J\b\u0010\u0016\u001a\u00020\u0014H\u0016J\b\u0010\u0017\u001a\u00020\u0014H\u0016J \u0010\u0018\u001a\u00020\u00142\u0016\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u001aj\b\u0012\u0004\u0012\u00020\u0007`\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0014H\u0016J\b\u0010\u001d\u001a\u0004\u0018\u00010\u0007J\b\u0010\u001e\u001a\u00020\u0014H\u0016J\u001c\u0010\u001f\u001a\u00020\u00142\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010\u0007H\u0016J \u0010#\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H\u0016J\u0018\u0010(\u001a\u00020\u00142\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0007H\u0016J\b\u0010,\u001a\u00020\u0014H\u0014J\u0018\u0010-\u001a\u00020\u00142\u0006\u0010.\u001a\u00020\u00072\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010/\u001a\u00020\u00142\u0006\u00100\u001a\u000201H\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00062"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/greeter_task/GreeterPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/greeter_task/GreeterContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/greeter_task/GreeterContract$Presenter;", "Lcom/pudutech/bumblebee/business/movementCallback/DeliverCallback;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "activeModel", "Lcom/pudutech/bumblebee/business/utils/ActiveModel2;", "delayAutoJob", "Lkotlinx/coroutines/Job;", "deliverInterface", "Lcom/pudutech/bumblebee/business/movementInterface/DeliverInterface;", "destination", "destinationList", "", "actionActive", "", "actionCancelTask", "actionPause", "actionPauseNoTimer", "actionUsher", "desList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "actionUsherDone", "getCurrentDestination", "onDone", "onMovementChanged", "state", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "description", "onStatusChanged", "status", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "range", "", "onTaskSetup", "isLegalTask", "", "info", "onViewRemoved", "reportToCloud", "des", "showEvent", "event", "Lcom/pudutech/bumblebee/presenter/greeter_task/GreeterContract$ViewEvent;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class GreeterPresenter extends BaseOneViewPresenter<GreeterContract.ViewInterface> implements GreeterContract.Presenter, DeliverCallback {
    private final String TAG = "GreeterPresenter";
    private final ActiveModel2 activeModel;
    private Job delayAutoJob;
    private DeliverInterface deliverInterface;
    private String destination;
    private List<String> destinationList;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TaskStatus.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[TaskStatus.AWAIT.ordinal()] = 1;
            $EnumSwitchMapping$0[TaskStatus.ON_THE_WAY.ordinal()] = 2;
            $EnumSwitchMapping$0[TaskStatus.ARRIVAL.ordinal()] = 3;
            $EnumSwitchMapping$0[TaskStatus.CANCEL.ordinal()] = 4;
            $EnumSwitchMapping$0[TaskStatus.DONE.ordinal()] = 5;
            $EnumSwitchMapping$1 = new int[TaskStatus.values().length];
            $EnumSwitchMapping$1[TaskStatus.ON_THE_WAY.ordinal()] = 1;
            $EnumSwitchMapping$1[TaskStatus.ARRIVAL.ordinal()] = 2;
            $EnumSwitchMapping$1[TaskStatus.DONE.ordinal()] = 3;
            $EnumSwitchMapping$1[TaskStatus.DONE_BEFORE_ARRIVAL.ordinal()] = 4;
            $EnumSwitchMapping$1[TaskStatus.CANCEL.ordinal()] = 5;
        }
    }

    public GreeterPresenter() {
        ActiveModel2 activeModel2 = new ActiveModel2();
        activeModel2.setNoNeedPauseShowScene(RobotState.Arrive, RobotState.Idle);
        this.activeModel = activeModel2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showEvent(final GreeterContract.ViewEvent event) {
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.greeter_task.GreeterPresenter$showEvent$1
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
                GreeterContract.ViewInterface theView;
                String str;
                theView = GreeterPresenter.this.getTheView();
                if (theView != null) {
                    str = GreeterPresenter.this.destination;
                    if (str == null) {
                        str = "";
                    }
                    theView.showMovingEvent(str, event);
                }
            }
        });
    }

    /* renamed from: getCurrentDestination, reason: from getter */
    public final String getDestination() {
        return this.destination;
    }

    @Override // com.pudutech.bumblebee.presenter.greeter_task.GreeterContract.Presenter
    public void actionUsher(final ArrayList<String> desList) {
        Intrinsics.checkParameterIsNotNull(desList, "desList");
        Pdlog.m3275i(getTAG(), "actionUsher des=" + desList);
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.greeter_task.GreeterPresenter$actionUsher$1
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

            /* JADX WARN: Removed duplicated region for block: B:25:0x018c  */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void invoke2() {
                Job job;
                DeliverInterface deliverInterface;
                DeliverInterface deliverInterface2;
                ReportDelivery.INSTANCE.toCloud();
                ReportDelivery.INSTANCE.createNewDelivery();
                job = GreeterPresenter.this.delayAutoJob;
                DeliverInterface deliverInterface3 = null;
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                Iterator it = desList.iterator();
                boolean z = true;
                while (it.hasNext()) {
                    if (!RobotMapManager.INSTANCE.checkDestinationExist((String) it.next())) {
                        z = false;
                    }
                }
                if (z) {
                    GreeterPresenter.this.destinationList = desList;
                    Peripherals.INSTANCE.getFunctionButton().setMute(false);
                    GreeterPresenter greeterPresenter = GreeterPresenter.this;
                    Behavior behavior = Behavior.INSTANCE;
                    TypeToken<DeliverInterface> typeToken = new TypeToken<DeliverInterface>() { // from class: com.pudutech.bumblebee.presenter.greeter_task.GreeterPresenter$actionUsher$1$$special$$inlined$changeMovementTask$1
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
                        deliverInterface = (DeliverInterface) movementTask;
                        deliverInterface2 = deliverInterface;
                        if (deliverInterface2 != null) {
                            deliverInterface2.setCallback(GreeterPresenter.this);
                            DeliverInterface.DefaultImpls.setDestinations$default(deliverInterface2, desList, null, null, 6, null);
                            deliverInterface2.setActive(true);
                            deliverInterface3 = deliverInterface2;
                        }
                        greeterPresenter.deliverInterface = deliverInterface3;
                        return;
                    }
                    deliverInterface = null;
                    deliverInterface2 = deliverInterface;
                    if (deliverInterface2 != null) {
                    }
                    greeterPresenter.deliverInterface = deliverInterface3;
                    return;
                }
                Pdlog.m3273d(GreeterPresenter.this.getTAG(), "couldn't find the des=" + desList);
                GreeterPresenter.this.showEvent(GreeterContract.ViewEvent.NOT_FIND_TARGET);
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.greeter_task.GreeterContract.Presenter
    public void actionUsherDone() {
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.greeter_task.GreeterPresenter$actionUsherDone$1
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
                deliverInterface = GreeterPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.setActive(true);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public void onViewRemoved() {
        super.onViewRemoved();
        Job job = this.delayAutoJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    @Override // com.pudutech.bumblebee.presenter.greeter_task.GreeterContract.Presenter
    public void actionActive() {
        Pdlog.m3275i(getTAG(), "actionActive ");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.greeter_task.GreeterPresenter$actionActive$1
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
                deliverInterface = GreeterPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.setActive(true);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.greeter_task.GreeterContract.Presenter
    public void actionPause() {
        Pdlog.m3275i(getTAG(), "actionPause ");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.greeter_task.GreeterPresenter$actionPause$1
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
                deliverInterface = GreeterPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.pause(BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms());
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.greeter_task.GreeterContract.Presenter
    public void actionPauseNoTimer() {
        Pdlog.m3275i(getTAG(), "actionPauseNoTimer");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.greeter_task.GreeterPresenter$actionPauseNoTimer$1
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
                deliverInterface = GreeterPresenter.this.deliverInterface;
                if (deliverInterface != null) {
                    deliverInterface.setActive(false);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.greeter_task.GreeterContract.Presenter
    public void actionCancelTask() {
        Pdlog.m3275i(getTAG(), "actionFinishCurrentTask");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.greeter_task.GreeterPresenter$actionCancelTask$1
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
                Job job;
                List<String> list;
                DeliverInterface deliverInterface;
                job = GreeterPresenter.this.delayAutoJob;
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                list = GreeterPresenter.this.destinationList;
                if (list != null) {
                    for (String str : list) {
                        deliverInterface = GreeterPresenter.this.deliverInterface;
                        if (deliverInterface != null) {
                            deliverInterface.set(str, TaskStatus.CANCEL);
                        }
                    }
                }
                GreeterPresenter.this.deliverInterface = (DeliverInterface) null;
            }
        });
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.DeliverCallback
    public void onStatusChanged(String destination, TaskStatus status, double range) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(status, "status");
        Pdlog.m3273d(getTAG(), "onStatusChanged destination=" + destination + "  status=" + status + "  range=" + range);
        this.destination = destination;
        int i = WhenMappings.$EnumSwitchMapping$0[status.ordinal()];
        if (i != 1) {
            if (i == 2) {
                showEvent(GreeterContract.ViewEvent.ON_THE_WAY);
            } else if (i == 3) {
                launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new GreeterPresenter$onStatusChanged$1(this, null), 3, null);
                this.delayAutoJob = launch$default;
                showEvent(GreeterContract.ViewEvent.ARRIVAL_DESTINATION);
            } else if (i == 4) {
                showEvent(GreeterContract.ViewEvent.CANCEL);
                Job job = this.delayAutoJob;
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
            } else if (i == 5) {
                showEvent(GreeterContract.ViewEvent.ARRIVAL_DESTINATION_DONE);
                Job job2 = this.delayAutoJob;
                if (job2 != null) {
                    Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
                }
            }
        }
        reportToCloud(destination, status);
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onDone() {
        Pdlog.m3273d(getTAG(), "onDone ");
        actionCancelTask();
        actionUsherDone();
        showEvent(GreeterContract.ViewEvent.DONE_ALL);
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onTaskSetup(boolean isLegalTask, String info) {
        Intrinsics.checkParameterIsNotNull(info, "info");
        Pdlog.m3273d(getTAG(), "onTaskSetup isLegalTask=" + isLegalTask + "  info=" + info);
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onMovementChanged(RobotState state, String description) {
        String tag = getTAG();
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("onMovementChanged state=");
        sb.append(state != null ? state.name() : null);
        sb.append("  description=");
        sb.append(description);
        objArr[0] = sb.toString();
        Pdlog.m3273d(tag, objArr);
        if (state != null) {
            ReportDelivery.INSTANCE.setAutoMove((state == RobotState.Pause || state == RobotState.Error) ? false : true);
            Pair<Boolean, Boolean> updateState = this.activeModel.updateState(state);
            if (updateState.getFirst().booleanValue()) {
                showEvent(updateState.getSecond().booleanValue() ? GreeterContract.ViewEvent.ACTIVE : GreeterContract.ViewEvent.PAUSE);
            }
        }
    }

    private final void reportToCloud(String des, TaskStatus status) {
        int i = WhenMappings.$EnumSwitchMapping$1[status.ordinal()];
        if (i == 1) {
            ReportDelivery.INSTANCE.goNext(des, TaskType.SEAT_GUEST, new ArrayList<>());
            return;
        }
        if (i == 2) {
            FactoryTestHelper.INSTANCE.recordDelivery();
            ReportDelivery.INSTANCE.markTaking();
        } else if (i == 3 || i == 4) {
            ReportDelivery.INSTANCE.setCurrentTaskDoneOrCancel(true);
            ReportDelivery.INSTANCE.toCloud();
        } else {
            if (i != 5) {
                return;
            }
            ReportDelivery.INSTANCE.setCurrentTaskDoneOrCancel(false);
            ReportDelivery.INSTANCE.toCloud();
        }
    }
}
