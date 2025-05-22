package com.pudutech.bumblebee.presenter.robot_open_task;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.business.behavior.Behavior;
import com.pudutech.bumblebee.business.core_devices_task.CoreDevices;
import com.pudutech.bumblebee.business.core_devices_task.battery.PowerState;
import com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.MonitorEvent;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.MonitorListener;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Error;
import com.pudutech.bumblebee.business.core_devices_task.monitor_task.protocol.Errors;
import com.pudutech.bumblebee.business.ims.manager.RobotStatusManager;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdkwrap.lib.move.RobotMoveInterfaceDecorator;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotStatusNotify.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012:\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0015\u0012\u0013\u0018\u00010\u0007¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\t0\u0002j\u0002`\n2\u00020\u000b2\u00020\fB\u0005¢\u0006\u0002\u0010\rJ\u0006\u0010\u000f\u001a\u00020\tJ\u001d\u0010\u0010\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0096\u0002J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J$\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0006\u0010\u001f\u001a\u00020\tR\u000e\u0010\u000e\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000¨\u0006 "}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/RobotStatusNotify;", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/MonitorListener;", "Lkotlin/Function2;", "Lcom/pudutech/mirsdk/aidl/serialize/RobotState;", "Lkotlin/ParameterName;", "name", "p0", "", "p1", "", "Lcom/pudutech/mirsdkwrap/lib/interf/RobotStateListener;", "Lcom/pudutech/bumblebee/business/behavior/Behavior$OnIdleListener;", "Lcom/pudutech/bumblebee/business/core_devices_task/battery/SystemBatteryListener;", "()V", "TAG", "init", "invoke", "onEvent", "event", "Lcom/pudutech/bumblebee/business/core_devices_task/monitor_task/MonitorEvent;", "onIdle", "boolean", "", "onPowerChange", "powerPercent", "", "onStateChange", "powerState", "Lcom/pudutech/bumblebee/business/core_devices_task/battery/PowerState;", "chargeState", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "release", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class RobotStatusNotify implements MonitorListener, Function2<RobotState, String, Unit>, Behavior.OnIdleListener, SystemBatteryListener {
    private final String TAG = RobotStatusManager.TAG;

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(RobotState robotState, String str) {
        invoke2(robotState, str);
        return Unit.INSTANCE;
    }

    public final void init() {
        CoreDevices.INSTANCE.getMonitorTask().addListener(this);
        RobotMoveInterfaceDecorator moveAction = CoreDevices.INSTANCE.getMoveAction();
        if (moveAction != null) {
            moveAction.addOnStateChangeListeners(this);
        }
        Behavior.INSTANCE.getOnIdleListeners().add(this);
    }

    public final void release() {
        CoreDevices.INSTANCE.getMonitorTask().removeListener(this);
        RobotMoveInterfaceDecorator moveAction = CoreDevices.INSTANCE.getMoveAction();
        if (moveAction != null) {
            moveAction.removeOnStateChangeListeners(this);
        }
        Behavior.INSTANCE.getOnIdleListeners().remove(this);
    }

    @Override // com.pudutech.bumblebee.business.core_devices_task.monitor_task.MonitorListener
    public void onEvent(MonitorEvent event) {
        ArrayList arrayList;
        ArrayList<Error> arrayList2;
        Intrinsics.checkParameterIsNotNull(event, "event");
        boolean z = true;
        Pdlog.m3273d(this.TAG, "onEvent event: " + event.getIsError() + ',' + event);
        if (event.getIsError()) {
            Errors errors = event.getErrors();
            if (errors == null || (arrayList2 = errors.list) == null) {
                arrayList = null;
            } else {
                ArrayList arrayList3 = new ArrayList();
                for (Object obj : arrayList2) {
                    Error error = (Error) obj;
                    if (Intrinsics.areEqual(error.level, MoveError.LEVEL_ERROR) || Intrinsics.areEqual(error.level, MoveError.LEVEL_FATAL) || Intrinsics.areEqual(error.level, MoveError.LEVEL_EVENT)) {
                        arrayList3.add(obj);
                    }
                }
                arrayList = arrayList3;
            }
            ArrayList arrayList4 = arrayList;
            if (arrayList4 != null && !arrayList4.isEmpty()) {
                z = false;
            }
            if (z) {
                return;
            }
            RobotNewOpenManager.INSTANCE.reportRobotTaskStatus(2);
        }
    }

    @Override // com.pudutech.bumblebee.business.behavior.Behavior.OnIdleListener
    public void onIdle(boolean r5) {
        Pdlog.m3273d(this.TAG, "onIdle: " + r5);
    }

    @Override // com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener
    public void onStateChange(int powerPercent, PowerState powerState, ChargeState chargeState) {
        Pdlog.m3273d(this.TAG, "onStateChange powerPercent:" + powerPercent + " ,powerState:" + powerState + ",chargeState:" + chargeState);
        RobotNewOpenManager.INSTANCE.reportRobotStatus();
    }

    @Override // com.pudutech.bumblebee.business.core_devices_task.battery.SystemBatteryListener
    public void onPowerChange(int powerPercent) {
        Pdlog.m3273d(this.TAG, "onPowerChange powerPercent: " + powerPercent);
        RobotNewOpenManager.INSTANCE.reportRobotStatus();
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public void invoke2(RobotState p0, String p1) {
        Pdlog.m3273d(this.TAG, "onStateChange state: " + p0 + ",description: " + p1);
    }
}
