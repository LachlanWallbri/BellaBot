package com.pudutech.bumblebee.presenter.greeter_task;

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
import com.pudutech.bumblebee.presenter.BusinessSetting;
import com.pudutech.bumblebee.presenter.greeter_task.BackToWaitAreaContract;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.performance.MovePerformance;
import com.pudutech.bumblebee.presenter.report_cloud.protocol.delivery.TaskType;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportDelivery;
import com.pudutech.mirsdk.aidl.serialize.MoveTaskMode;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdkwrap.lib.map.Destination;
import com.pudutech.mirsdkwrap.lib.map.DestinationType;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BackToWaitAreaPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 )2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001)B\u0005¢\u0006\u0002\u0010\u0005J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0011H\u0016J\b\u0010\u0017\u001a\u00020\u0011H\u0016J\u0010\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0011H\u0016J\u001c\u0010\u001f\u001a\u00020\u00112\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010 \u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010!\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u0007H\u0016J\u0010\u0010&\u001a\u00020\u00112\u0006\u0010'\u001a\u00020(H\u0002R\u0014\u0010\u0006\u001a\u00020\u0007X\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/greeter_task/BackToWaitAreaPresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/greeter_task/BackToWaitAreaContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/greeter_task/BackToWaitAreaContract$PresenterInterface;", "Lcom/pudutech/bumblebee/business/movementCallback/GoHomeCallback;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "autoMoveActive", "", "Ljava/lang/Integer;", "goHomeInterface", "Lcom/pudutech/bumblebee/business/movementInterface/GoHomeInterface;", "waitArea", "actionActive", "", "actionCancelTask", "actionGoWaitArea", "performance", "Lcom/pudutech/bumblebee/presenter/performance/MovePerformance;", "actionPause", "actionPauseNoTimer", "checkActive", "state", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "forReport", "status", "Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "onDone", "onMovementChanged", "description", "onStatusChanged", "onTaskSetup", "isLegalTask", "", "info", "showOnUIThread", "event", "Lcom/pudutech/bumblebee/presenter/greeter_task/BackToWaitAreaContract$ViewEvent;", "Companion", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class BackToWaitAreaPresenter extends BaseOneViewPresenter<BackToWaitAreaContract.ViewInterface> implements BackToWaitAreaContract.PresenterInterface, GoHomeCallback {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static boolean mIsFillIn;
    private Integer autoMoveActive;
    private GoHomeInterface goHomeInterface;
    private final String TAG = "BackToWaitAreaPresenter";
    private String waitArea = "";

    /* compiled from: BackToWaitAreaPresenter.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/greeter_task/BackToWaitAreaPresenter$Companion;", "", "()V", "mIsFillIn", "", "getMIsFillIn", "()Z", "setMIsFillIn", "(Z)V", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean getMIsFillIn() {
            return BackToWaitAreaPresenter.mIsFillIn;
        }

        public final void setMIsFillIn(boolean z) {
            BackToWaitAreaPresenter.mIsFillIn = z;
        }
    }

    @Override // com.pudutech.bumblebee.presenter.greeter_task.BackToWaitAreaContract.PresenterInterface
    public void actionPauseNoTimer() {
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.greeter_task.BackToWaitAreaPresenter$actionPauseNoTimer$1
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
                goHomeInterface = BackToWaitAreaPresenter.this.goHomeInterface;
                if (goHomeInterface != null) {
                    goHomeInterface.setActive(false);
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
                showOnUIThread(BackToWaitAreaContract.ViewEvent.ON_THE_WAY);
                break;
            case ARRIVAL:
                if (mIsFillIn) {
                    showOnUIThread(BackToWaitAreaContract.ViewEvent.TEMPORARY_POINT);
                    Peripherals.INSTANCE.getFunctionButton().setMute(true);
                    break;
                } else {
                    showOnUIThread(BackToWaitAreaContract.ViewEvent.DONE);
                    break;
                }
            case DONE:
                showOnUIThread(BackToWaitAreaContract.ViewEvent.DONE);
                break;
            case DONE_BEFORE_ARRIVAL:
                showOnUIThread(BackToWaitAreaContract.ViewEvent.DONE);
                break;
        }
        forReport(status);
    }

    private final void forReport(TaskStatus status) {
        if (status == TaskStatus.ON_THE_WAY) {
            ReportDelivery.INSTANCE.goHome(this.waitArea, TaskType.GO_WELCOME_AREA);
        } else {
            if (status != TaskStatus.ARRIVAL || mIsFillIn) {
                return;
            }
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
            int checkActive = checkActive(state);
            Integer num = this.autoMoveActive;
            if (num != null && checkActive == num.intValue()) {
                return;
            }
            this.autoMoveActive = Integer.valueOf(checkActive);
            if (checkActive == 0) {
                showOnUIThread(BackToWaitAreaContract.ViewEvent.ACTIVE);
                ReportDelivery.INSTANCE.setAutoMove(true);
            } else if (checkActive == 1) {
                showOnUIThread(BackToWaitAreaContract.ViewEvent.PAUSE);
                ReportDelivery.INSTANCE.setAutoMove(false);
            } else {
                showOnUIThread(BackToWaitAreaContract.ViewEvent.TEMPORARY_POINT);
                Peripherals.INSTANCE.getFunctionButton().setMute(true);
                ReportDelivery.INSTANCE.setAutoMove(false);
            }
        }
    }

    private final int checkActive(RobotState state) {
        switch (state) {
            case Idle:
                return 1;
            case Moving:
            case Stuck:
            case Approaching:
                return 0;
            case Arrive:
                return mIsFillIn ? 2 : 1;
            case Pause:
                return 1;
            case Resume:
            case Avoid:
                return 0;
            case Error:
                return 1;
            default:
                Pdlog.m3274e(getTAG(), "missing case " + state);
                return 0;
        }
    }

    @Override // com.pudutech.bumblebee.business.movementCallback.BaseTaskCallback
    public void onDone() {
        showOnUIThread(BackToWaitAreaContract.ViewEvent.DONE);
    }

    @Override // com.pudutech.bumblebee.presenter.greeter_task.BackToWaitAreaContract.PresenterInterface
    public void actionGoWaitArea(final MovePerformance performance) {
        Intrinsics.checkParameterIsNotNull(performance, "performance");
        Pdlog.m3275i(getTAG(), "actionGoWaitArea");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.greeter_task.BackToWaitAreaPresenter$actionGoWaitArea$1
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

            /* JADX WARN: Removed duplicated region for block: B:11:0x015e A[LOOP:0: B:9:0x0158->B:11:0x015e, LOOP_END] */
            /* JADX WARN: Removed duplicated region for block: B:16:0x01a9  */
            /* JADX WARN: Removed duplicated region for block: B:25:0x01bf  */
            /* JADX WARN: Removed duplicated region for block: B:39:0x020f  */
            /* JADX WARN: Removed duplicated region for block: B:7:0x0136  */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final void invoke2() {
                GoHomeInterface goHomeInterface;
                GoHomeInterface goHomeInterface2;
                Iterator<T> it;
                String str;
                GoHomeInterface goHomeInterface3;
                GoHomeInterface goHomeInterface4;
                GoHomeInterface goHomeInterface5;
                BackToWaitAreaPresenter.this.autoMoveActive = (Integer) null;
                Peripherals.INSTANCE.getFunctionButton().setMute(false);
                BackToWaitAreaPresenter backToWaitAreaPresenter = BackToWaitAreaPresenter.this;
                Behavior behavior = Behavior.INSTANCE;
                TypeToken<GoHomeInterface> typeToken = new TypeToken<GoHomeInterface>() { // from class: com.pudutech.bumblebee.presenter.greeter_task.BackToWaitAreaPresenter$actionGoWaitArea$1$$special$$inlined$changeMovementTask$1
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
                    backToWaitAreaPresenter.goHomeInterface = goHomeInterface;
                    goHomeInterface2 = BackToWaitAreaPresenter.this.goHomeInterface;
                    if (goHomeInterface2 != null) {
                        goHomeInterface2.setCallback(BackToWaitAreaPresenter.this);
                    }
                    ArrayList<Destination> destinationByType = RobotMapManager.INSTANCE.getDestinationByType(DestinationType.USHER);
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(destinationByType, 10));
                    it = destinationByType.iterator();
                    while (it.hasNext()) {
                        arrayList.add(((Destination) it.next()).getGroup());
                    }
                    ArrayList<String> arrayList2 = arrayList;
                    Pdlog.m3273d(BackToWaitAreaPresenter.this.getTAG(), "actionGoWaitArea : " + arrayList2 + " , def = " + RobotMapManager.INSTANCE.getCurrentMapUsherChosen());
                    str = (String) null;
                    for (String str2 : arrayList2) {
                        if (Intrinsics.areEqual(str2, RobotMapManager.INSTANCE.getCurrentMapUsherChosen())) {
                            str = str2;
                        }
                    }
                    if (str == null) {
                        Pdlog.m3273d(BackToWaitAreaPresenter.this.getTAG(), "go home " + str);
                        if (performance == MovePerformance.STEADY) {
                            goHomeInterface5 = BackToWaitAreaPresenter.this.goHomeInterface;
                            if (goHomeInterface5 != null) {
                                goHomeInterface5.setHome(str, MoveTaskMode.Steady);
                            }
                        } else {
                            goHomeInterface3 = BackToWaitAreaPresenter.this.goHomeInterface;
                            if (goHomeInterface3 != null) {
                                GoHomeInterface.DefaultImpls.setHome$default(goHomeInterface3, str, null, 2, null);
                            }
                        }
                        goHomeInterface4 = BackToWaitAreaPresenter.this.goHomeInterface;
                        if (goHomeInterface4 != null) {
                            goHomeInterface4.setActive(true, BackToWaitAreaPresenter.INSTANCE.getMIsFillIn());
                            return;
                        }
                        return;
                    }
                    Pdlog.m3277w(BackToWaitAreaPresenter.this.getTAG(), "no dining outlet");
                    BackToWaitAreaPresenter.this.showOnUIThread(BackToWaitAreaContract.ViewEvent.NO_DINING_OUTLET);
                    return;
                }
                goHomeInterface = null;
                backToWaitAreaPresenter.goHomeInterface = goHomeInterface;
                goHomeInterface2 = BackToWaitAreaPresenter.this.goHomeInterface;
                if (goHomeInterface2 != null) {
                }
                ArrayList<Destination> destinationByType2 = RobotMapManager.INSTANCE.getDestinationByType(DestinationType.USHER);
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(destinationByType2, 10));
                it = destinationByType2.iterator();
                while (it.hasNext()) {
                }
                ArrayList<String> arrayList22 = arrayList3;
                Pdlog.m3273d(BackToWaitAreaPresenter.this.getTAG(), "actionGoWaitArea : " + arrayList22 + " , def = " + RobotMapManager.INSTANCE.getCurrentMapUsherChosen());
                str = (String) null;
                while (r3.hasNext()) {
                }
                if (str == null) {
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.greeter_task.BackToWaitAreaContract.PresenterInterface
    public void actionActive() {
        Pdlog.m3275i(getTAG(), "actionActive");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.greeter_task.BackToWaitAreaPresenter$actionActive$1
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
                goHomeInterface = BackToWaitAreaPresenter.this.goHomeInterface;
                if (goHomeInterface != null) {
                    goHomeInterface.setActive(true, BackToWaitAreaPresenter.INSTANCE.getMIsFillIn());
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.greeter_task.BackToWaitAreaContract.PresenterInterface
    public void actionPause() {
        Pdlog.m3275i(getTAG(), "actionPause");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.greeter_task.BackToWaitAreaPresenter$actionPause$1
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
                goHomeInterface = BackToWaitAreaPresenter.this.goHomeInterface;
                if (goHomeInterface != null) {
                    goHomeInterface.pause(BusinessSetting.INSTANCE.getNotCruisePauseKeepTime_ms());
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.greeter_task.BackToWaitAreaContract.PresenterInterface
    public void actionCancelTask() {
        Pdlog.m3275i(getTAG(), "actionCancelTask");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.greeter_task.BackToWaitAreaPresenter$actionCancelTask$1
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
                goHomeInterface = BackToWaitAreaPresenter.this.goHomeInterface;
                if (goHomeInterface != null) {
                    goHomeInterface.setActive(false, BackToWaitAreaPresenter.INSTANCE.getMIsFillIn());
                }
                ReportDelivery.INSTANCE.setCurrentTaskDoneOrCancel(false);
                ReportDelivery.INSTANCE.toCloud();
                BackToWaitAreaPresenter.this.showOnUIThread(BackToWaitAreaContract.ViewEvent.DONE);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnUIThread(final BackToWaitAreaContract.ViewEvent event) {
        Pdlog.m3273d(getTAG(), "showOnUIThread event=" + event + " view=" + getTheView());
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.greeter_task.BackToWaitAreaPresenter$showOnUIThread$1
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
                BackToWaitAreaContract.ViewInterface theView;
                theView = BackToWaitAreaPresenter.this.getTheView();
                if (theView != null) {
                    theView.showGoHomeEvent(event);
                }
            }
        });
    }
}
