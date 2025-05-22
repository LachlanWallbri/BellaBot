package com.pudutech.bumblebee.presenter.gohome_task;

import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.business.behavior.DelayResumeActive;
import com.pudutech.bumblebee.business.movementCallback.GoHomeCallback;
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
import com.pudutech.bumblebee.business.utils.ActiveModel;
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.gohome_task.GoHomeContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.performance.MovePerformance;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportDelivery;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GoHomePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 )2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001)B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0013H\u0016J\b\u0010\u0019\u001a\u00020\u0013H\u0016J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u0013H\u0016J\u001c\u0010\u001e\u001a\u00020\u00132\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010\"\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010#\u001a\u00020\u00132\u0006\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020\u0007H\u0016J\u0010\u0010&\u001a\u00020\u00132\u0006\u0010'\u001a\u00020(H\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/gohome_task/GoHomePresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/gohome_task/GoHomeContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/gohome_task/GoHomeContract$PresenterInterface;", "Lcom/pudutech/bumblebee/business/movementCallback/GoHomeCallback;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "activeModel", "Lcom/pudutech/bumblebee/business/utils/ActiveModel;", "autoMoveActive", "", "Ljava/lang/Boolean;", "goHomeInterface", "Lcom/pudutech/bumblebee/business/movementInterface/GoHomeInterface;", "home", "actionActive", "", "actionCancelTask", "actionGoHome", "performance", "Lcom/pudutech/bumblebee/presenter/performance/MovePerformance;", "actionPause", "actionPauseNoTimer", "forReport", "status", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "onDone", "onMovementChanged", "state", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "description", "onStatusChanged", "onTaskSetup", "isLegalTask", "info", "showOnUIThread", "event", "Lcom/pudutech/bumblebee/presenter/gohome_task/GoHomeContract$ViewEvent;", "Companion", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class GoHomePresenter extends BaseOneViewPresenter<GoHomeContract.ViewInterface> implements GoHomeContract.PresenterInterface, GoHomeCallback {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean mIsFillIn;
    private Boolean autoMoveActive;
    private GoHomeInterface goHomeInterface;
    private final String TAG = "GoHomePresenter";
    private final ActiveModel activeModel = new ActiveModel();
    private String home = "";

    /* compiled from: GoHomePresenter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/gohome_task/GoHomePresenter$Companion;", "", "()V", "mIsFillIn", "", "getMIsFillIn", "()Z", "setMIsFillIn", "(Z)V", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean getMIsFillIn() {
            return GoHomePresenter.mIsFillIn;
        }

        public final void setMIsFillIn(boolean z) {
            GoHomePresenter.mIsFillIn = z;
        }
    }

    @Override // com.pudutech.bumblebee.presenter.gohome_task.GoHomeContract.PresenterInterface
    public void actionPauseNoTimer() {
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.gohome_task.GoHomePresenter$actionPauseNoTimer$1
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
                GoHomeInterface goHomeInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(true);
                goHomeInterface = GoHomePresenter.this.goHomeInterface;
                if (goHomeInterface != null) {
                    goHomeInterface.setActive(false, GoHomePresenter.INSTANCE.getMIsFillIn());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.GoHomeCallback
    public void onStatusChanged(TaskStatus status) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        Pdlog.m3273d(getTAG(), "onStatusChanged status=" + status);
        switch (status) {
            case ON_THE_WAY:
                showOnUIThread(GoHomeContract.ViewEvent.ON_THE_WAY);
                break;
            case ARRIVAL:
                showOnUIThread(GoHomeContract.ViewEvent.DONE);
                break;
            case DONE:
                showOnUIThread(GoHomeContract.ViewEvent.DONE);
                break;
            case DONE_BEFORE_ARRIVAL:
                showOnUIThread(GoHomeContract.ViewEvent.DONE);
                break;
        }
        forReport(status);
    }

    private final void forReport(TaskStatus status) {
        if (status == TaskStatus.ON_THE_WAY) {
            ReportDelivery.goHome$default(ReportDelivery.INSTANCE, this.home, null, 2, null);
        } else if (status == TaskStatus.ARRIVAL) {
            ReportDelivery.INSTANCE.setCurrentTaskDoneOrCancel(true);
            ReportDelivery.INSTANCE.toCloud();
        }
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onTaskSetup(boolean isLegalTask, String info) {
        Intrinsics.checkParameterIsNotNull(info, "info");
        Pdlog.m3273d(getTAG(), "onTaskSetup isLegalTask=" + isLegalTask + " info=" + info);
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onMovementChanged(RobotState state, String description) {
        String tag = getTAG();
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder();
        sb.append("view ");
        sb.append(getTheView());
        sb.append(" state ");
        sb.append(state != null ? state.name() : null);
        sb.append("  autoMoveActive=");
        sb.append(this.autoMoveActive);
        objArr[0] = sb.toString();
        Pdlog.m3273d(tag, objArr);
        if (state != null) {
            boolean checkActive = this.activeModel.checkActive(state);
            if (Intrinsics.areEqual(Boolean.valueOf(checkActive), this.autoMoveActive)) {
                return;
            }
            this.autoMoveActive = Boolean.valueOf(checkActive);
            if (checkActive) {
                showOnUIThread(GoHomeContract.ViewEvent.ACTIVE);
                ReportDelivery.INSTANCE.setAutoMove(true);
            } else {
                showOnUIThread(GoHomeContract.ViewEvent.PAUSE);
                ReportDelivery.INSTANCE.setAutoMove(false);
            }
        }
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onDone() {
        showOnUIThread(GoHomeContract.ViewEvent.DONE);
    }

    @Override // com.pudutech.bumblebee.presenter.gohome_task.GoHomeContract.PresenterInterface
    public void actionGoHome(final MovePerformance performance) {
        Intrinsics.checkParameterIsNotNull(performance, "performance");
        Pdlog.m3275i(getTAG(), "actionGoHome");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.gohome_task.GoHomePresenter$actionGoHome$1
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

            /* JADX WARN: Removed duplicated region for block: B:11:0x0170  */
            /* JADX WARN: Removed duplicated region for block: B:20:0x0186  */
            /* JADX WARN: Removed duplicated region for block: B:34:0x01d6  */
            /* JADX WARN: Removed duplicated region for block: B:7:0x0136  */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void invoke2() {
                GoHomeInterface goHomeInterface;
                GoHomeInterface goHomeInterface2;
                String str;
                GoHomeInterface goHomeInterface3;
                GoHomeInterface goHomeInterface4;
                GoHomeInterface goHomeInterface5;
                GoHomePresenter.this.autoMoveActive = (Boolean) null;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                GoHomePresenter goHomePresenter = GoHomePresenter.this;
                Behavior behavior = Behavior.INSTANCE;
                TypeToken<GoHomeInterface> typeToken = new TypeToken<GoHomeInterface>() { // from class: com.pudutech.bumblebee.presenter.gohome_task.GoHomePresenter$actionGoHome$1$$special$$inlined$changeMovementTask$1
                };
                Class<? super GoHomeInterface> rawType = typeToken.getRawType();
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
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.business.movementInterface.GoHomeInterface");
                    }
                    goHomeInterface = (GoHomeInterface) movementTask;
                    goHomePresenter.goHomeInterface = goHomeInterface;
                    goHomeInterface2 = GoHomePresenter.this.goHomeInterface;
                    if (goHomeInterface2 != null) {
                        goHomeInterface2.setCallback(GoHomePresenter.this);
                    }
                    ArrayList<String> dinningOutLets = RobotMapManager.INSTANCE.getDinningOutLets();
                    Pdlog.m3273d(GoHomePresenter.this.getTAG(), "actionGoHome " + dinningOutLets);
                    str = (String) null;
                    for (String str2 : dinningOutLets) {
                        if (Intrinsics.areEqual(str2, RobotMapManager.INSTANCE.getCurrentMapDiningOutLetChosen())) {
                            str = str2;
                        }
                    }
                    if (str == null) {
                        Pdlog.m3273d(GoHomePresenter.this.getTAG(), "go home " + str);
                        if (performance == MovePerformance.STEADY) {
                            goHomeInterface5 = GoHomePresenter.this.goHomeInterface;
                            if (goHomeInterface5 != null) {
                                goHomeInterface5.setHome(str, MoveTaskMode.Steady);
                            }
                        } else {
                            goHomeInterface3 = GoHomePresenter.this.goHomeInterface;
                            if (goHomeInterface3 != null) {
                                GoHomeInterface.DefaultImpls.setHome$default(goHomeInterface3, str, null, 2, null);
                            }
                        }
                        goHomeInterface4 = GoHomePresenter.this.goHomeInterface;
                        if (goHomeInterface4 != null) {
                            goHomeInterface4.setActive(true, GoHomePresenter.INSTANCE.getMIsFillIn());
                            return;
                        }
                        return;
                    }
                    Pdlog.m3277w(GoHomePresenter.this.getTAG(), "no dining outlet");
                    GoHomePresenter.this.showOnUIThread(GoHomeContract.ViewEvent.NO_DINING_OUTLET);
                    return;
                }
                goHomeInterface = null;
                goHomePresenter.goHomeInterface = goHomeInterface;
                goHomeInterface2 = GoHomePresenter.this.goHomeInterface;
                if (goHomeInterface2 != null) {
                }
                ArrayList<String> dinningOutLets2 = RobotMapManager.INSTANCE.getDinningOutLets();
                Pdlog.m3273d(GoHomePresenter.this.getTAG(), "actionGoHome " + dinningOutLets2);
                str = (String) null;
                while (r0.hasNext()) {
                }
                if (str == null) {
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.gohome_task.GoHomeContract.PresenterInterface
    public void actionActive() {
        Pdlog.m3275i(getTAG(), "actionActive");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.gohome_task.GoHomePresenter$actionActive$1
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
                GoHomeInterface goHomeInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                goHomeInterface = GoHomePresenter.this.goHomeInterface;
                if (goHomeInterface != null) {
                    goHomeInterface.setActive(true, GoHomePresenter.INSTANCE.getMIsFillIn());
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.gohome_task.GoHomeContract.PresenterInterface
    public void actionPause() {
        Pdlog.m3275i(getTAG(), "actionPause");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.gohome_task.GoHomePresenter$actionPause$1
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
                GoHomeInterface goHomeInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                goHomeInterface = GoHomePresenter.this.goHomeInterface;
                if (goHomeInterface != null) {
                    goHomeInterface.pause(BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms());
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.gohome_task.GoHomeContract.PresenterInterface
    public void actionCancelTask() {
        Pdlog.m3275i(getTAG(), "actionCancelTask");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.gohome_task.GoHomePresenter$actionCancelTask$1
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
                GoHomeInterface goHomeInterface;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                goHomeInterface = GoHomePresenter.this.goHomeInterface;
                if (goHomeInterface != null) {
                    goHomeInterface.setActive(false, GoHomePresenter.INSTANCE.getMIsFillIn());
                }
                ReportDelivery.INSTANCE.setCurrentTaskDoneOrCancel(false);
                ReportDelivery.INSTANCE.toCloud();
                GoHomePresenter.this.showOnUIThread(GoHomeContract.ViewEvent.CANCEL);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnUIThread(final GoHomeContract.ViewEvent event) {
        Pdlog.m3273d(getTAG(), "showOnUIThread event=" + event + " view=" + getTheView());
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.gohome_task.GoHomePresenter$showOnUIThread$1
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
                GoHomeContract.ViewInterface theView;
                theView = GoHomePresenter.this.getTheView();
                if (theView != null) {
                    theView.showGoHomeEvent(event);
                }
            }
        });
    }
}
