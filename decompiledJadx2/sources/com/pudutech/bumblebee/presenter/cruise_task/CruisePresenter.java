package com.pudutech.bumblebee.presenter.cruise_task;

import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.TimerThread;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.business.behavior.DelayResumeActive;
import com.pudutech.bumblebee.business.movementCallback.CruiseCallback;
import com.pudutech.bumblebee.business.movementInterface.BaseTaskInterface;
import com.pudutech.bumblebee.business.movementInterface.CruiseInterface;
import com.pudutech.bumblebee.business.movementInterface.DeliverInterface;
import com.pudutech.bumblebee.business.movementInterface.GoHomeInterface;
import com.pudutech.bumblebee.business.movementInterface.HangOutInterface;
import com.pudutech.bumblebee.business.movementInterface.IdleInterface;
import com.pudutech.bumblebee.business.movementInterface.RecycleInterface;
import com.pudutech.bumblebee.business.movementInterface.TempMoveInterface;
import com.pudutech.bumblebee.business.movementTask.CruiseTask;
import com.pudutech.bumblebee.business.movementTask.DeliverTask;
import com.pudutech.bumblebee.business.movementTask.GoHomeTask;
import com.pudutech.bumblebee.business.movementTask.HangoutTask;
import com.pudutech.bumblebee.business.movementTask.IdleTask;
import com.pudutech.bumblebee.business.movementTask.RecycleTask;
import com.pudutech.bumblebee.business.movementTask.TempTask;
import com.pudutech.bumblebee.business.peripherals_task.Peripherals;
import com.pudutech.bumblebee.business.utils.ActiveModel;
import com.pudutech.bumblebee.business.utils.FactoryTestHelper;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.cruise_task.CruiseContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.performance.MovePerformance;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportCruise;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CruisePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0014H\u0016J&\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0014H\u0016J\b\u0010\u001e\u001a\u00020\u0014H\u0016J\u0006\u0010\u001f\u001a\u00020\rJ\u001c\u0010 \u001a\u00020\u00142\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010$\u001a\u00020\u0014H\u0016J\b\u0010%\u001a\u00020\u0014H\u0002J\u0010\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020(H\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/cruise_task/CruisePresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseContract$PresenterInterface;", "Lcom/pudutech/bumblebee/business/movementCallback/CruiseCallback;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "activeModel", "Lcom/pudutech/bumblebee/business/utils/ActiveModel;", "autoMoveActive", "", "Ljava/lang/Boolean;", "cruiseInterface", "Lcom/pudutech/bumblebee/business/movementInterface/CruiseInterface;", "delayRunnable", "Ljava/lang/Runnable;", "actionActive", "", "actionCancelTask", "actionCruise", "id", "", "performance", "Lcom/pudutech/bumblebee/presenter/performance/MovePerformance;", "stops", "", "actionPause", "actionPauseNoTimer", "checkActive", "onMovementChanged", "state", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "description", "onTaskSetup", "setDelayAutoDone", "showOnUIThread", "event", "Lcom/pudutech/bumblebee/presenter/cruise_task/CruiseContract$ViewEvent;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CruisePresenter extends BaseOneViewPresenter<CruiseContract.ViewInterface> implements CruiseContract.PresenterInterface, CruiseCallback {
    private Boolean autoMoveActive;
    private CruiseInterface cruiseInterface;
    private final String TAG = "CruisePresenter";
    private final ActiveModel activeModel = new ActiveModel();
    private Runnable delayRunnable = new Runnable() { // from class: com.pudutech.bumblebee.presenter.cruise_task.CruisePresenter$delayRunnable$1
        @Override // java.lang.Runnable
        public final void run() {
            Pdlog.m3275i(CruisePresenter.this.getTAG(), "delay auto done");
            CruisePresenter.this.actionActive();
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.CruiseCallback
    public void onTaskSetup() {
        Pdlog.m3273d(getTAG(), "onTaskSetup");
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.CruiseCallback
    public void onMovementChanged(RobotState state, String description) {
        String tag = getTAG();
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("view ");
        sb.append(getTheView());
        sb.append(" state ");
        sb.append(state != null ? state.name() : null);
        sb.append(" autoMoveActive=");
        sb.append(this.autoMoveActive);
        objArr[0] = sb.toString();
        Pdlog.m3273d(tag, objArr);
        if (state != null) {
            boolean checkActive = this.activeModel.checkActive(state);
            if (Intrinsics.areEqual(Boolean.valueOf(checkActive), this.autoMoveActive)) {
                return;
            }
            if (state == RobotState.Arrive) {
                showOnUIThread(CruiseContract.ViewEvent.ARRIVAL_STOP_DOT);
                this.autoMoveActive = Boolean.valueOf(checkActive);
                return;
            }
            if (!checkActive && state != RobotState.Arrive) {
                showOnUIThread(CruiseContract.ViewEvent.PAUSE);
                ReportCruise.INSTANCE.setAutoMove(false);
                this.autoMoveActive = Boolean.valueOf(checkActive);
            } else if (checkActive) {
                showOnUIThread(CruiseContract.ViewEvent.ACTIVE);
                ReportCruise.INSTANCE.setAutoMove(true);
                this.autoMoveActive = Boolean.valueOf(checkActive);
            } else {
                Pdlog.m3277w(getTAG(), state + " shouldn't show on cruise mode");
            }
        }
    }

    @Override // com.pudutech.bumblebee.presenter.cruise_task.CruiseContract.PresenterInterface
    public void actionCruise(final int id, final MovePerformance performance, final List<String> stops) {
        Intrinsics.checkParameterIsNotNull(performance, "performance");
        Intrinsics.checkParameterIsNotNull(stops, "stops");
        Pdlog.m3275i(getTAG(), "actionCruise id=" + id);
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.cruise_task.CruisePresenter$actionCruise$1
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
                CruiseInterface cruiseInterface;
                CruiseInterface cruiseInterface2;
                CruiseInterface cruiseInterface3;
                CruiseInterface cruiseInterface4 = null;
                CruisePresenter.this.autoMoveActive = (Boolean) null;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                CruisePresenter cruisePresenter = CruisePresenter.this;
                Behavior behavior = Behavior.INSTANCE;
                TypeToken<CruiseInterface> typeToken = new TypeToken<CruiseInterface>() { // from class: com.pudutech.bumblebee.presenter.cruise_task.CruisePresenter$actionCruise$1$$special$$inlined$changeMovementTask$1
                };
                Class<? super CruiseInterface> rawType = typeToken.getRawType();
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
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.business.movementInterface.CruiseInterface");
                    }
                    cruiseInterface4 = (CruiseInterface) movementTask;
                }
                cruisePresenter.cruiseInterface = cruiseInterface4;
                cruiseInterface = CruisePresenter.this.cruiseInterface;
                if (cruiseInterface != null) {
                    cruiseInterface.setCallback(CruisePresenter.this);
                }
                if (performance == MovePerformance.STEADY) {
                    cruiseInterface3 = CruisePresenter.this.cruiseInterface;
                    if (cruiseInterface3 != null) {
                        cruiseInterface3.setPath(id, MoveTaskMode.Steady, stops);
                    }
                } else {
                    cruiseInterface2 = CruisePresenter.this.cruiseInterface;
                    if (cruiseInterface2 != null) {
                        CruiseInterface.DefaultImpls.setPath$default(cruiseInterface2, id, null, stops, 2, null);
                    }
                }
                ReportCruise.INSTANCE.createNewTask();
                FactoryTestHelper.INSTANCE.recordCruiseStart();
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.cruise_task.CruiseContract.PresenterInterface
    public void actionActive() {
        Pdlog.m3275i(getTAG(), "actionActive");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.cruise_task.CruisePresenter$actionActive$1
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
                CruiseInterface cruiseInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                cruiseInterface = CruisePresenter.this.cruiseInterface;
                if (cruiseInterface != null) {
                    cruiseInterface.setActive(true);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.cruise_task.CruiseContract.PresenterInterface
    public void actionPause() {
        Pdlog.m3275i(getTAG(), "actionPause");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.cruise_task.CruisePresenter$actionPause$1
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
                CruiseInterface cruiseInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                cruiseInterface = CruisePresenter.this.cruiseInterface;
                if (cruiseInterface != null) {
                    cruiseInterface.pause(BusinessSetting.INSTANCE.getCruisePauseKeepTime_ms());
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.cruise_task.CruiseContract.PresenterInterface
    public void actionPauseNoTimer() {
        Pdlog.m3275i(getTAG(), "actionPauseNoTimer");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.cruise_task.CruisePresenter$actionPauseNoTimer$1
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
                CruiseInterface cruiseInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(true);
                cruiseInterface = CruisePresenter.this.cruiseInterface;
                if (cruiseInterface != null) {
                    cruiseInterface.setActive(false);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.cruise_task.CruiseContract.PresenterInterface
    public void actionCancelTask() {
        Pdlog.m3275i(getTAG(), "actionCancelTask");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.cruise_task.CruisePresenter$actionCancelTask$1
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
                CruiseInterface cruiseInterface;
                Runnable runnable;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                cruiseInterface = CruisePresenter.this.cruiseInterface;
                if (cruiseInterface != null) {
                    cruiseInterface.setActive(false);
                }
                TimerThread timerThread = TimerThread.INSTANCE;
                runnable = CruisePresenter.this.delayRunnable;
                timerThread.remove(runnable);
                CruisePresenter.this.cruiseInterface = (CruiseInterface) null;
                CruisePresenter.this.showOnUIThread(CruiseContract.ViewEvent.EXIT);
                ReportCruise.INSTANCE.finish();
                FactoryTestHelper.INSTANCE.recordCruiseEnd();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnUIThread(final CruiseContract.ViewEvent event) {
        Pdlog.m3273d(getTAG(), "showOnUI event=" + event + " view=" + getTheView());
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.cruise_task.CruisePresenter$showOnUIThread$1
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
                CruiseContract.ViewInterface theView;
                theView = CruisePresenter.this.getTheView();
                if (theView != null) {
                    theView.showCruiseEvent(event);
                }
            }
        });
    }

    private final void setDelayAutoDone() {
        Pdlog.m3273d(getTAG(), "set delayAutoDone " + CruiseConfig.INSTANCE.getDelayAutoFinish_ms());
        TimerThread.INSTANCE.remove(this.delayRunnable);
        TimerThread.INSTANCE.post(this.delayRunnable, CruiseConfig.INSTANCE.getDelayAutoFinish_ms());
    }

    public final boolean checkActive() {
        CruiseInterface cruiseInterface = this.cruiseInterface;
        if (!(cruiseInterface instanceof CruiseTask)) {
            return true;
        }
        if (cruiseInterface != null) {
            return ((CruiseTask) cruiseInterface).getIsActive();
        }
        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.business.movementTask.CruiseTask");
    }
}
