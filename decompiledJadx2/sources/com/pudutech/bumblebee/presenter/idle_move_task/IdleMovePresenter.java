package com.pudutech.bumblebee.presenter.idle_move_task;

import com.google.gson.reflect.TypeToken;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.business.behavior.DelayResumeActive;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.core_devices_task.power_save.PowerSaveEvent;
import com.pudutech.bumblebee.business.core_devices_task.power_save.PowerSaveListener;
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
import com.pudutech.bumblebee.business.peripherals_task.lcd_screed_task.LCDScreenListener;
import com.pudutech.bumblebee.business.robotsdk.RobotPeripherals;
import com.pudutech.bumblebee.business.robotsdk.SDK;
import com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.TouchListener;
import com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract;
import com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter;
import com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter;
import com.pudutech.bumblebee.presenter.report_cloud.task.ReportDelivery;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchPlace;
import com.pudutech.bumblebee.robot.aidl.serialize.TouchState;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IdleMovePresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0004\u000f\u0012\u0015\u0018\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020!H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0013R\u0010\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u0010\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0019¨\u0006\""}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter;", "Lcom/pudutech/bumblebee/presenter/mvp_base/BaseOneViewPresenter;", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewInterface;", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$PresenterInterface;", "()V", "MOVING_MPS", "", "ROTATE_RPS", "TAG", "", "getTAG", "()Ljava/lang/String;", "idleInterface", "Lcom/pudutech/bumblebee/business/movementInterface/IdleInterface;", "lcdListener", "com/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter$lcdListener$1", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter$lcdListener$1;", "powerSaveListener", "com/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter$powerSaveListener$1", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter$powerSaveListener$1;", "speedListener", "com/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter$speedListener$1", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter$speedListener$1;", "touchListener", "com/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter$touchListener$1", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMovePresenter$touchListener$1;", "actionIDLE", "", "actionTimerCount", "onOrOff", "", "showOnUI", "viewEvent", "Lcom/pudutech/bumblebee/presenter/idle_move_task/IdleMoveContract$ViewEvent;", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class IdleMovePresenter extends BaseOneViewPresenter<IdleMoveContract.ViewInterface> implements IdleMoveContract.PresenterInterface {
    private IdleInterface idleInterface;
    private final String TAG = "IdleMovePresenter";
    private IdleMovePresenter$powerSaveListener$1 powerSaveListener = new PowerSaveListener() { // from class: com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter$powerSaveListener$1
        @Override // com.pudutech.bumblebee.business.core_devices_task.power_save.PowerSaveListener
        public void onEvent(PowerSaveEvent event) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            Pdlog.m3275i(IdleMovePresenter.this.getTAG(), "PowerSaveEvenT=" + event);
            int i = IdleMovePresenter.WhenMappings.$EnumSwitchMapping$0[event.ordinal()];
            if (i == 1) {
                IdleMovePresenter.this.showOnUI(IdleMoveContract.ViewEvent.STAND_BY);
            } else if (i != 2) {
                IdleMovePresenter.this.showOnUI(IdleMoveContract.ViewEvent.READY);
            } else {
                IdleMovePresenter.this.showOnUI(IdleMoveContract.ViewEvent.SLEEP);
            }
        }
    };
    private final IdleMovePresenter$touchListener$1 touchListener = new TouchListener() { // from class: com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter$touchListener$1
        @Override // com.pudutech.bumblebee.business.robotsdk.peripherals_listeners.TouchListener
        public void onTouch(TouchPlace place, TouchState state) {
            Intrinsics.checkParameterIsNotNull(place, "place");
            Intrinsics.checkParameterIsNotNull(state, "state");
            Pdlog.m3273d(IdleMovePresenter.this.getTAG(), "on touch " + place + ' ' + state);
            CoreDevices.INSTANCE.getPowerSaveTask().resetTimer();
        }
    };
    private final IdleMovePresenter$lcdListener$1 lcdListener = new LCDScreenListener() { // from class: com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter$lcdListener$1
        @Override // com.pudutech.bumblebee.business.peripherals_task.lcd_screed_task.LCDScreenListener
        public void onTouchEvent() {
            Pdlog.m3273d(IdleMovePresenter.this.getTAG(), "on screen touch");
            CoreDevices.INSTANCE.getPowerSaveTask().resetTimer();
        }
    };
    private final double MOVING_MPS = 0.02d;
    private final double ROTATE_RPS = 0.1d;
    private final IdleMovePresenter$speedListener$1 speedListener = new SpeedListener() { // from class: com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter$speedListener$1
        @Override // com.pudutech.bumblebee.business.robotsdk.sdk_listeners.SpeedListener
        public void onSpeed(double p0, double p1) {
            double d;
            double d2;
            double abs = Math.abs(p0);
            d = IdleMovePresenter.this.MOVING_MPS;
            if (abs <= d) {
                double abs2 = Math.abs(p1);
                d2 = IdleMovePresenter.this.ROTATE_RPS;
                if (abs2 <= d2) {
                    return;
                }
            }
            CoreDevices.INSTANCE.getPowerSaveTask().resetTimer();
        }
    };

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[PowerSaveEvent.values().length];

        static {
            $EnumSwitchMapping$0[PowerSaveEvent.STAND_BY.ordinal()] = 1;
            $EnumSwitchMapping$0[PowerSaveEvent.SLEEP.ordinal()] = 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.pudutech.bumblebee.presenter.mvp_base.BaseOneViewPresenter
    public String getTAG() {
        return this.TAG;
    }

    @Override // com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract.PresenterInterface
    public void actionTimerCount(boolean onOrOff) {
        Pdlog.m3275i(getTAG(), "actionTimerCount " + onOrOff);
        if (onOrOff) {
            SDK.INSTANCE.getSpeedListeners().addListener(this.speedListener);
            RobotPeripherals.INSTANCE.getTouchListeners().addListener(this.touchListener);
            Peripherals.INSTANCE.getLcd().addListener(this.lcdListener);
            CoreDevices.INSTANCE.getPowerSaveTask().addListener(this.powerSaveListener);
        } else {
            SDK.INSTANCE.getSpeedListeners().removeListener(this.speedListener);
            RobotPeripherals.INSTANCE.getTouchListeners().removeListener(this.touchListener);
            Peripherals.INSTANCE.getLcd().removeListener(this.lcdListener);
            CoreDevices.INSTANCE.getPowerSaveTask().removeListener(this.powerSaveListener);
        }
        CoreDevices.INSTANCE.getPowerSaveTask().setCountDown(onOrOff);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showOnUI(final IdleMoveContract.ViewEvent viewEvent) {
        runOnUIThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter$showOnUI$1
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
                IdleMoveContract.ViewInterface theView;
                theView = IdleMovePresenter.this.getTheView();
                if (theView != null) {
                    theView.showIdleEvent(viewEvent);
                }
            }
        });
    }

    @Override // com.pudutech.bumblebee.presenter.idle_move_task.IdleMoveContract.PresenterInterface
    public void actionIDLE() {
        Pdlog.m3275i(getTAG(), "actionIDLE");
        runOnBusinessThread(new Function0<Unit>() { // from class: com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter$actionIDLE$1
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
                IdleInterface idleInterface;
                IdleMovePresenter idleMovePresenter = IdleMovePresenter.this;
                Behavior behavior = Behavior.INSTANCE;
                TypeToken<IdleInterface> typeToken = new TypeToken<IdleInterface>() { // from class: com.pudutech.bumblebee.presenter.idle_move_task.IdleMovePresenter$actionIDLE$1$$special$$inlined$changeMovementTask$1
                };
                Class<? super IdleInterface> rawType = typeToken.getRawType();
                Pdlog.m3275i("Behavior", "change task=" + rawType + ' ' + typeToken.getType() + ' ' + typeToken.getClass() + ' ' + typeToken);
                IdleInterface idleInterface2 = null;
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
                        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.business.movementInterface.IdleInterface");
                    }
                    idleInterface2 = (IdleInterface) movementTask;
                }
                idleMovePresenter.idleInterface = idleInterface2;
                idleInterface = IdleMovePresenter.this.idleInterface;
                if (idleInterface != null) {
                    idleInterface.setActive(false);
                }
                ReportDelivery.INSTANCE.toCloud();
            }
        });
    }
}
